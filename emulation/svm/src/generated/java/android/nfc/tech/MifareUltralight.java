package android.nfc.tech;

/**
 * Provides access to MIFARE Ultralight properties and I/O operations on a {@link Tag}.
 *
 * <p>Acquire a {@link MifareUltralight} object using {@link #get}.
 *
 * <p>MIFARE Ultralight compatible tags have 4 byte pages {@link #PAGE_SIZE}.
 * The primary operations on an Ultralight tag are {@link #readPages} and
 * {@link #writePage}.
 *
 * <p>The original MIFARE Ultralight consists of a 64 byte EEPROM. The first
 * 4 pages are for the OTP area, manufacturer data, and locking bits. They are
 * readable and some bits are writable. The final 12 pages are the user
 * read/write area. For more information see the NXP data sheet MF0ICU1.
 *
 * <p>The MIFARE Ultralight C consists of a 192 byte EEPROM. The first 4 pages
 * are for OTP, manufacturer data, and locking bits. The next 36 pages are the
 * user read/write area. The next 4 pages are additional locking bits, counters
 * and authentication configuration and are readable. The final 4 pages are for
 * the authentication key and are not readable. For more information see the
 * NXP data sheet MF0ICU2.
 *
 * <p>Implementation of this class on a Android NFC device is optional.
 * If it is not implemented, then
 * {@link MifareUltralight} will never be enumerated in {@link Tag#getTechList}.
 * If it is enumerated, then all {@link MifareUltralight} I/O operations will be supported.
 * In either case, {@link NfcA} will also be enumerated on the tag,
 * because all MIFARE Ultralight tags are also {@link NfcA} tags.
 *
 * <p class="note"><strong>Note:</strong> Methods that perform I/O operations
 * require the {@link android.Manifest.permission#NFC} permission.
 */
public final class MifareUltralight {
    /**
     * Size of a MIFARE Ultralight page in bytes
     */
    public static final int PAGE_SIZE = 4;

    /**
     * A MIFARE Ultralight tag
     */
    public static final int TYPE_ULTRALIGHT = 1;

    /**
     * A MIFARE Ultralight C tag
     */
    public static final int TYPE_ULTRALIGHT_C = 2;

    /**
     * A MIFARE Ultralight compatible tag of unknown type
     */
    public static final int TYPE_UNKNOWN = -1;

    private MifareUltralight() {
    }
}
