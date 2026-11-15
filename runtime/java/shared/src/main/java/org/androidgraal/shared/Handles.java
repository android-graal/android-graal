package org.androidgraal.shared;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A side's handle table: the objects it has exported to the other side and the one proxy it holds for each object
 * received from it. A reference crosses as a {@code long} id, 0 for null, the sign marks the owning side, never reused.
 * A proxy handed back to its owner resolves to the owner's object. A proxy is held weakly and keeps the reference count
 * of its id, an export counts its sends. An export ends once the other side has released as many references as it was
 * sent, which it does when its proxy is collected or {@link #release released}. A proxy's export may already have ended
 * when a finalizer on the proxy's side runs, so a finalizer must not use a proxy.
 */
public final class Handles {

    /**
     * Builds the proxy this side holds for the object the other side exports under {@code id}, must be a {@link Proxy}.
     */
    public interface Factory<T> {
        T create(long id);
    }

    /**
     * Hands the other side the references of proxies this side no longer holds, for the other side's
     * {@link #releaseExports}.
     */
    public interface Release {
        void release(List<ReleasedReferences> batch);
    }

    private final Exports exports;
    private final Imports imports;
    private final Release release;
    private final long idStep;

    /** The table of the side whose ids have the sign of {@code idStep}. */
    public Handles(long idStep, Release release) {
        this.exports = new Exports(idStep);
        this.imports = new Imports();
        this.release = release;
        this.idStep = idStep;
    }

    /** The id {@code object} crosses as: 0 for null, a proxy's own foreign id, otherwise this side's export of it. */
    public long send(Object object) {
        if (object == null) {
            return 0L;
        }
        if (object instanceof Proxy proxy) {
            return proxy.foreignId();
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
        if ((wire > 0) == (idStep > 0)) {
            return object(wire);
        }
        if (factory == null) {
            throw new IllegalStateException(
                    "an object of the other side crossed as a type registered on this side only");
        }
        return (T) imports.getOrAdd(wire, factory);
    }

    /**
     * This side's object exported under {@code id}. An id whose export ended throws an {@link IllegalStateException}.
     */
    @SuppressWarnings("unchecked")
    public <T> T object(long id) {
        Object object = exports.object(id);
        if (object == null) {
            throw new IllegalStateException("no object exported as " + id);
        }
        return (T) object;
    }

    /**
     * Releases {@code proxy} and its references at once, without waiting for its collection. The next crossing of the
     * object creates a new proxy. Every later use of {@code proxy} throws an {@link IllegalStateException}. Only a
     * {@link ReleasableProxy} can be released, everything else throws an {@link IllegalArgumentException}.
     */
    public void release(Object proxy) {
        Objects.requireNonNull(proxy);
        if (!(proxy instanceof ReleasableProxy releasableProxy)) {
            throw new IllegalArgumentException(proxy.getClass().getName() + " is not a ReleasableProxy");
        }
        release.release(Collections.singletonList(imports.release(releasableProxy)));
    }

    /**
     * Ends the exports whose sends a batch of the other side's {@link Release} releases. An element naming an id this
     * side does not export, or more references than it sent, throws an {@link IllegalStateException}, the elements
     * after it are not applied.
     */
    public void releaseExports(List<ReleasedReferences> batch) {
        for (ReleasedReferences released : batch) {
            exports.release(released);
        }
    }

    /**
     * Blocks until a proxy of this side is collected, then releases its references and those of every other proxy
     * collected by then in one batch.
     */
    public void releaseNext() throws InterruptedException {
        release.release(imports.nextCollected());
    }

    /** Runs {@link #releaseNext} for as long as the process lives. Never returns. */
    public void releaseForever() {
        while (true) {
            try {
                releaseNext();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    /**
     * Ends this side's export of {@code id} whatever its sends. An invalid id throws an {@code IllegalStateException}.
     */
    public void forceDropExport(long id) {
        if (exports.forceRemove(id) == null) {
            throw new IllegalStateException("no object exported as " + id);
        }
    }

    /** The amount of currently exported objects. */
    public int ids() {
        return exports.size();
    }

    /** The amount of proxies this side holds that are not collected yet. */
    public int proxies() {
        return imports.size();
    }

    /**
     * A handle's tag identifying the class and proxy id: {@code id} in the high 48 bits, its class {@code ordinal} in
     * the low 16.
     */
    public static long tagOf(long id, int ordinal) {
        return id << 16 | ordinal;
    }

    /** The proxy id {@code tag} carries, its sign kept. */
    public static long idOf(long tag) {
        return tag >> 16;
    }

    /** The class ordinal {@code tag} carries. */
    public static int ordinalOf(long tag) {
        return (int) (tag & 0xFFFF);
    }
}
