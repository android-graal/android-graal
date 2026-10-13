package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CFunction.Transition;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CIntPointer;

@CContext(BionicDirectives.class)
public final class Errno {

    private Errno() {
    }

    @CConstant
    public static native int E2BIG();

    @CConstant
    public static native int EACCES();

    @CConstant
    public static native int EADDRINUSE();

    @CConstant
    public static native int EADDRNOTAVAIL();

    @CConstant
    public static native int EAFNOSUPPORT();

    @CConstant
    public static native int EAGAIN();

    @CConstant
    public static native int EALREADY();

    @CConstant
    public static native int EBADF();

    @CConstant
    public static native int EBADMSG();

    @CConstant
    public static native int EBUSY();

    @CConstant
    public static native int ECANCELED();

    @CConstant
    public static native int ECHILD();

    @CConstant
    public static native int ECONNABORTED();

    @CConstant
    public static native int ECONNREFUSED();

    @CConstant
    public static native int ECONNRESET();

    @CConstant
    public static native int EDEADLK();

    @CConstant
    public static native int EDESTADDRREQ();

    @CConstant
    public static native int EDOM();

    @CConstant
    public static native int EDQUOT();

    @CConstant
    public static native int EEXIST();

    @CConstant
    public static native int EFAULT();

    @CConstant
    public static native int EFBIG();

    @CConstant
    public static native int EHOSTUNREACH();

    @CConstant
    public static native int EIDRM();

    @CConstant
    public static native int EILSEQ();

    @CConstant
    public static native int EINPROGRESS();

    @CConstant
    public static native int EINTR();

    @CConstant
    public static native int EINVAL();

    @CConstant
    public static native int EIO();

    @CConstant
    public static native int EISCONN();

    @CConstant
    public static native int EISDIR();

    @CConstant
    public static native int ELOOP();

    @CConstant
    public static native int EMFILE();

    @CConstant
    public static native int EMLINK();

    @CConstant
    public static native int EMSGSIZE();

    @CConstant
    public static native int EMULTIHOP();

    @CConstant
    public static native int ENAMETOOLONG();

    @CConstant
    public static native int ENETDOWN();

    @CConstant
    public static native int ENETRESET();

    @CConstant
    public static native int ENETUNREACH();

    @CConstant
    public static native int ENFILE();

    @CConstant
    public static native int ENOBUFS();

    @CConstant
    public static native int ENODATA();

    @CConstant
    public static native int ENODEV();

    @CConstant
    public static native int ENOENT();

    @CConstant
    public static native int ENOEXEC();

    @CConstant
    public static native int ENOLCK();

    @CConstant
    public static native int ENOLINK();

    @CConstant
    public static native int ENOMEM();

    @CConstant
    public static native int ENOMSG();

    @CConstant
    public static native int ENONET();

    @CConstant
    public static native int ENOPROTOOPT();

    @CConstant
    public static native int ENOSPC();

    @CConstant
    public static native int ENOSR();

    @CConstant
    public static native int ENOSTR();

    @CConstant
    public static native int ENOSYS();

    @CConstant
    public static native int ENOTCONN();

    @CConstant
    public static native int ENOTDIR();

    @CConstant
    public static native int ENOTEMPTY();

    @CConstant
    public static native int ENOTSOCK();

    @CConstant
    public static native int ENOTSUP();

    @CConstant
    public static native int ENOTTY();

    @CConstant
    public static native int ENXIO();

    @CConstant
    public static native int EOPNOTSUPP();

    @CConstant
    public static native int EOVERFLOW();

    @CConstant
    public static native int EPERM();

    @CConstant
    public static native int EPIPE();

    @CConstant
    public static native int EPROTO();

    @CConstant
    public static native int EPROTONOSUPPORT();

    @CConstant
    public static native int EPROTOTYPE();

    @CConstant
    public static native int ERANGE();

    @CConstant
    public static native int EROFS();

    @CConstant
    public static native int ESPIPE();

    @CConstant
    public static native int ESRCH();

    @CConstant
    public static native int ESTALE();

    @CConstant
    public static native int ETIME();

    @CConstant
    public static native int ETIMEDOUT();

    @CConstant
    public static native int ETXTBSY();

    @CConstant
    public static native int EXDEV();

    @CFunction(value = "__errno", transition = Transition.NO_TRANSITION)
    public static native CIntPointer errno();

    @CFunction(transition = Transition.NO_TRANSITION)
    public static native CCharPointer strerror(int errnum);
}
