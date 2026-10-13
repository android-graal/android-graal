package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CFunction.Transition;

@CContext(BionicDirectives.class)
public final class Ctype {

    private Ctype() {
    }

    @CFunction(transition = Transition.NO_TRANSITION)
    public static native int isspace(int c);
}
