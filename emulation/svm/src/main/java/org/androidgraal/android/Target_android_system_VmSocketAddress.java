package org.androidgraal.android;

import android.system.VmSocketAddress;
import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.TargetClass;

@TargetClass(VmSocketAddress.class)
final class Target_android_system_VmSocketAddress {

    @Alias
    public int svmPort;

    @Alias
    public int svmCid;
}
