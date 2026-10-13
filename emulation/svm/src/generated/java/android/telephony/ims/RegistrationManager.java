package android.telephony.ims;

/**
 * Manages IMS Service registration state for associated {@code ImsFeature}s.
 */
public interface RegistrationManager {
    /**
     * The IMS service is currently not registered to the carrier network.
     */
    int REGISTRATION_STATE_NOT_REGISTERED = 0;

    /**
     * The IMS service is currently registered to the carrier network.
     */
    int REGISTRATION_STATE_REGISTERED = 2;

    /**
     * The IMS service is currently in the process of registering to the carrier network.
     */
    int REGISTRATION_STATE_REGISTERING = 1;
}
