package android.view.accessibility;

import android.os.Parcelable;

/**
 * This class represents a node of the window content as well as actions that
 * can be requested from its source. From the point of view of an
 * {@link android.accessibilityservice.AccessibilityService} a window's content is
 * presented as a tree of accessibility node infos, which may or may not map one-to-one
 * to the view hierarchy. In other words, a custom view is free to report itself as
 * a tree of accessibility node info.
 * </p>
 * <p>
 * Once an accessibility node info is delivered to an accessibility service it is
 * made immutable and calling a state mutation method generates an error. See
 * {@link #setQueryFromAppProcessEnabled} if you would like to inspect the
 * node tree from the app process for testing or debugging tools.
 * </p>
 * <p>
 * Please refer to {@link android.accessibilityservice.AccessibilityService} for
 * details about how to obtain a handle to window content as a tree of accessibility
 * node info as well as details about the security model.
 * </p>
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about making applications accessible, read the
 * <a href="{@docRoot}guide/topics/ui/accessibility/index.html">Accessibility</a>
 * developer guide.</p>
 * </div>
 * <aside class="note">
 * <b>Note:</b> Use a {@link androidx.core.view.accessibility.AccessibilityNodeInfoCompat}
 * wrapper instead of this class for backwards-compatibility. </aside>
 *
 * @see android.accessibilityservice.AccessibilityService
 * @see AccessibilityEvent
 * @see AccessibilityManager
 */
public final class AccessibilityNodeInfo implements Parcelable {
    /**
     * Action that gives accessibility focus to the node.
     * See {@link AccessibilityAction#ACTION_ACCESSIBILITY_FOCUS}
     */
    public static final int ACTION_ACCESSIBILITY_FOCUS = 64;

    /**
     * Argument for specifying the collection column to make visible on screen.
     * <p>
     * <strong>Type:</strong> int<br>
     * <strong>Actions:</strong>
     * <ul>
     *     <li>{@link AccessibilityAction#ACTION_SCROLL_TO_POSITION}</li>
     * </ul>
     *
     * @see AccessibilityAction#ACTION_SCROLL_TO_POSITION
     */
    public static final String ACTION_ARGUMENT_COLUMN_INT = "android.view.accessibility.action.ARGUMENT_COLUMN_INT";

    /**
     * <p>Argument to represent the direction when using
     * {@link AccessibilityAction#ACTION_SCROLL_IN_DIRECTION}.</p>
     *
     * <p>
     *     The value of this argument can be one of:
     *     <ul>
     *         <li>{@link View#FOCUS_DOWN}</li>
     *         <li>{@link View#FOCUS_UP}</li>
     *         <li>{@link View#FOCUS_LEFT}</li>
     *         <li>{@link View#FOCUS_RIGHT}</li>
     *         <li>{@link View#FOCUS_FORWARD}</li>
     *         <li>{@link View#FOCUS_BACKWARD}</li>
     *     </ul>
     * </p>
     */
    public static final String ACTION_ARGUMENT_DIRECTION_INT = "android.view.accessibility.action.ARGUMENT_DIRECTION_INT";

    /**
     * Argument for whether when moving at granularity to extend the selection
     * or to move it otherwise.
     * <p>
     * <strong>Type:</strong> boolean<br>
     * <strong>Actions:</strong>
     * <ul>
     *     <li>{@link AccessibilityAction#ACTION_NEXT_AT_MOVEMENT_GRANULARITY}</li>
     *     <li>{@link AccessibilityAction#ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY}</li>
     * </ul>
     *
     * @see AccessibilityAction#ACTION_NEXT_AT_MOVEMENT_GRANULARITY
     * @see AccessibilityAction#ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY
     */
    public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";

    /**
     * Argument for which HTML element to get moving to the next/previous HTML element.
     * <p>
     * <strong>Type:</strong> String<br>
     * <strong>Actions:</strong>
     * <ul>
     *     <li>{@link AccessibilityAction#ACTION_NEXT_HTML_ELEMENT}</li>
     *     <li>{@link AccessibilityAction#ACTION_PREVIOUS_HTML_ELEMENT}</li>
     * </ul>
     * </p>
     *
     * @see AccessibilityAction#ACTION_NEXT_HTML_ELEMENT
     * @see AccessibilityAction#ACTION_PREVIOUS_HTML_ELEMENT
     */
    public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";

    /**
     * Argument for which movement granularity to be used when traversing the node text.
     * <p>
     * <strong>Type:</strong> int<br>
     * <strong>Actions:</strong>
     * <ul>
     *     <li>{@link AccessibilityAction#ACTION_NEXT_AT_MOVEMENT_GRANULARITY}</li>
     *     <li>{@link AccessibilityAction#ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY}</li>
     * </ul>
     * </p>
     *
     * @see AccessibilityAction#ACTION_NEXT_AT_MOVEMENT_GRANULARITY
     * @see AccessibilityAction#ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY
     */
    public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";

    /**
     * Argument for specifying the x coordinate to which to move a window.
     * <p>
     * <strong>Type:</strong> int<br>
     * <strong>Actions:</strong>
     * <ul>
     *     <li>{@link AccessibilityAction#ACTION_MOVE_WINDOW}</li>
     * </ul>
     *
     * @see AccessibilityAction#ACTION_MOVE_WINDOW
     */
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_X = "ACTION_ARGUMENT_MOVE_WINDOW_X";

    /**
     * Argument for specifying the y coordinate to which to move a window.
     * <p>
     * <strong>Type:</strong> int<br>
     * <strong>Actions:</strong>
     * <ul>
     *     <li>{@link AccessibilityAction#ACTION_MOVE_WINDOW}</li>
     * </ul>
     *
     * @see AccessibilityAction#ACTION_MOVE_WINDOW
     */
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_Y = "ACTION_ARGUMENT_MOVE_WINDOW_Y";

    /**
     * Argument to represent the duration in milliseconds to press and hold a node.
     * <p>
     * <strong>Type:</strong> int<br>
     * <strong>Actions:</strong>
     * <ul>
     *     <li>{@link AccessibilityAction#ACTION_PRESS_AND_HOLD}</li>
     * </ul>
     *
     * @see AccessibilityAction#ACTION_PRESS_AND_HOLD
     */
    public static final String ACTION_ARGUMENT_PRESS_AND_HOLD_DURATION_MILLIS_INT = "android.view.accessibility.action.ARGUMENT_PRESS_AND_HOLD_DURATION_MILLIS_INT";

    /**
     * Argument for specifying the progress value to set.
     * <p>
     * <strong>Type:</strong> float<br>
     * <strong>Actions:</strong>
     * <ul>
     *     <li>{@link AccessibilityAction#ACTION_SET_PROGRESS}</li>
     * </ul>
     *
     * @see AccessibilityAction#ACTION_SET_PROGRESS
     */
    public static final String ACTION_ARGUMENT_PROGRESS_VALUE = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE";

    /**
     * Argument for specifying the collection row to make visible on screen.
     * <p>
     * <strong>Type:</strong> int<br>
     * <strong>Actions:</strong>
     * <ul>
     *     <li>{@link AccessibilityAction#ACTION_SCROLL_TO_POSITION}</li>
     * </ul>
     *
     * @see AccessibilityAction#ACTION_SCROLL_TO_POSITION
     */
    public static final String ACTION_ARGUMENT_ROW_INT = "android.view.accessibility.action.ARGUMENT_ROW_INT";

    /**
     * <p>Argument to represent the scroll amount as a percent of the visible area of a node, with
     * 1.0F as the default. Values smaller than 1.0F represent a partial scroll of the node, and
     * values larger than 1.0F represent a scroll that extends beyond the currently visible node
     * Rect. Setting this to {@link Float#POSITIVE_INFINITY} or to another "too large" value should
     * scroll to the end of the node. Negative values should not be used with this argument.
     * </p>
     *
     * <p>
     *     This argument should be used with the following scroll actions:
     *     <ul>
     *         <li>{@link AccessibilityAction#ACTION_SCROLL_FORWARD}</li>
     *         <li>{@link AccessibilityAction#ACTION_SCROLL_BACKWARD}</li>
     *         <li>{@link AccessibilityAction#ACTION_SCROLL_UP}</li>
     *         <li>{@link AccessibilityAction#ACTION_SCROLL_DOWN}</li>
     *         <li>{@link AccessibilityAction#ACTION_SCROLL_LEFT}</li>
     *         <li>{@link AccessibilityAction#ACTION_SCROLL_RIGHT}</li>
     *     </ul>
     * </p>
     * <p>
     *     Example: if a view representing a list of items implements
     *     {@link AccessibilityAction#ACTION_SCROLL_FORWARD} to scroll forward by an entire screen
     *     (one "page"), then passing a value of .25F via this argument should scroll that view
     *     only by 1/4th of a screen. Passing a value of 1.50F via this argument should scroll the
     *     view by 1 1/2 screens or to end of the node if the node doesn't extend to 1 1/2 screens.
     * </p>
     *
     * <p>
     *     This argument should not be used with the following scroll actions, which don't cleanly
     *     conform to granular scroll semantics:
     *     <ul>
     *         <li>{@link AccessibilityAction#ACTION_SCROLL_IN_DIRECTION}</li>
     *         <li>{@link AccessibilityAction#ACTION_SCROLL_TO_POSITION}</li>
     *     </ul>
     * </p>
     *
     * <p>
     *     Views that support this argument should set
     *     {@link #setGranularScrollingSupported(boolean)} to true. Clients should use
     *     {@link #isGranularScrollingSupported()} to check if granular scrolling is supported.
     * </p>
     */
    public static final String ACTION_ARGUMENT_SCROLL_AMOUNT_FLOAT = "android.view.accessibility.action.ARGUMENT_SCROLL_AMOUNT_FLOAT";

    /**
     * Argument for specifying the selection end.
     * <p>
     * <strong>Type:</strong> int<br>
     * <strong>Actions:</strong>
     * <ul>
     *     <li>{@link AccessibilityAction#ACTION_SET_SELECTION}</li>
     * </ul>
     *
     * @see AccessibilityAction#ACTION_SET_SELECTION
     */
    public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";

    /**
     * Argument for specifying the selection start.
     * <p>
     * <strong>Type:</strong> int<br>
     * <strong>Actions:</strong>
     * <ul>
     *     <li>{@link AccessibilityAction#ACTION_SET_SELECTION}</li>
     * </ul>
     *
     * @see AccessibilityAction#ACTION_SET_SELECTION
     */
    public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";

    /**
     * Argument for specifying the text content to set.
     * <p>
     * <strong>Type:</strong> CharSequence<br>
     * <strong>Actions:</strong>
     * <ul>
     *     <li>{@link AccessibilityAction#ACTION_SET_TEXT}</li>
     * </ul>
     *
     * @see AccessibilityAction#ACTION_SET_TEXT
     */
    public static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";

    /**
     * Action that clears accessibility focus of the node.
     * See {@link AccessibilityAction#ACTION_CLEAR_ACCESSIBILITY_FOCUS}
     */
    public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 128;

    /**
     * Action that clears input focus of the node.
     * See {@link AccessibilityAction#ACTION_CLEAR_FOCUS}
     */
    public static final int ACTION_CLEAR_FOCUS = 2;

    /**
     * Action that deselects the node.
     */
    public static final int ACTION_CLEAR_SELECTION = 8;

    /**
     * Action that clicks on the node info.
     *
     * @see AccessibilityAction#ACTION_CLICK
     */
    public static final int ACTION_CLICK = 16;

    /**
     * Action to collapse an expandable node.
     */
    public static final int ACTION_COLLAPSE = 524288;

    /**
     * Action to copy the current selection to the clipboard.
     */
    public static final int ACTION_COPY = 16384;

    /**
     * Action to cut the current selection and place it to the clipboard.
     */
    public static final int ACTION_CUT = 65536;

    /**
     * Action to dismiss a dismissable node.
     */
    public static final int ACTION_DISMISS = 1048576;

    /**
     * Action to expand an expandable node.
     */
    public static final int ACTION_EXPAND = 262144;

    /**
     * Action that gives input focus to the node.
     * See {@link AccessibilityAction#ACTION_FOCUS}
     */
    public static final int ACTION_FOCUS = 1;

    /**
     * Action that long clicks on the node.
     *
     * <p>It does not support coordinate information for anchoring.</p>
     * @see AccessibilityAction#ACTION_LONG_CLICK
     */
    public static final int ACTION_LONG_CLICK = 32;

    /**
     * Action that requests to go to the next entity in this node's text
     * at a given movement granularity. For example, move to the next character,
     * word, etc.
     * <p>
     * <strong>Arguments:</strong> {@link #ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT}<,
     * {@link #ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN}<br>
     * <strong>Example:</strong> Move to the previous character and do not extend selection.
     * <code><pre><p>
     *   Bundle arguments = new Bundle();
     *   arguments.putInt(AccessibilityNodeInfo.ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT,
     *           AccessibilityNodeInfo.MOVEMENT_GRANULARITY_CHARACTER);
     *   arguments.putBoolean(AccessibilityNodeInfo.ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN,
     *           false);
     *   info.performAction(AccessibilityNodeInfo.ACTION_NEXT_AT_MOVEMENT_GRANULARITY, arguments);
     * </code></pre></p>
     * </p>
     *
     * @see #ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT
     * @see #ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN
     *
     * @see #setMovementGranularities(int)
     * @see #getMovementGranularities()
     *
     * @see #MOVEMENT_GRANULARITY_CHARACTER
     * @see #MOVEMENT_GRANULARITY_WORD
     * @see #MOVEMENT_GRANULARITY_LINE
     * @see #MOVEMENT_GRANULARITY_PARAGRAPH
     * @see #MOVEMENT_GRANULARITY_PAGE
     */
    public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 256;

    /**
     * Action to move to the next HTML element of a given type. For example, move
     * to the BUTTON, INPUT, TABLE, etc.
     * <p>
     * <strong>Arguments:</strong> {@link #ACTION_ARGUMENT_HTML_ELEMENT_STRING}<br>
     * <strong>Example:</strong>
     * <code><pre><p>
     *   Bundle arguments = new Bundle();
     *   arguments.putString(AccessibilityNodeInfo.ACTION_ARGUMENT_HTML_ELEMENT_STRING, "BUTTON");
     *   info.performAction(AccessibilityNodeInfo.ACTION_NEXT_HTML_ELEMENT, arguments);
     * </code></pre></p>
     * </p>
     */
    public static final int ACTION_NEXT_HTML_ELEMENT = 1024;

    /**
     * Action to paste the current clipboard content.
     */
    public static final int ACTION_PASTE = 32768;

    /**
     * Action that requests to go to the previous entity in this node's text
     * at a given movement granularity. For example, move to the next character,
     * word, etc.
     * <p>
     * <strong>Arguments:</strong> {@link #ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT}<,
     * {@link #ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN}<br>
     * <strong>Example:</strong> Move to the next character and do not extend selection.
     * <code><pre><p>
     *   Bundle arguments = new Bundle();
     *   arguments.putInt(AccessibilityNodeInfo.ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT,
     *           AccessibilityNodeInfo.MOVEMENT_GRANULARITY_CHARACTER);
     *   arguments.putBoolean(AccessibilityNodeInfo.ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN,
     *           false);
     *   info.performAction(AccessibilityNodeInfo.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY,
     *           arguments);
     * </code></pre></p>
     * </p>
     *
     * @see #ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT
     * @see #ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN
     *
     * @see #setMovementGranularities(int)
     * @see #getMovementGranularities()
     *
     * @see #MOVEMENT_GRANULARITY_CHARACTER
     * @see #MOVEMENT_GRANULARITY_WORD
     * @see #MOVEMENT_GRANULARITY_LINE
     * @see #MOVEMENT_GRANULARITY_PARAGRAPH
     * @see #MOVEMENT_GRANULARITY_PAGE
     */
    public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 512;

    /**
     * Action to move to the previous HTML element of a given type. For example, move
     * to the BUTTON, INPUT, TABLE, etc.
     * <p>
     * <strong>Arguments:</strong> {@link #ACTION_ARGUMENT_HTML_ELEMENT_STRING}<br>
     * <strong>Example:</strong>
     * <code><pre><p>
     *   Bundle arguments = new Bundle();
     *   arguments.putString(AccessibilityNodeInfo.ACTION_ARGUMENT_HTML_ELEMENT_STRING, "BUTTON");
     *   info.performAction(AccessibilityNodeInfo.ACTION_PREVIOUS_HTML_ELEMENT, arguments);
     * </code></pre></p>
     * </p>
     */
    public static final int ACTION_PREVIOUS_HTML_ELEMENT = 2048;

    /**
     * Action to scroll the node content backward.
     * @see AccessibilityAction#ACTION_SCROLL_BACKWARD
     */
    public static final int ACTION_SCROLL_BACKWARD = 8192;

    /**
     * Action to scroll the node content forward.
     * @see AccessibilityAction#ACTION_SCROLL_FORWARD
     */
    public static final int ACTION_SCROLL_FORWARD = 4096;

    /**
     * Action that selects the node.
     * @see AccessibilityAction#ACTION_SELECT
     */
    public static final int ACTION_SELECT = 4;

    /**
     * Action to set the selection. Performing this action with no arguments
     * clears the selection.
     *
     * @see AccessibilityAction#ACTION_SET_SELECTION
     * @see #ACTION_ARGUMENT_SELECTION_START_INT
     * @see #ACTION_ARGUMENT_SELECTION_END_INT
     */
    public static final int ACTION_SET_SELECTION = 131072;

    /**
     * Action that sets the text of the node. Performing the action without argument, using <code>
     * null</code> or empty {@link CharSequence} will clear the text. This action will also put the
     * cursor at the end of text.
     * @see AccessibilityAction#ACTION_SET_TEXT
     */
    public static final int ACTION_SET_TEXT = 2097152;

    /**
     * This node is not checked.
     */
    public static final int CHECKED_STATE_FALSE = 0;

    /**
     * This node is partially checked. For example,
     * when a checkbox owns a number of sub-options and they have
     * different states, then the main checkbox is in a partially-checked state.
     */
    public static final int CHECKED_STATE_PARTIAL = 2;

    /**
     * This node is checked.
     */
    public static final int CHECKED_STATE_TRUE = 1;

    /**
     * Expanded state for a collapsed expandable element.
     *
     * @see #getExpandedState()
     * @see #setExpandedState(int)
     */
    public static final int EXPANDED_STATE_COLLAPSED = 1;

    /**
     * Expanded state for a expanded expandable element that cannot be expanded further.
     *
     * @see #getExpandedState()
     * @see #setExpandedState(int)
     */
    public static final int EXPANDED_STATE_FULL = 3;

    /**
     * Expanded state for an expanded expandable element that can still be expanded further.
     *
     * @see #getExpandedState()
     * @see #setExpandedState(int)
     */
    public static final int EXPANDED_STATE_PARTIAL = 2;

    /**
     * Expanded state for a non-expandable element
     *
     * @see #getExpandedState()
     * @see #setExpandedState(int)
     */
    public static final int EXPANDED_STATE_UNDEFINED = 0;

    /**
     * Key used to request extra data for the rendering information.
     * The key requests that a {@link AccessibilityNodeInfo.ExtraRenderingInfo} be added to this
     * info. This request is made with {@link #refreshWithExtraData(String, Bundle)} without
     * argument.
     * <p>
     * The data can be retrieved from the {@link ExtraRenderingInfo} returned by
     * {@link #getExtraRenderingInfo()} using {@link ExtraRenderingInfo#getLayoutSize},
     * {@link ExtraRenderingInfo#getTextSizeInPx()} and
     * {@link ExtraRenderingInfo#getTextSizeUnit()}. For layout params, it is supported by both
     * {@link TextView} and {@link ViewGroup}. For text size and unit, it is only supported by
     * {@link TextView}.
     *
     * @see #refreshWithExtraData(String, Bundle)
     */
    public static final String EXTRA_DATA_RENDERING_INFO_KEY = "android.view.accessibility.extra.DATA_RENDERING_INFO_KEY";

    /**
     * Integer argument specifying the end index of the requested text location data. Must be
     * positive and no larger than {@link #EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH}.
     *
     * @see #EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY
     */
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH";

    /**
     * The maximum allowed length of the requested text location data.
     */
    public static final int EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH = 20000;

    /**
     * Integer argument specifying the start index of the requested text location data. Must be
     * valid inside the CharSequence returned by {@link #getText()}.
     *
     * @see #EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY
     */
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX";

    /**
     * Key used to request and locate extra data for text character location in
     * window coordinates. This key requests that an array of
     * {@link android.graphics.RectF}s be added to the extras. This request is made
     * with {@link #refreshWithExtraData(String, Bundle)}. The arguments taken by
     * this request are two integers:
     * {@link #EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX} and
     * {@link #EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH}. The starting index
     * must be valid inside the CharSequence returned by {@link #getText()}, and
     * the length must be positive.
     * <p>
     * Providers may advertise that they support text characters in window coordinates using
     * {@link #setAvailableExtraData(List)}. Services may check if an implementation supports text
     * characters in window coordinates with {@link #getAvailableExtraData()}.
     * <p>
     * The data can be retrieved from the {@code Bundle} returned by
     * {@link #getExtras()} using this string as a key for
     * {@link Bundle#getParcelableArray(String, Class)}. The
     * {@link android.graphics.RectF} will be {@code null} for characters that either do
     * not exist or are outside of the window bounds.
     * <p>
     * Note that character locations in window bounds are not modified by
     * changes in display magnification.
     *
     * {@see #refreshWithExtraData(String, Bundle)}
     */
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_IN_WINDOW_KEY = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_IN_WINDOW_KEY";

    /**
     * Key used to request and locate extra data for text character location. This key requests that
     * an array of {@link android.graphics.RectF}s be added to the extras. This request is made with
     * {@link #refreshWithExtraData(String, Bundle)}. The arguments taken by this request are two
     * integers: {@link #EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX} and
     * {@link #EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH}. The starting index must be valid
     * inside the CharSequence returned by {@link #getText()}, and the length must be positive.
     * <p>
     * The data can be retrieved from the {@code Bundle} returned by {@link #getExtras()} using this
     * string as a key for {@link Bundle#getParcelableArray(String, Class)}. The
     * {@link android.graphics.RectF} will be {@code null} for characters that either do not exist
     * or are off the screen.
     * <p>
     * Note that character locations returned are modified by changes in display magnification.
     *
     * {@see #refreshWithExtraData(String, Bundle)}
     */
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY";

    /**
     * Prefetching strategy that prefetches the ancestors of the requested node.
     * <p> Ancestors will be prefetched before siblings and descendants.
     *
     * @see #getChild(int, int)
     * @see #getParent(int)
     * @see AccessibilityWindowInfo#getRoot(int)
     * @see AccessibilityService#getRootInActiveWindow(int)
     * @see AccessibilityEvent#getSource(int)
     */
    public static final int FLAG_PREFETCH_ANCESTORS = 1;

    /**
     * Prefetching strategy that prefetches the descendants of the requested node breadth-first.
     * <p> This must not be combined with {@link #FLAG_PREFETCH_DESCENDANTS_HYBRID} or
     * {@link #FLAG_PREFETCH_DESCENDANTS_DEPTH_FIRST} or this will trigger an
     * IllegalArgumentException.
     *
     * <p> See {@link #FLAG_PREFETCH_ANCESTORS} for information on where these flags can be used.
     */
    public static final int FLAG_PREFETCH_DESCENDANTS_BREADTH_FIRST = 16;

    /**
     * Prefetching strategy that prefetches the descendants of the requested node depth-first.
     * <p> This must not be combined with {@link #FLAG_PREFETCH_DESCENDANTS_HYBRID} or
     * {@link #FLAG_PREFETCH_DESCENDANTS_BREADTH_FIRST} or this will trigger an
     * IllegalArgumentException.
     *
     * <p> See {@link #FLAG_PREFETCH_ANCESTORS} for information on where these flags can be used.
     */
    public static final int FLAG_PREFETCH_DESCENDANTS_DEPTH_FIRST = 8;

    /**
     * Prefetching strategy that prefetches the descendants in a hybrid depth first and breadth
     * first approach.
     * <p> The children of the root node is prefetched before recursing on the children. This
     * must not be combined with {@link #FLAG_PREFETCH_DESCENDANTS_DEPTH_FIRST} or
     * {@link #FLAG_PREFETCH_DESCENDANTS_BREADTH_FIRST} or this will trigger an
     * IllegalArgumentException.
     *
     * <p> See {@link #FLAG_PREFETCH_ANCESTORS} for information on where these flags can be used.
     */
    public static final int FLAG_PREFETCH_DESCENDANTS_HYBRID = 4;

    /**
     * Prefetching strategy that prefetches the siblings of the requested node.
     * <p> To avoid disconnected trees, this flag will also prefetch the parent. Siblings will be
     * prefetched before descendants.
     *
     * <p> See {@link #FLAG_PREFETCH_ANCESTORS} for information on where these flags can be used.
     */
    public static final int FLAG_PREFETCH_SIBLINGS = 2;

    /**
     * Prefetching flag that specifies prefetching should not be interrupted by a request to
     * retrieve a node or perform an action on a node.
     *
     * <p> See {@link #FLAG_PREFETCH_ANCESTORS} for information on where these flags can be used.
     */
    public static final int FLAG_PREFETCH_UNINTERRUPTIBLE = 32;

    /**
     * The accessibility focus.
     */
    public static final int FOCUS_ACCESSIBILITY = 2;

    /**
     * The input focus.
     */
    public static final int FOCUS_INPUT = 1;

    /**
     * Maximum batch size of prefetched nodes for a request.
     */
    public static final int MAX_NUMBER_OF_PREFETCHED_NODES = 50;

    /**
     * Movement granularity bit for traversing the text of a node by character.
     */
    public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;

    /**
     * Movement granularity bit for traversing the text of a node by line.
     */
    public static final int MOVEMENT_GRANULARITY_LINE = 4;

    /**
     * Movement granularity bit for traversing the text of a node by page.
     */
    public static final int MOVEMENT_GRANULARITY_PAGE = 16;

    /**
     * Movement granularity bit for traversing the text of a node by paragraph.
     */
    public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;

    /**
     * Movement granularity bit for traversing the text of a node by word.
     */
    public static final int MOVEMENT_GRANULARITY_WORD = 2;

    private AccessibilityNodeInfo() {
    }

    /**
     * Class with information if a node is a collection.
     * <p>
     * A collection of items has rows and columns and may be hierarchical.
     * For example, a horizontal list is a collection with one column, as
     * many rows as the list items, and is not hierarchical; A table is a
     * collection with several rows, several columns, and is not hierarchical;
     * A vertical tree is a hierarchical collection with one column and
     * as many rows as the first level children.
     * </p>
     */
    public static final class CollectionInfo {
        /**
         * Selection mode where multiple items may be selected.
         */
        public static final int SELECTION_MODE_MULTIPLE = 2;

        /**
         * Selection mode where items are not selectable.
         */
        public static final int SELECTION_MODE_NONE = 0;

        /**
         * Selection mode where a single item may be selected.
         */
        public static final int SELECTION_MODE_SINGLE = 1;

        /**
         * Constant to denote a missing collection count.
         *
         * This should be used for {@code mItemCount} and
         * {@code mImportantForAccessibilityItemCount} when values for those fields are not known.
         */
        public static final int UNDEFINED = -1;

        private CollectionInfo() {
        }
    }

    /**
     * Class with information if a node is a range.
     */
    public static final class RangeInfo {
        /**
         * Range type: float.
         */
        public static final int RANGE_TYPE_FLOAT = 1;

        /**
         * Range type: indeterminate.
         *
         * When using this type, the {@code min}, {@code max}, and {@code current} values used to
         * construct an instance may be ignored.
         *
         *  @see #INDETERMINATE
         */
        public static final int RANGE_TYPE_INDETERMINATE = 3;

        /**
         * Range type: integer.
         */
        public static final int RANGE_TYPE_INT = 0;

        /**
         * Range type: percent with values from zero to one hundred.
         */
        public static final int RANGE_TYPE_PERCENT = 2;

        private RangeInfo() {
        }
    }
}
