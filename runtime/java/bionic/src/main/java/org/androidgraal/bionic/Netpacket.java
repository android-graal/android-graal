package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;

@CContext(BionicDirectives.class)
public final class Netpacket {

    private Netpacket() {
    }

    @CConstant
    public static native int PACKET_IGNORE_OUTGOING();
}
