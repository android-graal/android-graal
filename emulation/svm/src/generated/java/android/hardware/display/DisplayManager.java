package android.hardware.display;

/**
 * Manages the properties of attached displays.
 */
public final class DisplayManager {
    /**
     * Display category: Presentation displays.
     * <p>
     * This category can be used to identify secondary displays that are suitable for
     * use as presentation displays such as external or wireless displays.  Applications
     * may automatically project their content to presentation displays to provide
     * richer second screen experiences.
     * </p>
     *
     * @see android.app.Presentation
     * @see Display#FLAG_PRESENTATION
     * @see #getDisplays(String)
     */
    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";

    /**
     * Event type for when a new display is added. This notification is sent
     * through the {@link DisplayListener#onDisplayAdded} callback method
     *
     * @see #registerDisplayListener(DisplayListener, Handler, long)
     */
    public static final long EVENT_TYPE_DISPLAY_ADDED = 1L;

    /**
     * Event type for when a display is changed. {@link DisplayListener#onDisplayChanged} callback
     * is triggered whenever the properties of a {@link android.view.Display}, such as size,
     * state, density are modified.
     *
     * This event is not triggered for refresh rate changes as they can change very often.
     * To monitor refresh rate changes, subscribe to {@link EVENT_TYPE_DISPLAY_REFRESH_RATE}.
     *
     * @see #registerDisplayListener(DisplayListener, Handler, long)
     */
    public static final long EVENT_TYPE_DISPLAY_CHANGED = 4L;

    /**
     * Event type for when a display's refresh rate changes.
     * {@link DisplayListener#onDisplayChanged} callback is triggered whenever the refresh rate
     * of the display changes. New refresh rate values can be retrieved via
     * {@link Display#getRefreshRate()}.
     *
     * @see #registerDisplayListener(DisplayListener, Handler, long)
     */
    public static final long EVENT_TYPE_DISPLAY_REFRESH_RATE = 8L;

    /**
     * Event type for when a display is removed. This notification is sent
     * through the {@link DisplayListener#onDisplayRemoved} callback method
     *
     * @see #registerDisplayListener(DisplayListener, Handler, long)
     */
    public static final long EVENT_TYPE_DISPLAY_REMOVED = 2L;

    /**
     * Event type for when a display state changes.
     * {@link DisplayListener#onDisplayChanged} callback is triggered whenever the state
     * of the display changes. New state values can be retrieved via
     * {@link Display#getState()}.
     *
     * @see #registerDisplayListener(DisplayListener, Handler, long)
     */
    public static final long EVENT_TYPE_DISPLAY_STATE = 16L;

    /**
     * Refresh rate switches between all refresh rates are allowed even if they have visual
     * interruptions for the user.
     */
    public static final int MATCH_CONTENT_FRAMERATE_ALWAYS = 2;

    /**
     * No mode switching is allowed.
     */
    public static final int MATCH_CONTENT_FRAMERATE_NEVER = 0;

    /**
     * Only refresh rate switches without visual interruptions are allowed.
     */
    public static final int MATCH_CONTENT_FRAMERATE_SEAMLESSS_ONLY = 1;

    /**
     * Match content frame rate user preference is unknown.
     */
    public static final int MATCH_CONTENT_FRAMERATE_UNKNOWN = -1;

    /**
     * Virtual display flag: Allows content to be mirrored on private displays when no content is
     * being shown.
     *
     * <p>
     * This flag is mutually exclusive with {@link #VIRTUAL_DISPLAY_FLAG_OWN_CONTENT_ONLY}.
     * If both flags are specified then the own-content only behavior will be applied.
     * </p>
     *
     * <p>
     * The behavior of this flag is implied whenever {@link #VIRTUAL_DISPLAY_FLAG_PUBLIC} is set
     * and {@link #VIRTUAL_DISPLAY_FLAG_OWN_CONTENT_ONLY} has not been set.   This flag is only
     * required to override the default behavior when creating a private display.
     * </p>
     *
     * <p>
     * Creating an auto-mirroing virtual display requires the CAPTURE_VIDEO_OUTPUT
     * or CAPTURE_SECURE_VIDEO_OUTPUT permission.
     * These permissions are reserved for use by system components and are not available to
     * third-party applications.
     *
     * Alternatively, an appropriate {@link MediaProjection} may be used to create an
     * auto-mirroring virtual display.
     * </p>
     *
     * @see #createVirtualDisplay
     */
    public static final int VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR = 16;

    /**
     * Virtual display flag: Only show this display's own content; do not mirror
     * the content of another display.
     *
     * <p>
     * This flag is used in conjunction with {@link #VIRTUAL_DISPLAY_FLAG_PUBLIC}.
     * Ordinarily public virtual displays will automatically mirror the content of the
     * default display if they have no windows of their own.  When this flag is
     * specified, the virtual display will only ever show its own content and
     * will be blanked instead if it has no windows.
     * </p>
     *
     * <p>
     * This flag is mutually exclusive with {@link #VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR}.  If both
     * flags are specified then the own-content only behavior will be applied.
     * </p>
     *
     * <p>
     * This behavior of this flag is implied whenever neither {@link #VIRTUAL_DISPLAY_FLAG_PUBLIC}
     * nor {@link #VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR} have been set.  This flag is only required to
     * override the default behavior when creating a public display.
     * </p>
     *
     * @see #createVirtualDisplay
     */
    public static final int VIRTUAL_DISPLAY_FLAG_OWN_CONTENT_ONLY = 8;

    /**
     * Virtual display flag: Create a presentation display.
     *
     * <h3>Presentation virtual displays</h3>
     * <p>
     * When this flag is set, the virtual display is registered as a presentation
     * display in the {@link #DISPLAY_CATEGORY_PRESENTATION presentation display category}.
     * Applications may automatically project their content to presentation displays
     * to provide richer second screen experiences.
     * </p>
     *
     * <h3>Non-presentation virtual displays</h3>
     * <p>
     * When this flag is not set, the virtual display is not registered as a presentation
     * display.  Applications can still project their content on the display but they
     * will typically not do so automatically.  This option is appropriate for
     * more special-purpose displays.
     * </p>
     *
     * @see android.app.Presentation
     * @see #createVirtualDisplay
     * @see #DISPLAY_CATEGORY_PRESENTATION
     * @see Display#FLAG_PRESENTATION
     */
    public static final int VIRTUAL_DISPLAY_FLAG_PRESENTATION = 2;

    /**
     * Virtual display flag: Create a public display.
     *
     * <h3>Public virtual displays</h3>
     * <p>
     * When this flag is set, the virtual display is public.
     * </p><p>
     * A public virtual display behaves just like most any other display that is connected
     * to the system such as an external or wireless display.  Applications can open
     * windows on the display and the system may mirror the contents of other displays
     * onto it.
     * </p><p>
     * Creating a public virtual display that isn't restricted to own-content only implicitly
     * creates an auto-mirroring display. See {@link #VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR} for
     * restrictions on who is allowed to create an auto-mirroring display.
     * </p>
     *
     * <h3>Private virtual displays</h3>
     * <p>
     * When this flag is not set, the virtual display is private as defined by the
     * {@link Display#FLAG_PRIVATE} display flag.
     * </p>
     *
     * <p>
     * A private virtual display belongs to the application that created it.  Only the a owner of a
     * private virtual display and the apps that are already on that display are allowed to place
     * windows upon it.  The private virtual display also does not participate in display mirroring:
     * it will neither receive mirrored content from another display nor allow its own content to be
     * mirrored elsewhere.  More precisely, the only processes that are allowed to enumerate or
     * interact with the private display are those that have the same UID as the application that
     * originally created the private virtual display or as the activities that are already on that
     * display.
     * </p>
     *
     * @see #createVirtualDisplay
     * @see #VIRTUAL_DISPLAY_FLAG_OWN_CONTENT_ONLY
     * @see #VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR
     */
    public static final int VIRTUAL_DISPLAY_FLAG_PUBLIC = 1;

    /**
     * Virtual display flag: Create a secure display.
     *
     * <h3>Secure virtual displays</h3>
     * <p>
     * When this flag is set, the virtual display is considered secure as defined
     * by the {@link Display#FLAG_SECURE} display flag.  The caller promises to take
     * reasonable measures, such as over-the-air encryption, to prevent the contents
     * of the display from being intercepted or recorded on a persistent medium.
     * </p><p>
     * Creating a secure virtual display requires the CAPTURE_SECURE_VIDEO_OUTPUT permission.
     * This permission is reserved for use by system components and is not available to
     * third-party applications.
     * </p>
     *
     * <h3>Non-secure virtual displays</h3>
     * <p>
     * When this flag is not set, the virtual display is considered unsecure.
     * The content of secure windows will be blanked if shown on this display.
     * </p>
     *
     * @see Display#FLAG_SECURE
     * @see #createVirtualDisplay
     */
    public static final int VIRTUAL_DISPLAY_FLAG_SECURE = 4;

    private DisplayManager() {
    }
}
