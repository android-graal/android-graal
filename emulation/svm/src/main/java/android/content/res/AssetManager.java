/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.content.res;

import android.annotation.AnyRes;
import android.annotation.ArrayRes;
import android.annotation.AttrRes;
import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.StringRes;
import android.annotation.StyleRes;
import android.annotation.TestApi;
import android.compat.annotation.UnsupportedAppUsage;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.ravenwood.annotation.RavenwoodKeepWholeClass;
import android.ravenwood.annotation.RavenwoodReplace;
import android.ravenwood.annotation.RavenwoodThrow;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseArray;

import com.android.internal.annotations.GuardedBy;
import com.android.internal.annotations.VisibleForTesting;

import org.androidgraal.android.EmulationRoot;
import org.androidgraal.android.FileDescriptors;
import org.androidgraal.android.ScopedBytes;
import org.androidgraal.android.ScopedUtfChars;
import org.androidgraal.bionic.Unistd;
import org.androidgraal.hosted.annotate.Adapted;
import org.androidgraal.hosted.annotate.Added;
import org.androidgraal.hosted.annotate.Emulated;
import org.androidgraal.hosted.annotate.Premise;
import org.androidgraal.hosted.annotate.Transcribed;
import org.androidgraal.shared.MarshalledException;
import org.graalvm.nativeimage.StackValue;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CLibrary;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CLongPointer;
import org.graalvm.word.PointerBase;
import org.graalvm.word.UnsignedWord;
import org.graalvm.word.WordFactory;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Provides access to an application's raw asset files; see {@link Resources}
 * for the way most applications will want to retrieve their resource data.
 * This class presents a lower-level API that allows you to open and read raw
 * files that have been bundled with the application as a simple stream of
 * bytes.
 */
@RavenwoodKeepWholeClass
@CLibrary("android")
@Emulated
@Added(reason = "links libandroid")
public final class AssetManager implements AutoCloseable {
    private static final String TAG = "AssetManager";
    private static final boolean DEBUG_REFS = false;

    /**
     * @hide
     */
    public static final String FRAMEWORK_APK_PATH = getFrameworkApkPath();
    private static final String FRAMEWORK_APK_PATH_DEVICE = "/system/framework/framework-res.apk";
    private static final String FRAMEWORK_APK_PATH_RAVENWOOD = "ravenwood-data/framework-res.apk";

    private static final Object sSync = new Object();

    /**
     * Cookie value to use when the actual cookie is unknown. This value tells the system to search
     * all the ApkAssets for the asset.
     * @hide
     */
    public static final int COOKIE_UNKNOWN = -1;

    /**
     * Mode for {@link #open(String, int)}: no specific information about how
     * data will be accessed.
     */
    public static final int ACCESS_UNKNOWN = 0;
    /**
     * Mode for {@link #open(String, int)}: Read chunks, and seek forward and
     * backward.
     */
    public static final int ACCESS_RANDOM = 1;
    /**
     * Mode for {@link #open(String, int)}: Read sequentially, with an
     * occasional forward seek.
     */
    public static final int ACCESS_STREAMING = 2;
    /**
     * Mode for {@link #open(String, int)}: Attempt to load contents into
     * memory, for fast small reads.
     */
    public static final int ACCESS_BUFFER = 3;

    @GuardedBy("this") private final long[] mOffsets = new long[2];

    // Pointer to native implementation, stuffed inside a long.
    @UnsupportedAppUsage
    @GuardedBy("this") private long mObject;

    // Debug/reference counting implementation.
    @GuardedBy("this") private boolean mOpen = true;
    private AtomicInteger mNumRefs = new AtomicInteger(1);
    @GuardedBy("this") private HashMap<Long, RuntimeException> mRefStacks;

    @RavenwoodReplace
    private static String getFrameworkApkPath() {
        return FRAMEWORK_APK_PATH_DEVICE;
    }

    /**
     * Private constructor that doesn't call ensureSystemAssets.
     * Used for the creation of system assets.
     */
    @SuppressWarnings("unused")
    private AssetManager(boolean sentinel) {
        mObject = nativeCreate();
        if (DEBUG_REFS) {
            mNumRefs.set(0);
            incRefsLocked(hashCode());
        }
    }

    /**
     * Close this asset manager.
     */
    @Override
    public void close() {
        synchronized (this) {
            if (!mOpen) {
                return;
            }

            mOpen = false;
            decRefs(hashCode());
        }
    }

    /**
     * Invalidates the caches in this AssetManager according to the bitmask `diff`.
     *
     * @param diff The bitmask of changes generated by {@link Configuration#diff(Configuration)}.
     * @see ActivityInfo.Config
     */
    private void invalidateCachesLocked(int diff) {
        // TODO(adamlesinski): Currently there are no caches to invalidate in Java code.
    }

    /**
     * Ensures that the native implementation has not been destroyed.
     * The AssetManager may have been closed, but references to it still exist
     * and therefore the native implementation is not destroyed.
     */
    @GuardedBy("this")
    private void ensureValidLocked() {
        if (mObject == 0) {
            throw new RuntimeException("AssetManager has been destroyed");
        }
    }

    /**
     * Ensures that the AssetManager has not been explicitly closed. If this method passes,
     * then this implies that ensureValidLocked() also passes.
     */
    @GuardedBy("this")
    private void ensureOpenLocked() {
        // If mOpen is true, this implies that mObject != 0.
        if (!mOpen) {
            throw new RuntimeException("AssetManager has been closed");
        }
        // Let's still check if the native object exists, given all the memory corruptions.
        if (mObject == 0) {
            throw new RuntimeException("AssetManager is open but the native object is gone");
        }
    }

    /**
     * Open an asset using ACCESS_STREAMING mode.  This provides access to
     * files that have been bundled with an application as assets -- that is,
     * files placed in to the "assets" directory.
     *
     * @param fileName The name of the asset to open.  This name can be hierarchical.
     *
     * @see #open(String, int)
     * @see #list
     */
    public @NonNull InputStream open(@NonNull String fileName) throws IOException {
        return open(fileName, ACCESS_STREAMING);
    }

    /**
     * Open an asset using an explicit access mode, returning an InputStream to
     * read its contents.  This provides access to files that have been bundled
     * with an application as assets -- that is, files placed in to the
     * "assets" directory.
     *
     * @param fileName The name of the asset to open.  This name can be hierarchical.
     * @param accessMode Desired access mode for retrieving the data.
     *
     * @see #ACCESS_UNKNOWN
     * @see #ACCESS_STREAMING
     * @see #ACCESS_RANDOM
     * @see #ACCESS_BUFFER
     * @see #open(String)
     * @see #list
     */
    public @NonNull InputStream open(@NonNull String fileName, int accessMode) throws IOException {
        Objects.requireNonNull(fileName, "fileName");
        synchronized (this) {
            ensureOpenLocked();
            final long asset = nativeOpenAsset(mObject, fileName, accessMode);
            if (asset == 0) {
                throw new FileNotFoundException("Asset file: " + fileName);
            }
            final AssetInputStream assetInputStream = new AssetInputStream(asset);
            incRefsLocked(assetInputStream.hashCode());
            return assetInputStream;
        }
    }

    /**
     * Open an uncompressed asset by mmapping it and returning an {@link AssetFileDescriptor}.
     * This provides access to files that have been bundled with an application as assets -- that
     * is, files placed in to the "assets" directory.
     *
     * The asset must be uncompressed, or an exception will be thrown.
     *
     * @param fileName The name of the asset to open.  This name can be hierarchical.
     * @return An open AssetFileDescriptor.
     */
    public @NonNull AssetFileDescriptor openFd(@NonNull String fileName) throws IOException {
        Objects.requireNonNull(fileName, "fileName");
        synchronized (this) {
            ensureOpenLocked();
            final ParcelFileDescriptor pfd = nativeOpenAssetFd(mObject, fileName, mOffsets);
            if (pfd == null) {
                throw new FileNotFoundException("Asset file: " + fileName);
            }
            return new AssetFileDescriptor(pfd, mOffsets[0], mOffsets[1]);
        }
    }

    /**
     * Return a String array of all the assets at the given path.
     *
     * @param path A relative path within the assets, i.e., "docs/home.html".
     *
     * @return String[] Array of strings, one for each asset.  These file
     *         names are relative to 'path'.  You can open the file by
     *         concatenating 'path' and a name in the returned string (via
     *         File) and passing that to open().
     *
     * @see #open
     */
    public @Nullable String[] list(@NonNull String path) throws IOException {
        Objects.requireNonNull(path, "path");
        synchronized (this) {
            ensureValidLocked();
            return nativeList(mObject, path);
        }
    }

    @Override
    @Adapted(reason = "empty", premise = Premise.NO_FINALIZER)
    protected void finalize() throws Throwable {
    }

    /* No Locking is needed for AssetInputStream because an AssetInputStream is not-thread
    safe and it does not rely on AssetManager once it has been created. It completely owns the
    underlying Asset. */
    public final class AssetInputStream extends InputStream {
        private long mAssetNativePtr;
        private long mLength;
        private long mMarkPos;

        /**
         * @hide
         */
        @UnsupportedAppUsage
        public final int getAssetInt() {
            throw new UnsupportedOperationException();
        }

        /**
         * @hide
         */
        @UnsupportedAppUsage
        public final long getNativeAsset() {
            return mAssetNativePtr;
        }

        private AssetInputStream(long assetNativePtr) {
            mAssetNativePtr = assetNativePtr;
            mLength = nativeAssetGetLength(assetNativePtr);
        }

        @Override
        public final int read() throws IOException {
            ensureOpen();
            return nativeAssetReadChar(mAssetNativePtr);
        }

        @Override
        public final int read(@NonNull byte[] b) throws IOException {
            ensureOpen();
            Objects.requireNonNull(b, "b");
            return nativeAssetRead(mAssetNativePtr, b, 0, b.length);
        }

        @Override
        public final int read(@NonNull byte[] b, int off, int len) throws IOException {
            ensureOpen();
            Objects.requireNonNull(b, "b");
            return nativeAssetRead(mAssetNativePtr, b, off, len);
        }

        @Override
        public final long skip(long n) throws IOException {
            ensureOpen();
            long pos = nativeAssetSeek(mAssetNativePtr, 0, 0);
            if ((pos + n) > mLength) {
                n = mLength - pos;
            }
            if (n > 0) {
                nativeAssetSeek(mAssetNativePtr, n, 0);
            }
            return n;
        }

        @Override
        public final int available() throws IOException {
            ensureOpen();
            final long len = nativeAssetGetRemainingLength(mAssetNativePtr);
            return len > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) len;
        }

        @Override
        public final boolean markSupported() {
            return true;
        }

        @Override
        public final void mark(int readlimit) {
            ensureOpen();
            mMarkPos = nativeAssetSeek(mAssetNativePtr, 0, 0);
        }

        @Override
        public final void reset() throws IOException {
            ensureOpen();
            nativeAssetSeek(mAssetNativePtr, mMarkPos, -1);
        }

        @Override
        public final void close() throws IOException {
            if (mAssetNativePtr != 0) {
                nativeAssetDestroy(mAssetNativePtr);
                mAssetNativePtr = 0;

                decRefs(hashCode());
            }
        }

        @Override
        @Adapted(reason = "empty", premise = Premise.NO_FINALIZER)
        protected void finalize() throws Throwable {
        }

        private void ensureOpen() {
            if (mAssetNativePtr == 0) {
                throw new IllegalStateException("AssetInputStream is closed");
            }
        }
    }

    /**
     * Get the locales that this asset manager contains data for.
     *
     * <p>On SDK 21 (Android 5.0: Lollipop) and above, Locale strings are valid
     * <a href="https://tools.ietf.org/html/bcp47">BCP-47</a> language tags and can be
     * parsed using {@link Locale#forLanguageTag(String)}.
     *
     * <p>On SDK 20 (Android 4.4W: KitKat for watches) and below, locale strings
     * are of the form {@code ll_CC} where {@code ll} is a two letter language code,
     * and {@code CC} is a two letter country code.
     */
    public String[] getLocales() {
        synchronized (this) {
            ensureValidLocked();
            return nativeGetLocales(mObject, false /*excludeSystem*/);
        }
    }

    @GuardedBy("this")
    private void incRefsLocked(long id) {
        if (DEBUG_REFS) {
            if (mRefStacks == null) {
                mRefStacks = new HashMap<>();
            }
            RuntimeException ex = new RuntimeException();
            mRefStacks.put(id, ex);
        }
        mNumRefs.incrementAndGet();
    }

    @Adapted(reason = "without resetting mApkAssets", premise = Premise.HOST_OWNED)
    private void decRefs(long id) {
        if (DEBUG_REFS) {
            synchronized (this) {
                if (mRefStacks != null) {
                    mRefStacks.remove(id);
                }
            }
        }
        if (mNumRefs.decrementAndGet() == 0) {
            synchronized (this) {
                if (mNumRefs.get() == 0 && mObject != 0) {
                    nativeDestroy(mObject);
                    mObject = 0;
                }
            }
        }
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_util_AssetManager.cpp#L260-L292")
    private static ParcelFileDescriptor returnParcelFileDescriptor(PointerBase asset,
            long[] outOffsets) throws FileNotFoundException {
        CLongPointer startOffset = StackValue.get(CLongPointer.class);
        CLongPointer length = StackValue.get(CLongPointer.class);
        int fd = AAsset_openFileDescriptor64(asset, startOffset, length);
        AAsset_close(asset);

        if (fd < 0) {
            throw new FileNotFoundException(
                    "This file can not be opened as a file descriptor; it is probably "
                    + "compressed");
        }

        outOffsets[0] = startOffset.read();
        outOffsets[1] = length.read();

        return new ParcelFileDescriptor(FileDescriptors.create(fd));
    }

    // AssetManager setup native methods.
    @Adapted(reason = "the application's AAssetManager from ART instead of a new native one",
            premise = Premise.HOST_OWNED)
    private static long nativeCreate() {
        return EmulationRoot.host().assetManager();
    }

    @Adapted(reason = "empty instead of destroying a native AssetManager",
            premise = Premise.HOST_OWNED)
    private static void nativeDestroy(long ptr) {
    }

    // File native methods.
    @Adapted(reason = "lists through ART's AssetManager instead of the native",
            premise = Premise.HOST_OWNED)
    private static @Nullable String[] nativeList(long ptr, @NonNull String path)
            throws IOException {
        try {
            return EmulationRoot.host().listAssets(path);
        } catch (MarshalledException e) {
            throw io(e);
        }
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_util_AssetManager.cpp#L513-L537")
    private static long nativeOpenAsset(long ptr, @NonNull String fileName, int accessMode)
            throws FileNotFoundException {
        try (ScopedUtfChars assetPathUtf8 = new ScopedUtfChars(fileName)) {
            if (accessMode != ACCESS_UNKNOWN && accessMode != ACCESS_RANDOM
                    && accessMode != ACCESS_STREAMING && accessMode != ACCESS_BUFFER) {
                throw new IllegalArgumentException("Bad access mode");
            }

            PointerBase asset = AAssetManager_open(WordFactory.pointer(ptr), assetPathUtf8.get(),
                    accessMode);
            if (asset.isNull()) {
                throw new FileNotFoundException(fileName);
            }
            return asset.rawValue();
        }
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_util_AssetManager.cpp#L539-L556")
    private static @Nullable ParcelFileDescriptor nativeOpenAssetFd(long ptr,
            @NonNull String fileName, long[] outOffsets) throws IOException {
        try (ScopedUtfChars assetPathUtf8 = new ScopedUtfChars(fileName)) {
            PointerBase asset = AAssetManager_open(WordFactory.pointer(ptr), assetPathUtf8.get(),
                    ACCESS_RANDOM);
            if (asset.isNull()) {
                throw new FileNotFoundException(fileName);
            }
            return returnParcelFileDescriptor(asset, outOffsets);
        }
    }

    // Resource name/ID native methods.
    @Adapted(reason = "the locales of ART's AssetManager instead of the native",
            premise = Premise.HOST_OWNED)
    private static @Nullable String[] nativeGetLocales(long ptr, boolean excludeSystem) {
        return EmulationRoot.host().locales();
    }

    // AssetInputStream related native methods.
    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_util_AssetManager.cpp#L1502-L1504")
    private static void nativeAssetDestroy(long assetPtr) {
        AAsset_close(WordFactory.pointer(assetPtr));
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_util_AssetManager.cpp#L1506-L1511")
    private static int nativeAssetReadChar(long assetPtr) {
        PointerBase asset = WordFactory.pointer(assetPtr);
        CCharPointer b = StackValue.get(CCharPointer.class);
        int res = AAsset_read(asset, b, WordFactory.unsigned(1));
        return res == 1 ? Byte.toUnsignedInt(b.read()) : -1;
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_util_AssetManager.cpp#L1513-L1538")
    private static int nativeAssetRead(long assetPtr, byte[] b, int off, int len)
            throws IOException {
        if (len == 0) {
            return 0;
        }

        int bufferLen = b.length;
        if (off < 0 || off >= bufferLen || len < 0 || len > bufferLen
                || off > bufferLen - len) {
            throw new IndexOutOfBoundsException("");
        }

        try (ScopedBytes byteArray = new ScopedBytes(b)) {
            PointerBase asset = WordFactory.pointer(assetPtr);
            int res = AAsset_read(asset, byteArray.get().add(off), WordFactory.unsigned(len));
            if (res < 0) {
                throw new IOException("");
            }
            return res > 0 ? res : -1;
        }
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_util_AssetManager.cpp#L1540-L1545")
    private static long nativeAssetSeek(long assetPtr, long offset, int whence) {
        PointerBase asset = WordFactory.pointer(assetPtr);
        return AAsset_seek64(asset, offset,
                (whence > 0 ? Unistd.SEEK_END() : (whence < 0 ? Unistd.SEEK_SET() : Unistd.SEEK_CUR())));
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_util_AssetManager.cpp#L1547-L1550")
    private static long nativeAssetGetLength(long assetPtr) {
        PointerBase asset = WordFactory.pointer(assetPtr);
        return AAsset_getLength64(asset);
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_util_AssetManager.cpp#L1552-L1555")
    private static long nativeAssetGetRemainingLength(long assetPtr) {
        PointerBase asset = WordFactory.pointer(assetPtr);
        return AAsset_getRemainingLength64(asset);
    }

    @Added(reason = "turns ART's exception into the IOException the native throws")
    private static IOException io(MarshalledException e) {
        if (!(e.getCause() instanceof MarshalledException cause)) {
            throw e;
        }
        return switch (cause.getForeignExceptionClassName()) {
            case "java.io.FileNotFoundException" ->
                    (FileNotFoundException) new FileNotFoundException(cause.getMessage())
                            .initCause(e);
            case "java.io.IOException" -> new IOException(cause.getMessage(), e);
            default -> throw e;
        };
    }

    @CFunction
    @Added(reason = "binds libandroid's AAssetManager_open")
    private static native PointerBase AAssetManager_open(PointerBase mgr, CCharPointer filename,
            int mode);

    @CFunction
    @Added(reason = "binds libandroid's AAsset_read")
    private static native int AAsset_read(PointerBase asset, PointerBase buf, UnsignedWord count);

    @CFunction
    @Added(reason = "binds libandroid's AAsset_seek64")
    private static native long AAsset_seek64(PointerBase asset, long offset, int whence);

    @CFunction
    @Added(reason = "binds libandroid's AAsset_close")
    private static native void AAsset_close(PointerBase asset);

    @CFunction
    @Added(reason = "binds libandroid's AAsset_getLength64")
    private static native long AAsset_getLength64(PointerBase asset);

    @CFunction
    @Added(reason = "binds libandroid's AAsset_getRemainingLength64")
    private static native long AAsset_getRemainingLength64(PointerBase asset);

    @CFunction
    @Added(reason = "binds libandroid's AAsset_openFileDescriptor64")
    private static native int AAsset_openFileDescriptor64(PointerBase asset, CLongPointer outStart,
            CLongPointer outLength);
}
