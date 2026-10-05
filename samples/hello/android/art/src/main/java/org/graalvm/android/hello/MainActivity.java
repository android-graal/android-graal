package org.graalvm.android.hello;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class MainActivity extends Activity {

    static {
        System.loadLibrary(NativeImage.NAME);
    }

    private static long thread;

    private static native long createIsolate();

    private static native void hello(long thread);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = new TextView(this);
        text.setTextSize(18);
        text.setPadding(32, 64, 32, 32);
        text.setGravity(Gravity.CENTER);
        text.setTextColor(Color.BLACK);
        setContentView(text);

        if (thread == 0) {
            thread = createIsolate();
        }
        if (thread == 0) {
            text.setText("createIsolate() returned 0");
            return;
        }
        hello(thread);
        text.setText("hello() returned");
    }
}
