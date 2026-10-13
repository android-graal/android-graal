package android.service.controls.actions;

/**
 * An abstract action indicating a user interaction with a {@link Control}.
 *
 * In some cases, an action needs to be validated by the user, using a password, PIN or simple
 * acknowledgment. For those cases, an optional (nullable) parameter can be passed to send the user
 * input. This <b>challenge value</b> will be requested from the user and sent as part
 * of a {@link ControlAction} only if the service has responded to an action with one of:
 * <ul>
 *     <li> {@link #RESPONSE_CHALLENGE_ACK}
 *     <li> {@link #RESPONSE_CHALLENGE_PIN}
 *     <li> {@link #RESPONSE_CHALLENGE_PASSPHRASE}
 * </ul>
 */
public final class ControlAction {
    /**
     * Response code for the {@code consumer} in
     * {@link ControlsProviderService#performControlAction} indicating that in order for the action
     * to be performed, acknowledgment from the user is required. Any non-empty string returned
     * from {@link #getChallengeValue} shall be treated as a positive acknowledgment.
     */
    public static final int RESPONSE_CHALLENGE_ACK = 3;

    /**
     * Response code for the {@code consumer} in
     * {@link ControlsProviderService#performControlAction} indicating that in order for the action
     * to be performed, an alphanumeric passphrase is required.
     */
    public static final int RESPONSE_CHALLENGE_PASSPHRASE = 5;

    /**
     * Response code for the {@code consumer} in
     * {@link ControlsProviderService#performControlAction} indicating that in order for the action
     * to be performed, a PIN is required.
     */
    public static final int RESPONSE_CHALLENGE_PIN = 4;

    /**
     * Response code for the {@code consumer} in
     * {@link ControlsProviderService#performControlAction} indicating that the action has failed.
     */
    public static final int RESPONSE_FAIL = 2;

    /**
     * Response code for the {@code consumer} in
     * {@link ControlsProviderService#performControlAction} indicating that the action has been
     * performed. The action may still fail later and the state may not change.
     */
    public static final int RESPONSE_OK = 1;

    public static final int RESPONSE_UNKNOWN = 0;

    /**
     * The identifier of {@link BooleanAction}.
     */
    public static final int TYPE_BOOLEAN = 1;

    /**
     * The identifier of {@link CommandAction}.
     */
    public static final int TYPE_COMMAND = 5;

    /**
     * The identifier of the action returned by {@link #getErrorAction}.
     */
    public static final int TYPE_ERROR = -1;

    /**
     * The identifier of {@link FloatAction}.
     */
    public static final int TYPE_FLOAT = 2;

    /**
     * The identifier of {@link ModeAction}.
     */
    public static final int TYPE_MODE = 4;

    private ControlAction() {
    }
}
