-keepclasseswithmembers,includedescriptorclasses class * { @org.androidgraal.art.EndPoint <methods>; }
-keepnames class * extends java.lang.Throwable
-keepclasseswithmembernames class org.androidgraal.art.Image { native <methods>; }
-keepclasseswithmembernames class org.androidgraal.art.Transport { native <methods>; }
-keep class * implements org.androidgraal.art.Contract { <init>(); }
-keep class * implements org.androidgraal.art.Startup { <init>(); }
# R8 removes writes to a field it sees no read of.
-keepclassmembers,allowobfuscation class org.androidgraal.art.Image$ThreadData { java.lang.Object handedBack; }
-keepclassmembers,allowobfuscation class org.androidgraal.art.Image { java.lang.Object fenceSink; }
-dontwarn org.androidgraal.hosted.annotate.**
