package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CFieldAddress;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.word.PointerBase;

@CContext(BionicDirectives.class)
public final class Stat {

    private Stat() {
    }

    @CConstant
    public static native int S_IFBLK();

    @CConstant
    public static native int S_IFCHR();

    @CConstant
    public static native int S_IFDIR();

    @CConstant
    public static native int S_IFIFO();

    @CConstant
    public static native int S_IFLNK();

    @CConstant
    public static native int S_IFMT();

    @CConstant
    public static native int S_IFREG();

    @CConstant
    public static native int S_IFSOCK();

    @CConstant
    public static native int S_IRGRP();

    @CConstant
    public static native int S_IROTH();

    @CConstant
    public static native int S_IRUSR();

    @CConstant
    public static native int S_IRWXG();

    @CConstant
    public static native int S_IRWXO();

    @CConstant
    public static native int S_IRWXU();

    @CConstant
    public static native int S_ISGID();

    @CConstant
    public static native int S_ISUID();

    @CConstant
    public static native int S_ISVTX();

    @CConstant
    public static native int S_IWGRP();

    @CConstant
    public static native int S_IWOTH();

    @CConstant
    public static native int S_IWUSR();

    @CConstant
    public static native int S_IXGRP();

    @CConstant
    public static native int S_IXOTH();

    @CConstant
    public static native int S_IXUSR();

    @CStruct(addStructKeyword = true)
    public interface stat64 extends PointerBase {
        @CField
        long st_dev();

        @CField
        long st_ino();

        @CField
        int st_mode();

        @CField
        int st_nlink();

        @CField
        int st_uid();

        @CField
        int st_gid();

        @CField
        long st_rdev();

        @CField
        long st_size();

        @CField
        int st_blksize();

        @CField
        long st_blocks();

        @CFieldAddress
        Time.timespec st_atim();

        @CFieldAddress
        Time.timespec st_mtim();

        @CFieldAddress
        Time.timespec st_ctim();
    }

    @CFunction
    public static native int chmod(CCharPointer path, int mode);

    @CFunction
    public static native int fchmod(int fd, int mode);

    @CFunction
    public static native int fstat64(int fd, stat64 buf);

    @CFunction
    public static native int lstat64(CCharPointer path, stat64 buf);

    @CFunction
    public static native int mkdir(CCharPointer path, int mode);

    @CFunction
    public static native int mkfifo(CCharPointer path, int mode);

    @CFunction
    public static native int stat64(CCharPointer path, stat64 buf);

    @CFunction
    public static native int umask(int mask);
}
