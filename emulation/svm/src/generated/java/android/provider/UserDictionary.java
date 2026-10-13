package android.provider;

/**
 * A provider of user defined words for input methods to use for predictive text input.
 * Applications and input methods may add words into the dictionary. Words can have associated
 * frequency information and locale information.
 *
 * <p><strong>NOTE: </strong>Starting on API 23, the user dictionary is only accessible through
 * IME and spellchecker.
 */
public final class UserDictionary {
    /**
     * Authority string for this provider.
     */
    public static final String AUTHORITY = "user_dictionary";

    private UserDictionary() {
    }

    /**
     * Contains the user defined words.
     */
    public static final class Words implements BaseColumns {
        /**
         * The uid of the application that inserted the word.
         * <p>TYPE: INTEGER</p>
         */
        public static final String APP_ID = "appid";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single word.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.userword";

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of words.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.userword";

        /**
         * Sort by descending order of frequency.
         */
        public static final String DEFAULT_SORT_ORDER = "frequency DESC";

        /**
         * The frequency column. A value between 1 and 255. Higher values imply higher frequency.
         * <p>TYPE: INTEGER</p>
         */
        public static final String FREQUENCY = "frequency";

        /**
         * The locale that this word belongs to. Null if it pertains to all
         * locales. Locale is as defined by the string returned by Locale.toString().
         * <p>TYPE: TEXT</p>
         */
        public static final String LOCALE = "locale";

        /**
         * @deprecated Use {@link #addWord(Context, String, int, String, Locale)}.
         */
        @Deprecated
        public static final int LOCALE_TYPE_ALL = 0;

        /**
         * @deprecated Use {@link #addWord(Context, String, int, String, Locale)}.
         */
        @Deprecated
        public static final int LOCALE_TYPE_CURRENT = 1;

        /**
         * An optional shortcut for this word. When the shortcut is typed, supporting IMEs should
         * suggest the word in this row as an alternate spelling too.
         */
        public static final String SHORTCUT = "shortcut";

        /**
         * The word column.
         * <p>TYPE: TEXT</p>
         */
        public static final String WORD = "word";

        public static final String _ID = "_id";

        private Words() {
        }
    }
}
