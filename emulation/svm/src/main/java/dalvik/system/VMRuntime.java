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

package dalvik.system;

import static android.annotation.SystemApi.Client.MODULE_LIBRARIES;

import android.annotation.SystemApi;
import android.annotation.FlaggedApi;
import android.compat.annotation.ChangeId;
import android.compat.annotation.EnabledSince;
import android.compat.annotation.Disabled;
import android.compat.annotation.UnsupportedAppUsage;

import com.android.libcore.Flags;

import dalvik.annotation.optimization.FastNative;

import org.androidgraal.android.Words;
import org.androidgraal.hosted.annotate.Adapted;
import org.androidgraal.hosted.annotate.Premise;
import org.androidgraal.hosted.annotate.Transcribed;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import libcore.util.NonNull;

/**
 * Provides an interface to VM-global, Dalvik-specific features.
 * An application cannot create its own Runtime instance, and must obtain
 * one from the getRuntime method.
 *
 * @hide
 */
@SystemApi(client = MODULE_LIBRARIES)
@libcore.api.IntraCoreApi
public final class VMRuntime {

    /**
     * Holds the VMRuntime singleton.
     */
    private static final VMRuntime THE_ONE = new VMRuntime();

    // Note: Instruction set names are used to construct the names of some
    // system properties. To be sure that the properties stay valid the
    // instruction set name should not exceed 7 characters. See installd
    // and the package manager for the actual propeties.
    private static final Map<String, String> ABI_TO_INSTRUCTION_SET_MAP
            = new HashMap<String, String>(16);
    static {
        ABI_TO_INSTRUCTION_SET_MAP.put("armeabi", "arm");
        ABI_TO_INSTRUCTION_SET_MAP.put("armeabi-v7a", "arm");
        ABI_TO_INSTRUCTION_SET_MAP.put("x86", "x86");
        ABI_TO_INSTRUCTION_SET_MAP.put("x86_64", "x86_64");
        ABI_TO_INSTRUCTION_SET_MAP.put("arm64-v8a", "arm64");
        ABI_TO_INSTRUCTION_SET_MAP.put("arm64-v8a-hwasan", "arm64");
        ABI_TO_INSTRUCTION_SET_MAP.put("riscv64", "riscv64");
    }

    /**
     * Magic version number for a current development build, which has not
     * yet turned into an official release. This number must be larger than
     * any released version in {@code android.os.Build.VERSION_CODES}.
     * @hide
     */
    @SystemApi(client = MODULE_LIBRARIES)
    // Must match android.os.Build.VERSION_CODES.CUR_DEVELOPMENT.
    public static final int SDK_VERSION_CUR_DEVELOPMENT = 10000;

    private int targetSdkVersion = SDK_VERSION_CUR_DEVELOPMENT;

    /**
     * Prevents this class from being instantiated.
     */
    private VMRuntime() {
    }

    /**
     * Returns the object that represents the current runtime.
     * @return the runtime object
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    @libcore.api.IntraCoreApi
    public static VMRuntime getRuntime() {
        return THE_ONE;
    }

    /**
     * Returns whether the VM is running in 64-bit mode.
     *
     * @return true if VM is running in 64-bit mode, false otherwise.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    @Transcribed("https://android.googlesource.com/platform/art/+/refs/tags/android-16.0.0_r1/runtime/native/dalvik_system_VMRuntime.cc#L232-L235")
    public boolean is64Bit() {
        return Words.is64Bit();
    }

    /**
     * Sets the target SDK version. Should only be called before the
     * app starts to run, because it may change the VM's behavior in
     * dangerous ways. Defaults to {@link #SDK_VERSION_CUR_DEVELOPMENT}.
     *
     * @param targetSdkVersion the SDK version the app wants to run with.
     *
     * @hide
     */
    @UnsupportedAppUsage(maxTargetSdk=0, publicAlternatives="Use the {@code targetSdkVersion}"
        +" attribute in the {@code uses-sdk} manifest tag instead.")
    @SystemApi(client = MODULE_LIBRARIES)
    @Adapted(reason = "without the setTargetSdkVersionNative call",
            premise = Premise.NO_ART_RUNTIME)
    public synchronized void setTargetSdkVersion(int targetSdkVersion) {
        this.targetSdkVersion = targetSdkVersion;
    }

    /**
     * Gets the target SDK version. See {@link #setTargetSdkVersion} for
     * special values.
     *
     * @return the target SDK version.
     *
     * @hide
     */
    @SystemApi(client = MODULE_LIBRARIES)
    public synchronized int getTargetSdkVersion() {
        return targetSdkVersion;
    }

    /**
     * Returns an array of at least {@code minLength}, but potentially larger. The increased size
     * comes from avoiding any padding after the array. The amount of padding varies depending on
     * the componentType and the memory allocator implementation.
     *
     * @param componentType the component type of the returned array.
     * @param minLength     the minimum length of the returned array. The actual length could
     *                      be greater.
     * @return              array of at least of {@code minLength}
     *
     * @hide
     */
    @SystemApi(client = MODULE_LIBRARIES)
    @Adapted(reason = "an array of exactly minLength through Array.newInstance instead of the native",
            premise = Premise.NO_ART_RUNTIME)
    public Object newUnpaddedArray(Class<?> componentType, int minLength) {
        return Array.newInstance(componentType, minLength);
    }

    /**
     * Returns the runtime instruction set corresponding to a given ABI. Multiple
     * compatible ABIs might map to the same instruction set. For example
     * {@code armeabi-v7a} and {@code armeabi} might map to the instruction set {@code arm}.
     *
     * This influences the compilation of the applications classes.
     *
     * @param abi The ABI we want the instruction set from.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static String getInstructionSet(String abi) {
        final String instructionSet = ABI_TO_INSTRUCTION_SET_MAP.get(abi);
        if (instructionSet == null) {
            throw new IllegalArgumentException("Unsupported ABI: " + abi);
        }

        return instructionSet;
    }

    /**
     * Returns whether the given {@code instructionSet} is 64 bits.
     *
     * @param instructionSet a string representing an instruction set.
     *
     * @return true if given {@code instructionSet} is 64 bits, false otherwise.
     *
     * @hide
     */
    @SystemApi(client = MODULE_LIBRARIES)
    public static boolean is64BitInstructionSet(String instructionSet) {
        return (instructionSet != null) && instructionSet.contains("64");
    }

    /**
     * Returns whether the given {@code abi} is 64 bits.
     *
     * @param abi a string representing an ABI.
     *
     * @return true if given {@code abi} is 64 bits, false otherwise.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static boolean is64BitAbi(String abi) {
        return is64BitInstructionSet(getInstructionSet(abi));
    }
}
