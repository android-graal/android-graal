package android.view;

/**
 * Class containing timing data for various milestones in a frame
 * lifecycle reported by the rendering subsystem.
 * <p>
 * Supported metrics can be queried via their corresponding identifier.
 * </p>
 */
public final class FrameMetrics {
    /**
     * Metric identifier for animation callback duration.
     * <p>
     * Represents the number of nanoseconds elapsed issuing
     * animation callbacks.
     * </p>
     */
    public static final int ANIMATION_DURATION = 2;

    /**
     * Metric identifier for command issue duration.
     * <p>
     * Represents the number of nanoseconds elapsed
     * issuing draw commands to the GPU.
     * </p>
     */
    public static final int COMMAND_ISSUE_DURATION = 6;

    /**
     * Metric identifier for the total duration that was available to the app to produce a frame.
     * <p>
     * Represents the total time in nanoseconds the system allocated for the app to produce its
     * frame. If FrameMetrics.TOTAL_DURATION < FrameMetrics.DEADLINE, the app hit its intended
     * deadline and there was no jank visible to the user.
     * </p>
     */
    public static final int DEADLINE = 13;

    /**
     * Metric identifier for draw duration.
     * <p>
     * Represents the number of nanoseconds elapsed computing
     * DisplayLists for transformations applied to the view
     * hierarchy.
     * </p>
     */
    public static final int DRAW_DURATION = 4;

    /**
     * Metric identifier for a boolean value determining whether this frame was
     * the first to draw in a new Window layout.
     * <p>
     * {@link #getMetric(int)} will return 0 for false, 1 for true.
     * </p>
     * <p>
     * First draw frames are expected to be slow and should usually be exempt
     * from display jank calculations as they do not cause skips in animations
     * and are usually hidden by window animations or other tricks.
     * </p>
     */
    public static final int FIRST_DRAW_FRAME = 9;

    /**
     * Metric identifier for the frame's VSync identifier.
     * <p>
     * The id that corresponds to the chosen frame timeline, used to correlate a frame produced
     * by HWUI with the timeline data from the compositor.
     * </p>
     */
    public static final int FRAME_TIMELINE_VSYNC_ID = 14;

    /**
     * Metric identifier for GPU duration.
     * <p>
     * Represents the total time in nanoseconds this frame took to complete on the GPU.
     * </p>
     */
    public static final int GPU_DURATION = 12;

    /**
     * Metric identifier for input handling duration.
     * <p>
     * Represents the number of nanoseconds elapsed issuing
     * input handling callbacks.
     * </p>
     */
    public static final int INPUT_HANDLING_DURATION = 1;

    /**
     * Metric identifier for the timestamp of the intended vsync for this frame.
     * <p>
     * The intended start point for the frame. If this value is different from
     * {@link #VSYNC_TIMESTAMP}, there was work occurring on the UI thread that
     * prevented it from responding to the vsync signal in a timely fashion.
     * </p>
     */
    public static final int INTENDED_VSYNC_TIMESTAMP = 10;

    /**
     * Metric identifier for layout/measure duration.
     * <p>
     * Represents the number of nanoseconds elapsed measuring
     * and laying out the invalidated pieces of the view hierarchy.
     * </p>
     */
    public static final int LAYOUT_MEASURE_DURATION = 3;

    /**
     * Metric identifier for swap buffers duration.
     * <p>
     * Represents the number of nanoseconds elapsed issuing
     * the frame buffer for this frame to the display
     * subsystem.
     * </p>
     */
    public static final int SWAP_BUFFERS_DURATION = 7;

    /**
     * Metric identifier for sync duration.
     * <p>
     * Represents the number of nanoseconds elapsed
     * synchronizing the computed display lists with the render
     * thread.
     * </p>
     */
    public static final int SYNC_DURATION = 5;

    /**
     * Metric identifier for total frame duration.
     * <p>
     * Represents the total time in nanoseconds this frame took to render
     * and be issued to the display subsystem.
     * </p>
     * <p>
     * The total duration is the difference in time between when the frame
     * began and when it ended. This value may not be exactly equal to the
     * sum of the values of all other time-valued metric identifiers because
     * some stages may happen concurrently.
     * </p>
     */
    public static final int TOTAL_DURATION = 8;

    /**
     * Metric identifier for unknown delay.
     * <p>
     * Represents the number of nanoseconds elapsed waiting for the
     * UI thread to become responsive and process the frame. This
     * should be 0 most of the time.
     * </p>
     */
    public static final int UNKNOWN_DELAY_DURATION = 0;

    /**
     * Metric identifier for the timestamp of the actual vsync for this frame.
     * <p>
     * The time value that was used in all the vsync listeners and drawing for
     * the frame (Choreographer frame callbacks, animations,
     * {@link View#getDrawingTime()}, etc.)
     * </p>
     */
    public static final int VSYNC_TIMESTAMP = 11;

    private FrameMetrics() {
    }
}
