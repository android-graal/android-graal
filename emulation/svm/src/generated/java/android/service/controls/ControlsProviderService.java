package android.service.controls;

/**
 * Service implementation allowing applications to contribute controls to the
 * System UI.
 */
public final class ControlsProviderService {
    /**
     * Controls are being shown on the device controls activity panel.
     */
    public static final int CONTROLS_SURFACE_ACTIVITY_PANEL = 0;

    /**
     * Controls are being shown as a dream, while the device is idle.
     */
    public static final int CONTROLS_SURFACE_DREAM = 1;

    /**
     * Integer extra whose value specifies the surface which controls are being displayed on.
     * <p>
     * The possible values are:
     * <ul>
     * <li>{@link #CONTROLS_SURFACE_ACTIVITY_PANEL}
     * <li>{@link #CONTROLS_SURFACE_DREAM}
     * </ul>
     *
     * This is passed with the intent when the panel specified by {@link #META_DATA_PANEL_ACTIVITY}
     * is launched.
     */
    public static final String EXTRA_CONTROLS_SURFACE = "android.service.controls.extra.CONTROLS_SURFACE";

    /**
     * Boolean extra containing the value of the setting allowing actions on a locked device.
     *
     * This corresponds to the setting that indicates whether the user has
     * consented to allow actions on devices that declare {@link Control#isAuthRequired()} as
     * {@code false} when the device is locked.
     *
     * This is passed with the intent when the panel specified by {@link #META_DATA_PANEL_ACTIVITY}
     * is launched.
     */
    public static final String EXTRA_LOCKSCREEN_ALLOW_TRIVIAL_CONTROLS = "android.service.controls.extra.LOCKSCREEN_ALLOW_TRIVIAL_CONTROLS";

    /**
     * Manifest metadata to show a custom embedded activity as part of device controls.
     *
     * The value of this metadata must be the {@link ComponentName} as a string of an activity in
     * the same package that will be launched embedded in the device controls space.
     *
     * The activity must be exported, enabled and protected by
     * {@link Manifest.permission#BIND_CONTROLS}.
     *
     * It is recommended that the activity is declared {@code android:resizeableActivity="true"}.
     */
    public static final String META_DATA_PANEL_ACTIVITY = "android.service.controls.META_DATA_PANEL_ACTIVITY";

    public static final String SERVICE_CONTROLS = "android.service.controls.ControlsProviderService";

    public static final String TAG = "ControlsProviderService";

    private ControlsProviderService() {
    }
}
