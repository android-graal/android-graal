package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.type.CLongPointer;
import org.graalvm.word.UnsignedWord;

@CContext(BionicDirectives.class)
public final class Sendfile {

    private Sendfile() {
    }

    @CFunction
    public static native long sendfile64(int outFd, int inFd, CLongPointer offset, UnsignedWord count);
}
