package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CCharPointerPointer;
import org.graalvm.nativeimage.c.type.CIntPointer;
import org.graalvm.word.PointerBase;
import org.graalvm.word.SignedWord;
import org.graalvm.word.UnsignedWord;

@CContext(BionicDirectives.class)
public final class Unistd {

    private Unistd() {
    }

    @CConstant
    public static native int F_OK();

    @CConstant
    public static native int R_OK();

    @CConstant
    public static native int SEEK_CUR();

    @CConstant
    public static native int SEEK_END();

    @CConstant
    public static native int SEEK_SET();

    @CConstant
    public static native int STDERR_FILENO();

    @CConstant
    public static native int STDIN_FILENO();

    @CConstant
    public static native int STDOUT_FILENO();

    @CConstant
    public static native int W_OK();

    @CConstant
    public static native int X_OK();

    @CConstant
    public static native int _SC_2_CHAR_TERM();

    @CConstant
    public static native int _SC_2_C_BIND();

    @CConstant
    public static native int _SC_2_C_DEV();

    @CConstant
    public static native int _SC_2_C_VERSION();

    @CConstant
    public static native int _SC_2_FORT_DEV();

    @CConstant
    public static native int _SC_2_FORT_RUN();

    @CConstant
    public static native int _SC_2_LOCALEDEF();

    @CConstant
    public static native int _SC_2_SW_DEV();

    @CConstant
    public static native int _SC_2_UPE();

    @CConstant
    public static native int _SC_2_VERSION();

    @CConstant
    public static native int _SC_AIO_LISTIO_MAX();

    @CConstant
    public static native int _SC_AIO_MAX();

    @CConstant
    public static native int _SC_AIO_PRIO_DELTA_MAX();

    @CConstant
    public static native int _SC_ARG_MAX();

    @CConstant
    public static native int _SC_ASYNCHRONOUS_IO();

    @CConstant
    public static native int _SC_ATEXIT_MAX();

    @CConstant
    public static native int _SC_AVPHYS_PAGES();

    @CConstant
    public static native int _SC_BC_BASE_MAX();

    @CConstant
    public static native int _SC_BC_DIM_MAX();

    @CConstant
    public static native int _SC_BC_SCALE_MAX();

    @CConstant
    public static native int _SC_BC_STRING_MAX();

    @CConstant
    public static native int _SC_CHILD_MAX();

    @CConstant
    public static native int _SC_CLK_TCK();

    @CConstant
    public static native int _SC_COLL_WEIGHTS_MAX();

    @CConstant
    public static native int _SC_DELAYTIMER_MAX();

    @CConstant
    public static native int _SC_EXPR_NEST_MAX();

    @CConstant
    public static native int _SC_FSYNC();

    @CConstant
    public static native int _SC_GETGR_R_SIZE_MAX();

    @CConstant
    public static native int _SC_GETPW_R_SIZE_MAX();

    @CConstant
    public static native int _SC_IOV_MAX();

    @CConstant
    public static native int _SC_JOB_CONTROL();

    @CConstant
    public static native int _SC_LINE_MAX();

    @CConstant
    public static native int _SC_LOGIN_NAME_MAX();

    @CConstant
    public static native int _SC_MAPPED_FILES();

    @CConstant
    public static native int _SC_MEMLOCK();

    @CConstant
    public static native int _SC_MEMLOCK_RANGE();

    @CConstant
    public static native int _SC_MEMORY_PROTECTION();

    @CConstant
    public static native int _SC_MESSAGE_PASSING();

    @CConstant
    public static native int _SC_MQ_OPEN_MAX();

    @CConstant
    public static native int _SC_MQ_PRIO_MAX();

    @CConstant
    public static native int _SC_NGROUPS_MAX();

    @CConstant
    public static native int _SC_NPROCESSORS_CONF();

    @CConstant
    public static native int _SC_NPROCESSORS_ONLN();

    @CConstant
    public static native int _SC_OPEN_MAX();

    @CConstant
    public static native int _SC_PAGESIZE();

    @CConstant
    public static native int _SC_PAGE_SIZE();

    @CConstant
    public static native int _SC_PASS_MAX();

    @CConstant
    public static native int _SC_PHYS_PAGES();

    @CConstant
    public static native int _SC_PRIORITIZED_IO();

    @CConstant
    public static native int _SC_PRIORITY_SCHEDULING();

    @CConstant
    public static native int _SC_REALTIME_SIGNALS();

    @CConstant
    public static native int _SC_RE_DUP_MAX();

    @CConstant
    public static native int _SC_RTSIG_MAX();

    @CConstant
    public static native int _SC_SAVED_IDS();

    @CConstant
    public static native int _SC_SEMAPHORES();

    @CConstant
    public static native int _SC_SEM_NSEMS_MAX();

    @CConstant
    public static native int _SC_SEM_VALUE_MAX();

    @CConstant
    public static native int _SC_SHARED_MEMORY_OBJECTS();

    @CConstant
    public static native int _SC_SIGQUEUE_MAX();

    @CConstant
    public static native int _SC_STREAM_MAX();

    @CConstant
    public static native int _SC_SYNCHRONIZED_IO();

    @CConstant
    public static native int _SC_THREADS();

    @CConstant
    public static native int _SC_THREAD_ATTR_STACKADDR();

    @CConstant
    public static native int _SC_THREAD_ATTR_STACKSIZE();

    @CConstant
    public static native int _SC_THREAD_DESTRUCTOR_ITERATIONS();

    @CConstant
    public static native int _SC_THREAD_KEYS_MAX();

    @CConstant
    public static native int _SC_THREAD_PRIORITY_SCHEDULING();

    @CConstant
    public static native int _SC_THREAD_PRIO_INHERIT();

    @CConstant
    public static native int _SC_THREAD_PRIO_PROTECT();

    @CConstant
    public static native int _SC_THREAD_SAFE_FUNCTIONS();

    @CConstant
    public static native int _SC_THREAD_STACK_MIN();

    @CConstant
    public static native int _SC_THREAD_THREADS_MAX();

    @CConstant
    public static native int _SC_TIMERS();

    @CConstant
    public static native int _SC_TIMER_MAX();

    @CConstant
    public static native int _SC_TTY_NAME_MAX();

    @CConstant
    public static native int _SC_TZNAME_MAX();

    @CConstant
    public static native int _SC_VERSION();

    @CConstant
    public static native int _SC_XBS5_ILP32_OFF32();

    @CConstant
    public static native int _SC_XBS5_ILP32_OFFBIG();

    @CConstant
    public static native int _SC_XBS5_LP64_OFF64();

    @CConstant
    public static native int _SC_XBS5_LPBIG_OFFBIG();

    @CConstant
    public static native int _SC_XOPEN_CRYPT();

    @CConstant
    public static native int _SC_XOPEN_ENH_I18N();

    @CConstant
    public static native int _SC_XOPEN_LEGACY();

    @CConstant
    public static native int _SC_XOPEN_REALTIME();

    @CConstant
    public static native int _SC_XOPEN_REALTIME_THREADS();

    @CConstant
    public static native int _SC_XOPEN_SHM();

    @CConstant
    public static native int _SC_XOPEN_UNIX();

    @CConstant
    public static native int _SC_XOPEN_VERSION();

    @CConstant
    public static native int _SC_XOPEN_XCU_VERSION();

    @CFunction
    public static native int access(CCharPointer path, int mode);

    @CFunction
    public static native int chown(CCharPointer path, int uid, int gid);

    @CFunction
    public static native int close(int fd);

    @CFunction
    public static native int dup(int fd);

    @CFunction
    public static native int dup2(int fd, int newFd);

    @CFunction
    public static native int execv(CCharPointer path, CCharPointerPointer argv);

    @CFunction
    public static native int execve(CCharPointer path, CCharPointerPointer argv, CCharPointerPointer envp);

    @CFunction
    public static native int fchown(int fd, int uid, int gid);

    @CFunction
    public static native int fdatasync(int fd);

    @CFunction
    public static native int fsync(int fd);

    @CFunction
    public static native int ftruncate64(int fd, long length);

    @CFunction
    public static native int getegid();

    @CFunction
    public static native int geteuid();

    @CFunction
    public static native int getgid();

    @CFunction
    public static native int getpid();

    @CFunction
    public static native int getppid();

    @CFunction
    public static native int gettid();

    @CFunction
    public static native int getuid();

    @CFunction
    public static native int isatty(int fd);

    @CFunction
    public static native int lchown(CCharPointer path, int uid, int gid);

    @CFunction
    public static native int link(CCharPointer oldPath, CCharPointer newPath);

    @CFunction
    public static native long lseek64(int fd, long offset, int whence);

    @CFunction
    public static native int pipe2(CIntPointer fds, int flags);

    @CFunction
    public static native SignedWord pread64(int fd, PointerBase buffer, UnsignedWord count, long offset);

    @CFunction
    public static native SignedWord pwrite64(int fd, PointerBase buffer, UnsignedWord count, long offset);

    @CFunction
    public static native SignedWord read(int fd, PointerBase buffer, UnsignedWord count);

    @CFunction
    public static native SignedWord readlink(CCharPointer path, CCharPointer buffer, UnsignedWord size);

    @CFunction
    public static native int setegid(int egid);

    @CFunction
    public static native int seteuid(int euid);

    @CFunction
    public static native int setgid(int gid);

    @CFunction
    public static native int setsid();

    @CFunction
    public static native int setuid(int uid);

    @CFunction
    public static native int symlink(CCharPointer oldPath, CCharPointer newPath);

    @CFunction
    public static native SignedWord syscall(SignedWord number, CCharPointer name, int flags);

    @CFunction
    public static native SignedWord sysconf(int name);

    @CFunction
    public static native SignedWord write(int fd, PointerBase buffer, UnsignedWord count);
}
