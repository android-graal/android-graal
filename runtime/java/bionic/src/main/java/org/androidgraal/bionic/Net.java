package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.type.CCharPointer;

@CContext(BionicDirectives.class)
public final class Net {

    private Net() {
    }

    @CConstant
    public static native int ARPHRD_ETHER();

    @CConstant
    public static native int IFF_ALLMULTI();

    @CConstant
    public static native int IFF_AUTOMEDIA();

    @CConstant
    public static native int IFF_BROADCAST();

    @CConstant
    public static native int IFF_DEBUG();

    @CConstant
    public static native int IFF_DYNAMIC();

    @CConstant
    public static native int IFF_LOOPBACK();

    @CConstant
    public static native int IFF_MASTER();

    @CConstant
    public static native int IFF_MULTICAST();

    @CConstant
    public static native int IFF_NOARP();

    @CConstant
    public static native int IFF_NOTRAILERS();

    @CConstant
    public static native int IFF_POINTOPOINT();

    @CConstant
    public static native int IFF_PORTSEL();

    @CConstant
    public static native int IFF_PROMISC();

    @CConstant
    public static native int IFF_RUNNING();

    @CConstant
    public static native int IFF_SLAVE();

    @CConstant
    public static native int IFF_UP();

    @CConstant
    public static native int IF_NAMESIZE();

    @CFunction
    public static native CCharPointer if_indextoname(int index, CCharPointer name);

    @CFunction
    public static native int if_nametoindex(CCharPointer name);
}
