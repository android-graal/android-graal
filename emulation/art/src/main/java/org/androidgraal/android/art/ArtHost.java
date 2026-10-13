package org.androidgraal.android.art;

import android.content.res.AssetManager;
import dalvik.system.BaseDexClassLoader;
import java.io.IOException;
import org.androidgraal.android.host.ApplicationHost;
import org.androidgraal.art.Image;

public final class ArtHost implements ApplicationHost {

    public static native long nativeAssetManager(long thread, AssetManager assets);

    @Override
    public String[] listAssets(String path) {
        try {
            return Image.application().getAssets().list(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[] locales() {
        return Image.application().getAssets().getLocales();
    }

    @Override
    public String findLibrary(String name) {
        return ((BaseDexClassLoader) ArtHost.class.getClassLoader()).findLibrary(name);
    }

    @Override
    public long assetManager() {
        Image.ThreadData thread = Image.thread();
        long manager = nativeAssetManager(thread.isolateThread, Image.application().getAssets());
        thread.throwPendingException();
        return manager;
    }
}
