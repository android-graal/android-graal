package android.media.quality;

import android.os.Parcelable;

/**
 * Capability info of media quality parameters
 */
public final class ParameterCapability implements Parcelable {
    /**
     * The key for the default value of this parameter.
     */
    public static final String CAPABILITY_DEFAULT = "default";

    /**
     * The key for the enumeration of this parameter.
     */
    public static final String CAPABILITY_ENUM = "enum";

    /**
     * The key for the max possible value of this parameter.
     */
    public static final String CAPABILITY_MAX = "max";

    /**
     * The key for the min possible value of this parameter.
     */
    public static final String CAPABILITY_MIN = "min";

    /**
     * Double parameter type
     */
    public static final int TYPE_DOUBLE = 3;

    /**
     * Integer parameter type
     */
    public static final int TYPE_INT = 1;

    /**
     * Long integer parameter type
     */
    public static final int TYPE_LONG = 2;

    /**
     * None parameter type. It's used when a parameter is not supported.
     */
    public static final int TYPE_NONE = 0;

    /**
     * String parameter type
     */
    public static final int TYPE_STRING = 4;

    private ParameterCapability() {
    }
}
