package android.health.connect;

/**
 * This class provides APIs to interact with the centralized HealthConnect storage maintained by the
 * system.
 *
 * <p>HealthConnect is an offline, on-device storage that unifies data from multiple devices and
 * apps into an ecosystem featuring.
 *
 * <ul>
 *   <li>APIs to insert data of various types into the system.
 * </ul>
 *
 * <p>The basic unit of data in HealthConnect is represented as a {@link Record} object, which is
 * the base class for all the other data types such as {@link
 * android.health.connect.datatypes.StepsRecord}.
 */
public final class HealthConnectManager {
    /**
     * Activity action: Launch UI to manage (e.g. grant/revoke) health permissions.
     *
     * <p>Shows a list of apps which request at least one permission of the Health permission group.
     *
     * <p>Input: {@link android.content.Intent#EXTRA_PACKAGE_NAME} string extra with the name of the
     * app requesting the action. Optional: Adding package name extras launches a UI to manager
     * (e.g. grant/revoke) for this app.
     */
    public static final String ACTION_MANAGE_HEALTH_PERMISSIONS = "android.health.connect.action.MANAGE_HEALTH_PERMISSIONS";

    /**
     * Activity action: Launch UI to share the route associated with an exercise session.
     *
     * <p>Input: caller must provide `String` extra EXTRA_SESSION_ID
     *
     * <p>Result will be delivered via [Activity.onActivityResult] with `ExerciseRoute`
     * EXTRA_EXERCISE_ROUTE.
     */
    public static final String ACTION_REQUEST_EXERCISE_ROUTE = "android.health.connect.action.REQUEST_EXERCISE_ROUTE";

    /**
     * Used in conjunction with {@link android.content.Intent#ACTION_VIEW_PERMISSION_USAGE} to
     * launch UI to show an app’s health permission rationale/data policy.
     *
     * <p><b>Note:</b> Used by apps to define an intent filter in conjunction with {@link
     * android.content.Intent#ACTION_VIEW_PERMISSION_USAGE} that the HC UI can link out to.
     */
    public static final String CATEGORY_HEALTH_PERMISSIONS = "android.intent.category.HEALTH_PERMISSIONS";

    /**
     * An exercise route requested via {@link #ACTION_REQUEST_EXERCISE_ROUTE}.
     *
     * <p>This is returned for a successful request to access a route associated with an exercise
     * session.
     */
    public static final String EXTRA_EXERCISE_ROUTE = "android.health.connect.extra.EXERCISE_ROUTE";

    /**
     * A string ID of a session to be used with {@link #ACTION_REQUEST_EXERCISE_ROUTE}.
     *
     * <p>This is used to specify route of which exercise session we want to request.
     */
    public static final String EXTRA_SESSION_ID = "android.health.connect.extra.SESSION_ID";

    private HealthConnectManager() {
    }
}
