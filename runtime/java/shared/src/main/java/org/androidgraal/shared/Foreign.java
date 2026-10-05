package org.androidgraal.shared;

/**
 * The superclass of every generated proxy. Once released, every method of the proxy throws an
 * {@link IllegalStateException}.
 */
public abstract class Foreign {

    protected final long id;
    private volatile boolean released;

    protected Foreign(long id) {
        this.id = id;
    }

    public final long foreignId() {
        foreignCheck();
        return id;
    }

    public final void foreignCheck() {
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
