package android.hardware.camera2.params;

import android.os.Parcelable;

/**
 *  A class for describing camera output, which contains a {@link Surface} and its specific
 *  configuration for creating capture session.
 *
 *  <p>There are several ways to instantiate, modify and use OutputConfigurations. The most common
 *  and recommended usage patterns are summarized in the following list:</p>
 * <ul>
 *  <li>Passing a {@link Surface} to the constructor and using the OutputConfiguration instance as
 *  argument to {@link CameraDevice#createCaptureSessionByOutputConfigurations}. This is the most
 *  frequent usage and clients should consider it first before other more complicated alternatives.
 *  </li>
 *
 *  <li>Passing only a surface source class as an argument to the constructor. This is usually
 *  followed by a call to create a capture session
 *  (see {@link CameraDevice#createCaptureSessionByOutputConfigurations} and a {@link Surface} add
 *  call {@link #addSurface} with a valid {@link Surface}. The sequence completes with
 *  {@link CameraCaptureSession#finalizeOutputConfigurations}. This is the deferred usage case which
 *  aims to enhance performance by allowing the resource-intensive capture session create call to
 *  execute in parallel with any {@link Surface} initialization, such as waiting for a
 *  {@link android.view.SurfaceView} to be ready as part of the UI initialization.</li>
 *
 *  <li>The third and most complex usage pattern involves surface sharing. Once instantiated an
 *  OutputConfiguration can be enabled for surface sharing via {@link #enableSurfaceSharing}. This
 *  must be done before creating a new capture session and enables calls to
 *  {@link CameraCaptureSession#updateOutputConfiguration}. An OutputConfiguration with enabled
 *  surface sharing can be modified via {@link #addSurface} or {@link #removeSurface}. The updates
 *  to this OutputConfiguration will only come into effect after
 *  {@link CameraCaptureSession#updateOutputConfiguration} returns without throwing exceptions.
 *  Such updates can be done as long as the session is active. Clients should always consider the
 *  additional requirements and limitations placed on the output surfaces (for more details see
 *  {@link #enableSurfaceSharing}, {@link #addSurface}, {@link #removeSurface},
 *  {@link CameraCaptureSession#updateOutputConfiguration}). A trade-off exists between additional
 *  complexity and flexibility. If exercised correctly surface sharing can switch between different
 *  output surfaces without interrupting any ongoing repeating capture requests. This saves time and
 *  can significantly improve the user experience.</li>
 *
 *  <li>Surface sharing can be used in combination with deferred surfaces. The rules from both cases
 *  are combined and clients must call {@link #enableSurfaceSharing} before creating a capture
 *  session. Attach and/or remove output surfaces via  {@link #addSurface}/{@link #removeSurface} and
 *  finalize the configuration using {@link CameraCaptureSession#finalizeOutputConfigurations}.
 *  {@link CameraCaptureSession#updateOutputConfiguration} can be called after the configuration
 *  finalize method returns without exceptions.</li>
 *
 *  <li>If the camera device supports multi-resolution output streams, {@link
 *  CameraCharacteristics#SCALER_MULTI_RESOLUTION_STREAM_CONFIGURATION_MAP} will contain the
 *  formats and their corresponding stream info. The application can use an OutputConfiguration
 *  created with the multi-resolution stream info queried from {@link
 *  MultiResolutionStreamConfigurationMap#getOutputInfo} and
 *  {@link android.hardware.camera2.MultiResolutionImageReader} to capture variable size images.
 *
 *  </ul>
 *
 *  <p> As of {@link android.os.Build.VERSION_CODES#P Android P}, all formats except
 *  {@link ImageFormat#JPEG} and {@link ImageFormat#RAW_PRIVATE} can be used for sharing, subject to
 *  device support. On prior API levels, only {@link ImageFormat#PRIVATE} format may be used.</p>
 *
 *  @see CameraDevice#createCaptureSessionByOutputConfigurations
 *  @see CameraCharacteristics#SCALER_MULTI_RESOLUTION_STREAM_CONFIGURATION_MAP
 */
public final class OutputConfiguration implements Parcelable {
    /**
     * Automatic mirroring based on camera facing
     *
     * <p>This is the default mirroring mode for the camera device. With this mode,
     * the camera output is mirrored horizontally for front-facing cameras. There is
     * no mirroring for rear-facing and external cameras.</p>
     */
    public static final int MIRROR_MODE_AUTO = 0;

    /**
     * Camera output is mirrored horizontally
     *
     * <p>The camera output is mirrored horizontally, the same behavior as in AUTO mode for
     * front facing camera.</p>
     */
    public static final int MIRROR_MODE_H = 2;

    /**
     * No mirror transform is applied
     *
     * <p>No mirroring is applied to the camera output regardless of the camera facing.</p>
     */
    public static final int MIRROR_MODE_NONE = 1;

    /**
     * Camera output is mirrored vertically
     */
    public static final int MIRROR_MODE_V = 3;

    /**
     *  Invalid surface group ID.
     *
     * <p>An {@link OutputConfiguration} with this value indicates that the included surface
     * doesn't belong to any surface group.</p>
     */
    public static final int SURFACE_GROUP_ID_NONE = -1;

    /**
     * Timestamp is synchronized to choreographer.
     *
     * <p>The timestamp of the output images are overridden with choreographer pulses from the
     * display subsystem for smoother display of camera frames. An output target of SurfaceView
     * uses this time base by default. Note that the timestamp override is done for fixed camera
     * frame rate only.</p>
     *
     * <p>This timestamp base isn't applicable to SurfaceTexture targets. SurfaceTexture's
     * {@link android.graphics.SurfaceTexture#updateTexImage updateTexImage} function always
     * uses the latest image from the camera stream. In the case of a TextureView, the image is
     * displayed right away.</p>
     *
     * <p>Timestamps with this time base cannot directly match the timestamps in
     * {@link CameraCaptureSession.CaptureCallback#onCaptureStarted}, {@link
     * CameraCaptureSession.CaptureCallback#onReadoutStarted}, or the sensor timestamps in
     * {@link android.hardware.camera2.CaptureResult}. This timestamp base shouldn't be used if the
     * timestamp needs to be used for audio-video synchronization.</p>
     */
    public static final int TIMESTAMP_BASE_CHOREOGRAPHER_SYNCED = 4;

    /**
     * Default timestamp base.
     *
     * <p>The camera device decides the timestamp based on the properties of the
     * output surface.</p>
     *
     * <li> For a SurfaceView output surface, the timestamp base is {@link
     * #TIMESTAMP_BASE_CHOREOGRAPHER_SYNCED}. The timestamp is overridden with choreographer
     * pulses from the display subsystem for smoother display of camera frames when the camera
     * device runs in fixed frame rate. The timestamp is roughly in the same time base as
     * {@link android.os.SystemClock#uptimeMillis}.</li>
     * <li> For an output surface of MediaRecorder, MediaCodec, or ImageReader with {@link
     * android.hardware.HardwareBuffer#USAGE_VIDEO_ENCODE} usage flag, the timestamp base is
     * {@link #TIMESTAMP_BASE_MONOTONIC}, which is roughly the same time base as
     * {@link android.os.SystemClock#uptimeMillis}.</li>
     * <li> For all other cases, the timestamp base is {@link #TIMESTAMP_BASE_SENSOR}, the same
     * as what's specified by {@link CameraCharacteristics#SENSOR_INFO_TIMESTAMP_SOURCE}.
     * <ul><li> For a SurfaceTexture output surface, the camera system re-spaces the delivery
     * of output frames based on image readout intervals, reducing viewfinder jitter. The timestamps
     * of images remain to be {@link #TIMESTAMP_BASE_SENSOR}.</li></ul></li>
     *
     * <p>Note that the reduction of frame jitter for SurfaceView and SurfaceTexture comes with
     * slight increase in photon-to-photon latency, which is the time from when photons hit the
     * scene to when the corresponding pixels show up on the screen. If the photon-to-photon latency
     * is more important than the smoothness of viewfinder, {@link #TIMESTAMP_BASE_SENSOR} should be
     * used instead.</p>
     *
     * @see #TIMESTAMP_BASE_CHOREOGRAPHER_SYNCED
     * @see #TIMESTAMP_BASE_MONOTONIC
     * @see #TIMESTAMP_BASE_SENSOR
     */
    public static final int TIMESTAMP_BASE_DEFAULT = 0;

    /**
     * Timestamp base roughly the same as {@link android.os.SystemClock#uptimeMillis}.
     *
     * <p>The timestamps of the output images are monotonically increasing, and are roughly in the
     * same time base as {@link android.os.SystemClock#uptimeMillis}. The timestamps with this
     * time base can be directly used for audio-video sync in video recording.</p>
     *
     * <p>If the camera device's {@link CameraCharacteristics#SENSOR_INFO_TIMESTAMP_SOURCE} is
     * REALTIME, timestamps with this time base cannot directly match the timestamps in
     * {@link CameraCaptureSession.CaptureCallback#onCaptureStarted}, {@link
     * CameraCaptureSession.CaptureCallback#onReadoutStarted}, or the sensor timestamps in
     * {@link android.hardware.camera2.CaptureResult}.</p>
     */
    public static final int TIMESTAMP_BASE_MONOTONIC = 2;

    /**
     * Timestamp base roughly the same as {@link android.os.SystemClock#elapsedRealtime}.
     *
     * <p>The timestamps of the output images are roughly in the
     * same time base as {@link android.os.SystemClock#elapsedRealtime}. The timestamps with this
     * time base cannot be directly used for audio-video sync in video recording.</p>
     *
     * <p>If the camera device's {@link CameraCharacteristics#SENSOR_INFO_TIMESTAMP_SOURCE} is
     * UNKNOWN, timestamps with this time base cannot directly match the timestamps in
     * {@link CameraCaptureSession.CaptureCallback#onCaptureStarted}, {@link
     * CameraCaptureSession.CaptureCallback#onReadoutStarted}, or the sensor timestamps in
     * {@link android.hardware.camera2.CaptureResult}.</p>
     *
     * <p>If using a REALTIME timestamp base on a device that supports only
     * TIMESTAMP_SOURCE_UNKNOWN, the accuracy of timestamps is only what is guaranteed in the
     * documentation for UNKNOWN. In particular, they have no guarantees about being accurate
     * enough to use in fusing image data with the output of inertial sensors, for features such as
     * image stabilization or augmented reality.</p>
     */
    public static final int TIMESTAMP_BASE_REALTIME = 3;

    /**
     * Timestamp base of {@link CameraCharacteristics#SENSOR_INFO_TIMESTAMP_SOURCE}.
     *
     * <p>The timestamps of the output images are in the time base as specified by {@link
     * CameraCharacteristics#SENSOR_INFO_TIMESTAMP_SOURCE}. The application can look up the
     * corresponding result metadata by matching the timestamp with a {@link
     * CameraCaptureSession.CaptureCallback#onCaptureStarted}, or with a {@link
     * CameraCaptureSession.CaptureCallback#onReadoutStarted} if readout timestamp is used.</p>
     */
    public static final int TIMESTAMP_BASE_SENSOR = 1;

    private OutputConfiguration() {
    }
}
