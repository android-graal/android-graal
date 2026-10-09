package org.androidgraal.art;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import org.androidgraal.art.Image.ThreadData;
import org.androidgraal.shared.Blocks;

public final class Transport {

    private Transport() {
    }

    /** Allocates image memory and returns a view over it. */
    public static ByteBuffer alloc(ThreadData thread, int bytes) {
        ByteBuffer view = alloc(thread.isolateThread, bytes);
        thread.throwPendingException();
        return view.order(ByteOrder.nativeOrder());
    }

    /**
     * Frees the memory allocated by the image
     */
    public static void free(ThreadData thread, ByteBuffer view) {
        free(thread.isolateThread, view);
        thread.throwPendingException();
    }

    /**
     * Frees the memory allocated by the image, ignores exceptions raised
     */
    public static void freeNoException(ThreadData thread, ByteBuffer view) {
        free(thread.isolateThread, view);
        thread.pendingException = null;
    }

    /** Image memory freed when the calling thread exits. */
    public static long threadAlloc(ThreadData thread, int bytes) {
        long address = threadAlloc(thread.isolateThread, bytes);
        thread.throwPendingException();
        return address;
    }

    /** A view over the {@code address} with the size {@code bytes}. */
    public static ByteBuffer view(ThreadData thread, long address, int bytes) {
        ByteBuffer view = view(thread.isolateThread, address, bytes);
        thread.throwPendingException();
        return view.order(ByteOrder.nativeOrder());
    }

    /** A view over the block at {@code address}. */
    public static ByteBuffer viewBlock(ThreadData thread, long address) {
        ByteBuffer view = viewBlock(thread.isolateThread, address);
        thread.throwPendingException();
        return view.order(ByteOrder.nativeOrder());
    }

    /**
     * Allocates a block of {@code length} elements of {@code elementBytes} bytes with the block header, which the
     * caller hands to {@link #free}.
     */
    public static ByteBuffer allocBlock(ThreadData thread, int length, int elementBytes) {
        ByteBuffer block = alloc(thread, Blocks.size(length, elementBytes));
        setBlockHeader(block, 0, length, elementBytes);
        return block;
    }

    /** Writes the header of the block at the byte offset {@code at} of {@code view}. */
    public static void setBlockHeader(ByteBuffer view, int at, int length, int elementBytes) {
        view.putInt(at + Blocks.LENGTH_AT, length);
        view.putInt(at + Blocks.SIZE_AT, Blocks.size(length, elementBytes));
    }

    /** Returns the amount of elements in the block (not to be confused with byte size) */
    public static int getBlockLength(ByteBuffer view, int at) {
        return view.getInt(at + Blocks.LENGTH_AT);
    }

    /** The string in the block at the byte offset {@code at} of {@code view}, null for a null block. */
    public static String readStringBlock(ByteBuffer view, int at) {
        int length = getBlockLength(view, at);
        if (length == Blocks.NULL) {
            return null;
        }
        char[] chars = new char[length];
        view.position(at + Blocks.HEADER);
        view.asCharBuffer().get(chars);
        return new String(chars);
    }

    /** The string in {@code block}, null for a null block, read through {@code view} into {@code chars}. */
    public static String readStringBlock(ByteBuffer block, CharBuffer view, char[] chars) {
        int length = getBlockLength(block, 0);
        if (length == Blocks.NULL) {
            return null;
        }
        view.clear();
        view.get(chars, 0, length);
        return new String(chars, 0, length);
    }

    /** A copy of the bytes of the block at the start of {@code block}, its view left positioned after them. */
    public static byte[] readBlock(ByteBuffer block) {
        byte[] bytes = new byte[getBlockLength(block, 0)];
        block.position(Blocks.HEADER);
        block.get(bytes);
        return bytes;
    }

    private static native ByteBuffer alloc(long thread, int bytes);

    private static native void free(long thread, ByteBuffer view);

    private static native long threadAlloc(long thread, int bytes);

    private static native ByteBuffer view(long thread, long address, int bytes);

    private static native ByteBuffer viewBlock(long thread, long address);
}
