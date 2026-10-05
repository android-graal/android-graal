package org.androidgraal.shared;

/** Maps a foreign id to the one proxy this side holds for it. */
final class Imports {

    private final LongIndex<Foreign> byId = new LongIndex<>();

    /** The proxy held for {@code id}, built by {@code factory} on the first call for that id. */
    public synchronized Foreign getOrAdd(long id, Handles.Factory<?> factory) {
        Foreign known = byId.get(id);
        if (known != null) {
            return known;
        }
        Foreign proxy = (Foreign) factory.create(id);
        byId.put(id, proxy);
        return proxy;
    }

    /**
     * Returns the id of {@code proxy}, now released.
     *
     * @throws IllegalStateException when {@code proxy} was released
     * @throws IllegalArgumentException when {@code proxy} is not the proxy this side holds under its id
     */
    public synchronized long release(Foreign proxy) {
        long id = proxy.foreignId();
        if (byId.remove(id) != proxy) {
            throw new IllegalArgumentException(
                    proxy.getClass().getName() + " with the id " + id + " is not a proxy this side received");
        }
        proxy.markReleased();
        return id;
    }

    public synchronized int size() {
        return byId.size();
    }
}
