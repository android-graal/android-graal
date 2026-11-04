package org.androidgraal.android;

import android.os.Looper;
import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.TargetClass;

@TargetClass(Looper.class)
public final class Target_android_os_Looper {

    @Alias
    public static native boolean loopOnce(Looper me, long ident, int thresholdOverride);
}
