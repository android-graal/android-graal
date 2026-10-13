package org.androidgraal.android.host;

import org.androidgraal.hosted.annotate.Root;
import org.androidgraal.hosted.annotate.Svm;

@Root
@Svm
public interface Emulation {

    void attach(ApplicationHost host);
}
