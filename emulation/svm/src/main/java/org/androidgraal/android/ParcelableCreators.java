package org.androidgraal.android;

import android.os.Parcelable;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeReflection;

public final class ParcelableCreators implements Feature {

    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        access.registerSubtypeReachabilityHandler((_, type) -> {
            RuntimeReflection.register(type);
            try {
                RuntimeReflection.register(type.getDeclaredField("CREATOR"));
            } catch (NoSuchFieldException _) {
            }
        }, Parcelable.class);
    }
}
