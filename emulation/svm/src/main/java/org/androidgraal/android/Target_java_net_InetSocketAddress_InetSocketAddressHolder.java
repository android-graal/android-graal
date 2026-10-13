package org.androidgraal.android;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.TargetClass;
import java.net.InetAddress;

@TargetClass(className = "java.net.InetSocketAddress$InetSocketAddressHolder")
final class Target_java_net_InetSocketAddress_InetSocketAddressHolder {

    @Alias
    public InetAddress addr;

    @Alias
    public int port;
}
