package android.accessibilityservice;

import android.os.Parcelable;

/**
 * This class describes an {@link AccessibilityService}. The system notifies an
 * {@link AccessibilityService} for {@link android.view.accessibility.AccessibilityEvent}s
 * according to the information encapsulated in this class.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about creating AccessibilityServices, read the
 * <a href="{@docRoot}guide/topics/ui/accessibility/index.html">Accessibility</a>
 * developer guide.</p>
 * </div>
 *
 * @attr ref android.R.styleable#AccessibilityService_accessibilityEventTypes
 * @attr ref android.R.styleable#AccessibilityService_accessibilityFeedbackType
 * @attr ref android.R.styleable#AccessibilityService_accessibilityFlags
 * @attr ref android.R.styleable#AccessibilityService_animatedImageDrawable
 * @attr ref android.R.styleable#AccessibilityService_canControlMagnification
 * @attr ref android.R.styleable#AccessibilityService_canPerformGestures
 * @attr ref android.R.styleable#AccessibilityService_canRequestFilterKeyEvents
 * @attr ref android.R.styleable#AccessibilityService_canRequestFingerprintGestures
 * @attr ref android.R.styleable#AccessibilityService_canRequestTouchExplorationMode
 * @attr ref android.R.styleable#AccessibilityService_canRetrieveWindowContent
 * @attr ref android.R.styleable#AccessibilityService_canTakeScreenshot
 * @attr ref android.R.styleable#AccessibilityService_description
 * @attr ref android.R.styleable#AccessibilityService_htmlDescription
 * @attr ref android.R.styleable#AccessibilityService_interactiveUiTimeout
 * @attr ref android.R.styleable#AccessibilityService_intro
 * @attr ref android.R.styleable#AccessibilityService_isAccessibilityTool
 * @attr ref android.R.styleable#AccessibilityService_nonInteractiveUiTimeout
 * @attr ref android.R.styleable#AccessibilityService_notificationTimeout
 * @attr ref android.R.styleable#AccessibilityService_packageNames
 * @attr ref android.R.styleable#AccessibilityService_settingsActivity
 * @attr ref android.R.styleable#AccessibilityService_summary
 * @attr ref android.R.styleable#AccessibilityService_tileService
 * @see AccessibilityService
 * @see android.view.accessibility.AccessibilityEvent
 * @see android.view.accessibility.AccessibilityManager
 */
public final class AccessibilityServiceInfo implements Parcelable {
    /**
     * Capability: This accessibility service can control display magnification.
     * @see android.R.styleable#AccessibilityService_canControlMagnification
     */
    public static final int CAPABILITY_CAN_CONTROL_MAGNIFICATION = 16;

    /**
     * Capability: This accessibility service can perform gestures.
     * @see android.R.styleable#AccessibilityService_canPerformGestures
     */
    public static final int CAPABILITY_CAN_PERFORM_GESTURES = 32;

    /**
     * @deprecated No longer used
     */
    @Deprecated
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;

    /**
     * Capability: This accessibility service can request to filter the key event stream.
     * @see android.R.styleable#AccessibilityService_canRequestFilterKeyEvents
     */
    public static final int CAPABILITY_CAN_REQUEST_FILTER_KEY_EVENTS = 8;

    /**
     * Capability: This accessibility service can capture gestures from the fingerprint sensor
     * @see android.R.styleable#AccessibilityService_canRequestFingerprintGestures
     */
    public static final int CAPABILITY_CAN_REQUEST_FINGERPRINT_GESTURES = 64;

    /**
     * Capability: This accessibility service can request touch exploration mode in which
     * touched items are spoken aloud and the UI can be explored via gestures.
     * @see android.R.styleable#AccessibilityService_canRequestTouchExplorationMode
     */
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;

    /**
     * Capability: This accessibility service can retrieve the active window content.
     * @see android.R.styleable#AccessibilityService_canRetrieveWindowContent
     */
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;

    /**
     * Capability: This accessibility service can take screenshot.
     * @see android.R.styleable#AccessibilityService_canTakeScreenshot
     */
    public static final int CAPABILITY_CAN_TAKE_SCREENSHOT = 128;

    /**
     * If an {@link AccessibilityService} is the default for a given type.
     * Default service is invoked only if no package specific one exists. In case of
     * more than one package specific service only the earlier registered is notified.
     */
    public static final int DEFAULT = 1;

    /**
     * Mask for all feedback types.
     *
     * @see #FEEDBACK_SPOKEN
     * @see #FEEDBACK_HAPTIC
     * @see #FEEDBACK_AUDIBLE
     * @see #FEEDBACK_VISUAL
     * @see #FEEDBACK_GENERIC
     * @see #FEEDBACK_BRAILLE
     */
    public static final int FEEDBACK_ALL_MASK = -1;

    /**
     * Denotes audible (not spoken) feedback.
     */
    public static final int FEEDBACK_AUDIBLE = 4;

    /**
     * Denotes braille feedback.
     */
    public static final int FEEDBACK_BRAILLE = 32;

    /**
     * Denotes generic feedback.
     */
    public static final int FEEDBACK_GENERIC = 16;

    /**
     * Denotes haptic feedback.
     */
    public static final int FEEDBACK_HAPTIC = 2;

    /**
     * Denotes spoken feedback.
     */
    public static final int FEEDBACK_SPOKEN = 1;

    /**
     * Denotes visual feedback.
     */
    public static final int FEEDBACK_VISUAL = 8;

    /**
     * This flag requests that all audio tracks system-wide with
     * {@link android.media.AudioAttributes#USAGE_ASSISTANCE_ACCESSIBILITY} be controlled by the
     * {@link android.media.AudioManager#STREAM_ACCESSIBILITY} volume.
     */
    public static final int FLAG_ENABLE_ACCESSIBILITY_VOLUME = 128;

    /**
     * If this flag is set the system will regard views that are not important
     * for accessibility in addition to the ones that are important for accessibility.
     * That is, views that are marked as not important for accessibility via
     * {@link View#IMPORTANT_FOR_ACCESSIBILITY_NO} or
     * {@link View#IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS} and views that are
     * marked as potentially important for accessibility via
     * {@link View#IMPORTANT_FOR_ACCESSIBILITY_AUTO} for which the system has determined
     * that are not important for accessibility, are reported while querying the window
     * content and also the accessibility service will receive accessibility events from
     * them.
     * <p>
     * <strong>Note:</strong> For accessibility services targeting Android 4.1 (API level 16) or
     * higher, this flag has to be explicitly set for the system to regard views that are not
     * important for accessibility. For accessibility services targeting Android 4.0.4 (API level
     * 15) or lower, this flag is ignored and all views are regarded for accessibility purposes.
     * </p>
     * <p>
     * Usually views not important for accessibility are layout managers that do not
     * react to user actions, do not draw any content, and do not have any special
     * semantics in the context of the screen content. For example, a three by three
     * grid can be implemented as three horizontal linear layouts and one vertical,
     * or three vertical linear layouts and one horizontal, or one grid layout, etc.
     * In this context, the actual layout managers used to achieve the grid configuration
     * are not important; rather it is important that there are nine evenly distributed
     * elements.
     * </p>
     */
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;

    /**
     * This flag makes the AccessibilityService an input method editor with a subset of input
     * method editor capabilities: get the {@link android.view.inputmethod.InputConnection} and get
     * text selection change notifications.
     *
     * @see AccessibilityService#getInputMethod()
     */
    public static final int FLAG_INPUT_METHOD_EDITOR = 32768;

    /**
     * This flag requests that the {@link AccessibilityNodeInfo}s obtained
     * by an {@link AccessibilityService} contain the id of the source view.
     * The source view id will be a fully qualified resource name of the
     * form "package:id/name", for example "foo.bar:id/my_list", and it is
     * useful for UI test automation. This flag is not set by default.
     */
    public static final int FLAG_REPORT_VIEW_IDS = 16;

    /**
     * This flag requests that when when {@link #FLAG_REQUEST_MULTI_FINGER_GESTURES} is enabled,
     * two-finger passthrough gestures are re-enabled. Two-finger swipe gestures are not detected,
     * but instead passed through as one-finger gestures. In addition, three-finger swipes from the
     * bottom of the screen are not detected, and instead are passed through unchanged. If {@link
     * #FLAG_REQUEST_MULTI_FINGER_GESTURES} is disabled this flag has no effect.
     *
     * @see #FLAG_REQUEST_TOUCH_EXPLORATION_MODE
     */
    public static final int FLAG_REQUEST_2_FINGER_PASSTHROUGH = 8192;

    /**
     * This flag indicates to the system that the accessibility service requests that an
     * accessibility button be shown within the system's navigation area, if available.
     * <p>
     *   <strong>Note:</strong> For accessibility services targeting APIs greater than
     *   {@link Build.VERSION_CODES#Q API 29}, this flag must be specified in the
     *   accessibility service metadata file. Otherwise, it will be ignored.
     * </p>
     */
    public static final int FLAG_REQUEST_ACCESSIBILITY_BUTTON = 256;

    /**
     * @deprecated No longer used
     */
    @Deprecated
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;

    /**
     * This flag requests from the system to filter key events. If this flag
     * is set the accessibility service will receive the key events before
     * applications allowing it implement global shortcuts.
     * <p>
     * Services that want to set this flag have to declare this capability
     * in their meta-data by setting the attribute {@link android.R.attr
     * #canRequestFilterKeyEvents canRequestFilterKeyEvents} to true,
     * otherwise this flag will be ignored. For how to declare the meta-data
     * of a service refer to {@value AccessibilityService#SERVICE_META_DATA}.
     * </p>
     * @see android.R.styleable#AccessibilityService_canRequestFilterKeyEvents
     */
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;

    /**
     * This flag requests that all fingerprint gestures be sent to the accessibility service.
     * <p>
     * Services that want to set this flag have to declare the capability
     * to retrieve window content in their meta-data by setting the attribute
     * {@link android.R.attr#canRequestFingerprintGestures} to
     * true, otherwise this flag will be ignored. For how to declare the meta-data
     * of a service refer to {@value AccessibilityService#SERVICE_META_DATA}.
     * </p>
     *
     * @see android.R.styleable#AccessibilityService_canRequestFingerprintGestures
     * @see AccessibilityService#getFingerprintGestureController()
     */
    public static final int FLAG_REQUEST_FINGERPRINT_GESTURES = 512;

    /**
     * This flag requests that when when {@link #FLAG_REQUEST_TOUCH_EXPLORATION_MODE} is enabled,
     * multi-finger gestures are also enabled. As a consequence, two-finger bypass gestures will be
     * disabled. If {@link #FLAG_REQUEST_TOUCH_EXPLORATION_MODE} is disabled this flag has no
     * effect.
     *
     * @see #FLAG_REQUEST_TOUCH_EXPLORATION_MODE
     */
    public static final int FLAG_REQUEST_MULTI_FINGER_GESTURES = 4096;

    /**
     * This flag requests that accessibility shortcut warning dialog has spoken feedback when
     * dialog is shown.
     */
    public static final int FLAG_REQUEST_SHORTCUT_WARNING_DIALOG_SPOKEN_FEEDBACK = 1024;

    /**
     * This flag requests that the system gets into touch exploration mode.
     * In this mode a single finger moving on the screen behaves as a mouse
     * pointer hovering over the user interface. The system will also detect
     * certain gestures performed on the touch screen and notify this service.
     * The system will enable touch exploration mode if there is at least one
     * accessibility service that has this flag set. Hence, clearing this
     * flag does not guarantee that the device will not be in touch exploration
     * mode since there may be another enabled service that requested it.
     * <p>
     * For accessibility services targeting Android 4.3 (API level 18) or higher
     * that want to set this flag have to declare this capability in their
     * meta-data by setting the attribute
     * {@link android.R.attr#canRequestTouchExplorationMode
     * canRequestTouchExplorationMode} to true. Otherwise, this flag will
     * be ignored. For how to declare the meta-data of a service refer to
     * {@value AccessibilityService#SERVICE_META_DATA}.
     * </p>
     * <p>
     * Services targeting Android 4.2.2 (API level 17) or lower will work
     * normally. In other words, the first time they are run, if this flag is
     * specified, a dialog is shown to the user to confirm enabling explore by
     * touch.
     * </p>
     * @see android.R.styleable#AccessibilityService_canRequestTouchExplorationMode
     */
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;

    /**
     * This flag indicates to the system that the accessibility service wants
     * to access content of all interactive windows. An interactive window is a
     * window that has input focus or can be touched by a sighted user when explore
     * by touch is not enabled. If this flag is not set your service will not receive
     * {@link android.view.accessibility.AccessibilityEvent#TYPE_WINDOWS_CHANGED}
     * events, calling AccessibilityService{@link AccessibilityService#getWindows()
     * AccessibilityService.getWindows()} will return an empty list, and {@link
     * AccessibilityNodeInfo#getWindow() AccessibilityNodeInfo.getWindow()} will
     * return null.
     * <p>
     * Services that want to set this flag have to declare the capability
     * to retrieve window content in their meta-data by setting the attribute
     * {@link android.R.attr#canRetrieveWindowContent canRetrieveWindowContent} to
     * true, otherwise this flag will be ignored. For how to declare the meta-data
     * of a service refer to {@value AccessibilityService#SERVICE_META_DATA}.
     * </p>
     * @see android.R.styleable#AccessibilityService_canRetrieveWindowContent
     */
    public static final int FLAG_RETRIEVE_INTERACTIVE_WINDOWS = 64;

    /**
     * This flag requests that when when {@link #FLAG_REQUEST_TOUCH_EXPLORATION_MODE} is enabled, a
     * service will receive the motion events for each successfully-detected gesture. The service
     * will also receive an AccessibilityGestureEvent of type GESTURE_INVALID for each cancelled
     * gesture along with its motion events. A service will receive a gesture of type
     * GESTURE_PASSTHROUGH and accompanying motion events for every passthrough gesture that does
     * not start gesture detection. This information can be used to collect instances of improper
     * gesture detection behavior and relay that information to framework developers. If {@link
     * #FLAG_REQUEST_TOUCH_EXPLORATION_MODE} is disabled this flag has no effect.
     *
     * @see #FLAG_REQUEST_TOUCH_EXPLORATION_MODE
     */
    public static final int FLAG_SEND_MOTION_EVENTS = 16384;

    /**
     * This flag requests that when {@link #FLAG_REQUEST_TOUCH_EXPLORATION_MODE} is enabled,
     * double tap and double tap and hold gestures are dispatched to the service rather than being
     * handled by the framework. If {@link #FLAG_REQUEST_TOUCH_EXPLORATION_MODE} is disabled this
     * flag has no effect.
     *
     * @see #FLAG_REQUEST_TOUCH_EXPLORATION_MODE
     */
    public static final int FLAG_SERVICE_HANDLES_DOUBLE_TAP = 2048;

    private AccessibilityServiceInfo() {
    }
}
