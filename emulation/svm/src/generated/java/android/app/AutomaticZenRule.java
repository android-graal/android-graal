package android.app;

import android.os.Parcelable;

/**
 * Rule instance information for a zen (aka DND or Attention Management) mode.
 */
public final class AutomaticZenRule implements Parcelable {
    /**
     * The type for rules triggered by bedtime/sleeping, like time of day, or snore detection.
     *
     * <p>Only the 'Wellbeing' app may own rules of this type.
     */
    public static final int TYPE_BEDTIME = 3;

    /**
     * The type for rules triggered by driving detection, like Bluetooth connections or vehicle
     * sounds.
     */
    public static final int TYPE_DRIVING = 4;

    /**
     * The type for rules triggered by the user entering an immersive activity, like opening an app
     * using {@link WindowInsetsController#hide(int)}.
     */
    public static final int TYPE_IMMERSIVE = 5;

    /**
     * The type for rules created and managed by a device owner. These rules may not be fully
     * editable by the device user.
     *
     * <p>Only a 'Device Owner' app may own rules of this type.
     */
    public static final int TYPE_MANAGED = 7;

    /**
     * Rule is of a known type, but not one of the specific types.
     */
    public static final int TYPE_OTHER = 0;

    /**
     * The type for rules triggered by calendar events.
     */
    public static final int TYPE_SCHEDULE_CALENDAR = 2;

    /**
     * The type for rules triggered according to a time-based schedule.
     */
    public static final int TYPE_SCHEDULE_TIME = 1;

    /**
     * The type for rules that have a {@link ZenPolicy} that implies that the
     * device should not make sound and potentially hide some visual effects; may be triggered
     * when entering a location where silence is requested, like a theater.
     */
    public static final int TYPE_THEATER = 6;

    /**
     * Rule is of an unknown type. This is the default value if not provided by the owning app,
     * and the value returned if the true type was added in an API level higher than the calling
     * app's targetSdk.
     */
    public static final int TYPE_UNKNOWN = -1;

    private AutomaticZenRule() {
    }
}
