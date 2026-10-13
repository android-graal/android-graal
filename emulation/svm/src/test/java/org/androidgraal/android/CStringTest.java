package org.androidgraal.android;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CStringTest {

    @Test
    void theLengthGlWroteIsClampedToTheArray() {
        byte[] chars = "läuft\0\0".getBytes(UTF_8);

        assertEquals("läuft", CString.toJava(chars, 6));
        assertEquals("läu", CString.toJava(chars, 4));
        assertEquals("", CString.toJava(chars, -1));
        assertEquals("läuft\0\0", CString.toJava(chars, 100));
        assertEquals("", CString.toJava(new byte[0], 0));
    }

    @Test
    void aLengthMustNotPassTheStringsBytes() {
        assertEquals("length 7 > 6 bytes of the string",
                assertThrows(IllegalArgumentException.class, () -> CString.of("läuft", 7)).getMessage());
        assertEquals("length 1 > 0 bytes of the string",
                assertThrows(IllegalArgumentException.class, () -> CString.ofNullable("", 1)).getMessage());
        assertThrows(IllegalArgumentException.class, () -> CString.of(null, 0));
    }

    @Test
    void aNullableStringMayBeNull() {
        assertDoesNotThrow(() -> CString.ofNullable(null, 5).close());
    }
}
