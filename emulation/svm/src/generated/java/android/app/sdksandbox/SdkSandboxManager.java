package android.app.sdksandbox;

/**
 * Provides APIs to load {@link android.content.pm.SharedLibraryInfo#TYPE_SDK_PACKAGE SDKs} into the
 * SDK sandbox process, and then interact with them.
 *
 * <p>SDK sandbox is a java process running in a separate uid range. Each app may have its own SDK
 * sandbox process.
 *
 * <p>The app first needs to declare SDKs it depends on in its manifest using the {@code
 * <uses-sdk-library>} tag. Apps may only load SDKs they depend on into the SDK sandbox.
 *
 * @see android.content.pm.SharedLibraryInfo#TYPE_SDK_PACKAGE
 * @see <a href="https://developer.android.com/design-for-safety/ads/sdk-runtime">SDK Runtime design
 *     proposal</a>
 */
public final class SdkSandboxManager {
    /**
     * The name of key to be used in the Bundle fields of {@link #requestSurfacePackage(String,
     * Bundle, Executor, OutcomeReceiver)}, its value should define the integer ID of the logical
     * display to display the {@link SurfacePackage}.
     *
     * @deprecated Parameter for {@link #requestSurfacePackage(String, Bundle, Executor,
     *     OutcomeReceiver)} which is getting deprecated.
     */
    @Deprecated
    public static final String EXTRA_DISPLAY_ID = "android.app.sdksandbox.extra.DISPLAY_ID";

    /**
     * The name of key to be used in the Bundle fields of {@link #requestSurfacePackage(String,
     * Bundle, Executor, OutcomeReceiver)}, its value should define the integer height of the {@link
     * SurfacePackage} in pixels.
     *
     * @deprecated Parameter for {@link #requestSurfacePackage(String, Bundle, Executor,
     *     OutcomeReceiver)} which is getting deprecated.
     */
    @Deprecated
    public static final String EXTRA_HEIGHT_IN_PIXELS = "android.app.sdksandbox.extra.HEIGHT_IN_PIXELS";

    /**
     * The name of key to be used in the Bundle fields of {@link #requestSurfacePackage(String,
     * Bundle, Executor, OutcomeReceiver)}, its value should present the token returned by {@link
     * android.view.SurfaceView#getHostToken()} once the {@link android.view.SurfaceView} has been
     * added to the view hierarchy. Only a non-null value is accepted to enable ANR reporting.
     *
     * @deprecated Parameter for {@link #requestSurfacePackage(String, Bundle, Executor,
     *     OutcomeReceiver)} which is getting deprecated.
     */
    @Deprecated
    public static final String EXTRA_HOST_TOKEN = "android.app.sdksandbox.extra.HOST_TOKEN";

    /**
     * The name of key in the Bundle which is passed to the {@code onResult} function of the {@link
     * OutcomeReceiver} which is field of {@link #requestSurfacePackage(String, Bundle, Executor,
     * OutcomeReceiver)}, its value presents the requested {@link SurfacePackage}.
     *
     * @deprecated Parameter for {@link #requestSurfacePackage(String, Bundle, Executor,
     *     OutcomeReceiver)} which is getting deprecated.
     */
    @Deprecated
    public static final String EXTRA_SURFACE_PACKAGE = "android.app.sdksandbox.extra.SURFACE_PACKAGE";

    /**
     * The name of key to be used in the Bundle fields of {@link #requestSurfacePackage(String,
     * Bundle, Executor, OutcomeReceiver)}, its value should define the integer width of the {@link
     * SurfacePackage} in pixels.
     *
     * @deprecated Parameter for {@link #requestSurfacePackage(String, Bundle, Executor,
     *     OutcomeReceiver)} which is getting deprecated.
     */
    @Deprecated
    public static final String EXTRA_WIDTH_IN_PIXELS = "android.app.sdksandbox.extra.WIDTH_IN_PIXELS";

    /**
     * SDK is already loaded.
     *
     * <p>This indicates that client application tried to reload the same SDK by calling {@link
     * SdkSandboxManager#loadSdk(String, Bundle, Executor, OutcomeReceiver)} after being
     * successfully loaded.
     */
    public static final int LOAD_SDK_ALREADY_LOADED = 101;

    /**
     * Internal error while loading SDK.
     *
     * <p>This indicates a generic internal error happened while applying the call from client
     * application.
     */
    public static final int LOAD_SDK_INTERNAL_ERROR = 500;

    /**
     * SDK not found.
     *
     * <p>This indicates that client application tried to load a non-existing SDK by calling {@link
     * SdkSandboxManager#loadSdk(String, Bundle, Executor, OutcomeReceiver)}.
     */
    public static final int LOAD_SDK_NOT_FOUND = 100;

    /**
     * SDK error after being loaded.
     *
     * <p>This indicates that the SDK encountered an error during post-load initialization. The
     * details of this can be obtained from the Bundle returned in {@link LoadSdkException} through
     * the {@link OutcomeReceiver} passed in to {@link SdkSandboxManager#loadSdk}.
     */
    public static final int LOAD_SDK_SDK_DEFINED_ERROR = 102;

    /**
     * SDK sandbox is disabled.
     *
     * <p>This indicates that the SDK sandbox is disabled. Any subsequent attempts to load SDKs in
     * this boot will also fail.
     */
    public static final int LOAD_SDK_SDK_SANDBOX_DISABLED = 103;

    /**
     * Internal error while requesting a {@link SurfacePackage}.
     *
     *  <p>This indicates a generic internal error happened while requesting a
     *  {@link SurfacePackage}.
     */
    public static final int REQUEST_SURFACE_PACKAGE_INTERNAL_ERROR = 700;

    /**
     * SDK is not loaded while requesting a {@link SurfacePackage}.
     *
     * <p>This indicates that the SDK for which the {@link SurfacePackage} is being requested is not
     * loaded, either because the sandbox died or because it was not loaded in the first place.
     */
    public static final int REQUEST_SURFACE_PACKAGE_SDK_NOT_LOADED = 701;

    /**
     * SDK sandbox process is not available.
     *
     * <p>This indicates that the SDK sandbox process is not available, either because it has died,
     * disconnected or was not created in the first place.
     */
    public static final int SDK_SANDBOX_PROCESS_NOT_AVAILABLE = 503;

    /**
     * Use with {@link Context#getSystemService(String)} to retrieve an {@link SdkSandboxManager}
     * for interacting with the SDKs belonging to this client application.
     */
    public static final String SDK_SANDBOX_SERVICE = "sdk_sandbox";

    /**
     * SDK sandbox is disabled.
     *
     * <p>{@link SdkSandboxManager} APIs are hidden. Attempts at calling them will result in {@link
     * UnsupportedOperationException}.
     */
    public static final int SDK_SANDBOX_STATE_DISABLED = 0;

    /**
     * SDK sandbox is enabled.
     *
     * <p>App can use {@link SdkSandboxManager} APIs to load {@code SDKs} it depends on into the
     * corresponding SDK sandbox process.
     */
    public static final int SDK_SANDBOX_STATE_ENABLED_PROCESS_ISOLATION = 2;

    private SdkSandboxManager() {
    }
}
