package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;

@CContext(BionicDirectives.class)
public final class Termios {

    private Termios() {
    }

    @CFunction
    public static native int tcdrain(int fd);

    @CFunction
    public static native int tcsendbreak(int fd, int duration);
}
