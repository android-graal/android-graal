package android.provider;

/**
 * E2eeContactKeysManager provides access to the provider of end-to-end encryption contact keys.
 * It manages two types of keys - {@link E2eeContactKey} and {@link E2eeSelfKey}.
 * <ul>
 * <li>
 * A {@link E2eeContactKey} is a public key associated with a contact. It's used to end-to-end
 * encrypt the communications between a user and the contact. This API allows operations on
 * {@link E2eeContactKey}s to insert/update, remove, change the verification state, and retrieving
 * keys (either created by or visible to the caller app).
 * </li>
 * <li>
 * A {@link E2eeSelfKey} is a key for this device, so the key represents the owner of the device.
 * This API allows operations on {@link E2eeSelfKey}s to insert/update, remove, and retrieving
 * self keys (either created by or visible to the caller app).
 * </li>
 * </ul>
 * Keys are uniquely identified by:
 * <ul>
 * <li>
 * ownerPackageName - package name of an app that the key belongs to.
 * </li>
 * <li>
 * deviceId - an app-specified identifier for the device, defined by the app. Within that app,
 * the deviceID should be unique among devices belonging to that user.
 * </li>
 * <li>
 * accountId - the app-specified identifier for the account for which the contact key can be used.
 * Using different account IDs allows for multiple key entries representing the same user.
 * For most apps this would be a phone number.
 * </li>
 * </ul>
 * Contact keys also use lookupKey which is an opaque value used to identify a contact in
 * ContactsProvider.
 */
public final class E2eeContactKeysManager {
    /**
     * Unverified state of a contact end to end encrypted key.
     */
    public static final int VERIFICATION_STATE_UNVERIFIED = 0;

    /**
     * Failed verification state of a contact end to end encrypted key.
     */
    public static final int VERIFICATION_STATE_VERIFICATION_FAILED = 1;

    /**
     * Verified state of a contact end to end encrypted key.
     */
    public static final int VERIFICATION_STATE_VERIFIED = 2;

    private E2eeContactKeysManager() {
    }
}
