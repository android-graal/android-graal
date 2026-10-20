package android.adservices.customaudience;

import android.os.Parcelable;

/**
 * Represents the information necessary for a custom audience to participate in ad selection.
 *
 * <p>A custom audience is an abstract grouping of users with similar demonstrated interests. This
 * class is a collection of some data stored on a device that is necessary to serve advertisements
 * targeting a single custom audience.
 */
public final class CustomAudience implements Parcelable {
    /**
     * This auction server request flag indicates to the service that ads for this {@link
     * CustomAudience} can be omitted in the server auction payload.
     */
    public static final int FLAG_AUCTION_SERVER_REQUEST_OMIT_ADS = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private CustomAudience() {
    }
}
