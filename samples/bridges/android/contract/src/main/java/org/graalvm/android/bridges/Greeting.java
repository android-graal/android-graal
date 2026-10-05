package org.graalvm.android.bridges;

import org.androidgraal.hosted.annotate.Svm;

@Svm
public interface Greeting {
    String text();
}
