package android.health.connect.datatypes;

/**
 * Captures the user's sleep length and its stages. Each record represents a time interval for a
 * full sleep session.
 *
 * <p>All {@link Stage} time intervals should fall within the sleep session interval. Time intervals
 * for stages don't need to be continuous but shouldn't overlap.
 */
public final class SleepSessionRecord {
    private SleepSessionRecord() {
    }

    /**
     * Identifier for sleeping stage, as returned by {@link Stage#getType()}.
     */
    public static final class StageType {
        /**
         * The user is awake and either known to be in bed, or it is unknown whether they are in bed
         * or not.
         */
        public static final int STAGE_TYPE_AWAKE = 1;

        /**
         * The user is awake and in bed.
         */
        public static final int STAGE_TYPE_AWAKE_IN_BED = 7;

        /**
         * The user is out of bed and assumed to be awake.
         */
        public static final int STAGE_TYPE_AWAKE_OUT_OF_BED = 3;

        /**
         * The user is asleep but the particular stage of sleep (light, deep or REM) is unknown.
         */
        public static final int STAGE_TYPE_SLEEPING = 2;

        /**
         * The user is in a deep sleep stage.
         */
        public static final int STAGE_TYPE_SLEEPING_DEEP = 5;

        /**
         * The user is in a light sleep stage.
         */
        public static final int STAGE_TYPE_SLEEPING_LIGHT = 4;

        /**
         * The user is in a REM sleep stage.
         */
        public static final int STAGE_TYPE_SLEEPING_REM = 6;

        /**
         * Use this type if the stage of sleep is unknown.
         */
        public static final int STAGE_TYPE_UNKNOWN = 0;

        private StageType() {
        }
    }
}
