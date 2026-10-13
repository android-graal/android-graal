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

package android.os;

import static org.androidgraal.bionic.Ctype.isspace;
import static org.androidgraal.bionic.Stdlib.strtoll;
import static org.androidgraal.bionic.SystemProperties.__system_property_find;
import static org.androidgraal.bionic.SystemProperties.__system_property_read;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.util.MutableInt;

import androidx.annotation.GuardedBy;

import org.androidgraal.android.CString;
import org.androidgraal.android.ScopedUtfChars;
import org.androidgraal.bionic.Dlfcn;
import org.androidgraal.bionic.Errno;
import org.androidgraal.bionic.SystemProperties.SystemPropertyReadCallback;
import org.androidgraal.hosted.annotate.Emulated;
import org.graalvm.nativeimage.CurrentIsolate;
import org.graalvm.nativeimage.PinnedObject;
import org.graalvm.nativeimage.StackValue;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.function.CEntryPoint.IsolateThreadContext;
import org.graalvm.nativeimage.c.function.CEntryPoint.Publish;
import org.graalvm.nativeimage.c.function.CEntryPointLiteral;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CCharPointerPointer;
import org.graalvm.word.PointerBase;
import org.graalvm.word.WordFactory;

import libcore.util.HexEncoding;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Gives access to the system properties store.  The system properties
 * store contains a list of string key-value pairs.
 *
 * <p>Use this class only for the system properties that are local. e.g., within
 * an app, a partition, or a module. For system properties used across the
 * boundaries, formally define them in <code>*.sysprop</code> files and use the
 * auto-generated methods. For more information, see <a href=
 * "https://source.android.com/devices/architecture/sysprops-apis">Implementing
 * System Properties as APIs</a>.</p>
 *
 * {@hide}
 */
@Emulated
public class SystemProperties {
    private static final String TAG = "SystemProperties";
    private static final boolean TRACK_KEY_ACCESS = false;

    /**
     * Android O removed the property name length limit, but com.amazon.kindle 7.8.1.5
     * uses reflection to read this whenever text is selected (http://b/36095274).
     * @hide
     */
    public static final int PROP_NAME_MAX = Integer.MAX_VALUE;

    /** @hide */
    public static final int PROP_VALUE_MAX = 91;

    @GuardedBy("sChangeCallbacks")
    private static final ArrayList<Runnable> sChangeCallbacks = new ArrayList<Runnable>();

    @GuardedBy("sRoReads")
    private static final HashMap<String, MutableInt> sRoReads =
            TRACK_KEY_ACCESS ? new HashMap<>() : null;

    private static void onKeyAccess(String key) {
        if (!TRACK_KEY_ACCESS) return;

        if (key != null && key.startsWith("ro.")) {
            synchronized (sRoReads) {
                MutableInt numReads = sRoReads.getOrDefault(key, null);
                if (numReads == null) {
                    numReads = new MutableInt(0);
                    sRoReads.put(key, numReads);
                }
                numReads.value++;
                if (numReads.value > 3) {
                    Log.d(TAG, "Repeated read (count=" + numReads.value
                            + ") of a read-only system property '" + key + "'",
                            new Exception());
                }
            }
        }
    }

    // The one-argument version of native_get used to be a regular native function. Nowadays,
    // we use the two-argument form of native_get all the time, but we can't just delete the
    // one-argument overload: apps use it via reflection, as the UnsupportedAppUsage annotation
    // indicates. Let's just live with having a Java function with a very unusual name.
    private static String native_get(String key) {
        return native_get(key, "");
    }

    private static String native_get(String key, String def) {
        String[] ret = {def};
        ReadProperty(key, value -> {
            if (value.read() != 0) {
                ret[0] = CString.toJava(value);
            }
        });
        if (ret[0] == null) {
            ret[0] = "";
        }
        return ret[0];
    }

    private static int native_get_int(String key, int def) {
        long[] ret = {def};
        ReadProperty(key, value -> ParseInt(value, ret, Integer.MIN_VALUE, Integer.MAX_VALUE));
        return (int) ret[0];
    }

    private static long native_get_long(String key, long def) {
        long[] ret = {def};
        ReadProperty(key, value -> ParseInt(value, ret, Long.MIN_VALUE, Long.MAX_VALUE));
        return ret[0];
    }

    private static boolean native_get_boolean(String key, boolean def) {
        ParseBoolResult[] parseResult = {ParseBoolResult.kError};
        ReadProperty(key, value -> parseResult[0] = ParseBool(CString.toJava(value)));
        return jbooleanFromParseBoolResult(parseResult[0], def);
    }

    private static long native_find(String name) {
        try (ScopedUtfChars key = new ScopedUtfChars(name)) {
            return __system_property_find(key.get()).rawValue();
        }
    }

    private static String native_get(long handle) {
        String[] ret = {null};
        PointerBase prop = WordFactory.pointer(handle);
        ReadProperty(prop, value -> ret[0] = CString.toJava(value));
        return ret[0];
    }

    private static int native_get_int(long handle, int def) {
        long[] ret = {def};
        PointerBase prop = WordFactory.pointer(handle);
        ReadProperty(prop, value -> ParseInt(value, ret, Integer.MIN_VALUE, Integer.MAX_VALUE));
        return (int) ret[0];
    }

    private static long native_get_long(long handle, long def) {
        long[] ret = {def};
        PointerBase prop = WordFactory.pointer(handle);
        ReadProperty(prop, value -> ParseInt(value, ret, Long.MIN_VALUE, Long.MAX_VALUE));
        return ret[0];
    }

    private static boolean native_get_boolean(long handle, boolean def) {
        ParseBoolResult[] parseResult = {ParseBoolResult.kError};
        PointerBase prop = WordFactory.pointer(handle);
        ReadProperty(prop, value -> parseResult[0] = ParseBool(CString.toJava(value)));
        return jbooleanFromParseBoolResult(parseResult[0], def);
    }

    /**
     * Get the String value for the given {@code key}.
     *
     * @param key the key to lookup
     * @return an empty string if the {@code key} isn't found
     * @hide
     */
    @NonNull
    public static String get(@NonNull String key) {
        if (TRACK_KEY_ACCESS) onKeyAccess(key);
        return native_get(key);
    }

    /**
     * Get the String value for the given {@code key}.
     *
     * @param key the key to lookup
     * @param def the default value in case the property is not set or empty
     * @return if the {@code key} isn't found, return {@code def} if it isn't null, or an empty
     * string otherwise
     * @hide
     */
    @NonNull
    public static String get(@NonNull String key, @Nullable String def) {
        if (TRACK_KEY_ACCESS) onKeyAccess(key);
        return native_get(key, def);
    }

    /**
     * Get the value for the given {@code key}, and return as an integer.
     *
     * @param key the key to lookup
     * @param def a default value to return
     * @return the key parsed as an integer, or def if the key isn't found or
     *         cannot be parsed
     * @hide
     */
    public static int getInt(@NonNull String key, int def) {
        if (TRACK_KEY_ACCESS) onKeyAccess(key);
        return native_get_int(key, def);
    }

    /**
     * Get the value for the given {@code key}, and return as a long.
     *
     * @param key the key to lookup
     * @param def a default value to return
     * @return the key parsed as a long, or def if the key isn't found or
     *         cannot be parsed
     * @hide
     */
    public static long getLong(@NonNull String key, long def) {
        if (TRACK_KEY_ACCESS) onKeyAccess(key);
        return native_get_long(key, def);
    }

    /**
     * Get the value for the given {@code key}, returned as a boolean.
     * Values 'n', 'no', '0', 'false' or 'off' are considered false.
     * Values 'y', 'yes', '1', 'true' or 'on' are considered true.
     * (case sensitive).
     * If the key does not exist, or has any other value, then the default
     * result is returned.
     *
     * @param key the key to lookup
     * @param def a default value to return
     * @return the key parsed as a boolean, or def if the key isn't found or is
     *         not able to be parsed as a boolean.
     * @hide
     */
    public static boolean getBoolean(@NonNull String key, boolean def) {
        if (TRACK_KEY_ACCESS) onKeyAccess(key);
        return native_get_boolean(key, def);
    }

    /**
     * Remove the target callback.
     *
     * @param callback The {@link Runnable} that should be removed.
     * @hide
     */
    public static void removeChangeCallback(@NonNull Runnable callback) {
        synchronized (sChangeCallbacks) {
            if (sChangeCallbacks.contains(callback)) {
                sChangeCallbacks.remove(callback);
            }
        }
    }

    /**
     * Clear all callback changes.
     * @hide
     */
    public static void clearChangeCallbacksForTest() {
        synchronized (sChangeCallbacks) {
            sChangeCallbacks.clear();
        }
    }

    /**
     * Return a {@code SHA-1} digest of the given keys and their values as a
     * hex-encoded string. The ordering of the incoming keys doesn't change the
     * digest result.
     *
     * @hide
     */
    public static @NonNull String digestOf(@NonNull String... keys) {
        Arrays.sort(keys);
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-1");
            for (String key : keys) {
                final String item = key + "=" + get(key) + "\n";
                digest.update(item.getBytes(StandardCharsets.UTF_8));
            }
            return HexEncoding.encodeToString(digest.digest()).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private SystemProperties() {
    }

    /**
     * Look up a property location by name.
     * @name name of the property
     * @return property handle or {@code null} if property isn't set
     * @hide
     */
    @Nullable public static Handle find(@NonNull String name) {
        long nativeHandle = native_find(name);
        if (nativeHandle == 0) {
            return null;
        }
        return new Handle(nativeHandle);
    }

    /**
     * Handle to a pre-located property. Looking up a property handle in advance allows
     * for optimal repeated lookup of a single property.
     * @hide
     */
    public static final class Handle {

        private final long mNativeHandle;

        /**
         * @return Value of the property
         */
        @NonNull public String get() {
            return native_get(mNativeHandle);
        }
        /**
         * @param def default value
         * @return value or {@code def} on parse error
         */
        public int getInt(int def) {
            return native_get_int(mNativeHandle, def);
        }
        /**
         * @param def default value
         * @return value or {@code def} on parse error
         */
        public long getLong(long def) {
            return native_get_long(mNativeHandle, def);
        }
        /**
         * @param def default value
         * @return value or {@code def} on parse error
         */
        public boolean getBoolean(boolean def) {
            return native_get_boolean(mNativeHandle, def);
        }

        private Handle(long nativeHandle) {
            mNativeHandle = nativeHandle;
        }
    }

    private static final SystemPropertyReadCallback sReadCallback = readCallback();

    private static final ThreadLocal<Callback> sCallback = ThreadLocal.withInitial(Callback::new);

    private static void ReadProperty(PointerBase prop, ValueFunctor functor) {
        if (sReadCallback.isNull()) {
            byte[] value = new byte[org.androidgraal.bionic.SystemProperties.PROP_VALUE_MAX()];
            try (PinnedObject pin = PinnedObject.create(value)) {
                CCharPointer chars = pin.addressOfArrayElement(0);
                __system_property_read(prop, WordFactory.nullPointer(), chars);
                functor.accept(chars);
            }
            return;
        }
        Callback callback = sCallback.get();
        callback.functor = functor;
        sReadCallback.call(prop, Thunk.LITERAL.getFunctionPointer(),
                CurrentIsolate.getCurrentThread());
        callback.functor = null;
        Throwable thrown = callback.thrown;
        if (thrown != null) {
            callback.thrown = null;
            if (thrown instanceof Error error) {
                throw error;
            }
            throw (RuntimeException) thrown;
        }
    }

    @CEntryPoint(publishAs = Publish.NotPublished)
    private static void thunk(@IsolateThreadContext long cookie, CCharPointer name,
            CCharPointer value, int serial) {
        try {
            sCallback.get().functor.accept(value);
        } catch (Throwable t) {
            sCallback.get().thrown = t;
        }
    }

    private static void ReadProperty(String key, ValueFunctor functor) {
        try (ScopedUtfChars keyChars = new ScopedUtfChars(key)) {
            PointerBase prop = __system_property_find(keyChars.get());
            if (prop.isNull()) {
                return;
            }
            ReadProperty(prop, functor);
        }
    }

    private static boolean jbooleanFromParseBoolResult(ParseBoolResult parseResult,
            boolean defJ) {
        return switch (parseResult) {
            case kError -> defJ;
            case kFalse -> false;
            case kTrue -> true;
        };
    }

    private static boolean ParseInt(CCharPointer s, long[] out, long min, long max) {
        while (isspace(s.read() & 0xff) != 0) {
            s = s.addressOf(1);
        }

        int base = (s.read(0) == '0' && (s.read(1) == 'x' || s.read(1) == 'X')) ? 16 : 10;
        Errno.errno().write(0);
        CCharPointerPointer end = StackValue.get(CCharPointerPointer.class);
        long result = strtoll(s, end, base);
        if (Errno.errno().read() != 0) {
            return false;
        }
        if (s.equal(end.read()) || end.read().read() != '\0') {
            Errno.errno().write(Errno.EINVAL());
            return false;
        }
        if (result < min || max < result) {
            Errno.errno().write(Errno.ERANGE());
            return false;
        }
        if (out != null) {
            out[0] = result;
        }
        return true;
    }

    private static ParseBoolResult ParseBool(String s) {
        if (s.equals("1") || s.equals("y") || s.equals("yes") || s.equals("on")
                || s.equals("true")) {
            return ParseBoolResult.kTrue;
        }
        if (s.equals("0") || s.equals("n") || s.equals("no") || s.equals("off")
                || s.equals("false")) {
            return ParseBoolResult.kFalse;
        }
        return ParseBoolResult.kError;
    }

    private static SystemPropertyReadCallback readCallback() {
        try (ScopedUtfChars name = new ScopedUtfChars("__system_property_read_callback")) {
            return (SystemPropertyReadCallback) Dlfcn.dlsym(
                    Dlfcn.dlopen(WordFactory.nullPointer(), Dlfcn.RTLD_NOW()), name.get());
        }
    }

    private interface ValueFunctor {
        void accept(CCharPointer value);
    }

    private static final class Callback {
        private ValueFunctor functor;
        private Throwable thrown;
    }

    private enum ParseBoolResult {
        kError, kFalse, kTrue
    }

    private static final class Thunk {
        private static final CEntryPointLiteral<CFunctionPointer> LITERAL =
                CEntryPointLiteral.create(SystemProperties.class, "thunk", long.class,
                        CCharPointer.class, CCharPointer.class, int.class);
    }
}
