package org.graalvm.android.bridges;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

final class TextViewDisplay implements Display {

    private final Handler main = new Handler(Looper.getMainLooper());
    private final TextView view;

    TextViewDisplay(TextView view) {
        this.view = view;
    }

    @Override
    public void show(String text) {
        main.post(() -> view.append(text + "\n"));
    }

    @Override
    public void greet(Greeting greeting) {
        show("ART called back: " + greeting.text());
        NativeImage.releaseProxy(greeting);
    }
}
