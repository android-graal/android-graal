package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.word.PointerBase;

@CContext(BionicDirectives.class)
public final class Dlfcn {

    private Dlfcn() {
    }

    @CConstant
    public static native int RTLD_NOW();

    @CFunction
    public static native PointerBase dlopen(CCharPointer filename, int flags);

    @CFunction
    public static native PointerBase dlsym(PointerBase handle, CCharPointer symbol);
}
