package org.androidgraal.gles;

import static android.opengl.GLES20.GL_COLOR_WRITEMASK;
import static android.opengl.GLES20.GL_CURRENT_VERTEX_ATTRIB;
import static android.opengl.GLES20.GL_DEPTH_RANGE;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.GL_MAX_TEXTURE_SIZE;
import static android.opengl.GLES20.GL_SCISSOR_BOX;
import static android.opengl.GLES20.GL_TEXTURE_MIN_FILTER;
import static android.opengl.GLES20.GL_UNSIGNED_BYTE;
import static android.opengl.GLES20.GL_UNSIGNED_INT;
import static android.opengl.GLES20.GL_UNSIGNED_SHORT;
import static android.opengl.GLES20.GL_VERTEX_ATTRIB_ARRAY_ENABLED;
import static android.opengl.GLES20.GL_VIEWPORT;
import static android.opengl.GLES30.GL_COLOR;
import static android.opengl.GLES30.GL_DEPTH;
import static android.opengl.GLES30.GL_TRANSFORM_FEEDBACK_BUFFER_BINDING;
import static android.opengl.GLES30.GL_UNIFORM_BLOCK_NAME_LENGTH;
import static android.opengl.GLES31.GL_COMPUTE_WORK_GROUP_SIZE;
import static android.opengl.GLES32.GL_MULTISAMPLE_LINE_WIDTH_RANGE;
import static android.opengl.GLES32.GL_PRIMITIVE_BOUNDING_BOX;
import static android.opengl.GLES32.GL_TEXTURE_BORDER_COLOR;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CountsTest {

    @Test
    void theTablesSizeWhatGlWrites() {
        assertEquals(10, Counts.indexBytes(10, GL_UNSIGNED_BYTE));
        assertEquals(20, Counts.indexBytes(10, GL_UNSIGNED_SHORT));
        assertEquals(40, Counts.indexBytes(10, GL_UNSIGNED_INT));
        assertEquals(40, Counts.baseVertexIndexBytes(10, GL_UNSIGNED_INT, 5));
        assertEquals(20, Counts.baseVertexIndexBytes(10, GL_UNSIGNED_SHORT, -5));
        assertEquals(15, Counts.baseVertexIndexBytes(10, GL_UNSIGNED_BYTE, -5));
        assertEquals(10, Counts.baseVertexIndexBytes(10, GL_UNSIGNED_BYTE, 3));
        assertEquals(4, Counts.stateCount(GL_VIEWPORT));
        assertEquals(2, Counts.stateCount(GL_DEPTH_RANGE));
        assertEquals(8, Counts.stateCount(GL_PRIMITIVE_BOUNDING_BOX));
        assertEquals(2, Counts.stateCount(GL_MULTISAMPLE_LINE_WIDTH_RANGE));
        assertEquals(2, Counts.stateCount(0x825D));
        assertEquals(1, Counts.stateCount(GL_MAX_TEXTURE_SIZE));
        assertEquals(4, Counts.vertexAttribCount(GL_CURRENT_VERTEX_ATTRIB));
        assertEquals(1, Counts.vertexAttribCount(GL_VERTEX_ATTRIB_ARRAY_ENABLED));
        assertEquals(4, Counts.clearBufferCount(GL_COLOR));
        assertEquals(1, Counts.clearBufferCount(GL_DEPTH));
        assertEquals(1, Counts.uniformBlockCount(1, 0, GL_UNIFORM_BLOCK_NAME_LENGTH));
        assertEquals(4, Counts.indexedStateCount(GL_COLOR_WRITEMASK));
        assertEquals(4, Counts.indexedStateCount(GL_SCISSOR_BOX));
        assertEquals(4, Counts.indexedStateCount(0x8F12));
        assertEquals(1, Counts.indexedStateCount(GL_TRANSFORM_FEEDBACK_BUFFER_BINDING));
        assertEquals(4, Counts.parameterCount(GL_TEXTURE_BORDER_COLOR));
        assertEquals(1, Counts.parameterCount(GL_TEXTURE_MIN_FILTER));
        assertEquals(3, Counts.programCount(GL_COMPUTE_WORK_GROUP_SIZE));
        assertEquals(1, Counts.programCount(GL_LINK_STATUS));
    }
}
