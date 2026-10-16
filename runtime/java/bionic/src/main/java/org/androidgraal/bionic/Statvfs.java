package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.word.PointerBase;
import org.graalvm.word.UnsignedWord;

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
        UnsignedWord f_bsize();

        @CField
        UnsignedWord f_frsize();

        @CField
        UnsignedWord f_blocks();

        @CField
        UnsignedWord f_bfree();

        @CField
        UnsignedWord f_bavail();

        @CField
        UnsignedWord f_files();

        @CField
        UnsignedWord f_ffree();

        @CField
        UnsignedWord f_favail();

        @CField
        UnsignedWord f_fsid();

        @CField
        UnsignedWord f_flag();

        @CField
        UnsignedWord f_namemax();
    }

    @CFunction
    public static native int fstatvfs(int fd, statvfs buf);

    @CFunction
    public static native int statvfs(CCharPointer path, statvfs buf);
}
