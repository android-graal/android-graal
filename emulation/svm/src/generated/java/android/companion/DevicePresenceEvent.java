package android.companion;

import android.os.Parcelable;

/**
 * Event for observing device presence.
 *
 * @see CompanionDeviceManager#startObservingDevicePresence(ObservingDevicePresenceRequest)
 * @see ObservingDevicePresenceRequest.Builder#setUuid(ParcelUuid)
 * @see ObservingDevicePresenceRequest.Builder#setAssociationId(int)
 */
public final class DevicePresenceEvent implements Parcelable {
    /**
     * Companion app receives
     * {@link CompanionDeviceService#onDevicePresenceEvent(DevicePresenceEvent)} callback
     * with this event if the device comes into BLE range.
     */
    public static final int EVENT_BLE_APPEARED = 0;

    /**
     * Companion app receives
     * {@link CompanionDeviceService#onDevicePresenceEvent(DevicePresenceEvent)} callback
     * with this event if the device is no longer in BLE range.
     */
    public static final int EVENT_BLE_DISAPPEARED = 1;

    /**
     * Companion app receives
     * {@link CompanionDeviceService#onDevicePresenceEvent(DevicePresenceEvent)} callback
     * with this event when the bluetooth device is connected.
     */
    public static final int EVENT_BT_CONNECTED = 2;

    /**
     * Companion app receives
     * {@link CompanionDeviceService#onDevicePresenceEvent(DevicePresenceEvent)} callback
     * with this event if the bluetooth device is disconnected.
     */
    public static final int EVENT_BT_DISCONNECTED = 3;

    /**
     * A companion app for a self-managed device will receive the callback
     * {@link CompanionDeviceService#onDevicePresenceEvent(DevicePresenceEvent)}
     * if it reports that a device has appeared on its
     * own.
     */
    public static final int EVENT_SELF_MANAGED_APPEARED = 4;

    /**
     * A companion app for a self-managed device will receive the callback
     * {@link CompanionDeviceService#onDevicePresenceEvent(DevicePresenceEvent)} if it reports
     * that a device has disappeared on its own.
     */
    public static final int EVENT_SELF_MANAGED_DISAPPEARED = 5;

    /**
     * Indicate observing device presence base on the ParcelUuid but not association id.
     */
    public static final int NO_ASSOCIATION = -1;

    private DevicePresenceEvent() {
    }
}
