package android.media.metrics;

import android.os.Parcelable;

/**
 * Media network event.
 */
public final class NetworkEvent implements Parcelable {
    /**
     * 2G network
     */
    public static final int NETWORK_TYPE_2G = 4;

    /**
     * 3G network
     */
    public static final int NETWORK_TYPE_3G = 5;

    /**
     * 4G network
     */
    public static final int NETWORK_TYPE_4G = 6;

    /**
     * 5G NSA network
     */
    public static final int NETWORK_TYPE_5G_NSA = 7;

    /**
     * 5G SA network
     */
    public static final int NETWORK_TYPE_5G_SA = 8;

    /**
     * Ethernet network
     */
    public static final int NETWORK_TYPE_ETHERNET = 3;

    /**
     * Not network connected
     */
    public static final int NETWORK_TYPE_OFFLINE = 9;

    /**
     * Other network type
     */
    public static final int NETWORK_TYPE_OTHER = 1;

    /**
     * Network type is not known. Default type.
     */
    public static final int NETWORK_TYPE_UNKNOWN = 0;

    /**
     * Wi-Fi network
     */
    public static final int NETWORK_TYPE_WIFI = 2;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private NetworkEvent() {
    }
}
