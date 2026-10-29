package org.androidgraal.android;

import android.os.MessageQueue;
import android.os.MessageQueue.OnFileDescriptorEventListener;
import android.util.LongSparseArray;
import android.util.SparseIntArray;
import libcore.util.SneakyThrow;
import org.androidgraal.bionic.ALooper;
import org.graalvm.nativeimage.CurrentIsolate;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.function.CEntryPoint.IsolateThreadContext;
import org.graalvm.nativeimage.c.function.CEntryPoint.Publish;
import org.graalvm.nativeimage.c.function.CEntryPointLiteral;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.type.CIntPointer;
import org.graalvm.nativeimage.c.type.WordPointer;
import org.graalvm.word.PointerBase;
import org.graalvm.word.WordFactory;

public final class NativeMessageQueue {

    private static final LongSparseArray<NativeMessageQueue> QUEUES = new LongSparseArray<>();
    private static long nextPtr = 1;
    private static final ThreadLocal<NativeMessageQueue> POLLING = new ThreadLocal<>();

    private final PointerBase looper;
    private final IsolateThread thread;
    private final SparseIntArray watchedEvents = new SparseIntArray();
    private MessageQueue pollObj;
    private Throwable exception;
    private volatile boolean polling;

    private NativeMessageQueue(PointerBase looper, IsolateThread thread) {
        this.looper = looper;
        this.thread = thread;
    }

    public static long nativeInit() {
        PointerBase looper = ALooper.ALooper_prepare(0);
        ALooper.ALooper_acquire(looper);
        NativeMessageQueue queue = new NativeMessageQueue(looper, CurrentIsolate.getCurrentThread());
        synchronized (QUEUES) {
            long ptr = nextPtr++;
            QUEUES.put(ptr, queue);
            return ptr;
        }
    }

    public static void nativeDestroy(long ptr) {
        NativeMessageQueue queue;
        synchronized (QUEUES) {
            queue = QUEUES.get(ptr);
            QUEUES.remove(ptr);
        }
        ALooper.ALooper_release(queue.looper);
    }

    public static void nativePollOnce(MessageQueue obj, long ptr, int timeoutMillis) {
        NativeMessageQueue queue = getQueue(ptr);
        queue.pollObj = obj;
        POLLING.set(queue);
        queue.polling = true;
        ALooper.ALooper_pollOnce(timeoutMillis, WordFactory.nullPointer(), WordFactory.nullPointer(), WordFactory.nullPointer());
        queue.polling = false;
        POLLING.remove();
        queue.pollObj = null;

        Throwable thrown = queue.exception;
        if (thrown != null) {
            queue.exception = null;
            SneakyThrow.sneakyThrow(thrown);
        }
    }

    public static void nativeWake(long ptr) {
        ALooper.ALooper_wake(getQueue(ptr).looper);
    }

    public static boolean nativeIsPolling(long ptr) {
        return getQueue(ptr).polling;
    }

    public static void nativeSetFileDescriptorEvents(long ptr, int fd, int events) {
        getQueue(ptr).setFileDescriptorEvents(fd, events);
    }

    private static NativeMessageQueue getQueue(long ptr) {
        synchronized (QUEUES) {
            return QUEUES.get(ptr);
        }
    }

    private void setFileDescriptorEvents(int fd, int events) {
        if (events != 0) {
            int looperEvents = 0;
            if ((events & OnFileDescriptorEventListener.EVENT_INPUT) != 0) {
                looperEvents |= ALooper.ALOOPER_EVENT_INPUT();
            }
            if ((events & OnFileDescriptorEventListener.EVENT_OUTPUT) != 0) {
                looperEvents |= ALooper.ALOOPER_EVENT_OUTPUT();
            }
            synchronized (watchedEvents) {
                watchedEvents.put(fd, events);
                ALooper.ALooper_addFd(looper, fd, ALooper.ALOOPER_POLL_CALLBACK(), looperEvents,
                        LooperCallback.LITERAL.getFunctionPointer(), thread);
            }
        } else {
            synchronized (watchedEvents) {
                watchedEvents.delete(fd);
                ALooper.ALooper_removeFd(looper, fd);
            }
        }
    }

    private int handleEvent(int fd, int looperEvents) {
        int events = 0;
        if ((looperEvents & ALooper.ALOOPER_EVENT_INPUT()) != 0) {
            events |= OnFileDescriptorEventListener.EVENT_INPUT;
        }
        if ((looperEvents & ALooper.ALOOPER_EVENT_OUTPUT()) != 0) {
            events |= OnFileDescriptorEventListener.EVENT_OUTPUT;
        }
        if ((looperEvents & (ALooper.ALOOPER_EVENT_ERROR() | ALooper.ALOOPER_EVENT_HANGUP()
                | ALooper.ALOOPER_EVENT_INVALID())) != 0) {
            events |= OnFileDescriptorEventListener.EVENT_ERROR;
        }
        int oldWatchedEvents;
        synchronized (watchedEvents) {
            oldWatchedEvents = watchedEvents.get(fd);
        }
        int newWatchedEvents = ((Target_android_os_MessageQueue) (Object) pollObj).dispatchEvents(fd, events);
        if (newWatchedEvents == 0) {
            synchronized (watchedEvents) {
                watchedEvents.delete(fd);
            }
            return 0;
        }
        if (newWatchedEvents != oldWatchedEvents) {
            setFileDescriptorEvents(fd, newWatchedEvents);
        }
        return 1;
    }

    @CEntryPoint(publishAs = Publish.NotPublished)
    private static int looperCallback(int fd, int looperEvents, @IsolateThreadContext IsolateThread data) {
        NativeMessageQueue queue = null;
        try {
            queue = POLLING.get();
            if (queue == null) {
                return 0;
            }
            return queue.handleEvent(fd, looperEvents);
        } catch (Throwable t) {
            if (queue != null) {
                queue.exception = t;
            }
            return 0;
        }
    }

    private static final class LooperCallback {
        private static final CEntryPointLiteral<CFunctionPointer> LITERAL = CEntryPointLiteral
                .create(NativeMessageQueue.class, "looperCallback", int.class, int.class, IsolateThread.class);
    }
}
