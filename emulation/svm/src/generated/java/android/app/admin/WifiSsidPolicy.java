package android.app.admin;

import android.os.Parcelable;

/**
 * Used to indicate the Wi-Fi SSID restriction policy the network must satisfy
 * in order to be eligible for a connection.
 *
 * If the policy type is a denylist, the device may not connect to networks on the denylist.
 * If the policy type is an allowlist, the device may only connect to networks on the allowlist.
 * Admin configured networks are not exempt from this restriction.
 * This policy only prohibits connecting to a restricted network and
 * does not affect adding a restricted network.
 * If the current network is present in the denylist or not present in the allowlist,
 * it will be disconnected.
 */
public final class WifiSsidPolicy implements Parcelable {
    /**
     * SSID policy type indicator for {@link WifiSsidPolicy}.
     *
     * <p> When returned from {@link WifiSsidPolicy#getPolicyType()}, the constant
     * indicates that the SSID policy type is an allowlist.
     *
     * @see #WIFI_SSID_POLICY_TYPE_DENYLIST
     */
    public static final int WIFI_SSID_POLICY_TYPE_ALLOWLIST = 0;

    /**
     * SSID policy type indicator for {@link WifiSsidPolicy}.
     *
     * <p> When returned from {@link WifiSsidPolicy#getPolicyType()}, the constant
     * indicates that the SSID policy type is a denylist.
     *
     * @see #WIFI_SSID_POLICY_TYPE_ALLOWLIST
     */
    public static final int WIFI_SSID_POLICY_TYPE_DENYLIST = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private WifiSsidPolicy() {
    }
}
