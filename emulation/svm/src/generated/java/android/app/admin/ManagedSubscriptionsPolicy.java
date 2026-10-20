package android.app.admin;

import android.os.Parcelable;

/**
 * A policy class that describes how managed SIM subscriptions should behave on the device.
 */
public final class ManagedSubscriptionsPolicy implements Parcelable {
    /**
     * Represents policy to have only managed subscriptions on the device, any existing and
     * future subscriptions on the device are exclusively associated with the managed profile.
     *
     * <p>When a subscription is associated with the managed profile, incoming/outgoing calls and
     * text message using that subscription would only work via apps on managed profile.
     * Also, Call logs and messages would be accessible only from the managed profile.
     */
    public static final int TYPE_ALL_MANAGED_SUBSCRIPTIONS = 1;

    /**
     * Represents default policy to not have any managed subscriptions on the device.
     */
    public static final int TYPE_ALL_PERSONAL_SUBSCRIPTIONS = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private ManagedSubscriptionsPolicy() {
    }
}
