package org.graalvm.android.bridges;

import org.androidgraal.hosted.annotate.Art;

@Art
public interface Display {
    void show(String text);

    void greet(Greeting greeting);
}
