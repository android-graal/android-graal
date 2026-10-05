module org.graalvm.nativeimage {
    requires transitive org.graalvm.word;

    exports com.oracle.svm.core.annotate;
    exports org.graalvm.nativeimage;
    exports org.graalvm.nativeimage.c;
    exports org.graalvm.nativeimage.c.constant;
    exports org.graalvm.nativeimage.c.function;
    exports org.graalvm.nativeimage.c.struct;
    exports org.graalvm.nativeimage.c.type;
    exports org.graalvm.nativeimage.dynamicaccess;
    exports org.graalvm.nativeimage.hosted;

    uses org.graalvm.nativeimage.Platform;

    provides org.graalvm.nativeimage.Platform with
            org.graalvm.nativeimage.Platform.LINUX_AMD64,
            org.graalvm.nativeimage.Platform.LINUX_AARCH64,
            org.graalvm.nativeimage.Platform.LINUX_RISCV64,
            org.graalvm.nativeimage.Platform.LINUX_ARM32,
            org.graalvm.nativeimage.Platform.ANDROID_AARCH64,
            org.graalvm.nativeimage.Platform.IOS_AMD64,
            org.graalvm.nativeimage.Platform.IOS_AARCH64,
            org.graalvm.nativeimage.Platform.MACOS_AMD64,
            org.graalvm.nativeimage.Platform.MACOS_AARCH64,
            org.graalvm.nativeimage.Platform.WINDOWS_AMD64,
            org.graalvm.nativeimage.Platform.WINDOWS_AARCH64;
}
