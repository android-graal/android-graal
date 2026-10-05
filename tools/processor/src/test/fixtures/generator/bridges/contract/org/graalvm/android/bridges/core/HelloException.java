package org.graalvm.android.bridges.core;

public class HelloException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public HelloException(String message) {
        super(message);
    }
}
