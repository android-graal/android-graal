package android.os;

/**
 * Encapsulates a collection of attributes describing information about a vibration.
 */
public final class VibrationAttributes implements Parcelable {
    /**
     * Flag requesting vibration effect to be played even under limited interruptions.
     *
     * <p>Only privileged apps can ignore user settings that limit interruptions, and this
     * flag will be ignored otherwise.
     */
    public static final int FLAG_BYPASS_INTERRUPTION_POLICY = 1;

    /**
     * Usage value to use for accessibility vibrations, such as with a screen reader.
     */
    public static final int USAGE_ACCESSIBILITY = 66;

    /**
     * Usage value to use for alarm vibrations.
     */
    public static final int USAGE_ALARM = 17;

    /**
     * Vibration usage class value to use when the vibration is initiated to catch user's
     * attention, such as alarm, ringtone, and notification vibrations.
     */
    public static final int USAGE_CLASS_ALARM = 1;

    /**
     * Vibration usage class value to use when the vibration is initiated as a response to user's
     * actions, such as emulation of physical effects, and texting feedback vibration.
     */
    public static final int USAGE_CLASS_FEEDBACK = 2;

    /**
     * Mask for vibration usage class value.
     */
    public static final int USAGE_CLASS_MASK = 15;

    /**
     * Vibration usage class value to use when the vibration is part of media, such as music, movie,
     * soundtrack, game or animations.
     */
    public static final int USAGE_CLASS_MEDIA = 3;

    /**
     * Vibration usage class value to use when the vibration usage class is unknown.
     */
    public static final int USAGE_CLASS_UNKNOWN = 0;

    /**
     * Usage value to use for vibrations which mean a request to enter/end a
     * communication with the user, such as a voice prompt.
     */
    public static final int USAGE_COMMUNICATION_REQUEST = 65;

    /**
     * Usage value to use for vibrations which provide a feedback for hardware
     * component interaction, such as a fingerprint sensor.
     */
    public static final int USAGE_HARDWARE_FEEDBACK = 50;

    /**
     * Usage value to use for media vibrations, such as music, movie, soundtrack, animations, games,
     * or any interactive media that isn't for touch feedback specifically.
     */
    public static final int USAGE_MEDIA = 19;

    /**
     * Usage value to use for notification vibrations.
     */
    public static final int USAGE_NOTIFICATION = 49;

    /**
     * Usage value to use for vibrations which emulate physical hardware reactions,
     * such as edge squeeze.
     *
     * <p>Note that normal screen-touch feedback "click" effects would typically be
     * classed as {@link #USAGE_TOUCH}, and that on-screen "physical" animations
     * like bouncing would be {@link #USAGE_MEDIA}.
     */
    public static final int USAGE_PHYSICAL_EMULATION = 34;

    /**
     * Usage value to use for ringtone vibrations.
     */
    public static final int USAGE_RINGTONE = 33;

    /**
     * Usage value to use for touch vibrations.
     *
     * <p>Most typical haptic feedback should be classed as <em>touch</em> feedback. Examples
     * include vibrations for tap, long press, drag and scroll.
     */
    public static final int USAGE_TOUCH = 18;

    /**
     * Usage value to use when usage is unknown.
     */
    public static final int USAGE_UNKNOWN = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private VibrationAttributes() {
    }
}
