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

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.SystemApi;
import android.compat.annotation.UnsupportedAppUsage;
import android.ravenwood.annotation.RavenwoodClassLoadHook;
import android.ravenwood.annotation.RavenwoodKeepWholeClass;
import android.util.ExceptionUtils;
import android.util.Log;
import android.util.Slog;

import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.FastPrintWriter;

import dalvik.annotation.optimization.CriticalNative;

import libcore.io.IoUtils;

import org.androidgraal.hosted.annotate.Adapted;
import org.androidgraal.hosted.annotate.Emulated;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Base class for a remotable object, the core part of a lightweight
 * remote procedure call mechanism defined by {@link IBinder}.
 * This class is an implementation of IBinder that provides
 * standard local implementation of such an object.
 *
 * <p>Most developers will not implement this class directly, instead using the
 * <a href="{@docRoot}guide/components/aidl.html">aidl</a> tool to describe the desired
 * interface, having it generate the appropriate Binder subclass. You can,
 * however, derive directly from Binder to implement your own custom RPC
 * protocol or simply instantiate a raw Binder object directly to use as a
 * token that can be shared across processes.
 *
 * <p>This class is just a basic IPC primitive; it has no impact on an application's
 * lifecycle, and is valid only as long as the process that created it continues to run.
 * To use this correctly, you must be doing so within the context of a top-level
 * application component (a {@link android.app.Service}, {@link android.app.Activity},
 * or {@link android.content.ContentProvider}) that lets the system know your process
 * should remain running.
 *
 * <p>You must keep in mind the situations in which your process
 * could go away, and thus require that you later re-create a new Binder and re-attach
 * it when the process starts again. For example, if you are using this within an
 * {@link android.app.Activity}, your activity's process may be killed any time the
 * activity is not started; if the activity is later re-created you will need to
 * create a new Binder and hand it back to the correct place again; you need to be
 * aware that your process may be started for another reason (for example to receive
 * a broadcast) that will not involve re-creating the activity and thus run its code
 * to create a new Binder.
 *
 * @see IBinder
 */
@RavenwoodKeepWholeClass
@RavenwoodClassLoadHook(RavenwoodClassLoadHook.LIBANDROID_LOADING_HOOK)
@Emulated
@Adapted(reason = "does not implement IBinder")
public class Binder {
    private static boolean sIsHandlingBinderTransaction = false;

    /**
     * @hide
     */
    @Adapted(reason = "without isDirectlyHandlingTransactionNative")
    public static final boolean isDirectlyHandlingTransaction() {
        return sIsHandlingBinderTransaction;
    }

    /**
     * Default constructor just initializes the object.
     *
     * <p>If you're creating a Binder token (a Binder object without an attached interface),
     * you should use {@link #Binder(String)} instead.
     */
    @Adapted(reason = "empty instead of calling Binder(String)")
    public Binder() {
    }
}
