package android.media.audiofx;

/**
 * Bass boost is an audio effect to boost or amplify low frequencies of the sound. It is comparable
 * to a simple equalizer but limited to one band amplification in the low frequency range.
 * <p>An application creates a BassBoost object to instantiate and control a bass boost engine in
 * the audio framework.
 * <p>The methods, parameter types and units exposed by the BassBoost implementation are directly
 * mapping those defined by the OpenSL ES 1.0.1 Specification (http://www.khronos.org/opensles/)
 * for the SLBassBoostItf interface. Please refer to this specification for more details.
 * <p>To attach the BassBoost to a particular AudioTrack or MediaPlayer, specify the audio session
 * ID of this AudioTrack or MediaPlayer when constructing the BassBoost.
 * <p>NOTE: attaching a BassBoost to the global audio output mix by use of session 0 is deprecated.
 * <p>See {@link android.media.MediaPlayer#getAudioSessionId()} for details on audio sessions.
 * <p>See {@link android.media.audiofx.AudioEffect} class for more details on
 * controlling audio effects.
 */
public final class BassBoost {
    /**
     * Bass boost effect strength. Parameter ID for
     * {@link android.media.audiofx.BassBoost.OnParameterChangeListener}
     */
    public static final int PARAM_STRENGTH = 1;

    /**
     * Is strength parameter supported by bass boost engine. Parameter ID for getParameter().
     */
    public static final int PARAM_STRENGTH_SUPPORTED = 0;

    private BassBoost() {
    }
}
