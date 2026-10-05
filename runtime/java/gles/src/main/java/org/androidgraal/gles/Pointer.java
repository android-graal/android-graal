package org.androidgraal.gles;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.TargetClass;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.graalvm.nativeimage.PinnedObject;
import org.graalvm.word.PointerBase;
import org.graalvm.word.WordFactory;

public final class Pointer {

    private Pointer() {
    }

    public static PinnedObject pin(Buffer buffer, long bytes) {
        notNull(buffer);
        checkRemaining(buffer, bytes);
        return pinHeap(buffer);
    }

    public static PinnedObject pinNullable(Buffer buffer, long bytes) {
        return buffer == null ? null : pin(buffer, bytes);
    }

    public static PinnedObject pin(Buffer buffer) {
        notNull(buffer);
        return pinHeap(buffer);
    }

    public static PinnedObject pinNullable(Buffer buffer) {
        return buffer == null ? null : pinHeap(buffer);
    }

    public static PinnedObject pin(byte[] array, int offset, long bytes) {
        checkArray(array == null ? -1 : array.length, 0, offset, bytes);
        return PinnedObject.create(array);
    }

    public static PinnedObject pin(boolean[] array, int offset, long bytes) {
        checkArray(array == null ? -1 : array.length, 0, offset, bytes);
        return PinnedObject.create(array);
    }

    public static PinnedObject pin(int[] array, int offset, long bytes) {
        checkArray(array == null ? -1 : array.length, 2, offset, bytes);
        return PinnedObject.create(array);
    }

    public static PinnedObject pin(float[] array, int offset, long bytes) {
        checkArray(array == null ? -1 : array.length, 2, offset, bytes);
        return PinnedObject.create(array);
    }

    public static PinnedObject pin(long[] array, int offset, long bytes) {
        checkArray(array == null ? -1 : array.length, 3, offset, bytes);
        return PinnedObject.create(array);
    }

    public static PinnedObject pinNullable(int[] array, int offset, long bytes) {
        return array == null ? null : pin(array, offset, bytes);
    }

    public static PointerBase address(Buffer buffer, PinnedObject pin) {
        if (buffer == null) {
            return WordFactory.nullPointer();
        }
        if (pin == null) {
            return directAddress(buffer);
        }
        return pin.addressOfArrayElement(buffer.arrayOffset() + buffer.position());
    }

    public static PointerBase address(PinnedObject pin, int offset) {
        return pin == null ? WordFactory.nullPointer() : pin.addressOfArrayElement(offset);
    }

    public static PointerBase direct(Buffer buffer) {
        notNull(buffer);
        if (!buffer.isDirect()) {
            throw new IllegalArgumentException("not a direct buffer");
        }
        return directAddress(buffer);
    }

    public static PointerBase offset(int offset) {
        return WordFactory.pointer(offset);
    }

    public static int shift(Buffer buffer) {
        if (buffer instanceof ByteBuffer) {
            return 0;
        } else if (buffer instanceof ShortBuffer || buffer instanceof CharBuffer) {
            return 1;
        } else if (buffer instanceof IntBuffer || buffer instanceof FloatBuffer) {
            return 2;
        }
        return 3;
    }

    private static void notNull(Buffer buffer) {
        if (buffer == null) {
            throw new IllegalArgumentException("null buffer");
        }
    }

    private static void checkRemaining(Buffer buffer, long bytes) {
        int shift = shift(buffer);
        if ((long) buffer.remaining() << shift < bytes) {
            throw new IllegalArgumentException("remaining() " + buffer.remaining() + " < " + elements(bytes, shift));
        }
    }

    private static void checkArray(int length, int shift, int offset, long bytes) {
        if (length < 0) {
            throw new IllegalArgumentException("null array");
        }
        if (offset < 0 || offset > length) {
            throw new IllegalArgumentException("offset " + offset + " outside an array of " + length);
        }
        if ((long) (length - offset) << shift < bytes) {
            throw new IllegalArgumentException("length - offset " + (length - offset) + " < " + elements(bytes, shift));
        }
    }

    private static long elements(long bytes, int shift) {
        return (bytes + (1L << shift) - 1) >> shift;
    }

    private static PinnedObject pinHeap(Buffer buffer) {
        if (buffer.isDirect()) {
            return null;
        }
        if (!buffer.hasArray()) {
            throw new IllegalArgumentException("a heap buffer must expose its array");
        }
        return PinnedObject.create(buffer.array());
    }

    private static PointerBase directAddress(Buffer buffer) {
        long address = ((Target_java_nio_Buffer) (Object) buffer).address;
        return WordFactory.pointer(address + ((long) buffer.position() << shift(buffer)));
    }

    @TargetClass(Buffer.class)
    private static final class Target_java_nio_Buffer {
        @Alias
        public long address;
    }
}
