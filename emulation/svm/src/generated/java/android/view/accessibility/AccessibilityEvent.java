package android.view.accessibility;

import android.os.Parcelable;

/**
 * <p>
 * This class represents accessibility events that are sent by the system when
 * something notable happens in the user interface. For example, when a
 * {@link android.widget.Button} is clicked, a {@link android.view.View} is focused, etc.
 * </p>
 * <p>
 * An accessibility event is fired by an individual view which populates the event with
 * data for its state and requests from its parent to send the event to interested
 * parties. The parent can optionally modify or even block the event based on its broader
 * understanding of the user interface's context.
 * </p>
 * <p>
 * The main purpose of an accessibility event is to communicate changes in the UI to an
 * {@link android.accessibilityservice.AccessibilityService}. If needed, the service may then
 * inspect the user interface by examining the View hierarchy through the event's
 * {@link #getSource() source}, as represented by a tree of {@link AccessibilityNodeInfo}s (snapshot
 * of a View state) which can be used for exploring the window content. Note that the privilege for
 * accessing an event's source, thus the window content, has to be explicitly requested. For more
 * details refer to {@link android.accessibilityservice.AccessibilityService}. If an
 * accessibility service has not requested to retrieve the window content the event will
 * not contain reference to its source. <strong>Note: </strong> for events of type
 * {@link #TYPE_NOTIFICATION_STATE_CHANGED} the source is never available, and Views that set
 * {@link android.view.View#isAccessibilityDataSensitive()} may not populate all event properties on
 * events sent from higher up in the view hierarchy.
 * </p>
 * <p>
 * This class represents various semantically different accessibility event
 * types. Each event type has an associated set of related properties. In other
 * words, each event type is characterized via a subset of the properties exposed
 * by this class. For each event type there is a corresponding constant defined
 * in this class. Follows a specification of the event types and their associated properties:
 * </p>
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about creating and processing AccessibilityEvents, read the
 * <a href="{@docRoot}guide/topics/ui/accessibility/index.html">Accessibility</a>
 * developer guide.</p>
 * </div>
 * <p>
 * <b>VIEW TYPES</b></br>
 * </p>
 * <p>
 * <b>View clicked</b> - represents the event of clicking on a {@link android.view.View}
 * like {@link android.widget.Button}, {@link android.widget.CompoundButton}, etc.</br>
 * <em>Type:</em>{@link #TYPE_VIEW_CLICKED}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 *   <li>{@link #getSource()} - The source info (for registered clients).</li>
 *   <li>{@link #getClassName()} - The class name of the source.</li>
 *   <li>{@link #getPackageName()} - The package name of the source.</li>
 *   <li>{@link #getEventTime()}  - The event time.</li>
 * </ul>
 * </p>
 * <p>
 * <b>View long clicked</b> - represents the event of long clicking on a {@link android.view.View}
 * like {@link android.widget.Button}, {@link android.widget.CompoundButton}, etc </br>
 * <em>Type:</em>{@link #TYPE_VIEW_LONG_CLICKED}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 *   <li>{@link #getSource()} - The source info (for registered clients).</li>
 *   <li>{@link #getClassName()} - The class name of the source.</li>
 *   <li>{@link #getPackageName()} - The package name of the source.</li>
 *   <li>{@link #getEventTime()}  - The event time.</li>
 * </ul>
 * </p>
 * <p>
 * <b>View selected</b> - represents the event of selecting an item usually in
 * the context of an {@link android.widget.AdapterView}.</br>
 * <em>Type:</em> {@link #TYPE_VIEW_SELECTED}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 *   <li>{@link #getSource()} - The source info (for registered clients).</li>
 *   <li>{@link #getClassName()} - The class name of the source.</li>
 *   <li>{@link #getPackageName()} - The package name of the source.</li>
 *   <li>{@link #getEventTime()}  - The event time.</li>
 * </ul>
 * </p>
 * <p>
 * <b>View focused</b> - represents the event of focusing a
 * {@link android.view.View}.</br>
 * <em>Type:</em> {@link #TYPE_VIEW_FOCUSED}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 *   <li>{@link #getSource()} - The source info (for registered clients).</li>
 *   <li>{@link #getClassName()} - The class name of the source.</li>
 *   <li>{@link #getPackageName()} - The package name of the source.</li>
 *   <li>{@link #getEventTime()}  - The event time.</li>
 * </ul>
 * </p>
 * <p>
 * <b>View text changed</b> - represents the event of changing the text of an
 * {@link android.widget.EditText}.</br>
 * <em>Type:</em> {@link #TYPE_VIEW_TEXT_CHANGED}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 *   <li>{@link #getSource()} - The source info (for registered clients).</li>
 *   <li>{@link #getClassName()} - The class name of the source.</li>
 *   <li>{@link #getPackageName()} - The package name of the source.</li>
 *   <li>{@link #getEventTime()}  - The event time.</li>
 *   <li>{@link #getText()} - The new text of the source.</li>
 *   <li>{@link #getBeforeText()} - The text of the source before the change.</li>
 *   <li>{@link #getFromIndex()} - The text change start index.</li>
 *   <li>{@link #getAddedCount()} - The number of added characters.</li>
 *   <li>{@link #getRemovedCount()} - The number of removed characters.</li>
 * </ul>
 * </p>
 * <p>
 * <b>View text selection changed</b> - represents the event of changing the text
 * selection of an {@link android.widget.EditText}.</br>
 * <em>Type:</em> {@link #TYPE_VIEW_TEXT_SELECTION_CHANGED} </br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 *   <li>{@link #getSource()} - The source info (for registered clients).</li>
 *   <li>{@link #getClassName()} - The class name of the source.</li>
 *   <li>{@link #getPackageName()} - The package name of the source.</li>
 *   <li>{@link #getEventTime()}  - The event time.</li>
 * </ul>
 * </p>
 * <b>View text traversed at movement granularity</b> - represents the event of traversing the
 * text of a view at a given granularity. For example, moving to the next word.</br>
 * <em>Type:</em> {@link #TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY} </br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 *   <li>{@link #getSource()} - The source info (for registered clients).</li>
 *   <li>{@link #getClassName()} - The class name of the source.</li>
 *   <li>{@link #getPackageName()} - The package name of the source.</li>
 *   <li>{@link #getEventTime()}  - The event time.</li>
 *   <li>{@link #getMovementGranularity()} - Sets the granularity at which a view's text
 *       was traversed.</li>
 *   <li>{@link #getText()} -  The text of the source's sub-tree.</li>
 *   <li>{@link #getFromIndex()} - The start the text that was skipped over in this movement.
 *       This is the starting point when moving forward through the text, but not when moving
 *       back.</li>
 *   <li>{@link #getToIndex()} - The end of the text that was skipped over in this movement.
 *       This is the ending point when moving forward through the text, but not when moving
 *       back.</li>
 *   <li>{@link #getAction()} - Gets traversal action which specifies the direction.</li>
 * </ul>
 * </p>
 * <p>
 * <b>View scrolled</b> - represents the event of scrolling a view. </br>
 * <em>Type:</em> {@link #TYPE_VIEW_SCROLLED}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 *   <li>{@link #getSource()} - The source info (for registered clients).</li>
 *   <li>{@link #getClassName()} - The class name of the source.</li>
 *   <li>{@link #getPackageName()} - The package name of the source.</li>
 *   <li>{@link #getEventTime()}  - The event time.</li>
 *   <li>{@link #getScrollDeltaX()} - The difference in the horizontal position.</li>
 *   <li>{@link #getScrollDeltaY()} - The difference in the vertical position.</li>
 *   <li>{@link #getMaxScrollX()} ()} -  The max scroll offset of the source left edge</li>
 *   <li>{@link #getMaxScrollY()} ()} - The max scroll offset of the source top edge.</li>
 * </ul>
 * </p>
 * <p>
 * <b>TRANSITION TYPES</b></br>
 * </p>
 * <p>
 * <b>Window state changed</b> - represents the event of a change to a section of
 * the user interface that is visually distinct. Should be sent from either the
 * root view of a window or from a view that is marked as a pane
 * {@link android.view.View#setAccessibilityPaneTitle(CharSequence)}. Note that changes
 * to true windows are represented by {@link #TYPE_WINDOWS_CHANGED}.</br>
 * <em>Type:</em> {@link #TYPE_WINDOW_STATE_CHANGED}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 *   <li>{@link #getContentChangeTypes()} - The type of state changes.</li>
 *   <li>{@link #getSource()} - The source info (for registered clients).</li>
 *   <li>{@link #getClassName()} - The class name of the source.</li>
 *   <li>{@link #getPackageName()} - The package name of the source.</li>
 *   <li>{@link #getEventTime()}  - The event time.</li>
 *   <li>{@link #getText()} - The text of the source's sub-tree, including the pane titles.</li>
 * </ul>
 * </p>
 * <p>
 * <b>Window content changed</b> - represents the event of change in the
 * content of a window. This change can be adding/removing view, changing
 * a view size, etc.</br>
 * </p>
 * <p>
 * <em>Type:</em> {@link #TYPE_WINDOW_CONTENT_CHANGED}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 *   <li>{@link #getContentChangeTypes()} - The type of content changes.</li>
 *   <li>{@link #getSource()} - The source info (for registered clients).</li>
 *   <li>{@link #getClassName()} - The class name of the source.</li>
 *   <li>{@link #getPackageName()} - The package name of the source.</li>
 *   <li>{@link #getEventTime()}  - The event time.</li>
 * </ul>
 * </p>
 * <p>
 * <b>Windows changed</b> - represents a change in the windows shown on
 * the screen such as a window appeared, a window disappeared, a window size changed,
 * a window layer changed, etc. These events should only come from the system, which is responsible
 * for managing windows. The list of windows is available from
 * {@link android.accessibilityservice.AccessibilityService#getWindows()}.
 * For regions of the user interface that are presented as windows but are
 * controlled by an app's process, use {@link #TYPE_WINDOW_STATE_CHANGED}.</br>
 * <em>Type:</em> {@link #TYPE_WINDOWS_CHANGED}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 *   <li>{@link #getEventTime()} - The event time.</li>
 *   <li>{@link #getWindowChanges()}</li> - The specific change to the source window
 * </ul>
 * <em>Note:</em> You can retrieve the {@link AccessibilityWindowInfo} for the window
 * source of the event by looking through the list returned by
 * {@link android.accessibilityservice.AccessibilityService#getWindows()} for the window whose ID
 * matches {@link #getWindowId()}.
 * </p>
 * <p>
 * <b>NOTIFICATION TYPES</b></br>
 * </p>
 * <p>
 * <b>Notification state changed</b> - represents the event showing a transient piece of information
 * to the user. This information may be a {@link android.app.Notification} or
 * {@link android.widget.Toast}.</br>
 * <em>Type:</em> {@link #TYPE_NOTIFICATION_STATE_CHANGED}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 *   <li>{@link #getClassName()} - The class name of the source.</li>
 *   <li>{@link #getPackageName()} - The package name of the source.</li>
 *   <li>{@link #getEventTime()}  - The event time.</li>
 *   <li>{@link #getParcelableData()} - The posted {@link android.app.Notification}, if
 *   applicable.</li>
 *   <li>{@link #getText()} - Displayed text of the {@link android.widget.Toast}, if applicable,
 *   or may contain text from the {@link android.app.Notification}, although
 *   {@link #getParcelableData()} is a richer set of data for {@link android.app.Notification}.</li>
 * </ul>
 * </p>
 * <p>
 * <b>EXPLORATION TYPES</b></br>
 * </p>
 * <p>
 * <b>View hover enter</b> - represents the event of beginning to hover
 * over a {@link android.view.View}. The hover may be generated via
 * exploring the screen by touch or via a pointing device.</br>
 * <em>Type:</em> {@link #TYPE_VIEW_HOVER_ENTER}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 *   <li>{@link #getSource()} - The source info (for registered clients).</li>
 *   <li>{@link #getClassName()} - The class name of the source.</li>
 *   <li>{@link #getPackageName()} - The package name of the source.</li>
 *   <li>{@link #getEventTime()}  - The event time.</li>
 * </ul>
 * </p>
 * <b>View hover exit</b> - represents the event of stopping to hover
 * over a {@link android.view.View}. The hover may be generated via
 * exploring the screen by touch or via a pointing device.</br>
 * <em>Type:</em> {@link #TYPE_VIEW_HOVER_EXIT}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 *   <li>{@link #getSource()} - The source info (for registered clients).</li>
 *   <li>{@link #getClassName()} - The class name of the source.</li>
 *   <li>{@link #getPackageName()} - The package name of the source.</li>
 *   <li>{@link #getEventTime()}  - The event time.</li>
 * </ul>
 * </p>
 * <p>
 * <b>View scrolled to</b> - represents the event of a target node brought on screen by
 * ACTION_SCROLL_IN_DIRECTION.
 * <em>Type:</em> {@link #TYPE_VIEW_TARGETED_BY_SCROLL}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 *   <li>{@link #getSource()} - The source info (for registered clients). This represents the node
 *   that is brought on screen as a result of the scroll.</li>
 *   <li>{@link #getClassName()} - The class name of the source.</li>
 *   <li>{@link #getPackageName()} - The package name of the source.</li>
 *   <li>{@link #getEventTime()}  - The event time.</li>
 * </ul>
 * </p>
 * <p>
 * <b>Touch interaction start</b> - represents the event of starting a touch
 * interaction, which is the user starts touching the screen.</br>
 * <em>Type:</em> {@link #TYPE_TOUCH_INTERACTION_START}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 * </ul>
 * <em>Note:</em> This event is fired only by the system and is not passed to the
 * view tree to be populated.</br>
 * </p>
 * <p>
 * <b>Touch interaction end</b> - represents the event of ending a touch
 * interaction, which is the user stops touching the screen.</br>
 * <em>Type:</em> {@link #TYPE_TOUCH_INTERACTION_END}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 * </ul>
 * <em>Note:</em> This event is fired only by the system and is not passed to the
 * view tree to be populated.</br>
 * </p>
 * <p>
 * <b>Touch exploration gesture start</b> - represents the event of starting a touch
 * exploring gesture.</br>
 * <em>Type:</em> {@link #TYPE_TOUCH_EXPLORATION_GESTURE_START}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 * </ul>
 * <em>Note:</em> This event is fired only by the system and is not passed to the
 * view tree to be populated.</br>
 * </p>
 * <p>
 * <b>Touch exploration gesture end</b> - represents the event of ending a touch
 * exploring gesture.</br>
 * <em>Type:</em> {@link #TYPE_TOUCH_EXPLORATION_GESTURE_END}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 * </ul>
 * <em>Note:</em> This event is fired only by the system and is not passed to the
 * view tree to be populated.</br>
 * </p>
 * <p>
 * <b>Touch gesture detection start</b> - represents the event of starting a user
 * gesture detection.</br>
 * <em>Type:</em> {@link #TYPE_GESTURE_DETECTION_START}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 * </ul>
 * <em>Note:</em> This event is fired only by the system and is not passed to the
 * view tree to be populated.</br>
 * </p>
 * <p>
 * <b>Touch gesture detection end</b> - represents the event of ending a user
 * gesture detection.</br>
 * <em>Type:</em> {@link #TYPE_GESTURE_DETECTION_END}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 * </ul>
 * <em>Note:</em> This event is fired only by the system and is not passed to the
 * view tree to be populated.</br>
 * </p>
 * <p>
 * <b>MISCELLANEOUS TYPES</b></br>
 * </p>
 * <p>
 * <b>Announcement</b> - represents the event of an application requesting a screen reader to make
 * an announcement. Because the event carries no semantic meaning, this event is appropriate only
 * in exceptional situations where additional screen reader output is needed but other types of
 * accessibility services do not need to be aware of the change.</br>
 * <em>Type:</em> {@link #TYPE_ANNOUNCEMENT}</br>
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getEventType()} - The type of the event.</li>
 *   <li>{@link #getSource()} - The source info (for registered clients).</li>
 *   <li>{@link #getClassName()} - The class name of the source.</li>
 *   <li>{@link #getPackageName()} - The package name of the source.</li>
 *   <li>{@link #getEventTime()}  - The event time.</li>
 *   <li>{@link #getText()} - The text of the announcement.</li>
 * </ul>
 * </p>
 * <p>
 * <b>speechStateChanged</b>
 * <em>Type:</em> {@link #TYPE_SPEECH_STATE_CHANGE}</br>
 * Represents a change in the speech state defined by the
 * bit mask of the speech state change types.
 * A change in the speech state occurs when an application wants to signal that
 * it is either speaking or listening for human speech.
 * This event helps avoid conflicts where two applications want to speak or one listens
 * when another speaks.
 * When sending this event, the sender should ensure that  the accompanying state change types
 * make sense. For example, the sender should not send
 * {@link #SPEECH_STATE_SPEAKING_START} and {@link #SPEECH_STATE_SPEAKING_END} together.
 * <em>Properties:</em></br>
 * <ul>
 *   <li>{@link #getSpeechStateChangeTypes()} - The type of state changes</li>
 *   <li>{@link #getPackageName()} - The package name of the source.</li>
 *   <li>{@link #getEventTime()}  - The event time.</li>
 * </ul>
 * </p>
 *
 * @see android.view.accessibility.AccessibilityManager
 * @see android.accessibilityservice.AccessibilityService
 * @see AccessibilityNodeInfo
 */
public final class AccessibilityEvent implements Parcelable {
    /**
     * Change type for {@link #TYPE_WINDOW_CONTENT_CHANGED} event:
     * The source node changed its checked state, which is returned by
     * {@link AccessibilityNodeInfo#getChecked()}.
     * The view changing its checked state should call
     * {@link AccessibilityNodeInfo#setChecked(int)} and then send this event.
     *
     * @see AccessibilityNodeInfo#getChecked()
     * @see AccessibilityNodeInfo#setChecked(int)
     */
    public static final int CONTENT_CHANGE_TYPE_CHECKED = 8192;

    /**
     * Change type for {@link #TYPE_WINDOW_CONTENT_CHANGED} event:
     * The node's content description changed.
     */
    public static final int CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION = 4;

    /**
     * Change type for {@link #TYPE_WINDOW_CONTENT_CHANGED} event:
     * The source node changed its content validity returned by
     * {@link AccessibilityNodeInfo#isContentInvalid}.
     * The view changing content validity should call
     * {@link AccessibilityNodeInfo#setContentInvalid} and then send this event.
     *
     * @see AccessibilityNodeInfo#isContentInvalid
     * @see AccessibilityNodeInfo#setContentInvalid
     */
    public static final int CONTENT_CHANGE_TYPE_CONTENT_INVALID = 1024;

    /**
     * Change type for {@link #TYPE_WINDOW_CONTENT_CHANGED} event:
     * A drag in with accessibility enabled has ended. This means the content has been
     * unsuccessfully dropped, the user has canceled the action via an AccessibilityAction, or
     * no drop has been detected within a timeout and the drag was automatically cancelled. This is
     * sent from the source that initiated the drag.
     *
     * @see AccessibilityNodeInfo.AccessibilityAction#ACTION_DRAG_CANCEL
     */
    public static final int CONTENT_CHANGE_TYPE_DRAG_CANCELLED = 512;

    /**
     * Change type for {@link #TYPE_WINDOW_CONTENT_CHANGED} event:
     * A drag in with accessibility enabled has ended. This means the content has been
     * successfully dropped. This is sent from the target that accepted the dragged content.
     *
     * @see AccessibilityNodeInfo.AccessibilityAction#ACTION_DRAG_DROP
     */
    public static final int CONTENT_CHANGE_TYPE_DRAG_DROPPED = 256;

    /**
     * Change type for {@link #TYPE_WINDOW_CONTENT_CHANGED} event:
     * A drag has started while accessibility is enabled. This is either via an
     * AccessibilityAction, or via touch events. This is sent from the source that initiated the
     * drag.
     *
     * @see AccessibilityNodeInfo.AccessibilityAction#ACTION_DRAG_START
     */
    public static final int CONTENT_CHANGE_TYPE_DRAG_STARTED = 128;

    /**
     * Change type for {@link #TYPE_WINDOW_CONTENT_CHANGED} event:
     * The source node changed its ability to interact returned by
     * {@link AccessibilityNodeInfo#isEnabled}.
     * The view changing content's ability to interact should call
     * {@link AccessibilityNodeInfo#setEnabled} and then send this event.
     *
     * @see AccessibilityNodeInfo#isEnabled
     * @see AccessibilityNodeInfo#setEnabled
     */
    public static final int CONTENT_CHANGE_TYPE_ENABLED = 4096;

    /**
     * Change type for {@link #TYPE_WINDOW_CONTENT_CHANGED} event:
     * The source node changed its erroneous content's error message returned by
     * {@link AccessibilityNodeInfo#getError}.
     * The view changing erroneous content's error message should call
     * {@link AccessibilityNodeInfo#setError} and then send this event.
     *
     * @see AccessibilityNodeInfo#getError
     * @see AccessibilityNodeInfo#setError
     */
    public static final int CONTENT_CHANGE_TYPE_ERROR = 2048;

    /**
     * Change type for {@link #TYPE_WINDOW_CONTENT_CHANGED} event: The source node changed its
     * expanded state which is returned by {@link AccessibilityNodeInfo#getExpandedState()}. The
     * view changing the node's expanded state should call {@link
     * AccessibilityNodeInfo#setExpandedState(int)} and then send this event.
     *
     * @see AccessibilityNodeInfo#getExpandedState()
     * @see AccessibilityNodeInfo#setExpandedState(int)
     */
    public static final int CONTENT_CHANGE_TYPE_EXPANDED = 16384;

    /**
     * Change type for {@link #TYPE_WINDOW_STATE_CHANGED} event:
     * The node has a pane title, and either just appeared or just was assigned a title when it
     * had none before.
     * @see View#setAccessibilityPaneTitle(CharSequence)
     */
    public static final int CONTENT_CHANGE_TYPE_PANE_APPEARED = 16;

    /**
     * Change type for {@link #TYPE_WINDOW_STATE_CHANGED} event:
     * Can mean one of two slightly different things. The primary meaning is that the node has
     * a pane title, and was removed from the node hierarchy. It will also be sent if the pane
     * title is set to {@code null} after it contained a title.
     * No source will be returned if the node is no longer on the screen. To make the change more
     * clear for the user, the first entry in {@link #getText()} will return the value that would
     * have been returned by {@code getSource().getPaneTitle()}.
     * @see View#setAccessibilityPaneTitle(CharSequence)
     */
    public static final int CONTENT_CHANGE_TYPE_PANE_DISAPPEARED = 32;

    /**
     * Change type for {@link #TYPE_WINDOW_STATE_CHANGED} event:
     * The node's pane title changed.
     * <p>
     * If this makes the pane appear, {@link #CONTENT_CHANGE_TYPE_PANE_APPEARED} is sent
     * instead. If this makes the pane disappear, {@link #CONTENT_CHANGE_TYPE_PANE_DISAPPEARED}
     * is sent.
     * @see View#setAccessibilityPaneTitle(CharSequence)
     */
    public static final int CONTENT_CHANGE_TYPE_PANE_TITLE = 8;

    /**
     * Change type for {@link #TYPE_WINDOW_CONTENT_CHANGED} event:
     * state description of the node as returned by
     * {@link AccessibilityNodeInfo#getStateDescription} changed. If part of the state description
     * changes, the changed part can be put into event text. For example, if state description
     * changed from "on, wifi signal full" to "on, wifi three bars", "wifi three bars" can be put
     * into the event text.
     * @see View#setStateDescription(CharSequence)
     */
    public static final int CONTENT_CHANGE_TYPE_STATE_DESCRIPTION = 64;

    /**
     * Change type for {@link #TYPE_WINDOW_CONTENT_CHANGED} event:
     * One or more content changes occurred in the the subtree rooted at the source node,
     * or the subtree's structure changed when a node was added or removed.
     */
    public static final int CONTENT_CHANGE_TYPE_SUBTREE = 1;

    /**
     * Change type for {@link #TYPE_WINDOW_CONTENT_CHANGED} event:
     * The source node changed its supplemental description, which is returned by
     * {@link AccessibilityNodeInfo#getSupplementalDescription()}.
     * The view changing its supplemental description should call
     * {@link AccessibilityNodeInfo#setSupplementalDescription(CharSequence)} and
     * then send this event.
     *
     * @see AccessibilityNodeInfo#getSupplementalDescription()
     * @see AccessibilityNodeInfo#setSupplementalDescription(CharSequence)
     */
    public static final int CONTENT_CHANGE_TYPE_SUPPLEMENTAL_DESCRIPTION = 32768;

    /**
     * Change type for {@link #TYPE_WINDOW_CONTENT_CHANGED} event:
     * The node's text changed.
     * @see AccessibilityNodeInfo#setText(CharSequence)
     */
    public static final int CONTENT_CHANGE_TYPE_TEXT = 2;

    /**
     * Change type for {@link #TYPE_WINDOW_CONTENT_CHANGED} event: The type of change is not
     * defined.
     */
    public static final int CONTENT_CHANGE_TYPE_UNDEFINED = 0;

    /**
     * Invalid selection/focus position.
     *
     * @see #getCurrentItemIndex()
     */
    public static final int INVALID_POSITION = -1;

    /**
     * Maximum length of the text fields.
     *
     * @see #getBeforeText()
     * @see #getText()
     * </br>
     * Note: This constant is no longer needed since there
     *       is no limit on the length of text that is contained
     *       in an accessibility event anymore.
     */
    @Deprecated
    public static final int MAX_TEXT_LENGTH = 500;

    /**
     * Change type for {@link #TYPE_SPEECH_STATE_CHANGE} event: another service is no longer
     * listening to the microphone.
     */
    public static final int SPEECH_STATE_LISTENING_END = 8;

    /**
     * Change type for {@link #TYPE_SPEECH_STATE_CHANGE} event: another service is listening to the
     * microphone.
     */
    public static final int SPEECH_STATE_LISTENING_START = 4;

    /**
     * Change type for {@link #TYPE_SPEECH_STATE_CHANGE} event: another service is no longer
     * speaking.
     */
    public static final int SPEECH_STATE_SPEAKING_END = 2;

    /**
     * Change type for {@link #TYPE_SPEECH_STATE_CHANGE} event: another service is speaking.
     */
    public static final int SPEECH_STATE_SPEAKING_START = 1;

    /**
     * Mask for {@link AccessibilityEvent} all types.
     *
     * @see #TYPE_VIEW_CLICKED
     * @see #TYPE_VIEW_LONG_CLICKED
     * @see #TYPE_VIEW_SELECTED
     * @see #TYPE_VIEW_FOCUSED
     * @see #TYPE_VIEW_TEXT_CHANGED
     * @see #TYPE_WINDOW_STATE_CHANGED
     * @see #TYPE_NOTIFICATION_STATE_CHANGED
     * @see #TYPE_VIEW_HOVER_ENTER
     * @see #TYPE_VIEW_HOVER_EXIT
     * @see #TYPE_TOUCH_EXPLORATION_GESTURE_START
     * @see #TYPE_TOUCH_EXPLORATION_GESTURE_END
     * @see #TYPE_WINDOW_CONTENT_CHANGED
     * @see #TYPE_VIEW_SCROLLED
     * @see #TYPE_VIEW_TEXT_SELECTION_CHANGED
     * @see #TYPE_ANNOUNCEMENT
     * @see #TYPE_VIEW_ACCESSIBILITY_FOCUSED
     * @see #TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED
     * @see #TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY
     * @see #TYPE_GESTURE_DETECTION_START
     * @see #TYPE_GESTURE_DETECTION_END
     * @see #TYPE_TOUCH_INTERACTION_START
     * @see #TYPE_TOUCH_INTERACTION_END
     * @see #TYPE_WINDOWS_CHANGED
     * @see #TYPE_VIEW_CONTEXT_CLICKED
     * @see #TYPE_ASSIST_READING_CONTEXT
     * @see #TYPE_SPEECH_STATE_CHANGE
     * @see #TYPE_VIEW_TARGETED_BY_SCROLL
     */
    public static final int TYPES_ALL_MASK = -1;

    /**
     * Represents the event of an application making an announcement.
     *
     * @deprecated Use one of the semantic alternative methods described in the documentation of
     *     {@link View#announceForAccessibility(CharSequence)} instead of using this event, as
     *     accessibility services may choose to ignore dispatch of this event type.
     */
    @Deprecated
    public static final int TYPE_ANNOUNCEMENT = 16384;

    /**
     * Represents the event of the assistant currently reading the users screen context.
     */
    public static final int TYPE_ASSIST_READING_CONTEXT = 16777216;

    /**
     * Represents the event of ending gesture detection.
     */
    public static final int TYPE_GESTURE_DETECTION_END = 524288;

    /**
     * Represents the event of beginning gesture detection.
     */
    public static final int TYPE_GESTURE_DETECTION_START = 262144;

    /**
     * Represents the event showing a {@link android.app.Notification}.
     */
    public static final int TYPE_NOTIFICATION_STATE_CHANGED = 64;

    /**
     * Represents a change in the speech state defined by the speech state change types.
     * A change in the speech state occurs when an application wants to signal that it is either
     * speaking or listening for human speech.
     * This event helps avoid conflicts where two applications want to speak or one listens
     * when another speaks.
     * When sending this event, the sender should ensure that  the accompanying state change types
     * make sense. For example, the sender should not send
     * {@link #SPEECH_STATE_SPEAKING_START} and {@link #SPEECH_STATE_SPEAKING_END} together.
     * @see #SPEECH_STATE_SPEAKING_START
     * @see #SPEECH_STATE_SPEAKING_END
     * @see #SPEECH_STATE_LISTENING_START
     * @see #SPEECH_STATE_LISTENING_END
     * @see #getSpeechStateChangeTypes
     * @see #setSpeechStateChangeTypes
     */
    public static final int TYPE_SPEECH_STATE_CHANGE = 33554432;

    /**
     * Represents the event of ending a touch exploration gesture.
     */
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_END = 1024;

    /**
     * Represents the event of starting a touch exploration gesture.
     */
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_START = 512;

    /**
     * Represents the event of the user ending to touch the screen.
     */
    public static final int TYPE_TOUCH_INTERACTION_END = 2097152;

    /**
     * Represents the event of the user starting to touch the screen.
     */
    public static final int TYPE_TOUCH_INTERACTION_START = 1048576;

    /**
     * Represents the event of gaining accessibility focus.
     * @see AccessibilityNodeInfo.AccessibilityAction#ACTION_ACCESSIBILITY_FOCUS for the difference
     * between input and accessibility focus.
     */
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUSED = 32768;

    /**
     * Represents the event of clearing accessibility focus.
     * @see AccessibilityNodeInfo.AccessibilityAction#ACTION_ACCESSIBILITY_FOCUS for the difference
     * between input and accessibility focus.
     */
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED = 65536;

    /**
     * Represents the event of clicking on a {@link android.view.View} like
     * {@link android.widget.Button}, {@link android.widget.CompoundButton}, etc.
     * <p>See {@link AccessibilityNodeInfo.AccessibilityAction#ACTION_CLICK} for more
     * details.
     */
    public static final int TYPE_VIEW_CLICKED = 1;

    /**
     * Represents the event of a context click on a {@link android.view.View}.
     * <p>See {@link AccessibilityNodeInfo.AccessibilityAction#ACTION_CONTEXT_CLICK} for more
     * details.
     */
    public static final int TYPE_VIEW_CONTEXT_CLICKED = 8388608;

    /**
     * Represents the event of setting input focus of a {@link android.view.View}.
     * @see AccessibilityNodeInfo.AccessibilityAction#ACTION_ACCESSIBILITY_FOCUS for the difference
     * between input and accessibility focus.
     */
    public static final int TYPE_VIEW_FOCUSED = 8;

    /**
     * Represents the event of a hover enter over a {@link android.view.View}.
     */
    public static final int TYPE_VIEW_HOVER_ENTER = 128;

    /**
     * Represents the event of a hover exit over a {@link android.view.View}.
     */
    public static final int TYPE_VIEW_HOVER_EXIT = 256;

    /**
     * Represents the event of long clicking on a {@link android.view.View} like
     * {@link android.widget.Button}, {@link android.widget.CompoundButton}, etc.
     * <p>See {@link AccessibilityNodeInfo.AccessibilityAction#ACTION_LONG_CLICK} for more
     * details.
     */
    public static final int TYPE_VIEW_LONG_CLICKED = 2;

    /**
     * Represents the event of scrolling a view. This event type is generally not sent directly. In
     * the View system, this is sent in
     * {@link android.view.View#onScrollChanged(int, int, int, int)}
     * <p>In addition to the source and package name, the event should populate scroll-specific
     * properties like {@link #setScrollDeltaX(int)}, {@link #setScrollDeltaY(int)},
     * {@link #setMaxScrollX(int)}, and {@link #setMaxScrollY(int)}.
     * <p>Services are encouraged to rely on the source to query UI state over AccessibilityEvents
     * properties. For example, to check after a scroll if the bottom of the scrolling UI element
     * has been reached, check if the source node is scrollable and has the
     * {@link AccessibilityNodeInfo.AccessibilityAction#ACTION_SCROLL_BACKWARD} action but not the
     * {@link AccessibilityNodeInfo.AccessibilityAction#ACTION_SCROLL_FORWARD} action.
     * For scrolling to a target, use {@link #TYPE_VIEW_TARGETED_BY_SCROLL}.
     */
    public static final int TYPE_VIEW_SCROLLED = 4096;

    /**
     * Represents the event of selecting an item usually in the context of an
     * {@link android.widget.AdapterView}.
     * @see AccessibilityNodeInfo.AccessibilityAction#ACTION_SELECT
     */
    public static final int TYPE_VIEW_SELECTED = 4;

    /**
     * Represents the event of a scroll having completed and brought the target node on screen.
     */
    public static final int TYPE_VIEW_TARGETED_BY_SCROLL = 67108864;

    /**
     * Represents the event of changing the text of an {@link android.widget.EditText}.
     * @see AccessibilityNodeInfo#setText(CharSequence)
     */
    public static final int TYPE_VIEW_TEXT_CHANGED = 16;

    /**
     * Represents the event of changing the selection in an {@link android.widget.EditText}.
     */
    public static final int TYPE_VIEW_TEXT_SELECTION_CHANGED = 8192;

    /**
     * Represents the event of traversing the text of a view at a given movement granularity.
     */
    public static final int TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY = 131072;

    /**
     * Represents the event change in the system windows shown on the screen. This event type should
     * only be dispatched by the system.
     */
    public static final int TYPE_WINDOWS_CHANGED = 4194304;

    /**
     * Represents the event of changing the content of a window and more
     * specifically the sub-tree rooted at the event's source.
     */
    public static final int TYPE_WINDOW_CONTENT_CHANGED = 2048;

    /**
     * Represents the event of a change to a visually distinct section of the user interface.
     * <p>
     * These events should only be dispatched from {@link android.view.View}s that have
     * accessibility pane titles, and replaces {@link #TYPE_WINDOW_CONTENT_CHANGED} for those
     * sources. Details about the change are available from {@link #getContentChangeTypes()}.
     * <p>
     * Do not use this to get an accessibility service to make non-pane announcements. Instead,
     * follow the practices described in {@link View#announceForAccessibility(CharSequence)}.
     * <b>Note:</b> this does not suggest calling announceForAccessibility(), but using the
     * suggestions listed in its documentation.
     */
    public static final int TYPE_WINDOW_STATE_CHANGED = 32;

    /**
     * Change type for {@link #TYPE_WINDOWS_CHANGED} event:
     * The window's {@link AccessibilityWindowInfo#isAccessibilityFocused()} changed.
     */
    public static final int WINDOWS_CHANGE_ACCESSIBILITY_FOCUSED = 128;

    /**
     * Change type for {@link #TYPE_WINDOWS_CHANGED} event:
     * The window's {@link AccessibilityWindowInfo#isActive()} changed.
     */
    public static final int WINDOWS_CHANGE_ACTIVE = 32;

    /**
     * Change type for {@link #TYPE_WINDOWS_CHANGED} event:
     * The window was added.
     */
    public static final int WINDOWS_CHANGE_ADDED = 1;

    /**
     * Change type for {@link #TYPE_WINDOWS_CHANGED} event:
     * The window's bounds changed.
     * <p>
     * Starting in {@link android.os.Build.VERSION_CODES#R R}, this event implies the window's
     * region changed. It's also possible that region changed but bounds doesn't.
     * </p>
     */
    public static final int WINDOWS_CHANGE_BOUNDS = 8;

    /**
     * Change type for {@link #TYPE_WINDOWS_CHANGED} event:
     * The window's children changed.
     */
    public static final int WINDOWS_CHANGE_CHILDREN = 512;

    /**
     * Change type for {@link #TYPE_WINDOWS_CHANGED} event:
     * The window's {@link AccessibilityWindowInfo#isFocused()} changed.
     */
    public static final int WINDOWS_CHANGE_FOCUSED = 64;

    /**
     * Change type for {@link #TYPE_WINDOWS_CHANGED} event:
     * The window's layer changed.
     */
    public static final int WINDOWS_CHANGE_LAYER = 16;

    /**
     * Change type for {@link #TYPE_WINDOWS_CHANGED} event:
     * The window's parent changed.
     */
    public static final int WINDOWS_CHANGE_PARENT = 256;

    /**
     * Change type for {@link #TYPE_WINDOWS_CHANGED} event:
     * The window either entered or exited picture-in-picture mode.
     */
    public static final int WINDOWS_CHANGE_PIP = 1024;

    /**
     * Change type for {@link #TYPE_WINDOWS_CHANGED} event:
     * A window was removed.
     */
    public static final int WINDOWS_CHANGE_REMOVED = 2;

    /**
     * Change type for {@link #TYPE_WINDOWS_CHANGED} event:
     * The window's title changed.
     */
    public static final int WINDOWS_CHANGE_TITLE = 4;

    private AccessibilityEvent() {
    }
}
