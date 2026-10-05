package org.graalvm.android.gameloop;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private static Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!NativeImage.isStarted()) {
            NativeImage.start();
        }
        if (game == null) {
            game = NativeImage.createRoot(Game.class);
        }
        setContentView(new BallsView(this, game));
    }
}
