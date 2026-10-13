package android.view;

import android.os.Parcelable;

/**
 * Object used to report movement (mouse, pen, finger, trackball) events.
 * Motion events may hold either absolute or relative movements and other data,
 * depending on the type of device.
 *
 * <h3>Overview</h3>
 * <p>
 * Motion events describe movements in terms of an action code and a set of axis values.
 * The action code specifies the state change that occurred such as a pointer going
 * down or up.  The axis values describe the position and other movement properties.
 * </p><p>
 * For example, when the user first touches the screen, the system delivers a touch
 * event to the appropriate {@link View} with the action code {@link #ACTION_DOWN}
 * and a set of axis values that include the X and Y coordinates of the touch and
 * information about the pressure, size and orientation of the contact area.
 * </p><p>
 * Some devices can report multiple movement traces at the same time.  Multi-touch
 * screens emit one movement trace for each finger.  The individual fingers or
 * other objects that generate movement traces are referred to as <em>pointers</em>.
 * Motion events contain information about all of the pointers that are currently active
 * even if some of them have not moved since the last event was delivered.
 * </p><p>
 * The number of pointers only ever changes by one as individual pointers go up and down,
 * except when the gesture is canceled.
 * </p><p>
 * Each pointer has a unique id that is assigned when it first goes down
 * (indicated by {@link #ACTION_DOWN} or {@link #ACTION_POINTER_DOWN}).  A pointer id
 * remains valid until the pointer eventually goes up (indicated by {@link #ACTION_UP}
 * or {@link #ACTION_POINTER_UP}) or when the gesture is canceled (indicated by
 * {@link #ACTION_CANCEL}).
 * </p><p>
 * The MotionEvent class provides many methods to query the position and other properties of
 * pointers, such as {@link #getX(int)}, {@link #getY(int)}, {@link #getAxisValue},
 * {@link #getPointerId(int)}, {@link #getToolType(int)}, and many others.  Most of these
 * methods accept the pointer index as a parameter rather than the pointer id.
 * The pointer index of each pointer in the event ranges from 0 to one less than the value
 * returned by {@link #getPointerCount()}.
 * </p><p>
 * The order in which individual pointers appear within a motion event is undefined.
 * Thus the pointer index of a pointer can change from one event to the next but
 * the pointer id of a pointer is guaranteed to remain constant as long as the pointer
 * remains active.  Use the {@link #getPointerId(int)} method to obtain the
 * pointer id of a pointer to track it across all subsequent motion events in a gesture.
 * Then for successive motion events, use the {@link #findPointerIndex(int)} method
 * to obtain the pointer index for a given pointer id in that motion event.
 * </p><p>
 * Mouse and stylus buttons can be retrieved using {@link #getButtonState()}.  It is a
 * good idea to check the button state while handling {@link #ACTION_DOWN} as part
 * of a touch event.  The application may choose to perform some different action
 * if the touch event starts due to a secondary button click, such as presenting a
 * context menu.
 * </p>
 *
 * <h3>Batching</h3>
 * <p>
 * For efficiency, motion events with {@link #ACTION_MOVE} may batch together
 * multiple movement samples within a single object.  The most current
 * pointer coordinates are available using {@link #getX(int)} and {@link #getY(int)}.
 * Earlier coordinates within the batch are accessed using {@link #getHistoricalX(int, int)}
 * and {@link #getHistoricalY(int, int)}.  The coordinates are "historical" only
 * insofar as they are older than the current coordinates in the batch; however,
 * they are still distinct from any other coordinates reported in prior motion events.
 * To process all coordinates in the batch in time order, first consume the historical
 * coordinates then consume the current coordinates.
 * </p><p>
 * Example: Consuming all samples for all pointers in a motion event in time order.
 * </p><p><pre><code>
 * void printSamples(MotionEvent ev) {
 *     final int historySize = ev.getHistorySize();
 *     final int pointerCount = ev.getPointerCount();
 *     for (int h = 0; h &lt; historySize; h++) {
 *         System.out.printf("At time %d:", ev.getHistoricalEventTime(h));
 *         for (int p = 0; p &lt; pointerCount; p++) {
 *             System.out.printf("  pointer %d: (%f,%f)",
 *                 ev.getPointerId(p), ev.getHistoricalX(p, h), ev.getHistoricalY(p, h));
 *         }
 *     }
 *     System.out.printf("At time %d:", ev.getEventTime());
 *     for (int p = 0; p &lt; pointerCount; p++) {
 *         System.out.printf("  pointer %d: (%f,%f)",
 *             ev.getPointerId(p), ev.getX(p), ev.getY(p));
 *     }
 * }
 * </code></pre></p><p>
 * Developers should keep in mind that it is especially important to consume all samples
 * in a batched event when processing relative values that report changes since the last
 * event or sample. Examples of such relative axes include {@link #AXIS_RELATIVE_X},
 * {@link #AXIS_RELATIVE_Y}, and many of the axes prefixed with {@code AXIS_GESTURE_}.
 * In these cases, developers should first consume all historical values using
 * {@link #getHistoricalAxisValue(int, int)} and then consume the current values using
 * {@link #getAxisValue(int)} like in the example above, as these relative values are
 * not accumulated in a batched event.
 * </p>
 *
 * <h3>Device Types</h3>
 * <p>
 * The interpretation of the contents of a MotionEvent varies significantly depending
 * on the source class of the device.
 * </p><p>
 * On pointing devices with source class {@link InputDevice#SOURCE_CLASS_POINTER}
 * such as touch screens, the pointer coordinates specify absolute
 * positions such as view X/Y coordinates.  Each complete gesture is represented
 * by a sequence of motion events with actions that describe pointer state transitions
 * and movements.  A gesture starts with a motion event with {@link #ACTION_DOWN}
 * that provides the location of the first pointer down.  As each additional
 * pointer that goes down or up, the framework will generate a motion event with
 * {@link #ACTION_POINTER_DOWN} or {@link #ACTION_POINTER_UP} accordingly.
 * Pointer movements are described by motion events with {@link #ACTION_MOVE}.
 * Finally, a gesture end either when the final pointer goes up as represented
 * by a motion event with {@link #ACTION_UP} or when gesture is canceled
 * with {@link #ACTION_CANCEL}.
 * </p><p>
 * Some pointing devices such as mice may support vertical and/or horizontal scrolling.
 * A scroll event is reported as a generic motion event with {@link #ACTION_SCROLL} that
 * includes the relative scroll offset in the {@link #AXIS_VSCROLL} and
 * {@link #AXIS_HSCROLL} axes.  See {@link #getAxisValue(int)} for information
 * about retrieving these additional axes.
 * </p><p>
 * On trackball devices with source class {@link InputDevice#SOURCE_CLASS_TRACKBALL},
 * the pointer coordinates specify relative movements as X/Y deltas.
 * A trackball gesture consists of a sequence of movements described by motion
 * events with {@link #ACTION_MOVE} interspersed with occasional {@link #ACTION_DOWN}
 * or {@link #ACTION_UP} motion events when the trackball button is pressed or released.
 * </p><p>
 * On joystick devices with source class {@link InputDevice#SOURCE_CLASS_JOYSTICK},
 * the pointer coordinates specify the absolute position of the joystick axes.
 * The joystick axis values are normalized to a range of -1.0 to 1.0 where 0.0 corresponds
 * to the center position.  More information about the set of available axes and the
 * range of motion can be obtained using {@link InputDevice#getMotionRange}.
 * Some common joystick axes are {@link #AXIS_X}, {@link #AXIS_Y},
 * {@link #AXIS_HAT_X}, {@link #AXIS_HAT_Y}, {@link #AXIS_Z} and {@link #AXIS_RZ}.
 * </p><p>
 * Refer to {@link InputDevice} for more information about how different kinds of
 * input devices and sources represent pointer coordinates.
 * </p>
 *
 * <h3>Consistency Guarantees</h3>
 * <p>
 * Motion events are always delivered to views as a consistent stream of events.
 * What constitutes a consistent stream varies depending on the type of device.
 * For touch events, consistency implies that pointers go down one at a time,
 * move around as a group and then go up one at a time or are canceled.
 * </p><p>
 * While the framework tries to deliver consistent streams of motion events to
 * views, it cannot guarantee it.  Some events may be dropped or modified by
 * containing views in the application before they are delivered thereby making
 * the stream of events inconsistent.  Views should always be prepared to
 * handle {@link #ACTION_CANCEL} and should tolerate anomalous
 * situations such as receiving a new {@link #ACTION_DOWN} without first having
 * received an {@link #ACTION_UP} for the prior gesture.
 * </p>
 */
public final class MotionEvent implements Parcelable {
    /**
     * Constant for {@link #getActionMasked}: A button has been pressed.
     *
     * <p>
     * Use {@link #getActionButton()} to get which button was pressed.
     * </p><p>
     * This action is not a touch event so it is delivered to
     * {@link View#onGenericMotionEvent(MotionEvent)} rather than
     * {@link View#onTouchEvent(MotionEvent)}.
     * </p>
     */
    public static final int ACTION_BUTTON_PRESS = 11;

    /**
     * Constant for {@link #getActionMasked}: A button has been released.
     *
     * <p>
     * Use {@link #getActionButton()} to get which button was released.
     * </p><p>
     * This action is not a touch event so it is delivered to
     * {@link View#onGenericMotionEvent(MotionEvent)} rather than
     * {@link View#onTouchEvent(MotionEvent)}.
     * </p>
     */
    public static final int ACTION_BUTTON_RELEASE = 12;

    /**
     * Constant for {@link #getActionMasked}: The current gesture has been aborted.
     * You will not receive any more points in it.  You should treat this as
     * an up event, but not perform any action that you normally would.
     */
    public static final int ACTION_CANCEL = 3;

    /**
     * Constant for {@link #getActionMasked}: A pressed gesture has started, the
     * motion contains the initial starting location.
     * <p>
     * This is also a good time to check the button state to distinguish
     * secondary and tertiary button clicks and handle them appropriately.
     * Use {@link #getButtonState} to retrieve the button state.
     * </p>
     */
    public static final int ACTION_DOWN = 0;

    /**
     * Constant for {@link #getActionMasked}: The pointer is not down but has entered the
     * boundaries of a window or view.
     * <p>
     * This action is always delivered to the window or view under the pointer.
     * </p><p>
     * This action is not a touch event so it is delivered to
     * {@link View#onGenericMotionEvent(MotionEvent)} rather than
     * {@link View#onTouchEvent(MotionEvent)}.
     * </p>
     */
    public static final int ACTION_HOVER_ENTER = 9;

    /**
     * Constant for {@link #getActionMasked}: The pointer is not down but has exited the
     * boundaries of a window or view.
     * <p>
     * This action is always delivered to the window or view that was previously under the pointer.
     * </p><p>
     * This action is not a touch event so it is delivered to
     * {@link View#onGenericMotionEvent(MotionEvent)} rather than
     * {@link View#onTouchEvent(MotionEvent)}.
     * </p>
     */
    public static final int ACTION_HOVER_EXIT = 10;

    /**
     * Constant for {@link #getActionMasked}: A change happened but the pointer
     * is not down (unlike {@link #ACTION_MOVE}).  The motion contains the most
     * recent point, as well as any intermediate points since the last
     * hover move event.
     * <p>
     * This action is always delivered to the window or view under the pointer.
     * </p><p>
     * This action is not a touch event so it is delivered to
     * {@link View#onGenericMotionEvent(MotionEvent)} rather than
     * {@link View#onTouchEvent(MotionEvent)}.
     * </p>
     */
    public static final int ACTION_HOVER_MOVE = 7;

    /**
     * Bit mask of the parts of the action code that are the action itself.
     */
    public static final int ACTION_MASK = 255;

    /**
     * Constant for {@link #getActionMasked}: A change has happened during a
     * press gesture (between {@link #ACTION_DOWN} and {@link #ACTION_UP}).
     * The motion contains the most recent point, as well as any intermediate
     * points since the last down or move event.
     */
    public static final int ACTION_MOVE = 2;

    /**
     * Constant for {@link #getActionMasked}: A movement has happened outside of the
     * normal bounds of the UI element.  This does not provide a full gesture,
     * but only the initial location of the movement/touch.
     * <p>
     * Note: Because the location of any event will be outside the
     * bounds of the view hierarchy, it will not get dispatched to
     * any children of a ViewGroup by default. Therefore,
     * movements with ACTION_OUTSIDE should be handled in either the
     * root {@link View} or in the appropriate {@link Window.Callback}
     * (e.g. {@link android.app.Activity} or {@link android.app.Dialog}).
     * </p>
     */
    public static final int ACTION_OUTSIDE = 4;

    /**
     * @deprecated Use {@link #ACTION_POINTER_INDEX_MASK} to retrieve the
     * data index associated with {@link #ACTION_POINTER_DOWN}.
     */
    @Deprecated
    public static final int ACTION_POINTER_1_DOWN = 5;

    /**
     * @deprecated Use {@link #ACTION_POINTER_INDEX_MASK} to retrieve the
     * data index associated with {@link #ACTION_POINTER_UP}.
     */
    @Deprecated
    public static final int ACTION_POINTER_1_UP = 6;

    /**
     * @deprecated Use {@link #ACTION_POINTER_INDEX_MASK} to retrieve the
     * data index associated with {@link #ACTION_POINTER_DOWN}.
     */
    @Deprecated
    public static final int ACTION_POINTER_2_DOWN = 261;

    /**
     * @deprecated Use {@link #ACTION_POINTER_INDEX_MASK} to retrieve the
     * data index associated with {@link #ACTION_POINTER_UP}.
     */
    @Deprecated
    public static final int ACTION_POINTER_2_UP = 262;

    /**
     * @deprecated Use {@link #ACTION_POINTER_INDEX_MASK} to retrieve the
     * data index associated with {@link #ACTION_POINTER_DOWN}.
     */
    @Deprecated
    public static final int ACTION_POINTER_3_DOWN = 517;

    /**
     * @deprecated Use {@link #ACTION_POINTER_INDEX_MASK} to retrieve the
     * data index associated with {@link #ACTION_POINTER_UP}.
     */
    @Deprecated
    public static final int ACTION_POINTER_3_UP = 518;

    /**
     * Constant for {@link #getActionMasked}: A non-primary pointer has gone down.
     * <p>
     * Use {@link #getActionIndex} to retrieve the index of the pointer that changed.
     * </p><p>
     * The index is encoded in the {@link #ACTION_POINTER_INDEX_MASK} bits of the
     * unmasked action returned by {@link #getAction}.
     * </p>
     */
    public static final int ACTION_POINTER_DOWN = 5;

    /**
     * @deprecated Renamed to {@link #ACTION_POINTER_INDEX_MASK} to match
     * the actual data contained in these bits.
     */
    @Deprecated
    public static final int ACTION_POINTER_ID_MASK = 65280;

    /**
     * @deprecated Renamed to {@link #ACTION_POINTER_INDEX_SHIFT} to match
     * the actual data contained in these bits.
     */
    @Deprecated
    public static final int ACTION_POINTER_ID_SHIFT = 8;

    /**
     * Bits in the action code that represent a pointer index, used with
     * {@link #ACTION_POINTER_DOWN} and {@link #ACTION_POINTER_UP}.  Shifting
     * down by {@link #ACTION_POINTER_INDEX_SHIFT} provides the actual pointer
     * index where the data for the pointer going up or down can be found; you can
     * get its identifier with {@link #getPointerId(int)} and the actual
     * data with {@link #getX(int)} etc.
     *
     * @see #getActionIndex
     */
    public static final int ACTION_POINTER_INDEX_MASK = 65280;

    /**
     * Bit shift for the action bits holding the pointer index as
     * defined by {@link #ACTION_POINTER_INDEX_MASK}.
     *
     * @see #getActionIndex
     */
    public static final int ACTION_POINTER_INDEX_SHIFT = 8;

    /**
     * Constant for {@link #getActionMasked}: A non-primary pointer has gone up.
     * <p>
     * Use {@link #getActionIndex} to retrieve the index of the pointer that changed.
     * </p><p>
     * The index is encoded in the {@link #ACTION_POINTER_INDEX_MASK} bits of the
     * unmasked action returned by {@link #getAction}.
     * </p>
     */
    public static final int ACTION_POINTER_UP = 6;

    /**
     * Constant for {@link #getActionMasked}: The motion event contains relative
     * vertical and/or horizontal scroll offsets.  Use {@link #getAxisValue(int)}
     * to retrieve the information from {@link #AXIS_VSCROLL} and {@link #AXIS_HSCROLL}.
     * The pointer may or may not be down when this event is dispatched.
     * <p>
     * This action is always delivered to the window or view under the pointer, which
     * may not be the window or view currently touched.
     * </p><p>
     * This action is not a touch event so it is delivered to
     * {@link View#onGenericMotionEvent(MotionEvent)} rather than
     * {@link View#onTouchEvent(MotionEvent)}.
     * </p>
     */
    public static final int ACTION_SCROLL = 8;

    /**
     * Constant for {@link #getActionMasked}: A pressed gesture has finished, the
     * motion contains the final release location as well as any intermediate
     * points since the last down or move event.
     */
    public static final int ACTION_UP = 1;

    /**
     * Axis constant: Brake axis of a motion event.
     * <p>
     * <ul>
     * <li>For a joystick, reports the absolute position of the brake control.
     * The value is normalized to a range from 0.0 (no braking) to 1.0 (maximum braking).
     * </ul>
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_BRAKE = 23;

    /**
     * Axis constant: Distance axis of a motion event.
     * <p>
     * <ul>
     * <li>For a stylus, reports the distance of the stylus from the screen.
     * A value of 0.0 indicates direct contact and larger values indicate increasing
     * distance from the surface.
     * </ul>
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_DISTANCE = 24;

    /**
     * Axis constant: Gas axis of a motion event.
     * <p>
     * <ul>
     * <li>For a joystick, reports the absolute position of the gas (accelerator) control.
     * The value is normalized to a range from 0.0 (no acceleration)
     * to 1.0 (maximum acceleration).
     * </ul>
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GAS = 22;

    /**
     * Axis constant: Generic 1 axis of a motion event.
     * The interpretation of a generic axis is device-specific.
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GENERIC_1 = 32;

    /**
     * Axis constant: Generic 10 axis of a motion event.
     * The interpretation of a generic axis is device-specific.
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GENERIC_10 = 41;

    /**
     * Axis constant: Generic 11 axis of a motion event.
     * The interpretation of a generic axis is device-specific.
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GENERIC_11 = 42;

    /**
     * Axis constant: Generic 12 axis of a motion event.
     * The interpretation of a generic axis is device-specific.
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GENERIC_12 = 43;

    /**
     * Axis constant: Generic 13 axis of a motion event.
     * The interpretation of a generic axis is device-specific.
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GENERIC_13 = 44;

    /**
     * Axis constant: Generic 14 axis of a motion event.
     * The interpretation of a generic axis is device-specific.
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GENERIC_14 = 45;

    /**
     * Axis constant: Generic 15 axis of a motion event.
     * The interpretation of a generic axis is device-specific.
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GENERIC_15 = 46;

    /**
     * Axis constant: Generic 16 axis of a motion event.
     * The interpretation of a generic axis is device-specific.
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GENERIC_16 = 47;

    /**
     * Axis constant: Generic 2 axis of a motion event.
     * The interpretation of a generic axis is device-specific.
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GENERIC_2 = 33;

    /**
     * Axis constant: Generic 3 axis of a motion event.
     * The interpretation of a generic axis is device-specific.
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GENERIC_3 = 34;

    /**
     * Axis constant: Generic 4 axis of a motion event.
     * The interpretation of a generic axis is device-specific.
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GENERIC_4 = 35;

    /**
     * Axis constant: Generic 5 axis of a motion event.
     * The interpretation of a generic axis is device-specific.
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GENERIC_5 = 36;

    /**
     * Axis constant: Generic 6 axis of a motion event.
     * The interpretation of a generic axis is device-specific.
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GENERIC_6 = 37;

    /**
     * Axis constant: Generic 7 axis of a motion event.
     * The interpretation of a generic axis is device-specific.
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GENERIC_7 = 38;

    /**
     * Axis constant: Generic 8 axis of a motion event.
     * The interpretation of a generic axis is device-specific.
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GENERIC_8 = 39;

    /**
     * Axis constant: Generic 9 axis of a motion event.
     * The interpretation of a generic axis is device-specific.
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_GENERIC_9 = 40;

    /**
     * Axis constant: pinch scale factor of a motion event.
     * <p>
     * <ul>
     * <li>For a touch pad, reports the change in distance between the fingers when the user is
     * making a pinch gesture, as a proportion of the previous distance. For example, if the fingers
     * were 50 units apart and are now 52 units apart, the scale factor would be 1.04.
     * </ul>
     * These values are relative to the state from the last sample, not accumulated, so developers
     * should make sure to process this axis value for all batched historical samples.
     * <p>
     * This axis is only set on the first pointer in a motion event.
     */
    public static final int AXIS_GESTURE_PINCH_SCALE_FACTOR = 52;

    /**
     * Axis constant: X scroll distance axis of a motion event.
     * <p>
     * <ul>
     * <li>For a touch pad, reports the distance that should be scrolled in the X axis as a result
     * of the user's two-finger scroll gesture, in display pixels.
     * </ul>
     * These values are relative to the state from the last sample, not accumulated, so developers
     * should make sure to process this axis value for all batched historical samples.
     * <p>
     * This axis is only set on the first pointer in a motion event.
     */
    public static final int AXIS_GESTURE_SCROLL_X_DISTANCE = 50;

    /**
     * Axis constant: Y scroll distance axis of a motion event.
     *
     * The same as {@link #AXIS_GESTURE_SCROLL_X_DISTANCE}, but for the Y axis.
     */
    public static final int AXIS_GESTURE_SCROLL_Y_DISTANCE = 51;

    /**
     * Axis constant: X gesture offset axis of a motion event.
     * <p>
     * <ul>
     * <li>For a touch pad, reports the distance that a swipe gesture has moved in the X axis, as a
     * proportion of the touch pad's size. For example, if a touch pad is 1000 units wide, and a
     * swipe gesture starts at X = 500 then moves to X = 400, this axis would have a value of
     * -0.1.
     * </ul>
     * These values are relative to the state from the last sample, not accumulated, so developers
     * should make sure to process this axis value for all batched historical samples.
     * <p>
     * This axis is only set on the first pointer in a motion event.
     */
    public static final int AXIS_GESTURE_X_OFFSET = 48;

    /**
     * Axis constant: Y gesture offset axis of a motion event.
     *
     * The same as {@link #AXIS_GESTURE_X_OFFSET}, but for the Y axis.
     */
    public static final int AXIS_GESTURE_Y_OFFSET = 49;

    /**
     * Axis constant: Hat X axis of a motion event.
     * <p>
     * <ul>
     * <li>For a joystick, reports the absolute X position of the directional hat control.
     * The value is normalized to a range from -1.0 (left) to 1.0 (right).
     * </ul>
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_HAT_X = 15;

    /**
     * Axis constant: Hat Y axis of a motion event.
     * <p>
     * <ul>
     * <li>For a joystick, reports the absolute Y position of the directional hat control.
     * The value is normalized to a range from -1.0 (up) to 1.0 (down).
     * </ul>
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_HAT_Y = 16;

    /**
     * Axis constant: Horizontal Scroll axis of a motion event.
     * <p>
     * <ul>
     * <li>For a mouse, reports the relative movement of the horizontal scroll wheel.
     * The value is normalized to a range from -1.0 (left) to 1.0 (right).
     * </ul>
     * </p><p>
     * This axis should be used to scroll views horizontally.
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_HSCROLL = 10;

    /**
     * Axis constant: Left Trigger axis of a motion event.
     * <p>
     * <ul>
     * <li>For a joystick, reports the absolute position of the left trigger control.
     * The value is normalized to a range from 0.0 (released) to 1.0 (fully pressed).
     * </ul>
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_LTRIGGER = 17;

    /**
     * Axis constant: Orientation axis of a motion event.
     * <p>
     * <ul>
     * <li>For a touch screen or touch pad, reports the orientation of the finger
     * or tool in radians relative to the vertical plane of the device.
     * An angle of 0 radians indicates that the major axis of contact is oriented
     * upwards, is perfectly circular or is of unknown orientation.  A positive angle
     * indicates that the major axis of contact is oriented to the right.  A negative angle
     * indicates that the major axis of contact is oriented to the left.
     * The full range is from -PI/2 radians (finger pointing fully left) to PI/2 radians
     * (finger pointing fully right).
     * <li>For a stylus, the orientation indicates the direction in which the stylus
     * is pointing in relation to the vertical axis of the current orientation of the screen.
     * The range is from -PI radians to PI radians, where 0 is pointing up,
     * -PI/2 radians is pointing left, -PI or PI radians is pointing down, and PI/2 radians
     * is pointing right.  See also {@link #AXIS_TILT}.
     * </ul>
     * </p>
     *
     * @see #getOrientation(int)
     * @see #getHistoricalOrientation(int, int)
     * @see MotionEvent.PointerCoords#orientation
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_ORIENTATION = 8;

    /**
     * Axis constant: Pressure axis of a motion event.
     * <p>
     * <ul>
     * <li>For a touch screen or touch pad, reports the approximate pressure applied to the surface
     * by a finger or other tool.  The value is normalized to a range from
     * 0 (no pressure at all) to 1 (normal pressure), although values higher than 1
     * may be generated depending on the calibration of the input device.
     * <li>For a trackball, the value is set to 1 if the trackball button is pressed
     * or 0 otherwise.
     * <li>For a mouse, the value is set to 1 if the primary mouse button is pressed
     * or 0 otherwise.
     * </ul>
     * </p>
     *
     * @see #getPressure(int)
     * @see #getHistoricalPressure(int, int)
     * @see MotionEvent.PointerCoords#pressure
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_PRESSURE = 2;

    /**
     * Axis constant: The movement of x position of a motion event.
     * <p>
     * <ul>
     * <li>For a mouse, reports a difference of x position between the previous position.
     * This is useful when pointer is captured, in that case the mouse pointer doesn't change
     * the location but this axis reports the difference which allows the app to see
     * how the mouse is moved.
     * </ul>
     * </p><p>
     * These values are relative to the state from the last sample, not accumulated, so developers
     * should make sure to process this axis value for all batched historical samples.
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int, int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_RELATIVE_X = 27;

    /**
     * Axis constant: The movement of y position of a motion event.
     * <p>
     * This is similar to {@link #AXIS_RELATIVE_X} but for y-axis.
     * </p><p>
     * These values are relative to the state from the last sample, not accumulated, so developers
     * should make sure to process this axis value for all batched historical samples.
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int, int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_RELATIVE_Y = 28;

    /**
     * Axis constant: Right Trigger axis of a motion event.
     * <p>
     * <ul>
     * <li>For a joystick, reports the absolute position of the right trigger control.
     * The value is normalized to a range from 0.0 (released) to 1.0 (fully pressed).
     * </ul>
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_RTRIGGER = 18;

    /**
     * Axis constant: Rudder axis of a motion event.
     * <p>
     * <ul>
     * <li>For a joystick, reports the absolute position of the rudder control.
     * The value is normalized to a range from -1.0 (turn left) to 1.0 (turn right).
     * </ul>
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_RUDDER = 20;

    /**
     * Axis constant: X Rotation axis of a motion event.
     * <p>
     * <ul>
     * <li>For a joystick, reports the absolute rotation angle about the X axis.
     * The value is normalized to a range from -1.0 (counter-clockwise) to 1.0 (clockwise).
     * </ul>
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_RX = 12;

    /**
     * Axis constant: Y Rotation axis of a motion event.
     * <p>
     * <ul>
     * <li>For a joystick, reports the absolute rotation angle about the Y axis.
     * The value is normalized to a range from -1.0 (counter-clockwise) to 1.0 (clockwise).
     * </ul>
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_RY = 13;

    /**
     * Axis constant: Z Rotation axis of a motion event.
     * <p>
     * <ul>
     * <li>For a joystick, reports the absolute rotation angle about the Z axis.
     * The value is normalized to a range from -1.0 (counter-clockwise) to 1.0 (clockwise).
     * <em>On game pads with two analog joysticks, this axis is often reinterpreted
     * to report the absolute Y position of the second joystick instead.</em>
     * </ul>
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_RZ = 14;

    /**
     * Axis constant: Generic scroll axis of a motion event.
     * <p>
     * <ul>
     * <li>Reports the relative movement of the generic scrolling device.
     * </ul>
     * </p><p>
     * This axis should be used for scroll events that are neither strictly vertical nor horizontal.
     * A good example would be the rotation of a rotary encoder input device.
     * </p>
     *
     * @see #getAxisValue(int, int)
     */
    public static final int AXIS_SCROLL = 26;

    /**
     * Axis constant: Size axis of a motion event.
     * <p>
     * <ul>
     * <li>For a touch screen or touch pad, reports the approximate size of the contact area in
     * relation to the maximum detectable size for the device.  The value is normalized
     * to a range from 0 (smallest detectable size) to 1 (largest detectable size),
     * although it is not a linear scale.  The value of size can be used to
     * determine fat touch events.
     * To obtain calibrated size information, use
     * {@link #AXIS_TOUCH_MAJOR} or {@link #AXIS_TOOL_MAJOR}.
     * </ul>
     * </p>
     *
     * @see #getSize(int)
     * @see #getHistoricalSize(int, int)
     * @see MotionEvent.PointerCoords#size
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_SIZE = 3;

    /**
     * Axis constant: Throttle axis of a motion event.
     * <p>
     * <ul>
     * <li>For a joystick, reports the absolute position of the throttle control.
     * The value is normalized to a range from 0.0 (fully open) to 1.0 (fully closed).
     * </ul>
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_THROTTLE = 19;

    /**
     * Axis constant: Tilt axis of a motion event.
     * <p>
     * <ul>
     * <li>For a stylus, reports the tilt angle of the stylus in radians where
     * 0 radians indicates that the stylus is being held perpendicular to the
     * surface, and PI/2 radians indicates that the stylus is being held flat
     * against the surface.
     * </ul>
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int, int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_TILT = 25;

    /**
     * Axis constant: ToolMajor axis of a motion event.
     * <p>
     * <ul>
     * <li>For a touch screen, reports the length of the major axis of an ellipse that
     * represents the size of the approaching finger or tool used to make contact.
     * <li>For a touch pad, reports the length of the major axis of an ellipse that
     * represents the size of the approaching finger or tool used to make contact.
     * The units are device-dependent; use {@link InputDevice#getMotionRange(int)}
     * to query the effective range of values.
     * </ul>
     * </p><p>
     * When the touch is circular, the major and minor axis lengths will be equal to one another.
     * </p><p>
     * The tool size may be larger than the touch size since the tool may not be fully
     * in contact with the touch sensor.
     * </p>
     *
     * @see #getToolMajor(int)
     * @see #getHistoricalToolMajor(int, int)
     * @see MotionEvent.PointerCoords#toolMajor
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_TOOL_MAJOR = 6;

    /**
     * Axis constant: ToolMinor axis of a motion event.
     * <p>
     * <ul>
     * <li>For a touch screen, reports the length of the minor axis of an ellipse that
     * represents the size of the approaching finger or tool used to make contact.
     * <li>For a touch pad, reports the length of the minor axis of an ellipse that
     * represents the size of the approaching finger or tool used to make contact.
     * The units are device-dependent; use {@link InputDevice#getMotionRange(int)}
     * to query the effective range of values.
     * </ul>
     * </p><p>
     * When the touch is circular, the major and minor axis lengths will be equal to one another.
     * </p><p>
     * The tool size may be larger than the touch size since the tool may not be fully
     * in contact with the touch sensor.
     * </p>
     *
     * @see #getToolMinor(int)
     * @see #getHistoricalToolMinor(int, int)
     * @see MotionEvent.PointerCoords#toolMinor
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_TOOL_MINOR = 7;

    /**
     * Axis constant: TouchMajor axis of a motion event.
     * <p>
     * <ul>
     * <li>For a touch screen, reports the length of the major axis of an ellipse that
     * represents the touch area at the point of contact.
     * The units are display pixels.
     * <li>For a touch pad, reports the length of the major axis of an ellipse that
     * represents the touch area at the point of contact.
     * The units are device-dependent; use {@link InputDevice#getMotionRange(int)}
     * to query the effective range of values.
     * </ul>
     * </p>
     *
     * @see #getTouchMajor(int)
     * @see #getHistoricalTouchMajor(int, int)
     * @see MotionEvent.PointerCoords#touchMajor
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_TOUCH_MAJOR = 4;

    /**
     * Axis constant: TouchMinor axis of a motion event.
     * <p>
     * <ul>
     * <li>For a touch screen, reports the length of the minor axis of an ellipse that
     * represents the touch area at the point of contact.
     * The units are display pixels.
     * <li>For a touch pad, reports the length of the minor axis of an ellipse that
     * represents the touch area at the point of contact.
     * The units are device-dependent; use {@link InputDevice#getMotionRange(int)}
     * to query the effective range of values.
     * </ul>
     * </p><p>
     * When the touch is circular, the major and minor axis lengths will be equal to one another.
     * </p>
     *
     * @see #getTouchMinor(int)
     * @see #getHistoricalTouchMinor(int, int)
     * @see MotionEvent.PointerCoords#touchMinor
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_TOUCH_MINOR = 5;

    /**
     * Axis constant: Vertical Scroll axis of a motion event.
     * <p>
     * <ul>
     * <li>For a mouse, reports the relative movement of the vertical scroll wheel.
     * The value is normalized to a range from -1.0 (down) to 1.0 (up).
     * </ul>
     * </p><p>
     * This axis should be used to scroll views vertically.
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_VSCROLL = 9;

    /**
     * Axis constant: Wheel axis of a motion event.
     * <p>
     * <ul>
     * <li>For a joystick, reports the absolute position of the steering wheel control.
     * The value is normalized to a range from -1.0 (turn left) to 1.0 (turn right).
     * </ul>
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_WHEEL = 21;

    /**
     * Axis constant: X axis of a motion event.
     * <p>
     * <ul>
     * <li>For a touch screen, reports the absolute X screen position of the center of
     * the touch contact area.  The units are display pixels.
     * <li>For a touch pad, reports the absolute X surface position of the center of the touch
     * contact area.  The units are device-dependent; use {@link InputDevice#getMotionRange(int)}
     * to query the effective range of values.
     * <li>For a mouse, reports the absolute X screen position of the mouse pointer.
     * The units are display pixels.
     * <li>For a trackball, reports the relative horizontal displacement of the trackball.
     * The value is normalized to a range from -1.0 (left) to 1.0 (right).
     * <li>For a joystick, reports the absolute X position of the joystick.
     * The value is normalized to a range from -1.0 (left) to 1.0 (right).
     * </ul>
     * </p>
     *
     * @see #getX(int)
     * @see #getHistoricalX(int, int)
     * @see MotionEvent.PointerCoords#x
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_X = 0;

    /**
     * Axis constant: Y axis of a motion event.
     * <p>
     * <ul>
     * <li>For a touch screen, reports the absolute Y screen position of the center of
     * the touch contact area.  The units are display pixels.
     * <li>For a touch pad, reports the absolute Y surface position of the center of the touch
     * contact area.  The units are device-dependent; use {@link InputDevice#getMotionRange(int)}
     * to query the effective range of values.
     * <li>For a mouse, reports the absolute Y screen position of the mouse pointer.
     * The units are display pixels.
     * <li>For a trackball, reports the relative vertical displacement of the trackball.
     * The value is normalized to a range from -1.0 (up) to 1.0 (down).
     * <li>For a joystick, reports the absolute Y position of the joystick.
     * The value is normalized to a range from -1.0 (up or far) to 1.0 (down or near).
     * </ul>
     * </p>
     *
     * @see #getY(int)
     * @see #getHistoricalY(int, int)
     * @see MotionEvent.PointerCoords#y
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_Y = 1;

    /**
     * Axis constant: Z axis of a motion event.
     * <p>
     * <ul>
     * <li>For a joystick, reports the absolute Z position of the joystick.
     * The value is normalized to a range from -1.0 (high) to 1.0 (low).
     * <em>On game pads with two analog joysticks, this axis is often reinterpreted
     * to report the absolute X position of the second joystick instead.</em>
     * </ul>
     * </p>
     *
     * @see #getAxisValue(int, int)
     * @see #getHistoricalAxisValue(int, int, int)
     * @see MotionEvent.PointerCoords#getAxisValue(int)
     * @see InputDevice#getMotionRange
     */
    public static final int AXIS_Z = 11;

    /**
     * Button constant: Back button pressed (mouse back button).
     * <p>
     * The system may send a {@link KeyEvent#KEYCODE_BACK} key press to the application
     * when this button is pressed.
     * </p>
     *
     * @see #getButtonState
     */
    public static final int BUTTON_BACK = 8;

    /**
     * Button constant: Forward button pressed (mouse forward button).
     * <p>
     * The system may send a {@link KeyEvent#KEYCODE_FORWARD} key press to the application
     * when this button is pressed.
     * </p>
     *
     * @see #getButtonState
     */
    public static final int BUTTON_FORWARD = 16;

    /**
     * Button constant: Primary button (left mouse button).
     *
     * This button constant is not set in response to simple touches with a finger
     * or stylus tip.  The user must actually push a button.
     *
     * @see #getButtonState
     */
    public static final int BUTTON_PRIMARY = 1;

    /**
     * Button constant: Secondary button (right mouse button).
     *
     * @see #getButtonState
     */
    public static final int BUTTON_SECONDARY = 2;

    /**
     * Button constant: Primary stylus button pressed.
     *
     * @see #getButtonState
     */
    public static final int BUTTON_STYLUS_PRIMARY = 32;

    /**
     * Button constant: Secondary stylus button pressed.
     *
     * @see #getButtonState
     */
    public static final int BUTTON_STYLUS_SECONDARY = 64;

    /**
     * Button constant: Tertiary button (middle mouse button).
     *
     * @see #getButtonState
     */
    public static final int BUTTON_TERTIARY = 4;

    /**
     * Classification constant: Ambiguous gesture.
     *
     * The user's intent with respect to the current event stream is not yet determined.
     * Gestural actions, such as scrolling, should be inhibited until the classification resolves
     * to another value or the event stream ends.
     *
     * @see #getClassification
     */
    public static final int CLASSIFICATION_AMBIGUOUS_GESTURE = 1;

    /**
     * Classification constant: Deep press.
     *
     * The current event stream represents the user intentionally pressing harder on the screen.
     * This classification type should be used to accelerate the long press behaviour.
     *
     * @see #getClassification
     */
    public static final int CLASSIFICATION_DEEP_PRESS = 2;

    /**
     * Classification constant: None.
     *
     * No additional information is available about the current motion event stream.
     *
     * @see #getClassification
     */
    public static final int CLASSIFICATION_NONE = 0;

    /**
     * Classification constant: touchpad pinch.
     *
     * The current event stream represents the user pinching with two fingers on a touchpad. The
     * gesture is centered around the current cursor position.
     *
     * @see #getClassification
     */
    public static final int CLASSIFICATION_PINCH = 5;

    /**
     * Classification constant: touchpad scroll.
     *
     * The current event stream represents the user scrolling with two fingers on a touchpad.
     *
     * @see #getClassification
     */
    public static final int CLASSIFICATION_TWO_FINGER_SWIPE = 3;

    /**
     * Flag indicating the motion event intersected the bottom edge of the screen.
     */
    public static final int EDGE_BOTTOM = 2;

    /**
     * Flag indicating the motion event intersected the left edge of the screen.
     */
    public static final int EDGE_LEFT = 4;

    /**
     * Flag indicating the motion event intersected the right edge of the screen.
     */
    public static final int EDGE_RIGHT = 8;

    /**
     * Flag indicating the motion event intersected the top edge of the screen.
     */
    public static final int EDGE_TOP = 1;

    /**
     * This flag is only set for events with {@link #ACTION_POINTER_UP} and {@link #ACTION_CANCEL}.
     * It indicates that the pointer going up was an unintentional user touch. When FLAG_CANCELED
     * is set, the typical actions that occur in response for a pointer going up (such as click
     * handlers, end of drawing) should be aborted. This flag is typically set when the user was
     * accidentally touching the screen, such as by gripping the device, or placing the palm on the
     * screen.
     *
     * @see #ACTION_POINTER_UP
     * @see #ACTION_CANCEL
     */
    public static final int FLAG_CANCELED = 32;

    /**
     * This flag indicates that the window that received this motion event is partly
     * or wholly obscured by another visible window above it and the event directly passed through
     * the obscured area.
     *
     * A security sensitive application can check this flag to identify situations in which
     * a malicious application may have covered up part of its content for the purpose
     * of misleading the user or hijacking touches.  An appropriate response might be
     * to drop the suspect touches or to take additional precautions to confirm the user's
     * actual intent.
     */
    public static final int FLAG_WINDOW_IS_OBSCURED = 1;

    /**
     * This flag indicates that the window that received this motion event is partly
     * or wholly obscured by another visible window above it and the event did not directly pass
     * through the obscured area.
     *
     * A security sensitive application can check this flag to identify situations in which
     * a malicious application may have covered up part of its content for the purpose
     * of misleading the user or hijacking touches.  An appropriate response might be
     * to drop the suspect touches or to take additional precautions to confirm the user's
     * actual intent.
     *
     * Unlike FLAG_WINDOW_IS_OBSCURED, this is only true if the window that received this event is
     * obstructed in areas other than the touched location.
     */
    public static final int FLAG_WINDOW_IS_PARTIALLY_OBSCURED = 2;

    /**
     * An invalid pointer id.
     *
     * This value (-1) can be used as a placeholder to indicate that a pointer id
     * has not been assigned or is not available.  It cannot appear as
     * a pointer id inside a {@link MotionEvent}.
     */
    public static final int INVALID_POINTER_ID = -1;

    /**
     * Tool type constant: The tool is an eraser or a stylus being used in an inverted posture.
     *
     * @see #getToolType
     */
    public static final int TOOL_TYPE_ERASER = 4;

    /**
     * Tool type constant: The tool is a finger.
     *
     * @see #getToolType
     */
    public static final int TOOL_TYPE_FINGER = 1;

    /**
     * Tool type constant: The tool is a mouse.
     *
     * @see #getToolType
     */
    public static final int TOOL_TYPE_MOUSE = 3;

    /**
     * Tool type constant: The tool is a stylus.
     *
     * @see #getToolType
     */
    public static final int TOOL_TYPE_STYLUS = 2;

    /**
     * Tool type constant: Unknown tool type.
     * This constant is used when the tool type is not known or is not relevant,
     * such as for a trackball or other non-pointing device.
     *
     * @see #getToolType
     */
    public static final int TOOL_TYPE_UNKNOWN = 0;

    private MotionEvent() {
    }
}
