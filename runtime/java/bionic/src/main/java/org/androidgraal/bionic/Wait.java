package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;

@CContext(BionicDirectives.class)
public final class Wait {

    private Wait() {
    }

    @CConstant
    public static native int WCONTINUED();

    @CConstant
    public static native int WEXITED();

    @CConstant
    public static native int WNOHANG();

    @CConstant
    public static native int WNOWAIT();

    @CConstant
    public static native int WSTOPPED();

    @CConstant
    public static native int WUNTRACED();
}
