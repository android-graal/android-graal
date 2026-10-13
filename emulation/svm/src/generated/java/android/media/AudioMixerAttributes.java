package android.media;

import android.os.Parcelable;

/**
 * Class to represent the attributes of the audio mixer: its format, which represents by an
 * {@link AudioFormat} object and mixer behavior.
 */
public final class AudioMixerAttributes implements Parcelable {
    /**
     * Constant indicating the audio mixer behavior is bit-perfect, which indicates there will
     * not be mixing happen, the audio data will be sent as is down to the HAL.
     */
    public static final int MIXER_BEHAVIOR_BIT_PERFECT = 1;

    /**
     * Constant indicating the audio mixer behavior will follow the default platform behavior, which
     * is mixing all audio sources in the mixer.
     */
    public static final int MIXER_BEHAVIOR_DEFAULT = 0;

    private AudioMixerAttributes() {
    }
}
