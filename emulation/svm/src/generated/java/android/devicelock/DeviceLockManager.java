package android.devicelock;

/**
 * Manager used to interact with the system device lock service.
 * The device lock feature is used by special applications ('kiosk apps', downloaded and installed
 * by the device lock solution) to lock and unlock a device.
 * A typical use case is a financed device, where the financing entity has the capability to lock
 * the device in case of a missed payment.
 * When a device is locked, only a limited set of interactions with the device is allowed (for
 * example, placing emergency calls).
 * <p>
 * Use {@link android.content.Context#getSystemService(java.lang.String)}
 * with {@link Context#DEVICE_LOCK_SERVICE} to create a {@link DeviceLockManager}.
 * </p>
 */
public final class DeviceLockManager {
    /**
     * Constant representing a financed device role, returned by {@link #getKioskApps}.
     */
    public static final int DEVICE_LOCK_ROLE_FINANCING = 0;

    private DeviceLockManager() {
    }
}
