package android.view;

import android.os.Parcelable;

/**
 * Holds all the relevant data for a request to {@link View#performReceiveContent}.
 */
public final class ContentInfo implements Parcelable {
    /**
     * Flag requesting that the content should be converted to plain text prior to inserting.
     */
    public static final int FLAG_CONVERT_TO_PLAIN_TEXT = 1;

    /**
     * Specifies that the operation was triggered by the app that contains the target view.
     */
    public static final int SOURCE_APP = 0;

    /**
     * Specifies that the operation was triggered by the autofill framework. See
     * https://developer.android.com/guide/topics/text/autofill for more info.
     */
    public static final int SOURCE_AUTOFILL = 4;

    /**
     * Specifies that the operation was triggered by a paste from the clipboard (e.g. "Paste" or
     * "Paste as plain text" action in the insertion/selection menu).
     */
    public static final int SOURCE_CLIPBOARD = 1;

    /**
     * Specifies that the operation was triggered by the drag/drop framework. See
     * https://developer.android.com/guide/topics/ui/drag-drop for more info.
     */
    public static final int SOURCE_DRAG_AND_DROP = 3;

    /**
     * Specifies that the operation was triggered from the soft keyboard (also known as input
     * method editor or IME). See https://developer.android.com/guide/topics/text/image-keyboard
     * for more info.
     */
    public static final int SOURCE_INPUT_METHOD = 2;

    /**
     * Specifies that the operation was triggered by a result from a
     * {@link android.content.Intent#ACTION_PROCESS_TEXT PROCESS_TEXT} action in the selection
     * menu.
     */
    public static final int SOURCE_PROCESS_TEXT = 5;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private ContentInfo() {
    }
}
