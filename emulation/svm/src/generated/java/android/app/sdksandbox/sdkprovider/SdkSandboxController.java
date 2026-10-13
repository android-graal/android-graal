package android.app.sdksandbox.sdkprovider;

/**
 * Controller that is used by SDK loaded in the sandbox to access information provided by the sdk
 * sandbox.
 *
 * <p>It enables the SDK to communicate with other SDKS in the SDK sandbox and know about the state
 * of the sdks that are currently loaded in it.
 *
 * <p>An instance of {@link SdkSandboxController} can be obtained using {@link
 * Context#getSystemService} and {@link SdkSandboxController class}. The {@link Context} can in turn
 * be obtained using {@link android.app.sdksandbox.SandboxedSdkProvider#getContext()}.
 */
public final class SdkSandboxController {
    public static final String SDK_SANDBOX_CONTROLLER_SERVICE = "sdk_sandbox_controller_service";

    private SdkSandboxController() {
    }
}
