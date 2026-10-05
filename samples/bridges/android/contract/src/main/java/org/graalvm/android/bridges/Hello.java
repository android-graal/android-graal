package org.graalvm.android.bridges;

import org.androidgraal.hosted.annotate.Root;
import org.androidgraal.hosted.annotate.Svm;

@Root
@Svm
public interface Hello {
    void run(Display display);

    String echo(String text);

    void fail(String message) throws HelloException;
}
