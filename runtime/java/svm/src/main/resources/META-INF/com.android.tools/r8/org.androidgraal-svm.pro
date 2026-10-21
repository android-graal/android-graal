# native-image reads runtime-visible annotations, a parameter's among them.
-keepattributes RuntimeVisibleAnnotations,RuntimeVisibleParameterAnnotations

# native-image takes a C element's directives and library from its enclosing classes when it declares none.
-keepattributes InnerClasses,EnclosingMethod
-keep,allowobfuscation @org.graalvm.nativeimage.c.CContext class *

# native-image links the library of every class or method that names one, reachable or not.
-keep,allowobfuscation @org.graalvm.nativeimage.c.function.CLibrary class *
-keepclasseswithmembers,allowobfuscation class * {
    @org.graalvm.nativeimage.c.function.CLibrary <methods>;
}

# Full mode keeps an annotation only on an item a rule matches.
-keep,allowshrinking,allowobfuscation,allowoptimization @org.graalvm.nativeimage.** class *
-keep,allowshrinking,allowobfuscation,allowoptimization @com.oracle.svm.** class *
-keepclassmembers,allowshrinking,allowobfuscation,allowoptimization class * {
    @org.graalvm.nativeimage.** *;
    @com.oracle.svm.** *;
}

# native-image compiles every entry point.
-keepclasseswithmembers,allowobfuscation class * {
    @org.graalvm.nativeimage.c.function.CEntryPoint <methods>;
}

# native-image instantiates a feature by the name --features gives.
-keep class * implements org.graalvm.nativeimage.hosted.Feature {
    <init>();
}

# native-image applies every substitution on the classpath and matches its members to the target's by name, while its own name never reaches the image.
-keep @com.oracle.svm.core.annotate.TargetClass class * {
    *;
}

# A word type is implemented by the image builder alone.
-keep,allowobfuscation interface * extends org.graalvm.word.WordBase {
    *;
}

# A C struct and the field each of its accessors reaches default to their Java names.
-keepnames @org.graalvm.nativeimage.c.struct.CStruct interface * {
    *;
}

# A raw structure pairs a field's accessors by the name each one's Java name derives.
-keepclassmembernames @org.graalvm.nativeimage.c.struct.RawStructure interface * {
    *;
}

# The builder instantiates a directives class and names its CAP cache file after it.
-keep class * implements org.graalvm.nativeimage.c.CContext$Directives {
    <init>();
}

# A C enum's lookup maps any C value back to one of its constants, and a constant's C name defaults to its Java name.
-keepclassmembers @org.graalvm.nativeimage.c.constant.CEnum enum * {
    <fields>;
    @org.graalvm.nativeimage.c.constant.CEnumValue <methods>;
    @org.graalvm.nativeimage.c.constant.CEnumLookup <methods>;
}

# A native method links by name: a @CFunction's symbol and a @CConstant's name default to the method's.
-keepclasseswithmembernames,includedescriptorclasses class * {
    native <methods>;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Compile-only annotations, absent from the image classpath.
-dontwarn org.androidgraal.hosted.annotate.**
-dontwarn com.oracle.svm.shared.**
