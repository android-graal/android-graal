package android.app.slice;

import android.os.Parcelable;

/**
 * A slice is a piece of app content and actions that can be surfaced outside of the app.
 *
 * <p>They are constructed using {@link Builder} in a tree structure
 * that provides the OS some information about how the content should be displayed.
 * @deprecated Slice framework has been deprecated, it will not receive any updates from
 *          {@link android.os.Build.VANILLA_ICE_CREAM} and forward. If you are looking for a
 *          framework that sends displayable data from one app to another, consider using
 *          {@link android.app.appsearch.AppSearchManager}.
 */
@Deprecated
public final class Slice implements Parcelable {
    /**
     * Key to retrieve an extra added to an intent when the value of an input range is changed.
     */
    @Deprecated
    public static final String EXTRA_RANGE_VALUE = "android.app.slice.extra.RANGE_VALUE";

    /**
     * Key to retrieve an extra added to an intent when a control is changed.
     */
    @Deprecated
    public static final String EXTRA_TOGGLE_STATE = "android.app.slice.extra.TOGGLE_STATE";

    /**
     * Hint that this slice contains a number of actions that can be grouped together
     * in a sort of controls area of the UI.
     */
    @Deprecated
    public static final String HINT_ACTIONS = "actions";

    /**
     * A hint to indicate that this slice represents an error.
     */
    @Deprecated
    public static final String HINT_ERROR = "error";

    /**
     * Hint that list items within this slice or subslice would appear better
     * if organized horizontally.
     */
    @Deprecated
    public static final String HINT_HORIZONTAL = "horizontal";

    /**
     * A hint to indicate that the contents of this subslice represent a list of keywords
     * related to the parent slice.
     * Expected to be on an item of format {@link SliceItem#FORMAT_SLICE}.
     */
    @Deprecated
    public static final String HINT_KEYWORDS = "keywords";

    /**
     * Hint that this content is important and should be larger when displayed if
     * possible.
     */
    @Deprecated
    public static final String HINT_LARGE = "large";

    /**
     * Hint indicating an item representing when the content was created or last updated.
     */
    @Deprecated
    public static final String HINT_LAST_UPDATED = "last_updated";

    /**
     * Hint that all sub-items/sub-slices within this content should be considered
     * to have {@link #HINT_LIST_ITEM}.
     */
    @Deprecated
    public static final String HINT_LIST = "list";

    /**
     * Hint that this item is part of a list and should be formatted as if is part
     * of a list.
     */
    @Deprecated
    public static final String HINT_LIST_ITEM = "list_item";

    /**
     * Hint to indicate that this content should not be tinted.
     */
    @Deprecated
    public static final String HINT_NO_TINT = "no_tint";

    /**
     * Hint to indicate that this slice is incomplete and an update will be sent once
     * loading is complete. Slices which contain HINT_PARTIAL will not be cached by the
     * OS and should not be cached by apps.
     */
    @Deprecated
    public static final String HINT_PARTIAL = "partial";

    /**
     * A hint to indicate that this slice represents a permission request for showing
     * slices.
     */
    @Deprecated
    public static final String HINT_PERMISSION_REQUEST = "permission_request";

    /**
     * A hint representing that this item should be used to indicate that there's more
     * content associated with this slice.
     */
    @Deprecated
    public static final String HINT_SEE_MORE = "see_more";

    /**
     * Hint indicating that this item (and its sub-items) are the current selection.
     */
    @Deprecated
    public static final String HINT_SELECTED = "selected";

    /**
     * Hint to indicate that this content should only be displayed if the slice is presented
     * as a shortcut.
     */
    @Deprecated
    public static final String HINT_SHORTCUT = "shortcut";

    /**
     * Hint indicating this content should be shown instead of the normal content when the slice
     * is in small format.
     */
    @Deprecated
    public static final String HINT_SUMMARY = "summary";

    /**
     * Hint that this content is a title of other content in the slice. This can also indicate that
     * the content should be used in the shortcut representation of the slice (icon, label, action),
     * normally this should be indicated by adding the hint on the action containing that content.
     *
     * @see SliceItem#FORMAT_ACTION
     */
    @Deprecated
    public static final String HINT_TITLE = "title";

    /**
     * Hint indicating an item representing a time-to-live for the content.
     */
    @Deprecated
    public static final String HINT_TTL = "ttl";

    /**
     * Subtype to tag an item as representing a color.
     * Expected to be on an item of format {@link SliceItem#FORMAT_INT}.
     */
    @Deprecated
    public static final String SUBTYPE_COLOR = "color";

    /**
     * Subtype to tag an item to use as a content description.
     * Expected to be on an item of format {@link SliceItem#FORMAT_TEXT}.
     */
    @Deprecated
    public static final String SUBTYPE_CONTENT_DESCRIPTION = "content_description";

    /**
     * Subtype to indicate that this item indicates the layout direction for content
     * in the slice.
     * Expected to be an item of format {@link SliceItem#FORMAT_INT}.
     */
    @Deprecated
    public static final String SUBTYPE_LAYOUT_DIRECTION = "layout_direction";

    /**
     * Subtype to tag an item as representing the max int value for a {@link #SUBTYPE_RANGE}.
     * Expected to be on an item of format {@link SliceItem#FORMAT_INT}.
     */
    @Deprecated
    public static final String SUBTYPE_MAX = "max";

    /**
     * Subtype to indicate that this is a message as part of a communication
     * sequence in this slice.
     * Expected to be on an item of format {@link SliceItem#FORMAT_SLICE}.
     */
    @Deprecated
    public static final String SUBTYPE_MESSAGE = "message";

    /**
     * Subtype to tag an item as representing a time in milliseconds since midnight,
     * January 1, 1970 UTC.
     */
    @Deprecated
    public static final String SUBTYPE_MILLIS = "millis";

    /**
     * Subtype to tag an item representing priority.
     * Expected to be on an item of format {@link SliceItem#FORMAT_INT}.
     */
    @Deprecated
    public static final String SUBTYPE_PRIORITY = "priority";

    /**
     * Subtype to tag an item as representing a range.
     * Expected to be on an item of format {@link SliceItem#FORMAT_SLICE} containing
     * a {@link #SUBTYPE_VALUE} and possibly a {@link #SUBTYPE_MAX}.
     */
    @Deprecated
    public static final String SUBTYPE_RANGE = "range";

    /**
     * Subtype to tag the source (i.e. sender) of a {@link #SUBTYPE_MESSAGE}.
     * Expected to be on an item of format {@link SliceItem#FORMAT_TEXT},
     * {@link SliceItem#FORMAT_IMAGE} or an {@link SliceItem#FORMAT_SLICE} containing them.
     */
    @Deprecated
    public static final String SUBTYPE_SOURCE = "source";

    /**
     * Subtype to indicate that this content has a toggle action associated with it. To indicate
     * that the toggle is on, use {@link #HINT_SELECTED}. When the toggle state changes, the
     * intent associated with it will be sent along with an extra {@link #EXTRA_TOGGLE_STATE}
     * which can be retrieved to see the new state of the toggle.
     */
    @Deprecated
    public static final String SUBTYPE_TOGGLE = "toggle";

    /**
     * Subtype to tag an item as representing the current int value for a {@link #SUBTYPE_RANGE}.
     * Expected to be on an item of format {@link SliceItem#FORMAT_INT}.
     */
    @Deprecated
    public static final String SUBTYPE_VALUE = "value";

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private Slice() {
    }
}
