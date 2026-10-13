package android.health.connect.datatypes;

/**
 * Captures an occurrence of sexual activity. Each record is a single occurrence. ProtectionUsed
 * field is optional.
 */
public final class SexualActivityRecord {
    private SexualActivityRecord() {
    }

    /**
     * Identifier for sexual activity protection used
     */
    public static final class SexualActivityProtectionUsed {
        public static final int PROTECTION_USED_PROTECTED = 1;

        public static final int PROTECTION_USED_UNKNOWN = 0;

        public static final int PROTECTION_USED_UNPROTECTED = 2;

        private SexualActivityProtectionUsed() {
        }
    }
}
