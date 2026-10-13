package android.health.connect.datatypes;

/**
 * Captures the description of cervical mucus. Each record represents a self-assessed description of
 * cervical mucus for a user. All fields are optional and can be used to describe the look and feel
 * of cervical mucus.
 */
public final class CervicalMucusRecord {
    private CervicalMucusRecord() {
    }

    /**
     * Identifier for Cervical Mucus Appearance
     */
    public static final class CervicalMucusAppearance {
        /**
         * A constant describing creamy like looking cervical mucus.
         */
        public static final int APPEARANCE_CREAMY = 3;

        /**
         * A constant describing a dry cervical mucus.
         */
        public static final int APPEARANCE_DRY = 1;

        /**
         * A constant describing clear or egg white like looking cervical mucus.
         */
        public static final int APPEARANCE_EGG_WHITE = 5;

        /**
         * A constant describing a sticky cervical mucus.
         */
        public static final int APPEARANCE_STICKY = 2;

        /**
         * A constant describing cervical mucus which appearance is unknown.
         */
        public static final int APPEARANCE_UNKNOWN = 0;

        /**
         * A constant describing an unusual (worth attention) kind of cervical mucus.
         */
        public static final int APPEARANCE_UNUSUAL = 6;

        /**
         * A constant describing watery like looking cervical mucus.
         */
        public static final int APPEARANCE_WATERY = 4;

        private CervicalMucusAppearance() {
        }
    }

    /**
     * Identifier for Cervical Mucus Sensation
     */
    public static final class CervicalMucusSensation {
        public static final int SENSATION_HEAVY = 3;

        public static final int SENSATION_LIGHT = 1;

        public static final int SENSATION_MEDIUM = 2;

        public static final int SENSATION_UNKNOWN = 0;

        private CervicalMucusSensation() {
        }
    }
}
