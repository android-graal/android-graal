package org.androidgraal.android;

import android.system.VmSocketAddress;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public final class SocketAddresses {

    private SocketAddresses() {
    }

    public static void fill(InetSocketAddress address, InetAddress addr, int port) {
        Target_java_net_InetSocketAddress_InetSocketAddressHolder holder = ((Target_java_net_InetSocketAddress) (Object) address).holder;
        holder.addr = addr;
        holder.port = port;
    }

    public static void fill(VmSocketAddress address, int svmPort, int svmCid) {
        Target_android_system_VmSocketAddress target = (Target_android_system_VmSocketAddress) (Object) address;
        target.svmPort = svmPort;
        target.svmCid = svmCid;
    }
}
