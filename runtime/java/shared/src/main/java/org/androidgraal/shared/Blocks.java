package org.androidgraal.shared;

/**
 * One array or string in a transport, written by the sender and read by the receiver at these byte offsets:
 *
 * <pre>
 * 0  int length    element count, {@link #NULL} for a null array or string
 * 4  int size      bytes the block occupies, from offset 0 through the padding
 * 8      elements  length * element size
 *        padding   to the next multiple of 8 bytes, so the next block starts aligned
 * </pre>
 */
public final class Blocks {

    public static final int LENGTH_AT = 0;

    public static final int SIZE_AT = 4;

    public static final int HEADER = 8;

    public static final int NULL = -1;

    private Blocks() {
    }

    public static int size(int length, int elementBytes) {
        return (HEADER + (length < 0 ? 0 : length * elementBytes) + 7) & ~7;
    }

    /**
     * The bytes a string array's block holds: the element count as an {@code int} in a slot of {@link #HEADER} bytes,
     * then per element a string block.
     */
    public static int stringArrayLength(String[] a) {
        int length = HEADER;
        for (String s : a) {
            length += size(s == null ? NULL : s.length(), 2);
        }
        return length;
    }
}
