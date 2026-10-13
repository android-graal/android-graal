package android.os;

/**
 * A PowerMonitor represents either an ODPM rail (on-device power rail monitor) or a modeled
 * energy consumer.
 * <p/>
 * ODPM rail names are device-specific. No assumptions should be made about the names and
 * exact purpose of ODPM rails across different device models. A rail name may be something
 * like "S2S_VDD_G3D"; specific knowledge of the device hardware is required to interpret
 * the corresponding power monitor data.
 * <p/>
 * Energy consumer have more human-readable names, e.g. "GPU", "MODEM" etc. However, developers
 * must be extra cautious about using energy consumers across different device models,
 * as their exact implementations are also hardware dependent and are customized by OEMs.
 */
public final class PowerMonitor implements Parcelable {
    /**
     * Power monitor corresponding to a subsystem. The energy value may be a direct pass-through
     * power rail measurement, or modeled in some fashion.  For example, an energy consumer may
     * represent a combination of multiple rails or a portion of a rail shared between subsystems,
     * e.g. WiFi and Bluetooth are often handled by the same chip, powered by a shared rail.
     * Some consumer names are standardized, others are not.
     */
    public static final int POWER_MONITOR_TYPE_CONSUMER = 0;

    /**
     * Power monitor corresponding to a directly measured power rail. Rails are device-specific:
     * no assumptions can be made about the source of those measurements across different devices,
     * even if they have the same name.
     */
    public static final int POWER_MONITOR_TYPE_MEASUREMENT = 1;

    private PowerMonitor() {
    }
}
