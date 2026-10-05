package org.graalvm.android.gl;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends Activity {

    private Scene scene;
    private GLSurfaceView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!NativeImage.isStarted()) {
            NativeImage.start();
        }
        scene = NativeImage.createRoot(Scene.class);
        view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(2);
        view.setRenderer(new SceneRenderer(scene));
        setContentView(view);
    }

    @Override
    protected void onPause() {
        super.onPause();
        view.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NativeImage.releaseProxy(scene);
    }
}
