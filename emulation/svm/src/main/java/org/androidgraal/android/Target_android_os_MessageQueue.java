package org.androidgraal.android;

import android.os.MessageQueue;
import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.TargetClass;

@TargetClass(MessageQueue.class)
final class Target_android_os_MessageQueue {

    @Alias
    public native int dispatchEvents(int fd, int events);
}
