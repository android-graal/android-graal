package android.app;

/**
 * A primary toolbar within the activity that may display the activity title, application-level
 * navigation affordances, and other interactive items.
 *
 * <p>Beginning with Android 3.0 (API level 11), the action bar appears at the top of an
 * activity's window when the activity uses the system's {@link
 * android.R.style#Theme_Holo Holo} theme (or one of its descendant themes), which is the default.
 * You may otherwise add the action bar by calling {@link
 * android.view.Window#requestFeature requestFeature(FEATURE_ACTION_BAR)} or by declaring it in a
 * custom theme with the {@link android.R.styleable#Theme_windowActionBar windowActionBar} property.
 * </p>
 *
 * <p>Beginning with Android L (API level 21), the action bar may be represented by any
 * Toolbar widget within the application layout. The application may signal to the Activity
 * which Toolbar should be treated as the Activity's action bar. Activities that use this
 * feature should use one of the supplied <code>.NoActionBar</code> themes, set the
 * {@link android.R.styleable#Theme_windowActionBar windowActionBar} attribute to <code>false</code>
 * or otherwise not request the window feature.</p>
 *
 * <p>By adjusting the window features requested by the theme and the layouts used for
 * an Activity's content view, an app can use the standard system action bar on older platform
 * releases and the newer inline toolbars on newer platform releases. The <code>ActionBar</code>
 * object obtained from the Activity can be used to control either configuration transparently.</p>
 *
 * <p>When using the Holo themes the action bar shows the application icon on
 * the left, followed by the activity title. If your activity has an options menu, you can make
 * select items accessible directly from the action bar as "action items". You can also
 * modify various characteristics of the action bar or remove it completely.</p>
 *
 * <p>When using the Material themes (default in API 21 or newer) the navigation button
 * (formerly "Home") takes over the space previously occupied by the application icon.
 * Apps wishing to express a stronger branding should use their brand colors heavily
 * in the action bar and other application chrome or use a {@link #setLogo(int) logo}
 * in place of their standard title text.</p>
 *
 * <p>From your activity, you can retrieve an instance of {@link ActionBar} by calling {@link
 * android.app.Activity#getActionBar getActionBar()}.</p>
 *
 * <p>In some cases, the action bar may be overlayed by another bar that enables contextual actions,
 * using an {@link android.view.ActionMode}. For example, when the user selects one or more items in
 * your activity, you can enable an action mode that offers actions specific to the selected
 * items, with a UI that temporarily replaces the action bar. Although the UI may occupy the
 * same space, the {@link android.view.ActionMode} APIs are distinct and independent from those for
 * {@link ActionBar}.</p>
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For information about how to use the action bar, including how to add action items, navigation
 * modes and more, read the <a href="{@docRoot}guide/topics/ui/actionbar.html">Action
 * Bar</a> developer guide.</p>
 * </div>
 */
public final class ActionBar {
    /**
     * Display the 'home' element such that it appears as an 'up' affordance.
     * e.g. show an arrow to the left indicating the action that will be taken.
     *
     * Set this flag if selecting the 'home' button in the action bar to return
     * up by a single level in your UI rather than back to the top level or front page.
     *
     * <p>Setting this option will implicitly enable interaction with the home/up
     * button. See {@link #setHomeButtonEnabled(boolean)}.
     *
     * @see #setDisplayOptions(int)
     * @see #setDisplayOptions(int, int)
     */
    public static final int DISPLAY_HOME_AS_UP = 4;

    /**
     * Show the custom view if one has been set.
     * @see #setCustomView(View)
     * @see #setDisplayOptions(int)
     * @see #setDisplayOptions(int, int)
     */
    public static final int DISPLAY_SHOW_CUSTOM = 16;

    /**
     * Show 'home' elements in this action bar, leaving more space for other
     * navigation elements. This includes logo and icon.
     *
     * @see #setDisplayOptions(int)
     * @see #setDisplayOptions(int, int)
     */
    public static final int DISPLAY_SHOW_HOME = 2;

    /**
     * Show the activity title and subtitle, if present.
     *
     * @see #setTitle(CharSequence)
     * @see #setTitle(int)
     * @see #setSubtitle(CharSequence)
     * @see #setSubtitle(int)
     * @see #setDisplayOptions(int)
     * @see #setDisplayOptions(int, int)
     */
    public static final int DISPLAY_SHOW_TITLE = 8;

    /**
     * Use logo instead of icon if available. This flag will cause appropriate
     * navigation modes to use a wider logo in place of the standard icon.
     *
     * @see #setDisplayOptions(int)
     * @see #setDisplayOptions(int, int)
     */
    public static final int DISPLAY_USE_LOGO = 1;

    /**
     * List navigation mode. Instead of static title text this mode
     * presents a list menu for navigation within the activity.
     * e.g. this might be presented to the user as a dropdown list.
     *
     * @deprecated Action bar navigation modes are deprecated and not supported by inline
     * toolbar action bars. Consider using other
     * <a href="http://developer.android.com/design/patterns/navigation.html">common
     * navigation patterns</a> instead.
     */
    @Deprecated
    public static final int NAVIGATION_MODE_LIST = 1;

    /**
     * Standard navigation mode. Consists of either a logo or icon
     * and title text with an optional subtitle. Clicking any of these elements
     * will dispatch onOptionsItemSelected to the host Activity with
     * a MenuItem with item ID android.R.id.home.
     *
     * @deprecated Action bar navigation modes are deprecated and not supported by inline
     * toolbar action bars. Consider using other
     * <a href="http://developer.android.com/design/patterns/navigation.html">common
     * navigation patterns</a> instead.
     */
    @Deprecated
    public static final int NAVIGATION_MODE_STANDARD = 0;

    /**
     * Tab navigation mode. Instead of static title text this mode
     * presents a series of tabs for navigation within the activity.
     *
     * @deprecated Action bar navigation modes are deprecated and not supported by inline
     * toolbar action bars. Consider using other
     * <a href="http://developer.android.com/design/patterns/navigation.html">common
     * navigation patterns</a> instead.
     */
    @Deprecated
    public static final int NAVIGATION_MODE_TABS = 2;

    private ActionBar() {
    }

    /**
     * A tab in the action bar.
     *
     * <p>Tabs manage the hiding and showing of {@link Fragment}s.
     *
     * @deprecated Action bar navigation modes are deprecated and not supported by inline
     * toolbar action bars. Consider using other
     * <a href="http://developer.android.com/design/patterns/navigation.html">common
     * navigation patterns</a> instead.
     */
    @Deprecated
    public static final class Tab {
        /**
         * An invalid position for a tab.
         *
         * @see #getPosition()
         */
        @Deprecated
        public static final int INVALID_POSITION = -1;

        private Tab() {
        }
    }
}
