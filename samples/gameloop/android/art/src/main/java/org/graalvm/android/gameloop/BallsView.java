package org.graalvm.android.gameloop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

final class BallsView extends View implements Choreographer.FrameCallback {

    private static final int[] COLORS = {0xFFE53935, 0xFF1E88E5, 0xFF43A047, 0xFFFDD835, 0xFF8E24AA, 0xFFFB8C00};

    private final Game game;
    private final int[] touches = new int[Game.TOUCH_INTS];
    private int touchInts;
    private final float[] balls = new float[Game.BALL_FLOATS];
    private final Paint[] paints = new Paint[COLORS.length];
    private int count;

    BallsView(Context context, Game game) {
        super(context);
        this.game = game;
        for (int i = 0; i < paints.length; i++) {
            paints[i] = new Paint(Paint.ANTI_ALIAS_FLAG);
            paints[i].setColor(COLORS[i]);
        }
        game.start();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        Choreographer.getInstance().removeFrameCallback(this);
        super.onDetachedFromWindow();
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        game.resize(width, height);
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        count = game.render(frameTimeNanos, touches, touchInts, balls);
        touchInts = 0;
        invalidate();
        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.rgb(24, 24, 32));
        for (int i = 0; i < count; i++) {
            canvas.drawCircle(balls[3 * i], balls[3 * i + 1], balls[3 * i + 2], paints[i % paints.length]);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                queue(Game.DOWN, event);
                return true;
            case MotionEvent.ACTION_MOVE:
                queue(Game.MOVE, event);
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                queue(Game.UP, event);
                return true;
            default:
                return false;
        }
    }

    private void queue(int action, MotionEvent event) {
        if (action == Game.MOVE && touchInts > 0 && touches[touchInts - 3] == Game.MOVE) {
            touchInts -= 3;
        } else if (touchInts == touches.length) {
            if (action != Game.UP) {
                return;
            }
            touchInts -= 3;
        }
        touches[touchInts++] = action;
        touches[touchInts++] = Float.floatToRawIntBits(event.getX());
        touches[touchInts++] = Float.floatToRawIntBits(event.getY());
    }
}
