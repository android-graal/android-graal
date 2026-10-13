package android.media;

import android.os.Parcelable;

/**
 * An AudioProfile is specific to an audio format and lists supported sampling rates and
 * channel masks for that format.  An {@link AudioDeviceInfo} has a list of supported AudioProfiles.
 * There can be multiple profiles whose encoding format is the same. This usually happens when
 * an encoding format is only supported when it is encapsulated by some particular encapsulation
 * types. If there are multiple encapsulation types that can carry this encoding format, they will
 * be reported in different audio profiles. The application can choose any of the encapsulation
 * types.
 */
public final class AudioProfile implements Parcelable {
    /**
     * Encapsulation format is defined in standard IEC 61937.
     */
    public static final int AUDIO_ENCAPSULATION_TYPE_IEC61937 = 1;

    /**
     * No encapsulation type is specified.
     */
    public static final int AUDIO_ENCAPSULATION_TYPE_NONE = 0;

    /**
     * Encapsulation format is PCM, which can be used by other formats that can be wrapped in
     * a PCM frame, such as DSD(Direct Stream Digital).
     */
    public static final int AUDIO_ENCAPSULATION_TYPE_PCM = 2;

    private AudioProfile() {
    }
}
