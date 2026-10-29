package org.androidgraal.android;

import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeSystemProperties;

public final class RuntimeIdentity implements Feature {

    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        RuntimeSystemProperties.register("java.runtime.name", "Android Runtime");
        RuntimeSystemProperties.register("java.vendor", "The Android Project");
        RuntimeSystemProperties.register("java.vm.vendor", "The Android Project");
    }
}
