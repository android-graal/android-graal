package android.media.audiofx;

/**
 * A sound generated within a room travels in many directions. The listener first hears the direct
 * sound from the source itself. Later, he or she hears discrete echoes caused by sound bouncing off
 * nearby walls, the ceiling and the floor. As sound waves arrive after undergoing more and more
 * reflections, individual reflections become indistinguishable and the listener hears continuous
 * reverberation that decays over time.
 * Reverb is vital for modeling a listener's environment. It can be used in music applications
 * to simulate music being played back in various environments, or in games to immerse the
 * listener within the game's environment.
 * The EnvironmentalReverb class allows an application to control each reverb engine property in a
 * global reverb environment and is more suitable for games. For basic control, more suitable for
 * music applications, it is recommended to use the
 * {@link android.media.audiofx.PresetReverb} class.
 * <p>An application creates a EnvironmentalReverb object to instantiate and control a reverb engine
 * in the audio framework.
 * <p>The methods, parameter types and units exposed by the EnvironmentalReverb implementation are
 * directly mapping those defined by the OpenSL ES 1.0.1 Specification
 * (http://www.khronos.org/opensles/) for the SLEnvironmentalReverbItf interface.
 * Please refer to this specification for more details.
 * <p>The EnvironmentalReverb is an output mix auxiliary effect and should be created on
 * Audio session 0. In order for a MediaPlayer or AudioTrack to be fed into this effect,
 * they must be explicitely attached to it and a send level must be specified. Use the effect ID
 * returned by getId() method to designate this particular effect when attaching it to the
 * MediaPlayer or AudioTrack.
 * <p>Creating a reverb on the output mix (audio session 0) requires permission
 * {@link android.Manifest.permission#MODIFY_AUDIO_SETTINGS}
 * <p>See {@link android.media.audiofx.AudioEffect} class for more details on controlling
 * audio effects.
 */
public final class EnvironmentalReverb {
    /**
     * Decay HF ratio. Parameter ID for
     * {@link android.media.audiofx.EnvironmentalReverb.OnParameterChangeListener}
     */
    public static final int PARAM_DECAY_HF_RATIO = 3;

    /**
     * Decay time. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_DECAY_TIME = 2;

    /**
     * Density. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_DENSITY = 9;

    /**
     * Diffusion. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_DIFFUSION = 8;

    /**
     * Early reflections delay. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_REFLECTIONS_DELAY = 5;

    /**
     * Early reflections level. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_REFLECTIONS_LEVEL = 4;

    /**
     * Reverb delay. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_REVERB_DELAY = 7;

    /**
     * Reverb level. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_REVERB_LEVEL = 6;

    /**
     * Room HF level. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_ROOM_HF_LEVEL = 1;

    /**
     * Room level. Parameter ID for OnParameterChangeListener
     */
    public static final int PARAM_ROOM_LEVEL = 0;

    private EnvironmentalReverb() {
    }
}
