package android.nfc.tech;

/**
 * Provides access to NDEF content and operations on a {@link Tag}.
 *
 * <p>Acquire a {@link Ndef} object using {@link #get}.
 *
 * <p>NDEF is an NFC Forum data format. The data formats are implemented in
 * {@link android.nfc.NdefMessage} and
 * {@link android.nfc.NdefRecord}. This class provides methods to
 * retrieve and modify the {@link android.nfc.NdefMessage}
 * on a tag.
 *
 * <p>There are currently four NFC Forum standardized tag types that can be
 * formatted to contain NDEF data.
 * <ul>
 * <li>NFC Forum Type 1 Tag ({@link #NFC_FORUM_TYPE_1}), such as the Innovision Topaz
 * <li>NFC Forum Type 2 Tag ({@link #NFC_FORUM_TYPE_2}), such as the NXP MIFARE Ultralight
 * <li>NFC Forum Type 3 Tag ({@link #NFC_FORUM_TYPE_3}), such as Sony Felica
 * <li>NFC Forum Type 4 Tag ({@link #NFC_FORUM_TYPE_4}), such as NXP MIFARE Desfire
 * </ul>
 * It is mandatory for all Android devices with NFC to correctly enumerate
 * {@link Ndef} on NFC Forum Tag Types 1-4, and implement all NDEF operations
 * as defined in this class.
 *
 * <p>Some vendors have their own well defined specifications for storing NDEF data
 * on tags that do not fall into the above categories. Android devices with NFC
 * should enumerate and implement {@link Ndef} under these vendor specifications
 * where possible, but it is not mandatory. {@link #getType} returns a String
 * describing this specification, for example {@link #MIFARE_CLASSIC} is
 * <code>com.nxp.ndef.mifareclassic</code>.
 *
 * <p>Android devices that support MIFARE Classic must also correctly
 * implement {@link Ndef} on MIFARE Classic tags formatted to NDEF.
 *
 * <p>For guaranteed compatibility across all Android devices with NFC, it is
 * recommended to use NFC Forum Types 1-4 in new deployments of NFC tags
 * with NDEF payload. Vendor NDEF formats will not work on all Android devices.
 *
 * <p class="note"><strong>Note:</strong> Methods that perform I/O operations
 * require the {@link android.Manifest.permission#NFC} permission.
 */
public final class Ndef {
    /**
     * NDEF on MIFARE Classic
     */
    public static final String MIFARE_CLASSIC = "com.nxp.ndef.mifareclassic";

    /**
     * NFC Forum Tag Type 1
     */
    public static final String NFC_FORUM_TYPE_1 = "org.nfcforum.ndef.type1";

    /**
     * NFC Forum Tag Type 2
     */
    public static final String NFC_FORUM_TYPE_2 = "org.nfcforum.ndef.type2";

    /**
     * NFC Forum Tag Type 3
     */
    public static final String NFC_FORUM_TYPE_3 = "org.nfcforum.ndef.type3";

    /**
     * NFC Forum Tag Type 4
     */
    public static final String NFC_FORUM_TYPE_4 = "org.nfcforum.ndef.type4";

    private Ndef() {
    }
}
