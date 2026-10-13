package org.androidgraal.svm;

import org.androidgraal.shared.Blocks;
import org.graalvm.nativeimage.PinnedObject;
import org.graalvm.nativeimage.UnmanagedMemory;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.Pointer;
import org.graalvm.word.PointerBase;
import org.graalvm.word.UnsignedWord;
import org.graalvm.word.WordFactory;

public final class Transport {

    private static final int MEMCPY_THRESHOLD = 64;

    private Transport() {
    }

    public static long alloc(int bytes) {
        Pointer memory = UnmanagedMemory.malloc(bytes);
        return memory.rawValue();
    }

    public static void free(long address) {
        if (address != 0) {
            UnmanagedMemory.free(WordFactory.pointer(address));
        }
    }

    /** {@code bytes} of allocated memory, freed when the thread exits. */
    public static long threadAlloc(int bytes) {
        VoidPointer memory = Threads.threadAlloc(WordFactory.unsigned(bytes));
        if (memory.isNull()) {
            throw new OutOfMemoryError("allocating " + bytes + " bytes for the thread failed");
        }
        return memory.rawValue();
    }

    /**
     * Allocates a block of {@code length} elements of {@code elementBytes} bytes with the block header.
     */
    public static long allocBlock(int length, int elementBytes) {
        long block = alloc(Blocks.size(length, elementBytes));
        setBlockHeader(block, length, elementBytes);
        return block;
    }

    /** Writes the header of the block. */
    public static void setBlockHeader(long block, int length, int elementBytes) {
        Pointer p = WordFactory.pointer(block);
        p.writeInt(Blocks.LENGTH_AT, length);
        p.writeInt(Blocks.SIZE_AT, Blocks.size(length, elementBytes));
    }

    /** Returns the amount of elements in the block (not to be confused with byte size) */
    public static int getBlockLength(long block) {
        return WordFactory.<Pointer>pointer(block).readInt(Blocks.LENGTH_AT);
    }

    /** Returns the amount of bytes in the block (not to be confused with element count) */
    public static int getBlockSize(long block) {
        return WordFactory.<Pointer>pointer(block).readInt(Blocks.SIZE_AT);
    }

    /** A copy of the bytes of the block at the start of {@code block}. */
    public static byte[] readBlock(long block) {
        byte[] bytes = new byte[getBlockLength(block)];
        read(block + Blocks.HEADER, bytes);
        return bytes;
    }

    /** The string in the block, null for a null block. */
    public static String readStringBlock(long block) {
        if (block == 0) {
            return null;
        }
        int length = getBlockLength(block);
        if (length == Blocks.NULL) {
            return null;
        }
        char[] chars = new char[length];
        read(block + Blocks.HEADER, chars, length);
        return new String(chars);
    }

    /** The string in the block decoded using {@code chars}, null for a null block. */
    public static String readStringBlock(long block, char[] chars) {
        int length = getBlockLength(block);
        if (length == Blocks.NULL) {
            return null;
        }
        read(block + Blocks.HEADER, chars, length);
        return new String(chars, 0, length);
    }

    public static void write(String from, long address) {
        if (from == null) {
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < from.length(); i++) {
            p.writeChar(i * 2, from.charAt(i));
        }
    }

    public static void write(String[] from, long address) {
        if (from == null) {
            return;
        }
        WordFactory.<Pointer>pointer(address).writeInt(0, from.length);
        long at = address + Blocks.HEADER;
        for (String s : from) {
            int length = s == null ? Blocks.NULL : s.length();
            setBlockHeader(at, length, 2);
            write(s, at + Blocks.HEADER);
            at += Blocks.size(length, 2);
        }
    }

    /** The string array in the block, null for a null block. */
    public static String[] readStringArrayBlock(long block) {
        if (block == 0) {
            return null;
        }
        if (getBlockLength(block) == Blocks.NULL) {
            return null;
        }
        long address = block + Blocks.HEADER;
        String[] a = new String[WordFactory.<Pointer>pointer(address).readInt(0)];
        long at = address + Blocks.HEADER;
        for (int i = 0; i < a.length; i++) {
            a[i] = readStringBlock(at);
            at += Blocks.size(getBlockLength(at), 2);
        }
        return a;
    }

    public static void read(long address, boolean[] into) {
        if (into == null) {
            return;
        }
        if (into.length >= MEMCPY_THRESHOLD) {
            fromMemory(address, into, into.length);
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < into.length; i++) {
            into[i] = p.readByte(i) != 0;
        }
    }

    public static void write(boolean[] from, long address) {
        if (from == null) {
            return;
        }
        if (from.length >= MEMCPY_THRESHOLD) {
            toMemory(from, address, from.length);
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < from.length; i++) {
            p.writeByte(i, (byte) (from[i] ? 1 : 0));
        }
    }

    public static void read(long address, byte[] into) {
        if (into == null) {
            return;
        }
        if (into.length >= MEMCPY_THRESHOLD) {
            fromMemory(address, into, into.length);
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < into.length; i++) {
            into[i] = p.readByte(i);
        }
    }

    public static void write(byte[] from, long address) {
        if (from == null) {
            return;
        }
        if (from.length >= MEMCPY_THRESHOLD) {
            toMemory(from, address, from.length);
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < from.length; i++) {
            p.writeByte(i, from[i]);
        }
    }

    public static void read(long address, char[] into) {
        if (into != null) {
            read(address, into, into.length);
        }
    }

    private static void read(long address, char[] into, int length) {
        if (length >= MEMCPY_THRESHOLD) {
            fromMemory(address, into, length * 2L);
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < length; i++) {
            into[i] = p.readChar(i * 2);
        }
    }

    public static void write(char[] from, long address) {
        if (from == null) {
            return;
        }
        if (from.length >= MEMCPY_THRESHOLD) {
            toMemory(from, address, from.length * 2L);
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < from.length; i++) {
            p.writeChar(i * 2, from[i]);
        }
    }

    public static void read(long address, short[] into) {
        if (into == null) {
            return;
        }
        if (into.length >= MEMCPY_THRESHOLD) {
            fromMemory(address, into, into.length * 2L);
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < into.length; i++) {
            into[i] = p.readShort(i * 2);
        }
    }

    public static void write(short[] from, long address) {
        if (from == null) {
            return;
        }
        if (from.length >= MEMCPY_THRESHOLD) {
            toMemory(from, address, from.length * 2L);
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < from.length; i++) {
            p.writeShort(i * 2, from[i]);
        }
    }

    public static void read(long address, int[] into) {
        if (into == null) {
            return;
        }
        if (into.length >= MEMCPY_THRESHOLD) {
            fromMemory(address, into, into.length * 4L);
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < into.length; i++) {
            into[i] = p.readInt(i * 4);
        }
    }

    public static void write(int[] from, long address) {
        if (from == null) {
            return;
        }
        if (from.length >= MEMCPY_THRESHOLD) {
            toMemory(from, address, from.length * 4L);
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < from.length; i++) {
            p.writeInt(i * 4, from[i]);
        }
    }

    public static void read(long address, long[] into) {
        if (into == null) {
            return;
        }
        if (into.length >= MEMCPY_THRESHOLD) {
            fromMemory(address, into, into.length * 8L);
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < into.length; i++) {
            into[i] = p.readLong(i * 8);
        }
    }

    public static void write(long[] from, long address) {
        if (from == null) {
            return;
        }
        if (from.length >= MEMCPY_THRESHOLD) {
            toMemory(from, address, from.length * 8L);
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < from.length; i++) {
            p.writeLong(i * 8, from[i]);
        }
    }

    public static void read(long address, float[] into) {
        if (into == null) {
            return;
        }
        if (into.length >= MEMCPY_THRESHOLD) {
            fromMemory(address, into, into.length * 4L);
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < into.length; i++) {
            into[i] = p.readFloat(i * 4);
        }
    }

    public static void write(float[] from, long address) {
        if (from == null) {
            return;
        }
        if (from.length >= MEMCPY_THRESHOLD) {
            toMemory(from, address, from.length * 4L);
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < from.length; i++) {
            p.writeFloat(i * 4, from[i]);
        }
    }

    public static void read(long address, double[] into) {
        if (into == null) {
            return;
        }
        if (into.length >= MEMCPY_THRESHOLD) {
            fromMemory(address, into, into.length * 8L);
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < into.length; i++) {
            into[i] = p.readDouble(i * 8);
        }
    }

    public static void write(double[] from, long address) {
        if (from == null) {
            return;
        }
        if (from.length >= MEMCPY_THRESHOLD) {
            toMemory(from, address, from.length * 8L);
            return;
        }
        Pointer p = WordFactory.pointer(address);
        for (int i = 0; i < from.length; i++) {
            p.writeDouble(i * 8, from[i]);
        }
    }

    private static void fromMemory(long address, Object array, long bytes) {
        try (PinnedObject pinned = PinnedObject.create(array)) {
            memcpy(pinned.addressOfArrayElement(0), WordFactory.pointer(address), WordFactory.unsigned(bytes));
        }
    }

    private static void toMemory(Object array, long address, long bytes) {
        try (PinnedObject pinned = PinnedObject.create(array)) {
            memcpy(WordFactory.pointer(address), pinned.addressOfArrayElement(0), WordFactory.unsigned(bytes));
        }
    }

    @CFunction(value = "memcpy", transition = CFunction.Transition.NO_TRANSITION)
    private static native void memcpy(PointerBase dst, PointerBase src, UnsignedWord n);
}
