package org.androidgraal.shared;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/** Maps a foreign id to the one proxy this side holds for it, weakly, keeping the reference count of the id. */
final class Imports {

    private final LongIndex<Entry> byId = new LongIndex<>();
    private final ReferenceQueue<Proxy> releaseQueue = new ReferenceQueue<>();

    /** The live proxy held for {@code id}, built by {@code factory} when there is none, this reference counted. */
    public synchronized Proxy getOrAdd(long id, Handles.Factory<?> factory) {
        Entry known = byId.get(id);
        if (known != null) {
            Proxy proxy = known.get();
            if (proxy != null) {
                known.referenceCount++;
                return proxy;
            }
            byId.remove(id);
        }
        Proxy proxy = (Proxy) factory.create(id);
        byId.put(id, new Entry(proxy, id, releaseQueue));
        return proxy;
    }

    /**
     * Takes {@code proxy} out of the table and clears its entry, so that its collection releases nothing, marks it
     * released and returns its references.
     *
     * @throws IllegalStateException when {@code proxy} was released
     * @throws IllegalArgumentException when {@code proxy} is not the proxy this side holds under its id
     */
    public synchronized ReleasedReferences release(ReleasableProxy proxy) {
        long id = proxy.foreignId();
        Entry entry = byId.remove(id);
        if (entry == null || entry.get() != proxy) {
            throw new IllegalArgumentException(
                    proxy.getClass().getName() + " with the id " + id + " was not a proxy this side received");
        }

        entry.clear();
        proxy.markReleased();
        return new ReleasedReferences(id, entry.referenceCount);
    }

    /**
     * Blocks until a proxy is collected, then returns the references of it and of every other proxy collected by now.
     * An entry leaves the table only while it is still the one under its id.
     */
    public List<ReleasedReferences> nextCollected() throws InterruptedException {
        Entry dead = (Entry) releaseQueue.remove();
        List<ReleasedReferences> batch = new ArrayList<>();
        synchronized (this) {
            while (dead != null) {
                // If getOrAdd ran already, with a dead reference
                if (byId.get(dead.id) == dead) {
                    byId.remove(dead.id);
                }
                batch.add(new ReleasedReferences(dead.id, dead.referenceCount));
                dead = (Entry) releaseQueue.poll();
            }
        }
        return batch;
    }

    /** The proxies this side holds that are not collected yet. */
    public synchronized int size() {
        int live = 0;
        for (Entry entry : byId) {
            if (entry.get() != null) {
                live++;
            }
        }
        return live;
    }

    private static final class Entry extends WeakReference<Proxy> {

        private final long id;
        private long referenceCount = 1;

        private Entry(Proxy proxy, long id, ReferenceQueue<Proxy> collected) {
            super(proxy, collected);
            this.id = id;
        }
    }
}
