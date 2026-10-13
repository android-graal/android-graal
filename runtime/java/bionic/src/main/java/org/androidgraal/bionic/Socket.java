package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.nativeimage.c.type.CIntPointer;
import org.graalvm.word.PointerBase;
import org.graalvm.word.UnsignedWord;

@CContext(BionicDirectives.class)
public final class Socket {

    private Socket() {
    }

    @CConstant
    public static native int AF_INET();

    @CConstant
    public static native int AF_INET6();

    @CConstant
    public static native int AF_NETLINK();

    @CConstant
    public static native int AF_PACKET();

    @CConstant
    public static native int AF_UNIX();

    @CConstant
    public static native int AF_UNSPEC();

    @CConstant
    public static native int AF_VSOCK();

    @CConstant
    public static native int MSG_CTRUNC();

    @CConstant
    public static native int MSG_DONTROUTE();

    @CConstant
    public static native int MSG_EOR();

    @CConstant
    public static native int MSG_OOB();

    @CConstant
    public static native int MSG_PEEK();

    @CConstant
    public static native int MSG_TRUNC();

    @CConstant
    public static native int MSG_WAITALL();

    @CConstant
    public static native int SHUT_RD();

    @CConstant
    public static native int SHUT_RDWR();

    @CConstant
    public static native int SHUT_WR();

    @CConstant
    public static native int SIOCGIFADDR();

    @CConstant
    public static native int SIOCGIFBRDADDR();

    @CConstant
    public static native int SIOCGIFDSTADDR();

    @CConstant
    public static native int SIOCGIFNETMASK();

    @CConstant
    public static native int SOCK_CLOEXEC();

    @CConstant
    public static native int SOCK_DGRAM();

    @CConstant
    public static native int SOCK_NONBLOCK();

    @CConstant
    public static native int SOCK_RAW();

    @CConstant
    public static native int SOCK_SEQPACKET();

    @CConstant
    public static native int SOCK_STREAM();

    @CConstant
    public static native int SOL_PACKET();

    @CConstant
    public static native int SOL_SOCKET();

    @CConstant
    public static native int SOL_UDP();

    @CConstant
    public static native int SO_BINDTODEVICE();

    @CConstant
    public static native int SO_BROADCAST();

    @CConstant
    public static native int SO_DEBUG();

    @CConstant
    public static native int SO_DOMAIN();

    @CConstant
    public static native int SO_DONTROUTE();

    @CConstant
    public static native int SO_ERROR();

    @CConstant
    public static native int SO_KEEPALIVE();

    @CConstant
    public static native int SO_LINGER();

    @CConstant
    public static native int SO_OOBINLINE();

    @CConstant
    public static native int SO_PASSCRED();

    @CConstant
    public static native int SO_PEERCRED();

    @CConstant
    public static native int SO_RCVBUF();

    @CConstant
    public static native int SO_RCVLOWAT();

    @CConstant
    public static native int SO_RCVTIMEO();

    @CConstant
    public static native int SO_REUSEADDR();

    @CConstant
    public static native int SO_SNDBUF();

    @CConstant
    public static native int SO_SNDLOWAT();

    @CConstant
    public static native int SO_SNDTIMEO();

    @CConstant
    public static native int SO_TYPE();

    @CStruct(addStructKeyword = true)
    public interface sockaddr extends PointerBase {
        @CField
        short sa_family();

        @CField
        void sa_family(short value);
    }

    @CStruct(addStructKeyword = true)
    public interface sockaddr_storage extends PointerBase {
        @CField
        short ss_family();

        @CField
        void ss_family(short value);
    }

    @CStruct(addStructKeyword = true)
    public interface msghdr extends PointerBase {
        @CField
        PointerBase msg_name();

        @CField
        void msg_name(PointerBase value);

        @CField
        int msg_namelen();

        @CField
        void msg_namelen(int value);

        @CField
        Uio.iovec msg_iov();

        @CField
        void msg_iov(Uio.iovec value);

        @CField
        UnsignedWord msg_iovlen();

        @CField
        void msg_iovlen(UnsignedWord value);

        @CField
        PointerBase msg_control();

        @CField
        void msg_control(PointerBase value);

        @CField
        UnsignedWord msg_controllen();

        @CField
        void msg_controllen(UnsignedWord value);

        @CField
        int msg_flags();

        @CField
        void msg_flags(int value);
    }

    @CStruct(addStructKeyword = true)
    public interface cmsghdr extends PointerBase {
        @CField
        UnsignedWord cmsg_len();

        @CField
        void cmsg_len(UnsignedWord value);

        @CField
        int cmsg_level();

        @CField
        void cmsg_level(int value);

        @CField
        int cmsg_type();

        @CField
        void cmsg_type(int value);
    }

    @CFunction
    public static native cmsghdr __cmsg_nxthdr(msghdr message, cmsghdr cmsg);

    @CFunction
    public static native int accept(int fd, sockaddr address, CIntPointer length);

    @CFunction
    public static native int bind(int fd, sockaddr address, int length);

    @CFunction
    public static native int connect(int fd, sockaddr address, int length);

    @CFunction
    public static native int getpeername(int fd, sockaddr address, CIntPointer length);

    @CFunction
    public static native int getsockname(int fd, sockaddr address, CIntPointer length);

    @CFunction
    public static native int getsockopt(int fd, int level, int option, PointerBase value, CIntPointer length);

    @CFunction
    public static native int listen(int fd, int backlog);

    @CFunction
    public static native long recvfrom(int fd, PointerBase buffer, UnsignedWord length, int flags, sockaddr address,
            CIntPointer addressLength);

    @CFunction
    public static native long recvmsg(int fd, msghdr message, int flags);

    @CFunction
    public static native long sendmsg(int fd, msghdr message, int flags);

    @CFunction
    public static native long sendto(int fd, PointerBase buffer, UnsignedWord length, int flags, sockaddr address,
            int addressLength);

    @CFunction
    public static native int setsockopt(int fd, int level, int option, PointerBase value, int length);

    @CFunction
    public static native int shutdown(int fd, int how);

    @CFunction
    public static native int socket(int domain, int type, int protocol);

    @CFunction
    public static native int socketpair(int domain, int type, int protocol, CIntPointer fds);
}
