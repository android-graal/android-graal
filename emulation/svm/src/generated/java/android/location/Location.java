package android.location;

import android.os.Parcelable;

/**
 * A data class representing a geographic location. A location consists of a latitude, longitude,
 * timestamp, accuracy, and other information such as bearing, altitude and velocity.
 *
 * <p>All locations generated through {@link LocationManager} are guaranteed to have a valid
 * latitude, longitude, timestamp (both Unix epoch time and elapsed realtime since boot), and
 * accuracy. All other parameters are optional.
 *
 * <p class="note">Note that Android provides the ability for applications to submit "mock" or faked
 * locations through {@link LocationManager}, and that these locations can then be received by
 * applications using LocationManager to obtain location information. These locations can be
 * identified via the {@link #isMock()} API. Applications that wish to determine if a given location
 * represents the best estimate of the real position of the device as opposed to a fake location
 * coming from another application or the user should use this API. Keep in mind that the user may
 * have a good reason for mocking their location, and thus apps should generally reject mock
 * locations only when it is essential to their use case that only real locations are accepted.
 */
public final class Location implements Parcelable {
    /**
     * Constant used to specify formatting of a latitude or longitude in the form "[+-]DDD.DDDDD
     * where D indicates degrees.
     */
    public static final int FORMAT_DEGREES = 0;

    /**
     * Constant used to specify formatting of a latitude or longitude in the form "[+-]DDD:MM.MMMMM"
     * where D indicates degrees and M indicates minutes of arc (1 minute = 1/60th of a degree).
     */
    public static final int FORMAT_MINUTES = 1;

    /**
     * Constant used to specify formatting of a latitude or longitude in the form "DDD:MM:SS.SSSSS"
     * where D indicates degrees, M indicates minutes of arc, and S indicates seconds of arc (1
     * minute = 1/60th of a degree, 1 second = 1/3600th of a degree).
     */
    public static final int FORMAT_SECONDS = 2;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private Location() {
    }
}
