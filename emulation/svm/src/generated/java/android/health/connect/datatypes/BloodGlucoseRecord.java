package android.health.connect.datatypes;

/**
 * Captures the concentration of glucose in the blood. Each record represents a single instantaneous
 * blood glucose reading.
 */
public final class BloodGlucoseRecord {
    private BloodGlucoseRecord() {
    }

    /**
     * Relationship of the meal to the blood glucose measurement.
     */
    public static final class RelationToMealType {
        /**
         * Reading was taken after an unspecified meal.
         */
        public static final int RELATION_TO_MEAL_AFTER_MEAL = 4;

        /**
         * Reading was taken before an unspecified meal.
         */
        public static final int RELATION_TO_MEAL_BEFORE_MEAL = 3;

        /**
         * Reading was taken during a fasting period.
         */
        public static final int RELATION_TO_MEAL_FASTING = 2;

        /**
         * Reading was not taken immediately before or after eating.
         */
        public static final int RELATION_TO_MEAL_GENERAL = 1;

        /**
         * Represents a blood glucose which relationship to any meal is unknown / not defined.
         */
        public static final int RELATION_TO_MEAL_UNKNOWN = 0;

        private RelationToMealType() {
        }
    }

    /**
     * Type of body fluid used to measure the blood glucose.
     */
    public static final class SpecimenSource {
        /**
         * Glucose was measured in capillary blood.
         */
        public static final int SPECIMEN_SOURCE_CAPILLARY_BLOOD = 2;

        /**
         * Glucose was measured in interstitial fluid.
         */
        public static final int SPECIMEN_SOURCE_INTERSTITIAL_FLUID = 1;

        /**
         * Glucose was measured in plasma.
         */
        public static final int SPECIMEN_SOURCE_PLASMA = 3;

        /**
         * Glucose was measured in serum.
         */
        public static final int SPECIMEN_SOURCE_SERUM = 4;

        /**
         * Glucose was measured in tears.
         */
        public static final int SPECIMEN_SOURCE_TEARS = 5;

        /**
         * Fluid used to measure glucose is not identified.
         */
        public static final int SPECIMEN_SOURCE_UNKNOWN = 0;

        /**
         * Glucose was measured from whole blood.
         */
        public static final int SPECIMEN_SOURCE_WHOLE_BLOOD = 6;

        private SpecimenSource() {
        }
    }
}
