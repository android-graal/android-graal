package android.media;

/**
 * Parses media container formats and extracts contained media samples and metadata.
 *
 * <p>This class provides access to a battery of low-level media container parsers. Each instance of
 * this class is associated to a specific media parser implementation which is suitable for
 * extraction from a specific media container format. The media parser implementation assignment
 * depends on the factory method (see {@link #create} and {@link #createByName}) used to create the
 * instance.
 *
 * <p>Users must implement the following to use this class.
 *
 * <ul>
 *   <li>{@link InputReader}: Provides the media container's bytes to parse.
 *   <li>{@link OutputConsumer}: Provides a sink for all extracted data and metadata.
 * </ul>
 *
 * <p>The following code snippet includes a usage example:
 *
 * <pre>
 * MyOutputConsumer myOutputConsumer = new MyOutputConsumer();
 * MyInputReader myInputReader = new MyInputReader("www.example.com");
 * MediaParser mediaParser = MediaParser.create(myOutputConsumer);
 *
 * while (mediaParser.advance(myInputReader)) {}
 *
 * mediaParser.release();
 * mediaParser = null;
 * </pre>
 *
 * <p>The following code snippet provides a rudimentary {@link OutputConsumer} sample implementation
 * which extracts and publishes all video samples:
 *
 * <pre>
 * class VideoOutputConsumer implements MediaParser.OutputConsumer {
 *
 *     private byte[] sampleDataBuffer = new byte[4096];
 *     private byte[] discardedDataBuffer = new byte[4096];
 *     private int videoTrackIndex = -1;
 *     private int bytesWrittenCount = 0;
 *
 *     &#64;Override
 *     public void onSeekMapFound(int i, &#64;NonNull MediaFormat mediaFormat) {
 *       // Do nothing.
 *     }
 *
 *     &#64;Override
 *     public void onTrackDataFound(int i, &#64;NonNull TrackData trackData) {
 *       MediaFormat mediaFormat = trackData.mediaFormat;
 *       if (videoTrackIndex == -1 &amp;&amp;
 *           mediaFormat
 *               .getString(MediaFormat.KEY_MIME, &#47;* defaultValue= *&#47; "")
 *               .startsWith("video/")) {
 *         videoTrackIndex = i;
 *       }
 *     }
 *
 *     &#64;Override
 *     public void onSampleDataFound(int trackIndex, &#64;NonNull InputReader inputReader)
 *         throws IOException {
 *       int numberOfBytesToRead = (int) inputReader.getLength();
 *       if (videoTrackIndex != trackIndex) {
 *         // Discard contents.
 *         inputReader.read(
 *             discardedDataBuffer,
 *             &#47;* offset= *&#47; 0,
 *             Math.min(discardDataBuffer.length, numberOfBytesToRead));
 *       } else {
 *         ensureSpaceInBuffer(numberOfBytesToRead);
 *         int bytesRead = inputReader.read(
 *             sampleDataBuffer, bytesWrittenCount, numberOfBytesToRead);
 *         bytesWrittenCount += bytesRead;
 *       }
 *     }
 *
 *     &#64;Override
 *     public void onSampleCompleted(
 *         int trackIndex,
 *         long timeMicros,
 *         int flags,
 *         int size,
 *         int offset,
 *         &#64;Nullable CryptoInfo cryptoData) {
 *       if (videoTrackIndex != trackIndex) {
 *         return; // It's not the video track. Ignore.
 *       }
 *       byte[] sampleData = new byte[size];
 *       int sampleStartOffset = bytesWrittenCount - size - offset;
 *       System.arraycopy(
 *           sampleDataBuffer,
 *           sampleStartOffset,
 *           sampleData,
 *           &#47;* destPos= *&#47; 0,
 *           size);
 *       // Place trailing bytes at the start of the buffer.
 *       System.arraycopy(
 *           sampleDataBuffer,
 *           bytesWrittenCount - offset,
 *           sampleDataBuffer,
 *           &#47;* destPos= *&#47; 0,
 *           &#47;* size= *&#47; offset);
 *       bytesWrittenCount = bytesWrittenCount - offset;
 *       publishSample(sampleData, timeMicros, flags);
 *     }
 *
 *    private void ensureSpaceInBuffer(int numberOfBytesToRead) {
 *      int requiredLength = bytesWrittenCount + numberOfBytesToRead;
 *      if (requiredLength &gt; sampleDataBuffer.length) {
 *        sampleDataBuffer = Arrays.copyOf(sampleDataBuffer, requiredLength);
 *      }
 *    }
 *
 *   }
 *
 * </pre>
 */
public final class MediaParser {
    /**
     * Sets whether constant bitrate seeking should be enabled for ADTS parsing. {@code boolean}
     * expected. Default value is {@code false}.
     */
    public static final String PARAMETER_ADTS_ENABLE_CBR_SEEKING = "android.media.mediaparser.adts.enableCbrSeeking";

    /**
     * Sets whether constant bitrate seeking should be enabled for AMR. {@code boolean} expected.
     * Default value is {@code false}.
     */
    public static final String PARAMETER_AMR_ENABLE_CBR_SEEKING = "android.media.mediaparser.amr.enableCbrSeeking";

    /**
     * Sets whether the ID3 track should be disabled for FLAC. {@code boolean} expected. Default
     * value is {@code false}.
     */
    public static final String PARAMETER_FLAC_DISABLE_ID3 = "android.media.mediaparser.flac.disableId3";

    /**
     * Sets whether Matroska parsing should avoid seeking to the cues element. {@code boolean}
     * expected. Default value is {@code false}.
     *
     * <p>If this flag is enabled and the cues element occurs after the first cluster, then the
     * media is treated as unseekable.
     */
    public static final String PARAMETER_MATROSKA_DISABLE_CUES_SEEKING = "android.media.mediaparser.matroska.disableCuesSeeking";

    /**
     * Sets whether the ID3 track should be disabled for MP3. {@code boolean} expected. Default
     * value is {@code false}.
     */
    public static final String PARAMETER_MP3_DISABLE_ID3 = "android.media.mediaparser.mp3.disableId3";

    /**
     * Sets whether constant bitrate seeking should be enabled for MP3. {@code boolean} expected.
     * Default value is {@code false}.
     */
    public static final String PARAMETER_MP3_ENABLE_CBR_SEEKING = "android.media.mediaparser.mp3.enableCbrSeeking";

    /**
     * Sets whether MP3 parsing should generate a time-to-byte mapping. {@code boolean} expected.
     * Default value is {@code false}.
     *
     * <p>Enabling this flag may require to scan a significant portion of the file to compute a seek
     * point. Therefore, it should only be used if:
     *
     * <ul>
     *   <li>the file is small, or
     *   <li>the bitrate is variable (or the type of bitrate is unknown) and the seeking metadata
     *       provided in the file is not precise enough (or is not present).
     * </ul>
     */
    public static final String PARAMETER_MP3_ENABLE_INDEX_SEEKING = "android.media.mediaparser.mp3.enableIndexSeeking";

    /**
     * Sets whether MP4 parsing should ignore edit lists. {@code boolean} expected. Default value is
     * {@code false}.
     */
    public static final String PARAMETER_MP4_IGNORE_EDIT_LISTS = "android.media.mediaparser.mp4.ignoreEditLists";

    /**
     * Sets whether MP4 parsing should ignore the tfdt box. {@code boolean} expected. Default value
     * is {@code false}.
     */
    public static final String PARAMETER_MP4_IGNORE_TFDT_BOX = "android.media.mediaparser.mp4.ignoreTfdtBox";

    /**
     * Sets whether MP4 parsing should treat all video frames as key frames. {@code boolean}
     * expected. Default value is {@code false}.
     */
    public static final String PARAMETER_MP4_TREAT_VIDEO_FRAMES_AS_KEYFRAMES = "android.media.mediaparser.mp4.treatVideoFramesAsKeyframes";

    /**
     * Sets whether TS should treat samples consisting of non-IDR I slices as synchronization
     * samples (key-frames). {@code boolean} expected. Default value is {@code false}.
     */
    public static final String PARAMETER_TS_ALLOW_NON_IDR_AVC_KEYFRAMES = "android.media.mediaparser.ts.allowNonIdrAvcKeyframes";

    /**
     * Sets whether TS parsing should split AVC stream into access units based on slice headers.
     * {@code boolean} expected. Default value is {@code false}.
     *
     * <p>This flag should be left disabled if the stream contains access units delimiters in order
     * to avoid unnecessary computational costs.
     */
    public static final String PARAMETER_TS_DETECT_ACCESS_UNITS = "android.media.mediaparser.ts.ignoreDetectAccessUnits";

    /**
     * Sets whether TS parsing should handle HDMV DTS audio streams. {@code boolean} expected.
     * Default value is {@code false}.
     *
     * <p>Enabling this flag will disable the detection of SCTE subtitles.
     */
    public static final String PARAMETER_TS_ENABLE_HDMV_DTS_AUDIO_STREAMS = "android.media.mediaparser.ts.enableHdmvDtsAudioStreams";

    /**
     * Sets whether TS parsing should ignore AAC elementary streams. {@code boolean} expected.
     * Default value is {@code false}.
     */
    public static final String PARAMETER_TS_IGNORE_AAC_STREAM = "android.media.mediaparser.ts.ignoreAacStream";

    /**
     * Sets whether TS parsing should ignore AVC elementary streams. {@code boolean} expected.
     * Default value is {@code false}.
     */
    public static final String PARAMETER_TS_IGNORE_AVC_STREAM = "android.media.mediaparser.ts.ignoreAvcStream";

    /**
     * Sets whether TS parsing should ignore splice information streams. {@code boolean} expected.
     * Default value is {@code false}.
     */
    public static final String PARAMETER_TS_IGNORE_SPLICE_INFO_STREAM = "android.media.mediaparser.ts.ignoreSpliceInfoStream";

    /**
     * Sets the operation mode for TS parsing. {@code String} expected. Valid values are {@code
     * "single_pmt"}, {@code "multi_pmt"}, and {@code "hls"}. Default value is {@code "single_pmt"}.
     *
     * <p>The operation modes alter the way TS behaves so that it can handle certain kinds of
     * commonly-occurring malformed media.
     *
     * <ul>
     *   <li>{@code "single_pmt"}: Only the first found PMT is parsed. Others are ignored, even if
     *       more PMTs are declared in the PAT.
     *   <li>{@code "multi_pmt"}: Behave as described in ISO/IEC 13818-1.
     *   <li>{@code "hls"}: Enable {@code "single_pmt"} mode, and ignore continuity counters.
     * </ul>
     */
    public static final String PARAMETER_TS_MODE = "android.media.mediaparser.ts.mode";

    /**
     * Parser for the AC-3 container format, as defined in Digital Audio Compression Standard
     * (AC-3).
     */
    public static final String PARSER_NAME_AC3 = "android.media.mediaparser.Ac3Parser";

    /**
     * Parser for the AC-4 container format, as defined by Dolby AC-4: Audio delivery for
     * Next-Generation Entertainment Services.
     */
    public static final String PARSER_NAME_AC4 = "android.media.mediaparser.Ac4Parser";

    /**
     * Parser for the ADTS container format, as defined in ISO/IEC 13818-7.
     */
    public static final String PARSER_NAME_ADTS = "android.media.mediaparser.AdtsParser";

    /**
     * Parser for the AMR container format, as defined in RFC 4867.
     */
    public static final String PARSER_NAME_AMR = "android.media.mediaparser.AmrParser";

    /**
     * Parser for the FLAC container format, as defined in the <a
     * href="https://xiph.org/flac/">spec</a>.
     */
    public static final String PARSER_NAME_FLAC = "android.media.mediaparser.FlacParser";

    /**
     * Parser for the FLV container format, as defined in Adobe Flash Video File Format
     * Specification.
     */
    public static final String PARSER_NAME_FLV = "android.media.mediaparser.FlvParser";

    /**
     * Parser for fragmented files using the MP4 container format, as defined in ISO/IEC 14496-12.
     */
    public static final String PARSER_NAME_FMP4 = "android.media.mediaparser.FragmentedMp4Parser";

    /**
     * Parser for the Matroska container format, as defined in the <a
     * href="https://matroska.org/technical/specs/">spec</a>.
     */
    public static final String PARSER_NAME_MATROSKA = "android.media.mediaparser.MatroskaParser";

    /**
     * Parser for the MP3 container format, as defined in ISO/IEC 11172-3.
     */
    public static final String PARSER_NAME_MP3 = "android.media.mediaparser.Mp3Parser";

    /**
     * Parser for non-fragmented files using the MP4 container format, as defined in ISO/IEC
     * 14496-12.
     */
    public static final String PARSER_NAME_MP4 = "android.media.mediaparser.Mp4Parser";

    /**
     * Parser for the OGG container format, as defined in RFC 3533.
     */
    public static final String PARSER_NAME_OGG = "android.media.mediaparser.OggParser";

    /**
     * Parser for the PS container format, as defined in ISO/IEC 11172-1.
     */
    public static final String PARSER_NAME_PS = "android.media.mediaparser.PsParser";

    /**
     * Parser for the TS container format, as defined in ISO/IEC 13818-1.
     */
    public static final String PARSER_NAME_TS = "android.media.mediaparser.TsParser";

    /**
     * Parser name returned by {@link #getParserName()} when no parser has been selected yet.
     */
    public static final String PARSER_NAME_UNKNOWN = "android.media.mediaparser.UNKNOWN";

    /**
     * Parser for the WAV container format, as defined in Multimedia Programming Interface and Data
     * Specifications.
     */
    public static final String PARSER_NAME_WAV = "android.media.mediaparser.WavParser";

    /**
     * Indicates that the sample should be decoded but not rendered.
     */
    public static final int SAMPLE_FLAG_DECODE_ONLY = -2147483648;

    /**
     * Indicates that the sample is (at least partially) encrypted.
     */
    public static final int SAMPLE_FLAG_ENCRYPTED = 1073741824;

    /**
     * Indicates that the sample has supplemental data.
     *
     * <p>Samples will not have this flag set unless the {@code
     * "android.media.mediaparser.includeSupplementalData"} parameter is set to {@code true} via
     * {@link #setParameter}.
     *
     * <p>Samples with supplemental data have the following sample data format:
     *
     * <ul>
     *   <li>If the {@code "android.media.mediaparser.inBandCryptoInfo"} parameter is set, all
     *       encryption information.
     *   <li>(4 bytes) {@code sample_data_size}: The size of the actual sample data, not including
     *       supplemental data or encryption information.
     *   <li>({@code sample_data_size} bytes): The media sample data.
     *   <li>(remaining bytes) The supplemental data.
     * </ul>
     */
    public static final int SAMPLE_FLAG_HAS_SUPPLEMENTAL_DATA = 268435456;

    /**
     * Indicates that the sample holds a synchronization sample.
     */
    public static final int SAMPLE_FLAG_KEY_FRAME = 1;

    /**
     * Indicates that the sample is known to contain the last media sample of the stream.
     */
    public static final int SAMPLE_FLAG_LAST_SAMPLE = 536870912;

    private MediaParser() {
    }

    /**
     * Maps seek positions to {@link SeekPoint SeekPoints} in the stream.
     *
     * <p>A {@link SeekPoint} is a position in the stream from which a player may successfully start
     * playing media samples.
     */
    public static final class SeekMap {
        /**
         * Returned by {@link #getDurationMicros()} when the duration is unknown.
         */
        public static final int UNKNOWN_DURATION = -2147483648;

        private SeekMap() {
        }
    }
}
