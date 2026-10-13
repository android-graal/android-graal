/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.system;

import org.androidgraal.bionic.Capability;
import org.androidgraal.bionic.Errno;
import org.androidgraal.bionic.Fcntl;
import org.androidgraal.bionic.IfAddr;
import org.androidgraal.bionic.Ioctl;
import org.androidgraal.bionic.Mman;
import org.androidgraal.bionic.Net;
import org.androidgraal.bionic.Netdb;
import org.androidgraal.bionic.Netinet;
import org.androidgraal.bionic.Netlink;
import org.androidgraal.bionic.Netpacket;
import org.androidgraal.bionic.Poll;
import org.androidgraal.bionic.Prctl;
import org.androidgraal.bionic.Rtnetlink;
import org.androidgraal.bionic.Signal;
import org.androidgraal.bionic.Socket;
import org.androidgraal.bionic.Stat;
import org.androidgraal.bionic.Statvfs;
import org.androidgraal.bionic.Stdlib;
import org.androidgraal.bionic.Unistd;
import org.androidgraal.bionic.Vm;
import org.androidgraal.bionic.Wait;
import org.androidgraal.hosted.annotate.Emulated;

/**
 * Constants and helper functions for use with {@link Os}.
 */
@Emulated
public final class OsConstants {
    private OsConstants() {
    }

    /**
     * Tests whether the given mode is a block device.
     */
    public static boolean S_ISBLK(int mode) { return (mode & S_IFMT) == S_IFBLK; }

    /**
     * Tests whether the given mode is a character device.
     */
    public static boolean S_ISCHR(int mode) { return (mode & S_IFMT) == S_IFCHR; }

    /**
     * Tests whether the given mode is a directory.
     */
    public static boolean S_ISDIR(int mode) { return (mode & S_IFMT) == S_IFDIR; }

    /**
     * Tests whether the given mode is a FIFO.
     */
    public static boolean S_ISFIFO(int mode) { return (mode & S_IFMT) == S_IFIFO; }

    /**
     * Tests whether the given mode is a regular file.
     */
    public static boolean S_ISREG(int mode) { return (mode & S_IFMT) == S_IFREG; }

    /**
     * Tests whether the given mode is a symbolic link.
     */
    public static boolean S_ISLNK(int mode) { return (mode & S_IFMT) == S_IFLNK; }

    /**
     * Tests whether the given mode is a socket.
     */
    public static boolean S_ISSOCK(int mode) { return (mode & S_IFMT) == S_IFSOCK; }

    /**
     * Extracts the exit status of a child. Only valid if WIFEXITED returns true.
     */
    public static int WEXITSTATUS(int status) { return (status & 0xff00) >> 8; }

    /**
     * Tests whether the child dumped core. Only valid if WIFSIGNALED returns true.
     */
    public static boolean WCOREDUMP(int status) { return (status & 0x80) != 0; }

    /**
     * Returns the signal that caused the child to exit. Only valid if WIFSIGNALED returns true.
     */
    public static int WTERMSIG(int status) { return status & 0x7f; }

    /**
     * Returns the signal that cause the child to stop. Only valid if WIFSTOPPED returns true.
     */
    public static int WSTOPSIG(int status) { return WEXITSTATUS(status); }

    /**
     * Tests whether the child exited normally.
     */
    public static boolean WIFEXITED(int status) { return (WTERMSIG(status) == 0); }

    /**
     * Tests whether the child was stopped (not terminated) by a signal.
     */
    public static boolean WIFSTOPPED(int status) { return (WTERMSIG(status) == 0x7f); }

    /**
     * Tests whether the child was terminated by a signal.
     */
    public static boolean WIFSIGNALED(int status) { return (WTERMSIG(status + 1) >= 2); }

    public static final int AF_INET = Socket.AF_INET();
    public static final int AF_INET6 = Socket.AF_INET6();
    public static final int AF_NETLINK = Socket.AF_NETLINK();
    public static final int AF_PACKET = Socket.AF_PACKET();
    public static final int AF_UNIX = Socket.AF_UNIX();

    /**
     * The virt-vsock address family, linux specific.
     * It is used with {@code struct sockaddr_vm} from uapi/linux/vm_sockets.h.
     *
     * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
     * @see VmSocketAddress
     */
    public static final int AF_VSOCK = Socket.AF_VSOCK();
    public static final int AF_UNSPEC = Socket.AF_UNSPEC();
    public static final int AI_ADDRCONFIG = Netdb.AI_ADDRCONFIG();
    public static final int AI_ALL = Netdb.AI_ALL();
    public static final int AI_CANONNAME = Netdb.AI_CANONNAME();
    public static final int AI_NUMERICHOST = Netdb.AI_NUMERICHOST();
    public static final int AI_NUMERICSERV = Netdb.AI_NUMERICSERV();
    public static final int AI_PASSIVE = Netdb.AI_PASSIVE();
    public static final int AI_V4MAPPED = Netdb.AI_V4MAPPED();
    public static final int ARPHRD_ETHER = Net.ARPHRD_ETHER();

    /**
      * The virtio-vsock {@code svmPort} value to bind for any available port.
      *
      * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
      * @see VmSocketAddress
      */
    public static final int VMADDR_PORT_ANY = Vm.VMADDR_PORT_ANY();

    /**
      * The virtio-vsock {@code svmCid} value to listens for all CIDs.
      *
      * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
      * @see VmSocketAddress
      */
    public static final int VMADDR_CID_ANY = Vm.VMADDR_CID_ANY();

    /**
      * The virtio-vsock {@code svmCid} value for host communication.
      *
      * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
      * @see VmSocketAddress
      */
    public static final int VMADDR_CID_LOCAL = Vm.VMADDR_CID_LOCAL();

    /**
      * The virtio-vsock {@code svmCid} value for loopback communication.
      *
      * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
      * @see VmSocketAddress
      */
    public static final int VMADDR_CID_HOST = Vm.VMADDR_CID_HOST();
    public static final int CAP_AUDIT_CONTROL = Capability.CAP_AUDIT_CONTROL();
    public static final int CAP_AUDIT_WRITE = Capability.CAP_AUDIT_WRITE();
    public static final int CAP_BLOCK_SUSPEND = Capability.CAP_BLOCK_SUSPEND();
    public static final int CAP_CHOWN = Capability.CAP_CHOWN();
    public static final int CAP_DAC_OVERRIDE = Capability.CAP_DAC_OVERRIDE();
    public static final int CAP_DAC_READ_SEARCH = Capability.CAP_DAC_READ_SEARCH();
    public static final int CAP_FOWNER = Capability.CAP_FOWNER();
    public static final int CAP_FSETID = Capability.CAP_FSETID();
    public static final int CAP_IPC_LOCK = Capability.CAP_IPC_LOCK();
    public static final int CAP_IPC_OWNER = Capability.CAP_IPC_OWNER();
    public static final int CAP_KILL = Capability.CAP_KILL();
    public static final int CAP_LAST_CAP = Capability.CAP_LAST_CAP();
    public static final int CAP_LEASE = Capability.CAP_LEASE();
    public static final int CAP_LINUX_IMMUTABLE = Capability.CAP_LINUX_IMMUTABLE();
    public static final int CAP_MAC_ADMIN = Capability.CAP_MAC_ADMIN();
    public static final int CAP_MAC_OVERRIDE = Capability.CAP_MAC_OVERRIDE();
    public static final int CAP_MKNOD = Capability.CAP_MKNOD();
    public static final int CAP_NET_ADMIN = Capability.CAP_NET_ADMIN();
    public static final int CAP_NET_BIND_SERVICE = Capability.CAP_NET_BIND_SERVICE();
    public static final int CAP_NET_BROADCAST = Capability.CAP_NET_BROADCAST();
    public static final int CAP_NET_RAW = Capability.CAP_NET_RAW();
    public static final int CAP_SETFCAP = Capability.CAP_SETFCAP();
    public static final int CAP_SETGID = Capability.CAP_SETGID();
    public static final int CAP_SETPCAP = Capability.CAP_SETPCAP();
    public static final int CAP_SETUID = Capability.CAP_SETUID();
    public static final int CAP_SYS_ADMIN = Capability.CAP_SYS_ADMIN();
    public static final int CAP_SYS_BOOT = Capability.CAP_SYS_BOOT();
    public static final int CAP_SYS_CHROOT = Capability.CAP_SYS_CHROOT();
    public static final int CAP_SYSLOG = Capability.CAP_SYSLOG();
    public static final int CAP_SYS_MODULE = Capability.CAP_SYS_MODULE();
    public static final int CAP_SYS_NICE = Capability.CAP_SYS_NICE();
    public static final int CAP_SYS_PACCT = Capability.CAP_SYS_PACCT();
    public static final int CAP_SYS_PTRACE = Capability.CAP_SYS_PTRACE();
    public static final int CAP_SYS_RAWIO = Capability.CAP_SYS_RAWIO();
    public static final int CAP_SYS_RESOURCE = Capability.CAP_SYS_RESOURCE();
    public static final int CAP_SYS_TIME = Capability.CAP_SYS_TIME();
    public static final int CAP_SYS_TTY_CONFIG = Capability.CAP_SYS_TTY_CONFIG();
    public static final int CAP_WAKE_ALARM = Capability.CAP_WAKE_ALARM();
    public static final int E2BIG = Errno.E2BIG();
    public static final int EACCES = Errno.EACCES();
    public static final int EADDRINUSE = Errno.EADDRINUSE();
    public static final int EADDRNOTAVAIL = Errno.EADDRNOTAVAIL();
    public static final int EAFNOSUPPORT = Errno.EAFNOSUPPORT();
    public static final int EAGAIN = Errno.EAGAIN();
    public static final int EAI_AGAIN = Netdb.EAI_AGAIN();
    public static final int EAI_BADFLAGS = Netdb.EAI_BADFLAGS();
    public static final int EAI_FAIL = Netdb.EAI_FAIL();
    public static final int EAI_FAMILY = Netdb.EAI_FAMILY();
    public static final int EAI_MEMORY = Netdb.EAI_MEMORY();
    public static final int EAI_NODATA = Netdb.EAI_NODATA();
    public static final int EAI_NONAME = Netdb.EAI_NONAME();
    public static final int EAI_OVERFLOW = Netdb.EAI_OVERFLOW();
    public static final int EAI_SERVICE = Netdb.EAI_SERVICE();
    public static final int EAI_SOCKTYPE = Netdb.EAI_SOCKTYPE();
    public static final int EAI_SYSTEM = Netdb.EAI_SYSTEM();
    public static final int EALREADY = Errno.EALREADY();
    public static final int EBADF = Errno.EBADF();
    public static final int EBADMSG = Errno.EBADMSG();
    public static final int EBUSY = Errno.EBUSY();
    public static final int ECANCELED = Errno.ECANCELED();
    public static final int ECHILD = Errno.ECHILD();
    public static final int ECONNABORTED = Errno.ECONNABORTED();
    public static final int ECONNREFUSED = Errno.ECONNREFUSED();
    public static final int ECONNRESET = Errno.ECONNRESET();
    public static final int EDEADLK = Errno.EDEADLK();
    public static final int EDESTADDRREQ = Errno.EDESTADDRREQ();
    public static final int EDOM = Errno.EDOM();
    public static final int EDQUOT = Errno.EDQUOT();
    public static final int EEXIST = Errno.EEXIST();
    public static final int EFAULT = Errno.EFAULT();
    public static final int EFBIG = Errno.EFBIG();
    public static final int EHOSTUNREACH = Errno.EHOSTUNREACH();
    public static final int EIDRM = Errno.EIDRM();
    public static final int EILSEQ = Errno.EILSEQ();
    public static final int EINPROGRESS = Errno.EINPROGRESS();
    public static final int EINTR = Errno.EINTR();
    public static final int EINVAL = Errno.EINVAL();
    public static final int EIO = Errno.EIO();
    public static final int EISCONN = Errno.EISCONN();
    public static final int EISDIR = Errno.EISDIR();
    public static final int ELOOP = Errno.ELOOP();
    public static final int EMFILE = Errno.EMFILE();
    public static final int EMLINK = Errno.EMLINK();
    public static final int EMSGSIZE = Errno.EMSGSIZE();
    public static final int EMULTIHOP = Errno.EMULTIHOP();
    public static final int ENAMETOOLONG = Errno.ENAMETOOLONG();
    public static final int ENETDOWN = Errno.ENETDOWN();
    public static final int ENETRESET = Errno.ENETRESET();
    public static final int ENETUNREACH = Errno.ENETUNREACH();
    public static final int ENFILE = Errno.ENFILE();
    public static final int ENOBUFS = Errno.ENOBUFS();
    public static final int ENODATA = Errno.ENODATA();
    public static final int ENODEV = Errno.ENODEV();
    public static final int ENOENT = Errno.ENOENT();
    public static final int ENOEXEC = Errno.ENOEXEC();
    public static final int ENOLCK = Errno.ENOLCK();
    public static final int ENOLINK = Errno.ENOLINK();
    public static final int ENOMEM = Errno.ENOMEM();
    public static final int ENOMSG = Errno.ENOMSG();
    public static final int ENONET = Errno.ENONET();
    public static final int ENOPROTOOPT = Errno.ENOPROTOOPT();
    public static final int ENOSPC = Errno.ENOSPC();
    public static final int ENOSR = Errno.ENOSR();
    public static final int ENOSTR = Errno.ENOSTR();
    public static final int ENOSYS = Errno.ENOSYS();
    public static final int ENOTCONN = Errno.ENOTCONN();
    public static final int ENOTDIR = Errno.ENOTDIR();
    public static final int ENOTEMPTY = Errno.ENOTEMPTY();
    public static final int ENOTSOCK = Errno.ENOTSOCK();
    public static final int ENOTSUP = Errno.ENOTSUP();
    public static final int ENOTTY = Errno.ENOTTY();
    public static final int ENXIO = Errno.ENXIO();
    public static final int EOPNOTSUPP = Errno.EOPNOTSUPP();
    public static final int EOVERFLOW = Errno.EOVERFLOW();
    public static final int EPERM = Errno.EPERM();
    public static final int EPIPE = Errno.EPIPE();
    public static final int EPROTO = Errno.EPROTO();
    public static final int EPROTONOSUPPORT = Errno.EPROTONOSUPPORT();
    public static final int EPROTOTYPE = Errno.EPROTOTYPE();
    public static final int ERANGE = Errno.ERANGE();
    public static final int EROFS = Errno.EROFS();
    public static final int ESPIPE = Errno.ESPIPE();
    public static final int ESRCH = Errno.ESRCH();
    public static final int ESTALE = Errno.ESTALE();
    public static final int ETH_P_ALL = Netinet.ETH_P_ALL();
    public static final int ETH_P_ARP = Netinet.ETH_P_ARP();
    public static final int ETH_P_IP = Netinet.ETH_P_IP();
    public static final int ETH_P_IPV6 = Netinet.ETH_P_IPV6();
    public static final int ETIME = Errno.ETIME();
    public static final int ETIMEDOUT = Errno.ETIMEDOUT();
    public static final int ETXTBSY = Errno.ETXTBSY();
    // On Linux, EWOULDBLOCK == EAGAIN. Use EAGAIN instead, to reduce confusion.
    public static final int EXDEV = Errno.EXDEV();
    public static final int EXIT_FAILURE = Stdlib.EXIT_FAILURE();
    public static final int EXIT_SUCCESS = Stdlib.EXIT_SUCCESS();
    public static final int FD_CLOEXEC = Fcntl.FD_CLOEXEC();
    public static final int FIONREAD = Ioctl.FIONREAD();
    public static final int F_DUPFD = Fcntl.F_DUPFD();
    public static final int F_DUPFD_CLOEXEC = Fcntl.F_DUPFD_CLOEXEC();
    public static final int F_GETFD = Fcntl.F_GETFD();
    public static final int F_GETFL = Fcntl.F_GETFL();
    public static final int F_GETLK = Fcntl.F_GETLK();
    public static final int F_GETLK64 = Fcntl.F_GETLK64();
    public static final int F_GETOWN = Fcntl.F_GETOWN();
    public static final int F_OK = Unistd.F_OK();
    public static final int F_RDLCK = Fcntl.F_RDLCK();
    public static final int F_SETFD = Fcntl.F_SETFD();
    public static final int F_SETFL = Fcntl.F_SETFL();
    public static final int F_SETLK = Fcntl.F_SETLK();
    public static final int F_SETLK64 = Fcntl.F_SETLK64();
    public static final int F_SETLKW = Fcntl.F_SETLKW();
    public static final int F_SETLKW64 = Fcntl.F_SETLKW64();
    public static final int F_SETOWN = Fcntl.F_SETOWN();
    public static final int F_UNLCK = Fcntl.F_UNLCK();
    public static final int F_WRLCK = Fcntl.F_WRLCK();
    public static final int ICMP_ECHO = Netinet.ICMP_ECHO();
    public static final int ICMP_ECHOREPLY = Netinet.ICMP_ECHOREPLY();
    public static final int ICMP6_ECHO_REQUEST = Netinet.ICMP6_ECHO_REQUEST();
    public static final int ICMP6_ECHO_REPLY = Netinet.ICMP6_ECHO_REPLY();
    public static final int IFA_F_DADFAILED = IfAddr.IFA_F_DADFAILED();
    public static final int IFA_F_DEPRECATED = IfAddr.IFA_F_DEPRECATED();
    public static final int IFA_F_HOMEADDRESS = IfAddr.IFA_F_HOMEADDRESS();
    public static final int IFA_F_MANAGETEMPADDR = IfAddr.IFA_F_MANAGETEMPADDR();
    public static final int IFA_F_NODAD = IfAddr.IFA_F_NODAD();
    public static final int IFA_F_NOPREFIXROUTE = IfAddr.IFA_F_NOPREFIXROUTE();
    public static final int IFA_F_OPTIMISTIC = IfAddr.IFA_F_OPTIMISTIC();
    public static final int IFA_F_PERMANENT = IfAddr.IFA_F_PERMANENT();
    public static final int IFA_F_SECONDARY = IfAddr.IFA_F_SECONDARY();
    public static final int IFA_F_TEMPORARY = IfAddr.IFA_F_TEMPORARY();
    public static final int IFA_F_TENTATIVE = IfAddr.IFA_F_TENTATIVE();
    public static final int IFF_ALLMULTI = Net.IFF_ALLMULTI();
    public static final int IFF_AUTOMEDIA = Net.IFF_AUTOMEDIA();
    public static final int IFF_BROADCAST = Net.IFF_BROADCAST();
    public static final int IFF_DEBUG = Net.IFF_DEBUG();
    public static final int IFF_DYNAMIC = Net.IFF_DYNAMIC();
    public static final int IFF_LOOPBACK = Net.IFF_LOOPBACK();
    public static final int IFF_MASTER = Net.IFF_MASTER();
    public static final int IFF_MULTICAST = Net.IFF_MULTICAST();
    public static final int IFF_NOARP = Net.IFF_NOARP();
    public static final int IFF_NOTRAILERS = Net.IFF_NOTRAILERS();
    public static final int IFF_POINTOPOINT = Net.IFF_POINTOPOINT();
    public static final int IFF_PORTSEL = Net.IFF_PORTSEL();
    public static final int IFF_PROMISC = Net.IFF_PROMISC();
    public static final int IFF_RUNNING = Net.IFF_RUNNING();
    public static final int IFF_SLAVE = Net.IFF_SLAVE();
    public static final int IFF_UP = Net.IFF_UP();
    public static final int IPPROTO_ICMP = Netinet.IPPROTO_ICMP();
    public static final int IPPROTO_ICMPV6 = Netinet.IPPROTO_ICMPV6();
    public static final int IPPROTO_IP = Netinet.IPPROTO_IP();
    public static final int IPPROTO_IPV6 = Netinet.IPPROTO_IPV6();
    public static final int IPPROTO_RAW = Netinet.IPPROTO_RAW();
    public static final int IPPROTO_TCP = Netinet.IPPROTO_TCP();
    public static final int IPPROTO_UDP = Netinet.IPPROTO_UDP();

    /**
     * Encapsulation Security Payload protocol
     *
     * <p>Defined in /uapi/linux/in.h
     */
    public static final int IPPROTO_ESP = Netinet.IPPROTO_ESP();

    public static final int IPV6_CHECKSUM = Netinet.IPV6_CHECKSUM();
    public static final int IPV6_MULTICAST_HOPS = Netinet.IPV6_MULTICAST_HOPS();
    public static final int IPV6_MULTICAST_IF = Netinet.IPV6_MULTICAST_IF();
    public static final int IPV6_MULTICAST_LOOP = Netinet.IPV6_MULTICAST_LOOP();
    public static final int IPV6_PKTINFO = Netinet.IPV6_PKTINFO();
    public static final int IPV6_RECVDSTOPTS = Netinet.IPV6_RECVDSTOPTS();
    public static final int IPV6_RECVHOPLIMIT = Netinet.IPV6_RECVHOPLIMIT();
    public static final int IPV6_RECVHOPOPTS = Netinet.IPV6_RECVHOPOPTS();
    public static final int IPV6_RECVPKTINFO = Netinet.IPV6_RECVPKTINFO();
    public static final int IPV6_RECVRTHDR = Netinet.IPV6_RECVRTHDR();
    public static final int IPV6_RECVTCLASS = Netinet.IPV6_RECVTCLASS();
    public static final int IPV6_TCLASS = Netinet.IPV6_TCLASS();
    public static final int IPV6_UNICAST_HOPS = Netinet.IPV6_UNICAST_HOPS();
    public static final int IPV6_V6ONLY = Netinet.IPV6_V6ONLY();
    public static final int IP_MULTICAST_IF = Netinet.IP_MULTICAST_IF();
    public static final int IP_MULTICAST_LOOP = Netinet.IP_MULTICAST_LOOP();
    public static final int IP_MULTICAST_TTL = Netinet.IP_MULTICAST_TTL();
    public static final int IP_TOS = Netinet.IP_TOS();
    public static final int IP_TTL = Netinet.IP_TTL();
    public static final int MAP_FIXED = Mman.MAP_FIXED();
    public static final int MAP_ANONYMOUS = Mman.MAP_ANONYMOUS();
    public static final int MAP_PRIVATE = Mman.MAP_PRIVATE();
    public static final int MAP_SHARED = Mman.MAP_SHARED();
    public static final int MCAST_JOIN_GROUP = Netinet.MCAST_JOIN_GROUP();
    public static final int MCAST_LEAVE_GROUP = Netinet.MCAST_LEAVE_GROUP();
    public static final int MCAST_JOIN_SOURCE_GROUP = Netinet.MCAST_JOIN_SOURCE_GROUP();
    public static final int MCAST_LEAVE_SOURCE_GROUP = Netinet.MCAST_LEAVE_SOURCE_GROUP();
    public static final int MCAST_BLOCK_SOURCE = Netinet.MCAST_BLOCK_SOURCE();
    public static final int MCAST_UNBLOCK_SOURCE = Netinet.MCAST_UNBLOCK_SOURCE();
    public static final int MCL_CURRENT = Mman.MCL_CURRENT();
    public static final int MCL_FUTURE = Mman.MCL_FUTURE();
    public static final int MFD_CLOEXEC = Mman.MFD_CLOEXEC();
    public static final int MSG_CTRUNC = Socket.MSG_CTRUNC();
    public static final int MSG_DONTROUTE = Socket.MSG_DONTROUTE();
    public static final int MSG_EOR = Socket.MSG_EOR();
    public static final int MSG_OOB = Socket.MSG_OOB();
    public static final int MSG_PEEK = Socket.MSG_PEEK();
    public static final int MSG_TRUNC = Socket.MSG_TRUNC();
    public static final int MSG_WAITALL = Socket.MSG_WAITALL();
    public static final int MS_ASYNC = Mman.MS_ASYNC();
    public static final int MS_INVALIDATE = Mman.MS_INVALIDATE();
    public static final int MS_SYNC = Mman.MS_SYNC();
    public static final int NETLINK_NETFILTER = Netlink.NETLINK_NETFILTER();
    public static final int NETLINK_ROUTE = Netlink.NETLINK_ROUTE();
    /**
     * SELinux enforces that only system_server and netd may use this netlink socket type.
     */
    public static final int NETLINK_INET_DIAG = Netlink.NETLINK_INET_DIAG();

    /**
     * SELinux enforces that only system_server and netd may use this netlink socket type.
     *
     * @see <a href="https://man7.org/linux/man-pages/man7/netlink.7.html">netlink(7)</a>
     */
    public static final int NETLINK_XFRM = Netlink.NETLINK_XFRM();

    public static final int NI_DGRAM = Netdb.NI_DGRAM();
    public static final int NI_NAMEREQD = Netdb.NI_NAMEREQD();
    public static final int NI_NOFQDN = Netdb.NI_NOFQDN();
    public static final int NI_NUMERICHOST = Netdb.NI_NUMERICHOST();
    public static final int NI_NUMERICSERV = Netdb.NI_NUMERICSERV();
    public static final int O_ACCMODE = Fcntl.O_ACCMODE();
    public static final int O_APPEND = Fcntl.O_APPEND();
    public static final int O_CLOEXEC = Fcntl.O_CLOEXEC();
    public static final int O_CREAT = Fcntl.O_CREAT();
    public static final int O_EXCL = Fcntl.O_EXCL();
    public static final int O_NOCTTY = Fcntl.O_NOCTTY();
    public static final int O_NOFOLLOW = Fcntl.O_NOFOLLOW();
    public static final int O_NONBLOCK = Fcntl.O_NONBLOCK();
    public static final int O_RDONLY = Fcntl.O_RDONLY();
    public static final int O_RDWR = Fcntl.O_RDWR();
    public static final int O_SYNC = Fcntl.O_SYNC();
    public static final int O_DSYNC = Fcntl.O_DSYNC();
    public static final int O_TRUNC = Fcntl.O_TRUNC();
    public static final int O_WRONLY = Fcntl.O_WRONLY();
    public static final int POLLERR = Poll.POLLERR();
    public static final int POLLHUP = Poll.POLLHUP();
    public static final int POLLIN = Poll.POLLIN();
    public static final int POLLNVAL = Poll.POLLNVAL();
    public static final int POLLOUT = Poll.POLLOUT();
    public static final int POLLPRI = Poll.POLLPRI();
    public static final int POLLRDBAND = Poll.POLLRDBAND();
    public static final int POLLRDNORM = Poll.POLLRDNORM();
    public static final int POLLWRBAND = Poll.POLLWRBAND();
    public static final int POLLWRNORM = Poll.POLLWRNORM();
    public static final int PR_GET_DUMPABLE = Prctl.PR_GET_DUMPABLE();
    public static final int PR_SET_DUMPABLE = Prctl.PR_SET_DUMPABLE();
    public static final int PR_SET_NO_NEW_PRIVS = Prctl.PR_SET_NO_NEW_PRIVS();
    public static final int PROT_EXEC = Mman.PROT_EXEC();
    public static final int PROT_NONE = Mman.PROT_NONE();
    public static final int PROT_READ = Mman.PROT_READ();
    public static final int PROT_WRITE = Mman.PROT_WRITE();
    public static final int R_OK = Unistd.R_OK();
    public static final int RT_SCOPE_HOST = Rtnetlink.RT_SCOPE_HOST();
    public static final int RT_SCOPE_LINK = Rtnetlink.RT_SCOPE_LINK();
    public static final int RT_SCOPE_NOWHERE = Rtnetlink.RT_SCOPE_NOWHERE();
    public static final int RT_SCOPE_SITE = Rtnetlink.RT_SCOPE_SITE();
    public static final int RT_SCOPE_UNIVERSE = Rtnetlink.RT_SCOPE_UNIVERSE();
    public static final int RTMGRP_NEIGH = Rtnetlink.RTMGRP_NEIGH();
    public static final int SEEK_CUR = Unistd.SEEK_CUR();
    public static final int SEEK_END = Unistd.SEEK_END();
    public static final int SEEK_SET = Unistd.SEEK_SET();
    public static final int SHUT_RD = Socket.SHUT_RD();
    public static final int SHUT_RDWR = Socket.SHUT_RDWR();
    public static final int SHUT_WR = Socket.SHUT_WR();
    public static final int SIGABRT = Signal.SIGABRT();
    public static final int SIGALRM = Signal.SIGALRM();
    public static final int SIGBUS = Signal.SIGBUS();
    public static final int SIGCHLD = Signal.SIGCHLD();
    public static final int SIGCONT = Signal.SIGCONT();
    public static final int SIGFPE = Signal.SIGFPE();
    public static final int SIGHUP = Signal.SIGHUP();
    public static final int SIGILL = Signal.SIGILL();
    public static final int SIGINT = Signal.SIGINT();
    public static final int SIGIO = Signal.SIGIO();
    public static final int SIGKILL = Signal.SIGKILL();
    public static final int SIGPIPE = Signal.SIGPIPE();
    public static final int SIGPROF = Signal.SIGPROF();
    public static final int SIGPWR = Signal.SIGPWR();
    public static final int SIGQUIT = Signal.SIGQUIT();
    public static final int SIGRTMAX = Signal.SIGRTMAX();
    public static final int SIGRTMIN = Signal.SIGRTMIN();
    public static final int SIGSEGV = Signal.SIGSEGV();
    public static final int SIGSTKFLT = Signal.SIGSTKFLT();
    public static final int SIGSTOP = Signal.SIGSTOP();
    public static final int SIGSYS = Signal.SIGSYS();
    public static final int SIGTERM = Signal.SIGTERM();
    public static final int SIGTRAP = Signal.SIGTRAP();
    public static final int SIGTSTP = Signal.SIGTSTP();
    public static final int SIGTTIN = Signal.SIGTTIN();
    public static final int SIGTTOU = Signal.SIGTTOU();
    public static final int SIGURG = Signal.SIGURG();
    public static final int SIGUSR1 = Signal.SIGUSR1();
    public static final int SIGUSR2 = Signal.SIGUSR2();
    public static final int SIGVTALRM = Signal.SIGVTALRM();
    public static final int SIGWINCH = Signal.SIGWINCH();
    public static final int SIGXCPU = Signal.SIGXCPU();
    public static final int SIGXFSZ = Signal.SIGXFSZ();
    public static final int SIOCGIFADDR = Socket.SIOCGIFADDR();
    public static final int SIOCGIFBRDADDR = Socket.SIOCGIFBRDADDR();
    public static final int SIOCGIFDSTADDR = Socket.SIOCGIFDSTADDR();
    public static final int SIOCGIFNETMASK = Socket.SIOCGIFNETMASK();

    /**
     * Set the close-on-exec ({@code FD_CLOEXEC}) flag on the new file
     * descriptor created by {@link Os#socket(int,int,int)} or
     * {@link Os#socketpair(int,int,int,java.io.FileDescriptor,java.io.FileDescriptor)}.
     * See the description of the O_CLOEXEC flag in
     * <a href="http://man7.org/linux/man-pages/man2/open.2.html">open(2)</a>
     * for reasons why this may be useful.
     *
     * <p>Applications wishing to make use of this flag on older API versions
     * may use {@link #O_CLOEXEC} instead. On Android, {@code O_CLOEXEC} and
     * {@code SOCK_CLOEXEC} are the same value.
     */
    public static final int SOCK_CLOEXEC = Socket.SOCK_CLOEXEC();
    public static final int SOCK_DGRAM = Socket.SOCK_DGRAM();

    /**
     * Set the O_NONBLOCK file status flag on the file descriptor
     * created by {@link Os#socket(int,int,int)} or
     * {@link Os#socketpair(int,int,int,java.io.FileDescriptor,java.io.FileDescriptor)}.
     *
     * <p>Applications wishing to make use of this flag on older API versions
     * may use {@link #O_NONBLOCK} instead. On Android, {@code O_NONBLOCK}
     * and {@code SOCK_NONBLOCK} are the same value.
     */
    public static final int SOCK_NONBLOCK = Socket.SOCK_NONBLOCK();
    public static final int SOCK_RAW = Socket.SOCK_RAW();
    public static final int SOCK_SEQPACKET = Socket.SOCK_SEQPACKET();
    public static final int SOCK_STREAM = Socket.SOCK_STREAM();
    public static final int SOL_SOCKET = Socket.SOL_SOCKET();
    public static final int SOL_UDP = Socket.SOL_UDP();
    public static final int SOL_PACKET = Socket.SOL_PACKET();
    public static final int SO_BINDTODEVICE = Socket.SO_BINDTODEVICE();
    public static final int SO_BROADCAST = Socket.SO_BROADCAST();
    public static final int SO_DEBUG = Socket.SO_DEBUG();
    public static final int SO_DONTROUTE = Socket.SO_DONTROUTE();
    public static final int SO_ERROR = Socket.SO_ERROR();
    public static final int SO_KEEPALIVE = Socket.SO_KEEPALIVE();
    public static final int SO_LINGER = Socket.SO_LINGER();
    public static final int SO_OOBINLINE = Socket.SO_OOBINLINE();
    public static final int SO_PASSCRED = Socket.SO_PASSCRED();
    public static final int SO_PEERCRED = Socket.SO_PEERCRED();
    public static final int SO_RCVBUF = Socket.SO_RCVBUF();
    public static final int SO_RCVLOWAT = Socket.SO_RCVLOWAT();
    public static final int SO_RCVTIMEO = Socket.SO_RCVTIMEO();
    public static final int SO_REUSEADDR = Socket.SO_REUSEADDR();
    public static final int SO_SNDBUF = Socket.SO_SNDBUF();
    public static final int SO_SNDLOWAT = Socket.SO_SNDLOWAT();
    public static final int SO_SNDTIMEO = Socket.SO_SNDTIMEO();
    public static final int SO_TYPE = Socket.SO_TYPE();
    public static final int PACKET_IGNORE_OUTGOING = Netpacket.PACKET_IGNORE_OUTGOING();
    public static final int STDERR_FILENO = Unistd.STDERR_FILENO();
    public static final int STDIN_FILENO = Unistd.STDIN_FILENO();
    public static final int STDOUT_FILENO = Unistd.STDOUT_FILENO();
    public static final int ST_MANDLOCK = Statvfs.ST_MANDLOCK();
    public static final int ST_NOATIME = Statvfs.ST_NOATIME();
    public static final int ST_NODEV = Statvfs.ST_NODEV();
    public static final int ST_NODIRATIME = Statvfs.ST_NODIRATIME();
    public static final int ST_NOEXEC = Statvfs.ST_NOEXEC();
    public static final int ST_NOSUID = Statvfs.ST_NOSUID();
    public static final int ST_RDONLY = Statvfs.ST_RDONLY();
    public static final int ST_RELATIME = Statvfs.ST_RELATIME();
    public static final int ST_SYNCHRONOUS = Statvfs.ST_SYNCHRONOUS();
    public static final int S_IFBLK = Stat.S_IFBLK();
    public static final int S_IFCHR = Stat.S_IFCHR();
    public static final int S_IFDIR = Stat.S_IFDIR();
    public static final int S_IFIFO = Stat.S_IFIFO();
    public static final int S_IFLNK = Stat.S_IFLNK();
    public static final int S_IFMT = Stat.S_IFMT();
    public static final int S_IFREG = Stat.S_IFREG();
    public static final int S_IFSOCK = Stat.S_IFSOCK();
    public static final int S_IRGRP = Stat.S_IRGRP();
    public static final int S_IROTH = Stat.S_IROTH();
    public static final int S_IRUSR = Stat.S_IRUSR();
    public static final int S_IRWXG = Stat.S_IRWXG();
    public static final int S_IRWXO = Stat.S_IRWXO();
    public static final int S_IRWXU = Stat.S_IRWXU();
    public static final int S_ISGID = Stat.S_ISGID();
    public static final int S_ISUID = Stat.S_ISUID();
    public static final int S_ISVTX = Stat.S_ISVTX();
    public static final int S_IWGRP = Stat.S_IWGRP();
    public static final int S_IWOTH = Stat.S_IWOTH();
    public static final int S_IWUSR = Stat.S_IWUSR();
    public static final int S_IXGRP = Stat.S_IXGRP();
    public static final int S_IXOTH = Stat.S_IXOTH();
    public static final int S_IXUSR = Stat.S_IXUSR();
    public static final int TCP_NODELAY = Netinet.TCP_NODELAY();
    public static final int TCP_USER_TIMEOUT = Netinet.TCP_USER_TIMEOUT();
    public static final int UDP_GRO = Netinet.UDP_GRO();
    public static final int UDP_SEGMENT = Netinet.UDP_SEGMENT();
    public static final int WCONTINUED = Wait.WCONTINUED();
    public static final int WEXITED = Wait.WEXITED();
    public static final int WNOHANG = Wait.WNOHANG();
    public static final int WNOWAIT = Wait.WNOWAIT();
    public static final int WSTOPPED = Wait.WSTOPPED();
    public static final int WUNTRACED = Wait.WUNTRACED();
    public static final int W_OK = Unistd.W_OK();
    public static final int X_OK = Unistd.X_OK();
    public static final int _SC_2_CHAR_TERM = Unistd._SC_2_CHAR_TERM();
    public static final int _SC_2_C_BIND = Unistd._SC_2_C_BIND();
    public static final int _SC_2_C_DEV = Unistd._SC_2_C_DEV();
    public static final int _SC_2_C_VERSION = Unistd._SC_2_C_VERSION();
    public static final int _SC_2_FORT_DEV = Unistd._SC_2_FORT_DEV();
    public static final int _SC_2_FORT_RUN = Unistd._SC_2_FORT_RUN();
    public static final int _SC_2_LOCALEDEF = Unistd._SC_2_LOCALEDEF();
    public static final int _SC_2_SW_DEV = Unistd._SC_2_SW_DEV();
    public static final int _SC_2_UPE = Unistd._SC_2_UPE();
    public static final int _SC_2_VERSION = Unistd._SC_2_VERSION();
    public static final int _SC_AIO_LISTIO_MAX = Unistd._SC_AIO_LISTIO_MAX();
    public static final int _SC_AIO_MAX = Unistd._SC_AIO_MAX();
    public static final int _SC_AIO_PRIO_DELTA_MAX = Unistd._SC_AIO_PRIO_DELTA_MAX();
    public static final int _SC_ARG_MAX = Unistd._SC_ARG_MAX();
    public static final int _SC_ASYNCHRONOUS_IO = Unistd._SC_ASYNCHRONOUS_IO();
    public static final int _SC_ATEXIT_MAX = Unistd._SC_ATEXIT_MAX();
    public static final int _SC_AVPHYS_PAGES = Unistd._SC_AVPHYS_PAGES();
    public static final int _SC_BC_BASE_MAX = Unistd._SC_BC_BASE_MAX();
    public static final int _SC_BC_DIM_MAX = Unistd._SC_BC_DIM_MAX();
    public static final int _SC_BC_SCALE_MAX = Unistd._SC_BC_SCALE_MAX();
    public static final int _SC_BC_STRING_MAX = Unistd._SC_BC_STRING_MAX();
    public static final int _SC_CHILD_MAX = Unistd._SC_CHILD_MAX();
    public static final int _SC_CLK_TCK = Unistd._SC_CLK_TCK();
    public static final int _SC_COLL_WEIGHTS_MAX = Unistd._SC_COLL_WEIGHTS_MAX();
    public static final int _SC_DELAYTIMER_MAX = Unistd._SC_DELAYTIMER_MAX();
    public static final int _SC_EXPR_NEST_MAX = Unistd._SC_EXPR_NEST_MAX();
    public static final int _SC_FSYNC = Unistd._SC_FSYNC();
    public static final int _SC_GETGR_R_SIZE_MAX = Unistd._SC_GETGR_R_SIZE_MAX();
    public static final int _SC_GETPW_R_SIZE_MAX = Unistd._SC_GETPW_R_SIZE_MAX();
    public static final int _SC_IOV_MAX = Unistd._SC_IOV_MAX();
    public static final int _SC_JOB_CONTROL = Unistd._SC_JOB_CONTROL();
    public static final int _SC_LINE_MAX = Unistd._SC_LINE_MAX();
    public static final int _SC_LOGIN_NAME_MAX = Unistd._SC_LOGIN_NAME_MAX();
    public static final int _SC_MAPPED_FILES = Unistd._SC_MAPPED_FILES();
    public static final int _SC_MEMLOCK = Unistd._SC_MEMLOCK();
    public static final int _SC_MEMLOCK_RANGE = Unistd._SC_MEMLOCK_RANGE();
    public static final int _SC_MEMORY_PROTECTION = Unistd._SC_MEMORY_PROTECTION();
    public static final int _SC_MESSAGE_PASSING = Unistd._SC_MESSAGE_PASSING();
    public static final int _SC_MQ_OPEN_MAX = Unistd._SC_MQ_OPEN_MAX();
    public static final int _SC_MQ_PRIO_MAX = Unistd._SC_MQ_PRIO_MAX();
    public static final int _SC_NGROUPS_MAX = Unistd._SC_NGROUPS_MAX();
    public static final int _SC_NPROCESSORS_CONF = Unistd._SC_NPROCESSORS_CONF();
    public static final int _SC_NPROCESSORS_ONLN = Unistd._SC_NPROCESSORS_ONLN();
    public static final int _SC_OPEN_MAX = Unistd._SC_OPEN_MAX();
    public static final int _SC_PAGESIZE = Unistd._SC_PAGESIZE();
    public static final int _SC_PAGE_SIZE = Unistd._SC_PAGE_SIZE();
    public static final int _SC_PASS_MAX = Unistd._SC_PASS_MAX();
    public static final int _SC_PHYS_PAGES = Unistd._SC_PHYS_PAGES();
    public static final int _SC_PRIORITIZED_IO = Unistd._SC_PRIORITIZED_IO();
    public static final int _SC_PRIORITY_SCHEDULING = Unistd._SC_PRIORITY_SCHEDULING();
    public static final int _SC_REALTIME_SIGNALS = Unistd._SC_REALTIME_SIGNALS();
    public static final int _SC_RE_DUP_MAX = Unistd._SC_RE_DUP_MAX();
    public static final int _SC_RTSIG_MAX = Unistd._SC_RTSIG_MAX();
    public static final int _SC_SAVED_IDS = Unistd._SC_SAVED_IDS();
    public static final int _SC_SEMAPHORES = Unistd._SC_SEMAPHORES();
    public static final int _SC_SEM_NSEMS_MAX = Unistd._SC_SEM_NSEMS_MAX();
    public static final int _SC_SEM_VALUE_MAX = Unistd._SC_SEM_VALUE_MAX();
    public static final int _SC_SHARED_MEMORY_OBJECTS = Unistd._SC_SHARED_MEMORY_OBJECTS();
    public static final int _SC_SIGQUEUE_MAX = Unistd._SC_SIGQUEUE_MAX();
    public static final int _SC_STREAM_MAX = Unistd._SC_STREAM_MAX();
    public static final int _SC_SYNCHRONIZED_IO = Unistd._SC_SYNCHRONIZED_IO();
    public static final int _SC_THREADS = Unistd._SC_THREADS();
    public static final int _SC_THREAD_ATTR_STACKADDR = Unistd._SC_THREAD_ATTR_STACKADDR();
    public static final int _SC_THREAD_ATTR_STACKSIZE = Unistd._SC_THREAD_ATTR_STACKSIZE();
    public static final int _SC_THREAD_DESTRUCTOR_ITERATIONS =
            Unistd._SC_THREAD_DESTRUCTOR_ITERATIONS();
    public static final int _SC_THREAD_KEYS_MAX = Unistd._SC_THREAD_KEYS_MAX();
    public static final int _SC_THREAD_PRIORITY_SCHEDULING =
            Unistd._SC_THREAD_PRIORITY_SCHEDULING();
    public static final int _SC_THREAD_PRIO_INHERIT = Unistd._SC_THREAD_PRIO_INHERIT();
    public static final int _SC_THREAD_PRIO_PROTECT = Unistd._SC_THREAD_PRIO_PROTECT();
    public static final int _SC_THREAD_SAFE_FUNCTIONS = Unistd._SC_THREAD_SAFE_FUNCTIONS();
    public static final int _SC_THREAD_STACK_MIN = Unistd._SC_THREAD_STACK_MIN();
    public static final int _SC_THREAD_THREADS_MAX = Unistd._SC_THREAD_THREADS_MAX();
    public static final int _SC_TIMERS = Unistd._SC_TIMERS();
    public static final int _SC_TIMER_MAX = Unistd._SC_TIMER_MAX();
    public static final int _SC_TTY_NAME_MAX = Unistd._SC_TTY_NAME_MAX();
    public static final int _SC_TZNAME_MAX = Unistd._SC_TZNAME_MAX();
    public static final int _SC_VERSION = Unistd._SC_VERSION();
    public static final int _SC_XBS5_ILP32_OFF32 = Unistd._SC_XBS5_ILP32_OFF32();
    public static final int _SC_XBS5_ILP32_OFFBIG = Unistd._SC_XBS5_ILP32_OFFBIG();
    public static final int _SC_XBS5_LP64_OFF64 = Unistd._SC_XBS5_LP64_OFF64();
    public static final int _SC_XBS5_LPBIG_OFFBIG = Unistd._SC_XBS5_LPBIG_OFFBIG();
    public static final int _SC_XOPEN_CRYPT = Unistd._SC_XOPEN_CRYPT();
    public static final int _SC_XOPEN_ENH_I18N = Unistd._SC_XOPEN_ENH_I18N();
    public static final int _SC_XOPEN_LEGACY = Unistd._SC_XOPEN_LEGACY();
    public static final int _SC_XOPEN_REALTIME = Unistd._SC_XOPEN_REALTIME();
    public static final int _SC_XOPEN_REALTIME_THREADS = Unistd._SC_XOPEN_REALTIME_THREADS();
    public static final int _SC_XOPEN_SHM = Unistd._SC_XOPEN_SHM();
    public static final int _SC_XOPEN_UNIX = Unistd._SC_XOPEN_UNIX();
    public static final int _SC_XOPEN_VERSION = Unistd._SC_XOPEN_VERSION();
    public static final int _SC_XOPEN_XCU_VERSION = Unistd._SC_XOPEN_XCU_VERSION();

    /**
     * Returns the string name of a getaddrinfo(3) error value.
     * For example, "EAI_AGAIN".
     */
    public static String gaiName(int error) {
        if (error == EAI_AGAIN) {
            return "EAI_AGAIN";
        }
        if (error == EAI_BADFLAGS) {
            return "EAI_BADFLAGS";
        }
        if (error == EAI_FAIL) {
            return "EAI_FAIL";
        }
        if (error == EAI_FAMILY) {
            return "EAI_FAMILY";
        }
        if (error == EAI_MEMORY) {
            return "EAI_MEMORY";
        }
        if (error == EAI_NODATA) {
            return "EAI_NODATA";
        }
        if (error == EAI_NONAME) {
            return "EAI_NONAME";
        }
        if (error == EAI_OVERFLOW) {
            return "EAI_OVERFLOW";
        }
        if (error == EAI_SERVICE) {
            return "EAI_SERVICE";
        }
        if (error == EAI_SOCKTYPE) {
            return "EAI_SOCKTYPE";
        }
        if (error == EAI_SYSTEM) {
            return "EAI_SYSTEM";
        }
        return null;
    }

    /**
     * Returns the string name of an errno value.
     * For example, "EACCES". See {@link Os#strerror} for human-readable errno descriptions.
     */
    public static String errnoName(int errno) {
        if (errno == E2BIG) {
            return "E2BIG";
        }
        if (errno == EACCES) {
            return "EACCES";
        }
        if (errno == EADDRINUSE) {
            return "EADDRINUSE";
        }
        if (errno == EADDRNOTAVAIL) {
            return "EADDRNOTAVAIL";
        }
        if (errno == EAFNOSUPPORT) {
            return "EAFNOSUPPORT";
        }
        if (errno == EAGAIN) {
            return "EAGAIN";
        }
        if (errno == EALREADY) {
            return "EALREADY";
        }
        if (errno == EBADF) {
            return "EBADF";
        }
        if (errno == EBADMSG) {
            return "EBADMSG";
        }
        if (errno == EBUSY) {
            return "EBUSY";
        }
        if (errno == ECANCELED) {
            return "ECANCELED";
        }
        if (errno == ECHILD) {
            return "ECHILD";
        }
        if (errno == ECONNABORTED) {
            return "ECONNABORTED";
        }
        if (errno == ECONNREFUSED) {
            return "ECONNREFUSED";
        }
        if (errno == ECONNRESET) {
            return "ECONNRESET";
        }
        if (errno == EDEADLK) {
            return "EDEADLK";
        }
        if (errno == EDESTADDRREQ) {
            return "EDESTADDRREQ";
        }
        if (errno == EDOM) {
            return "EDOM";
        }
        if (errno == EDQUOT) {
            return "EDQUOT";
        }
        if (errno == EEXIST) {
            return "EEXIST";
        }
        if (errno == EFAULT) {
            return "EFAULT";
        }
        if (errno == EFBIG) {
            return "EFBIG";
        }
        if (errno == EHOSTUNREACH) {
            return "EHOSTUNREACH";
        }
        if (errno == EIDRM) {
            return "EIDRM";
        }
        if (errno == EILSEQ) {
            return "EILSEQ";
        }
        if (errno == EINPROGRESS) {
            return "EINPROGRESS";
        }
        if (errno == EINTR) {
            return "EINTR";
        }
        if (errno == EINVAL) {
            return "EINVAL";
        }
        if (errno == EIO) {
            return "EIO";
        }
        if (errno == EISCONN) {
            return "EISCONN";
        }
        if (errno == EISDIR) {
            return "EISDIR";
        }
        if (errno == ELOOP) {
            return "ELOOP";
        }
        if (errno == EMFILE) {
            return "EMFILE";
        }
        if (errno == EMLINK) {
            return "EMLINK";
        }
        if (errno == EMSGSIZE) {
            return "EMSGSIZE";
        }
        if (errno == EMULTIHOP) {
            return "EMULTIHOP";
        }
        if (errno == ENAMETOOLONG) {
            return "ENAMETOOLONG";
        }
        if (errno == ENETDOWN) {
            return "ENETDOWN";
        }
        if (errno == ENETRESET) {
            return "ENETRESET";
        }
        if (errno == ENETUNREACH) {
            return "ENETUNREACH";
        }
        if (errno == ENFILE) {
            return "ENFILE";
        }
        if (errno == ENOBUFS) {
            return "ENOBUFS";
        }
        if (errno == ENODATA) {
            return "ENODATA";
        }
        if (errno == ENODEV) {
            return "ENODEV";
        }
        if (errno == ENOENT) {
            return "ENOENT";
        }
        if (errno == ENOEXEC) {
            return "ENOEXEC";
        }
        if (errno == ENOLCK) {
            return "ENOLCK";
        }
        if (errno == ENOLINK) {
            return "ENOLINK";
        }
        if (errno == ENOMEM) {
            return "ENOMEM";
        }
        if (errno == ENOMSG) {
            return "ENOMSG";
        }
        if (errno == ENONET) {
            return "ENONET";
        }
        if (errno == ENOPROTOOPT) {
            return "ENOPROTOOPT";
        }
        if (errno == ENOSPC) {
            return "ENOSPC";
        }
        if (errno == ENOSR) {
            return "ENOSR";
        }
        if (errno == ENOSTR) {
            return "ENOSTR";
        }
        if (errno == ENOSYS) {
            return "ENOSYS";
        }
        if (errno == ENOTCONN) {
            return "ENOTCONN";
        }
        if (errno == ENOTDIR) {
            return "ENOTDIR";
        }
        if (errno == ENOTEMPTY) {
            return "ENOTEMPTY";
        }
        if (errno == ENOTSOCK) {
            return "ENOTSOCK";
        }
        if (errno == ENOTSUP) {
            return "ENOTSUP";
        }
        if (errno == ENOTTY) {
            return "ENOTTY";
        }
        if (errno == ENXIO) {
            return "ENXIO";
        }
        if (errno == EOPNOTSUPP) {
            return "EOPNOTSUPP";
        }
        if (errno == EOVERFLOW) {
            return "EOVERFLOW";
        }
        if (errno == EPERM) {
            return "EPERM";
        }
        if (errno == EPIPE) {
            return "EPIPE";
        }
        if (errno == EPROTO) {
            return "EPROTO";
        }
        if (errno == EPROTONOSUPPORT) {
            return "EPROTONOSUPPORT";
        }
        if (errno == EPROTOTYPE) {
            return "EPROTOTYPE";
        }
        if (errno == ERANGE) {
            return "ERANGE";
        }
        if (errno == EROFS) {
            return "EROFS";
        }
        if (errno == ESPIPE) {
            return "ESPIPE";
        }
        if (errno == ESRCH) {
            return "ESRCH";
        }
        if (errno == ESTALE) {
            return "ESTALE";
        }
        if (errno == ETIME) {
            return "ETIME";
        }
        if (errno == ETIMEDOUT) {
            return "ETIMEDOUT";
        }
        if (errno == ETXTBSY) {
            return "ETXTBSY";
        }
        if (errno == EXDEV) {
            return "EXDEV";
        }
        return null;
    }
}
