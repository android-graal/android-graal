// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
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

package android.content;

import static android.app.appfunctions.flags.Flags.FLAG_ENABLE_APP_FUNCTION_MANAGER;
import static android.app.ondeviceintelligence.flags.Flags.FLAG_ENABLE_ON_DEVICE_INTELLIGENCE_MODULE;
import static android.content.flags.Flags.FLAG_ENABLE_BIND_PACKAGE_ISOLATED_PROCESS;
import static android.security.Flags.FLAG_SECURE_LOCKDOWN;

import android.annotation.AttrRes;
import android.annotation.CallbackExecutor;
import android.annotation.CheckResult;
import android.annotation.ColorInt;
import android.annotation.ColorRes;
import android.annotation.DisplayContext;
import android.annotation.FlaggedApi;
import android.annotation.IntDef;
import android.annotation.LongDef;
import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.PermissionMethod;
import android.annotation.PermissionName;
import android.annotation.RequiresPermission;
import android.annotation.StringDef;
import android.annotation.StringRes;
import android.annotation.StyleRes;
import android.annotation.StyleableRes;
import android.annotation.SuppressLint;
import android.annotation.SystemApi;
import android.annotation.TestApi;
import android.annotation.UiContext;
import android.annotation.UserIdInt;
import android.compat.annotation.ChangeId;
import android.compat.annotation.EnabledSince;
import android.compat.annotation.UnsupportedAppUsage;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Flags;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.IBinder;
import android.os.Looper;
import android.os.StatFs;
import android.os.UserHandle;
import android.ravenwood.annotation.RavenwoodKeep;
import android.ravenwood.annotation.RavenwoodKeepPartialClass;
import android.util.AttributeSet;
import android.view.Display;

import com.android.internal.annotations.VisibleForTesting;

import org.androidgraal.android.handles.ContextBinding;
import org.androidgraal.hosted.annotate.Added;
import org.androidgraal.hosted.annotate.Emulated;
import org.androidgraal.shared.Proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * Interface to global information about an application environment.  This is
 * an abstract class whose implementation is provided by
 * the Android system.  It
 * allows access to application-specific resources and classes, as well as
 * up-calls for application-level operations such as launching activities,
 * broadcasting and receiving intents, etc.
 */
@RavenwoodKeepPartialClass
@Emulated
public abstract class Context implements Proxy {
    /**
     * After {@link Build.VERSION_CODES#TIRAMISU},
     * {@link #registerComponentCallbacks(ComponentCallbacks)} will add a {@link ComponentCallbacks}
     * to {@link Activity} or {@link ContextWrapper#getBaseContext()} instead of always adding to
     * {@link #getApplicationContext()}.
     *
     * @hide
     */
    @ChangeId
    @EnabledSince(targetSdkVersion = Build.VERSION_CODES.TIRAMISU)
    @VisibleForTesting
    public static final long OVERRIDABLE_COMPONENT_CALLBACKS = 193247900L;

    /**
     * The default device ID, which is the ID of the primary (non-virtual) device.
     */
    public static final int DEVICE_ID_DEFAULT = 0;
    /**
     * Invalid device ID.
     */
    public static final int DEVICE_ID_INVALID = -1;

    /** @hide */
    @IntDef(flag = true, prefix = { "MODE_" }, value = {
            MODE_PRIVATE,
            MODE_WORLD_READABLE,
            MODE_WORLD_WRITEABLE,
            MODE_APPEND,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface FileMode {}

    /** @hide */
    @IntDef(flag = true, prefix = { "MODE_" }, value = {
            MODE_PRIVATE,
            MODE_WORLD_READABLE,
            MODE_WORLD_WRITEABLE,
            MODE_MULTI_PROCESS,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface PreferencesMode {}

    /** @hide */
    @IntDef(flag = true, prefix = { "MODE_" }, value = {
            MODE_PRIVATE,
            MODE_WORLD_READABLE,
            MODE_WORLD_WRITEABLE,
            MODE_ENABLE_WRITE_AHEAD_LOGGING,
            MODE_NO_LOCALIZED_COLLATORS,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface DatabaseMode {}

    /**
     * File creation mode: the default mode, where the created file can only
     * be accessed by the calling application (or all applications sharing the
     * same user ID).
     */
    public static final int MODE_PRIVATE = 0x0000;

    /**
     * File creation mode: allow all other applications to have read access to
     * the created file.
     * <p>
     * Starting from {@link android.os.Build.VERSION_CODES#N}, attempting to use this
     * mode throws a {@link SecurityException}.
     *
     * @deprecated Creating world-readable files is very dangerous, and likely
     *             to cause security holes in applications. It is strongly
     *             discouraged; instead, applications should use more formal
     *             mechanism for interactions such as {@link ContentProvider},
     *             {@link BroadcastReceiver}, and {@link android.app.Service}.
     *             There are no guarantees that this access mode will remain on
     *             a file, such as when it goes through a backup and restore.
     * @see androidx.core.content.FileProvider
     * @see Intent#FLAG_GRANT_WRITE_URI_PERMISSION
     */
    @Deprecated
    public static final int MODE_WORLD_READABLE = 0x0001;

    /**
     * File creation mode: allow all other applications to have write access to
     * the created file.
     * <p>
     * Starting from {@link android.os.Build.VERSION_CODES#N}, attempting to use this
     * mode will throw a {@link SecurityException}.
     *
     * @deprecated Creating world-writable files is very dangerous, and likely
     *             to cause security holes in applications. It is strongly
     *             discouraged; instead, applications should use more formal
     *             mechanism for interactions such as {@link ContentProvider},
     *             {@link BroadcastReceiver}, and {@link android.app.Service}.
     *             There are no guarantees that this access mode will remain on
     *             a file, such as when it goes through a backup and restore.
     * @see androidx.core.content.FileProvider
     * @see Intent#FLAG_GRANT_WRITE_URI_PERMISSION
     */
    @Deprecated
    public static final int MODE_WORLD_WRITEABLE = 0x0002;

    /**
     * File creation mode: for use with {@link #openFileOutput}, if the file
     * already exists then write data to the end of the existing file
     * instead of erasing it.
     * @see #openFileOutput
     */
    public static final int MODE_APPEND = 0x8000;

    /**
     * SharedPreference loading flag: when set, the file on disk will
     * be checked for modification even if the shared preferences
     * instance is already loaded in this process.  This behavior is
     * sometimes desired in cases where the application has multiple
     * processes, all writing to the same SharedPreferences file.
     * Generally there are better forms of communication between
     * processes, though.
     *
     * <p>This was the legacy (but undocumented) behavior in and
     * before Gingerbread (Android 2.3) and this flag is implied when
     * targeting such releases.  For applications targeting SDK
     * versions <em>greater than</em> Android 2.3, this flag must be
     * explicitly set if desired.
     *
     * @see #getSharedPreferences
     *
     * @deprecated MODE_MULTI_PROCESS does not work reliably in
     * some versions of Android, and furthermore does not provide any
     * mechanism for reconciling concurrent modifications across
     * processes.  Applications should not attempt to use it.  Instead,
     * they should use an explicit cross-process data management
     * approach such as {@link android.content.ContentProvider ContentProvider}.
     */
    @Deprecated
    public static final int MODE_MULTI_PROCESS = 0x0004;

    /**
     * Database open flag: when set, the database is opened with write-ahead
     * logging enabled by default.
     *
     * @see #openOrCreateDatabase(String, int, CursorFactory)
     * @see #openOrCreateDatabase(String, int, CursorFactory, DatabaseErrorHandler)
     * @see SQLiteDatabase#enableWriteAheadLogging
     */
    public static final int MODE_ENABLE_WRITE_AHEAD_LOGGING = 0x0008;

    /**
     * Database open flag: when set, the database is opened without support for
     * localized collators.
     *
     * @see #openOrCreateDatabase(String, int, CursorFactory)
     * @see #openOrCreateDatabase(String, int, CursorFactory, DatabaseErrorHandler)
     * @see SQLiteDatabase#NO_LOCALIZED_COLLATORS
     */
    public static final int MODE_NO_LOCALIZED_COLLATORS = 0x0010;

    /**
     * Flags used for bindService(int) APIs. Note, we now have long BIND_* flags.
     * @hide
     */
    @IntDef(flag = true, prefix = { "BIND_" }, value = {
            BIND_AUTO_CREATE,
            BIND_DEBUG_UNBIND,
            BIND_NOT_FOREGROUND,
            BIND_ABOVE_CLIENT,
            BIND_ALLOW_OOM_MANAGEMENT,
            BIND_WAIVE_PRIORITY,
            BIND_IMPORTANT,
            BIND_ADJUST_WITH_ACTIVITY,
            BIND_NOT_PERCEPTIBLE,
            BIND_ALLOW_ACTIVITY_STARTS,
            BIND_INCLUDE_CAPABILITIES,
            BIND_SHARED_ISOLATED_PROCESS,
            BIND_PACKAGE_ISOLATED_PROCESS,
            BIND_EXTERNAL_SERVICE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface BindServiceFlagsBits {}

    /**
     * Long version of BIND_* flags used for bindService(BindServiceFlags) APIs.
     * @hide
     */
    @LongDef(flag = true, prefix = { "BIND_" }, value = {
            BIND_AUTO_CREATE,
            BIND_DEBUG_UNBIND,
            BIND_NOT_FOREGROUND,
            BIND_ABOVE_CLIENT,
            BIND_ALLOW_OOM_MANAGEMENT,
            BIND_WAIVE_PRIORITY,
            BIND_IMPORTANT,
            BIND_ADJUST_WITH_ACTIVITY,
            BIND_NOT_PERCEPTIBLE,
            BIND_ALLOW_ACTIVITY_STARTS,
            BIND_INCLUDE_CAPABILITIES,
            BIND_SHARED_ISOLATED_PROCESS,
            BIND_PACKAGE_ISOLATED_PROCESS,
            // Intentionally not include BIND_EXTERNAL_SERVICE, because it'd cause sign-extension.
            // This would allow Android Studio to show a warning, if someone tries to use
            // BIND_EXTERNAL_SERVICE BindServiceFlags.
            BIND_EXTERNAL_SERVICE_LONG,
            // Make sure no flag uses the sign bit (most significant bit) of the long integer,
            // to avoid future confusion.
            BIND_BYPASS_USER_NETWORK_RESTRICTIONS,
            BIND_MATCH_QUARANTINED_COMPONENTS,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface BindServiceFlagsLongBits {}

    /**
     * Flag for {@link #bindService}: automatically create the service as long
     * as the binding exists.  Note that while this will create the service,
     * its {@link android.app.Service#onStartCommand}
     * method will still only be called due to an
     * explicit call to {@link #startService}.  Even without that, though,
     * this still provides you with access to the service object while the
     * service is created.
     *
     * <p>Note that prior to {@link android.os.Build.VERSION_CODES#ICE_CREAM_SANDWICH},
     * not supplying this flag would also impact how important the system
     * consider's the target service's process to be.  When set, the only way
     * for it to be raised was by binding from a service in which case it will
     * only be important when that activity is in the foreground.  Now to
     * achieve this behavior you must explicitly supply the new flag
     * {@link #BIND_ADJUST_WITH_ACTIVITY}.  For compatibility, old applications
     * that don't specify {@link #BIND_AUTO_CREATE} will automatically have
     * the flags {@link #BIND_WAIVE_PRIORITY} and
     * {@link #BIND_ADJUST_WITH_ACTIVITY} set for them in order to achieve
     * the same result.
     */
    public static final int BIND_AUTO_CREATE = 0x0001;

    /**
     * Flag for {@link #bindService}: include debugging help for mismatched
     * calls to unbind.  When this flag is set, the callstack of the following
     * {@link #unbindService} call is retained, to be printed if a later
     * incorrect unbind call is made.  Note that doing this requires retaining
     * information about the binding that was made for the lifetime of the app,
     * resulting in a leak -- this should only be used for debugging.
     */
    public static final int BIND_DEBUG_UNBIND = 0x0002;

    /**
     * Flag for {@link #bindService}: don't allow this binding to raise
     * the target service's process to the foreground scheduling priority.
     * It will still be raised to at least the same memory priority
     * as the client (so that its process will not be killable in any
     * situation where the client is not killable), but for CPU scheduling
     * purposes it may be left in the background.  This only has an impact
     * in the situation where the binding client is a foreground process
     * and the target service is in a background process.
     */
    public static final int BIND_NOT_FOREGROUND = 0x0004;

    /**
     * Flag for {@link #bindService}: indicates that the client application
     * binding to this service considers the service to be more important than
     * the app itself.  When set, the platform will try to have the out of
     * memory killer kill the app before it kills the service it is bound to, though
     * this is not guaranteed to be the case.
     */
    public static final int BIND_ABOVE_CLIENT = 0x0008;

    /**
     * Flag for {@link #bindService}: allow the process hosting the bound
     * service to go through its normal memory management.  It will be
     * treated more like a running service, allowing the system to
     * (temporarily) expunge the process if low on memory or for some other
     * whim it may have, and being more aggressive about making it a candidate
     * to be killed (and restarted) if running for a long time.
     */
    public static final int BIND_ALLOW_OOM_MANAGEMENT = 0x0010;

    /**
     * Flag for {@link #bindService}: don't impact the scheduling or
     * memory management priority of the target service's hosting process.
     * Allows the service's process to be managed on the background LRU list
     * just like a regular application process in the background.
     */
    public static final int BIND_WAIVE_PRIORITY = 0x0020;

    /**
     * Flag for {@link #bindService}: this service is very important to
     * the client, so should be brought to the foreground process level
     * when the client is.  Normally a process can only be raised to the
     * visibility level by a client, even if that client is in the foreground.
     */
    public static final int BIND_IMPORTANT = 0x0040;

    /**
     * Flag for {@link #bindService}: If binding from an activity, allow the
     * target service's process importance to be raised based on whether the
     * activity is visible to the user, regardless whether another flag is
     * used to reduce the amount that the client process's overall importance
     * is used to impact it.
     */
    public static final int BIND_ADJUST_WITH_ACTIVITY = 0x0080;

    /**
     * Flag for {@link #bindService}: If binding from an app that is visible or user-perceptible,
     * lower the target service's importance to below the perceptible level. This allows
     * the system to (temporarily) expunge the bound process from memory to make room for more
     * important user-perceptible processes.
     */
    public static final int BIND_NOT_PERCEPTIBLE = 0x00000100;

    /**
     * Flag for {@link #bindService}: If binding from an app that is visible, the bound service is
     * allowed to start an activity from background. This was the default behavior before SDK
     * version {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE}. Since then, the default
     * behavior changed to disallow the bound service to start a background activity even if the app
     * bound to it is in foreground, unless this flag is specified when binding.
     */
    public static final int BIND_ALLOW_ACTIVITY_STARTS = 0X000000200;

    /**
     * Flag for {@link #bindService}: If binding from an app that has specific capabilities
     * due to its foreground state such as an activity or foreground service, then this flag will
     * allow the bound app to get the same capabilities, as long as it has the required permissions
     * as well.
     *
     * If binding from a top app and its target SDK version is at or above
     * {@link android.os.Build.VERSION_CODES#R}, the app needs to
     * explicitly use BIND_INCLUDE_CAPABILITIES flag to pass all capabilities to the service so the
     * other app can have while-in-use access such as location, camera, microphone from background.
     * If binding from a top app and its target SDK version is below
     * {@link android.os.Build.VERSION_CODES#R}, BIND_INCLUDE_CAPABILITIES is implicit.
     */
    public static final int BIND_INCLUDE_CAPABILITIES = 0x000001000;

    /**
     * Flag for {@link #bindIsolatedService}: Bind the service into a shared isolated process.
     * Specifying this flag allows multiple isolated services to be running in a single shared
     * isolated process.
     *
     * The shared isolated process instance is identified by the <var>instanceName</var>
     * parameter in {@link #bindIsolatedService(Intent, int, String, Executor, ServiceConnection)}.
     *
     * Subsequent calls to {@link #bindIsolatedService} with the same <var>instanceName</var>
     * will cause the isolated service to be co-located in the same shared isolated process.
     *
     * Note that the shared isolated process is scoped to the calling app; once created, only
     * the calling app can bind additional isolated services into the shared process. However,
     * the services themselves can come from different APKs and therefore different vendors.
     *
     * Only services that set the {@link android.R.attr#allowSharedIsolatedProcess} attribute
     * to {@code true} are allowed to be bound into a shared isolated process.
     *
     */
    public static final int BIND_SHARED_ISOLATED_PROCESS = 0x00002000;

    /**
     * Flag for {@link #bindIsolatedService}: Bind the service into a shared isolated process,
     * but only with other isolated services from the same package that declare the same process
     * name.
     *
     * <p>Specifying this flag allows multiple isolated services defined in the same package to be
     * running in a single shared isolated process. This shared isolated process must be specified
     * since this flag will not work with the default application process.
     *
     * <p>This flag is different from {@link #BIND_SHARED_ISOLATED_PROCESS} since it only
     * allows binding services from the same package in the same shared isolated process. This also
     * means the shared package isolated process is global, and not scoped to each potential
     * calling app.
     *
     * <p>The shared isolated process instance is identified by the "android:process" attribute
     * defined by the service. This flag cannot be used without this attribute set.
     */
    @FlaggedApi(FLAG_ENABLE_BIND_PACKAGE_ISOLATED_PROCESS)
    public static final int BIND_PACKAGE_ISOLATED_PROCESS = 1 << 14;

    /***********    Public flags above this line ***********/
    /***********    Hidden flags below this line ***********/

    /**
     * Flag for {@link #bindService}: This flag is only intended to be used by the system to
     * indicate that a service binding is not considered as real package component usage and should
     * not generate a {@link android.app.usage.UsageEvents.Event#APP_COMPONENT_USED} event in usage
     * stats.
     * @hide
     */
    public static final int BIND_NOT_APP_COMPONENT_USAGE = 0x00008000;

    /**
     * Flag for {@link #bindService}: allow the process hosting the target service to be treated
     * as if it's as important as a perceptible app to the user and avoid the oom killer killing
     * this process in low memory situations until there aren't any other processes left but the
     * ones which are user-perceptible.
     *
     * @hide
     */
    public static final int BIND_ALMOST_PERCEPTIBLE = 0x000010000;

    /**
     * Flag for {@link #bindService}: allow the process hosting the target service to gain
     * {@link ActivityManager#PROCESS_CAPABILITY_POWER_RESTRICTED_NETWORK}, which allows it be able
     * to access network regardless of any power saving restrictions.
     *
     * @hide
     */
    public static final int BIND_BYPASS_POWER_NETWORK_RESTRICTIONS = 0x00020000;

    /**
     * Do not use. This flag is no longer needed nor used.
     * @hide
     */
    @SystemApi
    @Deprecated
    public static final int BIND_ALLOW_FOREGROUND_SERVICE_STARTS_FROM_BACKGROUND = 0x00040000;

    /**
     * Flag for {@link #bindService}: This flag is intended to be used only by the system to adjust
     * the scheduling policy for IMEs (and any other out-of-process user-visible components that
     * work closely with the top app) so that UI hosted in such services can have the same
     * scheduling policy (e.g. SCHED_FIFO when it is enabled and TOP_APP_PRIORITY_BOOST otherwise)
     * as the actual top-app.
     * @hide
     */
    public static final int BIND_SCHEDULE_LIKE_TOP_APP = 0x00080000;

    /**
     * Flag for {@link #bindService}: allow background activity starts from the bound service's
     * process.
     * This flag is only respected if the caller is holding
     * {@link android.Manifest.permission#START_ACTIVITIES_FROM_BACKGROUND}.
     * @hide
     */
    @SystemApi
    public static final int BIND_ALLOW_BACKGROUND_ACTIVITY_STARTS = 0x00100000;

    /**
     * @hide Flag for {@link #bindService}: the service being bound to represents a
     * protected system component, so must have association restrictions applied to it.
     * That is, a system config must have one or more allow-association tags limiting
     * which packages it can interact with.  If it does not have any such association
     * restrictions, a default empty set will be created.
     */
    public static final int BIND_RESTRICT_ASSOCIATIONS = 0x00200000;

    /**
     * @hide Flag for {@link #bindService}: allows binding to a service provided
     * by an instant app. Note that the caller may not have access to the instant
     * app providing the service which is a violation of the instant app sandbox.
     * This flag is intended ONLY for development/testing and should be used with
     * great care. Only the system is allowed to use this flag.
     */
    public static final int BIND_ALLOW_INSTANT = 0x00400000;

    /**
     * @hide Flag for {@link #bindService}: like {@link #BIND_NOT_FOREGROUND}, but puts it
     * up in to the important background state (instead of transient).
     */
    public static final int BIND_IMPORTANT_BACKGROUND = 0x00800000;

    /**
     * @hide Flag for {@link #bindService}: allows application hosting service to manage allowlists
     * such as temporary allowing a {@code PendingIntent} to bypass Power Save mode.
     */
    public static final int BIND_ALLOW_WHITELIST_MANAGEMENT = 0x01000000;

    /**
     * @hide Flag for {@link #bindService}: Like {@link #BIND_FOREGROUND_SERVICE},
     * but only applies while the device is awake.
     */
    public static final int BIND_FOREGROUND_SERVICE_WHILE_AWAKE = 0x02000000;

    /**
     * Flag for {@link #bindService}: For only the case where the binding
     * is coming from the system, set the process state to BOUND_FOREGROUND_SERVICE
     * instead of the normal maximum of IMPORTANT_FOREGROUND.  That is, this is
     * saying that the process shouldn't participate in the normal power reduction
     * modes (removing network access etc).
     * @hide
     */
    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    @FlaggedApi(FLAG_ENABLE_ON_DEVICE_INTELLIGENCE_MODULE)
    public static final int BIND_FOREGROUND_SERVICE = 0x04000000;

    /**
     * @hide Flag for {@link #bindService}: Treat the binding as hosting
     * an activity, an unbinding as the activity going in the background.
     * That is, when unbinding, the process when empty will go on the activity
     * LRU list instead of the regular one, keeping it around more aggressively
     * than it otherwise would be.  This is intended for use with IMEs to try
     * to keep IME processes around for faster keyboard switching.
     */
    public static final int BIND_TREAT_LIKE_ACTIVITY = 0x08000000;

    /**
     * @hide An idea that is not yet implemented.
     * Flag for {@link #bindService}: If binding from an activity, consider
     * this service to be visible like the binding activity is.  That is,
     * it will be treated as something more important to keep around than
     * invisible background activities.  This will impact the number of
     * recent activities the user can switch between without having them
     * restart.  There is no guarantee this will be respected, as the system
     * tries to balance such requests from one app vs. the importance of
     * keeping other apps around.
     *
     * @deprecated Repurposed to {@link #BIND_TREAT_LIKE_VISIBLE_FOREGROUND_SERVICE}.
     */
    @Deprecated
    public static final int BIND_VISIBLE = 0x10000000;

    /**
     * @hide Flag for {@link #bindService}: Treat the binding as hosting a foreground service
     * and also visible to the user. That is, the app hosting the service will get its process state
     * bumped to the {@link android.app.ActivityManager#PROCESS_STATE_FOREGROUND_SERVICE},
     * and it's considered as visible to the user, thus less likely to be expunged from memory
     * on low memory situations. This is intented for use by processes with the process state
     * better than the {@link android.app.ActivityManager#PROCESS_STATE_TOP}.
     */
    public static final int BIND_TREAT_LIKE_VISIBLE_FOREGROUND_SERVICE = 0x10000000;

    /**
     * @hide
     * Flag for {@link #bindService}: Consider this binding to be causing the target
     * process to be showing UI, so it will be do a UI_HIDDEN memory trim when it goes
     * away.
     */
    public static final int BIND_SHOWING_UI = 0x20000000;

    /**
     * Flag for {@link #bindService}: Don't consider the bound service to be
     * visible, even if the caller is visible.
     * @hide
     */
    public static final int BIND_NOT_VISIBLE = 0x40000000;

    /**
     * Flag for {@link #bindService}: The service being bound is an
     * {@link android.R.attr#isolatedProcess isolated},
     * {@link android.R.attr#externalService external} service.  This binds the service into the
     * calling application's package, rather than the package in which the service is declared.
     * <p>
     * When using this flag, the code for the service being bound will execute under the calling
     * application's package name and user ID.  Because the service must be an isolated process,
     * it will not have direct access to the application's data, though.
     *
     * The purpose of this flag is to allow applications to provide services that are attributed
     * to the app using the service, rather than the application providing the service.
     * </p>
     *
     * <em>This flag is NOT compatible with {@link BindServiceFlags}. If you need to use
     * {@link BindServiceFlags}, you must use {@link #BIND_EXTERNAL_SERVICE_LONG} instead.</em>
     */
    public static final int BIND_EXTERNAL_SERVICE = 0x80000000;

    /**
     * Works in the same way as {@link #BIND_EXTERNAL_SERVICE}, but it's defined as a {@code long}
     * value that is compatible to {@link BindServiceFlags}.
     */
    public static final long BIND_EXTERNAL_SERVICE_LONG = 1L << 62;

    /**
     * Flag for {@link #bindService}: allow the process hosting the target service to gain
     * {@link ActivityManager#PROCESS_CAPABILITY_USER_RESTRICTED_NETWORK}, which allows it be able
     * to access network regardless of any user restrictions.
     *
     * @hide
     */
    public static final long BIND_BYPASS_USER_NETWORK_RESTRICTIONS = 0x1_0000_0000L;

    /**
     * Flag for {@link #bindService}.
     *
     * @hide
     */
    public static final long BIND_MATCH_QUARANTINED_COMPONENTS = 0x2_0000_0000L;

    /**
     * Flag for {@link #bindService} that allows the bound app to be frozen if it is eligible.
     *
     * @hide
     */
    public static final long BIND_ALLOW_FREEZE = 0x4_0000_0000L;

    /**
     * These bind flags reduce the strength of the binding such that we shouldn't
     * consider it as pulling the process up to the level of the one that is bound to it.
     * @hide
     */
    public static final long BIND_REDUCTION_FLAGS =
            Context.BIND_ALLOW_OOM_MANAGEMENT | Context.BIND_WAIVE_PRIORITY
                    | Context.BIND_NOT_PERCEPTIBLE | Context.BIND_NOT_VISIBLE
                    | Context.BIND_ALLOW_FREEZE;

    /** @hide */
    @IntDef(flag = true, prefix = { "RECEIVER_VISIBLE" }, value = {
            RECEIVER_VISIBLE_TO_INSTANT_APPS, RECEIVER_EXPORTED, RECEIVER_NOT_EXPORTED,
            RECEIVER_EXPORTED_UNAUDITED
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface RegisterReceiverFlags {}

    /**
     * Flag for {@link #registerReceiver}: The receiver can receive broadcasts from Instant Apps.
     */
    public static final int RECEIVER_VISIBLE_TO_INSTANT_APPS = 0x1;

    /**
     * Flag for {@link #registerReceiver}: The receiver can receive broadcasts from other Apps.
     * Has the same behavior as marking a statically registered receiver with "exported=true"
     */
    public static final int RECEIVER_EXPORTED = 0x2;

    /**
     * @deprecated Use {@link #RECEIVER_NOT_EXPORTED} or {@link #RECEIVER_EXPORTED} instead.
     * @hide
     */
    @Deprecated
    @TestApi
    public static final int RECEIVER_EXPORTED_UNAUDITED = RECEIVER_EXPORTED;

    /**
     * Flag for {@link #registerReceiver}: The receiver cannot receive broadcasts from other Apps.
     * Has the same behavior as marking a statically registered receiver with "exported=false"
     */
    public static final int RECEIVER_NOT_EXPORTED = 0x4;

    /**
     * The permission is granted.
     *
     * @hide
     */
    public static final int PERMISSION_REQUEST_STATE_GRANTED = 0;

    /**
     * The permission isn't granted, but apps can request the permission. When the app request
     * the permission, user will be prompted with permission dialog to grant or deny the request.
     *
     * @hide
     */
    public static final int PERMISSION_REQUEST_STATE_REQUESTABLE = 1;

    /**
     * The permission is denied, and shouldn't be requested by apps. Permission request
     * will be automatically denied by the system, preventing the permission dialog from being
     * displayed to the user.
     *
     * @hide
     */
    public static final int PERMISSION_REQUEST_STATE_UNREQUESTABLE = 2;


    /** @hide */
    @IntDef(prefix = { "PERMISSION_REQUEST_STATE_" }, value = {
            PERMISSION_REQUEST_STATE_GRANTED,
            PERMISSION_REQUEST_STATE_REQUESTABLE,
            PERMISSION_REQUEST_STATE_UNREQUESTABLE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface PermissionRequestState {}

    /**
     * Returns an AssetManager instance for the application's package.
     * <p>
     * <strong>Note:</strong> Implementations of this method should return
     * an AssetManager instance that is consistent with the Resources instance
     * returned by {@link #getResources()}. For example, they should share the
     * same {@link Configuration} object.
     *
     * @return an AssetManager instance for the application's package
     * @see #getResources()
     */
    public AssetManager getAssets() {
        throw new UnsupportedOperationException("getAssets()Landroid/content/res/AssetManager;");
    }

    /**
     * Return the Looper for the main thread of the current process.  This is
     * the thread used to dispatch calls to application components (activities,
     * services, etc).
     * <p>
     * By definition, this method returns the same result as would be obtained
     * by calling {@link Looper#getMainLooper() Looper.getMainLooper()}.
     * </p>
     *
     * @return The main looper.
     */
    public Looper getMainLooper() {
        throw new UnsupportedOperationException("getMainLooper()Landroid/os/Looper;");
    }

    /**
     * Return an {@link Executor} that will run enqueued tasks on the main
     * thread associated with this context. This is the thread used to dispatch
     * calls to application components (activities, services, etc).
     */
    public Executor getMainExecutor() {
        if (Build.VERSION.SDK_INT < 28) {
            throw new NoSuchMethodError("getMainExecutor()Ljava/util/concurrent/Executor;");
        }
        throw new UnsupportedOperationException("getMainExecutor()Ljava/util/concurrent/Executor;");
    }

    /**
     * Return the context of the single, global Application object of the
     * current process.  This generally should only be used if you need a
     * Context whose lifecycle is separate from the current context, that is
     * tied to the lifetime of the process rather than the current component.
     *
     * <p>Consider for example how this interacts with
     * {@link #registerReceiver(BroadcastReceiver, IntentFilter)}:
     * <ul>
     * <li> <p>If used from an Activity context, the receiver is being registered
     * within that activity.  This means that you are expected to unregister
     * before the activity is done being destroyed; in fact if you do not do
     * so, the framework will clean up your leaked registration as it removes
     * the activity and log an error.  Thus, if you use the Activity context
     * to register a receiver that is static (global to the process, not
     * associated with an Activity instance) then that registration will be
     * removed on you at whatever point the activity you used is destroyed.
     * <li> <p>If used from the Context returned here, the receiver is being
     * registered with the global state associated with your application.  Thus
     * it will never be unregistered for you.  This is necessary if the receiver
     * is associated with static data, not a particular component.  However
     * using the ApplicationContext elsewhere can easily lead to serious leaks
     * if you forget to unregister, unbind, etc.
     * </ul>
     */
    public Context getApplicationContext() {
        return ContextBinding.getApplicationContext(this);
    }

    /**
     * Gets the next autofill ID.
     *
     * <p>All IDs will be smaller or the same as {@link View#LAST_APP_AUTOFILL_ID}. All IDs
     * returned will be unique.
     *
     * @return A ID that is unique in the process
     *
     * {@hide}
     */
    public int getNextAutofillId() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Return a localized, styled CharSequence from the application's package's
     * default string table.
     *
     * @param resId Resource id for the CharSequence text
     */
    @NonNull
    @RavenwoodKeep
    public final CharSequence getText(@StringRes int resId) {
        throw new UnsupportedOperationException("getText(I)Ljava/lang/CharSequence;");
    }

    /**
     * Returns a localized string from the application's package's
     * default string table.
     *
     * @param resId Resource id for the string
     * @return The string data associated with the resource, stripped of styled
     *         text information.
     */
    @NonNull
    @RavenwoodKeep
    public final String getString(@StringRes int resId) {
        return ContextBinding.getString(this, resId);
    }

    /**
     * Returns a localized formatted string from the application's package's
     * default string table, substituting the format arguments as defined in
     * {@link java.util.Formatter} and {@link java.lang.String#format}.
     *
     * @param resId Resource id for the format string
     * @param formatArgs The format arguments that will be used for
     *                   substitution.
     * @return The string data associated with the resource, formatted and
     *         stripped of styled text information.
     */
    @NonNull
    @RavenwoodKeep
    public final String getString(@StringRes int resId, Object... formatArgs) {
        throw new UnsupportedOperationException(
                "getString(I[Ljava/lang/Object;)Ljava/lang/String;");
    }

    /**
     * Returns a color associated with a particular resource ID and styled for
     * the current theme.
     *
     * @param id The desired resource identifier, as generated by the aapt
     *           tool. This integer encodes the package, type, and resource
     *           entry. The value 0 is an invalid identifier.
     * @return A single color value in the form 0xAARRGGBB.
     * @throws android.content.res.Resources.NotFoundException if the given ID
     *         does not exist.
     */
    @ColorInt
    @RavenwoodKeep
    public final int getColor(@ColorRes int id) {
        if (Build.VERSION.SDK_INT < 23) {
            throw new NoSuchMethodError("getColor(I)I");
        }
        return ContextBinding.getColor(this, id);
    }

     /**
     * Set the base theme for this context.  Note that this should be called
     * before any views are instantiated in the Context (for example before
     * calling {@link android.app.Activity#setContentView} or
     * {@link android.view.LayoutInflater#inflate}).
     *
     * @param resid The style resource describing the theme.
     */
    public void setTheme(@StyleRes int resid) {
        ContextBinding.setTheme(this, resid);
    }

    /** @hide Needed for some internal implementation...  not public because
     * you can't assume this actually means anything. */
    @UnsupportedAppUsage
    public int getThemeResId() {
        return 0;
    }

    /**
     * Retrieve styled attribute information in this Context's theme.  See
     * {@link android.content.res.Resources.Theme#obtainStyledAttributes(int[])}
     * for more information.
     *
     * @see android.content.res.Resources.Theme#obtainStyledAttributes(int[])
     */
    @NonNull
    @RavenwoodKeep
    public final TypedArray obtainStyledAttributes(@NonNull @StyleableRes int[] attrs) {
        throw new UnsupportedOperationException(
                "obtainStyledAttributes([I)Landroid/content/res/TypedArray;");
    }

    /**
     * Retrieve styled attribute information in this Context's theme.  See
     * {@link android.content.res.Resources.Theme#obtainStyledAttributes(AttributeSet, int[], int, int)}
     * for more information.
     *
     * @see android.content.res.Resources.Theme#obtainStyledAttributes(AttributeSet, int[], int, int)
     */
    @NonNull
    @RavenwoodKeep
    public final TypedArray obtainStyledAttributes(
            @Nullable AttributeSet set, @NonNull @StyleableRes int[] attrs) {
        throw new UnsupportedOperationException(
                "obtainStyledAttributes(Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;");
    }

    /**
     * Retrieve styled attribute information in this Context's theme.  See
     * {@link android.content.res.Resources.Theme#obtainStyledAttributes(AttributeSet, int[], int, int)}
     * for more information.
     *
     * @see android.content.res.Resources.Theme#obtainStyledAttributes(AttributeSet, int[], int, int)
     */
    @NonNull
    @RavenwoodKeep
    public final TypedArray obtainStyledAttributes(@Nullable AttributeSet set,
            @NonNull @StyleableRes int[] attrs, @AttrRes int defStyleAttr,
            @StyleRes int defStyleRes) {
        throw new UnsupportedOperationException(
                "obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;");
    }

    /**
     * Return a class loader you can use to retrieve classes in this package.
     */
    public ClassLoader getClassLoader() {
        throw new UnsupportedOperationException("getClassLoader()Ljava/lang/ClassLoader;");
    }

    /** Return the name of this application's package. */
    public String getPackageName() {
        return ContextBinding.getPackageName(this);
    }

    /**
     * @hide Return the name of the base context this context is derived from.
     * This is the same as {@link #getOpPackageName()} except in
     * cases where system components are loaded into other app processes, in which
     * case {@link #getOpPackageName()} will be the name of the primary package in
     * that process (so that app ops uid verification will work with the name).
     */
    @SuppressWarnings("HiddenAbstractMethod")
    @UnsupportedAppUsage
    public String getBasePackageName() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Return the package name that should be used for {@link android.app.AppOpsManager} calls from
     * this context, so that app ops manager's uid verification will work with the name.
     * <p>
     * This is not generally intended for third party application developers.
     */
    @NonNull
    public String getOpPackageName() {
        if (Build.VERSION.SDK_INT < 29) {
            throw new NoSuchMethodError("getOpPackageName()Ljava/lang/String;");
        }
        return ContextBinding.getOpPackageName(this);
    }

    /**
     * <p>Attribution can be used in complex apps to logically separate parts of the app. E.g. a
     * blogging app might also have a instant messaging app built in. In this case two separate tags
     * can for used each sub-feature.
     *
     * @return the attribution tag this context is for or {@code null} if this is the default.
     */
    public @Nullable String getAttributionTag() {
        if (Build.VERSION.SDK_INT < 30) {
            throw new NoSuchMethodError("getAttributionTag()Ljava/lang/String;");
        }
        return ContextBinding.getAttributionTag(this);
    }

    // TODO moltmann: Remove
    /**
     * @removed
     */
    @Deprecated
    public @Nullable String getFeatureId() {
        return getAttributionTag();
    }

    /**
     * Return the full path to this context's primary Android package.
     * The Android package is a ZIP file which contains the application's
     * primary resources.
     *
     * <p>Note: this is not generally useful for applications, since they should
     * not be directly accessing the file system.
     *
     * @return String Path to the resources.
     */
    public String getPackageResourcePath() {
        return ContextBinding.getPackageResourcePath(this);
    }

    /**
     * Return the full path to this context's primary Android package.
     * The Android package is a ZIP file which contains application's
     * primary code and assets.
     *
     * <p>Note: this is not generally useful for applications, since they should
     * not be directly accessing the file system.
     *
     * @return String Path to the code and assets.
     */
    public String getPackageCodePath() {
        return ContextBinding.getPackageCodePath(this);
    }

    /**
     * @hide
     * @deprecated use {@link #getSharedPreferencesPath(String)}
     */
    @Deprecated
    @UnsupportedAppUsage
    public File getSharedPrefsFile(String name) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Retrieve and hold the contents of the preferences file 'name', returning
     * a SharedPreferences through which you can retrieve and modify its
     * values.  Only one instance of the SharedPreferences object is returned
     * to any callers for the same name, meaning they will see each other's
     * edits as soon as they are made.
     *
     * <p>This method is thread-safe.
     *
     * <p>If the preferences directory does not already exist, it will be created when this method
     * is called.
     *
     * <p>If a preferences file by this name does not exist, it will be created when you retrieve an
     * editor ({@link SharedPreferences#edit()}) and then commit changes ({@link
     * SharedPreferences.Editor#commit()} or {@link SharedPreferences.Editor#apply()}).
     *
     * @param name Desired preferences file.
     * @param mode Operating mode.
     *
     * @return The single {@link SharedPreferences} instance that can be used
     *         to retrieve and modify the preference values.
     *
     * @see #MODE_PRIVATE
     */
    public SharedPreferences getSharedPreferences(String name, @PreferencesMode int mode) {
        throw new UnsupportedOperationException(
                "getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;");
    }

    /**
     * Retrieve and hold the contents of the preferences file, returning
     * a SharedPreferences through which you can retrieve and modify its
     * values.  Only one instance of the SharedPreferences object is returned
     * to any callers for the same name, meaning they will see each other's
     * edits as soon as they are made.
     *
     * @param file Desired preferences file. If a preferences file by this name
     * does not exist, it will be created when you retrieve an
     * editor (SharedPreferences.edit()) and then commit changes (Editor.commit()).
     * @param mode Operating mode.
     *
     * @return The single {@link SharedPreferences} instance that can be used
     *         to retrieve and modify the preference values.
     *
     * @see #getSharedPreferencesPath(String)
     * @see #MODE_PRIVATE
     * @removed
     */
    @SuppressWarnings("HiddenAbstractMethod")
    public SharedPreferences getSharedPreferences(File file, @PreferencesMode int mode) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Move an existing shared preferences file from the given source storage
     * context to this context. This is typically used to migrate data between
     * storage locations after an upgrade, such as moving to device protected
     * storage.
     *
     * @param sourceContext The source context which contains the existing
     *            shared preferences to move.
     * @param name The name of the shared preferences file.
     * @return {@code true} if the move was successful or if the shared
     *         preferences didn't exist in the source context, otherwise
     *         {@code false}.
     * @see #createDeviceProtectedStorageContext()
     */
    public boolean moveSharedPreferencesFrom(Context sourceContext, String name) {
        if (Build.VERSION.SDK_INT < 24) {
            throw new NoSuchMethodError(
                    "moveSharedPreferencesFrom(Landroid/content/Context;Ljava/lang/String;)Z");
        }
        return ContextBinding.moveSharedPreferencesFrom(this, sourceContext, name);
    }

    /**
     * Delete an existing shared preferences file.
     *
     * @param name The name (unique in the application package) of the shared
     *            preferences file.
     * @return {@code true} if the shared preferences file was successfully
     *         deleted; else {@code false}.
     * @see #getSharedPreferences(String, int)
     */
    public boolean deleteSharedPreferences(String name) {
        if (Build.VERSION.SDK_INT < 24) {
            throw new NoSuchMethodError("deleteSharedPreferences(Ljava/lang/String;)Z");
        }
        return ContextBinding.deleteSharedPreferences(this, name);
    }

    /** @hide */
    @SuppressWarnings("HiddenAbstractMethod")
    public void reloadSharedPreferences() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Open a private file associated with this Context's application package
     * for reading.
     *
     * @param name The name of the file to open; can not contain path
     *             separators.
     *
     * @return The resulting {@link FileInputStream}.
     *
     * @see #openFileOutput
     * @see #fileList
     * @see #deleteFile
     * @see java.io.FileInputStream#FileInputStream(String)
     */
    public FileInputStream openFileInput(String name)
        throws FileNotFoundException {
        throw new UnsupportedOperationException(
                "openFileInput(Ljava/lang/String;)Ljava/io/FileInputStream;");
    }

    /**
     * Open a private file associated with this Context's application package
     * for writing. Creates the file if it doesn't already exist.
     * <p>
     * No additional permissions are required for the calling app to read or
     * write the returned file.
     *
     * @param name The name of the file to open; can not contain path
     *            separators.
     * @param mode Operating mode.
     * @return The resulting {@link FileOutputStream}.
     * @see #MODE_APPEND
     * @see #MODE_PRIVATE
     * @see #openFileInput
     * @see #fileList
     * @see #deleteFile
     * @see java.io.FileOutputStream#FileOutputStream(String)
     */
    public FileOutputStream openFileOutput(String name, @FileMode int mode)
        throws FileNotFoundException {
        throw new UnsupportedOperationException(
                "openFileOutput(Ljava/lang/String;I)Ljava/io/FileOutputStream;");
    }

    /**
     * Delete the given private file associated with this Context's
     * application package.
     *
     * @param name The name of the file to delete; can not contain path
     *             separators.
     *
     * @return {@code true} if the file was successfully deleted; else
     *         {@code false}.
     *
     * @see #openFileInput
     * @see #openFileOutput
     * @see #fileList
     * @see java.io.File#delete()
     */
    public boolean deleteFile(String name) {
        return ContextBinding.deleteFile(this, name);
    }

    /**
     * Returns the absolute path on the filesystem where a file created with
     * {@link #openFileOutput} is stored.
     * <p>
     * The returned path may change over time if the calling app is moved to an
     * adopted storage device, so only relative paths should be persisted.
     *
     * @param name The name of the file for which you would like to get
     *          its path.
     *
     * @return An absolute path to the given file.
     *
     * @see #openFileOutput
     * @see #getFilesDir
     * @see #getDir
     */
    public File getFileStreamPath(String name) {
        throw new UnsupportedOperationException(
                "getFileStreamPath(Ljava/lang/String;)Ljava/io/File;");
    }

    /**
     * Returns the absolute path on the filesystem where a file created with
     * {@link #getSharedPreferences(String, int)} is stored.
     * <p>
     * The returned path may change over time if the calling app is moved to an
     * adopted storage device, so only relative paths should be persisted.
     *
     * @param name The name of the shared preferences for which you would like
     *            to get a path.
     * @return An absolute path to the given file.
     * @see #getSharedPreferences(String, int)
     * @removed
     */
    @SuppressWarnings("HiddenAbstractMethod")
    public File getSharedPreferencesPath(String name) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Returns the absolute path to the directory on the filesystem where all
     * private files belonging to this app are stored. Apps should not use this
     * path directly; they should instead use {@link #getFilesDir()},
     * {@link #getCacheDir()}, {@link #getDir(String, int)}, or other storage
     * APIs on this class.
     * <p>
     * The returned path may change over time if the calling app is moved to an
     * adopted storage device, so only relative paths should be persisted.
     * <p>
     * No additional permissions are required for the calling app to read or
     * write files under the returned path.
     *
     * @see ApplicationInfo#dataDir
     */
    public File getDataDir() {
        if (Build.VERSION.SDK_INT < 24) {
            throw new NoSuchMethodError("getDataDir()Ljava/io/File;");
        }
        throw new UnsupportedOperationException("getDataDir()Ljava/io/File;");
    }

    /**
     * Returns the absolute path to the directory on the filesystem where files
     * created with {@link #openFileOutput} are stored.
     * <p>
     * The returned path may change over time if the calling app is moved to an
     * adopted storage device, so only relative paths should be persisted.
     * <p>
     * No additional permissions are required for the calling app to read or
     * write files under the returned path.
     *
     * @return The path of the directory holding application files.
     * @see #openFileOutput
     * @see #getFileStreamPath
     * @see #getDir
     */
    public File getFilesDir() {
        throw new UnsupportedOperationException("getFilesDir()Ljava/io/File;");
    }

    /**
     * Returns the absolute path to the directory that is related to the crate on the filesystem.
     * <p>
     *     The crateId require a validated file name. It can't contain any "..", ".",
     *     {@link File#separatorChar} etc..
     * </p>
     * <p>
     * The returned path may change over time if the calling app is moved to an
     * adopted storage device, so only relative paths should be persisted.
     * </p>
     * <p>
     * No additional permissions are required for the calling app to read or
     * write files under the returned path.
     *</p>
     *
     * @param crateId the relative validated file name under {@link Context#getDataDir()}/crates
     * @return the crate directory file.
     * @hide
     */
    @NonNull
    @TestApi
    public File getCrateDir(@NonNull String crateId) {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    /**
     * Returns the absolute path to the directory on the filesystem similar to
     * {@link #getFilesDir()}. The difference is that files placed under this
     * directory will be excluded from automatic backup to remote storage. See
     * {@link android.app.backup.BackupAgent BackupAgent} for a full discussion
     * of the automatic backup mechanism in Android.
     * <p>
     * The returned path may change over time if the calling app is moved to an
     * adopted storage device, so only relative paths should be persisted.
     * <p>
     * No additional permissions are required for the calling app to read or
     * write files under the returned path.
     *
     * @return The path of the directory holding application files that will not
     *         be automatically backed up to remote storage.
     * @see #openFileOutput
     * @see #getFileStreamPath
     * @see #getDir
     * @see android.app.backup.BackupAgent
     */
    public File getNoBackupFilesDir() {
        throw new UnsupportedOperationException("getNoBackupFilesDir()Ljava/io/File;");
    }

    /**
     * Returns the absolute path to the directory on the primary shared/external
     * storage device where the application can place persistent files it owns.
     * These files are internal to the applications, and not typically visible
     * to the user as media.
     * <p>
     * This is like {@link #getFilesDir()} in that these files will be deleted
     * when the application is uninstalled, however there are some important
     * differences:
     * <ul>
     * <li>Shared storage may not always be available, since removable media can
     * be ejected by the user. Media state can be checked using
     * {@link Environment#getExternalStorageState(File)}.
     * <li>There is no security enforced with these files. For example, any
     * application holding
     * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE} can write to
     * these files.
     * </ul>
     * <p>
     * If a shared storage device is emulated (as determined by
     * {@link Environment#isExternalStorageEmulated(File)}), its contents are
     * backed by a private user data partition, which means there is little
     * benefit to storing data here instead of the private directories returned
     * by {@link #getFilesDir()}, etc.
     * <p>
     * Starting in {@link android.os.Build.VERSION_CODES#KITKAT}, no permissions
     * are required to read or write to the returned path; it's always
     * accessible to the calling app. This only applies to paths generated for
     * package name of the calling application. To access paths belonging to
     * other packages,
     * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE} and/or
     * {@link android.Manifest.permission#READ_EXTERNAL_STORAGE} are required.
     * <p>
     * On devices with multiple users (as described by {@link UserManager}),
     * each user has their own isolated shared storage. Applications only have
     * access to the shared storage for the user they're running as.
     * <p>
     * The returned path may change over time if different shared storage media
     * is inserted, so only relative paths should be persisted.
     * <p>
     * Here is an example of typical code to manipulate a file in an
     * application's shared storage:
     * </p>
     * {@sample development/samples/ApiDemos/src/com/example/android/apis/content/ExternalStorage.java
     * private_file}
     * <p>
     * If you supply a non-null <var>type</var> to this function, the returned
     * file will be a path to a sub-directory of the given type. Though these
     * files are not automatically scanned by the media scanner, you can
     * explicitly add them to the media database with
     * {@link android.media.MediaScannerConnection#scanFile(Context, String[], String[], android.media.MediaScannerConnection.OnScanCompletedListener)
     * MediaScannerConnection.scanFile}. Note that this is not the same as
     * {@link android.os.Environment#getExternalStoragePublicDirectory
     * Environment.getExternalStoragePublicDirectory()}, which provides
     * directories of media shared by all applications. The directories returned
     * here are owned by the application, and their contents will be removed
     * when the application is uninstalled. Unlike
     * {@link android.os.Environment#getExternalStoragePublicDirectory
     * Environment.getExternalStoragePublicDirectory()}, the directory returned
     * here will be automatically created for you.
     * <p>
     * Here is an example of typical code to manipulate a picture in an
     * application's shared storage and add it to the media database:
     * </p>
     * {@sample development/samples/ApiDemos/src/com/example/android/apis/content/ExternalStorage.java
     * private_picture}
     *
     * @param type The type of files directory to return. May be {@code null}
     *            for the root of the files directory or one of the following
     *            constants for a subdirectory:
     *            {@link android.os.Environment#DIRECTORY_MUSIC},
     *            {@link android.os.Environment#DIRECTORY_PODCASTS},
     *            {@link android.os.Environment#DIRECTORY_RINGTONES},
     *            {@link android.os.Environment#DIRECTORY_ALARMS},
     *            {@link android.os.Environment#DIRECTORY_NOTIFICATIONS},
     *            {@link android.os.Environment#DIRECTORY_PICTURES}, or
     *            {@link android.os.Environment#DIRECTORY_MOVIES}.
     * @return the absolute path to application-specific directory. May return
     *         {@code null} if shared storage is not currently available.
     * @see #getFilesDir
     * @see #getExternalFilesDirs(String)
     * @see Environment#getExternalStorageState(File)
     * @see Environment#isExternalStorageEmulated(File)
     * @see Environment#isExternalStorageRemovable(File)
     */
    @Nullable
    public File getExternalFilesDir(@Nullable String type) {
        throw new UnsupportedOperationException(
                "getExternalFilesDir(Ljava/lang/String;)Ljava/io/File;");
    }

    /**
     * Returns absolute paths to application-specific directories on all
     * shared/external storage devices where the application can place
     * persistent files it owns. These files are internal to the application,
     * and not typically visible to the user as media.
     * <p>
     * This is like {@link #getFilesDir()} in that these files will be deleted
     * when the application is uninstalled, however there are some important
     * differences:
     * <ul>
     * <li>Shared storage may not always be available, since removable media can
     * be ejected by the user. Media state can be checked using
     * {@link Environment#getExternalStorageState(File)}.
     * <li>There is no security enforced with these files. For example, any
     * application holding
     * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE} can write to
     * these files.
     * </ul>
     * <p>
     * If a shared storage device is emulated (as determined by
     * {@link Environment#isExternalStorageEmulated(File)}), its contents are
     * backed by a private user data partition, which means there is little
     * benefit to storing data here instead of the private directories returned
     * by {@link #getFilesDir()}, etc.
     * <p>
     * Shared storage devices returned here are considered a stable part of the
     * device, including physical media slots under a protective cover. The
     * returned paths do not include transient devices, such as USB flash drives
     * connected to handheld devices.
     * <p>
     * An application may store data on any or all of the returned devices. For
     * example, an app may choose to store large files on the device with the
     * most available space, as measured by {@link StatFs}.
     * <p>
     * No additional permissions are required for the calling app to read or
     * write files under the returned path. Write access outside of these paths
     * on secondary external storage devices is not available.
     * <p>
     * The returned path may change over time if different shared storage media
     * is inserted, so only relative paths should be persisted.
     *
     * @param type The type of files directory to return. May be {@code null}
     *            for the root of the files directory or one of the following
     *            constants for a subdirectory:
     *            {@link android.os.Environment#DIRECTORY_MUSIC},
     *            {@link android.os.Environment#DIRECTORY_PODCASTS},
     *            {@link android.os.Environment#DIRECTORY_RINGTONES},
     *            {@link android.os.Environment#DIRECTORY_ALARMS},
     *            {@link android.os.Environment#DIRECTORY_NOTIFICATIONS},
     *            {@link android.os.Environment#DIRECTORY_PICTURES}, or
     *            {@link android.os.Environment#DIRECTORY_MOVIES}.
     * @return the absolute paths to application-specific directories. Some
     *         individual paths may be {@code null} if that shared storage is
     *         not currently available. The first path returned is the same as
     *         {@link #getExternalFilesDir(String)}.
     * @see #getExternalFilesDir(String)
     * @see Environment#getExternalStorageState(File)
     * @see Environment#isExternalStorageEmulated(File)
     * @see Environment#isExternalStorageRemovable(File)
     */
    public File[] getExternalFilesDirs(String type) {
        throw new UnsupportedOperationException(
                "getExternalFilesDirs(Ljava/lang/String;)[Ljava/io/File;");
    }

    /**
     * Return the primary shared/external storage directory where this
     * application's OBB files (if there are any) can be found. Note if the
     * application does not have any OBB files, this directory may not exist.
     * <p>
     * This is like {@link #getFilesDir()} in that these files will be deleted
     * when the application is uninstalled, however there are some important
     * differences:
     * <ul>
     * <li>Shared storage may not always be available, since removable media can
     * be ejected by the user. Media state can be checked using
     * {@link Environment#getExternalStorageState(File)}.
     * <li>There is no security enforced with these files. For example, any
     * application holding
     * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE} can write to
     * these files.
     * </ul>
     * <p>
     * Starting in {@link android.os.Build.VERSION_CODES#KITKAT}, no permissions
     * are required to read or write to the path that this method returns.
     * However, starting from {@link android.os.Build.VERSION_CODES#M},
     * to read the OBB expansion files, you must declare the
     * {@link android.Manifest.permission#READ_EXTERNAL_STORAGE} permission in the app manifest and ask for
     * permission at runtime as follows:
     * </p>
     * <p>
     * {@code <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"
     * android:maxSdkVersion="23" />}
     * </p>
     * <p>
     * Starting from {@link android.os.Build.VERSION_CODES#N},
     * {@link android.Manifest.permission#READ_EXTERNAL_STORAGE}
     * permission is not required, so don’t ask for this
     * permission at runtime. To handle both cases, your app must first try to read the OBB file,
     * and if it fails, you must request
     * {@link android.Manifest.permission#READ_EXTERNAL_STORAGE} permission at runtime.
     * </p>
     *
     * <p>
     * The following code snippet shows how to do this:
     * </p>
     *
     * <pre>
     * File obb = new File(obb_filename);
     * boolean open_failed = false;
     *
     * try {
     *     BufferedReader br = new BufferedReader(new FileReader(obb));
     *     open_failed = false;
     *     ReadObbFile(br);
     * } catch (IOException e) {
     *     open_failed = true;
     * }
     *
     * if (open_failed) {
     *     // request READ_EXTERNAL_STORAGE permission before reading OBB file
     *     ReadObbFileWithPermission();
     * }
     * </pre>
     *
     * On devices with multiple users (as described by {@link UserManager}),
     * multiple users may share the same OBB storage location. Applications
     * should ensure that multiple instances running under different users don't
     * interfere with each other.
     *
     * @return the absolute path to application-specific directory. May return
     *         {@code null} if shared storage is not currently available.
     * @see #getObbDirs()
     * @see Environment#getExternalStorageState(File)
     * @see Environment#isExternalStorageEmulated(File)
     * @see Environment#isExternalStorageRemovable(File)
     */
    public File getObbDir() {
        throw new UnsupportedOperationException("getObbDir()Ljava/io/File;");
    }

    /**
     * Returns absolute paths to application-specific directories on all
     * shared/external storage devices where the application's OBB files (if
     * there are any) can be found. Note if the application does not have any
     * OBB files, these directories may not exist.
     * <p>
     * This is like {@link #getFilesDir()} in that these files will be deleted
     * when the application is uninstalled, however there are some important
     * differences:
     * <ul>
     * <li>Shared storage may not always be available, since removable media can
     * be ejected by the user. Media state can be checked using
     * {@link Environment#getExternalStorageState(File)}.
     * <li>There is no security enforced with these files. For example, any
     * application holding
     * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE} can write to
     * these files.
     * </ul>
     * <p>
     * Shared storage devices returned here are considered a stable part of the
     * device, including physical media slots under a protective cover. The
     * returned paths do not include transient devices, such as USB flash drives
     * connected to handheld devices.
     * <p>
     * An application may store data on any or all of the returned devices. For
     * example, an app may choose to store large files on the device with the
     * most available space, as measured by {@link StatFs}.
     * <p>
     * No additional permissions are required for the calling app to read or
     * write files under the returned path. Write access outside of these paths
     * on secondary external storage devices is not available.
     *
     * @return the absolute paths to application-specific directories. Some
     *         individual paths may be {@code null} if that shared storage is
     *         not currently available. The first path returned is the same as
     *         {@link #getObbDir()}
     * @see #getObbDir()
     * @see Environment#getExternalStorageState(File)
     * @see Environment#isExternalStorageEmulated(File)
     * @see Environment#isExternalStorageRemovable(File)
     */
    public File[] getObbDirs() {
        throw new UnsupportedOperationException("getObbDirs()[Ljava/io/File;");
    }

    /**
     * Returns the absolute path to the application specific cache directory on
     * the filesystem.
     * <p>
     * The system will automatically delete files in this directory as disk
     * space is needed elsewhere on the device. The system will always delete
     * older files first, as reported by {@link File#lastModified()}. If
     * desired, you can exert more control over how files are deleted using
     * {@link StorageManager#setCacheBehaviorGroup(File, boolean)} and
     * {@link StorageManager#setCacheBehaviorTombstone(File, boolean)}.
     * <p>
     * Apps are strongly encouraged to keep their usage of cache space below the
     * quota returned by
     * {@link StorageManager#getCacheQuotaBytes(java.util.UUID)}. If your app
     * goes above this quota, your cached files will be some of the first to be
     * deleted when additional disk space is needed. Conversely, if your app
     * stays under this quota, your cached files will be some of the last to be
     * deleted when additional disk space is needed.
     * <p>
     * Note that your cache quota will change over time depending on how
     * frequently the user interacts with your app, and depending on how much
     * system-wide disk space is used.
     * <p>
     * The returned path may change over time if the calling app is moved to an
     * adopted storage device, so only relative paths should be persisted.
     * <p>
     * Apps require no extra permissions to read or write to the returned path,
     * since this path lives in their private storage.
     *
     * @return The path of the directory holding application cache files.
     * @see #openFileOutput
     * @see #getFileStreamPath
     * @see #getDir
     * @see #getExternalCacheDir
     */
    public File getCacheDir() {
        throw new UnsupportedOperationException("getCacheDir()Ljava/io/File;");
    }

    /**
     * Returns the absolute path to the application specific cache directory on
     * the filesystem designed for storing cached code.
     * <p>
     * The system will delete any files stored in this location both when your
     * specific application is upgraded, and when the entire platform is
     * upgraded.
     * <p>
     * This location is optimal for storing compiled or optimized code generated
     * by your application at runtime.
     * <p>
     * The returned path may change over time if the calling app is moved to an
     * adopted storage device, so only relative paths should be persisted.
     * <p>
     * Apps require no extra permissions to read or write to the returned path,
     * since this path lives in their private storage.
     *
     * @return The path of the directory holding application code cache files.
     */
    public File getCodeCacheDir() {
        throw new UnsupportedOperationException("getCodeCacheDir()Ljava/io/File;");
    }

    /**
     * Returns absolute path to application-specific directory on the primary
     * shared/external storage device where the application can place cache
     * files it owns. These files are internal to the application, and not
     * typically visible to the user as media.
     * <p>
     * This is like {@link #getCacheDir()} in that these files will be deleted
     * when the application is uninstalled, however there are some important
     * differences:
     * <ul>
     * <li>The platform does not always monitor the space available in shared
     * storage, and thus may not automatically delete these files. Apps should
     * always manage the maximum space used in this location. Currently the only
     * time files here will be deleted by the platform is when running on
     * {@link android.os.Build.VERSION_CODES#JELLY_BEAN_MR1} or later and
     * {@link Environment#isExternalStorageEmulated(File)} returns true.
     * <li>Shared storage may not always be available, since removable media can
     * be ejected by the user. Media state can be checked using
     * {@link Environment#getExternalStorageState(File)}.
     * <li>There is no security enforced with these files. For example, any
     * application holding
     * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE} can write to
     * these files.
     * </ul>
     * <p>
     * If a shared storage device is emulated (as determined by
     * {@link Environment#isExternalStorageEmulated(File)}), its contents are
     * backed by a private user data partition, which means there is little
     * benefit to storing data here instead of the private directory returned by
     * {@link #getCacheDir()}.
     * <p>
     * Starting in {@link android.os.Build.VERSION_CODES#KITKAT}, no permissions
     * are required to read or write to the returned path; it's always
     * accessible to the calling app. This only applies to paths generated for
     * package name of the calling application. To access paths belonging to
     * other packages,
     * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE} and/or
     * {@link android.Manifest.permission#READ_EXTERNAL_STORAGE} are required.
     * <p>
     * On devices with multiple users (as described by {@link UserManager}),
     * each user has their own isolated shared storage. Applications only have
     * access to the shared storage for the user they're running as.
     * <p>
     * The returned path may change over time if different shared storage media
     * is inserted, so only relative paths should be persisted.
     *
     * @return the absolute path to application-specific directory. May return
     *         {@code null} if shared storage is not currently available.
     * @see #getCacheDir
     * @see #getExternalCacheDirs()
     * @see Environment#getExternalStorageState(File)
     * @see Environment#isExternalStorageEmulated(File)
     * @see Environment#isExternalStorageRemovable(File)
     */
    @Nullable
    public File getExternalCacheDir() {
        throw new UnsupportedOperationException("getExternalCacheDir()Ljava/io/File;");
    }

    /**
     * Returns absolute path to application-specific directory in the preloaded cache.
     * <p>Files stored in the cache directory can be deleted when the device runs low on storage.
     * There is no guarantee when these files will be deleted.
     * @hide
     */
    @SuppressWarnings("HiddenAbstractMethod")
    @Nullable
    @SystemApi
    public File getPreloadsFileCache() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Returns absolute paths to application-specific directories on all
     * shared/external storage devices where the application can place cache
     * files it owns. These files are internal to the application, and not
     * typically visible to the user as media.
     * <p>
     * This is like {@link #getCacheDir()} in that these files will be deleted
     * when the application is uninstalled, however there are some important
     * differences:
     * <ul>
     * <li>The platform does not always monitor the space available in shared
     * storage, and thus may not automatically delete these files. Apps should
     * always manage the maximum space used in this location. Currently the only
     * time files here will be deleted by the platform is when running on
     * {@link android.os.Build.VERSION_CODES#JELLY_BEAN_MR1} or later and
     * {@link Environment#isExternalStorageEmulated(File)} returns true.
     * <li>Shared storage may not always be available, since removable media can
     * be ejected by the user. Media state can be checked using
     * {@link Environment#getExternalStorageState(File)}.
     * <li>There is no security enforced with these files. For example, any
     * application holding
     * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE} can write to
     * these files.
     * </ul>
     * <p>
     * If a shared storage device is emulated (as determined by
     * {@link Environment#isExternalStorageEmulated(File)}), its contents are
     * backed by a private user data partition, which means there is little
     * benefit to storing data here instead of the private directory returned by
     * {@link #getCacheDir()}.
     * <p>
     * Shared storage devices returned here are considered a stable part of the
     * device, including physical media slots under a protective cover. The
     * returned paths do not include transient devices, such as USB flash drives
     * connected to handheld devices.
     * <p>
     * An application may store data on any or all of the returned devices. For
     * example, an app may choose to store large files on the device with the
     * most available space, as measured by {@link StatFs}.
     * <p>
     * No additional permissions are required for the calling app to read or
     * write files under the returned path. Write access outside of these paths
     * on secondary external storage devices is not available.
     * <p>
     * The returned paths may change over time if different shared storage media
     * is inserted, so only relative paths should be persisted.
     *
     * @return the absolute paths to application-specific directories. Some
     *         individual paths may be {@code null} if that shared storage is
     *         not currently available. The first path returned is the same as
     *         {@link #getExternalCacheDir()}.
     * @see #getExternalCacheDir()
     * @see Environment#getExternalStorageState(File)
     * @see Environment#isExternalStorageEmulated(File)
     * @see Environment#isExternalStorageRemovable(File)
     */
    public File[] getExternalCacheDirs() {
        throw new UnsupportedOperationException("getExternalCacheDirs()[Ljava/io/File;");
    }

    /**
     * Returns absolute paths to application-specific directories on all
     * shared/external storage devices where the application can place media
     * files. These files are scanned and made available to other apps through
     * {@link MediaStore}.
     * <p>
     * This is like {@link #getExternalFilesDirs} in that these files will be
     * deleted when the application is uninstalled, however there are some
     * important differences:
     * <ul>
     * <li>Shared storage may not always be available, since removable media can
     * be ejected by the user. Media state can be checked using
     * {@link Environment#getExternalStorageState(File)}.
     * <li>There is no security enforced with these files. For example, any
     * application holding
     * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE} can write to
     * these files.
     * </ul>
     * <p>
     * Shared storage devices returned here are considered a stable part of the
     * device, including physical media slots under a protective cover. The
     * returned paths do not include transient devices, such as USB flash drives
     * connected to handheld devices.
     * <p>
     * An application may store data on any or all of the returned devices. For
     * example, an app may choose to store large files on the device with the
     * most available space, as measured by {@link StatFs}.
     * <p>
     * No additional permissions are required for the calling app to read or
     * write files under the returned path. Write access outside of these paths
     * on secondary external storage devices is not available.
     * <p>
     * The returned paths may change over time if different shared storage media
     * is inserted, so only relative paths should be persisted.
     *
     * @return the absolute paths to application-specific directories. Some
     *         individual paths may be {@code null} if that shared storage is
     *         not currently available.
     * @see Environment#getExternalStorageState(File)
     * @see Environment#isExternalStorageEmulated(File)
     * @see Environment#isExternalStorageRemovable(File)
     * @deprecated These directories still exist and are scanned, but developers
     *             are encouraged to migrate to inserting content into a
     *             {@link MediaStore} collection directly, as any app can
     *             contribute new media to {@link MediaStore} with no
     *             permissions required, starting in
     *             {@link android.os.Build.VERSION_CODES#Q}.
     */
    @Deprecated
    public File[] getExternalMediaDirs() {
        throw new UnsupportedOperationException("getExternalMediaDirs()[Ljava/io/File;");
    }

    /**
     * Returns an array of strings naming the private files associated with
     * this Context's application package.
     *
     * @return Array of strings naming the private files.
     *
     * @see #openFileInput
     * @see #openFileOutput
     * @see #deleteFile
     */
    public String[] fileList() {
        throw new UnsupportedOperationException("fileList()[Ljava/lang/String;");
    }

    /**
     * Retrieve, creating if needed, a new directory in which the application
     * can place its own custom data files.  You can use the returned File
     * object to create and access files in this directory.  Note that files
     * created through a File object will only be accessible by your own
     * application; you can only set the mode of the entire directory, not
     * of individual files.
     * <p>
     * The returned path may change over time if the calling app is moved to an
     * adopted storage device, so only relative paths should be persisted.
     * <p>
     * Apps require no extra permissions to read or write to the returned path,
     * since this path lives in their private storage.
     *
     * @param name Name of the directory to retrieve.  This is a directory
     * that is created as part of your application data.
     * @param mode Operating mode.
     *
     * @return A {@link File} object for the requested directory.  The directory
     * will have been created if it does not already exist.
     *
     * @see #openFileOutput(String, int)
     */
    public File getDir(String name, @FileMode int mode) {
        throw new UnsupportedOperationException("getDir(Ljava/lang/String;I)Ljava/io/File;");
    }

    /**
     * Move an existing database file from the given source storage context to
     * this context. This is typically used to migrate data between storage
     * locations after an upgrade, such as migrating to device protected
     * storage.
     * <p>
     * The database must be closed before being moved.
     *
     * @param sourceContext The source context which contains the existing
     *            database to move.
     * @param name The name of the database file.
     * @return {@code true} if the move was successful or if the database didn't
     *         exist in the source context, otherwise {@code false}.
     * @see #createDeviceProtectedStorageContext()
     */
    public boolean moveDatabaseFrom(Context sourceContext, String name) {
        if (Build.VERSION.SDK_INT < 24) {
            throw new NoSuchMethodError(
                    "moveDatabaseFrom(Landroid/content/Context;Ljava/lang/String;)Z");
        }
        return ContextBinding.moveDatabaseFrom(this, sourceContext, name);
    }

    /**
     * Delete an existing private SQLiteDatabase associated with this Context's
     * application package.
     *
     * @param name The name (unique in the application package) of the
     *             database.
     *
     * @return {@code true} if the database was successfully deleted; else {@code false}.
     *
     * @see #openOrCreateDatabase
     */
    public boolean deleteDatabase(String name) {
        return ContextBinding.deleteDatabase(this, name);
    }

    /**
     * Returns the absolute path on the filesystem where a database created with
     * {@link #openOrCreateDatabase} is stored.
     * <p>
     * The returned path may change over time if the calling app is moved to an
     * adopted storage device, so only relative paths should be persisted.
     *
     * @param name The name of the database for which you would like to get
     *          its path.
     *
     * @return An absolute path to the given database.
     *
     * @see #openOrCreateDatabase
     */
    public File getDatabasePath(String name) {
        throw new UnsupportedOperationException(
                "getDatabasePath(Ljava/lang/String;)Ljava/io/File;");
    }

    /**
     * Returns an array of strings naming the private databases associated with
     * this Context's application package.
     *
     * @return Array of strings naming the private databases.
     *
     * @see #openOrCreateDatabase
     * @see #deleteDatabase
     */
    public String[] databaseList() {
        throw new UnsupportedOperationException("databaseList()[Ljava/lang/String;");
    }

    /**
     * @deprecated Use {@link android.app.WallpaperManager#getDesiredMinimumWidth()
     * WallpaperManager.getDesiredMinimumWidth()} instead.
     */
    @Deprecated
    public int getWallpaperDesiredMinimumWidth() {
        return ContextBinding.getWallpaperDesiredMinimumWidth(this);
    }

    /**
     * @deprecated Use {@link android.app.WallpaperManager#getDesiredMinimumHeight()
     * WallpaperManager.getDesiredMinimumHeight()} instead.
     */
    @Deprecated
    public int getWallpaperDesiredMinimumHeight() {
        return ContextBinding.getWallpaperDesiredMinimumHeight(this);
    }

    /**
     * @deprecated Use {@link android.app.WallpaperManager#setStream(InputStream)
     * WallpaperManager.set()} instead.
     * <p>This method requires the caller to hold the permission
     * {@link android.Manifest.permission#SET_WALLPAPER}.
     */
    @Deprecated
    public void setWallpaper(InputStream data) throws IOException {
        throw new UnsupportedOperationException("setWallpaper(Ljava/io/InputStream;)V");
    }

    /**
     * @deprecated Use {@link android.app.WallpaperManager#clear
     * WallpaperManager.clear()} instead.
     * <p>This method requires the caller to hold the permission
     * {@link android.Manifest.permission#SET_WALLPAPER}.
     */
    @Deprecated
    public void clearWallpaper() throws IOException {
        ContextBinding.clearWallpaper(this);
    }

    /**
     * Identifies whether this Context instance will be able to process calls to
     * {@link #startActivityForResult(String, Intent, int, Bundle)}.
     * @hide
     */
    @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
    public boolean canStartActivityForResult() {
        return false;
    }

    /** @hide */
    @StringDef(suffix = { "_SERVICE" }, value = {
            POWER_SERVICE,
            //@hide: POWER_STATS_SERVICE,
            WINDOW_SERVICE,
            LAYOUT_INFLATER_SERVICE,
            ACCOUNT_SERVICE,
            ACTIVITY_SERVICE,
            ALARM_SERVICE,
            NOTIFICATION_SERVICE,
            ACCESSIBILITY_SERVICE,
            CAPTIONING_SERVICE,
            KEYGUARD_SERVICE,
            LOCATION_SERVICE,
            HEALTHCONNECT_SERVICE,
            //@hide: COUNTRY_DETECTOR,
            SEARCH_SERVICE,
            SENSOR_SERVICE,
            SENSOR_PRIVACY_SERVICE,
            STORAGE_SERVICE,
            STORAGE_STATS_SERVICE,
            WALLPAPER_SERVICE,
            VIBRATOR_MANAGER_SERVICE,
            VIBRATOR_SERVICE,
            //@hide: STATUS_BAR_SERVICE,
            THREAD_NETWORK_SERVICE,
            CONNECTIVITY_SERVICE,
            TETHERING_SERVICE,
            PAC_PROXY_SERVICE,
            VCN_MANAGEMENT_SERVICE,
            //@hide: IP_MEMORY_STORE_SERVICE,
            IPSEC_SERVICE,
            VPN_MANAGEMENT_SERVICE,
            TEST_NETWORK_SERVICE,
            //@hide: UPDATE_LOCK_SERVICE,
            //@hide: NETWORKMANAGEMENT_SERVICE,
            NETWORK_STATS_SERVICE,
            //@hide: NETWORK_POLICY_SERVICE,
            WIFI_SERVICE,
            WIFI_AWARE_SERVICE,
            WIFI_P2P_SERVICE,
            WIFI_SCANNING_SERVICE,
            //@hide: LOWPAN_SERVICE,
            //@hide: WIFI_RTT_SERVICE,
            //@hide: ETHERNET_SERVICE,
            WIFI_RTT_RANGING_SERVICE,
            WIFI_USD_SERVICE,
            NSD_SERVICE,
            AUDIO_SERVICE,
            AUDIO_DEVICE_VOLUME_SERVICE,
            AUTH_SERVICE,
            FINGERPRINT_SERVICE,
            //@hide: FACE_SERVICE,
            BIOMETRIC_SERVICE,
            AUTHENTICATION_POLICY_SERVICE,
            MEDIA_ROUTER_SERVICE,
            TELEPHONY_SERVICE,
            TELEPHONY_SUBSCRIPTION_SERVICE,
            CARRIER_CONFIG_SERVICE,
            EUICC_SERVICE,
            //@hide: MMS_SERVICE,
            TELECOM_SERVICE,
            CLIPBOARD_SERVICE,
            INPUT_METHOD_SERVICE,
            TEXT_SERVICES_MANAGER_SERVICE,
            TEXT_CLASSIFICATION_SERVICE,
            APPWIDGET_SERVICE,
            //@hide: VOICE_INTERACTION_MANAGER_SERVICE,
            //@hide: BACKUP_SERVICE,
            REBOOT_READINESS_SERVICE,
            ROLLBACK_SERVICE,
            DROPBOX_SERVICE,
            //@hide: DEVICE_IDLE_CONTROLLER,
            //@hide: POWER_WHITELIST_MANAGER,
            DEVICE_POLICY_SERVICE,
            UI_MODE_SERVICE,
            DOWNLOAD_SERVICE,
            NFC_SERVICE,
            BLUETOOTH_SERVICE,
            //@hide: SIP_SERVICE,
            USB_SERVICE,
            LAUNCHER_APPS_SERVICE,
            //@hide: SERIAL_SERVICE,
            //@hide: HDMI_CONTROL_SERVICE,
            INPUT_SERVICE,
            DISPLAY_SERVICE,
            //@hide COLOR_DISPLAY_SERVICE,
            USER_SERVICE,
            RESTRICTIONS_SERVICE,
            APP_OPS_SERVICE,
            ROLE_SERVICE,
            //@hide ROLE_CONTROLLER_SERVICE,
            CAMERA_SERVICE,
            //@hide: PLATFORM_COMPAT_SERVICE,
            //@hide: PLATFORM_COMPAT_NATIVE_SERVICE,
            PRINT_SERVICE,
            CONSUMER_IR_SERVICE,
            //@hide: TRUST_SERVICE,
            TV_INTERACTIVE_APP_SERVICE,
            TV_INPUT_SERVICE,
            //@hide: TV_TUNER_RESOURCE_MGR_SERVICE,
            //@hide: NETWORK_SCORE_SERVICE,
            USAGE_STATS_SERVICE,
            MEDIA_SESSION_SERVICE,
            MEDIA_COMMUNICATION_SERVICE,
            BATTERY_SERVICE,
            JOB_SCHEDULER_SERVICE,
            PERSISTENT_DATA_BLOCK_SERVICE,
            //@hide: OEM_LOCK_SERVICE,
            MEDIA_PROJECTION_SERVICE,
            MIDI_SERVICE,
            RADIO_SERVICE,
            HARDWARE_PROPERTIES_SERVICE,
            //@hide: SOUND_TRIGGER_SERVICE,
            SHORTCUT_SERVICE,
            //@hide: CONTEXTHUB_SERVICE,
            SYSTEM_HEALTH_SERVICE,
            //@hide: INCIDENT_SERVICE,
            //@hide: INCIDENT_COMPANION_SERVICE,
            //@hide: STATS_COMPANION_SERVICE,
            COMPANION_DEVICE_SERVICE,
            VIRTUAL_DEVICE_SERVICE,
            CROSS_PROFILE_APPS_SERVICE,
            //@hide: SYSTEM_UPDATE_SERVICE,
            //@hide: TIME_DETECTOR_SERVICE,
            //@hide: TIME_ZONE_DETECTOR_SERVICE,
            PERMISSION_SERVICE,
            LIGHTS_SERVICE,
            LOCALE_SERVICE,
            //@hide: PEOPLE_SERVICE,
            //@hide: DEVICE_STATE_SERVICE,
            //@hide: SPEECH_RECOGNITION_SERVICE,
            UWB_SERVICE,
            MEDIA_METRICS_SERVICE,
            //@hide: ATTESTATION_VERIFICATION_SERVICE,
            //@hide: SAFETY_CENTER_SERVICE,
            DISPLAY_HASH_SERVICE,
            CREDENTIAL_SERVICE,
            DEVICE_LOCK_SERVICE,
            VIRTUALIZATION_SERVICE,
            GRAMMATICAL_INFLECTION_SERVICE,
            SECURITY_STATE_SERVICE,
           //@hide: ECM_ENHANCED_CONFIRMATION_SERVICE,
            CONTACT_KEYS_SERVICE,
            RANGING_SERVICE,
            MEDIA_QUALITY_SERVICE,
            ADVANCED_PROTECTION_SERVICE,

    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface ServiceName {}

    /**
     * Return the handle to a system-level service by name. The class of the
     * returned object varies by the requested name. Currently available names
     * are:
     *
     * <dl>
     *  <dt> {@link #WINDOW_SERVICE} ("window")
     *  <dd> The top-level window manager in which you can place custom
     *  windows.  The returned object is a {@link android.view.WindowManager}. Must only be obtained
     *  from a visual context such as Activity or a Context created with
     *  {@link #createWindowContext(int, Bundle)}, which are adjusted to the configuration and
     *  visual bounds of an area on screen.
     *  <dt> {@link #LAYOUT_INFLATER_SERVICE} ("layout_inflater")
     *  <dd> A {@link android.view.LayoutInflater} for inflating layout resources
     *  in this context. Must only be obtained from a visual context such as Activity or a Context
     *  created with {@link #createWindowContext(int, Bundle)}, which are adjusted to the
     *  configuration and visual bounds of an area on screen.
     *  <dt> {@link #ACTIVITY_SERVICE} ("activity")
     *  <dd> A {@link android.app.ActivityManager} for interacting with the
     *  global activity state of the system.
     *  <dt> {@link #WALLPAPER_SERVICE} ("wallpaper")
     *  <dd> A {@link android.service.wallpaper.WallpaperService} for accessing wallpapers in this
     *  context. Must only be obtained from a visual context such as Activity or a Context created
     *  with {@link #createWindowContext(int, Bundle)}, which are adjusted to the configuration and
     *  visual bounds of an area on screen.
     *  <dt> {@link #POWER_SERVICE} ("power")
     *  <dd> A {@link android.os.PowerManager} for controlling power
     *  management.
     *  <dt> {@link #ALARM_SERVICE} ("alarm")
     *  <dd> A {@link android.app.AlarmManager} for receiving intents at the
     *  time of your choosing.
     *  <dt> {@link #NOTIFICATION_SERVICE} ("notification")
     *  <dd> A {@link android.app.NotificationManager} for informing the user
     *   of background events.
     *  <dt> {@link #KEYGUARD_SERVICE} ("keyguard")
     *  <dd> A {@link android.app.KeyguardManager} for controlling keyguard.
     *  <dt> {@link #LOCATION_SERVICE} ("location")
     *  <dd> A {@link android.location.LocationManager} for controlling location
     *   (e.g., GPS) updates.
     *  <dt> {@link #SEARCH_SERVICE} ("search")
     *  <dd> A {@link android.app.SearchManager} for handling search.
     *  <dt> {@link #VIBRATOR_MANAGER_SERVICE} ("vibrator_manager")
     *  <dd> A {@link android.os.VibratorManager} for accessing the device vibrators, interacting
     *  with individual ones and playing synchronized effects on multiple vibrators.
     *  <dt> {@link #VIBRATOR_SERVICE} ("vibrator")
     *  <dd> A {@link android.os.Vibrator} for interacting with the vibrator hardware.
     *  <dt> {@link #CONNECTIVITY_SERVICE} ("connectivity")
     *  <dd> A {@link android.net.ConnectivityManager ConnectivityManager} for
     *  handling management of network connections.
     *  <dt> {@link #IPSEC_SERVICE} ("ipsec")
     *  <dd> A {@link android.net.IpSecManager IpSecManager} for managing IPSec on
     *  sockets and networks.
     *  <dt> {@link #WIFI_SERVICE} ("wifi")
     *  <dd> A {@link android.net.wifi.WifiManager WifiManager} for management of Wi-Fi
     *  connectivity.  On releases before Android 7, it should only be obtained from an application
     *  context, and not from any other derived context to avoid memory leaks within the calling
     *  process.
     *  <dt> {@link #WIFI_AWARE_SERVICE} ("wifiaware")
     *  <dd> A {@link android.net.wifi.aware.WifiAwareManager WifiAwareManager} for management of
     * Wi-Fi Aware discovery and connectivity.
     *  <dt> {@link #WIFI_P2P_SERVICE} ("wifip2p")
     *  <dd> A {@link android.net.wifi.p2p.WifiP2pManager WifiP2pManager} for management of
     * Wi-Fi Direct connectivity.
     * <dt> {@link #INPUT_METHOD_SERVICE} ("input_method")
     * <dd> An {@link android.view.inputmethod.InputMethodManager InputMethodManager}
     * for management of input methods.
     * <dt> {@link #UI_MODE_SERVICE} ("uimode")
     * <dd> An {@link android.app.UiModeManager} for controlling UI modes.
     * <dt> {@link #DOWNLOAD_SERVICE} ("download")
     * <dd> A {@link android.app.DownloadManager} for requesting HTTP downloads
     * <dt> {@link #BATTERY_SERVICE} ("batterymanager")
     * <dd> A {@link android.os.BatteryManager} for managing battery state
     * <dt> {@link #JOB_SCHEDULER_SERVICE} ("taskmanager")
     * <dd>  A {@link android.app.job.JobScheduler} for managing scheduled tasks
     * <dt> {@link #NETWORK_STATS_SERVICE} ("netstats")
     * <dd> A {@link android.app.usage.NetworkStatsManager NetworkStatsManager} for querying network
     * usage statistics.
     * <dt> {@link #HARDWARE_PROPERTIES_SERVICE} ("hardware_properties")
     * <dd> A {@link android.os.HardwarePropertiesManager} for accessing hardware properties.
     * <dt> {@link #DOMAIN_VERIFICATION_SERVICE} ("domain_verification")
     * <dd> A {@link android.content.pm.verify.domain.DomainVerificationManager} for accessing
     * web domain approval state.
     * <dt> {@link #DISPLAY_HASH_SERVICE} ("display_hash")
     * <dd> A {@link android.view.displayhash.DisplayHashManager} for management of display hashes.
     * <dt> {@link #AUTHENTICATION_POLICY_SERVICE} ("authentication_policy")
     * <dd> A {@link android.security.authenticationpolicy.AuthenticationPolicyManager}
     * for managing authentication related policies on the device.
     * </dl>
     *
     * <p>Note:  System services obtained via this API may be closely associated with
     * the Context in which they are obtained from.  In general, do not share the
     * service objects between various different contexts (Activities, Applications,
     * Services, Providers, etc.)
     *
     * <p>Note: Instant apps, for which {@link PackageManager#isInstantApp()} returns true,
     * don't have access to the following system services: {@link #DEVICE_POLICY_SERVICE},
     * {@link #FINGERPRINT_SERVICE}, {@link #KEYGUARD_SERVICE}, {@link #SHORTCUT_SERVICE},
     * {@link #USB_SERVICE}, {@link #WALLPAPER_SERVICE}, {@link #WIFI_P2P_SERVICE},
     * {@link #WIFI_SERVICE}, {@link #WIFI_AWARE_SERVICE}. For these services this method will
     * return <code>null</code>.  Generally, if you are running as an instant app you should always
     * check whether the result of this method is {@code null}.
     *
     * <p>Note: When implementing this method, keep in mind that new services can be added on newer
     * Android releases, so if you're looking for just the explicit names mentioned above, make sure
     * to return {@code null} when you don't recognize the name &mdash; if you throw a
     * {@link RuntimeException} exception instead, your app might break on new Android releases.
     *
     * @param name The name of the desired service.
     *
     * @return The service or {@code null} if the name does not exist.
     *
     * @see #WINDOW_SERVICE
     * @see android.view.WindowManager
     * @see #LAYOUT_INFLATER_SERVICE
     * @see android.view.LayoutInflater
     * @see #ACTIVITY_SERVICE
     * @see android.app.ActivityManager
     * @see #POWER_SERVICE
     * @see android.os.PowerManager
     * @see #ALARM_SERVICE
     * @see android.app.AlarmManager
     * @see #NOTIFICATION_SERVICE
     * @see android.app.NotificationManager
     * @see #KEYGUARD_SERVICE
     * @see android.app.KeyguardManager
     * @see #LOCATION_SERVICE
     * @see android.location.LocationManager
     * @see #SEARCH_SERVICE
     * @see android.app.SearchManager
     * @see #SENSOR_SERVICE
     * @see android.hardware.SensorManager
     * @see #STORAGE_SERVICE
     * @see android.os.storage.StorageManager
     * @see #VIBRATOR_MANAGER_SERVICE
     * @see android.os.VibratorManager
     * @see #VIBRATOR_SERVICE
     * @see android.os.Vibrator
     * @see #CONNECTIVITY_SERVICE
     * @see android.net.ConnectivityManager
     * @see #WIFI_SERVICE
     * @see android.net.wifi.WifiManager
     * @see #AUDIO_SERVICE
     * @see android.media.AudioManager
     * @see #MEDIA_ROUTER_SERVICE
     * @see android.media.MediaRouter
     * @see #TELEPHONY_SERVICE
     * @see android.telephony.TelephonyManager
     * @see #TELEPHONY_SUBSCRIPTION_SERVICE
     * @see android.telephony.SubscriptionManager
     * @see #CARRIER_CONFIG_SERVICE
     * @see android.telephony.CarrierConfigManager
     * @see #EUICC_SERVICE
     * @see android.telephony.euicc.EuiccManager
     * @see #INPUT_METHOD_SERVICE
     * @see android.view.inputmethod.InputMethodManager
     * @see #UI_MODE_SERVICE
     * @see android.app.UiModeManager
     * @see #DOWNLOAD_SERVICE
     * @see android.app.DownloadManager
     * @see #BATTERY_SERVICE
     * @see android.os.BatteryManager
     * @see #JOB_SCHEDULER_SERVICE
     * @see android.app.job.JobScheduler
     * @see #NETWORK_STATS_SERVICE
     * @see android.app.usage.NetworkStatsManager
     * @see android.os.HardwarePropertiesManager
     * @see #HARDWARE_PROPERTIES_SERVICE
     * @see #DOMAIN_VERIFICATION_SERVICE
     * @see android.content.pm.verify.domain.DomainVerificationManager
     * @see #DISPLAY_HASH_SERVICE
     * @see android.view.displayhash.DisplayHashManager
     * @see #AUTHENTICATION_POLICY_SERVICE
     * @see android.security.authenticationpolicy.AuthenticationPolicyManager
     */
    public Object getSystemService(@ServiceName @NonNull String name) {
        throw new UnsupportedOperationException(
                "getSystemService(Ljava/lang/String;)Ljava/lang/Object;");
    }

    /**
     * Return the handle to a system-level service by class.
     * <p>
     * Currently available classes are:
     * {@link android.view.WindowManager}, {@link android.view.LayoutInflater},
     * {@link android.app.ActivityManager}, {@link android.os.PowerManager},
     * {@link android.app.AlarmManager}, {@link android.app.NotificationManager},
     * {@link android.app.KeyguardManager}, {@link android.location.LocationManager},
     * {@link android.app.SearchManager}, {@link android.os.Vibrator},
     * {@link android.net.ConnectivityManager},
     * {@link android.net.wifi.WifiManager},
     * {@link android.media.AudioManager}, {@link android.media.MediaRouter},
     * {@link android.telephony.TelephonyManager}, {@link android.telephony.SubscriptionManager},
     * {@link android.view.inputmethod.InputMethodManager},
     * {@link android.app.UiModeManager}, {@link android.app.DownloadManager},
     * {@link android.os.BatteryManager}, {@link android.app.job.JobScheduler},
     * {@link android.app.usage.NetworkStatsManager},
     * {@link android.content.pm.verify.domain.DomainVerificationManager},
     * {@link android.view.displayhash.DisplayHashManager}
     * {@link android.security.authenticationpolicy.AuthenticationPolicyManager}.
     * </p>
     *
     * <p>
     * Note: System services obtained via this API may be closely associated with
     * the Context in which they are obtained from.  In general, do not share the
     * service objects between various different contexts (Activities, Applications,
     * Services, Providers, etc.)
     * </p>
     *
     * <p>Note: Instant apps, for which {@link PackageManager#isInstantApp()} returns true,
     * don't have access to the following system services: {@link #DEVICE_POLICY_SERVICE},
     * {@link #FINGERPRINT_SERVICE}, {@link #KEYGUARD_SERVICE}, {@link #SHORTCUT_SERVICE},
     * {@link #USB_SERVICE}, {@link #WALLPAPER_SERVICE}, {@link #WIFI_P2P_SERVICE},
     * {@link #WIFI_SERVICE}, {@link #WIFI_AWARE_SERVICE}. For these services this method will
     * return {@code null}. Generally, if you are running as an instant app you should always
     * check whether the result of this method is {@code null}.
     * </p>
     *
     * @param serviceClass The class of the desired service.
     * @return The service or {@code null} if the class is not a supported system service. Note:
     * <b>never</b> throw a {@link RuntimeException} if the name is not supported.
     */
    @SuppressWarnings("unchecked")
    @RavenwoodKeep
    public final <T> T getSystemService(@NonNull Class<T> serviceClass) {
        if (Build.VERSION.SDK_INT < 23) {
            throw new NoSuchMethodError("getSystemService(Ljava/lang/Class;)Ljava/lang/Object;");
        }
        throw new UnsupportedOperationException(
                "getSystemService(Ljava/lang/Class;)Ljava/lang/Object;");
    }

    /**
     * Gets the name of the system-level service that is represented by the specified class.
     *
     * @param serviceClass The class of the desired service.
     * @return The service name or null if the class is not a supported system service.
     */
    public @Nullable String getSystemServiceName(@NonNull Class<?> serviceClass) {
        if (Build.VERSION.SDK_INT < 23) {
            throw new NoSuchMethodError(
                    "getSystemServiceName(Ljava/lang/Class;)Ljava/lang/String;");
        }
        throw new UnsupportedOperationException(
                "getSystemServiceName(Ljava/lang/Class;)Ljava/lang/String;");
    }

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.PowerManager} for controlling power management,
     * including "wake locks," which let you keep the device on while
     * you're running long tasks.
     */
    public static final String POWER_SERVICE = "power";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.PowerStatsService} for accessing power stats
     * service.
     *
     * @see #getSystemService(String)
     * @hide
     */
    public static final String POWER_STATS_SERVICE = "powerstats";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.RecoverySystem} for accessing the recovery system
     * service.
     *
     * @see #getSystemService(String)
     * @hide
     */
    public static final String RECOVERY_SERVICE = "recovery";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.SystemUpdateManager} for accessing the system update
     * manager service.
     *
     * @see #getSystemService(String)
     * @hide
     */
    @SystemApi
    public static final String SYSTEM_UPDATE_SERVICE = "system_update";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.view.WindowManager} for accessing the system's window
     * manager.
     *
     * @see #getSystemService(String)
     * @see android.view.WindowManager
     */
    @UiContext
    public static final String WINDOW_SERVICE = "window";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.view.LayoutInflater} for inflating layout resources in this
     * context.
     *
     * @see #getSystemService(String)
     * @see android.view.LayoutInflater
     */
    @UiContext
    public static final String LAYOUT_INFLATER_SERVICE = "layout_inflater";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.accounts.AccountManager} for receiving intents at a
     * time of your choosing.
     *
     * @see #getSystemService(String)
     * @see android.accounts.AccountManager
     */
    public static final String ACCOUNT_SERVICE = "account";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.ActivityManager} for interacting with the global
     * system state.
     *
     * @see #getSystemService(String)
     * @see android.app.ActivityManager
     */
    public static final String ACTIVITY_SERVICE = "activity";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.ActivityTaskManager} for interacting with the global system state.
     *
     * @see #getSystemService(String)
     * @see android.app.ActivityTaskManager
     * @hide
     */
    public static final String ACTIVITY_TASK_SERVICE = "activity_task";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.UriGrantsManager} for interacting with the global system state.
     *
     * @see #getSystemService(String)
     * @see android.app.UriGrantsManager
     * @hide
     */
    public static final String URI_GRANTS_SERVICE = "uri_grants";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.AlarmManager} for receiving intents at a
     * time of your choosing.
     *
     * @see #getSystemService(String)
     * @see android.app.AlarmManager
     */
    public static final String ALARM_SERVICE = "alarm";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.NotificationManager} for informing the user of
     * background events.
     *
     * @see #getSystemService(String)
     * @see android.app.NotificationManager
     */
    public static final String NOTIFICATION_SERVICE = "notification";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.view.accessibility.AccessibilityManager} for giving the user
     * feedback for UI events through the registered event listeners.
     *
     * @see #getSystemService(String)
     * @see android.view.accessibility.AccessibilityManager
     */
    public static final String ACCESSIBILITY_SERVICE = "accessibility";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.view.accessibility.CaptioningManager} for obtaining
     * captioning properties and listening for changes in captioning
     * preferences.
     *
     * @see #getSystemService(String)
     * @see android.view.accessibility.CaptioningManager
     */
    public static final String CAPTIONING_SERVICE = "captioning";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.KeyguardManager} for controlling keyguard.
     *
     * @see #getSystemService(String)
     * @see android.app.KeyguardManager
     */
    public static final String KEYGUARD_SERVICE = "keyguard";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.location.LocationManager} for controlling location
     * updates.
     *
     * @see #getSystemService(String)
     * @see android.location.LocationManager
     */
    public static final String LOCATION_SERVICE = "location";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.location.CountryDetector} for detecting the country that
     * the user is in.
     *
     * @hide
     */
    @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.P, trackingBug = 115609023)
    public static final String COUNTRY_DETECTOR = "country_detector";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.app.SearchManager} for handling searches.
     *
     * <p>
     * {@link Configuration#UI_MODE_TYPE_WATCH} does not support
     * {@link android.app.SearchManager}.
     *
     * @see #getSystemService
     * @see android.app.SearchManager
     */
    public static final String SEARCH_SERVICE = "search";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.hardware.SensorManager} for accessing sensors.
     *
     * @see #getSystemService(String)
     * @see android.hardware.SensorManager
     */
    public static final String SENSOR_SERVICE = "sensor";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.hardware.SensorPrivacyManager} for accessing sensor privacy
     * functions.
     *
     * @see #getSystemService(String)
     * @see android.hardware.SensorPrivacyManager
     *
     * @hide
     */
    public static final String SENSOR_PRIVACY_SERVICE = "sensor_privacy";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.security.keystore.KeyStoreManager} for accessing
     * <a href="/privacy-and-security/keystore">Android Keystore</a>
     * functions.
     *
     * @see #getSystemService(String)
     * @see android.security.keystore.KeyStoreManager
     */
    @FlaggedApi(android.security.Flags.FLAG_KEYSTORE_GRANT_API)
    public static final String KEYSTORE_SERVICE = "keystore";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.os.storage.StorageManager} for accessing system storage
     * functions.
     *
     * @see #getSystemService(String)
     * @see android.os.storage.StorageManager
     */
    public static final String STORAGE_SERVICE = "storage";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.app.usage.StorageStatsManager} for accessing system storage
     * statistics.
     *
     * @see #getSystemService(String)
     * @see android.app.usage.StorageStatsManager
     */
    public static final String STORAGE_STATS_SERVICE = "storagestats";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * com.android.server.WallpaperService for accessing wallpapers.
     *
     * @see #getSystemService(String)
     */
    @UiContext
    public static final String WALLPAPER_SERVICE = "wallpaper";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link android.os.VibratorManager}
     * for accessing the device vibrators, interacting with individual ones and playing synchronized
     * effects on multiple vibrators.
     *
     * @see #getSystemService(String)
     * @see android.os.VibratorManager
     */
    @SuppressLint("ServiceName")
    public static final String VIBRATOR_MANAGER_SERVICE = "vibrator_manager";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link android.os.Vibrator} for
     * interacting with the vibration hardware.
     *
     * @deprecated Use {@link android.os.VibratorManager} to retrieve the default system vibrator.
     * @see #getSystemService(String)
     * @see android.os.Vibrator
     */
    @Deprecated
    public static final String VIBRATOR_SERVICE = "vibrator";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.app.StatusBarManager} for interacting with the status bar and quick settings.
     *
     * @see #getSystemService(String)
     * @see android.app.StatusBarManager
     *
     */
    @SuppressLint("ServiceName")
    public static final String STATUS_BAR_SERVICE = "statusbar";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.ConnectivityManager} for handling management of
     * network connections.
     *
     * @see #getSystemService(String)
     * @see android.net.ConnectivityManager
     */
    public static final String CONNECTIVITY_SERVICE = "connectivity";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.PacProxyManager} for handling management of
     * pac proxy information.
     *
     * @see #getSystemService(String)
     * @see android.net.PacProxyManager
     * @hide
     */
    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    public static final String PAC_PROXY_SERVICE = "pac_proxy";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link android.net.vcn.VcnManager}
     * for managing Virtual Carrier Networks
     *
     * @see #getSystemService(String)
     * @see android.net.vcn.VcnManager
     * @hide
     */
    @FlaggedApi(android.os.Flags.FLAG_MAINLINE_VCN_PLATFORM_API)
    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    public static final String VCN_MANAGEMENT_SERVICE = "vcn_management";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.net.INetd} for communicating with the network stack
     * @hide
     * @see #getSystemService(String)
     * @hide
     */
    @SystemApi
    public static final String NETD_SERVICE = "netd";

    /**
     * Use with {@link android.os.ServiceManager.getService()} to retrieve a
     * {@link INetworkStackConnector} IBinder for communicating with the network stack
     * @hide
     * @see NetworkStackClient
     */
    public static final String NETWORK_STACK_SERVICE = "network_stack";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link android.net.TetheringManager}
     * for managing tethering functions.
     *
     * @see android.net.TetheringManager
     */
    @SuppressLint("UnflaggedApi")
    public static final String TETHERING_SERVICE = "tethering";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.net.thread.ThreadNetworkManager}.
     *
     * <p>On devices without {@link PackageManager#FEATURE_THREAD_NETWORK} system feature
     * the {@link #getSystemService(String)} will return {@code null}.
     *
     * @see #getSystemService(String)
     * @see android.net.thread.ThreadNetworkManager
     * @hide
     */
    @FlaggedApi(com.android.net.thread.platform.flags.Flags.FLAG_THREAD_ENABLED_PLATFORM)
    @SystemApi
    public static final String THREAD_NETWORK_SERVICE = "thread_network";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.net.IpSecManager} for encrypting Sockets or Networks with
     * IPSec.
     *
     * @see #getSystemService(String)
     */
    public static final String IPSEC_SERVICE = "ipsec";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link android.net.VpnManager} to
     * manage profiles for the platform built-in VPN.
     *
     * @see #getSystemService(String)
     */
    public static final String VPN_MANAGEMENT_SERVICE = "vpn_management";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.ConnectivityDiagnosticsManager} for performing network connectivity diagnostics
     * as well as receiving network connectivity information from the system.
     *
     * @see #getSystemService(String)
     * @see android.net.ConnectivityDiagnosticsManager
     */
    public static final String CONNECTIVITY_DIAGNOSTICS_SERVICE = "connectivity_diagnostics";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.TestNetworkManager} for building TUNs and limited-use Networks
     *
     * @see #getSystemService(String)
     * @hide
     */
    @TestApi @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    public static final String TEST_NETWORK_SERVICE = "test_network";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.os.IUpdateLock} for managing runtime sequences that
     * must not be interrupted by headless OTA application or similar.
     *
     * @hide
     * @see #getSystemService(String)
     * @see android.os.UpdateLock
     */
    public static final String UPDATE_LOCK_SERVICE = "updatelock";

    /**
     * Constant for the internal network management service, not really a Context service.
     * @hide
     */
    public static final String NETWORKMANAGEMENT_SERVICE = "network_management";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link com.android.server.slice.SliceManagerService} for managing slices.
     * @hide
     * @see #getSystemService(String)
     */
    public static final String SLICE_SERVICE = "slice";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.app.usage.NetworkStatsManager} for querying network usage stats.
     *
     * @see #getSystemService(String)
     * @see android.app.usage.NetworkStatsManager
     */
    public static final String NETWORK_STATS_SERVICE = "netstats";
    /** {@hide} */
    public static final String NETWORK_POLICY_SERVICE = "netpolicy";
    /** {@hide} */
    public static final String NETWORK_WATCHLIST_SERVICE = "network_watchlist";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.wifi.WifiManager} for handling management of
     * Wi-Fi access.
     *
     * @see #getSystemService(String)
     * @see android.net.wifi.WifiManager
     */
    public static final String WIFI_SERVICE = "wifi";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.net.wifi.wificond.WifiNl80211Manager} for handling management of the
     * Wi-Fi nl802.11 daemon (wificond).
     *
     * @see #getSystemService(String)
     * @see android.net.wifi.wificond.WifiNl80211Manager
     * @hide
     */
    @SystemApi
    @SuppressLint("ServiceName")
    public static final String WIFI_NL80211_SERVICE = "wifinl80211";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.wifi.p2p.WifiP2pManager} for handling management of
     * Wi-Fi peer-to-peer connections.
     *
     * @see #getSystemService(String)
     * @see android.net.wifi.p2p.WifiP2pManager
     */
    public static final String WIFI_P2P_SERVICE = "wifip2p";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.net.wifi.aware.WifiAwareManager} for handling management of
     * Wi-Fi Aware.
     *
     * @see #getSystemService(String)
     * @see android.net.wifi.aware.WifiAwareManager
     */
    public static final String WIFI_AWARE_SERVICE = "wifiaware";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.wifi.WifiScanner} for scanning the wifi universe
     *
     * @see #getSystemService(String)
     * @see android.net.wifi.WifiScanner
     * @hide
     */
    @SystemApi
    public static final String WIFI_SCANNING_SERVICE = "wifiscanner";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.wifi.RttManager} for ranging devices with wifi
     *
     * @see #getSystemService(String)
     * @see android.net.wifi.RttManager
     * @hide
     */
    @SystemApi
    @Deprecated
    public static final String WIFI_RTT_SERVICE = "rttmanager";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.wifi.rtt.WifiRttManager} for ranging devices with wifi.
     *
     * @see #getSystemService(String)
     * @see android.net.wifi.rtt.WifiRttManager
     */
    public static final String WIFI_RTT_RANGING_SERVICE = "wifirtt";


    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.wifi.usd.UsdManager} for Unsynchronized Service Discovery (USD) operation.
     *
     * @see #getSystemService(String)
     * @see android.net.wifi.usd.UsdManager
     * @hide
     */
    @FlaggedApi(android.net.wifi.flags.Flags.FLAG_USD)
    @SystemApi
    public static final String WIFI_USD_SERVICE = "wifi_usd";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.lowpan.LowpanManager} for handling management of
     * LoWPAN access.
     *
     * @see #getSystemService(String)
     * @see android.net.lowpan.LowpanManager
     *
     * @hide
     */
    public static final String LOWPAN_SERVICE = "lowpan";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link android.net.EthernetManager}
     * for handling management of Ethernet access.
     *
     * @see #getSystemService(String)
     * @see android.net.EthernetManager
     *
     * @hide
     */
    @SystemApi
    public static final String ETHERNET_SERVICE = "ethernet";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.nsd.NsdManager} for handling management of network service
     * discovery
     *
     * @see #getSystemService(String)
     * @see android.net.nsd.NsdManager
     */
    public static final String NSD_SERVICE = "servicediscovery";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.AudioManager} for handling management of volume,
     * ringer modes and audio routing.
     *
     * @see #getSystemService(String)
     * @see android.media.AudioManager
     */
    public static final String AUDIO_SERVICE = "audio";

    /**
     * @hide
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.AudioDeviceVolumeManager} for handling management of audio device
     * (e.g. speaker, USB headset) volume.
     *
     * @see #getSystemService(String)
     * @see android.media.AudioDeviceVolumeManager
     */
    @SystemApi
    public static final String AUDIO_DEVICE_VOLUME_SERVICE = "audio_device_volume";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.media.MediaTranscodingManager} for transcoding media.
     *
     * @hide
     * @see #getSystemService(String)
     * @see android.media.MediaTranscodingManager
     */
    @SystemApi
    public static final String MEDIA_TRANSCODING_SERVICE = "media_transcoding";

    /**
     * AuthService orchestrates biometric and PIN/pattern/password authentication.
     *
     * BiometricService was split into two services, AuthService and BiometricService, where
     * AuthService is the high level service that orchestrates all types of authentication, and
     * BiometricService is a lower layer responsible only for biometric authentication.
     *
     * Ideally we should have renamed BiometricManager to AuthManager, because it logically
     * corresponds to AuthService. However, because BiometricManager is a public API, we kept
     * the old name but changed the internal implementation to use AuthService.
     *
     * As of now, the AUTH_SERVICE constant is only used to identify the service in
     * SystemServiceRegistry and SELinux. To obtain the manager for AUTH_SERVICE, one should use
     * BIOMETRIC_SERVICE with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.biometrics.BiometricManager}
     *
     * Map of the two services and their managers:
     * [Service]            [Manager]
     * AuthService          BiometricManager
     * BiometricService     N/A
     *
     * @hide
     */
    public static final String AUTH_SERVICE = "auth";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an {@link
     * android.security.authenticationpolicy.AuthenticationPolicyManager}.
     * @see #getSystemService
     * @see android.security.authenticationpolicy.AuthenticationPolicyManager
     *
     * @hide
     */
    @SystemApi
    @FlaggedApi(FLAG_SECURE_LOCKDOWN)
    public static final String AUTHENTICATION_POLICY_SERVICE = "authentication_policy";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.fingerprint.FingerprintManager} for handling management
     * of fingerprints.
     *
     * @see #getSystemService(String)
     * @see android.hardware.fingerprint.FingerprintManager
     */
    public static final String FINGERPRINT_SERVICE = "fingerprint";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.face.FaceManager} for handling management
     * of face authentication.
     *
     * @hide
     * @see #getSystemService
     * @see android.hardware.face.FaceManager
     */
    public static final String FACE_SERVICE = "face";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.iris.IrisManager} for handling management
     * of iris authentication.
     *
     * @hide
     * @see #getSystemService
     * @see android.hardware.iris.IrisManager
     */
    public static final String IRIS_SERVICE = "iris";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.biometrics.BiometricManager} for handling
     * biometric and PIN/pattern/password authentication.
     *
     * @see #getSystemService
     * @see android.hardware.biometrics.BiometricManager
     */
    public static final String BIOMETRIC_SERVICE = "biometric";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.MediaCommunicationManager}
     * for managing {@link android.media.MediaSession2}.
     *
     * @see #getSystemService(String)
     * @see android.media.MediaCommunicationManager
     */
    public static final String MEDIA_COMMUNICATION_SERVICE = "media_communication";

    /**
     * Use with {@link #getSystemService} to retrieve a
     * {@link android.media.MediaRouter} for controlling and managing
     * routing of media.
     *
     * @see #getSystemService(String)
     * @see android.media.MediaRouter
     */
    public static final String MEDIA_ROUTER_SERVICE = "media_router";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.session.MediaSessionManager} for managing media Sessions.
     *
     * @see #getSystemService(String)
     * @see android.media.session.MediaSessionManager
     */
    public static final String MEDIA_SESSION_SERVICE = "media_session";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.telephony.TelephonyManager} for handling management the
     * telephony features of the device.
     *
     * @see #getSystemService(String)
     * @see android.telephony.TelephonyManager
     */
    public static final String TELEPHONY_SERVICE = "phone";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.telephony.SubscriptionManager} for handling management the
     * telephony subscriptions of the device.
     *
     * @see #getSystemService(String)
     * @see android.telephony.SubscriptionManager
     */
    public static final String TELEPHONY_SUBSCRIPTION_SERVICE = "telephony_subscription_service";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.telecom.TelecomManager} to manage telecom-related features
     * of the device.
     *
     * @see #getSystemService(String)
     * @see android.telecom.TelecomManager
     */
    public static final String TELECOM_SERVICE = "telecom";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.telephony.CarrierConfigManager} for reading carrier configuration values.
     *
     * @see #getSystemService(String)
     * @see android.telephony.CarrierConfigManager
     */
    public static final String CARRIER_CONFIG_SERVICE = "carrier_config";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.telephony.euicc.EuiccManager} to manage the device eUICC (embedded SIM).
     *
     * @see #getSystemService(String)
     * @see android.telephony.euicc.EuiccManager
     */
    public static final String EUICC_SERVICE = "euicc";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.telephony.euicc.EuiccCardManager} to access the device eUICC (embedded SIM).
     *
     * @see #getSystemService(String)
     * @see android.telephony.euicc.EuiccCardManager
     * @hide
     */
    @SystemApi
    public static final String EUICC_CARD_SERVICE = "euicc_card";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.telephony.MmsManager} to send/receive MMS messages.
     *
     * @see #getSystemService(String)
     * @see android.telephony.MmsManager
     * @hide
     */
    public static final String MMS_SERVICE = "mms";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.content.ClipboardManager} for accessing and modifying
     * the contents of the global clipboard.
     *
     * @see #getSystemService(String)
     * @see android.content.ClipboardManager
     */
    public static final String CLIPBOARD_SERVICE = "clipboard";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link TextClassificationManager} for text classification services.
     *
     * @see #getSystemService(String)
     * @see TextClassificationManager
     */
    public static final String TEXT_CLASSIFICATION_SERVICE = "textclassification";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.view.selectiontoolbar.SelectionToolbarManager} for selection toolbar service.
     *
     * @see #getSystemService(String)
     * @hide
     */
    public static final String SELECTION_TOOLBAR_SERVICE = "selection_toolbar";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.graphics.fonts.FontManager} for font services.
     *
     * @see #getSystemService(String)
     * @see android.graphics.fonts.FontManager
     * @hide
     */
    @SystemApi
    @TestApi
    public static final String FONT_SERVICE = "font";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link com.android.server.attention.AttentionManagerService} for attention services.
     *
     * @see #getSystemService(String)
     * @see android.server.attention.AttentionManagerService
     * @hide
     */
    @TestApi
    public static final String ATTENTION_SERVICE = "attention";

    /**
     * Official published name of the (internal) rotation resolver service.
     *
     * // TODO(b/178151184): change it back to rotation resolver before S release.
     *
     * @see #getSystemService(String)
     * @hide
     */
    public static final String ROTATION_RESOLVER_SERVICE = "resolver";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.view.inputmethod.InputMethodManager} for accessing input
     * methods.
     *
     * @see #getSystemService(String)
     */
    public static final String INPUT_METHOD_SERVICE = "input_method";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.view.textservice.TextServicesManager} for accessing
     * text services.
     *
     * @see #getSystemService(String)
     */
    public static final String TEXT_SERVICES_MANAGER_SERVICE = "textservices";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.appwidget.AppWidgetManager} for accessing AppWidgets.
     *
     * @see #getSystemService(String)
     */
    public static final String APPWIDGET_SERVICE = "appwidget";

    /**
     * Official published name of the (internal) voice interaction manager service.
     *
     * @hide
     * @see #getSystemService(String)
     */
    public static final String VOICE_INTERACTION_MANAGER_SERVICE = "voiceinteraction";

    /**
     * Official published name of the (internal) autofill service.
     *
     * @hide
     * @see #getSystemService(String)
     */
    public static final String AUTOFILL_MANAGER_SERVICE = "autofill";

    /**
     * Official published name of the (internal) text to speech manager service.
     *
     * @hide
     * @see #getSystemService(String)
     */
    public static final String TEXT_TO_SPEECH_MANAGER_SERVICE = "texttospeech";

    /**
     * Official published name of the content capture service.
     *
     * @hide
     * @see #getSystemService(String)
     */
    @TestApi
    @SuppressLint("ServiceName")  // TODO: This should be renamed to CONTENT_CAPTURE_SERVICE
    public static final String CONTENT_CAPTURE_MANAGER_SERVICE = "content_capture";

    /**
     * Official published name of the translation service.
     *
     * @hide
     * @see #getSystemService(String)
     */
    @SystemApi
    @SuppressLint("ServiceName")
    public static final String TRANSLATION_MANAGER_SERVICE = "translation";

    /**
     * Official published name of the translation service which supports ui translation function.
     *
     * @hide
     * @see #getSystemService(String)
     */
    @SystemApi
    public static final String UI_TRANSLATION_SERVICE = "ui_translation";

    /**
     * Used for getting content selections and classifications for task snapshots.
     *
     * @hide
     * @see #getSystemService(String)
     */
    @SystemApi
    public static final String CONTENT_SUGGESTIONS_SERVICE = "content_suggestions";

    /**
     * Official published name of the app prediction service.
     *
     * <p><b>NOTE: </b> this service is optional; callers of
     * {@code Context.getSystemServiceName(APP_PREDICTION_SERVICE)} should check for {@code null}.
     *
     * @hide
     * @see #getSystemService(String)
     */
    @SystemApi
    public static final String APP_PREDICTION_SERVICE = "app_prediction";

    /**
     * Used for reading system-wide, overridable flags.
     *
     * @hide
     */
    public static final String FEATURE_FLAGS_SERVICE = "feature_flags";

    /**
     * Official published name of the search ui service.
     *
     * <p><b>NOTE: </b> this service is optional; callers of
     * {@code Context.getSystemServiceName(SEARCH_UI_SERVICE)} should check for {@code null}.
     *
     * @hide
     * @see #getSystemService(String)
     */
    @SystemApi
    public static final String SEARCH_UI_SERVICE = "search_ui";

    /**
     * Used for getting the smartspace service.
     *
     * <p><b>NOTE: </b> this service is optional; callers of
     * {@code Context.getSystemServiceName(SMARTSPACE_SERVICE)} should check for {@code null}.
     *
     * @hide
     * @see #getSystemService(String)
     */
    @SystemApi
    public static final String SMARTSPACE_SERVICE = "smartspace";

    /**
     * Used for getting the contextual search service.
     *
     * <p><b>NOTE: </b> this service is optional; callers of
     * {@code Context.getSystemServiceName(CONTEXTUAL_SEARCH_SERVICE)} must check for {@code null}.
     *
     * @hide
     * @see #getSystemService(String)
     */
    @SystemApi
    public static final String CONTEXTUAL_SEARCH_SERVICE = "contextual_search";

    /**
     * Used for getting the cloudsearch service.
     *
     * <p><b>NOTE: </b> this service is optional; callers of
     * {@code Context.getSystemServiceName(CLOUDSEARCH_SERVICE)} should check for {@code null}.
     *
     * @hide
     * @see #getSystemService(String)
     */
    @SystemApi
    public static final String CLOUDSEARCH_SERVICE = "cloudsearch";

    /**
     * Use with {@link #getSystemService(String)} to access the
     * {@link com.android.server.voiceinteraction.SoundTriggerService}.
     *
     * @hide
     * @see #getSystemService(String)
     */
    public static final String SOUND_TRIGGER_SERVICE = "soundtrigger";

    /**
     * Use with {@link #getSystemService(String)} to access the
     * {@link com.android.server.soundtrigger_middleware.SoundTriggerMiddlewareService}.
     *
     * @hide
     * @see #getSystemService(String)
     */
    public static final String SOUND_TRIGGER_MIDDLEWARE_SERVICE = "soundtrigger_middleware";

    /**
     * Used for getting the wallpaper effects generation service.
     *
     * <p><b>NOTE: </b> this service is optional; callers of
     * {@code Context.getSystemServiceName(WALLPAPER_EFFECTS_GENERATION_SERVICE)} should check for
     * {@code null}.
     *
     * @hide
     * @see #getSystemService(String)
     */
    @SystemApi
    public static final String WALLPAPER_EFFECTS_GENERATION_SERVICE =
            "wallpaper_effects_generation";

    /**
     * Used to access {@link MusicRecognitionManagerService}.
     *
     * @hide
     * @see #getSystemService(String)
     */
    @SystemApi
    public static final String MUSIC_RECOGNITION_SERVICE = "music_recognition";

    /**
     * Official published name of the (internal) permission service.
     *
     * @see #getSystemService(String)
     * @hide
     */
    @SystemApi
    public static final String PERMISSION_SERVICE = "permission";

    /**
     * Official published name of the legacy (internal) permission service.
     *
     * @see #getSystemService(String)
     * @hide
     */
    //@SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    public static final String LEGACY_PERMISSION_SERVICE = "legacy_permission";

    /**
     * Official published name of the (internal) permission controller service.
     *
     * @see #getSystemService(String)
     * @hide
     */
    @SystemApi
    public static final String PERMISSION_CONTROLLER_SERVICE = "permission_controller";

    /**
     * Official published name of the (internal) permission checker service.
     *
     * @see #getSystemService(String)
     * @hide
     */
    public static final String PERMISSION_CHECKER_SERVICE = "permission_checker";

    /**
     * Official published name of the (internal) permission enforcer service.
     *
     * @see #getSystemService(String)
     * @hide
     */
    public static final String PERMISSION_ENFORCER_SERVICE = "permission_enforcer";

    /**
     * Use with {@link #getSystemService(String) to retrieve an
     * {@link android.apphibernation.AppHibernationManager}} for
     * communicating with the hibernation service.
     * @hide
     *
     * @see #getSystemService(String)
     */
    @SystemApi
    public static final String APP_HIBERNATION_SERVICE = "app_hibernation";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.app.backup.IBackupManager IBackupManager} for communicating
     * with the backup mechanism.
     * @hide
     *
     * @see #getSystemService(String)
     */
    @SystemApi
    public static final String BACKUP_SERVICE = "backup";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.content.rollback.RollbackManager} for communicating
     * with the rollback manager
     *
     * @see #getSystemService(String)
     * @hide
     */
    @SystemApi
    public static final String ROLLBACK_SERVICE = "rollback";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.scheduling.RebootReadinessManager} for communicating
     * with the reboot readiness detector.
     *
     * @see #getSystemService(String)
     * @hide
     */
    @SystemApi
    public static final String REBOOT_READINESS_SERVICE = "reboot_readiness";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.DropBoxManager} instance for recording
     * diagnostic logs.
     * @see #getSystemService(String)
     */
    public static final String DROPBOX_SERVICE = "dropbox";

    /**
     * System service name for BackgroundInstallControlService. This service supervises the MBAs
     * on device and provides the related metadata of the MBAs.
     *
     * @hide
     */
    @SuppressLint("ServiceName")
    public static final String BACKGROUND_INSTALL_CONTROL_SERVICE = "background_install_control";

    /**
     * System service name for BinaryTransparencyService. This is used to retrieve measurements
     * pertaining to various pre-installed and system binaries on device for the purposes of
     * providing transparency to the user.
     *
     * @hide
     */
    @SuppressLint("ServiceName")
    public static final String BINARY_TRANSPARENCY_SERVICE = "transparency";

    /**
     * System service name for IntrusionDetectionService.
     * The service manages the intrusion detection info on device.
     * @hide
     */
    @FlaggedApi(android.security.Flags.FLAG_AFL_API)
    public static final String INTRUSION_DETECTION_SERVICE = "intrusion_detection";

    /**
     * System service name for the DeviceIdleManager.
     * @see #getSystemService(String)
     * @hide
     */
    @TestApi
    @SuppressLint("ServiceName")  // TODO: This should be renamed to DEVICE_IDLE_SERVICE
    public static final String DEVICE_IDLE_CONTROLLER = "deviceidle";

    /**
     * System service name for the PowerWhitelistManager.
     *
     * @see #getSystemService(String)
     * @hide
     */
    @TestApi
    @Deprecated
    @SuppressLint("ServiceName")
    public static final String POWER_WHITELIST_MANAGER = "power_whitelist";

    /**
     * System service name for the PowerExemptionManager.
     *
     * @see #getSystemService(String)
     * @hide
     */
    @TestApi
    public static final String POWER_EXEMPTION_SERVICE = "power_exemption";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.admin.DevicePolicyManager} for working with global
     * device policy management.
     *
     * @see #getSystemService(String)
     */
    public static final String DEVICE_POLICY_SERVICE = "device_policy";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.UiModeManager} for controlling UI modes.
     *
     * @see #getSystemService(String)
     */
    public static final String UI_MODE_SERVICE = "uimode";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.DownloadManager} for requesting HTTP downloads.
     *
     * @see #getSystemService(String)
     */
    public static final String DOWNLOAD_SERVICE = "download";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.BatteryManager} for managing battery state.
     *
     * @see #getSystemService(String)
     */
    public static final String BATTERY_SERVICE = "batterymanager";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.nfc.NfcManager} for using NFC.
     *
     * @see #getSystemService(String)
     */
    public static final String NFC_SERVICE = "nfc";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.bluetooth.BluetoothManager} for using Bluetooth.
     *
     * @see #getSystemService(String)
     */
    public static final String BLUETOOTH_SERVICE = "bluetooth";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.net.sip.SipManager} for accessing the SIP related service.
     *
     * @see #getSystemService(String)
     */
    /** @hide */
    public static final String SIP_SERVICE = "sip";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.hardware.usb.UsbManager} for access to USB devices (as a USB host)
     * and for controlling this device's behavior as a USB device.
     *
     * @see #getSystemService(String)
     * @see android.hardware.usb.UsbManager
     */
    public static final String USB_SERVICE = "usb";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * Use with {@link #getSystemService} to retrieve a {@link
     * android.debug.AdbManager} for access to ADB debug functions.
     *
     * @see #getSystemService(String)
     * @see android.debug.AdbManager
     *
     * @hide
     */
    public static final String ADB_SERVICE = "adb";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.hardware.SerialManager} for access to serial ports.
     *
     * @see #getSystemService(String)
     * @see android.hardware.SerialManager
     *
     * @hide
     */
    public static final String SERIAL_SERVICE = "serial";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.hdmi.HdmiControlManager} for controlling and managing
     * HDMI-CEC protocol.
     *
     * @see #getSystemService(String)
     * @see android.hardware.hdmi.HdmiControlManager
     * @hide
     */
    @SystemApi
    public static final String HDMI_CONTROL_SERVICE = "hdmi_control";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.input.InputManager} for interacting with input devices.
     *
     * @see #getSystemService(String)
     * @see android.hardware.input.InputManager
     */
    public static final String INPUT_SERVICE = "input";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.display.DisplayManager} for interacting with display devices.
     *
     * @see #getSystemService(String)
     * @see android.hardware.display.DisplayManager
     */
    public static final String DISPLAY_SERVICE = "display";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.display.ColorDisplayManager} for controlling color transforms.
     *
     * @see #getSystemService(String)
     * @see android.hardware.display.ColorDisplayManager
     * @hide
     */
    public static final String COLOR_DISPLAY_SERVICE = "color_display";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.UserManager} for managing users on devices that support multiple users.
     *
     * @see #getSystemService(String)
     * @see android.os.UserManager
     */
    public static final String USER_SERVICE = "user";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.content.pm.LauncherApps} for querying and monitoring launchable apps across
     * profiles of a user.
     *
     * @see #getSystemService(String)
     * @see android.content.pm.LauncherApps
     */
    public static final String LAUNCHER_APPS_SERVICE = "launcherapps";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.content.RestrictionsManager} for retrieving application restrictions
     * and requesting permissions for restricted operations.
     * @see #getSystemService(String)
     * @see android.content.RestrictionsManager
     */
    public static final String RESTRICTIONS_SERVICE = "restrictions";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.AppOpsManager} for tracking application operations
     * on the device.
     *
     * @see #getSystemService(String)
     * @see android.app.AppOpsManager
     */
    public static final String APP_OPS_SERVICE = "appops";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link android.app.role.RoleManager}
     * for managing roles.
     *
     * @see #getSystemService(String)
     * @see android.app.role.RoleManager
     */
    public static final String ROLE_SERVICE = "role";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.camera2.CameraManager} for interacting with
     * camera devices.
     *
     * @see #getSystemService(String)
     * @see android.hardware.camera2.CameraManager
     */
    public static final String CAMERA_SERVICE = "camera";

    /**
     * {@link android.print.PrintManager} for printing and managing
     * printers and print tasks.
     *
     * @see #getSystemService(String)
     * @see android.print.PrintManager
     */
    public static final String PRINT_SERVICE = "print";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.companion.CompanionDeviceManager} for managing companion devices
     *
     * @see #getSystemService(String)
     * @see android.companion.CompanionDeviceManager
     */
    public static final String COMPANION_DEVICE_SERVICE = "companiondevice";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.companion.virtual.VirtualDeviceManager} for managing virtual devices.
     *
     * On devices without {@link PackageManager#FEATURE_COMPANION_DEVICE_SETUP}
     * system feature the {@link #getSystemService(String)} will return {@code null}.
     *
     * @see #getSystemService(String)
     * @see android.companion.virtual.VirtualDeviceManager
     */
    @SuppressLint("ServiceName")
    public static final String VIRTUAL_DEVICE_SERVICE = "virtualdevice";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.ConsumerIrManager} for transmitting infrared
     * signals from the device.
     *
     * @see #getSystemService(String)
     * @see android.hardware.ConsumerIrManager
     */
    public static final String CONSUMER_IR_SERVICE = "consumer_ir";

    /**
     * {@link android.app.trust.TrustManager} for managing trust agents.
     * @see #getSystemService(String)
     * @see android.app.trust.TrustManager
     * @hide
     */
    public static final String TRUST_SERVICE = "trust";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.tv.interactive.TvInteractiveAppManager} for interacting with TV
     * interactive applications on the device.
     *
     * @see #getSystemService(String)
     * @see android.media.tv.interactive.TvInteractiveAppManager
     */
    public static final String TV_INTERACTIVE_APP_SERVICE = "tv_interactive_app";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.tv.TvInputManager} for interacting with TV inputs
     * on the device.
     *
     * @see #getSystemService(String)
     * @see android.media.tv.TvInputManager
     */
    public static final String TV_INPUT_SERVICE = "tv_input";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.tv.ad.TvAdManager} for interacting with TV client-side advertisement
     * services on the device.
     *
     * @see #getSystemService(String)
     * @see android.media.tv.ad.TvAdManager
     */
    @FlaggedApi(android.media.tv.flags.Flags.FLAG_ENABLE_AD_SERVICE_FW)
    public static final String TV_AD_SERVICE = "tv_ad";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.tv.TunerResourceManager} for interacting with TV
     * tuner resources on the device.
     *
     * @see #getSystemService(String)
     * @see android.media.tv.TunerResourceManager
     * @hide
     */
    public static final String TV_TUNER_RESOURCE_MGR_SERVICE = "tv_tuner_resource_mgr";

    /**
     * {@link android.net.NetworkScoreManager} for managing network scoring.
     * @see #getSystemService(String)
     * @see android.net.NetworkScoreManager
     * @deprecated see
     * <a href="{@docRoot}guide/topics/connectivity/wifi-suggest">Wi-Fi Suggestion API</a>
     * for alternative API to propose WiFi networks.
     * @hide
     */
    @SystemApi
    @Deprecated
    public static final String NETWORK_SCORE_SERVICE = "network_score";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.app.usage.UsageStatsManager} for querying device usage stats.
     *
     * @see #getSystemService(String)
     * @see android.app.usage.UsageStatsManager
     */
    public static final String USAGE_STATS_SERVICE = "usagestats";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.app.job.JobScheduler} instance for managing occasional
     * background tasks.
     * @see #getSystemService(String)
     * @see android.app.job.JobScheduler
     */
    public static final String JOB_SCHEDULER_SERVICE = "jobscheduler";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.service.persistentdata.PersistentDataBlockManager} instance
     * for interacting with a storage device that lives across factory resets.
     *
     * @see #getSystemService(String)
     * @see android.service.persistentdata.PersistentDataBlockManager
     */
    @FlaggedApi(android.security.Flags.FLAG_FRP_ENFORCEMENT)
    public static final String PERSISTENT_DATA_BLOCK_SERVICE = "persistent_data_block";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.service.oemlock.OemLockManager} instance for managing the OEM lock.
     *
     * @see #getSystemService(String)
     * @see android.service.oemlock.OemLockManager
     * @hide
     */
    @SystemApi
    public static final String OEM_LOCK_SERVICE = "oem_lock";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.media.projection.MediaProjectionManager} instance for managing
     * media projection sessions.
     * @see #getSystemService(String)
     * @see android.media.projection.MediaProjectionManager
     */
    public static final String MEDIA_PROJECTION_SERVICE = "media_projection";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.midi.MidiManager} for accessing the MIDI service.
     *
     * @see #getSystemService(String)
     */
    public static final String MIDI_SERVICE = "midi";


    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.radio.RadioManager} for accessing the broadcast radio service.
     *
     * @see #getSystemService(String)
     * @hide
     */
    public static final String RADIO_SERVICE = "broadcastradio";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.HardwarePropertiesManager} for accessing the hardware properties service.
     *
     * @see #getSystemService(String)
     */
    public static final String HARDWARE_PROPERTIES_SERVICE = "hardware_properties";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.ThermalService} for accessing the thermal service.
     *
     * @see #getSystemService(String)
     * @hide
     */
    public static final String THERMAL_SERVICE = "thermalservice";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.PerformanceHintManager} for accessing the performance hinting service.
     *
     * @see #getSystemService(String)
     */
    public static final String PERFORMANCE_HINT_SERVICE = "performance_hint";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.content.pm.ShortcutManager} for accessing the launcher shortcut service.
     *
     * @see #getSystemService(String)
     * @see android.content.pm.ShortcutManager
     */
    public static final String SHORTCUT_SERVICE = "shortcut";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.hardware.location.ContextHubManager} for accessing context hubs.
     *
     * @see #getSystemService(String)
     * @see android.hardware.location.ContextHubManager
     *
     * @hide
     */
    @SystemApi
    public static final String CONTEXTHUB_SERVICE = "contexthub";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.health.SystemHealthManager} for accessing system health (battery, power,
     * memory, etc) metrics.
     *
     * @see #getSystemService(String)
     */
    public static final String SYSTEM_HEALTH_SERVICE = "systemhealth";

    /**
     * Gatekeeper Service.
     * @hide
     */
    public static final String GATEKEEPER_SERVICE = "android.service.gatekeeper.IGateKeeperService";

    /**
     * Service defining the policy for access to device identifiers.
     * @hide
     */
    public static final String DEVICE_IDENTIFIERS_SERVICE = "device_identifiers";

    /**
     * Service to report a system health "incident"
     * @hide
     */
    public static final String INCIDENT_SERVICE = "incident";

    /**
     * Service to assist incidentd and dumpstated in reporting status to the user
     * and in confirming authorization to take an incident report or bugreport
     * @hide
     */
    public static final String INCIDENT_COMPANION_SERVICE = "incidentcompanion";

    /**
     * Service to assist {@link android.app.StatsManager} that lives in system server.
     * @hide
     */
    public static final String STATS_MANAGER_SERVICE = "statsmanager";

    /**
     * Service to assist statsd in obtaining general stats.
     * @hide
     */
    public static final String STATS_COMPANION_SERVICE = "statscompanion";

    /**
     * Service to assist statsd in logging atoms from bootstrap atoms.
     * @hide
     */
    public static final String STATS_BOOTSTRAP_ATOM_SERVICE = "statsbootstrap";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an {@link android.app.StatsManager}.
     * @hide
     */
    @SystemApi
    public static final String STATS_MANAGER = "stats";

    /**
     * Use with {@link android.os.ServiceManager.getService()} to retrieve a
     * {@link IPlatformCompat} IBinder for communicating with the platform compat service.
     * @hide
     */
    public static final String PLATFORM_COMPAT_SERVICE = "platform_compat";

    /**
     * Use with {@link android.os.ServiceManager.getService()} to retrieve a
     * {@link IPlatformCompatNative} IBinder for native code communicating with the platform compat
     * service.
     * @hide
     */
    public static final String PLATFORM_COMPAT_NATIVE_SERVICE = "platform_compat_native";

    /**
     * Service to capture a bugreport.
     * @see #getSystemService(String)
     * @see android.os.BugreportManager
     */
    public static final String BUGREPORT_SERVICE = "bugreport";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.content.om.OverlayManager} for managing overlay packages.
     *
     * @see #getSystemService(String)
     * @see android.content.om.OverlayManager
     */
    public static final String OVERLAY_SERVICE = "overlay";

    /**
     * Use with {@link #getSystemService(String)} to manage resources.
     *
     * @see #getSystemService(String)
     * @see com.android.server.resources.ResourcesManagerService
     * @hide
     */
    public static final String RESOURCES_SERVICE = "resources";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {android.os.IIdmap2} for managing idmap files (used by overlay
     * packages).
     *
     * @see #getSystemService(String)
     * @hide
     */
    public static final String IDMAP_SERVICE = "idmap";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link VrManager} for accessing the VR service.
     *
     * @see #getSystemService(String)
     * @hide
     */
    @SystemApi
    public static final String VR_SERVICE = "vrmanager";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.content.pm.CrossProfileApps} for cross profile operations.
     *
     * @see #getSystemService(String)
     */
    public static final String CROSS_PROFILE_APPS_SERVICE = "crossprofileapps";

    /**
     * Use with {@link #getSystemService} to retrieve a
     * {@link android.se.omapi.ISecureElementService}
     * for accessing the SecureElementService.
     *
     * @hide
     */
    @SystemApi
    public static final String SECURE_ELEMENT_SERVICE = "secure_element";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.timedetector.TimeDetector}.
     * @hide
     *
     * @see #getSystemService(String)
     */
    public static final String TIME_DETECTOR_SERVICE = "time_detector";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.timezonedetector.TimeZoneDetector}.
     * @hide
     *
     * @see #getSystemService(String)
     */
    public static final String TIME_ZONE_DETECTOR_SERVICE = "time_zone_detector";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link TimeManager}.
     * @hide
     *
     * @see #getSystemService(String)
     */
    @SystemApi
    @SuppressLint("ServiceName")
    public static final String TIME_MANAGER_SERVICE = "time_manager";

    /**
     * Binder service name for {@link AppBindingService}.
     * @hide
     */
    public static final String APP_BINDING_SERVICE = "app_binding";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.telephony.ims.ImsManager}.
     */
    public static final String TELEPHONY_IMS_SERVICE = "telephony_ims";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.os.SystemConfigManager}.
     * @hide
     */
    @SystemApi
    public static final String SYSTEM_CONFIG_SERVICE = "system_config";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.telephony.ims.RcsMessageManager}.
     * @hide
     */
    public static final String TELEPHONY_RCS_MESSAGE_SERVICE = "ircsmessage";

     /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.os.image.DynamicSystemManager}.
     * @hide
     */
    public static final String DYNAMIC_SYSTEM_SERVICE = "dynamic_system";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.app.blob.BlobStoreManager} for contributing and accessing data blobs
     * from the blob store maintained by the system.
     *
     * @see #getSystemService(String)
     * @see android.app.blob.BlobStoreManager
     */
    public static final String BLOB_STORE_SERVICE = "blob_store";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link TelephonyRegistryManager}.
     * @hide
     */
    public static final String TELEPHONY_REGISTRY_SERVICE = "telephony_registry";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.os.BatteryStatsManager}.
     * @hide
     */
    @SystemApi
    @SuppressLint("ServiceName")
    public static final String BATTERY_STATS_SERVICE = "batterystats";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.app.appsearch.AppSearchManager} for
     * indexing and querying app data managed by the system.
     *
     * @see #getSystemService(String)
     */
    public static final String APP_SEARCH_SERVICE = "app_search";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link AppFunctionManager} for
     * executing app functions.
     *
     * @see #getSystemService(String)
     */
    @FlaggedApi(FLAG_ENABLE_APP_FUNCTION_MANAGER)
    public static final String APP_FUNCTION_SERVICE = "app_function";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.content.integrity.AppIntegrityManager}.
     * @hide
     */
    @SystemApi
    public static final String APP_INTEGRITY_SERVICE = "app_integrity";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.content.pm.DataLoaderManager}.
     * @hide
     */
    public static final String DATA_LOADER_MANAGER_SERVICE = "dataloader_manager";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.os.incremental.IncrementalManager}.
     * @hide
     */
    public static final String INCREMENTAL_SERVICE = "incremental";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.security.attestationverification.AttestationVerificationManager}.
     * @see #getSystemService(String)
     * @see android.security.attestationverification.AttestationVerificationManager
     * @hide
     */
    public static final String ATTESTATION_VERIFICATION_SERVICE = "attestation_verification";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.security.advancedprotection.AdvancedProtectionManager}
     * @see #getSystemService(String)
     * @see android.security.advancedprotection.AdvancedProtectionManager
     */
    @FlaggedApi(android.security.Flags.FLAG_AAPM_API)
    public static final String ADVANCED_PROTECTION_SERVICE = "advanced_protection";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.security.FileIntegrityManager}.
     * @see #getSystemService(String)
     * @see android.security.FileIntegrityManager
     */
    public static final String FILE_INTEGRITY_SERVICE = "file_integrity";

    /**
     * Binder service for remote key provisioning.
     *
     * @see android.frameworks.rkp.IRemoteProvisioning
     * @hide
     */
    public static final String REMOTE_PROVISIONING_SERVICE = "remote_provisioning";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.lights.LightsManager} for controlling device lights.
     *
     * @see #getSystemService(String)
     * @hide
     */
    public static final String LIGHTS_SERVICE = "lights";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.uwb.UwbManager}.
     *
     * @see #getSystemService(String)
     * @hide
     */
    @SystemApi
    public static final String UWB_SERVICE = "uwb";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.ranging.RangingManager}.
     *
     * @see #getSystemService(String)
     * @hide
     */
    @FlaggedApi(com.android.ranging.flags.Flags.FLAG_RANGING_STACK_ENABLED)
    @SystemApi
    public static final String RANGING_SERVICE = "ranging";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.DreamManager} for controlling Dream states.
     *
     * @see #getSystemService(String)

     * @hide
     */
    @TestApi
    public static final String DREAM_SERVICE = "dream";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.telephony.SmsManager} for accessing Sms functionality.
     *
     * @see #getSystemService(String)

     * @hide
     */
    public static final String SMS_SERVICE = "sms";

    /**
     * Use with {@link #getSystemService(String)} to access a {@link PeopleManager} to interact
     * with your published conversations.
     *
     * @see #getSystemService(String)
     */
    public static final String PEOPLE_SERVICE = "people";

    /**
     * Use with {@link #getSystemService(String)} to access device state service.
     *
     * @see #getSystemService(String)
     * @hide
     */
    public static final String DEVICE_STATE_SERVICE = "device_state";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.metrics.MediaMetricsManager} for interacting with media metrics
     * on the device.
     *
     * @see #getSystemService(String)
     * @see android.media.metrics.MediaMetricsManager
     */
    public static final String MEDIA_METRICS_SERVICE = "media_metrics";

    /**
     * Use with {@link #getSystemService(String)} to access system speech recognition service.
     *
     * @see #getSystemService(String)
     * @hide
    */
    public static final String SPEECH_RECOGNITION_SERVICE = "speech_recognition";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link GameManager}.
     *
     * @see #getSystemService(String)
     */
    public static final String GAME_SERVICE = "game";

    /**
     * Use with {@link #getSystemService(String)} to access
     * {@link android.content.pm.verify.domain.DomainVerificationManager} to retrieve approval and
     * user state for declared web domains.
     *
     * @see #getSystemService(String)
     * @see android.content.pm.verify.domain.DomainVerificationManager
     */
    public static final String DOMAIN_VERIFICATION_SERVICE = "domain_verification";

    /**
     * Use with {@link #getSystemService(String)} to access
     * {@link android.view.displayhash.DisplayHashManager} to handle display hashes.
     *
     * @see #getSystemService(String)
     */
    public static final String DISPLAY_HASH_SERVICE = "display_hash";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.LocaleManager}.
     *
     * @see #getSystemService(String)
     */
    public static final String LOCALE_SERVICE = "locale";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.safetycenter.SafetyCenterManager} instance for interacting with the safety center.
     *
     * @see #getSystemService(String)
     * @see android.safetycenter.SafetyCenterManager
     * @hide
     */
    @SystemApi
    public static final String SAFETY_CENTER_SERVICE = "safety_center";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.nearby.NearbyManager} to discover nearby devices.
     *
     * @see #getSystemService(String)
     * @see android.nearby.NearbyManager
     * @hide
     */
    @SystemApi
    public static final String NEARBY_SERVICE = "nearby";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.remoteauth.RemoteAuthManager} to discover,
     * register and authenticate via remote authenticator  devices.
     *
     * @see #getSystemService(String)
     * @see android.remoteauth.RemoteAuthManager
     * @hide
     */
    public static final String REMOTE_AUTH_SERVICE = "remote_auth";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.ambientcontext.AmbientContextManager}.
     *
     * @see #getSystemService(String)
     * @see AmbientContextManager
     * @hide
     */
    @SystemApi
    public static final String AMBIENT_CONTEXT_SERVICE = "ambient_context";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.wearable.WearableSensingManager}.
     *
     * @see #getSystemService(String)
     * @see WearableSensingManager
     * @hide
     */
    @SystemApi
    public static final String WEARABLE_SENSING_SERVICE = "wearable_sensing";


    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.ondeviceintelligence.OnDeviceIntelligenceManager}.
     *
     * @see #getSystemService(String)
     * @see OnDeviceIntelligenceManager
     * @hide
     */
    @SystemApi
    @FlaggedApi(android.app.ondeviceintelligence.flags.Flags.FLAG_ENABLE_ON_DEVICE_INTELLIGENCE)
    public static final String ON_DEVICE_INTELLIGENCE_SERVICE = "on_device_intelligence";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.health.connect.HealthConnectManager}.
     *
     * @see #getSystemService(String)
     * @see android.health.connect.HealthConnectManager
     */
    public static final String HEALTHCONNECT_SERVICE = "healthconnect";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.credentials.CredentialManager} to authenticate a user to your app.
     *
     * @see #getSystemService(String)
     * @see CredentialManager
     */
    public static final String CREDENTIAL_SERVICE = "credential";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.devicelock.DeviceLockManager}.
     *
     * @see #getSystemService(String)
     */
    public static final String DEVICE_LOCK_SERVICE = "device_lock";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.system.virtualmachine.VirtualMachineManager}.
     *
     * <p>On devices without {@link PackageManager#FEATURE_VIRTUALIZATION_FRAMEWORK} system feature
     * the {@link #getSystemService(String)} will return {@code null}.
     *
     * @see #getSystemService(String)
     * @see android.system.virtualmachine.VirtualMachineManager
     * @hide
     */
    @SystemApi
    public static final String VIRTUALIZATION_SERVICE = "virtualization";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link GrammaticalInflectionManager}.
     *
     * @see #getSystemService(String)
     */
    public static final String GRAMMATICAL_INFLECTION_SERVICE = "grammatical_inflection";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.telephony.satellite.SatelliteManager} for accessing satellite functionality.
     *
     * @see #getSystemService(String)
     * @see android.telephony.satellite.SatelliteManager
     */
    @FlaggedApi(com.android.internal.telephony.flags.Flags.FLAG_SATELLITE_STATE_CHANGE_LISTENER)
    public static final String SATELLITE_SERVICE = "satellite";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.net.wifi.sharedconnectivity.app.SharedConnectivityManager} for accessing
     * shared connectivity services.
     *
     * @see #getSystemService(String)
     * @see android.net.wifi.sharedconnectivity.app.SharedConnectivityManager
     * @hide
     */
    @SystemApi
    public static final String SHARED_CONNECTIVITY_SERVICE = "shared_connectivity";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.SecurityStateManager} for accessing the security state manager service.
     *
     * @see #getSystemService(String)
     * @see android.os.SecurityStateManager
     */
    @FlaggedApi(Flags.FLAG_SECURITY_STATE_SERVICE)
    public static final String SECURITY_STATE_SERVICE = "security_state";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.app.ecm.EnhancedConfirmationManager}.
     *
     * @see #getSystemService(String)
     * @see android.app.ecm.EnhancedConfirmationManager
     * @hide
     */
    @FlaggedApi(android.permission.flags.Flags.FLAG_ENHANCED_CONFIRMATION_MODE_APIS_ENABLED)
    @SystemApi
    public static final String ECM_ENHANCED_CONFIRMATION_SERVICE = "ecm_enhanced_confirmation";

    /**
     * Service to protect sensitive content during screen share.
     * @hide
     */
    public static final String SENSITIVE_CONTENT_PROTECTION_SERVICE =
            "sensitive_content_protection_service";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link E2eeContactKeysManager} to managing contact keys.
     *
     * @see #getSystemService(String)
     * @see E2eeContactKeysManager
     */
    @FlaggedApi(android.provider.Flags.FLAG_USER_KEYS)
    public static final String CONTACT_KEYS_SERVICE = "contact_keys";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.os.ProfilingManager}.
     *
     * @see #getSystemService(String)
     */
    @FlaggedApi(android.os.Flags.FLAG_TELEMETRY_APIS_FRAMEWORK_INITIALIZATION)
    public static final String PROFILING_SERVICE = "profiling";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.webkit.WebViewUpdateManager} for accessing the WebView update service.
     *
     * <p>This can only be used on devices with {@link PackageManager#FEATURE_WEBVIEW}.
     *
     * @see #getSystemService(String)
     * @see android.webkit.WebViewUpdateManager
     * @hide
     */
    @FlaggedApi(android.webkit.Flags.FLAG_UPDATE_SERVICE_IPC_WRAPPER)
    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    @SuppressLint("ServiceName")
    public static final String WEBVIEW_UPDATE_SERVICE = "webviewupdate";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.provider.BlockedNumbersManager} for accessing the blocked number service.
     *
     * @see #getSystemService(String)
     * @see android.provider.BlockedNumbersManager
     * @hide
     */
    @FlaggedApi(
            com.android.server.telecom.flags.Flags.FLAG_TELECOM_MAINLINE_BLOCKED_NUMBERS_MANAGER)
    @SystemApi
    public static final String BLOCKED_NUMBERS_SERVICE = "blocked_numbers";

    /**
     * Use with {@link #getSystemService(String)} to retrieve the
     * {@link ProtoLogConfigurationService} for registering ProtoLog clients.
     *
     * @see #getSystemService(String)
     * @see ProtoLogConfigurationService
     * @hide
     */
    public static final String PROTOLOG_CONFIGURATION_SERVICE = "protolog_configuration";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.supervision.SupervisionManager}.
     *
     * @see #getSystemService(String)
     * @see android.app.supervision.SupervisionManager
     * @hide
     */
    @SystemApi
    @FlaggedApi(android.app.supervision.flags.Flags.FLAG_SUPERVISION_MANAGER_APIS)
    public static final String SUPERVISION_SERVICE = "supervision";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.quality.MediaQuality} for standardize picture and audio
     * API parameters.
     *
     * @see #getSystemService(String)
     * @see android.media.quality.MediaQuality
     */
    @FlaggedApi(android.media.tv.flags.Flags.FLAG_MEDIA_QUALITY_FW)
    public static final String MEDIA_QUALITY_SERVICE = "media_quality";

    /**
     * Service to perform operations needed for dynamic instrumentation.
     * @hide
     */
    public static final String DYNAMIC_INSTRUMENTATION_SERVICE = "dynamic_instrumentation";

    /**
     * Determine whether the given permission is allowed for a particular
     * process and user ID running in the system.
     *
     * @param permission The name of the permission being checked.
     * @param pid The process ID being checked against.  Must be > 0.
     * @param uid The UID being checked against.  A uid of 0 is the root
     * user, which will pass every permission check.
     *
     * @return {@link PackageManager#PERMISSION_GRANTED} if the given
     * pid/uid is allowed that permission, or
     * {@link PackageManager#PERMISSION_DENIED} if it is not.
     *
     * @see PackageManager#checkPermission(String, String)
     * @see #checkCallingPermission
     */
    @CheckResult(suggest="#enforcePermission(String,int,int,String)")
    @PermissionMethod
    public int checkPermission(
            @NonNull @PermissionName String permission, int pid, int uid) {
        return ContextBinding.checkPermission(this, permission, pid, uid);
    }

    /** @hide */
    @SuppressWarnings("HiddenAbstractMethod")
    @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
    public int checkPermission(@NonNull String permission, int pid, int uid,
            IBinder callerToken) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Determine whether the calling process of an IPC you are handling has been
     * granted a particular permission.  This is basically the same as calling
     * {@link #checkPermission(String, int, int)} with the pid and uid returned
     * by {@link android.os.Binder#getCallingPid} and
     * {@link android.os.Binder#getCallingUid}.  One important difference
     * is that if you are not currently processing an IPC, this function
     * will always fail.  This is done to protect against accidentally
     * leaking permissions; you can use {@link #checkCallingOrSelfPermission}
     * to avoid this protection.
     *
     * @param permission The name of the permission being checked.
     *
     * @return {@link PackageManager#PERMISSION_GRANTED} if the calling
     * pid/uid is allowed that permission, or
     * {@link PackageManager#PERMISSION_DENIED} if it is not.
     *
     * @see PackageManager#checkPermission(String, String)
     * @see #checkPermission
     * @see #checkCallingOrSelfPermission
     */
    @CheckResult(suggest="#enforceCallingPermission(String,String)")
    @PermissionMethod
    public int checkCallingPermission(@NonNull @PermissionName String permission) {
        return ContextBinding.checkCallingPermission(this, permission);
    }

    /**
     * Determine whether the calling process of an IPC <em>or you</em> have been
     * granted a particular permission.  This is the same as
     * {@link #checkCallingPermission}, except it grants your own permissions
     * if you are not currently processing an IPC.  Use with care!
     *
     * @param permission The name of the permission being checked.
     *
     * @return {@link PackageManager#PERMISSION_GRANTED} if the calling
     * pid/uid is allowed that permission, or
     * {@link PackageManager#PERMISSION_DENIED} if it is not.
     *
     * @see PackageManager#checkPermission(String, String)
     * @see #checkPermission
     * @see #checkCallingPermission
     */
    @CheckResult(suggest="#enforceCallingOrSelfPermission(String,String)")
    @PermissionMethod(orSelf = true)
    public int checkCallingOrSelfPermission(@NonNull @PermissionName String permission) {
        return ContextBinding.checkCallingOrSelfPermission(this, permission);
    }

    /**
     * Determine whether <em>you</em> have been granted a particular permission.
     *
     * @param permission The name of the permission being checked.
     *
     * @return {@link PackageManager#PERMISSION_GRANTED} if you have the
     * permission, or {@link PackageManager#PERMISSION_DENIED} if not.
     *
     * @see PackageManager#checkPermission(String, String)
     * @see #checkCallingPermission(String)
     */
    public int checkSelfPermission(@NonNull String permission) {
        if (Build.VERSION.SDK_INT < 23) {
            throw new NoSuchMethodError("checkSelfPermission(Ljava/lang/String;)I");
        }
        return ContextBinding.checkSelfPermission(this, permission);
    }

    /**
     * If the given permission is not allowed for a particular process
     * and user ID running in the system, throw a {@link SecurityException}.
     *
     * @param permission The name of the permission being checked.
     * @param pid The process ID being checked against.  Must be &gt; 0.
     * @param uid The UID being checked against.  A uid of 0 is the root
     * user, which will pass every permission check.
     * @param message A message to include in the exception if it is thrown.
     *
     * @see #checkPermission(String, int, int)
     */
    @PermissionMethod
    public void enforcePermission(
            @NonNull @PermissionName String permission, int pid, int uid, @Nullable String message) {
        ContextBinding.enforcePermission(this, permission, pid, uid, message);
    }

    /**
     * If the calling process of an IPC you are handling has not been
     * granted a particular permission, throw a {@link
     * SecurityException}.  This is basically the same as calling
     * {@link #enforcePermission(String, int, int, String)} with the
     * pid and uid returned by {@link android.os.Binder#getCallingPid}
     * and {@link android.os.Binder#getCallingUid}.  One important
     * difference is that if you are not currently processing an IPC,
     * this function will always throw the SecurityException.  This is
     * done to protect against accidentally leaking permissions; you
     * can use {@link #enforceCallingOrSelfPermission} to avoid this
     * protection.
     *
     * @param permission The name of the permission being checked.
     * @param message A message to include in the exception if it is thrown.
     *
     * @see #checkCallingPermission(String)
     */
    @PermissionMethod
    public void enforceCallingPermission(
            @NonNull @PermissionName String permission, @Nullable String message) {
        ContextBinding.enforceCallingPermission(this, permission, message);
    }

    /**
     * If neither you nor the calling process of an IPC you are
     * handling has been granted a particular permission, throw a
     * {@link SecurityException}.  This is the same as {@link
     * #enforceCallingPermission}, except it grants your own
     * permissions if you are not currently processing an IPC.  Use
     * with care!
     *
     * @param permission The name of the permission being checked.
     * @param message A message to include in the exception if it is thrown.
     *
     * @see #checkCallingOrSelfPermission(String)
     */
    @PermissionMethod(orSelf = true)
    public void enforceCallingOrSelfPermission(
            @NonNull @PermissionName String permission, @Nullable String message) {
        ContextBinding.enforceCallingOrSelfPermission(this, permission, message);
    }

    /**
     * Returns the permission request state for a given runtime permission. This method provides a
     * streamlined mechanism for applications to determine whether a permission can be
     * requested (i.e. whether the user will be prompted with a permission dialog).
     *
     * <p>Traditionally, determining if a permission has been permanently denied (unrequestable)
     * required applications to initiate a permission request and subsequently analyze the result
     * of {@link android.app.Activity#shouldShowRequestPermissionRationale} in conjunction with the
     * grant result within the {@link android.app.Activity#onRequestPermissionsResult} callback.
     *
     * @param permission The name of the permission.
     *
     * @return The current request state of the specified permission, represented by one of the
     * following constants: {@link PermissionRequestState#PERMISSION_REQUEST_STATE_GRANTED},
     * {@link PermissionRequestState#PERMISSION_REQUEST_STATE_REQUESTABLE}, or
     * {@link PermissionRequestState#PERMISSION_REQUEST_STATE_UNREQUESTABLE}.
     *
     * @hide
     */
    @CheckResult
    @PermissionRequestState
    public int getPermissionRequestState(@NonNull String permission) {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    /**
     * Grant permission to access a specific Uri to another package, regardless
     * of whether that package has general permission to access the Uri's
     * content provider.  This can be used to grant specific, temporary
     * permissions, typically in response to user interaction (such as the
     * user opening an attachment that you would like someone else to
     * display).
     *
     * <p>Normally you should use {@link Intent#FLAG_GRANT_READ_URI_PERMISSION
     * Intent.FLAG_GRANT_READ_URI_PERMISSION} or
     * {@link Intent#FLAG_GRANT_WRITE_URI_PERMISSION
     * Intent.FLAG_GRANT_WRITE_URI_PERMISSION} with the Intent being used to
     * start an activity instead of this function directly.  If you use this
     * function directly, you should be sure to call
     * {@link #revokeUriPermission} when the target should no longer be allowed
     * to access it.
     *
     * <p>To succeed, the content provider owning the Uri must have set the
     * {@link android.R.styleable#AndroidManifestProvider_grantUriPermissions
     * grantUriPermissions} attribute in its manifest or included the
     * {@link android.R.styleable#AndroidManifestGrantUriPermission
     * &lt;grant-uri-permissions&gt;} tag.
     *
     * @param toPackage The package you would like to allow to access the Uri.
     * @param uri The Uri you would like to grant access to.
     * @param modeFlags The desired access modes.
     *
     * @see #revokeUriPermission
     */
    public void grantUriPermission(String toPackage, Uri uri,
            int modeFlags) {
        throw new UnsupportedOperationException(
                "grantUriPermission(Ljava/lang/String;Landroid/net/Uri;I)V");
    }

    /**
     * Remove all permissions to access a particular content provider Uri
     * that were previously added with {@link #grantUriPermission} or <em>any other</em> mechanism.
     * The given Uri will match all previously granted Uris that are the same or a
     * sub-path of the given Uri.  That is, revoking "content://foo/target" will
     * revoke both "content://foo/target" and "content://foo/target/sub", but not
     * "content://foo".  It will not remove any prefix grants that exist at a
     * higher level.
     *
     * <p>Prior to {@link android.os.Build.VERSION_CODES#LOLLIPOP}, if you did not have
     * regular permission access to a Uri, but had received access to it through
     * a specific Uri permission grant, you could not revoke that grant with this
     * function and a {@link SecurityException} would be thrown.  As of
     * {@link android.os.Build.VERSION_CODES#LOLLIPOP}, this function will not throw a security
     * exception, but will remove whatever permission grants to the Uri had been given to the app
     * (or none).</p>
     *
     * <p>Unlike {@link #revokeUriPermission(String, Uri, int)}, this method impacts all permission
     * grants matching the given Uri, for any package they had been granted to, through any
     * mechanism this had happened (such as indirectly through the clipboard, activity launch,
     * service start, etc).  That means this can be potentially dangerous to use, as it can
     * revoke grants that another app could be strongly expecting to stick around.</p>
     *
     * @param uri The Uri you would like to revoke access to.
     * @param modeFlags The access modes to revoke.
     *
     * @see #grantUriPermission
     */
    public void revokeUriPermission(Uri uri, int modeFlags) {
        throw new UnsupportedOperationException("revokeUriPermission(Landroid/net/Uri;I)V");
    }

    /**
     * Remove permissions to access a particular content provider Uri
     * that were previously added with {@link #grantUriPermission} for a specific target
     * package.  The given Uri will match all previously granted Uris that are the same or a
     * sub-path of the given Uri.  That is, revoking "content://foo/target" will
     * revoke both "content://foo/target" and "content://foo/target/sub", but not
     * "content://foo".  It will not remove any prefix grants that exist at a
     * higher level.
     *
     * <p>Unlike {@link #revokeUriPermission(Uri, int)}, this method will <em>only</em>
     * revoke permissions that had been explicitly granted through {@link #grantUriPermission}
     * and only for the package specified.  Any matching grants that have happened through
     * other mechanisms (clipboard, activity launching, service starting, etc) will not be
     * removed.</p>
     *
     * @param toPackage The package you had previously granted access to.
     * @param uri The Uri you would like to revoke access to.
     * @param modeFlags The access modes to revoke.
     *
     * @see #grantUriPermission
     */
    public void revokeUriPermission(String toPackage, Uri uri,
            int modeFlags) {
        if (Build.VERSION.SDK_INT < 26) {
            throw new NoSuchMethodError(
                    "revokeUriPermission(Ljava/lang/String;Landroid/net/Uri;I)V");
        }
        throw new UnsupportedOperationException(
                "revokeUriPermission(Ljava/lang/String;Landroid/net/Uri;I)V");
    }

    /**
     * Determine whether a particular process and uid has been granted
     * permission to access a specific URI.  This only checks for permissions
     * that have been explicitly granted -- if the given process/uid has
     * more general access to the URI's content provider then this check will
     * always fail.
     *
     * @param uri The uri that is being checked.
     * @param pid The process ID being checked against.  Must be &gt; 0.
     * @param uid The UID being checked against.  A uid of 0 is the root
     * user, which will pass every permission check.
     * @param modeFlags The access modes to check.
     *
     * @return {@link PackageManager#PERMISSION_GRANTED} if the given
     * pid/uid is allowed to access that uri, or
     * {@link PackageManager#PERMISSION_DENIED} if it is not.
     *
     * @see #checkCallingUriPermission
     */
    @CheckResult(suggest="#enforceUriPermission(Uri,int,int,String)")
    public int checkUriPermission(Uri uri, int pid, int uid,
            int modeFlags) {
        throw new UnsupportedOperationException("checkUriPermission(Landroid/net/Uri;III)I");
    }

    /**
     * Determine whether a particular process and uid has been granted
     * permission to access a specific content URI.
     *
     * <p>Unlike {@link #checkUriPermission(Uri, int, int, int)}, this method
     * checks for general access to the URI's content provider, as well as
     * explicitly granted permissions.</p>
     *
     * <p>Note, this check will throw an {@link IllegalArgumentException}
     * for non-content URIs.</p>
     *
     * @param uri The content uri that is being checked.
     * @param pid (Optional) The process ID being checked against. If the
     * pid is unknown, pass -1.
     * @param uid The UID being checked against.  A uid of 0 is the root
     * user, which will pass every permission check.
     * @param modeFlags The access modes to check.
     *
     * @return {@link PackageManager#PERMISSION_GRANTED} if the given
     * pid/uid is allowed to access that uri, or
     * {@link PackageManager#PERMISSION_DENIED} if it is not.
     *
     * @see #checkUriPermission(Uri, int, int, int)
     */
    @FlaggedApi(android.security.Flags.FLAG_CONTENT_URI_PERMISSION_APIS)
    public int checkContentUriPermissionFull(@NonNull Uri uri, int pid, int uid,
            int modeFlags) {
        if (Build.VERSION.SDK_INT < 35) {
            throw new NoSuchMethodError("checkContentUriPermissionFull(Landroid/net/Uri;III)I");
        }
        throw new UnsupportedOperationException(
                "checkContentUriPermissionFull(Landroid/net/Uri;III)I");
    }

    /**
     * Determine whether a particular process and uid has been granted
     * permission to access a list of URIs.  This only checks for permissions
     * that have been explicitly granted -- if the given process/uid has
     * more general access to the URI's content provider then this check will
     * always fail.
     *
     * <strong>Note:</strong> On SDK Version {@link android.os.Build.VERSION_CODES#S},
     * calling this method from a secondary-user's context will incorrectly return
     * {@link PackageManager#PERMISSION_DENIED} for all {code uris}.
     *
     * @param uris The list of URIs that is being checked.
     * @param pid The process ID being checked against.  Must be &gt; 0.
     * @param uid The UID being checked against.  A uid of 0 is the root
     * user, which will pass every permission check.
     * @param modeFlags The access modes to check for the list of uris
     *
     * @return Array of permission grants corresponding to each entry in the list of uris.
     * {@link PackageManager#PERMISSION_GRANTED} if the given pid/uid is allowed to access that uri,
     * or {@link PackageManager#PERMISSION_DENIED} if it is not.
     *
     * @see #checkCallingUriPermission
     */
    @NonNull
    public int[] checkUriPermissions(@NonNull List<Uri> uris, int pid, int uid,
            int modeFlags) {
        if (Build.VERSION.SDK_INT < 31) {
            throw new NoSuchMethodError("checkUriPermissions(Ljava/util/List;III)[I");
        }
        throw new UnsupportedOperationException("checkUriPermissions(Ljava/util/List;III)[I");
    }

    /** @hide */
    @SuppressWarnings("HiddenAbstractMethod")
    public int checkUriPermission(Uri uri, int pid, int uid,
            int modeFlags, IBinder callerToken) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Determine whether the calling process and uid has been
     * granted permission to access a specific URI.  This is basically
     * the same as calling {@link #checkUriPermission(Uri, int, int,
     * int)} with the pid and uid returned by {@link
     * android.os.Binder#getCallingPid} and {@link
     * android.os.Binder#getCallingUid}.  One important difference is
     * that if you are not currently processing an IPC, this function
     * will always fail.
     *
     * @param uri The uri that is being checked.
     * @param modeFlags The access modes to check.
     *
     * @return {@link PackageManager#PERMISSION_GRANTED} if the caller
     * is allowed to access that uri, or
     * {@link PackageManager#PERMISSION_DENIED} if it is not.
     *
     * @see #checkUriPermission(Uri, int, int, int)
     */
    @CheckResult(suggest="#enforceCallingUriPermission(Uri,int,String)")
    public int checkCallingUriPermission(Uri uri, int modeFlags) {
        throw new UnsupportedOperationException("checkCallingUriPermission(Landroid/net/Uri;I)I");
    }

    /**
     * Determine whether the calling process and uid has been
     * granted permission to access a list of URIs.  This is basically
     * the same as calling {@link #checkUriPermissions(List, int, int, int)}
     * with the pid and uid returned by {@link
     * android.os.Binder#getCallingPid} and {@link
     * android.os.Binder#getCallingUid}.  One important difference is
     * that if you are not currently processing an IPC, this function
     * will always fail.
     *
     * @param uris The list of URIs that is being checked.
     * @param modeFlags The access modes to check.
     *
     * @return Array of permission grants corresponding to each entry in the list of uris.
     * {@link PackageManager#PERMISSION_GRANTED} if the given pid/uid is allowed to access that uri,
     * or {@link PackageManager#PERMISSION_DENIED} if it is not.
     *
     * @see #checkUriPermission(Uri, int, int, int)
     */
    @NonNull
    public int[] checkCallingUriPermissions(@NonNull List<Uri> uris,
            int modeFlags) {
        if (Build.VERSION.SDK_INT < 31) {
            throw new NoSuchMethodError("checkCallingUriPermissions(Ljava/util/List;I)[I");
        }
        throw new UnsupportedOperationException("checkCallingUriPermissions(Ljava/util/List;I)[I");
    }

    /**
     * Determine whether the calling process of an IPC <em>or you</em> has been granted
     * permission to access a specific URI.  This is the same as
     * {@link #checkCallingUriPermission}, except it grants your own permissions
     * if you are not currently processing an IPC.  Use with care!
     *
     * @param uri The uri that is being checked.
     * @param modeFlags The access modes to check.
     *
     * @return {@link PackageManager#PERMISSION_GRANTED} if the caller
     * is allowed to access that uri, or
     * {@link PackageManager#PERMISSION_DENIED} if it is not.
     *
     * @see #checkCallingUriPermission
     */
    @CheckResult(suggest="#enforceCallingOrSelfUriPermission(Uri,int,String)")
    public int checkCallingOrSelfUriPermission(Uri uri,
            int modeFlags) {
        throw new UnsupportedOperationException(
                "checkCallingOrSelfUriPermission(Landroid/net/Uri;I)I");
    }

    /**
     * Determine whether the calling process of an IPC <em>or you</em> has been granted
     * permission to access a list of URIs.  This is the same as
     * {@link #checkCallingUriPermission}, except it grants your own permissions
     * if you are not currently processing an IPC.  Use with care!
     *
     * @param uris The list of URIs that is being checked.
     * @param modeFlags The access modes to check.
     *
     * @return Array of permission grants corresponding to each entry in the list of uris.
     * {@link PackageManager#PERMISSION_GRANTED} if the given pid/uid is allowed to access that uri,
     * or {@link PackageManager#PERMISSION_DENIED} if it is not.
     *
     * @see #checkCallingUriPermission
     */
    @NonNull
    public int[] checkCallingOrSelfUriPermissions(@NonNull List<Uri> uris,
            int modeFlags) {
        if (Build.VERSION.SDK_INT < 31) {
            throw new NoSuchMethodError("checkCallingOrSelfUriPermissions(Ljava/util/List;I)[I");
        }
        throw new UnsupportedOperationException(
                "checkCallingOrSelfUriPermissions(Ljava/util/List;I)[I");
    }

    /**
     * Check both a Uri and normal permission.  This allows you to perform
     * both {@link #checkPermission} and {@link #checkUriPermission} in one
     * call.
     *
     * @param uri The Uri whose permission is to be checked, or null to not
     * do this check.
     * @param readPermission The permission that provides overall read access,
     * or null to not do this check.
     * @param writePermission The permission that provides overall write
     * access, or null to not do this check.
     * @param pid The process ID being checked against.  Must be &gt; 0.
     * @param uid The UID being checked against.  A uid of 0 is the root
     * user, which will pass every permission check.
     * @param modeFlags The access modes to check.
     *
     * @return {@link PackageManager#PERMISSION_GRANTED} if the caller
     * is allowed to access that uri or holds one of the given permissions, or
     * {@link PackageManager#PERMISSION_DENIED} if it is not.
     */
    @CheckResult(suggest="#enforceUriPermission(Uri,String,String,int,int,int,String)")
    public int checkUriPermission(@Nullable Uri uri, @Nullable String readPermission,
            @Nullable String writePermission, int pid, int uid,
            int modeFlags) {
        throw new UnsupportedOperationException(
                "checkUriPermission(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;III)I");
    }

    /**
     * If a particular process and uid has not been granted
     * permission to access a specific URI, throw {@link
     * SecurityException}.  This only checks for permissions that have
     * been explicitly granted -- if the given process/uid has more
     * general access to the URI's content provider then this check
     * will always fail.
     *
     * @param uri The uri that is being checked.
     * @param pid The process ID being checked against.  Must be &gt; 0.
     * @param uid The UID being checked against.  A uid of 0 is the root
     * user, which will pass every permission check.
     * @param modeFlags The access modes to enforce.
     * @param message A message to include in the exception if it is thrown.
     *
     * @see #checkUriPermission(Uri, int, int, int)
     */
    public void enforceUriPermission(
            Uri uri, int pid, int uid, int modeFlags, String message) {
        throw new UnsupportedOperationException(
                "enforceUriPermission(Landroid/net/Uri;IIILjava/lang/String;)V");
    }

    /**
     * If the calling process and uid has not been granted
     * permission to access a specific URI, throw {@link
     * SecurityException}.  This is basically the same as calling
     * {@link #enforceUriPermission(Uri, int, int, int, String)} with
     * the pid and uid returned by {@link
     * android.os.Binder#getCallingPid} and {@link
     * android.os.Binder#getCallingUid}.  One important difference is
     * that if you are not currently processing an IPC, this function
     * will always throw a SecurityException.
     *
     * @param uri The uri that is being checked.
     * @param modeFlags The access modes to enforce.
     * @param message A message to include in the exception if it is thrown.
     *
     * @see #checkCallingUriPermission(Uri, int)
     */
    public void enforceCallingUriPermission(
            Uri uri, int modeFlags, String message) {
        throw new UnsupportedOperationException(
                "enforceCallingUriPermission(Landroid/net/Uri;ILjava/lang/String;)V");
    }

    /**
     * If the calling process of an IPC <em>or you</em> has not been
     * granted permission to access a specific URI, throw {@link
     * SecurityException}.  This is the same as {@link
     * #enforceCallingUriPermission}, except it grants your own
     * permissions if you are not currently processing an IPC.  Use
     * with care!
     *
     * @param uri The uri that is being checked.
     * @param modeFlags The access modes to enforce.
     * @param message A message to include in the exception if it is thrown.
     *
     * @see #checkCallingOrSelfUriPermission(Uri, int)
     */
    public void enforceCallingOrSelfUriPermission(
            Uri uri, int modeFlags, String message) {
        throw new UnsupportedOperationException(
                "enforceCallingOrSelfUriPermission(Landroid/net/Uri;ILjava/lang/String;)V");
    }

    /**
     * Enforce both a Uri and normal permission.  This allows you to perform
     * both {@link #enforcePermission} and {@link #enforceUriPermission} in one
     * call.
     *
     * @param uri The Uri whose permission is to be checked, or null to not
     * do this check.
     * @param readPermission The permission that provides overall read access,
     * or null to not do this check.
     * @param writePermission The permission that provides overall write
     * access, or null to not do this check.
     * @param pid The process ID being checked against.  Must be &gt; 0.
     * @param uid The UID being checked against.  A uid of 0 is the root
     * user, which will pass every permission check.
     * @param modeFlags The access modes to enforce.
     * @param message A message to include in the exception if it is thrown.
     *
     * @see #checkUriPermission(Uri, String, String, int, int, int)
     */
    public void enforceUriPermission(
            @Nullable Uri uri, @Nullable String readPermission,
            @Nullable String writePermission, int pid, int uid, int modeFlags,
            @Nullable String message) {
        throw new UnsupportedOperationException(
                "enforceUriPermission(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;IIILjava/lang/String;)V");
    }


    /**
     * Triggers the asynchronous revocation of a runtime permission. If the permission is not
     * currently granted, nothing happens (even if later granted by the user).
     *
     * @param permName The name of the permission to be revoked.
     * @see #revokeSelfPermissionsOnKill(Collection)
     * @throws IllegalArgumentException if the permission is not a runtime permission
     */
    public void revokeSelfPermissionOnKill(@NonNull String permName) {
        if (Build.VERSION.SDK_INT < 33) {
            throw new NoSuchMethodError("revokeSelfPermissionOnKill(Ljava/lang/String;)V");
        }
        ContextBinding.revokeSelfPermissionOnKill(this, permName);
    }

    /**
     * Triggers the revocation of one or more permissions for the calling package. A package is only
     * able to revoke runtime permissions. If a permission is not currently granted, it is ignored
     * and will not get revoked (even if later granted by the user). Ultimately, you should never
     * make assumptions about a permission status as users may grant or revoke them at any time.
     * <p>
     * Background permissions which have no corresponding foreground permission still granted once
     * the revocation is effective will also be revoked.
     * <p>
     * The revocation happens asynchronously and kills all processes running in the calling UID. It
     * will be triggered once it is safe to do so. In particular, it will not be triggered as long
     * as the package remains in the foreground, or has any active manifest components (e.g. when
     * another app is accessing a content provider in the package).
     * <p>
     * If you want to revoke the permissions right away, you could call {@code System.exit()} in
     * {@code Handler.postDelayed} with a delay to allow completion of async IPC, But
     * {@code System.exit()} could affect other apps that are accessing your app at the moment.
     * For example, apps accessing a content provider in your app will all crash.
     * <p>
     * Note that the settings UI shows a permission group as granted as long as at least one
     * permission in the group is granted. If you want the user to observe the revocation in the
     * settings, you should revoke every permission in the target group. To learn the current list
     * of permissions in a group, you may use
     * {@link PackageManager#getGroupOfPlatformPermission(String, Executor, Consumer)} and
     * {@link PackageManager#getPlatformPermissionsForGroup(String, Executor, Consumer)}. This list
     * of permissions may evolve over time, so it is recommended to check whether it contains any
     * permission you wish to retain before trying to revoke an entire group.
     *
     * @param permissions Collection of permissions to be revoked.
     * @see PackageManager#getGroupOfPlatformPermission(String, Executor, Consumer)
     * @see PackageManager#getPlatformPermissionsForGroup(String, Executor, Consumer)
     * @throws IllegalArgumentException if any of the permissions is not a runtime permission
     */
    public void revokeSelfPermissionsOnKill(@NonNull Collection<String> permissions) {
        if (Build.VERSION.SDK_INT < 33) {
            throw new NoSuchMethodError("revokeSelfPermissionsOnKill(Ljava/util/Collection;)V");
        }
        throw new UnsupportedOperationException(
                "revokeSelfPermissionsOnKill(Ljava/util/Collection;)V");
    }

    /** @hide */
    @IntDef(flag = true, prefix = { "CONTEXT_" }, value = {
            CONTEXT_INCLUDE_CODE,
            CONTEXT_IGNORE_SECURITY,
            CONTEXT_RESTRICTED,
            CONTEXT_DEVICE_PROTECTED_STORAGE,
            CONTEXT_CREDENTIAL_PROTECTED_STORAGE,
            CONTEXT_REGISTER_PACKAGE,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface CreatePackageOptions {}

    /**
     * Flag for use with {@link #createPackageContext}: include the application
     * code with the context.  This means loading code into the caller's
     * process, so that {@link #getClassLoader()} can be used to instantiate
     * the application's classes.  Setting this flags imposes security
     * restrictions on what application context you can access; if the
     * requested application can not be safely loaded into your process,
     * java.lang.SecurityException will be thrown.  If this flag is not set,
     * there will be no restrictions on the packages that can be loaded,
     * but {@link #getClassLoader} will always return the default system
     * class loader.
     */
    public static final int CONTEXT_INCLUDE_CODE = 0x00000001;

    /**
     * Flag for use with {@link #createPackageContext}: ignore any security
     * restrictions on the Context being requested, allowing it to always
     * be loaded.  For use with {@link #CONTEXT_INCLUDE_CODE} to allow code
     * to be loaded into a process even when it isn't safe to do so.  Use
     * with extreme care!
     */
    public static final int CONTEXT_IGNORE_SECURITY = 0x00000002;

    /**
     * Flag for use with {@link #createPackageContext}: a restricted context may
     * disable specific features. For instance, a View associated with a restricted
     * context would ignore particular XML attributes.
     */
    public static final int CONTEXT_RESTRICTED = 0x00000004;

    /**
     * Flag for use with {@link #createPackageContext}: point all file APIs at
     * device-protected storage.
     *
     * @hide
     */
    public static final int CONTEXT_DEVICE_PROTECTED_STORAGE = 0x00000008;

    /**
     * Flag for use with {@link #createPackageContext}: point all file APIs at
     * credential-protected storage.
     *
     * @hide
     */
    public static final int CONTEXT_CREDENTIAL_PROTECTED_STORAGE = 0x00000010;

    /**
     * @hide Used to indicate we should tell the activity manager about the process
     * loading this code.
     */
    public static final int CONTEXT_REGISTER_PACKAGE = 0x40000000;

    /**
     * Similar to {@link #createPackageContext(String, int)}, but for the own package with a
     * different {@link UserHandle}. For example, {@link #getContentResolver()}
     * will open any {@link Uri} as the given user.
     *
     * @hide
     */
    @SystemApi
    @NonNull
    public Context createContextAsUser(@NonNull UserHandle user, @CreatePackageOptions int flags) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Get the user associated with this context.
     *
     * @return the user associated with this context
     *
     * @hide
     */
    @NonNull
    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    @TestApi
    public UserHandle getUser() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Get the user associated with this context
     * @hide
     */
    @UnsupportedAppUsage
    @TestApi
    public @UserIdInt int getUserId() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Returns a new {@code Context} object from the current context but with resources
     * adjusted to match the metrics of {@code display}. Each call to this method
     * returns a new instance of a context object. Context objects are not shared; however,
     * common state (such as the {@link ClassLoader} and other resources for the same
     * configuration) can be shared, so the {@code Context} itself is lightweight.
     *
     * <p><b>Note:</b>
     * This {@code Context} is <b>not</b> expected to be updated with new configuration if the
     * underlying display configuration changes and the cached {@code Resources} it returns
     * could be stale. It is suggested to use
     * {@link android.hardware.display.DisplayManager.DisplayListener} to listen for
     * changes and re-create an instance if necessary. </p>
     * <p>
     * This {@code Context} is <b>not</b> a UI context, do not use it to access UI components
     * or obtain a {@link WindowManager} instance.
     * </p><p>
     * To obtain an instance of {@link WindowManager} configured to show windows on the given
     * display, call {@link #createWindowContext(int, Bundle)} on the returned display context,
     * then call {@link #getSystemService(String)} or {@link #getSystemService(Class)} on the
     * returned window context.
     * </p>
     * @param display The display to which the current context's resources are adjusted.
     *
     * @return A context for the display.
     */
    @DisplayContext
    public Context createDisplayContext(@NonNull Display display) {
        return ContextBinding.createDisplayContext(this, display);
    }

    /**
     * Returns a new {@code Context} object from the current context but with device association
     * given by the {@code deviceId}. Each call to this method returns a new instance of a context
     * object. Context objects are not shared; however, common state (such as the
     * {@link ClassLoader} and other resources for the same configuration) can be shared, so the
     * {@code Context} itself is lightweight.
     * <p>
     * Applications that run on virtual devices may use this method to access the default device
     * capabilities and functionality (by passing
     * {@link Context#DEVICE_ID_DEFAULT}. Similarly,
     * applications running on the default device may access the functionality of virtual devices.
     * </p>
     * <p>
     * Note that the newly created instance will be associated with the same display as the parent
     * Context, regardless of the device ID passed here.
     * </p>
     * @param deviceId The ID of the device to associate with this context.
     * @return A context associated with the given device ID.
     *
     * @see #getDeviceId()
     * @see VirtualDeviceManager#getVirtualDevices()
     * @throws IllegalArgumentException if the given device ID is not a valid ID of the default
     * device or a virtual device.
     */
    public @NonNull Context createDeviceContext(int deviceId) {
        if (Build.VERSION.SDK_INT < 34) {
            throw new NoSuchMethodError("createDeviceContext(I)Landroid/content/Context;");
        }
        return ContextBinding.createDeviceContext(this, deviceId);
    }

    /**
     * Creates a Context for a non-activity window.
     *
     * <p>
     * A window context is a context that can be used to add non-activity windows, such as
     * {@link android.view.WindowManager.LayoutParams#TYPE_APPLICATION_OVERLAY}. A window context
     * must be created from a context that has an associated {@link Display}, such as
     * {@link android.app.Activity Activity} or a context created with
     * {@link #createDisplayContext(Display)}.
     *
     * <p>
     * The window context is created with the appropriate {@link Configuration} for the area of the
     * display that the windows created with it can occupy; it must be used when
     * {@link android.view.LayoutInflater inflating} views, such that they can be inflated with
     * proper {@link Resources}.
     *
     * Below is a sample code to <b>add an application overlay window on the primary display:</b>
     * <pre class="prettyprint">
     * ...
     * final DisplayManager dm = anyContext.getSystemService(DisplayManager.class);
     * final Display primaryDisplay = dm.getDisplay(DEFAULT_DISPLAY);
     * final Context windowContext = anyContext.createDisplayContext(primaryDisplay)
     *         .createWindowContext(TYPE_APPLICATION_OVERLAY, null);
     * final View overlayView = Inflater.from(windowContext).inflate(someLayoutXml, null);
     *
     * // WindowManager.LayoutParams initialization
     * ...
     * // The types used in addView and createWindowContext must match.
     * mParams.type = TYPE_APPLICATION_OVERLAY;
     * ...
     *
     * windowContext.getSystemService(WindowManager.class).addView(overlayView, mParams);
     * </pre>
     *
     * <p>
     * This context's configuration and resources are adjusted to an area of the display where
     * the windows with provided type will be added. <b>Note that all windows associated with the
     * same context will have an affinity and can only be moved together between different displays
     * or areas on a display.</b> If there is a need to add different window types, or
     * non-associated windows, separate Contexts should be used.
     * </p>
     * <p>
     * Creating a window context is an expensive operation. Misuse of this API may lead to a huge
     * performance drop. The best practice is to use the same window context when possible.
     * An approach is to create one window context with specific window type and display and
     * use it everywhere it's needed.
     * </p>
     * <p>
     * After {@link Build.VERSION_CODES#S}, window context provides the capability to receive
     * configuration changes for existing token by overriding the
     * {@link android.view.WindowManager.LayoutParams#token token} of the
     * {@link android.view.WindowManager.LayoutParams} passed in
     * {@link WindowManager#addView(View, LayoutParams)}. This is useful when an application needs
     * to attach its window to an existing activity for window token sharing use-case.
     * </p>
     * <p>
     * Note that the window context in {@link Build.VERSION_CODES#R} didn't have this
     * capability. This is a no-op for the window context in {@link Build.VERSION_CODES#R}.
     * </p>
     * Below is sample code to <b>attach an existing token to a window context:</b>
     * <pre class="prettyprint">
     * final DisplayManager dm = anyContext.getSystemService(DisplayManager.class);
     * final Display primaryDisplay = dm.getDisplay(DEFAULT_DISPLAY);
     * final Context windowContext = anyContext.createWindowContext(primaryDisplay,
     *         TYPE_APPLICATION, null);
     *
     * // Get an existing token.
     * final IBinder existingToken = activity.getWindow().getAttributes().token;
     *
     * // The types used in addView() and createWindowContext() must match.
     * final WindowManager.LayoutParams params = new WindowManager.LayoutParams(TYPE_APPLICATION);
     * params.token = existingToken;
     *
     * // After WindowManager#addView(), the server side will extract the provided token from
     * // LayoutParams#token (existingToken in the sample code), and switch to propagate
     * // configuration changes from the node associated with the provided token.
     * windowContext.getSystemService(WindowManager.class).addView(overlayView, mParams);
     * </pre>
     * <p>
     * After {@link Build.VERSION_CODES#S}, window context provides the capability to listen to its
     * {@link Configuration} changes by calling
     * {@link #registerComponentCallbacks(ComponentCallbacks)}, while other kinds of {@link Context}
     * will register the {@link ComponentCallbacks} to {@link #getApplicationContext() its
     * Application context}. Note that window context only propagate
     * {@link ComponentCallbacks#onConfigurationChanged(Configuration)} callback.
     * {@link ComponentCallbacks#onLowMemory()} or other callbacks in {@link ComponentCallbacks2}
     * won't be invoked.
     * </p>
     * <p>
     * Note that using {@link android.app.Application} or {@link android.app.Service} context for
     * UI-related queries may result in layout or continuity issues on devices with variable screen
     * sizes (e.g. foldables) or in multi-window modes, since these non-UI contexts may not reflect
     * the {@link Configuration} changes for the visual container.
     * </p>
     * @param type Window type in {@link WindowManager.LayoutParams}
     * @param options A bundle used to pass window-related options
     * @return A {@link Context} that can be used to create
     *         non-{@link android.app.Activity activity} windows.
     *
     * @see #getSystemService(String)
     * @see #getSystemService(Class)
     * @see #WINDOW_SERVICE
     * @see #LAYOUT_INFLATER_SERVICE
     * @see #WALLPAPER_SERVICE
     * @throws UnsupportedOperationException if this {@link Context} does not attach to a display,
     * such as {@link android.app.Application Application} or {@link android.app.Service Service}.
     */
    @UiContext
    @NonNull
    public Context createWindowContext(int type, @Nullable Bundle options)  {
        if (Build.VERSION.SDK_INT < 30) {
            throw new NoSuchMethodError(
                    "createWindowContext(ILandroid/os/Bundle;)Landroid/content/Context;");
        }
        throw new UnsupportedOperationException(
                "createWindowContext(ILandroid/os/Bundle;)Landroid/content/Context;");
    }

    /**
     * Creates a {@code Context} for a non-{@link android.app.Activity activity} window on the given
     * {@link Display}.
     *
     * <p>
     * Similar to {@link #createWindowContext(int, Bundle)}, but the {@code display} is passed in,
     * instead of implicitly using the {@link #getDisplay() original Context's Display}.
     * </p>
     *
     * @param display The {@link Display} to associate with
     * @param type Window type in {@link WindowManager.LayoutParams}
     * @param options A bundle used to pass window-related options.
     * @return A {@link Context} that can be used to create
     *         non-{@link android.app.Activity activity} windows.
     * @throws IllegalArgumentException if the {@link Display} is {@code null}.
     *
     * @see #getSystemService(String)
     * @see #getSystemService(Class)
     * @see #WINDOW_SERVICE
     * @see #LAYOUT_INFLATER_SERVICE
     * @see #WALLPAPER_SERVICE
     */
    @UiContext
    @NonNull
    public Context createWindowContext(@NonNull Display display, int type,
            @SuppressLint("NullableCollection")
            @Nullable Bundle options) {
        if (Build.VERSION.SDK_INT < 31) {
            throw new NoSuchMethodError(
                    "createWindowContext(Landroid/view/Display;ILandroid/os/Bundle;)Landroid/content/Context;");
        }
        throw new UnsupportedOperationException(
                "createWindowContext(Landroid/view/Display;ILandroid/os/Bundle;)Landroid/content/Context;");
    }

    /**
     * Return a new Context object for the current Context but attribute to a different tag.
     * In complex apps attribution tagging can be used to distinguish between separate logical
     * parts.
     *
     * @param attributionTag The tag or {@code null} to create a context for the default.
     *
     * @return A {@link Context} that is tagged for the new attribution
     *
     * @see #getAttributionTag()
     */
    public @NonNull Context createAttributionContext(@Nullable String attributionTag) {
        if (Build.VERSION.SDK_INT < 30) {
            throw new NoSuchMethodError(
                    "createAttributionContext(Ljava/lang/String;)Landroid/content/Context;");
        }
        return ContextBinding.createAttributionContext(this, attributionTag);
    }

    // TODO moltmann: remove
    /**
     * @removed
     */
    @Deprecated
    public @NonNull Context createFeatureContext(@Nullable String attributionTag) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Return a new Context object for the current Context but whose storage
     * APIs are backed by device-protected storage.
     * <p>
     * On devices with direct boot, data stored in this location is encrypted
     * with a key tied to the physical device, and it can be accessed
     * immediately after the device has booted successfully, both
     * <em>before and after</em> the user has authenticated with their
     * credentials (such as a lock pattern or PIN).
     * <p>
     * Because device-protected data is available without user authentication,
     * you should carefully limit the data you store using this Context. For
     * example, storing sensitive authentication tokens or passwords in the
     * device-protected area is strongly discouraged.
     * <p>
     * If the underlying device does not have the ability to store
     * device-protected and credential-protected data using different keys, then
     * both storage areas will become available at the same time. They remain as
     * two distinct storage locations on disk, and only the window of
     * availability changes.
     * <p>
     * Each call to this method returns a new instance of a Context object;
     * Context objects are not shared, however common state (ClassLoader, other
     * Resources for the same configuration) may be so the Context itself can be
     * fairly lightweight.
     *
     * @see #isDeviceProtectedStorage()
     */
    public Context createDeviceProtectedStorageContext() {
        if (Build.VERSION.SDK_INT < 24) {
            throw new NoSuchMethodError(
                    "createDeviceProtectedStorageContext()Landroid/content/Context;");
        }
        return ContextBinding.createDeviceProtectedStorageContext(this);
    }

    /**
     * Return a new Context object for the current Context but whose storage
     * APIs are backed by credential-protected storage. This is the default
     * storage area for apps unless
     * {@link android.R.attr#defaultToDeviceProtectedStorage} was requested.
     * <p>
     * On devices with direct boot, data stored in this location is encrypted
     * with a key tied to user credentials, which can be accessed
     * <em>only after</em> the user has entered their credentials (such as a
     * lock pattern or PIN).
     * <p>
     * If the underlying device does not have the ability to store
     * device-protected and credential-protected data using different keys, then
     * both storage areas will become available at the same time. They remain as
     * two distinct storage locations on disk, and only the window of
     * availability changes.
     * <p>
     * Each call to this method returns a new instance of a Context object;
     * Context objects are not shared, however common state (ClassLoader, other
     * Resources for the same configuration) may be so the Context itself can be
     * fairly lightweight.
     *
     * @see #isCredentialProtectedStorage()
     * @hide
     */
    @SuppressWarnings("HiddenAbstractMethod")
    @SystemApi
    public Context createCredentialProtectedStorageContext() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Creates a UI context with a {@code token}. The users of this API should handle this context's
     * configuration changes.
     *
     * @param token The token to associate with the {@link Resources}
     * @param display The display to associate with the token context
     *
     * @hide
     */
    @UiContext
    @NonNull
    public Context createTokenContext(@NonNull IBinder token, @NonNull Display display) {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    /**
     * Get the display this context is associated with. Applications should use this method with
     * {@link android.app.Activity} or a context associated with a {@link Display} via
     * {@link #createDisplayContext(Display)} to get a display object associated with a Context, or
     * {@link android.hardware.display.DisplayManager#getDisplay} to get a display object by id.
     * @return Returns the {@link Display} object this context is associated with.
     * @throws UnsupportedOperationException if the method is called on an instance that is not
     *         associated with any display.
     */
    @NonNull
    public Display getDisplay() {
        if (Build.VERSION.SDK_INT < 30) {
            throw new NoSuchMethodError("getDisplay()Landroid/view/Display;");
        }
        return ContextBinding.getDisplay(this);
    }

    /**
     * A version of {@link #getDisplay()} that does not perform a Context misuse check to be used by
     * legacy APIs.
     * TODO(b/149790106): Fix usages and remove.
     * @hide
     */
    @Nullable
    public Display getDisplayNoVerify() {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    /**
     * Gets the ID of the display this context is associated with.
     *
     * @return display ID associated with this {@link Context}.
     * @see #getDisplay()
     * @hide
     */
    @SuppressWarnings("HiddenAbstractMethod")
    @TestApi
    public int getDisplayId() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * @return Returns the id of the Display object associated with this Context or
     * {@link Display#INVALID_DISPLAY} if no Display has been associated.
     * @see #getDisplay()
     * @see #getDisplayId()
     *
     * @hide
     */
    public int getAssociatedDisplayId() {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    /**
     * Updates the display association of this Context with the display with the given ID.
     *
     * @hide
     */
    @SuppressWarnings("HiddenAbstractMethod")
    @SuppressLint("UnflaggedApi") // @TestApi without associated feature.
    @TestApi
    public void updateDisplay(int displayId) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Updates the device ID association of this Context. Since a Context created with
     * {@link #createDeviceContext} cannot change its device association, this method must
     * not be called for instances created with {@link #createDeviceContext}.
     *<p>
     * Note that updating the deviceId of the Context will not update its associated display.
     *</p>
     * @param deviceId The new device ID to assign to this Context.
     * @throws UnsupportedOperationException if the method is called on an instance that was
     *         created with {@link Context#createDeviceContext(int)}
     * @throws IllegalArgumentException if the given device ID is not a valid ID of the default
     *         device or a virtual device.
     *
     * @see #createDeviceContext(int)
     * @hide
     */
    @TestApi
    public void updateDeviceId(int deviceId) {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    /**
     * Gets the device ID this context is associated with. Applications can use this method to
     * determine whether they are running on a virtual device and identify that device.
     *
     * The device ID of the host device is
     * {@link Context#DEVICE_ID_DEFAULT}
     *
     * <p>
     * If the underlying device ID is changed by the system, for example, when an
     * {@link Activity} is moved to a different virtual device, applications can register to listen
     * to changes by calling
     * {@link Context#registerDeviceIdChangeListener(Executor, IntConsumer)}.
     * </p>
     *
     * <p>
     * This method will only return a reliable value for this instance if it was created with
     * {@link Context#createDeviceContext(int)}, or if this instance is a UI or Display Context.
     * Contexts created with {@link Context#createDeviceContext(int)} will have an explicit
     * device association, which will never change, even if the underlying device is closed or is
     * removed. UI Contexts and Display Contexts are
     * already associated with a display, so if the device association is not explicitly
     * given, {@link Context#getDeviceId()} will return the ID of the device associated with
     * the associated display. The system can assign an arbitrary device id value for Contexts not
     * logically associated with a device.
     * </p>
     *
     * @return the ID of the device this context is associated with.
     * @see #createDeviceContext(int)
     * @see #registerDeviceIdChangeListener(Executor, IntConsumer)
     * @see #isUiContext()
     */
    public int getDeviceId() {
        if (Build.VERSION.SDK_INT < 34) {
            throw new NoSuchMethodError("getDeviceId()I");
        }
        return ContextBinding.getDeviceId(this);
    }

    /**
     * Adds a new device ID changed listener to the {@code Context}, which will be called when
     * the device association is changed by the system.
     * <p>
     * The callback can be called when an app is moved to a different device and the {@code Context}
     * is not explicitly associated with a specific device.
     * </p>
     * <p> When an application receives a device id update callback, this Context is guaranteed to
     * also have an updated display ID(if any) and {@link Configuration}.
     * <p/>
     * @param executor The Executor on whose thread to execute the callbacks of the {@code listener}
     *                 object.
     * @param listener The listener {@code IntConsumer} to call which will receive the updated
     *                 device ID.
     *
     * @see Context#getDeviceId()
     * @see Context#createDeviceContext(int)
     */
    public void registerDeviceIdChangeListener(@NonNull @CallbackExecutor Executor executor,
            @NonNull IntConsumer listener) {
        if (Build.VERSION.SDK_INT < 34) {
            throw new NoSuchMethodError(
                    "registerDeviceIdChangeListener(Ljava/util/concurrent/Executor;Ljava/util/function/IntConsumer;)V");
        }
        throw new UnsupportedOperationException(
                "registerDeviceIdChangeListener(Ljava/util/concurrent/Executor;Ljava/util/function/IntConsumer;)V");
    }

    /**
     * Removes a device ID changed listener from the Context. It's a no-op if
     * the listener is not already registered.
     *
     * @param listener The {@code Consumer} to remove.
     *
     * @see #getDeviceId()
     * @see #registerDeviceIdChangeListener(Executor, IntConsumer)
     */
    public void unregisterDeviceIdChangeListener(@NonNull IntConsumer listener) {
        if (Build.VERSION.SDK_INT < 34) {
            throw new NoSuchMethodError(
                    "unregisterDeviceIdChangeListener(Ljava/util/function/IntConsumer;)V");
        }
        throw new UnsupportedOperationException(
                "unregisterDeviceIdChangeListener(Ljava/util/function/IntConsumer;)V");
    }

    /**
     * Indicates whether this Context is restricted.
     *
     * @return {@code true} if this Context is restricted, {@code false} otherwise.
     *
     * @see #CONTEXT_RESTRICTED
     */
    public boolean isRestricted() {
        return ContextBinding.isRestricted(this);
    }

    /**
     * Indicates if the storage APIs of this Context are backed by
     * device-protected storage.
     *
     * @see #createDeviceProtectedStorageContext()
     */
    public boolean isDeviceProtectedStorage() {
        if (Build.VERSION.SDK_INT < 24) {
            throw new NoSuchMethodError("isDeviceProtectedStorage()Z");
        }
        return ContextBinding.isDeviceProtectedStorage(this);
    }

    /**
     * Indicates if the storage APIs of this Context are backed by
     * credential-protected storage.
     *
     * @see #createCredentialProtectedStorageContext()
     * @hide
     */
    @SuppressWarnings("HiddenAbstractMethod")
    @SystemApi
    public boolean isCredentialProtectedStorage() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Returns true if the context can load unsafe resources, e.g. fonts.
     * @hide
     */
    @SuppressWarnings("HiddenAbstractMethod")
    public boolean canLoadUnsafeResources() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Returns token if the {@link Context} is a {@link android.app.Activity}. Returns
     * {@code null} otherwise.
     *
     * @hide
     */
    @Nullable
    public IBinder getActivityToken() {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    /**
     * Returns the {@link IBinder} representing the associated
     * {@link com.android.server.wm.WindowToken} if the {@link Context} is a
     * {@link android.app.WindowContext}. Returns {@code null} otherwise.
     *
     * @hide
     */
    @Nullable
    public IBinder getWindowContextToken() {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    /**
     * Returns the proper token of a {@link Context}.
     *
     * If the {@link Context} is an {@link android.app.Activity}, returns
     * {@link #getActivityToken()}. If the {@lijnk Context} is a {@link android.app.WindowContext},
     * returns {@link #getWindowContextToken()}. Returns {@code null}, otherwise.
     *
     * @hide
     */
    @Nullable
    public static IBinder getToken(@NonNull Context context) {
        return context.getActivityToken() != null ? context.getActivityToken()
                : context.getWindowContextToken();
    }

    /**
     * Used by a mainline module to uniquely identify a specific app process.
     * @hide
     */
    @NonNull
    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    public IBinder getProcessToken() {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    /**
     * @hide
     */
    public Handler getMainThreadHandler() {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    /**
     * @hide
     */
    public final boolean isAutofillCompatibilityEnabled() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Throws an exception if the Context is using system resources,
     * which are non-runtime-overlay-themable and may show inconsistent UI.
     * @hide
     */
    public void assertRuntimeOverlayThemable() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Returns {@code true} if the context is a UI context which can access UI components such as
     * {@link WindowManager}, {@link android.view.LayoutInflater LayoutInflater} or
     * {@link android.app.WallpaperManager WallpaperManager}. Accessing UI components from non-UI
     * contexts throws {@link android.os.strictmode.Violation} if
     * {@link android.os.StrictMode.VmPolicy.Builder#detectIncorrectContextUse()} is enabled.
     * <p>
     * Examples of UI contexts are
     * an {@link android.app.Activity Activity}, a context created from
     * {@link #createWindowContext(int, Bundle)} or
     * {@link android.inputmethodservice.InputMethodService InputMethodService}
     * </p>
     * <p>
     * Note that even if it is allowed programmatically, it is not suggested to override this
     * method to bypass {@link android.os.strictmode.IncorrectContextUseViolation} verification.
     * </p>
     *
     * @see #getDisplay()
     * @see #getSystemService(String)
     * @see android.os.StrictMode.VmPolicy.Builder#detectIncorrectContextUse()
     */
    public boolean isUiContext() {
        if (Build.VERSION.SDK_INT < 31) {
            throw new NoSuchMethodError("isUiContext()Z");
        }
        return ContextBinding.isUiContext(this);
    }

    /**
     * Called when a {@link Context} is going to be released.
     * This method can be overridden to perform the final cleanups, such as release
     * {@link BroadcastReceiver} registrations.
     *
     * @see WindowContext#destroy()
     *
     * @hide
     */
    public void destroy() { }

    /**
     * Indicates this {@link Context} has the proper {@link Configuration} to obtain
     * {@link android.view.LayoutInflater}, {@link android.view.ViewConfiguration} and
     * {@link android.view.GestureDetector}. Generally, all UI contexts, such as
     * {@link android.app.Activity} or {@link android.app.WindowContext}, are initialized with base
     * configuration.
     * <p>
     * Note that the context created via {@link Context#createConfigurationContext(Configuration)}
     * is also regarded as a context that is based on a configuration because the
     * configuration is explicitly provided via the API.
     * </p>
     *
     * @see #isUiContext()
     * @see #createConfigurationContext(Configuration)
     *
     * @hide
     */
    public boolean isConfigurationContext() {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    /**
     * Closes temporary system dialogs. Some examples of temporary system dialogs are the
     * notification window-shade and the recent tasks dialog.
     *
     * @hide
     */
    public void closeSystemDialogs() {
        throw new RuntimeException("Not implemented. Must override in a subclass.");
    }

    @Added(reason = "the id of the ART object this handle forwards to")
    private final long id;

    @Added(reason = "the public constructor android.jar declares")
    public Context() {
        throw new UnsupportedOperationException("<init>()V");
    }

    @Added(reason = "the handle of the ART object received under its id")
    public Context(long id) {
        this.id = id;
    }

    @Added(reason = "the id this handle crosses to ART as")
    @Override
    public final long foreignId() {
        return id;
    }
}
