/*
 * Copyright (C) 2007 The Android Open Source Project
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
import android.annotation.Nullable;
import android.compat.annotation.UnsupportedAppUsage;
import android.util.Log;

import com.android.internal.util.FastPrintWriter;
import com.android.internal.util.Preconditions;
import com.android.internal.util.TypedProperties;

import org.androidgraal.bionic.Malloc;
import org.androidgraal.hosted.annotate.Adapted;
import org.androidgraal.hosted.annotate.Emulated;
import org.graalvm.nativeimage.StackValue;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;


/**
 * Provides various debugging methods for Android applications, including
 * tracing and allocation counts.
 * <p><strong>Logging Trace Files</strong></p>
 * <p>Debug can create log files that give details about an application, such as
 * a call stack and start/stop times for any running methods. See <a
 * href="{@docRoot}studio/profile/traceview.html">Inspect Trace Logs with
 * Traceview</a> for information about reading trace files. To start logging
 * trace files, call one of the startMethodTracing() methods. To stop tracing,
 * call {@link #stopMethodTracing()}.
 */
@Emulated
public final class Debug
{
    private static final String TAG = "Debug";

    /**
     * Flags for startMethodTracing().  These can be ORed together.
     *
     * TRACE_COUNT_ALLOCS adds the results from startAllocCounting to the
     * trace key file.
     *
     * @deprecated Accurate counting is a burden on the runtime and may be removed.
     */
    // This must match VMDebug.TRACE_COUNT_ALLOCS.
    @Deprecated
    public static final int TRACE_COUNT_ALLOCS  = 1;

    /**
     * Flags for printLoadedClasses().  Default behavior is to only show
     * the class name.
     */
    public static final int SHOW_FULL_DETAIL    = 1;
    public static final int SHOW_CLASSLOADER    = (1 << 1);
    public static final int SHOW_INITIALIZED    = (1 << 2);

    // set/cleared by waitForDebugger()
    private static volatile boolean mWaiting = false;

    @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
    private Debug() {}

    /*
     * How long to wait for the debugger to finish sending requests.  I've
     * seen this hit 800msec on the device while waiting for a response
     * to travel over USB and get processed, so we take that and add
     * half a second.
     */
    private static final int MIN_DEBUGGER_IDLE = 1300;      // msec

    /* how long to sleep when polling for activity */
    private static final int SPIN_DELAY = 200;              // msec

    /**
     * Default trace file path and file
     */
    private static final String DEFAULT_TRACE_BODY = "dmtrace";
    private static final String DEFAULT_TRACE_EXTENSION = ".trace";

    private static final String[] FRAMEWORK_FEATURES = new String[] {
        "opengl-tracing",
        "view-hierarchy",
        "support_boot_stages",
        "app_info",
    };

    /**
     * This class is used to retrieved various statistics about the memory mappings for this
     * process. The returned info is broken down by dalvik, native, and other. All results are in kB.
     */
    public static class MemoryInfo implements Parcelable {
        /** The proportional set size for dalvik heap.  (Doesn't include other Dalvik overhead.) */
        public int dalvikPss;
        /** The proportional set size that is swappable for dalvik heap. */
        /** @hide We may want to expose this, eventually. */
        @UnsupportedAppUsage
        public int dalvikSwappablePss;
        /** @hide The resident set size for dalvik heap.  (Without other Dalvik overhead.) */
        @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
        public int dalvikRss;
        /** The private dirty pages used by dalvik heap. */
        public int dalvikPrivateDirty;
        /** The shared dirty pages used by dalvik heap. */
        public int dalvikSharedDirty;
        /** The private clean pages used by dalvik heap. */
        /** @hide We may want to expose this, eventually. */
        @UnsupportedAppUsage
        public int dalvikPrivateClean;
        /** The shared clean pages used by dalvik heap. */
        /** @hide We may want to expose this, eventually. */
        @UnsupportedAppUsage
        public int dalvikSharedClean;
        /** The dirty dalvik pages that have been swapped out. */
        /** @hide We may want to expose this, eventually. */
        @UnsupportedAppUsage
        public int dalvikSwappedOut;
        /** The dirty dalvik pages that have been swapped out, proportional. */
        /** @hide We may want to expose this, eventually. */
        @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
        public int dalvikSwappedOutPss;

        /** The proportional set size for the native heap. */
        public int nativePss;
        /** The proportional set size that is swappable for the native heap. */
        /** @hide We may want to expose this, eventually. */
        @UnsupportedAppUsage
        public int nativeSwappablePss;
        /** @hide The resident set size for the native heap. */
        @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
        public int nativeRss;
        /** The private dirty pages used by the native heap. */
        public int nativePrivateDirty;
        /** The shared dirty pages used by the native heap. */
        public int nativeSharedDirty;
        /** The private clean pages used by the native heap. */
        /** @hide We may want to expose this, eventually. */
        @UnsupportedAppUsage
        public int nativePrivateClean;
        /** The shared clean pages used by the native heap. */
        /** @hide We may want to expose this, eventually. */
        @UnsupportedAppUsage
        public int nativeSharedClean;
        /** The dirty native pages that have been swapped out. */
        /** @hide We may want to expose this, eventually. */
        @UnsupportedAppUsage
        public int nativeSwappedOut;
        /** The dirty native pages that have been swapped out, proportional. */
        /** @hide We may want to expose this, eventually. */
        @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
        public int nativeSwappedOutPss;

        /** The proportional set size for everything else. */
        public int otherPss;
        /** The proportional set size that is swappable for everything else. */
        /** @hide We may want to expose this, eventually. */
        @UnsupportedAppUsage
        public int otherSwappablePss;
        /** @hide The resident set size for everything else. */
        @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
        public int otherRss;
        /** The private dirty pages used by everything else. */
        public int otherPrivateDirty;
        /** The shared dirty pages used by everything else. */
        public int otherSharedDirty;
        /** The private clean pages used by everything else. */
        /** @hide We may want to expose this, eventually. */
        @UnsupportedAppUsage
        public int otherPrivateClean;
        /** The shared clean pages used by everything else. */
        /** @hide We may want to expose this, eventually. */
        @UnsupportedAppUsage
        public int otherSharedClean;
        /** The dirty pages used by anyting else that have been swapped out. */
        /** @hide We may want to expose this, eventually. */
        @UnsupportedAppUsage
        public int otherSwappedOut;
        /** The dirty pages used by anyting else that have been swapped out, proportional. */
        /** @hide We may want to expose this, eventually. */
        @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
        public int otherSwappedOutPss;

        /** Whether the kernel reports proportional swap usage */
        /** @hide */
        @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
        public boolean hasSwappedOutPss;

        // LINT.IfChange
        /** @hide */
        public static final int HEAP_UNKNOWN = 0;
        /** @hide */
        public static final int HEAP_DALVIK = 1;
        /** @hide */
        public static final int HEAP_NATIVE = 2;

        /** @hide */
        public static final int OTHER_DALVIK_OTHER = 0;
        /** @hide */
        public static final int OTHER_STACK = 1;
        /** @hide */
        public static final int OTHER_CURSOR = 2;
        /** @hide */
        public static final int OTHER_ASHMEM = 3;
        /** @hide */
        public static final int OTHER_GL_DEV = 4;
        /** @hide */
        public static final int OTHER_UNKNOWN_DEV = 5;
        /** @hide */
        public static final int OTHER_SO = 6;
        /** @hide */
        public static final int OTHER_JAR = 7;
        /** @hide */
        public static final int OTHER_APK = 8;
        /** @hide */
        public static final int OTHER_TTF = 9;
        /** @hide */
        public static final int OTHER_DEX = 10;
        /** @hide */
        public static final int OTHER_OAT = 11;
        /** @hide */
        public static final int OTHER_ART = 12;
        /** @hide */
        public static final int OTHER_UNKNOWN_MAP = 13;
        /** @hide */
        public static final int OTHER_GRAPHICS = 14;
        /** @hide */
        public static final int OTHER_GL = 15;
        /** @hide */
        public static final int OTHER_OTHER_MEMTRACK = 16;

        // Needs to be declared here for the DVK_STAT ranges below.
        /** @hide */
        @UnsupportedAppUsage
        public static final int NUM_OTHER_STATS = 17;

        // Dalvik subsections.
        /** @hide */
        public static final int OTHER_DALVIK_NORMAL = 17;
        /** @hide */
        public static final int OTHER_DALVIK_LARGE = 18;
        /** @hide */
        public static final int OTHER_DALVIK_ZYGOTE = 19;
        /** @hide */
        public static final int OTHER_DALVIK_NON_MOVING = 20;
        // Section begins and ends for dumpsys, relative to the DALVIK categories.
        /** @hide */
        public static final int OTHER_DVK_STAT_DALVIK_START =
                OTHER_DALVIK_NORMAL - NUM_OTHER_STATS;
        /** @hide */
        public static final int OTHER_DVK_STAT_DALVIK_END =
                OTHER_DALVIK_NON_MOVING - NUM_OTHER_STATS;

        // Dalvik Other subsections.
        /** @hide */
        public static final int OTHER_DALVIK_OTHER_LINEARALLOC = 21;
        /** @hide */
        public static final int OTHER_DALVIK_OTHER_ACCOUNTING = 22;
        /** @hide */
        public static final int OTHER_DALVIK_OTHER_ZYGOTE_CODE_CACHE = 23;
        /** @hide */
        public static final int OTHER_DALVIK_OTHER_APP_CODE_CACHE = 24;
        /** @hide */
        public static final int OTHER_DALVIK_OTHER_COMPILER_METADATA = 25;
        /** @hide */
        public static final int OTHER_DALVIK_OTHER_INDIRECT_REFERENCE_TABLE = 26;
        /** @hide */
        public static final int OTHER_DVK_STAT_DALVIK_OTHER_START =
                OTHER_DALVIK_OTHER_LINEARALLOC - NUM_OTHER_STATS;
        /** @hide */
        public static final int OTHER_DVK_STAT_DALVIK_OTHER_END =
                OTHER_DALVIK_OTHER_INDIRECT_REFERENCE_TABLE - NUM_OTHER_STATS;

        // Dex subsections (Boot vdex, App dex, and App vdex).
        /** @hide */
        public static final int OTHER_DEX_BOOT_VDEX = 27;
        /** @hide */
        public static final int OTHER_DEX_APP_DEX = 28;
        /** @hide */
        public static final int OTHER_DEX_APP_VDEX = 29;
        /** @hide */
        public static final int OTHER_DVK_STAT_DEX_START = OTHER_DEX_BOOT_VDEX - NUM_OTHER_STATS;
        /** @hide */
        public static final int OTHER_DVK_STAT_DEX_END = OTHER_DEX_APP_VDEX - NUM_OTHER_STATS;

        // Art subsections (App image, boot image).
        /** @hide */
        public static final int OTHER_ART_APP = 30;
        /** @hide */
        public static final int OTHER_ART_BOOT = 31;
        // LINT.ThenChange(/system/memory/libmeminfo/include/meminfo/androidprocheaps.h)
        /** @hide */
        public static final int OTHER_DVK_STAT_ART_START = OTHER_ART_APP - NUM_OTHER_STATS;
        /** @hide */
        public static final int OTHER_DVK_STAT_ART_END = OTHER_ART_BOOT - NUM_OTHER_STATS;

        /** @hide */
        @UnsupportedAppUsage
        public static final int NUM_DVK_STATS = OTHER_ART_BOOT + 1 - OTHER_DALVIK_NORMAL;

        /** @hide */
        public static final int NUM_CATEGORIES = 9;

        /** @hide */
        public static final int OFFSET_PSS = 0;
        /** @hide */
        public static final int OFFSET_SWAPPABLE_PSS = 1;
        /** @hide */
        public static final int OFFSET_RSS = 2;
        /** @hide */
        public static final int OFFSET_PRIVATE_DIRTY = 3;
        /** @hide */
        public static final int OFFSET_SHARED_DIRTY = 4;
        /** @hide */
        public static final int OFFSET_PRIVATE_CLEAN = 5;
        /** @hide */
        public static final int OFFSET_SHARED_CLEAN = 6;
        /** @hide */
        public static final int OFFSET_SWAPPED_OUT = 7;
        /** @hide */
        public static final int OFFSET_SWAPPED_OUT_PSS = 8;

        @UnsupportedAppUsage
        private int[] otherStats = new int[(NUM_OTHER_STATS+NUM_DVK_STATS)*NUM_CATEGORIES];

        public MemoryInfo() {
        }

        /**
         * @hide Copy contents from another object.
         */
        public void set(MemoryInfo other) {
            dalvikPss = other.dalvikPss;
            dalvikSwappablePss = other.dalvikSwappablePss;
            dalvikRss = other.dalvikRss;
            dalvikPrivateDirty = other.dalvikPrivateDirty;
            dalvikSharedDirty = other.dalvikSharedDirty;
            dalvikPrivateClean = other.dalvikPrivateClean;
            dalvikSharedClean = other.dalvikSharedClean;
            dalvikSwappedOut = other.dalvikSwappedOut;
            dalvikSwappedOutPss = other.dalvikSwappedOutPss;

            nativePss = other.nativePss;
            nativeSwappablePss = other.nativeSwappablePss;
            nativeRss = other.nativeRss;
            nativePrivateDirty = other.nativePrivateDirty;
            nativeSharedDirty = other.nativeSharedDirty;
            nativePrivateClean = other.nativePrivateClean;
            nativeSharedClean = other.nativeSharedClean;
            nativeSwappedOut = other.nativeSwappedOut;
            nativeSwappedOutPss = other.nativeSwappedOutPss;

            otherPss = other.otherPss;
            otherSwappablePss = other.otherSwappablePss;
            otherRss = other.otherRss;
            otherPrivateDirty = other.otherPrivateDirty;
            otherSharedDirty = other.otherSharedDirty;
            otherPrivateClean = other.otherPrivateClean;
            otherSharedClean = other.otherSharedClean;
            otherSwappedOut = other.otherSwappedOut;
            otherSwappedOutPss = other.otherSwappedOutPss;

            hasSwappedOutPss = other.hasSwappedOutPss;

            System.arraycopy(other.otherStats, 0, otherStats, 0, otherStats.length);
        }

        /**
         * Return total PSS memory usage in kB.
         */
        public int getTotalPss() {
            return dalvikPss + nativePss + otherPss + getTotalSwappedOutPss();
        }

        /**
         * @hide Return total PSS memory usage in kB.
         */
        @UnsupportedAppUsage
        public int getTotalUss() {
            return dalvikPrivateClean + dalvikPrivateDirty
                    + nativePrivateClean + nativePrivateDirty
                    + otherPrivateClean + otherPrivateDirty;
        }

        /**
         * Return total PSS memory usage in kB mapping a file of one of the following extension:
         * .so, .jar, .apk, .ttf, .dex, .odex, .oat, .art .
         */
        public int getTotalSwappablePss() {
            return dalvikSwappablePss + nativeSwappablePss + otherSwappablePss;
        }

        /**
         * @hide Return total RSS memory usage in kB.
         */
        public int getTotalRss() {
            return dalvikRss + nativeRss + otherRss;
        }

        /**
         * Return total private dirty memory usage in kB.
         */
        public int getTotalPrivateDirty() {
            return dalvikPrivateDirty + nativePrivateDirty + otherPrivateDirty;
        }

        /**
         * Return total shared dirty memory usage in kB.
         */
        public int getTotalSharedDirty() {
            return dalvikSharedDirty + nativeSharedDirty + otherSharedDirty;
        }

        /**
         * Return total shared clean memory usage in kB.
         */
        public int getTotalPrivateClean() {
            return dalvikPrivateClean + nativePrivateClean + otherPrivateClean;
        }

        /**
         * Return total shared clean memory usage in kB.
         */
        public int getTotalSharedClean() {
            return dalvikSharedClean + nativeSharedClean + otherSharedClean;
        }

        /**
         * Return total swapped out memory in kB.
         * @hide
         */
        public int getTotalSwappedOut() {
            return dalvikSwappedOut + nativeSwappedOut + otherSwappedOut;
        }

        /**
         * Return total swapped out memory in kB, proportional.
         * @hide
         */
        public int getTotalSwappedOutPss() {
            return dalvikSwappedOutPss + nativeSwappedOutPss + otherSwappedOutPss;
        }

        /** @hide */
        @UnsupportedAppUsage
        public int getOtherPss(int which) {
            return otherStats[which * NUM_CATEGORIES + OFFSET_PSS];
        }

        /** @hide */
        public int getOtherSwappablePss(int which) {
            return otherStats[which * NUM_CATEGORIES + OFFSET_SWAPPABLE_PSS];
        }

        /** @hide */
        public int getOtherRss(int which) {
            return otherStats[which * NUM_CATEGORIES + OFFSET_RSS];
        }

        /** @hide */
        @UnsupportedAppUsage
        public int getOtherPrivateDirty(int which) {
            return otherStats[which * NUM_CATEGORIES + OFFSET_PRIVATE_DIRTY];
        }

        /** @hide */
        @UnsupportedAppUsage
        public int getOtherSharedDirty(int which) {
            return otherStats[which * NUM_CATEGORIES + OFFSET_SHARED_DIRTY];
        }

        /** @hide */
        public int getOtherPrivateClean(int which) {
            return otherStats[which * NUM_CATEGORIES + OFFSET_PRIVATE_CLEAN];
        }

        /** @hide */
        @UnsupportedAppUsage
        public int getOtherPrivate(int which) {
          return getOtherPrivateClean(which) + getOtherPrivateDirty(which);
        }

        /** @hide */
        public int getOtherSharedClean(int which) {
            return otherStats[which * NUM_CATEGORIES + OFFSET_SHARED_CLEAN];
        }

        /** @hide */
        public int getOtherSwappedOut(int which) {
            return otherStats[which * NUM_CATEGORIES + OFFSET_SWAPPED_OUT];
        }

        /** @hide */
        public int getOtherSwappedOutPss(int which) {
            return otherStats[which * NUM_CATEGORIES + OFFSET_SWAPPED_OUT_PSS];
        }

        /** @hide */
        @UnsupportedAppUsage
        public static String getOtherLabel(int which) {
            switch (which) {
                case OTHER_DALVIK_OTHER: return "Dalvik Other";
                case OTHER_STACK: return "Stack";
                case OTHER_CURSOR: return "Cursor";
                case OTHER_ASHMEM: return "Ashmem";
                case OTHER_GL_DEV: return "Gfx dev";
                case OTHER_UNKNOWN_DEV: return "Other dev";
                case OTHER_SO: return ".so mmap";
                case OTHER_JAR: return ".jar mmap";
                case OTHER_APK: return ".apk mmap";
                case OTHER_TTF: return ".ttf mmap";
                case OTHER_DEX: return ".dex mmap";
                case OTHER_OAT: return ".oat mmap";
                case OTHER_ART: return ".art mmap";
                case OTHER_UNKNOWN_MAP: return "Other mmap";
                case OTHER_GRAPHICS: return "EGL mtrack";
                case OTHER_GL: return "GL mtrack";
                case OTHER_OTHER_MEMTRACK: return "Other mtrack";
                case OTHER_DALVIK_NORMAL: return ".Heap";
                case OTHER_DALVIK_LARGE: return ".LOS";
                case OTHER_DALVIK_ZYGOTE: return ".Zygote";
                case OTHER_DALVIK_NON_MOVING: return ".NonMoving";
                case OTHER_DALVIK_OTHER_LINEARALLOC: return ".LinearAlloc";
                case OTHER_DALVIK_OTHER_ACCOUNTING: return ".GC";
                case OTHER_DALVIK_OTHER_ZYGOTE_CODE_CACHE: return ".ZygoteJIT";
                case OTHER_DALVIK_OTHER_APP_CODE_CACHE: return ".AppJIT";
                case OTHER_DALVIK_OTHER_COMPILER_METADATA: return ".CompilerMetadata";
                case OTHER_DALVIK_OTHER_INDIRECT_REFERENCE_TABLE: return ".IndirectRef";
                case OTHER_DEX_BOOT_VDEX: return ".Boot vdex";
                case OTHER_DEX_APP_DEX: return ".App dex";
                case OTHER_DEX_APP_VDEX: return ".App vdex";
                case OTHER_ART_APP: return ".App art";
                case OTHER_ART_BOOT: return ".Boot art";
                default: return "????";
            }
        }

      /**
       * Returns the value of a particular memory statistic or {@code null} if no
       * such memory statistic exists.
       *
       * <p>The following table lists the memory statistics that are supported.
       * Note that memory statistics may be added or removed in a future API level.</p>
       *
       * <table>
       *     <thead>
       *         <tr>
       *             <th>Memory statistic name</th>
       *             <th>Meaning</th>
       *             <th>Example</th>
       *             <th>Supported (API Levels)</th>
       *         </tr>
       *     </thead>
       *     <tbody>
       *         <tr>
       *             <td>summary.java-heap</td>
       *             <td>The private Java Heap usage in kB. This corresponds to the Java Heap field
       *                 in the App Summary section output by dumpsys meminfo.</td>
       *             <td>{@code 1442}</td>
       *             <td>23</td>
       *         </tr>
       *         <tr>
       *             <td>summary.native-heap</td>
       *             <td>The private Native Heap usage in kB. This corresponds to the Native Heap
       *                 field in the App Summary section output by dumpsys meminfo.</td>
       *             <td>{@code 1442}</td>
       *             <td>23</td>
       *         </tr>
       *         <tr>
       *             <td>summary.code</td>
       *             <td>The memory usage for static code and resources in kB. This corresponds to
       *                 the Code field in the App Summary section output by dumpsys meminfo.</td>
       *             <td>{@code 1442}</td>
       *             <td>23</td>
       *         </tr>
       *         <tr>
       *             <td>summary.stack</td>
       *             <td>The stack usage in kB. This corresponds to the Stack field in the
       *                 App Summary section output by dumpsys meminfo.</td>
       *             <td>{@code 1442}</td>
       *             <td>23</td>
       *         </tr>
       *         <tr>
       *             <td>summary.graphics</td>
       *             <td>The graphics usage in kB. This corresponds to the Graphics field in the
       *                 App Summary section output by dumpsys meminfo.</td>
       *             <td>{@code 1442}</td>
       *             <td>23</td>
       *         </tr>
       *         <tr>
       *             <td>summary.private-other</td>
       *             <td>Other private memory usage in kB. This corresponds to the Private Other
       *                 field output in the App Summary section by dumpsys meminfo.</td>
       *             <td>{@code 1442}</td>
       *             <td>23</td>
       *         </tr>
       *         <tr>
       *             <td>summary.system</td>
       *             <td>Shared and system memory usage in kB. This corresponds to the System
       *                 field output in the App Summary section by dumpsys meminfo.</td>
       *             <td>{@code 1442}</td>
       *             <td>23</td>
       *         </tr>
       *         <tr>
       *             <td>summary.total-pss</td>
       *             <td>Total PSS memory usage in kB.</td>
       *             <td>{@code 1442}</td>
       *             <td>23</td>
       *         </tr>
       *         <tr>
       *             <td>summary.total-swap</td>
       *             <td>Total swap usage in kB.</td>
       *             <td>{@code 1442}</td>
       *             <td>23</td>
       *         </tr>
       *     </tbody>
       * </table>
       */
       public String getMemoryStat(String statName) {
            switch(statName) {
                case "summary.java-heap":
                    return Integer.toString(getSummaryJavaHeap());
                case "summary.native-heap":
                    return Integer.toString(getSummaryNativeHeap());
                case "summary.code":
                    return Integer.toString(getSummaryCode());
                case "summary.stack":
                    return Integer.toString(getSummaryStack());
                case "summary.graphics":
                    return Integer.toString(getSummaryGraphics());
                case "summary.private-other":
                    return Integer.toString(getSummaryPrivateOther());
                case "summary.system":
                    return Integer.toString(getSummarySystem());
                case "summary.total-pss":
                    return Integer.toString(getSummaryTotalPss());
                case "summary.total-swap":
                    return Integer.toString(getSummaryTotalSwap());
                default:
                    return null;
            }
        }

        /**
         * Returns a map of the names/values of the memory statistics
         * that {@link #getMemoryStat(String)} supports.
         *
         * @return a map of the names/values of the supported memory statistics.
         */
        public Map<String, String> getMemoryStats() {
            Map<String, String> stats = new HashMap<String, String>();
            stats.put("summary.java-heap", Integer.toString(getSummaryJavaHeap()));
            stats.put("summary.native-heap", Integer.toString(getSummaryNativeHeap()));
            stats.put("summary.code", Integer.toString(getSummaryCode()));
            stats.put("summary.stack", Integer.toString(getSummaryStack()));
            stats.put("summary.graphics", Integer.toString(getSummaryGraphics()));
            stats.put("summary.private-other", Integer.toString(getSummaryPrivateOther()));
            stats.put("summary.system", Integer.toString(getSummarySystem()));
            stats.put("summary.total-pss", Integer.toString(getSummaryTotalPss()));
            stats.put("summary.total-swap", Integer.toString(getSummaryTotalSwap()));
            return stats;
        }

        /**
         * Pss of Java Heap bytes in KB due to the application.
         * Notes:
         *  * OTHER_ART is the boot image. Anything private here is blamed on
         *    the application, not the system.
         *  * dalvikPrivateDirty includes private zygote, which means the
         *    application dirtied something allocated by the zygote. We blame
         *    the application for that memory, not the system.
         *  * Does not include OTHER_DALVIK_OTHER, which is considered VM
         *    Overhead and lumped into Private Other.
         *  * We don't include dalvikPrivateClean, because there should be no
         *    such thing as private clean for the Java Heap.
         * @hide
         */
        @UnsupportedAppUsage
        public int getSummaryJavaHeap() {
            return dalvikPrivateDirty + getOtherPrivate(OTHER_ART);
        }

        /**
         * Pss of Native Heap bytes in KB due to the application.
         * Notes:
         *  * Includes private dirty malloc space.
         *  * We don't include nativePrivateClean, because there should be no
         *    such thing as private clean for the Native Heap.
         * @hide
         */
        @UnsupportedAppUsage
        public int getSummaryNativeHeap() {
            return nativePrivateDirty;
        }

        /**
         * Pss of code and other static resource bytes in KB due to
         * the application.
         * @hide
         */
        @UnsupportedAppUsage
        public int getSummaryCode() {
            return getOtherPrivate(OTHER_SO)
              + getOtherPrivate(OTHER_JAR)
              + getOtherPrivate(OTHER_APK)
              + getOtherPrivate(OTHER_TTF)
              + getOtherPrivate(OTHER_DEX)
                + getOtherPrivate(OTHER_OAT)
                + getOtherPrivate(OTHER_DALVIK_OTHER_ZYGOTE_CODE_CACHE)
                + getOtherPrivate(OTHER_DALVIK_OTHER_APP_CODE_CACHE);
        }

        /**
         * Pss in KB of the stack due to the application.
         * Notes:
         *  * Includes private dirty stack, which includes both Java and Native
         *    stack.
         *  * Does not include private clean stack, because there should be no
         *    such thing as private clean for the stack.
         * @hide
         */
        @UnsupportedAppUsage
        public int getSummaryStack() {
            return getOtherPrivateDirty(OTHER_STACK);
        }

        /**
         * Pss in KB of graphics due to the application.
         * Notes:
         *  * Includes private Gfx, EGL, and GL.
         *  * Warning: These numbers can be misreported by the graphics drivers.
         *  * We don't include shared graphics. It may make sense to, because
         *    shared graphics are likely buffers due to the application
         *    anyway, but it's simpler to implement to just group all shared
         *    memory into the System category.
         * @hide
         */
        @UnsupportedAppUsage
        public int getSummaryGraphics() {
            return getOtherPrivate(OTHER_GL_DEV)
              + getOtherPrivate(OTHER_GRAPHICS)
              + getOtherPrivate(OTHER_GL);
        }

        /**
         * Pss in KB due to the application that haven't otherwise been
         * accounted for.
         * @hide
         */
        @UnsupportedAppUsage
        public int getSummaryPrivateOther() {
            return getTotalPrivateClean()
              + getTotalPrivateDirty()
              - getSummaryJavaHeap()
              - getSummaryNativeHeap()
              - getSummaryCode()
              - getSummaryStack()
              - getSummaryGraphics();
        }

        /**
         * Pss in KB due to the system.
         * Notes:
         *  * Includes all shared memory.
         * @hide
         */
        @UnsupportedAppUsage
        public int getSummarySystem() {
            return getTotalPss()
              - getTotalPrivateClean()
              - getTotalPrivateDirty();
        }

        /**
         * Rss of Java Heap bytes in KB due to the application.
         * @hide
         */
        public int getSummaryJavaHeapRss() {
            return dalvikRss + getOtherRss(OTHER_ART);
        }

        /**
         * Rss of Native Heap bytes in KB due to the application.
         * @hide
         */
        public int getSummaryNativeHeapRss() {
            return nativeRss;
        }

        /**
         * Rss of code and other static resource bytes in KB due to
         * the application.
         * @hide
         */
        public int getSummaryCodeRss() {
            return getOtherRss(OTHER_SO)
                + getOtherRss(OTHER_JAR)
                + getOtherRss(OTHER_APK)
                + getOtherRss(OTHER_TTF)
                + getOtherRss(OTHER_DEX)
                + getOtherRss(OTHER_OAT)
                + getOtherRss(OTHER_DALVIK_OTHER_ZYGOTE_CODE_CACHE)
                + getOtherRss(OTHER_DALVIK_OTHER_APP_CODE_CACHE);
        }

        /**
         * Rss in KB of the stack due to the application.
         * @hide
         */
        public int getSummaryStackRss() {
            return getOtherRss(OTHER_STACK);
        }

        /**
         * Rss in KB of graphics due to the application.
         * @hide
         */
        public int getSummaryGraphicsRss() {
            return getOtherRss(OTHER_GL_DEV)
                + getOtherRss(OTHER_GRAPHICS)
                + getOtherRss(OTHER_GL);
        }

        /**
         * Rss in KB due to either the application or system that haven't otherwise been
         * accounted for.
         * @hide
         */
        public int getSummaryUnknownRss() {
            return getTotalRss()
                - getSummaryJavaHeapRss()
                - getSummaryNativeHeapRss()
                - getSummaryCodeRss()
                - getSummaryStackRss()
                - getSummaryGraphicsRss();
        }

        /**
         * Total Pss in KB.
         * @hide
         */
        public int getSummaryTotalPss() {
            return getTotalPss();
        }

        /**
         * Total Swap in KB.
         * Notes:
         *  * Some of this memory belongs in other categories, but we don't
         *    know if the Swap memory is shared or private, so we don't know
         *    what to blame on the application and what on the system.
         *    For now, just lump all the Swap in one place.
         *    For kernels reporting SwapPss {@link #getSummaryTotalSwapPss()}
         *    will report the application proportional Swap.
         * @hide
         */
        public int getSummaryTotalSwap() {
            return getTotalSwappedOut();
        }

        /**
         * Total proportional Swap in KB.
         * Notes:
         *  * Always 0 if {@link #hasSwappedOutPss} is false.
         * @hide
         */
        public int getSummaryTotalSwapPss() {
            return getTotalSwappedOutPss();
        }

        /**
         * Return true if the kernel is reporting pss swapped out...  that is, if
         * {@link #getSummaryTotalSwapPss()} will return non-0 values.
         * @hide
         */
        public boolean hasSwappedOutPss() {
            return hasSwappedOutPss;
        }

        public int describeContents() {
            return 0;
        }

        @Adapted(reason = "throws instead of writing the fields")
        public void writeToParcel(Parcel dest, int flags) {
            throw new UnsupportedOperationException();
        }
    }


    /**
     * Returns "true" if one or more threads is waiting for a debugger
     * to attach.
     */
    public static boolean waitingForDebugger() {
        return mWaiting;
    }

    /**
     * Returns an array of strings that identify Framework features. This is
     * used by DDMS to determine what sorts of operations the Framework can
     * perform.
     *
     * @hide
     */
    public static String[] getFeatureList() {
        return FRAMEWORK_FEATURES;
    }

    /**
     * Change the JDWP port.
     *
     * @deprecated no longer needed or useful
     */
    @Deprecated
    public static void changeDebugPort(int port) {}

    /**
     * This is the pathname to the sysfs file that enables and disables
     * tracing on the qemu emulator.
     */
    private static final String SYSFS_QEMU_TRACE_STATE = "/sys/qemu_trace/state";

    /**
     * Enable qemu tracing. For this to work requires running everything inside
     * the qemu emulator; otherwise, this method will have no effect. The trace
     * file is specified on the command line when the emulator is started. For
     * example, the following command line <br />
     * <code>emulator -trace foo</code><br />
     * will start running the emulator and create a trace file named "foo". This
     * method simply enables writing the trace records to the trace file.
     *
     * <p>
     * The main differences between this and {@link #startMethodTracing()} are
     * that tracing in the qemu emulator traces every cpu instruction of every
     * process, including kernel code, so we have more complete information,
     * including all context switches. We can also get more detailed information
     * such as cache misses. The sequence of calls is determined by
     * post-processing the instruction trace. The qemu tracing is also done
     * without modifying the application or perturbing the timing of calls
     * because no instrumentation is added to the application being traced.
     * </p>
     *
     * <p>
     * One limitation of using this method compared to using
     * {@link #startMethodTracing()} on the real device is that the emulator
     * does not model all of the real hardware effects such as memory and
     * bus contention.  The emulator also has a simple cache model and cannot
     * capture all the complexities of a real cache.
     * </p>
     */
    public static void startNativeTracing() {
        // Open the sysfs file for writing and write "1" to it.
        PrintWriter outStream = null;
        try {
            FileOutputStream fos = new FileOutputStream(SYSFS_QEMU_TRACE_STATE);
            outStream = new FastPrintWriter(fos);
            outStream.println("1");
        } catch (Exception e) {
        } finally {
            if (outStream != null)
                outStream.close();
        }
    }

    /**
     * Stop qemu tracing.  See {@link #startNativeTracing()} to start tracing.
     *
     * <p>Tracing can be started and stopped as many times as desired.  When
     * the qemu emulator itself is stopped then the buffered trace records
     * are flushed and written to the trace file.  In fact, it is not necessary
     * to call this method at all; simply killing qemu is sufficient.  But
     * starting and stopping a trace is useful for examining a specific
     * region of code.</p>
     */
    public static void stopNativeTracing() {
        // Open the sysfs file for writing and write "0" to it.
        PrintWriter outStream = null;
        try {
            FileOutputStream fos = new FileOutputStream(SYSFS_QEMU_TRACE_STATE);
            outStream = new FastPrintWriter(fos);
            outStream.println("0");
        } catch (Exception e) {
            // We could print an error message here but we probably want
            // to quietly ignore errors if we are not running in the emulator.
        } finally {
            if (outStream != null)
                outStream.close();
        }
    }

    /**
     * Enable "emulator traces", in which information about the current
     * method is made available to the "emulator -trace" feature.  There
     * is no corresponding "disable" call -- this is intended for use by
     * the framework when tracing should be turned on and left that way, so
     * that traces captured with F9/F10 will include the necessary data.
     *
     * This puts the VM into "profile" mode, which has performance
     * consequences.
     *
     * To temporarily enable tracing, use {@link #startNativeTracing()}.
     *
     * @deprecated Please use other tracing method in this class.
     */
    public static void enableEmulatorTraceOutput() {
        Log.w(TAG, "Unimplemented");
    }

    /**
     * This method exists for compatibility and always returns 0.
     * @deprecated This method is now obsolete.
     */
    @Deprecated
    public static int getGlobalExternalAllocCount() {
        return 0;
    }

    /**
     * This method exists for compatibility and has no effect.
     * @deprecated This method is now obsolete.
     */
    @Deprecated
    public static void resetGlobalExternalAllocSize() {}

    /**
     * This method exists for compatibility and has no effect.
     * @deprecated This method is now obsolete.
     */
    @Deprecated
    public static void resetGlobalExternalAllocCount() {}

    /**
     * This method exists for compatibility and always returns 0.
     * @deprecated This method is now obsolete.
     */
    @Deprecated
    public static int getGlobalExternalAllocSize() {
        return 0;
    }

    /**
     * This method exists for compatibility and always returns 0.
     * @deprecated This method is now obsolete.
     */
    @Deprecated
    public static int getGlobalExternalFreedCount() {
        return 0;
    }

    /**
     * This method exists for compatibility and has no effect.
     * @deprecated This method is now obsolete.
     */
    @Deprecated
    public static void resetGlobalExternalFreedCount() {}

    /**
     * This method exists for compatibility and has no effect.
     * @deprecated This method is now obsolete.
     */
    @Deprecated
    public static int getGlobalExternalFreedSize() {
        return 0;
    }

    /**
     * This method exists for compatibility and has no effect.
     * @deprecated This method is now obsolete.
     */
    @Deprecated
    public static void resetGlobalExternalFreedSize() {}

    /**
     * This method exists for compatibility and has no effect.
     * @deprecated This method is now obsolete.
     */
    @Deprecated
    public static int getThreadExternalAllocCount() {
        return 0;
    }

    /**
     * This method exists for compatibility and has no effect.
     * @deprecated This method is now obsolete.
     */
    @Deprecated
    public static void resetThreadExternalAllocCount() {}

    /**
     * This method exists for compatibility and has no effect.
     * @deprecated This method is now obsolete.
     */
    @Deprecated
    public static int getThreadExternalAllocSize() {
        return 0;
    }

    /**
     * This method exists for compatibility and has no effect.
     * @deprecated This method is now obsolete.
     */
    @Deprecated
    public static void resetThreadExternalAllocSize() {}

    /**
     * Returns the size of the native heap.
     * @return The size of the native heap in bytes.
     */
    @Adapted(value = "https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Debug.cpp#L105-L109",
            reason = "the native's body in Java over bionic's mallinfo")
    public static long getNativeHeapSize() {
        Malloc.mallinfo info = StackValue.get(Malloc.mallinfo.class);
        Malloc.mallinfo(info);
        return info.usmblks().rawValue();
    }

    /**
     * Returns the amount of allocated memory in the native heap.
     * @return The allocated size in bytes.
     */
    @Adapted(value = "https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Debug.cpp#L111-L115",
            reason = "the native's body in Java over bionic's mallinfo")
    public static long getNativeHeapAllocatedSize() {
        Malloc.mallinfo info = StackValue.get(Malloc.mallinfo.class);
        Malloc.mallinfo(info);
        return info.uordblks().rawValue();
    }

    /**
     * Returns the amount of free memory in the native heap.
     * @return The freed size in bytes.
     */
    @Adapted(value = "https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_os_Debug.cpp#L117-L121",
            reason = "the native's body in Java over bionic's mallinfo")
    public static long getNativeHeapFreeSize() {
        Malloc.mallinfo info = StackValue.get(Malloc.mallinfo.class);
        Malloc.mallinfo(info);
        return info.fordblks().rawValue();
    }

    /** @hide */
    public static final int MEMINFO_TOTAL = 0;
    /** @hide */
    public static final int MEMINFO_FREE = 1;
    /** @hide */
    public static final int MEMINFO_BUFFERS = 2;
    /** @hide */
    public static final int MEMINFO_CACHED = 3;
    /** @hide */
    public static final int MEMINFO_SHMEM = 4;
    /** @hide */
    public static final int MEMINFO_SLAB = 5;
     /** @hide */
    public static final int MEMINFO_SLAB_RECLAIMABLE = 6;
     /** @hide */
    public static final int MEMINFO_SLAB_UNRECLAIMABLE = 7;
    /** @hide */
    public static final int MEMINFO_SWAP_TOTAL = 8;
    /** @hide */
    public static final int MEMINFO_SWAP_FREE = 9;
    /** @hide */
    public static final int MEMINFO_ZRAM_TOTAL = 10;
    /** @hide */
    public static final int MEMINFO_MAPPED = 11;
    /** @hide */
    public static final int MEMINFO_VM_ALLOC_USED = 12;
    /** @hide */
    public static final int MEMINFO_PAGE_TABLES = 13;
    /** @hide */
    public static final int MEMINFO_KERNEL_STACK = 14;
    /**
     * Note: MEMINFO_KRECLAIMABLE includes MEMINFO_SLAB_RECLAIMABLE (see KReclaimable field
     * description in kernel documentation).
     * @hide
     */
    public static final int MEMINFO_KRECLAIMABLE = 15;
    /** @hide */
    public static final int MEMINFO_ACTIVE = 16;
    /** @hide */
    public static final int MEMINFO_INACTIVE = 17;
    /** @hide */
    public static final int MEMINFO_UNEVICTABLE = 18;
    /** @hide */
    public static final int MEMINFO_AVAILABLE = 19;
    /** @hide */
    public static final int MEMINFO_ACTIVE_ANON = 20;
    /** @hide */
    public static final int MEMINFO_INACTIVE_ANON = 21;
    /** @hide */
    public static final int MEMINFO_ACTIVE_FILE = 22;
    /** @hide */
    public static final int MEMINFO_INACTIVE_FILE = 23;
    /** @hide */
    public static final int MEMINFO_CMA_TOTAL = 24;
    /** @hide */
    public static final int MEMINFO_CMA_FREE = 25;
    /** @hide */
    public static final int MEMINFO_COUNT = 26;

    /**
     * Establish an object allocation limit in the current thread.
     * This feature was never enabled in release builds.  The
     * allocation limits feature was removed in Honeycomb.  This
     * method exists for compatibility and always returns -1 and has
     * no effect.
     *
     * @deprecated This method is now obsolete.
     */
    @Deprecated
    public static int setAllocationLimit(int limit) {
        return -1;
    }

    /**
     * Establish a global object allocation limit.  This feature was
     * never enabled in release builds.  The allocation limits feature
     * was removed in Honeycomb.  This method exists for compatibility
     * and always returns -1 and has no effect.
     *
     * @deprecated This method is now obsolete.
     */
    @Deprecated
    public static int setGlobalAllocationLimit(int limit) {
        return -1;
    }

    /**
     * API for gathering and querying instruction counts.
     *
     * Example usage:
     * <pre>
     *   Debug.InstructionCount icount = new Debug.InstructionCount();
     *   icount.resetAndStart();
     *    [... do lots of stuff ...]
     *   if (icount.collect()) {
     *       System.out.println("Total instructions executed: "
     *           + icount.globalTotal());
     *       System.out.println("Method invocations: "
     *           + icount.globalMethodInvocations());
     *   }
     * </pre>
     *
     * @deprecated Instruction counting is no longer supported.
     */
    @Deprecated
    public static class InstructionCount {
        public InstructionCount() {
        }

        /**
         * Reset counters and ensure counts are running.  Counts may
         * have already been running.
         *
         * @return true if counting was started
         */
        public boolean resetAndStart() {
            return false;
        }

        /**
         * Collect instruction counts.  May or may not stop the
         * counting process.
         */
        public boolean collect() {
            return false;
        }

        /**
         * Return the total number of instructions executed globally (i.e. in
         * all threads).
         */
        public int globalTotal() {
            return 0;
        }

        /**
         * Return the total number of method-invocation instructions
         * executed globally.
         */
        public int globalMethodInvocations() {
            return 0;
        }
    }

    /**
     * A Map of typed debug properties.
     */
    private static final TypedProperties debugProperties;

    /*
     * Load the debug properties from the standard files into debugProperties.
     */
    static {
        if (false) {
            final String TAG = "DebugProperties";
            final String[] files = { "/system/debug.prop", "/debug.prop", "/data/debug.prop" };
            final TypedProperties tp = new TypedProperties();

            // Read the properties from each of the files, if present.
            for (String file : files) {
                Reader r;
                try {
                    r = new FileReader(file);
                } catch (FileNotFoundException ex) {
                    // It's ok if a file is missing.
                    continue;
                }

                try {
                    tp.load(r);
                } catch (Exception ex) {
                    throw new RuntimeException("Problem loading " + file, ex);
                } finally {
                    try {
                        r.close();
                    } catch (IOException ex) {
                        // Ignore this error.
                    }
                }
            }

            debugProperties = tp.isEmpty() ? null : tp;
        } else {
            debugProperties = null;
        }
    }


    /**
     * Returns true if the type of the field matches the specified class.
     * Handles the case where the class is, e.g., java.lang.Boolean, but
     * the field is of the primitive "boolean" type.  Also handles all of
     * the java.lang.Number subclasses.
     */
    private static boolean fieldTypeMatches(Field field, Class<?> cl) {
        Class<?> fieldClass = field.getType();
        if (fieldClass == cl) {
            return true;
        }
        Field primitiveTypeField;
        try {
            /* All of the classes we care about (Boolean, Integer, etc.)
             * have a Class field called "TYPE" that points to the corresponding
             * primitive class.
             */
            primitiveTypeField = cl.getField("TYPE");
        } catch (NoSuchFieldException ex) {
            return false;
        }
        try {
            return fieldClass == (Class<?>) primitiveTypeField.get(null);
        } catch (IllegalAccessException ex) {
            return false;
        }
    }


    /**
     * Looks up the property that corresponds to the field, and sets the field's value
     * if the types match.
     */
    private static void modifyFieldIfSet(final Field field, final TypedProperties properties,
                                         final String propertyName) {
        if (field.getType() == java.lang.String.class) {
            int stringInfo = properties.getStringInfo(propertyName);
            switch (stringInfo) {
                case TypedProperties.STRING_SET:
                    // Handle as usual below.
                    break;
                case TypedProperties.STRING_NULL:
                    try {
                        field.set(null, null);  // null object for static fields; null string
                    } catch (IllegalAccessException ex) {
                        throw new IllegalArgumentException(
                            "Cannot set field for " + propertyName, ex);
                    }
                    return;
                case TypedProperties.STRING_NOT_SET:
                    return;
                case TypedProperties.STRING_TYPE_MISMATCH:
                    throw new IllegalArgumentException(
                        "Type of " + propertyName + " " +
                        " does not match field type (" + field.getType() + ")");
                default:
                    throw new IllegalStateException(
                        "Unexpected getStringInfo(" + propertyName + ") return value " +
                        stringInfo);
            }
        }
        Object value = properties.get(propertyName);
        if (value != null) {
            if (!fieldTypeMatches(field, value.getClass())) {
                throw new IllegalArgumentException(
                    "Type of " + propertyName + " (" + value.getClass() + ") " +
                    " does not match field type (" + field.getType() + ")");
            }
            try {
                field.set(null, value);  // null object for static fields
            } catch (IllegalAccessException ex) {
                throw new IllegalArgumentException(
                    "Cannot set field for " + propertyName, ex);
            }
        }
    }


    /**
     * Equivalent to <code>setFieldsOn(cl, false)</code>.
     *
     * @see #setFieldsOn(Class, boolean)
     *
     * @hide
     */
    public static void setFieldsOn(Class<?> cl) {
        setFieldsOn(cl, false);
    }

    /**
     * Reflectively sets static fields of a class based on internal debugging
     * properties.  This method is a no-op if false is
     * false.
     * <p>
     * <strong>NOTE TO APPLICATION DEVELOPERS</strong>: false will
     * always be false in release builds.  This API is typically only useful
     * for platform developers.
     * </p>
     * Class setup: define a class whose only fields are non-final, static
     * primitive types (except for "char") or Strings.  In a static block
     * after the field definitions/initializations, pass the class to
     * this method, Debug.setFieldsOn(). Example:
     * <pre>
     * package com.example;
     *
     * import android.os.Debug;
     *
     * public class MyDebugVars {
     *    public static String s = "a string";
     *    public static String s2 = "second string";
     *    public static String ns = null;
     *    public static boolean b = false;
     *    public static int i = 5;
     *    @Debug.DebugProperty
     *    public static float f = 0.1f;
     *    @@Debug.DebugProperty
     *    public static double d = 0.5d;
     *
     *    // This MUST appear AFTER all fields are defined and initialized!
     *    static {
     *        // Sets all the fields
     *        Debug.setFieldsOn(MyDebugVars.class);
     *
     *        // Sets only the fields annotated with @Debug.DebugProperty
     *        // Debug.setFieldsOn(MyDebugVars.class, true);
     *    }
     * }
     * </pre>
     * setFieldsOn() may override the value of any field in the class based
     * on internal properties that are fixed at boot time.
     * <p>
     * These properties are only set during platform debugging, and are not
     * meant to be used as a general-purpose properties store.
     *
     * {@hide}
     *
     * @param cl The class to (possibly) modify
     * @param partial If false, sets all static fields, otherwise, only set
     *        fields with the {@link android.os.Debug.DebugProperty}
     *        annotation
     * @throws IllegalArgumentException if any fields are final or non-static,
     *         or if the type of the field does not match the type of
     *         the internal debugging property value.
     */
    public static void setFieldsOn(Class<?> cl, boolean partial) {
        if (false) {
            if (debugProperties != null) {
                /* Only look for fields declared directly by the class,
                 * so we don't mysteriously change static fields in superclasses.
                 */
                for (Field field : cl.getDeclaredFields()) {
                    if (!partial || field.getAnnotation(DebugProperty.class) != null) {
                        final String propertyName = cl.getName() + "." + field.getName();
                        boolean isStatic = Modifier.isStatic(field.getModifiers());
                        boolean isFinal = Modifier.isFinal(field.getModifiers());

                        if (!isStatic || isFinal) {
                            throw new IllegalArgumentException(propertyName +
                                " must be static and non-final");
                        }
                        modifyFieldIfSet(field, debugProperties, propertyName);
                    }
                }
            }
        } else {
            Log.wtf(TAG,
                  "setFieldsOn(" + (cl == null ? "null" : cl.getName()) +
                  ") called in non-DEBUG build");
        }
    }

    /**
     * Annotation to put on fields you want to set with
     * {@link Debug#setFieldsOn(Class, boolean)}.
     *
     * @hide
     */
    @Target({ ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DebugProperty {
    }

    /**
     * Return a String describing the calling method and location at a particular stack depth.
     * @param callStack the Thread stack
     * @param depth the depth of stack to return information for.
     * @return the String describing the caller at that depth.
     */
    private static String getCaller(StackTraceElement callStack[], int depth) {
        // callStack[4] is the caller of the method that called getCallers()
        if (4 + depth >= callStack.length) {
            return "<bottom of call stack>";
        }
        StackTraceElement caller = callStack[4 + depth];
        return caller.getClassName() + "." + caller.getMethodName() + ":" + caller.getLineNumber();
    }

    /**
     * Return a string consisting of methods and locations at multiple call stack levels.
     * @param depth the number of levels to return, starting with the immediate caller.
     * @return a string describing the call stack.
     * {@hide}
     */
    @UnsupportedAppUsage
    public static String getCallers(final int depth) {
        final StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append(getCaller(callStack, i)).append(" ");
        }
        return sb.toString();
    }

    /**
     * Return a string consisting of methods and locations at multiple call stack levels.
     * @param depth the number of levels to return, starting with the immediate caller.
     * @return a string describing the call stack.
     * {@hide}
     */
    public static String getCallers(final int start, int depth) {
        final StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder();
        depth += start;
        for (int i = start; i < depth; i++) {
            sb.append(getCaller(callStack, i)).append(" ");
        }
        return sb.toString();
    }

    /**
     * Like {@link #getCallers(int)}, but each location is append to the string
     * as a new line with <var>linePrefix</var> in front of it.
     * @param depth the number of levels to return, starting with the immediate caller.
     * @param linePrefix prefix to put in front of each location.
     * @return a string describing the call stack.
     * {@hide}
     */
    public static String getCallers(final int depth, String linePrefix) {
        final StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append(linePrefix).append(getCaller(callStack, i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * @return a String describing the immediate caller of the calling method.
     * {@hide}
     */
    @UnsupportedAppUsage
    public static String getCaller() {
        return getCaller(Thread.currentThread().getStackTrace(), 0);
    }

}
