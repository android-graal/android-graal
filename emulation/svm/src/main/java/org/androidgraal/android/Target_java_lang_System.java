package org.androidgraal.android;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;
import com.oracle.svm.core.annotate.TargetElement;

/**
 * Once the emulation is attached, {@code System.loadLibrary} asks the app's class loader on ART first, then the image's
 * own search.
 */
@TargetClass(System.class)
final class Target_java_lang_System {

    private Target_java_lang_System() {
    }

    @Substitute
    private static void loadLibrary(String libname) {
        String path = EmulationRoot.isAttached() ? EmulationRoot.host().findLibrary(libname) : null;
        if (path != null) {
            System.load(path);
        } else {
            loadLibraryOriginal(libname);
        }
    }

    @Alias
    @TargetElement(name = "loadLibrary")
    private static native void loadLibraryOriginal(String libname);
}
