/*
 * Copyright (C) 2024 The Android Open Source Project
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

import dalvik.annotation.optimization.CriticalNative;
import dalvik.annotation.optimization.FastNative;

import org.androidgraal.hosted.annotate.Adapted;
import org.androidgraal.hosted.annotate.Emulated;
import org.androidgraal.hosted.annotate.Premise;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Writes trace events to the perfetto trace buffer. These trace events can be
 * collected and visualized using the Perfetto UI.
 *
 * <p>This tracing mechanism is independent of the method tracing mechanism
 * offered by {@link Debug#startMethodTracing} or {@link Trace}.
 *
 * @hide
 */
@android.ravenwood.annotation.RavenwoodKeepWholeClass
@Emulated
public final class PerfettoTrace {
    // Keep in sync with C++
    private static final int PERFETTO_TE_TYPE_SLICE_BEGIN = 1;
    private static final int PERFETTO_TE_TYPE_SLICE_END = 2;
    private static final int PERFETTO_TE_TYPE_INSTANT = 3;
    private static final int PERFETTO_TE_TYPE_COUNTER = 4;

    private static final boolean IS_FLAG_ENABLED = android.os.Flags.perfettoSdkTracingV2();

    /**
     * For fetching the next flow event id in a process.
     */
    private static final AtomicInteger sFlowEventId = new AtomicInteger();

    public static final PerfettoTrace.Category MQ_CATEGORY = new PerfettoTrace.Category("mq");

    /**
     * Perfetto category a trace event belongs to.
     * Registering a category is not sufficient to capture events within the category, it must
     * also be enabled in the trace config.
     */
    @android.ravenwood.annotation.RavenwoodKeepWholeClass
    public static final class Category implements PerfettoTrackEventExtra.PerfettoPointer {
        private final long mPtr;
        private final long mExtraPtr;
        private final String mName;
        private final String mTag;
        private final String mSeverity;

        /**
         * Category ctor.
         *
         * @param name The category name.
         */
        public Category(String name) {
            this(name, "", "");
        }

        /**
         * Category ctor.
         *
         * @param name The category name.
         * @param tag An atrace tag name that this category maps to.
         * @param severity A Log style severity string for the category.
         */
        @Adapted(reason = "registers no native allocation", premise = Premise.NO_FINALIZER)
        public Category(String name, String tag, String severity) {
            mName = name;
            mTag = tag;
            mSeverity = severity;
            mPtr = native_init(name, tag, severity);
            mExtraPtr = native_get_extra_ptr(mPtr);
        }

        @android.ravenwood.annotation.RavenwoodReplace
        @Adapted(reason = "returns 0", premise = Premise.NO_TRACING)
        private static long native_init(String name, String tag, String severity) {
            return 0;
        }
        @Adapted(reason = "returns false", premise = Premise.NO_TRACING)
        private static boolean native_is_enabled(long ptr) {
            return false;
        }
        @android.ravenwood.annotation.RavenwoodReplace
        @Adapted(reason = "returns 0", premise = Premise.NO_TRACING)
        private static long native_get_extra_ptr(long ptr) {
            return 0;
        }

        /**
         * Whether the category is enabled or not.
         */
        @android.ravenwood.annotation.RavenwoodReplace
        public boolean isEnabled() {
            return IS_FLAG_ENABLED && native_is_enabled(mPtr);
        }

        /**
         * Returns the native pointer for the category.
         */
        @Override
        public long getPtr() {
            return mExtraPtr;
        }
    }

    @Adapted(reason = "returns 0", premise = Premise.NO_TRACING)
    private static long native_get_thread_track_uuid(long tid) {
        return 0;
    }

    /**
     * Writes a trace message to indicate a given section of code was invoked.
     *
     * @param category The perfetto category.
     * @param eventName The event name to appear in the trace.
     */
    public static PerfettoTrackEventExtra.Builder instant(Category category, String eventName) {
        return PerfettoTrackEventExtra.builder(category.isEnabled())
            .init(PERFETTO_TE_TYPE_INSTANT, category)
            .setEventName(eventName);
    }

    /**
     * Writes a trace message to indicate the start of a given section of code.
     *
     * @param category The perfetto category.
     * @param eventName The event name to appear in the trace.
     */
    public static PerfettoTrackEventExtra.Builder begin(Category category, String eventName) {
        return PerfettoTrackEventExtra.builder(category.isEnabled())
            .init(PERFETTO_TE_TYPE_SLICE_BEGIN, category)
            .setEventName(eventName);
    }

    /**
     * Writes a trace message to indicate the end of a given section of code.
     *
     * @param category The perfetto category.
     */
    public static PerfettoTrackEventExtra.Builder end(Category category) {
        return PerfettoTrackEventExtra.builder(category.isEnabled())
            .init(PERFETTO_TE_TYPE_SLICE_END, category);
    }

    /**
     * Writes a trace message to indicate the value of a given section of code.
     *
     * @param category The perfetto category.
     * @param value The value of the counter.
     */
    public static PerfettoTrackEventExtra.Builder counter(Category category, long value) {
        return PerfettoTrackEventExtra.builder(category.isEnabled())
            .init(PERFETTO_TE_TYPE_COUNTER, category)
            .setCounter(value);
    }

    /**
     * Returns the next flow id to be used.
     */
    public static int getFlowId() {
        return sFlowEventId.incrementAndGet();
    }

    /**
     * Given a thread tid, returns the thread track uuid that can be used as a parent track uuid.
     */
    public static long getThreadTrackUuid(long tid) {
        if (!IS_FLAG_ENABLED) {
            return 0;
        }
        return native_get_thread_track_uuid(tid);
    }
}
