package org.androidgraal.art;

/**
 * A service {@link Image#start} runs once every contract is loaded, on the thread that started the image: every
 * provider's {@code prepare}, for what other providers build on, before any provider's {@code start}, in no further
 * order.
 */
public interface Startup {

    default void prepare() {
    }

    default void start() {
    }
}
