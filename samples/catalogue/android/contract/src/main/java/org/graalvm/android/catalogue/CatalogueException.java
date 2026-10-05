package org.graalvm.android.catalogue;

public class CatalogueException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CatalogueException(String message, Throwable cause) {
        super(message, cause);
    }
}
