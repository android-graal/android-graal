package org.androidgraal.shared;

import java.util.IdentityHashMap;

/** Maps an owned object to a handle id by identity. An id is never reused. */
final class Exports {

    private final long step;
    private long last;
    private final IdentityHashMap<Object, Long> byObject = new IdentityHashMap<>();
    private final LongIndex<Object> byId = new LongIndex<>();

    public Exports(long step) {
        this.step = step;
    }

    /** An id for the {@code object} to export. */
    public synchronized long send(Object object) {
        Long known = byObject.get(object);
        if (known != null) {
            return known;
        }
        last += step;
        byObject.put(object, last);
        byId.put(last, object);
        return last;
    }

    /** The object exported under {@code id}, or null when there is none. */
    public synchronized Object object(long id) {
        return byId.get(id);
    }

    /** Ends the export of {@code id}, returns the object exported under it, or null when there was none. */
    public synchronized Object remove(long id) {
        Object object = byId.remove(id);
        if (object != null) {
            byObject.remove(object);
        }
        return object;
    }

    public synchronized int size() {
        return byId.size();
    }
}
