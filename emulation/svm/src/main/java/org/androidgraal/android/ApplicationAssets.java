package org.androidgraal.android;

import android.content.res.AssetManager;
import java.lang.reflect.Constructor;

public final class ApplicationAssets {

    private static AssetManager instance;

    private ApplicationAssets() {
    }

    /** The application's {@link AssetManager}, created on the first call. */
    public static synchronized AssetManager get() {
        if (instance == null) {
            try {
                Constructor<AssetManager> constructor = AssetManager.class.getDeclaredConstructor(boolean.class);
                constructor.setAccessible(true);
                instance = constructor.newInstance(false);
            } catch (ReflectiveOperationException e) {
                throw new IllegalStateException("creating the application's AssetManager failed", e);
            }
        }
        return instance;
    }
}
