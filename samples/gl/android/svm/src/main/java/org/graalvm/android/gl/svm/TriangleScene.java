package org.graalvm.android.gl.svm;

import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import org.graalvm.android.gl.Scene;

public final class TriangleScene implements Scene {

    private static final String VERTEX_SHADER = """
            attribute vec2 a_position;
            uniform float u_angle;
            void main() {
                float c = cos(u_angle);
                float s = sin(u_angle);
                gl_Position = vec4(mat2(c, s, -s, c) * a_position, 0.0, 1.0);
            }
            """;

    private static final String FRAGMENT_SHADER = """
            precision mediump float;
            void main() {
                gl_FragColor = vec4(1.0, 1.0, 1.0, 1.0);
            }
            """;

    private static final float[] TRIANGLE = {0f, 0.6f, -0.52f, -0.3f, 0.52f, -0.3f};

    private final int[] status = new int[1];
    private int program;
    private int position;
    private int angle;
    private int vertices;
    private long firstFrame;

    @Override
    public void created() {
        program = link(compile(GLES20.GL_VERTEX_SHADER, VERTEX_SHADER),
                compile(GLES20.GL_FRAGMENT_SHADER, FRAGMENT_SHADER));
        position = GLES20.glGetAttribLocation(program, "a_position");
        angle = GLES20.glGetUniformLocation(program, "u_angle");

        FloatBuffer data = ByteBuffer.allocateDirect(TRIANGLE.length * Float.BYTES).order(ByteOrder.nativeOrder())
                .asFloatBuffer().put(TRIANGLE);
        data.flip();
        int[] buffer = new int[1];
        GLES20.glGenBuffers(1, buffer, 0);
        vertices = buffer[0];
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertices);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, TRIANGLE.length * Float.BYTES, data, GLES20.GL_STATIC_DRAW);
    }

    @Override
    public void resized(int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void draw(long frameNanos) {
        if (firstFrame == 0) {
            firstFrame = frameNanos;
        }
        float t = (frameNanos - firstFrame) / 1e9f;
        GLES20.glClearColor(wave(t, 0), wave(t, 2.1f), wave(t, 4.2f), 1f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        GLES20.glUseProgram(program);
        GLES20.glUniform1f(angle, t);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertices);
        GLES20.glEnableVertexAttribArray(position);
        GLES20.glVertexAttribPointer(position, 2, GLES20.GL_FLOAT, false, 0, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
    }

    private static float wave(float t, float phase) {
        return 0.35f + 0.25f * (float) Math.sin(t + phase);
    }

    private int compile(int type, String source) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, source);
        GLES20.glCompileShader(shader);
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, status, 0);
        if (status[0] == 0) {
            throw new IllegalStateException(GLES20.glGetShaderInfoLog(shader));
        }
        return shader;
    }

    private int link(int vertexShader, int fragmentShader) {
        int linked = GLES20.glCreateProgram();
        GLES20.glAttachShader(linked, vertexShader);
        GLES20.glAttachShader(linked, fragmentShader);
        GLES20.glLinkProgram(linked);
        GLES20.glGetProgramiv(linked, GLES20.GL_LINK_STATUS, status, 0);
        if (status[0] == 0) {
            throw new IllegalStateException(GLES20.glGetProgramInfoLog(linked));
        }
        return linked;
    }
}
