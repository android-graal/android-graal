package org.graalvm.android.bridges.svm;

import org.graalvm.android.bridges.core.Allocations;
import org.graalvm.android.bridges.core.Display;
import org.graalvm.android.bridges.core.Frame;
import org.graalvm.android.bridges.core.Hello;
import org.graalvm.android.bridges.core.HelloException;
import org.graalvm.android.bridges.core.Probe;
import org.graalvm.android.bridges.core.Refused;
import org.graalvm.android.bridges.core.Registry;

public final class HelloImage implements Hello {
    private final GameFrame frame = new GameFrame();

    public void run(Display display) { display.show("hello from the image"); }
    public String echo(String s) { return s; }
    public void fail(String message) { throw new IllegalArgumentException(message); }
    public void failTyped(String message) { throw new HelloException(message); }
    public void refuse(String message) throws Refused { throw new Refused(message); }
    public int ping(int value) { return value; }
    public long callbacks(Display display, int count, boolean onImageThread) { return 0; }
    public void shows(Display display, int count, boolean onImageThread) { }
    public Hello self() { return this; }
    public Display same(Display display) { return display; }
    public boolean register(Registry registry) { return false; }
    public void churn(Registry registry, int count) { }
    public void coldThrow(Display display) { }
    public long liveIds() { return 0; }
    public long liveProxies() { return 0; }
    public void gc() { }
    public int threads() { return 0; }
    public Frame frame() { return frame; }
    public int values(Display display, boolean onImageThread) { return 0; }
    public long loop(Probe probe, Display display, Allocations counter, int shape, int count, boolean onImageThread) {
        return 0;
    }
}
