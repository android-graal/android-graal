package org.androidgraal.android;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.TargetClass;

@TargetClass(className = "jdk.internal.ref.PhantomCleanable")
final class Target_jdk_internal_ref_PhantomCleanable {

    @Alias
    public Target_jdk_internal_ref_CleanerImpl_CleanableList list;
}
