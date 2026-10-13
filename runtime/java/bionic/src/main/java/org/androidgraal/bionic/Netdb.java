package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CFunction.Transition;
import org.graalvm.nativeimage.c.type.CCharPointer;

@CContext(BionicDirectives.class)
public final class Netdb {

    private Netdb() {
    }

    @CConstant
    public static native int AI_ADDRCONFIG();

    @CConstant
    public static native int AI_ALL();

    @CConstant
    public static native int AI_CANONNAME();

    @CConstant
    public static native int AI_NUMERICHOST();

    @CConstant
    public static native int AI_NUMERICSERV();

    @CConstant
    public static native int AI_PASSIVE();

    @CConstant
    public static native int AI_V4MAPPED();

    @CConstant
    public static native int EAI_AGAIN();

    @CConstant
    public static native int EAI_BADFLAGS();

    @CConstant
    public static native int EAI_FAIL();

    @CConstant
    public static native int EAI_FAMILY();

    @CConstant
    public static native int EAI_MEMORY();

    @CConstant
    public static native int EAI_NODATA();

    @CConstant
    public static native int EAI_NONAME();

    @CConstant
    public static native int EAI_OVERFLOW();

    @CConstant
    public static native int EAI_SERVICE();

    @CConstant
    public static native int EAI_SOCKTYPE();

    @CConstant
    public static native int EAI_SYSTEM();

    @CConstant
    public static native int NI_DGRAM();

    @CConstant
    public static native int NI_NAMEREQD();

    @CConstant
    public static native int NI_NOFQDN();

    @CConstant
    public static native int NI_NUMERICHOST();

    @CConstant
    public static native int NI_NUMERICSERV();

    @CFunction(transition = Transition.NO_TRANSITION)
    public static native CCharPointer gai_strerror(int error);
}
