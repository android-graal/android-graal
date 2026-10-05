package org.graalvm.android.bridges.core;

public class Refused extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public Refused(String message) {
        super(message);
    }
}
