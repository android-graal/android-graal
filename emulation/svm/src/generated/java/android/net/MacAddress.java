package android.net;

import android.os.Parcelable;

/**
 * Representation of a MAC address.
 *
 * This class only supports 48 bits long addresses and does not support 64 bits long addresses.
 * Instances of this class are immutable. This class provides implementations of hashCode()
 * and equals() that make it suitable for use as keys in standard implementations of
 * {@link java.util.Map}.
 */
public final class MacAddress implements Parcelable {
    /**
     * Indicates a MAC address is the broadcast address.
     */
    public static final int TYPE_BROADCAST = 3;

    /**
     * Indicates a MAC address is a multicast address.
     */
    public static final int TYPE_MULTICAST = 2;

    /**
     * Indicates a MAC address is a unicast address.
     */
    public static final int TYPE_UNICAST = 1;

    private MacAddress() {
    }
}
