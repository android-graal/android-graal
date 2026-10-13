package org.androidgraal.android;

import org.graalvm.word.PointerBase;

public final class Scoped<T extends PointerBase> {

    private final T pointer;

    private Scoped(T pointer) {
        this.pointer = pointer;
    }

    public static <T extends PointerBase> Scoped<T> of(T pointer) {
        return new Scoped<>(pointer);
    }

    public T get() {
        return pointer;
    }
}
