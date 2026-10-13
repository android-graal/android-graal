package android.webkit;

/**
 * Sorts dates into the following groups:
 *   Today
 *   Yesterday
 *   seven days ago
 *   one month ago
 *   older than a month ago
 */
public final class DateSorter {
    /**
     * must be >= 3
     */
    public static final int DAY_COUNT = 5;

    private DateSorter() {
    }
}
