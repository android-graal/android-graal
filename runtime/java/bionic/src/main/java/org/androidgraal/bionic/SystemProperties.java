package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.word.PointerBase;

@CContext(BionicDirectives.class)
public final class SystemProperties {

    private SystemProperties() {
    }

    @CConstant
    public static native int PROP_VALUE_MAX();

    @CFunction
    public static native PointerBase __system_property_find(CCharPointer name);

    @CFunction
    public static native int __system_property_read(PointerBase pi, CCharPointer name, CCharPointer value);

    // __system_property_read_callback, which bionic exports from API 26 on.
    public interface SystemPropertyReadCallback extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(PointerBase pi, CFunctionPointer callback, PointerBase cookie);
    }
}
