package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;

@CContext(BionicDirectives.class)
public final class Resource {

    private Resource() {
    }

    @CConstant
    public static native int PRIO_PROCESS();

    @CFunction
    public static native int getpriority(int which, int who);

    @CFunction
    public static native int setpriority(int which, int who, int priority);
}
