package android.nfc.cardemulation;

/**
 * This class can be used to query the state of
 * NFC card emulation services.
 *
 * For a general introduction into NFC card emulation,
 * please read the <a href="{@docRoot}guide/topics/connectivity/nfc/hce.html">
 * NFC card emulation developer guide</a>.</p>
 *
 * <p class="note">Use of this class requires the
 * {@link PackageManager#FEATURE_NFC_HOST_CARD_EMULATION} to be present
 * on the device.
 */
public final class CardEmulation {
    /**
     * Activity action: ask the user to change the default
     * card emulation service for a certain category. This will
     * show a dialog that asks the user whether they want to
     * replace the current default service with the service
     * identified with the ComponentName specified in
     * {@link #EXTRA_SERVICE_COMPONENT}, for the category
     * specified in {@link #EXTRA_CATEGORY}. There is an optional
     * extra field using {@link Intent#EXTRA_USER} to specify
     * the {@link UserHandle} of the user that owns the app.
     *
     * @deprecated Please use {@link android.app.role.RoleManager#createRequestRoleIntent(String)}
     * with {@link android.app.role.RoleManager#ROLE_WALLET} parameter
     * and {@link Activity#startActivityForResult(Intent, int)} instead.
     */
    @Deprecated
    public static final String ACTION_CHANGE_DEFAULT = "android.nfc.cardemulation.action.ACTION_CHANGE_DEFAULT";

    /**
     * Category that can be used for all other card emulation
     * services.
     */
    public static final String CATEGORY_OTHER = "other";

    /**
     * Category used for NFC payment services.
     */
    public static final String CATEGORY_PAYMENT = "payment";

    /**
     * The category extra for {@link #ACTION_CHANGE_DEFAULT}.
     *
     * @see #ACTION_CHANGE_DEFAULT
     */
    public static final String EXTRA_CATEGORY = "category";

    /**
     * The service {@link ComponentName} object passed in as an
     * extra for {@link #ACTION_CHANGE_DEFAULT}.
     *
     * @see #ACTION_CHANGE_DEFAULT
     */
    public static final String EXTRA_SERVICE_COMPONENT = "component";

    /**
     * This error is reported when the NFC stack times out while waiting for a response to a command
     * sent to the NFC hardware.
     */
    public static final int NFC_INTERNAL_ERROR_COMMAND_TIMEOUT = 3;

    /**
     * This error is reported when the NFC command watchdog restarts the NFC stack.
     */
    public static final int NFC_INTERNAL_ERROR_NFC_CRASH_RESTART = 1;

    /**
     * This error is reported when the NFC controller does not respond or there's an NCI transport
     * error.
     */
    public static final int NFC_INTERNAL_ERROR_NFC_HARDWARE_ERROR = 2;

    public static final int NFC_INTERNAL_ERROR_UNKNOWN = 0;

    /**
     * Property name used to indicate that an application wants to allow associated services
     * to share the same AID routing priority when this application is the role holder.
     * <p>
     * Example:
     * <pre>
     *     {@code
     *     <application>
     *       ...
     *       <property android:name="android.nfc.cardemulation.PROPERTY_ALLOW_SHARED_ROLE_PRIORITY"
     *         android:value="true"/>
     *     </application>
     *     }
     * </pre>
     */
    public static final String PROPERTY_ALLOW_SHARED_ROLE_PRIORITY = "android.nfc.cardemulation.PROPERTY_ALLOW_SHARED_ROLE_PRIORITY";

    /**
     * Route to the default value in config file.
     */
    public static final int PROTOCOL_AND_TECHNOLOGY_ROUTE_DEFAULT = 3;

    /**
     * Route to Device Host (DH).
     */
    public static final int PROTOCOL_AND_TECHNOLOGY_ROUTE_DH = 0;

    /**
     * Route to eSE.
     */
    public static final int PROTOCOL_AND_TECHNOLOGY_ROUTE_ESE = 1;

    /**
     * Route to UICC.
     */
    public static final int PROTOCOL_AND_TECHNOLOGY_ROUTE_UICC = 2;

    /**
     * Route unset.
     */
    public static final int PROTOCOL_AND_TECHNOLOGY_ROUTE_UNSET = -1;

    /**
     * Return value for {@link #getSelectionModeForCategory(String)}.
     *
     * <p>In this mode, when using ISO-DEP card emulation with {@link HostApduService}
     *    or {@link OffHostApduService}, whenever an Application ID (AID) of this category
     *    is selected, the user is asked which service they want to use to handle
     *    the transaction, even if there is only one matching service.
     */
    public static final int SELECTION_MODE_ALWAYS_ASK = 1;

    /**
     * Return value for {@link #getSelectionModeForCategory(String)}.
     *
     * <p>In this mode, when using ISO-DEP card emulation with {@link HostApduService}
     *    or {@link OffHostApduService}, the user will only be asked to select a service
     *    if the Application ID (AID) selected by the reader has been registered by multiple
     *    services. If there is only one service that has registered for the AID,
     *    that service will be invoked directly.
     */
    public static final int SELECTION_MODE_ASK_IF_CONFLICT = 2;

    /**
     * Return value for {@link #getSelectionModeForCategory(String)}.
     *
     * <p>In this mode, the user has set a default service for this
     *    category.
     *
     * <p>When using ISO-DEP card emulation with {@link HostApduService}
     *    or {@link OffHostApduService}, if a remote NFC device selects
     *    any of the Application IDs (AIDs)
     *    that the default service has registered in this category,
     *    that service will automatically be bound to to handle
     *    the transaction.
     */
    public static final int SELECTION_MODE_PREFER_DEFAULT = 0;

    private CardEmulation() {
    }
}
