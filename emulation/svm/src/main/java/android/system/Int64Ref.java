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
 * A signed 64bit integer reference suitable for passing to lower-level system calls.
 */
@Emulated
public class Int64Ref {
    public long value;

    public Int64Ref(long value) {
        this.value = value;
    }

    @Transcribed("https://android.googlesource.com/platform/libcore/+/refs/tags/android-16.0.0_r1/luni/src/main/java/libcore/util/Objects.java#L31-L84")
    @Override public String toString() {
        return "Int64Ref[value=" + value + "]";
    }
}
