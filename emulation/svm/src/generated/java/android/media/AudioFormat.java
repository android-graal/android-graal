package android.media;

import android.os.Parcelable;

/**
 * The {@link AudioFormat} class is used to access a number of audio format and
 * channel configuration constants. They are for instance used
 * in {@link AudioTrack} and {@link AudioRecord}, as valid values in individual parameters of
 * constructors like {@link AudioTrack#AudioTrack(int, int, int, int, int, int)}, where the fourth
 * parameter is one of the <code>AudioFormat.ENCODING_*</code> constants.
 * The <code>AudioFormat</code> constants are also used in {@link MediaFormat} to specify
 * audio related values commonly used in media, such as for {@link MediaFormat#KEY_CHANNEL_MASK}.
 * <p>The {@link AudioFormat.Builder} class can be used to create instances of
 * the <code>AudioFormat</code> format class.
 * Refer to
 * {@link AudioFormat.Builder} for documentation on the mechanics of the configuration and building
 * of such instances. Here we describe the main concepts that the <code>AudioFormat</code> class
 * allow you to convey in each instance, they are:
 * <ol>
 * <li><a href="#sampleRate">sample rate</a>
 * <li><a href="#encoding">encoding</a>
 * <li><a href="#channelMask">channel masks</a>
 * </ol>
 * <p>Closely associated with the <code>AudioFormat</code> is the notion of an
 * <a href="#audioFrame">audio frame</a>, which is used throughout the documentation
 * to represent the minimum size complete unit of audio data.
 *
 * <h4 id="sampleRate">Sample rate</h4>
 * <p>Expressed in Hz, the sample rate in an <code>AudioFormat</code> instance expresses the number
 * of audio samples for each channel per second in the content you are playing or recording. It is
 * not the sample rate
 * at which content is rendered or produced. For instance a sound at a media sample rate of 8000Hz
 * can be played on a device operating at a sample rate of 48000Hz; the sample rate conversion is
 * automatically handled by the platform, it will not play at 6x speed.
 *
 * <p>As of API {@link android.os.Build.VERSION_CODES#M},
 * sample rates up to 192kHz are supported
 * for <code>AudioRecord</code> and <code>AudioTrack</code>, with sample rate conversion
 * performed as needed.
 * To improve efficiency and avoid lossy conversions, it is recommended to match the sample rate
 * for <code>AudioRecord</code> and <code>AudioTrack</code> to the endpoint device
 * sample rate, and limit the sample rate to no more than 48kHz unless there are special
 * device capabilities that warrant a higher rate.
 *
 * <h4 id="encoding">Encoding</h4>
 * <p>Audio encoding is used to describe the bit representation of audio data, which can be
 * either linear PCM or compressed audio, such as AC3 or DTS.
 * <p>For linear PCM, the audio encoding describes the sample size, 8 bits, 16 bits, or 32 bits,
 * and the sample representation, integer or float.
 * <ul>
 * <li> {@link #ENCODING_PCM_8BIT}: The audio sample is a 8 bit unsigned integer in the
 * range [0, 255], with a 128 offset for zero. This is typically stored as a Java byte in a
 * byte array or ByteBuffer. Since the Java byte is <em>signed</em>,
 * be careful with math operations and conversions as the most significant bit is inverted.
 * </li>
 * <li> {@link #ENCODING_PCM_16BIT}: The audio sample is a 16 bit signed integer
 * typically stored as a Java short in a short array, but when the short
 * is stored in a ByteBuffer, it is native endian (as compared to the default Java big endian).
 * The short has full range from [-32768, 32767],
 * and is sometimes interpreted as fixed point Q.15 data.
 * </li>
 * <li> {@link #ENCODING_PCM_FLOAT}: Introduced in
 * API {@link android.os.Build.VERSION_CODES#LOLLIPOP}, this encoding specifies that
 * the audio sample is a 32 bit IEEE single precision float. The sample can be
 * manipulated as a Java float in a float array, though within a ByteBuffer
 * it is stored in native endian byte order.
 * The nominal range of <code>ENCODING_PCM_FLOAT</code> audio data is [-1.0, 1.0].
 * It is implementation dependent whether the positive maximum of 1.0 is included
 * in the interval. Values outside of the nominal range are clamped before
 * sending to the endpoint device. Beware that
 * the handling of NaN is undefined; subnormals may be treated as zero; and
 * infinities are generally clamped just like other values for <code>AudioTrack</code>
 * &ndash; try to avoid infinities because they can easily generate a NaN.
 * <br>
 * To achieve higher audio bit depth than a signed 16 bit integer short,
 * it is recommended to use <code>ENCODING_PCM_FLOAT</code> for audio capture, processing,
 * and playback.
 * Floats are efficiently manipulated by modern CPUs,
 * have greater precision than 24 bit signed integers,
 * and have greater dynamic range than 32 bit signed integers.
 * <code>AudioRecord</code> as of API {@link android.os.Build.VERSION_CODES#M} and
 * <code>AudioTrack</code> as of API {@link android.os.Build.VERSION_CODES#LOLLIPOP}
 * support <code>ENCODING_PCM_FLOAT</code>.
 * </li>
 * <li> {@link #ENCODING_PCM_24BIT_PACKED}: Introduced in
 * API {@link android.os.Build.VERSION_CODES#S},
 * this encoding specifies the audio sample is an
 * extended precision 24 bit signed integer
 * stored as a 3 Java bytes in a {@code ByteBuffer} or byte array in native endian
 * (see {@link java.nio.ByteOrder#nativeOrder()}).
 * Each sample has full range from [-8388608, 8388607],
 * and can be interpreted as fixed point Q.23 data.
 * </li>
 * <li> {@link #ENCODING_PCM_32BIT}: Introduced in
 * API {@link android.os.Build.VERSION_CODES#S},
 * this encoding specifies the audio sample is an
 * extended precision 32 bit signed integer
 * stored as a 4 Java bytes in a {@code ByteBuffer} or byte array in native endian
 * (see {@link java.nio.ByteOrder#nativeOrder()}).
 * Each sample has full range from [-2147483648, 2147483647],
 * and can be interpreted as fixed point Q.31 data.
 * </li>
 * </ul>
 * <p>For compressed audio, the encoding specifies the method of compression,
 * for example {@link #ENCODING_AC3} and {@link #ENCODING_DTS}. The compressed
 * audio data is typically stored as bytes in
 * a byte array or ByteBuffer. When a compressed audio encoding is specified
 * for an <code>AudioTrack</code>, it creates a direct (non-mixed) track
 * for output to an endpoint (such as HDMI) capable of decoding the compressed audio.
 * For (most) other endpoints, which are not capable of decoding such compressed audio,
 * you will need to decode the data first, typically by creating a {@link MediaCodec}.
 * Alternatively, one may use {@link MediaPlayer} for playback of compressed
 * audio files or streams.
 * <p>When compressed audio is sent out through a direct <code>AudioTrack</code>,
 * it need not be written in exact multiples of the audio access unit;
 * this differs from <code>MediaCodec</code> input buffers.
 *
 * <h4 id="channelMask">Channel mask</h4>
 * <p>Channel masks are used in <code>AudioTrack</code> and <code>AudioRecord</code> to describe
 * the samples and their arrangement in the audio frame. They are also used in the endpoint (e.g.
 * a USB audio interface, a DAC connected to headphones) to specify allowable configurations of a
 * particular device.
 * <br>As of API {@link android.os.Build.VERSION_CODES#M}, there are two types of channel masks:
 * channel position masks and channel index masks.
 *
 * <h5 id="channelPositionMask">Channel position masks</h5>
 * Channel position masks are the original Android channel masks, and are used since API
 * {@link android.os.Build.VERSION_CODES#BASE}.
 * For input and output, they imply a positional nature - the location of a speaker or a microphone
 * for recording or playback.
 * <br>For a channel position mask, each allowed channel position corresponds to a bit in the
 * channel mask. If that channel position is present in the audio frame, that bit is set,
 * otherwise it is zero. The order of the bits (from lsb to msb) corresponds to the order of that
 * position's sample in the audio frame.
 * <br>The canonical channel position masks by channel count are as follows:
 * <br><table>
 * <tr><td>channel count</td><td>channel position mask</td></tr>
 * <tr><td>1</td><td>{@link #CHANNEL_OUT_MONO}</td></tr>
 * <tr><td>2</td><td>{@link #CHANNEL_OUT_STEREO}</td></tr>
 * <tr><td>3</td><td>{@link #CHANNEL_OUT_STEREO} | {@link #CHANNEL_OUT_FRONT_CENTER}</td></tr>
 * <tr><td>4</td><td>{@link #CHANNEL_OUT_QUAD}</td></tr>
 * <tr><td>5</td><td>{@link #CHANNEL_OUT_QUAD} | {@link #CHANNEL_OUT_FRONT_CENTER}</td></tr>
 * <tr><td>6</td><td>{@link #CHANNEL_OUT_5POINT1}</td></tr>
 * <tr><td>7</td><td>{@link #CHANNEL_OUT_5POINT1} | {@link #CHANNEL_OUT_BACK_CENTER}</td></tr>
 * <tr><td>8</td><td>{@link #CHANNEL_OUT_7POINT1_SURROUND}</td></tr>
 * </table>
 * <br>These masks are an ORed composite of individual channel masks. For example
 * {@link #CHANNEL_OUT_STEREO} is composed of {@link #CHANNEL_OUT_FRONT_LEFT} and
 * {@link #CHANNEL_OUT_FRONT_RIGHT}.
 * <p>
 * The following diagram represents the layout of the output channels, as seen from above
 * the listener (in the center at the "lis" position, facing the front-center channel).
 * <pre>
 *       TFL ----- TFC ----- TFR     T is Top
 *       |  \       |       /  |
 *       |   FL --- FC --- FR  |     F is Front
 *       |   |\     |     /|   |
 *       |   | BFL-BFC-BFR |   |     BF is Bottom Front
 *       |   |             |   |
 *       |   FWL   lis   FWR   |     W is Wide
 *       |   |             |   |
 *      TSL  SL    TC     SR  TSR    S is Side
 *       |   |             |   |
 *       |   BL --- BC -- BR   |     B is Back
 *       |  /               \  |
 *       TBL ----- TBC ----- TBR     C is Center, L/R is Left/Right
 * </pre>
 * All "T" (top) channels are above the listener, all "BF" (bottom-front) channels are below the
 * listener, all others are in the listener's horizontal plane. When used in conjunction, LFE1 and
 * LFE2 are below the listener, when used alone, LFE plane is undefined.
 * See the channel definitions for the abbreviations
 *
 * <h5 id="channelIndexMask">Channel index masks</h5>
 * Channel index masks are introduced in API {@link android.os.Build.VERSION_CODES#M}. They allow
 * the selection of a particular channel from the source or sink endpoint by number, i.e. the first
 * channel, the second channel, and so forth. This avoids problems with artificially assigning
 * positions to channels of an endpoint, or figuring what the i<sup>th</sup> position bit is within
 * an endpoint's channel position mask etc.
 * <br>Here's an example where channel index masks address this confusion: dealing with a 4 channel
 * USB device. Using a position mask, and based on the channel count, this would be a
 * {@link #CHANNEL_OUT_QUAD} device, but really one is only interested in channel 0
 * through channel 3. The USB device would then have the following individual bit channel masks:
 * {@link #CHANNEL_OUT_FRONT_LEFT},
 * {@link #CHANNEL_OUT_FRONT_RIGHT}, {@link #CHANNEL_OUT_BACK_LEFT}
 * and {@link #CHANNEL_OUT_BACK_RIGHT}. But which is channel 0 and which is
 * channel 3?
 * <br>For a channel index mask, each channel number is represented as a bit in the mask, from the
 * lsb (channel 0) upwards to the msb, numerically this bit value is
 * <code>1 << channelNumber</code>.
 * A set bit indicates that channel is present in the audio frame, otherwise it is cleared.
 * The order of the bits also correspond to that channel number's sample order in the audio frame.
 * <br>For the previous 4 channel USB device example, the device would have a channel index mask
 * <code>0xF</code>. Suppose we wanted to select only the first and the third channels; this would
 * correspond to a channel index mask <code>0x5</code> (the first and third bits set). If an
 * <code>AudioTrack</code> uses this channel index mask, the audio frame would consist of two
 * samples, the first sample of each frame routed to channel 0, and the second sample of each frame
 * routed to channel 2.
 * The canonical channel index masks by channel count are given by the formula
 * <code>(1 << channelCount) - 1</code>.
 *
 * <h5>Use cases</h5>
 * <ul>
 * <li><i>Channel position mask for an endpoint:</i> <code>CHANNEL_OUT_FRONT_LEFT</code>,
 *  <code>CHANNEL_OUT_FRONT_CENTER</code>, etc. for HDMI home theater purposes.
 * <li><i>Channel position mask for an audio stream:</i> Creating an <code>AudioTrack</code>
 *  to output movie content, where 5.1 multichannel output is to be written.
 * <li><i>Channel index mask for an endpoint:</i> USB devices for which input and output do not
 *  correspond to left or right speaker or microphone.
 * <li><i>Channel index mask for an audio stream:</i> An <code>AudioRecord</code> may only want the
 *  third and fourth audio channels of the endpoint (i.e. the second channel pair), and not care the
 *  about position it corresponds to, in which case the channel index mask is <code>0xC</code>.
 *  Multichannel <code>AudioRecord</code> sessions should use channel index masks.
 * </ul>
 * <h4 id="audioFrame">Audio Frame</h4>
 * <p>For linear PCM, an audio frame consists of a set of samples captured at the same time,
 * whose count and
 * channel association are given by the <a href="#channelMask">channel mask</a>,
 * and whose sample contents are specified by the <a href="#encoding">encoding</a>.
 * For example, a stereo 16 bit PCM frame consists of
 * two 16 bit linear PCM samples, with a frame size of 4 bytes.
 * For compressed audio, an audio frame may alternately
 * refer to an access unit of compressed data bytes that is logically grouped together for
 * decoding and bitstream access (e.g. {@link MediaCodec}),
 * or a single byte of compressed data (e.g. {@link AudioTrack#getBufferSizeInFrames()
 * AudioTrack.getBufferSizeInFrames()}),
 * or the linear PCM frame result from decoding the compressed data
 * (e.g.{@link AudioTrack#getPlaybackHeadPosition()
 * AudioTrack.getPlaybackHeadPosition()}),
 * depending on the context where audio frame is used.
 * For the purposes of {@link AudioFormat#getFrameSizeInBytes()}, a compressed data format
 * returns a frame size of 1 byte.
 */
public final class AudioFormat implements Parcelable {
    /**
     * @deprecated Use {@link #CHANNEL_OUT_DEFAULT} or {@link #CHANNEL_IN_DEFAULT} instead.
     */
    @Deprecated
    public static final int CHANNEL_CONFIGURATION_DEFAULT = 1;

    /**
     * @deprecated Use {@link #CHANNEL_INVALID} instead.
     */
    @Deprecated
    public static final int CHANNEL_CONFIGURATION_INVALID = 0;

    /**
     * @deprecated Use {@link #CHANNEL_OUT_MONO} or {@link #CHANNEL_IN_MONO} instead.
     */
    @Deprecated
    public static final int CHANNEL_CONFIGURATION_MONO = 2;

    /**
     * @deprecated Use {@link #CHANNEL_OUT_STEREO} or {@link #CHANNEL_IN_STEREO} instead.
     */
    @Deprecated
    public static final int CHANNEL_CONFIGURATION_STEREO = 3;

    /**
     * Invalid audio channel mask
     */
    public static final int CHANNEL_INVALID = 0;

    public static final int CHANNEL_IN_BACK = 32;

    public static final int CHANNEL_IN_BACK_PROCESSED = 512;

    public static final int CHANNEL_IN_DEFAULT = 1;

    public static final int CHANNEL_IN_FRONT = 16;

    public static final int CHANNEL_IN_FRONT_PROCESSED = 256;

    public static final int CHANNEL_IN_LEFT = 4;

    public static final int CHANNEL_IN_LEFT_PROCESSED = 64;

    public static final int CHANNEL_IN_MONO = 16;

    public static final int CHANNEL_IN_PRESSURE = 1024;

    public static final int CHANNEL_IN_RIGHT = 8;

    public static final int CHANNEL_IN_RIGHT_PROCESSED = 128;

    public static final int CHANNEL_IN_STEREO = 12;

    public static final int CHANNEL_IN_VOICE_DNLINK = 32768;

    public static final int CHANNEL_IN_VOICE_UPLINK = 16384;

    public static final int CHANNEL_IN_X_AXIS = 2048;

    public static final int CHANNEL_IN_Y_AXIS = 4096;

    public static final int CHANNEL_IN_Z_AXIS = 8192;

    /**
     * Output channel mask for 5.1
     */
    public static final int CHANNEL_OUT_5POINT1 = 252;

    /**
     * Output channel mask for 5.1.2
     *   Same as 5.1 with the addition of left and right top channels
     */
    public static final int CHANNEL_OUT_5POINT1POINT2 = 3145980;

    /**
     * Output channel mask for 5.1.4
     *  Same as 5.1 with the addition of four top channels
     */
    public static final int CHANNEL_OUT_5POINT1POINT4 = 737532;

    /**
     * Output channel mask for 6.1
     *   Same as 5.1 with the addition of the back center channel
     */
    public static final int CHANNEL_OUT_6POINT1 = 1276;

    /**
     * @deprecated Not the typical 7.1 surround configuration. Use {@link #CHANNEL_OUT_7POINT1_SURROUND} instead.
     */
    @Deprecated
    public static final int CHANNEL_OUT_7POINT1 = 1020;

    /**
     * Output channel mask for 7.1.2
     *  Same as 7.1 with the addition of left and right top channels
     */
    public static final int CHANNEL_OUT_7POINT1POINT2 = 3152124;

    /**
     * Output channel mask for 7.1.4
     *   Same as 7.1 with the addition of four top channels
     */
    public static final int CHANNEL_OUT_7POINT1POINT4 = 743676;

    /**
     * Output channel mask for 7.1
     */
    public static final int CHANNEL_OUT_7POINT1_SURROUND = 6396;

    /**
     * Output channel mask for 9.1.4
     *  Same as 7.1.4 with the addition of left and right front wide channels
     */
    public static final int CHANNEL_OUT_9POINT1POINT4 = 202070268;

    /**
     * Output channel mask for 9.1.6
     *  Same as 9.1.4 with the addition of left and right top side channels
     */
    public static final int CHANNEL_OUT_9POINT1POINT6 = 205215996;

    /**
     * Back center output channel (see BC in channel diagram)
     */
    public static final int CHANNEL_OUT_BACK_CENTER = 1024;

    /**
     * Back left output channel (see BL in channel diagram)
     */
    public static final int CHANNEL_OUT_BACK_LEFT = 64;

    /**
     * Back right output channel (see BR in channel diagram)
     */
    public static final int CHANNEL_OUT_BACK_RIGHT = 128;

    /**
     * Bottom front center output channel (see BFC in channel diagram below FC)
     */
    public static final int CHANNEL_OUT_BOTTOM_FRONT_CENTER = 8388608;

    /**
     * Bottom front left output channel (see BFL in channel diagram below FL)
     */
    public static final int CHANNEL_OUT_BOTTOM_FRONT_LEFT = 4194304;

    /**
     * Bottom front right output channel (see BFR in channel diagram below FR)
     */
    public static final int CHANNEL_OUT_BOTTOM_FRONT_RIGHT = 16777216;

    /**
     * Default audio channel mask
     */
    public static final int CHANNEL_OUT_DEFAULT = 1;

    /**
     * Front center output channel (see FC in channel diagram)
     */
    public static final int CHANNEL_OUT_FRONT_CENTER = 16;

    /**
     * Front left output channel (see FL in channel diagram)
     */
    public static final int CHANNEL_OUT_FRONT_LEFT = 4;

    public static final int CHANNEL_OUT_FRONT_LEFT_OF_CENTER = 256;

    /**
     * Front right output channel (see FR in channel diagram)
     */
    public static final int CHANNEL_OUT_FRONT_RIGHT = 8;

    public static final int CHANNEL_OUT_FRONT_RIGHT_OF_CENTER = 512;

    /**
     * Front wide left output channel (see FWL in channel diagram)
     */
    public static final int CHANNEL_OUT_FRONT_WIDE_LEFT = 67108864;

    /**
     * Front wide right output channel (see FWR in channel diagram)
     */
    public static final int CHANNEL_OUT_FRONT_WIDE_RIGHT = 134217728;

    /**
     * LFE "low frequency effect" channel
     *  When used in conjunction with {@link #CHANNEL_OUT_LOW_FREQUENCY_2}, it is intended
     *  to contain the left low-frequency effect signal, also referred to as "LFE1"
     *  in ITU-R BS.2159-8
     */
    public static final int CHANNEL_OUT_LOW_FREQUENCY = 32;

    /**
     * The second LFE channel
     *  When used in conjunction with {@link #CHANNEL_OUT_LOW_FREQUENCY}, it is intended
     *  to contain the right low-frequency effect signal, also referred to as "LFE2"
     *  in ITU-R BS.2159-8
     */
    public static final int CHANNEL_OUT_LOW_FREQUENCY_2 = 33554432;

    public static final int CHANNEL_OUT_MONO = 4;

    public static final int CHANNEL_OUT_QUAD = 204;

    /**
     * Side left output channel (see SL in channel diagram)
     */
    public static final int CHANNEL_OUT_SIDE_LEFT = 2048;

    /**
     * Side right output channel (see SR in channel diagram)
     */
    public static final int CHANNEL_OUT_SIDE_RIGHT = 4096;

    public static final int CHANNEL_OUT_STEREO = 12;

    public static final int CHANNEL_OUT_SURROUND = 1052;

    /**
     * Top back center output channel (see TBC in channel diagram above BC)
     */
    public static final int CHANNEL_OUT_TOP_BACK_CENTER = 262144;

    /**
     * Top back left output channel (see TBL in channel diagram above BL)
     */
    public static final int CHANNEL_OUT_TOP_BACK_LEFT = 131072;

    /**
     * Top back right output channel (see TBR in channel diagram above BR)
     */
    public static final int CHANNEL_OUT_TOP_BACK_RIGHT = 524288;

    /**
     * Top center (above listener) output channel (see TC in channel diagram)
     */
    public static final int CHANNEL_OUT_TOP_CENTER = 8192;

    /**
     * Top front center output channel (see TFC in channel diagram above FC)
     */
    public static final int CHANNEL_OUT_TOP_FRONT_CENTER = 32768;

    /**
     * Top front left output channel (see TFL in channel diagram above FL)
     */
    public static final int CHANNEL_OUT_TOP_FRONT_LEFT = 16384;

    /**
     * Top front right output channel (see TFR in channel diagram above FR)
     */
    public static final int CHANNEL_OUT_TOP_FRONT_RIGHT = 65536;

    /**
     * Top side left output channel (see TSL in channel diagram above SL)
     */
    public static final int CHANNEL_OUT_TOP_SIDE_LEFT = 1048576;

    /**
     * Top side right output channel (see TSR in channel diagram above SR)
     */
    public static final int CHANNEL_OUT_TOP_SIDE_RIGHT = 2097152;

    /**
     * Audio data format: AAC ELD compressed
     */
    public static final int ENCODING_AAC_ELD = 15;

    /**
     * Audio data format: AAC HE V1 compressed
     */
    public static final int ENCODING_AAC_HE_V1 = 11;

    /**
     * Audio data format: AAC HE V2 compressed
     */
    public static final int ENCODING_AAC_HE_V2 = 12;

    /**
     * Audio data format: AAC LC compressed
     */
    public static final int ENCODING_AAC_LC = 10;

    /**
     * Audio data format: AAC xHE compressed
     */
    public static final int ENCODING_AAC_XHE = 16;

    /**
     * Audio data format: AC-3 compressed, also known as Dolby Digital
     */
    public static final int ENCODING_AC3 = 5;

    /**
     * Audio data format: AC-4 (levels 0-3) sync frame transport format
     */
    public static final int ENCODING_AC4 = 17;

    /**
     * Default audio data format
     */
    public static final int ENCODING_DEFAULT = 1;

    /**
     * Audio data format: Dolby MAT (Metadata-enhanced Audio Transmission)
     *  Dolby MAT bitstreams are used to transmit Dolby TrueHD, channel-based PCM, or PCM with
     *  metadata (object audio) over HDMI (e.g. Dolby Atmos content).
     */
    public static final int ENCODING_DOLBY_MAT = 19;

    /**
     * Audio data format: DOLBY TRUEHD compressed
     */
    public static final int ENCODING_DOLBY_TRUEHD = 14;

    /**
     * Audio data format: DRA compressed
     */
    public static final int ENCODING_DRA = 28;

    /**
     * Audio data format: Direct Stream Digital
     */
    public static final int ENCODING_DSD = 31;

    /**
     * Audio data format: DTS compressed
     */
    public static final int ENCODING_DTS = 7;

    /**
     * Audio data format: DTS HD compressed
     */
    public static final int ENCODING_DTS_HD = 8;

    /**
     * Audio data format: DTS HD Master Audio compressed
     *  DTS HD Master Audio stream is variable bit rate and contains lossless audio.
     *  Use {@link #ENCODING_DTS_HD_MA} for lossless audio content (DTS-HD MA Lossless)
     *  and use {@link #ENCODING_DTS_HD} for other DTS bitstreams with extension substream
     *  (DTS 8Ch Discrete, DTS Hi Res, DTS Express).
     */
    public static final int ENCODING_DTS_HD_MA = 29;

    /**
     * Audio data format: DTS UHD Profile-1 compressed (aka DTS:X Profile 1)
     *  Has the same meaning and value as ENCODING_DTS_UHD_P1.
     *  @deprecated Use {@link #ENCODING_DTS_UHD_P1} instead.
     */
    @Deprecated
    public static final int ENCODING_DTS_UHD = 27;

    /**
     * Audio data format: DTS UHD Profile-1 compressed (aka DTS:X Profile 1)
     *  Has the same meaning and value as the deprecated {@link #ENCODING_DTS_UHD}.
     */
    public static final int ENCODING_DTS_UHD_P1 = 27;

    /**
     * Audio data format: DTS UHD Profile-2 compressed
     *  DTS-UHD Profile-2 supports delivery of Channel-Based Audio, Object-Based Audio
     *  and High Order Ambisonic presentations up to the fourth order.
     *  Use {@link #ENCODING_DTS_UHD_P1} to transmit DTS UHD Profile 1 (aka DTS:X Profile 1)
     *  bitstream.
     *  Use {@link #ENCODING_DTS_UHD_P2} to transmit DTS UHD Profile 2 (aka DTS:X Profile 2)
     *  bitstream.
     */
    public static final int ENCODING_DTS_UHD_P2 = 30;

    /**
     * Audio data format: E-AC-3 compressed, also known as Dolby Digital Plus or DD+
     */
    public static final int ENCODING_E_AC3 = 6;

    /**
     * Audio data format: E-AC-3-JOC compressed
     *  E-AC-3-JOC streams can be decoded by downstream devices supporting {@link #ENCODING_E_AC3}.
     *  Use {@link #ENCODING_E_AC3} as the AudioTrack encoding when the downstream device
     *  supports {@link #ENCODING_E_AC3} but not {@link #ENCODING_E_AC3_JOC}.
     */
    public static final int ENCODING_E_AC3_JOC = 18;

    /**
     * Audio data format: IAMF using the
     * <a href="https://aomediacodec.github.io/iamf/#profiles-base-enhanced">base-enhanced profile</a>
     * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
     * in AAC.
     */
    public static final int ENCODING_IAMF_BASE_ENHANCED_PROFILE_AAC = 42;

    /**
     * Audio data format: IAMF using the
     * <a href="https://aomediacodec.github.io/iamf/#profiles-base-enhanced">base-enhanced profile</a>
     * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
     * in FLAC.
     */
    public static final int ENCODING_IAMF_BASE_ENHANCED_PROFILE_FLAC = 43;

    /**
     * Audio data format: IAMF using the
     * <a href="https://aomediacodec.github.io/iamf/#profiles-base-enhanced">base-enhanced profile</a>
     * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
     * in OPUS.
     */
    public static final int ENCODING_IAMF_BASE_ENHANCED_PROFILE_OPUS = 41;

    /**
     * Audio data format: IAMF using the
     * <a href="https://aomediacodec.github.io/iamf/#profiles-base-enhanced">base-enhanced profile</a>
     * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
     * in PCM.
     */
    public static final int ENCODING_IAMF_BASE_ENHANCED_PROFILE_PCM = 44;

    /**
     * Audio data format: IAMF using the
     * <a href="https://aomediacodec.github.io/iamf/#profiles-base">base profile</a>
     * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
     * in AAC.
     */
    public static final int ENCODING_IAMF_BASE_PROFILE_AAC = 38;

    /**
     * Audio data format: IAMF using the
     * <a href="https://aomediacodec.github.io/iamf/#profiles-base">base profile</a>
     * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
     * in FLAC.
     */
    public static final int ENCODING_IAMF_BASE_PROFILE_FLAC = 39;

    /**
     * Audio data format: IAMF using the
     * <a href="https://aomediacodec.github.io/iamf/#profiles-base">base profile</a>
     * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
     * in OPUS.
     */
    public static final int ENCODING_IAMF_BASE_PROFILE_OPUS = 37;

    /**
     * Audio data format: IAMF using the
     * <a href="https://aomediacodec.github.io/iamf/#profiles-base">base profile</a>
     * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
     * in PCM.
     */
    public static final int ENCODING_IAMF_BASE_PROFILE_PCM = 40;

    /**
     * Audio data format: IAMF using the
     * <a href="https://aomediacodec.github.io/iamf/#profiles-simple">simple profile</a>
     * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
     * in AAC.
     */
    public static final int ENCODING_IAMF_SIMPLE_PROFILE_AAC = 34;

    /**
     * Audio data format: IAMF using the
     * <a href="https://aomediacodec.github.io/iamf/#profiles-simple">simple profile</a>
     * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
     * in FLAC.
     */
    public static final int ENCODING_IAMF_SIMPLE_PROFILE_FLAC = 35;

    /**
     * Audio data format: IAMF using the
     * <a href="https://aomediacodec.github.io/iamf/#profiles-simple">simple profile</a>
     * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
     * in OPUS.
     */
    public static final int ENCODING_IAMF_SIMPLE_PROFILE_OPUS = 33;

    /**
     * Audio data format: IAMF using the
     * <a href="https://aomediacodec.github.io/iamf/#profiles-simple">simple profile</a>
     * with audio streams <a href="https://aomediacodec.github.io/iamf/#codec_id">encoded</a>
     * in PCM.
     */
    public static final int ENCODING_IAMF_SIMPLE_PROFILE_PCM = 36;

    /**
     * Audio data format: compressed audio wrapped in PCM for HDMI
     *  or S/PDIF passthrough.
     *  For devices whose SDK version is less than {@link android.os.Build.VERSION_CODES#S}, the
     *  channel mask of IEC61937 track must be {@link #CHANNEL_OUT_STEREO}.
     *  Data should be written to the stream in a short[] array.
     *  If the data is written in a byte[] array then there may be endian problems
     *  on some platforms when converting to short internally.
     */
    public static final int ENCODING_IEC61937 = 13;

    /**
     * Invalid audio data format
     */
    public static final int ENCODING_INVALID = 0;

    /**
     * Audio data format: MP3 compressed
     */
    public static final int ENCODING_MP3 = 9;

    /**
     * Audio data format: MPEG-H baseline profile, level 3
     */
    public static final int ENCODING_MPEGH_BL_L3 = 23;

    /**
     * Audio data format: MPEG-H baseline profile, level 4
     */
    public static final int ENCODING_MPEGH_BL_L4 = 24;

    /**
     * Audio data format: MPEG-H low complexity profile, level 3
     */
    public static final int ENCODING_MPEGH_LC_L3 = 25;

    /**
     * Audio data format: MPEG-H low complexity profile, level 4
     */
    public static final int ENCODING_MPEGH_LC_L4 = 26;

    /**
     * Audio data format: OPUS compressed.
     */
    public static final int ENCODING_OPUS = 20;

    /**
     * Audio data format: PCM 16 bit per sample. Guaranteed to be supported by devices.
     */
    public static final int ENCODING_PCM_16BIT = 2;

    /**
     * Audio data format: PCM 24 bit per sample packed as 3 bytes.
     *
     *  The bytes are in little-endian order, so the least significant byte
     *  comes first in the byte array.
     *
     *  Not guaranteed to be supported by devices, may be emulated if not supported.
     */
    public static final int ENCODING_PCM_24BIT_PACKED = 21;

    /**
     * Audio data format: PCM 32 bit per sample.
     *  Not guaranteed to be supported by devices, may be emulated if not supported.
     */
    public static final int ENCODING_PCM_32BIT = 22;

    /**
     * Audio data format: PCM 8 bit per sample. Not guaranteed to be supported by devices.
     */
    public static final int ENCODING_PCM_8BIT = 3;

    /**
     * Audio data format: single-precision floating-point per sample
     */
    public static final int ENCODING_PCM_FLOAT = 4;

    /**
     * Sample rate will be a route-dependent value.
     *  For AudioTrack, it is usually the sink sample rate,
     *  and for AudioRecord it is usually the source sample rate.
     */
    public static final int SAMPLE_RATE_UNSPECIFIED = 0;

    private AudioFormat() {
    }
}
