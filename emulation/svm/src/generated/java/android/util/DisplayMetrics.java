package android.util;

/**
 * A structure describing general information about a display, such as its
 * size, density, and font scaling.
 * <p>To access the DisplayMetrics members, retrieve display metrics like this:</p>
 * <pre>context.getResources().getDisplayMetrics();</pre>
 *
 * <p>
 * For UI layout, obtain {@link android.view.WindowMetrics} from
 * {@link WindowManager#getCurrentWindowMetrics()}. {@code DisplayMetrics} should only be used for
 * obtaining display related properties, such as {@link #xdpi} and {@link #ydpi}
 * </p><p>
 * See {@link #density} for more information about the differences between {@link #xdpi},
 * {@link #ydpi} and {@link #density}.
 * </p>
 */
public final class DisplayMetrics {
    /**
     * Intermediate density for screens that sit between {@link #DENSITY_LOW} (120dpi) and
     * {@link #DENSITY_MEDIUM} (160dpi). This is not a density that applications should target,
     * instead relying on the system to scale their {@link #DENSITY_MEDIUM} assets for them.
     */
    public static final int DENSITY_140 = 140;

    /**
     * Intermediate density for screens that sit between {@link #DENSITY_MEDIUM} (160dpi) and
     * {@link #DENSITY_HIGH} (240dpi). This is not a density that applications should target,
     * instead relying on the system to scale their {@link #DENSITY_HIGH} assets for them.
     */
    public static final int DENSITY_180 = 180;

    /**
     * Intermediate density for screens that sit between {@link #DENSITY_MEDIUM} (160dpi) and
     * {@link #DENSITY_HIGH} (240dpi). This is not a density that applications should target,
     * instead relying on the system to scale their {@link #DENSITY_HIGH} assets for them.
     */
    public static final int DENSITY_200 = 200;

    /**
     * Intermediate density for screens that sit between {@link #DENSITY_MEDIUM} (160dpi) and
     * {@link #DENSITY_HIGH} (240dpi). This is not a density that applications should target,
     * instead relying on the system to scale their {@link #DENSITY_HIGH} assets for them.
     */
    public static final int DENSITY_220 = 220;

    /**
     * Intermediate density for screens that sit between {@link #DENSITY_HIGH} (240dpi) and
     * {@link #DENSITY_XHIGH} (320dpi). This is not a density that applications should target,
     * instead relying on the system to scale their {@link #DENSITY_XHIGH} assets for them.
     */
    public static final int DENSITY_260 = 260;

    /**
     * Intermediate density for screens that sit between {@link #DENSITY_HIGH} (240dpi) and
     * {@link #DENSITY_XHIGH} (320dpi). This is not a density that applications should target,
     * instead relying on the system to scale their {@link #DENSITY_XHIGH} assets for them.
     */
    public static final int DENSITY_280 = 280;

    /**
     * Intermediate density for screens that sit between {@link #DENSITY_HIGH} (240dpi) and
     * {@link #DENSITY_XHIGH} (320dpi). This is not a density that applications should target,
     * instead relying on the system to scale their {@link #DENSITY_XHIGH} assets for them.
     */
    public static final int DENSITY_300 = 300;

    /**
     * Intermediate density for screens that sit somewhere between
     * {@link #DENSITY_XHIGH} (320 dpi) and {@link #DENSITY_XXHIGH} (480 dpi).
     * This is not a density that applications should target, instead relying
     * on the system to scale their {@link #DENSITY_XXHIGH} assets for them.
     */
    public static final int DENSITY_340 = 340;

    /**
     * Intermediate density for screens that sit somewhere between
     * {@link #DENSITY_XHIGH} (320 dpi) and {@link #DENSITY_XXHIGH} (480 dpi).
     * This is not a density that applications should target, instead relying
     * on the system to scale their {@link #DENSITY_XXHIGH} assets for them.
     */
    public static final int DENSITY_360 = 360;

    /**
     * Intermediate density for screens that sit somewhere between
     * {@link #DENSITY_XHIGH} (320 dpi) and {@link #DENSITY_XXHIGH} (480 dpi).
     * This is not a density that applications should target, instead relying
     * on the system to scale their {@link #DENSITY_XXHIGH} assets for them.
     */
    public static final int DENSITY_390 = 390;

    /**
     * Intermediate density for screens that sit somewhere between
     * {@link #DENSITY_XHIGH} (320 dpi) and {@link #DENSITY_XXHIGH} (480 dpi).
     * This is not a density that applications should target, instead relying
     * on the system to scale their {@link #DENSITY_XXHIGH} assets for them.
     */
    public static final int DENSITY_400 = 400;

    /**
     * Intermediate density for screens that sit somewhere between
     * {@link #DENSITY_XHIGH} (320 dpi) and {@link #DENSITY_XXHIGH} (480 dpi).
     * This is not a density that applications should target, instead relying
     * on the system to scale their {@link #DENSITY_XXHIGH} assets for them.
     */
    public static final int DENSITY_420 = 420;

    /**
     * Intermediate density for screens that sit somewhere between
     * {@link #DENSITY_XHIGH} (320 dpi) and {@link #DENSITY_XXHIGH} (480 dpi).
     * This is not a density that applications should target, instead relying
     * on the system to scale their {@link #DENSITY_XXHIGH} assets for them.
     */
    public static final int DENSITY_440 = 440;

    /**
     * Intermediate density for screens that sit somewhere between
     * {@link #DENSITY_XHIGH} (320 dpi) and {@link #DENSITY_XXHIGH} (480 dpi).
     * This is not a density that applications should target, instead relying
     * on the system to scale their {@link #DENSITY_XXHIGH} assets for them.
     */
    public static final int DENSITY_450 = 450;

    /**
     * Intermediate density for screens that sit somewhere between
     * {@link #DENSITY_XXHIGH} (480 dpi) and {@link #DENSITY_XXXHIGH} (640 dpi).
     * This is not a density that applications should target, instead relying
     * on the system to scale their {@link #DENSITY_XXXHIGH} assets for them.
     */
    public static final int DENSITY_520 = 520;

    /**
     * Intermediate density for screens that sit somewhere between
     * {@link #DENSITY_XXHIGH} (480 dpi) and {@link #DENSITY_XXXHIGH} (640 dpi).
     * This is not a density that applications should target, instead relying
     * on the system to scale their {@link #DENSITY_XXXHIGH} assets for them.
     */
    public static final int DENSITY_560 = 560;

    /**
     * Intermediate density for screens that sit somewhere between
     * {@link #DENSITY_XXHIGH} (480 dpi) and {@link #DENSITY_XXXHIGH} (640 dpi).
     * This is not a density that applications should target, instead relying
     * on the system to scale their {@link #DENSITY_XXXHIGH} assets for them.
     */
    public static final int DENSITY_600 = 600;

    /**
     * The reference density used throughout the system.
     */
    public static final int DENSITY_DEFAULT = 160;

    /**
     * Standard quantized DPI for high-density screens.
     */
    public static final int DENSITY_HIGH = 240;

    /**
     * Standard quantized DPI for low-density screens.
     */
    public static final int DENSITY_LOW = 120;

    /**
     * Standard quantized DPI for medium-density screens.
     */
    public static final int DENSITY_MEDIUM = 160;

    /**
     * This is a secondary density, added for some common screen configurations.
     * It is recommended that applications not generally target this as a first
     * class density -- that is, don't supply specific graphics for this
     * density, instead allow the platform to scale from other densities
     * (typically {@link #DENSITY_HIGH}) as
     * appropriate.  In most cases (such as using bitmaps in
     * {@link android.graphics.drawable.Drawable}) the platform
     * can perform this scaling at load time, so the only cost is some slight
     * startup runtime overhead.
     *
     * <p>This density was original introduced to correspond with a
     * 720p TV screen: the density for 1080p televisions is
     * {@link #DENSITY_XHIGH}, and the value here provides the same UI
     * size for a TV running at 720p.  It has also found use in 7" tablets,
     * when these devices have 1280x720 displays.
     */
    public static final int DENSITY_TV = 213;

    /**
     * Standard quantized DPI for extra-high-density screens.
     */
    public static final int DENSITY_XHIGH = 320;

    /**
     * Standard quantized DPI for extra-extra-high-density screens.
     */
    public static final int DENSITY_XXHIGH = 480;

    /**
     * Standard quantized DPI for extra-extra-extra-high-density screens.  Applications
     * should not generally worry about this density; relying on XHIGH graphics
     * being scaled up to it should be sufficient for almost all cases.  A typical
     * use of this density would be 4K television screens -- 3840x2160, which
     * is 2x a traditional HD 1920x1080 screen which runs at DENSITY_XHIGH.
     */
    public static final int DENSITY_XXXHIGH = 640;

    private DisplayMetrics() {
    }
}
