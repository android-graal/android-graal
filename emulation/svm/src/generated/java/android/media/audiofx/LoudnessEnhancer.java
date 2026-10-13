package android.media.audiofx;

/**
 * LoudnessEnhancer is an audio effect for increasing audio loudness.
 * The processing is parametrized by a target gain value, which determines the maximum amount
 * by which an audio signal will be amplified; signals amplified outside of the sample
 * range supported by the platform are compressed.
 * An application creates a LoudnessEnhancer object to instantiate and control a
 * this audio effect in the audio framework.
 * To attach the LoudnessEnhancer to a particular AudioTrack or MediaPlayer,
 * specify the audio session ID of this AudioTrack or MediaPlayer when constructing the effect
 * (see {@link AudioTrack#getAudioSessionId()} and {@link MediaPlayer#getAudioSessionId()}).
 */
public final class LoudnessEnhancer {
    /**
     * The maximum gain applied applied to the signal to process.
     * It is expressed in millibels (100mB = 1dB) where 0mB corresponds to no amplification.
     */
    public static final int PARAM_TARGET_GAIN_MB = 0;

    private LoudnessEnhancer() {
    }
}
