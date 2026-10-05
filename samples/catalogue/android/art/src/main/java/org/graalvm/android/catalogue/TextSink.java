package org.graalvm.android.catalogue;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import java.util.Arrays;

final class TextSink implements Sink {

    private final Handler main = new Handler(Looper.getMainLooper());
    private final TextView view;

    TextSink(TextView view) {
        this.view = view;
    }

    @Override
    public void line(String text) {
        main.post(() -> view.append(text + "\n"));
    }

    @Override
    public void level(Mode mode) {
        line("level: " + mode);
    }

    @Override
    public void samples(float[] values, int count) {
        line("samples: " + Arrays.toString(Arrays.copyOf(values, count)));
    }

    @Override
    public void collect(int[] values) {
        for (int i = 0; i < values.length; i++) {
            values[i] = (i + 1) * 10;
        }
    }

    @Override
    public int accept(Peer peer) {
        String name = peer.name();
        NativeImage.releaseProxy(peer);
        line("accept: " + name);
        return name.length();
    }

    @Override
    public void samples16(float[] values) {
        line("samples16: " + Arrays.toString(values));
    }

    @Override
    public void collect16Tls(int[] values) {
        for (int i = 0; i < values.length; i++) {
            values[i] = i * i;
        }
    }

    @Override
    public int[] squares(int count) {
        int[] values = new int[count];
        for (int i = 0; i < count; i++) {
            values[i] = i * i;
        }
        return values;
    }

    @Override
    public int width16(String text) {
        line("width16: " + text);
        return text.length();
    }

    @Override
    public float[] corners4() {
        return new float[] {0, 1, 1, 0};
    }

    @Override
    public void place(Placement placement) {
        Spot spot = placement.spot();
        line("place: " + placement.label() + ", " + placement.mode() + ", " + spot.x() + "/" + spot.y());
    }

    @Override
    public void fail(String message) {
        throw new ArtException(message);
    }

    @Override
    public long primitives(boolean b, byte y, char c, short s, int i, long l, float f, double d) {
        line("primitives: " + b + " " + y + " " + c + " " + s + " " + i + " " + l + " " + f + " " + d);
        return y + s + i + l;
    }
}
