package android.media;

/**
 * MediaSync class can be used to synchronously play audio and video streams.
 * It can be used to play audio-only or video-only stream, too.
 *
 * <p>MediaSync is generally used like this:
 * <pre>
 * MediaSync sync = new MediaSync();
 * sync.setSurface(surface);
 * Surface inputSurface = sync.createInputSurface();
 * ...
 * // MediaCodec videoDecoder = ...;
 * videoDecoder.configure(format, inputSurface, ...);
 * ...
 * sync.setAudioTrack(audioTrack);
 * sync.setCallback(new MediaSync.Callback() {
 *     {@literal @Override}
 *     public void onAudioBufferConsumed(MediaSync sync, ByteBuffer audioBuffer, int bufferId) {
 *         ...
 *     }
 * }, null);
 * // This needs to be done since sync is paused on creation.
 * sync.setPlaybackParams(new PlaybackParams().setSpeed(1.f));
 *
 * for (;;) {
 *   ...
 *   // send video frames to surface for rendering, e.g., call
 *   // videoDecoder.releaseOutputBuffer(videoOutputBufferIx, videoPresentationTimeNs);
 *   // More details are available as below.
 *   ...
 *   sync.queueAudio(audioByteBuffer, bufferId, audioPresentationTimeUs); // non-blocking.
 *   // The audioByteBuffer and bufferId will be returned via callback.
 *   // More details are available as below.
 *   ...
 *     ...
 * }
 * sync.setPlaybackParams(new PlaybackParams().setSpeed(0.f));
 * sync.release();
 * sync = null;
 *
 * // The following code snippet illustrates how video/audio raw frames are created by
 * // MediaCodec's, how they are fed to MediaSync and how they are returned by MediaSync.
 * // This is the callback from MediaCodec.
 * onOutputBufferAvailable(MediaCodec codec, int bufferId, BufferInfo info) {
 *     // ...
 *     if (codec == videoDecoder) {
 *         // surface timestamp must contain media presentation time in nanoseconds.
 *         codec.releaseOutputBuffer(bufferId, 1000 * info.presentationTime);
 *     } else {
 *         ByteBuffer audioByteBuffer = codec.getOutputBuffer(bufferId);
 *         sync.queueAudio(audioByteBuffer, bufferId, info.presentationTime);
 *     }
 *     // ...
 * }
 *
 * // This is the callback from MediaSync.
 * onAudioBufferConsumed(MediaSync sync, ByteBuffer buffer, int bufferId) {
 *     // ...
 *     audioDecoder.releaseBuffer(bufferId, false);
 *     // ...
 * }
 *
 * </pre>
 *
 * The client needs to configure corresponding sink by setting the Surface and/or AudioTrack
 * based on the stream type it will play.
 * <p>
 * For video, the client needs to call {@link #createInputSurface} to obtain a surface on
 * which it will render video frames.
 * <p>
 * For audio, the client needs to set up audio track correctly, e.g., using {@link
 * AudioTrack#MODE_STREAM}. The audio buffers are sent to MediaSync directly via {@link
 * #queueAudio}, and are returned to the client via {@link Callback#onAudioBufferConsumed}
 * asynchronously. The client should not modify an audio buffer till it's returned.
 * <p>
 * The client can optionally pre-fill audio/video buffers by setting playback rate to 0.0,
 * and then feed audio/video buffers to corresponding components. This can reduce possible
 * initial underrun.
 * <p>
 */
public final class MediaSync {
    /**
     * Audio track failed.
     *  @see android.media.MediaSync.OnErrorListener
     */
    public static final int MEDIASYNC_ERROR_AUDIOTRACK_FAIL = 1;

    /**
     * The surface failed to handle video buffers.
     *  @see android.media.MediaSync.OnErrorListener
     */
    public static final int MEDIASYNC_ERROR_SURFACE_FAIL = 2;

    private MediaSync() {
    }
}
