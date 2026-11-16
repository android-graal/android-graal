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

package android.view;

import android.annotation.Nullable;
import android.annotation.StyleRes;
import android.compat.annotation.UnsupportedAppUsage;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.os.Build;

import org.androidgraal.android.handles.ContextBinding;
import org.androidgraal.hosted.annotate.Added;
import org.androidgraal.hosted.annotate.Emulated;

/**
 * A context wrapper that allows you to modify or replace the theme of the
 * wrapped context.
 */
@Emulated
public class ContextThemeWrapper extends ContextWrapper {
    /**
     * Creates a new context wrapper with no theme and no base context.
     * <p class="note">
     * <strong>Note:</strong> A base context <strong>must</strong> be attached
     * using {@link #attachBaseContext(Context)} before calling any other
     * method on the newly constructed context wrapper.
     */
    public ContextThemeWrapper() {
        super(null);
        throw new UnsupportedOperationException("<init>()V");
    }

    /**
     * Creates a new context wrapper with the specified theme.
     * <p>
     * The specified theme will be applied on top of the base context's theme.
     * Any attributes not explicitly defined in the theme identified by
     * <var>themeResId</var> will retain their original values.
     *
     * @param base the base context
     * @param themeResId the resource ID of the theme to be applied on top of
     *                   the base context's theme
     */
    public ContextThemeWrapper(Context base, @StyleRes int themeResId) {
        super(base);
        throw new UnsupportedOperationException("<init>(Landroid/content/Context;I)V");
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        throw new UnsupportedOperationException("attachBaseContext(Landroid/content/Context;)V");
    }

    @Override
    public AssetManager getAssets() {
        throw new UnsupportedOperationException("getAssets()Landroid/content/res/AssetManager;");
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
    public Object getSystemService(String name) {
        throw new UnsupportedOperationException(
                "getSystemService(Ljava/lang/String;)Ljava/lang/Object;");
    }

    @UnsupportedAppUsage
    private void initializeTheme() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    @Added(reason = "the handle of the ART object received under its id")
    public ContextThemeWrapper(long id) {
        super(id);
    }
}

