package android.speech;

import android.os.Parcelable;

/**
 * Info about a single recognition part.
 *
 * <p> A recognition part represents a recognized word or character, as well as any potential
 * adjacent punctuation, that is returned by the {@link SpeechRecognizer}.
 *
 * <p> Each recognition part is described with a {@link String} denoting the raw text.
 * Additionally, if formatting is enabled with {@link RecognizerIntent#EXTRA_ENABLE_FORMATTING},
 * another {@link String} representation exists denoting the formatted text.
 *
 * <p> If the timestamps are requested with {@link RecognizerIntent#EXTRA_REQUEST_WORD_TIMING}, each
 * recognition part will contain a value representing the offset of the beginning of this part from
 * the start of the recognition session in milliseconds.
 *
 * <p> If the confidence levels are requested with
 * {@link RecognizerIntent#EXTRA_REQUEST_WORD_CONFIDENCE}, each recognition part will contain
 * a value describing the level of recognition confidence.
 */
public final class RecognitionPart implements Parcelable {
    /**
     * Highest level of confidence out of five levels.
     */
    public static final int CONFIDENCE_LEVEL_HIGH = 5;

    /**
     * Lowest level of confidence out of five levels.
     */
    public static final int CONFIDENCE_LEVEL_LOW = 1;

    /**
     * Medium level of confidence out of five levels.
     */
    public static final int CONFIDENCE_LEVEL_MEDIUM = 3;

    /**
     * Second-highest level of confidence out of five levels.
     */
    public static final int CONFIDENCE_LEVEL_MEDIUM_HIGH = 4;

    /**
     * Second-lowest level of confidence out of five levels.
     */
    public static final int CONFIDENCE_LEVEL_MEDIUM_LOW = 2;

    /**
     * Confidence level not requested.
     */
    public static final int CONFIDENCE_LEVEL_UNKNOWN = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private RecognitionPart() {
    }
}
