package android.telephony.data;

import android.os.Parcelable;

/**
 * Represents a single route selection descriptor as defined in
 * 3GPP TS 24.526.
 */
public final class RouteSelectionDescriptor implements Parcelable {
    /**
     * The route selection descriptor is using SSC mode 1. The session will provide continual
     * support when UE's location is updated.
     */
    public static final int ROUTE_SSC_MODE_1 = 1;

    /**
     * The route selection descriptor is using SSC mode 2. The new session for the same network
     * will be established after releasing the old session when UE's location is updated.
     */
    public static final int ROUTE_SSC_MODE_2 = 2;

    /**
     * The route selection descriptor is using SSC mode 3. The new session for the same network is
     * allowed to be established before releasing the old session when UE's location is updated.
     */
    public static final int ROUTE_SSC_MODE_3 = 3;

    /**
     * The route selection descriptor is for the session with IPV4 type.
     */
    public static final int SESSION_TYPE_IPV4 = 0;

    /**
     * The route selection descriptor is for the session with both IP and IPV6 types.
     */
    public static final int SESSION_TYPE_IPV4V6 = 2;

    /**
     * The route selection descriptor is for the session with IPV6 type.
     */
    public static final int SESSION_TYPE_IPV6 = 1;

    private RouteSelectionDescriptor() {
    }
}
