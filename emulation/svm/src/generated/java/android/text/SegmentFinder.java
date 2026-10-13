package android.text;

/**
 * Finds text segment boundaries within text. Subclasses can implement different types of text
 * segments. Grapheme clusters and words are examples of possible text segments. These are
 * implemented by {@link GraphemeClusterSegmentFinder} and {@link WordSegmentFinder}.
 *
 * <p>Text segments may not overlap, so every character belongs to at most one text segment. A
 * character may belong to no text segments.
 *
 * <p>For example, WordSegmentFinder subdivides the text "Hello, World!" into four text segments:
 * "Hello", ",", "World", "!". The space character does not belong to any text segments.
 *
 * @see Layout#getRangeForRect(RectF, SegmentFinder, Layout.TextInclusionStrategy)
 */
public final class SegmentFinder {
    /**
     * Return value of previousStartBoundary(int), previousEndBoundary(int), nextStartBoundary(int),
     * and nextEndBoundary(int) when there are no boundaries of the specified type in the specified
     * direction.
     */
    public static final int DONE = -1;

    private SegmentFinder() {
    }
}
