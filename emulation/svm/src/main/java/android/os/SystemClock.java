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

import android.annotation.FlaggedApi;
import android.annotation.NonNull;
import android.compat.annotation.UnsupportedAppUsage;
import android.util.Log;
import android.util.Slog;

import dalvik.annotation.optimization.CriticalNative;

import org.androidgraal.android.CString;
import org.androidgraal.android.Words;
import org.androidgraal.bionic.Errno;
import org.androidgraal.bionic.Time;
import org.androidgraal.hosted.annotate.Emulated;
import org.androidgraal.hosted.annotate.Transcribed;
import org.graalvm.nativeimage.StackValue;
import org.graalvm.word.WordFactory;

import java.time.Clock;
import java.time.DateTimeException;
import java.time.ZoneOffset;

/**
 * Core timekeeping facilities.
 *
 * <p> Three different clocks are available, and they should not be confused:
 *
 * <ul>
 *     <li> <p> {@link System#currentTimeMillis System.currentTimeMillis()}
 *     is the standard "wall" clock (time and date) expressing milliseconds
 *     since the epoch.  The wall clock can be set by the user or the phone
 *     network (see {@link #setCurrentTimeMillis}), so the time may jump
 *     backwards or forwards unpredictably.  This clock should only be used
 *     when correspondence with real-world dates and times is important, such
 *     as in a calendar or alarm clock application.  Interval or elapsed
 *     time measurements should use a different clock.  If you are using
 *     System.currentTimeMillis(), consider listening to the
 *     {@link android.content.Intent#ACTION_TIME_TICK ACTION_TIME_TICK},
 *     {@link android.content.Intent#ACTION_TIME_CHANGED ACTION_TIME_CHANGED}
 *     and {@link android.content.Intent#ACTION_TIMEZONE_CHANGED
 *     ACTION_TIMEZONE_CHANGED} {@link android.content.Intent Intent}
 *     broadcasts to find out when the time changes.
 *
 *     <li> <p> {@link #uptimeMillis} is counted in milliseconds since the
 *     system was booted.  This clock stops when the system enters deep
 *     sleep (CPU off, display dark, device waiting for external input),
 *     but is not affected by clock scaling, idle, or other power saving
 *     mechanisms.  This is the basis for most interval timing
 *     such as {@link Thread#sleep(long) Thread.sleep(millis)},
 *     {@link Object#wait(long) Object.wait(millis)}, and
 *     {@link System#nanoTime System.nanoTime()}.  This clock is guaranteed
 *     to be monotonic, and is suitable for interval timing when the
 *     interval does not span device sleep.  Most methods that accept a
 *     timestamp value currently expect the {@link #uptimeMillis} clock.
 *
 *     <li> <p> {@link #elapsedRealtime} and {@link #elapsedRealtimeNanos}
 *     return the time since the system was booted, and include deep sleep.
 *     This clock is guaranteed to be monotonic, and continues to tick even
 *     when the CPU is in power saving modes, so is the recommend basis
 *     for general purpose interval timing.
 *
 * </ul>
 *
 * There are several mechanisms for controlling the timing of events:
 *
 * <ul>
 *     <li> <p> Standard functions like {@link Thread#sleep(long)
 *     Thread.sleep(millis)} and {@link Object#wait(long) Object.wait(millis)}
 *     are always available.  These functions use the {@link #uptimeMillis}
 *     clock; if the device enters sleep, the remainder of the time will be
 *     postponed until the device wakes up.  These synchronous functions may
 *     be interrupted with {@link Thread#interrupt Thread.interrupt()}, and
 *     you must handle {@link InterruptedException}.
 *
 *     <li> <p> {@link #sleep SystemClock.sleep(millis)} is a utility function
 *     very similar to {@link Thread#sleep(long) Thread.sleep(millis)}, but it
 *     ignores {@link InterruptedException}.  Use this function for delays if
 *     you do not use {@link Thread#interrupt Thread.interrupt()}, as it will
 *     preserve the interrupted state of the thread.
 *
 *     <li> <p> The {@link android.os.Handler} class can schedule asynchronous
 *     callbacks at an absolute or relative time.  Handler objects also use the
 *     {@link #uptimeMillis} clock, and require an {@link android.os.Looper
 *     event loop} (normally present in any GUI application).
 *
 *     <li> <p> The {@link android.app.AlarmManager} can trigger one-time or
 *     recurring events which occur even when the device is in deep sleep
 *     or your application is not running.  Events may be scheduled with your
 *     choice of {@link java.lang.System#currentTimeMillis} (RTC) or
 *     {@link #elapsedRealtime} (ELAPSED_REALTIME), and cause an
 *     {@link android.content.Intent} broadcast when they occur.
 * </ul>
 */
@Emulated
public final class SystemClock {
    private static final String TAG = "SystemClock";

    /**
     * This class is uninstantiable.
     */
    @UnsupportedAppUsage
    private SystemClock() {
        // This space intentionally left blank.
    }

    /**
     * Waits a given number of milliseconds (of uptimeMillis) before returning.
     * Similar to {@link java.lang.Thread#sleep(long)}, but does not throw
     * {@link InterruptedException}; {@link Thread#interrupt()} events are
     * deferred until the next interruptible operation.  Does not return until
     * at least the specified number of milliseconds has elapsed.
     *
     * @param ms to sleep before returning, in milliseconds of uptime.
     */
    @android.ravenwood.annotation.RavenwoodKeep
    public static void sleep(long ms)
    {
        long start = uptimeMillis();
        long duration = ms;
        boolean interrupted = false;
        do {
            try {
                Thread.sleep(duration);
            }
            catch (InterruptedException e) {
                interrupted = true;
            }
            duration = start + ms - uptimeMillis();
        } while (duration > 0);

        if (interrupted) {
            // Important: we don't want to quietly eat an interrupt() event,
            // so we make sure to re-interrupt the thread so that the next
            // call to Thread.sleep() or Object.wait() will be interrupted.
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Returns milliseconds since boot, not counting time spent in deep sleep.
     *
     * @return milliseconds of non-sleep uptime since boot.
     */
    @android.ravenwood.annotation.RavenwoodReplace
    @Transcribed({"https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_SystemClock.cpp#L80",
            "https://android.googlesource.com/platform/system/core/+/refs/tags/android-16.0.0_r1/libutils/SystemClock.cpp#L40-L43",
            "https://android.googlesource.com/platform/system/core/+/refs/tags/android-16.0.0_r1/libutils/include/utils/Timers.h#L54-L57"})
    public static long uptimeMillis() {
        return uptimeNanos() / 1000000;
    }

    /**
     * Returns nanoseconds since boot, not counting time spent in deep sleep.
     *
     * @return nanoseconds of non-sleep uptime since boot.
     */
    @FlaggedApi(Flags.FLAG_ADPF_GPU_REPORT_ACTUAL_WORK_DURATION)
    @android.ravenwood.annotation.RavenwoodReplace
    @Transcribed({"https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_SystemClock.cpp#L81",
            "https://android.googlesource.com/platform/system/core/+/refs/tags/android-16.0.0_r1/libutils/SystemClock.cpp#L48-L51"})
    public static long uptimeNanos() {
        return systemTime(Time.CLOCK_MONOTONIC());
    }

    /**
     * Return {@link Clock} that starts at system boot, not counting time spent
     * in deep sleep.
     *
     * @removed
     */
    public static @NonNull Clock uptimeClock() {
        return new SimpleClock(ZoneOffset.UTC) {
            @Override
            public long millis() {
                return SystemClock.uptimeMillis();
            }
        };
    }

    /**
     * Returns milliseconds since boot, including time spent in sleep.
     *
     * @return elapsed milliseconds since boot.
     */
    @android.ravenwood.annotation.RavenwoodReplace
    @Transcribed({"https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_SystemClock.cpp#L82",
            "https://android.googlesource.com/platform/system/core/+/refs/tags/android-16.0.0_r1/libutils/SystemClock.cpp#L56-L59",
            "https://android.googlesource.com/platform/system/core/+/refs/tags/android-16.0.0_r1/libutils/include/utils/Timers.h#L54-L57"})
    public static long elapsedRealtime() {
        return elapsedRealtimeNanos() / 1000000;
    }

    /**
     * Return {@link Clock} that starts at system boot, including time spent in
     * sleep.
     *
     * @removed
     */
    public static @NonNull Clock elapsedRealtimeClock() {
        return new SimpleClock(ZoneOffset.UTC) {
            @Override
            public long millis() {
                return SystemClock.elapsedRealtime();
            }
        };
    }

    /**
     * Returns nanoseconds since boot, including time spent in sleep.
     *
     * @return elapsed nanoseconds since boot.
     */
    @android.ravenwood.annotation.RavenwoodReplace
    @Transcribed({"https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_SystemClock.cpp#L83",
            "https://android.googlesource.com/platform/system/core/+/refs/tags/android-16.0.0_r1/libutils/SystemClock.cpp#L64-L79",
            "https://android.googlesource.com/platform/system/core/+/refs/tags/android-16.0.0_r1/libutils/include/utils/Timers.h#L34-L37"})
    public static long elapsedRealtimeNanos() {
        Time.timespec ts = StackValue.get(Time.timespec.class);
        int err = Time.clock_gettime(Time.CLOCK_BOOTTIME(), ts);
        if (err != 0) {
            Log.e(TAG, "clock_gettime(CLOCK_BOOTTIME) failed: "
                    + CString.toJava(Errno.strerror(Errno.errno().read())));
            return 0;
        }

        return Words.signExtended(ts.tv_sec()) * 1000000000L + Words.signExtended(ts.tv_nsec());
    }

    /**
     * Returns milliseconds running in the current thread.
     *
     * @return elapsed milliseconds in the thread
     */
    @Transcribed({"https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_SystemClock.cpp#L50-L53",
            "https://android.googlesource.com/platform/system/core/+/refs/tags/android-16.0.0_r1/libutils/include/utils/Timers.h#L54-L57"})
    public static long currentThreadTimeMillis() {
        return systemTime(Time.CLOCK_THREAD_CPUTIME_ID()) / 1000000;
    }

    /**
     * Returns microseconds running in the current thread.
     *
     * @return elapsed microseconds in the thread
     *
     * @hide
     */
    @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
    @Transcribed({"https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_SystemClock.cpp#L58-L61",
            "https://android.googlesource.com/platform/system/core/+/refs/tags/android-16.0.0_r1/libutils/include/utils/Timers.h#L59-L62"})
    public static long currentThreadTimeMicro() {
        return systemTime(Time.CLOCK_THREAD_CPUTIME_ID()) / 1000;
    }

    /**
     * Returns current wall time in  microseconds.
     *
     * @return elapsed microseconds in wall time
     *
     * @hide
     */
    @UnsupportedAppUsage
    @android.ravenwood.annotation.RavenwoodReplace
    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_SystemClock.cpp#L66-L72")
    public static long currentTimeMicro() {
        Time.timespec ts = StackValue.get(Time.timespec.class);
        Time.clock_gettime(Time.CLOCK_REALTIME(), ts);
        return Words.signExtended(ts.tv_sec()) * 1000000L + Words.signExtended(ts.tv_nsec()) / 1000;
    }

    @Transcribed("https://android.googlesource.com/platform/system/core/+/refs/tags/android-16.0.0_r1/libutils/Timers.cpp#L33-L42")
    private static long systemTime(int clock) {
        Time.timespec t = StackValue.get(Time.timespec.class);
        t.tv_sec(WordFactory.zero());
        t.tv_nsec(WordFactory.zero());
        Time.clock_gettime(clock, t);
        return Words.signExtended(t.tv_sec()) * 1000000000L + Words.signExtended(t.tv_nsec());
    }
}
