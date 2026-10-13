package android.media.audiofx;

/**
 * DynamicsProcessing is an audio effect for equalizing and changing dynamic range properties of the
 * sound. It is composed of multiple stages including equalization, multi-band compression and
 * limiter.
 * <p>The number of bands and active stages is configurable, and most parameters can be controlled
 * in realtime, such as gains, attack/release times, thresholds, etc.
 * <p>The effect is instantiated and controlled by channels. Each channel has the same basic
 * architecture, but all of their parameters are independent from other channels.
 * <p>The basic channel configuration is:
 * <pre>
 *
 *    Channel 0          Channel 1       ....       Channel N-1
 *      Input              Input                       Input
 *        |                  |                           |
 *   +----v----+        +----v----+                 +----v----+
 *   |inputGain|        |inputGain|                 |inputGain|
 *   +---------+        +---------+                 +---------+
 *        |                  |                           |
 *  +-----v-----+      +-----v-----+               +-----v-----+
 *  |   PreEQ   |      |   PreEQ   |               |   PreEQ   |
 *  +-----------+      +-----------+               +-----------+
 *        |                  |                           |
 *  +-----v-----+      +-----v-----+               +-----v-----+
 *  |    MBC    |      |    MBC    |               |    MBC    |
 *  +-----------+      +-----------+               +-----------+
 *        |                  |                           |
 *  +-----v-----+      +-----v-----+               +-----v-----+
 *  |  PostEQ   |      |  PostEQ   |               |  PostEQ   |
 *  +-----------+      +-----------+               +-----------+
 *        |                  |                           |
 *  +-----v-----+      +-----v-----+               +-----v-----+
 *  |  Limiter  |      |  Limiter  |               |  Limiter  |
 *  +-----------+      +-----------+               +-----------+
 *        |                  |                           |
 *     Output             Output                      Output
 * </pre>
 *
 * <p>Where the stages are:
 * inputGain: input gain factor in decibels (dB). 0 dB means no change in level.
 * PreEQ:  Multi-band Equalizer.
 * MBC:    Multi-band Compressor
 * PostEQ: Multi-band Equalizer
 * Limiter: Single band compressor/limiter.
 *
 * <p>An application creates a DynamicsProcessing object to instantiate and control this audio
 * effect in the audio framework. A DynamicsProcessor.Config and DynamicsProcessor.Config.Builder
 * are available to help configure the multiple stages and each band parameters if desired.
 * <p>See each stage documentation for further details.
 * <p>If no Config is specified during creation, a default configuration is chosen.
 * <p>To attach the DynamicsProcessing to a particular AudioTrack or MediaPlayer,
 * specify the audio session ID of this AudioTrack or MediaPlayer when constructing the effect
 * (see {@link AudioTrack#getAudioSessionId()} and {@link MediaPlayer#getAudioSessionId()}).
 *
 * <p>To attach the DynamicsProcessing to a particular AudioTrack or MediaPlayer, specify the audio
 * session ID of this AudioTrack or MediaPlayer when constructing the DynamicsProcessing.
 * <p>See {@link android.media.MediaPlayer#getAudioSessionId()} for details on audio sessions.
 * <p>See {@link android.media.audiofx.AudioEffect} class for more details on controlling audio
 * effects.
 */
public final class DynamicsProcessing {
    /**
     * Index of variant that favors frequency resolution. Frequency domain based implementation.
     */
    public static final int VARIANT_FAVOR_FREQUENCY_RESOLUTION = 0;

    /**
     * Index of variant that favors time resolution resolution. Time domain based implementation.
     */
    public static final int VARIANT_FAVOR_TIME_RESOLUTION = 1;

    private DynamicsProcessing() {
    }
}
