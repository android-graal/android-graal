package android.media.tv.interactive;

/**
 * Displays contents of interactive TV applications.
 */
public final class TvInteractiveAppView {
    /**
     * Used to share the {@link KeyStore} alias with {@link TvInteractiveAppService}.
     * @see #createBiInteractiveApp(Uri, Bundle)
     * @see KeyStore#aliases()
     */
    public static final String BI_INTERACTIVE_APP_KEY_ALIAS = "alias";

    /**
     * Used to share client {@link java.security.cert.Certificate} with
     * {@link TvInteractiveAppService}.
     * @see #createBiInteractiveApp(Uri, Bundle)
     * @see java.security.cert.Certificate
     */
    public static final String BI_INTERACTIVE_APP_KEY_CERTIFICATE = "certificate";

    /**
     * Additional HTTP headers to be used by {@link TvInteractiveAppService} to load the
     * broadcast-independent interactive application.
     * @see #createBiInteractiveApp(Uri, Bundle)
     */
    public static final String BI_INTERACTIVE_APP_KEY_HTTP_ADDITIONAL_HEADERS = "http_additional_headers";

    /**
     * HTTP user agent to be used by {@link TvInteractiveAppService} for broadcast-independent
     * interactive application.
     * @see #createBiInteractiveApp(Uri, Bundle)
     */
    public static final String BI_INTERACTIVE_APP_KEY_HTTP_USER_AGENT = "http_user_agent";

    /**
     * Used to share the {@link java.security.PrivateKey} with {@link TvInteractiveAppService}.
     * <p>The private key is optional. It is used to encrypt data when necessary.
     *
     * @see #createBiInteractiveApp(Uri, Bundle)
     * @see java.security.PrivateKey
     */
    public static final String BI_INTERACTIVE_APP_KEY_PRIVATE_KEY = "private_key";

    /**
     * The name of the method where the error happened, if applicable. For example, if there is an
     * error during signing, the request name is "onRequestSigning".
     * @see #notifyError(String, Bundle)
     */
    public static final String ERROR_KEY_METHOD_NAME = "method_name";

    private TvInteractiveAppView() {
    }
}
