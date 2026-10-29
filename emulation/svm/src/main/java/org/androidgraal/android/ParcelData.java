package org.androidgraal.android;

import android.os.Build;
import android.os.ParcelFormatException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.androidgraal.hosted.annotate.Adapted;
import org.androidgraal.hosted.annotate.Premise;
import org.androidgraal.hosted.annotate.Transcribed;

public final class ParcelData {

    // libutils' status_t codes, which signalExceptionForError maps to exceptions.
    @Transcribed("https://android.googlesource.com/platform/system/core/+/refs/tags/android-16.0.0_r1/libutils/binder/include/utils/Errors.h#L38")
    private static final int OK = 0;

    @Transcribed("https://android.googlesource.com/platform/system/core/+/refs/tags/android-16.0.0_r1/libutils/binder/include/utils/Errors.h#L47")
    private static final int NO_MEMORY = -12;

    @Transcribed("https://android.googlesource.com/platform/system/core/+/refs/tags/android-16.0.0_r1/libutils/binder/include/utils/Errors.h#L49")
    private static final int BAD_VALUE = -22;

    @Transcribed("https://android.googlesource.com/platform/system/core/+/refs/tags/android-16.0.0_r1/libutils/binder/include/utils/Errors.h#L59")
    private static final int NOT_ENOUGH_DATA = -61;

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L152")
    private static final int BLOB_INPLACE = 0;

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L3032")
    private static final int MIN_GROWTH = 128;

    // libbinder's STRICT_MODE_PENALTY_GATHER before 23, before 29 and from 29.
    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-5.0.0_r1/libs/binder/Parcel.cpp#L59")
    private static final int GATHER_BEFORE_M = 0x100;

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-6.0.0_r1/libs/binder/Parcel.cpp#L70")
    private static final int GATHER_BEFORE_Q = 0x40 << 16;

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L96")
    private static final int GATHER = 1 << 31;

    // IPCThreadState::kUnsetWorkSource.
    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/include/binder/IPCThreadState.h#L212")
    private static final int UNSET_WORK_SOURCE = -1;

    // libbinder's kHeader of the system build, B_PACK_CHARS('S', 'Y', 'S', 'T').
    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/include/binder/IBinder.h#L30-L33",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L954"})
    private static final int SYSTEM_HEADER = 'S' << 24 | 'Y' << 16 | 'S' << 8 | 'T';

    private byte[] data;

    private int size;

    private int position;

    private int capacity;

    private boolean allowFds = true;

    private boolean sensitive;

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L422-L425")
    public int dataSize() {
        return Math.max(size, position);
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L431-L438")
    public int dataAvail() {
        return dataSize() - position;
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L440-L443")
    public int dataPosition() {
        return position;
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L445-L448")
    public int dataCapacity() {
        return capacity;
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L148-L157")
    public void setDataSize(int newSize) {
        signalIfError(setSize(newSize));
    }

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L159-L165",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L467-L480"})
    public void setDataPosition(int newPosition) {
        if (newPosition < 0) {
            throw new IllegalArgumentException("pos too big: " + newPosition);
        }
        position = newPosition;
    }

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L167-L176",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L482-L492"})
    public void setDataCapacity(int newCapacity) {
        if (newCapacity < 0) {
            signalExceptionForError(BAD_VALUE);
        } else if (newCapacity > capacity) {
            signalIfError(continueWrite(newCapacity));
        }
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L725-L732")
    public boolean pushAllowFds(boolean allow) {
        boolean original = allowFds;
        if (!allow) {
            allowFds = false;
        }
        return original;
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L734-L737")
    public void restoreAllowFds(boolean lastValue) {
        allowFds = lastValue;
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L907-L910")
    public void markSensitive() {
        sensitive = true;
    }

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L2978-L2982",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L3375-L3391"})
    public void freeData() {
        destroy();
        size = 0;
        position = 0;
        capacity = 0;
        allowFds = true;
        sensitive = false;
    }

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L403-L407",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L2984-L3009"})
    public void destroy() {
        if (sensitive && data != null) {
            Arrays.fill(data, 0, Math.min(size, data.length), (byte) 0);
        }
        data = null;
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L1372-L1375")
    public int writeInt32(int value) {
        return writeAligned(value, Integer.BYTES);
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L1430-L1433")
    public int writeInt64(long value) {
        return writeAligned(value, Long.BYTES);
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L1445-L1448")
    public int writeFloat(float value) {
        return writeAligned(Float.floatToRawIntBits(value), Float.BYTES);
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L1464-L1467")
    public int writeDouble(double value) {
        return writeAligned(Double.doubleToRawLongBits(value), Double.BYTES);
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L198-L223")
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

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L225-L262",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L1664-L1681"})
    @Adapted(reason = "writes every blob inline", premise = Premise.NO_BINDER)
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

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L288-L312",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L1483"})
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

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L314-L338",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L1506"})
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

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L2062-L2065")
    public int readInt32() {
        return (int) readAligned(Integer.BYTES);
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L2083-L2086")
    public long readInt64() {
        return readAligned(Long.BYTES);
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L2120-L2123")
    public float readFloat() {
        return Float.intBitsToFloat((int) readAligned(Float.BYTES));
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L2157-L2160")
    public double readDouble() {
        return Double.longBitsToDouble(readAligned(Double.BYTES));
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L363-L392")
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

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L394-L419")
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

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L421-L449",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L2517-L2558"})
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

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L487-L499",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L2270-L2286"})
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

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L501-L513",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L2315-L2331"})
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

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L582-L602")
    public byte[] marshall() {
        byte[] bytes = new byte[dataSize()];
        copyDataTo(bytes, 0);
        return bytes;
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L613-L633")
    public int marshall(byte[] destination, int offset, int remaining) {
        int length = ensureMarshallable(remaining);
        copyDataTo(destination, offset);
        return length;
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L635-L652")
    public int marshall(ByteBuffer destination, int offset, int remaining) {
        int length = ensureMarshallable(remaining);
        byte[] bytes = marshall();
        destination.put(offset, bytes, 0, length);
        return length;
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L654-L673")
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

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L675-L692")
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

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L693-L699")
    public int compareData(ParcelData other) {
        int length = dataSize();
        int otherLength = other.dataSize();
        if (length != otherLength) {
            return length < otherLength ? -1 : 1;
        }
        return compare(this, 0, other, 0, length);
    }

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L706-L723",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L701-L718"})
    public boolean compareData(int offset, ParcelData other, int otherOffset, int length) {
        if (length < 0 || offset < 0 || otherOffset < 0 || (long) offset + length > size
                || (long) otherOffset + length > other.size) {
            signalExceptionForError(BAD_VALUE);
            return false;
        }
        return compare(this, offset, other, otherOffset, length) == 0;
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L725-L742")
    public void appendFrom(ParcelData source, int offset, int length) {
        signalIfError(append(source, offset, length));
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L739-L749")
    @Adapted(reason = "answers false, the buffer holds no object", premise = Premise.NO_BINDER)
    public boolean hasFileDescriptors() {
        return false;
    }

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L785-L799",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L858-L905"})
    @Adapted(reason = "answers false, the buffer holds no object", premise = Premise.NO_BINDER)
    public boolean hasFileDescriptors(int offset, int length) {
        if (length < 0 || offset < 0 || (long) offset + length > size) {
            signalExceptionForError(BAD_VALUE);
        }
        return false;
    }

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-5.0.0_r1/libs/binder/Parcel.cpp#L481-L487",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-6.0.0_r1/libs/binder/Parcel.cpp#L554-L560",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-10.0.0_r1/libs/binder/Parcel.cpp#L607-L616",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L972-L990"})
    @Adapted(reason = "writes policy 0 and the unset work source", premise = Premise.NO_FRAMEWORK_PROCESS)
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

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-5.0.0_r1/libs/binder/Parcel.cpp#L494-L519",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-10.0.0_r1/libs/binder/Parcel.cpp#L649-L680",
            "https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L849-L878",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L1037-L1099"})
    @Adapted(reason = "sets no thread state from the header", premise = Premise.NO_FRAMEWORK_PROCESS)
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

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_util_Binder.cpp#L1026-L1149")
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

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L450-L465")
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

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L524-L558")
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

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L1504-L1521")
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

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L2037-L2055")
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

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L2003-L2035")
    private long readAligned(int length) {
        if (!canRead(length)) {
            return 0;
        }
        long value = get(position, length);
        position += length;
        return value;
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L2008")
    private boolean canRead(int length) {
        return (long) position + length <= size;
    }

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L86-L93",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L1181-L1229"})
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

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L86-L93",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L1951-L1978"})
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

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L1146-L1163")
    private void finishWrite(int length) {
        position += length;
        if (position > size) {
            size = position;
        }
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L3011-L3033")
    private int growData(long length) {
        if (length > Integer.MAX_VALUE || position > size) {
            return BAD_VALUE;
        }
        long newSize = ((size + length) * 3) / 2;
        return newSize <= size ? NO_MEMORY : continueWrite(Math.max(newSize, MIN_GROWTH));
    }

    @Transcribed({
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L3035-L3048",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L3108-L3114",
            "https://android.googlesource.com/platform/frameworks/native/+/refs/tags/android-16.0.0_r1/libs/binder/Parcel.cpp#L3282-L3332"})
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

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Parcel.cpp#L604-L611")
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
    @Transcribed({
            "https://android.googlesource.com/platform/art/+/refs/tags/android-16.0.0_r1/runtime/jni/jni_internal.cc#L226-L235",
            "https://android.googlesource.com/platform/art/+/refs/tags/android-16.0.0_r1/libdexfile/dex/utf-inl.h#L97-L144"})
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

    @Transcribed({
            "https://android.googlesource.com/platform/art/+/refs/tags/android-16.0.0_r1/libdexfile/dex/utf-inl.h#L102-L103",
            "https://android.googlesource.com/platform/art/+/refs/tags/android-16.0.0_r1/libdexfile/dex/utf-inl.h#L112"})
    private static boolean isPair(String value, int i) {
        return Character.isHighSurrogate(value.charAt(i)) && i + 1 < value.length()
                && Character.isLowSurrogate(value.charAt(i + 1));
    }

    @Transcribed({
            "https://android.googlesource.com/platform/art/+/refs/tags/android-16.0.0_r1/runtime/jni/jni_internal.cc#L226-L228",
            "https://android.googlesource.com/platform/art/+/refs/tags/android-16.0.0_r1/runtime/jni/jni_internal.cc#L237-L241",
            "https://android.googlesource.com/platform/art/+/refs/tags/android-16.0.0_r1/libdexfile/dex/utf-inl.h#L97-L144"})
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
