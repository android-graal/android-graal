package android.media.tv;

import android.os.Parcelable;

/**
 * A response for DSM-CC from broadcast signal.
 */
public final class DsmccResponse implements Parcelable {
    /**
     * BIOP directory message
     */
    public static final String BIOP_MESSAGE_TYPE_DIRECTORY = "directory";

    /**
     * BIOP file message
     */
    public static final String BIOP_MESSAGE_TYPE_FILE = "file";

    /**
     * BIOP service gateway message
     */
    public static final String BIOP_MESSAGE_TYPE_SERVICE_GATEWAY = "service_gateway";

    /**
     * BIOP stream message
     */
    public static final String BIOP_MESSAGE_TYPE_STREAM = "stream";

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private DsmccResponse() {
    }
}
