package android.location;

import android.os.Parcelable;

/**
 * A class implementing a container for data associated with a measurement event.
 * Events are delivered to registered instances of {@link Callback}.
 */
public final class GnssMeasurementsEvent implements Parcelable {
    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private GnssMeasurementsEvent() {
    }

    /**
     * Used for receiving GNSS satellite measurements from the GNSS engine.
     * Each measurement contains raw and computed data identifying a satellite.
     * You can implement this interface and call
     * {@link LocationManager#registerGnssMeasurementsCallback}.
     */
    public static final class Callback {
        /**
         * GPS provider or Location is disabled, updates will not be received until they are
         * enabled.
         *
         * @deprecated Do not use.
         */
        @Deprecated
        public static final int STATUS_LOCATION_DISABLED = 2;

        /**
         * The client is not allowed to register for GNSS Measurements in general or in the
         * requested mode.
         *
         * <p>Such a status is returned when a client tries to request a functionality from the GNSS
         * chipset while another client has an ongoing request that does not allow such
         * functionality to be performed.
         *
         * <p>If such a status is received, one would try again at a later time point where no
         * other client is having a conflicting request.
         *
         * @deprecated Do not use.
         */
        @Deprecated
        public static final int STATUS_NOT_ALLOWED = 3;

        /**
         * The system does not support tracking of GNSS Measurements.
         *
         * <p>This status will not change in the future.
         *
         * @deprecated Do not use.
         */
        @Deprecated
        public static final int STATUS_NOT_SUPPORTED = 0;

        /**
         * GNSS Measurements are successfully being tracked, it will receive updates once they are
         * available.
         *
         * @deprecated Do not use.
         */
        @Deprecated
        public static final int STATUS_READY = 1;

        private Callback() {
        }
    }
}
