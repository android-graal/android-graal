package android.telephony.ims.stub;

/**
 * Controls IMS registration for this ImsService and notifies the framework when the IMS
 * registration for this ImsService has changed status.
 * <p>
 * Note: There is no guarantee on the thread that the calls from the framework will be called on. It
 * is the implementors responsibility to handle moving the calls to a working thread if required.
 */
public final class ImsRegistrationImplBase {
    /**
     * This ImsService is registered to IMS via 3G.
     */
    public static final int REGISTRATION_TECH_3G = 4;

    /**
     * This ImsService is registered to IMS via internet over second subscription.
     */
    public static final int REGISTRATION_TECH_CROSS_SIM = 2;

    /**
     * This ImsService is registered to IMS via IWLAN.
     */
    public static final int REGISTRATION_TECH_IWLAN = 1;

    /**
     * This ImsService is registered to IMS via LTE.
     */
    public static final int REGISTRATION_TECH_LTE = 0;

    /**
     * No registration technology specified, used when we are not registered.
     */
    public static final int REGISTRATION_TECH_NONE = -1;

    /**
     * This ImsService is registered to IMS via NR.
     */
    public static final int REGISTRATION_TECH_NR = 3;

    private ImsRegistrationImplBase() {
    }
}
