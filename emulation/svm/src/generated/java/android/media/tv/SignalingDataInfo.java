package android.media.tv;

import android.os.Parcelable;

/**
 * Describes a metadata object of a {@link SignalingDataResponse}.
 */
public final class SignalingDataInfo implements Parcelable {
    /**
     *  A/344:2023-5 9.2.10 compliant string for when the encoding of the content is Base64.
     */
    public static final String CONTENT_ENCODING_BASE64 = "Base64";

    /**
     * The encoding of the content is UTF-8. This is the default value.
     */
    public static final String CONTENT_ENCODING_UTF_8 = "UTF-8";

    /**
     * This value for {@link #getGroup()} denotes that there's no group associated with this
     * metadata.
     */
    public static final int LLS_NO_GROUP_ID = -1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private SignalingDataInfo() {
    }
}
