package android.media;

import android.os.Parcelable;

/**
 * This API is not generally intended for third party application developers.
 * Use the <a href="{@docRoot}jetpack/androidx.html">AndroidX</a>
 * <a href="{@docRoot}media/media3/session/control-playback">Media3 session
 * Library</a> for consistent behavior across all devices.
 * <p>
 * Define a command that a {@link MediaController2} can send to a {@link MediaSession2}.
 * <p>
 * If {@link #getCommandCode()} isn't {@link #COMMAND_CODE_CUSTOM}), it's predefined command.
 * If {@link #getCommandCode()} is {@link #COMMAND_CODE_CUSTOM}), it's custom command and
 * {@link #getCustomAction()} shouldn't be {@code null}.
 */
public final class Session2Command implements Parcelable {
    /**
     * Command code for the custom command which can be defined by string action in the
     * {@link Session2Command}.
     */
    public static final int COMMAND_CODE_CUSTOM = 0;

    private Session2Command() {
    }

    /**
     * This API is not generally intended for third party application developers.
     * Use the <a href="{@docRoot}jetpack/androidx.html">AndroidX</a>
     * <a href="{@docRoot}media/media3/session/control-playback">Media3 session
     * Library</a> for consistent behavior across all devices.
     * <p>
     * Contains the result of {@link Session2Command}.
     */
    public static final class Result {
        /**
         * Result code represents that call is ended with an unknown error.
         */
        public static final int RESULT_ERROR_UNKNOWN_ERROR = -1;

        /**
         * Result code representing that the command is skipped or canceled. For an example, a seek
         * command can be skipped if it is followed by another seek command.
         */
        public static final int RESULT_INFO_SKIPPED = 1;

        /**
         * Result code representing that the command is successfully completed.
         */
        public static final int RESULT_SUCCESS = 0;

        private Result() {
        }
    }
}
