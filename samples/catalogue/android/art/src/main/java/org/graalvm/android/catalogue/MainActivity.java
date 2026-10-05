package org.graalvm.android.catalogue;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Arrays;
import org.androidgraal.art.ImageStartException;
import org.androidgraal.shared.MarshalledException;

public class MainActivity extends Activity {

    private static Factory factory;

    private Patterns patterns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = new TextView(this);
        text.setTextSize(14);
        text.setPadding(32, 64, 32, 32);
        text.setTextColor(Color.BLACK);
        setContentView(text);
        Sink sink = new TextSink(text);

        try {
            if (!NativeImage.isStarted()) {
                NativeImage.start();
            }
            if (factory == null) {
                factory = NativeImage.createRoot(Factory.class);
            }
            patterns = factory.newPatterns();
        } catch (ImageStartException e) {
            sink.line("the image did not start: " + e);
            return;
        }

        sink.line("not: " + patterns.not(true));
        sink.line("negate: " + patterns.negate((byte) 5));
        sink.line("upper: " + patterns.upper('a'));
        sink.line("twice: " + patterns.twice((short) 21));
        sink.line("square: " + patterns.square(7));
        sink.line("cube: " + patterns.cube(3));
        sink.line("half: " + patterns.half(3f));
        sink.line("root: " + patterns.root(2));
        sink.line("next: " + patterns.next(Mode.AUTO));
        sink.line("next: " + patterns.next(null));
        sink.line("echo: " + patterns.echo("äöü€😀"));
        sink.line("place: " + patterns.place(new Placement("title", Mode.ON, new Spot(3, 4))));
        sink.line("place: " + patterns.place(null));
        sink.line("sum: " + patterns.sum(new int[] {1, 2, 3, 4}, 3));
        float[] floats = new float[3];
        patterns.fill(floats);
        sink.line("fill: " + Arrays.toString(floats));
        sink.line("first: " + patterns.first(new long[] {7, 8}));
        byte[] bytes = new byte[3];
        patterns.bytes(bytes);
        sink.line("bytes: " + Arrays.toString(bytes));
        float[] sixteen = new float[16];
        patterns.fill16(sixteen);
        sink.line("fill16: " + Arrays.toString(sixteen));
        int[] counted = new int[16];
        Arrays.fill(counted, 2);
        sink.line("sum16Tls: " + patterns.sum16Tls(counted, 5));
        sink.line("range: " + Arrays.toString(patterns.range(4)));
        sink.line("length16: " + patterns.length16("sixteen or less"));
        sink.line("label16: " + patterns.label16(42));
        sink.line("greet: " + patterns.greet("catalogue"));
        sink.line("tag: " + Patterns.tag(7));
        patterns.show(sink);
        Peer peer = patterns.swap(new ArtPeer());
        sink.line("swap: " + peer.name());
        sink.line("describe: " + peer.describe());
        NativeImage.releaseProxy(peer);
        sink.line("self: " + (patterns.self() == patterns));
        try {
            patterns.fail("thrown in the image");
        } catch (MarshalledException e) {
            sink.line("caught " + carried(e));
        }
        try {
            patterns.read("missing.txt");
        } catch (MarshalledException e) {
            sink.line("caught " + carried(e) + ", cause " + carried((MarshalledException) e.getCause()));
        }
    }

    @Override
    protected void onDestroy() {
        if (patterns != null) {
            NativeImage.releaseProxy(patterns);
            patterns = null;
        }
        super.onDestroy();
    }

    private static String carried(MarshalledException e) {
        return e.getForeignExceptionClassName() + ": " + e.getMessage();
    }
}
