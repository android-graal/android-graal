package org.androidgraal.android;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.TargetClass;
import java.lang.ref.PhantomReference;

@TargetClass(PhantomReference.class)
final class Target_java_lang_ref_PhantomReference {

    @Alias
    public native void clearImpl();
}
