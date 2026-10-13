package android.provider;

/**
 * Provides a contract for platform-supported Settings {@link android.app.slice.Slice Slices}.
 * <p>
 * Contains definitions for the supported {@link android.app.slice.SliceProvider SliceProvider}
 * authority, authority {@link Uri}, and key constants.
 * <p>
 * {@link android.app.slice.Slice Slice} presenters interested in learning meta-data about the
 * {@link android.app.slice.Slice Slice} should read the {@link android.app.slice.Slice Slice}
 * object at runtime.
 * <p>
 * {@link Uri} builder example:
 * <pre>
 * Uri wifiActionUri = BASE_URI
 *         .buildUpon()
 *         .appendPath(PATH_SETTING_ACTION)
 *         .appendPath(KEY_WIFI)
 *         .build();
 * Uri bluetoothIntentUri = BASE_URI
 *         .buildUpon()
 *         .appendPath(PATH_SETTING_INTENT)
 *         .appendPath(KEY_BLUETOOTH)
 *         .build();
 * </pre>
 */
public final class SettingsSlicesContract {
    /**
     * Authority for platform Settings Slices.
     */
    public static final String AUTHORITY = "android.settings.slices";

    /**
     * {@link Uri} key for the Airplane Mode setting.
     */
    public static final String KEY_AIRPLANE_MODE = "airplane_mode";

    /**
     * {@link Uri} key for the Battery Saver setting.
     */
    public static final String KEY_BATTERY_SAVER = "battery_saver";

    /**
     * {@link Uri} key for the Bluetooth setting.
     */
    public static final String KEY_BLUETOOTH = "bluetooth";

    /**
     * {@link Uri} key for the Location setting.
     */
    public static final String KEY_LOCATION = "location";

    /**
     * {@link Uri} key for the Wi-fi setting.
     */
    public static final String KEY_WIFI = "wifi";

    /**
     * {@link Uri} path indicating that the requested {@link android.app.slice.Slice Slice} should
     * have inline controls for the corresponding setting.
     * <p>
     * This path will only contain Slices defined by keys in this class.
     */
    public static final String PATH_SETTING_ACTION = "action";

    /**
     * {@link Uri} path indicating that the requested {@link android.app.slice.Slice Slice} should
     * be {@link android.content.Intent Intent}-only.
     * <p>
     * {@link android.app.slice.Slice Slices} with actions should use the {@link
     * #PATH_SETTING_ACTION} path.
     * <p>
     * This path will only contain Slices defined by keys in this class
     */
    public static final String PATH_SETTING_INTENT = "intent";

    private SettingsSlicesContract() {
    }
}
