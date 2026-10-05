package org.graalvm.android.gl;

import org.androidgraal.hosted.annotate.Root;
import org.androidgraal.hosted.annotate.Svm;

@Root
@Svm
public interface Scene {
    void created();

    void resized(int width, int height);

    void draw(long frameNanos);
}
