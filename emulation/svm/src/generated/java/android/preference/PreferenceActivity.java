package android.preference;

/**
 * This is the base class for an activity to show a hierarchy of preferences
 * to the user.  Prior to {@link android.os.Build.VERSION_CODES#HONEYCOMB}
 * this class only allowed the display of a single set of preference; this
 * functionality should now be found in the new {@link PreferenceFragment}
 * class.  If you are using PreferenceActivity in its old mode, the documentation
 * there applies to the deprecated APIs here.
 *
 * <p>This activity shows one or more headers of preferences, each of which
 * is associated with a {@link PreferenceFragment} to display the preferences
 * of that header.  The actual layout and display of these associations can
 * however vary; currently there are two major approaches it may take:
 *
 * <ul>
 * <li>On a small screen it may display only the headers as a single list when first launched.
 * Selecting one of the header items will only show the PreferenceFragment of that header (on
 * Android N and lower a new Activity is launched).
 * <li>On a large screen it may display both the headers and current PreferenceFragment together as
 * panes. Selecting a header item switches to showing the correct PreferenceFragment for that item.
 * </ul>
 *
 * <p>Subclasses of PreferenceActivity should implement
 * {@link #onBuildHeaders} to populate the header list with the desired
 * items.  Doing this implicitly switches the class into its new "headers
 * + fragments" mode rather than the old style of just showing a single
 * preferences list.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For information about using {@code PreferenceActivity},
 * read the <a href="{@docRoot}guide/topics/ui/settings.html">Settings</a>
 * guide.</p>
 * </div>
 *
 * @deprecated Use the <a href="{@docRoot}jetpack/androidx.html">AndroidX</a>
 *      <a href="{@docRoot}reference/androidx/preference/package-summary.html">
 *      Preference Library</a> for consistent behavior across all devices. For more information on
 *      using the AndroidX Preference Library see
 *      <a href="{@docRoot}guide/topics/ui/settings.html">Settings</a>.
 */
@Deprecated
public final class PreferenceActivity {
    /**
     * When starting this activity, the invoking Intent can contain this extra
     * boolean that the header list should not be displayed.  This is most often
     * used in conjunction with {@link #EXTRA_SHOW_FRAGMENT} to launch
     * the activity to display a specific fragment that the user has navigated
     * to.
     */
    @Deprecated
    public static final String EXTRA_NO_HEADERS = ":android:no_headers";

    /**
     * When starting this activity, the invoking Intent can contain this extra
     * string to specify which fragment should be initially displayed.
     * <p/>Starting from Key Lime Pie, when this argument is passed in, the PreferenceActivity
     * will call isValidFragment() to confirm that the fragment class name is valid for this
     * activity.
     */
    @Deprecated
    public static final String EXTRA_SHOW_FRAGMENT = ":android:show_fragment";

    /**
     * When starting this activity and using {@link #EXTRA_SHOW_FRAGMENT},
     * this extra can also be specified to supply a Bundle of arguments to pass
     * to that fragment when it is instantiated during the initial creation
     * of PreferenceActivity.
     */
    @Deprecated
    public static final String EXTRA_SHOW_FRAGMENT_ARGUMENTS = ":android:show_fragment_args";

    /**
     * When starting this activity and using {@link #EXTRA_SHOW_FRAGMENT},
     * this extra can also be specify to supply the short title to be shown for
     * that fragment.
     */
    @Deprecated
    public static final String EXTRA_SHOW_FRAGMENT_SHORT_TITLE = ":android:show_fragment_short_title";

    /**
     * When starting this activity and using {@link #EXTRA_SHOW_FRAGMENT},
     * this extra can also be specify to supply the title to be shown for
     * that fragment.
     */
    @Deprecated
    public static final String EXTRA_SHOW_FRAGMENT_TITLE = ":android:show_fragment_title";

    /**
     * Default value for {@link Header#id Header.id} indicating that no
     * identifier value is set.  All other values (including those below -1)
     * are valid.
     */
    @Deprecated
    public static final long HEADER_ID_UNDEFINED = -1L;

    private PreferenceActivity() {
    }
}
