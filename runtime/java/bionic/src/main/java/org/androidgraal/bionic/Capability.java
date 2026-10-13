package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;

@CContext(BionicDirectives.class)
public final class Capability {

    private Capability() {
    }

    @CConstant
    public static native int CAP_AUDIT_CONTROL();

    @CConstant
    public static native int CAP_AUDIT_WRITE();

    @CConstant
    public static native int CAP_BLOCK_SUSPEND();

    @CConstant
    public static native int CAP_CHOWN();

    @CConstant
    public static native int CAP_DAC_OVERRIDE();

    @CConstant
    public static native int CAP_DAC_READ_SEARCH();

    @CConstant
    public static native int CAP_FOWNER();

    @CConstant
    public static native int CAP_FSETID();

    @CConstant
    public static native int CAP_IPC_LOCK();

    @CConstant
    public static native int CAP_IPC_OWNER();

    @CConstant
    public static native int CAP_KILL();

    @CConstant
    public static native int CAP_LAST_CAP();

    @CConstant
    public static native int CAP_LEASE();

    @CConstant
    public static native int CAP_LINUX_IMMUTABLE();

    @CConstant
    public static native int CAP_MAC_ADMIN();

    @CConstant
    public static native int CAP_MAC_OVERRIDE();

    @CConstant
    public static native int CAP_MKNOD();

    @CConstant
    public static native int CAP_NET_ADMIN();

    @CConstant
    public static native int CAP_NET_BIND_SERVICE();

    @CConstant
    public static native int CAP_NET_BROADCAST();

    @CConstant
    public static native int CAP_NET_RAW();

    @CConstant
    public static native int CAP_SETFCAP();

    @CConstant
    public static native int CAP_SETGID();

    @CConstant
    public static native int CAP_SETPCAP();

    @CConstant
    public static native int CAP_SETUID();

    @CConstant
    public static native int CAP_SYSLOG();

    @CConstant
    public static native int CAP_SYS_ADMIN();

    @CConstant
    public static native int CAP_SYS_BOOT();

    @CConstant
    public static native int CAP_SYS_CHROOT();

    @CConstant
    public static native int CAP_SYS_MODULE();

    @CConstant
    public static native int CAP_SYS_NICE();

    @CConstant
    public static native int CAP_SYS_PACCT();

    @CConstant
    public static native int CAP_SYS_PTRACE();

    @CConstant
    public static native int CAP_SYS_RAWIO();

    @CConstant
    public static native int CAP_SYS_RESOURCE();

    @CConstant
    public static native int CAP_SYS_TIME();

    @CConstant
    public static native int CAP_SYS_TTY_CONFIG();

    @CConstant
    public static native int CAP_WAKE_ALARM();
}
