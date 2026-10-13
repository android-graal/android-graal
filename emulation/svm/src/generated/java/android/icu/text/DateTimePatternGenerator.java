package android.icu.text;

/**
 * This class provides flexible generation of date format patterns, like
 * "yy-MM-dd". The user can build up the generator by adding successive
 * patterns. Once that is done, a query can be made using a "skeleton", which is
 * a pattern which just includes the desired fields and lengths. The generator
 * will return the "best fit" pattern corresponding to that skeleton.
 * <p>
 * The main method people will use is getBestPattern(String skeleton), since
 * normally this class is pre-built with data from a particular locale. However,
 * generators can be built directly from other data as well.
 */
public final class DateTimePatternGenerator {
    /**
     *
     */
    public static final int DAY = 7;

    /**
     *
     */
    public static final int DAYPERIOD = 10;

    /**
     *
     */
    public static final int DAY_OF_WEEK_IN_MONTH = 9;

    /**
     *
     */
    public static final int DAY_OF_YEAR = 8;

    /**
     *
     */
    public static final int ERA = 0;

    /**
     *
     */
    public static final int FRACTIONAL_SECOND = 14;

    /**
     *
     */
    public static final int HOUR = 11;

    /**
     * Option mask for forcing the width of all date and time fields.
     * @see #getBestPattern(String, int)
     * @see #replaceFieldTypes(String, String, int)
     */
    public static final int MATCH_ALL_FIELDS_LENGTH = 65535;

    /**
     * Option mask for forcing the width of hour field.
     * @see #getBestPattern(String, int)
     * @see #replaceFieldTypes(String, String, int)
     */
    public static final int MATCH_HOUR_FIELD_LENGTH = 2048;

    /**
     * Default option mask used for {@link #getBestPattern(String, int)}
     * and {@link #replaceFieldTypes(String, String, int)}.
     * @see #getBestPattern(String, int)
     * @see #replaceFieldTypes(String, String, int)
     */
    public static final int MATCH_NO_OPTIONS = 0;

    /**
     *
     */
    public static final int MINUTE = 12;

    /**
     *
     */
    public static final int MONTH = 3;

    /**
     *
     */
    public static final int QUARTER = 2;

    /**
     *
     */
    public static final int SECOND = 13;

    /**
     *
     */
    public static final int WEEKDAY = 6;

    /**
     *
     */
    public static final int WEEK_OF_MONTH = 5;

    /**
     *
     */
    public static final int WEEK_OF_YEAR = 4;

    /**
     *
     */
    public static final int YEAR = 1;

    /**
     *
     */
    public static final int ZONE = 15;

    private DateTimePatternGenerator() {
    }

    /**
     * PatternInfo supplies output parameters for addPattern(...). It is used because
     * Java doesn't have real output parameters. It is treated like a struct (eg
     * Point), so all fields are public.
     */
    public static final class PatternInfo {
        /**
         *
         */
        public static final int BASE_CONFLICT = 1;

        /**
         *
         */
        public static final int CONFLICT = 2;

        /**
         *
         */
        public static final int OK = 0;

        private PatternInfo() {
        }
    }
}
