package android.renderscript;

/**
 * This class provides access to a RenderScript context, which controls RenderScript
 * initialization, resource management, and teardown. An instance of the RenderScript
 * class must be created before any other RS objects can be created.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about creating an application that uses RenderScript, read the
 * <a href="{@docRoot}guide/topics/renderscript/index.html">RenderScript</a> developer guide.</p>
 * </div>
 *
 * @deprecated Renderscript has been deprecated in API level 31. Please refer to the <a
 * href="https://developer.android.com/guide/topics/renderscript/migration-guide">migration
 * guide</a> for the proposed alternatives.
 */
@Deprecated
public final class RenderScript {
    @Deprecated
    public static final int CREATE_FLAG_LOW_LATENCY = 2;

    @Deprecated
    public static final int CREATE_FLAG_LOW_POWER = 4;

    @Deprecated
    public static final int CREATE_FLAG_NONE = 0;

    private RenderScript() {
    }
}
