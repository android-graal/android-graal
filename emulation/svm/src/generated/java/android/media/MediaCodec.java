package android.media;

/**
 * MediaCodec class can be used to access low-level media codecs, i.e. encoder/decoder components.
 * It is part of the Android low-level multimedia support infrastructure (normally used together
 * with {@link MediaExtractor}, {@link MediaSync}, {@link MediaMuxer}, {@link MediaCrypto},
 * {@link MediaDrm}, {@link Image}, {@link Surface}, and {@link AudioTrack}.)
 * <p>
 * <center>
 *   <img src="../../../images/media/mediacodec_buffers.svg" style="width: 540px; height: 205px"
 *       alt="MediaCodec buffer flow diagram">
 * </center>
 * <p>
 * In broad terms, a codec processes input data to generate output data. It processes data
 * asynchronously and uses a set of input and output buffers. At a simplistic level, you request
 * (or receive) an empty input buffer, fill it up with data and send it to the codec for
 * processing. The codec uses up the data and transforms it into one of its empty output buffers.
 * Finally, you request (or receive) a filled output buffer, consume its contents and release it
 * back to the codec.
 *
 * <h3 id=qualityFloor><a name="qualityFloor">Minimum Quality Floor for Video Encoding</h3>
 * <p>
 * Beginning with {@link android.os.Build.VERSION_CODES#S}, Android's Video MediaCodecs enforce a
 * minimum quality floor. The intent is to eliminate poor quality video encodings. This quality
 * floor is applied when the codec is in Variable Bitrate (VBR) mode; it is not applied when
 * the codec is in Constant Bitrate (CBR) mode. The quality floor enforcement is also restricted
 * to a particular size range; this size range is currently for video resolutions
 * larger than 320x240 up through 1920x1080.
 *
 * <p>
 * When this quality floor is in effect, the codec and supporting framework code will work to
 * ensure that the generated video is of at least a "fair" or "good" quality. The metric
 * used to choose these targets is the VMAF (Video Multi-method Assessment Function) with a
 * target score of 70 for selected test sequences.
 *
 * <p>
 * The typical effect is that
 * some videos will generate a higher bitrate than originally configured. This will be most
 * notable for videos which were configured with very low bitrates; the codec will use a bitrate
 * that is determined to be more likely to generate an "fair" or "good" quality video. Another
 * situation is where a video includes very complicated content (lots of motion and detail);
 * in such configurations, the codec will use extra bitrate as needed to avoid losing all of
 * the content's finer detail.
 *
 * <p>
 * This quality floor will not impact content captured at high bitrates (a high bitrate should
 * already provide the codec with sufficient capacity to encode all of the detail).
 * The quality floor does not operate on CBR encodings.
 * The quality floor currently does not operate on resolutions of 320x240 or lower, nor on
 * videos with resolution above 1920x1080.
 *
 * <h3>Data Types</h3>
 * <p>
 * Codecs operate on three kinds of data: compressed data, raw audio data and raw video data.
 * All three kinds of data can be processed using {@link ByteBuffer ByteBuffers}, but you should use
 * a {@link Surface} for raw video data to improve codec performance. Surface uses native video
 * buffers without mapping or copying them to ByteBuffers; thus, it is much more efficient.
 * You normally cannot access the raw video data when using a Surface, but you can use the
 * {@link ImageReader} class to access unsecured decoded (raw) video frames. This may still be more
 * efficient than using ByteBuffers, as some native buffers may be mapped into {@linkplain
 * ByteBuffer#isDirect direct} ByteBuffers. When using ByteBuffer mode, you can access raw video
 * frames using the {@link Image} class and {@link #getInputImage getInput}/{@link #getOutputImage
 * OutputImage(int)}.
 *
 * <h4>Compressed Buffers</h4>
 * <p>
 * Input buffers (for decoders) and output buffers (for encoders) contain compressed data according
 * to the {@linkplain MediaFormat#KEY_MIME format's type}. For video types this is normally a single
 * compressed video frame. For audio data this is normally a single access unit (an encoded audio
 * segment typically containing a few milliseconds of audio as dictated by the format type), but
 * this requirement is slightly relaxed in that a buffer may contain multiple encoded access units
 * of audio. In either case, buffers do not start or end on arbitrary byte boundaries, but rather on
 * frame/access unit boundaries unless they are flagged with {@link #BUFFER_FLAG_PARTIAL_FRAME}.
 *
 * <h4>Raw Audio Buffers</h4>
 * <p>
 * Raw audio buffers contain entire frames of PCM audio data, which is one sample for each channel
 * in channel order. Each PCM audio sample is either a 16 bit signed integer or a float,
 * in native byte order.
 * Raw audio buffers in the float PCM encoding are only possible
 * if the MediaFormat's {@linkplain MediaFormat#KEY_PCM_ENCODING}
 * is set to {@linkplain AudioFormat#ENCODING_PCM_FLOAT} during MediaCodec
 * {@link #configure configure(&hellip;)}
 * and confirmed by {@link #getOutputFormat} for decoders
 * or {@link #getInputFormat} for encoders.
 * A sample method to check for float PCM in the MediaFormat is as follows:
 *
 * <pre class=prettyprint>
 * static boolean isPcmFloat(MediaFormat format) {
 *   return format.getInteger(MediaFormat.KEY_PCM_ENCODING, AudioFormat.ENCODING_PCM_16BIT)
 *       == AudioFormat.ENCODING_PCM_FLOAT;
 * }</pre>
 *
 * In order to extract, in a short array,
 * one channel of a buffer containing 16 bit signed integer audio data,
 * the following code may be used:
 *
 * <pre class=prettyprint>
 * // Assumes the buffer PCM encoding is 16 bit.
 * short[] getSamplesForChannel(MediaCodec codec, int bufferId, int channelIx) {
 *   ByteBuffer outputBuffer = codec.getOutputBuffer(bufferId);
 *   MediaFormat format = codec.getOutputFormat(bufferId);
 *   ShortBuffer samples = outputBuffer.order(ByteOrder.nativeOrder()).asShortBuffer();
 *   int numChannels = format.getInteger(MediaFormat.KEY_CHANNEL_COUNT);
 *   if (channelIx &lt; 0 || channelIx &gt;= numChannels) {
 *     return null;
 *   }
 *   short[] res = new short[samples.remaining() / numChannels];
 *   for (int i = 0; i &lt; res.length; ++i) {
 *     res[i] = samples.get(i * numChannels + channelIx);
 *   }
 *   return res;
 * }</pre>
 *
 * <h4>Raw Video Buffers</h4>
 * <p>
 * In ByteBuffer mode video buffers are laid out according to their {@linkplain
 * MediaFormat#KEY_COLOR_FORMAT color format}. You can get the supported color formats as an array
 * from {@link #getCodecInfo}{@code .}{@link MediaCodecInfo#getCapabilitiesForType
 * getCapabilitiesForType(&hellip;)}{@code .}{@link CodecCapabilities#colorFormats colorFormats}.
 * Video codecs may support three kinds of color formats:
 * <ul>
 * <li><strong>native raw video format:</strong> This is marked by {@link
 * CodecCapabilities#COLOR_FormatSurface} and it can be used with an input or output Surface.</li>
 * <li><strong>flexible YUV buffers</strong> (such as {@link
 * CodecCapabilities#COLOR_FormatYUV420Flexible}): These can be used with an input/output Surface,
 * as well as in ByteBuffer mode, by using {@link #getInputImage getInput}/{@link #getOutputImage
 * OutputImage(int)}.</li>
 * <li><strong>other, specific formats:</strong> These are normally only supported in ByteBuffer
 * mode. Some color formats are vendor specific. Others are defined in {@link CodecCapabilities}.
 * For color formats that are equivalent to a flexible format, you can still use {@link
 * #getInputImage getInput}/{@link #getOutputImage OutputImage(int)}.</li>
 * </ul>
 * <p>
 * All video codecs support flexible YUV 4:2:0 buffers since {@link
 * android.os.Build.VERSION_CODES#LOLLIPOP_MR1}.
 *
 * <h4>Accessing Raw Video ByteBuffers on Older Devices</h4>
 * <p>
 * Prior to {@link android.os.Build.VERSION_CODES#LOLLIPOP} and {@link Image} support, you need to
 * use the {@link MediaFormat#KEY_STRIDE} and {@link MediaFormat#KEY_SLICE_HEIGHT} output format
 * values to understand the layout of the raw output buffers.
 * <p class=note>
 * Note that on some devices the slice-height is advertised as 0. This could mean either that the
 * slice-height is the same as the frame height, or that the slice-height is the frame height
 * aligned to some value (usually a power of 2). Unfortunately, there is no standard and simple way
 * to tell the actual slice height in this case. Furthermore, the vertical stride of the {@code U}
 * plane in planar formats is also not specified or defined, though usually it is half of the slice
 * height.
 * <p>
 * The {@link MediaFormat#KEY_WIDTH} and {@link MediaFormat#KEY_HEIGHT} keys specify the size of the
 * video frames; however, for most encondings the video (picture) only occupies a portion of the
 * video frame. This is represented by the 'crop rectangle'.
 * <p>
 * You need to use the following keys to get the crop rectangle of raw output images from the
 * {@linkplain #getOutputFormat output format}. If these keys are not present, the video occupies the
 * entire video frame.The crop rectangle is understood in the context of the output frame
 * <em>before</em> applying any {@linkplain MediaFormat#KEY_ROTATION rotation}.
 * <table style="width: 0%">
 *  <thead>
 *   <tr>
 *    <th>Format Key</th>
 *    <th>Type</th>
 *    <th>Description</th>
 *   </tr>
 *  </thead>
 *  <tbody>
 *   <tr>
 *    <td>{@link MediaFormat#KEY_CROP_LEFT}</td>
 *    <td>Integer</td>
 *    <td>The left-coordinate (x) of the crop rectangle</td>
 *   </tr><tr>
 *    <td>{@link MediaFormat#KEY_CROP_TOP}</td>
 *    <td>Integer</td>
 *    <td>The top-coordinate (y) of the crop rectangle</td>
 *   </tr><tr>
 *    <td>{@link MediaFormat#KEY_CROP_RIGHT}</td>
 *    <td>Integer</td>
 *    <td>The right-coordinate (x) <strong>MINUS 1</strong> of the crop rectangle</td>
 *   </tr><tr>
 *    <td>{@link MediaFormat#KEY_CROP_BOTTOM}</td>
 *    <td>Integer</td>
 *    <td>The bottom-coordinate (y) <strong>MINUS 1</strong> of the crop rectangle</td>
 *   </tr><tr>
 *    <td colspan=3>
 *     The right and bottom coordinates can be understood as the coordinates of the right-most
 *     valid column/bottom-most valid row of the cropped output image.
 *    </td>
 *   </tr>
 *  </tbody>
 * </table>
 * <p>
 * The size of the video frame (before rotation) can be calculated as such:
 * <pre class=prettyprint>
 * MediaFormat format = decoder.getOutputFormat(&hellip;);
 * int width = format.getInteger(MediaFormat.KEY_WIDTH);
 * if (format.containsKey(MediaFormat.KEY_CROP_LEFT)
 *         && format.containsKey(MediaFormat.KEY_CROP_RIGHT)) {
 *     width = format.getInteger(MediaFormat.KEY_CROP_RIGHT) + 1
 *                 - format.getInteger(MediaFormat.KEY_CROP_LEFT);
 * }
 * int height = format.getInteger(MediaFormat.KEY_HEIGHT);
 * if (format.containsKey(MediaFormat.KEY_CROP_TOP)
 *         && format.containsKey(MediaFormat.KEY_CROP_BOTTOM)) {
 *     height = format.getInteger(MediaFormat.KEY_CROP_BOTTOM) + 1
 *                  - format.getInteger(MediaFormat.KEY_CROP_TOP);
 * }
 * </pre>
 * <p class=note>
 * Also note that the meaning of {@link BufferInfo#offset BufferInfo.offset} was not consistent across
 * devices. On some devices the offset pointed to the top-left pixel of the crop rectangle, while on
 * most devices it pointed to the top-left pixel of the entire frame.
 *
 * <h3>States</h3>
 * <p>
 * During its life a codec conceptually exists in one of three states: Stopped, Executing or
 * Released. The Stopped collective state is actually the conglomeration of three states:
 * Uninitialized, Configured and Error, whereas the Executing state conceptually progresses through
 * three sub-states: Flushed, Running and End-of-Stream.
 * <p>
 * <center>
 *   <img src="../../../images/media/mediacodec_states.svg" style="width: 519px; height: 356px"
 *       alt="MediaCodec state diagram">
 * </center>
 * <p>
 * When you create a codec using one of the factory methods, the codec is in the Uninitialized
 * state. First, you need to configure it via {@link #configure configure(&hellip;)}, which brings
 * it to the Configured state, then call {@link #start} to move it to the Executing state. In this
 * state you can process data through the buffer queue manipulation described above.
 * <p>
 * The Executing state has three sub-states: Flushed, Running and End-of-Stream. Immediately after
 * {@link #start} the codec is in the Flushed sub-state, where it holds all the buffers. As soon
 * as the first input buffer is dequeued, the codec moves to the Running sub-state, where it spends
 * most of its life. When you queue an input buffer with the {@linkplain #BUFFER_FLAG_END_OF_STREAM
 * end-of-stream marker}, the codec transitions to the End-of-Stream sub-state. In this state the
 * codec no longer accepts further input buffers, but still generates output buffers until the
 * end-of-stream is reached on the output. For decoders, you can move back to the Flushed sub-state
 * at any time while in the Executing state using {@link #flush}.
 * <p class=note>
 * <strong>Note:</strong> Going back to Flushed state is only supported for decoders, and may not
 * work for encoders (the behavior is undefined).
 * <p>
 * Call {@link #stop} to return the codec to the Uninitialized state, whereupon it may be configured
 * again. When you are done using a codec, you must release it by calling {@link #release}.
 * <p>
 * On rare occasions the codec may encounter an error and move to the Error state. This is
 * communicated using an invalid return value from a queuing operation, or sometimes via an
 * exception. Call {@link #reset} to make the codec usable again. You can call it from any state to
 * move the codec back to the Uninitialized state. Otherwise, call {@link #release} to move to the
 * terminal Released state.
 *
 * <h3>Creation</h3>
 * <p>
 * Use {@link MediaCodecList} to create a MediaCodec for a specific {@link MediaFormat}. When
 * decoding a file or a stream, you can get the desired format from {@link
 * MediaExtractor#getTrackFormat MediaExtractor.getTrackFormat}. Inject any specific features that
 * you want to add using {@link MediaFormat#setFeatureEnabled MediaFormat.setFeatureEnabled}, then
 * call {@link MediaCodecList#findDecoderForFormat MediaCodecList.findDecoderForFormat} to get the
 * name of a codec that can handle that specific media format. Finally, create the codec using
 * {@link #createByCodecName}.
 * <p class=note>
 * <strong>Note:</strong> On {@link android.os.Build.VERSION_CODES#LOLLIPOP}, the format to
 * {@code MediaCodecList.findDecoder}/{@code EncoderForFormat} must not contain a {@linkplain
 * MediaFormat#KEY_FRAME_RATE frame rate}. Use
 * <code class=prettyprint>format.setString(MediaFormat.KEY_FRAME_RATE, null)</code>
 * to clear any existing frame rate setting in the format.
 * <p>
 * You can also create the preferred codec for a specific MIME type using {@link
 * #createDecoderByType createDecoder}/{@link #createEncoderByType EncoderByType(String)}.
 * This, however, cannot be used to inject features, and may create a codec that cannot handle the
 * specific desired media format.
 *
 * <h4>Creating secure decoders</h4>
 * <p>
 * On versions {@link android.os.Build.VERSION_CODES#KITKAT_WATCH} and earlier, secure codecs might
 * not be listed in {@link MediaCodecList}, but may still be available on the system. Secure codecs
 * that exist can be instantiated by name only, by appending {@code ".secure"} to the name of a
 * regular codec (the name of all secure codecs must end in {@code ".secure"}.) {@link
 * #createByCodecName} will throw an {@code IOException} if the codec is not present on the system.
 * <p>
 * From {@link android.os.Build.VERSION_CODES#LOLLIPOP} onwards, you should use the {@link
 * CodecCapabilities#FEATURE_SecurePlayback} feature in the media format to create a secure decoder.
 *
 * <h3>Initialization</h3>
 * <p>
 * After creating the codec, you can set a callback using {@link #setCallback setCallback} if you
 * want to process data asynchronously. Then, {@linkplain #configure configure} the codec using the
 * specific media format. This is when you can specify the output {@link Surface} for video
 * producers &ndash; codecs that generate raw video data (e.g. video decoders). This is also when
 * you can set the decryption parameters for secure codecs (see {@link MediaCrypto}). Finally, since
 * some codecs can operate in multiple modes, you must specify whether you want it to work as a
 * decoder or an encoder.
 * <p>
 * Since {@link android.os.Build.VERSION_CODES#LOLLIPOP}, you can query the resulting input and
 * output format in the Configured state. You can use this to verify the resulting configuration,
 * e.g. color formats, before starting the codec.
 * <p>
 * If you want to process raw input video buffers natively with a video consumer &ndash; a codec
 * that processes raw video input, such as a video encoder &ndash; create a destination Surface for
 * your input data using {@link #createInputSurface} after configuration. Alternately, set up the
 * codec to use a previously created {@linkplain #createPersistentInputSurface persistent input
 * surface} by calling {@link #setInputSurface}.
 *
 * <h4 id=EncoderProfiles><a name="EncoderProfiles"></a>Encoder Profiles</h4>
 * <p>
 * When using an encoder, it is recommended to set the desired codec {@link MediaFormat#KEY_PROFILE
 * profile} during {@link #configure configure()}. (This is only meaningful for
 * {@link MediaFormat#KEY_MIME media formats} for which profiles are defined.)
 * <p>
 * If a profile is not specified during {@code configure}, the encoder will choose a profile for the
 * session based on the available information. We will call this value the <i>default profile</i>.
 * The selection of the default profile is device specific and may not be deterministic
 * (could be ad hoc or even experimental). The encoder may choose a default profile that is not
 * suitable for the intended encoding session, which may result in the encoder ultimately rejecting
 * the session.
 * <p>
 * The encoder may reject the encoding session if the configured (or default if unspecified) profile
 * does not support the codec input (mainly the {@link MediaFormat#KEY_COLOR_FORMAT color format} for
 * video/image codecs, or the {@link MediaFormat#KEY_PCM_ENCODING sample encoding} and the {@link
 * MediaFormat#KEY_CHANNEL_COUNT number of channels} for audio codecs, but also possibly
 * {@link MediaFormat#KEY_WIDTH width}, {@link MediaFormat#KEY_HEIGHT height},
 * {@link MediaFormat#KEY_FRAME_RATE frame rate}, {@link MediaFormat#KEY_BIT_RATE bitrate} or
 * {@link MediaFormat#KEY_SAMPLE_RATE sample rate}.)
 * Alternatively, the encoder may choose to (but is not required to) convert the input to support the
 * selected (or default) profile - or adjust the chosen profile based on the presumed or detected
 * input format - to ensure a successful encoding session. <b>Note</b>: Converting the input to match
 * an incompatible profile will in most cases result in decreased codec performance.
 * <p>
 * To ensure backward compatibility, the following guarantees are provided by Android:
 * <ul>
 * <li>The default video encoder profile always supports 8-bit YUV 4:2:0 color format ({@link
 * CodecCapabilities#COLOR_FormatYUV420Flexible COLOR_FormatYUV420Flexible} and equivalent
 * {@link CodecCapabilities#colorFormats supported formats}) for both Surface and ByteBuffer modes.
 * <li>The default video encoder profile always supports the default 8-bit RGBA color format in
 * Surface mode even if no such formats are enumerated in the {@link CodecCapabilities#colorFormats
 * supported formats}.
 * </ul>
 * <p class=note>
 * <b>Note</b>: the accepted profile can be queried through the {@link #getOutputFormat output
 * format} of the encoder after {@code configure} to allow applications to set up their
 * codec input to a format supported by the encoder profile.
 * <p>
 * <b>Implication:</b>
 * <ul>
 * <li>Applications that want to encode 4:2:2, 4:4:4, 10+ bit or HDR video input <b>MUST</b> configure
 * a suitable profile for encoders.
 * </ul>
 *
 * <h4 id=CSD><a name="CSD"></a>Codec-specific Data</h4>
 * <p>
 * Some formats, notably AAC audio and MPEG4, H.264 and H.265 video formats require the actual data
 * to be prefixed by a number of buffers containing setup data, or codec specific data. When
 * processing such compressed formats, this data must be submitted to the codec after {@link
 * #start} and before any frame data. Such data must be marked using the flag {@link
 * #BUFFER_FLAG_CODEC_CONFIG} in a call to {@link #queueInputBuffer queueInputBuffer}.
 * <p>
 * Codec-specific data can also be included in the format passed to {@link #configure configure} in
 * ByteBuffer entries with keys "csd-0", "csd-1", etc. These keys are always included in the track
 * {@link MediaFormat} obtained from the {@link MediaExtractor#getTrackFormat MediaExtractor}.
 * Codec-specific data in the format is automatically submitted to the codec upon {@link #start};
 * you <strong>MUST NOT</strong> submit this data explicitly. If the format did not contain codec
 * specific data, you can choose to submit it using the specified number of buffers in the correct
 * order, according to the format requirements. In case of H.264 AVC, you can also concatenate all
 * codec-specific data and submit it as a single codec-config buffer.
 * <p>
 * Android uses the following codec-specific data buffers. These are also required to be set in
 * the track format for proper {@link MediaMuxer} track configuration. Each parameter set and the
 * codec-specific-data sections marked with (<sup>*</sup>) must start with a start code of
 * {@code "\x00\x00\x00\x01"}.
 * <p>
 * <style>td.NA { background: #ccc; } .mid > tr > td { vertical-align: middle; }</style>
 * <table>
 *  <thead>
 *   <th>Format</th>
 *   <th>CSD buffer #0</th>
 *   <th>CSD buffer #1</th>
 *   <th>CSD buffer #2</th>
 *  </thead>
 *  <tbody class=mid>
 *   <tr>
 *    <td>AAC</td>
 *    <td>Decoder-specific information from ESDS<sup>*</sup></td>
 *    <td class=NA>Not Used</td>
 *    <td class=NA>Not Used</td>
 *   </tr>
 *   <tr>
 *    <td>VORBIS</td>
 *    <td>Identification header</td>
 *    <td>Setup header</td>
 *    <td class=NA>Not Used</td>
 *   </tr>
 *   <tr>
 *    <td>OPUS</td>
 *    <td>Identification header</td>
 *    <td>Pre-skip in nanosecs<br>
 *        (unsigned 64-bit {@linkplain ByteOrder#nativeOrder native-order} integer.)<br>
 *        This overrides the pre-skip value in the identification header.</td>
 *    <td>Seek Pre-roll in nanosecs<br>
 *        (unsigned 64-bit {@linkplain ByteOrder#nativeOrder native-order} integer.)</td>
 *   </tr>
 *   <tr>
 *    <td>FLAC</td>
 *    <td>"fLaC", the FLAC stream marker in ASCII,<br>
 *        followed by the STREAMINFO block (the mandatory metadata block),<br>
 *        optionally followed by any number of other metadata blocks</td>
 *    <td class=NA>Not Used</td>
 *    <td class=NA>Not Used</td>
 *   </tr>
 *   <tr>
 *    <td>MPEG-4</td>
 *    <td>Decoder-specific information from ESDS<sup>*</sup></td>
 *    <td class=NA>Not Used</td>
 *    <td class=NA>Not Used</td>
 *   </tr>
 *   <tr>
 *    <td>H.264 AVC</td>
 *    <td>SPS (Sequence Parameter Sets<sup>*</sup>)</td>
 *    <td>PPS (Picture Parameter Sets<sup>*</sup>)</td>
 *    <td class=NA>Not Used</td>
 *   </tr>
 *   <tr>
 *    <td>H.265 HEVC</td>
 *    <td>VPS (Video Parameter Sets<sup>*</sup>) +<br>
 *     SPS (Sequence Parameter Sets<sup>*</sup>) +<br>
 *     PPS (Picture Parameter Sets<sup>*</sup>)</td>
 *    <td class=NA>Not Used</td>
 *    <td class=NA>Not Used</td>
 *   </tr>
 *   <tr>
 *    <td>VP9</td>
 *    <td>VP9 <a href="http://wiki.webmproject.org/vp9-codecprivate">CodecPrivate</a> Data
 *        (optional)</td>
 *    <td class=NA>Not Used</td>
 *    <td class=NA>Not Used</td>
 *   </tr>
 *   <tr>
 *    <td>AV1</td>
 *    <td>AV1 <a href="https://aomediacodec.github.io/av1-isobmff/#av1codecconfigurationbox-syntax">
 *        AV1CodecConfigurationRecord</a> Data (optional)
 *    </td>
 *    <td class=NA>Not Used</td>
 *    <td class=NA>Not Used</td>
 *   </tr>
 *  </tbody>
 * </table>
 *
 * <p class=note>
 * <strong>Note:</strong> care must be taken if the codec is flushed immediately or shortly
 * after start, before any output buffer or output format change has been returned, as the codec
 * specific data may be lost during the flush. You must resubmit the data using buffers marked with
 * {@link #BUFFER_FLAG_CODEC_CONFIG} after such flush to ensure proper codec operation.
 * <p>
 * Encoders (or codecs that generate compressed data) will create and return the codec specific data
 * before any valid output buffer in output buffers marked with the {@linkplain
 * #BUFFER_FLAG_CODEC_CONFIG codec-config flag}. Buffers containing codec-specific-data have no
 * meaningful timestamps.
 *
 * <h3>Data Processing</h3>
 * <p>
 * Each codec maintains a set of input and output buffers that are referred to by a buffer-ID in
 * API calls. After a successful call to {@link #start} the client "owns" neither input nor output
 * buffers. In synchronous mode, call {@link #dequeueInputBuffer dequeueInput}/{@link
 * #dequeueOutputBuffer OutputBuffer(&hellip;)} to obtain (get ownership of) an input or output
 * buffer from the codec. In asynchronous mode, you will automatically receive available buffers via
 * the {@link Callback#onInputBufferAvailable MediaCodec.Callback.onInput}/{@link
 * Callback#onOutputBufferAvailable OutputBufferAvailable(&hellip;)} callbacks.
 * <p>
 * Upon obtaining an input buffer, fill it with data and submit it to the codec using {@link
 * #queueInputBuffer queueInputBuffer} &ndash; or {@link #queueSecureInputBuffer
 * queueSecureInputBuffer} if using decryption. Do not submit multiple input buffers with the same
 * timestamp (unless it is <a href="#CSD">codec-specific data</a> marked as such).
 * <p>
 * The codec in turn will return a read-only output buffer via the {@link
 * Callback#onOutputBufferAvailable onOutputBufferAvailable} callback in asynchronous mode, or in
 * response to a {@link #dequeueOutputBuffer dequeueOutputBuffer} call in synchronous mode. After the
 * output buffer has been processed, call one of the {@link #releaseOutputBuffer
 * releaseOutputBuffer} methods to return the buffer to the codec.
 * <p>
 * While you are not required to resubmit/release buffers immediately to the codec, holding onto
 * input and/or output buffers may stall the codec, and this behavior is device dependent.
 * <strong>Specifically, it is possible that a codec may hold off on generating output buffers until
 * <em>all</em> outstanding buffers have been released/resubmitted.</strong> Therefore, try to
 * hold onto to available buffers as little as possible.
 * <p>
 * Depending on the API version, you can process data in three ways:
 * <table>
 *  <thead>
 *   <tr>
 *    <th>Processing Mode</th>
 *    <th>API version <= 20<br>Jelly Bean/KitKat</th>
 *    <th>API version >= 21<br>Lollipop and later</th>
 *   </tr>
 *  </thead>
 *  <tbody>
 *   <tr>
 *    <td>Synchronous API using buffer arrays</td>
 *    <td>Supported</td>
 *    <td>Deprecated</td>
 *   </tr>
 *   <tr>
 *    <td>Synchronous API using buffers</td>
 *    <td class=NA>Not Available</td>
 *    <td>Supported</td>
 *   </tr>
 *   <tr>
 *    <td>Asynchronous API using buffers</td>
 *    <td class=NA>Not Available</td>
 *    <td>Supported</td>
 *   </tr>
 *  </tbody>
 * </table>
 *
 * <h4>Asynchronous Processing using Buffers</h4>
 * <p>
 * Since {@link android.os.Build.VERSION_CODES#LOLLIPOP}, the preferred method is to process data
 * asynchronously by setting a callback before calling {@link #configure configure}. Asynchronous
 * mode changes the state transitions slightly, because you must call {@link #start} after {@link
 * #flush} to transition the codec to the Running sub-state and start receiving input buffers.
 * Similarly, upon an initial call to {@code start} the codec will move directly to the Running
 * sub-state and start passing available input buffers via the callback.
 * <p>
 * <center>
 *   <img src="../../../images/media/mediacodec_async_states.svg" style="width: 516px; height: 353px"
 *       alt="MediaCodec state diagram for asynchronous operation">
 * </center>
 * <p>
 * MediaCodec is typically used like this in asynchronous mode:
 * <pre class=prettyprint>
 * MediaCodec codec = MediaCodec.createByCodecName(name);
 * MediaFormat mOutputFormat; // member variable
 * codec.setCallback(new MediaCodec.Callback() {
 *   {@literal @Override}
 *   void onInputBufferAvailable(MediaCodec mc, int inputBufferId) {
 *     ByteBuffer inputBuffer = codec.getInputBuffer(inputBufferId);
 *     // fill inputBuffer with valid data
 *     &hellip;
 *     codec.queueInputBuffer(inputBufferId, &hellip;);
 *   }
 *
 *   {@literal @Override}
 *   void onOutputBufferAvailable(MediaCodec mc, int outputBufferId, &hellip;) {
 *     ByteBuffer outputBuffer = codec.getOutputBuffer(outputBufferId);
 *     MediaFormat bufferFormat = codec.getOutputFormat(outputBufferId); // option A
 *     // bufferFormat is equivalent to mOutputFormat
 *     // outputBuffer is ready to be processed or rendered.
 *     &hellip;
 *     codec.releaseOutputBuffer(outputBufferId, &hellip;);
 *   }
 *
 *   {@literal @Override}
 *   void onOutputFormatChanged(MediaCodec mc, MediaFormat format) {
 *     // Subsequent data will conform to new format.
 *     // Can ignore if using getOutputFormat(outputBufferId)
 *     mOutputFormat = format; // option B
 *   }
 *
 *   {@literal @Override}
 *   void onError(&hellip;) {
 *     &hellip;
 *   }
 *   {@literal @Override}
 *   void onCryptoError(&hellip;) {
 *     &hellip;
 *   }
 * });
 * codec.configure(format, &hellip;);
 * mOutputFormat = codec.getOutputFormat(); // option B
 * codec.start();
 * // wait for processing to complete
 * codec.stop();
 * codec.release();</pre>
 *
 * <h4>Synchronous Processing using Buffers</h4>
 * <p>
 * Since {@link android.os.Build.VERSION_CODES#LOLLIPOP}, you should retrieve input and output
 * buffers using {@link #getInputBuffer getInput}/{@link #getOutputBuffer OutputBuffer(int)} and/or
 * {@link #getInputImage getInput}/{@link #getOutputImage OutputImage(int)} even when using the
 * codec in synchronous mode. This allows certain optimizations by the framework, e.g. when
 * processing dynamic content. This optimization is disabled if you call {@link #getInputBuffers
 * getInput}/{@link #getOutputBuffers OutputBuffers()}.
 *
 * <p class=note>
 * <strong>Note:</strong> do not mix the methods of using buffers and buffer arrays at the same
 * time. Specifically, only call {@code getInput}/{@code OutputBuffers} directly after {@link
 * #start} or after having dequeued an output buffer ID with the value of {@link
 * #INFO_OUTPUT_FORMAT_CHANGED}.
 * <p>
 * MediaCodec is typically used like this in synchronous mode:
 * <pre>
 * MediaCodec codec = MediaCodec.createByCodecName(name);
 * codec.configure(format, &hellip;);
 * MediaFormat outputFormat = codec.getOutputFormat(); // option B
 * codec.start();
 * for (;;) {
 *   int inputBufferId = codec.dequeueInputBuffer(timeoutUs);
 *   if (inputBufferId &gt;= 0) {
 *     ByteBuffer inputBuffer = codec.getInputBuffer(&hellip;);
 *     // fill inputBuffer with valid data
 *     &hellip;
 *     codec.queueInputBuffer(inputBufferId, &hellip;);
 *   }
 *   int outputBufferId = codec.dequeueOutputBuffer(&hellip;);
 *   if (outputBufferId &gt;= 0) {
 *     ByteBuffer outputBuffer = codec.getOutputBuffer(outputBufferId);
 *     MediaFormat bufferFormat = codec.getOutputFormat(outputBufferId); // option A
 *     // bufferFormat is identical to outputFormat
 *     // outputBuffer is ready to be processed or rendered.
 *     &hellip;
 *     codec.releaseOutputBuffer(outputBufferId, &hellip;);
 *   } else if (outputBufferId == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
 *     // Subsequent data will conform to new format.
 *     // Can ignore if using getOutputFormat(outputBufferId)
 *     outputFormat = codec.getOutputFormat(); // option B
 *   }
 * }
 * codec.stop();
 * codec.release();</pre>
 *
 * <h4>Synchronous Processing using Buffer Arrays (deprecated)</h4>
 * <p>
 * In versions {@link android.os.Build.VERSION_CODES#KITKAT_WATCH} and before, the set of input and
 * output buffers are represented by the {@code ByteBuffer[]} arrays. After a successful call to
 * {@link #start}, retrieve the buffer arrays using {@link #getInputBuffers getInput}/{@link
 * #getOutputBuffers OutputBuffers()}. Use the buffer ID-s as indices into these arrays (when
 * non-negative), as demonstrated in the sample below. Note that there is no inherent correlation
 * between the size of the arrays and the number of input and output buffers used by the system,
 * although the array size provides an upper bound.
 * <pre>
 * MediaCodec codec = MediaCodec.createByCodecName(name);
 * codec.configure(format, &hellip;);
 * codec.start();
 * ByteBuffer[] inputBuffers = codec.getInputBuffers();
 * ByteBuffer[] outputBuffers = codec.getOutputBuffers();
 * for (;;) {
 *   int inputBufferId = codec.dequeueInputBuffer(&hellip;);
 *   if (inputBufferId &gt;= 0) {
 *     // fill inputBuffers[inputBufferId] with valid data
 *     &hellip;
 *     codec.queueInputBuffer(inputBufferId, &hellip;);
 *   }
 *   int outputBufferId = codec.dequeueOutputBuffer(&hellip;);
 *   if (outputBufferId &gt;= 0) {
 *     // outputBuffers[outputBufferId] is ready to be processed or rendered.
 *     &hellip;
 *     codec.releaseOutputBuffer(outputBufferId, &hellip;);
 *   } else if (outputBufferId == MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED) {
 *     outputBuffers = codec.getOutputBuffers();
 *   } else if (outputBufferId == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
 *     // Subsequent data will conform to new format.
 *     MediaFormat format = codec.getOutputFormat();
 *   }
 * }
 * codec.stop();
 * codec.release();</pre>
 *
 * <h4>End-of-stream Handling</h4>
 * <p>
 * When you reach the end of the input data, you must signal it to the codec by specifying the
 * {@link #BUFFER_FLAG_END_OF_STREAM} flag in the call to {@link #queueInputBuffer
 * queueInputBuffer}. You can do this on the last valid input buffer, or by submitting an additional
 * empty input buffer with the end-of-stream flag set. If using an empty buffer, the timestamp will
 * be ignored.
 * <p>
 * The codec will continue to return output buffers until it eventually signals the end of the
 * output stream by specifying the same end-of-stream flag in the {@link BufferInfo} set in {@link
 * #dequeueOutputBuffer dequeueOutputBuffer} or returned via {@link Callback#onOutputBufferAvailable
 * onOutputBufferAvailable}. This can be set on the last valid output buffer, or on an empty buffer
 * after the last valid output buffer. The timestamp of such empty buffer should be ignored.
 * <p>
 * Do not submit additional input buffers after signaling the end of the input stream, unless the
 * codec has been flushed, or stopped and restarted.
 *
 * <h4>Using an Output Surface</h4>
 * <p>
 * The data processing is nearly identical to the ByteBuffer mode when using an output {@link
 * Surface}; however, the output buffers will not be accessible, and are represented as {@code null}
 * values. E.g. {@link #getOutputBuffer getOutputBuffer}/{@link #getOutputImage Image(int)} will
 * return {@code null} and {@link #getOutputBuffers} will return an array containing only {@code
 * null}-s.
 * <p>
 * When using an output Surface, you can select whether or not to render each output buffer on the
 * surface. You have three choices:
 * <ul>
 * <li><strong>Do not render the buffer:</strong> Call {@link #releaseOutputBuffer(int, boolean)
 * releaseOutputBuffer(bufferId, false)}.</li>
 * <li><strong>Render the buffer with the default timestamp:</strong> Call {@link
 * #releaseOutputBuffer(int, boolean) releaseOutputBuffer(bufferId, true)}.</li>
 * <li><strong>Render the buffer with a specific timestamp:</strong> Call {@link
 * #releaseOutputBuffer(int, long) releaseOutputBuffer(bufferId, timestamp)}.</li>
 * </ul>
 * <p>
 * Since {@link android.os.Build.VERSION_CODES#M}, the default timestamp is the {@linkplain
 * BufferInfo#presentationTimeUs presentation timestamp} of the buffer (converted to nanoseconds).
 * It was not defined prior to that.
 * <p>
 * Also since {@link android.os.Build.VERSION_CODES#M}, you can change the output Surface
 * dynamically using {@link #setOutputSurface setOutputSurface}.
 * <p>
 * When rendering output to a Surface, the Surface may be configured to drop excessive frames (that
 * are not consumed by the Surface in a timely manner). Or it may be configured to not drop excessive
 * frames. In the latter mode if the Surface is not consuming output frames fast enough, it will
 * eventually block the decoder. Prior to {@link android.os.Build.VERSION_CODES#Q} the exact behavior
 * was undefined, with the exception that View surfaces (SurfaceView or TextureView) always dropped
 * excessive frames. Since {@link android.os.Build.VERSION_CODES#Q} the default behavior is to drop
 * excessive frames. Applications can opt out of this behavior for non-View surfaces (such as
 * ImageReader or SurfaceTexture) by targeting SDK {@link android.os.Build.VERSION_CODES#Q} and
 * setting the key {@link MediaFormat#KEY_ALLOW_FRAME_DROP} to {@code 0}
 * in their configure format.
 *
 * <h4>Transformations When Rendering onto Surface</h4>
 *
 * If the codec is configured into Surface mode, any crop rectangle, {@linkplain
 * MediaFormat#KEY_ROTATION rotation} and {@linkplain #setVideoScalingMode video scaling
 * mode} will be automatically applied with one exception:
 * <p class=note>
 * Prior to the {@link android.os.Build.VERSION_CODES#M} release, software decoders may not
 * have applied the rotation when being rendered onto a Surface. Unfortunately, there is no standard
 * and simple way to identify software decoders, or if they apply the rotation other than by trying
 * it out.
 * <p>
 * There are also some caveats.
 * <p class=note>
 * Note that the pixel aspect ratio is not considered when displaying the output onto the
 * Surface. This means that if you are using {@link #VIDEO_SCALING_MODE_SCALE_TO_FIT} mode, you
 * must position the output Surface so that it has the proper final display aspect ratio. Conversely,
 * you can only use {@link #VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING} mode for content with
 * square pixels (pixel aspect ratio or 1:1).
 * <p class=note>
 * Note also that as of {@link android.os.Build.VERSION_CODES#N} release, {@link
 * #VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING} mode may not work correctly for videos rotated
 * by 90 or 270 degrees.
 * <p class=note>
 * When setting the video scaling mode, note that it must be reset after each time the output
 * buffers change. Since the {@link #INFO_OUTPUT_BUFFERS_CHANGED} event is deprecated, you can
 * do this after each time the output format changes.
 *
 * <h4>Using an Input Surface</h4>
 * <p>
 * When using an input Surface, there are no accessible input buffers, as buffers are automatically
 * passed from the input surface to the codec. Calling {@link #dequeueInputBuffer
 * dequeueInputBuffer} will throw an {@code IllegalStateException}, and {@link #getInputBuffers}
 * returns a bogus {@code ByteBuffer[]} array that <strong>MUST NOT</strong> be written into.
 * <p>
 * Call {@link #signalEndOfInputStream} to signal end-of-stream. The input surface will stop
 * submitting data to the codec immediately after this call.
 * <p>
 *
 * <h3>Seeking &amp; Adaptive Playback Support</h3>
 * <p>
 * Video decoders (and in general codecs that consume compressed video data) behave differently
 * regarding seek and format change whether or not they support and are configured for adaptive
 * playback. You can check if a decoder supports {@linkplain
 * CodecCapabilities#FEATURE_AdaptivePlayback adaptive playback} via {@link
 * CodecCapabilities#isFeatureSupported CodecCapabilities.isFeatureSupported(String)}. Adaptive
 * playback support for video decoders is only activated if you configure the codec to decode onto a
 * {@link Surface}.
 *
 * <h4 id=KeyFrames><a name="KeyFrames"></a>Stream Boundary and Key Frames</h4>
 * <p>
 * It is important that the input data after {@link #start} or {@link #flush} starts at a suitable
 * stream boundary: the first frame must be a key frame. A <em>key frame</em> can be decoded
 * completely on its own (for most codecs this means an I-frame), and no frames that are to be
 * displayed after a key frame refer to frames before the key frame.
 * <p>
 * The following table summarizes suitable key frames for various video formats.
 * <table>
 *  <thead>
 *   <tr>
 *    <th>Format</th>
 *    <th>Suitable key frame</th>
 *   </tr>
 *  </thead>
 *  <tbody class=mid>
 *   <tr>
 *    <td>VP9/VP8</td>
 *    <td>a suitable intraframe where no subsequent frames refer to frames prior to this frame.<br>
 *      <i>(There is no specific name for such key frame.)</i></td>
 *   </tr>
 *   <tr>
 *    <td>H.265 HEVC</td>
 *    <td>IDR or CRA</td>
 *   </tr>
 *   <tr>
 *    <td>H.264 AVC</td>
 *    <td>IDR</td>
 *   </tr>
 *   <tr>
 *    <td>MPEG-4<br>H.263<br>MPEG-2</td>
 *    <td>a suitable I-frame where no subsequent frames refer to frames prior to this frame.<br>
 *      <i>(There is no specific name for such key frame.)</td>
 *   </tr>
 *  </tbody>
 * </table>
 *
 * <h4>For decoders that do not support adaptive playback (including when not decoding onto a
 * Surface)</h4>
 * <p>
 * In order to start decoding data that is not adjacent to previously submitted data (i.e. after a
 * seek) you <strong>MUST</strong> flush the decoder. Since all output buffers are immediately
 * revoked at the point of the flush, you may want to first signal then wait for the end-of-stream
 * before you call {@code flush}. It is important that the input data after a flush starts at a
 * suitable stream boundary/key frame.
 * <p class=note>
 * <strong>Note:</strong> the format of the data submitted after a flush must not change; {@link
 * #flush} does not support format discontinuities; for that, a full {@link #stop} - {@link
 * #configure configure(&hellip;)} - {@link #start} cycle is necessary.
 *
 * <p class=note>
 * <strong>Also note:</strong> if you flush the codec too soon after {@link #start} &ndash;
 * generally, before the first output buffer or output format change is received &ndash; you
 * will need to resubmit the codec-specific-data to the codec. See the <a
 * href="#CSD">codec-specific-data section</a> for more info.
 *
 * <h4>For decoders that support and are configured for adaptive playback</h4>
 * <p>
 * In order to start decoding data that is not adjacent to previously submitted data (i.e. after a
 * seek) it is <em>not necessary</em> to flush the decoder; however, input data after the
 * discontinuity must start at a suitable stream boundary/key frame.
 * <p>
 * For some video formats - namely H.264, H.265, VP8 and VP9 - it is also possible to change the
 * picture size or configuration mid-stream. To do this you must package the entire new
 * codec-specific configuration data together with the key frame into a single buffer (including
 * any start codes), and submit it as a <strong>regular</strong> input buffer.
 * <p>
 * You will receive an {@link #INFO_OUTPUT_FORMAT_CHANGED} return value from {@link
 * #dequeueOutputBuffer dequeueOutputBuffer} or a {@link Callback#onOutputFormatChanged
 * onOutputFormatChanged} callback just after the picture-size change takes place and before any
 * frames with the new size have been returned.
 * <p class=note>
 * <strong>Note:</strong> just as the case for codec-specific data, be careful when calling
 * {@link #flush} shortly after you have changed the picture size. If you have not received
 * confirmation of the picture size change, you will need to repeat the request for the new picture
 * size.
 *
 * <h3>Error handling</h3>
 * <p>
 * The factory methods {@link #createByCodecName createByCodecName} and {@link #createDecoderByType
 * createDecoder}/{@link #createEncoderByType EncoderByType} throw {@code IOException} on failure
 * which you must catch or declare to pass up. MediaCodec methods throw {@code
 * IllegalStateException} when the method is called from a codec state that does not allow it; this
 * is typically due to incorrect application API usage. Methods involving secure buffers may throw
 * {@link CryptoException}, which has further error information obtainable from {@link
 * CryptoException#getErrorCode}.
 * <p>
 * Internal codec errors result in a {@link CodecException}, which may be due to media content
 * corruption, hardware failure, resource exhaustion, and so forth, even when the application is
 * correctly using the API. The recommended action when receiving a {@code CodecException}
 * can be determined by calling {@link CodecException#isRecoverable} and {@link
 * CodecException#isTransient}:
 * <ul>
 * <li><strong>recoverable errors:</strong> If {@code isRecoverable()} returns true, then call
 * {@link #stop}, {@link #configure configure(&hellip;)}, and {@link #start} to recover.</li>
 * <li><strong>transient errors:</strong> If {@code isTransient()} returns true, then resources are
 * temporarily unavailable and the method may be retried at a later time.</li>
 * <li><strong>fatal errors:</strong> If both {@code isRecoverable()} and {@code isTransient()}
 * return false, then the {@code CodecException} is fatal and the codec must be {@linkplain #reset
 * reset} or {@linkplain #release released}.</li>
 * </ul>
 * <p>
 * Both {@code isRecoverable()} and {@code isTransient()} do not return true at the same time.
 *
 * <h2 id=History><a name="History"></a>Valid API Calls and API History</h2>
 * <p>
 * This sections summarizes the valid API calls in each state and the API history of the MediaCodec
 * class. For API version numbers, see {@link android.os.Build.VERSION_CODES}.
 *
 * <style>
 * .api > tr > th, .api > tr > td { text-align: center; padding: 4px 4px; }
 * .api > tr > th     { vertical-align: bottom; }
 * .api > tr > td     { vertical-align: middle; }
 * .sml > tr > th, .sml > tr > td { text-align: center; padding: 2px 4px; }
 * .fn { text-align: left; }
 * .fn > code > a { font: 14px/19px Roboto Condensed, sans-serif; }
 * .deg45 {
 *   white-space: nowrap; background: none; border: none; vertical-align: bottom;
 *   width: 30px; height: 83px;
 * }
 * .deg45 > div {
 *   transform: skew(-45deg, 0deg) translate(1px, -67px);
 *   transform-origin: bottom left 0;
 *   width: 30px; height: 20px;
 * }
 * .deg45 > div > div { border: 1px solid #ddd; background: #999; height: 90px; width: 42px; }
 * .deg45 > div > div > div { transform: skew(45deg, 0deg) translate(-55px, 55px) rotate(-45deg); }
 * </style>
 *
 * <table align="right" style="width: 0%">
 *  <thead>
 *   <tr><th>Symbol</th><th>Meaning</th></tr>
 *  </thead>
 *  <tbody class=sml>
 *   <tr><td>&#9679;</td><td>Supported</td></tr>
 *   <tr><td>&#8277;</td><td>Semantics changed</td></tr>
 *   <tr><td>&#9675;</td><td>Experimental support</td></tr>
 *   <tr><td>[ ]</td><td>Deprecated</td></tr>
 *   <tr><td>&#9099;</td><td>Restricted to surface input mode</td></tr>
 *   <tr><td>&#9094;</td><td>Restricted to surface output mode</td></tr>
 *   <tr><td>&#9639;</td><td>Restricted to ByteBuffer input mode</td></tr>
 *   <tr><td>&#8617;</td><td>Restricted to synchronous mode</td></tr>
 *   <tr><td>&#8644;</td><td>Restricted to asynchronous mode</td></tr>
 *   <tr><td>( )</td><td>Can be called, but shouldn't</td></tr>
 *  </tbody>
 * </table>
 *
 * <table style="width: 100%;">
 *  <thead class=api>
 *   <tr>
 *    <th class=deg45><div><div style="background:#4285f4"><div>Uninitialized</div></div></div></th>
 *    <th class=deg45><div><div style="background:#f4b400"><div>Configured</div></div></div></th>
 *    <th class=deg45><div><div style="background:#e67c73"><div>Flushed</div></div></div></th>
 *    <th class=deg45><div><div style="background:#0f9d58"><div>Running</div></div></div></th>
 *    <th class=deg45><div><div style="background:#f7cb4d"><div>End of Stream</div></div></div></th>
 *    <th class=deg45><div><div style="background:#db4437"><div>Error</div></div></div></th>
 *    <th class=deg45><div><div style="background:#666"><div>Released</div></div></div></th>
 *    <th></th>
 *    <th colspan="8">SDK Version</th>
 *   </tr>
 *   <tr>
 *    <th colspan="7">State</th>
 *    <th>Method</th>
 *    <th>16</th>
 *    <th>17</th>
 *    <th>18</th>
 *    <th>19</th>
 *    <th>20</th>
 *    <th>21</th>
 *    <th>22</th>
 *    <th>23</th>
 *   </tr>
 *  </thead>
 *  <tbody class=api>
 *   <tr>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td class=fn>{@link #createByCodecName createByCodecName}</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td class=fn>{@link #createDecoderByType createDecoderByType}</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td class=fn>{@link #createEncoderByType createEncoderByType}</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td class=fn>{@link #createPersistentInputSurface createPersistentInputSurface}</td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>16+</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #configure configure}</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#8277;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>18+</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #createInputSurface createInputSurface}</td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9099;</td>
 *    <td>&#9099;</td>
 *    <td>&#9099;</td>
 *    <td>&#9099;</td>
 *    <td>&#9099;</td>
 *    <td>&#9099;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>(16+)</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #dequeueInputBuffer dequeueInputBuffer}</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9639;</td>
 *    <td>&#9639;</td>
 *    <td>&#9639;</td>
 *    <td>&#8277;&#9639;&#8617;</td>
 *    <td>&#9639;&#8617;</td>
 *    <td>&#9639;&#8617;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #dequeueOutputBuffer dequeueOutputBuffer}</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#8277;&#8617;</td>
 *    <td>&#8617;</td>
 *    <td>&#8617;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #flush flush}</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>18+</td>
 *    <td>18+</td>
 *    <td>18+</td>
 *    <td>18+</td>
 *    <td>18+</td>
 *    <td>18+</td>
 *    <td>-</td>
 *    <td class=fn>{@link #getCodecInfo getCodecInfo}</td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>(21+)</td>
 *    <td>21+</td>
 *    <td>(21+)</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #getInputBuffer getInputBuffer}</td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>16+</td>
 *    <td>(16+)</td>
 *    <td>(16+)</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #getInputBuffers getInputBuffers}</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>[&#8277;&#8617;]</td>
 *    <td>[&#8617;]</td>
 *    <td>[&#8617;]</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>21+</td>
 *    <td>(21+)</td>
 *    <td>(21+)</td>
 *    <td>(21+)</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #getInputFormat getInputFormat}</td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>(21+)</td>
 *    <td>21+</td>
 *    <td>(21+)</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #getInputImage getInputImage}</td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9675;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>18+</td>
 *    <td>18+</td>
 *    <td>18+</td>
 *    <td>18+</td>
 *    <td>18+</td>
 *    <td>18+</td>
 *    <td>-</td>
 *    <td class=fn>{@link #getName getName}</td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>(21+)</td>
 *    <td>21+</td>
 *    <td>21+</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #getOutputBuffer getOutputBuffer}</td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #getOutputBuffers getOutputBuffers}</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>[&#8277;&#8617;]</td>
 *    <td>[&#8617;]</td>
 *    <td>[&#8617;]</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>21+</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #getOutputFormat()}</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>(21+)</td>
 *    <td>21+</td>
 *    <td>21+</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #getOutputFormat(int)}</td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>(21+)</td>
 *    <td>21+</td>
 *    <td>21+</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #getOutputImage getOutputImage}</td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9675;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>16+</td>
 *    <td>(16+)</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #queueInputBuffer queueInputBuffer}</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#8277;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>16+</td>
 *    <td>(16+)</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #queueSecureInputBuffer queueSecureInputBuffer}</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#8277;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td class=fn>{@link #release release}</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #releaseOutputBuffer(int, boolean)}</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#8277;</td>
 *    <td>&#9679;</td>
 *    <td>&#8277;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>21+</td>
 *    <td>21+</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #releaseOutputBuffer(int, long)}</td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9094;</td>
 *    <td>&#9094;</td>
 *    <td>&#9094;</td>
 *   </tr>
 *   <tr>
 *    <td>21+</td>
 *    <td>21+</td>
 *    <td>21+</td>
 *    <td>21+</td>
 *    <td>21+</td>
 *    <td>21+</td>
 *    <td>-</td>
 *    <td class=fn>{@link #reset reset}</td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>21+</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #setCallback(Callback) setCallback}</td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>{@link #setCallback(Callback, Handler) &#8277;}</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>23+</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #setInputSurface setInputSurface}</td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9099;</td>
 *   </tr>
 *   <tr>
 *    <td>23+</td>
 *    <td>23+</td>
 *    <td>23+</td>
 *    <td>23+</td>
 *    <td>23+</td>
 *    <td>(23+)</td>
 *    <td>(23+)</td>
 *    <td class=fn>{@link #setOnFrameRenderedListener setOnFrameRenderedListener}</td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9675; &#9094;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>23+</td>
 *    <td>23+</td>
 *    <td>23+</td>
 *    <td>23+</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #setOutputSurface setOutputSurface}</td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9094;</td>
 *   </tr>
 *   <tr>
 *    <td>19+</td>
 *    <td>19+</td>
 *    <td>19+</td>
 *    <td>19+</td>
 *    <td>19+</td>
 *    <td>(19+)</td>
 *    <td>-</td>
 *    <td class=fn>{@link #setParameters setParameters}</td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>(16+)</td>
 *    <td>(16+)</td>
 *    <td>16+</td>
 *    <td>(16+)</td>
 *    <td>(16+)</td>
 *    <td>-</td>
 *    <td class=fn>{@link #setVideoScalingMode setVideoScalingMode}</td>
 *    <td>&#9094;</td>
 *    <td>&#9094;</td>
 *    <td>&#9094;</td>
 *    <td>&#9094;</td>
 *    <td>&#9094;</td>
 *    <td>&#9094;</td>
 *    <td>&#9094;</td>
 *    <td>&#9094;</td>
 *   </tr>
 *   <tr>
 *    <td>(29+)</td>
 *    <td>29+</td>
 *    <td>29+</td>
 *    <td>29+</td>
 *    <td>(29+)</td>
 *    <td>(29+)</td>
 *    <td>-</td>
 *    <td class=fn>{@link #setAudioPresentation setAudioPresentation}</td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *    <td></td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>18+</td>
 *    <td>18+</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #signalEndOfInputStream signalEndOfInputStream}</td>
 *    <td></td>
 *    <td></td>
 *    <td>&#9099;</td>
 *    <td>&#9099;</td>
 *    <td>&#9099;</td>
 *    <td>&#9099;</td>
 *    <td>&#9099;</td>
 *    <td>&#9099;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>16+</td>
 *    <td>21+(&#8644;)</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #start start}</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#8277;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *   <tr>
 *    <td>-</td>
 *    <td>-</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>16+</td>
 *    <td>-</td>
 *    <td>-</td>
 *    <td class=fn>{@link #stop stop}</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *    <td>&#9679;</td>
 *   </tr>
 *  </tbody>
 * </table>
 */
public final class MediaCodec {
    /**
     * This indicated that the buffer marked as such contains codec
     * initialization / codec specific data instead of media data.
     */
    public static final int BUFFER_FLAG_CODEC_CONFIG = 2;

    /**
     * This indicates that the buffer is decoded and updates the internal state of the decoder,
     * but does not produce any output buffer.
     *
     * When a buffer has this flag set,
     * {@link OnFrameRenderedListener#onFrameRendered(MediaCodec, long, long)} and
     * {@link Callback#onOutputBufferAvailable(MediaCodec, int, BufferInfo)} will not be called for
     * that given buffer.
     *
     * For example, when seeking to a certain frame, that frame may need to reference previous
     * frames in order for it to produce output. The preceding frames can be marked with this flag
     * so that they are only decoded and their data is used when decoding the latter frame that
     * should be initially displayed post-seek.
     * Another example would be trick play, trick play is when a video is fast-forwarded and only a
     * subset of the frames is to be rendered on the screen. The frames not to be rendered can be
     * marked with this flag for the same reason as the above one.
     * Marking frames with this flag improves the overall performance of playing a video stream as
     * fewer frames need to be passed back to the app.
     *
     * In {@link CodecCapabilities#FEATURE_TunneledPlayback}, buffers marked with this flag
     * are not rendered on the output surface.
     *
     * A frame should not be marked with this flag and {@link #BUFFER_FLAG_END_OF_STREAM}
     * simultaneously, doing so will produce a {@link InvalidBufferFlagsException}
     */
    public static final int BUFFER_FLAG_DECODE_ONLY = 32;

    /**
     * This signals the end of stream, i.e. no buffers will be available
     * after this, unless of course, {@link #flush} follows.
     */
    public static final int BUFFER_FLAG_END_OF_STREAM = 4;

    /**
     * This indicates that the (encoded) buffer marked as such contains
     * the data for a key frame.
     */
    public static final int BUFFER_FLAG_KEY_FRAME = 1;

    /**
     * This indicates that the buffer only contains part of a frame,
     * and the decoder should batch the data until a buffer without
     * this flag appears before decoding the frame.
     */
    public static final int BUFFER_FLAG_PARTIAL_FRAME = 8;

    /**
     * This indicates that the (encoded) buffer marked as such contains
     * the data for a key frame.
     *
     * @deprecated Use {@link #BUFFER_FLAG_KEY_FRAME} instead.
     */
    @Deprecated
    public static final int BUFFER_FLAG_SYNC_FRAME = 1;

    /**
     * Configure the codec with a detached output surface.
     * <p>
     * This flag is only defined for a video decoder. MediaCodec
     * configured with this flag will be in Surface mode even though
     * the surface parameter is null.
     *
     * @see detachOutputSurface
     */
    public static final int CONFIGURE_FLAG_DETACHED_SURFACE = 8;

    /**
     * If this codec is to be used as an encoder, pass this flag.
     */
    public static final int CONFIGURE_FLAG_ENCODE = 1;

    /**
     * If this codec is to be used with {@link LinearBlock} and/or {@link
     * HardwareBuffer}, pass this flag.
     * <p>
     * When this flag is set, the following APIs throw {@link IncompatibleWithBlockModelException}.
     * <ul>
     * <li>{@link #getInputBuffer}
     * <li>{@link #getInputImage}
     * <li>{@link #getInputBuffers}
     * <li>{@link #getOutputBuffer}
     * <li>{@link #getOutputImage}
     * <li>{@link #getOutputBuffers}
     * <li>{@link #queueInputBuffer}
     * <li>{@link #queueSecureInputBuffer}
     * <li>{@link #dequeueInputBuffer}
     * <li>{@link #dequeueOutputBuffer}
     * </ul>
     */
    public static final int CONFIGURE_FLAG_USE_BLOCK_MODEL = 2;

    /**
     * This flag should be used on a secure decoder only. MediaCodec configured with this
     * flag does decryption in a separate thread. The flag requires MediaCodec to operate
     * asynchronously and will throw CryptoException if any, in the onCryptoError()
     * callback. Applications should override the default implementation of
     * onCryptoError() and access the associated CryptoException.
     *
     * CryptoException thrown will contain {@link MediaCodec.CryptoInfo}
     * This can be accessed using getCryptoInfo()
     */
    public static final int CONFIGURE_FLAG_USE_CRYPTO_ASYNC = 4;

    public static final int CRYPTO_MODE_AES_CBC = 2;

    public static final int CRYPTO_MODE_AES_CTR = 1;

    public static final int CRYPTO_MODE_UNENCRYPTED = 0;

    /**
     * The output buffers have changed, the client must refer to the new
     * set of output buffers returned by {@link #getOutputBuffers} from
     * this point on.
     *
     * <p>Additionally, this event signals that the video scaling mode
     * may have been reset to the default.</p>
     *
     * @deprecated This return value can be ignored as {@link
     * #getOutputBuffers} has been deprecated.  Client should
     * request a current buffer using on of the get-buffer or
     * get-image methods each time one has been dequeued.
     */
    @Deprecated
    public static final int INFO_OUTPUT_BUFFERS_CHANGED = -3;

    /**
     * The output format has changed, subsequent data will follow the new
     * format. {@link #getOutputFormat()} returns the new format.  Note, that
     * you can also use the new {@link #getOutputFormat(int)} method to
     * get the format for a specific output buffer.  This frees you from
     * having to track output format changes.
     */
    public static final int INFO_OUTPUT_FORMAT_CHANGED = -2;

    /**
     * If a non-negative timeout had been specified in the call
     * to {@link #dequeueOutputBuffer}, indicates that the call timed out.
     */
    public static final int INFO_TRY_AGAIN_LATER = -1;

    /**
     *  Set the HDR10+ metadata on the next queued input frame.
     *
     *  Provide a byte array of data that's conforming to the
     *  user_data_registered_itu_t_t35() syntax of SEI message for ST 2094-40.
     * <p>
     *  For decoders:
     * <p>
     *  When a decoder is configured for one of the HDR10+ profiles that uses
     *  out-of-band metadata (such as {@link
     *  MediaCodecInfo.CodecProfileLevel#VP9Profile2HDR10Plus} or {@link
     *  MediaCodecInfo.CodecProfileLevel#VP9Profile3HDR10Plus}), this
     *  parameter sets the HDR10+ metadata on the next input buffer queued
     *  to the decoder. A decoder supporting these profiles must propagate
     *  the metadata to the format of the output buffer corresponding to this
     *  particular input buffer (under key {@link MediaFormat#KEY_HDR10_PLUS_INFO}).
     *  The metadata should be applied to that output buffer and the buffers
     *  following it (in display order), until the next output buffer (in
     *  display order) upon which an HDR10+ metadata is set.
     * <p>
     *  This parameter shouldn't be set if the decoder is not configured for
     *  an HDR10+ profile that uses out-of-band metadata. In particular,
     *  it shouldn't be set for HDR10+ profiles that uses in-band metadata
     *  where the metadata is embedded in the input buffers, for example
     *  {@link MediaCodecInfo.CodecProfileLevel#HEVCProfileMain10HDR10Plus}.
     * <p>
     *  For encoders:
     * <p>
     *  When an encoder is configured for one of the HDR10+ profiles and the
     *  operates in byte buffer input mode (instead of surface input mode),
     *  this parameter sets the HDR10+ metadata on the next input buffer queued
     *  to the encoder. For the HDR10+ profiles that uses out-of-band metadata
     *  (such as {@link MediaCodecInfo.CodecProfileLevel#VP9Profile2HDR10Plus},
     *  or {@link MediaCodecInfo.CodecProfileLevel#VP9Profile3HDR10Plus}),
     *  the metadata must be propagated to the format of the output buffer
     *  corresponding to this particular input buffer (under key {@link
     *  MediaFormat#KEY_HDR10_PLUS_INFO}). For the HDR10+ profiles that uses
     *  in-band metadata (such as {@link
     *  MediaCodecInfo.CodecProfileLevel#HEVCProfileMain10HDR10Plus}), the
     *  metadata info must be embedded in the corresponding output buffer itself.
     * <p>
     *  This parameter shouldn't be set if the encoder is not configured for
     *  an HDR10+ profile, or if it's operating in surface input mode.
     * <p>
     *
     *  @see MediaFormat#KEY_HDR10_PLUS_INFO
     */
    public static final String PARAMETER_KEY_HDR10_PLUS_INFO = "hdr10-plus-info";

    /**
     * Enable/disable low latency decoding mode.
     * When enabled, the decoder doesn't hold input and output data more than
     * required by the codec standards.
     * The value is an Integer object containing the value 1 to enable
     * or the value 0 to disable.
     *
     * @see #setParameters(Bundle)
     * @see MediaFormat#KEY_LOW_LATENCY
     */
    public static final String PARAMETER_KEY_LOW_LATENCY = "low-latency";

    /**
     * Specify an offset (in micro-second) to be added on top of the timestamps
     * onward. A typical use case is to apply an adjust to the timestamps after
     * a period of pause by the user.
     *
     * This parameter can only be used on an encoder in "surface-input" mode.
     *
     * The value is a long int, indicating the timestamp offset to be applied.
     *
     * @see #setParameters(Bundle)
     */
    public static final String PARAMETER_KEY_OFFSET_TIME = "time-offset-us";

    /**
     * Set the region of interest as QpOffset-Map on the next queued input frame.
     * <p>
     * The associated value is a byte array containing quantization parameter (QP) offsets in
     * raster scan order for the entire frame at 16x16 granularity. The size of the byte array
     * shall be ((frame_width + 15) / 16) * ((frame_height + 15) / 16), where frame_width and
     * frame_height correspond to width and height configured using {@link MediaFormat#KEY_WIDTH}
     * and {@link MediaFormat#KEY_HEIGHT} keys respectively. During encoding, if the coding unit
     * size is larger than 16x16, then the qpOffset information of all 16x16 blocks that
     * encompass the coding unit is combined and used. The QP of target block will be calculated
     * as 'frameQP + offsetQP'. If the result exceeds minQP or maxQP configured then the value
     * will be clamped. Negative offset results in blocks encoded at lower QP than frame QP and
     * positive offsets will result in encoding blocks at higher QP than frame QP. If the areas
     * of negative QP and positive QP are chosen wisely, the overall viewing experience can be
     * improved.
     * <p>
     * If byte array size is smaller than the expected size, components will ignore the
     * configuration and print an error message. If the byte array exceeds the expected size,
     * components will use the initial portion and ignore the rest.
     * <p>
     * The scope of this key is throughout the encoding session until it is reconfigured during
     * running state.
     * <p>
     * @see #setParameters(Bundle)
     */
    public static final String PARAMETER_KEY_QP_OFFSET_MAP = "qp-offset-map";

    /**
     * Set the region of interest as QpOffset-Rects on the next queued input frame.
     * <p>
     * The associated value is a String in the format "Top1,Left1-Bottom1,Right1=Offset1;Top2,
     * Left2-Bottom2,Right2=Offset2;...". If the configuration doesn't follow this pattern,
     * it will be ignored. Co-ordinates (Top, Left), (Top, Right), (Bottom, Left)
     * and (Bottom, Right) form the vertices of bounding box of region of interest in pixels.
     * Pixel (0, 0) points to the top-left corner of the frame. Offset is the suggested
     * quantization parameter (QP) offset of the blocks in the bounding box. The bounding box
     * will get stretched outwards to align to LCU boundaries during encoding. The Qp Offset is
     * integral and shall be in the range [-128, 127]. The QP of target block will be calculated
     * as frameQP + offsetQP. If the result exceeds minQP or maxQP configured then the value will
     * be clamped. Negative offset results in blocks encoded at lower QP than frame QP and
     * positive offsets will result in blocks encoded at higher QP than frame QP. If the areas of
     * negative QP and positive QP are chosen wisely, the overall viewing experience can be
     * improved.
     * <p>
     * If roi (region of interest) rect is outside the frame boundaries, that is, left < 0 or
     * top < 0 or right > width or bottom > height, then rect shall be clamped to the frame
     * boundaries. If roi rect is not valid, that is left > right or top > bottom, then the
     * parameter setting is ignored.
     * <p>
     * The scope of this key is throughout the encoding session until it is reconfigured during
     * running state.
     * <p>
     * The maximum number of contours (rectangles) that can be specified for a given input frame
     * is device specific. Implementations will drop/ignore the rectangles that are beyond their
     * supported limit. Hence it is preferable to place the rects in descending order of
     * importance. Transitively, if the bounding boxes overlap, then the most preferred
     * rectangle's qp offset (earlier rectangle qp offset) will be used to quantize the block.
     * <p>
     * @see #setParameters(Bundle)
     */
    public static final String PARAMETER_KEY_QP_OFFSET_RECTS = "qp-offset-rects";

    /**
     * Request that the encoder produce a sync frame "soon".
     * Provide an Integer with the value 0.
     *
     * @see #setParameters(Bundle)
     */
    public static final String PARAMETER_KEY_REQUEST_SYNC_FRAME = "request-sync";

    /**
     * Temporarily suspend/resume encoding of input data. While suspended
     * input data is effectively discarded instead of being fed into the
     * encoder. This parameter really only makes sense to use with an encoder
     * in "surface-input" mode, as the client code has no control over the
     * input-side of the encoder in that case.
     * The value is an Integer object containing the value 1 to suspend
     * or the value 0 to resume.
     *
     * @see #setParameters(Bundle)
     */
    public static final String PARAMETER_KEY_SUSPEND = "drop-input-frames";

    /**
     * When {@link #PARAMETER_KEY_SUSPEND} is present, the client can also
     * optionally use this key to specify the timestamp (in micro-second)
     * at which the suspend/resume operation takes effect.
     *
     * Note that the specified timestamp must be greater than or equal to the
     * timestamp of any previously queued suspend/resume operations.
     *
     * The value is a long int, indicating the timestamp to suspend/resume.
     *
     * @see #setParameters(Bundle)
     */
    public static final String PARAMETER_KEY_SUSPEND_TIME = "drop-start-time-us";

    /**
     *  Control video peek of the first frame when a codec is configured for tunnel mode with
     *  {@link MediaFormat#KEY_AUDIO_SESSION_ID} while the {@link AudioTrack} is paused.
     * <p>
     *  When disabled (1) after a {@link #flush} or {@link #start}, (2) while the corresponding
     *  {@link AudioTrack} is paused and (3) before any buffers are queued, the first frame is not to
     *  be rendered until either this parameter is enabled or the corresponding {@link AudioTrack}
     *  has begun playback. Once the frame is decoded and ready to be rendered,
     *  {@link OnFirstTunnelFrameReadyListener#onFirstTunnelFrameReady} is called but the frame is
     *  not rendered. The surface continues to show the previously-rendered content, or black if the
     *  surface is new. A subsequent call to {@link AudioTrack#play} renders this frame and triggers
     *  a callback to {@link OnFrameRenderedListener#onFrameRendered}, and video playback begins.
     * <p>
     *  <b>Note</b>: To clear any previously rendered content and show black, configure the
     *  MediaCodec with {@code KEY_PUSH_BLANK_BUFFERS_ON_STOP(1)}, and call {@link #stop} before
     *  pushing new video frames to the codec.
     * <p>
     *  When enabled (1) after a {@link #flush} or {@link #start} and (2) while the corresponding
     *  {@link AudioTrack} is paused, the first frame is rendered as soon as it is decoded, or
     *  immediately, if it has already been decoded. If not already decoded, when the frame is
     *  decoded and ready to be rendered,
     *  {@link OnFirstTunnelFrameReadyListener#onFirstTunnelFrameReady} is called. The frame is then
     *  immediately rendered and {@link OnFrameRenderedListener#onFrameRendered} is subsequently
     *  called.
     * <p>
     *  The value is an Integer object containing the value 1 to enable or the value 0 to disable.
     * <p>
     *  The default for this parameter is <b>enabled</b>. Once a frame has been rendered, changing
     *  this parameter has no effect until a subsequent {@link #flush} or
     *  {@link #stop}/{@link #start}.
     *
     *  @see #setParameters(Bundle)
     */
    public static final String PARAMETER_KEY_TUNNEL_PEEK = "tunnel-peek";

    /**
     * Change a video encoder's target bitrate on the fly. The value is an
     * Integer object containing the new bitrate in bps.
     *
     * @see #setParameters(Bundle)
     */
    public static final String PARAMETER_KEY_VIDEO_BITRATE = "video-bitrate";

    /**
     * The content is scaled to the surface dimensions
     */
    public static final int VIDEO_SCALING_MODE_SCALE_TO_FIT = 1;

    /**
     * The content is scaled, maintaining its aspect ratio, the whole
     * surface area is used, content may be cropped.
     * <p class=note>
     * This mode is only suitable for content with 1:1 pixel aspect ratio as you cannot
     * configure the pixel aspect ratio for a {@link Surface}.
     * <p class=note>
     * As of {@link android.os.Build.VERSION_CODES#N} release, this mode may not work if
     * the video is {@linkplain MediaFormat#KEY_ROTATION rotated} by 90 or 270 degrees.
     */
    public static final int VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING = 2;

    private MediaCodec() {
    }

    /**
     * Thrown when an internal codec error occurs.
     */
    public static final class CodecException {
        /**
         * This indicates required resource was not able to be allocated.
         */
        public static final int ERROR_INSUFFICIENT_RESOURCE = 1100;

        /**
         * This indicates the resource manager reclaimed the media resource used by the codec.
         * <p>
         * With this exception, the codec must be released, as it has moved to terminal state.
         */
        public static final int ERROR_RECLAIMED = 1101;

        private CodecException() {
        }
    }

    /**
     * Thrown when a crypto error occurs while queueing a secure input buffer.
     */
    public static final class CryptoException implements MediaDrmThrowable {
        /**
         * This indicates that the video frame being decrypted exceeds
         * the size of the device's protected output buffers. When
         * encountering this error the app should try playing content
         * of a lower resolution.
         * @deprecated Please use {@link MediaDrm.ErrorCodes#ERROR_FRAME_TOO_LARGE}
         */
        @Deprecated
        public static final int ERROR_FRAME_TOO_LARGE = 8;

        /**
         * This indicates that the output protection levels supported by the
         * device are not sufficient to meet the requirements set by the
         * content owner in the license policy.
         * @deprecated Please use {@link MediaDrm.ErrorCodes#ERROR_INSUFFICIENT_OUTPUT_PROTECTION}
         */
        @Deprecated
        public static final int ERROR_INSUFFICIENT_OUTPUT_PROTECTION = 4;

        /**
         * This indicates that the security level of the device is not
         * sufficient to meet the requirements set by the content owner
         * in the license policy.
         * @deprecated Please use {@link MediaDrm.ErrorCodes#ERROR_INSUFFICIENT_SECURITY}
         */
        @Deprecated
        public static final int ERROR_INSUFFICIENT_SECURITY = 7;

        /**
         * This indicates that the key used for decryption is no longer
         * valid due to license term expiration.  The operation can be retried
         * after updating the expired keys.
         * @deprecated Please use {@link MediaDrm.ErrorCodes#ERROR_KEY_EXPIRED}.
         */
        @Deprecated
        public static final int ERROR_KEY_EXPIRED = 2;

        /**
         * This error indicates that session state has been
         * invalidated. It can occur on devices that are not capable
         * of retaining crypto session state across device
         * suspend/resume. The session must be closed and a new
         * session opened to resume operation.
         * @deprecated Please use {@link MediaDrm.ErrorCodes#ERROR_LOST_STATE}
         */
        @Deprecated
        public static final int ERROR_LOST_STATE = 9;

        /**
         * This indicates that the requested key was not found when trying to
         * perform a decrypt operation.  The operation can be retried after adding
         * the correct decryption key.
         * @deprecated Please use {@link MediaDrm.ErrorCodes#ERROR_NO_KEY}.
         */
        @Deprecated
        public static final int ERROR_NO_KEY = 1;

        /**
         * This indicates that a required crypto resource was not able to be
         * allocated while attempting the requested operation.  The operation
         * can be retried if the app is able to release resources.
         * @deprecated Please use {@link MediaDrm.ErrorCodes#ERROR_RESOURCE_BUSY}
         */
        @Deprecated
        public static final int ERROR_RESOURCE_BUSY = 3;

        /**
         * This indicates that decryption was attempted on a session that is
         * not opened, which could be due to a failure to open the session,
         * closing the session prematurely, or the session being reclaimed
         * by the resource manager.
         * @deprecated Please use {@link MediaDrm.ErrorCodes#ERROR_SESSION_NOT_OPENED}
         */
        @Deprecated
        public static final int ERROR_SESSION_NOT_OPENED = 5;

        /**
         * This indicates that an operation was attempted that could not be
         * supported by the crypto system of the device in its current
         * configuration.  It may occur when the license policy requires
         * device security features that aren't supported by the device,
         * or due to an internal error in the crypto system that prevents
         * the specified security policy from being met.
         * @deprecated Please use {@link MediaDrm.ErrorCodes#ERROR_UNSUPPORTED_OPERATION}
         */
        @Deprecated
        public static final int ERROR_UNSUPPORTED_OPERATION = 6;

        private CryptoException() {
        }
    }

    public static final class MetricsConstants {
        /**
         * Key to extract the codec being used
         * from the {@link MediaCodec#getMetrics} return value.
         * The value is a String.
         */
        public static final String CODEC = "android.media.mediacodec.codec";

        /**
         * Key to extract the flag indicating whether the codec is running
         * as an encoder or decoder from the {@link MediaCodec#getMetrics} return value.
         * The value is an integer.
         * A 0 indicates decoder; 1 indicates encoder.
         */
        public static final String ENCODER = "android.media.mediacodec.encoder";

        /**
         * Key to extract the height (in pixels) of the video track
         * from the {@link MediaCodec#getMetrics} return value.
         * The value is an integer.
         */
        public static final String HEIGHT = "android.media.mediacodec.height";

        /**
         * Key to extract the MIME type
         * from the {@link MediaCodec#getMetrics} return value.
         * The value is a String.
         */
        public static final String MIME_TYPE = "android.media.mediacodec.mime";

        /**
         * Key to extract what the codec mode
         * from the {@link MediaCodec#getMetrics} return value.
         * The value is a String. Values will be one of the constants
         * {@link #MODE_AUDIO} or {@link #MODE_VIDEO}.
         */
        public static final String MODE = "android.media.mediacodec.mode";

        /**
         * The value returned for the key {@link #MODE} when the
         * codec is a audio codec.
         */
        public static final String MODE_AUDIO = "audio";

        /**
         * The value returned for the key {@link #MODE} when the
         * codec is a video codec.
         */
        public static final String MODE_VIDEO = "video";

        /**
         * Key to extract the rotation (in degrees) to properly orient the video
         * from the {@link MediaCodec#getMetrics} return.
         * The value is a integer.
         */
        public static final String ROTATION = "android.media.mediacodec.rotation";

        /**
         * Key to extract the flag indicating whether the codec is running
         * in secure (DRM) mode from the {@link MediaCodec#getMetrics} return value.
         * The value is an integer.
         */
        public static final String SECURE = "android.media.mediacodec.secure";

        /**
         * Key to extract the width (in pixels) of the video track
         * from the {@link MediaCodec#getMetrics} return value.
         * The value is an integer.
         */
        public static final String WIDTH = "android.media.mediacodec.width";

        private MetricsConstants() {
        }
    }
}
