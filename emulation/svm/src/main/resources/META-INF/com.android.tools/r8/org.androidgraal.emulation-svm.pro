-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# Parcel writes a Parcelable's class name into bytes ART reads.
-keepnames class android.** implements android.os.Parcelable

# native-image.properties initializes it at build time by this name.
-keepnames class android.os.SystemProperties$Thunk
-keepnames class org.androidgraal.android.NativeMessageQueue$LooperCallback

# CEntryPointLiteral.create finds it by this name.
-keepclassmembernames class android.os.SystemProperties {
    void thunk(...);
}
-keepclassmembernames class org.androidgraal.android.NativeMessageQueue {
    int looperCallback(...);
}

# reachability-metadata.json names the type and the constructor ApplicationAssets calls.
-keep class android.content.res.AssetManager {
    <init>(boolean);
}

# reachability-metadata.json names the types and the constructors XmlPullParserFactory instantiates by name.
-keep class com.android.org.kxml2.io.KXmlParser {
    <init>();
}
-keep class com.android.org.kxml2.io.KXmlSerializer {
    <init>();
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
-keep,allowobfuscation class android.os.MessageQueue {
    int dispatchEvents(int, int);
}
-keepclassmembers class android.os.MessageQueue {
    int dispatchEvents(int, int);
}

# MessageQueue's static initializers find these fields with MethodHandles.Lookup.findVarHandle by name.
-keepclassmembers class android.os.MessageQueue {
    android.os.MessageQueue$StackNode mStateValue;
    long mNextInsertSeqValue;
    long mNextFrontInsertSeqValue;
    boolean mQuittingValue;
}
-keepclassmembers class android.os.MessageQueue$MessageNode {
    boolean mRemovedFromStackValue;
}
-keepclassmembers class android.os.MessageQueue$MessageCounts {
    long mCountsValue;
}
