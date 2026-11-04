package org.androidgraal.android;

import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;
import org.androidgraal.hosted.annotate.Adapted;
import org.androidgraal.hosted.annotate.Premise;

@TargetClass(className = "jdk.internal.ref.CleanerImpl$PhantomCleanableRef")
final class Target_jdk_internal_ref_CleanerImpl_PhantomCleanableRef {

    @Substitute
    @Adapted(reason = "unregisters the cleanable and clears its reference instead of throwing", premise = Premise.NO_FINALIZER)
    public void clear() {
        Target_jdk_internal_ref_PhantomCleanable cleanable = (Target_jdk_internal_ref_PhantomCleanable) (Object) this;
        if (cleanable.list.remove(cleanable)) {
            ((Target_java_lang_ref_PhantomReference) (Object) this).clearImpl();
        }
    }
}
