package android.media.audiofx;

/**
 * An Equalizer is used to alter the frequency response of a particular music source or of the main
 * output mix.
 * <p>An application creates an Equalizer object to instantiate and control an Equalizer engine
 * in the audio framework. The application can either simply use predefined presets or have a more
 * precise control of the gain in each frequency band controlled by the equalizer.
 * <p>The methods, parameter types and units exposed by the Equalizer implementation are directly
 * mapping those defined by the OpenSL ES 1.0.1 Specification (http://www.khronos.org/opensles/)
 * for the SLEqualizerItf interface. Please refer to this specification for more details.
 * <p>To attach the Equalizer to a particular AudioTrack or MediaPlayer, specify the audio session
 * ID of this AudioTrack or MediaPlayer when constructing the Equalizer.
 * <p>NOTE: attaching an Equalizer to the global audio output mix by use of session 0 is deprecated.
 * <p>See {@link android.media.MediaPlayer#getAudioSessionId()} for details on audio sessions.
 * <p>See {@link android.media.audiofx.AudioEffect} class for more details on controlling audio
 * effects.
 */
public final class Equalizer {
    /**
     * Band frequency range. Parameter ID for
     * {@link android.media.audiofx.Equalizer.OnParameterChangeListener}
     */
    public static final int PARAM_BAND_FREQ_RANGE = 4;

    /**
     * Band level. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_BAND_LEVEL = 2;

    /**
     * Band center frequency. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_CENTER_FREQ = 3;

    /**
     * Current preset. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_CURRENT_PRESET = 6;

    /**
     * Band for a given frequency. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_GET_BAND = 5;

    /**
     * Request number of presets. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_GET_NUM_OF_PRESETS = 7;

    /**
     * Request preset name. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_GET_PRESET_NAME = 8;

    /**
     * Band level range. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_LEVEL_RANGE = 1;

    /**
     * Number of bands. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_NUM_BANDS = 0;

    /**
     * Maximum size for preset name
     */
    public static final int PARAM_STRING_SIZE_MAX = 32;

    private Equalizer() {
    }
}
