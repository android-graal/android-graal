package android.accessibilityservice;

/**
 * Used to communicate with a Braille display that supports the Braille display HID standard
 * (usage page 0x41).
 *
 * <p>Only one Braille display may be connected at a time.
 */
public interface BrailleDisplayController {
    /**
     * Interface provided to {@link BrailleDisplayController} connection methods to
     * receive callbacks from the system.
     */
    interface BrailleDisplayCallback {
        /**
         * A unique Braille display matching the requested properties could not be identified.
         */
        int FLAG_ERROR_BRAILLE_DISPLAY_NOT_FOUND = 2;

        /**
         * The system cannot access connected HID devices.
         */
        int FLAG_ERROR_CANNOT_ACCESS = 1;
    }
}
