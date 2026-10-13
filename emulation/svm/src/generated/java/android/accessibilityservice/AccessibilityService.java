package android.accessibilityservice;

/**
 * Accessibility services should only be used to assist users with disabilities in using
 * Android devices and apps. They run in the background and receive callbacks by the system
 * when {@link AccessibilityEvent}s are fired. Such events denote some state transition
 * in the user interface, for example, the focus has changed, a button has been clicked,
 * etc. Such a service can optionally request the capability for querying the content
 * of the active window. Development of an accessibility service requires extending this
 * class and implementing its abstract methods.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about creating AccessibilityServices, read the
 * <a href="{@docRoot}guide/topics/ui/accessibility/index.html">Accessibility</a>
 * developer guide.</p>
 * </div>
 *
 * <h3>Lifecycle</h3>
 * <p>
 * The lifecycle of an accessibility service is managed exclusively by the system and
 * follows the established service life cycle. Starting an accessibility service is triggered
 * exclusively by the user explicitly turning the service on in device settings. After the system
 * binds to a service, it calls {@link AccessibilityService#onServiceConnected()}. This method can
 * be overridden by clients that want to perform post binding setup.
 * </p>
 * <p>
 * An accessibility service stops either when the user turns it off in device settings or when
 * it calls {@link AccessibilityService#disableSelf()}.
 * </p>
 * <h3>Declaration</h3>
 * <p>
 * An accessibility is declared as any other service in an AndroidManifest.xml, but it
 * must do two things:
 * <ul>
 *     <li>
 *         Specify that it handles the "android.accessibilityservice.AccessibilityService"
 *         {@link android.content.Intent}.
 *     </li>
 *     <li>
 *         Request the {@link android.Manifest.permission#BIND_ACCESSIBILITY_SERVICE} permission to
 *         ensure that only the system can bind to it.
 *     </li>
 * </ul>
 * If either of these items is missing, the system will ignore the accessibility service.
 * Following is an example declaration:
 * </p>
 * <pre> &lt;service android:name=".MyAccessibilityService"
 *         android:permission="android.permission.BIND_ACCESSIBILITY_SERVICE"&gt;
 *     &lt;intent-filter&gt;
 *         &lt;action android:name="android.accessibilityservice.AccessibilityService" /&gt;
 *     &lt;/intent-filter&gt;
 *     . . .
 * &lt;/service&gt;</pre>
 * <h3>Configuration</h3>
 * <p>
 * An accessibility service can be configured to receive specific types of accessibility events,
 * listen only to specific packages, get events from each type only once in a given time frame,
 * retrieve window content, specify a settings activity, etc.
 * </p>
 * <p>
 * There are two approaches for configuring an accessibility service:
 * </p>
 * <ul>
 * <li>
 * Providing a {@link #SERVICE_META_DATA meta-data} entry in the manifest when declaring
 * the service. A service declaration with a meta-data tag is presented below:
 * <pre> &lt;service android:name=".MyAccessibilityService"&gt;
 *     &lt;intent-filter&gt;
 *         &lt;action android:name="android.accessibilityservice.AccessibilityService" /&gt;
 *     &lt;/intent-filter&gt;
 *     &lt;meta-data android:name="android.accessibilityservice" android:resource="@xml/accessibilityservice" /&gt;
 * &lt;/service&gt;</pre>
 * <p class="note">
 * <strong>Note:</strong> This approach enables setting all properties.
 * </p>
 * <p>
 * For more details refer to {@link #SERVICE_META_DATA} and
 * <code>&lt;{@link android.R.styleable#AccessibilityService accessibility-service}&gt;</code>.
 * </p>
 * </li>
 * <li>
 * Calling {@link AccessibilityService#setServiceInfo(AccessibilityServiceInfo)}. Note
 * that this method can be called any time to dynamically change the service configuration.
 * <p class="note">
 * <strong>Note:</strong> This approach enables setting only dynamically configurable properties:
 * {@link AccessibilityServiceInfo#eventTypes},
 * {@link AccessibilityServiceInfo#feedbackType},
 * {@link AccessibilityServiceInfo#flags},
 * {@link AccessibilityServiceInfo#notificationTimeout},
 * {@link AccessibilityServiceInfo#packageNames}
 * </p>
 * <p>
 * For more details refer to {@link AccessibilityServiceInfo}.
 * </p>
 * </li>
 * </ul>
 * <h3>Retrieving window content</h3>
 * <p>
 * A service can specify in its declaration that it can retrieve window
 * content which is represented as a tree of {@link AccessibilityWindowInfo} and
 * {@link AccessibilityNodeInfo} objects. Note that
 * declaring this capability requires that the service declares its configuration via
 * an XML resource referenced by {@link #SERVICE_META_DATA}.
 * </p>
 * <p>
 * Window content may be retrieved with
 * {@link AccessibilityEvent#getSource() AccessibilityEvent.getSource()},
 * {@link AccessibilityService#findFocus(int)},
 * {@link AccessibilityService#getWindows()}, or
 * {@link AccessibilityService#getRootInActiveWindow()}.
 * </p>
 * <p class="note">
 * <strong>Note</strong> An accessibility service may have requested to be notified for
 * a subset of the event types, and thus be unaware when the node hierarchy has changed. It is also
 * possible for a node to contain outdated information because the window content may change at any
 * time.
 * </p>
 * <h3>Drawing Accessibility Overlays</h3>
 * <p>Accessibility services can draw overlays on top of existing screen contents.
 * Accessibility overlays can be used to visually highlight items on the screen
 * e.g. indicate the current item with accessibility focus.
 * Overlays can also offer the user a way to interact with the service directly and quickly
 * customize the service's behavior.</p>
 * <p>Accessibility overlays can be attached to a particular window or to the display itself.
 * Attaching an overlay to a window allows the overly to move, grow and shrink as the window does.
 * The overlay will maintain the same relative position within the window bounds as the window
 * moves. The overlay will also maintain the same relative position within the window bounds if
 * the window is resized.
 * To attach an overlay to a window, use {@link #attachAccessibilityOverlayToWindow}.
 * Attaching an overlay to the display means that the overlay is independent of the active
 * windows on that display.
 * To attach an overlay to a display, use {@link #attachAccessibilityOverlayToDisplay}. </p>
 * <p> When positioning an overlay that is attached to a window, the service must use window
 * coordinates. In order to position an overlay on top of an existing UI element it is necessary
 * to know the bounds of that element in window coordinates. To find the bounds in window
 * coordinates of an element, find the corresponding {@link AccessibilityNodeInfo} as discussed
 * above and call {@link AccessibilityNodeInfo#getBoundsInWindow}. </p>
 * <h3>Notification strategy</h3>
 * <p>
 * All accessibility services are notified of all events they have requested, regardless of their
 * feedback type.
 * </p>
 * <p class="note">
 * <strong>Note:</strong> The event notification timeout is useful to avoid propagating
 * events to the client too frequently since this is accomplished via an expensive
 * interprocess call. One can think of the timeout as a criteria to determine when
 * event generation has settled down.</p>
 * <h3>Event types</h3>
 * <ul>
 * <li>{@link AccessibilityEvent#TYPE_VIEW_CLICKED}</li>
 * <li>{@link AccessibilityEvent#TYPE_VIEW_LONG_CLICKED}</li>
 * <li>{@link AccessibilityEvent#TYPE_VIEW_FOCUSED}</li>
 * <li>{@link AccessibilityEvent#TYPE_VIEW_SELECTED}</li>
 * <li>{@link AccessibilityEvent#TYPE_VIEW_TEXT_CHANGED}</li>
 * <li>{@link AccessibilityEvent#TYPE_WINDOW_STATE_CHANGED}</li>
 * <li>{@link AccessibilityEvent#TYPE_NOTIFICATION_STATE_CHANGED}</li>
 * <li>{@link AccessibilityEvent#TYPE_TOUCH_EXPLORATION_GESTURE_START}</li>
 * <li>{@link AccessibilityEvent#TYPE_TOUCH_EXPLORATION_GESTURE_END}</li>
 * <li>{@link AccessibilityEvent#TYPE_VIEW_HOVER_ENTER}</li>
 * <li>{@link AccessibilityEvent#TYPE_VIEW_HOVER_EXIT}</li>
 * <li>{@link AccessibilityEvent#TYPE_VIEW_SCROLLED}</li>
 * <li>{@link AccessibilityEvent#TYPE_VIEW_TEXT_SELECTION_CHANGED}</li>
 * <li>{@link AccessibilityEvent#TYPE_WINDOW_CONTENT_CHANGED}</li>
 * <li>{@link AccessibilityEvent#TYPE_ANNOUNCEMENT}</li>
 * <li>{@link AccessibilityEvent#TYPE_GESTURE_DETECTION_START}</li>
 * <li>{@link AccessibilityEvent#TYPE_GESTURE_DETECTION_END}</li>
 * <li>{@link AccessibilityEvent#TYPE_TOUCH_INTERACTION_START}</li>
 * <li>{@link AccessibilityEvent#TYPE_TOUCH_INTERACTION_END}</li>
 * <li>{@link AccessibilityEvent#TYPE_VIEW_ACCESSIBILITY_FOCUSED}</li>
 * <li>{@link AccessibilityEvent#TYPE_WINDOWS_CHANGED}</li>
 * <li>{@link AccessibilityEvent#TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED}</li>
 * </ul>
 * <h3>Feedback types</h3>
 * <ul>
 * <li>{@link AccessibilityServiceInfo#FEEDBACK_AUDIBLE}</li>
 * <li>{@link AccessibilityServiceInfo#FEEDBACK_HAPTIC}</li>
 * <li>{@link AccessibilityServiceInfo#FEEDBACK_SPOKEN}</li>
 * <li>{@link AccessibilityServiceInfo#FEEDBACK_VISUAL}</li>
 * <li>{@link AccessibilityServiceInfo#FEEDBACK_GENERIC}</li>
 * <li>{@link AccessibilityServiceInfo#FEEDBACK_BRAILLE}</li>
 * </ul>
 * @see AccessibilityEvent
 * @see AccessibilityServiceInfo
 * @see android.view.accessibility.AccessibilityManager
 */
public final class AccessibilityService {
    /**
     * The status of taking screenshot is failure and the reason is internal error.
     */
    public static final int ERROR_TAKE_SCREENSHOT_INTERNAL_ERROR = 1;

    /**
     * The status of taking screenshot is failure and the reason is that too little time has
     * elapsed since the last screenshot.
     */
    public static final int ERROR_TAKE_SCREENSHOT_INTERVAL_TIME_SHORT = 3;

    /**
     * The status of taking screenshot is failure and the reason is invalid display Id.
     */
    public static final int ERROR_TAKE_SCREENSHOT_INVALID_DISPLAY = 4;

    /**
     * The status of taking screenshot is failure and the reason is invalid accessibility window Id.
     */
    public static final int ERROR_TAKE_SCREENSHOT_INVALID_WINDOW = 5;

    /**
     * The status of taking screenshot is failure and the reason is no accessibility access.
     */
    public static final int ERROR_TAKE_SCREENSHOT_NO_ACCESSIBILITY_ACCESS = 2;

    /**
     * The status of taking screenshot is failure and the reason is the window contains secure
     * content.
     * @see WindowManager.LayoutParams#FLAG_SECURE
     */
    public static final int ERROR_TAKE_SCREENSHOT_SECURE_WINDOW = 6;

    /**
     * The user has performed a two-finger double tap gesture on the touch screen.
     */
    public static final int GESTURE_2_FINGER_DOUBLE_TAP = 20;

    /**
     * The user has performed a two-finger double tap and hold gesture on the touch screen.
     */
    public static final int GESTURE_2_FINGER_DOUBLE_TAP_AND_HOLD = 40;

    /**
     * The user has performed a two-finger single tap gesture on the touch screen.
     */
    public static final int GESTURE_2_FINGER_SINGLE_TAP = 19;

    /**
     * The user has performed a two-finger swipe down gesture on the touch screen.
     */
    public static final int GESTURE_2_FINGER_SWIPE_DOWN = 26;

    /**
     * The user has performed a two-finger swipe left gesture on the touch screen.
     */
    public static final int GESTURE_2_FINGER_SWIPE_LEFT = 27;

    /**
     * The user has performed a two-finger swipe right gesture on the touch screen.
     */
    public static final int GESTURE_2_FINGER_SWIPE_RIGHT = 28;

    /**
     * The user has performed a two-finger swipe up gesture on the touch screen.
     */
    public static final int GESTURE_2_FINGER_SWIPE_UP = 25;

    /**
     * The user has performed a two-finger triple tap gesture on the touch screen.
     */
    public static final int GESTURE_2_FINGER_TRIPLE_TAP = 21;

    /**
     * The user has performed a two-finger  triple-tap and hold gesture on the touch screen.
     */
    public static final int GESTURE_2_FINGER_TRIPLE_TAP_AND_HOLD = 43;

    /**
     * The user has performed a three-finger double tap gesture on the touch screen.
     */
    public static final int GESTURE_3_FINGER_DOUBLE_TAP = 23;

    /**
     * The user has performed a three-finger double tap and hold gesture on the touch screen.
     */
    public static final int GESTURE_3_FINGER_DOUBLE_TAP_AND_HOLD = 41;

    /**
     * The user has performed a three-finger single tap gesture on the touch screen.
     */
    public static final int GESTURE_3_FINGER_SINGLE_TAP = 22;

    /**
     * The user has performed a three-finger  single-tap and hold gesture on the touch screen.
     */
    public static final int GESTURE_3_FINGER_SINGLE_TAP_AND_HOLD = 44;

    /**
     * The user has performed a three-finger swipe down gesture on the touch screen.
     */
    public static final int GESTURE_3_FINGER_SWIPE_DOWN = 30;

    /**
     * The user has performed a three-finger swipe left gesture on the touch screen.
     */
    public static final int GESTURE_3_FINGER_SWIPE_LEFT = 31;

    /**
     * The user has performed a three-finger swipe right gesture on the touch screen.
     */
    public static final int GESTURE_3_FINGER_SWIPE_RIGHT = 32;

    /**
     * The user has performed a three-finger swipe up gesture on the touch screen.
     */
    public static final int GESTURE_3_FINGER_SWIPE_UP = 29;

    /**
     * The user has performed a three-finger triple tap gesture on the touch screen.
     */
    public static final int GESTURE_3_FINGER_TRIPLE_TAP = 24;

    /**
     * The user has performed a three-finger  triple-tap and hold gesture on the touch screen.
     */
    public static final int GESTURE_3_FINGER_TRIPLE_TAP_AND_HOLD = 45;

    /**
     * The user has performed a four-finger double tap gesture on the touch screen.
     */
    public static final int GESTURE_4_FINGER_DOUBLE_TAP = 38;

    /**
     * The user has performed a two-finger double tap and hold gesture on the touch screen.
     */
    public static final int GESTURE_4_FINGER_DOUBLE_TAP_AND_HOLD = 42;

    /**
     * The user has performed a four-finger single tap gesture on the touch screen.
     */
    public static final int GESTURE_4_FINGER_SINGLE_TAP = 37;

    /**
     * The user has performed a four-finger swipe down gesture on the touch screen.
     */
    public static final int GESTURE_4_FINGER_SWIPE_DOWN = 34;

    /**
     * The user has performed a four-finger swipe left gesture on the touch screen.
     */
    public static final int GESTURE_4_FINGER_SWIPE_LEFT = 35;

    /**
     * The user has performed a four-finger swipe right gesture on the touch screen.
     */
    public static final int GESTURE_4_FINGER_SWIPE_RIGHT = 36;

    /**
     * The user has performed a four-finger swipe up gesture on the touch screen.
     */
    public static final int GESTURE_4_FINGER_SWIPE_UP = 33;

    /**
     * The user has performed a four-finger triple tap gesture on the touch screen.
     */
    public static final int GESTURE_4_FINGER_TRIPLE_TAP = 39;

    /**
     * The user has performed a double tap gesture on the touch screen.
     */
    public static final int GESTURE_DOUBLE_TAP = 17;

    /**
     * The user has performed a double tap and hold gesture on the touch screen.
     */
    public static final int GESTURE_DOUBLE_TAP_AND_HOLD = 18;

    /**
     * The user has performed a swipe down gesture on the touch screen.
     */
    public static final int GESTURE_SWIPE_DOWN = 2;

    /**
     * The user has performed a down and left gesture on the touch screen.
     */
    public static final int GESTURE_SWIPE_DOWN_AND_LEFT = 15;

    /**
     * The user has performed a down and right gesture on the touch screen.
     */
    public static final int GESTURE_SWIPE_DOWN_AND_RIGHT = 16;

    /**
     * The user has performed a swipe down and up gesture on the touch screen.
     */
    public static final int GESTURE_SWIPE_DOWN_AND_UP = 8;

    /**
     * The user has performed a swipe left gesture on the touch screen.
     */
    public static final int GESTURE_SWIPE_LEFT = 3;

    /**
     * The user has performed a left and down gesture on the touch screen.
     */
    public static final int GESTURE_SWIPE_LEFT_AND_DOWN = 10;

    /**
     * The user has performed a swipe left and right gesture on the touch screen.
     */
    public static final int GESTURE_SWIPE_LEFT_AND_RIGHT = 5;

    /**
     * The user has performed a left and up gesture on the touch screen.
     */
    public static final int GESTURE_SWIPE_LEFT_AND_UP = 9;

    /**
     * The user has performed a swipe right gesture on the touch screen.
     */
    public static final int GESTURE_SWIPE_RIGHT = 4;

    /**
     * The user has performed a right and down gesture on the touch screen.
     */
    public static final int GESTURE_SWIPE_RIGHT_AND_DOWN = 12;

    /**
     * The user has performed a swipe right and left gesture on the touch screen.
     */
    public static final int GESTURE_SWIPE_RIGHT_AND_LEFT = 6;

    /**
     * The user has performed a right and up gesture on the touch screen.
     */
    public static final int GESTURE_SWIPE_RIGHT_AND_UP = 11;

    /**
     * The user has performed a swipe up gesture on the touch screen.
     */
    public static final int GESTURE_SWIPE_UP = 1;

    /**
     * The user has performed a swipe up and down gesture on the touch screen.
     */
    public static final int GESTURE_SWIPE_UP_AND_DOWN = 7;

    /**
     * The user has performed an up and left gesture on the touch screen.
     */
    public static final int GESTURE_SWIPE_UP_AND_LEFT = 13;

    /**
     * The user has performed an up and right gesture on the touch screen.
     */
    public static final int GESTURE_SWIPE_UP_AND_RIGHT = 14;

    /**
     * The user has performed an unrecognized gesture on the touch screen. This gesture is only
     * dispatched when {@link AccessibilityServiceInfo#FLAG_SEND_MOTION_EVENTS} is set.
     */
    public static final int GESTURE_UNKNOWN = 0;

    /**
     * Action to show Launcher's all apps.
     */
    public static final int GLOBAL_ACTION_ACCESSIBILITY_ALL_APPS = 14;

    /**
     * Action to trigger the Accessibility Button
     */
    public static final int GLOBAL_ACTION_ACCESSIBILITY_BUTTON = 11;

    /**
     * Action to bring up the Accessibility Button's chooser menu
     */
    public static final int GLOBAL_ACTION_ACCESSIBILITY_BUTTON_CHOOSER = 12;

    /**
     * Action to trigger the Accessibility Shortcut. This shortcut has a hardware trigger and can
     * be activated by holding down the two volume keys.
     */
    public static final int GLOBAL_ACTION_ACCESSIBILITY_SHORTCUT = 13;

    /**
     * Action to go back.
     */
    public static final int GLOBAL_ACTION_BACK = 1;

    /**
     * Action to dismiss the notification shade
     */
    public static final int GLOBAL_ACTION_DISMISS_NOTIFICATION_SHADE = 15;

    /**
     * Action to trigger dpad center keyevent.
     */
    public static final int GLOBAL_ACTION_DPAD_CENTER = 20;

    /**
     * Action to trigger dpad down keyevent.
     */
    public static final int GLOBAL_ACTION_DPAD_DOWN = 17;

    /**
     * Action to trigger dpad left keyevent.
     */
    public static final int GLOBAL_ACTION_DPAD_LEFT = 18;

    /**
     * Action to trigger dpad right keyevent.
     */
    public static final int GLOBAL_ACTION_DPAD_RIGHT = 19;

    /**
     * Action to trigger dpad up keyevent.
     */
    public static final int GLOBAL_ACTION_DPAD_UP = 16;

    /**
     * Action to go home.
     */
    public static final int GLOBAL_ACTION_HOME = 2;

    /**
     * Action to send the KEYCODE_HEADSETHOOK KeyEvent, which is used to answer and hang up calls
     * and play and stop media. Calling takes priority. If there is an incoming call,
     * this action can be used to answer that call, and if there is an ongoing call, to hang up on
     * that call.
     */
    public static final int GLOBAL_ACTION_KEYCODE_HEADSETHOOK = 10;

    /**
     * Action to lock the screen
     */
    public static final int GLOBAL_ACTION_LOCK_SCREEN = 8;

    /**
     * Action to trigger media play/pause key event.
     */
    public static final int GLOBAL_ACTION_MEDIA_PLAY_PAUSE = 22;

    /**
     * Action to trigger menu key event.
     */
    public static final int GLOBAL_ACTION_MENU = 21;

    /**
     * Action to open the notifications.
     */
    public static final int GLOBAL_ACTION_NOTIFICATIONS = 4;

    /**
     * Action to open the power long-press dialog.
     */
    public static final int GLOBAL_ACTION_POWER_DIALOG = 6;

    /**
     * Action to open the quick settings.
     */
    public static final int GLOBAL_ACTION_QUICK_SETTINGS = 5;

    /**
     * Action to toggle showing the overview of recent apps. Will fail on platforms that don't
     * show recent apps.
     */
    public static final int GLOBAL_ACTION_RECENTS = 3;

    /**
     * Action to take a screenshot
     */
    public static final int GLOBAL_ACTION_TAKE_SCREENSHOT = 9;

    /**
     * Action to toggle docking the current app's window.
     * <p>
     * <strong>Note:</strong>  It is effective only if it appears in {@link #getSystemActions()}.
     */
    public static final int GLOBAL_ACTION_TOGGLE_SPLIT_SCREEN = 7;

    /**
     * The {@link Intent} that must be declared as handled by the service.
     */
    public static final String SERVICE_INTERFACE = "android.accessibilityservice.AccessibilityService";

    /**
     * Name under which an AccessibilityService component publishes information
     * about itself. This meta-data must reference an XML resource containing an
     * <code>&lt;{@link android.R.styleable#AccessibilityService accessibility-service}&gt;</code>
     * tag. This is a sample XML file configuring an accessibility service:
     * <pre> &lt;accessibility-service
     *     android:accessibilityEventTypes="typeViewClicked|typeViewFocused"
     *     android:packageNames="foo.bar, foo.baz"
     *     android:accessibilityFeedbackType="feedbackSpoken"
     *     android:notificationTimeout="100"
     *     android:accessibilityFlags="flagDefault"
     *     android:settingsActivity="foo.bar.TestBackActivity"
     *     android:canRetrieveWindowContent="true"
     *     android:canRequestTouchExplorationMode="true"
     *     . . .
     * /&gt;</pre>
     */
    public static final String SERVICE_META_DATA = "android.accessibilityservice";

    /**
     * Allow the system to control when the soft keyboard is shown.
     * @see SoftKeyboardController
     */
    public static final int SHOW_MODE_AUTO = 0;

    /**
     * Never show the soft keyboard.
     * @see SoftKeyboardController
     */
    public static final int SHOW_MODE_HIDDEN = 1;

    /**
     * Allow the soft keyboard to be shown, even if a hard keyboard is connected
     * @see SoftKeyboardController
     */
    public static final int SHOW_MODE_IGNORE_HARD_KEYBOARD = 2;

    private AccessibilityService() {
    }

    /**
     * Used to control, query, and listen for changes to the soft keyboard show mode.
     * <p>
     * Accessibility services may request to override the decisions normally made about whether or
     * not the soft keyboard is shown.
     * <p>
     * If multiple services make conflicting requests, the last request is honored. A service may
     * register a listener to find out if the mode has changed under it.
     * <p>
     * If the user takes action to override the behavior behavior requested by an accessibility
     * service, the user's request takes precendence, the show mode will be reset to
     * {@link AccessibilityService#SHOW_MODE_AUTO}, and services will no longer be able to control
     * that aspect of the soft keyboard's behavior.
     * <p>
     * Note: Because soft keyboards are independent apps, the framework does not have total control
     * over their behavior. They may choose to show themselves, or not, without regard to requests
     * made here. So the framework will make a best effort to deliver the behavior requested, but
     * cannot guarantee success.
     *
     * @see AccessibilityService#SHOW_MODE_AUTO
     * @see AccessibilityService#SHOW_MODE_HIDDEN
     * @see AccessibilityService#SHOW_MODE_IGNORE_HARD_KEYBOARD
     */
    public static final class SoftKeyboardController {
        /**
         * Return value for {@link #setInputMethodEnabled(String, boolean)}. The action failed
         * because the InputMethod is not permitted by device policy manager.
         */
        public static final int ENABLE_IME_FAIL_BY_ADMIN = 1;

        /**
         * Return value for {@link #setInputMethodEnabled(String, boolean)}. The action failed
         * and the reason is unknown.
         */
        public static final int ENABLE_IME_FAIL_UNKNOWN = 2;

        /**
         * Return value for {@link #setInputMethodEnabled(String, boolean)}. The action succeeded.
         */
        public static final int ENABLE_IME_SUCCESS = 0;

        private SoftKeyboardController() {
        }
    }
}
