package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.word.PointerBase;
import org.graalvm.word.UnsignedWord;

@CContext(BionicDirectives.class)
public final class Xattr {

    private Xattr() {
    }

    @CFunction
    public static native long getxattr(CCharPointer path, CCharPointer name, PointerBase value, UnsignedWord size);

    @CFunction
    public static native long listxattr(CCharPointer path, CCharPointer list, UnsignedWord size);

    @CFunction
    public static native int removexattr(CCharPointer path, CCharPointer name);

    @CFunction
    public static native int setxattr(CCharPointer path, CCharPointer name, PointerBase value, UnsignedWord size,
            int flags);
}
