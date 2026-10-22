package org.androidgraal.android;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

@TargetClass(NetworkInterface.class)
final class Target_java_net_NetworkInterface {

    @Alias
    private String name;

    @Alias
    private int index;

    @Alias
    private InetAddress[] addrs;

    @Alias
    private static native boolean isLoopback0(String name, int ind) throws SocketException;

    @Alias
    private static native byte[] getMacAddr0(byte[] inAddr, String name, int ind) throws SocketException;

    /**
     * Android's app sandbox denies the MAC-address ioctl and libcore returns null when it cannot read the address.
     */
    @Substitute
    public byte[] getHardwareAddress() throws SocketException {
        if (isLoopback0(name, index)) {
            return null;
        }
        try {
            for (InetAddress addr : addrs) {
                if (addr instanceof Inet4Address) {
                    return getMacAddr0(((Inet4Address) addr).getAddress(), name, index);
                }
            }
            return getMacAddr0(null, name, index);
        } catch (SocketException e) {
            return null;
        }
    }
}
