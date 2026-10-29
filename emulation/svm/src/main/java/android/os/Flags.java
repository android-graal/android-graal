package android.os;
// TODO(b/303773055): Remove the annotation after access issue is resolved.
import android.compat.annotation.UnsupportedAppUsage;
import org.androidgraal.hosted.annotate.Emulated;

/** @hide */
@Emulated
public final class Flags {
    /** @hide */
    public static final String FLAG_ADPF_GPU_REPORT_ACTUAL_WORK_DURATION = "android.os.adpf_gpu_report_actual_work_duration";
    /** @hide */
    public static final String FLAG_ALLOW_PRIVATE_PROFILE = "android.os.allow_private_profile";
    /** @hide */
    public static final String FLAG_API_FOR_BACKPORTED_FIXES = "android.os.api_for_backported_fixes";
    /** @hide */
    public static final String FLAG_BINDER_FROZEN_STATE_CHANGE_CALLBACK = "android.os.binder_frozen_state_change_callback";
    /** @hide */
    public static final String FLAG_ENABLE_HAS_BINDERS = "android.os.enable_has_binders";
    /** @hide */
    public static final String FLAG_MAINLINE_VCN_PLATFORM_API = "android.os.mainline_vcn_platform_api";
    /** @hide */
    public static final String FLAG_PARCEL_MARSHALL_BYTEBUFFER = "android.os.parcel_marshall_bytebuffer";

    @UnsupportedAppUsage
    public static boolean forceConcurrentMessageQueue() {
        return false;
    }
    @UnsupportedAppUsage
    public static boolean messageQueueForceLegacy() {
        return false;
    }
    @UnsupportedAppUsage
    public static boolean messageQueueTailTracking() {
        return true;
    }
    @UnsupportedAppUsage
    public static boolean perfettoSdkTracingV2() {
        return false;
    }
}
