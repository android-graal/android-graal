package android.graphics;

/**
 * <p>Creates an instance of a hardware-accelerated renderer. This is used to render a scene built
 * from {@link RenderNode}s to an output {@link HardwareBuffer}. There can be as many
 * HardwareBufferRenderer instances as desired.</p>
 *
 * <h3>Resources & lifecycle</h3>
 *
 * <p>All HardwareBufferRenderer and {@link HardwareRenderer} instances share a common render
 * thread. Therefore HardwareBufferRenderer will share common resources and GPU utilization with
 * hardware accelerated rendering initiated by the UI thread of an application.
 * The render thread contains the GPU context & resources necessary to do GPU-accelerated
 * rendering. As such, the first HardwareBufferRenderer created comes with the cost of also creating
 * the associated GPU contexts, however each incremental HardwareBufferRenderer thereafter is fairly
 * cheap. The expected usage is to have a HardwareBufferRenderer instance for every active {@link
 * HardwareBuffer}.</p>
 *
 * This is useful in situations where a scene built with {@link RenderNode}s can be consumed
 * directly by the system compositor through
 * {@link SurfaceControl.Transaction#setBuffer(SurfaceControl, HardwareBuffer)}.
 *
 * HardwareBufferRenderer will never clear contents before each draw invocation so previous contents
 * in the {@link HardwareBuffer} target will be preserved across renders.
 */
public final class HardwareBufferRenderer {
    private HardwareBufferRenderer() {
    }

    /**
     * Class that contains data regarding the result of the render request.
     * Consumers are to wait on the provided {@link SyncFence} before consuming the HardwareBuffer
     * provided to {@link HardwareBufferRenderer} as well as verify that the status returned by
     * {@link RenderResult#getStatus()} returns {@link RenderResult#SUCCESS}.
     */
    public static final class RenderResult {
        /**
         * Render request failed with an unknown error
         */
        public static final int ERROR_UNKNOWN = 1;

        /**
         * Render request was completed successfully
         */
        public static final int SUCCESS = 0;

        private RenderResult() {
        }
    }
}
