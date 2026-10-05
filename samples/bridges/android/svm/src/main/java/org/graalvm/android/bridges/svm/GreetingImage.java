package org.graalvm.android.bridges.svm;

import org.graalvm.android.bridges.Greeting;

public final class GreetingImage implements Greeting {

    @Override
    public String text() {
        return "a greeting the image implements";
    }
}
