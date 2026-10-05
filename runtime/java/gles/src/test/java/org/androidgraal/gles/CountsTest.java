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
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CountsTest {

    @Test
    void theTablesSizeWhatGlWrites() {
        assertEquals(10, Counts.indices(10, GL_UNSIGNED_BYTE));
        assertEquals(20, Counts.indices(10, GL_UNSIGNED_SHORT));
        assertEquals(40, Counts.indices(10, GL_UNSIGNED_INT));
        assertEquals(4, Counts.get(GL_VIEWPORT));
        assertEquals(2, Counts.get(GL_DEPTH_RANGE));
        assertEquals(8, Counts.get(0x92BE));
        assertEquals(2, Counts.get(0x9381));
        assertEquals(2, Counts.get(0x825D));
        assertEquals(1, Counts.get(GL_MAX_TEXTURE_SIZE));
        assertEquals(4, Counts.vertexAttrib(GL_CURRENT_VERTEX_ATTRIB));
        assertEquals(1, Counts.vertexAttrib(GL_VERTEX_ATTRIB_ARRAY_ENABLED));
        assertEquals(4, Counts.clearBuffer(GL_COLOR));
        assertEquals(1, Counts.clearBuffer(GL_DEPTH));
        assertEquals(1, Counts.uniformBlock(1, 0, GL_UNIFORM_BLOCK_NAME_LENGTH));
        assertEquals(4, Counts.indexed(GL_COLOR_WRITEMASK));
        assertEquals(4, Counts.indexed(GL_SCISSOR_BOX));
        assertEquals(4, Counts.indexed(0x8F12));
        assertEquals(1, Counts.indexed(GL_TRANSFORM_FEEDBACK_BUFFER_BINDING));
        assertEquals(4, Counts.texParameter(0x1004));
        assertEquals(1, Counts.texParameter(GL_TEXTURE_MIN_FILTER));
        assertEquals(3, Counts.program(0x8267));
        assertEquals(1, Counts.program(GL_LINK_STATUS));
    }
}
