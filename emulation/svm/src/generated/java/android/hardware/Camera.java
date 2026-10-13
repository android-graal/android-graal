package android.hardware;

/**
 * The Camera class is used to set image capture settings, start/stop preview,
 * snap pictures, and retrieve frames for encoding for video.  This class is a
 * client for the Camera service, which manages the actual camera hardware.
 *
 * <p>To access the device camera, you must declare the
 * {@link android.Manifest.permission#CAMERA} permission in your Android
 * Manifest. Also be sure to include the
 * <a href="{@docRoot}guide/topics/manifest/uses-feature-element.html">&lt;uses-feature></a>
 * manifest element to declare camera features used by your application.
 * For example, if you use the camera and auto-focus feature, your Manifest
 * should include the following:</p>
 * <pre> &lt;uses-permission android:name="android.permission.CAMERA" />
 * &lt;uses-feature android:name="android.hardware.camera" />
 * &lt;uses-feature android:name="android.hardware.camera.autofocus" /></pre>
 *
 * <p>To take pictures with this class, use the following steps:</p>
 *
 * <ol>
 * <li>Obtain an instance of Camera from {@link #open(int)}.
 *
 * <li>Get existing (default) settings with {@link #getParameters()}.
 *
 * <li>If necessary, modify the returned {@link Camera.Parameters} object and call
 * {@link #setParameters(Camera.Parameters)}.
 *
 * <li>Call {@link #setDisplayOrientation(int)} to ensure correct orientation of preview.
 *
 * <li><b>Important</b>: Pass a fully initialized {@link SurfaceHolder} to
 * {@link #setPreviewDisplay(SurfaceHolder)}.  Without a surface, the camera
 * will be unable to start the preview.
 *
 * <li><b>Important</b>: Call {@link #startPreview()} to start updating the
 * preview surface.  Preview must be started before you can take a picture.
 *
 * <li>When you want, call {@link #takePicture(Camera.ShutterCallback,
 * Camera.PictureCallback, Camera.PictureCallback, Camera.PictureCallback)} to
 * capture a photo.  Wait for the callbacks to provide the actual image data.
 *
 * <li>After taking a picture, preview display will have stopped.  To take more
 * photos, call {@link #startPreview()} again first.
 *
 * <li>Call {@link #stopPreview()} to stop updating the preview surface.
 *
 * <li><b>Important:</b> Call {@link #release()} to release the camera for
 * use by other applications.  Applications should release the camera
 * immediately in {@link android.app.Activity#onPause()} (and re-{@link #open()}
 * it in {@link android.app.Activity#onResume()}).
 * </ol>
 *
 * <p>To quickly switch to video recording mode, use these steps:</p>
 *
 * <ol>
 * <li>Obtain and initialize a Camera and start preview as described above.
 *
 * <li>Call {@link #unlock()} to allow the media process to access the camera.
 *
 * <li>Pass the camera to {@link android.media.MediaRecorder#setCamera(Camera)}.
 * See {@link android.media.MediaRecorder} information about video recording.
 *
 * <li>When finished recording, call {@link #reconnect()} to re-acquire
 * and re-lock the camera.
 *
 * <li>If desired, restart preview and take more photos or videos.
 *
 * <li>Call {@link #stopPreview()} and {@link #release()} as described above.
 * </ol>
 *
 * <p>This class is not thread-safe, and is meant for use from one event thread.
 * Most long-running operations (preview, focus, photo capture, etc) happen
 * asynchronously and invoke callbacks as necessary.  Callbacks will be invoked
 * on the event thread {@link #open(int)} was called from.  This class's methods
 * must never be called from multiple threads at once.</p>
 *
 * <p class="caution"><strong>Caution:</strong> Different Android-powered devices
 * may have different hardware specifications, such as megapixel ratings and
 * auto-focus capabilities. In order for your application to be compatible with
 * more devices, you should not make assumptions about the device camera
 * specifications.</p>
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about using cameras, read the
 * <a href="{@docRoot}guide/topics/media/camera.html">Camera</a> developer guide.</p>
 * </div>
 *
 * @deprecated We recommend using the new {@link android.hardware.camera2} API for new
 *             applications.
 */
@Deprecated
public final class Camera {
    /**
     * Broadcast Action:  A new picture is taken by the camera, and the entry of
     * the picture has been added to the media store.
     * {@link android.content.Intent#getData} is URI of the picture.
     *
     * <p>In {@link android.os.Build.VERSION_CODES#N Android N} this broadcast was removed, and
     * applications are recommended to use
     * {@link android.app.job.JobInfo.Builder JobInfo.Builder}.{@link android.app.job.JobInfo.Builder#addTriggerContentUri}
     * instead.</p>
     *
     * <p>In {@link android.os.Build.VERSION_CODES#O Android O} this broadcast has been brought
     * back, but only for <em>registered</em> receivers.  Apps that are actively running can
     * again listen to the broadcast if they want an immediate clear signal about a picture
     * being taken, however anything doing heavy work (or needing to be launched) as a result of
     * this should still use JobScheduler.</p>
     */
    @Deprecated
    public static final String ACTION_NEW_PICTURE = "android.hardware.action.NEW_PICTURE";

    /**
     * Broadcast Action:  A new video is recorded by the camera, and the entry
     * of the video has been added to the media store.
     * {@link android.content.Intent#getData} is URI of the video.
     *
     * <p>In {@link android.os.Build.VERSION_CODES#N Android N} this broadcast was removed, and
     * applications are recommended to use
     * {@link android.app.job.JobInfo.Builder JobInfo.Builder}.{@link android.app.job.JobInfo.Builder#addTriggerContentUri}
     * instead.</p>
     *
     * <p>In {@link android.os.Build.VERSION_CODES#O Android O} this broadcast has been brought
     * back, but only for <em>registered</em> receivers.  Apps that are actively running can
     * again listen to the broadcast if they want an immediate clear signal about a video
     * being taken, however anything doing heavy work (or needing to be launched) as a result of
     * this should still use JobScheduler.</p>
     */
    @Deprecated
    public static final String ACTION_NEW_VIDEO = "android.hardware.action.NEW_VIDEO";

    /**
     * Camera was disconnected due to use by higher priority user.
     * @see Camera.ErrorCallback
     */
    @Deprecated
    public static final int CAMERA_ERROR_EVICTED = 2;

    /**
     * Media server died. In this case, the application must release the
     * Camera object and instantiate a new one.
     * @see Camera.ErrorCallback
     */
    @Deprecated
    public static final int CAMERA_ERROR_SERVER_DIED = 100;

    /**
     * Unspecified camera error.
     * @see Camera.ErrorCallback
     */
    @Deprecated
    public static final int CAMERA_ERROR_UNKNOWN = 1;

    private Camera() {
    }

    /**
     * Information about a camera
     *
     * @deprecated We recommend using the new {@link android.hardware.camera2} API for new
     *             applications.
     */
    @Deprecated
    public static final class CameraInfo {
        /**
         * The facing of the camera is opposite to that of the screen.
         */
        @Deprecated
        public static final int CAMERA_FACING_BACK = 0;

        /**
         * The facing of the camera is the same as that of the screen.
         */
        @Deprecated
        public static final int CAMERA_FACING_FRONT = 1;

        private CameraInfo() {
        }
    }

    /**
     * Camera service settings.
     *
     * <p>To make camera parameters take effect, applications have to call
     * {@link Camera#setParameters(Camera.Parameters)}. For example, after
     * {@link Camera.Parameters#setWhiteBalance} is called, white balance is not
     * actually changed until {@link Camera#setParameters(Camera.Parameters)}
     * is called with the changed parameters object.
     *
     * <p>Different devices may have different camera capabilities, such as
     * picture size or flash modes. The application should query the camera
     * capabilities before setting parameters. For example, the application
     * should call {@link Camera.Parameters#getSupportedColorEffects()} before
     * calling {@link Camera.Parameters#setColorEffect(String)}. If the
     * camera does not support color effects,
     * {@link Camera.Parameters#getSupportedColorEffects()} will return null.
     *
     * @deprecated We recommend using the new {@link android.hardware.camera2} API for new
     *             applications.
     */
    @Deprecated
    public static final class Parameters {
        @Deprecated
        public static final String ANTIBANDING_50HZ = "50hz";

        @Deprecated
        public static final String ANTIBANDING_60HZ = "60hz";

        @Deprecated
        public static final String ANTIBANDING_AUTO = "auto";

        @Deprecated
        public static final String ANTIBANDING_OFF = "off";

        @Deprecated
        public static final String EFFECT_AQUA = "aqua";

        @Deprecated
        public static final String EFFECT_BLACKBOARD = "blackboard";

        @Deprecated
        public static final String EFFECT_MONO = "mono";

        @Deprecated
        public static final String EFFECT_NEGATIVE = "negative";

        @Deprecated
        public static final String EFFECT_NONE = "none";

        @Deprecated
        public static final String EFFECT_POSTERIZE = "posterize";

        @Deprecated
        public static final String EFFECT_SEPIA = "sepia";

        @Deprecated
        public static final String EFFECT_SOLARIZE = "solarize";

        @Deprecated
        public static final String EFFECT_WHITEBOARD = "whiteboard";

        /**
         * Flash will be fired automatically when required. The flash may be fired
         * during preview, auto-focus, or snapshot depending on the driver.
         */
        @Deprecated
        public static final String FLASH_MODE_AUTO = "auto";

        /**
         * Flash will not be fired.
         */
        @Deprecated
        public static final String FLASH_MODE_OFF = "off";

        /**
         * Flash will always be fired during snapshot. The flash may also be
         * fired during preview or auto-focus depending on the driver.
         */
        @Deprecated
        public static final String FLASH_MODE_ON = "on";

        /**
         * Flash will be fired in red-eye reduction mode.
         */
        @Deprecated
        public static final String FLASH_MODE_RED_EYE = "red-eye";

        /**
         * Constant emission of light during preview, auto-focus and snapshot.
         * This can also be used for video recording.
         */
        @Deprecated
        public static final String FLASH_MODE_TORCH = "torch";

        /**
         * The array index of far focus distance for use with
         * {@link #getFocusDistances(float[])}.
         */
        @Deprecated
        public static final int FOCUS_DISTANCE_FAR_INDEX = 2;

        /**
         * The array index of near focus distance for use with
         * {@link #getFocusDistances(float[])}.
         */
        @Deprecated
        public static final int FOCUS_DISTANCE_NEAR_INDEX = 0;

        /**
         * The array index of optimal focus distance for use with
         * {@link #getFocusDistances(float[])}.
         */
        @Deprecated
        public static final int FOCUS_DISTANCE_OPTIMAL_INDEX = 1;

        /**
         * Auto-focus mode. Applications should call {@link
         * #autoFocus(AutoFocusCallback)} to start the focus in this mode.
         */
        @Deprecated
        public static final String FOCUS_MODE_AUTO = "auto";

        /**
         * Continuous auto focus mode intended for taking pictures. The camera
         * continuously tries to focus. The speed of focus change is more
         * aggressive than {@link #FOCUS_MODE_CONTINUOUS_VIDEO}. Auto focus
         * starts when the parameter is set.
         *
         * <p>Applications can call {@link #autoFocus(AutoFocusCallback)} in
         * this mode. If the autofocus is in the middle of scanning, the focus
         * callback will return when it completes. If the autofocus is not
         * scanning, the focus callback will immediately return with a boolean
         * that indicates whether the focus is sharp or not. The apps can then
         * decide if they want to take a picture immediately or to change the
         * focus mode to auto, and run a full autofocus cycle. The focus
         * position is locked after autoFocus call. If applications want to
         * resume the continuous focus, cancelAutoFocus must be called.
         * Restarting the preview will not resume the continuous autofocus. To
         * stop continuous focus, applications should change the focus mode to
         * other modes.
         *
         * @see #FOCUS_MODE_CONTINUOUS_VIDEO
         */
        @Deprecated
        public static final String FOCUS_MODE_CONTINUOUS_PICTURE = "continuous-picture";

        /**
         * Continuous auto focus mode intended for video recording. The camera
         * continuously tries to focus. This is the best choice for video
         * recording because the focus changes smoothly . Applications still can
         * call {@link #takePicture(Camera.ShutterCallback,
         * Camera.PictureCallback, Camera.PictureCallback)} in this mode but the
         * subject may not be in focus. Auto focus starts when the parameter is
         * set.
         *
         * <p>Since API level 14, applications can call {@link
         * #autoFocus(AutoFocusCallback)} in this mode. The focus callback will
         * immediately return with a boolean that indicates whether the focus is
         * sharp or not. The focus position is locked after autoFocus call. If
         * applications want to resume the continuous focus, cancelAutoFocus
         * must be called. Restarting the preview will not resume the continuous
         * autofocus. To stop continuous focus, applications should change the
         * focus mode to other modes.
         *
         * @see #FOCUS_MODE_CONTINUOUS_PICTURE
         */
        @Deprecated
        public static final String FOCUS_MODE_CONTINUOUS_VIDEO = "continuous-video";

        /**
         * Extended depth of field (EDOF). Focusing is done digitally and
         * continuously. Applications should not call {@link
         * #autoFocus(AutoFocusCallback)} in this mode.
         */
        @Deprecated
        public static final String FOCUS_MODE_EDOF = "edof";

        /**
         * Focus is fixed. The camera is always in this mode if the focus is not
         * adjustable. If the camera has auto-focus, this mode can fix the
         * focus, which is usually at hyperfocal distance. Applications should
         * not call {@link #autoFocus(AutoFocusCallback)} in this mode.
         */
        @Deprecated
        public static final String FOCUS_MODE_FIXED = "fixed";

        /**
         * Focus is set at infinity. Applications should not call
         * {@link #autoFocus(AutoFocusCallback)} in this mode.
         */
        @Deprecated
        public static final String FOCUS_MODE_INFINITY = "infinity";

        /**
         * Macro (close-up) focus mode. Applications should call
         * {@link #autoFocus(AutoFocusCallback)} to start the focus in this
         * mode.
         */
        @Deprecated
        public static final String FOCUS_MODE_MACRO = "macro";

        /**
         * The array index of maximum preview fps for use with {@link
         * #getPreviewFpsRange(int[])} or {@link
         * #getSupportedPreviewFpsRange()}.
         */
        @Deprecated
        public static final int PREVIEW_FPS_MAX_INDEX = 1;

        /**
         * The array index of minimum preview fps for use with {@link
         * #getPreviewFpsRange(int[])} or {@link
         * #getSupportedPreviewFpsRange()}.
         */
        @Deprecated
        public static final int PREVIEW_FPS_MIN_INDEX = 0;

        /**
         * Take photos of fast moving objects. Same as {@link
         * #SCENE_MODE_SPORTS}.
         */
        @Deprecated
        public static final String SCENE_MODE_ACTION = "action";

        /**
         * Scene mode is off.
         */
        @Deprecated
        public static final String SCENE_MODE_AUTO = "auto";

        /**
         * Applications are looking for a barcode. Camera driver will be
         * optimized for barcode reading.
         */
        @Deprecated
        public static final String SCENE_MODE_BARCODE = "barcode";

        /**
         * Take pictures on the beach.
         */
        @Deprecated
        public static final String SCENE_MODE_BEACH = "beach";

        /**
         * Capture the naturally warm color of scenes lit by candles.
         */
        @Deprecated
        public static final String SCENE_MODE_CANDLELIGHT = "candlelight";

        /**
         * For shooting firework displays.
         */
        @Deprecated
        public static final String SCENE_MODE_FIREWORKS = "fireworks";

        /**
         * Capture a scene using high dynamic range imaging techniques. The
         * camera will return an image that has an extended dynamic range
         * compared to a regular capture. Capturing such an image may take
         * longer than a regular capture.
         */
        @Deprecated
        public static final String SCENE_MODE_HDR = "hdr";

        /**
         * Take pictures on distant objects.
         */
        @Deprecated
        public static final String SCENE_MODE_LANDSCAPE = "landscape";

        /**
         * Take photos at night.
         */
        @Deprecated
        public static final String SCENE_MODE_NIGHT = "night";

        /**
         * Take people pictures at night.
         */
        @Deprecated
        public static final String SCENE_MODE_NIGHT_PORTRAIT = "night-portrait";

        /**
         * Take indoor low-light shot.
         */
        @Deprecated
        public static final String SCENE_MODE_PARTY = "party";

        /**
         * Take people pictures.
         */
        @Deprecated
        public static final String SCENE_MODE_PORTRAIT = "portrait";

        /**
         * Take pictures on the snow.
         */
        @Deprecated
        public static final String SCENE_MODE_SNOW = "snow";

        /**
         * Take photos of fast moving objects. Same as {@link
         * #SCENE_MODE_ACTION}.
         */
        @Deprecated
        public static final String SCENE_MODE_SPORTS = "sports";

        /**
         * Avoid blurry pictures (for example, due to hand shake).
         */
        @Deprecated
        public static final String SCENE_MODE_STEADYPHOTO = "steadyphoto";

        /**
         * Take sunset photos.
         */
        @Deprecated
        public static final String SCENE_MODE_SUNSET = "sunset";

        /**
         * Take photos in a theater. Flash light is off.
         */
        @Deprecated
        public static final String SCENE_MODE_THEATRE = "theatre";

        @Deprecated
        public static final String WHITE_BALANCE_AUTO = "auto";

        @Deprecated
        public static final String WHITE_BALANCE_CLOUDY_DAYLIGHT = "cloudy-daylight";

        @Deprecated
        public static final String WHITE_BALANCE_DAYLIGHT = "daylight";

        @Deprecated
        public static final String WHITE_BALANCE_FLUORESCENT = "fluorescent";

        @Deprecated
        public static final String WHITE_BALANCE_INCANDESCENT = "incandescent";

        @Deprecated
        public static final String WHITE_BALANCE_SHADE = "shade";

        @Deprecated
        public static final String WHITE_BALANCE_TWILIGHT = "twilight";

        @Deprecated
        public static final String WHITE_BALANCE_WARM_FLUORESCENT = "warm-fluorescent";

        private Parameters() {
        }
    }
}
