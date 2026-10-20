package org.androidgraal.android;

import android.os.Build;
import android.os.ParcelFormatException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class ParcelData {

    // libutils' status_t codes, which signalExceptionForError maps to exceptions.
    private static final int OK = 0;

    private static final int NO_MEMORY = -12;

    private static final int BAD_VALUE = -22;

    private static final int NOT_ENOUGH_DATA = -61;

    private static final int BLOB_INPLACE = 0;

    private static final int MIN_GROWTH = 128;

    // libbinder's STRICT_MODE_PENALTY_GATHER before 23, before 29 and from 29.
    private static final int GATHER_BEFORE_M = 0x100;

    private static final int GATHER_BEFORE_Q = 0x40 << 16;

    private static final int GATHER = 1 << 31;

    // IPCThreadState::kUnsetWorkSource.
    private static final int UNSET_WORK_SOURCE = -1;

    // libbinder's kHeader of the system build, B_PACK_CHARS('S', 'Y', 'S', 'T').
    private static final int SYSTEM_HEADER = 'S' << 24 | 'Y' << 16 | 'S' << 8 | 'T';

    private byte[] data;

    private int size;

    private int position;

    private int capacity;

    private boolean allowFds = true;

    private boolean sensitive;

    public int dataSize() {
        return Math.max(size, position);
    }

    public int dataAvail() {
        return dataSize() - position;
    }

    public int dataPosition() {
        return position;
    }

    public int dataCapacity() {
        return capacity;
    }

    public void setDataSize(int newSize) {
        signalIfError(setSize(newSize));
    }

    public void setDataPosition(int newPosition) {
        if (newPosition < 0) {
            throw new IllegalArgumentException("pos too big: " + newPosition);
        }
        position = newPosition;
    }

    public void setDataCapacity(int newCapacity) {
        if (newCapacity < 0) {
            signalExceptionForError(BAD_VALUE);
        } else if (newCapacity > capacity) {
            signalIfError(continueWrite(newCapacity));
        }
    }

    public boolean pushAllowFds(boolean allow) {
        boolean original = allowFds;
        if (!allow) {
            allowFds = false;
        }
        return original;
    }

    public void restoreAllowFds(boolean lastValue) {
        allowFds = lastValue;
    }

    public void markSensitive() {
        sensitive = true;
    }

    public void freeData() {
        destroy();
        size = 0;
        position = 0;
        capacity = 0;
        allowFds = true;
        sensitive = false;
    }

    public void destroy() {
        if (sensitive && data != null) {
            Arrays.fill(data, 0, Math.min(size, data.length), (byte) 0);
        }
        data = null;
    }

    public int writeInt32(int value) {
        return writeAligned(value, Integer.BYTES);
    }

    public int writeInt64(long value) {
        return writeAligned(value, Long.BYTES);
    }

    public int writeFloat(float value) {
        return writeAligned(Float.floatToRawIntBits(value), Float.BYTES);
    }

    public int writeDouble(double value) {
        return writeAligned(Double.doubleToRawLongBits(value), Double.BYTES);
    }

    public void writeByteArray(byte[] bytes, int offset, int length) {
        int error = writeInt32(length);
        if (error != OK) {
            signalExceptionForError(error);
            return;
        }
        int at = writeInplace(length);
        if (at < 0) {
            signalExceptionForError(NO_MEMORY);
            return;
        }
        System.arraycopy(bytes, offset, data, at, length);
    }

    public void writeBlob(byte[] bytes, int offset, int length) {
        if (bytes == null) {
            signalIfError(writeInt32(-1));
            return;
        }
        int error = writeInt32(length);
        if (error != OK) {
            signalExceptionForError(error);
            return;
        }
        if (length < 0) {
            signalExceptionForError(BAD_VALUE);
            return;
        }
        error = writeInt32(BLOB_INPLACE);
        if (error != OK) {
            signalExceptionForError(error);
            return;
        }
        int at = writeInplace(length);
        if (at < 0) {
            signalExceptionForError(NO_MEMORY);
            return;
        }
        System.arraycopy(bytes, offset, data, at, length);
    }

    public void writeString8(String value) {
        int error;
        if (value != null) {
            long length = modifiedUtf8Length(value);
            error = writeInt32((int) length);
            int at = writeInplace(length + 1);
            if (at >= 0) {
                encodeModifiedUtf8(value, at);
                data[at + (int) length] = 0;
            } else {
                error = NO_MEMORY;
            }
        } else {
            error = writeInt32(-1);
        }
        signalIfError(error);
    }

    public void writeString16(String value) {
        int error;
        if (value != null) {
            int length = value.length();
            long byteLength = 2L * length;
            error = writeInt32(length);
            int at = writeInplace(byteLength + 2);
            if (at >= 0) {
                for (int i = 0; i < length; i++) {
                    put(at + 2 * i, value.charAt(i), 2);
                }
                put(at + (int) byteLength, 0, 2);
            } else {
                error = NO_MEMORY;
            }
        } else {
            error = writeInt32(-1);
        }
        signalIfError(error);
    }

    public int readInt32() {
        return (int) readAligned(Integer.BYTES);
    }

    public long readInt64() {
        return readAligned(Long.BYTES);
    }

    public float readFloat() {
        return Float.intBitsToFloat((int) readAligned(Float.BYTES));
    }

    public double readDouble() {
        return Double.longBitsToDouble(readAligned(Double.BYTES));
    }

    public byte[] createByteArray() {
        int length = readInt32();
        if (length < 0 || length > dataAvail()) {
            return null;
        }
        byte[] bytes = new byte[length];
        int at = readInplace(length);
        if (at < 0) {
            return null;
        }
        System.arraycopy(data, at, bytes, 0, length);
        return bytes;
    }

    public boolean readByteArray(byte[] destination, int destinationLength) {
        int length = readInt32();
        if (length < 0 || length > dataAvail() || length != destinationLength || destination == null) {
            return false;
        }
        int at = readInplace(length);
        if (at < 0) {
            return false;
        }
        System.arraycopy(data, at, destination, 0, length);
        return true;
    }

    public byte[] readBlob() {
        int length = readInt32();
        if (length < 0) {
            return null;
        }
        if (!canRead(Integer.BYTES)) {
            signalExceptionForError(NOT_ENOUGH_DATA);
            return null;
        }
        if (readInt32() != BLOB_INPLACE) {
            signalExceptionForError(BAD_VALUE);
            return null;
        }
        int at = readInplace(length);
        if (at < 0) {
            signalExceptionForError(BAD_VALUE);
            return null;
        }
        return Arrays.copyOfRange(data, at, at + length);
    }

    public String readString8() {
        int length = readInt32();
        if (length < 0 || length == Integer.MAX_VALUE) {
            return null;
        }
        int at = readInplace(length + 1L);
        if (at < 0 || data[at + length] != 0) {
            return null;
        }
        return decodeModifiedUtf8(at, at + length);
    }

    public String readString16() {
        int length = readInt32();
        if (length < 0 || length == Integer.MAX_VALUE) {
            return null;
        }
        int at = readInplace(2L * (length + 1L));
        if (at < 0 || get(at + 2 * length, 2) != 0) {
            return null;
        }
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = (char) get(at + 2 * i, 2);
        }
        return new String(chars);
    }

    public byte[] marshall() {
        byte[] bytes = new byte[dataSize()];
        copyDataTo(bytes, 0);
        return bytes;
    }

    public int marshall(byte[] destination, int offset, int remaining) {
        int length = ensureMarshallable(remaining);
        copyDataTo(destination, offset);
        return length;
    }

    public int marshall(ByteBuffer destination, int offset, int remaining) {
        int length = ensureMarshallable(remaining);
        byte[] bytes = marshall();
        destination.put(offset, bytes, 0, length);
        return length;
    }

    public void unmarshall(byte[] source, int offset, int length) {
        if (length < 0) {
            return;
        }
        setSize(length);
        position = 0;
        int at = writeInplace(length);
        if (at >= 0) {
            System.arraycopy(source, offset, data, at, length);
        }
    }

    public void unmarshall(ByteBuffer source, int offset, int length) {
        if (length < 0) {
            return;
        }
        setSize(length);
        position = 0;
        int at = writeInplace(length);
        if (at >= 0) {
            source.get(offset, data, at, length);
        }
    }

    public int compareData(ParcelData other) {
        int length = dataSize();
        int otherLength = other.dataSize();
        if (length != otherLength) {
            return length < otherLength ? -1 : 1;
        }
        return compare(this, 0, other, 0, length);
    }

    public boolean compareData(int offset, ParcelData other, int otherOffset, int length) {
        if (length < 0 || offset < 0 || otherOffset < 0 || (long) offset + length > size
                || (long) otherOffset + length > other.size) {
            signalExceptionForError(BAD_VALUE);
            return false;
        }
        return compare(this, offset, other, otherOffset, length) == 0;
    }

    public void appendFrom(ParcelData source, int offset, int length) {
        signalIfError(append(source, offset, length));
    }

    public boolean hasFileDescriptors() {
        return false;
    }

    public boolean hasFileDescriptors(int offset, int length) {
        if (length < 0 || offset < 0 || (long) offset + length > size) {
            signalExceptionForError(BAD_VALUE);
        }
        return false;
    }

    public void writeInterfaceToken(String interfaceName, int api) {
        if (api >= Build.VERSION_CODES.Q) {
            writeInt32(GATHER);
            writeInt32(UNSET_WORK_SOURCE);
            if (api >= Build.VERSION_CODES.R) {
                writeInt32(SYSTEM_HEADER);
            }
        } else {
            writeInt32(api >= Build.VERSION_CODES.M ? GATHER_BEFORE_Q : GATHER_BEFORE_M);
        }
        writeString16Silently(interfaceName);
    }

    public void enforceInterface(String interfaceName, int api) {
        readInt32();
        if (api >= Build.VERSION_CODES.Q) {
            readInt32();
        }
        if (api < Build.VERSION_CODES.R || readInt32() == SYSTEM_HEADER) {
            String read = readString16();
            if (interfaceName.equals(read == null ? "" : read)) {
                return;
            }
        }
        throw new SecurityException("Binder invocation to an incorrect interface");
    }

    public static void signalExceptionForError(int error) {
        switch (error) {
            case NO_MEMORY:
                throw new OutOfMemoryError();
            case BAD_VALUE:
                throw new IllegalArgumentException();
            case NOT_ENOUGH_DATA:
                throw new ParcelFormatException("Not enough data");
            default:
                throw new RuntimeException("Unknown binder error code. 0x" + Integer.toHexString(error));
        }
    }

    private static void signalIfError(int error) {
        if (error != OK) {
            signalExceptionForError(error);
        }
    }

    private int setSize(long newSize) {
        if (newSize < 0 || newSize > Integer.MAX_VALUE) {
            return BAD_VALUE;
        }
        int error = continueWrite(newSize);
        if (error == OK) {
            size = (int) newSize;
        }
        return error;
    }

    private int append(ParcelData source, int offset, int length) {
        byte[] sourceData = source.data;
        if (length == 0) {
            return OK;
        }
        if (length < 0 || offset < 0 || offset > source.size || length > source.size
                || (long) offset + length > source.size) {
            return BAD_VALUE;
        }
        if ((long) position + length > capacity) {
            int error = growData(length);
            if (error != OK) {
                return error;
            }
        }
        System.arraycopy(sourceData, offset, data, position, length);
        position += length;
        size += length;
        return OK;
    }

    private void writeString16Silently(String value) {
        int length = value.length();
        if (writeInt32(length) != OK) {
            return;
        }
        int at = writeInplace(2L * length + 2);
        if (at < 0) {
            return;
        }
        for (int i = 0; i < length; i++) {
            put(at + 2 * i, value.charAt(i), 2);
        }
        put(at + 2 * length, 0, 2);
    }

    private int writeAligned(long value, int length) {
        if ((long) position + length > capacity) {
            int error = growData(length);
            if (error != OK) {
                return error;
            }
        }
        put(position, value, length);
        finishWrite(length);
        return OK;
    }

    private long readAligned(int length) {
        if (!canRead(length)) {
            return 0;
        }
        long value = get(position, length);
        position += length;
        return value;
    }

    private boolean canRead(int length) {
        return (long) position + length <= size;
    }

    private int writeInplace(long length) {
        if (length < 0 || length > Integer.MAX_VALUE) {
            return -1;
        }
        long padded = (length + 3) & ~3L;
        if (position + padded > capacity && growData(padded) != OK) {
            return -1;
        }
        int at = position;
        if (padded != length) {
            Arrays.fill(data, at + (int) length, at + (int) padded, (byte) 0);
        }
        finishWrite((int) padded);
        return data == null ? -1 : at;
    }

    private int readInplace(long length) {
        if (length < 0 || length > Integer.MAX_VALUE) {
            return -1;
        }
        long padded = (length + 3) & ~3L;
        if (position + padded > size) {
            return -1;
        }
        int at = position;
        position += (int) padded;
        return data == null ? -1 : at;
    }

    private void finishWrite(int length) {
        position += length;
        if (position > size) {
            size = position;
        }
    }

    private int growData(long length) {
        if (length > Integer.MAX_VALUE || position > size) {
            return BAD_VALUE;
        }
        long newSize = ((size + length) * 3) / 2;
        return newSize <= size ? NO_MEMORY : continueWrite(Math.max(newSize, MIN_GROWTH));
    }

    private int continueWrite(long desired) {
        if (desired > Integer.MAX_VALUE) {
            return BAD_VALUE;
        }
        if (data == null) {
            data = new byte[(int) desired];
            size = 0;
            position = 0;
            capacity = (int) desired;
        } else if (desired > capacity) {
            byte[] grown = Arrays.copyOf(data, (int) desired);
            if (sensitive) {
                Arrays.fill(data, (byte) 0);
            }
            data = grown;
            capacity = (int) desired;
        } else {
            size = (int) Math.min(size, desired);
            position = (int) Math.min(position, desired);
        }
        return OK;
    }

    private int ensureMarshallable(int remaining) {
        int length = dataSize();
        if (remaining < length) {
            throw new BufferOverflowException();
        }
        return length;
    }

    private void copyDataTo(byte[] destination, int offset) {
        if (data != null) {
            System.arraycopy(data, 0, destination, offset, Math.min(dataSize(), data.length));
        }
    }

    private void put(int at, long value, int length) {
        for (int i = 0; i < length; i++) {
            data[at + i] = (byte) (value >>> (8 * i));
        }
    }

    private long get(int at, int length) {
        long value = 0;
        for (int i = 0; i < length; i++) {
            value |= (data[at + i] & 0xffL) << (8 * i);
        }
        return length == Integer.BYTES ? (int) value : length == 2 ? (char) value : value;
    }

    private static int compare(ParcelData a, int offset, ParcelData b, int otherOffset, int length) {
        for (int i = 0; i < length; i++) {
            int left = a.byteAt(offset + i);
            int right = b.byteAt(otherOffset + i);
            if (left != right) {
                return left - right;
            }
        }
        return 0;
    }

    private int byteAt(int at) {
        return data != null && at < data.length ? data[at] & 0xff : 0;
    }

    // ART's GetStringUTFRegion writes a surrogate pair as one four-byte sequence, an unpaired surrogate as three bytes.
    private static long modifiedUtf8Length(String value) {
        long length = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (isPair(value, i)) {
                length += 4;
                i++;
            } else {
                length += c != 0 && c < 0x80 ? 1 : c < 0x800 ? 2 : 3;
            }
        }
        return length;
    }

    private static boolean isPair(String value, int i) {
        return Character.isHighSurrogate(value.charAt(i)) && i + 1 < value.length()
                && Character.isLowSurrogate(value.charAt(i + 1));
    }

    private void encodeModifiedUtf8(String value, int at) {
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (isPair(value, i)) {
                int codePoint = Character.toCodePoint(c, value.charAt(++i));
                data[at++] = (byte) (0xf0 | (codePoint >> 18));
                data[at++] = (byte) (0x80 | ((codePoint >> 12) & 0x3f));
                data[at++] = (byte) (0x80 | ((codePoint >> 6) & 0x3f));
                data[at++] = (byte) (0x80 | (codePoint & 0x3f));
            } else if (c != 0 && c < 0x80) {
                data[at++] = (byte) c;
            } else if (c < 0x800) {
                data[at++] = (byte) (0xc0 | (c >> 6));
                data[at++] = (byte) (0x80 | (c & 0x3f));
            } else {
                data[at++] = (byte) (0xe0 | (c >> 12));
                data[at++] = (byte) (0x80 | ((c >> 6) & 0x3f));
                data[at++] = (byte) (0x80 | (c & 0x3f));
            }
        }
    }

    private String decodeModifiedUtf8(int at, int end) {
        StringBuilder builder = new StringBuilder();
        while (at < end && data[at] != 0) {
            int one = data[at++] & 0xff;
            if ((one & 0x80) == 0) {
                builder.append((char) one);
                continue;
            }
            int two = continuation(at++, end);
            if ((one & 0x20) == 0) {
                builder.append((char) ((one & 0x1f) << 6 | two));
                continue;
            }
            int three = continuation(at++, end);
            if ((one & 0x10) == 0) {
                builder.append((char) ((one & 0x0f) << 12 | two << 6 | three));
                continue;
            }
            int codePoint = (one & 0x07) << 18 | two << 12 | three << 6 | continuation(at++, end);
            builder.append((char) ((codePoint >> 10) + 0xd7c0));
            builder.append((char) ((codePoint & 0x3ff) | 0xdc00));
        }
        return builder.toString();
    }

    private int continuation(int at, int end) {
        return at < end ? data[at] & 0x3f : 0;
    }
}
