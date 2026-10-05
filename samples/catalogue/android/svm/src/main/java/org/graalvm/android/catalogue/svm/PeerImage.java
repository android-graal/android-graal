package org.graalvm.android.catalogue.svm;

import org.graalvm.android.catalogue.Peer;

public final class PeerImage implements Peer {

    @Override
    public String name() {
        return "image";
    }

    @Override
    public String describe() {
        return "image peer";
    }
}
