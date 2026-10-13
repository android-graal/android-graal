package android.graphics;

/**
 * <code>PathIterator</code> can be used to query a given {@link Path} object, to discover its
 * operations and point values.
 */
public final class PathIterator {
    public static final int VERB_CLOSE = 5;

    public static final int VERB_CONIC = 3;

    public static final int VERB_CUBIC = 4;

    public static final int VERB_DONE = 6;

    public static final int VERB_LINE = 1;

    public static final int VERB_MOVE = 0;

    public static final int VERB_QUAD = 2;

    private PathIterator() {
    }
}
