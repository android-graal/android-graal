package android.telephony.emergency;

import android.os.Parcelable;

/**
 * A parcelable class that wraps and retrieves the information of number, service category(s) and
 * country code for a specific emergency number.
 */
public final class EmergencyNumber implements Parcelable {
    /**
     * Indicates the radio or IMS implementation must handle the call through emergency routing.
     */
    public static final int EMERGENCY_CALL_ROUTING_EMERGENCY = 1;

    /**
     * Indicates the radio or IMS implementation must handle the call through normal call routing.
     */
    public static final int EMERGENCY_CALL_ROUTING_NORMAL = 2;

    /**
     * Indicated the framework does not know whether an emergency call should be placed using
     * emergency or normal call routing. This means the underlying radio or IMS implementation is
     * free to determine for itself how to route the call.
     */
    public static final int EMERGENCY_CALL_ROUTING_UNKNOWN = 0;

    /**
     * Bit-field which indicates the number is from the platform-maintained database.
     */
    public static final int EMERGENCY_NUMBER_SOURCE_DATABASE = 16;

    /**
     * Bit-field which indicates the number is available as default.
     *
     * 112, 911 must always be available; additionally, 000, 08, 110, 999, 118 and 119 must be
     * available when sim is not present.
     *
     * Reference: 3gpp 22.101, Section 10 - Emergency Calls
     */
    public static final int EMERGENCY_NUMBER_SOURCE_DEFAULT = 8;

    /**
     * Bit-field which indicates the number is from the modem config.
     */
    public static final int EMERGENCY_NUMBER_SOURCE_MODEM_CONFIG = 4;

    /**
     * Bit-field which indicates the number is from the network signaling.
     *
     * Reference: 3gpp 22.101, Section 10 - Emergency Calls
     */
    public static final int EMERGENCY_NUMBER_SOURCE_NETWORK_SIGNALING = 1;

    /**
     * Bit-field which indicates the number is from the sim.
     *
     * Reference: 3gpp 22.101, Section 10 - Emergency Calls
     */
    public static final int EMERGENCY_NUMBER_SOURCE_SIM = 2;

    /**
     * Bit-field that indicates Emergency Service Category for Automatically Initiated eCall (AIeC)
     *
     * Reference: 3gpp 22.101, Section 10 - Emergency Calls
     */
    public static final int EMERGENCY_SERVICE_CATEGORY_AIEC = 64;

    /**
     * Bit-field that indicates Emergency Service Category for Ambulance.
     *
     * Reference: 3gpp 22.101, Section 10 - Emergency Calls
     */
    public static final int EMERGENCY_SERVICE_CATEGORY_AMBULANCE = 2;

    /**
     * Bit-field that indicates Emergency Service Category for Fire Brigade.
     *
     * Reference: 3gpp 22.101, Section 10 - Emergency Calls
     */
    public static final int EMERGENCY_SERVICE_CATEGORY_FIRE_BRIGADE = 4;

    /**
     * Bit-field that indicates Emergency Service Category for Marine Guard.
     *
     * Reference: 3gpp 22.101, Section 10 - Emergency Calls
     */
    public static final int EMERGENCY_SERVICE_CATEGORY_MARINE_GUARD = 8;

    /**
     * Bit-field that indicates Emergency Service Category for Manually Initiated eCall (MIeC)
     *
     * Reference: 3gpp 22.101, Section 10 - Emergency Calls
     */
    public static final int EMERGENCY_SERVICE_CATEGORY_MIEC = 32;

    /**
     * Bit-field that indicates Emergency Service Category for Mountain Rescue.
     *
     * Reference: 3gpp 22.101, Section 10 - Emergency Calls
     */
    public static final int EMERGENCY_SERVICE_CATEGORY_MOUNTAIN_RESCUE = 16;

    /**
     * Bit-field that indicates Emergency Service Category for Police.
     *
     * Reference: 3gpp 22.101, Section 10 - Emergency Calls
     */
    public static final int EMERGENCY_SERVICE_CATEGORY_POLICE = 1;

    /**
     * Emergency Service Category UNSPECIFIED (General emergency call, all categories) bit-field
     * indicates that no specific services are associated with this emergency number; if the
     * emergency number is specified, it has one or more defined emergency service categories.
     *
     * Reference: 3gpp 22.101, Section 10 - Emergency Calls
     */
    public static final int EMERGENCY_SERVICE_CATEGORY_UNSPECIFIED = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private EmergencyNumber() {
    }
}
