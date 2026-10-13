package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;

@CContext(BionicDirectives.class)
public final class IfAddr {

    private IfAddr() {
    }

    @CConstant
    public static native int IFA_F_DADFAILED();

    @CConstant
    public static native int IFA_F_DEPRECATED();

    @CConstant
    public static native int IFA_F_HOMEADDRESS();

    @CConstant
    public static native int IFA_F_MANAGETEMPADDR();

    @CConstant
    public static native int IFA_F_NODAD();

    @CConstant
    public static native int IFA_F_NOPREFIXROUTE();

    @CConstant
    public static native int IFA_F_OPTIMISTIC();

    @CConstant
    public static native int IFA_F_PERMANENT();

    @CConstant
    public static native int IFA_F_SECONDARY();

    @CConstant
    public static native int IFA_F_TEMPORARY();

    @CConstant
    public static native int IFA_F_TENTATIVE();
}
