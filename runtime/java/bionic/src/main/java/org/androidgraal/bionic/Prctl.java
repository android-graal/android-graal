package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;

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
    public static native int prctl(int option, long arg2, long arg3, long arg4, long arg5);
}
