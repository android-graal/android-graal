package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.word.PointerBase;

@CContext(BionicDirectives.class)
public final class Arpa {

    private Arpa() {
    }

    @CFunction
    public static native int inet_pton(int family, CCharPointer source, PointerBase destination);
}
