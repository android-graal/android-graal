package org.graalvm.android.bridges.core;

import org.androidgraal.hosted.annotate.Art;
import org.androidgraal.hosted.annotate.Svm;

@Art
@Svm
public interface Display {
    void show(String text);

    void show(String text, int times);

    int ping(int value);

    char initial(String text);

    double mix(boolean z, byte b, char c, short s, long j, float f, double d);

    void open() throws Unavailable;

    void place(Placement placement, int layer);
}
