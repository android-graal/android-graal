package android.os;

/**
 * SecurityStateManager provides the functionality to query the security status of the system and
 * platform components. For example, this includes the system and vendor security patch level.
 */
public final class SecurityStateManager {
    /**
     * The kernel version key returned as part of the {@code Bundle} from
     * {@code getGlobalSecurityState}.
     */
    public static final String KEY_KERNEL_VERSION = "kernel_version";

    /**
     * The system SPL key returned as part of the {@code Bundle} from
     * {@code getGlobalSecurityState}.
     */
    public static final String KEY_SYSTEM_SPL = "system_spl";

    /**
     * The vendor SPL key returned as part of the {@code Bundle} from
     * {@code getGlobalSecurityState}.
     */
    public static final String KEY_VENDOR_SPL = "vendor_spl";

    private SecurityStateManager() {
    }
}
