package android.media.audiofx;

/**
 * An audio virtualizer is a general name for an effect to spatialize audio channels. The exact
 * behavior of this effect is dependent on the number of audio input channels and the types and
 * number of audio output channels of the device. For example, in the case of a stereo input and
 * stereo headphone output, a stereo widening effect is used when this effect is turned on.
 * <p>An application creates a Virtualizer object to instantiate and control a virtualizer engine
 * in the audio framework.
 * <p>The methods, parameter types and units exposed by the Virtualizer implementation are directly
 * mapping those defined by the OpenSL ES 1.0.1 Specification (http://www.khronos.org/opensles/)
 * for the SLVirtualizerItf interface. Please refer to this specification for more details.
 * <p>To attach the Virtualizer to a particular AudioTrack or MediaPlayer, specify the audio session
 * ID of this AudioTrack or MediaPlayer when constructing the Virtualizer.
 * <p>NOTE: attaching a Virtualizer to the global audio output mix by use of session 0 is
 * deprecated.
 * <p>See {@link android.media.MediaPlayer#getAudioSessionId()} for details on audio sessions.
 * <p>See {@link android.media.audiofx.AudioEffect} class for more details on controlling
 * audio effects.
 *
 * @deprecated use the {@link android.media.Spatializer} class to query the capabilities of the
 *     platform with regards to spatialization, a different name for audio channel virtualization,
 *     and the {@link android.media.AudioAttributes.Builder#setSpatializationBehavior(int)} to
 *     characterize how you want your content to be played when spatialization is supported.
 */
@Deprecated
public final class Virtualizer {
    /**
     * Virtualizer effect strength. Parameter ID for
     * {@link android.media.audiofx.Virtualizer.OnParameterChangeListener}
     */
    @Deprecated
    public static final int PARAM_STRENGTH = 1;

    /**
     * Is strength parameter supported by virtualizer engine. Parameter ID for getParameter().
     */
    @Deprecated
    public static final int PARAM_STRENGTH_SUPPORTED = 0;

    /**
     * A virtualization mode used to indicate the virtualizer effect must stop forcing the
     * processing to a particular mode in {@link #forceVirtualizationMode(int)}.
     */
    @Deprecated
    public static final int VIRTUALIZATION_MODE_AUTO = 1;

    /**
     * A virtualization mode typically used over headphones.
     * Binaural virtualization describes an audio processing configuration for virtualization
     * where the left and right channels are respectively reaching the left and right ear of the
     * user, without also feeding the opposite ear (as is the case when listening over speakers).
     * <p>Such a mode is therefore meant to be used when audio is playing over stereo wired
     * headphones or headsets, but also stereo headphones through a wireless A2DP Bluetooth link.
     * <p>See {@link #canVirtualize(int, int)} to verify this mode is supported by this Virtualizer.
     */
    @Deprecated
    public static final int VIRTUALIZATION_MODE_BINAURAL = 2;

    /**
     * A virtualization mode indicating virtualization processing is not active.
     * See {@link #getVirtualizationMode()} as one of the possible return value.
     */
    @Deprecated
    public static final int VIRTUALIZATION_MODE_OFF = 0;

    /**
     * A virtualization mode typically used over speakers.
     * Transaural virtualization describes an audio processing configuration that differs from
     * binaural (as described in {@link #VIRTUALIZATION_MODE_BINAURAL} in that cross-talk is
     * present, i.e. audio played from the left channel also reaches the right ear of the user,
     * and vice-versa.
     * <p>When supported, such a mode is therefore meant to be used when audio is playing over the
     * built-in stereo speakers of a device, if they are featured.
     * <p>See {@link #canVirtualize(int, int)} to verify this mode is supported by this Virtualizer.
     */
    @Deprecated
    public static final int VIRTUALIZATION_MODE_TRANSAURAL = 3;

    private Virtualizer() {
    }
}
