package android.os;

import android.util.Log;
import java.io.FileDescriptor;
import java.util.concurrent.CopyOnWriteArrayList;
import org.androidgraal.android.EmulationRoot;
import org.androidgraal.android.Target_android_os_Looper;
import org.androidgraal.hosted.annotate.Adapted;
import org.androidgraal.hosted.annotate.Added;
import org.androidgraal.hosted.annotate.Premise;

@Added(reason = "the queue of a proxy Looper, which keeps its messages in stock's list and runs them on its ART Looper's thread from dispatches posted there")
public final class ProxyMessageQueue extends MessageQueue {

    private final long looper;
    private final boolean quitAllowed;
    private final CopyOnWriteArrayList<IdleHandler> idleHandlers = new CopyOnWriteArrayList<>();
    private Message taken;

    public ProxyMessageQueue(long looper, boolean quitAllowed) {
        super(quitAllowed);
        this.looper = looper;
        this.quitAllowed = quitAllowed;
    }

    public void dispatch(Looper me, long uptimeMillis) {
        long ran;
        synchronized (this) {
            Message head = mMessages;
            if (head == null || head.when > uptimeMillis) {
                return;
            }
            ran = head.when;
            mMessages = head.next;
            head.next = null;
            taken = head;
        }
        // If there is a race, where while ART processed the queue, some other message was posted to the front in SVM,
        // the dispatch will run the front-posted message. Because of this, we repost the intended ran message again to ART.
        if (ran < uptimeMillis) {
            EmulationRoot.host().post(looper, uptimeMillis);
        }
        Target_android_os_Looper.loopOnce(me, Binder.clearCallingIdentity(), -1);
    }

    @Override
    @Adapted(reason = "posts one dispatch at the message's uptime on the ART Looper and quits safely when ART refuses the post and the queue may quit", premise = Premise.PROXY_LOOPER)
    public synchronized boolean enqueueMessage(Message msg, long when) {
        if (!EmulationRoot.host().post(looper, when) && quitAllowed) {
            super.quit(true);
        }
        return super.enqueueMessage(msg, when);
    }

    @Override
    @Adapted(reason = "returns the message the current dispatch took and throws when it took none", premise = Premise.PROXY_LOOPER)
    public synchronized Message next() {
        Message msg = taken;
        if (msg == null) {
            throw new IllegalStateException("A proxy Looper is dispatched by its ART Looper, never looped");
        }
        taken = null;
        return msg;
    }

    @Override
    @Adapted(reason = "quits the ART Looper, which dispatches the kept messages, instead of ending a loop", premise = Premise.PROXY_LOOPER)
    public void quit(boolean safe) {
        super.quit(safe);
        EmulationRoot.host().quit(looper, safe);
    }

    @Override
    @Adapted(reason = "has the ART Looper run the idle handlers whenever its own queue is idle", premise = Premise.PROXY_LOOPER)
    public void addIdleHandler(IdleHandler handler) {
        if (handler == null) {
            throw new NullPointerException("Can't add a null IdleHandler");
        }
        idleHandlers.add(handler);
    }

    @Override
    public void removeIdleHandler(IdleHandler handler) {
        idleHandlers.remove(handler);
    }

    public void idle() {
        for (IdleHandler idler : idleHandlers) {
            boolean keep = false;
            try {
                keep = idler.queueIdle();
            } catch (Throwable t) {
                Log.wtf("LegacyMessageQueue", "IdleHandler threw exception", t);
            }
            if (!keep) {
                removeIdleHandler(idler);
            }
        }
    }

    @Override
    @Adapted(reason = "throws instead of posting a sync barrier", premise = Premise.PROXY_LOOPER)
    public int postSyncBarrier() {
        throw new UnsupportedOperationException();
    }

    @Override
    @Adapted(reason = "throws instead of removing the sync barrier", premise = Premise.PROXY_LOOPER)
    public void removeSyncBarrier(int token) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Adapted(reason = "throws instead of watching the descriptor", premise = Premise.PROXY_LOOPER)
    public void addOnFileDescriptorEventListener(FileDescriptor fd, int events,
            OnFileDescriptorEventListener listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Adapted(reason = "throws instead of unwatching the descriptor", premise = Premise.PROXY_LOOPER)
    public void removeOnFileDescriptorEventListener(FileDescriptor fd) {
        throw new UnsupportedOperationException();
    }
}
