/*
 * Copyright (C) 2011 The Android Open Source Project
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

import java.io.FileDescriptor;
import java.util.Arrays;

/**
 * Used as an in/out parameter to {@link Os#poll}.
 * Corresponds to C's {@code struct pollfd} from {@code <poll.h>}.
 */
@Emulated
public final class StructPollfd {
    /** The file descriptor to poll. */
    public FileDescriptor fd;

    /**
     * The events we're interested in. POLLIN corresponds to being in select(2)'s read fd set,
     * POLLOUT to the write fd set.
     */
    public short events;

    /** The events that actually happened. */
    public short revents;

    /**
     * A non-standard extension that lets callers conveniently map back to the object
     * their fd belongs to. This is used by Selector, for example, to associate each
     * FileDescriptor with the corresponding SelectionKey.
     */
    public Object userData;

    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/java/libcore/util/Objects.java#L31-L84")
    @Override public String toString() {
        return "StructPollfd[events=" + events + ",fd=" + valueOf(fd) + ",revents=" + revents
                + ",userData=" + valueOf(userData) + "]";
    }

    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/java/libcore/util/Objects.java#L51-L77")
    private static String valueOf(Object value) {
        if (value.getClass().isArray()) {
            if (value.getClass() == boolean[].class) {
                return Arrays.toString((boolean[]) value);
            } else if (value.getClass() == byte[].class) {
                return Arrays.toString((byte[]) value);
            } else if (value.getClass() == char[].class) {
                return Arrays.toString((char[]) value);
            } else if (value.getClass() == double[].class) {
                return Arrays.toString((double[]) value);
            } else if (value.getClass() == float[].class) {
                return Arrays.toString((float[]) value);
            } else if (value.getClass() == int[].class) {
                return Arrays.toString((int[]) value);
            } else if (value.getClass() == long[].class) {
                return Arrays.toString((long[]) value);
            } else if (value.getClass() == short[].class) {
                return Arrays.toString((short[]) value);
            } else {
                return Arrays.toString((Object[]) value);
            }
        } else if (value.getClass() == Character.class) {
            return "'" + value + "'";
        } else if (value.getClass() == String.class) {
            return "\"" + value + "\"";
        } else {
            return String.valueOf(value);
        }
    }
}
