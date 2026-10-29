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
import static android.opengl.GLES31.GL_COMPUTE_WORK_GROUP_SIZE;
import static android.opengl.GLES32.GL_MULTISAMPLE_LINE_WIDTH_RANGE;
import static android.opengl.GLES32.GL_PRIMITIVE_BOUNDING_BOX;
import static android.opengl.GLES32.GL_TEXTURE_BORDER_COLOR;

import org.androidgraal.hosted.annotate.Adapted;
import org.androidgraal.hosted.annotate.Premise;
import org.androidgraal.hosted.annotate.Transcribed;

public final class Counts {

    private static final int GL_VIEWPORT_BOUNDS_RANGE_OES = 0x825D;
    private static final int GL_WINDOW_RECTANGLE_EXT = 0x8F12;

    private Counts() {
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_opengl_GLES20.cpp#L239-L319")
    @Adapted(reason = "counts GL_DEPTH_RANGE, GL_MULTISAMPLE_LINE_WIDTH_RANGE, GL_VIEWPORT_BOUNDS_RANGE_OES and GL_PRIMITIVE_BOUNDING_BOX by the tables", premise = Premise.GL_STATE_TABLES)
    public static int stateCount(int pname) {
        return switch (pname) {
            case GL_ALIASED_LINE_WIDTH_RANGE, GL_ALIASED_POINT_SIZE_RANGE, GL_DEPTH_RANGE, GL_MAX_VIEWPORT_DIMS,
                    GL_MULTISAMPLE_LINE_WIDTH_RANGE, GL_VIEWPORT_BOUNDS_RANGE_OES -> 2;
            case GL_BLEND_COLOR, GL_COLOR_CLEAR_VALUE, GL_COLOR_WRITEMASK, GL_SCISSOR_BOX, GL_VIEWPORT -> 4;
            case GL_PRIMITIVE_BOUNDING_BOX -> 8;
            case GL_COMPRESSED_TEXTURE_FORMATS -> Queries.queryInteger(GL_NUM_COMPRESSED_TEXTURE_FORMATS);
            case GL_SHADER_BINARY_FORMATS -> Queries.queryInteger(GL_NUM_SHADER_BINARY_FORMATS);
            case GL_PROGRAM_BINARY_FORMATS -> Queries.queryInteger(GL_NUM_PROGRAM_BINARY_FORMATS);
            default -> 1;
        };
    }

    public static int indexedStateCount(int target) {
        return switch (target) {
            case GL_COLOR_WRITEMASK, GL_SCISSOR_BOX, GL_WINDOW_RECTANGLE_EXT -> 4;
            default -> 1;
        };
    }

    public static int parameterCount(int pname) {
        return pname == GL_TEXTURE_BORDER_COLOR ? 4 : 1;
    }

    public static int programCount(int pname) {
        return pname == GL_COMPUTE_WORK_GROUP_SIZE ? 3 : 1;
    }

    @Transcribed("https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-16.0.0_r1/core/jni/android_opengl_GLES20.cpp#L3909-L3919")
    @Adapted(reason = "applies the switch to the integer forms too", premise = Premise.GL_STATE_TABLES)
    public static int vertexAttribCount(int pname) {
        return pname == GL_CURRENT_VERTEX_ATTRIB ? 4 : 1;
    }

    public static int clearBufferCount(int buffer) {
        return buffer == GL_COLOR ? 4 : 1;
    }

    public static int uniformBlockCount(int program, int uniformBlockIndex, int pname) {
        if (pname != GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES) {
            return 1;
        }
        return Queries.queryUniformBlock(program, uniformBlockIndex, GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS);
    }

    public static long indexBytes(int count, int type) {
        return switch (type) {
            case GL_UNSIGNED_SHORT -> count * 2L;
            case GL_UNSIGNED_INT -> count * 4L;
            default -> count;
        };
    }

    public static long baseVertexIndexBytes(int count, int type, int basevertex) {
        return Math.max(indexBytes(count, type), (long) count - basevertex);
    }
}
