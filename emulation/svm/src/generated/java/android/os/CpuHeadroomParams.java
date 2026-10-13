package android.os;

/**
 * Headroom request params used by {@link SystemHealthManager#getCpuHeadroom(CpuHeadroomParams)}.
 *
 * <p>This class is immutable and one should use the {@link Builder} to build a new instance.
 */
public final class CpuHeadroomParams {
    /**
     * The headroom calculation type bases on average value over a specified window.
     */
    public static final int CPU_HEADROOM_CALCULATION_TYPE_AVERAGE = 1;

    /**
     * The headroom calculation type bases on minimum value over a specified window.
     */
    public static final int CPU_HEADROOM_CALCULATION_TYPE_MIN = 0;

    private CpuHeadroomParams() {
    }
}
