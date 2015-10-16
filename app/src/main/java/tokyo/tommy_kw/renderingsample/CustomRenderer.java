package tokyo.tommy_kw.renderingsample;

import android.graphics.Rect;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by tomita on 15/10/16.
 */
public class CustomRenderer implements GLSurfaceView.Renderer {
    private final float[] matricesProjection = new float[16];
    private final float[] matricesView = new float[16];
    private final float[] matricesProjectionAndView = new float[16];
    public Rect image;

    private float mScreenWidth = 1280;
    private float mScreenHeight = 768;

    public static float vertices[];
    public static short indices[];
    public static float uvs[];
    public FloatBuffer vertexBuffer;
    public ShortBuffer drawListBuffer;
    public FloatBuffer uvBuffer;
    public CustomSprite sprite;

    private long lastTime;
    private int program;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        long now = System.currentTimeMillis();
        if (lastTime > now) return;
        long elapsed = now - lastTime;
        Render
    }

    public CustomRenderer() {
        lastTime = System.currentTimeMillis() + 100;
    }

    public void onResume() {
        lastTime = System.currentTimeMillis();
    }

    private void Render(float[] m) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        int position = GLES20.glGetAttribLocation(GUtil.sp_Image, "vPosition");
        GLES20.glEnableVertexAttribArray(position);
        GLES20.glVertexAttribPointer(position, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer);
        int texCoordLoc = GLES20.glGetAttribLocation(GUtil.sp_Image, "a_texCoord");

        GLES20.glEnableVertexAttribArray(texCoordLoc);
        GLES20.glVertexAttribPointer(texCoordLoc, 2, GLES20.GL_FLOAT, false, 0, uvBuffer);
        int mtrxhandle = GLES20.glGetUniformLocation(GUtil.sp_Image, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(mtrxhandle, 1, false, m, 0);
        int samplerLoc = GLES20.glGetUniformLocation(GUtil.sp_Image, "s_texture");
        GLES20.glUniform1i(samplerLoc, 0);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indices.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
        GLES20.glDisableVertexAttribArray(position);
        GLES20.glDisableVertexAttribArray(texCoordLoc);
    }
}
