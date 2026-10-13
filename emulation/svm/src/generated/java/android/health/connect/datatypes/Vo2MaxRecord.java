package android.health.connect.datatypes;

/**
 * Capture user's VO2 max score and optionally the measurement method.
 */
public final class Vo2MaxRecord {
    private Vo2MaxRecord() {
    }

    /**
     * Identifier for V02 max measurement method
     */
    public static final class Vo2MaxMeasurementMethod {
        public static final int MEASUREMENT_METHOD_COOPER_TEST = 3;

        public static final int MEASUREMENT_METHOD_HEART_RATE_RATIO = 2;

        public static final int MEASUREMENT_METHOD_METABOLIC_CART = 1;

        public static final int MEASUREMENT_METHOD_MULTISTAGE_FITNESS_TEST = 4;

        public static final int MEASUREMENT_METHOD_OTHER = 0;

        public static final int MEASUREMENT_METHOD_ROCKPORT_FITNESS_TEST = 5;

        private Vo2MaxMeasurementMethod() {
        }
    }
}
