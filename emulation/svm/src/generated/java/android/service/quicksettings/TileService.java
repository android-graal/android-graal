package android.service.quicksettings;

/**
 * A TileService provides the user a tile that can be added to Quick Settings.
 * Quick Settings is a space provided that allows the user to change settings and
 * take quick actions without leaving the context of their current app.
 *
 * <p>The lifecycle of a TileService is different from some other services in
 * that it may be unbound during parts of its lifecycle.  Any of the following
 * lifecycle events can happen independently in a separate binding/creation of the
 * service.</p>
 *
 * <ul>
 * <li>When a tile is added by the user its TileService will be bound to and
 * {@link #onTileAdded()} will be called.</li>
 *
 * <li>When a tile should be up to date and listing will be indicated by
 * {@link #onStartListening()} and {@link #onStopListening()}.</li>
 *
 * <li>When the user removes a tile from Quick Settings {@link #onTileRemoved()}
 * will be called.</li>
 *
 * <li>{@link #onTileAdded()} and {@link #onTileRemoved()} may be called outside of the
 * {@link #onCreate()} - {@link #onDestroy()} window</li>
 * </ul>
 * <p>TileService will resolve against services that match the {@value #ACTION_QS_TILE} action
 * and require the permission {@code android.permission.BIND_QUICK_SETTINGS_TILE}.
 * The label and icon for the service will be used as the default label and
 * icon for the tile. Here is an example TileService declaration.</p>
 * <pre class="prettyprint">
 * {@literal
 * <service
 *     android:name=".MyQSTileService"
 *     android:label="@string/my_default_tile_label"
 *     android:icon="@drawable/my_default_icon_label"
 *     android:permission="android.permission.BIND_QUICK_SETTINGS_TILE">
 *     <intent-filter>
 *         <action android:name="android.service.quicksettings.action.QS_TILE" />
 *     </intent-filter>
 * </service>}
 * </pre>
 *
 * @see Tile Tile for details about the UI of a Quick Settings Tile.
 */
public final class TileService {
    /**
     * Action that identifies a Service as being a TileService.
     */
    public static final String ACTION_QS_TILE = "android.service.quicksettings.action.QS_TILE";

    /**
     * An activity that provides a user interface for adjusting TileService
     * preferences. Optional but recommended for apps that implement a
     * TileService.
     * <p>
     * This intent may also define a {@link Intent#EXTRA_COMPONENT_NAME} value
     * to indicate the {@link ComponentName} that caused the preferences to be
     * opened.
     * <p>
     * To ensure that the activity can only be launched through quick settings
     * UI provided by this service, apps can protect it with the
     * BIND_QUICK_SETTINGS_TILE permission.
     */
    public static final String ACTION_QS_TILE_PREFERENCES = "android.service.quicksettings.action.QS_TILE_PREFERENCES";

    /**
     * Meta-data for tile definition to set a tile into active mode.
     * <p>
     * Active mode is for tiles which already listen and keep track of their state in their
     * own process.  These tiles may request to send an update to the System while their process
     * is alive using {@link #requestListeningState}.  The System will only bind these tiles
     * on its own when a click needs to occur.
     *
     * To make a TileService an active tile, set this meta-data to true on the TileService's
     * manifest declaration.
     * <pre class="prettyprint">
     * {@literal
     * <meta-data android:name="android.service.quicksettings.ACTIVE_TILE"
     *      android:value="true" />
     * }
     * </pre>
     */
    public static final String META_DATA_ACTIVE_TILE = "android.service.quicksettings.ACTIVE_TILE";

    /**
     * Meta-data for a tile to mark is toggleable.
     * <p>
     * Toggleable tiles support switch tile behavior in accessibility. This is
     * the behavior of most of the framework tiles.
     *
     * To indicate that a TileService is toggleable, set this meta-data to true on the
     * TileService's manifest declaration.
     * <pre class="prettyprint">
     * {@literal
     * <meta-data android:name="android.service.quicksettings.TOGGLEABLE_TILE"
     *      android:value="true" />
     * }
     * </pre>
     */
    public static final String META_DATA_TOGGLEABLE_TILE = "android.service.quicksettings.TOGGLEABLE_TILE";

    private TileService() {
    }
}
