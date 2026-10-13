package android.health.connect.datatypes;

/**
 * Captures a description of how heavy a user's menstrual flow was (spotting, light, medium, or
 * heavy). Each record represents a description of how heavy the user's menstrual bleeding was.
 */
public final class MenstruationFlowRecord {
    private MenstruationFlowRecord() {
    }

    /**
     * Identifier for Menstruation Flow
     */
    public static final class MenstruationFlowType {
        public static final int FLOW_HEAVY = 3;

        public static final int FLOW_LIGHT = 1;

        public static final int FLOW_MEDIUM = 2;

        public static final int FLOW_UNKNOWN = 0;

        private MenstruationFlowType() {
        }
    }
}
