package android.net.wifi.aware;

import android.os.Parcelable;

/**
 * The characteristics of the Wi-Fi Aware implementation.
 */
public final class Characteristics implements Parcelable {
    /**
     * Wi-Fi Aware supported cipher suite representing NCS PK 2WDH 128: 128 bit public-key.
     */
    public static final int WIFI_AWARE_CIPHER_SUITE_NCS_PK_128 = 4;

    /**
     * Wi-Fi Aware supported cipher suite representing NCS 2WDH 256: 256 bit public-key.
     */
    public static final int WIFI_AWARE_CIPHER_SUITE_NCS_PK_256 = 8;

    /**
     * Wi-Fi Aware supported cipher suite representing NCS PASN 128: 128 bit public-key.
     */
    public static final int WIFI_AWARE_CIPHER_SUITE_NCS_PK_PASN_128 = 16;

    /**
     * Wi-Fi Aware supported cipher suite representing NCS PASN 256: 256 bit public-key.
     */
    public static final int WIFI_AWARE_CIPHER_SUITE_NCS_PK_PASN_256 = 32;

    /**
     * Wi-Fi Aware supported cipher suite representing NCS SK 128: 128 bit shared-key.
     */
    public static final int WIFI_AWARE_CIPHER_SUITE_NCS_SK_128 = 1;

    /**
     * Wi-Fi Aware supported cipher suite representing NCS SK 256: 256 bit shared-key.
     */
    public static final int WIFI_AWARE_CIPHER_SUITE_NCS_SK_256 = 2;

    /**
     * Wi-Fi Aware supported open (unencrypted) data-path.
     */
    public static final int WIFI_AWARE_CIPHER_SUITE_NONE = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private Characteristics() {
    }
}
