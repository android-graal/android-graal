package android.view.accessibility;

/**
 * System level service that serves as an event dispatch for {@link AccessibilityEvent}s,
 * and provides facilities for querying the accessibility state of the system.
 * Accessibility events are generated when something notable happens in the user interface,
 * for example an {@link android.app.Activity} starts, the focus or selection of a
 * {@link android.view.View} changes etc. Parties interested in handling accessibility
 * events implement and register an accessibility service which extends
 * {@link android.accessibilityservice.AccessibilityService}.
 *
 * @see AccessibilityEvent
 * @see AccessibilityNodeInfo
 * @see android.accessibilityservice.AccessibilityService
 * @see Context#getSystemService
 * @see Context#ACCESSIBILITY_SERVICE
 */
public final class AccessibilityManager {
    /**
     * Use this flag to indicate the content of a UI that times out contains interactive controls.
     *
     * @see #getRecommendedTimeoutMillis(int, int)
     */
    public static final int FLAG_CONTENT_CONTROLS = 4;

    /**
     * Use this flag to indicate the content of a UI that times out contains icons.
     *
     * @see #getRecommendedTimeoutMillis(int, int)
     */
    public static final int FLAG_CONTENT_ICONS = 1;

    /**
     * Use this flag to indicate the content of a UI that times out contains text.
     *
     * @see #getRecommendedTimeoutMillis(int, int)
     */
    public static final int FLAG_CONTENT_TEXT = 2;

    private AccessibilityManager() {
    }
}
