/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.os;

import android.system.Os;
import android.system.OsConstants;
import android.util.Log;

import org.androidgraal.android.ScopedUtfChars;
import org.androidgraal.bionic.Errno;
import org.androidgraal.bionic.Fcntl;
import org.androidgraal.bionic.Signal;
import org.androidgraal.bionic.Stdlib;
import org.androidgraal.bionic.Strings;
import org.androidgraal.bionic.Unistd;
import org.androidgraal.hosted.annotate.Emulated;
import org.graalvm.nativeimage.StackValue;
import org.graalvm.nativeimage.UnmanagedMemory;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CCharPointerPointer;
import org.graalvm.word.SignedWord;
import org.graalvm.word.WordFactory;

import dalvik.system.VMRuntime;

import java.util.NoSuchElementException;

/**
 * Tools for managing OS processes.
 */
@Emulated
public class Process {
    private static final String LOG_TAG = "Process";

    /**
     * An invalid UID value.
     */
    public static final int INVALID_UID = -1;

    /**
     * Defines the root UID.
     */
    public static final int ROOT_UID = 0;

    /**
     * Defines the UID/GID under which system code runs.
     */
    public static final int SYSTEM_UID = 1000;

    /**
     * Defines the UID/GID under which the telephony code runs.
     */
    public static final int PHONE_UID = 1001;

    /**
     * Defines the UID/GID for the user shell.
     */
    public static final int SHELL_UID = 2000;

    /**
     * Defines the UID/GID for the log group.
     * @hide
     */
    public static final int LOG_UID = 1007;

    /**
     * Defines the UID/GID for the WIFI native processes like wificond, supplicant, hostapd,
     * vendor HAL, etc.
     */
    public static final int WIFI_UID = 1010;

    /**
     * Defines the UID/GID for the mediaserver process.
     * @hide
     */
    public static final int MEDIA_UID = 1013;

    /**
     * Defines the UID/GID for the DRM process.
     * @hide
     */
    public static final int DRM_UID = 1019;

    /**
     * Defines the GID for the group that allows write access to the internal media storage.
     * @hide
     */
    public static final int SDCARD_RW_GID = 1015;

    /**
     * Defines the UID/GID for the group that controls VPN services.
     * @hide
     */
    public static final int VPN_UID = 1016;

    /**
     * Defines the UID/GID for keystore.
     * @hide
     */
    public static final int KEYSTORE_UID = 1017;

    /**
     * Defines the UID/GID for credstore.
     * @hide
     */
    public static final int CREDSTORE_UID = 1076;

    /**
     * Defines the UID/GID for the NFC service process.
     * @hide
     */
    public static final int NFC_UID = 1027;

    /**
     * Defines the UID/GID for the clatd process.
     * @hide
     * */
    public static final int CLAT_UID = 1029;

    /**
     * Defines the UID/GID for the Bluetooth service process.
     */
    public static final int BLUETOOTH_UID = 1002;

    /**
     * Defines the GID for the group that allows write access to the internal media storage.
     * @hide
     */
    public static final int MEDIA_RW_GID = 1023;

    /**
     * Access to installed package details
     * @hide
     */
    public static final int PACKAGE_INFO_GID = 1032;

    /**
     * Defines the UID/GID for the shared RELRO file updater process.
     * @hide
     */
    public static final int SHARED_RELRO_UID = 1037;

    /**
     * Defines the UID/GID for the audioserver process.
     * @hide
     */
    public static final int AUDIOSERVER_UID = 1041;

    /**
     * Defines the UID/GID for the cameraserver process
     * @hide
     */
    public static final int CAMERASERVER_UID = 1047;

    /**
     * Defines the UID/GID for the tethering DNS resolver (currently dnsmasq).
     * @hide
     */
    public static final int DNS_TETHER_UID = 1052;

    /**
     * Defines the UID/GID for the WebView zygote process.
     * @hide
     */
    public static final int WEBVIEW_ZYGOTE_UID = 1053;

    /**
     * Defines the UID used for resource tracking for OTA updates.
     * @hide
     */
    public static final int OTA_UPDATE_UID = 1061;

    /**
     * Defines the UID used for statsd
     * @hide
     */
    public static final int STATSD_UID = 1066;

    /**
     * Defines the UID used for incidentd.
     * @hide
     */
    public static final int INCIDENTD_UID = 1067;

    /**
     * Defines the UID/GID for the Secure Element service process.
     * @hide
     */
    public static final int SE_UID = 1068;

    /**
     * Defines the UID/GID for the NetworkStack app.
     * @hide
     */
    public static final int NETWORK_STACK_UID = 1073;

    /**
     * Defines the UID/GID for fs-verity certificate ownership in keystore.
     * @hide
     */
    public static final int FSVERITY_CERT_UID = 1075;

    /**
     * GID that gives access to USB OTG (unreliable) volumes on /mnt/media_rw/<vol name>
     * @hide
     */
    public static final int EXTERNAL_STORAGE_GID = 1077;

    /**
     * GID that gives write access to app-private data directories on external
     * storage (used on devices without sdcardfs only).
     * @hide
     */
    public static final int EXT_DATA_RW_GID = 1078;

    /**
     * GID that gives write access to app-private OBB directories on external
     * storage (used on devices without sdcardfs only).
     * @hide
     */
    public static final int EXT_OBB_RW_GID = 1079;

    /**
     * Defines the UID/GID for the Uwb service process.
     * @hide
     */
    public static final int UWB_UID = 1083;

    /**
     * Defines a virtual UID that is used to aggregate data related to SDK sandbox UIDs.
     * {@see SdkSandboxManager}
     * @hide
     */
    public static final int SDK_SANDBOX_VIRTUAL_UID = 1090;

    /**
     * GID that corresponds to the INTERNET permission.
     * Must match the value of AID_INET.
     * @hide
     */
    public static final int INET_GID = 3003;

    /** {@hide} */
    public static final int NOBODY_UID = 9999;

    /**
     * Defines the start of a range of UIDs (and GIDs), going from this
     * number to {@link #LAST_APPLICATION_UID} that are reserved for assigning
     * to applications.
     */
    public static final int FIRST_APPLICATION_UID = 10000;

    /**
     * Last of application-specific UIDs starting at
     * {@link #FIRST_APPLICATION_UID}.
     */
    public static final int LAST_APPLICATION_UID = 19999;

    /**
     * Defines the start of a range of UIDs going from this number to
     * {@link #LAST_SDK_SANDBOX_UID} that are reserved for assigning to
     * sdk sandbox processes. There is a 1-1 mapping between a sdk sandbox
     * process UID and the app that it belongs to, which can be computed by
     * subtracting (FIRST_SDK_SANDBOX_UID - FIRST_APPLICATION_UID) from the
     * uid of a sdk sandbox process.
     *
     * Note that there are no GIDs associated with these processes; storage
     * attribution for them will be done using project IDs.
     * @hide
     */
    public static final int FIRST_SDK_SANDBOX_UID = 20000;

    /**
     * Last UID that is used for sdk sandbox processes.
     * @hide
     */
    public static final int LAST_SDK_SANDBOX_UID = 29999;

    /**
     * First uid used for fully isolated sandboxed processes spawned from an app zygote
     * @hide
     */
    public static final int FIRST_APP_ZYGOTE_ISOLATED_UID = 90000;

    /**
     * Number of UIDs we allocate per application zygote
     * @hide
     */
    public static final int NUM_UIDS_PER_APP_ZYGOTE = 100;

    /**
     * Last uid used for fully isolated sandboxed processes spawned from an app zygote
     * @hide
     */
    public static final int LAST_APP_ZYGOTE_ISOLATED_UID = 98999;

    /**
     * First uid used for fully isolated sandboxed processes (with no permissions of their own)
     * @hide
     */
    public static final int FIRST_ISOLATED_UID = 99000;

    /**
     * Last uid used for fully isolated sandboxed processes (with no permissions of their own)
     * @hide
     */
    public static final int LAST_ISOLATED_UID = 99999;

    /**
     * Defines the gid shared by all applications running under the same profile.
     * @hide
     */
    public static final int SHARED_USER_GID = 9997;

    /**
     * First gid for applications to share resources. Used when forward-locking
     * is enabled but all UserHandles need to be able to read the resources.
     * @hide
     */
    public static final int FIRST_SHARED_APPLICATION_GID = 50000;

    /**
     * Last gid for applications to share resources. Used when forward-locking
     * is enabled but all UserHandles need to be able to read the resources.
     * @hide
     */
    public static final int LAST_SHARED_APPLICATION_GID = 59999;

    /** {@hide} */
    public static final int FIRST_APPLICATION_CACHE_GID = 20000;
    /** {@hide} */
    public static final int LAST_APPLICATION_CACHE_GID = 29999;

    /**
     * An invalid PID value.
     */
    public static final int INVALID_PID = -1;

    /**
     * Standard priority of application threads.
     * Use with {@link #setThreadPriority(int)} and
     * {@link #setThreadPriority(int, int)}, <b>not</b> with the normal
     * {@link java.lang.Thread} class.
     */
    public static final int THREAD_PRIORITY_DEFAULT = 0;

    /*
     * ***************************************
     * ** Keep in sync with utils/threads.h **
     * ***************************************
     */

    /**
     * Lowest available thread priority.  Only for those who really, really
     * don't want to run if anything else is happening.
     * Use with {@link #setThreadPriority(int)} and
     * {@link #setThreadPriority(int, int)}, <b>not</b> with the normal
     * {@link java.lang.Thread} class.
     */
    public static final int THREAD_PRIORITY_LOWEST = 19;

    /**
     * Standard priority background threads.  This gives your thread a slightly
     * lower than normal priority, so that it will have less chance of impacting
     * the responsiveness of the user interface.
     * Use with {@link #setThreadPriority(int)} and
     * {@link #setThreadPriority(int, int)}, <b>not</b> with the normal
     * {@link java.lang.Thread} class.
     */
    public static final int THREAD_PRIORITY_BACKGROUND = 10;

    /**
     * Standard priority of threads that are currently running a user interface
     * that the user is interacting with.  Applications can not normally
     * change to this priority; the system will automatically adjust your
     * application threads as the user moves through the UI.
     * Use with {@link #setThreadPriority(int)} and
     * {@link #setThreadPriority(int, int)}, <b>not</b> with the normal
     * {@link java.lang.Thread} class.
     */
    public static final int THREAD_PRIORITY_FOREGROUND = -2;

    /**
     * Standard priority of system display threads, involved in updating
     * the user interface.  Applications can not
     * normally change to this priority.
     * Use with {@link #setThreadPriority(int)} and
     * {@link #setThreadPriority(int, int)}, <b>not</b> with the normal
     * {@link java.lang.Thread} class.
     */
    public static final int THREAD_PRIORITY_DISPLAY = -4;

    /**
     * Standard priority of the most important display threads, for compositing
     * the screen and retrieving input events.  Applications can not normally
     * change to this priority.
     * Use with {@link #setThreadPriority(int)} and
     * {@link #setThreadPriority(int, int)}, <b>not</b> with the normal
     * {@link java.lang.Thread} class.
     */
    public static final int THREAD_PRIORITY_URGENT_DISPLAY = -8;

    /**
     * Standard priority of video threads.  Applications can not normally
     * change to this priority.
     * Use with {@link #setThreadPriority(int)} and
     * {@link #setThreadPriority(int, int)}, <b>not</b> with the normal
     * {@link java.lang.Thread} class.
     */
    public static final int THREAD_PRIORITY_VIDEO = -10;

    /**
     * Priority we boost main thread and RT of top app to.
     * @hide
     */
    public static final int THREAD_PRIORITY_TOP_APP_BOOST = -10;

    /**
     * Standard priority of audio threads.  Applications can not normally
     * change to this priority.
     * Use with {@link #setThreadPriority(int)} and
     * {@link #setThreadPriority(int, int)}, <b>not</b> with the normal
     * {@link java.lang.Thread} class.
     */
    public static final int THREAD_PRIORITY_AUDIO = -16;

    /**
     * Standard priority of the most important audio threads.
     * Applications can not normally change to this priority.
     * Use with {@link #setThreadPriority(int)} and
     * {@link #setThreadPriority(int, int)}, <b>not</b> with the normal
     * {@link java.lang.Thread} class.
     */
    public static final int THREAD_PRIORITY_URGENT_AUDIO = -19;

    /**
     * Minimum increment to make a priority more favorable.
     */
    public static final int THREAD_PRIORITY_MORE_FAVORABLE = -1;

    /**
     * Minimum increment to make a priority less favorable.
     */
    public static final int THREAD_PRIORITY_LESS_FAVORABLE = +1;

    /**
     * Default scheduling policy
     * @hide
     */
    public static final int SCHED_OTHER = 0;

    /**
     * First-In First-Out scheduling policy
     * @hide
     */
    public static final int SCHED_FIFO = 1;

    /**
     * Round-Robin scheduling policy
     * @hide
     */
    public static final int SCHED_RR = 2;

    /**
     * Batch scheduling policy
     * @hide
     */
    public static final int SCHED_BATCH = 3;

    /**
     * Idle scheduling policy
     * @hide
     */
    public static final int SCHED_IDLE = 5;

    /**
     * Reset scheduler choice on fork.
     * @hide
     */
    public static final int SCHED_RESET_ON_FORK = 0x40000000;

    // Keep in sync with SP_* constants of enum type SchedPolicy
    // declared in system/core/include/cutils/sched_policy.h,
    // except THREAD_GROUP_DEFAULT does not correspond to any SP_* value.

    /**
     * Default thread group -
     * has meaning with setProcessGroup() only, cannot be used with setThreadGroup().
     * When used with setProcessGroup(), the group of each thread in the process
     * is conditionally changed based on that thread's current priority, as follows:
     * threads with priority numerically less than THREAD_PRIORITY_BACKGROUND
     * are moved to foreground thread group.  All other threads are left unchanged.
     * @hide
     */
    public static final int THREAD_GROUP_DEFAULT = -1;

    /**
     * Background thread group - All threads in
     * this group are scheduled with a reduced share of the CPU.
     * Value is same as constant SP_BACKGROUND of enum SchedPolicy.
     * @hide
     */
    public static final int THREAD_GROUP_BACKGROUND = 0;

    /**
     * Foreground thread group - All threads in
     * this group are scheduled with a normal share of the CPU.
     * Value is same as constant SP_FOREGROUND of enum SchedPolicy.
     * @hide
     **/
    public static final int THREAD_GROUP_FOREGROUND = 1;

    /**
     * System thread group.
     * @hide
     **/
    public static final int THREAD_GROUP_SYSTEM = 2;

    /**
     * Application audio thread group.
     * @hide
     **/
    public static final int THREAD_GROUP_AUDIO_APP = 3;

    /**
     * System audio thread group.
     * @hide
     **/
    public static final int THREAD_GROUP_AUDIO_SYS = 4;

    /**
     * Thread group for top foreground app.
     * @hide
     **/
    public static final int THREAD_GROUP_TOP_APP = 5;

    /**
     * Thread group for RT app.
     * @hide
     **/
    public static final int THREAD_GROUP_RT_APP = 6;

    /**
     * Thread group for bound foreground services that should
     * have additional CPU restrictions during screen off
     * @hide
     **/
    public static final int THREAD_GROUP_RESTRICTED = 7;

    /**
     * Thread group for foreground apps in multi-window mode
     * @hide
     **/
    public static final int THREAD_GROUP_FOREGROUND_WINDOW = 8;

    /** @hide */
    public static final int SIGNAL_DEFAULT = 0;
    public static final int SIGNAL_QUIT = 3;
    public static final int SIGNAL_KILL = 9;
    public static final int SIGNAL_USR1 = 10;

    private static final int PIDFD_UNKNOWN = 0;
    private static final int PIDFD_SUPPORTED = 1;
    private static final int PIDFD_UNSUPPORTED = 2;

    /**
     * Whether or not the underlying OS supports pidfd
     */
    private static int sPidFdSupported = PIDFD_UNKNOWN;

    /**
     * Value used to indicate that there is no special information about an application launch.  App
     * launches with this policy will occur through the primary or secondary Zygote with no special
     * treatment.
     *
     * @hide
     */
    public static final int ZYGOTE_POLICY_FLAG_EMPTY = 0;

    /**
     * Flag used to indicate that an application launch is user-visible and latency sensitive.  Any
     * launch with this policy will use a Unspecialized App Process Pool if the target Zygote
     * supports it.
     *
     * @hide
     */
    public static final int ZYGOTE_POLICY_FLAG_LATENCY_SENSITIVE = 1 << 0;

    /**
     * Flag used to indicate that the launch is one in a series of app launches that will be
     * performed in quick succession.  For future use.
     *
     * @hide
     */
    public static final int ZYGOTE_POLICY_FLAG_BATCH_LAUNCH = 1 << 1;

    /**
     * Flag used to indicate that the current launch event is for a system process.  All system
     * processes are equally important, so none of them should be prioritized over the others.
     *
     * @hide
     */
    public static final int ZYGOTE_POLICY_FLAG_SYSTEM_PROCESS = 1 << 2;

    /**
     * Returns true if the current process is a 64-bit runtime.
     */
    public static final boolean is64Bit() {
        return VMRuntime.getRuntime().is64Bit();
    }

    /**
     * Returns the identifier of this process, which can be used with
     * {@link #killProcess} and {@link #sendSignal}.
     */
    public static final int myPid() {
        return Os.getpid();
    }

    /**
     * Returns the identifier of this process' parent.
     * @hide
     */
    public static final int myPpid() {
        return Os.getppid();
    }

    /**
     * Returns the identifier of the calling thread, which be used with
     * {@link #setThreadPriority(int, int)}.
     */
    public static final int myTid() {
        return Os.gettid();
    }

    /**
     * Returns the identifier of this process's uid.  This is the kernel uid
     * that the process is running under, which is the identity of its
     * app-specific sandbox.  It is different from {@link #myUserHandle} in that
     * a uid identifies a specific app sandbox in a specific user.
     */
    public static final int myUid() {
        return Os.getuid();
    }

    /**
     *
     * Returns the sdk sandbox process corresponding to an app process.
     *
     * @hide
     */
    // TODO(b/318651609): Deprecate once Process#getSdkSandboxUidForAppUid is rolled out to 100%
    public static final int toSdkSandboxUid(int uid) {
        return uid + (FIRST_SDK_SANDBOX_UID - FIRST_APPLICATION_UID);
    }

    /**
     * Returns a uid for a currently running process.
     * @param pid the process id
     * @return the uid of the process, or -1 if the process is not running.
     * @hide pending API council review
     */
    public static final int getUidForPid(int pid) {
        String[] procStatusLabels = { "Uid:" };
        long[] procStatusValues = new long[1];
        procStatusValues[0] = -1;
        Process.readProcLines("/proc/" + pid + "/status", procStatusLabels, procStatusValues);
        return (int) procStatusValues[0];
    }

    /**
     * Returns the parent process id for a currently running process.
     * @param pid the process id
     * @return the parent process id of the process, or -1 if the process is not running.
     * @hide
     */
    public static final int getParentPid(int pid) {
        String[] procStatusLabels = { "PPid:" };
        long[] procStatusValues = new long[1];
        procStatusValues[0] = -1;
        Process.readProcLines("/proc/" + pid + "/status", procStatusLabels, procStatusValues);
        return (int) procStatusValues[0];
    }

    /**
     * Returns the thread group leader id for a currently running thread.
     * @param tid the thread id
     * @return the thread group leader id of the thread, or -1 if the thread is not running.
     *         This is same as what getpid(2) would return if called by tid.
     * @hide
     */
    public static final int getThreadGroupLeader(int tid) {
        String[] procStatusLabels = { "Tgid:" };
        long[] procStatusValues = new long[1];
        procStatusValues[0] = -1;
        Process.readProcLines("/proc/" + tid + "/status", procStatusLabels, procStatusValues);
        return (int) procStatusValues[0];
    }

    /**
     * Determine whether the current environment supports multiple processes.
     *
     * @return Returns true if the system can run in multiple processes, else
     * false if everything is running in a single process.
     *
     * @deprecated This method always returns true.  Do not use.
     */
    @Deprecated
    public static final boolean supportsProcesses() {
        return true;
    }

    /**
     * Kill the process with the given PID.
     * Note that, though this API allows us to request to
     * kill any process based on its PID, the kernel will
     * still impose standard restrictions on which PIDs you
     * are actually able to kill.  Typically this means only
     * the process running the caller's packages/application
     * and any additional processes created by that app; packages
     * sharing a common UID will also be able to kill each
     * other's processes.
     */
    public static final void killProcess(int pid) {
        sendSignal(pid, SIGNAL_KILL);
    }

    /**
     * Check if the pid still exists.
     *
     * TOCTOU warning: the status of the pid can change at the time this method returns. This should
     * be used in very rare cases such as checking if a pid that belongs to an isolated process of a
     * uid known to exist recently no longer exists now. As the possibility of the same pid to be
     * reused again under the same uid during a short window is rare. And even if it happens the
     * caller logic should be robust to handle it without error.
     *
     * @throws IllegalArgumentException if pid is not positive.
     * @throws SecurityException if the caller doesn't have the permission, this method is expected
     *                           to be used by system process with {@link #SYSTEM_UID} because it
     *                           internally uses kill(2).
     * @throws NoSuchElementException if the Linux process with the pid has exited.
     * @hide
     */
    public static final void checkPid(int pid)
            throws IllegalArgumentException, SecurityException, NoSuchElementException {
        sendSignalThrows(pid, SIGNAL_DEFAULT);
    }

    /** @hide */
    public static final int setUid(int uid) {
        if (uid < 0) {
            throw new IllegalArgumentException("uid is negative: " + uid);
        }

        return Unistd.setuid(uid) == 0 ? 0 : Errno.errno().read();
    }

    /** @hide */
    public static final int setGid(int uid) {
        if (uid < 0) {
            throw new IllegalArgumentException("gid is negative: " + uid);
        }

        return Unistd.setgid(uid) == 0 ? 0 : Errno.errno().read();
    }

    /**
     * Send a signal to the given process.
     *
     * @param pid The pid of the target process.
     * @param signal The signal to send.
     */
    public static final void sendSignal(int pid, int signal) {
        if (pid > 0) {
            Log.i(LOG_TAG, "Sending signal. PID: " + pid + " SIG: " + signal);
            Signal.kill(pid, signal);
        }
    }

    private static void sendSignalThrows(int pid, int signal)
            throws IllegalArgumentException, SecurityException, NoSuchElementException {
        if (pid <= 0) {
            throw new IllegalArgumentException("Invalid argument: pid(" + pid + ")");
        }
        int ret = Signal.kill(pid, signal);
        int errno = Errno.errno().read();
        if (ret < 0) {
            if (errno == OsConstants.ESRCH) {
                throw new NoSuchElementException("Process with pid " + pid + " not found");
            } else {
                signalExceptionForError(errno, pid);
            }
        }
    }

    /**
     * @hide
     * Private impl for avoiding a log message...  DO NOT USE without doing
     * your own log, or the Android Illuminati will find you some night and
     * beat you up.
     */
    public static final void killProcessQuiet(int pid) {
        sendSignalQuiet(pid, SIGNAL_KILL);
    }

    /**
     * @hide
     * Private impl for avoiding a log message...  DO NOT USE without doing
     * your own log, or the Android Illuminati will find you some night and
     * beat you up.
     */
    public static final void sendSignalQuiet(int pid, int signal) {
        if (pid > 0) {
            Signal.kill(pid, signal);
        }
    }

    /** @hide */
    public static final void readProcLines(String path,
            String[] reqFields, long[] outSizes) {
        if (path == null || reqFields == null || outSizes == null) {
            throw new NullPointerException();
        }

        String file = path;

        int count = reqFields.length;
        if (count > outSizes.length) {
            throw new IllegalArgumentException("Array lengths differ");
        }

        ScopedUtfChars[] fields = new ScopedUtfChars[count];
        int i;

        try {
            for (i = 0; i < count; i++) {
                String obj = reqFields[i];
                if (obj != null) {
                    fields[i] = new ScopedUtfChars(obj);
                } else {
                    throw new NullPointerException("Element in reqFields");
                }
            }

            long[] sizesArray = outSizes;

            int fd;
            try (ScopedUtfChars fileChars = new ScopedUtfChars(file)) {
                fd = Fcntl.open(fileChars.get(), Fcntl.O_RDONLY() | Fcntl.O_CLOEXEC(), 0);
            }

            if (fd >= 0) {
                for (i = 0; i < count; i++) {
                    sizesArray[i] = 0;
                }

                final int BUFFER_SIZE = 4096;
                CCharPointer buffer = UnmanagedMemory.malloc(BUFFER_SIZE);
                SignedWord len = Unistd.read(fd, buffer, WordFactory.unsigned(BUFFER_SIZE - 1));
                Unistd.close(fd);

                if (len.lessThan(0)) {
                    Log.w(LOG_TAG, "Unable to read " + file);
                    len = WordFactory.zero();
                }
                buffer.write(len, (byte) 0);

                int foundCount = 0;

                CCharPointer p = buffer;
                while (p.read() != 0 && foundCount < count) {
                    boolean skipToEol = true;
                    for (i = 0; i < count; i++) {
                        ScopedUtfChars field = fields[i];
                        if (Strings.strncmp(p, field.get(),
                                WordFactory.unsigned(field.size())) == 0) {
                            p = p.addressOf(field.size());
                            while (p.read() == ' ' || p.read() == '\t') {
                                p = p.addressOf(1);
                            }
                            CCharPointer num = p;
                            while (p.read() >= '0' && p.read() <= '9') {
                                p = p.addressOf(1);
                            }
                            skipToEol = p.read() != '\n';
                            if (p.read() != 0) {
                                p.write((byte) 0);
                                p = p.addressOf(1);
                            }
                            CCharPointerPointer end = StackValue.get(CCharPointerPointer.class);
                            sizesArray[i] = Stdlib.strtoll(num, end, 10);
                            foundCount++;
                            break;
                        }
                    }
                    if (skipToEol) {
                        while (p.read() != 0 && p.read() != '\n') {
                            p = p.addressOf(1);
                        }
                        if (p.read() == '\n') {
                            p = p.addressOf(1);
                        }
                    }
                }

                UnmanagedMemory.free(buffer);
            } else {
                Log.w(LOG_TAG, "Unable to open " + file);
            }
        } finally {
            for (ScopedUtfChars field : fields) {
                if (field != null) {
                    field.close();
                }
            }
        }
    }

    /** @hide */
    public static final int PROC_TERM_MASK = 0xff;
    /** @hide */
    public static final int PROC_ZERO_TERM = 0;
    /** @hide */
    public static final int PROC_SPACE_TERM = (int)' ';
    /** @hide */
    public static final int PROC_TAB_TERM = (int)'\t';
    /** @hide */
    public static final int PROC_NEWLINE_TERM = (int) '\n';
    /** @hide */
    public static final int PROC_COMBINE = 0x100;
    /** @hide */
    public static final int PROC_PARENS = 0x200;
    /** @hide */
    public static final int PROC_QUOTES = 0x400;
    /** @hide */
    public static final int PROC_CHAR = 0x800;
    /** @hide */
    public static final int PROC_OUT_STRING = 0x1000;
    /** @hide */
    public static final int PROC_OUT_LONG = 0x2000;
    /** @hide */
    public static final int PROC_OUT_FLOAT = 0x4000;

    /**
     * Specifies the outcome of having started a process.
     * @hide
     */
    public static final class ProcessStartResult {
        /**
         * The PID of the newly started process.
         * Always >= 0.  (If the start failed, an exception will have been thrown instead.)
         */
        public int pid;

        /**
         * True if the process was started with a wrapper attached.
         */
        public boolean usingWrapper;
    }

    private static void signalExceptionForError(int err, int tid) {
        if (err == OsConstants.EINVAL) {
            throw new IllegalArgumentException("Invalid argument: " + tid);
        } else if (err == OsConstants.ESRCH) {
            throw new IllegalArgumentException("Given thread " + tid + " does not exist");
        } else if (err == OsConstants.EPERM) {
            throw new SecurityException("No permission to modify given thread " + tid);
        } else {
            throw new RuntimeException("Unknown error");
        }
    }
}
