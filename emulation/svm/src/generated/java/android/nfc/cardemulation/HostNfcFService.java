package android.nfc.cardemulation;

/**
 * <p>HostNfcFService is a convenience {@link Service} class that can be
 * extended to emulate an NFC-F card inside an Android service component.
 *
 * <h3>NFC Protocols</h3>
 * <p>Cards emulated by this class are based on the NFC-Forum NFC-F
 * protocol (based on the JIS-X 6319-4 specification.)</p>
 *
 * <h3>System Code and NFCID2 registration</h3>
 * <p>A {@link HostNfcFService HostNfcFService service} can register
 * exactly one System Code and one NFCID2. For details about the use of
 * System Code and NFCID2, see the NFC Forum Digital specification.</p>
 * <p>To statically register a System Code and NFCID2 with the service, a {@link #SERVICE_META_DATA}
 * entry must be included in the declaration of the service.
 *
 * <p>All {@link HostNfcFService HostNfcFService} declarations in the manifest must require the
 * {@link android.Manifest.permission#BIND_NFC_SERVICE} permission
 * in their &lt;service&gt; tag, to ensure that only the platform can bind to your service.</p>
 *
 * <p>An example of a HostNfcFService manifest declaration is shown below:
 *
 * <pre> &lt;service android:name=".MyHostNfcFService" android:exported="true" android:permission="android.permission.BIND_NFC_SERVICE"&gt;
 *     &lt;intent-filter&gt;
 *         &lt;action android:name="android.nfc.cardemulation.action.HOST_NFCF_SERVICE"/&gt;
 *     &lt;/intent-filter&gt;
 *     &lt;meta-data android:name="android.nfc.cardemulation.host_nfcf_service" android:resource="@xml/nfcfservice"/&gt;
 * &lt;/service&gt;</pre>
 *
 * This meta-data tag points to an nfcfservice.xml file.
 * An example of this file with a System Code and NFCID2 declaration is shown below:
 * <pre>
 * &lt;host-nfcf-service xmlns:android="http://schemas.android.com/apk/res/android"
 *           android:description="@string/servicedesc"&gt;
 *       &lt;system-code-filter android:name="4000"/&gt;
 *       &lt;nfcid2-filter android:name="02FE000000000000"/&gt;
 *         &lt;t3tPmm-filter android:name="FFFFFFFFFFFFFFFF"/&gt;
 * &lt;/host-nfcf-service&gt;
 * </pre>
 *
 * <p>The {@link android.R.styleable#HostNfcFService &lt;host-nfcf-service&gt;} is required
 * to contain a
 * {@link android.R.styleable#HostApduService_description &lt;android:description&gt;}
 * attribute that contains a user-friendly description of the service that may be shown in UI.
 * <p>The {@link android.R.styleable#HostNfcFService &lt;host-nfcf-service&gt;} must
 * contain:
 * <ul>
 * <li>Exactly one {@link android.R.styleable#SystemCodeFilter &lt;system-code-filter&gt;} tag.</li>
 * <li>Exactly one {@link android.R.styleable#Nfcid2Filter &lt;nfcid2-filter&gt;} tag.</li>
 * <li>Zero or one {@link android.R.styleable#T3tPmmFilter &lt;t3tPmm-filter&gt;} tag.</li>
 * </ul>
 * </p>
 *
 * <p>Alternatively, the System Code and NFCID2 can be dynamically registererd for a service
 * by using the {@link NfcFCardEmulation#registerSystemCodeForService(ComponentName, String)} and
 * {@link NfcFCardEmulation#setNfcid2ForService(ComponentName, String)} methods.
 * </p>
 *
 * <h3>Service selection</h3>
 * <p>When a remote NFC devices wants to communicate with your service, it
 * sends a SENSF_REQ command to the NFC controller, requesting a System Code.
 * If a {@link NfcFCardEmulation NfcFCardEmulation service} has registered
 * this system code and has been enabled by the foreground application, the
 * NFC controller will respond with the NFCID2 that is registered for this service.
 * The reader can then continue data exchange with this service by using the NFCID2.</p>
 *
 * <h3>Data exchange</h3>
 * <p>After service selection, all frames addressed to the NFCID2 of this service will
 * be sent through {@link #processNfcFPacket(byte[], Bundle)}, until the NFC link is
 * broken.<p>
 *
 * <p>When the NFC link is broken, {@link #onDeactivated(int)} will be called.</p>
 */
public final class HostNfcFService {
    /**
     * Reason for {@link #onDeactivated(int)}.
     * Indicates deactivation was due to the NFC link
     * being lost.
     */
    public static final int DEACTIVATION_LINK_LOSS = 0;

    /**
     * The {@link Intent} action that must be declared as handled by the service.
     */
    public static final String SERVICE_INTERFACE = "android.nfc.cardemulation.action.HOST_NFCF_SERVICE";

    /**
     * The name of the meta-data element that contains
     * more information about this service.
     */
    public static final String SERVICE_META_DATA = "android.nfc.cardemulation.host_nfcf_service";

    private HostNfcFService() {
    }
}
