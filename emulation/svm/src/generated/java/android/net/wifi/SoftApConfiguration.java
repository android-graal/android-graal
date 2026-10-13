package android.net.wifi;

import android.os.Parcelable;

/**
 * Configuration for a soft access point (a.k.a. Soft AP, SAP, Hotspot).
 *
 * <p>This is input for the framework provided by a client app, i.e. it exposes knobs to instruct
 * the framework how it should configure a hotspot.
 *
 * <p>System apps can use this to configure a tethered hotspot or local-only hotspot.
 *
 * <p>Instances of this class are immutable.
 */
public final class SoftApConfiguration implements Parcelable {
    /**
     * 2GHz band.
     */
    public static final int BAND_2GHZ = 1;

    /**
     * 5GHz band.
     */
    public static final int BAND_5GHZ = 2;

    /**
     * 60GHz band.
     */
    public static final int BAND_60GHZ = 8;

    /**
     * 6GHz band.
     */
    public static final int BAND_6GHZ = 4;

    /**
     * THe definition of security type OPEN.
     */
    public static final int SECURITY_TYPE_OPEN = 0;

    /**
     * The definition of security type WPA2-PSK.
     */
    public static final int SECURITY_TYPE_WPA2_PSK = 1;

    /**
     * The definition of security type WPA3-OWE.
     */
    public static final int SECURITY_TYPE_WPA3_OWE = 5;

    /**
     * The definition of security type WPA3-OWE Transition.
     */
    public static final int SECURITY_TYPE_WPA3_OWE_TRANSITION = 4;

    /**
     * The definition of security type WPA3-SAE.
     */
    public static final int SECURITY_TYPE_WPA3_SAE = 3;

    /**
     * The definition of security type WPA3-SAE Transition mode.
     */
    public static final int SECURITY_TYPE_WPA3_SAE_TRANSITION = 2;

    private SoftApConfiguration() {
    }
}
