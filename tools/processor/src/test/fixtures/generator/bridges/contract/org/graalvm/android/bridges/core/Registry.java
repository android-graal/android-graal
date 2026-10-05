package org.graalvm.android.bridges.core;

import org.androidgraal.hosted.annotate.Art;

@Art
public interface Registry {
    void add(Listener listener);

    boolean remove(Listener listener);

    int size();
}
