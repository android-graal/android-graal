package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CFunction.Transition;
import org.graalvm.nativeimage.c.type.CCharPointer;

@CContext(BionicDirectives.class)
public final class Signal {

    private Signal() {
    }

    @CConstant
    public static native int SIGABRT();

    @CConstant
    public static native int SIGALRM();

    @CConstant
    public static native int SIGBUS();

    @CConstant
    public static native int SIGCHLD();

    @CConstant
    public static native int SIGCONT();

    @CConstant
    public static native int SIGFPE();

    @CConstant
    public static native int SIGHUP();

    @CConstant
    public static native int SIGILL();

    @CConstant
    public static native int SIGINT();

    @CConstant
    public static native int SIGIO();

    @CConstant
    public static native int SIGKILL();

    @CConstant
    public static native int SIGPIPE();

    @CConstant
    public static native int SIGPROF();

    @CConstant
    public static native int SIGPWR();

    @CConstant
    public static native int SIGQUIT();

    @CConstant
    public static native int SIGSEGV();

    @CConstant
    public static native int SIGSTKFLT();

    @CConstant
    public static native int SIGSTOP();

    @CConstant
    public static native int SIGSYS();

    @CConstant
    public static native int SIGTERM();

    @CConstant
    public static native int SIGTRAP();

    @CConstant
    public static native int SIGTSTP();

    @CConstant
    public static native int SIGTTIN();

    @CConstant
    public static native int SIGTTOU();

    @CConstant
    public static native int SIGURG();

    @CConstant
    public static native int SIGUSR1();

    @CConstant
    public static native int SIGUSR2();

    @CConstant
    public static native int SIGVTALRM();

    @CConstant
    public static native int SIGWINCH();

    @CConstant
    public static native int SIGXCPU();

    @CConstant
    public static native int SIGXFSZ();

    @CFunction(value = "__libc_current_sigrtmin", transition = Transition.NO_TRANSITION)
    public static native int SIGRTMIN();

    @CFunction(value = "__libc_current_sigrtmax", transition = Transition.NO_TRANSITION)
    public static native int SIGRTMAX();

    @CFunction
    public static native int kill(int pid, int signal);

    @CFunction(transition = Transition.NO_TRANSITION)
    public static native CCharPointer strsignal(int signal);
}
