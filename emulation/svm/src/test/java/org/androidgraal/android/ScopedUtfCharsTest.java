package org.androidgraal.android;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ScopedUtfCharsTest {

    @Test
    void nullIsANullPointerException() {
        assertThrows(NullPointerException.class, () -> new ScopedUtfChars(null));
    }

    @Test
    void aStringIsPinned() {
        assertThrows(UnsupportedOperationException.class, () -> new ScopedUtfChars("läuft"));
        assertThrows(UnsupportedOperationException.class, () -> new ScopedUtfChars(""));
    }
}
