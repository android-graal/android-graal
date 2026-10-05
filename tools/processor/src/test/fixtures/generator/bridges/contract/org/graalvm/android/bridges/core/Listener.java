package org.graalvm.android.bridges.core;

import org.androidgraal.hosted.annotate.Svm;

@Svm
public interface Listener {
    void on(int event);
}
