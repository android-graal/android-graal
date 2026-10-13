package android.app;

import android.os.Parcelable;

/**
 * A description of an Intent and target action to perform with it.  Instances
 * of this class are created with {@link #getActivity}, {@link #getActivities},
 * {@link #getBroadcast}, and {@link #getService}; the returned object can be
 * handed to other applications so that they can perform the action you
 * described on your behalf at a later time.
 *
 * <p>By giving a PendingIntent to another application,
 * you are granting it the right to perform the operation you have specified
 * as if the other application was yourself (with the same permissions and
 * identity).  As such, you should be careful about how you build the PendingIntent:
 * almost always, for example, the base Intent you supply should have the component
 * name explicitly set to one of your own components, to ensure it is ultimately
 * sent there and nowhere else.
 *
 * <p>A PendingIntent itself is simply a reference to a token maintained by
 * the system describing the original data used to retrieve it.  This means
 * that, even if its owning application's process is killed, the
 * PendingIntent itself will remain usable from other processes that
 * have been given it.  If the creating application later re-retrieves the
 * same kind of PendingIntent (same operation, same Intent action, data,
 * categories, and components, and same flags), it will receive a PendingIntent
 * representing the same token if that is still valid, and can thus call
 * {@link #cancel} to remove it.
 *
 * <p>Because of this behavior, it is important to know when two Intents
 * are considered to be the same for purposes of retrieving a PendingIntent.
 * A common mistake people make is to create multiple PendingIntent objects
 * with Intents that only vary in their "extra" contents, expecting to get
 * a different PendingIntent each time.  This does <em>not</em> happen.  The
 * parts of the Intent that are used for matching are the same ones defined
 * by {@link Intent#filterEquals(Intent) Intent.filterEquals}.  If you use two
 * Intent objects that are equivalent as per
 * {@link Intent#filterEquals(Intent) Intent.filterEquals}, then you will get
 * the same PendingIntent for both of them.
 *
 * <p>There are two typical ways to deal with this.
 *
 * <p>If you truly need multiple distinct PendingIntent objects active at
 * the same time (such as to use as two notifications that are both shown
 * at the same time), then you will need to ensure there is something that
 * is different about them to associate them with different PendingIntents.
 * This may be any of the Intent attributes considered by
 * {@link Intent#filterEquals(Intent) Intent.filterEquals}, or different
 * request code integers supplied to {@link #getActivity}, {@link #getActivities},
 * {@link #getBroadcast}, or {@link #getService}.
 *
 * <p>If you only need one PendingIntent active at a time for any of the
 * Intents you will use, then you can alternatively use the flags
 * {@link #FLAG_CANCEL_CURRENT} or {@link #FLAG_UPDATE_CURRENT} to either
 * cancel or modify whatever current PendingIntent is associated with the
 * Intent you are supplying.
 *
 * <p>Also note that flags like {@link #FLAG_ONE_SHOT} or {@link #FLAG_IMMUTABLE} describe the
 * PendingIntent instance and thus, are used to identify it. Any calls to retrieve or modify a
 * PendingIntent created with these flags will also require these flags to be supplied in
 * conjunction with others. E.g. To retrieve an existing PendingIntent created with
 * FLAG_ONE_SHOT, <b>both</b> FLAG_ONE_SHOT and FLAG_NO_CREATE need to be supplied.
 */
public final class PendingIntent implements Parcelable {
    /**
     * Flag indicating that the created PendingIntent with {@link #FLAG_MUTABLE}
     * is allowed to have an unsafe implicit Intent within. <p>Starting with
     * {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE}, for apps that
     * target SDK {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE} or
     * higher, creation of a PendingIntent with {@link #FLAG_MUTABLE} and an
     * implicit Intent within will throw an {@link IllegalArgumentException}
     * for security reasons. To bypass this check, use
     * {@link #FLAG_ALLOW_UNSAFE_IMPLICIT_INTENT} when creating a PendingIntent.
     * However, it is strongly recommended to not to use this flag and make the
     * Intent explicit or the PendingIntent immutable, thereby making the Intent
     * safe.
     */
    public static final int FLAG_ALLOW_UNSAFE_IMPLICIT_INTENT = 16777216;

    /**
     * Flag indicating that if the described PendingIntent already exists,
     * the current one should be canceled before generating a new one.
     * For use with {@link #getActivity}, {@link #getBroadcast}, and
     * {@link #getService}. <p>You can use
     * this to retrieve a new PendingIntent when you are only changing the
     * extra data in the Intent; by canceling the previous pending intent,
     * this ensures that only entities given the new data will be able to
     * launch it.  If this assurance is not an issue, consider
     * {@link #FLAG_UPDATE_CURRENT}.
     */
    public static final int FLAG_CANCEL_CURRENT = 268435456;

    /**
     * Flag indicating that the created PendingIntent should be immutable.
     * This means that the additional intent argument passed to the send
     * methods to fill in unpopulated properties of this intent will be
     * ignored.
     *
     * <p>{@link #FLAG_IMMUTABLE} only limits the ability to alter the
     * semantics of the intent that is sent by {@link #send} by the invoker of
     * {@link #send}. The creator of the PendingIntent can always update the
     * PendingIntent itself via {@link #FLAG_UPDATE_CURRENT}.
     */
    public static final int FLAG_IMMUTABLE = 67108864;

    /**
     * Flag indicating that the created PendingIntent should be mutable.
     * This flag cannot be combined with {@link #FLAG_IMMUTABLE}. <p>Up until
     * {@link android.os.Build.VERSION_CODES#R}, PendingIntents are assumed to
     * be mutable by default, unless {@link #FLAG_IMMUTABLE} is set. Starting
     * with {@link android.os.Build.VERSION_CODES#S}, it will be required to
     * explicitly specify the mutability of PendingIntents on creation with
     * either {@link #FLAG_IMMUTABLE} or {@link #FLAG_MUTABLE}. It is strongly
     * recommended to use {@link #FLAG_IMMUTABLE} when creating a
     * PendingIntent. {@link #FLAG_MUTABLE} should only be used when some
     * functionality relies on modifying the underlying intent, e.g. any
     * PendingIntent that needs to be used with inline reply or bubbles.
     */
    public static final int FLAG_MUTABLE = 33554432;

    /**
     * Flag indicating that if the described PendingIntent does not
     * already exist, then simply return null instead of creating it.
     * For use with {@link #getActivity}, {@link #getBroadcast}, and
     * {@link #getService}.
     */
    public static final int FLAG_NO_CREATE = 536870912;

    /**
     * Flag indicating that this PendingIntent can be used only once.
     * For use with {@link #getActivity}, {@link #getBroadcast}, and
     * {@link #getService}. <p>If set, after
     * {@link #send()} is called on it, it will be automatically
     * canceled for you and any future attempt to send through it will fail.
     */
    public static final int FLAG_ONE_SHOT = 1073741824;

    /**
     * Flag indicating that if the described PendingIntent already exists,
     * then keep it but replace its extra data with what is in this new
     * Intent. For use with {@link #getActivity}, {@link #getBroadcast}, and
     * {@link #getService}. <p>This can be used if you are creating intents where only the
     * extras change, and don't care that any entities that received your
     * previous PendingIntent will be able to launch it with your new
     * extras even if they are not explicitly given to it.
     *
     * <p>{@link #FLAG_UPDATE_CURRENT} still works even if {@link
     * #FLAG_IMMUTABLE} is set - the creator of the PendingIntent can always
     * update the PendingIntent itself. The IMMUTABLE flag only limits the
     * ability to alter the semantics of the intent that is sent by {@link
     * #send} by the invoker of {@link #send}.
     */
    public static final int FLAG_UPDATE_CURRENT = 134217728;

    private PendingIntent() {
    }
}
