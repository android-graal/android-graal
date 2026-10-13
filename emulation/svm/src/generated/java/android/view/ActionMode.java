package android.view;

/**
 * Represents a contextual mode of the user interface. Action modes can be used to provide
 * alternative interaction modes and replace parts of the normal UI until finished.
 * Examples of good action modes include text selection and contextual actions.
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For information about how to provide contextual actions with {@code ActionMode},
 * read the <a href="{@docRoot}guide/topics/ui/menus.html#context-menu">Menus</a>
 * developer guide.</p>
 * </div>
 */
public final class ActionMode {
    /**
     * Default value to hide the action mode for
     * {@link ViewConfiguration#getDefaultActionModeHideDuration()}.
     */
    public static final int DEFAULT_HIDE_DURATION = -1;

    /**
     * The action mode is treated as a Floating Toolbar.
     * Use with {@link #setType}.
     */
    public static final int TYPE_FLOATING = 1;

    /**
     * The action mode is treated as a Primary mode. This is the default.
     * Use with {@link #setType}.
     */
    public static final int TYPE_PRIMARY = 0;

    private ActionMode() {
    }
}
