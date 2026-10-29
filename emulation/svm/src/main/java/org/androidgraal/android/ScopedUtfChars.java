package org.androidgraal.android;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.oracle.svm.shared.AlwaysInline;
import java.util.Arrays;
import java.util.Objects;
import org.androidgraal.hosted.annotate.Adapted;
import org.androidgraal.hosted.annotate.Premise;
import org.androidgraal.hosted.annotate.Transcribed;
import org.graalvm.nativeimage.PinnedObject;
import org.graalvm.nativeimage.c.type.CCharPointer;

@Transcribed("https://android.googlesource.com/platform/libnativehelper/+/refs/tags/android-16.0.0_r1/header_only_include/nativehelper/scoped_utf_chars.h#L42-L108")
@Adapted(reason = "encodes standard UTF-8 and counts its bytes", premise = Premise.STANDARD_UTF8)
public final class ScopedUtfChars implements AutoCloseable {

    private final PinnedObject pin;
    private final CCharPointer chars;
    private final int size;

    @AlwaysInline("the holder is scalar-replaced only when its constructor is inlined")
    public ScopedUtfChars(String value) {
        byte[] bytes = Objects.requireNonNull(value).getBytes(UTF_8);
        pin = PinnedObject.create(Arrays.copyOf(bytes, bytes.length + 1));
        chars = pin.addressOfArrayElement(0);
        size = bytes.length;
    }

    public CCharPointer get() {
        return chars;
    }

    public int size() {
        return size;
    }

    @Override
    public void close() {
        pin.close();
    }
}
