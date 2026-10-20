package org.androidgraal.android;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import android.os.ParcelFormatException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import org.junit.jupiter.api.Test;

class ParcelDataTest {

    @Test
    void aStringIsItsUtf16UnitsWithATerminatorPaddedToFourBytes() {
        ParcelData parcel = new ParcelData();
        parcel.writeString16("ab");
        assertBytes(parcel, "02 00 00 00 61 00 62 00 00 00 00 00");
        parcel.setDataPosition(0);
        assertEquals("ab", parcel.readString16());
        assertEquals(12, parcel.dataPosition());
    }

    @Test
    void theEmptyAndTheNullStringOfBothForms() {
        ParcelData parcel = new ParcelData();
        parcel.writeString16("");
        parcel.writeString16(null);
        parcel.writeString8("");
        parcel.writeString8(null);
        assertBytes(parcel, "00 00 00 00 00 00 00 00 FF FF FF FF 00 00 00 00 00 00 00 00 FF FF FF FF");
        parcel.setDataPosition(0);
        assertEquals("", parcel.readString16());
        assertNull(parcel.readString16());
        assertEquals("", parcel.readString8());
        assertNull(parcel.readString8());
    }

    @Test
    void anAsciiString8IsItsBytesWithATerminatorPaddedToFourBytes() {
        ParcelData parcel = new ParcelData();
        parcel.writeString8("ab");
        assertBytes(parcel, "02 00 00 00 61 62 00 00");
        parcel.setDataPosition(0);
        assertEquals("ab", parcel.readString8());
    }

    @Test
    void aString8WritesNulAsTwoBytesAndASurrogatePairAsFourAsArtDoes() {
        ParcelData parcel = new ParcelData();
        parcel.writeString8("a\u0000\uD83D\uDE00\uD83Db");
        assertBytes(parcel, "0B 00 00 00 61 C0 80 F0 9F 98 80 ED A0 BD 62 00");
        parcel.setDataPosition(0);
        assertEquals("a\u0000\uD83D\uDE00\uD83Db", parcel.readString8());
    }

    @Test
    void aLongFollowsAnIntWithoutAGap() {
        ParcelData parcel = new ParcelData();
        parcel.writeInt32(1);
        parcel.writeInt64(2);
        assertBytes(parcel, "01 00 00 00 02 00 00 00 00 00 00 00");
        parcel.setDataPosition(0);
        assertEquals(1, parcel.readInt32());
        assertEquals(2, parcel.readInt64());
    }

    @Test
    void floatsAndDoublesAreTheirIeeeBits() {
        ParcelData parcel = new ParcelData();
        parcel.writeFloat(1.0f);
        parcel.writeDouble(1.0);
        assertBytes(parcel, "00 00 80 3F 00 00 00 00 00 00 F0 3F");
        parcel.setDataPosition(0);
        assertEquals(1.0f, parcel.readFloat());
        assertEquals(1.0, parcel.readDouble());
    }

    @Test
    void paddingIsZeroedEvenOverOlderBytes() {
        ParcelData parcel = new ParcelData();
        parcel.writeInt32(-1);
        parcel.writeInt32(-1);
        parcel.setDataPosition(0);
        parcel.writeByteArray(new byte[] {1, 2, 3, 4, 5}, 1, 2);
        assertBytes(parcel, "02 00 00 00 02 03 00 00");
        parcel.setDataPosition(0);
        parcel.writeByteArray(new byte[] {1, 2, 3}, 0, 3);
        assertBytes(parcel, "03 00 00 00 01 02 03 00");
        ParcelData empty = new ParcelData();
        empty.writeByteArray(new byte[0], 0, 0);
        assertBytes(empty, "00 00 00 00");
    }

    @Test
    void theBlobIsAlwaysInline() {
        ParcelData parcel = new ParcelData();
        parcel.writeBlob(new byte[] {1, 2, 3}, 0, 3);
        assertBytes(parcel, "03 00 00 00 00 00 00 00 01 02 03 00");
        ParcelData large = new ParcelData();
        large.writeBlob(new byte[65536], 0, 65536);
        assertEquals(65536 + 8, large.dataSize());
        large.setDataPosition(0);
        assertEquals(65536, large.readBlob().length);
        parcel.setDataPosition(0);
        assertArrayEquals(new byte[] {1, 2, 3}, parcel.readBlob());
    }

    @Test
    void theFirstWriteAllocatesAtLeast128BytesAndGrowthIsHalfAgainTheNeed() {
        ParcelData parcel = new ParcelData();
        assertEquals(0, parcel.dataCapacity());
        parcel.writeInt32(1);
        assertEquals(128, parcel.dataCapacity());
        for (int i = 1; i < 32; i++) {
            parcel.writeInt32(i);
        }
        assertEquals(128, parcel.dataCapacity());
        parcel.writeInt32(32);
        assertEquals((128 + 4) * 3 / 2, parcel.dataCapacity());
        parcel.setDataCapacity(1000);
        assertEquals(1000, parcel.dataCapacity());
        parcel.setDataCapacity(10);
        assertEquals(1000, parcel.dataCapacity());
        parcel.setDataPosition(0);
        for (int i = 0; i <= 32; i++) {
            assertEquals(i == 0 ? 1 : i, parcel.readInt32());
        }
    }

    @Test
    void theSizeIsTheLargerOfTheWrittenEndAndThePosition() {
        ParcelData parcel = new ParcelData();
        parcel.writeInt32(1);
        parcel.writeInt32(2);
        parcel.setDataPosition(12);
        assertEquals(12, parcel.dataSize());
        assertEquals(0, parcel.dataAvail());
        parcel.setDataPosition(4);
        assertEquals(8, parcel.dataSize());
        assertEquals(4, parcel.dataAvail());
        parcel.setDataPosition(20);
        assertEquals(0, parcel.readInt32());
        assertEquals(20, parcel.dataPosition());
        parcel.setDataPosition(0);
        parcel.setDataSize(4);
        assertEquals(4, parcel.dataSize());
        assertEquals(1, parcel.readInt32());
        assertEquals(0, parcel.readInt32());
        assertEquals(4, parcel.dataPosition());
    }

    @Test
    void aWritePastTheSizeBeyondTheCapacityIsRefused() {
        ParcelData parcel = new ParcelData();
        parcel.writeInt32(1);
        parcel.setDataPosition(200);
        assertEquals(-22, parcel.writeInt32(2));
        assertThrows(IllegalArgumentException.class, () -> ParcelData.signalExceptionForError(-22));
        parcel.setDataPosition(64);
        assertEquals(0, parcel.writeInt32(3));
        assertEquals(68, parcel.dataSize());
    }

    @Test
    void unmarshallLeavesThePositionAfterThePaddedBytes() {
        ParcelData parcel = new ParcelData();
        parcel.unmarshall(new byte[] {1, 2, 3, 4, 5}, 0, 5);
        assertEquals(8, parcel.dataPosition());
        assertEquals(8, parcel.dataSize());
        assertBytes(parcel, "01 02 03 04 05 00 00 00");
        ParcelData buffer = new ParcelData();
        ByteBuffer direct = ByteBuffer.allocateDirect(6).put(hex("09 01 02 03 04 05"));
        buffer.unmarshall(direct, 1, 4);
        assertBytes(buffer, "01 02 03 04");
        ParcelData untouched = new ParcelData();
        untouched.writeInt32(7);
        untouched.unmarshall(new byte[0], 0, -1);
        assertBytes(untouched, "07 00 00 00");
    }

    @Test
    void marshallIntoACallersBufferNeedsRoomForTheWholeSize() {
        ParcelData parcel = new ParcelData();
        parcel.writeInt32(0x04030201);
        byte[] array = new byte[6];
        assertEquals(4, parcel.marshall(array, 1, 5));
        assertArrayEquals(hex("00 01 02 03 04 00"), array);
        assertThrows(BufferOverflowException.class, () -> parcel.marshall(array, 0, 3));
        ByteBuffer direct = ByteBuffer.allocateDirect(8);
        assertEquals(4, parcel.marshall(direct, 2, 6));
        assertEquals(0x01, direct.get(2));
        assertEquals(0x04, direct.get(5));
    }

    @Test
    void appendFromCopiesARangeAndGrowsTheSizeByItsLength() {
        ParcelData source = new ParcelData();
        source.writeInt32(1);
        source.writeInt32(2);
        source.writeInt32(3);
        ParcelData target = new ParcelData();
        target.writeInt32(9);
        target.writeInt32(9);
        target.setDataPosition(0);
        target.appendFrom(source, 4, 8);
        assertEquals(8, target.dataPosition());
        assertEquals(16, target.dataSize());
        target.setDataPosition(0);
        assertEquals(2, target.readInt32());
        assertEquals(3, target.readInt32());
        target.appendFrom(source, 100, 0);
        assertThrows(IllegalArgumentException.class, () -> target.appendFrom(source, 4, 12));
        assertThrows(IllegalArgumentException.class, () -> target.appendFrom(source, -1, 4));
    }

    @Test
    void compareDataOrdersBySizeThenByTheFirstDifferentByte() {
        ParcelData a = new ParcelData();
        a.writeInt32(1);
        ParcelData b = new ParcelData();
        b.writeInt32(1);
        assertEquals(0, a.compareData(b));
        b.writeInt32(0);
        assertEquals(-1, a.compareData(b));
        assertEquals(1, b.compareData(a));
        ParcelData c = new ParcelData();
        c.writeInt32(3);
        assertTrue(a.compareData(c) < 0);
        assertTrue(a.compareData(0, b, 0, 4));
        assertFalse(a.compareData(0, c, 0, 4));
        assertThrows(IllegalArgumentException.class, () -> a.compareData(0, b, 4, 8));
        assertThrows(IllegalArgumentException.class, () -> a.compareData(-1, b, 0, 1));
    }

    @Test
    void aParcelOfBytesHoldsNoFileDescriptor() {
        ParcelData parcel = new ParcelData();
        parcel.writeInt32(1);
        assertFalse(parcel.hasFileDescriptors());
        assertFalse(parcel.hasFileDescriptors(0, 4));
        assertThrows(IllegalArgumentException.class, () -> parcel.hasFileDescriptors(0, 8));
        assertThrows(IllegalArgumentException.class, () -> parcel.hasFileDescriptors(-1, 1));
    }

    @Test
    void allowFdsOnlyEverDropsUntilRestored() {
        ParcelData parcel = new ParcelData();
        assertTrue(parcel.pushAllowFds(true));
        assertTrue(parcel.pushAllowFds(false));
        assertFalse(parcel.pushAllowFds(true));
        parcel.restoreAllowFds(true);
        assertTrue(parcel.pushAllowFds(false));
        parcel.freeData();
        assertTrue(parcel.pushAllowFds(true));
    }

    @Test
    void freeDataStartsOver() {
        ParcelData parcel = new ParcelData();
        parcel.markSensitive();
        parcel.writeInt32(1);
        parcel.freeData();
        assertEquals(0, parcel.dataSize());
        assertEquals(0, parcel.dataPosition());
        assertEquals(0, parcel.dataCapacity());
        assertEquals(0, parcel.readInt32());
        parcel.writeInt32(2);
        assertBytes(parcel, "02 00 00 00");
    }

    @Test
    void aReadPastTheEndReturnsWhatTheGlueReturns() {
        ParcelData fresh = new ParcelData();
        assertEquals(0, fresh.readInt32());
        assertEquals(0, fresh.readInt64());
        assertNull(fresh.readString16());
        assertNull(fresh.readString8());
        assertNull(fresh.createByteArray());
        assertFalse(fresh.readByteArray(new byte[0], 0));
        assertThrows(ParcelFormatException.class, fresh::readBlob);

        ParcelData parcel = new ParcelData();
        parcel.writeInt32(8);
        parcel.writeInt32(1);
        parcel.setDataPosition(0);
        assertNull(parcel.createByteArray());
        parcel.setDataPosition(8);
        assertArrayEquals(new byte[0], parcel.createByteArray());
        parcel.setDataPosition(0);
        assertFalse(parcel.readByteArray(new byte[4], 4));
        parcel.setDataPosition(4);
        assertFalse(parcel.readByteArray(new byte[2], 2));
    }

    @Test
    void aStringWithoutItsTerminatorReadsAsNull() {
        ParcelData parcel = new ParcelData();
        parcel.unmarshall(hex("01 00 00 00 61 00 62 00 02 00 00 00 61 62 63 00"), 0, 16);
        parcel.setDataPosition(0);
        assertNull(parcel.readString16());
        parcel.setDataPosition(8);
        assertNull(parcel.readString8());
    }

    @Test
    void aBlobReadFailsAsTheGlueSignalsIt() {
        ParcelData truncated = new ParcelData();
        truncated.writeInt32(3);
        truncated.setDataPosition(0);
        assertThrows(ParcelFormatException.class, truncated::readBlob);

        ParcelData ashmem = new ParcelData();
        ashmem.writeInt32(3);
        ashmem.writeInt32(1);
        ashmem.setDataPosition(0);
        assertThrows(IllegalArgumentException.class, ashmem::readBlob);

        ParcelData shortInline = new ParcelData();
        shortInline.writeInt32(8);
        shortInline.writeInt32(0);
        shortInline.writeInt32(1);
        shortInline.setDataPosition(0);
        assertThrows(IllegalArgumentException.class, shortInline::readBlob);
    }

    @Test
    void negativeSizesAreBadValues() {
        ParcelData parcel = new ParcelData();
        assertThrows(IllegalArgumentException.class, () -> parcel.setDataSize(-1));
        assertThrows(IllegalArgumentException.class, () -> parcel.setDataCapacity(-1));
        assertThrows(IllegalArgumentException.class, () -> parcel.setDataPosition(-1));
    }

    @Test
    void statusCodesMapAsSignalExceptionForErrorMapsThem() {
        assertThrows(OutOfMemoryError.class, () -> ParcelData.signalExceptionForError(-12));
        assertNull(assertThrows(IllegalArgumentException.class, () -> ParcelData.signalExceptionForError(-22))
                .getMessage());
        assertEquals("Not enough data",
                assertThrows(ParcelFormatException.class, () -> ParcelData.signalExceptionForError(-61)).getMessage());
        assertEquals("Unknown binder error code. 0xfffffffe",
                assertThrows(RuntimeException.class, () -> ParcelData.signalExceptionForError(-2)).getMessage());
    }

    @Test
    void theInterfaceTokenHeaderAtEachLevel() {
        assertBytes(token(21), "00 01 00 00 01 00 00 00 61 00 00 00");
        assertBytes(token(22), "00 01 00 00 01 00 00 00 61 00 00 00");
        assertBytes(token(23), "00 00 40 00 01 00 00 00 61 00 00 00");
        assertBytes(token(28), "00 00 40 00 01 00 00 00 61 00 00 00");
        assertBytes(token(29), "00 00 00 80 FF FF FF FF 01 00 00 00 61 00 00 00");
        assertBytes(token(30), "00 00 00 80 FF FF FF FF 54 53 59 53 01 00 00 00 61 00 00 00");
        assertBytes(token(36), "00 00 00 80 FF FF FF FF 54 53 59 53 01 00 00 00 61 00 00 00");
    }

    @Test
    void enforceInterfaceReadsTheLevelsHeaderAndComparesTheName() {
        for (int api : new int[] {21, 23, 29, 30, 36}) {
            ParcelData parcel = token(api);
            parcel.setDataPosition(0);
            parcel.enforceInterface("a", api);
            assertEquals(parcel.dataSize(), parcel.dataPosition());
            parcel.setDataPosition(0);
            assertThrows(SecurityException.class, () -> parcel.enforceInterface("b", api));
        }
        ParcelData foreign = token(36);
        foreign.setDataPosition(8);
        foreign.writeInt32(0x564e4452);
        foreign.setDataPosition(0);
        assertEquals("Binder invocation to an incorrect interface",
                assertThrows(SecurityException.class, () -> foreign.enforceInterface("a", 36)).getMessage());
    }

    private static ParcelData token(int api) {
        ParcelData parcel = new ParcelData();
        parcel.writeInterfaceToken("a", api);
        return parcel;
    }

    private static void assertBytes(ParcelData parcel, String expected) {
        assertArrayEquals(hex(expected), parcel.marshall());
    }

    private static byte[] hex(String spaced) {
        String[] parts = spaced.split(" ");
        byte[] bytes = new byte[parts.length];
        for (int i = 0; i < parts.length; i++) {
            bytes[i] = (byte) Integer.parseInt(parts[i], 16);
        }
        return bytes;
    }
}
