package android.service.voice;

import android.content.ComponentCallbacks2;

/**
 * An active voice interaction session, providing a facility for the implementation
 * to interact with the user in the voice interaction layer. The user interface is
 * initially shown by default, and can be created by overriding {@link #onCreateContentView()}
 * in which the UI can be built.
 *
 * <p>A voice interaction session can be self-contained, ultimately calling {@link #finish}
 * when done. It can also initiate voice interactions with applications by calling
 * {@link #startVoiceActivity}</p>.
 */
public final class VoiceInteractionSession implements ComponentCallbacks2 {
    /**
     * Bundle key used to specify foreground activity app components.
     * <p>
     * Type: ArrayList&ltComponentName&gt
     * </p>
     * @see #onShow(Bundle, int)
     */
    public static final String KEY_FOREGROUND_ACTIVITIES = "android.service.voice.FOREGROUND_ACTIVITIES";

    /**
     * Bundle key used to specify the id when the system prepares to show session. It increases for
     * each request.
     * <p>
     * Type: int
     * </p>
     * @see VoiceInteractionService#showSession(Bundle, int)
     * @see VoiceInteractionService#onPrepareToShowSession(Bundle, int)
     * @see VoiceInteractionService#onShowSessionFailed(Bundle)
     * @see #onShow(Bundle, int)
     * @see #show(Bundle, int)
     */
    public static final String KEY_SHOW_SESSION_ID = "android.service.voice.SHOW_SESSION_ID";

    /**
     * Flag for use with {@link #onShow}: indicates that an Activity has invoked the voice
     * interaction service for a local interaction using
     * {@link Activity#startLocalVoiceInteraction(Bundle)}.
     */
    public static final int SHOW_SOURCE_ACTIVITY = 16;

    /**
     * Flag for use with {@link #onShow}: indicates that the application itself has invoked
     * the assistant.
     */
    public static final int SHOW_SOURCE_APPLICATION = 8;

    /**
     * Flag for use with {@link #onShow}: indicates that the session has been started from the
     * system assist gesture.
     */
    public static final int SHOW_SOURCE_ASSIST_GESTURE = 4;

    /**
     * Flag for use with {@link #onShow}: indicates that the voice interaction service was invoked
     * from an Android automotive system UI.
     */
    public static final int SHOW_SOURCE_AUTOMOTIVE_SYSTEM_UI = 128;

    /**
     * Flag for use with {@link #onShow}: indicates that the voice interaction service was invoked
     * from a notification.
     */
    public static final int SHOW_SOURCE_NOTIFICATION = 64;

    /**
     * Flag for use with {@link #onShow}: indicates that the voice interaction service was invoked
     * from a physical button.
     */
    public static final int SHOW_SOURCE_PUSH_TO_TALK = 32;

    /**
     * Flag received in {@link #onShow}: originator requested that the session be started with
     * assist data from the currently focused activity.
     */
    public static final int SHOW_WITH_ASSIST = 1;

    /**
     * Flag received in {@link #onShow}: originator requested that the session be started with
     * a screen shot of the currently focused activity.
     */
    public static final int SHOW_WITH_SCREENSHOT = 2;

    private VoiceInteractionSession() {
    }

    /**
     * Information about where interesting parts of the input method UI appear.
     */
    public static final class Insets {
        /**
         * Option for {@link #touchableInsets}: the area inside of
         * the content insets can be touched.
         */
        public static final int TOUCHABLE_INSETS_CONTENT = 1;

        /**
         * Option for {@link #touchableInsets}: the entire window frame
         * can be touched.
         */
        public static final int TOUCHABLE_INSETS_FRAME = 0;

        /**
         * Option for {@link #touchableInsets}: the region specified by
         * {@link #touchableRegion} can be touched.
         */
        public static final int TOUCHABLE_INSETS_REGION = 3;

        private Insets() {
        }
    }
}
