package android.os;

/**
 * Takes care of the grunt work of maintaining a list of remote interfaces,
 * typically for the use of performing callbacks from a
 * {@link android.app.Service} to its clients.  In particular, this:
 *
 * <ul>
 * <li> Keeps track of a set of registered {@link IInterface} objects,
 * taking care to identify them through their underlying unique {@link IBinder}
 * (by calling {@link IInterface#asBinder IInterface.asBinder()}.
 * <li> Attaches a {@link IBinder.DeathRecipient IBinder.DeathRecipient} to
 * each registered interface, so that it can be cleaned out of the list if its
 * process goes away.
 * <li> Performs locking of the underlying list of interfaces to deal with
 * multithreaded incoming calls, and a thread-safe way to iterate over a
 * snapshot of the list without holding its lock.
 * </ul>
 *
 * <p>To use this class, simply create a single instance along with your
 * service, and call its {@link #register} and {@link #unregister} methods
 * as client register and unregister with your service.  To call back on to
 * the registered clients, use {@link #beginBroadcast},
 * {@link #getBroadcastItem}, and {@link #finishBroadcast}.
 *
 * <p>If a registered interface's process goes away, this class will take
 * care of automatically removing it from the list.  If you want to do
 * additional work in this situation, you can create a subclass that
 * implements the {@link #onCallbackDied} method.
 */
public final class RemoteCallbackList {
    /**
     * When the callback recipient's process is frozen, callbacks are suppressed as if they never
     * happened.
     *
     * This could be useful in the case where the recipient wishes to react to callbacks only when
     * they occur while the recipient is not frozen. For example, certain network events are only
     * worth responding to if the response can be immediate. Another example is recipients having
     * another way of getting the latest state once it's unfrozen. Therefore there is no need to
     * save callbacks that happened while the recipient was frozen.
     */
    public static final int FROZEN_CALLEE_POLICY_DROP = 3;

    /**
     * When the callback recipient's process is frozen, callbacks are enqueued so they're invoked
     * after the recipient is unfrozen.
     *
     * This is commonly used when the recipient wants to receive all callbacks without losing any
     * history, e.g. the recipient maintains a running count of events that occurred.
     *
     * Queued callbacks are invoked in the order they were originally broadcasted.
     */
    public static final int FROZEN_CALLEE_POLICY_ENQUEUE_ALL = 1;

    /**
     * When the callback recipient's process is frozen, only the most recent callback is enqueued,
     * which is later invoked after the recipient is unfrozen.
     *
     * This can be used when only the most recent state matters, for instance when clients are
     * listening to screen brightness changes.
     */
    public static final int FROZEN_CALLEE_POLICY_ENQUEUE_MOST_RECENT = 2;

    /**
     * Callbacks are invoked immediately regardless of the frozen state of the target process.
     *
     * Not recommended. Only exists for backward-compatibility. This represents the behavior up to
     * SDK 35. Starting with SDK 36, clients should set a policy to govern callback invocations when
     * recipients are frozen.
     */
    public static final int FROZEN_CALLEE_POLICY_UNSET = 0;

    private RemoteCallbackList() {
    }
}
