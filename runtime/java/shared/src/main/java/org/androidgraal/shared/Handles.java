package org.androidgraal.shared;

import java.util.Objects;

/**
 * A side's handle table: the objects it has exported to the other side and the one proxy it holds for each object
 * received from it. A reference crosses as a {@code long} id, 0 for null, the sign marks the owning side, never reused.
 * A proxy handed back to its owner resolves to the owner's object. An export lives until the side holding its proxy
 * calls {@link #release}.
 */
public final class Handles {

    /**
     * Builds the proxy this side holds for the object the other side exports under {@code id}, must be a
     * {@link Foreign}.
     */
    public interface Factory<T> {
        T create(long id);
    }

    /** Tells the other side that this side released its proxy of {@code id}, so it ends that export. */
    public interface Release {
        void release(long id);
    }

    private final Exports exports;
    private final Imports imports;
    private final Release onProxyReleased;
    private final long step;

    public Handles(long step, Release onProxyReleased) {
        this.exports = new Exports(step);
        this.imports = new Imports();
        this.onProxyReleased = onProxyReleased;
        this.step = step;
    }

    /** The id {@code object} crosses as: 0 for null, a proxy's own foreign id, otherwise this side's export of it. */
    public long send(Object object) {
        if (object == null) {
            return 0L;
        }
        if (object instanceof Foreign foreign) {
            return foreign.foreignId();
        }
        return exports.send(object);
    }

    /**
     * This side's own object or the proxy of the other side's
     */
    @SuppressWarnings("unchecked")
    public <T> T receive(long wire, Factory<? extends T> factory) {
        if (wire == 0L) {
            return null;
        }
        if ((wire > 0) == (step > 0)) {
            return object(wire);
        }
        if (factory == null) {
            throw new IllegalStateException(
                    "an object of the other side crossed as a type registered on this side only");
        }
        return (T) imports.getOrAdd(wire, factory);
    }

    /**
     * This side's object exported under {@code id}. An id whose proxy was released throws an
     * {@link IllegalStateException}
     */
    @SuppressWarnings("unchecked")
    public <T> T object(long id) {
        Object object = exports.object(id);
        if (object == null) {
            throw new IllegalStateException("no object exported as " + id + ": its proxy was released");
        }
        return (T) object;
    }

    /**
     * Releases {@code proxy} and ends the other side's export. The next crossing of the object brings a new proxy.
     * Every later use of {@code proxy} throws an {@link IllegalStateException}. Anything that is not a proxy throws
     * {@link IllegalArgumentException}.
     */
    public void release(Object proxy) {
        Objects.requireNonNull(proxy);
        if (!(proxy instanceof Foreign foreign)) {
            throw new IllegalArgumentException(proxy.getClass().getName() + " is not a proxy");
        }
        onProxyReleased.release(imports.release(foreign));
    }

    /**
     * Ends this side's export of {@code id}. Called by the other side's {@link Release}, never from this side. An
     * invalid id throws an {@link IllegalStateException}.
     */
    public void drop(long id) {
        if (exports.remove(id) == null) {
            throw new IllegalStateException("no object exported as " + id);
        }
    }

    /** The amount of currently exported objects. */
    public int ids() {
        return exports.size();
    }

    /** The amount of currently registered foreign proxies. */
    public int proxies() {
        return imports.size();
    }
}
