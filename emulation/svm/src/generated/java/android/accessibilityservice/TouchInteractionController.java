package android.accessibilityservice;

/**
 * This class allows a service to handle touch exploration and the detection of specialized
 * accessibility gestures. The service receives motion events and can match those motion events
 * against the gestures it supports. The service can also request the framework enter three other
 * states of operation for the duration of this interaction. Upon entering any of these states the
 * framework will take over and the service will not receive motion events until the start of a new
 * interaction. The states are as follows:
 *
 * <ul>
 *   <li>The service can tell the framework that this interaction is touch exploration. The user is
 *       trying to explore the screen rather than manipulate it. The framework will then convert the
 *       motion events to hover events to support touch exploration.
 *   <li>The service can tell the framework that this interaction is a dragging interaction where
 *       two fingers are used to execute a one-finger gesture such as scrolling the screen. The
 *       service must specify which of the two fingers should be passed through to rest of the input
 *       pipeline.
 *   <li>Finally, the service can request that the framework delegate this interaction, meaning pass
 *       it through to the rest of the input pipeline as-is.
 * </ul>
 *
 * When {@link AccessibilityServiceInfo#FLAG_REQUEST_TOUCH_EXPLORATION_MODE } is enabled, this
 * controller will receive all motion events received by the framework for the specified display
 * when not touch-exploring or delegating. If the service classifies this interaction as touch
 * exploration or delegating the framework will stop sending motion events to the service for the
 * duration of this interaction. If the service classifies this interaction as a dragging
 * interaction the framework will send motion events to the service to allow the service to
 * determine if the interaction still qualifies as dragging or if it has become a delegating
 * interaction. If {@link AccessibilityServiceInfo#FLAG_REQUEST_TOUCH_EXPLORATION_MODE } is disabled
 * this controller will not receive any motion events because touch interactions are being passed
 * through to the input pipeline unaltered.
 * Note that {@link AccessibilityServiceInfo#FLAG_REQUEST_TOUCH_EXPLORATION_MODE }
 * requires setting {@link android.R.attr#canRequestTouchExplorationMode} as well.
 */
public final class TouchInteractionController {
    /**
     * The state where the user is not touching the screen.
     */
    public static final int STATE_CLEAR = 0;

    /**
     * The user is performing a gesture which is being passed through to the input pipeline as-is.
     * The service is not receiving motion events.
     */
    public static final int STATE_DELEGATING = 4;

    /**
     * The state where the user is dragging with two fingers. The service is not receiving motion
     * events. The selected finger is being dispatched to the rest of the input pipeline to execute
     * the drag.
     */
    public static final int STATE_DRAGGING = 3;

    /**
     * The state where the user is explicitly exploring the screen. The service is not receiving
     * motion events.
     */
    public static final int STATE_TOUCH_EXPLORING = 2;

    /**
     * The state where the user is touching the screen and the service is receiving motion events.
     */
    public static final int STATE_TOUCH_INTERACTING = 1;

    private TouchInteractionController() {
    }
}
