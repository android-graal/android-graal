package org.androidgraal.android;

import java.io.FileDescriptor;
import java.io.IOException;

public final class FileDescriptors {

    private FileDescriptors() {
    }

    public static FileDescriptor create(int fd) {
        return (FileDescriptor) (Object) new Target_java_io_FileDescriptor(fd);
    }

    public static int getFd(FileDescriptor descriptor) {
        return target(descriptor).fd;
    }

    public static void setFd(FileDescriptor descriptor, int fd) {
        target(descriptor).fd = fd;
    }

    public static int release(FileDescriptor descriptor) {
        Target_java_io_FileDescriptor target = target(descriptor);
        target.unregisterCleanup();
        int fd = target.fd;
        target.fd = -1;
        return fd;
    }

    public static void close(FileDescriptor descriptor) throws IOException {
        if (descriptor == null) {
            return;
        }
        target(descriptor).close();
    }

    public static void releaseWhenUnreachable(FileDescriptor descriptor, Runnable action) {
        target(descriptor).registerCleanup(
                (Target_jdk_internal_ref_PhantomCleanable) (Object) ImageCleaner.register(descriptor, action));
    }

    private static Target_java_io_FileDescriptor target(FileDescriptor descriptor) {
        return (Target_java_io_FileDescriptor) (Object) descriptor;
    }
}
