package org.androidgraal.art;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.util.Arrays;
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

    @Test
    void aStringArrayBlockHoldsItsCountAndAStringBlockPerElement() {
        String[] a = {"ab", null, ""};
        int length = Blocks.stringArrayLength(a);
        ByteBuffer transport = ByteBuffer.allocate(16 + Blocks.size(length, 1)).order(ByteOrder.nativeOrder());
        Transport.setBlockHeader(transport, 16, length, 1);
        Transport.write(a, transport, 16 + Blocks.HEADER);

        ByteBuffer expected = ByteBuffer
                .allocate(Blocks.HEADER + Blocks.size(2, 2) + Blocks.size(Blocks.NULL, 2) + Blocks.size(0, 2))
                .order(ByteOrder.nativeOrder());
        expected.putInt(3);
        expected.position(Blocks.HEADER);
        expected.putInt(2).putInt(Blocks.size(2, 2)).putChar('a').putChar('b');
        expected.position(Blocks.HEADER + Blocks.size(2, 2));
        expected.putInt(Blocks.NULL).putInt(Blocks.size(Blocks.NULL, 2));
        expected.putInt(0).putInt(Blocks.size(0, 2));
        byte[] written = new byte[length];
        transport.position(16 + Blocks.HEADER);
        transport.get(written);
        assertArrayEquals(expected.array(), written);
        assertArrayEquals(a, Transport.readStringArrayBlock(transport, 16));
    }

    @Test
    void aStringArrayBlockCarriesALongElement() {
        char[] wide = new char[70000];
        Arrays.fill(wide, 'w');
        String[] a = {new String(wide), "x"};
        int length = Blocks.stringArrayLength(a);
        ByteBuffer transport = ByteBuffer.allocate(Blocks.size(length, 1)).order(ByteOrder.nativeOrder());
        Transport.setBlockHeader(transport, 0, length, 1);
        Transport.write(a, transport, Blocks.HEADER);

        assertEquals(70000, transport.getInt(Blocks.HEADER + Blocks.HEADER + Blocks.LENGTH_AT));
        assertArrayEquals(a, Transport.readStringArrayBlock(transport, 0));
    }

    @Test
    void aNullStringArrayBlockReadsNull() {
        Transport.setBlockHeader(block, 0, Blocks.NULL, 1);

        assertNull(Transport.readStringArrayBlock(block, 0));
    }
}
