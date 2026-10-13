package android.icu.util;

/**
 * A class for accessing miscellaneous data in the locale bundles
 * @author ram
 */
public final class LocaleData {
    /**
     * Delimiter type for {@link #getDelimiter(int)}.
     */
    public static final int ALT_QUOTATION_END = 3;

    /**
     * Delimiter type for {@link #getDelimiter(int)}.
     */
    public static final int ALT_QUOTATION_START = 2;

    /**
     * Delimiter type for {@link #getDelimiter(int)}.
     */
    public static final int QUOTATION_END = 1;

    /**
     * Delimiter type for {@link #getDelimiter(int)}.
     */
    public static final int QUOTATION_START = 0;

    private LocaleData() {
    }
}
