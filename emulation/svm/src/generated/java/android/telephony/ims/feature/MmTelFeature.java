package android.telephony.ims.feature;

/**
 * Base implementation for Voice and SMS (IR-92) and Video (IR-94) IMS support.
 *
 * Any class wishing to use MmTelFeature should extend this class and implement all methods that the
 * service supports.
 */
public final class MmTelFeature {
    private MmTelFeature() {
    }

    /**
     * Contains the capabilities defined and supported by a MmTelFeature in the form of a Bitmask.
     * The capabilities that are used in MmTelFeature are defined as
     * {@link MmTelCapabilities#CAPABILITY_TYPE_VOICE},
     * {@link MmTelCapabilities#CAPABILITY_TYPE_VIDEO},
     * {@link MmTelCapabilities#CAPABILITY_TYPE_UT},
     * {@link MmTelCapabilities#CAPABILITY_TYPE_SMS}, and
     * {@link MmTelCapabilities#CAPABILITY_TYPE_CALL_COMPOSER}.
     *
     * The capabilities of this MmTelFeature will be set by the framework.
     */
    public static final class MmTelCapabilities {
        /**
         * This MmTelFeature supports Call Composer (section 2.4 of RC.20). This is the superset
         * Call Composer, meaning that all subset types of Call Composers must be enabled when this
         * capability is enabled
         */
        public static final int CAPABILITY_TYPE_CALL_COMPOSER = 16;

        /**
         * This MmTelFeature supports Business-only Call Composer. This is a subset of
         * {@code CAPABILITY_TYPE_CALL_COMPOSER} that only supports business related
         * information for calling (e.g. information to signal if the call is a business call) in
         * the SIP header.  When enabling {@code CAPABILITY_TYPE_CALL_COMPOSER}, the
         * {@code CAPABILITY_TYPE_CALL_COMPOSER_BUSINESS_ONLY} capability must also be enabled.
         */
        public static final int CAPABILITY_TYPE_CALL_COMPOSER_BUSINESS_ONLY = 32;

        /**
         * This MmTelFeature supports SMS (IR.92)
         */
        public static final int CAPABILITY_TYPE_SMS = 8;

        /**
         * This MmTelFeature supports XCAP over Ut for supplementary services. (IR.92)
         */
        public static final int CAPABILITY_TYPE_UT = 4;

        /**
         * This MmTelFeature supports Video (IR.94)
         */
        public static final int CAPABILITY_TYPE_VIDEO = 2;

        /**
         * This MmTelFeature supports Voice calling (IR.92)
         */
        public static final int CAPABILITY_TYPE_VOICE = 1;

        private MmTelCapabilities() {
        }
    }
}
