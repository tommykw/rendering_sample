package tokyo.tommy_kw.renderingsample.drawer;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import tokyo.tommy_kw.renderingsample.GlRenderer;
import tokyo.tommy_kw.renderingsample.geometry.Point;
import tokyo.tommy_kw.renderingsample.geometry.Polygon;

/**
 * Created by tommy on 15/11/02.
 */
public class PolygonRenderer {
    private final String vertexShaderCode =
            "uniform   mat4 uMVPMatrix;"              +
            "attribute vec4 aPosition;"               +
            "void main() {"                           +
            "  gl_Position = uMVPMatrix * aPosition;" +
            "}";

    private final String fragmentShaderCode =
            "precision mediump float;"  +
            "uniform   vec4    uColor;" +
            "void main() {"             +
            "  gl_FragColor = uColor;"  +
            "}";

    private final String vertexShaderCodeColor =
            "uniform   mat4  uMVPMatrix;"         +
            "attribute vec4  aPosition;"              +
            "attribute vec4  aColor;"                 +
            "varying   vec4  vColor;"                 +
            "void main(void) {"                       +
            "  gl_Position = uMVPMatrix * aPosition;" +
            "  vColor      = aColor;"                 +
            "}";

    private final String fragmentShaderCodeColor =
            "precision mediump float;" +
            "varying vec4 vColor;"     +
            "void main(void) {"        +
            "  gl_FragColor = vColor;" +
            "}";

    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;
    private int mProgram;
    private int mPositionHandle;
    private int mColorHandle;
    private int mAlphaHandle;
    private int mMVPMatrixHandle;

    private static final int COORDS_PER_VERTEX = 4;
    private static final int COORDS_PER_COLOR = 4;
    private static final int COORDS_PER_VERTEX_COLOR = COORDS_PER_VERTEX + COORDS_PER_COLOR;

    private static final int NBYTES_PER_FLOAT = 4;
    private static final int NBYTES_PER_SHORT = 2;

    private final int VERTEX_STRIDE = COORDS_PER_VERTEX * NBYTES_PER_FLOAT;
    private final int VERTEX_STRIDE_COLOR = COORDS_PER_VERTEX_COLOR * NBYTES_PER_FLOAT;

    public PolygonRenderer() {
        vertexBuffer = null;
        drawListBuffer = null;
        mProgram = 0;
    }

    public void draw(Polygon polygon, Color color, float[] mvpMatrix) {
        Point[] points = polygon.points();
        float[] polyCoords = new float[points.length * COORDS_PER_VERTEX];
        for (int i = 0; i < points.length; i++) {
            polyCoords[i * COORDS_PER_VERTEX + 0] = (float)points[i].getX();
            polyCoords[i * COORDS_PER_VERTEX + 1] = (float)points[i].getY();
            polyCoords[i * COORDS_PER_VERTEX + 2] = (float)0.0;
            polyCoords[i * COORDS_PER_VERTEX + 3] = (float)1.0;
        }
        short[] drawOrder = polygon.glOrder();

        ByteBuffer bb = ByteBuffer.allocateDirect(polyCoords.length * NBYTES_PER_FLOAT);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(polyCoords);
        vertexBuffer.position(0);

        ByteBuffer dlb = ByteBuffer.allocateDirect(drawOrder.length * NBYTES_PER_SHORT);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        int vertexShader = GlRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = GlRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram);
        GLES20.glUseProgram(mProgram);

        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "uColor");
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        GlRenderer.checkGlError("glGetUniformLocation");

        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, VERTEX_STRIDE, vertexBuffer);
        GLES20.glUniform4fv(mColorHandle, 1, color.GLcoords(), 0);
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
        GlRenderer.checkGlError("glUniformMatrix4fv");

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
