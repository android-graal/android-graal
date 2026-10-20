package android.view.textclassifier;

import android.os.Parcelable;

/**
 * A selection event.
 * Specify index parameters as word token indices.
 */
public final class SelectionEvent implements Parcelable {
    /**
     * User abandoned the selection.
     */
    public static final int ACTION_ABANDON = 107;

    /**
     * User copied the selection.
     */
    public static final int ACTION_COPY = 101;

    /**
     * User cut the selection.
     */
    public static final int ACTION_CUT = 103;

    /**
     * User dragged+dropped the selection.
     */
    public static final int ACTION_DRAG = 106;

    /**
     * User performed an action on the selection.
     */
    public static final int ACTION_OTHER = 108;

    /**
     * User typed over the selection.
     */
    public static final int ACTION_OVERTYPE = 100;

    /**
     * User pasted over the selection.
     */
    public static final int ACTION_PASTE = 102;

    /**
     * User reset the smart selection.
     */
    public static final int ACTION_RESET = 201;

    /**
     * User activated Select All
     */
    public static final int ACTION_SELECT_ALL = 200;

    /**
     * User shared the selection.
     */
    public static final int ACTION_SHARE = 104;

    /**
     * User clicked the textAssist menu item.
     */
    public static final int ACTION_SMART_SHARE = 105;

    /**
     * Something else other than User or the default TextClassifier triggered a selection.
     */
    public static final int EVENT_AUTO_SELECTION = 5;

    /**
     * User modified an existing selection.
     */
    public static final int EVENT_SELECTION_MODIFIED = 2;

    /**
     * User started a new selection.
     */
    public static final int EVENT_SELECTION_STARTED = 1;

    /**
     * Smart selection triggered spanning multiple tokens (words).
     */
    public static final int EVENT_SMART_SELECTION_MULTI = 4;

    /**
     * Smart selection triggered for a single token (word).
     */
    public static final int EVENT_SMART_SELECTION_SINGLE = 3;

    /**
     * Selection was invoked by the user tapping on a link.
     */
    public static final int INVOCATION_LINK = 2;

    /**
     * Selection was invoked by the user long pressing, double tapping, or dragging to select.
     */
    public static final int INVOCATION_MANUAL = 1;

    /**
     * Unknown invocation method
     */
    public static final int INVOCATION_UNKNOWN = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private SelectionEvent() {
    }
}
