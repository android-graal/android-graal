package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CFieldAddress;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.word.PointerBase;

@CContext(BionicDirectives.class)
public final class Netinet {

    private Netinet() {
    }

    @CConstant
    public static native int ETH_P_ALL();

    @CConstant
    public static native int ETH_P_ARP();

    @CConstant
    public static native int ETH_P_IP();

    @CConstant
    public static native int ETH_P_IPV6();

    @CConstant
    public static native int ICMP6_ECHO_REPLY();

    @CConstant
    public static native int ICMP6_ECHO_REQUEST();

    @CConstant
    public static native int ICMP_ECHO();

    @CConstant
    public static native int ICMP_ECHOREPLY();

    @CConstant
    public static native int IPPROTO_ESP();

    @CConstant
    public static native int IPPROTO_ICMP();

    @CConstant
    public static native int IPPROTO_ICMPV6();

    @CConstant
    public static native int IPPROTO_IP();

    @CConstant
    public static native int IPPROTO_IPV6();

    @CConstant
    public static native int IPPROTO_RAW();

    @CConstant
    public static native int IPPROTO_TCP();

    @CConstant
    public static native int IPPROTO_UDP();

    @CConstant
    public static native int IPV6_CHECKSUM();

    @CConstant
    public static native int IPV6_MULTICAST_HOPS();

    @CConstant
    public static native int IPV6_MULTICAST_IF();

    @CConstant
    public static native int IPV6_MULTICAST_LOOP();

    @CConstant
    public static native int IPV6_PKTINFO();

    @CConstant
    public static native int IPV6_RECVDSTOPTS();

    @CConstant
    public static native int IPV6_RECVHOPLIMIT();

    @CConstant
    public static native int IPV6_RECVHOPOPTS();

    @CConstant
    public static native int IPV6_RECVPKTINFO();

    @CConstant
    public static native int IPV6_RECVRTHDR();

    @CConstant
    public static native int IPV6_RECVTCLASS();

    @CConstant
    public static native int IPV6_TCLASS();

    @CConstant
    public static native int IPV6_UNICAST_HOPS();

    @CConstant
    public static native int IPV6_V6ONLY();

    @CConstant
    public static native int IP_MULTICAST_IF();

    @CConstant
    public static native int IP_MULTICAST_LOOP();

    @CConstant
    public static native int IP_MULTICAST_TTL();

    @CConstant
    public static native int IP_TOS();

    @CConstant
    public static native int IP_TTL();

    @CConstant
    public static native int MCAST_BLOCK_SOURCE();

    @CConstant
    public static native int MCAST_JOIN_GROUP();

    @CConstant
    public static native int MCAST_JOIN_SOURCE_GROUP();

    @CConstant
    public static native int MCAST_LEAVE_GROUP();

    @CConstant
    public static native int MCAST_LEAVE_SOURCE_GROUP();

    @CConstant
    public static native int MCAST_UNBLOCK_SOURCE();

    @CConstant
    public static native int TCP_NODELAY();

    @CConstant
    public static native int TCP_USER_TIMEOUT();

    @CConstant
    public static native int UDP_GRO();

    @CConstant
    public static native int UDP_SEGMENT();

    @CStruct(addStructKeyword = true)
    public interface sockaddr_in extends PointerBase {
        @CField
        short sin_family();

        @CField
        void sin_family(short value);

        @CField
        short sin_port();

        @CField
        void sin_port(short value);

        @CFieldAddress
        CCharPointer sin_addr();
    }

    @CStruct(addStructKeyword = true)
    public interface sockaddr_in6 extends PointerBase {
        @CField
        short sin6_family();

        @CField
        void sin6_family(short value);

        @CField
        short sin6_port();

        @CField
        void sin6_port(short value);

        @CFieldAddress
        CCharPointer sin6_addr();

        @CField
        int sin6_scope_id();

        @CField
        void sin6_scope_id(int value);
    }
}
