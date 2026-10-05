package org.androidgraal.gles;

import static android.opengl.GLES20.GL_ALIASED_LINE_WIDTH_RANGE;
import static android.opengl.GLES20.GL_ALIASED_POINT_SIZE_RANGE;
import static android.opengl.GLES20.GL_BLEND_COLOR;
import static android.opengl.GLES20.GL_COLOR_CLEAR_VALUE;
import static android.opengl.GLES20.GL_COLOR_WRITEMASK;
import static android.opengl.GLES20.GL_COMPRESSED_TEXTURE_FORMATS;
import static android.opengl.GLES20.GL_CURRENT_VERTEX_ATTRIB;
import static android.opengl.GLES20.GL_DEPTH_RANGE;
import static android.opengl.GLES20.GL_MAX_VIEWPORT_DIMS;
import static android.opengl.GLES20.GL_NUM_COMPRESSED_TEXTURE_FORMATS;
import static android.opengl.GLES20.GL_NUM_SHADER_BINARY_FORMATS;
import static android.opengl.GLES20.GL_SCISSOR_BOX;
import static android.opengl.GLES20.GL_SHADER_BINARY_FORMATS;
import static android.opengl.GLES20.GL_UNSIGNED_INT;
import static android.opengl.GLES20.GL_UNSIGNED_SHORT;
import static android.opengl.GLES20.GL_VIEWPORT;
import static android.opengl.GLES30.GL_COLOR;
import static android.opengl.GLES30.GL_NUM_PROGRAM_BINARY_FORMATS;
import static android.opengl.GLES30.GL_PROGRAM_BINARY_FORMATS;
import static android.opengl.GLES30.GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS;
import static android.opengl.GLES30.GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES;

public final class Counts {

    private static final int GL_TEXTURE_BORDER_COLOR = 0x1004;
    private static final int GL_VIEWPORT_BOUNDS_RANGE_OES = 0x825D;
    private static final int GL_COMPUTE_WORK_GROUP_SIZE = 0x8267;
    private static final int GL_WINDOW_RECTANGLE_EXT = 0x8F12;
    private static final int GL_PRIMITIVE_BOUNDING_BOX = 0x92BE;
    private static final int GL_MULTISAMPLE_LINE_WIDTH_RANGE = 0x9381;

    private Counts() {
    }

    public static int get(int pname) {
        return switch (pname) {
            case GL_ALIASED_LINE_WIDTH_RANGE, GL_ALIASED_POINT_SIZE_RANGE, GL_DEPTH_RANGE, GL_MAX_VIEWPORT_DIMS,
                    GL_MULTISAMPLE_LINE_WIDTH_RANGE, GL_VIEWPORT_BOUNDS_RANGE_OES -> 2;
            case GL_BLEND_COLOR, GL_COLOR_CLEAR_VALUE, GL_COLOR_WRITEMASK, GL_SCISSOR_BOX, GL_VIEWPORT -> 4;
            case GL_PRIMITIVE_BOUNDING_BOX -> 8;
            case GL_COMPRESSED_TEXTURE_FORMATS -> Queries.integer(GL_NUM_COMPRESSED_TEXTURE_FORMATS);
            case GL_SHADER_BINARY_FORMATS -> Queries.integer(GL_NUM_SHADER_BINARY_FORMATS);
            case GL_PROGRAM_BINARY_FORMATS -> Queries.integer(GL_NUM_PROGRAM_BINARY_FORMATS);
            default -> 1;
        };
    }

    public static int indexed(int target) {
        return switch (target) {
            case GL_COLOR_WRITEMASK, GL_SCISSOR_BOX, GL_WINDOW_RECTANGLE_EXT -> 4;
            default -> 1;
        };
    }

    public static int texParameter(int pname) {
        return pname == GL_TEXTURE_BORDER_COLOR ? 4 : 1;
    }

    public static int program(int pname) {
        return pname == GL_COMPUTE_WORK_GROUP_SIZE ? 3 : 1;
    }

    public static int vertexAttrib(int pname) {
        return pname == GL_CURRENT_VERTEX_ATTRIB ? 4 : 1;
    }

    public static int clearBuffer(int buffer) {
        return buffer == GL_COLOR ? 4 : 1;
    }

    public static int uniformBlock(int program, int uniformBlockIndex, int pname) {
        if (pname != GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES) {
            return 1;
        }
        return Queries.uniformBlock(program, uniformBlockIndex, GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS);
    }

    public static long indices(int count, int type) {
        return switch (type) {
            case GL_UNSIGNED_SHORT -> count * 2L;
            case GL_UNSIGNED_INT -> count * 4L;
            default -> count;
        };
    }
}
