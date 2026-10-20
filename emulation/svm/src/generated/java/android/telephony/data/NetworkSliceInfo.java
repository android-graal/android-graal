package android.telephony.data;

import android.os.Parcelable;

/**
 * Represents a S-NSSAI as defined in 3GPP TS 24.501, which represents a network slice.
 *
 * There are 2 main fields that define a slice, SliceServiceType and SliceDifferentiator.
 * SliceServiceType defines the type of service provided by the slice, and SliceDifferentiator is
 * used to differentiate between multiple slices of the same type. If the devices is not on HPLMN,
 * the mappedHplmn versions of these 2 fields indicate the corresponding values in HPLMN.
 */
public final class NetworkSliceInfo implements Parcelable {
    /**
     * When set on a Slice Differentiator, this value indicates that there is no corresponding
     * Slice.
     */
    public static final int SLICE_DIFFERENTIATOR_NO_SLICE = -1;

    /**
     *  Slice suitable for the handling of 5G enhanced Mobile Broadband.
     */
    public static final int SLICE_SERVICE_TYPE_EMBB = 1;

    /**
     * Slice suitable for the handling of massive IoT.
     */
    public static final int SLICE_SERVICE_TYPE_MIOT = 3;

    /**
     *  Indicates that the service type is not present.
     */
    public static final int SLICE_SERVICE_TYPE_NONE = 0;

    /**
     * Slice suitable for the handling of ultra-reliable low latency communications.
     */
    public static final int SLICE_SERVICE_TYPE_URLLC = 2;

    /**
     * The slice is allowed to be used.
     */
    public static final int SLICE_STATUS_ALLOWED = 2;

    /**
     * The slice is configured but not allowed or rejected yet.
     */
    public static final int SLICE_STATUS_CONFIGURED = 1;

    /**
     * The slice is configured by home operator(HPLMN) in default and is used if configured/allowed
     * slices are not available for the serving PLMN.
     */
    public static final int SLICE_STATUS_DEFAULT_CONFIGURED = 5;

    /**
     * The slice is rejected because not available in PLMN.
     */
    public static final int SLICE_STATUS_REJECTED_NOT_AVAILABLE_IN_PLMN = 3;

    /**
     * The slice is rejected because not available in registered area.
     */
    public static final int SLICE_STATUS_REJECTED_NOT_AVAILABLE_IN_REGISTERED_AREA = 4;

    /**
     * The slice status is unknown. This can happen during IWLAN->cellular handover when the
     * NetworkSliceInfo is received over IWLAN.
     */
    public static final int SLICE_STATUS_UNKNOWN = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private NetworkSliceInfo() {
    }
}
