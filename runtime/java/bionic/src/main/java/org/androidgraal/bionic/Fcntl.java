package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.type.CCharPointer;

@CContext(BionicDirectives.class)
public final class Fcntl {

    private Fcntl() {
    }

    @CConstant
    public static native int FD_CLOEXEC();

    @CConstant
    public static native int F_DUPFD();

    @CConstant
    public static native int F_DUPFD_CLOEXEC();

    @CConstant
    public static native int F_GETFD();

    @CConstant
    public static native int F_GETFL();

    @CConstant
    public static native int F_GETLK();

    @CConstant
    public static native int F_GETLK64();

    @CConstant
    public static native int F_GETOWN();

    @CConstant
    public static native int F_RDLCK();

    @CConstant
    public static native int F_SETFD();

    @CConstant
    public static native int F_SETFL();

    @CConstant
    public static native int F_SETLK();

    @CConstant
    public static native int F_SETLK64();

    @CConstant
    public static native int F_SETLKW();

    @CConstant
    public static native int F_SETLKW64();

    @CConstant
    public static native int F_SETOWN();

    @CConstant
    public static native int F_UNLCK();

    @CConstant
    public static native int F_WRLCK();

    @CConstant
    public static native int O_ACCMODE();

    @CConstant
    public static native int O_APPEND();

    @CConstant
    public static native int O_CLOEXEC();

    @CConstant
    public static native int O_CREAT();

    @CConstant
    public static native int O_DSYNC();

    @CConstant
    public static native int O_EXCL();

    @CConstant
    public static native int O_NOCTTY();

    @CConstant
    public static native int O_NOFOLLOW();

    @CConstant
    public static native int O_NONBLOCK();

    @CConstant
    public static native int O_RDONLY();

    @CConstant
    public static native int O_RDWR();

    @CConstant
    public static native int O_SYNC();

    @CConstant
    public static native int O_TRUNC();

    @CConstant
    public static native int O_WRONLY();

    @CFunction
    public static native int open(CCharPointer path, int flags, int mode);

    @CFunction
    public static native int fcntl(int fd, int cmd, int arg);

    @CFunction
    public static native int fcntl(int fd, int cmd);

    @CFunction
    public static native int posix_fallocate64(int fd, long offset, long length);
}
