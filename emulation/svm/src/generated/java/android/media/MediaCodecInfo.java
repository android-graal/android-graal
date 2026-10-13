package android.media;

/**
 * Provides information about a given media codec available on the device. You can
 * iterate through all codecs available by querying {@link MediaCodecList}. For example,
 * here's how to find an encoder that supports a given MIME type:
 * <pre>
 * private static MediaCodecInfo selectCodec(String mimeType) {
 *     int numCodecs = MediaCodecList.getCodecCount();
 *     for (int i = 0; i &lt; numCodecs; i++) {
 *         MediaCodecInfo codecInfo = MediaCodecList.getCodecInfoAt(i);
 *
 *         if (!codecInfo.isEncoder()) {
 *             continue;
 *         }
 *
 *         String[] types = codecInfo.getSupportedTypes();
 *         for (int j = 0; j &lt; types.length; j++) {
 *             if (types[j].equalsIgnoreCase(mimeType)) {
 *                 return codecInfo;
 *             }
 *         }
 *     }
 *     return null;
 * }</pre>
 */
public final class MediaCodecInfo {
    /**
     * In this model the codec is not running in a sandboxed process, but
     * written in a memory-safe way. It typically means that the software
     * implementation of the codec is written in a memory-safe language such
     * as Rust.
     */
    public static final int SECURITY_MODEL_MEMORY_SAFE = 1;

    /**
     * In this model the codec is running in a sandboxed process. Even if a
     * malicious content was fed to the codecs in this model, the impact will
     * be contained in the sandboxed process.
     */
    public static final int SECURITY_MODEL_SANDBOXED = 0;

    private MediaCodecInfo() {
    }

    /**
     * Encapsulates the capabilities of a given codec component.
     * For example, what profile/level combinations it supports and what colorspaces
     * it is capable of providing the decoded data in, as well as some
     * codec-type specific capability flags.
     * <p>You can get an instance for a given {@link MediaCodecInfo} object with
     * {@link MediaCodecInfo#getCapabilitiesForType getCapabilitiesForType()}, passing a MIME type.
     */
    public static final class CodecCapabilities {
        /**
         * @deprecated Use {@link #COLOR_Format24bitBGR888}.
         */
        @Deprecated
        public static final int COLOR_Format12bitRGB444 = 3;

        /**
         * @deprecated Use {@link #COLOR_Format32bitABGR8888}.
         */
        @Deprecated
        public static final int COLOR_Format16bitARGB1555 = 5;

        /**
         * @deprecated Use {@link #COLOR_Format32bitABGR8888}.
         */
        @Deprecated
        public static final int COLOR_Format16bitARGB4444 = 4;

        /**
         * @deprecated Use {@link #COLOR_Format16bitRGB565}.
         */
        @Deprecated
        public static final int COLOR_Format16bitBGR565 = 7;

        /**
         * 16 bits per pixel RGB color format, with 5-bit red & blue and 6-bit green component.
         * <p>
         * Using 16-bit little-endian representation, colors stored as Red 15:11, Green 10:5, Blue 4:0.
         * <pre>
         *            byte                   byte
         *  <--------- i --------> | <------ i + 1 ------>
         * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
         * |     BLUE     |      GREEN      |     RED      |
         * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
         *  0           4  5     7   0     2  3           7
         * bit
         * </pre>
         *
         * This format corresponds to {@link android.graphics.PixelFormat#RGB_565} and
         * {@link android.graphics.ImageFormat#RGB_565}.
         */
        public static final int COLOR_Format16bitRGB565 = 6;

        /**
         * @deprecated Use {@link #COLOR_Format24bitBGR888}.
         */
        @Deprecated
        public static final int COLOR_Format18BitBGR666 = 41;

        /**
         * @deprecated Use {@link #COLOR_Format32bitABGR8888}.
         */
        @Deprecated
        public static final int COLOR_Format18bitARGB1665 = 9;

        /**
         * @deprecated Use {@link #COLOR_Format24bitBGR888}.
         */
        @Deprecated
        public static final int COLOR_Format18bitRGB666 = 8;

        /**
         * @deprecated Use {@link #COLOR_Format32bitABGR8888}.
         */
        @Deprecated
        public static final int COLOR_Format19bitARGB1666 = 10;

        /**
         * @deprecated Use {@link #COLOR_Format32bitABGR8888}.
         */
        @Deprecated
        public static final int COLOR_Format24BitABGR6666 = 43;

        /**
         * @deprecated Use {@link #COLOR_Format32bitABGR8888}.
         */
        @Deprecated
        public static final int COLOR_Format24BitARGB6666 = 42;

        /**
         * @deprecated Use {@link #COLOR_Format32bitABGR8888}.
         */
        @Deprecated
        public static final int COLOR_Format24bitARGB1887 = 13;

        /**
         * 24 bits per pixel RGB color format, with 8-bit red, green & blue components.
         * <p>
         * Using 24-bit little-endian representation, colors stored as Red 7:0, Green 15:8, Blue 23:16.
         * <pre>
         *         byte              byte             byte
         *  <------ i -----> | <---- i+1 ----> | <---- i+2 ----->
         * +-----------------+-----------------+-----------------+
         * |       RED       |      GREEN      |       BLUE      |
         * +-----------------+-----------------+-----------------+
         * </pre>
         *
         * This format corresponds to {@link android.graphics.PixelFormat#RGB_888}, and can also be
         * represented as a flexible format by {@link #COLOR_FormatRGBFlexible}.
         */
        public static final int COLOR_Format24bitBGR888 = 12;

        /**
         * @deprecated Use {@link #COLOR_Format24bitBGR888} or {@link #COLOR_FormatRGBFlexible}.
         */
        @Deprecated
        public static final int COLOR_Format24bitRGB888 = 11;

        /**
         * @deprecated Use {@link #COLOR_Format32bitABGR8888}.
         */
        @Deprecated
        public static final int COLOR_Format25bitARGB1888 = 14;

        /**
         * 32 bits per pixel RGBA color format, with 10-bit red, green,
         * blue, and 2-bit alpha components.
         * <p>
         * Using 32-bit little-endian representation, colors stored as
         * Red 9:0, Green 19:10, Blue 29:20, and Alpha 31:30.
         * <pre>
         *         byte              byte             byte              byte
         *  <------ i -----> | <---- i+1 ----> | <---- i+2 ----> | <---- i+3 ----->
         * +-----------------+---+-------------+-------+---------+-----------+-----+
         * |       RED           |      GREEN          |       BLUE          |ALPHA|
         * +-----------------+---+-------------+-------+---------+-----------+-----+
         *  0               7 0 1 2           7 0     3 4       7 0         5 6   7
         * </pre>
         *
         * This corresponds to {@link android.graphics.PixelFormat#RGBA_1010102}.
         */
        public static final int COLOR_Format32bitABGR2101010 = 2130750114;

        /**
         * 32 bits per pixel RGBA color format, with 8-bit red, green, blue, and alpha components.
         * <p>
         * Using 32-bit little-endian representation, colors stored as Red 7:0, Green 15:8,
         * Blue 23:16, and Alpha 31:24.
         * <pre>
         *         byte              byte             byte              byte
         *  <------ i -----> | <---- i+1 ----> | <---- i+2 ----> | <---- i+3 ----->
         * +-----------------+-----------------+-----------------+-----------------+
         * |       RED       |      GREEN      |       BLUE      |      ALPHA      |
         * +-----------------+-----------------+-----------------+-----------------+
         * </pre>
         *
         * This corresponds to {@link android.graphics.PixelFormat#RGBA_8888}.
         */
        public static final int COLOR_Format32bitABGR8888 = 2130747392;

        /**
         * @deprecated Use {@link #COLOR_Format32bitABGR8888} Or {@link #COLOR_FormatRGBAFlexible}.
         */
        @Deprecated
        public static final int COLOR_Format32bitARGB8888 = 16;

        /**
         * @deprecated Use {@link #COLOR_Format32bitABGR8888} Or {@link #COLOR_FormatRGBAFlexible}.
         */
        @Deprecated
        public static final int COLOR_Format32bitBGRA8888 = 15;

        /**
         * 64 bits per pixel RGBA color format, with 16-bit signed
         * floating point red, green, blue, and alpha components.
         * <p>
         *
         * <pre>
         *         byte              byte             byte              byte
         *  <-- i -->|<- i+1 ->|<- i+2 ->|<- i+3 ->|<- i+4 ->|<- i+5 ->|<- i+6 ->|<- i+7 ->
         * +---------+---------+-------------------+---------+---------+---------+---------+
         * |        RED        |       GREEN       |       BLUE        |       ALPHA       |
         * +---------+---------+-------------------+---------+---------+---------+---------+
         *  0       7 0       7 0       7 0       7 0       7 0       7 0       7 0       7
         * </pre>
         *
         * This corresponds to {@link android.graphics.PixelFormat#RGBA_F16}.
         */
        public static final int COLOR_Format64bitABGRFloat = 2130710294;

        /**
         * @deprecated Use {@link #COLOR_Format24bitBGR888}.
         */
        @Deprecated
        public static final int COLOR_Format8bitRGB332 = 2;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV422Flexible}.
         */
        @Deprecated
        public static final int COLOR_FormatCbYCrY = 27;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV422Flexible}.
         */
        @Deprecated
        public static final int COLOR_FormatCrYCbY = 28;

        /**
         * 16 bits per pixel, little-endian Y color format.
         * <p>
         * <pre>
         *            byte                   byte
         *  <--------- i --------> | <------ i + 1 ------>
         * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
         * |                       Y                       |
         * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
         *  0                    7   0                    7
         * bit
         * </pre>
         */
        public static final int COLOR_FormatL16 = 36;

        /**
         * @deprecated Use {@link #COLOR_FormatL8}.
         */
        @Deprecated
        public static final int COLOR_FormatL2 = 33;

        /**
         * @deprecated Use {@link #COLOR_FormatL16}.
         */
        @Deprecated
        public static final int COLOR_FormatL24 = 37;

        /**
         * 32 bits per pixel, little-endian Y color format.
         * <p>
         * <pre>
         *         byte              byte             byte              byte
         *  <------ i -----> | <---- i+1 ----> | <---- i+2 ----> | <---- i+3 ----->
         * +-----------------+-----------------+-----------------+-----------------+
         * |                                   Y                                   |
         * +-----------------+-----------------+-----------------+-----------------+
         *  0               7 0               7 0               7 0               7
         * bit
         * </pre>
         *
         * @deprecated Use {@link #COLOR_FormatL16}.
         */
        @Deprecated
        public static final int COLOR_FormatL32 = 38;

        /**
         * @deprecated Use {@link #COLOR_FormatL8}.
         */
        @Deprecated
        public static final int COLOR_FormatL4 = 34;

        /**
         * 8 bits per pixel Y color format.
         * <p>
         * Each byte contains a single pixel.
         * This format corresponds to {@link android.graphics.PixelFormat#L_8}.
         */
        public static final int COLOR_FormatL8 = 35;

        /**
         * @deprecated Use {@link #COLOR_Format24bitBGR888}.
         */
        @Deprecated
        public static final int COLOR_FormatMonochrome = 1;

        /**
         * Flexible 32 bits per pixel RGBA color format with 8-bit red, green, blue, and alpha
         * components.
         * <p>
         * Use this format with {@link Image}. This format corresponds to
         * {@link android.graphics.ImageFormat#FLEX_RGBA_8888}, and can represent
         * {@link #COLOR_Format32bitBGRA8888}, {@link #COLOR_Format32bitABGR8888} and
         * {@link #COLOR_Format32bitARGB8888} formats.
         *
         * @see Image#getFormat()
         */
        public static final int COLOR_FormatRGBAFlexible = 2134288520;

        /**
         * Flexible 24 bits per pixel RGB color format with 8-bit red, green and blue
         * components.
         * <p>
         * Use this format with {@link Image}. This format corresponds to
         * {@link android.graphics.ImageFormat#FLEX_RGB_888}, and can represent
         * {@link #COLOR_Format24bitBGR888} and {@link #COLOR_Format24bitRGB888} formats.
         * @see Image#getFormat()
         */
        public static final int COLOR_FormatRGBFlexible = 2134292616;

        /**
         * SMIA 10-bit Bayer format.
         */
        public static final int COLOR_FormatRawBayer10bit = 31;

        /**
         * SMIA 8-bit Bayer format.
         * Each byte represents the top 8-bits of a 10-bit signal.
         */
        public static final int COLOR_FormatRawBayer8bit = 30;

        /**
         * SMIA 8-bit compressed Bayer format.
         * Each byte represents a sample from the 10-bit signal that is compressed into 8-bits
         * using DPCM/PCM compression, as defined by the SMIA Functional Specification.
         */
        public static final int COLOR_FormatRawBayer8bitcompressed = 32;

        public static final int COLOR_FormatSurface = 2130708361;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV422Flexible}.
         */
        @Deprecated
        public static final int COLOR_FormatYCbYCr = 25;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV422Flexible}.
         */
        @Deprecated
        public static final int COLOR_FormatYCrYCb = 26;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV420Flexible}.
         */
        @Deprecated
        public static final int COLOR_FormatYUV411PackedPlanar = 18;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV420Flexible}.
         */
        @Deprecated
        public static final int COLOR_FormatYUV411Planar = 17;

        /**
         * Flexible 12 bits per pixel, subsampled YUV color format with 8-bit chroma and luma
         * components.
         * <p>
         * Chroma planes are subsampled by 2 both horizontally and vertically.
         * Use this format with {@link Image}.
         * This format corresponds to {@link android.graphics.ImageFormat#YUV_420_888},
         * and can represent the {@link #COLOR_FormatYUV411Planar},
         * {@link #COLOR_FormatYUV411PackedPlanar}, {@link #COLOR_FormatYUV420Planar},
         * {@link #COLOR_FormatYUV420PackedPlanar}, {@link #COLOR_FormatYUV420SemiPlanar}
         * and {@link #COLOR_FormatYUV420PackedSemiPlanar} formats.
         *
         * @see Image#getFormat
         */
        public static final int COLOR_FormatYUV420Flexible = 2135033992;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV420Flexible}.
         */
        @Deprecated
        public static final int COLOR_FormatYUV420PackedPlanar = 20;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV420Flexible}.
         */
        @Deprecated
        public static final int COLOR_FormatYUV420PackedSemiPlanar = 39;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV420Flexible}.
         */
        @Deprecated
        public static final int COLOR_FormatYUV420Planar = 19;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV420Flexible}.
         */
        @Deprecated
        public static final int COLOR_FormatYUV420SemiPlanar = 21;

        /**
         * Flexible 16 bits per pixel, subsampled YUV color format with 8-bit chroma and luma
         * components.
         * <p>
         * Chroma planes are horizontally subsampled by 2. Use this format with {@link Image}.
         * This format corresponds to {@link android.graphics.ImageFormat#YUV_422_888},
         * and can represent the {@link #COLOR_FormatYCbYCr}, {@link #COLOR_FormatYCrYCb},
         * {@link #COLOR_FormatCbYCrY}, {@link #COLOR_FormatCrYCbY},
         * {@link #COLOR_FormatYUV422Planar}, {@link #COLOR_FormatYUV422PackedPlanar},
         * {@link #COLOR_FormatYUV422SemiPlanar} and {@link #COLOR_FormatYUV422PackedSemiPlanar}
         * formats.
         *
         * @see Image#getFormat
         */
        public static final int COLOR_FormatYUV422Flexible = 2135042184;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV422Flexible}.
         */
        @Deprecated
        public static final int COLOR_FormatYUV422PackedPlanar = 23;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV422Flexible}.
         */
        @Deprecated
        public static final int COLOR_FormatYUV422PackedSemiPlanar = 40;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV422Flexible}.
         */
        @Deprecated
        public static final int COLOR_FormatYUV422Planar = 22;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV422Flexible}.
         */
        @Deprecated
        public static final int COLOR_FormatYUV422SemiPlanar = 24;

        /**
         * Flexible 24 bits per pixel YUV color format with 8-bit chroma and luma
         * components.
         * <p>
         * Chroma planes are not subsampled. Use this format with {@link Image}.
         * This format corresponds to {@link android.graphics.ImageFormat#YUV_444_888},
         * and can represent the {@link #COLOR_FormatYUV444Interleaved} format.
         * @see Image#getFormat
         */
        public static final int COLOR_FormatYUV444Flexible = 2135181448;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV444Flexible}.
         */
        @Deprecated
        public static final int COLOR_FormatYUV444Interleaved = 29;

        /**
         * P010 is 10-bit-per component 4:2:0 YCbCr semiplanar format.
         * <p>
         * This format uses 24 allocated bits per pixel with 15 bits of
         * data per pixel. Chroma planes are subsampled by 2 both
         * horizontally and vertically. Each chroma and luma component
         * has 16 allocated bits in little-endian configuration with 10
         * MSB of actual data.
         *
         * <pre>
         *            byte                   byte
         *  <--------- i --------> | <------ i + 1 ------>
         * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
         * |     UNUSED      |      Y/Cb/Cr                |
         * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
         *  0               5 6   7 0                    7
         * bit
         * </pre>
         *
         * Use this format with {@link Image}. This format corresponds
         * to {@link android.graphics.ImageFormat#YCBCR_P010}.
         * <p>
         */
        public static final int COLOR_FormatYUVP010 = 54;

        /**
         * P210 is 10-bit-per component 4:2:2 YCbCr semiplanar format.
         * <p>
         * This format uses 32 allocated bits per pixel with 20 bits of
         * data per pixel. Chroma planes are subsampled by 2 both
         * horizontally. Each chroma and luma component
         * has 16 allocated bits in little-endian configuration with 10
         * MSB of actual data.
         *
         * <pre>
         *            byte                   byte
         *  <--------- i --------> | <------ i + 1 ------>
         * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
         * |     UNUSED      |      Y/Cb/Cr                |
         * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
         *  0               5 6   7 0                    7
         * bit
         * </pre>
         *
         * Use this format with {@link Image}. This format corresponds
         * to {@link android.graphics.ImageFormat#YCBCR_P210}.
         * <p>
         */
        public static final int COLOR_FormatYUVP210 = 60;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV420Flexible}.
         */
        @Deprecated
        public static final int COLOR_QCOM_FormatYUV420SemiPlanar = 2141391872;

        /**
         * @deprecated Use {@link #COLOR_FormatYUV420Flexible}.
         */
        @Deprecated
        public static final int COLOR_TI_FormatYUV420PackedSemiPlanar = 2130706688;

        /**
         * <b>video decoder only</b>: codec supports seamless resolution changes.
         */
        public static final String FEATURE_AdaptivePlayback = "adaptive-playback";

        /**
         * <b>video decoder only</b>: codec supports detaching the
         * output surface when in Surface mode.
         * <p> If true, the codec can be configured in Surface mode
         * without an actual surface (in detached surface mode).
         * @see MediaCodec#CONFIGURE_FLAG_DETACHED_SURFACE
         */
        public static final String FEATURE_DetachedSurface = "detached-surface";

        /**
         * <b>video decoder only</b>: codec supports dynamically
         * changing color aspects.
         * <p>
         * If true, the codec can propagate color aspect changes during
         * decoding. This is only meaningful at session boundaries, e.g.
         * upon processing Picture Parameter Sets prior to a new IDR.
         * The color aspects may come from the bitstream, or may be
         * provided using {@link MediaCodec#setParameters} calls.
         * <p>
         * If the codec supports both 8-bit and 10-bit profiles, this
         * feature means that the codec can dynamically switch between 8
         * and 10-bit profiles, but this is restricted to Surface mode
         * only.
         * <p>
         * If the device supports HDR transfer functions, switching
         * between SDR and HDR transfer is also supported. Together with
         * the previous clause this means that switching between SDR and
         * HDR sessions are supported in Surface mode, as SDR is
         * typically encoded at 8-bit and HDR at 10-bit.
         */
        public static final String FEATURE_DynamicColorAspects = "dynamic-color-aspects";

        /**
         * If true, the timestamp of each output buffer is derived from the timestamp of the input
         * buffer that produced the output. If false, the timestamp of each output buffer is
         * derived from the timestamp of the first input buffer.
         */
        public static final String FEATURE_DynamicTimestamp = "dynamic-timestamp";

        /**
         * <b>video encoder only</b>: codec supports exporting encoding statistics.
         * Encoders with this feature can provide the App clients with the encoding statistics
         * information about the frame.
         * The scope of encoding statistics is controlled by
         * {@link MediaFormat#KEY_VIDEO_ENCODING_STATISTICS_LEVEL}.
         *
         * @see MediaFormat#KEY_VIDEO_ENCODING_STATISTICS_LEVEL
         */
        public static final String FEATURE_EncodingStatistics = "encoding-statistics";

        /**
         * <b>decoder only</b>If true, the codec supports partial (including multiple) access units
         * per input buffer.
         */
        public static final String FEATURE_FrameParsing = "frame-parsing";

        /**
         * <b>video encoder only</b>: codec supports HDR editing.
         * <p>
         * HDR editing support means that the codec accepts 10-bit HDR
         * input surface, and it is capable of generating any HDR
         * metadata required from both YUV and RGB input when the
         * metadata is not present. This feature is only meaningful when
         * using an HDR capable profile (and 10-bit HDR input).
         * <p>
         * This feature implies that the codec is capable of encoding at
         * least one HDR format, and that it supports RGBA_1010102 as
         * well as P010, and optionally RGBA_FP16 input formats, and
         * that the encoder can generate HDR metadata for all supported
         * HDR input formats.
         */
        public static final String FEATURE_HdrEditing = "hdr-editing";

        /**
         * <b>video encoder only</b>: codec supports HLG editing.
         * <p>
         * HLG editing support means that the codec accepts 10-bit HDR
         * input surface in both YUV and RGB pixel format. This feature
         * is only meaningful when using a 10-bit (HLG) profile and
         * 10-bit input.
         * <p>
         * This feature implies that the codec is capable of encoding
         * 10-bit format, and that it supports RGBA_1010102 as
         * well as P010, and optionally RGBA_FP16 input formats.
         * <p>
         * The difference between this feature and {@link
         * FEATURE_HdrEditing} is that HLG does not require the
         * generation of HDR metadata and does not use an explicit HDR
         * profile.
         */
        public static final String FEATURE_HlgEditing = "hlg-editing";

        /**
         * <b>video encoder only</b>: codec supports intra refresh.
         */
        public static final String FEATURE_IntraRefresh = "intra-refresh";

        /**
         * <b>decoder only</b>: codec supports low latency decoding.
         * If supported, clients can enable the low latency mode for the decoder.
         * When the mode is enabled, the decoder doesn't hold input and output data more than
         * required by the codec standards.
         */
        public static final String FEATURE_LowLatency = "low-latency";

        /**
         * If true, the codec supports multiple access units (for decoding, or to output for
         * encoders). If false, the codec only supports single access units. Producing multiple
         * access units for output is an optional feature.
         */
        public static final String FEATURE_MultipleFrames = "multiple-frames";

        /**
         * <b>video decoder only</b>: codec supports queuing partial frames.
         */
        public static final String FEATURE_PartialFrame = "partial-frame";

        /**
         * <b>video encoder only</b>: codec supports quantization parameter bounds.
         * @see MediaFormat#KEY_VIDEO_QP_MAX
         * @see MediaFormat#KEY_VIDEO_QP_MIN
         */
        public static final String FEATURE_QpBounds = "qp-bounds";

        /**
         * <b>video encoder only</b>: codec supports region of interest encoding.
         * <p>
         * RoI encoding support means the codec accepts information that specifies the relative
         * importance of different portions of each video frame. This allows the encoder to
         * separate a video frame into critical and non-critical regions, and use more bits
         * (better quality) to represent the critical regions and de-prioritize non-critical
         * regions. In other words, the encoder chooses a negative qp bias for the critical
         * portions and a zero or positive qp bias for the non-critical portions.
         * <p>
         * At a basic level, if the encoder decides to encode each frame with a uniform
         * quantization value 'qpFrame' and a 'qpBias' is chosen/suggested for an LCU of the
         * frame, then the actual qp of the LCU will be 'qpFrame + qpBias', although this value
         * can be clamped basing on the min-max configured qp bounds for the current encoding
         * session.
         * <p>
         * In a shot, if a group of LCUs pan out quickly they can be marked as non-critical
         * thereby enabling the encoder to reserve fewer bits during their encoding. Contrarily,
         * LCUs that remain in shot for a prolonged duration can be encoded at better quality in
         * one frame thereby setting-up an excellent long-term reference for all future frames.
         * <p>
         * Note that by offsetting the quantization of each LCU, the overall bit allocation will
         * differ from the originally estimated bit allocation, and the encoder will adjust the
         * frame quantization for subsequent frames to meet the bitrate target. An effective
         * selection of critical regions can set-up a golden reference and this can compensate
         * for the bit burden that was introduced due to encoding RoI's at better quality.
         * On the other hand, an ineffective choice of critical regions might increase the
         * quality of certain parts of the image but this can hamper quality in subsequent frames.
         * <p>
         * @see MediaCodec#PARAMETER_KEY_QP_OFFSET_MAP
         * @see MediaCodec#PARAMETER_KEY_QP_OFFSET_RECTS
         */
        public static final String FEATURE_Roi = "region-of-interest";

        /**
         * <b>video decoder only</b>: codec supports secure decryption.
         */
        public static final String FEATURE_SecurePlayback = "secure-playback";

        /**
         * <b>video or audio decoder only</b>: codec supports tunneled playback.
         */
        public static final String FEATURE_TunneledPlayback = "tunneled-playback";

        private CodecCapabilities() {
        }
    }

    /**
     * Encapsulates the profiles available for a codec component.
     * <p>You can get a set of {@link MediaCodecInfo.CodecProfileLevel} objects for a given
     * {@link MediaCodecInfo} object from the
     * {@link MediaCodecInfo.CodecCapabilities#profileLevels} field.
     */
    public static final class CodecProfileLevel {
        public static final int AACObjectELD = 39;

        public static final int AACObjectERLC = 17;

        public static final int AACObjectERScalable = 20;

        public static final int AACObjectHE = 5;

        public static final int AACObjectHE_PS = 29;

        public static final int AACObjectLC = 2;

        public static final int AACObjectLD = 23;

        public static final int AACObjectLTP = 4;

        public static final int AACObjectMain = 1;

        public static final int AACObjectSSR = 3;

        public static final int AACObjectScalable = 6;

        /**
         * xHE-AAC (includes USAC)
         */
        public static final int AACObjectXHE = 42;

        /**
         * AC-4 codec level corresponding to mdcompat 0 as per ETSI TS 103 190-2 v1.2.1
         */
        public static final int AC4Level0 = 1;

        /**
         * AC-4 codec level corresponding to mdcompat 1 as per ETSI TS 103 190-2 v1.2.1
         */
        public static final int AC4Level1 = 2;

        /**
         * AC-4 codec level corresponding to mdcompat 2 as per ETSI TS 103 190-2 v1.2.1
         */
        public static final int AC4Level2 = 4;

        /**
         * AC-4 codec level corresponding to mdcompat 3 as per ETSI TS 103 190-2 v1.2.1
         */
        public static final int AC4Level3 = 8;

        /**
         * AC-4 codec level corresponding to mdcompat 4 as per ETSI TS 103 190-2 v1.2.1
         */
        public static final int AC4Level4 = 16;

        /**
         * AC-4 codec profile with bitstream_version 0 and presentation_version 0
         * as per ETSI TS 103 190-2 v1.2.1
         */
        public static final int AC4Profile00 = 257;

        /**
         * AC-4 codec profile with bitstream_version 1 and presentation_version 0
         * as per ETSI TS 103 190-2 v1.2.1
         */
        public static final int AC4Profile10 = 513;

        /**
         * AC-4 codec profile with bitstream_version 1 and presentation_version 1
         * as per ETSI TS 103 190-2 v1.2.1
         */
        public static final int AC4Profile11 = 514;

        /**
         * AC-4 codec profile with bitstream_version 2 and presentation_version 1
         * as per ETSI TS 103 190-2 v1.2.1
         */
        public static final int AC4Profile21 = 1026;

        /**
         * AC-4 codec profile with bitstream_version 2 and presentation_version 2
         * as per ETSI TS 103 190-2 v1.2.1
         */
        public static final int AC4Profile22 = 1028;

        /**
         * APV Codec Level 1.1, Band 0 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel11Band0 = 513;

        /**
         * APV Codec Level 1.1, Band 1 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel11Band1 = 514;

        /**
         * APV Codec Level 1.1, Band 2 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel11Band2 = 516;

        /**
         * APV Codec Level 1.1, Band 3 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel11Band3 = 520;

        /**
         * APV Codec Level 1, Band 0 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel1Band0 = 257;

        /**
         * APV Codec Level 1, Band 1 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel1Band1 = 258;

        /**
         * APV Codec Level 1, Band 2 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel1Band2 = 260;

        /**
         * APV Codec Level 1, Band 3 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel1Band3 = 264;

        /**
         * APV Codec Level 2.1, Band 0 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel21Band0 = 2049;

        /**
         * APV Codec Level 2.1, Band 1 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel21Band1 = 2050;

        /**
         * APV Codec Level 2.1, Band 2 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel21Band2 = 2052;

        /**
         * APV Codec Level 2.1, Band 3 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel21Band3 = 2056;

        /**
         * APV Codec Level 2, Band 0 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel2Band0 = 1025;

        /**
         * APV Codec Level 2, Band 1 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel2Band1 = 1026;

        /**
         * APV Codec Level 2, Band 2 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel2Band2 = 1028;

        /**
         * APV Codec Level 2, Band 3 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel2Band3 = 1032;

        /**
         * APV Codec Level 3.1, Band 0 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel31Band0 = 8193;

        /**
         * APV Codec Level 3.1, Band 1 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel31Band1 = 8194;

        /**
         * APV Codec Level 3.1, Band 2 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel31Band2 = 8196;

        /**
         * APV Codec Level 3.1, Band 3 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel31Band3 = 8200;

        /**
         * APV Codec Level 3, Band 0 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel3Band0 = 4097;

        /**
         * APV Codec Level 3, Band 1 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel3Band1 = 4098;

        /**
         * APV Codec Level 3, Band 2 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel3Band2 = 4100;

        /**
         * APV Codec Level 3, Band 3 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel3Band3 = 4104;

        /**
         * APV Codec Level 4.1, Band 0 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel41Band0 = 32769;

        /**
         * APV Codec Level 4.1, Band 1 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel41Band1 = 32770;

        /**
         * APV Codec Level 4.1, Band 2 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel41Band2 = 32772;

        /**
         * APV Codec Level 4.1, Band 3 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel41Band3 = 32776;

        /**
         * APV Codec Level 4, Band 0 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel4Band0 = 16385;

        /**
         * APV Codec Level 4, Band 1 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel4Band1 = 16386;

        /**
         * APV Codec Level 4, Band 2 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel4Band2 = 16388;

        /**
         * APV Codec Level 4, Band 3 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel4Band3 = 16392;

        /**
         * APV Codec Level 5.1, Band 0 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel51Band0 = 131073;

        /**
         * APV Codec Level 5.1, Band 1 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel51Band1 = 131074;

        /**
         * APV Codec Level 5.1, Band 2 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel51Band2 = 131076;

        /**
         * APV Codec Level 5.1, Band 3 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel51Band3 = 131080;

        /**
         * APV Codec Level 5, Band 0 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel5Band0 = 65537;

        /**
         * APV Codec Level 5, Band 1 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel5Band1 = 65538;

        /**
         * APV Codec Level 5, Band 2 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel5Band2 = 65540;

        /**
         * APV Codec Level 5, Band 3 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel5Band3 = 65544;

        /**
         * APV Codec Level 6.1, Band 0 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel61Band0 = 524289;

        /**
         * APV Codec Level 6.1, Band 1 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel61Band1 = 524290;

        /**
         * APV Codec Level 6.1, Band 2 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel61Band2 = 524292;

        /**
         * APV Codec Level 6.1, Band 3 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel61Band3 = 524296;

        /**
         * APV Codec Level 6, Band 0 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel6Band0 = 262145;

        /**
         * APV Codec Level 6, Band 1 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel6Band1 = 262146;

        /**
         * APV Codec Level 6, Band 2 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel6Band2 = 262148;

        /**
         * APV Codec Level 6, Band 3 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel6Band3 = 262152;

        /**
         * APV Codec Level 7.1, Band 0 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel71Band0 = 2097153;

        /**
         * APV Codec Level 7.1, Band 1 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel71Band1 = 2097154;

        /**
         * APV Codec Level 7.1, Band 2 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel71Band2 = 2097156;

        /**
         * APV Codec Level 7.1, Band 3 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel71Band3 = 2097160;

        /**
         * APV Codec Level 7, Band 0 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel7Band0 = 1048577;

        /**
         * APV Codec Level 7, Band 1 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel7Band1 = 1048578;

        /**
         * APV Codec Level 7, Band 2 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel7Band2 = 1048580;

        /**
         * APV Codec Level 7, Band 3 as per IETF lim-apv-02, 10.1.4
         */
        public static final int APVLevel7Band3 = 1048584;

        /**
         * APV codec profile 422-10 as per IETF lim-apv-02, 10.1.3.1.1
         */
        public static final int APVProfile422_10 = 1;

        /**
         * APV codec profile 422-10 as per IETF lim-apv-02, 10.1.3.1.1
         * with HDR10.
         */
        public static final int APVProfile422_10HDR10 = 4096;

        /**
         * APV codec profile 422-10 as per IETF lim-apv-02, 10.1.3.1.1
         * with HDR10Plus.
         */
        public static final int APVProfile422_10HDR10Plus = 8192;

        public static final int AV1Level2 = 1;

        public static final int AV1Level21 = 2;

        public static final int AV1Level22 = 4;

        public static final int AV1Level23 = 8;

        public static final int AV1Level3 = 16;

        public static final int AV1Level31 = 32;

        public static final int AV1Level32 = 64;

        public static final int AV1Level33 = 128;

        public static final int AV1Level4 = 256;

        public static final int AV1Level41 = 512;

        public static final int AV1Level42 = 1024;

        public static final int AV1Level43 = 2048;

        public static final int AV1Level5 = 4096;

        public static final int AV1Level51 = 8192;

        public static final int AV1Level52 = 16384;

        public static final int AV1Level53 = 32768;

        public static final int AV1Level6 = 65536;

        public static final int AV1Level61 = 131072;

        public static final int AV1Level62 = 262144;

        public static final int AV1Level63 = 524288;

        public static final int AV1Level7 = 1048576;

        public static final int AV1Level71 = 2097152;

        public static final int AV1Level72 = 4194304;

        public static final int AV1Level73 = 8388608;

        /**
         * AV1 Main profile 4:2:0 10-bit
         *
         * See definition in
         * <a href="https://aomedia.org/av1-bitstream-and-decoding-process-specification/">AV1 Specification</a>
         * Annex A.
         */
        public static final int AV1ProfileMain10 = 2;

        /**
         * AV1 Main profile 4:2:0 10-bit with HDR10.
         */
        public static final int AV1ProfileMain10HDR10 = 4096;

        /**
         * AV1 Main profile 4:2:0 10-bit with HDR10Plus.
         */
        public static final int AV1ProfileMain10HDR10Plus = 8192;

        /**
         * AV1 Main profile 4:2:0 8-bit
         *
         * See definition in
         * <a href="https://aomedia.org/av1-bitstream-and-decoding-process-specification/">AV1 Specification</a>
         * Annex A.
         */
        public static final int AV1ProfileMain8 = 1;

        public static final int AVCLevel1 = 1;

        public static final int AVCLevel11 = 4;

        public static final int AVCLevel12 = 8;

        public static final int AVCLevel13 = 16;

        public static final int AVCLevel1b = 2;

        public static final int AVCLevel2 = 32;

        public static final int AVCLevel21 = 64;

        public static final int AVCLevel22 = 128;

        public static final int AVCLevel3 = 256;

        public static final int AVCLevel31 = 512;

        public static final int AVCLevel32 = 1024;

        public static final int AVCLevel4 = 2048;

        public static final int AVCLevel41 = 4096;

        public static final int AVCLevel42 = 8192;

        public static final int AVCLevel5 = 16384;

        public static final int AVCLevel51 = 32768;

        public static final int AVCLevel52 = 65536;

        public static final int AVCLevel6 = 131072;

        public static final int AVCLevel61 = 262144;

        public static final int AVCLevel62 = 524288;

        /**
         * AVC Baseline profile.
         * See definition in
         * <a href="https://www.itu.int/rec/T-REC-H.264-201704-I">H.264 recommendation</a>,
         * Annex A.
         */
        public static final int AVCProfileBaseline = 1;

        /**
         * AVC Constrained Baseline profile.
         * See definition in
         * <a href="https://www.itu.int/rec/T-REC-H.264-201704-I">H.264 recommendation</a>,
         * Annex A.
         */
        public static final int AVCProfileConstrainedBaseline = 65536;

        /**
         * AVC Constrained High profile.
         * See definition in
         * <a href="https://www.itu.int/rec/T-REC-H.264-201704-I">H.264 recommendation</a>,
         * Annex A.
         */
        public static final int AVCProfileConstrainedHigh = 524288;

        /**
         * AVC Extended profile.
         * See definition in
         * <a href="https://www.itu.int/rec/T-REC-H.264-201704-I">H.264 recommendation</a>,
         * Annex A.
         */
        public static final int AVCProfileExtended = 4;

        /**
         * AVC High profile.
         * See definition in
         * <a href="https://www.itu.int/rec/T-REC-H.264-201704-I">H.264 recommendation</a>,
         * Annex A.
         */
        public static final int AVCProfileHigh = 8;

        /**
         * AVC High 10 profile.
         * See definition in
         * <a href="https://www.itu.int/rec/T-REC-H.264-201704-I">H.264 recommendation</a>,
         * Annex A.
         */
        public static final int AVCProfileHigh10 = 16;

        /**
         * AVC High 4:2:2 profile.
         * See definition in
         * <a href="https://www.itu.int/rec/T-REC-H.264-201704-I">H.264 recommendation</a>,
         * Annex A.
         */
        public static final int AVCProfileHigh422 = 32;

        /**
         * AVC High 4:4:4 profile.
         * See definition in
         * <a href="https://www.itu.int/rec/T-REC-H.264-201704-I">H.264 recommendation</a>,
         * Annex A.
         */
        public static final int AVCProfileHigh444 = 64;

        /**
         * AVC Main profile.
         * See definition in
         * <a href="https://www.itu.int/rec/T-REC-H.264-201704-I">H.264 recommendation</a>,
         * Annex A.
         */
        public static final int AVCProfileMain = 2;

        /**
         * DTS codec profile for DTS HRA.
         */
        public static final int DTS_HDProfileHRA = 1;

        /**
         * DTS codec profile for DTS Express.
         */
        public static final int DTS_HDProfileLBR = 2;

        /**
         * DTS codec profile for DTS-HD Master Audio
         */
        public static final int DTS_HDProfileMA = 4;

        /**
         * DTS codec profile for DTS:X Profile 1
         */
        public static final int DTS_UHDProfileP1 = 1;

        /**
         * DTS codec profile for DTS:X Profile 2
         */
        public static final int DTS_UHDProfileP2 = 2;

        public static final int DolbyVisionLevel8k30 = 1024;

        public static final int DolbyVisionLevel8k60 = 2048;

        public static final int DolbyVisionLevelFhd24 = 4;

        public static final int DolbyVisionLevelFhd30 = 8;

        public static final int DolbyVisionLevelFhd60 = 16;

        public static final int DolbyVisionLevelHd24 = 1;

        public static final int DolbyVisionLevelHd30 = 2;

        public static final int DolbyVisionLevelUhd120 = 512;

        public static final int DolbyVisionLevelUhd24 = 32;

        public static final int DolbyVisionLevelUhd30 = 64;

        public static final int DolbyVisionLevelUhd48 = 128;

        public static final int DolbyVisionLevelUhd60 = 256;

        /**
         * Dolby Vision AV1 profile
         */
        public static final int DolbyVisionProfileDvav110 = 1024;

        public static final int DolbyVisionProfileDvavPen = 2;

        public static final int DolbyVisionProfileDvavPer = 1;

        public static final int DolbyVisionProfileDvavSe = 512;

        public static final int DolbyVisionProfileDvheDen = 8;

        public static final int DolbyVisionProfileDvheDer = 4;

        public static final int DolbyVisionProfileDvheDtb = 128;

        public static final int DolbyVisionProfileDvheDth = 64;

        public static final int DolbyVisionProfileDvheDtr = 16;

        public static final int DolbyVisionProfileDvheSt = 256;

        public static final int DolbyVisionProfileDvheStn = 32;

        public static final int H263Level10 = 1;

        public static final int H263Level20 = 2;

        public static final int H263Level30 = 4;

        public static final int H263Level40 = 8;

        public static final int H263Level45 = 16;

        public static final int H263Level50 = 32;

        public static final int H263Level60 = 64;

        public static final int H263Level70 = 128;

        public static final int H263ProfileBackwardCompatible = 4;

        public static final int H263ProfileBaseline = 1;

        public static final int H263ProfileH320Coding = 2;

        public static final int H263ProfileHighCompression = 32;

        public static final int H263ProfileHighLatency = 256;

        public static final int H263ProfileISWV2 = 8;

        public static final int H263ProfileISWV3 = 16;

        public static final int H263ProfileInterlace = 128;

        public static final int H263ProfileInternet = 64;

        public static final int HEVCHighTierLevel1 = 2;

        public static final int HEVCHighTierLevel2 = 8;

        public static final int HEVCHighTierLevel21 = 32;

        public static final int HEVCHighTierLevel3 = 128;

        public static final int HEVCHighTierLevel31 = 512;

        public static final int HEVCHighTierLevel4 = 2048;

        public static final int HEVCHighTierLevel41 = 8192;

        public static final int HEVCHighTierLevel5 = 32768;

        public static final int HEVCHighTierLevel51 = 131072;

        public static final int HEVCHighTierLevel52 = 524288;

        public static final int HEVCHighTierLevel6 = 2097152;

        public static final int HEVCHighTierLevel61 = 8388608;

        public static final int HEVCHighTierLevel62 = 33554432;

        public static final int HEVCMainTierLevel1 = 1;

        public static final int HEVCMainTierLevel2 = 4;

        public static final int HEVCMainTierLevel21 = 16;

        public static final int HEVCMainTierLevel3 = 64;

        public static final int HEVCMainTierLevel31 = 256;

        public static final int HEVCMainTierLevel4 = 1024;

        public static final int HEVCMainTierLevel41 = 4096;

        public static final int HEVCMainTierLevel5 = 16384;

        public static final int HEVCMainTierLevel51 = 65536;

        public static final int HEVCMainTierLevel52 = 262144;

        public static final int HEVCMainTierLevel6 = 1048576;

        public static final int HEVCMainTierLevel61 = 4194304;

        public static final int HEVCMainTierLevel62 = 16777216;

        public static final int HEVCProfileMain = 1;

        public static final int HEVCProfileMain10 = 2;

        public static final int HEVCProfileMain10HDR10 = 4096;

        public static final int HEVCProfileMain10HDR10Plus = 8192;

        public static final int HEVCProfileMainStill = 4;

        /**
         * IAMF profile using the
         * <a href="https://aomediacodec.github.io/iamf/#profiles-base">base profile</a>
         * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
         * in AAC.
         */
        public static final int IAMFProfileBaseAac = 16908290;

        /**
         * IAMF profile using the
         * <a href="https://aomediacodec.github.io/iamf/#profiles-base-enhanced">base-enhanced profile</a>
         * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
         * in AAC.
         */
        public static final int IAMFProfileBaseEnhancedAac = 17039362;

        /**
         * IAMF profile using the
         * <a href="https://aomediacodec.github.io/iamf/#profiles-base-enhanced">base-enhanced profile</a>
         * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
         * in FLAC.
         */
        public static final int IAMFProfileBaseEnhancedFlac = 17039364;

        /**
         * IAMF profile using the
         * <a href="https://aomediacodec.github.io/iamf/#profiles-base-enhanced">base-enhanced profile</a>
         * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
         * in OPUS.
         */
        public static final int IAMFProfileBaseEnhancedOpus = 17039361;

        /**
         * IAMF profile using the
         * <a href="https://aomediacodec.github.io/iamf/#profiles-base-enhanced">base-enhanced profile</a>
         * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
         * in PCM.
         */
        public static final int IAMFProfileBaseEnhancedPcm = 17039368;

        /**
         * IAMF profile using the
         * <a href="https://aomediacodec.github.io/iamf/#profiles-base">base profile</a>
         * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
         * in FLAC.
         */
        public static final int IAMFProfileBaseFlac = 16908292;

        /**
         * IAMF profile using the
         * <a href="https://aomediacodec.github.io/iamf/#profiles-base">base profile</a>
         * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
         * in OPUS.
         */
        public static final int IAMFProfileBaseOpus = 16908289;

        /**
         * IAMF profile using the
         * <a href="https://aomediacodec.github.io/iamf/#profiles-base">base profile</a>
         * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
         * in PCM.
         */
        public static final int IAMFProfileBasePcm = 16908296;

        /**
         * IAMF profile using the
         * <a href="https://aomediacodec.github.io/iamf/#profiles-simple">simple profile</a>
         * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
         * in AAC.
         */
        public static final int IAMFProfileSimpleAac = 16842754;

        /**
         * IAMF profile using the
         * <a href="https://aomediacodec.github.io/iamf/#profiles-simple">simple profile</a>
         * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
         * in FLAC.
         */
        public static final int IAMFProfileSimpleFlac = 16842756;

        /**
         * IAMF profile using the
         * <a href="https://aomediacodec.github.io/iamf/#profiles-simple">simple profile</a>
         * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
         * in OPUS.
         */
        public static final int IAMFProfileSimpleOpus = 16842753;

        /**
         * IAMF profile using the
         * <a href="https://aomediacodec.github.io/iamf/#profiles-simple">simple profile</a>
         * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
         * in PCM.
         */
        public static final int IAMFProfileSimplePcm = 16842760;

        public static final int MPEG2LevelH14 = 2;

        public static final int MPEG2LevelHL = 3;

        public static final int MPEG2LevelHP = 4;

        public static final int MPEG2LevelLL = 0;

        public static final int MPEG2LevelML = 1;

        public static final int MPEG2Profile422 = 2;

        public static final int MPEG2ProfileHigh = 5;

        public static final int MPEG2ProfileMain = 1;

        public static final int MPEG2ProfileSNR = 3;

        public static final int MPEG2ProfileSimple = 0;

        public static final int MPEG2ProfileSpatial = 4;

        public static final int MPEG4Level0 = 1;

        public static final int MPEG4Level0b = 2;

        public static final int MPEG4Level1 = 4;

        public static final int MPEG4Level2 = 8;

        public static final int MPEG4Level3 = 16;

        public static final int MPEG4Level3b = 24;

        public static final int MPEG4Level4 = 32;

        public static final int MPEG4Level4a = 64;

        public static final int MPEG4Level5 = 128;

        public static final int MPEG4Level6 = 256;

        public static final int MPEG4ProfileAdvancedCoding = 4096;

        public static final int MPEG4ProfileAdvancedCore = 8192;

        public static final int MPEG4ProfileAdvancedRealTime = 1024;

        public static final int MPEG4ProfileAdvancedScalable = 16384;

        public static final int MPEG4ProfileAdvancedSimple = 32768;

        public static final int MPEG4ProfileBasicAnimated = 256;

        public static final int MPEG4ProfileCore = 4;

        public static final int MPEG4ProfileCoreScalable = 2048;

        public static final int MPEG4ProfileHybrid = 512;

        public static final int MPEG4ProfileMain = 8;

        public static final int MPEG4ProfileNbit = 16;

        public static final int MPEG4ProfileScalableTexture = 32;

        public static final int MPEG4ProfileSimple = 1;

        public static final int MPEG4ProfileSimpleFBA = 128;

        public static final int MPEG4ProfileSimpleFace = 64;

        public static final int MPEG4ProfileSimpleScalable = 2;

        public static final int VP8Level_Version0 = 1;

        public static final int VP8Level_Version1 = 2;

        public static final int VP8Level_Version2 = 4;

        public static final int VP8Level_Version3 = 8;

        public static final int VP8ProfileMain = 1;

        public static final int VP9Level1 = 1;

        public static final int VP9Level11 = 2;

        public static final int VP9Level2 = 4;

        public static final int VP9Level21 = 8;

        public static final int VP9Level3 = 16;

        public static final int VP9Level31 = 32;

        public static final int VP9Level4 = 64;

        public static final int VP9Level41 = 128;

        public static final int VP9Level5 = 256;

        public static final int VP9Level51 = 512;

        public static final int VP9Level52 = 1024;

        public static final int VP9Level6 = 2048;

        public static final int VP9Level61 = 4096;

        public static final int VP9Level62 = 8192;

        /**
         * VP9 Profile 0 4:2:0 8-bit
         */
        public static final int VP9Profile0 = 1;

        /**
         * VP9 Profile 1 4:2:2 8-bit
         */
        public static final int VP9Profile1 = 2;

        /**
         * VP9 Profile 2 4:2:0 10-bit
         */
        public static final int VP9Profile2 = 4;

        /**
         * VP9 Profile 2 4:2:0 10-bit HDR
         */
        public static final int VP9Profile2HDR = 4096;

        /**
         * VP9 Profile 2 4:2:0 10-bit HDR10Plus
         */
        public static final int VP9Profile2HDR10Plus = 16384;

        /**
         * VP9 Profile 3 4:2:2 10-bit
         */
        public static final int VP9Profile3 = 8;

        /**
         * VP9 Profile 3 4:2:2 10-bit HDR
         */
        public static final int VP9Profile3HDR = 8192;

        /**
         * VP9 Profile 3 4:2:2 10-bit HDR10Plus
         */
        public static final int VP9Profile3HDR10Plus = 32768;

        private CodecProfileLevel() {
        }
    }

    /**
     * A class that supports querying the encoding capabilities of a codec.
     */
    public static final class EncoderCapabilities {
        /**
         * Constant bitrate mode
         */
        public static final int BITRATE_MODE_CBR = 2;

        /**
         * Constant bitrate mode with frame drops
         */
        public static final int BITRATE_MODE_CBR_FD = 3;

        /**
         * Constant quality mode
         */
        public static final int BITRATE_MODE_CQ = 0;

        /**
         * Variable bitrate mode
         */
        public static final int BITRATE_MODE_VBR = 1;

        private EncoderCapabilities() {
        }
    }
}
