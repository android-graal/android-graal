package android.provider;

/**
 * Utility class to deal with Font ContentProviders.
 * @deprecated Use the <a href="{@docRoot}jetpack">Jetpack Core Library</a>
 *      {@link androidx.core.provider.FontsContractCompat} for consistent behavior across all
 *      devices.
 */
@Deprecated
public final class FontsContract {
    private FontsContract() {
    }

    /**
     * Defines the constants used in a response from a Font Provider. The cursor returned from the
     * query should have the ID column populated with the content uri ID for the resulting font.
     * This should point to a real file or shared memory, as the client will mmap the given file
     * descriptor. Pipes, sockets and other non-mmap-able file descriptors will fail to load in the
     * client application.
     *
     * @deprecated Use the {@link androidx.core.provider.FontsContractCompat.Columns} for consistent
     * behavior across all devices.
     */
    @Deprecated
    public static final class Columns implements BaseColumns {
        /**
         * Constant used to request data from a font provider. The cursor returned from the query
         * may populate this column with a long for the font file ID. The client will request a file
         * descriptor to "file/FILE_ID" with this ID immediately under the top-level content URI. If
         * not present, the client will request a file descriptor to the top-level URI with the
         * given base font ID. Note that several results may return the same file ID, e.g. for TTC
         * files with different indices.
         */
        @Deprecated
        public static final String FILE_ID = "file_id";

        /**
         * Constant used to request data from a font provider. The cursor returned from the query
         * should have this column populated with the int italic for the resulting font. This should
         * be 0 for regular style and 1 for italic.
         */
        @Deprecated
        public static final String ITALIC = "font_italic";

        /**
         * Constant used to request data from a font provider. The cursor returned from the query
         * should have this column populated to indicate the result status of the
         * query. This will be checked before any other data in the cursor. Possible values are
         * {@link #RESULT_CODE_OK}, {@link #RESULT_CODE_FONT_NOT_FOUND},
         * {@link #RESULT_CODE_MALFORMED_QUERY} and {@link #RESULT_CODE_FONT_UNAVAILABLE} for system
         * defined values. You may also define your own values in the 0x000010000..0xFFFF0000 range.
         * If not present, {@link #RESULT_CODE_OK} will be assumed.
         */
        @Deprecated
        public static final String RESULT_CODE = "result_code";

        /**
         * Constant used to represent a result was not found. See {@link #RESULT_CODE}.
         */
        @Deprecated
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;

        /**
         * Constant used to represent a result was found, but cannot be provided at this moment. Use
         * this to indicate, for example, that a font needs to be fetched from the network. See
         * {@link #RESULT_CODE}.
         */
        @Deprecated
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;

        /**
         * Constant used to represent that the query was not in a supported format by the provider.
         * See {@link #RESULT_CODE}.
         */
        @Deprecated
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;

        /**
         * Constant used to represent a result was retrieved successfully. The given fonts will be
         * attempted to retrieve immediately via
         * {@link android.content.ContentProvider#openFile(Uri, String)}. See {@link #RESULT_CODE}.
         */
        @Deprecated
        public static final int RESULT_CODE_OK = 0;

        /**
         * Constant used to request data from a font provider. The cursor returned from the query
         * should have this column populated with an int for the ttc index for the resulting font.
         */
        @Deprecated
        public static final String TTC_INDEX = "font_ttc_index";

        /**
         * Constant used to request data from a font provider. The cursor returned from the query
         * may populate this column with the font variation settings String information for the
         * font.
         */
        @Deprecated
        public static final String VARIATION_SETTINGS = "font_variation_settings";

        /**
         * Constant used to request data from a font provider. The cursor returned from the query
         * should have this column populated with the int weight for the resulting font. This value
         * should be between 100 and 900. The most common values are 400 for regular weight and 700
         * for bold weight.
         */
        @Deprecated
        public static final String WEIGHT = "font_weight";

        private Columns() {
        }
    }

    /**
     * Object returned from {@link #fetchFonts}.
     *
     * @deprecated Use the {@link androidx.core.provider.FontsContractCompat.FontFamilyResult} for
     * consistent behavior across all devices
     */
    @Deprecated
    public static final class FontFamilyResult {
        /**
         * Constant represents that the font was successfully retrieved. Note that when this value
         * is set and {@link #getFonts} returns an empty array, it means there were no fonts
         * matching the given query.
         */
        @Deprecated
        public static final int STATUS_OK = 0;

        /**
         * Constant represents that the fetching font data was rejected by system. This happens if
         * the passed context is restricted.
         */
        @Deprecated
        public static final int STATUS_REJECTED = 3;

        /**
         * Constant represents that the provider returns unexpected data. {@link #getFonts} returns
         * null if this status was set. For example, this value is set when the font provider
         * gives invalid format of variation settings.
         */
        @Deprecated
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;

        /**
         * Constant represents that the given certificate was not matched with the provider's
         * signature. {@link #getFonts} returns null if this status was set.
         */
        @Deprecated
        public static final int STATUS_WRONG_CERTIFICATES = 1;

        private FontFamilyResult() {
        }
    }

    /**
     * Interface used to receive asynchronously fetched typefaces.
     *
     * @deprecated Use the {@link androidx.core.provider.FontsContractCompat.FontRequestCallback}
     * for consistent behavior across all devices
     */
    @Deprecated
    public static final class FontRequestCallback {
        /**
         * Constant returned by {@link #onTypefaceRequestFailed(int)} signaling that the font
         * returned by the provider was not loaded properly.
         */
        @Deprecated
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;

        /**
         * Constant returned by {@link #onTypefaceRequestFailed(int)} signaling that the font
         * provider did not return any results for the given query.
         */
        @Deprecated
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;

        /**
         * Constant returned by {@link #onTypefaceRequestFailed(int)} signaling that the font
         * provider found the queried font, but it is currently unavailable.
         */
        @Deprecated
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;

        /**
         * Constant returned by {@link #onTypefaceRequestFailed(int)} signaling that the given
         * query was not supported by the provider.
         */
        @Deprecated
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;

        /**
         * Constant returned by {@link #onTypefaceRequestFailed(int)} signaling that the given
         * provider was not found on the device.
         */
        @Deprecated
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;

        /**
         * Constant returned by {@link #onTypefaceRequestFailed(int)} signaling that the given
         * provider must be authenticated and the given certificates do not match its signature.
         */
        @Deprecated
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;

        private FontRequestCallback() {
        }
    }
}
