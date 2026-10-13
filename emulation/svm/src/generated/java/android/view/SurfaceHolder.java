package android.view;

/**
 * Abstract interface to someone holding a display surface.  Allows you to
 * control the surface size and format, edit the pixels in the surface, and
 * monitor changes to the surface.  This interface is typically available
 * through the {@link SurfaceView} class.
 *
 * <p>When using this interface from a thread other than the one running
 * its {@link SurfaceView}, you will want to carefully read the
 * methods
 * {@link #lockCanvas} and {@link Callback#surfaceCreated Callback.surfaceCreated()}.
 */
public interface SurfaceHolder {
    /**
     * @deprecated this is ignored, this value is set automatically when needed.
     */
    @Deprecated
    int SURFACE_TYPE_GPU = 2;

    /**
     * @deprecated this is ignored, this value is set automatically when needed.
     */
    @Deprecated
    int SURFACE_TYPE_HARDWARE = 1;

    /**
     * @deprecated this is ignored, this value is set automatically when needed.
     */
    @Deprecated
    int SURFACE_TYPE_NORMAL = 0;

    /**
     * @deprecated this is ignored, this value is set automatically when needed.
     */
    @Deprecated
    int SURFACE_TYPE_PUSH_BUFFERS = 3;
}
