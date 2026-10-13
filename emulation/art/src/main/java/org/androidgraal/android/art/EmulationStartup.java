package org.androidgraal.android.art;

import org.androidgraal.android.host.Emulation;
import org.androidgraal.art.Image;
import org.androidgraal.art.Startup;

public final class EmulationStartup implements Startup {

    @Override
    public void prepare() {
        Emulation root = Image.createRoot(Emulation.class);
        root.attach(new ArtHost());
        Image.handles().release(root);
    }
}
