package org.androidgraal.shared;

import java.util.IdentityHashMap;

/** Maps an owned object to a handle id by identity, counting its references. An id is never reused. */
final class Exports {

    private final long idStep;
    private final IdentityHashMap<Object, Entry> byObject = new IdentityHashMap<>();
    private final LongIndex<Entry> byId = new LongIndex<>();
    private long currentId;

    public Exports(long idStep) {
        this.idStep = idStep;
    }

    /** The id {@code object} is exported under. */
    public synchronized long send(Object object) {
        Entry known = byObject.get(object);
        if (known == null) {
            currentId += idStep;
            known = new Entry(currentId, object);
            byObject.put(object, known);
            byId.put(currentId, known);
        }
        known.referenceCount++;
        return known.id;
    }

    /** The object exported under {@code id}, or null when there is none. */
    public synchronized Object object(long id) {
        Entry entry = byId.get(id);
        return entry == null ? null : entry.object;
    }

    /** Ends the export of {@code id}, returns the object exported under it, or null. Ignores all reference counts. */
    public synchronized Object forceRemove(long id) {
        Entry entry = byId.remove(id);
        if (entry == null) {
            return null;
        }
        byObject.remove(entry.object);
        return entry.object;
    }

    /**
     * Processes the release request {@code released} and ends its export when all references are released.
     *
     * @throws IllegalStateException when {@code id} is not exported or was released more often than retained
     */
    public synchronized void release(ReleasedReferences released) {
        long id = released.id();
        long referenceCount = released.referenceCount();
        Entry entry = byId.get(id);
        if (entry == null) {
            throw new IllegalStateException(
                    "released " + referenceCount + " references of " + id + ", no object exported as " + id);
        }
        if (referenceCount < 1 || referenceCount > entry.referenceCount) {
            throw new IllegalStateException(
                    "released " + referenceCount + " references of " + id + ", sent " + entry.referenceCount);
        }
        entry.referenceCount -= referenceCount;
        if (entry.referenceCount == 0) {
            byId.remove(id);
            byObject.remove(entry.object);
        }
    }

    public synchronized int size() {
        return byId.size();
    }

    private static final class Entry {

        private final long id;
        private final Object object;
        private long referenceCount;

        private Entry(long id, Object object) {
            this.id = id;
            this.object = object;
        }
    }
}
