package android.nfc.cardemulation;

/**
 * <p>HostApduService is a convenience {@link Service} class that can be
 * extended to emulate an NFC card inside an Android
 * service component.
 *
 * <div class="special reference">
 * <h3>Developer Guide</h3>
 * For a general introduction to card emulation, see
 * <a href="{@docRoot}guide/topics/connectivity/nfc/hce.html">
 * Host-based Card Emulation</a>.</p>
 * </div>
 *
 * <h3>NFC Protocols</h3>
 * <p>Cards emulated by this class are based on the NFC-Forum ISO-DEP
 * protocol (based on ISO/IEC 14443-4) and support processing
 * command Application Protocol Data Units (APDUs) as
 * defined in the ISO/IEC 7816-4 specification.
 *
 * <h3>Service selection</h3>
 * <p>When a remote NFC device wants to talk to your
 * service, it sends a so-called
 * "SELECT AID" APDU as defined in the ISO/IEC 7816-4 specification.
 * The AID is an application identifier defined in ISO/IEC 7816-4.
 *
 * <p>The registration procedure for AIDs is defined in the
 * ISO/IEC 7816-5 specification. If you don't want to register an
 * AID, you are free to use AIDs in the proprietary range:
 * bits 8-5 of the first byte must each be set to '1'. For example,
 * "0xF00102030405" is a proprietary AID. If you do use proprietary
 * AIDs, it is recommended to choose an AID of at least 6 bytes,
 * to reduce the risk of collisions with other applications that
 * might be using proprietary AIDs as well.
 *
 * <h3>AID groups</h3>
 * <p>In some cases, a service may need to register multiple AIDs
 * to implement a certain application, and it needs to be sure
 * that it is the default handler for all of these AIDs (as opposed
 * to some AIDs in the group going to another service).
 *
 * <p>An AID group is a list of AIDs that should be considered as
 * belonging together by the OS. For all AIDs in an AID group, the
 * OS will guarantee one of the following:
 * <ul>
 * <li>All AIDs in the group are routed to this service
 * <li>No AIDs in the group are routed to this service
 * </ul>
 * In other words, there is no in-between state, where some AIDs
 * in the group can be routed to this service, and some to another.
 * <h3>AID groups and categories</h3>
 * <p>Each AID group can be associated with a category. This allows
 * the Android OS to classify services, and it allows the user to
 * set defaults at the category level instead of the AID level.
 *
 * <p>You can use
 * {@link CardEmulation#isDefaultServiceForCategory(android.content.ComponentName, String)}
 * to determine if your service is the default handler for a category.
 *
 * <p>In this version of the platform, the only known categories
 * are {@link CardEmulation#CATEGORY_PAYMENT} and {@link CardEmulation#CATEGORY_OTHER}.
 * AID groups without a category, or with a category that is not recognized
 * by the current platform version, will automatically be
 * grouped into the {@link CardEmulation#CATEGORY_OTHER} category.
 * <h3>Service AID registration</h3>
 * <p>To tell the platform which AIDs groups
 * are requested by this service, a {@link #SERVICE_META_DATA}
 * entry must be included in the declaration of the service. An
 * example of a HostApduService manifest declaration is shown below:
 * <pre> &lt;service android:name=".MyHostApduService" android:exported="true" android:permission="android.permission.BIND_NFC_SERVICE"&gt;
 *     &lt;intent-filter&gt;
 *         &lt;action android:name="android.nfc.cardemulation.action.HOST_APDU_SERVICE"/&gt;
 *     &lt;/intent-filter&gt;
 *     &lt;meta-data android:name="android.nfc.cardemulation.host_apdu_service" android:resource="@xml/apduservice"/&gt;
 * &lt;/service&gt;</pre>
 *
 * This meta-data tag points to an apduservice.xml file.
 * An example of this file with a single AID group declaration is shown below:
 * <pre>
 * &lt;host-apdu-service xmlns:android="http://schemas.android.com/apk/res/android"
 *           android:description="@string/servicedesc" android:requireDeviceUnlock="false"&gt;
 *       &lt;aid-group android:description="@string/aiddescription" android:category="other">
 *           &lt;aid-filter android:name="F0010203040506"/&gt;
 *           &lt;aid-filter android:name="F0394148148100"/&gt;
 *       &lt;/aid-group&gt;
 * &lt;/host-apdu-service&gt;
 * </pre>
 *
 * <p>The {@link android.R.styleable#HostApduService &lt;host-apdu-service&gt;} is required
 * to contain a
 * {@link android.R.styleable#HostApduService_description &lt;android:description&gt;}
 * attribute that contains a user-friendly description of the service that may be shown in UI.
 * The
 * {@link android.R.styleable#HostApduService_requireDeviceUnlock &lt;requireDeviceUnlock&gt;}
 * attribute can be used to specify that the device must be unlocked before this service
 * can be invoked to handle APDUs.
 * <p>The {@link android.R.styleable#HostApduService &lt;host-apdu-service&gt;} must
 * contain one or more {@link android.R.styleable#AidGroup &lt;aid-group&gt;} tags.
 * Each {@link android.R.styleable#AidGroup &lt;aid-group&gt;} must contain one or
 * more {@link android.R.styleable#AidFilter &lt;aid-filter&gt;} tags, each of which
 * contains a single AID. The AID must be specified in hexadecimal format, and contain
 * an even number of characters.
 * <h3>AID conflict resolution</h3>
 * Multiple HostApduServices may be installed on a single device, and the same AID
 * can be registered by more than one service. The Android platform resolves AID
 * conflicts depending on which category an AID belongs to. Each category may
 * have a different conflict resolution policy. For example, for some categories
 * the user may be able to select a default service in the Android settings UI.
 * For other categories, to policy may be to always ask the user which service
 * is to be invoked in case of conflict.
 *
 * To query the conflict resolution policy for a certain category, see
 * {@link CardEmulation#getSelectionModeForCategory(String)}.
 *
 * <h3>Data exchange</h3>
 * <p>Once the platform has resolved a "SELECT AID" command APDU to a specific
 * service component, the "SELECT AID" command APDU and all subsequent
 * command APDUs will be sent to that service through
 * {@link #processCommandApdu(byte[], Bundle)}, until either:
 * <ul>
 * <li>The NFC link is broken</li>
 * <li>A "SELECT AID" APDU is received which resolves to another service</li>
 * </ul>
 * These two scenarios are indicated by a call to {@link #onDeactivated(int)}.
 *
 * <p class="note">Use of this class requires the
 * {@link PackageManager#FEATURE_NFC_HOST_CARD_EMULATION} to be present
 * on the device.
 */
public final class HostApduService {
    /**
     * Reason for {@link #onDeactivated(int)}.
     *
     * <p>Indicates deactivation was due to a different AID
     * being selected (which implicitly deselects the AID
     * currently active on the logical channel).
     *
     * <p>Note that this next AID may still be resolved to this
     * service, in which case {@link #processCommandApdu(byte[], Bundle)}
     * will be called again.
     */
    public static final int DEACTIVATION_DESELECTED = 1;

    /**
     * Reason for {@link #onDeactivated(int)}.
     * Indicates deactivation was due to the NFC link
     * being lost.
     */
    public static final int DEACTIVATION_LINK_LOSS = 0;

    /**
     * The {@link Intent} action that must be declared as handled by the service.
     */
    public static final String SERVICE_INTERFACE = "android.nfc.cardemulation.action.HOST_APDU_SERVICE";

    /**
     * The name of the meta-data element that contains
     * more information about this service.
     */
    public static final String SERVICE_META_DATA = "android.nfc.cardemulation.host_apdu_service";

    private HostApduService() {
    }
}
