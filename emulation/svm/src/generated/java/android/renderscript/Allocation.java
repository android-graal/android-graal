package android.renderscript;

/**
 * <p> This class provides the primary method through which data is passed to
 * and from RenderScript kernels.  An Allocation provides the backing store for
 * a given {@link android.renderscript.Type}.  </p>
 *
 * <p>An Allocation also contains a set of usage flags that denote how the
 * Allocation could be used. For example, an Allocation may have usage flags
 * specifying that it can be used from a script as well as input to a {@link
 * android.renderscript.Sampler}. A developer must synchronize across these
 * different usages using {@link android.renderscript.Allocation#syncAll} in
 * order to ensure that different users of the Allocation have a consistent view
 * of memory. For example, in the case where an Allocation is used as the output
 * of one kernel and as Sampler input in a later kernel, a developer must call
 * {@link #syncAll syncAll(Allocation.USAGE_SCRIPT)} prior to launching the
 * second kernel to ensure correctness.
 *
 * <p>An Allocation can be populated with the {@link #copyFrom} routines. For
 * more complex Element types, the {@link #copyFromUnchecked} methods can be
 * used to copy from byte arrays or similar constructs.</p>
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
public final class Allocation {
    /**
     * The Allocation will be used as the source of shader constants by one or
     * more programs.
     *
     * This was deprecated in API level 16.
     */
    @Deprecated
    public static final int USAGE_GRAPHICS_CONSTANTS = 8;

    /**
     * The Allocation will be used as a target for offscreen rendering
     *
     * This was deprecated in API level 16.
     */
    @Deprecated
    public static final int USAGE_GRAPHICS_RENDER_TARGET = 16;

    /**
     * The Allocation will be used as a texture source by one or more graphics
     * programs.
     */
    @Deprecated
    public static final int USAGE_GRAPHICS_TEXTURE = 2;

    /**
     * The Allocation will be used as a graphics mesh.
     *
     * This was deprecated in API level 16.
     */
    @Deprecated
    public static final int USAGE_GRAPHICS_VERTEX = 4;

    /**
     * The Allocation will be used as a {@link android.view.Surface}
     * consumer.  This usage will cause the Allocation to be created
     * as read-only.
     */
    @Deprecated
    public static final int USAGE_IO_INPUT = 32;

    /**
     * The Allocation will be used as a {@link android.view.Surface}
     * producer.  The dimensions and format of the {@link
     * android.view.Surface} will be forced to those of the
     * Allocation.
     */
    @Deprecated
    public static final int USAGE_IO_OUTPUT = 64;

    /**
     * The Allocation will be bound to and accessed by scripts.
     */
    @Deprecated
    public static final int USAGE_SCRIPT = 1;

    /**
     * The Allocation's backing store will be inherited from another object
     * (usually a {@link android.graphics.Bitmap}); copying to or from the
     * original source Bitmap will cause a synchronization rather than a full
     * copy.  {@link #syncAll} may also be used to synchronize the Allocation
     * and the source Bitmap.
     *
     * <p>This is set by default for allocations created with {@link
     * #createFromBitmap} in API version 18 and higher.</p>
     */
    @Deprecated
    public static final int USAGE_SHARED = 128;

    private Allocation() {
    }
}
