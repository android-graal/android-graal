package org.androidgraal.android;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.TargetClass;

@TargetClass(className = "jdk.internal.ref.CleanerImpl$CleanableList")
final class Target_jdk_internal_ref_CleanerImpl_CleanableList {

    @Alias
    public native boolean remove(Target_jdk_internal_ref_PhantomCleanable phc);
}
