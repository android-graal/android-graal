package android.graphics;

/**
 * <p>Creates an instance of a hardware-accelerated renderer. This is used to render a scene built
 * from {@link RenderNode}'s to an output {@link android.view.Surface}. There can be as many
 * HardwareRenderer instances as desired.</p>
 *
 * <h3>Resources & lifecycle</h3>
 *
 * <p>All HardwareRenderer instances share a common render thread. The render thread contains
 * the GPU context & resources necessary to do GPU-accelerated rendering. As such, the first
 * HardwareRenderer created comes with the cost of also creating the associated GPU contexts,
 * however each incremental HardwareRenderer thereafter is fairly cheap. The expected usage
 * is to have a HardwareRenderer instance for every active {@link Surface}. For example
 * when an Activity shows a Dialog the system internally will use 2 hardware renderers, both
 * of which may be drawing at the same time.</p>
 *
 * <p>NOTE: Due to the shared, cooperative nature of the render thread it is critical that
 * any {@link Surface} used must have a prompt, reliable consuming side. System-provided
 * consumers such as {@link android.view.SurfaceView},
 * {@link android.view.Window#takeSurface(SurfaceHolder.Callback2)},
 * or {@link android.view.TextureView} all fit this requirement. However if custom consumers
 * are used such as when using {@link SurfaceTexture} or {@link android.media.ImageReader}
 * it is the app's responsibility to ensure that they consume updates promptly and rapidly.
 * Failure to do so will cause the render thread to stall on that surface, blocking all
 * HardwareRenderer instances.</p>
 */
public final class HardwareRenderer {
    /**
     * The hardware renderer has been set to a "stopped" state. If this is returned then the
     * rendering content has been synced, however a frame was not produced.
     */
    public static final int SYNC_CONTEXT_IS_STOPPED = 4;

    /**
     * The content was synced but the renderer has declined to produce a frame in this vsync
     * interval. This can happen if a frame was already drawn in this vsync or if the renderer
     * is outrunning the frame consumer. The renderer will internally re-schedule itself
     * to render a frame in the next vsync signal, so the caller does not need to do anything
     * in response to this signal.
     */
    public static final int SYNC_FRAME_DROPPED = 8;

    /**
     * The hardware renderer no longer has a valid {@link android.view.Surface} to render to.
     * This can happen if {@link Surface#release()} was called. The user should no longer
     * attempt to call syncAndDraw until a new surface has been provided by calling
     * setSurface.
     *
     * <p>Spoiler: the reward is GPU-accelerated drawing, better find that Surface!
     */
    public static final int SYNC_LOST_SURFACE_REWARD_IF_FOUND = 2;

    /**
     * Nothing interesting to report. Sync & draw kicked off
     */
    public static final int SYNC_OK = 0;

    /**
     * The renderer is requesting a redraw. This can occur if there's an animation that's running
     * in the RenderNode tree and the hardware renderer is unable to self-animate.
     *
     * <p>If this is returned from syncAndDraw the expectation is that syncAndDraw
     * will be called again on the next vsync signal.
     */
    public static final int SYNC_REDRAW_REQUESTED = 1;

    private HardwareRenderer() {
    }
}
