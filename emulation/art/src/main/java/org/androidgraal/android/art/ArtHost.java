package org.androidgraal.android.art;

import android.app.Application;
import android.content.res.AssetManager;
import android.os.Build;
import dalvik.system.BaseDexClassLoader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    @Override
    public int targetSdkVersion() {
        return Image.application().getApplicationInfo().targetSdkVersion;
    }

    @Override
    public String processName() {
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                return (String) Application.class.getMethod("getProcessName").invoke(null);
            } catch (ReflectiveOperationException e) {
                throw new IllegalStateException(e);
            }
        }
        try (FileInputStream cmdline = new FileInputStream("/proc/self/cmdline")) {
            ByteArrayOutputStream name = new ByteArrayOutputStream();
            for (int b = cmdline.read(); b > 0; b = cmdline.read()) {
                name.write(b);
            }
            return new String(name.toByteArray(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
