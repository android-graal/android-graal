package android.media;

/**
 * Class providing information on a microphone. It indicates the location and orientation of the
 * microphone on the device as well as useful information like frequency response and sensitivity.
 * It can be used by applications implementing special pre processing effects like noise suppression
 * of beam forming that need to know about precise microphone characteristics in order to adapt
 * their algorithms.
 */
public final class MicrophoneInfo {
    /**
     * The channel contains raw audio from this microphone.
     */
    public static final int CHANNEL_MAPPING_DIRECT = 1;

    /**
     * The channel contains processed audio from this microphone and possibly another microphone.
     */
    public static final int CHANNEL_MAPPING_PROCESSED = 2;

    /**
     * Microphone directionality type: bi-directional.
     */
    public static final int DIRECTIONALITY_BI_DIRECTIONAL = 2;

    /**
     * Microphone directionality type: cardioid.
     */
    public static final int DIRECTIONALITY_CARDIOID = 3;

    /**
     * Microphone directionality type: hyper cardioid.
     */
    public static final int DIRECTIONALITY_HYPER_CARDIOID = 4;

    /**
     * Microphone directionality type: omni.
     */
    public static final int DIRECTIONALITY_OMNI = 1;

    /**
     * Microphone directionality type: super cardioid.
     */
    public static final int DIRECTIONALITY_SUPER_CARDIOID = 5;

    /**
     * Unknown microphone directionality.
     */
    public static final int DIRECTIONALITY_UNKNOWN = 0;

    /**
     * Value used for when the group of the microphone is unknown.
     */
    public static final int GROUP_UNKNOWN = -1;

    /**
     * Value used for when the index in the group of the microphone is unknown.
     */
    public static final int INDEX_IN_THE_GROUP_UNKNOWN = -1;

    /**
     * A microphone that locate on main body of the device.
     */
    public static final int LOCATION_MAINBODY = 1;

    /**
     * A microphone that locate on a movable main body of the device.
     */
    public static final int LOCATION_MAINBODY_MOVABLE = 2;

    /**
     * A microphone that locate on a peripheral.
     */
    public static final int LOCATION_PERIPHERAL = 3;

    /**
     * A microphone that the location is unknown.
     */
    public static final int LOCATION_UNKNOWN = 0;

    /**
     * Value used for when the sensitivity of the microphone is unknown.
     */
    public static final float SENSITIVITY_UNKNOWN = -3.4028235E38f;

    /**
     * Value used for when the SPL of the microphone is unknown. This value could be used when
     * maximum SPL or minimum SPL is unknown.
     */
    public static final float SPL_UNKNOWN = -3.4028235E38f;

    private MicrophoneInfo() {
    }
}
