package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CFunction.Transition;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.word.UnsignedWord;

@CContext(BionicDirectives.class)
public final class Strings {

    private Strings() {
    }

    @CFunction(transition = Transition.NO_TRANSITION)
    public static native int strncmp(CCharPointer lhs, CCharPointer rhs, UnsignedWord n);
}
