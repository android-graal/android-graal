package android.view.textclassifier;

import android.os.Parcelable;

/**
 * Represents a list of actions suggested by a {@link TextClassifier} on a given conversation.
 *
 * @see TextClassifier#suggestConversationActions(Request)
 */
public final class ConversationActions implements Parcelable {
    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private ConversationActions() {
    }

    /**
     * A request object for generating conversation action suggestions.
     *
     * @see TextClassifier#suggestConversationActions(Request)
     */
    public static final class Request implements Parcelable {
        /**
         * To indicate the generated actions will be used within the app.
         */
        public static final String HINT_FOR_IN_APP = "in_app";

        /**
         * To indicate the generated actions will be used for notification.
         */
        public static final String HINT_FOR_NOTIFICATION = "notification";

        @Override
        public int describeContents() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void writeToParcel(android.os.Parcel dest, int flags) {
            throw new UnsupportedOperationException();
        }

        private Request() {
        }
    }
}
