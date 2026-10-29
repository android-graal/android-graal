/*
 * Copyright (C) 2017 The Android Open Source Project
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

package android.system;

import org.androidgraal.hosted.annotate.Emulated;
import org.androidgraal.hosted.annotate.Transcribed;

/**
 * Corresponds to C's {@code struct timespec} from {@code <time.h>}.
 */
@Emulated
public final class StructTimespec implements Comparable<StructTimespec> {
    /** Seconds part of time of last data modification. */
    public final long tv_sec; /*time_t*/

    /** Nanoseconds (values are [0, 999999999]). */
    public final long tv_nsec;

    public StructTimespec(long tv_sec, long tv_nsec) {
        this.tv_sec = tv_sec;
        this.tv_nsec = tv_nsec;
        if (tv_nsec < 0 || tv_nsec > 999_999_999) {
            throw new IllegalArgumentException(
                    "tv_nsec value " + tv_nsec + " is not in [0, 999999999]");
        }
    }

    @Override
    public int compareTo(StructTimespec other) {
        if (tv_sec > other.tv_sec) {
            return 1;
        }
        if (tv_sec < other.tv_sec) {
            return -1;
        }
        if (tv_nsec > other.tv_nsec) {
            return 1;
        }
        if (tv_nsec < other.tv_nsec) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StructTimespec that = (StructTimespec) o;

        if (tv_sec != that.tv_sec) return false;
        return tv_nsec == that.tv_nsec;
    }

    @Override
    public int hashCode() {
        int result = (int) (tv_sec ^ (tv_sec >>> 32));
        result = 31 * result + (int) (tv_nsec ^ (tv_nsec >>> 32));
        return result;
    }

    @Override
    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/java/libcore/util/Objects.java#L31-L84")
    public String toString() {
        return "StructTimespec[tv_nsec=" + tv_nsec + ",tv_sec=" + tv_sec + "]";
    }
}
