package org.androidgraal.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/** Utility class to deal with java services */
public final class Services {

    private Services() {
    }

    /** A new object of every provider of {@code service}, in iteration order. */
    public static <T> List<T> all(Class<T> service) {
        List<T> providers = new ArrayList<>();
        for (T provider : ServiceLoader.load(service, service.getClassLoader())) {
            providers.add(provider);
        }
        return providers;
    }

    /**
     * A new object of the one provider of {@code service}. None or several throw an {@link IllegalStateException}.
     */
    public static <T> T one(Class<T> service) {
        List<T> providers = all(service);
        if (providers.size() == 1) {
            return providers.get(0);
        }
        StringBuilder message = new StringBuilder();
        message.append(providers.size()).append(" providers of ").append(service.getName());
        for (int i = 0; i < providers.size(); i++) {
            message.append(i == 0 ? ": " : ", ").append(providers.get(i).getClass().getName());
        }
        throw new IllegalStateException(message.toString());
    }
}
