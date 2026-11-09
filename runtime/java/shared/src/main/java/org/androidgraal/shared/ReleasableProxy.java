package org.androidgraal.shared;

/**
 * A {@link Proxy} that can be explicitly released over {@link Handles#release}. Once released, every method of the
 * proxy throws an {@link IllegalStateException}.
 */
public abstract class ReleasableProxy implements Proxy {

    protected final long id;
    private volatile boolean released;

    protected ReleasableProxy(long id) {
        this.id = id;
    }

    @Override
    public final long foreignId() {
        checkNotReleased();
        return id;
    }

    public final void checkNotReleased() {
        if (released) {
            throw new IllegalStateException(getClass().getName() + " proxy " + id + " was released");
        }
    }

    public final boolean isReleased() {
        return released;
    }

    public final void markReleased() {
        released = true;
    }
}
