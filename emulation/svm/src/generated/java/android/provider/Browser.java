package android.provider;

public final class Browser {
    /**
     * The name of the extra data when starting the Browser from another
     * application.
     * <p>
     * The value is a unique identification string that will be used to
     * identify the calling application. The Browser will attempt to reuse the
     * same window each time the application launches the Browser with the same
     * identifier.
     */
    public static final String EXTRA_APPLICATION_ID = "com.android.browser.application_id";

    /**
     * Boolean extra passed along with an Intent to a browser, specifying that
     * a new tab be created.  Overrides EXTRA_APPLICATION_ID; if both are set,
     * a new tab will be used, rather than using the same one.
     */
    public static final String EXTRA_CREATE_NEW_TAB = "create_new_tab";

    /**
     * The name of the extra data in the VIEW intent. The data are key/value
     * pairs in the format of Bundle. They will be sent in the HTTP request
     * headers for the provided url. The keys can't be the standard HTTP headers
     * as they are set by the WebView. The url's schema must be http(s).
     * <p>
     */
    public static final String EXTRA_HEADERS = "com.android.browser.headers";

    /**
     * The name of extra data when starting Browser with ACTION_VIEW or
     * ACTION_SEARCH intent.
     * <p>
     * The value should be an integer between 0 and 1000. If not set or set to
     * 0, the Browser will use default. If set to 100, the Browser will start
     * with 100%.
     */
    public static final String INITIAL_ZOOM_LEVEL = "browser.initialZoomLevel";

    private Browser() {
    }
}
