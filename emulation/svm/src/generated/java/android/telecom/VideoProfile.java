package android.telecom;

import android.os.Parcelable;

/**
 * Represents attributes of video calls.
 */
public final class VideoProfile implements Parcelable {
    /**
     * Use default video quality.
     */
    public static final int QUALITY_DEFAULT = 4;

    /**
     * "High" video quality.
     */
    public static final int QUALITY_HIGH = 1;

    /**
     * "Low" video quality.
     */
    public static final int QUALITY_LOW = 3;

    /**
     * "Medium" video quality.
     */
    public static final int QUALITY_MEDIUM = 2;

    /**
     * Used when answering or dialing a call to indicate that the call does not have a video
     * component.
     * <p>
     * Should <b>not</b> be used in comparison checks to determine if a video state represents an
     * audio-only call.
     * <p>
     * The following, for example, is not the correct way to check if a call is audio-only:
     * <pre>
     * {@code
     * // This is the incorrect way to check for an audio-only call.
     * if (videoState == VideoProfile.STATE_AUDIO_ONLY) {
     *      // Handle audio-only call.
     * }
     * }
     * </pre>
     * <p>
     * Instead, use the {@link VideoProfile#isAudioOnly(int)} helper function to check if a
     * video state represents an audio-only call:
     * <pre>
     * {@code
     * // This is the correct way to check for an audio-only call.
     * if (VideoProfile.isAudioOnly(videoState)) {
     *      // Handle audio-only call.
     * }
     * }
     * </pre>
     */
    public static final int STATE_AUDIO_ONLY = 0;

    /**
     * Video signal is bi-directional.
     */
    public static final int STATE_BIDIRECTIONAL = 3;

    /**
     * Video is paused.
     */
    public static final int STATE_PAUSED = 4;

    /**
     * Video reception is enabled.
     */
    public static final int STATE_RX_ENABLED = 2;

    /**
     * Video transmission is enabled.
     */
    public static final int STATE_TX_ENABLED = 1;

    private VideoProfile() {
    }
}
