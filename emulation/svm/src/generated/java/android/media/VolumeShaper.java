package android.media;

import android.os.Parcelable;

/**
 * The {@code VolumeShaper} class is used to automatically control audio volume during media
 * playback, allowing simple implementation of transition effects and ducking.
 * It is created from implementations of {@code VolumeAutomation},
 * such as {@code MediaPlayer} and {@code AudioTrack} (referred to as "players" below),
 * by {@link MediaPlayer#createVolumeShaper} or {@link AudioTrack#createVolumeShaper}.
 *
 * A {@code VolumeShaper} is intended for short volume changes.
 * If the audio output sink changes during
 * a {@code VolumeShaper} transition, the precise curve position may be lost, and the
 * {@code VolumeShaper} may advance to the end of the curve for the new audio output sink.
 *
 * The {@code VolumeShaper} appears as an additional scaling on the audio output,
 * and adjusts independently of track or stream volume controls.
 */
public final class VolumeShaper {
    private VolumeShaper() {
    }

    /**
     * The {@code VolumeShaper.Configuration} class contains curve
     * and duration information.
     * It is constructed by the {@link VolumeShaper.Configuration.Builder}.
     * <p>
     * A {@code VolumeShaper.Configuration} is used by
     * {@link VolumeAutomation#createVolumeShaper(Configuration)
     * VolumeAutomation.createVolumeShaper(Configuration)} to create
     * a {@code VolumeShaper} and
     * by {@link VolumeShaper#replace(Configuration, Operation, boolean)
     * VolumeShaper.replace(Configuration, Operation, boolean)}
     * to replace an existing {@code configuration}.
     * <p>
     * The {@link AudioTrack} and {@link MediaPlayer} classes implement
     * the {@link VolumeAutomation} interface.
     */
    public static final class Configuration implements Parcelable {
        /**
         * Cubic interpolated volume curve.
         * This is default if unspecified.
         */
        public static final int INTERPOLATOR_TYPE_CUBIC = 2;

        /**
         * Cubic interpolated volume curve
         * that preserves local monotonicity.
         * So long as the control points are locally monotonic,
         * the curve interpolation between those points are monotonic.
         * This is useful for cubic spline interpolated
         * volume ramps and ducks.
         */
        public static final int INTERPOLATOR_TYPE_CUBIC_MONOTONIC = 3;

        /**
         * Linear interpolated volume curve.
         */
        public static final int INTERPOLATOR_TYPE_LINEAR = 1;

        /**
         * Stepwise volume curve.
         */
        public static final int INTERPOLATOR_TYPE_STEP = 0;

        @Override
        public int describeContents() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void writeToParcel(android.os.Parcel dest, int flags) {
            throw new UnsupportedOperationException();
        }

        private Configuration() {
        }
    }
}
