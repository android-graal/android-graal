package org.graalvm.android.hello.svm;

import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.function.CEntryPoint.Builtin;
import org.graalvm.nativeimage.c.function.CEntryPoint.IsolateThreadContext;
import org.graalvm.word.PointerBase;

final class HelloWorld {

    private HelloWorld() {
    }

    @CEntryPoint(name = "Java_org_graalvm_android_hello_MainActivity_createIsolate", builtin = Builtin.CREATE_ISOLATE)
    static native long createIsolate(PointerBase env, PointerBase cls);

    @CEntryPoint(name = "Java_org_graalvm_android_hello_MainActivity_hello")
    static void hello(PointerBase env, PointerBase cls, @IsolateThreadContext long thread) {
        System.out.println("Hello from android-graal native-image!");
    }
}
