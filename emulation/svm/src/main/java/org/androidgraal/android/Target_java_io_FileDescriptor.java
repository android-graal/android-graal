package org.androidgraal.android;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.TargetClass;
import java.io.FileDescriptor;
import java.io.IOException;

@TargetClass(FileDescriptor.class)
final class Target_java_io_FileDescriptor {

    @Alias
    public int fd;

    @Alias
    public Target_java_io_FileDescriptor(int fd) {
    }

    @Alias
    public native void close() throws IOException;

    @Alias
    public native void unregisterCleanup();
}
