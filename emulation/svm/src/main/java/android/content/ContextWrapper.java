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

import android.annotation.CallbackExecutor;
import android.annotation.FlaggedApi;
import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.RequiresPermission;
import android.annotation.SuppressLint;
import android.annotation.SystemApi;
import android.annotation.TestApi;
import android.annotation.UiContext;
import android.compat.annotation.UnsupportedAppUsage;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;

import com.android.internal.annotations.GuardedBy;
import com.android.internal.annotations.VisibleForTesting;

import org.androidgraal.android.handles.ContextBinding;
import org.androidgraal.android.handles.ContextWrapperBinding;
import org.androidgraal.hosted.annotate.Added;
import org.androidgraal.hosted.annotate.Emulated;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.IntConsumer;

/**
 * Proxying implementation of Context that simply delegates all of its calls to
 * another Context.  Can be subclassed to modify behavior without changing
 * the original Context.
 */
@android.ravenwood.annotation.RavenwoodKeepWholeClass
@Emulated
public class ContextWrapper extends Context {
    public ContextWrapper(Context base) {
        throw new UnsupportedOperationException("<init>(Landroid/content/Context;)V");
    }

    /**
     * Set the base context for this ContextWrapper.  All calls will then be
     * delegated to the base context.  Throws
     * IllegalStateException if a base context has already been set.
     *
     * @param base The new base context for this wrapper.
     */
    protected void attachBaseContext(Context base) {
        throw new UnsupportedOperationException("attachBaseContext(Landroid/content/Context;)V");
    }

    /**
     * @return the base context as set by the constructor or setBaseContext
     */
    public Context getBaseContext() {
        return ContextWrapperBinding.getBaseContext(this);
    }

    @Override
    public AssetManager getAssets() {
        throw new UnsupportedOperationException("getAssets()Landroid/content/res/AssetManager;");
    }

    @Override
    public Looper getMainLooper() {
        throw new UnsupportedOperationException("getMainLooper()Landroid/os/Looper;");
    }

    @Override
    public Executor getMainExecutor() {
        if (Build.VERSION.SDK_INT < 28) {
            throw new NoSuchMethodError("getMainExecutor()Ljava/util/concurrent/Executor;");
        }
        throw new UnsupportedOperationException("getMainExecutor()Ljava/util/concurrent/Executor;");
    }

    @Override
    public Context getApplicationContext() {
        return ContextBinding.getApplicationContext(this);
    }

    @Override
    public void setTheme(int resid) {
        ContextBinding.setTheme(this, resid);
    }

    /** @hide */
    @Override
    @UnsupportedAppUsage
    public int getThemeResId() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    @Override
    public ClassLoader getClassLoader() {
        throw new UnsupportedOperationException("getClassLoader()Ljava/lang/ClassLoader;");
    }

    @Override
    public String getPackageName() {
        return ContextBinding.getPackageName(this);
    }

    /** @hide */
    @Override
    @UnsupportedAppUsage
    public String getBasePackageName() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /** @hide */
    @Override
    public String getOpPackageName() {
        if (Build.VERSION.SDK_INT < 29) {
            throw new NoSuchMethodError("getOpPackageName()Ljava/lang/String;");
        }
        return ContextBinding.getOpPackageName(this);
    }

    /** @hide */
    @Override
    public @Nullable String getAttributionTag() {
        if (Build.VERSION.SDK_INT < 30) {
            throw new NoSuchMethodError("getAttributionTag()Ljava/lang/String;");
        }
        return ContextBinding.getAttributionTag(this);
    }

    @Override
    public String getPackageResourcePath() {
        return ContextBinding.getPackageResourcePath(this);
    }

    @Override
    public String getPackageCodePath() {
        return ContextBinding.getPackageCodePath(this);
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        throw new UnsupportedOperationException(
                "getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;");
    }

    /** @removed */
    @Override
    public SharedPreferences getSharedPreferences(File file, int mode) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /** @hide */
    @Override
    public void reloadSharedPreferences() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    @Override
    public boolean moveSharedPreferencesFrom(Context sourceContext, String name) {
        if (Build.VERSION.SDK_INT < 24) {
            throw new NoSuchMethodError(
                    "moveSharedPreferencesFrom(Landroid/content/Context;Ljava/lang/String;)Z");
        }
        return ContextBinding.moveSharedPreferencesFrom(this, sourceContext, name);
    }

    @Override
    public boolean deleteSharedPreferences(String name) {
        if (Build.VERSION.SDK_INT < 24) {
            throw new NoSuchMethodError("deleteSharedPreferences(Ljava/lang/String;)Z");
        }
        return ContextBinding.deleteSharedPreferences(this, name);
    }

    @Override
    public FileInputStream openFileInput(String name)
            throws FileNotFoundException {
        throw new UnsupportedOperationException(
                "openFileInput(Ljava/lang/String;)Ljava/io/FileInputStream;");
    }

    @Override
    public FileOutputStream openFileOutput(String name, int mode)
            throws FileNotFoundException {
        throw new UnsupportedOperationException(
                "openFileOutput(Ljava/lang/String;I)Ljava/io/FileOutputStream;");
    }

    @Override
    public boolean deleteFile(String name) {
        return ContextBinding.deleteFile(this, name);
    }

    @Override
    public File getFileStreamPath(String name) {
        throw new UnsupportedOperationException(
                "getFileStreamPath(Ljava/lang/String;)Ljava/io/File;");
    }

    /** @removed */
    @Override
    public File getSharedPreferencesPath(String name) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    @Override
    public String[] fileList() {
        throw new UnsupportedOperationException("fileList()[Ljava/lang/String;");
    }

    @Override
    public File getDataDir() {
        if (Build.VERSION.SDK_INT < 24) {
            throw new NoSuchMethodError("getDataDir()Ljava/io/File;");
        }
        throw new UnsupportedOperationException("getDataDir()Ljava/io/File;");
    }

    @Override
    public File getFilesDir() {
        throw new UnsupportedOperationException("getFilesDir()Ljava/io/File;");
    }

    /**
     * {@inheritDoc Context#getCrateDir()}
     * @hide
     */
    @NonNull
    @Override
    public File getCrateDir(@NonNull String cratedId) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    @Override
    public File getNoBackupFilesDir() {
        throw new UnsupportedOperationException("getNoBackupFilesDir()Ljava/io/File;");
    }

    @Override
    public @Nullable File getExternalFilesDir(@Nullable String type) {
        throw new UnsupportedOperationException(
                "getExternalFilesDir(Ljava/lang/String;)Ljava/io/File;");
    }

    @Override
    public File[] getExternalFilesDirs(String type) {
        throw new UnsupportedOperationException(
                "getExternalFilesDirs(Ljava/lang/String;)[Ljava/io/File;");
    }

    @Override
    public File getObbDir() {
        throw new UnsupportedOperationException("getObbDir()Ljava/io/File;");
    }

    @Override
    public File[] getObbDirs() {
        throw new UnsupportedOperationException("getObbDirs()[Ljava/io/File;");
    }

    @Override
    public File getCacheDir() {
        throw new UnsupportedOperationException("getCacheDir()Ljava/io/File;");
    }

    @Override
    public File getCodeCacheDir() {
        throw new UnsupportedOperationException("getCodeCacheDir()Ljava/io/File;");
    }

    @Override
    public @Nullable File getExternalCacheDir() {
        throw new UnsupportedOperationException("getExternalCacheDir()Ljava/io/File;");
    }

    @Override
    public File[] getExternalCacheDirs() {
        throw new UnsupportedOperationException("getExternalCacheDirs()[Ljava/io/File;");
    }

    @Override
    public File[] getExternalMediaDirs() {
        throw new UnsupportedOperationException("getExternalMediaDirs()[Ljava/io/File;");
    }

    @Override
    public File getDir(String name, int mode) {
        throw new UnsupportedOperationException("getDir(Ljava/lang/String;I)Ljava/io/File;");
    }


    /** @hide **/
    @Override
    public @Nullable File getPreloadsFileCache() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    @Override
    public boolean moveDatabaseFrom(Context sourceContext, String name) {
        if (Build.VERSION.SDK_INT < 24) {
            throw new NoSuchMethodError(
                    "moveDatabaseFrom(Landroid/content/Context;Ljava/lang/String;)Z");
        }
        return ContextBinding.moveDatabaseFrom(this, sourceContext, name);
    }

    @Override
    public boolean deleteDatabase(String name) {
        return ContextBinding.deleteDatabase(this, name);
    }

    @Override
    public File getDatabasePath(String name) {
        throw new UnsupportedOperationException(
                "getDatabasePath(Ljava/lang/String;)Ljava/io/File;");
    }

    @Override
    public String[] databaseList() {
        throw new UnsupportedOperationException("databaseList()[Ljava/lang/String;");
    }

    @Override
    @Deprecated
    public int getWallpaperDesiredMinimumWidth() {
        return ContextBinding.getWallpaperDesiredMinimumWidth(this);
    }

    @Override
    @Deprecated
    public int getWallpaperDesiredMinimumHeight() {
        return ContextBinding.getWallpaperDesiredMinimumHeight(this);
    }

    @Override
    @Deprecated
    public void setWallpaper(InputStream data) throws IOException {
        throw new UnsupportedOperationException("setWallpaper(Ljava/io/InputStream;)V");
    }

    @Override
    @Deprecated
    public void clearWallpaper() throws IOException {
        ContextBinding.clearWallpaper(this);
    }

    /** @hide **/
    public boolean canStartActivityForResult() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    @Override
    public Object getSystemService(String name) {
        throw new UnsupportedOperationException(
                "getSystemService(Ljava/lang/String;)Ljava/lang/Object;");
    }

    @Override
    public String getSystemServiceName(Class<?> serviceClass) {
        if (Build.VERSION.SDK_INT < 23) {
            throw new NoSuchMethodError(
                    "getSystemServiceName(Ljava/lang/Class;)Ljava/lang/String;");
        }
        throw new UnsupportedOperationException(
                "getSystemServiceName(Ljava/lang/Class;)Ljava/lang/String;");
    }

    @Override
    public int checkPermission(String permission, int pid, int uid) {
        return ContextBinding.checkPermission(this, permission, pid, uid);
    }

    /** @hide */
    @Override
    public int checkPermission(String permission, int pid, int uid, IBinder callerToken) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    @Override
    public int checkCallingPermission(String permission) {
        return ContextBinding.checkCallingPermission(this, permission);
    }

    @Override
    public int checkCallingOrSelfPermission(String permission) {
        return ContextBinding.checkCallingOrSelfPermission(this, permission);
    }

    @Override
    public int checkSelfPermission(String permission) {
        if (Build.VERSION.SDK_INT < 23) {
            throw new NoSuchMethodError("checkSelfPermission(Ljava/lang/String;)I");
        }
        return ContextBinding.checkSelfPermission(this, permission);
    }

    @Override
    public void enforcePermission(
            String permission, int pid, int uid, @Nullable String message) {
        ContextBinding.enforcePermission(this, permission, pid, uid, message);
    }

    @Override
    public void enforceCallingPermission(String permission, @Nullable String message) {
        ContextBinding.enforceCallingPermission(this, permission, message);
    }

    @Override
    public void enforceCallingOrSelfPermission(
            String permission, @Nullable String message) {
        ContextBinding.enforceCallingOrSelfPermission(this, permission, message);
    }

    /** @hide */
    @Override
    public int getPermissionRequestState(String permission) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    @Override
    public void grantUriPermission(String toPackage, Uri uri, int modeFlags) {
        throw new UnsupportedOperationException(
                "grantUriPermission(Ljava/lang/String;Landroid/net/Uri;I)V");
    }

    @Override
    public void revokeUriPermission(Uri uri, int modeFlags) {
        throw new UnsupportedOperationException("revokeUriPermission(Landroid/net/Uri;I)V");
    }

    @Override
    public void revokeUriPermission(String targetPackage, Uri uri, int modeFlags) {
        if (Build.VERSION.SDK_INT < 26) {
            throw new NoSuchMethodError(
                    "revokeUriPermission(Ljava/lang/String;Landroid/net/Uri;I)V");
        }
        throw new UnsupportedOperationException(
                "revokeUriPermission(Ljava/lang/String;Landroid/net/Uri;I)V");
    }

    @Override
    public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {
        throw new UnsupportedOperationException("checkUriPermission(Landroid/net/Uri;III)I");
    }

    @FlaggedApi(android.security.Flags.FLAG_CONTENT_URI_PERMISSION_APIS)
    @Override
    public int checkContentUriPermissionFull(@NonNull Uri uri, int pid, int uid, int modeFlags) {
        if (Build.VERSION.SDK_INT < 35) {
            throw new NoSuchMethodError("checkContentUriPermissionFull(Landroid/net/Uri;III)I");
        }
        throw new UnsupportedOperationException(
                "checkContentUriPermissionFull(Landroid/net/Uri;III)I");
    }

    @NonNull
    @Override
    public int[] checkUriPermissions(@NonNull List<Uri> uris, int pid, int uid,
            int modeFlags) {
        if (Build.VERSION.SDK_INT < 31) {
            throw new NoSuchMethodError("checkUriPermissions(Ljava/util/List;III)[I");
        }
        throw new UnsupportedOperationException("checkUriPermissions(Ljava/util/List;III)[I");
    }

    /** @hide */
    @Override
    public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags, IBinder callerToken) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    @Override
    public int checkCallingUriPermission(Uri uri, int modeFlags) {
        throw new UnsupportedOperationException("checkCallingUriPermission(Landroid/net/Uri;I)I");
    }

    @NonNull
    @Override
    public int[] checkCallingUriPermissions(@NonNull List<Uri> uris, int modeFlags) {
        if (Build.VERSION.SDK_INT < 31) {
            throw new NoSuchMethodError("checkCallingUriPermissions(Ljava/util/List;I)[I");
        }
        throw new UnsupportedOperationException("checkCallingUriPermissions(Ljava/util/List;I)[I");
    }

    @Override
    public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {
        throw new UnsupportedOperationException(
                "checkCallingOrSelfUriPermission(Landroid/net/Uri;I)I");
    }

    @NonNull
    @Override
    public int[] checkCallingOrSelfUriPermissions(@NonNull List<Uri> uris, int modeFlags) {
        if (Build.VERSION.SDK_INT < 31) {
            throw new NoSuchMethodError("checkCallingOrSelfUriPermissions(Ljava/util/List;I)[I");
        }
        throw new UnsupportedOperationException(
                "checkCallingOrSelfUriPermissions(Ljava/util/List;I)[I");
    }

    @Override
    public int checkUriPermission(@Nullable Uri uri, @Nullable String readPermission,
            @Nullable String writePermission, int pid, int uid, int modeFlags) {
        throw new UnsupportedOperationException(
                "checkUriPermission(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;III)I");
    }

    @Override
    public void enforceUriPermission(
            Uri uri, int pid, int uid, int modeFlags, String message) {
        throw new UnsupportedOperationException(
                "enforceUriPermission(Landroid/net/Uri;IIILjava/lang/String;)V");
    }

    @Override
    public void enforceCallingUriPermission(
            Uri uri, int modeFlags, String message) {
        throw new UnsupportedOperationException(
                "enforceCallingUriPermission(Landroid/net/Uri;ILjava/lang/String;)V");
    }

    @Override
    public void enforceCallingOrSelfUriPermission(
            Uri uri, int modeFlags, String message) {
        throw new UnsupportedOperationException(
                "enforceCallingOrSelfUriPermission(Landroid/net/Uri;ILjava/lang/String;)V");
    }

    @Override
    public void enforceUriPermission(
            @Nullable Uri uri, @Nullable String readPermission, @Nullable String writePermission,
            int pid, int uid, int modeFlags, @Nullable String message) {
        throw new UnsupportedOperationException(
                "enforceUriPermission(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;IIILjava/lang/String;)V");
    }

    @Override
    public void revokeSelfPermissionsOnKill(@NonNull Collection<String> permissions) {
        if (Build.VERSION.SDK_INT < 33) {
            throw new NoSuchMethodError("revokeSelfPermissionsOnKill(Ljava/util/Collection;)V");
        }
        throw new UnsupportedOperationException(
                "revokeSelfPermissionsOnKill(Ljava/util/Collection;)V");
    }

    /** @hide */
    @Override
    public Context createContextAsUser(UserHandle user, @CreatePackageOptions int flags) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /** @hide */
    @Override
    public int getUserId() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /** @hide */
    @Override
    public UserHandle getUser() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    @Override
    public Context createDisplayContext(Display display) {
        return ContextBinding.createDisplayContext(this, display);
    }

    @Override
    public @NonNull Context createDeviceContext(int deviceId) {
        if (Build.VERSION.SDK_INT < 34) {
            throw new NoSuchMethodError("createDeviceContext(I)Landroid/content/Context;");
        }
        return ContextBinding.createDeviceContext(this, deviceId);
    }

    @Override
    @NonNull
    public Context createWindowContext(int type, @Nullable Bundle options) {
        if (Build.VERSION.SDK_INT < 30) {
            throw new NoSuchMethodError(
                    "createWindowContext(ILandroid/os/Bundle;)Landroid/content/Context;");
        }
        throw new UnsupportedOperationException(
                "createWindowContext(ILandroid/os/Bundle;)Landroid/content/Context;");
    }

    @Override
    @NonNull
    public Context createWindowContext(@NonNull Display display, int type,
            @Nullable Bundle options) {
        if (Build.VERSION.SDK_INT < 31) {
            throw new NoSuchMethodError(
                    "createWindowContext(Landroid/view/Display;ILandroid/os/Bundle;)Landroid/content/Context;");
        }
        throw new UnsupportedOperationException(
                "createWindowContext(Landroid/view/Display;ILandroid/os/Bundle;)Landroid/content/Context;");
    }

    @Override
    public @NonNull Context createAttributionContext(@Nullable String attributionTag) {
        if (Build.VERSION.SDK_INT < 30) {
            throw new NoSuchMethodError(
                    "createAttributionContext(Ljava/lang/String;)Landroid/content/Context;");
        }
        return ContextBinding.createAttributionContext(this, attributionTag);
    }

    @Override
    public boolean isRestricted() {
        return ContextBinding.isRestricted(this);
    }

    @Override
    public @Nullable Display getDisplay() {
        if (Build.VERSION.SDK_INT < 30) {
            throw new NoSuchMethodError("getDisplay()Landroid/view/Display;");
        }
        return ContextBinding.getDisplay(this);
    }

    /** @hide */
    @Override
    public @Nullable Display getDisplayNoVerify() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * @hide
     */
    @Override
    public int getDisplayId() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * @hide
     */
    @Override
    public int getAssociatedDisplayId() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * @hide
     */
    @Override
    public void updateDisplay(int displayId) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * @hide
     */
    @Override
    public void updateDeviceId(int deviceId) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    @Override
    public int getDeviceId() {
        if (Build.VERSION.SDK_INT < 34) {
            throw new NoSuchMethodError("getDeviceId()I");
        }
        return ContextBinding.getDeviceId(this);
    }

    @Override
    public void registerDeviceIdChangeListener(@NonNull @CallbackExecutor Executor executor,
            @NonNull IntConsumer listener) {
        if (Build.VERSION.SDK_INT < 34) {
            throw new NoSuchMethodError(
                    "registerDeviceIdChangeListener(Ljava/util/concurrent/Executor;Ljava/util/function/IntConsumer;)V");
        }
        throw new UnsupportedOperationException(
                "registerDeviceIdChangeListener(Ljava/util/concurrent/Executor;Ljava/util/function/IntConsumer;)V");
    }

    @Override
    public void unregisterDeviceIdChangeListener(@NonNull IntConsumer listener) {
        if (Build.VERSION.SDK_INT < 34) {
            throw new NoSuchMethodError(
                    "unregisterDeviceIdChangeListener(Ljava/util/function/IntConsumer;)V");
        }
        throw new UnsupportedOperationException(
                "unregisterDeviceIdChangeListener(Ljava/util/function/IntConsumer;)V");
    }

    @Override
    public Context createDeviceProtectedStorageContext() {
        if (Build.VERSION.SDK_INT < 24) {
            throw new NoSuchMethodError(
                    "createDeviceProtectedStorageContext()Landroid/content/Context;");
        }
        return ContextBinding.createDeviceProtectedStorageContext(this);
    }

    /** {@hide} */
    @SystemApi
    @Override
    public Context createCredentialProtectedStorageContext() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /** @hide */
    @UiContext
    @NonNull
    @Override
    public Context createTokenContext(@NonNull IBinder token, @NonNull Display display) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    @Override
    public boolean isDeviceProtectedStorage() {
        if (Build.VERSION.SDK_INT < 24) {
            throw new NoSuchMethodError("isDeviceProtectedStorage()Z");
        }
        return ContextBinding.isDeviceProtectedStorage(this);
    }

    /** {@hide} */
    @SystemApi
    @Override
    public boolean isCredentialProtectedStorage() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /** {@hide} */
    @Override
    public boolean canLoadUnsafeResources() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * @hide
     */
    @Override
    public @Nullable IBinder getActivityToken() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * @hide
     */
    @Override
    public @Nullable IBinder getWindowContextToken() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * @hide
     */
    @Override
    public IBinder getProcessToken() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * @hide
     */
    @Override
    public Handler getMainThreadHandler() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * @hide
     */
    @Override
    public int getNextAutofillId() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * @hide
     */
    @Override
    public boolean isUiContext() {
        if (Build.VERSION.SDK_INT < 31) {
            throw new NoSuchMethodError("isUiContext()Z");
        }
        return ContextBinding.isUiContext(this);
    }

    /**
     * @hide
     */
    @Override
    public boolean isConfigurationContext() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Closes temporary system dialogs. Some examples of temporary system dialogs are the
     * notification window-shade and the recent tasks dialog.
     *
     * @hide
     */
    public void closeSystemDialogs() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    @Added(reason = "the handle of the ART object received under its id")
    public ContextWrapper(long id) {
        super(id);
    }
}
