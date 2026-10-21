-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# Parcel writes a Parcelable's class name into bytes ART reads.
-keepnames class android.** implements android.os.Parcelable

# native-image.properties initializes it at build time by this name.
-keepnames class android.os.SystemProperties$Thunk

# CEntryPointLiteral.create finds it by this name.
-keepclassmembernames class android.os.SystemProperties {
    void thunk(...);
}

# reachability-metadata.json names the type and the constructor ApplicationAssets calls.
-keep class android.content.res.AssetManager {
    <init>(boolean);
}

# reachability-metadata.json names the types whose annotated fields HealthKeys reads.
-keepnames class android.os.health.*HealthStats
-keepclassmembers,allowobfuscation class android.os.health.*HealthStats {
    @android.os.health.HealthKeys$Constant <fields>;
}

# native-image applies every substitution on the classpath, and the emulation's substitutions name these members.
-keep,allowobfuscation class android.system.VmSocketAddress {
    int svmPort;
    int svmCid;
}
-keepclassmembernames class android.system.VmSocketAddress {
    int svmPort;
    int svmCid;
}
-keep,allowobfuscation class android.system.StructTimeval {
    <init>(long, long);
}
