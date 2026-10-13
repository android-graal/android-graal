package android.app;

/**
 * Class for interacting with the device's UI by simulation user actions and
 * introspection of the screen content. It relies on the platform accessibility
 * APIs to introspect the screen and to perform some actions on the remote view
 * tree. It also allows injecting of arbitrary raw input events simulating user
 * interaction with keyboards and touch devices. One can think of a UiAutomation
 * as a special type of {@link android.accessibilityservice.AccessibilityService}
 * which does not provide hooks for the service life cycle and exposes other
 * APIs that are useful for UI test automation.
 * <p>
 * The APIs exposed by this class are low-level to maximize flexibility when
 * developing UI test automation tools and libraries. Generally, a UiAutomation
 * client should be using a higher-level library or implement high-level functions.
 * For example, performing a tap on the screen requires construction and injecting
 * of a touch down and up events which have to be delivered to the system by a
 * call to {@link #injectInputEvent(InputEvent, boolean)}.
 * </p>
 * <p>
 * The APIs exposed by this class operate across applications enabling a client
 * to write tests that cover use cases spanning over multiple applications. For
 * example, going to the settings application to change a setting and then
 * interacting with another application whose behavior depends on that setting.
 * </p>
 */
public final class UiAutomation {
    /**
     * UiAutomation suppresses accessibility services by default. This flag specifies that
     * existing accessibility services should continue to run, and that new ones may start.
     * This flag is set when obtaining the UiAutomation from
     * {@link Instrumentation#getUiAutomation(int)}.
     */
    public static final int FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES = 1;

    /**
     * UiAutomation uses the accessibility subsystem by default. This flag provides an option to
     * eliminate the overhead of engaging the accessibility subsystem for tests that do not need to
     * interact with the user interface. Setting this flag disables methods that rely on
     * accessibility. This flag is set when obtaining the UiAutomation from
     * {@link Instrumentation#getUiAutomation(int)}.
     */
    public static final int FLAG_DONT_USE_ACCESSIBILITY = 2;

    /**
     * Rotation constant: Freeze rotation to 0 degrees (natural orientation)
     */
    public static final int ROTATION_FREEZE_0 = 0;

    /**
     * Rotation constant: Freeze rotation to 180 degrees .
     */
    public static final int ROTATION_FREEZE_180 = 2;

    /**
     * Rotation constant: Freeze rotation to 270 degrees .
     */
    public static final int ROTATION_FREEZE_270 = 3;

    /**
     * Rotation constant: Freeze rotation to 90 degrees .
     */
    public static final int ROTATION_FREEZE_90 = 1;

    /**
     * Rotation constant: Freeze rotation to its current state.
     */
    public static final int ROTATION_FREEZE_CURRENT = -1;

    /**
     * Rotation constant: Unfreeze rotation (rotating the device changes its rotation state).
     */
    public static final int ROTATION_UNFREEZE = -2;

    private UiAutomation() {
    }
}
