package android.app.admin;

import android.os.Parcelable;

/**
 * Network configuration to be set for the user profile
 * {@see DevicePolicyManager#setPreferentialNetworkServiceConfigs}.
 */
public final class PreferentialNetworkServiceConfig implements Parcelable {
    /**
     * Preferential network identifier 1.
     */
    public static final int PREFERENTIAL_NETWORK_ID_1 = 1;

    /**
     * Preferential network identifier 2.
     */
    public static final int PREFERENTIAL_NETWORK_ID_2 = 2;

    /**
     * Preferential network identifier 3.
     */
    public static final int PREFERENTIAL_NETWORK_ID_3 = 3;

    /**
     * Preferential network identifier 4.
     */
    public static final int PREFERENTIAL_NETWORK_ID_4 = 4;

    /**
     * Preferential network identifier 5.
     */
    public static final int PREFERENTIAL_NETWORK_ID_5 = 5;

    private PreferentialNetworkServiceConfig() {
    }
}
