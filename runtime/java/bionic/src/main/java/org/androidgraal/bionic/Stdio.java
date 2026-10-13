package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.type.CCharPointer;

@CContext(BionicDirectives.class)
public final class Stdio {

    private Stdio() {
    }

    @CFunction
    public static native int remove(CCharPointer path);

    @CFunction
    public static native int rename(CCharPointer oldPath, CCharPointer newPath);
}
