package android.graphics;

/**
 * Class representing a mesh object.
 *
 * This class represents a Mesh object that can optionally be indexed.
 * A {@link MeshSpecification} is required along with various attributes for
 * detailing the mesh object, including a mode, vertex buffer, optional index buffer, and bounds
 * for the mesh. Once generated, a mesh object can be drawn through
 * {@link Canvas#drawMesh(Mesh, BlendMode, Paint)}
 */
public final class Mesh {
    /**
     * The mesh will be drawn with triangles without utilizing shared vertices.
     */
    public static final int TRIANGLES = 0;

    /**
     * The mesh will be drawn with triangles utilizing shared vertices.
     */
    public static final int TRIANGLE_STRIP = 1;

    private Mesh() {
    }
}
