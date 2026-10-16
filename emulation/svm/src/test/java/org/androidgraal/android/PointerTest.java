package org.androidgraal.android;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.graalvm.nativeimage.PinnedObject;
import org.junit.jupiter.api.Test;

class PointerTest {

    @Test
    void theShiftIsLog2OfTheElementSize() {
        assertEquals(0, Pointer.elementShift(ByteBuffer.allocate(1)));
        assertEquals(1, Pointer.elementShift(ShortBuffer.allocate(1)));
        assertEquals(1, Pointer.elementShift(CharBuffer.allocate(1)));
        assertEquals(2, Pointer.elementShift(IntBuffer.allocate(1)));
        assertEquals(2, Pointer.elementShift(FloatBuffer.allocate(1)));
        assertEquals(3, Pointer.elementShift(LongBuffer.allocate(1)));
        assertEquals(3, Pointer.elementShift(DoubleBuffer.allocate(1)));
        assertEquals(2, Pointer.elementShift(ByteBuffer.allocateDirect(8).asFloatBuffer()));
    }

    @Test
    void aBufferNeedsItsRemainingBytesFromThePosition() {
        FloatBuffer floats = FloatBuffer.allocate(8).position(4);

        assertThrows(UnsupportedOperationException.class, () -> Pointer.pin(floats, 16));
        assertEquals("remaining() 16 bytes < 20", message(() -> Pointer.pin(floats, 20)));
        assertEquals("remaining() 3 bytes < 4", message(() -> Pointer.pin(ByteBuffer.allocateDirect(3), 4)));
        assertEquals("remaining() 4 bytes < 5", message(() -> Pointer.pin(IntBuffer.allocate(1), 5)));
        assertEquals("remaining() 3 bytes < 4", message(() -> Pointer.pinNullable(ByteBuffer.allocateDirect(3), 4)));
    }

    @Test
    void aDirectBufferIsNotPinned() {
        assertNull(Pointer.pin(ByteBuffer.allocateDirect(4), 4));
        assertNull(Pointer.pin(ByteBuffer.allocateDirect(4)));
        assertNull(Pointer.pinNullable(ByteBuffer.allocateDirect(4), 4));
        assertNull(Pointer.pinNullable(ByteBuffer.allocateDirect(4)));
    }

    @Test
    void aHeapBufferIsPinned() {
        assertThrows(UnsupportedOperationException.class, () -> Pointer.pin(IntBuffer.allocate(4)));
        assertThrows(UnsupportedOperationException.class, () -> Pointer.pinNullable(IntBuffer.allocate(4), 16));
        assertThrows(UnsupportedOperationException.class, () -> Pointer.pinNullable(IntBuffer.allocate(4)));
    }

    @Test
    void onlyANullableBufferMayBeNull() {
        assertNull(Pointer.pinNullable(null, 64));
        assertNull(Pointer.pinNullable(null));
        assertTrue(Pointer.address(null, null).isNull());
        assertEquals("null buffer", message(() -> Pointer.pin(null, 0)));
        assertEquals("null buffer", message(() -> Pointer.pin((Buffer) null)));
    }

    @Test
    void anArrayNeedsItsLengthPastTheOffset() {
        int[] ints = new int[4];

        assertThrows(UnsupportedOperationException.class, () -> Pointer.pin(ints, 1, 12));
        assertThrows(UnsupportedOperationException.class, () -> Pointer.pin(new boolean[4], 0, 4));
        assertThrows(UnsupportedOperationException.class, () -> Pointer.pin(new byte[4], 4, 0));
        assertThrows(UnsupportedOperationException.class, () -> Pointer.pin(new float[4], 0, 16));
        assertThrows(UnsupportedOperationException.class, () -> Pointer.pin(new long[2], 0, 16));
        assertEquals("length - offset 12 bytes < 16", message(() -> Pointer.pin(ints, 1, 16)));
        assertEquals("offset -1 outside an array of 4", message(() -> Pointer.pin(ints, -1, 0)));
        assertEquals("offset 5 outside an array of 4", message(() -> Pointer.pin(ints, 5, 0)));
        assertEquals("null array", message(() -> Pointer.pin((int[]) null, 0, 0)));
        assertEquals("length - offset 60 bytes < 64", message(() -> Pointer.pin(new float[15], 0, 64)));
        assertEquals("length - offset 8 bytes < 16", message(() -> Pointer.pin(new long[1], 0, 16)));
        assertEquals("length - offset 4 bytes < 5", message(() -> Pointer.pin(new boolean[4], 0, 5)));
        assertEquals("length - offset 8 bytes < 9", message(() -> Pointer.pin(new byte[8], 0, 9)));
        assertEquals("length - offset 4 bytes < 5", message(() -> Pointer.pin(new int[1], 0, 5)));
    }

    @Test
    void onlyAnIntArrayMayBeNull() {
        assertNull(Pointer.pinNullable((int[]) null, 0, 4));
        assertTrue(Pointer.address((PinnedObject) null, 0).isNull());
        assertThrows(UnsupportedOperationException.class, () -> Pointer.pinNullable(new int[1], 0, 4));
        assertEquals("offset 2 outside an array of 1", message(() -> Pointer.pinNullable(new int[1], 2, 4)));
    }

    @Test
    void aVertexPointerMustBeDirect() {
        assertEquals("not a direct buffer", message(() -> Pointer.direct(FloatBuffer.allocate(4))));
        assertEquals("null buffer", message(() -> Pointer.direct(null)));
    }

    @Test
    void aHeapBufferWithoutAnAccessibleArrayCannotBePinned() {
        assertEquals("a heap buffer must expose its array",
                message(() -> Pointer.pinNullable(FloatBuffer.allocate(4).asReadOnlyBuffer())));
        assertEquals("a heap buffer must expose its array",
                message(() -> Pointer.pin(ByteBuffer.allocate(8).asIntBuffer())));
    }

    @Test
    void aRangeMustLieInsideTheArrayOrTheDirectBuffer() {
        Pointer.checkRange(new byte[8], 2, 6);
        Pointer.checkRange(new byte[8], 8, 0);
        Pointer.checkRange(ByteBuffer.allocateDirect(8).position(4), 0, 8);
        Pointer.checkRange(ByteBuffer.allocateDirect(8).asIntBuffer(), 4, 4);
        Pointer.checkRange(ByteBuffer.allocate(1), -1, 64);
        Pointer.checkRange(new int[1], 0, 64);
        assertEquals("length - offset 6 bytes < 7", message(() -> Pointer.checkRange(new byte[8], 2, 7)));
        assertEquals("offset 9 outside 8 bytes", message(() -> Pointer.checkRange(new byte[8], 9, 0)));
        assertEquals("offset -1 outside 8 bytes", message(() -> Pointer.checkRange(new byte[8], -1, 0)));
        assertEquals("bytes -1 < 0", message(() -> Pointer.checkRange(new byte[8], 0, -1)));
        assertEquals("length - offset 4 bytes < 5",
                message(() -> Pointer.checkRange(ByteBuffer.allocateDirect(8).asIntBuffer(), 4, 5)));
    }

    private static String message(Runnable check) {
        return assertThrows(IllegalArgumentException.class, check::run).getMessage();
    }
}
