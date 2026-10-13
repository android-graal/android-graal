package org.androidgraal.android;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.TargetClass;
import java.net.InetSocketAddress;

@TargetClass(InetSocketAddress.class)
final class Target_java_net_InetSocketAddress {

    @Alias
    public Target_java_net_InetSocketAddress_InetSocketAddressHolder holder;
}
