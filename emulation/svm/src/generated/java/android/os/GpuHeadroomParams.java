package android.os;

/**
 * Headroom request params used by {@link SystemHealthManager#getGpuHeadroom(GpuHeadroomParams)}.
 *
 * <p>This class is immutable and one should use the {@link Builder} to build a new instance.
 */
public final class GpuHeadroomParams {
    /**
     * The headroom calculation type bases on average value over a specified window.
     */
    public static final int GPU_HEADROOM_CALCULATION_TYPE_AVERAGE = 1;

    /**
     * The headroom calculation type bases on minimum value over a specified window.
     */
    public static final int GPU_HEADROOM_CALCULATION_TYPE_MIN = 0;

    /**
     * The maximum size of the window to compute the headroom over.
     */
    public static final int GPU_HEADROOM_CALCULATION_WINDOW_MILLIS_MAX = 10000;

    /**
     * The minimum size of the window to compute the headroom over.
     */
    public static final int GPU_HEADROOM_CALCULATION_WINDOW_MILLIS_MIN = 50;

    private GpuHeadroomParams() {
    }
}
