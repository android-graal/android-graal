package org.androidgraal.art;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import org.androidgraal.shared.Blocks;
import org.junit.jupiter.api.Test;

class TransportTest {

    private static final int CAPACITY = 8;

    private final ByteBuffer block = ByteBuffer.allocate(Blocks.size(CAPACITY, 2)).order(ByteOrder.nativeOrder());

    private final CharBuffer view = block.position(Blocks.HEADER).asCharBuffer();

    private final char[] chars = new char[CAPACITY];

    @Test
    void aStaticStringBlockReadsItsChars() {
        Transport.setBlockHeader(block, 0, 5, 2);
        view.clear();
        view.put("hello");
        view.position(3);

        assertEquals("hello", Transport.readStringBlock(block, view, chars));
    }

    @Test
    void aStaticStringBlockOfNoCharsReadsTheEmptyString() {
        Transport.setBlockHeader(block, 0, 0, 2);

        assertEquals("", Transport.readStringBlock(block, view, chars));
    }

    @Test
    void aNullStaticStringBlockReadsNull() {
        Transport.setBlockHeader(block, 0, Blocks.NULL, 2);

        assertNull(Transport.readStringBlock(block, view, chars));
    }
}
