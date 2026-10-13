package android.media;

/**
 * MediaExtractor facilitates extraction of demuxed, typically encoded,  media data
 * from a data source.
 * <p>It is generally used like this:
 * <pre>
 * MediaExtractor extractor = new MediaExtractor();
 * extractor.setDataSource(...);
 * int numTracks = extractor.getTrackCount();
 * for (int i = 0; i &lt; numTracks; ++i) {
 *   MediaFormat format = extractor.getTrackFormat(i);
 *   String mime = format.getString(MediaFormat.KEY_MIME);
 *   if (weAreInterestedInThisTrack) {
 *     extractor.selectTrack(i);
 *   }
 * }
 * ByteBuffer inputBuffer = ByteBuffer.allocate(...)
 * while (extractor.readSampleData(inputBuffer, ...) &gt;= 0) {
 *   int trackIndex = extractor.getSampleTrackIndex();
 *   long presentationTimeUs = extractor.getSampleTime();
 *   ...
 *   extractor.advance();
 * }
 *
 * extractor.release();
 * extractor = null;
 * </pre>
 *
 * <p>This class requires the {@link android.Manifest.permission#INTERNET} permission
 * when used with network-based content.
 */
public final class MediaExtractor {
    /**
     * The sample is (at least partially) encrypted, see also the documentation
     * for {@link android.media.MediaCodec#queueSecureInputBuffer}
     */
    public static final int SAMPLE_FLAG_ENCRYPTED = 2;

    /**
     * This indicates that the buffer only contains part of a frame,
     * and the decoder should batch the data until a buffer without
     * this flag appears before decoding the frame.
     *
     * @see MediaCodec#BUFFER_FLAG_PARTIAL_FRAME
     */
    public static final int SAMPLE_FLAG_PARTIAL_FRAME = 4;

    /**
     * The sample is a sync sample (or in {@link MediaCodec}'s terminology
     * it is a key frame.)
     *
     * @see MediaCodec#BUFFER_FLAG_KEY_FRAME
     */
    public static final int SAMPLE_FLAG_SYNC = 1;

    /**
     * If possible, seek to the sync sample closest to the specified time
     */
    public static final int SEEK_TO_CLOSEST_SYNC = 2;

    /**
     * If possible, seek to a sync sample at or after the specified time
     */
    public static final int SEEK_TO_NEXT_SYNC = 1;

    /**
     * If possible, seek to a sync sample at or before the specified time
     */
    public static final int SEEK_TO_PREVIOUS_SYNC = 0;

    private MediaExtractor() {
    }

    public static final class MetricsConstants {
        /**
         * Key to extract the container format
         * from the {@link MediaExtractor#getMetrics} return value.
         * The value is a String.
         */
        public static final String FORMAT = "android.media.mediaextractor.fmt";

        /**
         * Key to extract the container MIME type
         * from the {@link MediaExtractor#getMetrics} return value.
         * The value is a String.
         */
        public static final String MIME_TYPE = "android.media.mediaextractor.mime";

        /**
         * Key to extract the number of tracks in the container
         * from the {@link MediaExtractor#getMetrics} return value.
         * The value is an integer.
         */
        public static final String TRACKS = "android.media.mediaextractor.ntrk";

        private MetricsConstants() {
        }
    }
}
