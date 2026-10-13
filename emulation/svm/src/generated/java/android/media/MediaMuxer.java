package android.media;

/**
 *  MediaMuxer facilitates muxing elementary streams. Currently MediaMuxer supports MP4, Webm
 *  and 3GP file as the output. It also supports muxing B-frames in MP4 since Android Nougat MR1.
 *  <p>
 *  It is generally used like this:
 *
 *  <pre>
 *  MediaMuxer muxer = new MediaMuxer("temp.mp4", OutputFormat.MUXER_OUTPUT_MPEG_4);
 *  // More often, the MediaFormat will be retrieved from MediaCodec.getOutputFormat()
 *  // or MediaExtractor.getTrackFormat().
 *  MediaFormat audioFormat = new MediaFormat(...);
 *  MediaFormat videoFormat = new MediaFormat(...);
 *  int audioTrackIndex = muxer.addTrack(audioFormat);
 *  int videoTrackIndex = muxer.addTrack(videoFormat);
 *  ByteBuffer inputBuffer = ByteBuffer.allocate(bufferSize);
 *  boolean finished = false;
 *  BufferInfo bufferInfo = new BufferInfo();
 *
 *  muxer.start();
 *  while(!finished) {
 *    // getInputBuffer() will fill the inputBuffer with one frame of encoded
 *    // sample from either MediaCodec or MediaExtractor, set isAudioSample to
 *    // true when the sample is audio data, set up all the fields of bufferInfo,
 *    // and return true if there are no more samples.
 *    finished = getInputBuffer(inputBuffer, isAudioSample, bufferInfo);
 *    if (!finished) {
 *      int currentTrackIndex = isAudioSample ? audioTrackIndex : videoTrackIndex;
 *      muxer.writeSampleData(currentTrackIndex, inputBuffer, bufferInfo);
 *    }
 *  };
 *  muxer.stop();
 *  muxer.release();
 *  </pre>
 *
 *
 *  <h4>Metadata Track</h4>
 *  <p>
 *   Per-frame metadata carries information that correlates with video or audio to facilitate offline
 *   processing. For example, gyro signals from the sensor can help video stabilization when doing
 *   offline processing. Metadata tracks are only supported when multiplexing to the MP4 container
 *   format. When adding a new metadata track, the MIME type format must start with prefix
 *   "application/" (for example, "application/gyro"). The format of the metadata is
 *   application-defined. Metadata timestamps must be in the same time base as video and audio
 *   timestamps. The generated MP4 file uses TextMetaDataSampleEntry (defined in section 12.3.3.2 of
 *   the ISOBMFF specification) to signal the metadata's MIME type.
 *
 *  <pre class=prettyprint>
 *    MediaMuxer muxer = new MediaMuxer("temp.mp4", OutputFormat.MUXER_OUTPUT_MPEG_4);
 *    // SetUp Video/Audio Tracks.
 *    MediaFormat audioFormat = new MediaFormat(...);
 *    MediaFormat videoFormat = new MediaFormat(...);
 *    int audioTrackIndex = muxer.addTrack(audioFormat);
 *    int videoTrackIndex = muxer.addTrack(videoFormat);
 *
 *    // Setup Metadata Track
 *    MediaFormat metadataFormat = new MediaFormat(...);
 *    metadataFormat.setString(KEY_MIME, "application/gyro");
 *    int metadataTrackIndex = muxer.addTrack(metadataFormat);
 *
 *    muxer.start();
 *    while(..) {
 *        // Allocate bytebuffer and write gyro data(x,y,z) into it.
 *        ByteBuffer metaData = ByteBuffer.allocate(bufferSize);
 *        metaData.putFloat(x);
 *        metaData.putFloat(y);
 *        metaData.putFloat(z);
 *        BufferInfo metaInfo = new BufferInfo();
 *        // Associate this metadata with the video frame by setting
 *        // the same timestamp as the video frame.
 *        metaInfo.presentationTimeUs = currentVideoTrackTimeUs;
 *        metaInfo.offset = 0;
 *        metaInfo.flags = 0;
 *        metaInfo.size = bufferSize;
 *        muxer.writeSampleData(metadataTrackIndex, metaData, metaInfo);
 *    };
 *    muxer.stop();
 *    muxer.release();
 *  }</pre>
 *
 *  <h2 id=History><a name="History"></a>Features and API History</h2>
 *  <p>
 *  The following table summarizes the feature support in different API version and containers.
 *  For API version numbers, see {@link android.os.Build.VERSION_CODES}.
 *
 *  <style>
 *  .api > tr > th, .api > tr > td { text-align: center; padding: 4px 4px; }
 *  .api > tr > th     { vertical-align: bottom; }
 *  .api > tr > td     { vertical-align: middle; }
 *  .sml > tr > th, .sml > tr > td { text-align: center; padding: 2px 4px; }
 *  .fn { text-align: center; }
 *  </style>
 *
 *  <table align="right" style="width: 0%">
 *   <thead>
 *    <tbody class=api>
 *     <tr><th>Symbol</th>
 *     <th>Meaning</th></tr>
 *    </tbody>
 *   </thead>
 *   <tbody class=sml>
 *    <tr><td>&#9679;</td><td>Supported</td></tr>
 *    <tr><td>&#9675;</td><td>Not supported</td></tr>
 *    <tr><td>&#9639;</td><td>Supported in MP4/WebM/3GP</td></tr>
 *    <tr><td>&#8277;</td><td>Only Supported in MP4</td></tr>
 *   </tbody>
 *  </table>
 * <table align="center" style="width: 100%;">
 *   <thead class=api>
 *    <tr>
 *     <th rowspan=2>Feature</th>
 *     <th colspan="24">SDK Version</th>
 *    </tr>
 *    <tr>
 *     <th>18</th>
 *     <th>19</th>
 *     <th>20</th>
 *     <th>21</th>
 *     <th>22</th>
 *     <th>23</th>
 *     <th>24</th>
 *     <th>25</th>
 *     <th>26+</th>
 *    </tr>
 *   </thead>
 *  <tbody class=api>
 *    <tr>
 *     <td align="center">MP4 container</td>
 *     <td>&#9679;</td>
 *     <td>&#9679;</td>
 *     <td>&#9679;</td>
 *     <td>&#9679;</td>
 *     <td>&#9679;</td>
 *     <td>&#9679;</td>
 *     <td>&#9679;</td>
 *     <td>&#9679;</td>
 *     <td>&#9679;</td>
 *    </tr>
 *     <td align="center">WebM container</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9679;</td>
 *     <td>&#9679;</td>
 *     <td>&#9679;</td>
 *     <td>&#9679;</td>
 *     <td>&#9679;</td>
 *     <td>&#9679;</td>
 *    </tr>
 *     <td align="center">3GP container</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9679;</td>
 *    </tr>
 *     <td align="center">Muxing B-Frames (bi-directional predicted frames)</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#8277;</td>
 *     <td>&#8277;</td>
 *    </tr>
 *    </tr>
 *     <td align="center">Muxing Single Video/Audio Track</td>
 *     <td>&#9639;</td>
 *     <td>&#9639;</td>
 *     <td>&#9639;</td>
 *     <td>&#9639;</td>
 *     <td>&#9639;</td>
 *     <td>&#9639;</td>
 *     <td>&#9639;</td>
 *     <td>&#9639;</td>
 *     <td>&#9639;</td>
 *    </tr>
 *    </tr>
 *     <td align="center">Muxing Multiple Video/Audio Tracks</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#8277;</td>
 *    </tr>
 *    </tr>
 *     <td align="center">Muxing Metadata Tracks</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#9675;</td>
 *     <td>&#8277;</td>
 *    </tr>
 *    </tbody>
 *  </table>
 */
public final class MediaMuxer {
    private MediaMuxer() {
    }

    /**
     * Defines the output format. These constants are used with constructor.
     */
    public static final class OutputFormat {
        /**
         * 3GPP media file format
         */
        public static final int MUXER_OUTPUT_3GPP = 2;

        /**
         * HEIF media file format
         */
        public static final int MUXER_OUTPUT_HEIF = 3;

        /**
         * MPEG4 media file format
         */
        public static final int MUXER_OUTPUT_MPEG_4 = 0;

        /**
         * Ogg media file format
         */
        public static final int MUXER_OUTPUT_OGG = 4;

        /**
         * WEBM media file format
         */
        public static final int MUXER_OUTPUT_WEBM = 1;

        private OutputFormat() {
        }
    }
}
