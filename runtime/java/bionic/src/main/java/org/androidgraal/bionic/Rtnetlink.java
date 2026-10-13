package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;

@CContext(BionicDirectives.class)
public final class Rtnetlink {

    private Rtnetlink() {
    }

    @CConstant
    public static native int RTMGRP_NEIGH();

    @CConstant
    public static native int RT_SCOPE_HOST();

    @CConstant
    public static native int RT_SCOPE_LINK();

    @CConstant
    public static native int RT_SCOPE_NOWHERE();

    @CConstant
    public static native int RT_SCOPE_SITE();

    @CConstant
    public static native int RT_SCOPE_UNIVERSE();
}
