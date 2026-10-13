package android.health.connect.datatypes;

/**
 * Each record represents the result of an ovulation test.
 */
public final class OvulationTestRecord {
    private OvulationTestRecord() {
    }

    /**
     * Identifier for Ovulation Test Result
     */
    public static final class OvulationTestResult {
        /**
         * High fertility. Refers to a rise in estrogen or luteinizing hormone that may signal the
         * fertile window (time in the menstrual cycle when conception is likely to occur).
         */
        public static final int RESULT_HIGH = 2;

        /**
         * Inconclusive result. Refers to ovulation test results that are indeterminate (e.g. may be
         * testing malfunction, user error, etc.). ". Any unknown value will also be returned as
         * [RESULT_INCONCLUSIVE].
         */
        public static final int RESULT_INCONCLUSIVE = 0;

        /**
         * Negative fertility (may also be referred as "low" fertility). Refers to the time in the
         * cycle where fertility/conception is expected to be low.
         */
        public static final int RESULT_NEGATIVE = 3;

        /**
         * Positive fertility (may also be referred as "peak" fertility). Refers to the peak of the
         * luteinizing hormone (LH) surge and ovulation is expected to occur in 10-36 hours.
         */
        public static final int RESULT_POSITIVE = 1;

        private OvulationTestResult() {
        }
    }
}
