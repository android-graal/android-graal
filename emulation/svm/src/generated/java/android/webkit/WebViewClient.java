package android.webkit;

public final class WebViewClient {
    /**
     * User authentication failed on server
     */
    public static final int ERROR_AUTHENTICATION = -4;

    /**
     * Malformed URL
     */
    public static final int ERROR_BAD_URL = -12;

    /**
     * Failed to connect to the server
     */
    public static final int ERROR_CONNECT = -6;

    /**
     * Failed to perform SSL handshake
     */
    public static final int ERROR_FAILED_SSL_HANDSHAKE = -11;

    /**
     * Generic file error
     */
    public static final int ERROR_FILE = -13;

    /**
     * File not found
     */
    public static final int ERROR_FILE_NOT_FOUND = -14;

    /**
     * Server or proxy hostname lookup failed
     */
    public static final int ERROR_HOST_LOOKUP = -2;

    /**
     * Failed to read or write to the server
     */
    public static final int ERROR_IO = -7;

    /**
     * User authentication failed on proxy
     */
    public static final int ERROR_PROXY_AUTHENTICATION = -5;

    /**
     * Too many redirects
     */
    public static final int ERROR_REDIRECT_LOOP = -9;

    /**
     * Connection timed out
     */
    public static final int ERROR_TIMEOUT = -8;

    /**
     * Too many requests during this load
     */
    public static final int ERROR_TOO_MANY_REQUESTS = -15;

    /**
     * Generic error
     */
    public static final int ERROR_UNKNOWN = -1;

    /**
     * Resource load was canceled by Safe Browsing
     */
    public static final int ERROR_UNSAFE_RESOURCE = -16;

    /**
     * Unsupported authentication scheme (not basic or digest)
     */
    public static final int ERROR_UNSUPPORTED_AUTH_SCHEME = -3;

    /**
     * Unsupported URI scheme
     */
    public static final int ERROR_UNSUPPORTED_SCHEME = -10;

    /**
     * The resource was blocked because it may trick the user into a billing agreement.
     *
     * <p>This constant is only used when targetSdkVersion is at least {@link
     * android.os.Build.VERSION_CODES#Q}. Otherwise, {@link #SAFE_BROWSING_THREAT_UNKNOWN} is used
     * instead.
     */
    public static final int SAFE_BROWSING_THREAT_BILLING = 4;

    /**
     * The resource was blocked because it contains malware.
     */
    public static final int SAFE_BROWSING_THREAT_MALWARE = 1;

    /**
     * The resource was blocked because it contains deceptive content.
     */
    public static final int SAFE_BROWSING_THREAT_PHISHING = 2;

    /**
     * The resource was blocked for an unknown reason.
     */
    public static final int SAFE_BROWSING_THREAT_UNKNOWN = 0;

    /**
     * The resource was blocked because it contains unwanted software.
     */
    public static final int SAFE_BROWSING_THREAT_UNWANTED_SOFTWARE = 3;

    private WebViewClient() {
    }
}
