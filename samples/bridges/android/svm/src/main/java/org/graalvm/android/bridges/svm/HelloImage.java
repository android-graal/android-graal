package org.graalvm.android.bridges.svm;

import org.androidgraal.svm.Art;
import org.graalvm.android.bridges.Display;
import org.graalvm.android.bridges.Hello;
import org.graalvm.android.bridges.HelloException;

public final class HelloImage implements Hello {

    @Override
    public void run(Display display) {
        System.out.println("Hello from the image");
        display.show("hello from the image");
        display.greet(new GreetingImage());
        Thread thread = new Thread(() -> display.show("hello from a thread the image started"), "image-thread");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Art.handles().release(display);
    }

    @Override
    public String echo(String text) {
        return text;
    }

    @Override
    public void fail(String message) {
        throw new HelloException(message);
    }
}
