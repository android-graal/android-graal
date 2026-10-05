package org.graalvm.android.catalogue;

import org.androidgraal.hosted.annotate.Root;
import org.androidgraal.hosted.annotate.Svm;

@Root
@Svm
public interface Factory {
    Patterns newPatterns();
}
