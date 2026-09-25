package org.graalvm.android.hello;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class MainActivity extends Activity {

    static {
        // Loaded first so the launcher's dlopen("libhello.so") resolves the already loaded image.
        System.loadLibrary("hello");
        System.loadLibrary("launcher");
    }

    private static native int startGraalApp();

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        text = new TextView(this);
        text.setTextSize(18);
        text.setPadding(32, 64, 32, 32);
        text.setGravity(Gravity.CENTER);
        text.setTextColor(Color.BLACK);
        text.setText("starting native image ...");
        setContentView(text);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                final int exitCode;
                try {
                    exitCode = startGraalApp();
                } catch (Throwable e) {
                    show("run_main() threw: " + e);
                    return;
                }
                show("run_main() returned " + exitCode);
            }
        }, "graal-main");
        t.start();
    }

    private void show(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(message);
            }
        });
    }
}
