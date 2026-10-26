package org.androidgraal.bionic;

import java.util.List;
import org.graalvm.nativeimage.c.CContext;

public final class LooperDirectives implements CContext.Directives {

    @Override
    public List<String> getHeaderFiles() {
        return List.of("<android/looper.h>");
    }
}
