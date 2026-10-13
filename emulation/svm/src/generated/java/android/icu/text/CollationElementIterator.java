package android.icu.text;

/**
 * <code>CollationElementIterator</code> is an iterator created by
 * a RuleBasedCollator to walk through a string. The return result of
 * each iteration is a 32-bit collation element (CE) that defines the
 * ordering priority of the next character or sequence of characters
 * in the source string.
 *
 * <p>For illustration, consider the following in Slovak and in traditional Spanish collation:
 * <blockquote>
 * <pre>
 * "ca" -&gt; the first collation element is CE('c') and the second
 *         collation element is CE('a').
 * "cha" -&gt; the first collation element is CE('ch') and the second
 *          collation element is CE('a').
 * </pre>
 * </blockquote>
 * And in German phonebook collation,
 * <blockquote>
 * <pre>
 * Since the character '&#230;' is a composed character of 'a' and 'e', the
 * iterator returns two collation elements for the single character '&#230;'
 *
 * "&#230;b" -&gt; the first collation element is collation_element('a'), the
 *              second collation element is collation_element('e'), and the
 *              third collation element is collation_element('b').
 * </pre>
 * </blockquote>
 *
 * <p>For collation ordering comparison, the collation element results
 * can not be compared simply by using basic arithmetic operators,
 * e.g. &lt;, == or &gt;, further processing has to be done. Details
 * can be found in the ICU
 * <a href="https://unicode-org.github.io/icu/userguide/collation/architecture">
 * User Guide</a>. An example of using the CollationElementIterator
 * for collation ordering comparison is the class
 * {@link android.icu.text.StringSearch}.
 *
 * <p>To construct a CollationElementIterator object, users
 * call the method getCollationElementIterator() on a
 * RuleBasedCollator that defines the desired sorting order.
 *
 * <p> Example:
 * <blockquote>
 * <pre>
 *  String testString = "This is a test";
 *  RuleBasedCollator rbc = new RuleBasedCollator("&amp;a&lt;b");
 *  CollationElementIterator iterator = rbc.getCollationElementIterator(testString);
 *  int primaryOrder = iterator.IGNORABLE;
 *  while (primaryOrder != iterator.NULLORDER) {
 *      int order = iterator.next();
 *      if (order != iterator.IGNORABLE &amp;&amp;
 *          order != iterator.NULLORDER) {
 *          // order is valid, not ignorable and we have not passed the end
 *          // of the iteration, we do something
 *          primaryOrder = CollationElementIterator.primaryOrder(order);
 *          System.out.println("Next primary order 0x" +
 *                             Integer.toHexString(primaryOrder));
 *      }
 *  }
 * </pre>
 * </blockquote>
 * <p>
 * The method next() returns the collation order of the next character based on
 * the comparison level of the collator. The method previous() returns the
 * collation order of the previous character based on the comparison level of
 * the collator. The Collation Element Iterator moves only in one direction
 * between calls to reset(), setOffset(), or setText(). That is, next() and
 * previous() can not be inter-used. Whenever previous() is to be called after
 * next() or vice versa, reset(), setOffset() or setText() has to be called first
 * to reset the status, shifting current position to either the end or the start of
 * the string (reset() or setText()), or the specified position (setOffset()).
 * Hence at the next call of next() or previous(), the first or last collation order,
 * or collation order at the specified position will be returned. If a change of
 * direction is done without one of these calls, the result is undefined.
 * <p>
 * This class is not subclassable.
 * @see Collator
 * @see RuleBasedCollator
 * @see StringSearch
 * @author Syn Wee Quek
 */
public final class CollationElementIterator {
    /**
     * This constant is returned by the iterator in the methods
     * next() and previous() when a collation element result is to be
     * ignored.
     *
     * <p>See class documentation for an example of use.
     * @see #next
     * @see #previous
     */
    public static final int IGNORABLE = 0;

    /**
     * This constant is returned by the iterator in the methods
     * next() and previous() when the end or the beginning of the
     * source string has been reached, and there are no more valid
     * collation elements to return.
     *
     * <p>See class documentation for an example of use.
     * @see #next
     * @see #previous
     */
    public static final int NULLORDER = -1;

    private CollationElementIterator() {
    }
}
