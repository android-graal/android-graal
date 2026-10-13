package android.icu.text;

/**
 * <tt>SearchIterator</tt> is an abstract base class that provides 
 * methods to search for a pattern within a text string. Instances of
 * <tt>SearchIterator</tt> maintain a current position and scan over the 
 * target text, returning the indices the pattern is matched and the length 
 * of each match.
 * <p>
 * <tt>SearchIterator</tt> defines a protocol for text searching. 
 * Subclasses provide concrete implementations of various search algorithms. 
 * For example, <tt>StringSearch</tt> implements language-sensitive pattern 
 * matching based on the comparison rules defined in a 
 * <tt>RuleBasedCollator</tt> object. 
 * <p> 
 * Other options for searching include using a BreakIterator to restrict 
 * the points at which matches are detected.
 * <p>
 * <tt>SearchIterator</tt> provides an API that is similar to that of
 * other text iteration classes such as <tt>BreakIterator</tt>. Using 
 * this class, it is easy to scan through text looking for all occurrences of 
 * a given pattern. The following example uses a <tt>StringSearch</tt> 
 * object to find all instances of "fox" in the target string. Any other 
 * subclass of <tt>SearchIterator</tt> can be used in an identical 
 * manner.
 * <pre><code>
 * String target = "The quick brown fox jumped over the lazy fox";
 * String pattern = "fox";
 * SearchIterator iter = new StringSearch(pattern, target);
 * for (int pos = iter.first(); pos != SearchIterator.DONE;
 *         pos = iter.next()) {
 *     System.out.println("Found match at " + pos +
 *             ", length is " + iter.getMatchLength());
 * }
 * </code></pre>
 *
 * @author Laura Werner, synwee
 * @see BreakIterator
 * @see RuleBasedCollator
 */
public final class SearchIterator {
    /**
     * DONE is returned by previous() and next() after all valid matches have 
     * been returned, and by first() and last() if there are no matches at all.
     * @see #previous
     * @see #next
     */
    public static final int DONE = -1;

    private SearchIterator() {
    }
}
