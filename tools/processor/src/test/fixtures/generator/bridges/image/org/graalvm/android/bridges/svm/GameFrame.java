package org.graalvm.android.bridges.svm;

import org.graalvm.android.bridges.core.Frame;
import org.graalvm.android.bridges.core.Mode;
import org.graalvm.android.bridges.core.Probe;

final class GameFrame implements Frame {
    public int step(int value) { return value + 1; }
    public Mode mode(Mode mode) { return mode; }
    public Frame self() { return this; }
    public int probe(Probe probe) { return 0; }
    public float sum(float[] values) { return 0; }
    public void fill(int[] values) { }
    public float sum16(float[] values) { return 0; }
    public void fill16(int[] values) { }
    public float sum16Tls(float[] values) { return 0; }
    public int total(int[] values, int count) { return 0; }
    public void squares(int[] values, int count) { }
    public void press(int x, int y, int pointer) { }
    public int events(int[] into) { return 0; }
    public float render(Probe probe) { return probe.x(); }
    public String text(String s) { return s; }
    public int[] range(int count) { return new int[count]; }
    public String join(int[] values, String separator) { return separator; }
    public String[] texts(String[] a) { return a; }
    public int count16(String s) { return s.length(); }
    public String text16(int value) { return String.valueOf(value); }
    public float[] corners4() { return new float[4]; }
}
