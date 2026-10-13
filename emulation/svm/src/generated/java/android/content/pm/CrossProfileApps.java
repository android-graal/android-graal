package android.content.pm;

/**
 * Class for handling cross profile operations. Apps can use this class to interact with its
 * instance in any profile that is in {@link #getTargetUserProfiles()}. For example, app can
 * use this class to start its main activity in managed profile.
 */
public final class CrossProfileApps {
    /**
     * Broadcast signalling that the receiving app's permission to interact across profiles has
     * changed. This includes the user, admin, or OEM changing their consent such that the
     * permission for the app to interact across profiles has changed.
     *
     * <p>This broadcast is not sent when other circumstances result in a change to being able to
     * interact across profiles in practice, such as the profile being turned off or removed, apps
     * being uninstalled, etc. The methods {@link #canInteractAcrossProfiles()} and {@link
     * #canRequestInteractAcrossProfiles()} can be used by apps prior to attempting to interact
     * across profiles or attempting to request user consent to interact across profiles.
     *
     * <p>Apps that have set the {@code android:crossProfile} manifest attribute to {@code true}
     * can receive this broadcast in manifest broadcast receivers. Otherwise, it can only be
     * received by dynamically-registered broadcast receivers.
     */
    public static final String ACTION_CAN_INTERACT_ACROSS_PROFILES_CHANGED = "android.content.pm.action.CAN_INTERACT_ACROSS_PROFILES_CHANGED";

    private CrossProfileApps() {
    }
}
