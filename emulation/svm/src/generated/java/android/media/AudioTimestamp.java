package android.media;

import android.os.Parcelable;

/**
 * Structure that groups a position in frame units relative to an assumed audio stream,
 * together with the estimated time when that frame enters or leaves the audio
 * processing pipeline on that device. This can be used to coordinate events
 * and interactions with the external environment.
 * <p>
 * The time is based on the implementation's best effort, using whatever knowledge
 * is available to the system, but cannot account for any delay unknown to the implementation.
 *
 * @see AudioTrack#getTimestamp AudioTrack.getTimestamp(AudioTimestamp)
 * @see AudioRecord#getTimestamp AudioRecord.getTimestamp(AudioTimestamp, int)
 */
public final class AudioTimestamp implements Parcelable {
    /**
     * Clock monotonic including suspend time or its equivalent on the system,
     * in the same units and timebase as {@link android.os.SystemClock#elapsedRealtimeNanos}.
     */
    public static final int TIMEBASE_BOOTTIME = 1;

    /**
     * Clock monotonic or its equivalent on the system,
     * in the same units and timebase as {@link java.lang.System#nanoTime}.
     */
    public static final int TIMEBASE_MONOTONIC = 0;

    private AudioTimestamp() {
    }
}
