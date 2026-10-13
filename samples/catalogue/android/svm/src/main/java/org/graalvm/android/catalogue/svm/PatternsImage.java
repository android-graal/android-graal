package org.graalvm.android.catalogue.svm;

import java.io.IOException;
import java.util.Arrays;
import org.androidgraal.shared.MarshalledException;
import org.androidgraal.svm.Art;
import org.graalvm.android.catalogue.CatalogueException;
import org.graalvm.android.catalogue.ImageException;
import org.graalvm.android.catalogue.Mode;
import org.graalvm.android.catalogue.Patterns;
import org.graalvm.android.catalogue.Peer;
import org.graalvm.android.catalogue.Placement;
import org.graalvm.android.catalogue.Sink;
import org.graalvm.android.catalogue.Spot;

public final class PatternsImage implements Patterns {

    @Override
    public boolean not(boolean v) {
        return !v;
    }

    @Override
    public byte negate(byte v) {
        return (byte) -v;
    }

    @Override
    public char upper(char v) {
        return Character.toUpperCase(v);
    }

    @Override
    public short twice(short v) {
        return (short) (v * 2);
    }

    @Override
    public int square(int v) {
        return v * v;
    }

    @Override
    public long cube(long v) {
        return v * v * v;
    }

    @Override
    public float half(float v) {
        return v / 2;
    }

    @Override
    public double root(double v) {
        return Math.sqrt(v);
    }

    @Override
    public Mode next(Mode mode) {
        return mode == null ? null : Mode.values()[(mode.ordinal() + 1) % Mode.values().length];
    }

    @Override
    public String echo(String text) {
        return text;
    }

    @Override
    public String place(Placement placement) {
        return String.valueOf(placement);
    }

    @Override
    public int sum(int[] values, int count) {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += values[i];
        }
        return sum;
    }

    @Override
    public void fill(float[] values) {
        for (int i = 0; i < values.length; i++) {
            values[i] = i / 2f;
        }
    }

    @Override
    public long first(long[] values) {
        return values[0];
    }

    @Override
    public void bytes(byte[] values) {
        for (int i = 0; i < values.length; i++) {
            values[i] = (byte) (i + 1);
        }
    }

    @Override
    public void fill16(float[] values) {
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }
    }

    @Override
    public int sum16Tls(int[] values, int count) {
        return sum(values, count);
    }

    @Override
    public int[] range(int count) {
        int[] values = new int[count];
        for (int i = 0; i < count; i++) {
            values[i] = i;
        }
        return values;
    }

    @Override
    public String[] reverse(String[] words) {
        String[] reversed = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            reversed[i] = words[words.length - 1 - i];
        }
        return reversed;
    }

    @Override
    public int length16(String text) {
        return text.length();
    }

    @Override
    public String label16(int value) {
        return "label " + value;
    }

    @Override
    public void show(Sink sink) {
        sink.line("line from the image");
        sink.level(Mode.AUTO);
        sink.samples(new float[] {0.25f, 0.5f, 0.75f}, 2);
        int[] values = new int[3];
        sink.collect(values);
        sink.line("collected " + Arrays.toString(values));
        sink.line("accepted " + sink.accept(new PeerImage()));
        float[] sixteen = new float[16];
        for (int i = 0; i < sixteen.length; i++) {
            sixteen[i] = i / 4f;
        }
        sink.samples16(sixteen);
        int[] collected = new int[16];
        sink.collect16Tls(collected);
        sink.line("collected16 " + Arrays.toString(collected));
        sink.line("squares " + Arrays.toString(sink.squares(4)));
        sink.line("capitals " + Arrays.toString(sink.capitals(new String[] {"a", null, "c"})));
        sink.line("width16 " + sink.width16("from the image"));
        sink.line("corners4 " + Arrays.toString(sink.corners4()));
        sink.place(new Placement("from the image", Mode.OFF, new Spot(5, 6)));
        try {
            sink.fail("thrown on ART");
        } catch (MarshalledException e) {
            sink.line("caught " + e.getForeignExceptionClassName() + ": " + e.getMessage());
        }
        sink.line("primitives returned " + sink.primitives(true, (byte) 1, 'x', (short) 2, 3, 4L, 0.5f, 0.25));
        Thread thread = new Thread(() -> sink.line("line from a thread the image started"), "image-thread");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Art.handles().release(sink);
    }

    @Override
    public Peer swap(Peer peer) {
        System.out.println("swap received " + peer.name());
        System.out.println("describe " + peer.describe());
        Art.handles().release(peer);
        return new PeerImage();
    }

    @Override
    public Patterns self() {
        return this;
    }

    @Override
    public void fail(String message) {
        throw new ImageException(message);
    }

    @Override
    public void read(String name) {
        try {
            load(name);
        } catch (IOException e) {
            throw new CatalogueException("reading " + name + " failed", e);
        }
    }

    private static void load(String name) throws IOException {
        throw new IOException("cannot read " + name);
    }
}
