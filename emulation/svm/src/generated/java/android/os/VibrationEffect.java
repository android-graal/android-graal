package android.os;

/**
 * A VibrationEffect describes a haptic effect to be performed by a {@link Vibrator}.
 *
 * <p>These effects may be any number of things, from single shot vibrations to complex waveforms.
 */
public abstract class VibrationEffect implements Parcelable {
    /**
     * The default vibration strength of the device.
     */
    public static final int DEFAULT_AMPLITUDE = -1;

    /**
     * A click effect. Use this effect as a baseline, as it's the most common type of click effect.
     */
    public static final int EFFECT_CLICK = 0;

    /**
     * A double click effect.
     */
    public static final int EFFECT_DOUBLE_CLICK = 1;

    /**
     * A heavy click effect. This effect is stronger than {@link #EFFECT_CLICK}.
     */
    public static final int EFFECT_HEAVY_CLICK = 5;

    /**
     * A tick effect. This effect is less strong compared to {@link #EFFECT_CLICK}.
     */
    public static final int EFFECT_TICK = 2;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    private VibrationEffect() {
    }

    /**
     * A composition of haptic elements that are combined to be playable as a single
     * {@link VibrationEffect}.
     *
     * <p>The haptic primitives are available as {@code Composition.PRIMITIVE_*} constants and
     * can be added to a composition to create a custom vibration effect. Here is an example of an
     * effect that grows in intensity and then dies off, with a longer rising portion for emphasis
     * and an extra tick 100ms after:
     *
     * <pre>
     * {@code VibrationEffect effect = VibrationEffect.startComposition()
     *     .addPrimitive(VibrationEffect.Composition.PRIMITIVE_SLOW_RISE, 0.5f)
     *     .addPrimitive(VibrationEffect.Composition.PRIMITIVE_QUICK_FALL, 0.5f)
     *     .addPrimitive(VibrationEffect.Composition.PRIMITIVE_TICK, 1.0f, 100)
     *     .compose();}</pre>
     *
     * <p>When choosing to play a composed effect, you should check that individual components are
     * supported by the device by using {@link Vibrator#arePrimitivesSupported}.
     *
     * @see VibrationEffect#startComposition()
     */
    public static final class Composition {
        /**
         * The delay represents a pause in the composition between the end of the previous primitive
         * and the beginning of the next one.
         *
         * <p>The primitive will start after the requested pause after the last primitive ended.
         * The actual time the primitive will be played depends on the previous primitive's actual
         * duration on the device hardware. This enables the combination of primitives to create
         * more complex effects based on how close to each other they'll play. Here is an example:
         *
         * <pre>
         *     VibrationEffect popEffect = VibrationEffect.startComposition()
         *         .addPrimitive(PRIMITIVE_QUICK_RISE)
         *         .addPrimitive(PRIMITIVE_CLICK, 0.7, 50, DELAY_TYPE_PAUSE)
         *         .compose()
         * </pre>
         */
        public static final int DELAY_TYPE_PAUSE = 0;

        /**
         * The delay represents an offset before starting this primitive, relative to the start
         * time of the previous primitive in the composition.
         *
         * <p>The primitive will start at the requested fixed time after the last primitive started,
         * independently of that primitive's actual duration on the device hardware. This enables
         * precise timings of primitives within a composition, ensuring they'll be played at the
         * desired intervals. Here is an example:
         *
         * <pre>
         *     VibrationEffect.startComposition()
         *         .addPrimitive(PRIMITIVE_CLICK, 1.0)
         *         .addPrimitive(PRIMITIVE_TICK, 1.0, 20, DELAY_TYPE_RELATIVE_START_OFFSET)
         *         .addPrimitive(PRIMITIVE_THUD, 1.0, 80, DELAY_TYPE_RELATIVE_START_OFFSET)
         *         .compose()
         * </pre>
         *
         * Will be performed on the device as follows:
         *
         * <pre>
         *  0ms               20ms                     100ms
         *  PRIMITIVE_CLICK---PRIMITIVE_TICK-----------PRIMITIVE_THUD
         * </pre>
         *
         * <p>A primitive will be dropped from the composition if it overlaps with previous ones.
         */
        public static final int DELAY_TYPE_RELATIVE_START_OFFSET = 1;

        /**
         * This effect should produce a sharp, crisp click sensation.
         */
        public static final int PRIMITIVE_CLICK = 1;

        /**
         * This very short low frequency effect should produce a light crisp sensation
         * intended to be used repetitively for dynamic feedback.
         */
        public static final int PRIMITIVE_LOW_TICK = 8;

        /**
         * A haptic effect that simulates quick downwards movement with gravity.
         */
        public static final int PRIMITIVE_QUICK_FALL = 6;

        /**
         * A haptic effect that simulates quick upward movement against gravity.
         */
        public static final int PRIMITIVE_QUICK_RISE = 4;

        /**
         * A haptic effect that simulates slow upward movement against gravity.
         */
        public static final int PRIMITIVE_SLOW_RISE = 5;

        /**
         * A haptic effect that simulates spinning momentum.
         */
        public static final int PRIMITIVE_SPIN = 3;

        /**
         * A haptic effect that simulates downwards movement with gravity. Often
         * followed by extra energy of hitting and reverberation to augment
         * physicality.
         */
        public static final int PRIMITIVE_THUD = 2;

        /**
         * This very short effect should produce a light crisp sensation intended
         * to be used repetitively for dynamic feedback.
         */
        public static final int PRIMITIVE_TICK = 7;

        private Composition() {
        }
    }
}
