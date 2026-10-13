package android.media.tv.ad;

/**
 * Displays contents of TV advertisement services.
 */
public final class TvAdView {
    /**
     * The error code of an error.
     *
     * <p>It can be {@link TvAdManager#ERROR_WEAK_SIGNAL},
     * {@link TvAdManager#ERROR_RESOURCE_UNAVAILABLE}, etc.
     *
     * @see #notifyError(String, Bundle)
     */
    public static final String ERROR_KEY_ERROR_CODE = "error_code";

    /**
     * The name of the method where the error happened, if applicable. For example, if there is an
     * error during signing, the request name is "onRequestSigning".
     * @see #notifyError(String, Bundle)
     */
    public static final String ERROR_KEY_METHOD_NAME = "method_name";

    private TvAdView() {
    }
}
