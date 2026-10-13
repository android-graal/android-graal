package android.hardware;

/**
 * This class provides information about the microphone and camera toggles.
 */
public final class SensorPrivacyManager {
    /**
     * Constant for hardware toggle.
     */
    public static final int TOGGLE_TYPE_HARDWARE = 2;

    /**
     * Constant for software toggle.
     */
    public static final int TOGGLE_TYPE_SOFTWARE = 1;

    private SensorPrivacyManager() {
    }

    /**
     * Sensor constants which are used in {@link SensorPrivacyManager}
     */
    public static final class Sensors {
        /**
         * Constant for the camera
         */
        public static final int CAMERA = 2;

        /**
         * Constant for the microphone
         */
        public static final int MICROPHONE = 1;

        private Sensors() {
        }
    }
}
