package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;

@CContext(BionicDirectives.class)
public final class Netlink {

    private Netlink() {
    }

    @CConstant
    public static native int NETLINK_INET_DIAG();

    @CConstant
    public static native int NETLINK_NETFILTER();

    @CConstant
    public static native int NETLINK_ROUTE();

    @CConstant
    public static native int NETLINK_XFRM();
}
