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
import org.androidgraal.hosted.annotate.Adapted;
import org.androidgraal.hosted.annotate.Emulated;

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

    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L65-L65",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int AF_INET = Socket.AF_INET();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L66-L66",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int AF_INET6 = Socket.AF_INET6();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L68-L68",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int AF_NETLINK = Socket.AF_NETLINK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L67-L67",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int AF_PACKET = Socket.AF_PACKET();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L69-L69",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int AF_UNIX = Socket.AF_UNIX();

    /**
     * The virt-vsock address family, linux specific.
     * It is used with {@code struct sockaddr_vm} from uapi/linux/vm_sockets.h.
     *
     * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
     * @see VmSocketAddress
     */
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L70-L70",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int AF_VSOCK = Socket.AF_VSOCK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L71-L71",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int AF_UNSPEC = Socket.AF_UNSPEC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L72-L72",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int AI_ADDRCONFIG = Netdb.AI_ADDRCONFIG();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L73-L73",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int AI_ALL = Netdb.AI_ALL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L74-L74",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int AI_CANONNAME = Netdb.AI_CANONNAME();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L75-L75",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int AI_NUMERICHOST = Netdb.AI_NUMERICHOST();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L76-L76",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int AI_NUMERICSERV = Netdb.AI_NUMERICSERV();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L77-L77",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int AI_PASSIVE = Netdb.AI_PASSIVE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L78-L78",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int AI_V4MAPPED = Netdb.AI_V4MAPPED();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L79-L79",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ARPHRD_ETHER = Net.ARPHRD_ETHER();

    /**
      * The virtio-vsock {@code svmPort} value to bind for any available port.
      *
      * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
      * @see VmSocketAddress
      */
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L80-L80",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int VMADDR_PORT_ANY = Vm.VMADDR_PORT_ANY();

    /**
      * The virtio-vsock {@code svmCid} value to listens for all CIDs.
      *
      * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
      * @see VmSocketAddress
      */
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L81-L81",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int VMADDR_CID_ANY = Vm.VMADDR_CID_ANY();

    /**
      * The virtio-vsock {@code svmCid} value for host communication.
      *
      * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
      * @see VmSocketAddress
      */
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L82-L82",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int VMADDR_CID_LOCAL = Vm.VMADDR_CID_LOCAL();

    /**
      * The virtio-vsock {@code svmCid} value for loopback communication.
      *
      * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
      * @see VmSocketAddress
      */
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L83-L83",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int VMADDR_CID_HOST = Vm.VMADDR_CID_HOST();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L85-L85",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_AUDIT_CONTROL = Capability.CAP_AUDIT_CONTROL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L86-L86",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_AUDIT_WRITE = Capability.CAP_AUDIT_WRITE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L87-L87",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_BLOCK_SUSPEND = Capability.CAP_BLOCK_SUSPEND();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L88-L88",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_CHOWN = Capability.CAP_CHOWN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L89-L89",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_DAC_OVERRIDE = Capability.CAP_DAC_OVERRIDE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L90-L90",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_DAC_READ_SEARCH = Capability.CAP_DAC_READ_SEARCH();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L91-L91",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_FOWNER = Capability.CAP_FOWNER();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L92-L92",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_FSETID = Capability.CAP_FSETID();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L93-L93",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_IPC_LOCK = Capability.CAP_IPC_LOCK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L94-L94",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_IPC_OWNER = Capability.CAP_IPC_OWNER();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L95-L95",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_KILL = Capability.CAP_KILL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L96-L96",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_LAST_CAP = Capability.CAP_LAST_CAP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L97-L97",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_LEASE = Capability.CAP_LEASE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L98-L98",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_LINUX_IMMUTABLE = Capability.CAP_LINUX_IMMUTABLE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L99-L99",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_MAC_ADMIN = Capability.CAP_MAC_ADMIN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L100-L100",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_MAC_OVERRIDE = Capability.CAP_MAC_OVERRIDE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L101-L101",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_MKNOD = Capability.CAP_MKNOD();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L102-L102",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_NET_ADMIN = Capability.CAP_NET_ADMIN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L103-L103",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_NET_BIND_SERVICE = Capability.CAP_NET_BIND_SERVICE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L104-L104",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_NET_BROADCAST = Capability.CAP_NET_BROADCAST();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L105-L105",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_NET_RAW = Capability.CAP_NET_RAW();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L106-L106",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_SETFCAP = Capability.CAP_SETFCAP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L107-L107",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_SETGID = Capability.CAP_SETGID();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L108-L108",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_SETPCAP = Capability.CAP_SETPCAP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L109-L109",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_SETUID = Capability.CAP_SETUID();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L110-L110",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_SYS_ADMIN = Capability.CAP_SYS_ADMIN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L111-L111",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_SYS_BOOT = Capability.CAP_SYS_BOOT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L112-L112",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_SYS_CHROOT = Capability.CAP_SYS_CHROOT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L113-L113",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_SYSLOG = Capability.CAP_SYSLOG();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L114-L114",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_SYS_MODULE = Capability.CAP_SYS_MODULE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L115-L115",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_SYS_NICE = Capability.CAP_SYS_NICE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L116-L116",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_SYS_PACCT = Capability.CAP_SYS_PACCT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L117-L117",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_SYS_PTRACE = Capability.CAP_SYS_PTRACE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L118-L118",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_SYS_RAWIO = Capability.CAP_SYS_RAWIO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L119-L119",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_SYS_RESOURCE = Capability.CAP_SYS_RESOURCE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L120-L120",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_SYS_TIME = Capability.CAP_SYS_TIME();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L121-L121",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_SYS_TTY_CONFIG = Capability.CAP_SYS_TTY_CONFIG();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L122-L122",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int CAP_WAKE_ALARM = Capability.CAP_WAKE_ALARM();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L123-L123",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int E2BIG = Errno.E2BIG();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L124-L124",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EACCES = Errno.EACCES();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L125-L125",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EADDRINUSE = Errno.EADDRINUSE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L126-L126",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EADDRNOTAVAIL = Errno.EADDRNOTAVAIL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L127-L127",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EAFNOSUPPORT = Errno.EAFNOSUPPORT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L128-L128",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EAGAIN = Errno.EAGAIN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L129-L129",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EAI_AGAIN = Netdb.EAI_AGAIN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L130-L130",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EAI_BADFLAGS = Netdb.EAI_BADFLAGS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L131-L131",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EAI_FAIL = Netdb.EAI_FAIL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L132-L132",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EAI_FAMILY = Netdb.EAI_FAMILY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L133-L133",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EAI_MEMORY = Netdb.EAI_MEMORY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L134-L134",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EAI_NODATA = Netdb.EAI_NODATA();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L135-L135",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EAI_NONAME = Netdb.EAI_NONAME();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L136-L136",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EAI_OVERFLOW = Netdb.EAI_OVERFLOW();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L137-L137",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EAI_SERVICE = Netdb.EAI_SERVICE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L138-L138",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EAI_SOCKTYPE = Netdb.EAI_SOCKTYPE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L139-L139",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EAI_SYSTEM = Netdb.EAI_SYSTEM();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L140-L140",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EALREADY = Errno.EALREADY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L141-L141",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EBADF = Errno.EBADF();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L142-L142",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EBADMSG = Errno.EBADMSG();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L143-L143",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EBUSY = Errno.EBUSY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L144-L144",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ECANCELED = Errno.ECANCELED();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L145-L145",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ECHILD = Errno.ECHILD();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L146-L146",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ECONNABORTED = Errno.ECONNABORTED();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L147-L147",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ECONNREFUSED = Errno.ECONNREFUSED();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L148-L148",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ECONNRESET = Errno.ECONNRESET();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L149-L149",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EDEADLK = Errno.EDEADLK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L150-L150",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EDESTADDRREQ = Errno.EDESTADDRREQ();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L151-L151",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EDOM = Errno.EDOM();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L152-L152",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EDQUOT = Errno.EDQUOT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L153-L153",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EEXIST = Errno.EEXIST();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L154-L154",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EFAULT = Errno.EFAULT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L155-L155",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EFBIG = Errno.EFBIG();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L156-L156",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EHOSTUNREACH = Errno.EHOSTUNREACH();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L157-L157",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EIDRM = Errno.EIDRM();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L158-L158",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EILSEQ = Errno.EILSEQ();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L159-L159",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EINPROGRESS = Errno.EINPROGRESS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L160-L160",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EINTR = Errno.EINTR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L161-L161",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EINVAL = Errno.EINVAL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L162-L162",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EIO = Errno.EIO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L163-L163",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EISCONN = Errno.EISCONN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L164-L164",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EISDIR = Errno.EISDIR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L165-L165",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ELOOP = Errno.ELOOP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L166-L166",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EMFILE = Errno.EMFILE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L167-L167",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EMLINK = Errno.EMLINK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L168-L168",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EMSGSIZE = Errno.EMSGSIZE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L169-L169",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EMULTIHOP = Errno.EMULTIHOP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L170-L170",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENAMETOOLONG = Errno.ENAMETOOLONG();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L171-L171",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENETDOWN = Errno.ENETDOWN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L172-L172",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENETRESET = Errno.ENETRESET();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L173-L173",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENETUNREACH = Errno.ENETUNREACH();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L174-L174",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENFILE = Errno.ENFILE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L175-L175",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOBUFS = Errno.ENOBUFS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L176-L176",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENODATA = Errno.ENODATA();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L177-L177",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENODEV = Errno.ENODEV();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L178-L178",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOENT = Errno.ENOENT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L179-L179",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOEXEC = Errno.ENOEXEC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L180-L180",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOLCK = Errno.ENOLCK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L181-L181",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOLINK = Errno.ENOLINK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L182-L182",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOMEM = Errno.ENOMEM();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L183-L183",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOMSG = Errno.ENOMSG();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L184-L184",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENONET = Errno.ENONET();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L185-L185",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOPROTOOPT = Errno.ENOPROTOOPT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L186-L186",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOSPC = Errno.ENOSPC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L187-L187",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOSR = Errno.ENOSR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L188-L188",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOSTR = Errno.ENOSTR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L189-L189",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOSYS = Errno.ENOSYS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L190-L190",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOTCONN = Errno.ENOTCONN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L191-L191",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOTDIR = Errno.ENOTDIR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L192-L192",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOTEMPTY = Errno.ENOTEMPTY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L193-L193",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOTSOCK = Errno.ENOTSOCK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L194-L194",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOTSUP = Errno.ENOTSUP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L195-L195",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENOTTY = Errno.ENOTTY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L196-L196",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ENXIO = Errno.ENXIO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L197-L197",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EOPNOTSUPP = Errno.EOPNOTSUPP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L198-L198",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EOVERFLOW = Errno.EOVERFLOW();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L199-L199",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EPERM = Errno.EPERM();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L200-L200",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EPIPE = Errno.EPIPE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L201-L201",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EPROTO = Errno.EPROTO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L202-L202",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EPROTONOSUPPORT = Errno.EPROTONOSUPPORT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L203-L203",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EPROTOTYPE = Errno.EPROTOTYPE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L204-L204",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ERANGE = Errno.ERANGE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L205-L205",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EROFS = Errno.EROFS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L206-L206",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ESPIPE = Errno.ESPIPE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L207-L207",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ESRCH = Errno.ESRCH();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L208-L208",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ESTALE = Errno.ESTALE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L209-L209",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ETH_P_ALL = Netinet.ETH_P_ALL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L210-L210",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ETH_P_ARP = Netinet.ETH_P_ARP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L211-L211",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ETH_P_IP = Netinet.ETH_P_IP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L212-L212",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ETH_P_IPV6 = Netinet.ETH_P_IPV6();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L213-L213",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ETIME = Errno.ETIME();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L214-L214",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ETIMEDOUT = Errno.ETIMEDOUT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L215-L215",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ETXTBSY = Errno.ETXTBSY();
    // On Linux, EWOULDBLOCK == EAGAIN. Use EAGAIN instead, to reduce confusion.
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L217-L217",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EXDEV = Errno.EXDEV();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L218-L218",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EXIT_FAILURE = Stdlib.EXIT_FAILURE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L219-L219",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int EXIT_SUCCESS = Stdlib.EXIT_SUCCESS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L220-L220",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int FD_CLOEXEC = Fcntl.FD_CLOEXEC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L221-L221",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int FIONREAD = Ioctl.FIONREAD();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L222-L222",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_DUPFD = Fcntl.F_DUPFD();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L223-L223",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_DUPFD_CLOEXEC = Fcntl.F_DUPFD_CLOEXEC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L224-L224",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_GETFD = Fcntl.F_GETFD();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L225-L225",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_GETFL = Fcntl.F_GETFL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L226-L226",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_GETLK = Fcntl.F_GETLK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L227-L227",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_GETLK64 = Fcntl.F_GETLK64();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L228-L228",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_GETOWN = Fcntl.F_GETOWN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L229-L229",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_OK = Unistd.F_OK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L230-L230",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_RDLCK = Fcntl.F_RDLCK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L231-L231",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_SETFD = Fcntl.F_SETFD();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L232-L232",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_SETFL = Fcntl.F_SETFL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L233-L233",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_SETLK = Fcntl.F_SETLK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L234-L234",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_SETLK64 = Fcntl.F_SETLK64();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L235-L235",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_SETLKW = Fcntl.F_SETLKW();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L236-L236",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_SETLKW64 = Fcntl.F_SETLKW64();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L237-L237",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_SETOWN = Fcntl.F_SETOWN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L238-L238",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_UNLCK = Fcntl.F_UNLCK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L239-L239",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int F_WRLCK = Fcntl.F_WRLCK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L240-L240",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ICMP_ECHO = Netinet.ICMP_ECHO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L241-L241",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ICMP_ECHOREPLY = Netinet.ICMP_ECHOREPLY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L242-L242",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ICMP6_ECHO_REQUEST = Netinet.ICMP6_ECHO_REQUEST();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L243-L243",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ICMP6_ECHO_REPLY = Netinet.ICMP6_ECHO_REPLY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L244-L244",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFA_F_DADFAILED = IfAddr.IFA_F_DADFAILED();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L245-L245",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFA_F_DEPRECATED = IfAddr.IFA_F_DEPRECATED();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L246-L246",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFA_F_HOMEADDRESS = IfAddr.IFA_F_HOMEADDRESS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L247-L247",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFA_F_MANAGETEMPADDR = IfAddr.IFA_F_MANAGETEMPADDR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L248-L248",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFA_F_NODAD = IfAddr.IFA_F_NODAD();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L249-L249",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFA_F_NOPREFIXROUTE = IfAddr.IFA_F_NOPREFIXROUTE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L250-L250",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFA_F_OPTIMISTIC = IfAddr.IFA_F_OPTIMISTIC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L251-L251",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFA_F_PERMANENT = IfAddr.IFA_F_PERMANENT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L252-L252",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFA_F_SECONDARY = IfAddr.IFA_F_SECONDARY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L253-L253",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFA_F_TEMPORARY = IfAddr.IFA_F_TEMPORARY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L254-L254",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFA_F_TENTATIVE = IfAddr.IFA_F_TENTATIVE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L255-L255",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFF_ALLMULTI = Net.IFF_ALLMULTI();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L256-L256",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFF_AUTOMEDIA = Net.IFF_AUTOMEDIA();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L257-L257",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFF_BROADCAST = Net.IFF_BROADCAST();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L258-L258",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFF_DEBUG = Net.IFF_DEBUG();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L259-L259",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFF_DYNAMIC = Net.IFF_DYNAMIC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L260-L260",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFF_LOOPBACK = Net.IFF_LOOPBACK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L261-L261",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFF_MASTER = Net.IFF_MASTER();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L262-L262",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFF_MULTICAST = Net.IFF_MULTICAST();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L263-L263",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFF_NOARP = Net.IFF_NOARP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L264-L264",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFF_NOTRAILERS = Net.IFF_NOTRAILERS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L265-L265",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFF_POINTOPOINT = Net.IFF_POINTOPOINT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L266-L266",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFF_PORTSEL = Net.IFF_PORTSEL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L267-L267",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFF_PROMISC = Net.IFF_PROMISC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L268-L268",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFF_RUNNING = Net.IFF_RUNNING();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L269-L269",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFF_SLAVE = Net.IFF_SLAVE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L270-L270",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IFF_UP = Net.IFF_UP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L271-L271",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPPROTO_ICMP = Netinet.IPPROTO_ICMP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L272-L272",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPPROTO_ICMPV6 = Netinet.IPPROTO_ICMPV6();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L273-L273",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPPROTO_IP = Netinet.IPPROTO_IP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L274-L274",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPPROTO_IPV6 = Netinet.IPPROTO_IPV6();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L275-L275",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPPROTO_RAW = Netinet.IPPROTO_RAW();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L276-L276",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPPROTO_TCP = Netinet.IPPROTO_TCP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L277-L277",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPPROTO_UDP = Netinet.IPPROTO_UDP();

    /**
     * Encapsulation Security Payload protocol
     *
     * <p>Defined in /uapi/linux/in.h
     */
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L278-L278",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPPROTO_ESP = Netinet.IPPROTO_ESP();

    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L279-L279",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPV6_CHECKSUM = Netinet.IPV6_CHECKSUM();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L280-L280",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPV6_MULTICAST_HOPS = Netinet.IPV6_MULTICAST_HOPS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L281-L281",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPV6_MULTICAST_IF = Netinet.IPV6_MULTICAST_IF();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L282-L282",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPV6_MULTICAST_LOOP = Netinet.IPV6_MULTICAST_LOOP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L283-L283",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPV6_PKTINFO = Netinet.IPV6_PKTINFO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L284-L284",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPV6_RECVDSTOPTS = Netinet.IPV6_RECVDSTOPTS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L285-L285",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPV6_RECVHOPLIMIT = Netinet.IPV6_RECVHOPLIMIT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L286-L286",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPV6_RECVHOPOPTS = Netinet.IPV6_RECVHOPOPTS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L287-L287",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPV6_RECVPKTINFO = Netinet.IPV6_RECVPKTINFO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L288-L288",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPV6_RECVRTHDR = Netinet.IPV6_RECVRTHDR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L289-L289",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPV6_RECVTCLASS = Netinet.IPV6_RECVTCLASS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L290-L290",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPV6_TCLASS = Netinet.IPV6_TCLASS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L291-L291",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPV6_UNICAST_HOPS = Netinet.IPV6_UNICAST_HOPS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L292-L292",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IPV6_V6ONLY = Netinet.IPV6_V6ONLY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L294-L294",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IP_MULTICAST_IF = Netinet.IP_MULTICAST_IF();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L295-L295",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IP_MULTICAST_LOOP = Netinet.IP_MULTICAST_LOOP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L296-L296",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IP_MULTICAST_TTL = Netinet.IP_MULTICAST_TTL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L298-L298",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IP_TOS = Netinet.IP_TOS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L299-L299",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int IP_TTL = Netinet.IP_TTL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L325-L325",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MAP_FIXED = Mman.MAP_FIXED();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L326-L326",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MAP_ANONYMOUS = Mman.MAP_ANONYMOUS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L328-L328",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MAP_PRIVATE = Mman.MAP_PRIVATE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L329-L329",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MAP_SHARED = Mman.MAP_SHARED();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L330-L330",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MCAST_JOIN_GROUP = Netinet.MCAST_JOIN_GROUP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L331-L331",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MCAST_LEAVE_GROUP = Netinet.MCAST_LEAVE_GROUP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L332-L332",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MCAST_JOIN_SOURCE_GROUP = Netinet.MCAST_JOIN_SOURCE_GROUP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L333-L333",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MCAST_LEAVE_SOURCE_GROUP = Netinet.MCAST_LEAVE_SOURCE_GROUP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L334-L334",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MCAST_BLOCK_SOURCE = Netinet.MCAST_BLOCK_SOURCE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L335-L335",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MCAST_UNBLOCK_SOURCE = Netinet.MCAST_UNBLOCK_SOURCE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L336-L336",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MCL_CURRENT = Mman.MCL_CURRENT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L337-L337",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MCL_FUTURE = Mman.MCL_FUTURE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L338-L338",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MFD_CLOEXEC = Mman.MFD_CLOEXEC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L339-L339",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MSG_CTRUNC = Socket.MSG_CTRUNC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L340-L340",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MSG_DONTROUTE = Socket.MSG_DONTROUTE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L341-L341",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MSG_EOR = Socket.MSG_EOR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L342-L342",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MSG_OOB = Socket.MSG_OOB();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L343-L343",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MSG_PEEK = Socket.MSG_PEEK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L344-L344",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MSG_TRUNC = Socket.MSG_TRUNC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L345-L345",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MSG_WAITALL = Socket.MSG_WAITALL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L346-L346",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MS_ASYNC = Mman.MS_ASYNC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L347-L347",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MS_INVALIDATE = Mman.MS_INVALIDATE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L348-L348",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int MS_SYNC = Mman.MS_SYNC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L349-L349",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int NETLINK_NETFILTER = Netlink.NETLINK_NETFILTER();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L350-L350",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int NETLINK_ROUTE = Netlink.NETLINK_ROUTE();
    /**
     * SELinux enforces that only system_server and netd may use this netlink socket type.
     */
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L351-L351",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int NETLINK_INET_DIAG = Netlink.NETLINK_INET_DIAG();

    /**
     * SELinux enforces that only system_server and netd may use this netlink socket type.
     *
     * @see <a href="https://man7.org/linux/man-pages/man7/netlink.7.html">netlink(7)</a>
     */
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L352-L352",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int NETLINK_XFRM = Netlink.NETLINK_XFRM();

    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L353-L353",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int NI_DGRAM = Netdb.NI_DGRAM();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L354-L354",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int NI_NAMEREQD = Netdb.NI_NAMEREQD();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L355-L355",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int NI_NOFQDN = Netdb.NI_NOFQDN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L356-L356",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int NI_NUMERICHOST = Netdb.NI_NUMERICHOST();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L357-L357",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int NI_NUMERICSERV = Netdb.NI_NUMERICSERV();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L358-L358",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int O_ACCMODE = Fcntl.O_ACCMODE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L359-L359",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int O_APPEND = Fcntl.O_APPEND();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L360-L360",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int O_CLOEXEC = Fcntl.O_CLOEXEC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L361-L361",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int O_CREAT = Fcntl.O_CREAT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L363-L363",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int O_EXCL = Fcntl.O_EXCL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L364-L364",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int O_NOCTTY = Fcntl.O_NOCTTY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L365-L365",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int O_NOFOLLOW = Fcntl.O_NOFOLLOW();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L366-L366",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int O_NONBLOCK = Fcntl.O_NONBLOCK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L367-L367",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int O_RDONLY = Fcntl.O_RDONLY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L368-L368",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int O_RDWR = Fcntl.O_RDWR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L369-L369",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int O_SYNC = Fcntl.O_SYNC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L370-L370",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int O_DSYNC = Fcntl.O_DSYNC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L371-L371",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int O_TRUNC = Fcntl.O_TRUNC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L372-L372",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int O_WRONLY = Fcntl.O_WRONLY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L373-L373",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int POLLERR = Poll.POLLERR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L374-L374",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int POLLHUP = Poll.POLLHUP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L375-L375",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int POLLIN = Poll.POLLIN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L376-L376",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int POLLNVAL = Poll.POLLNVAL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L377-L377",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int POLLOUT = Poll.POLLOUT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L378-L378",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int POLLPRI = Poll.POLLPRI();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L379-L379",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int POLLRDBAND = Poll.POLLRDBAND();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L380-L380",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int POLLRDNORM = Poll.POLLRDNORM();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L381-L381",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int POLLWRBAND = Poll.POLLWRBAND();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L382-L382",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int POLLWRNORM = Poll.POLLWRNORM();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L385-L385",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int PR_GET_DUMPABLE = Prctl.PR_GET_DUMPABLE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L386-L386",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int PR_SET_DUMPABLE = Prctl.PR_SET_DUMPABLE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L387-L387",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int PR_SET_NO_NEW_PRIVS = Prctl.PR_SET_NO_NEW_PRIVS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L388-L388",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int PROT_EXEC = Mman.PROT_EXEC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L389-L389",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int PROT_NONE = Mman.PROT_NONE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L390-L390",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int PROT_READ = Mman.PROT_READ();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L391-L391",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int PROT_WRITE = Mman.PROT_WRITE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L392-L392",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int R_OK = Unistd.R_OK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L395-L395",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int RT_SCOPE_HOST = Rtnetlink.RT_SCOPE_HOST();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L396-L396",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int RT_SCOPE_LINK = Rtnetlink.RT_SCOPE_LINK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L397-L397",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int RT_SCOPE_NOWHERE = Rtnetlink.RT_SCOPE_NOWHERE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L398-L398",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int RT_SCOPE_SITE = Rtnetlink.RT_SCOPE_SITE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L399-L399",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int RT_SCOPE_UNIVERSE = Rtnetlink.RT_SCOPE_UNIVERSE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L410-L410",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int RTMGRP_NEIGH = Rtnetlink.RTMGRP_NEIGH();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L413-L413",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SEEK_CUR = Unistd.SEEK_CUR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L414-L414",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SEEK_END = Unistd.SEEK_END();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L415-L415",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SEEK_SET = Unistd.SEEK_SET();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L416-L416",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SHUT_RD = Socket.SHUT_RD();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L417-L417",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SHUT_RDWR = Socket.SHUT_RDWR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L418-L418",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SHUT_WR = Socket.SHUT_WR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L419-L419",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGABRT = Signal.SIGABRT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L420-L420",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGALRM = Signal.SIGALRM();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L421-L421",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGBUS = Signal.SIGBUS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L422-L422",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGCHLD = Signal.SIGCHLD();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L423-L423",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGCONT = Signal.SIGCONT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L424-L424",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGFPE = Signal.SIGFPE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L425-L425",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGHUP = Signal.SIGHUP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L426-L426",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGILL = Signal.SIGILL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L427-L427",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGINT = Signal.SIGINT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L428-L428",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGIO = Signal.SIGIO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L429-L429",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGKILL = Signal.SIGKILL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L430-L430",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGPIPE = Signal.SIGPIPE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L431-L431",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGPROF = Signal.SIGPROF();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L432-L432",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGPWR = Signal.SIGPWR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L433-L433",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGQUIT = Signal.SIGQUIT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L434-L434",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGRTMAX = Signal.SIGRTMAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L435-L435",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGRTMIN = Signal.SIGRTMIN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L436-L436",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGSEGV = Signal.SIGSEGV();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L437-L437",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGSTKFLT = Signal.SIGSTKFLT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L438-L438",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGSTOP = Signal.SIGSTOP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L439-L439",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGSYS = Signal.SIGSYS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L440-L440",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGTERM = Signal.SIGTERM();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L441-L441",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGTRAP = Signal.SIGTRAP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L442-L442",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGTSTP = Signal.SIGTSTP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L443-L443",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGTTIN = Signal.SIGTTIN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L444-L444",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGTTOU = Signal.SIGTTOU();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L445-L445",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGURG = Signal.SIGURG();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L446-L446",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGUSR1 = Signal.SIGUSR1();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L447-L447",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGUSR2 = Signal.SIGUSR2();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L448-L448",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGVTALRM = Signal.SIGVTALRM();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L449-L449",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGWINCH = Signal.SIGWINCH();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L450-L450",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGXCPU = Signal.SIGXCPU();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L451-L451",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIGXFSZ = Signal.SIGXFSZ();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L452-L452",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIOCGIFADDR = Socket.SIOCGIFADDR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L453-L453",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIOCGIFBRDADDR = Socket.SIOCGIFBRDADDR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L454-L454",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SIOCGIFDSTADDR = Socket.SIOCGIFDSTADDR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L455-L455",
            reason = "bionic's value instead of the placeholder initConstants sets")
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
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L456-L456",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SOCK_CLOEXEC = Socket.SOCK_CLOEXEC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L457-L457",
            reason = "bionic's value instead of the placeholder initConstants sets")
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
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L458-L458",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SOCK_NONBLOCK = Socket.SOCK_NONBLOCK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L459-L459",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SOCK_RAW = Socket.SOCK_RAW();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L460-L460",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SOCK_SEQPACKET = Socket.SOCK_SEQPACKET();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L461-L461",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SOCK_STREAM = Socket.SOCK_STREAM();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L462-L462",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SOL_SOCKET = Socket.SOL_SOCKET();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L463-L463",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SOL_UDP = Socket.SOL_UDP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L464-L464",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SOL_PACKET = Socket.SOL_PACKET();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L465-L465",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_BINDTODEVICE = Socket.SO_BINDTODEVICE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L466-L466",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_BROADCAST = Socket.SO_BROADCAST();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L467-L467",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_DEBUG = Socket.SO_DEBUG();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L469-L469",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_DONTROUTE = Socket.SO_DONTROUTE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L470-L470",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_ERROR = Socket.SO_ERROR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L471-L471",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_KEEPALIVE = Socket.SO_KEEPALIVE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L472-L472",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_LINGER = Socket.SO_LINGER();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L473-L473",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_OOBINLINE = Socket.SO_OOBINLINE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L474-L474",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_PASSCRED = Socket.SO_PASSCRED();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L475-L475",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_PEERCRED = Socket.SO_PEERCRED();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L477-L477",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_RCVBUF = Socket.SO_RCVBUF();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L478-L478",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_RCVLOWAT = Socket.SO_RCVLOWAT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L479-L479",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_RCVTIMEO = Socket.SO_RCVTIMEO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L480-L480",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_REUSEADDR = Socket.SO_REUSEADDR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L481-L481",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_SNDBUF = Socket.SO_SNDBUF();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L482-L482",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_SNDLOWAT = Socket.SO_SNDLOWAT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L483-L483",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_SNDTIMEO = Socket.SO_SNDTIMEO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L484-L484",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int SO_TYPE = Socket.SO_TYPE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L485-L485",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int PACKET_IGNORE_OUTGOING = Netpacket.PACKET_IGNORE_OUTGOING();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L489-L489",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int STDERR_FILENO = Unistd.STDERR_FILENO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L490-L490",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int STDIN_FILENO = Unistd.STDIN_FILENO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L491-L491",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int STDOUT_FILENO = Unistd.STDOUT_FILENO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L492-L492",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ST_MANDLOCK = Statvfs.ST_MANDLOCK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L493-L493",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ST_NOATIME = Statvfs.ST_NOATIME();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L494-L494",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ST_NODEV = Statvfs.ST_NODEV();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L495-L495",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ST_NODIRATIME = Statvfs.ST_NODIRATIME();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L496-L496",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ST_NOEXEC = Statvfs.ST_NOEXEC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L497-L497",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ST_NOSUID = Statvfs.ST_NOSUID();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L498-L498",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ST_RDONLY = Statvfs.ST_RDONLY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L499-L499",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ST_RELATIME = Statvfs.ST_RELATIME();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L500-L500",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int ST_SYNCHRONOUS = Statvfs.ST_SYNCHRONOUS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L501-L501",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IFBLK = Stat.S_IFBLK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L502-L502",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IFCHR = Stat.S_IFCHR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L503-L503",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IFDIR = Stat.S_IFDIR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L504-L504",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IFIFO = Stat.S_IFIFO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L505-L505",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IFLNK = Stat.S_IFLNK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L506-L506",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IFMT = Stat.S_IFMT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L507-L507",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IFREG = Stat.S_IFREG();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L508-L508",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IFSOCK = Stat.S_IFSOCK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L509-L509",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IRGRP = Stat.S_IRGRP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L510-L510",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IROTH = Stat.S_IROTH();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L511-L511",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IRUSR = Stat.S_IRUSR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L512-L512",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IRWXG = Stat.S_IRWXG();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L513-L513",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IRWXO = Stat.S_IRWXO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L514-L514",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IRWXU = Stat.S_IRWXU();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L515-L515",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_ISGID = Stat.S_ISGID();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L516-L516",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_ISUID = Stat.S_ISUID();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L517-L517",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_ISVTX = Stat.S_ISVTX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L518-L518",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IWGRP = Stat.S_IWGRP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L519-L519",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IWOTH = Stat.S_IWOTH();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L520-L520",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IWUSR = Stat.S_IWUSR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L521-L521",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IXGRP = Stat.S_IXGRP();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L522-L522",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IXOTH = Stat.S_IXOTH();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L523-L523",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int S_IXUSR = Stat.S_IXUSR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L524-L524",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int TCP_NODELAY = Netinet.TCP_NODELAY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L525-L525",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int TCP_USER_TIMEOUT = Netinet.TCP_USER_TIMEOUT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L530-L530",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int UDP_GRO = Netinet.UDP_GRO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L531-L531",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int UDP_SEGMENT = Netinet.UDP_SEGMENT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L534-L534",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int WCONTINUED = Wait.WCONTINUED();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L535-L535",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int WEXITED = Wait.WEXITED();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L536-L536",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int WNOHANG = Wait.WNOHANG();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L537-L537",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int WNOWAIT = Wait.WNOWAIT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L538-L538",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int WSTOPPED = Wait.WSTOPPED();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L539-L539",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int WUNTRACED = Wait.WUNTRACED();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L540-L540",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int W_OK = Unistd.W_OK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L543-L543",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int X_OK = Unistd.X_OK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L544-L544",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_2_CHAR_TERM = Unistd._SC_2_CHAR_TERM();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L545-L545",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_2_C_BIND = Unistd._SC_2_C_BIND();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L546-L546",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_2_C_DEV = Unistd._SC_2_C_DEV();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L547-L547",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_2_C_VERSION = Unistd._SC_2_C_VERSION();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L548-L548",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_2_FORT_DEV = Unistd._SC_2_FORT_DEV();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L549-L549",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_2_FORT_RUN = Unistd._SC_2_FORT_RUN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L550-L550",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_2_LOCALEDEF = Unistd._SC_2_LOCALEDEF();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L551-L551",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_2_SW_DEV = Unistd._SC_2_SW_DEV();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L552-L552",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_2_UPE = Unistd._SC_2_UPE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L553-L553",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_2_VERSION = Unistd._SC_2_VERSION();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L554-L554",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_AIO_LISTIO_MAX = Unistd._SC_AIO_LISTIO_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L555-L555",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_AIO_MAX = Unistd._SC_AIO_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L556-L556",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_AIO_PRIO_DELTA_MAX = Unistd._SC_AIO_PRIO_DELTA_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L557-L557",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_ARG_MAX = Unistd._SC_ARG_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L558-L558",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_ASYNCHRONOUS_IO = Unistd._SC_ASYNCHRONOUS_IO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L559-L559",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_ATEXIT_MAX = Unistd._SC_ATEXIT_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L560-L560",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_AVPHYS_PAGES = Unistd._SC_AVPHYS_PAGES();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L561-L561",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_BC_BASE_MAX = Unistd._SC_BC_BASE_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L562-L562",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_BC_DIM_MAX = Unistd._SC_BC_DIM_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L563-L563",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_BC_SCALE_MAX = Unistd._SC_BC_SCALE_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L564-L564",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_BC_STRING_MAX = Unistd._SC_BC_STRING_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L565-L565",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_CHILD_MAX = Unistd._SC_CHILD_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L566-L566",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_CLK_TCK = Unistd._SC_CLK_TCK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L567-L567",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_COLL_WEIGHTS_MAX = Unistd._SC_COLL_WEIGHTS_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L568-L568",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_DELAYTIMER_MAX = Unistd._SC_DELAYTIMER_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L569-L569",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_EXPR_NEST_MAX = Unistd._SC_EXPR_NEST_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L570-L570",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_FSYNC = Unistd._SC_FSYNC();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L571-L571",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_GETGR_R_SIZE_MAX = Unistd._SC_GETGR_R_SIZE_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L572-L572",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_GETPW_R_SIZE_MAX = Unistd._SC_GETPW_R_SIZE_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L573-L573",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_IOV_MAX = Unistd._SC_IOV_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L574-L574",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_JOB_CONTROL = Unistd._SC_JOB_CONTROL();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L575-L575",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_LINE_MAX = Unistd._SC_LINE_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L576-L576",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_LOGIN_NAME_MAX = Unistd._SC_LOGIN_NAME_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L577-L577",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_MAPPED_FILES = Unistd._SC_MAPPED_FILES();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L578-L578",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_MEMLOCK = Unistd._SC_MEMLOCK();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L579-L579",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_MEMLOCK_RANGE = Unistd._SC_MEMLOCK_RANGE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L580-L580",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_MEMORY_PROTECTION = Unistd._SC_MEMORY_PROTECTION();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L581-L581",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_MESSAGE_PASSING = Unistd._SC_MESSAGE_PASSING();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L582-L582",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_MQ_OPEN_MAX = Unistd._SC_MQ_OPEN_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L583-L583",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_MQ_PRIO_MAX = Unistd._SC_MQ_PRIO_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L584-L584",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_NGROUPS_MAX = Unistd._SC_NGROUPS_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L585-L585",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_NPROCESSORS_CONF = Unistd._SC_NPROCESSORS_CONF();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L586-L586",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_NPROCESSORS_ONLN = Unistd._SC_NPROCESSORS_ONLN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L587-L587",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_OPEN_MAX = Unistd._SC_OPEN_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L588-L588",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_PAGESIZE = Unistd._SC_PAGESIZE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L589-L589",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_PAGE_SIZE = Unistd._SC_PAGE_SIZE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L590-L590",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_PASS_MAX = Unistd._SC_PASS_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L591-L591",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_PHYS_PAGES = Unistd._SC_PHYS_PAGES();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L592-L592",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_PRIORITIZED_IO = Unistd._SC_PRIORITIZED_IO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L593-L593",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_PRIORITY_SCHEDULING = Unistd._SC_PRIORITY_SCHEDULING();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L594-L594",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_REALTIME_SIGNALS = Unistd._SC_REALTIME_SIGNALS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L595-L595",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_RE_DUP_MAX = Unistd._SC_RE_DUP_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L596-L596",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_RTSIG_MAX = Unistd._SC_RTSIG_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L597-L597",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_SAVED_IDS = Unistd._SC_SAVED_IDS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L598-L598",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_SEMAPHORES = Unistd._SC_SEMAPHORES();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L599-L599",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_SEM_NSEMS_MAX = Unistd._SC_SEM_NSEMS_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L600-L600",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_SEM_VALUE_MAX = Unistd._SC_SEM_VALUE_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L601-L601",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_SHARED_MEMORY_OBJECTS = Unistd._SC_SHARED_MEMORY_OBJECTS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L602-L602",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_SIGQUEUE_MAX = Unistd._SC_SIGQUEUE_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L603-L603",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_STREAM_MAX = Unistd._SC_STREAM_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L604-L604",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_SYNCHRONIZED_IO = Unistd._SC_SYNCHRONIZED_IO();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L605-L605",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_THREADS = Unistd._SC_THREADS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L606-L606",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_THREAD_ATTR_STACKADDR = Unistd._SC_THREAD_ATTR_STACKADDR();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L607-L607",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_THREAD_ATTR_STACKSIZE = Unistd._SC_THREAD_ATTR_STACKSIZE();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L608-L608",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_THREAD_DESTRUCTOR_ITERATIONS =
            Unistd._SC_THREAD_DESTRUCTOR_ITERATIONS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L609-L609",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_THREAD_KEYS_MAX = Unistd._SC_THREAD_KEYS_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L610-L610",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_THREAD_PRIORITY_SCHEDULING =
            Unistd._SC_THREAD_PRIORITY_SCHEDULING();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L611-L611",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_THREAD_PRIO_INHERIT = Unistd._SC_THREAD_PRIO_INHERIT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L612-L612",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_THREAD_PRIO_PROTECT = Unistd._SC_THREAD_PRIO_PROTECT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L613-L613",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_THREAD_SAFE_FUNCTIONS = Unistd._SC_THREAD_SAFE_FUNCTIONS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L614-L614",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_THREAD_STACK_MIN = Unistd._SC_THREAD_STACK_MIN();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L615-L615",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_THREAD_THREADS_MAX = Unistd._SC_THREAD_THREADS_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L616-L616",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_TIMERS = Unistd._SC_TIMERS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L617-L617",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_TIMER_MAX = Unistd._SC_TIMER_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L618-L618",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_TTY_NAME_MAX = Unistd._SC_TTY_NAME_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L619-L619",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_TZNAME_MAX = Unistd._SC_TZNAME_MAX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L620-L620",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_VERSION = Unistd._SC_VERSION();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L621-L621",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_XBS5_ILP32_OFF32 = Unistd._SC_XBS5_ILP32_OFF32();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L622-L622",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_XBS5_ILP32_OFFBIG = Unistd._SC_XBS5_ILP32_OFFBIG();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L623-L623",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_XBS5_LP64_OFF64 = Unistd._SC_XBS5_LP64_OFF64();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L624-L624",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_XBS5_LPBIG_OFFBIG = Unistd._SC_XBS5_LPBIG_OFFBIG();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L625-L625",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_XOPEN_CRYPT = Unistd._SC_XOPEN_CRYPT();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L626-L626",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_XOPEN_ENH_I18N = Unistd._SC_XOPEN_ENH_I18N();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L627-L627",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_XOPEN_LEGACY = Unistd._SC_XOPEN_LEGACY();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L628-L628",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_XOPEN_REALTIME = Unistd._SC_XOPEN_REALTIME();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L629-L629",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_XOPEN_REALTIME_THREADS = Unistd._SC_XOPEN_REALTIME_THREADS();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L630-L630",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_XOPEN_SHM = Unistd._SC_XOPEN_SHM();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L631-L631",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_XOPEN_UNIX = Unistd._SC_XOPEN_UNIX();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L632-L632",
            reason = "bionic's value instead of the placeholder initConstants sets")
    public static final int _SC_XOPEN_VERSION = Unistd._SC_XOPEN_VERSION();
    @Adapted(value = "https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/native/android_system_OsConstants.cpp#L633-L633",
            reason = "bionic's value instead of the placeholder initConstants sets")
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
