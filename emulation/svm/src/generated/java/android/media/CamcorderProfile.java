package android.media;

/**
 * Retrieves the
 * predefined camcorder profile settings for camcorder applications.
 * These settings are read-only.
 *
 * <p>The compressed output from a recording session with a given
 * CamcorderProfile contains two tracks: one for audio and one for video.
 *
 * <p>Each profile specifies the following set of parameters:
 * <ul>
 * <li> The file output format
 * <li> Video codec format
 * <li> Video bit rate in bits per second
 * <li> Video frame rate in frames per second
 * <li> Video frame width and height,
 * <li> Audio codec format
 * <li> Audio bit rate in bits per second,
 * <li> Audio sample rate
 * <li> Number of audio channels for recording.
 * </ul>
 */
public final class CamcorderProfile {
    /**
     * Quality level corresponding to the 1080p (1920 x 1080) resolution.
     * Note that the vertical resolution for 1080p can also be 1088,
     * instead of 1080 (used by some vendors to avoid cropping during
     * video playback).
     */
    public static final int QUALITY_1080P = 6;

    /**
     * Quality level corresponding to the 2160p (3840x2160) resolution.
     */
    public static final int QUALITY_2160P = 8;

    /**
     * Quality level corresponding to 2K (2048 x 1080) resolution
     */
    public static final int QUALITY_2K = 12;

    /**
     * Quality level corresponding to the 480p (720 x 480) resolution.
     * Note that the horizontal resolution for 480p can also be other
     * values, such as 640 or 704, instead of 720.
     */
    public static final int QUALITY_480P = 4;

    /**
     * Quality level corresponding to 4k-DCI (4096 x 2160) resolution.
     */
    public static final int QUALITY_4KDCI = 10;

    /**
     * Quality level corresponding to the 720p (1280 x 720) resolution.
     */
    public static final int QUALITY_720P = 5;

    /**
     * Quality level corresponding to 8K UHD (7680 x 4320) resolution
     */
    public static final int QUALITY_8KUHD = 13;

    /**
     * Quality level corresponding to the cif (352 x 288) resolution.
     */
    public static final int QUALITY_CIF = 3;

    /**
     * Quality level corresponding to the highest available resolution.
     */
    public static final int QUALITY_HIGH = 1;

    /**
     * High speed ( >= 100fps) quality level corresponding to the 1080p (1920 x 1080 or 1920x1088)
     * resolution.
     */
    public static final int QUALITY_HIGH_SPEED_1080P = 2004;

    /**
     * High speed ( >= 100fps) quality level corresponding to the 2160p (3840 x 2160)
     * resolution.
     */
    public static final int QUALITY_HIGH_SPEED_2160P = 2005;

    /**
     * High speed ( >= 100fps) quality level corresponding to the 480p (720 x 480) resolution.
     *
     * Note that the horizontal resolution for 480p can also be other
     * values, such as 640 or 704, instead of 720.
     */
    public static final int QUALITY_HIGH_SPEED_480P = 2002;

    /**
     * High speed ( >= 100fps) quality level corresponding to the 4K-DCI (4096 x 2160)
     */
    public static final int QUALITY_HIGH_SPEED_4KDCI = 2008;

    /**
     * High speed ( >= 100fps) quality level corresponding to the 720p (1280 x 720) resolution.
     */
    public static final int QUALITY_HIGH_SPEED_720P = 2003;

    /**
     * High speed ( >= 100fps) quality level corresponding to the CIF (352 x 288)
     */
    public static final int QUALITY_HIGH_SPEED_CIF = 2006;

    /**
     * High speed ( >= 100fps) quality level corresponding to the highest available resolution.
     */
    public static final int QUALITY_HIGH_SPEED_HIGH = 2001;

    /**
     * High speed ( >= 100fps) quality level corresponding to the lowest available resolution.
     * <p>
     * For all the high speed profiles defined below ((from {@link #QUALITY_HIGH_SPEED_LOW} to
     * {@link #QUALITY_HIGH_SPEED_2160P}), they are similar as normal recording profiles, with just
     * higher output frame rate and bit rate. Therefore, setting these profiles with
     * {@link MediaRecorder#setProfile} without specifying any other encoding parameters will
     * produce high speed videos rather than slow motion videos that have different capture and
     * output (playback) frame rates. To record slow motion videos, the application must set video
     * output (playback) frame rate and bit rate appropriately via
     * {@link MediaRecorder#setVideoFrameRate} and {@link MediaRecorder#setVideoEncodingBitRate}
     * based on the slow motion factor. If the application intends to do the video recording with
     * {@link MediaCodec} encoder, it must set each individual field of {@link MediaFormat}
     * similarly according to this CamcorderProfile.
     * </p>
     *
     * @see #videoBitRate
     * @see #videoFrameRate
     * @see MediaRecorder
     * @see MediaCodec
     * @see MediaFormat
     */
    public static final int QUALITY_HIGH_SPEED_LOW = 2000;

    /**
     * High speed ( >= 100fps) quality level corresponding to the VGA (640 x 480)
     */
    public static final int QUALITY_HIGH_SPEED_VGA = 2007;

    /**
     * Quality level corresponding to the lowest available resolution.
     */
    public static final int QUALITY_LOW = 0;

    /**
     * Quality level corresponding to the qcif (176 x 144) resolution.
     */
    public static final int QUALITY_QCIF = 2;

    /**
     * Quality level corresponding to QHD (2560 x 1440) resolution
     */
    public static final int QUALITY_QHD = 11;

    /**
     * Quality level corresponding to the QVGA (320x240) resolution.
     */
    public static final int QUALITY_QVGA = 7;

    /**
     * Time lapse quality level corresponding to the 1080p (1920 x 1088) resolution.
     */
    public static final int QUALITY_TIME_LAPSE_1080P = 1006;

    /**
     * Time lapse quality level corresponding to the 2160p (3840 x 2160) resolution.
     */
    public static final int QUALITY_TIME_LAPSE_2160P = 1008;

    /**
     * Time lapse quality level corresponding to the 2K (2048 x 1080) resolution.
     */
    public static final int QUALITY_TIME_LAPSE_2K = 1012;

    /**
     * Time lapse quality level corresponding to the 480p (720 x 480) resolution.
     */
    public static final int QUALITY_TIME_LAPSE_480P = 1004;

    /**
     * Time lapse quality level corresponding to the 4k-DCI (4096 x 2160) resolution.
     */
    public static final int QUALITY_TIME_LAPSE_4KDCI = 1010;

    /**
     * Time lapse quality level corresponding to the 720p (1280 x 720) resolution.
     */
    public static final int QUALITY_TIME_LAPSE_720P = 1005;

    /**
     * Time lapse quality level corresponding to the 8K UHD (7680 x 4320) resolution.
     */
    public static final int QUALITY_TIME_LAPSE_8KUHD = 1013;

    /**
     * Time lapse quality level corresponding to the cif (352 x 288) resolution.
     */
    public static final int QUALITY_TIME_LAPSE_CIF = 1003;

    /**
     * Time lapse quality level corresponding to the highest available resolution.
     */
    public static final int QUALITY_TIME_LAPSE_HIGH = 1001;

    /**
     * Time lapse quality level corresponding to the lowest available resolution.
     */
    public static final int QUALITY_TIME_LAPSE_LOW = 1000;

    /**
     * Time lapse quality level corresponding to the qcif (176 x 144) resolution.
     */
    public static final int QUALITY_TIME_LAPSE_QCIF = 1002;

    /**
     * Time lapse quality level corresponding to the QHD (2560 x 1440) resolution.
     */
    public static final int QUALITY_TIME_LAPSE_QHD = 1011;

    /**
     * Time lapse quality level corresponding to the QVGA (320 x 240) resolution.
     */
    public static final int QUALITY_TIME_LAPSE_QVGA = 1007;

    /**
     * Time lapse quality level corresponding to the VGA (640 x 480) resolution.
     */
    public static final int QUALITY_TIME_LAPSE_VGA = 1009;

    /**
     * Quality level corresponding to the VGA (640 x 480) resolution.
     */
    public static final int QUALITY_VGA = 9;

    private CamcorderProfile() {
    }
}
