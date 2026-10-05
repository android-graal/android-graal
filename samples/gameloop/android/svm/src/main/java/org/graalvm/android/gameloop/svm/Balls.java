package org.graalvm.android.gameloop.svm;

import java.util.Random;
import org.graalvm.android.gameloop.Game;

public final class Balls implements Game {

    private static final int MAX = 64;
    private static final float MAX_STEP = 0.05f;
    private static final float PULL = 6f;
    private static final float DRAG = 0.8f;

    private final float[] x = new float[MAX];
    private final float[] y = new float[MAX];
    private final float[] vx = new float[MAX];
    private final float[] vy = new float[MAX];
    private final float[] radius = new float[MAX];
    private final Random random = new Random(42);
    private int count;
    private int next;
    private float width;
    private float height;
    private long lastFrame;
    private boolean held;
    private float heldX;
    private float heldY;

    @Override
    public void start() {
        lastFrame = 0;
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        while (count < 8) {
            spawn(random.nextFloat() * width, random.nextFloat() * height);
        }
    }

    @Override
    public int render(long frameNanos, int[] touches, int touchInts, float[] balls) {
        for (int i = 0; i + 3 <= touchInts; i += 3) {
            heldX = Float.intBitsToFloat(touches[i + 1]);
            heldY = Float.intBitsToFloat(touches[i + 2]);
            held = touches[i] != UP;
            if (touches[i] == DOWN) {
                spawn(heldX, heldY);
            }
        }
        float dt = lastFrame == 0 ? 0 : Math.min((frameNanos - lastFrame) / 1e9f, MAX_STEP);
        lastFrame = frameNanos;
        int written = Math.min(count, balls.length / 3);
        for (int i = 0; i < count; i++) {
            if (held) {
                pull(i, dt);
            }
            x[i] += vx[i] * dt;
            y[i] += vy[i] * dt;
            bounce(i);
            if (i < written) {
                balls[3 * i] = x[i];
                balls[3 * i + 1] = y[i];
                balls[3 * i + 2] = radius[i];
            }
        }
        return written;
    }

    private void spawn(float atX, float atY) {
        int i = next;
        next = (next + 1) % MAX;
        count = Math.max(count, next == 0 ? MAX : next);
        float speed = 0.5f * Math.max(width, height);
        x[i] = atX;
        y[i] = atY;
        vx[i] = (random.nextFloat() - 0.5f) * speed;
        vy[i] = (random.nextFloat() - 0.5f) * speed;
        radius[i] = 0.02f * Math.min(width, height) * (1 + random.nextFloat());
    }

    private void pull(int i, float dt) {
        vx[i] += (PULL * (heldX - x[i]) - DRAG * vx[i]) * dt;
        vy[i] += (PULL * (heldY - y[i]) - DRAG * vy[i]) * dt;
    }

    private void bounce(int i) {
        float r = radius[i];
        if (x[i] < r || x[i] > width - r) {
            x[i] = Math.max(r, Math.min(width - r, x[i]));
            vx[i] = -vx[i];
        }
        if (y[i] < r || y[i] > height - r) {
            y[i] = Math.max(r, Math.min(height - r, y[i]));
            vy[i] = -vy[i];
        }
    }
}
