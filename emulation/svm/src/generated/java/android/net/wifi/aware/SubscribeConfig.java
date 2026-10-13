package android.net.wifi.aware;

import android.os.Parcelable;

/**
 * Defines the configuration of an Aware subscribe session. Built using
 * {@link SubscribeConfig.Builder}. Subscribe is done using
 * {@link WifiAwareSession#subscribe(SubscribeConfig, DiscoverySessionCallback,
 * android.os.Handler)} or
 * {@link SubscribeDiscoverySession#updateSubscribe(SubscribeConfig)}.
 */
public final class SubscribeConfig implements Parcelable {
    /**
     * Defines an active subscribe session - a subscribe session where
     * subscribe packets are transmitted over-the-air. Configuration is done
     * using {@link SubscribeConfig.Builder#setSubscribeType(int)}.
     */
    public static final int SUBSCRIBE_TYPE_ACTIVE = 1;

    /**
     * Defines a passive subscribe session - a subscribe session where
     * subscribe packets are not transmitted over-the-air and the device listens
     * and matches to transmitted publish packets. Configuration is done using
     * {@link SubscribeConfig.Builder#setSubscribeType(int)}.
     */
    public static final int SUBSCRIBE_TYPE_PASSIVE = 0;

    private SubscribeConfig() {
    }
}
