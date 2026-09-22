import org.androidgraal.buildlogic.NativeHost
import org.androidgraal.buildlogic.Script

plugins {
    id("androidgraal.base")
    base
}

val nativeHost = the<NativeHost>()

tasks.withType<Script>().configureEach {
    forbid(nativeHost.root, nativeHost.sdk.root, nativeHost.ndk.root)
}
