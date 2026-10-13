package android.location;

/**
 * Dynamically specifies the summary (subtitle) and enabled status of a preference injected into
 * the list of app settings displayed by the system settings app
 * <p/>
 * For use only by apps that are included in the system image, for preferences that affect multiple
 * apps. Location settings that apply only to one app should be shown within that app,
 * rather than in the system settings.
 * <p/>
 * To add a preference to the list, a subclass of {@link SettingInjectorService} must be declared in
 * the manifest as so:
 *
 * <pre>
 *     &lt;service android:name="com.example.android.injector.MyInjectorService" &gt;
 *         &lt;intent-filter&gt;
 *             &lt;action android:name="android.location.SettingInjectorService" /&gt;
 *         &lt;/intent-filter&gt;
 *
 *         &lt;meta-data
 *             android:name="android.location.SettingInjectorService"
 *             android:resource="@xml/my_injected_location_setting" /&gt;
 *     &lt;/service&gt;
 * </pre>
 * The resource file specifies the static data for the setting:
 * <pre>
 *     &lt;injected-location-setting xmlns:android="http://schemas.android.com/apk/res/android"
 *         android:title="@string/injected_setting_title"
 *         android:icon="@drawable/ic_acme_corp"
 *         android:settingsActivity="com.example.android.injector.MySettingActivity"
 *     /&gt;
 * </pre>
 * Here:
 * <ul>
 * <li>title: The {@link android.preference.Preference#getTitle()} value. The title should make
 * it clear which apps are affected by the setting, typically by including the name of the
 * developer. For example, "Acme Corp. ads preferences." </li>
 *
 * <li>icon: The {@link android.preference.Preference#getIcon()} value. Typically this will be a
 * generic icon for the developer rather than the icon for an individual app.</li>
 *
 * <li>settingsActivity: the activity which is launched to allow the user to modify the setting
 * value.  The activity must be in the same package as the subclass of
 * {@link SettingInjectorService}. The activity should use your own branding to help emphasize
 * to the user that it is not part of the system settings.</li>
 * </ul>
 *
 * To ensure a good user experience, your {@link android.app.Application#onCreate()},
 * {@link #onGetSummary()}, and {@link #onGetEnabled()} methods must all be fast. If any are slow,
 * it can delay the display of settings values for other apps as well. Note further that all are
 * called on your app's UI thread.
 * <p/>
 * For compactness, only one copy of a given setting should be injected. If each account has a
 * distinct value for the setting, then the {@link #onGetSummary()} value should represent a summary
 * of the state across all of the accounts and {@code settingsActivity} should display the value for
 * each account.
 */
public final class SettingInjectorService {
    /**
     * Intent action a client should broadcast when the value of one of its injected settings has
     * changed, so that the setting can be updated in the UI.
     */
    public static final String ACTION_INJECTED_SETTING_CHANGED = "android.location.InjectedSettingChanged";

    /**
     * Intent action that must be declared in the manifest for the subclass. Used to start the
     * service to read the dynamic status for the setting.
     */
    public static final String ACTION_SERVICE_INTENT = "android.location.SettingInjectorService";

    /**
     * Name of the XML tag that includes the attributes for the setting.
     */
    public static final String ATTRIBUTES_NAME = "injected-location-setting";

    /**
     * Name of the meta-data tag used to specify the resource file that includes the settings
     * attributes.
     */
    public static final String META_DATA_NAME = "android.location.SettingInjectorService";

    private SettingInjectorService() {
    }
}
