package android.media;

/**
 * MediaMetadataRetriever class provides a unified interface for retrieving
 * frame and meta data from an input media file.
 */
public final class MediaMetadataRetriever {
    /**
     * The metadata key to retrieve the information about the album title
     * of the data source.
     */
    public static final int METADATA_KEY_ALBUM = 1;

    /**
     * The metadata key to retrieve the information about the performers or
     * artist associated with the data source.
     */
    public static final int METADATA_KEY_ALBUMARTIST = 13;

    /**
     * The metadata key to retrieve the information about the artist of
     * the data source.
     */
    public static final int METADATA_KEY_ARTIST = 2;

    /**
     * The metadata key to retrieve the information about the author of
     * the data source.
     */
    public static final int METADATA_KEY_AUTHOR = 3;

    /**
     * This key retrieves the average bitrate (in bits/sec), if available.
     */
    public static final int METADATA_KEY_BITRATE = 20;

    /**
     * This key retrieves the bits per sample in numbers of bits, if available.
     * This is a signed 32-bit integer formatted as a string in base 10.
     */
    public static final int METADATA_KEY_BITS_PER_SAMPLE = 39;

    /**
     * This key retrieves the original capture framerate, if it's
     * available. The capture framerate will be a floating point
     * number.
     */
    public static final int METADATA_KEY_CAPTURE_FRAMERATE = 25;

    /**
     * The metadata key to retrieve the numeric string describing the
     * order of the audio data source on its original recording.
     */
    public static final int METADATA_KEY_CD_TRACK_NUMBER = 0;

    /**
     * This key retrieves the color range, if available.
     *
     * @see MediaFormat#COLOR_RANGE_LIMITED
     * @see MediaFormat#COLOR_RANGE_FULL
     */
    public static final int METADATA_KEY_COLOR_RANGE = 37;

    /**
     * This key retrieves the color standard, if available.
     *
     * @see MediaFormat#COLOR_STANDARD_BT709
     * @see MediaFormat#COLOR_STANDARD_BT601_PAL
     * @see MediaFormat#COLOR_STANDARD_BT601_NTSC
     * @see MediaFormat#COLOR_STANDARD_BT2020
     */
    public static final int METADATA_KEY_COLOR_STANDARD = 35;

    /**
     * This key retrieves the color transfer, if available.
     *
     * @see MediaFormat#COLOR_TRANSFER_LINEAR
     * @see MediaFormat#COLOR_TRANSFER_SDR_VIDEO
     * @see MediaFormat#COLOR_TRANSFER_ST2084
     * @see MediaFormat#COLOR_TRANSFER_HLG
     */
    public static final int METADATA_KEY_COLOR_TRANSFER = 36;

    /**
     * The metadata key to retrieve the music album compilation status.
     */
    public static final int METADATA_KEY_COMPILATION = 15;

    /**
     * The metadata key to retrieve the information about the composer of
     * the data source.
     */
    public static final int METADATA_KEY_COMPOSER = 4;

    /**
     * The metadata key to retrieve the date when the data source was created
     * or modified.
     */
    public static final int METADATA_KEY_DATE = 5;

    /**
     * The metadata key to retrieve the numberic string that describes which
     * part of a set the audio data source comes from.
     */
    public static final int METADATA_KEY_DISC_NUMBER = 14;

    /**
     * The metadata key to retrieve the playback duration (in ms) of the data source.
     */
    public static final int METADATA_KEY_DURATION = 9;

    /**
     * If the media contains EXIF data, this key retrieves the length (in bytes)
     * of the data.
     */
    public static final int METADATA_KEY_EXIF_LENGTH = 34;

    /**
     * If the media contains EXIF data, this key retrieves the offset (in bytes)
     * of the data.
     */
    public static final int METADATA_KEY_EXIF_OFFSET = 33;

    /**
     * The metadata key to retrieve the content type or genre of the data
     * source.
     */
    public static final int METADATA_KEY_GENRE = 6;

    /**
     * If this key exists the media contains audio content.
     */
    public static final int METADATA_KEY_HAS_AUDIO = 16;

    /**
     * If this key exists the media contains still image content.
     */
    public static final int METADATA_KEY_HAS_IMAGE = 26;

    /**
     * If this key exists the media contains video content.
     */
    public static final int METADATA_KEY_HAS_VIDEO = 17;

    /**
     * If the media contains still images, this key retrieves the number
     * of still images.
     */
    public static final int METADATA_KEY_IMAGE_COUNT = 27;

    /**
     * If the media contains still images, this key retrieves the height
     * of the primary image.
     */
    public static final int METADATA_KEY_IMAGE_HEIGHT = 30;

    /**
     * If the media contains still images, this key retrieves the image
     * index of the primary image.
     */
    public static final int METADATA_KEY_IMAGE_PRIMARY = 28;

    /**
     * If the media contains still images, this key retrieves the rotation
     * angle (in degrees clockwise) of the primary image. The image rotation
     * angle must be one of 0, 90, 180, or 270 degrees.
     */
    public static final int METADATA_KEY_IMAGE_ROTATION = 31;

    /**
     * If the media contains still images, this key retrieves the width
     * of the primary image.
     */
    public static final int METADATA_KEY_IMAGE_WIDTH = 29;

    /**
     * This key retrieves the location information, if available.
     * The location should be specified according to ISO-6709 standard, under
     * a mp4/3gp box "@xyz". Location with longitude of -90 degrees and latitude
     * of 180 degrees will be retrieved as "+180.0000-90.0000/", for instance.
     */
    public static final int METADATA_KEY_LOCATION = 23;

    /**
     * The metadata key to retrieve the mime type of the data source. Some
     * example mime types include: "video/mp4", "audio/mp4", "audio/amr-wb",
     * etc.
     */
    public static final int METADATA_KEY_MIMETYPE = 12;

    /**
     * The metadata key to retrieve the number of tracks, such as audio, video,
     * text, in the data source, such as a mp4 or 3gpp file.
     */
    public static final int METADATA_KEY_NUM_TRACKS = 10;

    /**
     * This key retrieves the sample rate in Hz, if available.
     * This is a signed 32-bit integer formatted as a string in base 10.
     */
    public static final int METADATA_KEY_SAMPLERATE = 38;

    /**
     * The metadata key to retrieve the data source title.
     */
    public static final int METADATA_KEY_TITLE = 7;

    /**
     * If the media contains video and this key exists, it retrieves the
     * total number of frames in the video sequence.
     */
    public static final int METADATA_KEY_VIDEO_FRAME_COUNT = 32;

    /**
     * If the media contains video, this key retrieves its height.
     */
    public static final int METADATA_KEY_VIDEO_HEIGHT = 19;

    /**
     * This key retrieves the video rotation angle in degrees, if available.
     * The video rotation angle may be 0, 90, 180, or 270 degrees.
     */
    public static final int METADATA_KEY_VIDEO_ROTATION = 24;

    /**
     * If the media contains video, this key retrieves its width.
     */
    public static final int METADATA_KEY_VIDEO_WIDTH = 18;

    /**
     * The metadata key to retrieve the information of the writer (such as
     * lyricist) of the data source.
     */
    public static final int METADATA_KEY_WRITER = 11;

    /**
     * If the media contains XMP data, this key retrieves the length (in bytes)
     * of the data.
     */
    public static final int METADATA_KEY_XMP_LENGTH = 42;

    /**
     * If the media contains XMP data, this key retrieves the offset (in bytes)
     * of the data.
     */
    public static final int METADATA_KEY_XMP_OFFSET = 41;

    /**
     * The metadata key to retrieve the year when the data source was created
     * or modified.
     */
    public static final int METADATA_KEY_YEAR = 8;

    /**
     * This option is used with {@link #getFrameAtTime(long, int)} to retrieve
     * a frame (not necessarily a key frame) associated with a data source that
     * is located closest to or at the given time.
     *
     * @see #getFrameAtTime(long, int)
     */
    public static final int OPTION_CLOSEST = 3;

    /**
     * This option is used with {@link #getFrameAtTime(long, int)} to retrieve
     * a sync (or key) frame associated with a data source that is located
     * closest to (in time) or at the given time.
     *
     * @see #getFrameAtTime(long, int)
     */
    public static final int OPTION_CLOSEST_SYNC = 2;

    /**
     * This option is used with {@link #getFrameAtTime(long, int)} to retrieve
     * a sync (or key) frame associated with a data source that is located
     * right after or at the given time.
     *
     * @see #getFrameAtTime(long, int)
     */
    public static final int OPTION_NEXT_SYNC = 1;

    /**
     * This option is used with {@link #getFrameAtTime(long, int)} to retrieve
     * a sync (or key) frame associated with a data source that is located
     * right before or at the given time.
     *
     * @see #getFrameAtTime(long, int)
     */
    public static final int OPTION_PREVIOUS_SYNC = 0;

    private MediaMetadataRetriever() {
    }
}
