package org.androidgraal.android;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.oracle.svm.shared.AlwaysInline;
import java.util.Arrays;
import java.util.Objects;
import org.graalvm.nativeimage.PinnedObject;
import org.graalvm.nativeimage.c.type.CCharPointer;

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
