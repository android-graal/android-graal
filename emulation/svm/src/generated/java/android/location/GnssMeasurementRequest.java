package android.location;

import android.os.Parcelable;

/**
 * This class contains extra parameters to pass in a GNSS measurement request.
 */
public final class GnssMeasurementRequest implements Parcelable {
    /**
     * Represents a passive only request. Such a request will not trigger any active GNSS
     * measurements or power usage itself, but may receive GNSS measurements generated in response
     * to other requests.
     *
     * <p class="note">Note that on Android T, such a request will trigger one GNSS measurement.
     * Another GNSS measurement will be triggered after {@link #PASSIVE_INTERVAL} and so on.
     *
     * @see GnssMeasurementRequest#getIntervalMillis()
     */
    public static final int PASSIVE_INTERVAL = 2147483647;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private GnssMeasurementRequest() {
    }
}
