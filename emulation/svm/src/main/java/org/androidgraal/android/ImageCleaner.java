package org.androidgraal.android;

import java.lang.ref.Cleaner;

final class ImageCleaner {

    private static final Cleaner CLEANER = Cleaner.create();

    private ImageCleaner() {
    }

    /**
     * Runs {@code action} on the cleaner's thread once {@code object} is dead. An action that retains {@code object}
     * keeps it reachable forever. An exception thrown by an action is printed and swallowed.
     */
    public static void register(Object object, Runnable action) {
        CLEANER.register(object, () -> {
            try {
                action.run();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
