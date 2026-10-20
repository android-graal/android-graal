package android.service.chooser;

import android.os.Parcelable;

/**
 * An event reported to a supplied [IntentSender] by the system chooser when an activity is selected
 * or other actions are taken to complete the session.
 *
 * @see Intent#EXTRA_CHOOSER_RESULT_INTENT_SENDER
 */
public final class ChooserResult implements Parcelable {
    /**
     * The session was completed by invoking the copy action.
     */
    public static final int CHOOSER_RESULT_COPY = 1;

    /**
     * The session was completed by invoking the edit action.
     */
    public static final int CHOOSER_RESULT_EDIT = 2;

    /**
     * The session was completed by selecting an activity to launch.
     */
    public static final int CHOOSER_RESULT_SELECTED_COMPONENT = 0;

    /**
     * An unknown action was taken to complete the session.
     */
    public static final int CHOOSER_RESULT_UNKNOWN = -1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private ChooserResult() {
    }
}
