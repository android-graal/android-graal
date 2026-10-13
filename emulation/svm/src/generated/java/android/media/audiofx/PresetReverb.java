package android.media.audiofx;

/**
 * A sound generated within a room travels in many directions. The listener first hears the
 * direct sound from the source itself. Later, he or she hears discrete echoes caused by sound
 * bouncing off nearby walls, the ceiling and the floor. As sound waves arrive after
 * undergoing more and more reflections, individual reflections become indistinguishable and
 * the listener hears continuous reverberation that decays over time.
 * Reverb is vital for modeling a listener's environment. It can be used in music applications
 * to simulate music being played back in various environments, or in games to immerse the
 * listener within the game's environment.
 * The PresetReverb class allows an application to configure the global reverb using a reverb preset.
 * This is primarily used for adding some reverb in a music playback context. Applications
 * requiring control over a more advanced environmental reverb are advised to use the
 * {@link android.media.audiofx.EnvironmentalReverb} class.
 * <p>An application creates a PresetReverb object to instantiate and control a reverb engine in the
 * audio framework.
 * <p>The methods, parameter types and units exposed by the PresetReverb implementation are
 * directly mapping those defined by the OpenSL ES 1.0.1 Specification
 * (http://www.khronos.org/opensles/) for the SLPresetReverbItf interface.
 * Please refer to this specification for more details.
 * <p>The PresetReverb is an output mix auxiliary effect and should be created on
 * Audio session 0. In order for a MediaPlayer or AudioTrack to be fed into this effect,
 * they must be explicitely attached to it and a send level must be specified. Use the effect ID
 * returned by getId() method to designate this particular effect when attaching it to the
 * MediaPlayer or AudioTrack.
 * <p>Creating a reverb on the output mix (audio session 0) requires permission
 * {@link android.Manifest.permission#MODIFY_AUDIO_SETTINGS}
 * <p>See {@link android.media.audiofx.AudioEffect} class for more details on controlling
 * audio effects.
 */
public final class PresetReverb {
    /**
     * Preset. Parameter ID for
     * {@link android.media.audiofx.PresetReverb.OnParameterChangeListener}
     */
    public static final int PARAM_PRESET = 0;

    /**
     * Reverb preset representing a large-sized hall suitable for a full orchestra
     */
    public static final short PRESET_LARGEHALL = 5;

    /**
     * Reverb preset representing a large-sized room suitable for live performances
     */
    public static final short PRESET_LARGEROOM = 3;

    /**
     * Reverb preset representing a medium-sized hall
     */
    public static final short PRESET_MEDIUMHALL = 4;

    /**
     * Reverb preset representing a medium room with a length of ten meters or less
     */
    public static final short PRESET_MEDIUMROOM = 2;

    /**
     * No reverb or reflections
     */
    public static final short PRESET_NONE = 0;

    /**
     * Reverb preset representing a synthesis of the traditional plate reverb
     */
    public static final short PRESET_PLATE = 6;

    /**
     * Reverb preset representing a small room less than five meters in length
     */
    public static final short PRESET_SMALLROOM = 1;

    private PresetReverb() {
    }
}
