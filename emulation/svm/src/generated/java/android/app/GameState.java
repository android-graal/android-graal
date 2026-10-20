package android.app;

import android.os.Parcelable;

/**
 * State of the game passed to the GameManager.
 *
 * This includes a top-level state for the game (indicating if the game can be interrupted without
 * interfering with content that can't be paused). Since content can be loaded in any state, it
 * includes an independent boolean flag to indicate loading status.
 *
 * Free-form metadata (as a Bundle) and a string description can also be specified by the
 * application.
 */
public final class GameState implements Parcelable {
    /**
     * Indicates that the current content shown is not gameplay related. For example it can be an
     * ad, a web page, a text, or a video.
     */
    public static final int MODE_CONTENT = 4;

    /**
     * Indicates if the game is in active, but interruptible, game play.
     */
    public static final int MODE_GAMEPLAY_INTERRUPTIBLE = 2;

    /**
     * Indicates if the game is in active user play mode, which is real time and cannot be
     *  interrupted.
     */
    public static final int MODE_GAMEPLAY_UNINTERRUPTIBLE = 3;

    /**
     * No mode means that the game is not in active play, for example the user is using the game
     * menu.
     */
    public static final int MODE_NONE = 1;

    /**
     * Default Game mode is unknown.
     */
    public static final int MODE_UNKNOWN = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private GameState() {
    }
}
