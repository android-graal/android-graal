package org.androidgraal.android.host;

import org.androidgraal.hosted.annotate.Art;

@Art
public interface ApplicationHost {

    /** {@code AssetManager.list(path)}. Its {@code IOException} crosses as the cause. */
    String[] listAssets(String path);

    /** {@code AssetManager.getLocales()}. */
    String[] locales();

    /** The path of {@code lib<name>.so} as the app's class loader finds it, null when it finds none. */
    String findLibrary(String name);

    /** {@code AAssetManager_fromJava} of the application's {@code AssetManager}. */
    long assetManager();

    /** {@code ApplicationInfo.targetSdkVersion} of the application. */
    int targetSdkVersion();

    /** {@code Application.getProcessName()} of the application's process. */
    String processName();

    /**
     * Posts a synchronous message on a Handler of the ART Looper {@code looper}, at {@code uptimeMillis} of
     * {@code SystemClock.uptimeMillis()}, zero meaning the front of its queue, whose dispatch runs
     * {@link ProxyLoopers#dispatch(long)} with that uptime. Returns the Handler's own result, also false when that
     * Looper is quitting or its thread ended.
     */
    boolean post(long looper, long uptimeMillis);

    /** Quits the ART Looper {@code looper}, with {@code quitSafely} when {@code safe}. */
    void quit(long looper, boolean safe);
}
