package android.service.controls.templates;

/**
 * A template for a temperature related {@link Control} that supports multiple modes.
 *
 * Both the current mode and the active mode for the control can be specified. The combination of
 * the {@link Control#getDeviceType} and the current and active mode will determine colors and
 * transitions for the UI element.
 */
public final class TemperatureControlTemplate {
    /**
     * Flag to indicate that the device supports cool mode.
     */
    public static final int FLAG_MODE_COOL = 8;

    /**
     * Flag to indicate that the device supports eco mode.
     */
    public static final int FLAG_MODE_ECO = 32;

    /**
     * Flag to indicate that the device supports heat mode.
     */
    public static final int FLAG_MODE_HEAT = 4;

    /**
     * Flag to indicate that the device supports heat-cool mode.
     */
    public static final int FLAG_MODE_HEAT_COOL = 16;

    /**
     * Flag to indicate that the device supports off mode.
     */
    public static final int FLAG_MODE_OFF = 2;

    /**
     * Indicates that the current or active mode of the device is set to cool.
     */
    public static final int MODE_COOL = 3;

    /**
     * Indicates that the current or active mode of the device is set to eco.
     */
    public static final int MODE_ECO = 5;

    /**
     * Indicates that the current or active mode of the device is set to heat.
     */
    public static final int MODE_HEAT = 2;

    /**
     * Indicates that the current or active mode of the device is set to heat-cool.
     */
    public static final int MODE_HEAT_COOL = 4;

    /**
     * Indicates that the current or active mode of the device is off.
     */
    public static final int MODE_OFF = 1;

    /**
     * Use when the current or active mode of the device is not known
     */
    public static final int MODE_UNKNOWN = 0;

    private TemperatureControlTemplate() {
    }
}
