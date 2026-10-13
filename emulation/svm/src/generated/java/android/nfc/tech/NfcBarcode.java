package android.nfc.tech;

/**
 * Provides access to tags containing just a barcode.
 *
 * <p>Acquire an {@link NfcBarcode} object using {@link #get}.
 */
public final class NfcBarcode {
    /**
     * Kovio Tags
     */
    public static final int TYPE_KOVIO = 1;

    public static final int TYPE_UNKNOWN = -1;

    private NfcBarcode() {
    }
}
