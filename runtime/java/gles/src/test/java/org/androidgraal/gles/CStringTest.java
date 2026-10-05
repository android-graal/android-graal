package org.androidgraal.gles;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
