package android.adservices.adselection;

/**
 * Request object wrapping the required arguments needed to report an ad event.
 */
public final class ReportEventRequest {
    /**
     * This is used to represent buyer as the destination for report event API.
     */
    public static final int FLAG_REPORTING_DESTINATION_BUYER = 2;

    /**
     * This is used to component seller as the destination for report event API
     */
    public static final int FLAG_REPORTING_DESTINATION_COMPONENT_SELLER = 4;

    /**
     * This is used to represent seller as the destination for report event API
     */
    public static final int FLAG_REPORTING_DESTINATION_SELLER = 1;

    private ReportEventRequest() {
    }
}
