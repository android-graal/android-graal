package org.androidgraal.android;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import org.androidgraal.hosted.annotate.Adapted;
import org.androidgraal.hosted.annotate.Premise;
import org.androidgraal.hosted.annotate.Transcribed;

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

    @Substitute
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/ojluni/src/main/java/java/net/NetworkInterface.java#L611-L633")
    @Adapted(reason = "asks the JDK's ioctl path and returns null when it fails, on every level", premise = Premise.NO_LIBCORE_JDK)
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
