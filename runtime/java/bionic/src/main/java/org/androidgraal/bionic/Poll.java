package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

@CContext(BionicDirectives.class)
public final class Poll {

    private Poll() {
    }

    @CConstant
    public static native int POLLERR();

    @CConstant
    public static native int POLLHUP();

    @CConstant
    public static native int POLLIN();

    @CConstant
    public static native int POLLNVAL();

    @CConstant
    public static native int POLLOUT();

    @CConstant
    public static native int POLLPRI();

    @CConstant
    public static native int POLLRDBAND();

    @CConstant
    public static native int POLLRDNORM();

    @CConstant
    public static native int POLLWRBAND();

    @CConstant
    public static native int POLLWRNORM();

    @CStruct(addStructKeyword = true)
    public interface pollfd extends PointerBase {
        @CField
        int fd();

        @CField
        void fd(int value);

        @CField
        short events();

        @CField
        void events(short value);

        @CField
        short revents();

        @CField
        void revents(short value);

        pollfd addressOf(int index);
    }

    @CFunction
    public static native int poll(pollfd fds, int count, int timeout);
}
