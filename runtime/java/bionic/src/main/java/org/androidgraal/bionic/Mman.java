package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.word.PointerBase;
import org.graalvm.word.UnsignedWord;

@CContext(BionicDirectives.class)
public final class Mman {

    private Mman() {
    }

    @CConstant
    public static native int MAP_ANONYMOUS();

    @CConstant
    public static native int MAP_FIXED();

    @CConstant
    public static native int MAP_PRIVATE();

    @CConstant
    public static native int MAP_SHARED();

    @CConstant
    public static native int MCL_CURRENT();

    @CConstant
    public static native int MCL_FUTURE();

    @CConstant
    public static native int MFD_CLOEXEC();

    @CConstant
    public static native int MS_ASYNC();

    @CConstant
    public static native int MS_INVALIDATE();

    @CConstant
    public static native int MS_SYNC();

    @CConstant
    public static native int PROT_EXEC();

    @CConstant
    public static native int PROT_NONE();

    @CConstant
    public static native int PROT_READ();

    @CConstant
    public static native int PROT_WRITE();

    @CFunction
    public static native int mincore(PointerBase address, UnsignedWord length, CCharPointer vector);

    @CFunction
    public static native int mlock(PointerBase address, UnsignedWord length);

    @CFunction
    public static native PointerBase mmap64(PointerBase address, UnsignedWord length, int prot, int flags, int fd,
            long offset);

    @CFunction
    public static native int msync(PointerBase address, UnsignedWord length, int flags);

    @CFunction
    public static native int munlock(PointerBase address, UnsignedWord length);

    @CFunction
    public static native int munmap(PointerBase address, UnsignedWord length);
}
