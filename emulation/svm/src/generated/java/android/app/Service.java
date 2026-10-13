package android.app;

import android.content.ComponentCallbacks2;

/**
 * A Service is an application component representing either an application's desire
 * to perform a longer-running operation while not interacting with the user
 * or to supply functionality for other applications to use.  Each service
 * class must have a corresponding
 * {@link android.R.styleable#AndroidManifestService &lt;service&gt;}
 * declaration in its package's <code>AndroidManifest.xml</code>.  Services
 * can be started with
 * {@link android.content.Context#startService Context.startService()} and
 * {@link android.content.Context#bindService Context.bindService()}.
 *
 * <p>Note that services, like other application objects, run in the main
 * thread of their hosting process.  This means that, if your service is going
 * to do any CPU intensive (such as MP3 playback) or blocking (such as
 * networking) operations, it should spawn its own thread in which to do that
 * work.  More information on this can be found in
 * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html">Processes and
 * Threads</a>.  The {@link androidx.core.app.JobIntentService} class is available
 * as a standard implementation of Service that has its own thread where it
 * schedules its work to be done.</p>
 *
 * <p>Topics covered here:
 * <ol>
 * <li><a href="#WhatIsAService">What is a Service?</a>
 * <li><a href="#ServiceLifecycle">Service Lifecycle</a>
 * <li><a href="#Permissions">Permissions</a>
 * <li><a href="#ProcessLifecycle">Process Lifecycle</a>
 * <li><a href="#LocalServiceSample">Local Service Sample</a>
 * <li><a href="#RemoteMessengerServiceSample">Remote Messenger Service Sample</a>
 * </ol>
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For a detailed discussion about how to create services, read the
 * <a href="{@docRoot}guide/topics/fundamentals/services.html">Services</a> developer guide.</p>
 * </div>
 *
 * <a name="WhatIsAService"></a>
 * <h3>What is a Service?</h3>
 *
 * <p>Most confusion about the Service class actually revolves around what
 * it is <em>not</em>:</p>
 *
 * <ul>
 * <li> A Service is <b>not</b> a separate process.  The Service object itself
 * does not imply it is running in its own process; unless otherwise specified,
 * it runs in the same process as the application it is part of.
 * <li> A Service is <b>not</b> a thread.  It is not a means itself to do work off
 * of the main thread (to avoid Application Not Responding errors).
 * </ul>
 *
 * <p>Thus a Service itself is actually very simple, providing two main features:</p>
 *
 * <ul>
 * <li>A facility for the application to tell the system <em>about</em>
 * something it wants to be doing in the background (even when the user is not
 * directly interacting with the application).  This corresponds to calls to
 * {@link android.content.Context#startService Context.startService()}, which
 * ask the system to schedule work for the service, to be run until the service
 * or someone else explicitly stop it.
 * <li>A facility for an application to expose some of its functionality to
 * other applications.  This corresponds to calls to
 * {@link android.content.Context#bindService Context.bindService()}, which
 * allows a long-standing connection to be made to the service in order to
 * interact with it.
 * </ul>
 *
 * <p>When a Service component is actually created, for either of these reasons,
 * all that the system actually does is instantiate the component
 * and call its {@link #onCreate} and any other appropriate callbacks on the
 * main thread.  It is up to the Service to implement these with the appropriate
 * behavior, such as creating a secondary thread in which it does its work.</p>
 *
 * <p>Note that because Service itself is so simple, you can make your
 * interaction with it as simple or complicated as you want: from treating it
 * as a local Java object that you make direct method calls on (as illustrated
 * by <a href="#LocalServiceSample">Local Service Sample</a>), to providing
 * a full remoteable interface using AIDL.</p>
 *
 * <a name="ServiceLifecycle"></a>
 * <h3>Service Lifecycle</h3>
 *
 * <p>There are two reasons that a service can be run by the system.  If someone
 * calls {@link android.content.Context#startService Context.startService()} then the system will
 * retrieve the service (creating it and calling its {@link #onCreate} method
 * if needed) and then call its {@link #onStartCommand} method with the
 * arguments supplied by the client.  The service will at this point continue
 * running until {@link android.content.Context#stopService Context.stopService()} or
 * {@link #stopSelf()} is called.  Note that multiple calls to
 * Context.startService() do not nest (though they do result in multiple corresponding
 * calls to onStartCommand()), so no matter how many times it is started a service
 * will be stopped once Context.stopService() or stopSelf() is called; however,
 * services can use their {@link #stopSelf(int)} method to ensure the service is
 * not stopped until started intents have been processed.
 *
 * <p>For started services, there are two additional major modes of operation
 * they can decide to run in, depending on the value they return from
 * onStartCommand(): {@link #START_STICKY} is used for services that are
 * explicitly started and stopped as needed, while {@link #START_NOT_STICKY}
 * or {@link #START_REDELIVER_INTENT} are used for services that should only
 * remain running while processing any commands sent to them.  See the linked
 * documentation for more detail on the semantics.
 *
 * <p>Clients can also use {@link android.content.Context#bindService Context.bindService()} to
 * obtain a persistent connection to a service.  This likewise creates the
 * service if it is not already running (calling {@link #onCreate} while
 * doing so), but does not call onStartCommand().  The client will receive the
 * {@link android.os.IBinder} object that the service returns from its
 * {@link #onBind} method, allowing the client to then make calls back
 * to the service.  The service will remain running as long as the connection
 * is established (whether or not the client retains a reference on the
 * service's IBinder).  Usually the IBinder returned is for a complex
 * interface that has been <a href="{@docRoot}guide/components/aidl.html">written
 * in aidl</a>.
 *
 * <p>A service can be both started and have connections bound to it.  In such
 * a case, the system will keep the service running as long as either it is
 * started <em>or</em> there are one or more connections to it with the
 * {@link android.content.Context#BIND_AUTO_CREATE Context.BIND_AUTO_CREATE}
 * flag.  Once neither
 * of these situations hold, the service's {@link #onDestroy} method is called
 * and the service is effectively terminated.  All cleanup (stopping threads,
 * unregistering receivers) should be complete upon returning from onDestroy().
 *
 * <a name="Permissions"></a>
 * <h3>Permissions</h3>
 *
 * <p>Global access to a service can be enforced when it is declared in its
 * manifest's {@link android.R.styleable#AndroidManifestService &lt;service&gt;}
 * tag.  By doing so, other applications will need to declare a corresponding
 * {@link android.R.styleable#AndroidManifestUsesPermission &lt;uses-permission&gt;}
 * element in their own manifest to be able to start, stop, or bind to
 * the service.
 *
 * <p>As of {@link android.os.Build.VERSION_CODES#GINGERBREAD}, when using
 * {@link Context#startService(Intent) Context.startService(Intent)}, you can
 * also set {@link Intent#FLAG_GRANT_READ_URI_PERMISSION
 * Intent.FLAG_GRANT_READ_URI_PERMISSION} and/or {@link Intent#FLAG_GRANT_WRITE_URI_PERMISSION
 * Intent.FLAG_GRANT_WRITE_URI_PERMISSION} on the Intent.  This will grant the
 * Service temporary access to the specific URIs in the Intent.  Access will
 * remain until the Service has called {@link #stopSelf(int)} for that start
 * command or a later one, or until the Service has been completely stopped.
 * This works for granting access to the other apps that have not requested
 * the permission protecting the Service, or even when the Service is not
 * exported at all.
 *
 * <p>In addition, a service can protect individual IPC calls into it with
 * permissions, by calling the
 * {@link #checkCallingPermission}
 * method before executing the implementation of that call.
 *
 * <p>See the <a href="{@docRoot}guide/topics/security/security.html">Security and Permissions</a>
 * document for more information on permissions and security in general.
 *
 * <a name="ProcessLifecycle"></a>
 * <h3>Process Lifecycle</h3>
 *
 * <p>The Android system will attempt to keep the process hosting a service
 * around as long as the service has been started or has clients bound to it.
 * When running low on memory and needing to kill existing processes, the
 * priority of a process hosting the service will be the higher of the
 * following possibilities:
 *
 * <ul>
 * <li><p>If the service is currently executing code in its
 * {@link #onCreate onCreate()}, {@link #onStartCommand onStartCommand()},
 * or {@link #onDestroy onDestroy()} methods, then the hosting process will
 * be a foreground process to ensure this code can execute without
 * being killed.
 * <li><p>If the service has been started, then its hosting process is considered
 * to be less important than any processes that are currently visible to the
 * user on-screen, but more important than any process not visible.  Because
 * only a few processes are generally visible to the user, this means that
 * the service should not be killed except in low memory conditions.  However, since
 * the user is not directly aware of a background service, in that state it <em>is</em>
 * considered a valid candidate to kill, and you should be prepared for this to
 * happen.  In particular, long-running services will be increasingly likely to
 * kill and are guaranteed to be killed (and restarted if appropriate) if they
 * remain started long enough.
 * <li><p>If there are clients bound to the service, then the service's hosting
 * process is never less important than the most important client.  That is,
 * if one of its clients is visible to the user, then the service itself is
 * considered to be visible.  The way a client's importance impacts the service's
 * importance can be adjusted through {@link Context#BIND_ABOVE_CLIENT},
 * {@link Context#BIND_ALLOW_OOM_MANAGEMENT}, {@link Context#BIND_WAIVE_PRIORITY},
 * {@link Context#BIND_IMPORTANT}, and {@link Context#BIND_ADJUST_WITH_ACTIVITY}.
 * <li><p>A started service can use the {@link #startForeground(int, Notification)}
 * API to put the service in a foreground state, where the system considers
 * it to be something the user is actively aware of and thus not a candidate
 * for killing when low on memory.  (It is still theoretically possible for
 * the service to be killed under extreme memory pressure from the current
 * foreground application, but in practice this should not be a concern.)
 * </ul>
 *
 * <p>Note this means that most of the time your service is running, it may
 * be killed by the system if it is under heavy memory pressure.  If this
 * happens, the system will later try to restart the service.  An important
 * consequence of this is that if you implement {@link #onStartCommand onStartCommand()}
 * to schedule work to be done asynchronously or in another thread, then you
 * may want to use {@link #START_FLAG_REDELIVERY} to have the system
 * re-deliver an Intent for you so that it does not get lost if your service
 * is killed while processing it.
 *
 * <p>Other application components running in the same process as the service
 * (such as an {@link android.app.Activity}) can, of course, increase the
 * importance of the overall
 * process beyond just the importance of the service itself.
 *
 * <a name="LocalServiceSample"></a>
 * <h3>Local Service Sample</h3>
 *
 * <p>One of the most common uses of a Service is as a secondary component
 * running alongside other parts of an application, in the same process as
 * the rest of the components.  All components of an .apk run in the same
 * process unless explicitly stated otherwise, so this is a typical situation.
 *
 * <p>When used in this way, by assuming the
 * components are in the same process, you can greatly simplify the interaction
 * between them: clients of the service can simply cast the IBinder they
 * receive from it to a concrete class published by the service.
 *
 * <p>An example of this use of a Service is shown here.  First is the Service
 * itself, publishing a custom class when bound:
 *
 * {@sample development/samples/ApiDemos/src/com/example/android/apis/app/LocalService.java
 *      service}
 *
 * <p>With that done, one can now write client code that directly accesses the
 * running service, such as:
 *
 * {@sample development/samples/ApiDemos/src/com/example/android/apis/app/LocalServiceActivities.java
 *      bind}
 *
 * <a name="RemoteMessengerServiceSample"></a>
 * <h3>Remote Messenger Service Sample</h3>
 *
 * <p>If you need to be able to write a Service that can perform complicated
 * communication with clients in remote processes (beyond simply the use of
 * {@link Context#startService(Intent) Context.startService} to send
 * commands to it), then you can use the {@link android.os.Messenger} class
 * instead of writing full AIDL files.
 *
 * <p>An example of a Service that uses Messenger as its client interface
 * is shown here.  First is the Service itself, publishing a Messenger to
 * an internal Handler when bound:
 *
 * {@sample development/samples/ApiDemos/src/com/example/android/apis/app/MessengerService.java
 *      service}
 *
 * <p>If we want to make this service run in a remote process (instead of the
 * standard one for its .apk), we can use <code>android:process</code> in its
 * manifest tag to specify one:
 *
 * {@sample development/samples/ApiDemos/AndroidManifest.xml remote_service_declaration}
 *
 * <p>Note that the name "remote" chosen here is arbitrary, and you can use
 * other names if you want additional processes.  The ':' prefix appends the
 * name to your package's standard process name.
 *
 * <p>With that done, clients can now bind to the service and send messages
 * to it.  Note that this allows clients to register with it to receive
 * messages back as well:
 *
 * {@sample development/samples/ApiDemos/src/com/example/android/apis/app/MessengerServiceActivities.java
 *      bind}
 */
public final class Service implements ComponentCallbacks2 {
    /**
     * Bits returned by {@link #onStartCommand} describing how to continue
     * the service if it is killed.  May be {@link #START_STICKY},
     * {@link #START_NOT_STICKY}, {@link #START_REDELIVER_INTENT},
     * or {@link #START_STICKY_COMPATIBILITY}.
     */
    public static final int START_CONTINUATION_MASK = 15;

    /**
     * This flag is set in {@link #onStartCommand} if the Intent is a
     * re-delivery of a previously delivered intent, because the service
     * had previously returned {@link #START_REDELIVER_INTENT} but had been
     * killed before calling {@link #stopSelf(int)} for that Intent.
     */
    public static final int START_FLAG_REDELIVERY = 1;

    /**
     * This flag is set in {@link #onStartCommand} if the Intent is a
     * retry because the original attempt never got to or returned from
     * {@link #onStartCommand(Intent, int, int)}.
     */
    public static final int START_FLAG_RETRY = 2;

    /**
     * Constant to return from {@link #onStartCommand}: if this service's
     * process is killed while it is started (after returning from
     * {@link #onStartCommand}), and there are no new start intents to
     * deliver to it, then take the service out of the started state and
     * don't recreate until a future explicit call to
     * {@link Context#startService Context.startService(Intent)}.  The
     * service will not receive a {@link #onStartCommand(Intent, int, int)}
     * call with a null Intent because it will not be restarted if there
     * are no pending Intents to deliver.
     *
     * <p>This mode makes sense for things that want to do some work as a
     * result of being started, but can be stopped when under memory pressure
     * and will explicit start themselves again later to do more work.  An
     * example of such a service would be one that polls for data from
     * a server: it could schedule an alarm to poll every N minutes by having
     * the alarm start its service.  When its {@link #onStartCommand} is
     * called from the alarm, it schedules a new alarm for N minutes later,
     * and spawns a thread to do its networking.  If its process is killed
     * while doing that check, the service will not be restarted until the
     * alarm goes off.
     */
    public static final int START_NOT_STICKY = 2;

    /**
     * Constant to return from {@link #onStartCommand}: if this service's
     * process is killed while it is started (after returning from
     * {@link #onStartCommand}), then it will be scheduled for a restart
     * and the last delivered Intent re-delivered to it again via
     * {@link #onStartCommand}.  This Intent will remain scheduled for
     * redelivery until the service calls {@link #stopSelf(int)} with the
     * start ID provided to {@link #onStartCommand}.  The
     * service will not receive a {@link #onStartCommand(Intent, int, int)}
     * call with a null Intent because it will only be restarted if
     * it is not finished processing all Intents sent to it (and any such
     * pending events will be delivered at the point of restart).
     */
    public static final int START_REDELIVER_INTENT = 3;

    /**
     * Constant to return from {@link #onStartCommand}: if this service's
     * process is killed while it is started (after returning from
     * {@link #onStartCommand}), then leave it in the started state but
     * don't retain this delivered intent.  Later the system will try to
     * re-create the service.  Because it is in the started state, it will
     * guarantee to call {@link #onStartCommand} after creating the new
     * service instance; if there are not any pending start commands to be
     * delivered to the service, it will be called with a null intent
     * object, so you must take care to check for this.
     *
     * <p>This mode makes sense for things that will be explicitly started
     * and stopped to run for arbitrary periods of time, such as a service
     * performing background music playback.
     *
     * <p>Since Android version {@link Build.VERSION_CODES#S}, apps
     * targeting {@link Build.VERSION_CODES#S} or above are disallowed
     * to start a foreground service from the background, but the restriction
     * doesn't impact <em>restarts</em> of a sticky foreground service. However,
     * when apps start a sticky foreground service from the background,
     * the same restriction still applies.
     */
    public static final int START_STICKY = 1;

    /**
     * Constant to return from {@link #onStartCommand}: compatibility
     * version of {@link #START_STICKY} that does not guarantee that
     * {@link #onStartCommand} will be called again after being killed.
     */
    public static final int START_STICKY_COMPATIBILITY = 0;

    /**
     * Selector for {@link #stopForeground(int)}: if set, the notification previously supplied
     * to {@link #startForeground} will be detached from the service's lifecycle.  The notification
     * will remain shown even after the service is stopped and destroyed.
     */
    public static final int STOP_FOREGROUND_DETACH = 2;

    /**
     * Selector for {@link #stopForeground(int)}:  equivalent to passing {@code false}
     * to the legacy API {@link #stopForeground(boolean)}.
     *
     * @deprecated Use {@link #STOP_FOREGROUND_DETACH} instead.  The legacy
     * behavior was inconsistent, leading to bugs around unpredictable results.
     */
    @Deprecated
    public static final int STOP_FOREGROUND_LEGACY = 0;

    /**
     * Selector for {@link #stopForeground(int)}: if supplied, the notification previously
     * supplied to {@link #startForeground} will be cancelled and removed from display.
     */
    public static final int STOP_FOREGROUND_REMOVE = 1;

    private Service() {
    }
}
