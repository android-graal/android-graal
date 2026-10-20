package android.service.quicksettings;

import android.os.Parcelable;

/**
 * A Tile holds the state of a tile that will be displayed
 * in Quick Settings.
 *
 * A tile in Quick Settings exists as an icon with an accompanied label.
 * It also may have content description for accessibility usability.
 * The style and layout of the tile may change to match a given
 * device.
 */
public final class Tile implements Parcelable {
    /**
     * This represents a tile that is currently active. (e.g. wifi is connected, bluetooth is on,
     * cast is casting).  This is the default state.
     */
    public static final int STATE_ACTIVE = 2;

    /**
     * This represents a tile that is currently in a disabled state but is still interactable.
     *
     * A disabled state indicates that the tile is not currently active (e.g. wifi disconnected or
     * bluetooth disabled), but is still interactable by the user to modify this state.  Tiles
     * that have boolean states should use this to represent one of their states.  The tile's
     * icon will be tinted differently to reflect this state, but still be distinct from unavailable.
     */
    public static final int STATE_INACTIVE = 1;

    /**
     * An unavailable state indicates that for some reason this tile is not currently
     * available to the user, and will have no click action.  The tile's icon will be
     * tinted differently to reflect this state.
     */
    public static final int STATE_UNAVAILABLE = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private Tile() {
    }
}
