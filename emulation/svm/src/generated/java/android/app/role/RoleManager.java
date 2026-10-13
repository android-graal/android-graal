package android.app.role;

/**
 * This class provides information about and manages roles.
 * <p>
 * A role is a unique name within the system associated with certain privileges. The list of
 * available roles might change with a system app update, so apps should not make assumption about
 * the availability of roles. Instead, they should always query if the role is available using
 * {@link #isRoleAvailable(String)} before trying to do anything with it. Some predefined role names
 * are available as constants in this class, and a list of possibly available roles can be found in
 * the <a href="{@docRoot}reference/androidx/core/role/package-summary.html">AndroidX Role
 * library</a>.
 * <p>
 * There can be multiple applications qualifying for a role, but only a subset of them can become
 * role holders. To qualify for a role, an application must meet certain requirements, including
 * defining certain components in its manifest. These requirements can be found in the AndroidX
 * Libraries. Then the application will need user consent to become a role holder, which can be
 * requested using {@link android.app.Activity#startActivityForResult(Intent, int)} with the
 * {@code Intent} obtained from {@link #createRequestRoleIntent(String)}.
 * <p>
 * Upon becoming a role holder, the application may be granted certain privileges that are role
 * specific. When the application loses its role, these privileges will also be revoked.
 */
public final class RoleManager {
    /**
     * The name of the assistant app role.
     *
     * @see android.service.voice.VoiceInteractionService
     */
    public static final String ROLE_ASSISTANT = "android.app.role.ASSISTANT";

    /**
     * The name of the browser role.
     *
     * @see Intent#CATEGORY_APP_BROWSER
     */
    public static final String ROLE_BROWSER = "android.app.role.BROWSER";

    /**
     * The name of the call redirection role.
     * <p>
     * A call redirection app provides a means to re-write the phone number for an outgoing call to
     * place the call through a call redirection service.
     *
     * @see android.telecom.CallRedirectionService
     */
    public static final String ROLE_CALL_REDIRECTION = "android.app.role.CALL_REDIRECTION";

    /**
     * The name of the call screening and caller id role.
     *
     * @see android.telecom.CallScreeningService
     */
    public static final String ROLE_CALL_SCREENING = "android.app.role.CALL_SCREENING";

    /**
     * The name of the dialer role.
     *
     * @see Intent#ACTION_DIAL
     * @see android.telecom.InCallService
     */
    public static final String ROLE_DIALER = "android.app.role.DIALER";

    /**
     * The name of the emergency role
     */
    public static final String ROLE_EMERGENCY = "android.app.role.EMERGENCY";

    /**
     * The name of the home role.
     *
     * @see Intent#CATEGORY_HOME
     */
    public static final String ROLE_HOME = "android.app.role.HOME";

    /**
     * The name of the notes role.
     *
     * @see Intent#ACTION_CREATE_NOTE
     * @see Intent#EXTRA_USE_STYLUS_MODE
     */
    public static final String ROLE_NOTES = "android.app.role.NOTES";

    /**
     * The name of the SMS role.
     *
     * @see Intent#CATEGORY_APP_MESSAGING
     */
    public static final String ROLE_SMS = "android.app.role.SMS";

    /**
     * The name of the Wallet role.
     *
     * @see android.nfc.cardemulation.CardEmulation
     */
    public static final String ROLE_WALLET = "android.app.role.WALLET";

    private RoleManager() {
    }
}
