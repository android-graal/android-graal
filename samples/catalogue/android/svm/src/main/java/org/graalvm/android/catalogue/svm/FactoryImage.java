package org.graalvm.android.catalogue.svm;

import org.graalvm.android.catalogue.Factory;
import org.graalvm.android.catalogue.Patterns;

public final class FactoryImage implements Factory {

    @Override
    public Patterns newPatterns() {
        return new PatternsImage();
    }
}
