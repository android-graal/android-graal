package android.window;

/**
 * Dispatcher to register {@link OnBackInvokedCallback} instances for handling
 * back invocations.
 *
 * It also provides interfaces to update the attributes of {@link OnBackInvokedCallback}.
 * Attribute updates are proactively pushed to the window manager if they change the dispatch
 * target (a.k.a. the callback to be invoked next), or its behavior.
 */
public interface OnBackInvokedDispatcher {
    /**
     * Default priority level of {@link OnBackInvokedCallback}s.
     */
    int PRIORITY_DEFAULT = 0;

    /**
     * Priority level of {@link OnBackInvokedCallback}s for overlays such as menus and
     * navigation drawers that should receive back dispatch before non-overlays.
     */
    int PRIORITY_OVERLAY = 1000000;

    /**
     * Priority level of {@link OnBackInvokedCallback}s designed to observe system-level back
     * handling.
     *
     * <p>Callbacks registered with this priority do not consume back events. They receive back
     * events whenever the system handles a back navigation and have no impact on the normal back
     * navigation flow. Useful for logging or analytics.
     *
     * <p>Only one callback with {@link #PRIORITY_SYSTEM_NAVIGATION_OBSERVER} can be registered at a
     * time.
     */
    int PRIORITY_SYSTEM_NAVIGATION_OBSERVER = -2;
}
