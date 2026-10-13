package android.view;

/**
 * Provides a dedicated drawing surface embedded inside of a view hierarchy.
 * You can control the format of this surface and, if you like, its size; the
 * SurfaceView takes care of placing the surface at the correct location on the
 * screen
 *
 * <p>The surface is Z ordered so that it is behind the window holding its
 * SurfaceView; the SurfaceView punches a hole in its window to allow its
 * surface to be displayed. The view hierarchy will take care of correctly
 * compositing with the Surface any siblings of the SurfaceView that would
 * normally appear on top of it. This can be used to place overlays such as
 * buttons on top of the Surface, though note however that it can have an
 * impact on performance since a full alpha-blended composite will be performed
 * each time the Surface changes.
 *
 * <p> The transparent region that makes the surface visible is based on the
 * layout positions in the view hierarchy. If the post-layout transform
 * properties are used to draw a sibling view on top of the SurfaceView, the
 * view may not be properly composited with the surface.
 *
 * <p>Access to the underlying surface is provided via the SurfaceHolder interface,
 * which can be retrieved by calling {@link #getHolder}.
 *
 * <p>The Surface will be created for you while the SurfaceView's window is
 * visible; you should implement {@link SurfaceHolder.Callback#surfaceCreated}
 * and {@link SurfaceHolder.Callback#surfaceDestroyed} to discover when the
 * Surface is created and destroyed as the window is shown and hidden.
 *
 * <p>One of the purposes of this class is to provide a surface in which a
 * secondary thread can render into the screen. If you are going to use it
 * this way, you need to be aware of some threading semantics:
 *
 * <ul>
 * <li> All SurfaceView and
 * {@link SurfaceHolder.Callback SurfaceHolder.Callback} methods will be called
 * from the thread running the SurfaceView's window (typically the main thread
 * of the application). They thus need to correctly synchronize with any
 * state that is also touched by the drawing thread.
 * <li> You must ensure that the drawing thread only touches the underlying
 * Surface while it is valid -- between
 * {@link SurfaceHolder.Callback#surfaceCreated SurfaceHolder.Callback.surfaceCreated()}
 * and
 * {@link SurfaceHolder.Callback#surfaceDestroyed SurfaceHolder.Callback.surfaceDestroyed()}.
 * </ul>
 *
 * <p class="note"><strong>Note:</strong> Starting in platform version
 * {@link android.os.Build.VERSION_CODES#N}, SurfaceView's window position is
 * updated synchronously with other View rendering. This means that translating
 * and scaling a SurfaceView on screen will not cause rendering artifacts. Such
 * artifacts may occur on previous versions of the platform when its window is
 * positioned asynchronously.</p>
 *
 * <p class="note"><strong>Note:</strong> Starting in platform version
 * {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE}, SurfaceView will support arbitrary
 * alpha blending. Prior platform versions ignored alpha values on the SurfaceView if they were
 * between 0 and 1. If the SurfaceView is configured with Z-above, then the alpha is applied
 * directly to the Surface. If the SurfaceView is configured with Z-below, then the alpha is
 * applied to the hole punch directly. Note that when using Z-below, overlapping SurfaceViews
 * may not blend properly as a consequence of not applying alpha to the surface content directly.
 */
public final class SurfaceView {
    /**
     * Default lifecycle of the Surface owned by this SurfaceView.
     *
     * The default lifecycle matches {@link #SURFACE_LIFECYCLE_FOLLOWS_VISIBILITY}.
     */
    public static final int SURFACE_LIFECYCLE_DEFAULT = 0;

    /**
     * The Surface lifecycle is tied to SurfaceView attachment.
     * The Surface is created when the SurfaceView first becomes attached, but is not destroyed
     * until this SurfaceView has been detached from the current window.
     */
    public static final int SURFACE_LIFECYCLE_FOLLOWS_ATTACHMENT = 2;

    /**
     * The Surface lifecycle is tied to SurfaceView visibility.
     *
     * The Surface is created when the SurfaceView becomes visible, and is destroyed when the
     * SurfaceView is no longer visible.
     */
    public static final int SURFACE_LIFECYCLE_FOLLOWS_VISIBILITY = 1;

    private SurfaceView() {
    }
}
