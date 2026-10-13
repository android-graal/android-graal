package android.os;

/**
 * The HardwarePropertiesManager class provides a mechanism of accessing hardware state of a
 * device: CPU, GPU and battery temperatures, CPU usage per core, fan speed, etc.
 */
public final class HardwarePropertiesManager {
    /**
     * Temperature of battery in Celsius.
     */
    public static final int DEVICE_TEMPERATURE_BATTERY = 2;

    /**
     * Temperature of CPUs in Celsius.
     */
    public static final int DEVICE_TEMPERATURE_CPU = 0;

    /**
     * Temperature of GPUs in Celsius.
     */
    public static final int DEVICE_TEMPERATURE_GPU = 1;

    /**
     * Temperature of device skin in Celsius.
     */
    public static final int DEVICE_TEMPERATURE_SKIN = 3;

    /**
     * Get current temperature.
     */
    public static final int TEMPERATURE_CURRENT = 0;

    /**
     * Get shutdown temperature threshold.
     */
    public static final int TEMPERATURE_SHUTDOWN = 2;

    /**
     * Get throttling temperature threshold.
     */
    public static final int TEMPERATURE_THROTTLING = 1;

    /**
     * Get throttling temperature threshold above which minimum clockrates for VR mode will not be
     * met.
     */
    public static final int TEMPERATURE_THROTTLING_BELOW_VR_MIN = 3;

    /**
     * Undefined temperature constant.
     */
    public static final float UNDEFINED_TEMPERATURE = -3.4028235E38f;

    private HardwarePropertiesManager() {
    }
}
