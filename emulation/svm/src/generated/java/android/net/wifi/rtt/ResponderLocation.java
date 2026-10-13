package android.net.wifi.rtt;

import android.os.Parcelable;

/**
 * ResponderLocation is both a Location Configuration Information (LCI) decoder and a Location Civic
 * Report (LCR) decoder for information received from a Wi-Fi Access Point (AP) during Wi-Fi RTT
 * ranging process.
 *
 * <p>This is based on the IEEE P802.11-REVmc/D8.0 spec section 9.4.2.22, under Measurement Report
 * Element. Subelement location data-fields parsed from separate input LCI and LCR Information
 * Elements are unified in this class.</p>
 *
 * <p>Note: The information provided by this class is broadcast by a responder (usually an Access
 * Point), and passed on as-is. There is no guarantee this information is accurate or correct, and
 * as a result developers should carefully consider how this information should be used and provide
 * corresponding advice to users.</p>
 */
public final class ResponderLocation implements Parcelable {
    /**
     * Responder Altitude is measured in floors.
     */
    public static final int ALTITUDE_FLOORS = 2;

    /**
     * Responder Altitude is measured in meters.
     */
    public static final int ALTITUDE_METERS = 1;

    /**
     * Altitude is not defined for the Responder.
     * The altitude and altitude uncertainty should not be used: see section 2.4 of IETF RFC 6225.
     */
    public static final int ALTITUDE_UNDEFINED = 0;

    /**
     * Datum used is NAD83 MLLW.
     */
    public static final int DATUM_NAD83_MLLW = 3;

    /**
     * Datum used is NAD83 NAV88.
     */
    public static final int DATUM_NAD83_NAV88 = 2;

    /**
     * Datum is not defined.
     */
    public static final int DATUM_UNDEFINED = 0;

    /**
     * Datum used is WGS84.
     */
    public static final int DATUM_WGS84 = 1;

    /**
     * Version of the LCI protocol is 1.0, the only defined protocol at this time.
     */
    public static final int LCI_VERSION_1 = 1;

    /**
     * Location of responder is fixed (does not move)
     */
    public static final int LOCATION_FIXED = 0;

    /**
     * Location of the responder is not known to be either fixed or variable.
     */
    public static final int LOCATION_MOVEMENT_UNKNOWN = 2;

    /**
     * Location of the responder status is a reserved value
     */
    public static final int LOCATION_RESERVED = 3;

    /**
     * Location of the responder is variable, and may move
     */
    public static final int LOCATION_VARIABLE = 1;

    private ResponderLocation() {
    }
}
