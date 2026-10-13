package android.icu.text;

/**
 * A <code>CollationKey</code> represents a <code>String</code>
 * under the rules of a specific <code>Collator</code>
 * object. Comparing two <code>CollationKey</code>s returns the
 * relative order of the <code>String</code>s they represent.
 *
 * <p>Since the rule set of <code>Collator</code>s can differ, the
 * sort orders of the same string under two different
 * <code>Collator</code>s might differ.  Hence comparing
 * <code>CollationKey</code>s generated from different
 * <code>Collator</code>s can give incorrect results.
 *
 * <p>Both the method
 * <code>CollationKey.compareTo(CollationKey)</code> and the method
 * <code>Collator.compare(String, String)</code> compare two strings
 * and returns their relative order.  The performance characteristics
 * of these two approaches can differ.
 * Note that collation keys are often less efficient than simply doing comparison.
 * For more details, see the ICU User Guide.
 *
 * <p>During the construction of a <code>CollationKey</code>, the
 * entire source string is examined and processed into a series of
 * bits terminated by a null, that are stored in the <code>CollationKey</code>.
 * When <code>CollationKey.compareTo(CollationKey)</code> executes, it
 * performs bitwise comparison on the bit sequences.  This can incurs
 * startup cost when creating the <code>CollationKey</code>, but once
 * the key is created, binary comparisons are fast.  This approach is
 * recommended when the same strings are to be compared over and over
 * again.
 *
 * <p>On the other hand, implementations of
 * <code>Collator.compare(String, String)</code> can examine and
 * process the strings only until the first characters differing in
 * order.  This approach is recommended if the strings are to be
 * compared only once.</p>
 *
 * <p>More information about the composition of the bit sequence can
 * be found in the
 * <a href="https://unicode-org.github.io/icu/userguide/collation/architecture">
 * user guide</a>.</p>
 *
 * <p>The following example shows how <code>CollationKey</code>s can be used
 * to sort a list of <code>String</code>s.</p>
 * <blockquote>
 * <pre>
 * // Create an array of CollationKeys for the Strings to be sorted.
 * Collator myCollator = Collator.getInstance();
 * CollationKey[] keys = new CollationKey[3];
 * keys[0] = myCollator.getCollationKey("Tom");
 * keys[1] = myCollator.getCollationKey("Dick");
 * keys[2] = myCollator.getCollationKey("Harry");
 * sort( keys );
 * <br>
 * //...
 * <br>
 * // Inside body of sort routine, compare keys this way
 * if( keys[i].compareTo( keys[j] ) &gt; 0 )
 *    // swap keys[i] and keys[j]
 * <br>
 * //...
 * <br>
 * // Finally, when we've returned from sort.
 * System.out.println( keys[0].getSourceString() );
 * System.out.println( keys[1].getSourceString() );
 * System.out.println( keys[2].getSourceString() );
 * </pre>
 * </blockquote>
 * <p>
 * This class is not subclassable
 * @see Collator
 * @see RuleBasedCollator
 * @author Syn Wee Quek
 */
public final class CollationKey {
    private CollationKey() {
    }

    /**
     * Options that used in the API CollationKey.getBound() for getting a
     * CollationKey based on the bound mode requested.
     */
    public static final class BoundMode {
        /**
         * Lower bound
         */
        public static final int LOWER = 0;

        /**
         * Upper bound that will match strings of exact size
         */
        public static final int UPPER = 1;

        /**
         * Upper bound that will match all the strings that have the same
         * initial substring as the given string
         */
        public static final int UPPER_LONG = 2;

        private BoundMode() {
        }
    }
}
