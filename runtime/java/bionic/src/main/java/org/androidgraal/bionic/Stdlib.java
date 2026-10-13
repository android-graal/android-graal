package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CFunction.Transition;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CCharPointerPointer;

@CContext(BionicDirectives.class)
public final class Stdlib {

    private Stdlib() {
    }

    @CConstant
    public static native int EXIT_FAILURE();

    @CConstant
    public static native int EXIT_SUCCESS();

    @CFunction
    public static native CCharPointer getenv(CCharPointer name);

    @CFunction
    public static native int setenv(CCharPointer name, CCharPointer value, int overwrite);

    @CFunction(transition = Transition.NO_TRANSITION)
    public static native long strtoll(CCharPointer s, CCharPointerPointer endPtr, int base);

    @CFunction
    public static native int unsetenv(CCharPointer name);
}
