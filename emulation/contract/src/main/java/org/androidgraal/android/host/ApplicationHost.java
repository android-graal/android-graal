package org.androidgraal.android.host;

import org.androidgraal.hosted.annotate.Art;

@Art
public interface ApplicationHost {

    /** {@code AssetManager.list(path)}; its {@code IOException} crosses as the cause. */
    String[] listAssets(String path);

    /** {@code AssetManager.getLocales()}. */
    String[] locales();

    /** The path of {@code lib<name>.so} as the app's class loader finds it, null when it finds none. */
    String findLibrary(String name);

    /** {@code AAssetManager_fromJava} of the application's {@code AssetManager}. */
    long assetManager();
}
