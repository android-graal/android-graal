package android.view.accessibility;

import android.os.Parcelable;

/**
 * This class represents a state snapshot of a window for accessibility
 * purposes. The screen content contains one or more windows where some
 * windows can be descendants of other windows, which is the windows are
 * hierarchically ordered. Note that there is no root window. Hence, the
 * screen content can be seen as a collection of window trees.
 */
public final class AccessibilityWindowInfo implements Parcelable {
    /**
     * Window type: Windows that are overlaid <em>only</em> by an {@link
     * android.accessibilityservice.AccessibilityService} for interception of
     * user interactions without changing the windows an accessibility service
     * can introspect. In particular, an accessibility service can introspect
     * only windows that a sighted user can interact with which they can touch
     * these windows or can type into these windows. For example, if there
     * is a full screen accessibility overlay that is touchable, the windows
     * below it will be introspectable by an accessibility service regardless
     * they are covered by a touchable window.
     */
    public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;

    /**
     * Window type: This is an application window. Such a window shows UI for
     * interacting with an application.
     */
    public static final int TYPE_APPLICATION = 1;

    /**
     * Window type: This is an input method window. Such a window shows UI for
     * inputting text such as keyboard, suggestions, etc.
     */
    public static final int TYPE_INPUT_METHOD = 2;

    /**
     * Window type: A system window used to show the UI for the interaction with
     * window-based magnification, which includes the magnified content and the option menu.
     */
    public static final int TYPE_MAGNIFICATION_OVERLAY = 6;

    /**
     * Window type: A system window used to divide the screen in split-screen mode.
     * This type of window is present only in split-screen mode.
     */
    public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;

    /**
     * Window type: This is a system window. Such a window shows UI for
     * interacting with the system.
     */
    public static final int TYPE_SYSTEM = 3;

    /**
     * Window type: A system window that has the function to control an associated window.
     */
    public static final int TYPE_WINDOW_CONTROL = 7;

    private AccessibilityWindowInfo() {
    }
}
