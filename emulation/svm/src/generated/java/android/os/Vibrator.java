package android.os;

/**
 * Class that operates the vibrator on the device.
 * <p>
 * If your process exits, any vibration you started will stop.
 * </p>
 */
public final class Vibrator {
    /**
     * Vibration effect support: unsupported
     *
     * <p>This effect is <b>not</b> natively supported by the underlying hardware, although
     * the system may still play a fallback vibration.
     */
    public static final int VIBRATION_EFFECT_SUPPORT_NO = 2;

    /**
     * Vibration effect support: unknown
     *
     * <p>The hardware doesn't report its supported effects, so we can't determine whether the
     * effect is supported or not.
     */
    public static final int VIBRATION_EFFECT_SUPPORT_UNKNOWN = 0;

    /**
     * Vibration effect support: supported
     *
     * <p>This effect is supported by the underlying hardware.
     */
    public static final int VIBRATION_EFFECT_SUPPORT_YES = 1;

    private Vibrator() {
    }
}
