package android.nfc;

/**
 * Represents the local NFC adapter.
 * <p>
 * Use the helper {@link #getDefaultAdapter(Context)} to get the default NFC
 * adapter for this Android device.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about using NFC, read the
 * <a href="{@docRoot}guide/topics/nfc/index.html">Near Field Communication</a> developer guide.</p>
 * <p>To perform basic file sharing between devices, read
 * <a href="{@docRoot}training/beam-files/index.html">Sharing Files with NFC</a>.
 * </div>
 */
public final class NfcAdapter {
    /**
     * Broadcast Action: The state of the local NFC adapter has been
     * changed.
     * <p>For example, NFC has been turned on or off.
     * <p>Always contains the extra field {@link #EXTRA_ADAPTER_STATE}
     */
    public static final String ACTION_ADAPTER_STATE_CHANGED = "android.nfc.action.ADAPTER_STATE_CHANGED";

    /**
     * Activity action: Bring up the settings page that allows the user to enable or disable tag
     * intent reception for apps.
     *
     * <p>This will direct user to the settings page shows a list that asks users whether
     * they want to allow or disallow the package to start an activity when a tag is discovered.
     */
    public static final String ACTION_CHANGE_TAG_INTENT_PREFERENCE = "android.nfc.action.CHANGE_TAG_INTENT_PREFERENCE";

    /**
     * Intent to start an activity when a tag with NDEF payload is discovered.
     *
     * <p>The system inspects the first {@link NdefRecord} in the first {@link NdefMessage} and
     * looks for a URI, SmartPoster, or MIME record. If a URI or SmartPoster record is found the
     * intent will contain the URI in its data field. If a MIME record is found the intent will
     * contain the MIME type in its type field. This allows activities to register
     * {@link IntentFilter}s targeting specific content on tags. Activities should register the
     * most specific intent filters possible to avoid the activity chooser dialog, which can
     * disrupt the interaction with the tag as the user interacts with the screen.
     *
     * <p>If the tag has an NDEF payload this intent is started before
     * {@link #ACTION_TECH_DISCOVERED}. If any activities respond to this intent neither
     * {@link #ACTION_TECH_DISCOVERED} or {@link #ACTION_TAG_DISCOVERED} will be started.
     *
     * <p>The MIME type or data URI of this intent are normalized before dispatch -
     * so that MIME, URI scheme and URI host are always lower-case.
     */
    public static final String ACTION_NDEF_DISCOVERED = "android.nfc.action.NDEF_DISCOVERED";

    /**
     * Broadcast Action: Intent to notify if the preferred payment service changed.
     *
     * <p>This intent will only be sent to the application has requested permission for
     * {@link android.Manifest.permission#NFC_PREFERRED_PAYMENT_INFO} and if the application
     * has the necessary access to Secure Element which witnessed the particular event.
     */
    public static final String ACTION_PREFERRED_PAYMENT_CHANGED = "android.nfc.action.PREFERRED_PAYMENT_CHANGED";

    /**
     * Intent to start an activity when a tag is discovered.
     *
     * <p>This intent will not be started when a tag is discovered if any activities respond to
     * {@link #ACTION_NDEF_DISCOVERED} or {@link #ACTION_TECH_DISCOVERED} for the current tag.
     */
    public static final String ACTION_TAG_DISCOVERED = "android.nfc.action.TAG_DISCOVERED";

    /**
     * Intent to start an activity when a tag is discovered and activities are registered for the
     * specific technologies on the tag.
     *
     * <p>To receive this intent an activity must include an intent filter
     * for this action and specify the desired tech types in a
     * manifest <code>meta-data</code> entry. Here is an example manfiest entry:
     * <pre>
     * &lt;activity android:name=".nfc.TechFilter" android:label="NFC/TechFilter"&gt;
     *     &lt;!-- Add a technology filter --&gt;
     *     &lt;intent-filter&gt;
     *         &lt;action android:name="android.nfc.action.TECH_DISCOVERED" /&gt;
     *     &lt;/intent-filter&gt;
     *
     *     &lt;meta-data android:name="android.nfc.action.TECH_DISCOVERED"
     *         android:resource="@xml/filter_nfc"
     *     /&gt;
     * &lt;/activity&gt;</pre>
     *
     * <p>The meta-data XML file should contain one or more <code>tech-list</code> entries
     * each consisting or one or more <code>tech</code> entries. The <code>tech</code> entries refer
     * to the qualified class name implementing the technology, for example "android.nfc.tech.NfcA".
     *
     * <p>A tag matches if any of the
     * <code>tech-list</code> sets is a subset of {@link Tag#getTechList() Tag.getTechList()}. Each
     * of the <code>tech-list</code>s is considered independently and the
     * activity is considered a match is any single <code>tech-list</code> matches the tag that was
     * discovered. This provides AND and OR semantics for filtering desired techs. Here is an
     * example that will match any tag using {@link NfcF} or any tag using {@link NfcA},
     * {@link MifareClassic}, and {@link Ndef}:
     *
     * <pre>
     * &lt;resources xmlns:xliff="urn:oasis:names:tc:xliff:document:1.2"&gt;
     *     &lt;!-- capture anything using NfcF --&gt;
     *     &lt;tech-list&gt;
     *         &lt;tech&gt;android.nfc.tech.NfcF&lt;/tech&gt;
     *     &lt;/tech-list&gt;
     *
     *     &lt;!-- OR --&gt;
     *
     *     &lt;!-- capture all MIFARE Classics with NDEF payloads --&gt;
     *     &lt;tech-list&gt;
     *         &lt;tech&gt;android.nfc.tech.NfcA&lt;/tech&gt;
     *         &lt;tech&gt;android.nfc.tech.MifareClassic&lt;/tech&gt;
     *         &lt;tech&gt;android.nfc.tech.Ndef&lt;/tech&gt;
     *     &lt;/tech-list&gt;
     * &lt;/resources&gt;</pre>
     *
     * <p>This intent is started after {@link #ACTION_NDEF_DISCOVERED} and before
     * {@link #ACTION_TAG_DISCOVERED}. If any activities respond to {@link #ACTION_NDEF_DISCOVERED}
     * this intent will not be started. If any activities respond to this intent
     * {@link #ACTION_TAG_DISCOVERED} will not be started.
     */
    public static final String ACTION_TECH_DISCOVERED = "android.nfc.action.TECH_DISCOVERED";

    /**
     * Broadcast Action: Intent to notify an application that a transaction event has occurred
     * on the Secure Element.
     *
     * <p>This intent will only be sent if the application has requested permission for
     * {@link android.Manifest.permission#NFC_TRANSACTION_EVENT} and if the application has the
     * necessary access to Secure Element which witnessed the particular event.
     */
    public static final String ACTION_TRANSACTION_DETECTED = "android.nfc.action.TRANSACTION_DETECTED";

    /**
     * Used as an int extra field in {@link #ACTION_ADAPTER_STATE_CHANGED}
     * intents to request the current power state. Possible values are:
     * {@link #STATE_OFF},
     * {@link #STATE_TURNING_ON},
     * {@link #STATE_ON},
     * {@link #STATE_TURNING_OFF},
     */
    public static final String EXTRA_ADAPTER_STATE = "android.nfc.extra.ADAPTER_STATE";

    /**
     * Mandatory byte[] extra field in {@link #ACTION_TRANSACTION_DETECTED}
     */
    public static final String EXTRA_AID = "android.nfc.extra.AID";

    /**
     * Optional byte[] extra field in {@link #ACTION_TRANSACTION_DETECTED}
     */
    public static final String EXTRA_DATA = "android.nfc.extra.DATA";

    /**
     * Optional extra containing a byte array containing the ID of the discovered tag for
     * the {@link #ACTION_NDEF_DISCOVERED}, {@link #ACTION_TECH_DISCOVERED}, and
     * {@link #ACTION_TAG_DISCOVERED} intents.
     */
    public static final String EXTRA_ID = "android.nfc.extra.ID";

    /**
     * Extra containing an array of {@link NdefMessage} present on the discovered tag.<p>
     * This extra is mandatory for {@link #ACTION_NDEF_DISCOVERED} intents,
     * and optional for {@link #ACTION_TECH_DISCOVERED}, and
     * {@link #ACTION_TAG_DISCOVERED} intents.<p>
     * When this extra is present there will always be at least one
     * {@link NdefMessage} element. Most NDEF tags have only one NDEF message,
     * but we use an array for future compatibility.
     */
    public static final String EXTRA_NDEF_MESSAGES = "android.nfc.extra.NDEF_MESSAGES";

    /**
     * Mandatory String extra field in {@link #ACTION_PREFERRED_PAYMENT_CHANGED}
     * Indicates the condition when trigger this event. Possible values are:
     * {@link #PREFERRED_PAYMENT_LOADED},
     * {@link #PREFERRED_PAYMENT_CHANGED},
     * {@link #PREFERRED_PAYMENT_UPDATED},
     */
    public static final String EXTRA_PREFERRED_PAYMENT_CHANGED_REASON = "android.nfc.extra.PREFERRED_PAYMENT_CHANGED_REASON";

    /**
     * Int Extra for use with {@link #enableReaderMode(Activity, ReaderCallback, int, Bundle)}.
     * <p>
     * Setting this integer extra allows the calling application to specify
     * the delay that the platform will use for performing presence checks
     * on any discovered tag.
     */
    public static final String EXTRA_READER_PRESENCE_CHECK_DELAY = "presence";

    /**
     * Mandatory String extra field in {@link #ACTION_TRANSACTION_DETECTED}
     * Indicates the Secure Element on which the transaction occurred.
     * eSE1...eSEn for Embedded Secure Elements, SIM1...SIMn for UICC/EUICC, etc.
     */
    public static final String EXTRA_SECURE_ELEMENT_NAME = "android.nfc.extra.SECURE_ELEMENT_NAME";

    /**
     * Mandatory extra containing the {@link Tag} that was discovered for the
     * {@link #ACTION_NDEF_DISCOVERED}, {@link #ACTION_TECH_DISCOVERED}, and
     * {@link #ACTION_TAG_DISCOVERED} intents.
     */
    public static final String EXTRA_TAG = "android.nfc.extra.TAG";

    /**
     * Flags for use with {@link #setDiscoveryTechnology(Activity, int, int)}.
     * <p>
     * Setting this flag disables listening.
     */
    public static final int FLAG_LISTEN_DISABLE = 0;

    /**
     * Flags for use with {@link #setDiscoveryTechnology(Activity, int, int)}.
     * <p>
     * Setting this flag makes listening to be set to the current stored default technology
     * configuration.
     */
    public static final int FLAG_LISTEN_KEEP = -2147483648;

    /**
     * Flag for use with {@link #setDiscoveryTechnology(Activity, int, int)}.
     * <p>
     * Setting this flag enables listening for Nfc-A technology.
     */
    public static final int FLAG_LISTEN_NFC_PASSIVE_A = 1;

    /**
     * Flag for use with {@link #setDiscoveryTechnology(Activity, int, int)}.
     * <p>
     * Setting this flag enables listening for Nfc-B technology.
     */
    public static final int FLAG_LISTEN_NFC_PASSIVE_B = 2;

    /**
     * Flag for use with {@link #setDiscoveryTechnology(Activity, int, int)}.
     * <p>
     * Setting this flag enables listening for Nfc-F technology.
     */
    public static final int FLAG_LISTEN_NFC_PASSIVE_F = 4;

    /**
     * Flags for use with {@link #setDiscoveryTechnology(Activity, int, int)}.
     * <p>
     * Setting this flag disables polling.
     */
    public static final int FLAG_READER_DISABLE = 0;

    /**
     * Flags for use with {@link #setDiscoveryTechnology(Activity, int, int)}.
     * <p>
     * Setting this flag makes polling to be set to the current stored default technology
     * configuration.
     */
    public static final int FLAG_READER_KEEP = -2147483648;

    /**
     * Flag for use with {@link #enableReaderMode(Activity, ReaderCallback, int, Bundle)}.
     * <p>
     * Setting this flag enables polling for Nfc-A technology.
     */
    public static final int FLAG_READER_NFC_A = 1;

    /**
     * Flag for use with {@link #enableReaderMode(Activity, ReaderCallback, int, Bundle)}.
     * <p>
     * Setting this flag enables polling for Nfc-B technology.
     */
    public static final int FLAG_READER_NFC_B = 2;

    /**
     * Flag for use with {@link #enableReaderMode(Activity, ReaderCallback, int, Bundle)}.
     * <p>
     * Setting this flag enables polling for NfcBarcode technology.
     */
    public static final int FLAG_READER_NFC_BARCODE = 16;

    /**
     * Flag for use with {@link #enableReaderMode(Activity, ReaderCallback, int, Bundle)}.
     * <p>
     * Setting this flag enables polling for Nfc-F technology.
     */
    public static final int FLAG_READER_NFC_F = 4;

    /**
     * Flag for use with {@link #enableReaderMode(Activity, ReaderCallback, int, Bundle)}.
     * <p>
     * Setting this flag enables polling for Nfc-V (ISO15693) technology.
     */
    public static final int FLAG_READER_NFC_V = 8;

    /**
     * Flag for use with {@link #enableReaderMode(Activity, ReaderCallback, int, Bundle)}.
     * <p>
     * Setting this flag allows the caller to prevent the
     * platform from playing sounds when it discovers a tag.
     */
    public static final int FLAG_READER_NO_PLATFORM_SOUNDS = 256;

    /**
     * Flag for use with {@link #enableReaderMode(Activity, ReaderCallback, int, Bundle)}.
     * <p>
     * Setting this flag allows the caller to prevent the
     * platform from performing an NDEF check on the tags it
     * finds.
     */
    public static final int FLAG_READER_SKIP_NDEF_CHECK = 128;

    /**
     * User selected another payment application as the preferred payment.
     */
    public static final int PREFERRED_PAYMENT_CHANGED = 2;

    /**
     * Nfc is enabled and the preferred payment aids are registered.
     */
    public static final int PREFERRED_PAYMENT_LOADED = 1;

    /**
     * Current preferred payment has issued an update (registered/unregistered new aids or has been
     * updated itself).
     */
    public static final int PREFERRED_PAYMENT_UPDATED = 3;

    public static final int STATE_OFF = 1;

    public static final int STATE_ON = 3;

    public static final int STATE_TURNING_OFF = 4;

    public static final int STATE_TURNING_ON = 2;

    private NfcAdapter() {
    }
}
