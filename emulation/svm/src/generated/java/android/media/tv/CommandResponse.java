package android.media.tv;

import android.os.Parcelable;

/**
 * A response for command from broadcast signal.
 */
public final class CommandResponse implements Parcelable {
    public static final String RESPONSE_TYPE_JSON = "json";

    public static final String RESPONSE_TYPE_XML = "xml";

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private CommandResponse() {
    }
}
