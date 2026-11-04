package org.androidgraal.android.art;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.androidgraal.android.host.ProxyLoopers;
import org.androidgraal.art.Image;
import org.androidgraal.art.Startup;

public final class ArtLoopers implements Startup {

    private static final ConcurrentHashMap<Long, Handler> HANDLERS = new ConcurrentHashMap<>();
    private static volatile ProxyLoopers loopers;

    @Override
    public void start() {
        loopers = Image.createRoot(ProxyLoopers.class);
        prepareLooper(Looper.getMainLooper(), true);
    }

    /**
     * Prepares a proxy Looper in the image for {@code looper} on that Looper's thread, so that image Handlers post to
     * it.
     */
    public static void prepareLooper(Looper looper) {
        prepareLooper(looper, false);
    }

    private static void prepareLooper(Looper looper, boolean main) {
        clearDeadLoopers();
        long id = Image.handles().send(looper);
        Handler handler = HANDLERS.get(id);
        if (handler == null) {
            handler = new Handler(looper, message -> {
                loopers.dispatch(message.getWhen());
                return true;
            });
            HANDLERS.put(id, handler);
        }

        Runnable prepare = () -> {
            loopers.prepare(id, main);
            Looper.myQueue().addIdleHandler(() -> {
                loopers.idle();
                return true;
            });
        };
        if (looper.getThread() == Thread.currentThread()) {
            prepare.run();
        } else {
            handler.post(prepare);
        }
    }

    public static boolean post(long looper, long uptimeMillis) {
        clearDeadLoopers();
        Handler handler = HANDLERS.get(looper);

        if (handler == null) {
            return false;
        }

        Message message = Message.obtain(handler);
        return handler.sendMessageAtTime(message, uptimeMillis);
    }

    public static void quit(long looper, boolean safe) {
        clearDeadLoopers();
        Handler handler = HANDLERS.get(looper);

        if (handler == null) {
            return;
        }

        if (safe) {
            handler.getLooper().quitSafely();
        } else {
            handler.getLooper().quit();
        }
    }

    private static void clearDeadLoopers() {
        for (Map.Entry<Long, Handler> entry : HANDLERS.entrySet()) {
            if (!entry.getValue().getLooper().getThread().isAlive()) {
                if (HANDLERS.remove(entry.getKey(), entry.getValue())) {
                    Image.handles().drop(entry.getKey());
                }
            }
        }
    }
}
