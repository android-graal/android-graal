package android.view;

import android.os.Parcelable;

/**
 * Describes the capabilities of a particular input device.
 * <p>
 * Each input device may support multiple classes of input.  For example, a multi-function
 * keyboard may compose the capabilities of a standard keyboard together with a track pad mouse
 * or other pointing device.
 * </p><p>
 * Some input devices present multiple distinguishable sources of input.
 * Applications can query the framework about the characteristics of each distinct source.
 * </p><p>
 * As a further wrinkle, different kinds of input sources uses different coordinate systems
 * to describe motion events.  Refer to the comments on the input source constants for
 * the appropriate interpretation.
 * </p>
 */
public final class InputDevice implements Parcelable {
    /**
     * The keyboard supports a complement of alphabetic keys.
     */
    public static final int KEYBOARD_TYPE_ALPHABETIC = 2;

    /**
     * There is no keyboard.
     */
    public static final int KEYBOARD_TYPE_NONE = 0;

    /**
     * The keyboard is not fully alphabetic.  It may be a numeric keypad or an assortment
     * of buttons that are not mapped as alphabetic keys suitable for text input.
     */
    public static final int KEYBOARD_TYPE_NON_ALPHABETIC = 1;

    /**
     * Constant for retrieving the range of values for {@link MotionEvent#AXIS_ORIENTATION}.
     *
     * @see #getMotionRange
     * @deprecated Use {@link MotionEvent#AXIS_ORIENTATION} instead.
     */
    @Deprecated
    public static final int MOTION_RANGE_ORIENTATION = 8;

    /**
     * Constant for retrieving the range of values for {@link MotionEvent#AXIS_PRESSURE}.
     *
     * @see #getMotionRange
     * @deprecated Use {@link MotionEvent#AXIS_PRESSURE} instead.
     */
    @Deprecated
    public static final int MOTION_RANGE_PRESSURE = 2;

    /**
     * Constant for retrieving the range of values for {@link MotionEvent#AXIS_SIZE}.
     *
     * @see #getMotionRange
     * @deprecated Use {@link MotionEvent#AXIS_SIZE} instead.
     */
    @Deprecated
    public static final int MOTION_RANGE_SIZE = 3;

    /**
     * Constant for retrieving the range of values for {@link MotionEvent#AXIS_TOOL_MAJOR}.
     *
     * @see #getMotionRange
     * @deprecated Use {@link MotionEvent#AXIS_TOOL_MAJOR} instead.
     */
    @Deprecated
    public static final int MOTION_RANGE_TOOL_MAJOR = 6;

    /**
     * Constant for retrieving the range of values for {@link MotionEvent#AXIS_TOOL_MINOR}.
     *
     * @see #getMotionRange
     * @deprecated Use {@link MotionEvent#AXIS_TOOL_MINOR} instead.
     */
    @Deprecated
    public static final int MOTION_RANGE_TOOL_MINOR = 7;

    /**
     * Constant for retrieving the range of values for {@link MotionEvent#AXIS_TOUCH_MAJOR}.
     *
     * @see #getMotionRange
     * @deprecated Use {@link MotionEvent#AXIS_TOUCH_MAJOR} instead.
     */
    @Deprecated
    public static final int MOTION_RANGE_TOUCH_MAJOR = 4;

    /**
     * Constant for retrieving the range of values for {@link MotionEvent#AXIS_TOUCH_MINOR}.
     *
     * @see #getMotionRange
     * @deprecated Use {@link MotionEvent#AXIS_TOUCH_MINOR} instead.
     */
    @Deprecated
    public static final int MOTION_RANGE_TOUCH_MINOR = 5;

    /**
     * Constant for retrieving the range of values for {@link MotionEvent#AXIS_X}.
     *
     * @see #getMotionRange
     * @deprecated Use {@link MotionEvent#AXIS_X} instead.
     */
    @Deprecated
    public static final int MOTION_RANGE_X = 0;

    /**
     * Constant for retrieving the range of values for {@link MotionEvent#AXIS_Y}.
     *
     * @see #getMotionRange
     * @deprecated Use {@link MotionEvent#AXIS_Y} instead.
     */
    @Deprecated
    public static final int MOTION_RANGE_Y = 1;

    /**
     * A special input source constant that is used when filtering input devices
     * to match devices that provide any type of input source.
     */
    public static final int SOURCE_ANY = -256;

    /**
     * The input device is a Bluetooth stylus.
     * <p>
     * Note that this bit merely indicates that an input device is capable of
     * obtaining input from a Bluetooth stylus.  To determine whether a given
     * touch event was produced by a stylus, examine the tool type returned by
     * {@link MotionEvent#getToolType(int)} for each individual pointer.
     * </p><p>
     * A single touch event may multiple pointers with different tool types,
     * such as an event that has one pointer with tool type
     * {@link MotionEvent#TOOL_TYPE_FINGER} and another pointer with tool type
     * {@link MotionEvent#TOOL_TYPE_STYLUS}.  So it is important to examine
     * the tool type of each pointer, regardless of the source reported
     * by {@link MotionEvent#getSource()}.
     * </p><p>
     * A bluetooth stylus generally receives its pressure and button state
     * information from the stylus itself, and derives the rest from another
     * source. For example, a Bluetooth stylus used in conjunction with a
     * touchscreen would derive its contact position and pointer size from the
     * touchscreen and may not be any more accurate than other tools such as
     * fingers.
     * </p>
     *
     * @see #SOURCE_STYLUS
     * @see #SOURCE_CLASS_POINTER
     */
    public static final int SOURCE_BLUETOOTH_STYLUS = 49154;

    /**
     * The input source has buttons or keys.
     * Examples: {@link #SOURCE_KEYBOARD}, {@link #SOURCE_DPAD}.
     *
     * A {@link KeyEvent} should be interpreted as a button or key press.
     *
     * Use {@link #getKeyCharacterMap} to query the device's button and key mappings.
     */
    public static final int SOURCE_CLASS_BUTTON = 1;

    /**
     * The input source is a joystick.
     *
     * A {@link MotionEvent} should be interpreted as absolute joystick movements.
     *
     * Use {@link #getMotionRange} to query the range of positions.
     */
    public static final int SOURCE_CLASS_JOYSTICK = 16;

    /**
     * A mask for input source classes.
     *
     * Each distinct input source constant has one or more input source class bits set to
     * specify the desired interpretation for its input events.
     */
    public static final int SOURCE_CLASS_MASK = 255;

    /**
     * The input source has no class.
     *
     * It is up to the application to determine how to handle the device based on the device type.
     */
    public static final int SOURCE_CLASS_NONE = 0;

    /**
     * The input source is a pointing device associated with a display.
     * Examples: {@link #SOURCE_TOUCHSCREEN}, {@link #SOURCE_MOUSE}.
     *
     * A {@link MotionEvent} should be interpreted as absolute coordinates in
     * display units according to the {@link View} hierarchy.  Pointer down/up indicated when
     * the finger touches the display or when the selection button is pressed/released.
     *
     * Use {@link #getMotionRange} to query the range of the pointing device.  Some devices permit
     * touches outside the display area so the effective range may be somewhat smaller or larger
     * than the actual display size.
     */
    public static final int SOURCE_CLASS_POINTER = 2;

    /**
     * The input source is an absolute positioning device not associated with a display
     * (unlike {@link #SOURCE_CLASS_POINTER}).
     *
     * A {@link MotionEvent} should be interpreted as absolute coordinates in
     * device-specific surface units.
     *
     * Use {@link #getMotionRange} to query the range of positions.
     */
    public static final int SOURCE_CLASS_POSITION = 8;

    /**
     * The input source is a trackball navigation device.
     * Examples: {@link #SOURCE_TRACKBALL}.
     *
     * A {@link MotionEvent} should be interpreted as relative movements in device-specific
     * units used for navigation purposes.  Pointer down/up indicates when the selection button
     * is pressed/released.
     *
     * Use {@link #getMotionRange} to query the range of motion.
     */
    public static final int SOURCE_CLASS_TRACKBALL = 4;

    /**
     * The input source is a DPad.
     *
     * @see #SOURCE_CLASS_BUTTON
     */
    public static final int SOURCE_DPAD = 513;

    /**
     * The input source is a game pad.
     * (It may also be a {@link #SOURCE_JOYSTICK}).
     *
     * @see #SOURCE_CLASS_BUTTON
     */
    public static final int SOURCE_GAMEPAD = 1025;

    /**
     * The input source is a device connected through HDMI-based bus.
     *
     * The key comes in through HDMI-CEC or MHL signal line, and is treated as if it were
     * generated by a locally connected DPAD or keyboard.
     */
    public static final int SOURCE_HDMI = 33554433;

    /**
     * The input source is a joystick.
     * (It may also be a {@link #SOURCE_GAMEPAD}).
     *
     * @see #SOURCE_CLASS_JOYSTICK
     */
    public static final int SOURCE_JOYSTICK = 16777232;

    /**
     * The input source is a keyboard.
     *
     * This source indicates pretty much anything that has buttons.  Use
     * {@link #getKeyboardType()} to determine whether the keyboard has alphabetic keys
     * and can be used to enter text.
     *
     * @see #SOURCE_CLASS_BUTTON
     */
    public static final int SOURCE_KEYBOARD = 257;

    /**
     * The input source is a mouse pointing device.
     * This value is also used for other mouse-like pointing devices such as touchpads and pointing
     * sticks. When used in combination with {@link #SOURCE_STYLUS}, it denotes an external drawing
     * tablet.
     *
     * @see #SOURCE_CLASS_POINTER
     */
    public static final int SOURCE_MOUSE = 8194;

    /**
     * The input source is a mouse device whose relative motions should be interpreted as
     * navigation events.
     *
     * @see #SOURCE_CLASS_TRACKBALL
     */
    public static final int SOURCE_MOUSE_RELATIVE = 131076;

    /**
     * The input source is a rotating encoder device whose motions should be interpreted as akin to
     * those of a scroll wheel.
     *
     * @see #SOURCE_CLASS_NONE
     */
    public static final int SOURCE_ROTARY_ENCODER = 4194304;

    /**
     * The input source is a sensor associated with the input device.
     *
     * @see #SOURCE_CLASS_NONE
     */
    public static final int SOURCE_SENSOR = 67108864;

    /**
     * The input source is a stylus pointing device.
     * <p>
     * Note that this bit merely indicates that an input device is capable of obtaining
     * input from a stylus.  To determine whether a given touch event was produced
     * by a stylus, examine the tool type returned by {@link MotionEvent#getToolType(int)}
     * for each individual pointer.
     * </p><p>
     * A single touch event may multiple pointers with different tool types,
     * such as an event that has one pointer with tool type
     * {@link MotionEvent#TOOL_TYPE_FINGER} and another pointer with tool type
     * {@link MotionEvent#TOOL_TYPE_STYLUS}.  So it is important to examine
     * the tool type of each pointer, regardless of the source reported
     * by {@link MotionEvent#getSource()}.
     * </p>
     *
     * @see #SOURCE_CLASS_POINTER
     */
    public static final int SOURCE_STYLUS = 16386;

    /**
     * The input source is a touchpad (also known as a trackpad). Touchpads that are used to move
     * the mouse cursor will also have {@link #SOURCE_MOUSE}.
     *
     * @see #SOURCE_CLASS_POSITION
     */
    public static final int SOURCE_TOUCHPAD = 1048584;

    /**
     * The input source is a touch screen pointing device.
     *
     * @see #SOURCE_CLASS_POINTER
     */
    public static final int SOURCE_TOUCHSCREEN = 4098;

    /**
     * The input source is a touch device whose motions should be interpreted as navigation events.
     *
     * For example, an upward swipe should be as an upward focus traversal in the same manner as
     * pressing up on a D-Pad would be. Swipes to the left, right and down should be treated in a
     * similar manner.
     *
     * @see #SOURCE_CLASS_NONE
     */
    public static final int SOURCE_TOUCH_NAVIGATION = 2097152;

    /**
     * The input source is a trackball.
     *
     * @see #SOURCE_CLASS_TRACKBALL
     */
    public static final int SOURCE_TRACKBALL = 65540;

    /**
     * The input source is unknown.
     */
    public static final int SOURCE_UNKNOWN = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private InputDevice() {
    }
}
