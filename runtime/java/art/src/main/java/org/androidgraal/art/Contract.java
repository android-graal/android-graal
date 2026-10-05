package org.androidgraal.art;

/** A contract class on ART, found as a service and initialised once, after the isolate starts. */
public interface Contract {

    void initialize();

    /** The contract's {@code @Root} interfaces. */
    Class<?>[] roots();

    /** A new image object implementing {@code root}, one of {@link #roots()}. */
    <T> T create(Class<T> root);
}
