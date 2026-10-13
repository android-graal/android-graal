package android.speech.tts;

/**
 *
 * Synthesizes speech from text for immediate playback or to create a sound file.
 * <p>A TextToSpeech instance can only be used to synthesize text once it has completed its
 * initialization. Implement the {@link TextToSpeech.OnInitListener} to be
 * notified of the completion of the initialization.<br>
 * When you are done using the TextToSpeech instance, call the {@link #shutdown()} method
 * to release the native resources used by the TextToSpeech engine.
 *
 * Apps targeting Android 11 that use text-to-speech should declare {@link
 * TextToSpeech.Engine#INTENT_ACTION_TTS_SERVICE} in the {@code queries} elements of their
 * manifest:
 *
 * <pre>
 * &lt;queries&gt;
 *   ...
 *  &lt;intent&gt;
 *      &lt;action android:name="android.intent.action.TTS_SERVICE" /&gt;
 *  &lt;/intent&gt;
 * &lt;/queries&gt;
 * </pre>
 */
public final class TextToSpeech {
    /**
     * Broadcast Action: The TextToSpeech synthesizer has completed processing
     * of all the text in the speech queue.
     *
     * Note that this notifies callers when the <b>engine</b> has finished has
     * processing text data. Audio playback might not have completed (or even started)
     * at this point. If you wish to be notified when this happens, see
     * {@link OnUtteranceCompletedListener}.
     */
    public static final String ACTION_TTS_QUEUE_PROCESSING_COMPLETED = "android.speech.tts.TTS_QUEUE_PROCESSING_COMPLETED";

    /**
     * Denotes a generic operation failure.
     */
    public static final int ERROR = -1;

    /**
     * Denotes a failure caused by an invalid request.
     */
    public static final int ERROR_INVALID_REQUEST = -8;

    /**
     * Denotes a failure caused by a network connectivity problems.
     */
    public static final int ERROR_NETWORK = -6;

    /**
     * Denotes a failure caused by network timeout.
     */
    public static final int ERROR_NETWORK_TIMEOUT = -7;

    /**
     * Denotes a failure caused by an unfinished download of the voice data.
     * @see Engine#KEY_FEATURE_NOT_INSTALLED
     */
    public static final int ERROR_NOT_INSTALLED_YET = -9;

    /**
     * Denotes a failure related to the output (audio device or a file).
     */
    public static final int ERROR_OUTPUT = -5;

    /**
     * Denotes a failure of a TTS service.
     */
    public static final int ERROR_SERVICE = -4;

    /**
     * Denotes a failure of a TTS engine to synthesize the given input.
     */
    public static final int ERROR_SYNTHESIS = -3;

    /**
     * Denotes the language is available for the language by the locale,
     * but not the country and variant.
     */
    public static final int LANG_AVAILABLE = 0;

    /**
     * Denotes the language is available for the language and country specified
     * by the locale, but not the variant.
     */
    public static final int LANG_COUNTRY_AVAILABLE = 1;

    /**
     * Denotes the language is available exactly as specified by the locale.
     */
    public static final int LANG_COUNTRY_VAR_AVAILABLE = 2;

    /**
     * Denotes the language data is missing.
     */
    public static final int LANG_MISSING_DATA = -1;

    /**
     * Denotes the language is not supported.
     */
    public static final int LANG_NOT_SUPPORTED = -2;

    /**
     * Queue mode where the new entry is added at the end of the playback queue.
     */
    public static final int QUEUE_ADD = 1;

    /**
     * Queue mode where all entries in the playback queue (media to be played
     * and text to be synthesized) are dropped and replaced by the new entry.
     * Queues are flushed with respect to a given calling app. Entries in the queue
     * from other callees are not discarded.
     */
    public static final int QUEUE_FLUSH = 0;

    /**
     * Denotes a stop requested by a client. It's used only on the service side of the API,
     * client should never expect to see this result code.
     */
    public static final int STOPPED = -2;

    /**
     * Denotes a successful operation.
     */
    public static final int SUCCESS = 0;

    private TextToSpeech() {
    }

    /**
     * Constants and parameter names for controlling text-to-speech. These include:
     *
     * <ul>
     *     <li>
     *         Intents to ask engine to install data or check its data and
     *         extras for a TTS engine's check data activity.
     *     </li>
     *     <li>
     *         Keys for the parameters passed with speak commands, e.g.
     *         {@link Engine#KEY_PARAM_UTTERANCE_ID}, {@link Engine#KEY_PARAM_STREAM}.
     *     </li>
     *     <li>
     *         A list of feature strings that engines might support, e.g
     *         {@link Engine#KEY_FEATURE_NETWORK_SYNTHESIS}. These values may be passed in to
     *         {@link TextToSpeech#speak} and {@link TextToSpeech#synthesizeToFile} to modify
     *         engine behaviour. The engine can be queried for the set of features it supports
     *         through {@link TextToSpeech#getFeatures(java.util.Locale)}.
     *     </li>
     * </ul>
     *
     * Apps targeting Android 11 that use text-to-speech should declare {@link
     * TextToSpeech.Engine#INTENT_ACTION_TTS_SERVICE} in the {@code queries} elements of their
     * manifest:
     *
     * <pre>
     * &lt;queries&gt;
     *   ...
     *  &lt;intent&gt;
     *      &lt;action android:name="android.intent.action.TTS_SERVICE" /&gt;
     *  &lt;/intent&gt;
     * &lt;/queries&gt;
     * </pre>
     */
    public static final class Engine {
        /**
         * Activity Action: Starts the activity from the platform TextToSpeech
         * engine to verify the proper installation and availability of the
         * resource files on the system. Upon completion, the activity will
         * return one of the following codes:
         * {@link #CHECK_VOICE_DATA_PASS},
         * {@link #CHECK_VOICE_DATA_FAIL},
         * <p> Moreover, the data received in the activity result will contain the following
         * fields:
         * <ul>
         *   <li>{@link #EXTRA_AVAILABLE_VOICES} which contains an ArrayList<String> of all the
         *   available voices. The format of each voice is: lang-COUNTRY-variant where COUNTRY and
         *   variant are optional (ie, "eng" or "eng-USA" or "eng-USA-FEMALE").</li>
         *   <li>{@link #EXTRA_UNAVAILABLE_VOICES} which contains an ArrayList<String> of all the
         *   unavailable voices (ones that user can install). The format of each voice is:
         *   lang-COUNTRY-variant where COUNTRY and variant are optional (ie, "eng" or
         *   "eng-USA" or "eng-USA-FEMALE").</li>
         * </ul>
         */
        public static final String ACTION_CHECK_TTS_DATA = "android.speech.tts.engine.CHECK_TTS_DATA";

        /**
         * Activity intent for getting some sample text to use for demonstrating TTS. Specific
         * locale have to be requested by passing following extra parameters:
         * <ul>
         *   <li>language</li>
         *   <li>country</li>
         *   <li>variant</li>
         * </ul>
         *
         * Upon completion, the activity result may contain the following fields:
         * <ul>
         *   <li>{@link #EXTRA_SAMPLE_TEXT} which contains an String with sample text.</li>
         * </ul>
         */
        public static final String ACTION_GET_SAMPLE_TEXT = "android.speech.tts.engine.GET_SAMPLE_TEXT";

        /**
         * Activity Action: Triggers the platform TextToSpeech engine to
         * start the activity that installs the resource files on the device
         * that are required for TTS to be operational. Since the installation
         * of the data can be interrupted or declined by the user, the application
         * shouldn't expect successful installation upon return from that intent,
         * and if need be, should check installation status with
         * {@link #ACTION_CHECK_TTS_DATA}.
         */
        public static final String ACTION_INSTALL_TTS_DATA = "android.speech.tts.engine.INSTALL_TTS_DATA";

        /**
         * Broadcast Action: broadcast to signal the change in the list of available
         * languages or/and their features.
         */
        public static final String ACTION_TTS_DATA_INSTALLED = "android.speech.tts.engine.TTS_DATA_INSTALLED";

        /**
         * Indicates erroneous data when checking the installation status of the resources used by
         * the TextToSpeech engine with the {@link #ACTION_CHECK_TTS_DATA} intent.
         *
         * @deprecated Use CHECK_VOICE_DATA_FAIL instead.
         */
        @Deprecated
        public static final int CHECK_VOICE_DATA_BAD_DATA = -1;

        /**
         * Indicates failure when checking the installation status of the resources used by the
         * TextToSpeech engine with the {@link #ACTION_CHECK_TTS_DATA} intent.
         */
        public static final int CHECK_VOICE_DATA_FAIL = 0;

        /**
         * Indicates missing resources when checking the installation status of the resources used
         * by the TextToSpeech engine with the {@link #ACTION_CHECK_TTS_DATA} intent.
         *
         * @deprecated Use CHECK_VOICE_DATA_FAIL instead.
         */
        @Deprecated
        public static final int CHECK_VOICE_DATA_MISSING_DATA = -2;

        /**
         * Indicates missing storage volume when checking the installation status of the resources
         * used by the TextToSpeech engine with the {@link #ACTION_CHECK_TTS_DATA} intent.
         *
         * @deprecated Use CHECK_VOICE_DATA_FAIL instead.
         */
        @Deprecated
        public static final int CHECK_VOICE_DATA_MISSING_VOLUME = -3;

        /**
         * Indicates success when checking the installation status of the resources used by the
         * TextToSpeech engine with the {@link #ACTION_CHECK_TTS_DATA} intent.
         */
        public static final int CHECK_VOICE_DATA_PASS = 1;

        /**
         * Default audio stream used when playing synthesized speech.
         */
        public static final int DEFAULT_STREAM = 3;

        /**
         * Extra information received with the {@link #ACTION_CHECK_TTS_DATA} intent result where
         * the TextToSpeech engine returns an ArrayList<String> of all the available voices.
         * The format of each voice is: lang-COUNTRY-variant where COUNTRY and variant are
         * optional (ie, "eng" or "eng-USA" or "eng-USA-FEMALE").
         */
        public static final String EXTRA_AVAILABLE_VOICES = "availableVoices";

        /**
         * Extra information sent with the {@link #ACTION_CHECK_TTS_DATA} intent where the
         * caller indicates to the TextToSpeech engine which specific sets of voice data to
         * check for by sending an ArrayList<String> of the voices that are of interest.
         * The format of each voice is: lang-COUNTRY-variant where COUNTRY and variant are
         * optional (ie, "eng" or "eng-USA" or "eng-USA-FEMALE").
         *
         * @deprecated Redundant functionality, checking for existence of specific sets of voice
         * data can be done on client side.
         */
        @Deprecated
        public static final String EXTRA_CHECK_VOICE_DATA_FOR = "checkVoiceDataFor";

        /**
         * Extra information received with the {@link #ACTION_GET_SAMPLE_TEXT} intent result where
         * the TextToSpeech engine returns an String with sample text for requested voice
         */
        public static final String EXTRA_SAMPLE_TEXT = "sampleText";

        /**
         * Extra information received with the {@link #ACTION_TTS_DATA_INSTALLED} intent result.
         * It indicates whether the data files for the synthesis engine were successfully
         * installed. The installation was initiated with the  {@link #ACTION_INSTALL_TTS_DATA}
         * intent. The possible values for this extra are
         * {@link TextToSpeech#SUCCESS} and {@link TextToSpeech#ERROR}.
         *
         * @deprecated No longer in use. If client is interested in information about what
         * changed, it should use the ACTION_CHECK_TTS_DATA
         * intent to discover available voices.
         */
        @Deprecated
        public static final String EXTRA_TTS_DATA_INSTALLED = "dataInstalled";

        /**
         * Extra information received with the {@link #ACTION_CHECK_TTS_DATA} intent result where
         * the TextToSpeech engine returns an ArrayList<String> of all the unavailable voices.
         * The format of each voice is: lang-COUNTRY-variant where COUNTRY and variant are
         * optional (ie, "eng" or "eng-USA" or "eng-USA-FEMALE").
         */
        public static final String EXTRA_UNAVAILABLE_VOICES = "unavailableVoices";

        /**
         * Extra information received with the {@link #ACTION_CHECK_TTS_DATA} intent result where
         * the TextToSpeech engine specifies the file names of its resources under the
         * resource path.
         *
         * @deprecated TTS engine implementation detail, this information has no use for
         * text-to-speech API client.
         */
        @Deprecated
        public static final String EXTRA_VOICE_DATA_FILES = "dataFiles";

        /**
         * Extra information received with the {@link #ACTION_CHECK_TTS_DATA} intent result where
         * the TextToSpeech engine specifies the locale associated with each resource file.
         *
         * @deprecated TTS engine implementation detail, this information has no use for
         * text-to-speech API client.
         */
        @Deprecated
        public static final String EXTRA_VOICE_DATA_FILES_INFO = "dataFilesInfo";

        /**
         * Extra information received with the {@link #ACTION_CHECK_TTS_DATA} intent result where
         * the TextToSpeech engine specifies the path to its resources.
         *
         * It may be used by language packages to find out where to put their data.
         *
         * @deprecated TTS engine implementation detail, this information has no use for
         * text-to-speech API client.
         */
        @Deprecated
        public static final String EXTRA_VOICE_DATA_ROOT_DIRECTORY = "dataRoot";

        /**
         * Intent for starting a TTS service. Services that handle this intent must
         * extend {@link TextToSpeechService}. Normal applications should not use this intent
         * directly, instead they should talk to the TTS service using the the methods in this
         * class.
         */
        public static final String INTENT_ACTION_TTS_SERVICE = "android.intent.action.TTS_SERVICE";

        /**
         * Feature key for embedded synthesis. See {@link TextToSpeech#getFeatures(Locale)}
         * for a description of how feature keys work. If set and supported by the engine
         * as per {@link TextToSpeech#getFeatures(Locale)}, the engine must synthesize
         * text on-device (without making network requests).
         *
         * @see TextToSpeech#speak(CharSequence, int, Bundle, String)
         * @see TextToSpeech#synthesizeToFile(String, java.util.HashMap, String)
         * @see TextToSpeech#getFeatures(java.util.Locale)
         *
         * @deprecated Starting from API level 21, to select embedded synthesis, call
         * ({@link TextToSpeech#getVoices()}, find a suitable embedded voice
         * ({@link Voice#isNetworkConnectionRequired()}) and pass it
         * to {@link TextToSpeech#setVoice(Voice)}).
         */
        @Deprecated
        public static final String KEY_FEATURE_EMBEDDED_SYNTHESIS = "embeddedTts";

        /**
         * Feature key that indicates that network request retries count can be set for the request.
         * If set and supported as per {@link TextToSpeech#getFeatures(Locale)} or
         * {@link Voice#getFeatures()}, it can be used as a request parameter to set the
         * number of network request retries that are attempted in case of failure. When used as
         * a key of a request parameter, its value should be a string with an integer value.
         *
         * @see TextToSpeech#getFeatures(java.util.Locale)
         * @see Voice#getFeatures()
         */
        public static final String KEY_FEATURE_NETWORK_RETRIES_COUNT = "networkRetriesCount";

        /**
         * Feature key for network synthesis. See {@link TextToSpeech#getFeatures(Locale)}
         * for a description of how feature keys work. If set (and supported by the engine
         * as per {@link TextToSpeech#getFeatures(Locale)}, the engine must
         * use network based synthesis.
         *
         * @see TextToSpeech#speak(CharSequence, int, Bundle, String)
         * @see TextToSpeech#synthesizeToFile(String, java.util.HashMap, String)
         * @see TextToSpeech#getFeatures(java.util.Locale)
         *
         * @deprecated Starting from API level 21, to select network synthesis, call
         * {@link TextToSpeech#getVoices()}, find a suitable network voice
         * ({@link Voice#isNetworkConnectionRequired()}) and pass it
         * to {@link TextToSpeech#setVoice(Voice)}.
         */
        @Deprecated
        public static final String KEY_FEATURE_NETWORK_SYNTHESIS = "networkTts";

        /**
         * Feature key that indicate that a network timeout can be set for the request. If set and
         * supported as per {@link TextToSpeech#getFeatures(Locale)} or {@link Voice#getFeatures()},
         * it can be used as request parameter to set the maximum allowed time for a single
         * request attempt, in milliseconds, before synthesis fails. When used as a key of
         * a request parameter, its value should be a string with an integer value.
         *
         * @see TextToSpeech#getFeatures(java.util.Locale)
         * @see Voice#getFeatures()
         */
        public static final String KEY_FEATURE_NETWORK_TIMEOUT_MS = "networkTimeoutMs";

        /**
         * Feature key that indicates that the voice may need to download additional data to be fully
         * functional. The download will be triggered by calling
         * {@link TextToSpeech#setVoice(Voice)} or {@link TextToSpeech#setLanguage(Locale)}.
         * Until download is complete, each synthesis request will either report
         * {@link TextToSpeech#ERROR_NOT_INSTALLED_YET} error, or use a different voice to synthesize
         * the request. This feature should NOT be used as a key of a request parameter.
         *
         * @see TextToSpeech#getFeatures(java.util.Locale)
         * @see Voice#getFeatures()
         */
        public static final String KEY_FEATURE_NOT_INSTALLED = "notInstalled";

        /**
         * Parameter key to specify how the speech is panned from left to right when speaking text.
         * Pan is specified as a float ranging from -1 to +1 where -1 maps to a hard-left pan,
         * 0 to center (the default behavior), and +1 to hard-right.
         *
         * @see TextToSpeech#speak(CharSequence, int, Bundle, String)
         * @see TextToSpeech#playEarcon(String, int, HashMap)
         */
        public static final String KEY_PARAM_PAN = "pan";

        /**
         * Parameter key to specify an audio session identifier (obtained from
         * {@link AudioManager#generateAudioSessionId()}) that will be used by the request audio
         * output. It can be used to associate one of the {@link android.media.audiofx.AudioEffect}
         * objects with the synthesis (or earcon) output.
         *
         * @see TextToSpeech#speak(CharSequence, int, Bundle, String)
         * @see TextToSpeech#playEarcon(String, int, HashMap)
         */
        public static final String KEY_PARAM_SESSION_ID = "sessionId";

        /**
         * Parameter key to specify the audio stream type to be used when speaking text
         * or playing back a file. The value should be one of the STREAM_ constants
         * defined in {@link AudioManager}.
         *
         * @see TextToSpeech#speak(CharSequence, int, Bundle, String)
         * @see TextToSpeech#playEarcon(String, int, HashMap)
         */
        public static final String KEY_PARAM_STREAM = "streamType";

        /**
         * Parameter key to identify an utterance in the
         * {@link TextToSpeech.OnUtteranceCompletedListener} after text has been
         * spoken, a file has been played back or a silence duration has elapsed.
         *
         * @see TextToSpeech#speak(CharSequence, int, Bundle, String)
         * @see TextToSpeech#playEarcon(String, int, HashMap)
         * @see TextToSpeech#synthesizeToFile(String, HashMap, String)
         */
        public static final String KEY_PARAM_UTTERANCE_ID = "utteranceId";

        /**
         * Parameter key to specify the speech volume relative to the current stream type
         * volume used when speaking text. Volume is specified as a float ranging from 0 to 1
         * where 0 is silence, and 1 is the maximum volume (the default behavior).
         *
         * @see TextToSpeech#speak(CharSequence, int, Bundle, String)
         * @see TextToSpeech#playEarcon(String, int, HashMap)
         */
        public static final String KEY_PARAM_VOLUME = "volume";

        /**
         * Name under which a text to speech engine publishes information about itself.
         * This meta-data should reference an XML resource containing a
         * <code>&lt;{@link android.R.styleable#TextToSpeechEngine tts-engine}&gt;</code>
         * tag.
         */
        public static final String SERVICE_META_DATA = "android.speech.tts";

        private Engine() {
        }
    }
}
