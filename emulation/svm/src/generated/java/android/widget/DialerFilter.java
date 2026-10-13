package android.widget;

/**
 * This widget is a layout that contains several specifically-named child views that
 * handle keyboard entry interpreted as standard phone dialpad digits.
 *
 * @deprecated Use a custom view or layout to handle this functionality instead
 */
@Deprecated
public final class DialerFilter {
    /**
     * This mode has both lines
     */
    @Deprecated
    public static final int DIGITS_AND_LETTERS = 1;

    /**
     * This mode is when after starting in {@link #DIGITS_AND_LETTERS} mode the filter
     *   has removed all possibility of the digits matching, leaving only the letters line
     */
    @Deprecated
    public static final int DIGITS_AND_LETTERS_NO_DIGITS = 2;

    /**
     * This mode is when after starting in {@link #DIGITS_AND_LETTERS} mode the filter
     *   has removed all possibility of the letters matching, leaving only the digits line
     */
    @Deprecated
    public static final int DIGITS_AND_LETTERS_NO_LETTERS = 3;

    /**
     * This mode has only the digits line
     */
    @Deprecated
    public static final int DIGITS_ONLY = 4;

    /**
     * This mode has only the letters line
     */
    @Deprecated
    public static final int LETTERS_ONLY = 5;

    private DialerFilter() {
    }
}
