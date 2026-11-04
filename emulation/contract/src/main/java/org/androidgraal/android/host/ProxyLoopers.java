package org.androidgraal.android.host;

import org.androidgraal.hosted.annotate.Root;
import org.androidgraal.hosted.annotate.Svm;

@Root
@Svm
public interface ProxyLoopers {

    /**
     * Prepares a proxy Looper on the calling thread for the ART Looper {@code looper}.
     */
    void prepare(long looper, boolean main);

    /**
     * Runs the head Message of the calling thread's proxy Looper when it is due at {@code uptimeMillis}, the uptime
     * {@link ApplicationHost#post} was given. A head due before {@code uptimeMillis} first has {@code uptimeMillis}
     * posted again through {@link ApplicationHost#post}.
     */
    void dispatch(long uptimeMillis);

    /** Runs the idle handlers of the calling thread's proxy Looper. */
    void idle();
}
