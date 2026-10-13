package android.speech;

/**
 * This class provides access to the speech recognition service. This service allows access to the
 * speech recognizer. Do not instantiate this class directly, instead, call
 * {@link SpeechRecognizer#createSpeechRecognizer(Context)}, or
 * {@link SpeechRecognizer#createOnDeviceSpeechRecognizer(Context)}. This class's methods must be
 * invoked only from the main application thread.
 *
 * <p><strong>Important:</strong> the caller MUST invoke {@link #destroy()} on a
 * SpeechRecognizer object when it is no longer needed.
 *
 * <p>The implementation of this API is likely to stream audio to remote servers to perform speech
 * recognition. As such this API is not intended to be used for continuous recognition, which would
 * consume a significant amount of battery and bandwidth.
 *
 * <p>Please note that the application must have {@link android.Manifest.permission#RECORD_AUDIO}
 * permission to use this class.
 */
public final class SpeechRecognizer {
    /**
     * Key used to retrieve a float array from the {@link Bundle} passed to the
     * {@link RecognitionListener#onResults(Bundle)} and
     * {@link RecognitionListener#onPartialResults(Bundle)} methods. The array should be
     * the same size as the ArrayList provided in {@link #RESULTS_RECOGNITION}, and should contain
     * values ranging from 0.0 to 1.0, or -1 to represent an unavailable confidence score.
     * <p>
     * Confidence values close to 1.0 indicate high confidence (the speech recognizer is confident
     * that the recognition result is correct), while values close to 0.0 indicate low confidence.
     * <p>
     * This value is optional and might not be provided.
     */
    public static final String CONFIDENCE_SCORES = "confidence_scores";

    /**
     * Key used to retrieve a {@link String} representation of the IETF language tag (as defined by
     * BCP 47, e.g., "en-US", "de-DE") of the detected language of the most recent audio chunk.
     *
     * <p> This info is returned to the client in the {@link Bundle} passed to
     * {@link RecognitionListener#onLanguageDetection(Bundle)} only if
     * {@link RecognizerIntent#EXTRA_ENABLE_LANGUAGE_DETECTION} is set. Additionally, if
     * {@link RecognizerIntent#EXTRA_LANGUAGE_DETECTION_ALLOWED_LANGUAGES} are listed,
     * the detected language is constrained to be one from the list.
     */
    public static final String DETECTED_LANGUAGE = "detected_language";

    /**
     * Audio recording error.
     */
    public static final int ERROR_AUDIO = 3;

    /**
     * The service does not allow to check for support.
     */
    public static final int ERROR_CANNOT_CHECK_SUPPORT = 14;

    /**
     * The service does not support listening to model downloads events.
     */
    public static final int ERROR_CANNOT_LISTEN_TO_DOWNLOAD_EVENTS = 15;

    /**
     * Other client side errors.
     */
    public static final int ERROR_CLIENT = 5;

    /**
     * Insufficient permissions
     */
    public static final int ERROR_INSUFFICIENT_PERMISSIONS = 9;

    /**
     * Requested language is not available to be used with the current recognizer.
     */
    public static final int ERROR_LANGUAGE_NOT_SUPPORTED = 12;

    /**
     * Requested language is supported, but not available currently (e.g. not downloaded yet).
     */
    public static final int ERROR_LANGUAGE_UNAVAILABLE = 13;

    /**
     * Other network related errors.
     */
    public static final int ERROR_NETWORK = 2;

    /**
     * Network operation timed out.
     */
    public static final int ERROR_NETWORK_TIMEOUT = 1;

    /**
     * No recognition result matched.
     */
    public static final int ERROR_NO_MATCH = 7;

    /**
     * RecognitionService busy.
     */
    public static final int ERROR_RECOGNIZER_BUSY = 8;

    /**
     * Server sends error status.
     */
    public static final int ERROR_SERVER = 4;

    /**
     * Server has been disconnected, e.g. because the app has crashed.
     */
    public static final int ERROR_SERVER_DISCONNECTED = 11;

    /**
     * No speech input
     */
    public static final int ERROR_SPEECH_TIMEOUT = 6;

    /**
     * Too many requests from the same client.
     */
    public static final int ERROR_TOO_MANY_REQUESTS = 10;

    /**
     * Key used to retrieve the level of confidence of the detected language
     * of the most recent audio chunk,
     * represented by an {@code int} value prefixed by {@code LANGUAGE_DETECTION_CONFIDENCE_LEVEL_}.
     *
     * <p> This info is returned to the client in the {@link Bundle} passed to
     * {@link RecognitionListener#onLanguageDetection(Bundle)} only if
     * {@link RecognizerIntent#EXTRA_ENABLE_LANGUAGE_DETECTION} is set.
     */
    public static final String LANGUAGE_DETECTION_CONFIDENCE_LEVEL = "language_detection_confidence_level";

    public static final int LANGUAGE_DETECTION_CONFIDENCE_LEVEL_CONFIDENT = 2;

    public static final int LANGUAGE_DETECTION_CONFIDENCE_LEVEL_HIGHLY_CONFIDENT = 3;

    public static final int LANGUAGE_DETECTION_CONFIDENCE_LEVEL_NOT_CONFIDENT = 1;

    public static final int LANGUAGE_DETECTION_CONFIDENCE_LEVEL_UNKNOWN = 0;

    /**
     * Key used to retrieve the result of the language switch of the most recent audio chunk,
     * represented by an {@code int} value prefixed by {@code LANGUAGE_SWITCH_}.
     *
     * <p> This info is returned to the client in the {@link Bundle} passed to the
     * {@link RecognitionListener#onLanguageDetection(Bundle)} only if
     * {@link RecognizerIntent#EXTRA_ENABLE_LANGUAGE_SWITCH} is set.
     */
    public static final String LANGUAGE_SWITCH_RESULT = "language_switch_result";

    /**
     * Switch attempted and failed.
     */
    public static final int LANGUAGE_SWITCH_RESULT_FAILED = 2;

    /**
     * Switch not attempted.
     */
    public static final int LANGUAGE_SWITCH_RESULT_NOT_ATTEMPTED = 0;

    /**
     * Switch skipped because the language model is missing
     * or the language is not allowlisted for auto switch.
     */
    public static final int LANGUAGE_SWITCH_RESULT_SKIPPED_NO_MODEL = 3;

    /**
     * Switch attempted and succeeded.
     */
    public static final int LANGUAGE_SWITCH_RESULT_SUCCEEDED = 1;

    /**
     * Key used to receive an ArrayList&lt;{@link RecognitionPart}&gt; object from the
     * {@link Bundle} passed to the {@link RecognitionListener#onResults(Bundle)} and
     * {@link RecognitionListener#onSegmentResults(Bundle)} methods.
     *
     * <p> A single {@link SpeechRecognizer} result is represented as a {@link String}. Each word of
     * the resulting String, as well as any potential adjacent punctuation, is represented by a
     * {@link RecognitionPart} item from the ArrayList retrieved by this key.
     */
    public static final String RECOGNITION_PARTS = "recognition_parts";

    /**
     * Key used to retrieve an ArrayList&lt;{@link AlternativeSpans}&gt; from the {@link Bundle}
     * passed to the {@link RecognitionListener#onResults(Bundle)} and
     * {@link RecognitionListener#onPartialResults(Bundle)} methods. The list should be the same
     * size as the ArrayList provided in {@link #RESULTS_RECOGNITION}.
     *
     * <p> A single {@link SpeechRecognizer} result is represented as a {@link String}. For a
     * specific span (substring) of the originally recognized result string the recognizer provides
     * a list of alternative hypotheses in the form of an {@link AlternativeSpan} object.
     * Alternatives for different spans of a result string are listed in an {@link AlternativeSpans}
     * object. Each item from the ArrayList retrieved by this key corresponds to a single result
     * string provided in {@link #RESULTS_RECOGNITION}.
     *
     * <p> This value is optional and might not be provided.
     */
    public static final String RESULTS_ALTERNATIVES = "results_alternatives";

    /**
     * Key used to retrieve an {@code ArrayList<String>} from the {@link Bundle} passed to the
     * {@link RecognitionListener#onResults(Bundle)} and
     * {@link RecognitionListener#onPartialResults(Bundle)} methods. These strings are the possible
     * recognition results, where the first element is the most likely candidate.
     */
    public static final String RESULTS_RECOGNITION = "results_recognition";

    /**
     * Key used to retrieve an ArrayList&lt;{@link String}&gt; containing representations of the
     * IETF language tags (as defined by BCP 47, e.g., "en-US", "en-UK") denoting the alternative
     * locales for the same language retrieved by the key {@link #DETECTED_LANGUAGE}.
     *
     * This info is returned to the client in the {@link Bundle} passed to
     * {@link RecognitionListener#onLanguageDetection(Bundle)} only if
     * {@link RecognizerIntent#EXTRA_ENABLE_LANGUAGE_DETECTION} is set.
     */
    public static final String TOP_LOCALE_ALTERNATIVES = "top_locale_alternatives";

    private SpeechRecognizer() {
    }
}
