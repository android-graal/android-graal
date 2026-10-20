package android.view;

import android.os.Parcelable;

/**
 * Provides information about the size and density of a logical display.
 * <p>
 * The display area is described in two different ways.
 * <ul>
 * <li>The application display area specifies the part of the display that may contain
 * an application window, excluding the system decorations.  The application display area may
 * be smaller than the real display area because the system subtracts the space needed
 * for decor elements such as the status bar.  Use {@link WindowMetrics#getBounds()} to query the
 * application window bounds.</li>
 * <li>The real display area specifies the part of the display that is accessible to an application
 * in the current system state. The real display area may be smaller than the physical size of the
 * display in a few scenarios. Use {@link WindowManager#getCurrentWindowMetrics()} to identify the
 * current size of the activity window. UI-related work, such as choosing UI layouts, should rely
 * upon {@link WindowMetrics#getBounds()}. See {@link #getRealSize} / {@link #getRealMetrics} for
 * details.</li>
 * </ul>
 * </p><p>
 * A logical display does not necessarily represent a particular physical display device
 * such as the internal display or an external display.  The contents of a logical
 * display may be presented on one or more physical displays according to the devices
 * that are currently attached and whether mirroring has been enabled.
 * </p>
 */
public final class Display {
    /**
     * The default Display id, which is the id of the primary display assuming there is one.
     */
    public static final int DEFAULT_DISPLAY = 0;

    /**
     * Display flag: Indicates that the display is a presentation display.
     * <p>
     * This flag identifies secondary displays that are suitable for
     * use as presentation displays such as external or wireless displays.  Applications
     * may automatically project their content to presentation displays to provide
     * richer second screen experiences.
     * </p>
     *
     * @see #getFlags
     */
    public static final int FLAG_PRESENTATION = 8;

    /**
     * Display flag: Indicates that the display is private.  Only the application that
     * owns the display and apps that are already on the display can create windows on it.
     *
     * @see #getFlags
     */
    public static final int FLAG_PRIVATE = 4;

    /**
     * Display flag: Indicates that the display has a round shape.
     * <p>
     * This flag identifies displays that are circular, elliptical or otherwise
     * do not permit the user to see all the way to the logical corners of the display.
     * </p>
     *
     * @see #getFlags
     */
    public static final int FLAG_ROUND = 16;

    /**
     * Display flag: Indicates that the display has a secure video output and
     * supports compositing secure surfaces.
     * <p>
     * If this flag is set then the display device has a secure video output
     * and is capable of showing secure surfaces.  It may also be capable of
     * showing {@link #FLAG_SUPPORTS_PROTECTED_BUFFERS protected buffers}.
     * </p><p>
     * If this flag is not set then the display device may not have a secure video
     * output; the user may see a blank region on the screen instead of
     * the contents of secure surfaces or protected buffers.
     * </p><p>
     * Secure surfaces are used to prevent content rendered into those surfaces
     * by applications from appearing in screenshots or from being viewed
     * on non-secure displays.  Protected buffers are used by secure video decoders
     * for a similar purpose.
     * </p><p>
     * An application creates a window with a secure surface by specifying the
     * {@link WindowManager.LayoutParams#FLAG_SECURE} window flag.
     * Likewise, an application creates a {@link SurfaceView} with a secure surface
     * by calling {@link SurfaceView#setSecure} before attaching the secure view to
     * its containing window.
     * </p><p>
     * An application can use the absence of this flag as a hint that it should not create
     * secure surfaces or protected buffers on this display because the content may
     * not be visible.  For example, if the flag is not set then the application may
     * choose not to show content on this display, show an informative error message,
     * select an alternate content stream or adopt a different strategy for decoding
     * content that does not rely on secure surfaces or protected buffers.
     * </p>
     *
     * @see #getFlags
     */
    public static final int FLAG_SECURE = 2;

    /**
     * Display flag: Indicates that the display supports compositing content
     * that is stored in protected graphics buffers.
     * <p>
     * If this flag is set then the display device supports compositing protected buffers.
     * </p><p>
     * If this flag is not set then the display device may not support compositing
     * protected buffers; the user may see a blank region on the screen instead of
     * the protected content.
     * </p><p>
     * Secure (DRM) video decoders may allocate protected graphics buffers to request that
     * a hardware-protected path be provided between the video decoder and the external
     * display sink.  If a hardware-protected path is not available, then content stored
     * in protected graphics buffers may not be composited.
     * </p><p>
     * An application can use the absence of this flag as a hint that it should not use protected
     * buffers for this display because the content may not be visible.  For example,
     * if the flag is not set then the application may choose not to show content on this
     * display, show an informative error message, select an alternate content stream
     * or adopt a different strategy for decoding content that does not rely on
     * protected buffers.
     * </p>
     *
     * @see #getFlags
     */
    public static final int FLAG_SUPPORTS_PROTECTED_BUFFERS = 1;

    /**
     * High category determines the framework's recommended high frame rate.
     * Opt for this high rate when a higher frame rate significantly enhances
     * the user experience.
     *
     * @see #getSuggestedFrameRate(int)
     * @see #FRAME_RATE_CATEGORY_NORMAL
     */
    public static final int FRAME_RATE_CATEGORY_HIGH = 1;

    /**
     * Normal category determines the framework's recommended normal frame rate.
     * Opt for this normal rate unless a higher frame rate significantly enhances
     * the user experience.
     *
     * @see #getSuggestedFrameRate(int)
     * @see #FRAME_RATE_CATEGORY_HIGH
     */
    public static final int FRAME_RATE_CATEGORY_NORMAL = 0;

    /**
     * Invalid display id.
     */
    public static final int INVALID_DISPLAY = -1;

    /**
     * Display state: The display is dozing in a low power state; it is still
     * on but is optimized for showing system-provided content while the
     * device is non-interactive.
     *
     * @see #getState
     * @see android.os.PowerManager#isInteractive
     */
    public static final int STATE_DOZE = 3;

    /**
     * Display state: The display is dozing in a suspended low power state; it is still
     * on but the CPU is not updating it. This may be used in one of two ways: to show
     * static system-provided content while the device is non-interactive, or to allow
     * a "Sidekick" compute resource to update the display. For this reason, the
     * CPU must not control the display in this mode.
     *
     * @see #getState
     * @see android.os.PowerManager#isInteractive
     */
    public static final int STATE_DOZE_SUSPEND = 4;

    /**
     * Display state: The display is off.
     *
     * @see #getState
     */
    public static final int STATE_OFF = 1;

    /**
     * Display state: The display is on.
     *
     * @see #getState
     */
    public static final int STATE_ON = 2;

    /**
     * Display state: The display is in a suspended full power state; it is still
     * on but the CPU is not updating it. This may be used in one of two ways: to show
     * static system-provided content while the device is non-interactive, or to allow
     * a "Sidekick" compute resource to update the display. For this reason, the
     * CPU must not control the display in this mode.
     *
     * @see #getState
     * @see android.os.PowerManager#isInteractive
     */
    public static final int STATE_ON_SUSPEND = 6;

    /**
     * Display state: The display state is unknown.
     *
     * @see #getState
     */
    public static final int STATE_UNKNOWN = 0;

    /**
     * Display state: The display is on and optimized for VR mode.
     *
     * @see #getState
     * @see android.os.PowerManager#isInteractive
     */
    public static final int STATE_VR = 5;

    private Display() {
    }

    /**
     * Encapsulates the HDR capabilities of a given display.
     * For example, what HDR types it supports and details about the desired luminance data.
     * <p>You can get an instance for a given {@link Display} object with
     * {@link Display#getHdrCapabilities getHdrCapabilities()}.
     */
    public static final class HdrCapabilities implements Parcelable {
        /**
         * Dolby Vision high dynamic range (HDR) display.
         */
        public static final int HDR_TYPE_DOLBY_VISION = 1;

        /**
         * HDR10 display.
         */
        public static final int HDR_TYPE_HDR10 = 2;

        /**
         * HDR10+ display.
         */
        public static final int HDR_TYPE_HDR10_PLUS = 4;

        /**
         * Hybrid Log-Gamma HDR display.
         */
        public static final int HDR_TYPE_HLG = 3;

        /**
         * Invalid HDR type value.
         */
        public static final int HDR_TYPE_INVALID = -1;

        /**
         * Invalid luminance value.
         */
        public static final float INVALID_LUMINANCE = -1.0f;

        @Override
        public int describeContents() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void writeToParcel(android.os.Parcel dest, int flags) {
            throw new UnsupportedOperationException();
        }

        private HdrCapabilities() {
        }
    }
}
