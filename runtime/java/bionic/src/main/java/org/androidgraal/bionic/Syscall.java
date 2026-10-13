package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;

@CContext(BionicDirectives.class)
public final class Syscall {

    private Syscall() {
    }

    @CConstant
    public static native int __NR_memfd_create();
}
