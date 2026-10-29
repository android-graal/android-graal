package org.androidgraal.android;

import com.oracle.svm.shared.AlwaysInline;
import java.nio.Buffer;
import java.util.Objects;
import org.androidgraal.hosted.annotate.Transcribed;
import org.graalvm.nativeimage.PinnedObject;
import org.graalvm.word.WordFactory;

public final class ScopedBytes implements AutoCloseable {

    private final PinnedObject pin;
    private final org.graalvm.word.Pointer pointer;

    @AlwaysInline("the holder is scalar-replaced only when its constructor is inlined")
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/ScopedBytes.h#L32-L46")
    public ScopedBytes(Object bytes) {
        if (Objects.requireNonNull(bytes) instanceof byte[]) {
            pin = PinnedObject.create(bytes);
            pointer = pin.addressOfArrayElement(0);
        } else {
            pin = null;
            pointer = bytes instanceof Buffer buffer && buffer.isDirect()
                    ? (org.graalvm.word.Pointer) Pointer.directBase(buffer)
                    : WordFactory.nullPointer();
        }
    }

    @AlwaysInline("the holder is scalar-replaced only when its constructor is inlined")
    public ScopedBytes(Object bytes, long byteOffset, long byteCount) {
        Pointer.checkRange(bytes, byteOffset, byteCount);
        this(bytes);
    }

    @Transcribed({
            "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/ScopedBytes.h#L73-L75",
            "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/ScopedBytes.h#L81-L83"})
    public org.graalvm.word.Pointer get() {
        return pointer;
    }

    @Override
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/ScopedBytes.h#L48-L52")
    public void close() {
        if (pin != null) {
            pin.close();
        }
    }
}
