package android.hardware.lights;

import android.os.Parcelable;

/**
 * Represents a logical light on the device.
 */
public final class Light implements Parcelable {
    /**
     * Capability for lights that could adjust its LED brightness. If the capability is not present
     * the LED can only be turned either on or off.
     */
    public static final int LIGHT_CAPABILITY_BRIGHTNESS = 1;

    /**
     * Capability for lights that have red, green and blue LEDs to control the light's color.
     */
    public static final int LIGHT_CAPABILITY_COLOR_RGB = 2;

    /**
     * Capability for lights that have red, green and blue LEDs to control the light's color.
     *
     * @deprecated Wrong int based flag with value 0. Use capability flag {@code
     * LIGHT_CAPABILITY_COLOR_RGB} instead.
     */
    @Deprecated
    public static final int LIGHT_CAPABILITY_RGB = 0;

    /**
     * Type for lights that indicate a monochrome color LED light.
     */
    public static final int LIGHT_TYPE_INPUT = 10001;

    /**
     * Type for lights that illuminate keyboard keys.
     */
    public static final int LIGHT_TYPE_KEYBOARD_BACKLIGHT = 10003;

    /**
     * Type for lights that indicate microphone usage
     */
    public static final int LIGHT_TYPE_MICROPHONE = 8;

    /**
     * Type for lights that indicate a group of LED lights representing player id.
     * Player id lights normally present on game controllers are lights that consist of a row of
     * LEDs.
     * During multi-player game, the player id for the current game controller is represented by
     * one of the LED that is lit according to its position in the row.
     */
    public static final int LIGHT_TYPE_PLAYER_ID = 10002;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private Light() {
    }
}
