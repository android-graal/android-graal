package org.graalvm.android.bridges;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import org.androidgraal.art.ImageStartException;
import org.androidgraal.shared.MarshalledException;

public class MainActivity extends Activity {

    private static Hello hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = new TextView(this);
        text.setTextSize(18);
        text.setPadding(32, 64, 32, 32);
        text.setTextColor(Color.BLACK);
        setContentView(text);
        Display display = new TextViewDisplay(text);

        try {
            if (!NativeImage.isStarted()) {
                NativeImage.start();
            }
            if (hello == null) {
                hello = NativeImage.createRoot(Hello.class);
            }
        } catch (ImageStartException e) {
            display.show("the image did not start: " + e);
            return;
        }

        hello.run(display);
        display.show("echo: " + hello.echo("äöü€😀"));
        try {
            hello.fail("thrown in the image");
        } catch (MarshalledException e) {
            display.show("caught " + e.getForeignExceptionClassName() + ": " + e.getMessage());
        }
    }
}
