package android.service.controls.templates;

/**
 * An abstract input template for a {@link Control}.
 *
 * Specifies what layout is presented to the user for a given {@link Control}.
 * <p>
 * Some instances of {@link Control} can originate actions (via user interaction) to modify its
 * associated state. The actions available to a given {@link Control} are determined by its
 * {@link ControlTemplate}.
 * @see ControlAction
 */
public final class ControlTemplate {
    /**
     * Type identifier of the template returned by {@link #getErrorTemplate()}.
     */
    public static final int TYPE_ERROR = -1;

    /**
     * Type identifier of {@link ControlTemplate#getNoTemplateObject}.
     */
    public static final int TYPE_NO_TEMPLATE = 0;

    /**
     * Type identifier of {@link RangeTemplate}.
     */
    public static final int TYPE_RANGE = 2;

    /**
     * Type identifier of {@link StatelessTemplate}.
     */
    public static final int TYPE_STATELESS = 8;

    /**
     * Type identifier of {@link TemperatureControlTemplate}.
     */
    public static final int TYPE_TEMPERATURE = 7;

    /**
     * Type identifier of {@link ThumbnailTemplate}.
     */
    public static final int TYPE_THUMBNAIL = 3;

    /**
     * Type identifier of {@link ToggleTemplate}.
     */
    public static final int TYPE_TOGGLE = 1;

    /**
     * Type identifier of {@link ToggleRangeTemplate}.
     */
    public static final int TYPE_TOGGLE_RANGE = 6;

    private ControlTemplate() {
    }
}
