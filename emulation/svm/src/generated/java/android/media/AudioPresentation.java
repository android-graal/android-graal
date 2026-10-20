package android.media;

import android.os.Parcelable;

/**
 * The AudioPresentation class encapsulates the information that describes an audio presentation
 * which is available in next generation audio content.
 *
 * Used by {@link MediaExtractor} {@link MediaExtractor#getAudioPresentations(int)} and
 * {@link AudioTrack} {@link AudioTrack#setPresentation(AudioPresentation)} to query available
 * presentations and to select one, respectively.
 *
 * A list of available audio presentations in a media source can be queried using
 * {@link MediaExtractor#getAudioPresentations(int)}. This list can be presented to a user for
 * selection.
 * An AudioPresentation can be passed to an offloaded audio decoder via
 * {@link AudioTrack#setPresentation(AudioPresentation)} to request decoding of the selected
 * presentation. An audio stream may contain multiple presentations that differ by language,
 * accessibility, end point mastering and dialogue enhancement. An audio presentation may also have
 * a set of description labels in different languages to help the user to make an informed
 * selection.
 *
 * Applications that parse media streams and extract presentation information on their own
 * can create instances of AudioPresentation by using {@link AudioPresentation.Builder} class.
 */
public final class AudioPresentation implements Parcelable {
    /**
     * Audio presentation content classifier: Commentary.
     */
    public static final int CONTENT_COMMENTARY = 5;

    /**
     * Audio presentation content classifier: Dialog.
     */
    public static final int CONTENT_DIALOG = 4;

    /**
     * Audio presentation content classifier: Emergency.
     */
    public static final int CONTENT_EMERGENCY = 6;

    /**
     * Audio presentation content classifier: Hearing impaired.
     */
    public static final int CONTENT_HEARING_IMPAIRED = 3;

    /**
     * Audio presentation classifier: Complete main.
     */
    public static final int CONTENT_MAIN = 0;

    /**
     * Audio presentation content classifier: Music and effects.
     */
    public static final int CONTENT_MUSIC_AND_EFFECTS = 1;

    /**
     * Audio presentation classifier: Unknown.
     */
    public static final int CONTENT_UNKNOWN = -1;

    /**
     * Audio presentation content classifier: Visually impaired.
     */
    public static final int CONTENT_VISUALLY_IMPAIRED = 2;

    /**
     * Audio presentation content classifier: Voice over.
     */
    public static final int CONTENT_VOICEOVER = 7;

    /**
     * Three-dimensional (e.g. 5.1.2) speaker layout.
     *
     * @see Builder#setMasteringIndication(int)
     */
    public static final int MASTERED_FOR_3D = 3;

    /**
     * Prerendered for headphone playback.
     *
     * @see Builder#setMasteringIndication(int)
     */
    public static final int MASTERED_FOR_HEADPHONE = 4;

    /**
     * Stereo speaker layout.
     *
     * @see Builder#setMasteringIndication(int)
     */
    public static final int MASTERED_FOR_STEREO = 1;

    /**
     * Two-dimensional (e.g. 5.1) speaker layout.
     *
     * @see Builder#setMasteringIndication(int)
     */
    public static final int MASTERED_FOR_SURROUND = 2;

    /**
     * No preferred reproduction channel layout.
     *
     * @see Builder#setMasteringIndication(int)
     */
    public static final int MASTERING_NOT_INDICATED = 0;

    /**
     * Unknown audio presentation ID, this indicates audio presentation ID is not selected.
     */
    public static final int PRESENTATION_ID_UNKNOWN = -1;

    /**
     * Unknown audio program ID, this indicates audio program ID is not selected.
     */
    public static final int PROGRAM_ID_UNKNOWN = -1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private AudioPresentation() {
    }
}
