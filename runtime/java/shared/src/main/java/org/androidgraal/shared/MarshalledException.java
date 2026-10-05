package org.androidgraal.shared;

/** A throwable of the other side under its class's name. Its cause arrives as a {@code MarshalledException} too. */
public final class MarshalledException extends RuntimeException {

    private final String foreignExceptionClassName;

    public MarshalledException(String foreignExceptionClassName, String message, StackTraceElement[] stackTrace,
            Throwable cause) {
        super(message, cause);
        this.foreignExceptionClassName = foreignExceptionClassName;
        setStackTrace(stackTrace);
    }

    public String getForeignExceptionClassName() {
        return foreignExceptionClassName;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    @Override
    public String toString() {
        String message = getLocalizedMessage();
        return message != null ? foreignExceptionClassName + ": " + message : foreignExceptionClassName;
    }
}
