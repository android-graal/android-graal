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

import static android.annotation.SystemApi.Client.MODULE_LIBRARIES;

import android.annotation.SystemApi;
import android.compat.annotation.UnsupportedAppUsage;

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
import org.androidgraal.hosted.annotate.Transcribed;

/**
 * Constants and helper functions for use with {@link Os}.
 */
@Emulated
public final class OsConstants {
    @UnsupportedAppUsage
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

    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L65-L65")
    public static final int AF_INET = Socket.AF_INET();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L66-L66")
    public static final int AF_INET6 = Socket.AF_INET6();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L68-L68")
    public static final int AF_NETLINK = Socket.AF_NETLINK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L67-L67")
    public static final int AF_PACKET = Socket.AF_PACKET();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L69-L69")
    public static final int AF_UNIX = Socket.AF_UNIX();

    /**
     * The virt-vsock address family, linux specific.
     * It is used with {@code struct sockaddr_vm} from uapi/linux/vm_sockets.h.
     *
     * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
     * @see VmSocketAddress
     */
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L70-L70")
    public static final int AF_VSOCK = Socket.AF_VSOCK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L71-L71")
    public static final int AF_UNSPEC = Socket.AF_UNSPEC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L72-L72")
    public static final int AI_ADDRCONFIG = Netdb.AI_ADDRCONFIG();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L73-L73")
    public static final int AI_ALL = Netdb.AI_ALL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L74-L74")
    public static final int AI_CANONNAME = Netdb.AI_CANONNAME();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L75-L75")
    public static final int AI_NUMERICHOST = Netdb.AI_NUMERICHOST();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L76-L76")
    public static final int AI_NUMERICSERV = Netdb.AI_NUMERICSERV();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L77-L77")
    public static final int AI_PASSIVE = Netdb.AI_PASSIVE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L78-L78")
    public static final int AI_V4MAPPED = Netdb.AI_V4MAPPED();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L79-L79")
    public static final int ARPHRD_ETHER = Net.ARPHRD_ETHER();

    /**
      * The virtio-vsock {@code svmPort} value to bind for any available port.
      *
      * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
      * @see VmSocketAddress
      */
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L80-L80")
    public static final int VMADDR_PORT_ANY = Vm.VMADDR_PORT_ANY();

    /**
      * The virtio-vsock {@code svmCid} value to listens for all CIDs.
      *
      * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
      * @see VmSocketAddress
      */
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L81-L81")
    public static final int VMADDR_CID_ANY = Vm.VMADDR_CID_ANY();

    /**
      * The virtio-vsock {@code svmCid} value for host communication.
      *
      * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
      * @see VmSocketAddress
      */
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L82-L82")
    public static final int VMADDR_CID_LOCAL = Vm.VMADDR_CID_LOCAL();

    /**
      * The virtio-vsock {@code svmCid} value for loopback communication.
      *
      * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
      * @see VmSocketAddress
      */
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L83-L83")
    public static final int VMADDR_CID_HOST = Vm.VMADDR_CID_HOST();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L85-L85")
    public static final int CAP_AUDIT_CONTROL = Capability.CAP_AUDIT_CONTROL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L86-L86")
    public static final int CAP_AUDIT_WRITE = Capability.CAP_AUDIT_WRITE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L87-L87")
    public static final int CAP_BLOCK_SUSPEND = Capability.CAP_BLOCK_SUSPEND();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L88-L88")
    public static final int CAP_CHOWN = Capability.CAP_CHOWN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L89-L89")
    public static final int CAP_DAC_OVERRIDE = Capability.CAP_DAC_OVERRIDE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L90-L90")
    public static final int CAP_DAC_READ_SEARCH = Capability.CAP_DAC_READ_SEARCH();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L91-L91")
    public static final int CAP_FOWNER = Capability.CAP_FOWNER();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L92-L92")
    public static final int CAP_FSETID = Capability.CAP_FSETID();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L93-L93")
    public static final int CAP_IPC_LOCK = Capability.CAP_IPC_LOCK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L94-L94")
    public static final int CAP_IPC_OWNER = Capability.CAP_IPC_OWNER();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L95-L95")
    public static final int CAP_KILL = Capability.CAP_KILL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L96-L96")
    public static final int CAP_LAST_CAP = Capability.CAP_LAST_CAP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L97-L97")
    public static final int CAP_LEASE = Capability.CAP_LEASE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L98-L98")
    public static final int CAP_LINUX_IMMUTABLE = Capability.CAP_LINUX_IMMUTABLE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L99-L99")
    public static final int CAP_MAC_ADMIN = Capability.CAP_MAC_ADMIN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L100-L100")
    public static final int CAP_MAC_OVERRIDE = Capability.CAP_MAC_OVERRIDE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L101-L101")
    public static final int CAP_MKNOD = Capability.CAP_MKNOD();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L102-L102")
    public static final int CAP_NET_ADMIN = Capability.CAP_NET_ADMIN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L103-L103")
    public static final int CAP_NET_BIND_SERVICE = Capability.CAP_NET_BIND_SERVICE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L104-L104")
    public static final int CAP_NET_BROADCAST = Capability.CAP_NET_BROADCAST();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L105-L105")
    public static final int CAP_NET_RAW = Capability.CAP_NET_RAW();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L106-L106")
    public static final int CAP_SETFCAP = Capability.CAP_SETFCAP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L107-L107")
    public static final int CAP_SETGID = Capability.CAP_SETGID();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L108-L108")
    public static final int CAP_SETPCAP = Capability.CAP_SETPCAP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L109-L109")
    public static final int CAP_SETUID = Capability.CAP_SETUID();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L110-L110")
    public static final int CAP_SYS_ADMIN = Capability.CAP_SYS_ADMIN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L111-L111")
    public static final int CAP_SYS_BOOT = Capability.CAP_SYS_BOOT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L112-L112")
    public static final int CAP_SYS_CHROOT = Capability.CAP_SYS_CHROOT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L113-L113")
    public static final int CAP_SYSLOG = Capability.CAP_SYSLOG();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L114-L114")
    public static final int CAP_SYS_MODULE = Capability.CAP_SYS_MODULE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L115-L115")
    public static final int CAP_SYS_NICE = Capability.CAP_SYS_NICE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L116-L116")
    public static final int CAP_SYS_PACCT = Capability.CAP_SYS_PACCT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L117-L117")
    public static final int CAP_SYS_PTRACE = Capability.CAP_SYS_PTRACE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L118-L118")
    public static final int CAP_SYS_RAWIO = Capability.CAP_SYS_RAWIO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L119-L119")
    public static final int CAP_SYS_RESOURCE = Capability.CAP_SYS_RESOURCE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L120-L120")
    public static final int CAP_SYS_TIME = Capability.CAP_SYS_TIME();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L121-L121")
    public static final int CAP_SYS_TTY_CONFIG = Capability.CAP_SYS_TTY_CONFIG();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L122-L122")
    public static final int CAP_WAKE_ALARM = Capability.CAP_WAKE_ALARM();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L123-L123")
    public static final int E2BIG = Errno.E2BIG();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L124-L124")
    public static final int EACCES = Errno.EACCES();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L125-L125")
    public static final int EADDRINUSE = Errno.EADDRINUSE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L126-L126")
    public static final int EADDRNOTAVAIL = Errno.EADDRNOTAVAIL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L127-L127")
    public static final int EAFNOSUPPORT = Errno.EAFNOSUPPORT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L128-L128")
    public static final int EAGAIN = Errno.EAGAIN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L129-L129")
    public static final int EAI_AGAIN = Netdb.EAI_AGAIN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L130-L130")
    public static final int EAI_BADFLAGS = Netdb.EAI_BADFLAGS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L131-L131")
    public static final int EAI_FAIL = Netdb.EAI_FAIL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L132-L132")
    public static final int EAI_FAMILY = Netdb.EAI_FAMILY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L133-L133")
    public static final int EAI_MEMORY = Netdb.EAI_MEMORY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L134-L134")
    public static final int EAI_NODATA = Netdb.EAI_NODATA();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L135-L135")
    public static final int EAI_NONAME = Netdb.EAI_NONAME();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L136-L136")
    public static final int EAI_OVERFLOW = Netdb.EAI_OVERFLOW();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L137-L137")
    public static final int EAI_SERVICE = Netdb.EAI_SERVICE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L138-L138")
    public static final int EAI_SOCKTYPE = Netdb.EAI_SOCKTYPE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L139-L139")
    public static final int EAI_SYSTEM = Netdb.EAI_SYSTEM();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L140-L140")
    public static final int EALREADY = Errno.EALREADY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L141-L141")
    public static final int EBADF = Errno.EBADF();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L142-L142")
    public static final int EBADMSG = Errno.EBADMSG();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L143-L143")
    public static final int EBUSY = Errno.EBUSY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L144-L144")
    public static final int ECANCELED = Errno.ECANCELED();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L145-L145")
    public static final int ECHILD = Errno.ECHILD();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L146-L146")
    public static final int ECONNABORTED = Errno.ECONNABORTED();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L147-L147")
    public static final int ECONNREFUSED = Errno.ECONNREFUSED();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L148-L148")
    public static final int ECONNRESET = Errno.ECONNRESET();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L149-L149")
    public static final int EDEADLK = Errno.EDEADLK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L150-L150")
    public static final int EDESTADDRREQ = Errno.EDESTADDRREQ();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L151-L151")
    public static final int EDOM = Errno.EDOM();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L152-L152")
    public static final int EDQUOT = Errno.EDQUOT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L153-L153")
    public static final int EEXIST = Errno.EEXIST();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L154-L154")
    public static final int EFAULT = Errno.EFAULT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L155-L155")
    public static final int EFBIG = Errno.EFBIG();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L156-L156")
    public static final int EHOSTUNREACH = Errno.EHOSTUNREACH();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L157-L157")
    public static final int EIDRM = Errno.EIDRM();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L158-L158")
    public static final int EILSEQ = Errno.EILSEQ();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L159-L159")
    public static final int EINPROGRESS = Errno.EINPROGRESS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L160-L160")
    public static final int EINTR = Errno.EINTR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L161-L161")
    public static final int EINVAL = Errno.EINVAL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L162-L162")
    public static final int EIO = Errno.EIO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L163-L163")
    public static final int EISCONN = Errno.EISCONN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L164-L164")
    public static final int EISDIR = Errno.EISDIR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L165-L165")
    public static final int ELOOP = Errno.ELOOP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L166-L166")
    public static final int EMFILE = Errno.EMFILE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L167-L167")
    public static final int EMLINK = Errno.EMLINK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L168-L168")
    public static final int EMSGSIZE = Errno.EMSGSIZE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L169-L169")
    public static final int EMULTIHOP = Errno.EMULTIHOP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L170-L170")
    public static final int ENAMETOOLONG = Errno.ENAMETOOLONG();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L171-L171")
    public static final int ENETDOWN = Errno.ENETDOWN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L172-L172")
    public static final int ENETRESET = Errno.ENETRESET();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L173-L173")
    public static final int ENETUNREACH = Errno.ENETUNREACH();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L174-L174")
    public static final int ENFILE = Errno.ENFILE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L175-L175")
    public static final int ENOBUFS = Errno.ENOBUFS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L176-L176")
    public static final int ENODATA = Errno.ENODATA();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L177-L177")
    public static final int ENODEV = Errno.ENODEV();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L178-L178")
    public static final int ENOENT = Errno.ENOENT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L179-L179")
    public static final int ENOEXEC = Errno.ENOEXEC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L180-L180")
    public static final int ENOLCK = Errno.ENOLCK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L181-L181")
    public static final int ENOLINK = Errno.ENOLINK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L182-L182")
    public static final int ENOMEM = Errno.ENOMEM();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L183-L183")
    public static final int ENOMSG = Errno.ENOMSG();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L184-L184")
    public static final int ENONET = Errno.ENONET();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L185-L185")
    public static final int ENOPROTOOPT = Errno.ENOPROTOOPT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L186-L186")
    public static final int ENOSPC = Errno.ENOSPC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L187-L187")
    public static final int ENOSR = Errno.ENOSR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L188-L188")
    public static final int ENOSTR = Errno.ENOSTR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L189-L189")
    public static final int ENOSYS = Errno.ENOSYS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L190-L190")
    public static final int ENOTCONN = Errno.ENOTCONN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L191-L191")
    public static final int ENOTDIR = Errno.ENOTDIR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L192-L192")
    public static final int ENOTEMPTY = Errno.ENOTEMPTY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L193-L193")
    public static final int ENOTSOCK = Errno.ENOTSOCK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L194-L194")
    public static final int ENOTSUP = Errno.ENOTSUP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L195-L195")
    public static final int ENOTTY = Errno.ENOTTY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L196-L196")
    public static final int ENXIO = Errno.ENXIO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L197-L197")
    public static final int EOPNOTSUPP = Errno.EOPNOTSUPP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L198-L198")
    public static final int EOVERFLOW = Errno.EOVERFLOW();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L199-L199")
    public static final int EPERM = Errno.EPERM();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L200-L200")
    public static final int EPIPE = Errno.EPIPE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L201-L201")
    public static final int EPROTO = Errno.EPROTO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L202-L202")
    public static final int EPROTONOSUPPORT = Errno.EPROTONOSUPPORT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L203-L203")
    public static final int EPROTOTYPE = Errno.EPROTOTYPE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L204-L204")
    public static final int ERANGE = Errno.ERANGE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L205-L205")
    public static final int EROFS = Errno.EROFS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L206-L206")
    public static final int ESPIPE = Errno.ESPIPE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L207-L207")
    public static final int ESRCH = Errno.ESRCH();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L208-L208")
    public static final int ESTALE = Errno.ESTALE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L209-L209")
    public static final int ETH_P_ALL = Netinet.ETH_P_ALL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L210-L210")
    public static final int ETH_P_ARP = Netinet.ETH_P_ARP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L211-L211")
    public static final int ETH_P_IP = Netinet.ETH_P_IP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L212-L212")
    public static final int ETH_P_IPV6 = Netinet.ETH_P_IPV6();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L213-L213")
    public static final int ETIME = Errno.ETIME();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L214-L214")
    public static final int ETIMEDOUT = Errno.ETIMEDOUT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L215-L215")
    public static final int ETXTBSY = Errno.ETXTBSY();
    // On Linux, EWOULDBLOCK == EAGAIN. Use EAGAIN instead, to reduce confusion.
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L217-L217")
    public static final int EXDEV = Errno.EXDEV();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L218-L218")
    public static final int EXIT_FAILURE = Stdlib.EXIT_FAILURE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L219-L219")
    public static final int EXIT_SUCCESS = Stdlib.EXIT_SUCCESS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L220-L220")
    public static final int FD_CLOEXEC = Fcntl.FD_CLOEXEC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L221-L221")
    public static final int FIONREAD = Ioctl.FIONREAD();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L222-L222")
    public static final int F_DUPFD = Fcntl.F_DUPFD();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L223-L223")
    public static final int F_DUPFD_CLOEXEC = Fcntl.F_DUPFD_CLOEXEC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L224-L224")
    public static final int F_GETFD = Fcntl.F_GETFD();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L225-L225")
    public static final int F_GETFL = Fcntl.F_GETFL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L226-L226")
    public static final int F_GETLK = Fcntl.F_GETLK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L227-L227")
    public static final int F_GETLK64 = Fcntl.F_GETLK64();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L228-L228")
    public static final int F_GETOWN = Fcntl.F_GETOWN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L229-L229")
    public static final int F_OK = Unistd.F_OK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L230-L230")
    public static final int F_RDLCK = Fcntl.F_RDLCK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L231-L231")
    public static final int F_SETFD = Fcntl.F_SETFD();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L232-L232")
    public static final int F_SETFL = Fcntl.F_SETFL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L233-L233")
    public static final int F_SETLK = Fcntl.F_SETLK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L234-L234")
    public static final int F_SETLK64 = Fcntl.F_SETLK64();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L235-L235")
    public static final int F_SETLKW = Fcntl.F_SETLKW();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L236-L236")
    public static final int F_SETLKW64 = Fcntl.F_SETLKW64();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L237-L237")
    public static final int F_SETOWN = Fcntl.F_SETOWN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L238-L238")
    public static final int F_UNLCK = Fcntl.F_UNLCK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L239-L239")
    public static final int F_WRLCK = Fcntl.F_WRLCK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L240-L240")
    public static final int ICMP_ECHO = Netinet.ICMP_ECHO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L241-L241")
    public static final int ICMP_ECHOREPLY = Netinet.ICMP_ECHOREPLY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L242-L242")
    public static final int ICMP6_ECHO_REQUEST = Netinet.ICMP6_ECHO_REQUEST();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L243-L243")
    public static final int ICMP6_ECHO_REPLY = Netinet.ICMP6_ECHO_REPLY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L244-L244")
    public static final int IFA_F_DADFAILED = IfAddr.IFA_F_DADFAILED();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L245-L245")
    public static final int IFA_F_DEPRECATED = IfAddr.IFA_F_DEPRECATED();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L246-L246")
    public static final int IFA_F_HOMEADDRESS = IfAddr.IFA_F_HOMEADDRESS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L247-L247")
    public static final int IFA_F_MANAGETEMPADDR = IfAddr.IFA_F_MANAGETEMPADDR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L248-L248")
    public static final int IFA_F_NODAD = IfAddr.IFA_F_NODAD();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L249-L249")
    public static final int IFA_F_NOPREFIXROUTE = IfAddr.IFA_F_NOPREFIXROUTE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L250-L250")
    public static final int IFA_F_OPTIMISTIC = IfAddr.IFA_F_OPTIMISTIC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L251-L251")
    public static final int IFA_F_PERMANENT = IfAddr.IFA_F_PERMANENT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L252-L252")
    public static final int IFA_F_SECONDARY = IfAddr.IFA_F_SECONDARY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L253-L253")
    public static final int IFA_F_TEMPORARY = IfAddr.IFA_F_TEMPORARY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L254-L254")
    public static final int IFA_F_TENTATIVE = IfAddr.IFA_F_TENTATIVE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L255-L255")
    public static final int IFF_ALLMULTI = Net.IFF_ALLMULTI();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L256-L256")
    public static final int IFF_AUTOMEDIA = Net.IFF_AUTOMEDIA();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L257-L257")
    public static final int IFF_BROADCAST = Net.IFF_BROADCAST();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L258-L258")
    public static final int IFF_DEBUG = Net.IFF_DEBUG();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L259-L259")
    public static final int IFF_DYNAMIC = Net.IFF_DYNAMIC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L260-L260")
    public static final int IFF_LOOPBACK = Net.IFF_LOOPBACK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L261-L261")
    public static final int IFF_MASTER = Net.IFF_MASTER();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L262-L262")
    public static final int IFF_MULTICAST = Net.IFF_MULTICAST();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L263-L263")
    public static final int IFF_NOARP = Net.IFF_NOARP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L264-L264")
    public static final int IFF_NOTRAILERS = Net.IFF_NOTRAILERS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L265-L265")
    public static final int IFF_POINTOPOINT = Net.IFF_POINTOPOINT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L266-L266")
    public static final int IFF_PORTSEL = Net.IFF_PORTSEL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L267-L267")
    public static final int IFF_PROMISC = Net.IFF_PROMISC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L268-L268")
    public static final int IFF_RUNNING = Net.IFF_RUNNING();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L269-L269")
    public static final int IFF_SLAVE = Net.IFF_SLAVE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L270-L270")
    public static final int IFF_UP = Net.IFF_UP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L271-L271")
    public static final int IPPROTO_ICMP = Netinet.IPPROTO_ICMP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L272-L272")
    public static final int IPPROTO_ICMPV6 = Netinet.IPPROTO_ICMPV6();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L273-L273")
    public static final int IPPROTO_IP = Netinet.IPPROTO_IP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L274-L274")
    public static final int IPPROTO_IPV6 = Netinet.IPPROTO_IPV6();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L275-L275")
    public static final int IPPROTO_RAW = Netinet.IPPROTO_RAW();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L276-L276")
    public static final int IPPROTO_TCP = Netinet.IPPROTO_TCP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L277-L277")
    public static final int IPPROTO_UDP = Netinet.IPPROTO_UDP();

    /**
     * Encapsulation Security Payload protocol
     *
     * <p>Defined in /uapi/linux/in.h
     */
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L278-L278")
    public static final int IPPROTO_ESP = Netinet.IPPROTO_ESP();

    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L279-L279")
    public static final int IPV6_CHECKSUM = Netinet.IPV6_CHECKSUM();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L280-L280")
    public static final int IPV6_MULTICAST_HOPS = Netinet.IPV6_MULTICAST_HOPS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L281-L281")
    public static final int IPV6_MULTICAST_IF = Netinet.IPV6_MULTICAST_IF();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L282-L282")
    public static final int IPV6_MULTICAST_LOOP = Netinet.IPV6_MULTICAST_LOOP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L283-L283")
    public static final int IPV6_PKTINFO = Netinet.IPV6_PKTINFO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L284-L284")
    public static final int IPV6_RECVDSTOPTS = Netinet.IPV6_RECVDSTOPTS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L285-L285")
    public static final int IPV6_RECVHOPLIMIT = Netinet.IPV6_RECVHOPLIMIT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L286-L286")
    public static final int IPV6_RECVHOPOPTS = Netinet.IPV6_RECVHOPOPTS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L287-L287")
    public static final int IPV6_RECVPKTINFO = Netinet.IPV6_RECVPKTINFO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L288-L288")
    public static final int IPV6_RECVRTHDR = Netinet.IPV6_RECVRTHDR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L289-L289")
    public static final int IPV6_RECVTCLASS = Netinet.IPV6_RECVTCLASS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L290-L290")
    public static final int IPV6_TCLASS = Netinet.IPV6_TCLASS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L291-L291")
    public static final int IPV6_UNICAST_HOPS = Netinet.IPV6_UNICAST_HOPS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L292-L292")
    public static final int IPV6_V6ONLY = Netinet.IPV6_V6ONLY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L294-L294")
    public static final int IP_MULTICAST_IF = Netinet.IP_MULTICAST_IF();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L295-L295")
    public static final int IP_MULTICAST_LOOP = Netinet.IP_MULTICAST_LOOP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L296-L296")
    public static final int IP_MULTICAST_TTL = Netinet.IP_MULTICAST_TTL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L298-L298")
    public static final int IP_TOS = Netinet.IP_TOS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L299-L299")
    public static final int IP_TTL = Netinet.IP_TTL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L325-L325")
    public static final int MAP_FIXED = Mman.MAP_FIXED();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L326-L326")
    public static final int MAP_ANONYMOUS = Mman.MAP_ANONYMOUS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L328-L328")
    public static final int MAP_PRIVATE = Mman.MAP_PRIVATE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L329-L329")
    public static final int MAP_SHARED = Mman.MAP_SHARED();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L330-L330")
    public static final int MCAST_JOIN_GROUP = Netinet.MCAST_JOIN_GROUP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L331-L331")
    public static final int MCAST_LEAVE_GROUP = Netinet.MCAST_LEAVE_GROUP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L332-L332")
    public static final int MCAST_JOIN_SOURCE_GROUP = Netinet.MCAST_JOIN_SOURCE_GROUP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L333-L333")
    public static final int MCAST_LEAVE_SOURCE_GROUP = Netinet.MCAST_LEAVE_SOURCE_GROUP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L334-L334")
    public static final int MCAST_BLOCK_SOURCE = Netinet.MCAST_BLOCK_SOURCE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L335-L335")
    public static final int MCAST_UNBLOCK_SOURCE = Netinet.MCAST_UNBLOCK_SOURCE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L336-L336")
    public static final int MCL_CURRENT = Mman.MCL_CURRENT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L337-L337")
    public static final int MCL_FUTURE = Mman.MCL_FUTURE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L338-L338")
    public static final int MFD_CLOEXEC = Mman.MFD_CLOEXEC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L339-L339")
    public static final int MSG_CTRUNC = Socket.MSG_CTRUNC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L340-L340")
    public static final int MSG_DONTROUTE = Socket.MSG_DONTROUTE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L341-L341")
    public static final int MSG_EOR = Socket.MSG_EOR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L342-L342")
    public static final int MSG_OOB = Socket.MSG_OOB();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L343-L343")
    public static final int MSG_PEEK = Socket.MSG_PEEK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L344-L344")
    public static final int MSG_TRUNC = Socket.MSG_TRUNC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L345-L345")
    public static final int MSG_WAITALL = Socket.MSG_WAITALL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L346-L346")
    public static final int MS_ASYNC = Mman.MS_ASYNC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L347-L347")
    public static final int MS_INVALIDATE = Mman.MS_INVALIDATE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L348-L348")
    public static final int MS_SYNC = Mman.MS_SYNC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L349-L349")
    public static final int NETLINK_NETFILTER = Netlink.NETLINK_NETFILTER();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L350-L350")
    public static final int NETLINK_ROUTE = Netlink.NETLINK_ROUTE();
    /**
     * SELinux enforces that only system_server and netd may use this netlink socket type.
     */
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L351-L351")
    public static final int NETLINK_INET_DIAG = Netlink.NETLINK_INET_DIAG();

    /**
     * SELinux enforces that only system_server and netd may use this netlink socket type.
     *
     * @see <a href="https://man7.org/linux/man-pages/man7/netlink.7.html">netlink(7)</a>
     */
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L352-L352")
    public static final int NETLINK_XFRM = Netlink.NETLINK_XFRM();

    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L353-L353")
    public static final int NI_DGRAM = Netdb.NI_DGRAM();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L354-L354")
    public static final int NI_NAMEREQD = Netdb.NI_NAMEREQD();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L355-L355")
    public static final int NI_NOFQDN = Netdb.NI_NOFQDN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L356-L356")
    public static final int NI_NUMERICHOST = Netdb.NI_NUMERICHOST();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L357-L357")
    public static final int NI_NUMERICSERV = Netdb.NI_NUMERICSERV();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L358-L358")
    public static final int O_ACCMODE = Fcntl.O_ACCMODE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L359-L359")
    public static final int O_APPEND = Fcntl.O_APPEND();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L360-L360")
    public static final int O_CLOEXEC = Fcntl.O_CLOEXEC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L361-L361")
    public static final int O_CREAT = Fcntl.O_CREAT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L363-L363")
    public static final int O_EXCL = Fcntl.O_EXCL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L364-L364")
    public static final int O_NOCTTY = Fcntl.O_NOCTTY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L365-L365")
    public static final int O_NOFOLLOW = Fcntl.O_NOFOLLOW();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L366-L366")
    public static final int O_NONBLOCK = Fcntl.O_NONBLOCK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L367-L367")
    public static final int O_RDONLY = Fcntl.O_RDONLY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L368-L368")
    public static final int O_RDWR = Fcntl.O_RDWR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L369-L369")
    public static final int O_SYNC = Fcntl.O_SYNC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L370-L370")
    public static final int O_DSYNC = Fcntl.O_DSYNC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L371-L371")
    public static final int O_TRUNC = Fcntl.O_TRUNC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L372-L372")
    public static final int O_WRONLY = Fcntl.O_WRONLY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L373-L373")
    public static final int POLLERR = Poll.POLLERR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L374-L374")
    public static final int POLLHUP = Poll.POLLHUP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L375-L375")
    public static final int POLLIN = Poll.POLLIN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L376-L376")
    public static final int POLLNVAL = Poll.POLLNVAL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L377-L377")
    public static final int POLLOUT = Poll.POLLOUT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L378-L378")
    public static final int POLLPRI = Poll.POLLPRI();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L379-L379")
    public static final int POLLRDBAND = Poll.POLLRDBAND();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L380-L380")
    public static final int POLLRDNORM = Poll.POLLRDNORM();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L381-L381")
    public static final int POLLWRBAND = Poll.POLLWRBAND();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L382-L382")
    public static final int POLLWRNORM = Poll.POLLWRNORM();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L385-L385")
    public static final int PR_GET_DUMPABLE = Prctl.PR_GET_DUMPABLE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L386-L386")
    public static final int PR_SET_DUMPABLE = Prctl.PR_SET_DUMPABLE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L387-L387")
    public static final int PR_SET_NO_NEW_PRIVS = Prctl.PR_SET_NO_NEW_PRIVS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L388-L388")
    public static final int PROT_EXEC = Mman.PROT_EXEC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L389-L389")
    public static final int PROT_NONE = Mman.PROT_NONE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L390-L390")
    public static final int PROT_READ = Mman.PROT_READ();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L391-L391")
    public static final int PROT_WRITE = Mman.PROT_WRITE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L392-L392")
    public static final int R_OK = Unistd.R_OK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L395-L395")
    public static final int RT_SCOPE_HOST = Rtnetlink.RT_SCOPE_HOST();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L396-L396")
    public static final int RT_SCOPE_LINK = Rtnetlink.RT_SCOPE_LINK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L397-L397")
    public static final int RT_SCOPE_NOWHERE = Rtnetlink.RT_SCOPE_NOWHERE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L398-L398")
    public static final int RT_SCOPE_SITE = Rtnetlink.RT_SCOPE_SITE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L399-L399")
    public static final int RT_SCOPE_UNIVERSE = Rtnetlink.RT_SCOPE_UNIVERSE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L410-L410")
    public static final int RTMGRP_NEIGH = Rtnetlink.RTMGRP_NEIGH();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L413-L413")
    public static final int SEEK_CUR = Unistd.SEEK_CUR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L414-L414")
    public static final int SEEK_END = Unistd.SEEK_END();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L415-L415")
    public static final int SEEK_SET = Unistd.SEEK_SET();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L416-L416")
    public static final int SHUT_RD = Socket.SHUT_RD();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L417-L417")
    public static final int SHUT_RDWR = Socket.SHUT_RDWR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L418-L418")
    public static final int SHUT_WR = Socket.SHUT_WR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L419-L419")
    public static final int SIGABRT = Signal.SIGABRT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L420-L420")
    public static final int SIGALRM = Signal.SIGALRM();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L421-L421")
    public static final int SIGBUS = Signal.SIGBUS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L422-L422")
    public static final int SIGCHLD = Signal.SIGCHLD();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L423-L423")
    public static final int SIGCONT = Signal.SIGCONT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L424-L424")
    public static final int SIGFPE = Signal.SIGFPE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L425-L425")
    public static final int SIGHUP = Signal.SIGHUP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L426-L426")
    public static final int SIGILL = Signal.SIGILL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L427-L427")
    public static final int SIGINT = Signal.SIGINT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L428-L428")
    public static final int SIGIO = Signal.SIGIO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L429-L429")
    public static final int SIGKILL = Signal.SIGKILL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L430-L430")
    public static final int SIGPIPE = Signal.SIGPIPE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L431-L431")
    public static final int SIGPROF = Signal.SIGPROF();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L432-L432")
    public static final int SIGPWR = Signal.SIGPWR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L433-L433")
    public static final int SIGQUIT = Signal.SIGQUIT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L434-L434")
    public static final int SIGRTMAX = Signal.SIGRTMAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L435-L435")
    public static final int SIGRTMIN = Signal.SIGRTMIN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L436-L436")
    public static final int SIGSEGV = Signal.SIGSEGV();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L437-L437")
    public static final int SIGSTKFLT = Signal.SIGSTKFLT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L438-L438")
    public static final int SIGSTOP = Signal.SIGSTOP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L439-L439")
    public static final int SIGSYS = Signal.SIGSYS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L440-L440")
    public static final int SIGTERM = Signal.SIGTERM();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L441-L441")
    public static final int SIGTRAP = Signal.SIGTRAP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L442-L442")
    public static final int SIGTSTP = Signal.SIGTSTP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L443-L443")
    public static final int SIGTTIN = Signal.SIGTTIN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L444-L444")
    public static final int SIGTTOU = Signal.SIGTTOU();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L445-L445")
    public static final int SIGURG = Signal.SIGURG();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L446-L446")
    public static final int SIGUSR1 = Signal.SIGUSR1();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L447-L447")
    public static final int SIGUSR2 = Signal.SIGUSR2();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L448-L448")
    public static final int SIGVTALRM = Signal.SIGVTALRM();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L449-L449")
    public static final int SIGWINCH = Signal.SIGWINCH();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L450-L450")
    public static final int SIGXCPU = Signal.SIGXCPU();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L451-L451")
    public static final int SIGXFSZ = Signal.SIGXFSZ();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L452-L452")
    public static final int SIOCGIFADDR = Socket.SIOCGIFADDR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L453-L453")
    public static final int SIOCGIFBRDADDR = Socket.SIOCGIFBRDADDR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L454-L454")
    public static final int SIOCGIFDSTADDR = Socket.SIOCGIFDSTADDR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L455-L455")
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
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L456-L456")
    public static final int SOCK_CLOEXEC = Socket.SOCK_CLOEXEC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L457-L457")
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
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L458-L458")
    public static final int SOCK_NONBLOCK = Socket.SOCK_NONBLOCK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L459-L459")
    public static final int SOCK_RAW = Socket.SOCK_RAW();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L460-L460")
    public static final int SOCK_SEQPACKET = Socket.SOCK_SEQPACKET();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L461-L461")
    public static final int SOCK_STREAM = Socket.SOCK_STREAM();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L462-L462")
    public static final int SOL_SOCKET = Socket.SOL_SOCKET();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L463-L463")
    public static final int SOL_UDP = Socket.SOL_UDP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L464-L464")
    public static final int SOL_PACKET = Socket.SOL_PACKET();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L465-L465")
    public static final int SO_BINDTODEVICE = Socket.SO_BINDTODEVICE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L466-L466")
    public static final int SO_BROADCAST = Socket.SO_BROADCAST();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L467-L467")
    public static final int SO_DEBUG = Socket.SO_DEBUG();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L469-L469")
    public static final int SO_DONTROUTE = Socket.SO_DONTROUTE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L470-L470")
    public static final int SO_ERROR = Socket.SO_ERROR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L471-L471")
    public static final int SO_KEEPALIVE = Socket.SO_KEEPALIVE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L472-L472")
    public static final int SO_LINGER = Socket.SO_LINGER();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L473-L473")
    public static final int SO_OOBINLINE = Socket.SO_OOBINLINE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L474-L474")
    public static final int SO_PASSCRED = Socket.SO_PASSCRED();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L475-L475")
    public static final int SO_PEERCRED = Socket.SO_PEERCRED();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L477-L477")
    public static final int SO_RCVBUF = Socket.SO_RCVBUF();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L478-L478")
    public static final int SO_RCVLOWAT = Socket.SO_RCVLOWAT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L479-L479")
    public static final int SO_RCVTIMEO = Socket.SO_RCVTIMEO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L480-L480")
    public static final int SO_REUSEADDR = Socket.SO_REUSEADDR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L481-L481")
    public static final int SO_SNDBUF = Socket.SO_SNDBUF();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L482-L482")
    public static final int SO_SNDLOWAT = Socket.SO_SNDLOWAT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L483-L483")
    public static final int SO_SNDTIMEO = Socket.SO_SNDTIMEO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L484-L484")
    public static final int SO_TYPE = Socket.SO_TYPE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L485-L485")
    public static final int PACKET_IGNORE_OUTGOING = Netpacket.PACKET_IGNORE_OUTGOING();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L489-L489")
    public static final int STDERR_FILENO = Unistd.STDERR_FILENO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L490-L490")
    public static final int STDIN_FILENO = Unistd.STDIN_FILENO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L491-L491")
    public static final int STDOUT_FILENO = Unistd.STDOUT_FILENO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L492-L492")
    public static final int ST_MANDLOCK = Statvfs.ST_MANDLOCK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L493-L493")
    public static final int ST_NOATIME = Statvfs.ST_NOATIME();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L494-L494")
    public static final int ST_NODEV = Statvfs.ST_NODEV();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L495-L495")
    public static final int ST_NODIRATIME = Statvfs.ST_NODIRATIME();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L496-L496")
    public static final int ST_NOEXEC = Statvfs.ST_NOEXEC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L497-L497")
    public static final int ST_NOSUID = Statvfs.ST_NOSUID();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L498-L498")
    public static final int ST_RDONLY = Statvfs.ST_RDONLY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L499-L499")
    public static final int ST_RELATIME = Statvfs.ST_RELATIME();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L500-L500")
    public static final int ST_SYNCHRONOUS = Statvfs.ST_SYNCHRONOUS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L501-L501")
    public static final int S_IFBLK = Stat.S_IFBLK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L502-L502")
    public static final int S_IFCHR = Stat.S_IFCHR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L503-L503")
    public static final int S_IFDIR = Stat.S_IFDIR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L504-L504")
    public static final int S_IFIFO = Stat.S_IFIFO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L505-L505")
    public static final int S_IFLNK = Stat.S_IFLNK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L506-L506")
    public static final int S_IFMT = Stat.S_IFMT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L507-L507")
    public static final int S_IFREG = Stat.S_IFREG();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L508-L508")
    public static final int S_IFSOCK = Stat.S_IFSOCK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L509-L509")
    public static final int S_IRGRP = Stat.S_IRGRP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L510-L510")
    public static final int S_IROTH = Stat.S_IROTH();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L511-L511")
    public static final int S_IRUSR = Stat.S_IRUSR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L512-L512")
    public static final int S_IRWXG = Stat.S_IRWXG();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L513-L513")
    public static final int S_IRWXO = Stat.S_IRWXO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L514-L514")
    public static final int S_IRWXU = Stat.S_IRWXU();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L515-L515")
    public static final int S_ISGID = Stat.S_ISGID();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L516-L516")
    public static final int S_ISUID = Stat.S_ISUID();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L517-L517")
    public static final int S_ISVTX = Stat.S_ISVTX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L518-L518")
    public static final int S_IWGRP = Stat.S_IWGRP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L519-L519")
    public static final int S_IWOTH = Stat.S_IWOTH();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L520-L520")
    public static final int S_IWUSR = Stat.S_IWUSR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L521-L521")
    public static final int S_IXGRP = Stat.S_IXGRP();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L522-L522")
    public static final int S_IXOTH = Stat.S_IXOTH();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L523-L523")
    public static final int S_IXUSR = Stat.S_IXUSR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L524-L524")
    public static final int TCP_NODELAY = Netinet.TCP_NODELAY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L525-L525")
    public static final int TCP_USER_TIMEOUT = Netinet.TCP_USER_TIMEOUT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L530-L530")
    public static final int UDP_GRO = Netinet.UDP_GRO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L531-L531")
    public static final int UDP_SEGMENT = Netinet.UDP_SEGMENT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L534-L534")
    public static final int WCONTINUED = Wait.WCONTINUED();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L535-L535")
    public static final int WEXITED = Wait.WEXITED();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L536-L536")
    public static final int WNOHANG = Wait.WNOHANG();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L537-L537")
    public static final int WNOWAIT = Wait.WNOWAIT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L538-L538")
    public static final int WSTOPPED = Wait.WSTOPPED();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L539-L539")
    public static final int WUNTRACED = Wait.WUNTRACED();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L540-L540")
    public static final int W_OK = Unistd.W_OK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L543-L543")
    public static final int X_OK = Unistd.X_OK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L544-L544")
    public static final int _SC_2_CHAR_TERM = Unistd._SC_2_CHAR_TERM();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L545-L545")
    public static final int _SC_2_C_BIND = Unistd._SC_2_C_BIND();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L546-L546")
    public static final int _SC_2_C_DEV = Unistd._SC_2_C_DEV();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L547-L547")
    public static final int _SC_2_C_VERSION = Unistd._SC_2_C_VERSION();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L548-L548")
    public static final int _SC_2_FORT_DEV = Unistd._SC_2_FORT_DEV();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L549-L549")
    public static final int _SC_2_FORT_RUN = Unistd._SC_2_FORT_RUN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L550-L550")
    public static final int _SC_2_LOCALEDEF = Unistd._SC_2_LOCALEDEF();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L551-L551")
    public static final int _SC_2_SW_DEV = Unistd._SC_2_SW_DEV();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L552-L552")
    public static final int _SC_2_UPE = Unistd._SC_2_UPE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L553-L553")
    public static final int _SC_2_VERSION = Unistd._SC_2_VERSION();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L554-L554")
    public static final int _SC_AIO_LISTIO_MAX = Unistd._SC_AIO_LISTIO_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L555-L555")
    public static final int _SC_AIO_MAX = Unistd._SC_AIO_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L556-L556")
    public static final int _SC_AIO_PRIO_DELTA_MAX = Unistd._SC_AIO_PRIO_DELTA_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L557-L557")
    public static final int _SC_ARG_MAX = Unistd._SC_ARG_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L558-L558")
    public static final int _SC_ASYNCHRONOUS_IO = Unistd._SC_ASYNCHRONOUS_IO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L559-L559")
    public static final int _SC_ATEXIT_MAX = Unistd._SC_ATEXIT_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L560-L560")
    public static final int _SC_AVPHYS_PAGES = Unistd._SC_AVPHYS_PAGES();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L561-L561")
    public static final int _SC_BC_BASE_MAX = Unistd._SC_BC_BASE_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L562-L562")
    public static final int _SC_BC_DIM_MAX = Unistd._SC_BC_DIM_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L563-L563")
    public static final int _SC_BC_SCALE_MAX = Unistd._SC_BC_SCALE_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L564-L564")
    public static final int _SC_BC_STRING_MAX = Unistd._SC_BC_STRING_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L565-L565")
    public static final int _SC_CHILD_MAX = Unistd._SC_CHILD_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L566-L566")
    public static final int _SC_CLK_TCK = Unistd._SC_CLK_TCK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L567-L567")
    public static final int _SC_COLL_WEIGHTS_MAX = Unistd._SC_COLL_WEIGHTS_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L568-L568")
    public static final int _SC_DELAYTIMER_MAX = Unistd._SC_DELAYTIMER_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L569-L569")
    public static final int _SC_EXPR_NEST_MAX = Unistd._SC_EXPR_NEST_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L570-L570")
    public static final int _SC_FSYNC = Unistd._SC_FSYNC();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L571-L571")
    public static final int _SC_GETGR_R_SIZE_MAX = Unistd._SC_GETGR_R_SIZE_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L572-L572")
    public static final int _SC_GETPW_R_SIZE_MAX = Unistd._SC_GETPW_R_SIZE_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L573-L573")
    public static final int _SC_IOV_MAX = Unistd._SC_IOV_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L574-L574")
    public static final int _SC_JOB_CONTROL = Unistd._SC_JOB_CONTROL();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L575-L575")
    public static final int _SC_LINE_MAX = Unistd._SC_LINE_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L576-L576")
    public static final int _SC_LOGIN_NAME_MAX = Unistd._SC_LOGIN_NAME_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L577-L577")
    public static final int _SC_MAPPED_FILES = Unistd._SC_MAPPED_FILES();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L578-L578")
    public static final int _SC_MEMLOCK = Unistd._SC_MEMLOCK();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L579-L579")
    public static final int _SC_MEMLOCK_RANGE = Unistd._SC_MEMLOCK_RANGE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L580-L580")
    public static final int _SC_MEMORY_PROTECTION = Unistd._SC_MEMORY_PROTECTION();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L581-L581")
    public static final int _SC_MESSAGE_PASSING = Unistd._SC_MESSAGE_PASSING();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L582-L582")
    public static final int _SC_MQ_OPEN_MAX = Unistd._SC_MQ_OPEN_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L583-L583")
    public static final int _SC_MQ_PRIO_MAX = Unistd._SC_MQ_PRIO_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L584-L584")
    public static final int _SC_NGROUPS_MAX = Unistd._SC_NGROUPS_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L585-L585")
    public static final int _SC_NPROCESSORS_CONF = Unistd._SC_NPROCESSORS_CONF();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L586-L586")
    public static final int _SC_NPROCESSORS_ONLN = Unistd._SC_NPROCESSORS_ONLN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L587-L587")
    public static final int _SC_OPEN_MAX = Unistd._SC_OPEN_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L588-L588")
    public static final int _SC_PAGESIZE = Unistd._SC_PAGESIZE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L589-L589")
    public static final int _SC_PAGE_SIZE = Unistd._SC_PAGE_SIZE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L590-L590")
    public static final int _SC_PASS_MAX = Unistd._SC_PASS_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L591-L591")
    public static final int _SC_PHYS_PAGES = Unistd._SC_PHYS_PAGES();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L592-L592")
    public static final int _SC_PRIORITIZED_IO = Unistd._SC_PRIORITIZED_IO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L593-L593")
    public static final int _SC_PRIORITY_SCHEDULING = Unistd._SC_PRIORITY_SCHEDULING();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L594-L594")
    public static final int _SC_REALTIME_SIGNALS = Unistd._SC_REALTIME_SIGNALS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L595-L595")
    public static final int _SC_RE_DUP_MAX = Unistd._SC_RE_DUP_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L596-L596")
    public static final int _SC_RTSIG_MAX = Unistd._SC_RTSIG_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L597-L597")
    public static final int _SC_SAVED_IDS = Unistd._SC_SAVED_IDS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L598-L598")
    public static final int _SC_SEMAPHORES = Unistd._SC_SEMAPHORES();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L599-L599")
    public static final int _SC_SEM_NSEMS_MAX = Unistd._SC_SEM_NSEMS_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L600-L600")
    public static final int _SC_SEM_VALUE_MAX = Unistd._SC_SEM_VALUE_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L601-L601")
    public static final int _SC_SHARED_MEMORY_OBJECTS = Unistd._SC_SHARED_MEMORY_OBJECTS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L602-L602")
    public static final int _SC_SIGQUEUE_MAX = Unistd._SC_SIGQUEUE_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L603-L603")
    public static final int _SC_STREAM_MAX = Unistd._SC_STREAM_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L604-L604")
    public static final int _SC_SYNCHRONIZED_IO = Unistd._SC_SYNCHRONIZED_IO();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L605-L605")
    public static final int _SC_THREADS = Unistd._SC_THREADS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L606-L606")
    public static final int _SC_THREAD_ATTR_STACKADDR = Unistd._SC_THREAD_ATTR_STACKADDR();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L607-L607")
    public static final int _SC_THREAD_ATTR_STACKSIZE = Unistd._SC_THREAD_ATTR_STACKSIZE();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L608-L608")
    public static final int _SC_THREAD_DESTRUCTOR_ITERATIONS =
            Unistd._SC_THREAD_DESTRUCTOR_ITERATIONS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L609-L609")
    public static final int _SC_THREAD_KEYS_MAX = Unistd._SC_THREAD_KEYS_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L610-L610")
    public static final int _SC_THREAD_PRIORITY_SCHEDULING =
            Unistd._SC_THREAD_PRIORITY_SCHEDULING();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L611-L611")
    public static final int _SC_THREAD_PRIO_INHERIT = Unistd._SC_THREAD_PRIO_INHERIT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L612-L612")
    public static final int _SC_THREAD_PRIO_PROTECT = Unistd._SC_THREAD_PRIO_PROTECT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L613-L613")
    public static final int _SC_THREAD_SAFE_FUNCTIONS = Unistd._SC_THREAD_SAFE_FUNCTIONS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L614-L614")
    public static final int _SC_THREAD_STACK_MIN = Unistd._SC_THREAD_STACK_MIN();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L615-L615")
    public static final int _SC_THREAD_THREADS_MAX = Unistd._SC_THREAD_THREADS_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L616-L616")
    public static final int _SC_TIMERS = Unistd._SC_TIMERS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L617-L617")
    public static final int _SC_TIMER_MAX = Unistd._SC_TIMER_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L618-L618")
    public static final int _SC_TTY_NAME_MAX = Unistd._SC_TTY_NAME_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L619-L619")
    public static final int _SC_TZNAME_MAX = Unistd._SC_TZNAME_MAX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L620-L620")
    public static final int _SC_VERSION = Unistd._SC_VERSION();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L621-L621")
    public static final int _SC_XBS5_ILP32_OFF32 = Unistd._SC_XBS5_ILP32_OFF32();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L622-L622")
    public static final int _SC_XBS5_ILP32_OFFBIG = Unistd._SC_XBS5_ILP32_OFFBIG();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L623-L623")
    public static final int _SC_XBS5_LP64_OFF64 = Unistd._SC_XBS5_LP64_OFF64();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L624-L624")
    public static final int _SC_XBS5_LPBIG_OFFBIG = Unistd._SC_XBS5_LPBIG_OFFBIG();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L625-L625")
    public static final int _SC_XOPEN_CRYPT = Unistd._SC_XOPEN_CRYPT();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L626-L626")
    public static final int _SC_XOPEN_ENH_I18N = Unistd._SC_XOPEN_ENH_I18N();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L627-L627")
    public static final int _SC_XOPEN_LEGACY = Unistd._SC_XOPEN_LEGACY();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L628-L628")
    public static final int _SC_XOPEN_REALTIME = Unistd._SC_XOPEN_REALTIME();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L629-L629")
    public static final int _SC_XOPEN_REALTIME_THREADS = Unistd._SC_XOPEN_REALTIME_THREADS();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L630-L630")
    public static final int _SC_XOPEN_SHM = Unistd._SC_XOPEN_SHM();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L631-L631")
    public static final int _SC_XOPEN_UNIX = Unistd._SC_XOPEN_UNIX();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L632-L632")
    public static final int _SC_XOPEN_VERSION = Unistd._SC_XOPEN_VERSION();
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L633-L633")
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
