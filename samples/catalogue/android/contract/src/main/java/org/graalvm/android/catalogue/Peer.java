package org.graalvm.android.catalogue;

import org.androidgraal.hosted.annotate.Art;
import org.androidgraal.hosted.annotate.Svm;

@Art
@Svm
public interface Peer {
    String name();

    default String describe() {
        return "peer " + name();
    }
}
