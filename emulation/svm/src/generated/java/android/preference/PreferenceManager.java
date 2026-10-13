package android.preference;

/**
 * Used to help create {@link Preference} hierarchies
 * from activities or XML.
 * <p>
 * In most cases, clients should use
 * {@link PreferenceActivity#addPreferencesFromIntent} or
 * {@link PreferenceActivity#addPreferencesFromResource(int)}.
 *
 * @see PreferenceActivity
 *
 * @deprecated Use the <a href="{@docRoot}jetpack/androidx.html">AndroidX</a>
 *      <a href="{@docRoot}reference/androidx/preference/package-summary.html">
 *      Preference Library</a> for consistent behavior across all devices. For more information on
 *      using the AndroidX Preference Library see
 *      <a href="{@docRoot}guide/topics/ui/settings.html">Settings</a>.
 */
@Deprecated
public final class PreferenceManager {
    @Deprecated
    public static final String KEY_HAS_SET_DEFAULT_VALUES = "_has_set_default_values";

    /**
     * The Activity meta-data key for its XML preference hierarchy.
     */
    @Deprecated
    public static final String METADATA_KEY_PREFERENCES = "android.preference";

    private PreferenceManager() {
    }
}
