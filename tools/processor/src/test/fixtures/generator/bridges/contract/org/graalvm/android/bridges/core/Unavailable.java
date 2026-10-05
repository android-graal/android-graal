package org.graalvm.android.bridges.core;

public class Unavailable extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public Unavailable(String message, Throwable cause) {
        super(message, cause);
    }
}
