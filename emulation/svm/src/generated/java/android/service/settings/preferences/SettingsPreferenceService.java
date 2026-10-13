package android.service.settings.preferences;

/**
 * Base class for a service that exposes its settings preferences to external access.
 * <p>This class is to be implemented by apps that contribute to the Android Settings surface.
 * Access to this service is permission guarded by
 * {@link android.permission.READ_SYSTEM_PREFERENCES} for binding and reading, and guarded by both
 * {@link android.permission.READ_SYSTEM_PREFERENCES} and
 * {@link android.permission.WRITE_SYSTEM_PREFERENCES} for writing. An additional checks for access
 * control are the responsibility of the implementing class.
 *
 * <p>This implementation must correspond to an exported service declaration in the host app
 * AndroidManifest.xml as follows
 * <pre class="prettyprint">
 * {@literal
 * <service
 *     android:permission="android.permission.READ_SYSTEM_PREFERENCES"
 *     android:exported="true">
 *     <intent-filter>
 *         <action android:name="android.service.settings.preferences.action.PREFERENCE_SERVICE" />
 *     </intent-filter>
 * </service>}
 * </pre>
 *
 * <ul>
 *   <li>It is recommended to expose the metadata for most, if not all, preferences within a
 *   settings app, thus implementing {@link #onGetAllPreferenceMetadata}.
 *   <li>Exposing preferences for read access of their values is up to the implementer, but any
 *   exposed must be a subset of the preferences exposed in {@link #onGetAllPreferenceMetadata}.
 *   To expose a preference for read access, the implementation will contain
 *   {@link #onGetPreferenceValue}.
 *   <li>Exposing a preference for write access of their values is up to the implementer, but should
 *   be done so with extra care and consideration, both for security and privacy. These must also
 *   be a subset of those exposed in {@link #onGetAllPreferenceMetadata}. To expose a preference for
 *   write access, the implementation will contain {@link #onSetPreferenceValue}.
 * </ul>
 */
public final class SettingsPreferenceService {
    /**
     * Intent Action corresponding to a {@link SettingsPreferenceService}. Note that any checks for
     * such services must be accompanied by a check to ensure the host is a system application.
     * Given an {@link android.content.pm.ApplicationInfo} you can check for
     * {@link android.content.pm.ApplicationInfo#FLAG_SYSTEM}, or when querying
     * {@link PackageManager#queryIntentServices} you can provide the flag
     * {@link PackageManager#MATCH_SYSTEM_ONLY}.
     */
    public static final String ACTION_PREFERENCE_SERVICE = "android.service.settings.preferences.action.PREFERENCE_SERVICE";

    private SettingsPreferenceService() {
    }
}
