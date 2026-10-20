package android.net.wifi.aware;

import android.os.Parcelable;

/**
 * Defines the configuration of an Aware publish session. Built using
 * {@link PublishConfig.Builder}. A publish session is created using
 * {@link WifiAwareSession#publish(PublishConfig, DiscoverySessionCallback,
 * android.os.Handler)} or updated using
 * {@link PublishDiscoverySession#updatePublish(PublishConfig)}.
 */
public final class PublishConfig implements Parcelable {
    /**
     * Defines a solicited publish session - a publish session which is silent, waiting for a
     * matching active subscribe session - and responding to it in unicast. A
     * solicited publish session is paired with an active subscribe session
     * {@link SubscribeConfig#SUBSCRIBE_TYPE_ACTIVE}. Configuration is done using
     * {@link PublishConfig.Builder#setPublishType(int)}.
     */
    public static final int PUBLISH_TYPE_SOLICITED = 1;

    /**
     * Defines an unsolicited publish session - a publish session where the publisher is
     * advertising itself by broadcasting on-the-air. An unsolicited publish session is paired
     * with a passive subscribe session {@link SubscribeConfig#SUBSCRIBE_TYPE_PASSIVE}.
     * Configuration is done using {@link PublishConfig.Builder#setPublishType(int)}.
     */
    public static final int PUBLISH_TYPE_UNSOLICITED = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private PublishConfig() {
    }
}
