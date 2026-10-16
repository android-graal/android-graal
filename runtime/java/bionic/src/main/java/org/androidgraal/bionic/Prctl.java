package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.word.UnsignedWord;

@CContext(BionicDirectives.class)
public final class Prctl {

    private Prctl() {
    }

    @CConstant
    public static native int PR_GET_DUMPABLE();

    @CConstant
    public static native int PR_SET_DUMPABLE();

    @CConstant
    public static native int PR_SET_NO_NEW_PRIVS();

    @CFunction
    public static native int prctl(int option, UnsignedWord arg2, UnsignedWord arg3, UnsignedWord arg4,
            UnsignedWord arg5);
}
