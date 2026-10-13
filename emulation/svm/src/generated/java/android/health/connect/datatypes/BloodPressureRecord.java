package android.health.connect.datatypes;

/**
 * Captures the blood pressure of a user. Each record represents a single instantaneous blood
 * pressure reading.
 */
public final class BloodPressureRecord {
    private BloodPressureRecord() {
    }

    /**
     * Identifier for Blood Pressure Measurement Location
     */
    public static final class BloodPressureMeasurementLocation {
        /**
         * Blood pressure measurement location constant for the left upper arm.
         */
        public static final int BLOOD_PRESSURE_MEASUREMENT_LOCATION_LEFT_UPPER_ARM = 3;

        /**
         * Blood pressure measurement location constant for the left wrist.
         */
        public static final int BLOOD_PRESSURE_MEASUREMENT_LOCATION_LEFT_WRIST = 1;

        /**
         * Blood pressure measurement location constant for the right upper arm.
         */
        public static final int BLOOD_PRESSURE_MEASUREMENT_LOCATION_RIGHT_UPPER_ARM = 4;

        /**
         * Blood pressure measurement location constant for the right wrist.
         */
        public static final int BLOOD_PRESSURE_MEASUREMENT_LOCATION_RIGHT_WRIST = 2;

        public static final int BLOOD_PRESSURE_MEASUREMENT_LOCATION_UNKNOWN = 0;

        private BloodPressureMeasurementLocation() {
        }
    }

    /**
     * Identifier for body position
     */
    public static final class BodyPosition {
        /**
         * Body position constant representing lying down.
         */
        public static final int BODY_POSITION_LYING_DOWN = 3;

        /**
         * Body position constant representing semi-recumbent (partially reclining) pose.
         */
        public static final int BODY_POSITION_RECLINING = 4;

        /**
         * Body position constant representing sitting down.
         */
        public static final int BODY_POSITION_SITTING_DOWN = 2;

        /**
         * Body position constant representing standing up.
         */
        public static final int BODY_POSITION_STANDING_UP = 1;

        /**
         * Body position unknown / not identified.
         */
        public static final int BODY_POSITION_UNKNOWN = 0;

        private BodyPosition() {
        }
    }
}
