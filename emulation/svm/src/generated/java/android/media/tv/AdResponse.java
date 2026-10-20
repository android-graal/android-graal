package android.media.tv;

import android.os.Parcelable;

/**
 * An advertisement response which can be sent to TV interactive App service to inform AD status.
 */
public final class AdResponse implements Parcelable {
    public static final int RESPONSE_TYPE_BUFFERING = 5;

    public static final int RESPONSE_TYPE_ERROR = 4;

    public static final int RESPONSE_TYPE_FINISHED = 2;

    public static final int RESPONSE_TYPE_PLAYING = 1;

    public static final int RESPONSE_TYPE_STOPPED = 3;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private AdResponse() {
    }
}
