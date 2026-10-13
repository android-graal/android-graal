package android.graphics;

/**
 * Class responsible for holding specifications for {@link Mesh} creations. This class generates a
 * {@link MeshSpecification} via the
 * {@link MeshSpecification#make(Attribute[], int, Varying[], String, String)} method,
 * where multiple parameters to set up the mesh are supplied, including attributes, vertex stride,
 * {@link Varying}, and vertex/fragment shaders. There are also additional methods to provide an
 * optional {@link ColorSpace} as well as an alpha type.
 *
 * For example a vertex shader that leverages a {@link Varying} may look like the following:
 *
 * <pre>
 *        Varyings main(const Attributes attributes) {
 *             Varyings varyings;
 *             varyings.position = attributes.position;
 *             return varyings;
 *        }
 * </pre>
 *
 * The corresponding fragment shader that may consume the varying look like the following:
 *
 * <pre>
 *      float2 main(const Varyings varyings, out float4 color) {
 *             color = vec4(1.0, 0.0, 0.0, 1.0);
 *             return varyings.position;
 *      }
 * </pre>
 *
 * The color returned from this fragment shader is blended with the other parameters that are
 * configured on the Paint object (ex. {@link Paint#setBlendMode(BlendMode)} used to draw the mesh.
 *
 * The position returned in the fragment shader can be consumed by any following fragment shaders in
 * the shader chain.
 *
 * See https://developer.android.com/develop/ui/views/graphics/agsl for more information
 * regarding Android Graphics Shader Language.
 *
 * Note that there are several limitations on various mesh specifications:
 * 1. The max amount of attributes allowed is 8.
 * 2. The offset alignment length is 4 bytes.
 * 2. The max stride length is 1024.
 * 3. The max amount of varyings is 6.
 *
 * These should be kept in mind when generating a mesh specification, as exceeding them will
 * lead to errors.
 */
public final class MeshSpecification {
    /**
     * Pixel is opaque.
     */
    public static final int ALPHA_TYPE_OPAQUE = 1;

    /**
     * Pixel components are premultiplied by alpha.
     */
    public static final int ALPHA_TYPE_PREMULTIPLIED = 2;

    /**
     * uninitialized.
     */
    public static final int ALPHA_TYPE_UNKNOWN = 0;

    /**
     * Pixel components are independent of alpha.
     */
    public static final int ALPHA_TYPE_UNPREMULTIPLIED = 3;

    /**
     * Represents one float. Its equivalent shader type is float.
     */
    public static final int TYPE_FLOAT = 0;

    /**
     * Represents two floats. Its equivalent shader type is float2.
     */
    public static final int TYPE_FLOAT2 = 1;

    /**
     * Represents three floats. Its equivalent shader type is float3.
     */
    public static final int TYPE_FLOAT3 = 2;

    /**
     * Represents four floats. Its equivalent shader type is float4.
     */
    public static final int TYPE_FLOAT4 = 3;

    /**
     * Represents four bytes. Its equivalent shader type is half4.
     */
    public static final int TYPE_UBYTE4 = 4;

    private MeshSpecification() {
    }
}
