package android.drm;

/**
 * The main programming interface for the DRM framework. An application must instantiate this class
 * to access DRM agents through the DRM framework.
 *
 * @deprecated Please use {@link android.media.MediaDrm}
 */
@Deprecated
public final class DrmManagerClient {
    /**
     * Indicates that a request was successful or that no error occurred.
     */
    @Deprecated
    public static final int ERROR_NONE = 0;

    /**
     * Indicates that an error occurred and the reason is not known.
     */
    @Deprecated
    public static final int ERROR_UNKNOWN = -2000;

    private DrmManagerClient() {
    }
}
