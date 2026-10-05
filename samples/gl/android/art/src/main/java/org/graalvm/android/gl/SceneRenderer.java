package org.graalvm.android.gl;

import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

final class SceneRenderer implements GLSurfaceView.Renderer {

    private final Scene scene;

    SceneRenderer(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        scene.created();
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        scene.resized(width, height);
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        scene.draw(System.nanoTime());
    }
}
