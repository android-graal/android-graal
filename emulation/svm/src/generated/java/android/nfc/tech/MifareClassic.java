package android.nfc.tech;

/**
 * Provides access to MIFARE Classic properties and I/O operations on a {@link Tag}.
 *
 * <p>Acquire a {@link MifareClassic} object using {@link #get}.
 *
 * <p>MIFARE Classic is also known as MIFARE Standard.
 * <p>MIFARE Classic tags are divided into sectors, and each sector is sub-divided into
 * blocks. Block size is always 16 bytes ({@link #BLOCK_SIZE}. Sector size varies.
 * <ul>
 * <li>MIFARE Classic Mini are 320 bytes ({@link #SIZE_MINI}), with 5 sectors each of 4 blocks.
 * <li>MIFARE Classic 1k are 1024 bytes ({@link #SIZE_1K}), with 16 sectors each of 4 blocks.
 * <li>MIFARE Classic 2k are 2048 bytes ({@link #SIZE_2K}), with 32 sectors each of 4 blocks.
 * <li>MIFARE Classic 4k are 4096 bytes ({@link #SIZE_4K}). The first 32 sectors contain 4 blocks
 * and the last 8 sectors contain 16 blocks.
 * </ul>
 *
 * <p>MIFARE Classic tags require authentication on a per-sector basis before any
 * other I/O operations on that sector can be performed. There are two keys per sector,
 * and ACL bits determine what I/O operations are allowed on that sector after
 * authenticating with a key. {@see #authenticateSectorWithKeyA} and
 * {@see #authenticateSectorWithKeyB}.
 *
 * <p>Three well-known authentication keys are defined in this class:
 * {@link #KEY_DEFAULT}, {@link #KEY_MIFARE_APPLICATION_DIRECTORY},
 * {@link #KEY_NFC_FORUM}.
 * <ul>
 * <li>{@link #KEY_DEFAULT} is the default factory key for MIFARE Classic.
 * <li>{@link #KEY_MIFARE_APPLICATION_DIRECTORY} is the well-known key for
 * MIFARE Classic cards that have been formatted according to the
 * MIFARE Application Directory (MAD) specification.
 * <li>{@link #KEY_NFC_FORUM} is the well-known key for MIFARE Classic cards that
 * have been formatted according to the NXP specification for NDEF on MIFARE Classic.
 *
 * <p>Implementation of this class on a Android NFC device is optional.
 * If it is not implemented, then
 * {@link MifareClassic} will never be enumerated in {@link Tag#getTechList}.
 * If it is enumerated, then all {@link MifareClassic} I/O operations will be supported,
 * and {@link Ndef#MIFARE_CLASSIC} NDEF tags will also be supported. In either case,
 * {@link NfcA} will also be enumerated on the tag, because all MIFARE Classic tags are also
 * {@link NfcA}.
 *
 * <p class="note"><strong>Note:</strong> Methods that perform I/O operations
 * require the {@link android.Manifest.permission#NFC} permission.
 */
public final class MifareClassic {
    /**
     * Size of a MIFARE Classic block (in bytes)
     */
    public static final int BLOCK_SIZE = 16;

    /**
     * Tag contains 16 sectors, each with 4 blocks.
     */
    public static final int SIZE_1K = 1024;

    /**
     * Tag contains 32 sectors, each with 4 blocks.
     */
    public static final int SIZE_2K = 2048;

    /**
     * Tag contains 40 sectors. The first 32 sectors contain 4 blocks and the last 8 sectors
     * contain 16 blocks.
     */
    public static final int SIZE_4K = 4096;

    /**
     * Tag contains 5 sectors, each with 4 blocks.
     */
    public static final int SIZE_MINI = 320;

    /**
     * A MIFARE Classic tag
     */
    public static final int TYPE_CLASSIC = 0;

    /**
     * A MIFARE Plus tag
     */
    public static final int TYPE_PLUS = 1;

    /**
     * A MIFARE Pro tag
     */
    public static final int TYPE_PRO = 2;

    /**
     * A MIFARE Classic compatible card of unknown type
     */
    public static final int TYPE_UNKNOWN = -1;

    private MifareClassic() {
    }
}
