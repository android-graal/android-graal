package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.word.PointerBase;

@CContext(BionicDirectives.class)
public final class Statvfs {

    private Statvfs() {
    }

    @CConstant
    public static native int ST_MANDLOCK();

    @CConstant
    public static native int ST_NOATIME();

    @CConstant
    public static native int ST_NODEV();

    @CConstant
    public static native int ST_NODIRATIME();

    @CConstant
    public static native int ST_NOEXEC();

    @CConstant
    public static native int ST_NOSUID();

    @CConstant
    public static native int ST_RDONLY();

    @CConstant
    public static native int ST_RELATIME();

    @CConstant
    public static native int ST_SYNCHRONOUS();

    @CStruct(addStructKeyword = true)
    public interface statvfs extends PointerBase {
        @CField
        long f_bsize();

        @CField
        long f_frsize();

        @CField
        long f_blocks();

        @CField
        long f_bfree();

        @CField
        long f_bavail();

        @CField
        long f_files();

        @CField
        long f_ffree();

        @CField
        long f_favail();

        @CField
        long f_fsid();

        @CField
        long f_flag();

        @CField
        long f_namemax();
    }

    @CFunction
    public static native int fstatvfs(int fd, statvfs buf);

    @CFunction
    public static native int statvfs(CCharPointer path, statvfs buf);
}
