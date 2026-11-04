package org.androidgraal.android;

import android.os.Looper;
import android.os.ProxyMessageQueue;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.androidgraal.android.host.ProxyLoopers;

public final class ImageLoopers implements ProxyLoopers {

    private static final Map<Long, WeakReference<Looper>> LOOPERS = new ConcurrentHashMap<>();

    /**
     * The proxy Looper for the ART Looper {@code looper}, null before it is prepared and once its thread died.
     */
    public static Looper looperOf(long looper) {
        WeakReference<Looper> reference = LOOPERS.get(looper);
        if (reference == null) {
            return null;
        }
        Looper proxy = reference.get();
        if (proxy == null) {
            return null;
        }
        if (!proxy.getThread().isAlive()) {
            return null;
        }
        return proxy;
    }

    @Override
    public void prepare(long looper, boolean main) {
        if (main) {
            Looper.prepareMainProxyLooper(looper);
        } else {
            Looper.prepareProxy(looper);
        }
        Looper proxy = Looper.myLooper();
        LOOPERS.put(looper, new WeakReference<>(proxy));

        if (!main) {
            ImageCleaner.register(proxy, () -> LOOPERS.remove(looper));
        }
    }

    @Override
    public void dispatch(long uptimeMillis) {
        Looper proxy = Looper.myLooper();
        ((ProxyMessageQueue) proxy.getQueue()).dispatch(proxy, uptimeMillis);
    }

    @Override
    public void idle() {
        ((ProxyMessageQueue) Looper.myLooper().getQueue()).idle();
    }
}
