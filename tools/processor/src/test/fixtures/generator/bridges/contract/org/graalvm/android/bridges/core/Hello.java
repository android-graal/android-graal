package org.graalvm.android.bridges.core;

import org.androidgraal.hosted.annotate.Root;
import org.androidgraal.hosted.annotate.Svm;

@Root
@Svm
public interface Hello {
    void run(Display display);

    String echo(String s);

    void fail(String message);

    void failTyped(String message) throws HelloException;

    void refuse(String message) throws Refused;

    int ping(int value);

    default int doubled(int value) {
        return 2 * ping(value);
    }

    static String describe(int value) {
        return "hello " + value;
    }

    long callbacks(Display display, int count, boolean onImageThread);

    void shows(Display display, int count, boolean onImageThread);

    Hello self();

    Display same(Display display);

    boolean register(Registry registry);

    void churn(Registry registry, int count);

    void coldThrow(Display display);

    long liveIds();

    long liveProxies();

    void gc();

    int threads();

    Frame frame();

    int values(Display display, boolean onImageThread);

    long loop(Probe probe, Display display, Allocations counter, int shape, int count, boolean onImageThread);
}
