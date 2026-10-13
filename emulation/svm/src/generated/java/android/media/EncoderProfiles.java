package android.media;

/**
 * Describes a set of encoding profiles for a given media (audio and/or video) profile.
 * These settings are read-only.
 *
 * <p>Currently, this is used to describe camera recording profile with more detail than {@link
 * CamcorderProfile}, by providing encoding parameters for more than just the default audio
 * and/or video codec.
 *
 * <p>The compressed output from a camera recording session contains two tracks:
 * one for audio and one for video.
 * <p>In the future audio-only recording profiles may be defined.
 *
 * <p>Each media profile specifies a set of audio and a set of video specific settings.
 * <ul>
 * <li> The file output format
 * <li> Default file duration
 * <p>Video-specific settings are:
 * <li> Video codec format
 * <li> Video bit rate in bits per second
 * <li> Video frame rate in frames per second
 * <li> Video frame width and height,
 * <li> Video encoder profile.
 * <p>Audio-specific settings are:
 * <li> Audio codec format
 * <li> Audio bit rate in bits per second,
 * <li> Audio sample rate
 * <li> Number of audio channels for recording.
 * </ul>
 */
public final class EncoderProfiles {
    private EncoderProfiles() {
    }

    /**
     * Configuration for a video encoder.
     */
    public static final class VideoProfile {
        /**
         *  Dolby Vision
         *  <p>
         *  An HDR format specifying Dolby Vision. For this format
         *  the codec is always a Dolby Vision encoder. The encoder
         *  profile specifies which Dolby Vision version is being
         *  used.
         *
         *  @see #getProfile
         */
        public static final int HDR_DOLBY_VISION = 4;

        /**
         * HDR10.
         *   <p>
         *   An HDR format specifying HDR10.
         */
        public static final int HDR_HDR10 = 2;

        /**
         * HDR10+.
         *   <p>
         *   An HDR format specifying HDR10+.
         */
        public static final int HDR_HDR10PLUS = 3;

        /**
         * HLG (Hybrid-Log Gamma).
         *   <p>
         *   An HDR format specifying HLG.
         */
        public static final int HDR_HLG = 1;

        /**
         * Not HDR (SDR).
         *   <p>
         *   An HDR format specifying SDR (Standard Dynamic
         *   Range) recording.
         */
        public static final int HDR_NONE = 0;

        /**
         * YUV 4:2:0.
         *   <p>
         *   A chroma subsampling where the U and V planes are subsampled
         *   by 2 both horizontally and vertically.
         */
        public static final int YUV_420 = 0;

        /**
         * YUV 4:2:2.
         *   <p>
         *   A chroma subsampling where the U and V planes are subsampled
         *   by 2 horizontally alone.
         */
        public static final int YUV_422 = 1;

        /**
         * YUV 4:4:4.
         *   <p>
         *   A chroma subsampling where the U and V planes are not
         *   subsampled.
         */
        public static final int YUV_444 = 2;

        private VideoProfile() {
        }
    }
}
