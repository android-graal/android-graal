package android.renderscript;

/**
 *
 * ScriptIntrinsicBLAS class provides high performance RenderScript APIs to BLAS.
 *
 * The BLAS (Basic Linear Algebra Subprograms) are routines that provide standard
 * building blocks for performing basic vector and matrix operations.
 *
 * For detailed description of BLAS, please refer to http://www.netlib.org/blas/
 *
 * @deprecated Renderscript has been deprecated in API level 31. Please refer to the <a
 * href="https://developer.android.com/guide/topics/renderscript/migration-guide">migration
 * guide</a> for the proposed alternatives.
 */
@Deprecated
public final class ScriptIntrinsicBLAS {
    @Deprecated
    public static final int CONJ_TRANSPOSE = 113;

    @Deprecated
    public static final int LEFT = 141;

    @Deprecated
    public static final int LOWER = 122;

    @Deprecated
    public static final int NON_UNIT = 131;

    @Deprecated
    public static final int NO_TRANSPOSE = 111;

    @Deprecated
    public static final int RIGHT = 142;

    @Deprecated
    public static final int TRANSPOSE = 112;

    @Deprecated
    public static final int UNIT = 132;

    @Deprecated
    public static final int UPPER = 121;

    private ScriptIntrinsicBLAS() {
    }
}
