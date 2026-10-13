package org.androidgraal.android;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.Test;

class ScopedBytesTest {

    @Test
    void nullIsANullPointerExceptionBeforeTheRange() {
        assertThrows(NullPointerException.class, () -> new ScopedBytes(null));
        assertThrows(NullPointerException.class, () -> new ScopedBytes(null, -1, -1));
    }

    @Test
    void aRangeIsCheckedBeforeAnythingIsPinned() {
        assertEquals("length - offset 6 bytes < 7", message(() -> new ScopedBytes(new byte[8], 2, 7)));
        assertEquals("offset 9 outside 8 bytes", message(() -> new ScopedBytes(ByteBuffer.allocateDirect(8), 9, 0)));
        assertThrows(UnsupportedOperationException.class, () -> new ScopedBytes(new byte[8], 2, 6));
    }

    @Test
    void aByteArrayIsPinned() {
        assertThrows(UnsupportedOperationException.class, () -> new ScopedBytes(new byte[4]));
        assertThrows(UnsupportedOperationException.class, () -> new ScopedBytes(ByteBuffer.allocate(4).array()));
    }

    @Test
    void anythingElseIsTheNullPointerAndNothingIsPinned() {
        assertTrue(new ScopedBytes(ByteBuffer.allocate(4)).get().isNull());
        assertTrue(new ScopedBytes(ByteBuffer.allocate(1), -1, 64).get().isNull());
        assertTrue(new ScopedBytes(new int[1]).get().isNull());
    }

    private static String message(Runnable check) {
        return assertThrows(IllegalArgumentException.class, check::run).getMessage();
    }
}
