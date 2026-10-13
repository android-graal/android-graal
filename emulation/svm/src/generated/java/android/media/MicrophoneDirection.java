package android.media;

/**
 * Interface defining mechanism for controlling the directionality and field width of
 * audio capture.
 */
public interface MicrophoneDirection {
    /**
     * Optimize capture for audio coming from the side of the device pointing away from the user.
     * In the typical case, a device with a single screen, screen-side camera/microphone and
     * non-screen-side camera/microphone, this will be the non-screen side.
     * For a different device geometry, it is the side for which the expectation is to be
     * facing away from the user. This is the "taking a video of something else" case.
     */
    int MIC_DIRECTION_AWAY_FROM_USER = 2;

    /**
     * Optimize capture for audio coming from an off-device microphone.
     */
    int MIC_DIRECTION_EXTERNAL = 3;

    /**
     * Optimize capture for audio coming from the side of the device facing the user.
     * In the typical case, a device with a single screen, screen-side camera/microphone and
     * non-screen-side camera/microphone, this will be the screen side (as in a "selfie").
     * For a different device geometry, it is the side for which the expectation is to be
     * facing the user.
     */
    int MIC_DIRECTION_TOWARDS_USER = 1;

    /**
     * Don't do any directionality processing of the activated microphone(s).
     */
    int MIC_DIRECTION_UNSPECIFIED = 0;
}
