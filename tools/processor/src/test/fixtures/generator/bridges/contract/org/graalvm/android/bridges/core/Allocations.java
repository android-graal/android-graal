package org.graalvm.android.bridges.core;

import org.androidgraal.hosted.annotate.Art;

@Art
public interface Allocations {
    void start();

    long stop();

    void allocate(int count);
}
