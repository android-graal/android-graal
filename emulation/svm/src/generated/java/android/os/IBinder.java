package android.os;

/**
 * Base interface for a remotable object, the core part of a lightweight
 * remote procedure call mechanism designed for high performance when
 * performing in-process and cross-process calls.  This
 * interface describes the abstract protocol for interacting with a
 * remotable object.  Do not implement this interface directly, instead
 * extend from {@link Binder}.
 *
 * <p>The key IBinder API is {@link #transact transact()} matched by
 * {@link Binder#onTransact Binder.onTransact()}.  These
 * methods allow you to send a call to an IBinder object and receive a
 * call coming in to a Binder object, respectively.  This transaction API
 * is synchronous, such that a call to {@link #transact transact()} does not
 * return until the target has returned from
 * {@link Binder#onTransact Binder.onTransact()}; this is the
 * expected behavior when calling an object that exists in the local
 * process, and the underlying inter-process communication (IPC) mechanism
 * ensures that these same semantics apply when going across processes.
 *
 * <p>The data sent through transact() is a {@link Parcel}, a generic buffer
 * of data that also maintains some meta-data about its contents.  The meta
 * data is used to manage IBinder object references in the buffer, so that those
 * references can be maintained as the buffer moves across processes.  This
 * mechanism ensures that when an IBinder is written into a Parcel and sent to
 * another process, if that other process sends a reference to that same IBinder
 * back to the original process, then the original process will receive the
 * same IBinder object back.  These semantics allow IBinder/Binder objects to
 * be used as a unique identity (to serve as a token or for other purposes)
 * that can be managed across processes.
 *
 * <p>The system maintains a pool of transaction threads in each process that
 * it runs in.  These threads are used to dispatch all
 * IPCs coming in from other processes.  For example, when an IPC is made from
 * process A to process B, the calling thread in A blocks in transact() as
 * it sends the transaction to process B.  The next available pool thread in
 * B receives the incoming transaction, calls Binder.onTransact() on the target
 * object, and replies with the result Parcel.  Upon receiving its result, the
 * thread in process A returns to allow its execution to continue.  In effect,
 * other processes appear to use as additional threads that you did not create
 * executing in your own process.
 *
 * <p>The Binder system also supports recursion across processes.  For example
 * if process A performs a transaction to process B, and process B while
 * handling that transaction calls transact() on an IBinder that is implemented
 * in A, then the thread in A that is currently waiting for the original
 * transaction to finish will take care of calling Binder.onTransact() on the
 * object being called by B.  This ensures that the recursion semantics when
 * calling remote binder object are the same as when calling local objects.
 *
 * <p>When working with remote objects, you often want to find out when they
 * are no longer valid.  There are three ways this can be determined:
 * <ul>
 * <li> The {@link #transact transact()} method will throw a
 * {@link RemoteException} exception if you try to call it on an IBinder
 * whose process no longer exists.
 * <li> The {@link #pingBinder()} method can be called, and will return false
 * if the remote process no longer exists.
 * <li> The {@link #linkToDeath linkToDeath()} method can be used to register
 * a {@link DeathRecipient} with the IBinder, which will be called when its
 * containing process goes away.
 * </ul>
 *
 * @see Binder
 */
public interface IBinder {
    /**
     * IBinder protocol transaction code: dump internal state.
     */
    int DUMP_TRANSACTION = 1598311760;

    /**
     * The first transaction code available for user commands.
     */
    int FIRST_CALL_TRANSACTION = 1;

    /**
     * Flag to {@link #transact}: this is a one-way call, meaning that the
     * caller returns immediately, without waiting for a result from the
     * callee. Applies only if the caller and callee are in different
     * processes.
     *
     * <p>The system provides special ordering semantics for multiple oneway calls
     * being made to the same IBinder object: these calls will be dispatched in the
     * other process one at a time, with the same order as the original calls.  These
     * are still dispatched by the IPC thread pool, so may execute on different threads,
     * but the next one will not be dispatched until the previous one completes.  This
     * ordering is not guaranteed for calls on different IBinder objects or when mixing
     * oneway and non-oneway calls on the same IBinder object.</p>
     */
    int FLAG_ONEWAY = 1;

    /**
     * IBinder protocol transaction code: interrogate the recipient side
     * of the transaction for its canonical interface descriptor.
     */
    int INTERFACE_TRANSACTION = 1598968902;

    /**
     * The last transaction code available for user commands.
     */
    int LAST_CALL_TRANSACTION = 16777215;

    /**
     * IBinder protocol transaction code: tell an app asynchronously that the
     * caller likes it.  The app is responsible for incrementing and maintaining
     * its own like counter, and may display this value to the user to indicate the
     * quality of the app.  This is an optional command that applications do not
     * need to handle, so the default implementation is to do nothing.
     *
     * <p>There is no response returned and nothing about the
     * system will be functionally affected by it, but it will improve the
     * app's self-esteem.
     */
    int LIKE_TRANSACTION = 1598835019;

    /**
     * IBinder protocol transaction code: pingBinder().
     */
    int PING_TRANSACTION = 1599098439;

    /**
     * IBinder protocol transaction code: send a tweet to the target
     * object.  The data in the parcel is intended to be delivered to
     * a shared messaging service associated with the object; it can be
     * anything, as long as it is not more than 130 UTF-8 characters to
     * conservatively fit within common messaging services.  As part of
     * {@link Build.VERSION_CODES#HONEYCOMB_MR2}, all Binder objects are
     * expected to support this protocol for fully integrated tweeting
     * across the platform.  To support older code, the default implementation
     * logs the tweet to the main log as a simple emulation of broadcasting
     * it publicly over the Internet.
     *
     * <p>Also, upon completing the dispatch, the object must make a cup
     * of tea, return it to the caller, and exclaim "jolly good message
     * old boy!".
     */
    int TWEET_TRANSACTION = 1599362900;

    /**
     * A callback interface for receiving frozen state change events.
     */
    interface FrozenStateChangeCallback {
        /**
         * Represents the frozen state of the remote process.
         *
         * While in this state, the remote process won't be able to receive and handle a
         * transaction. Therefore, any asynchronous transactions will be buffered and delivered when
         * the process is unfrozen, and any synchronous transactions will result in an error.
         *
         * Buffered transactions may be stale by the time that the process is unfrozen and handles
         * them. To avoid overwhelming the remote process with stale events or overflowing their
         * buffers, it's best to avoid sending binder transactions to a frozen process.
         */
        int STATE_FROZEN = 0;

        /**
         * Represents the unfrozen state of the remote process.
         *
         * In this state, the process hosting the object can execute and is not restricted
         * by the freezer from using the CPU or responding to binder transactions.
         */
        int STATE_UNFROZEN = 1;
    }
}
