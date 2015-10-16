package tokyo.tommy_kw.renderingsample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;
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
    private Context mContext;

    public CustomRenderer(Context context) {
        mContext = context;
        lastTime = System.currentTimeMillis() + 100;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        SetupTriangle();
        SetupImage();

        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1);

        int vertexShader = GUtil.loadShader(GLES20.GL_VERTEX_SHADER,
                GUtil.vs_SolidColor);
        int fragmentShader = GUtil.loadShader(GLES20.GL_FRAGMENT_SHADER,
                GUtil.fs_SolidColor);

        GUtil.sp_SolidColor = GLES20.glCreateProgram();
        GLES20.glAttachShader(GUtil.sp_SolidColor, vertexShader);
        GLES20.glAttachShader(GUtil.sp_SolidColor, fragmentShader);
        GLES20.glLinkProgram(GUtil.sp_SolidColor);

        vertexShader = GUtil.loadShader(GLES20.GL_VERTEX_SHADER,
                GUtil.vs_Image);
        fragmentShader = GUtil.loadShader(GLES20.GL_FRAGMENT_SHADER,
                GUtil.fs_Image);

        GUtil.sp_Image = GLES20.glCreateProgram();
        GLES20.glAttachShader(GUtil.sp_Image, vertexShader);
        GLES20.glAttachShader(GUtil.sp_Image, fragmentShader);
        GLES20.glLinkProgram(GUtil.sp_Image);
        GLES20.glUseProgram(GUtil.sp_Image);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        mScreenWidth = width;
        mScreenHeight = height;

        GLES20.glViewport(0, 0, (int) mScreenWidth, (int) mScreenHeight);

        for (int i = 0; i < 16; i++) {
            matricesProjection[i] = 0.0f;
            matricesView[i] = 0.0f;
            matricesProjectionAndView[i] = 0.0f;
        }

        Matrix.orthoM(matricesProjection, 0, 0f, mScreenWidth, 0.0f, mScreenHeight, 0, 50);
        Matrix.setLookAtM(matricesView, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(matricesProjectionAndView, 0, matricesProjection, 0, matricesView, 0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        long now = System.currentTimeMillis();
        if (lastTime > now) return;
        long elapsed = now - lastTime;
        Render(matricesProjectionAndView);
        lastTime = now;
        UpdateSprite();
    }

    public void UpdateSprite() {
        vertices = sprite.getTransformedVertices();
        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
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

    public void SetupTriangle() {
        sprite = new CustomSprite();
        vertices = sprite.getTransformedVertices();
        indices = new short[]{0,1,2,0,2,3};

        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        ByteBuffer dlb = ByteBuffer.allocateDirect(indices.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(indices);
        drawListBuffer.position(0);
    }

    public void TranslateSprite() {
        vertices = sprite.getTransformedVertices();
        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
    }

    public void processTouchEvent(MotionEvent event) {
        int screenHalf = (int) (mScreenWidth / 2);
        int screenHeightPart = (int) (mScreenHeight / 3);
        if (event.getX() < screenHalf) {
            if (event.getY() < screenHeightPart) {
                sprite.scale(-0.01f);
            } else if (event.getY() < (screenHeightPart * 2)) {
                sprite.translate(-10f, -10f);
            } else {
                sprite.rotate(0.01f);
            }
        } else {
            if (event.getY() < screenHeightPart) {
                sprite.scale(-0.01f);
            } else if (event.getY() < (screenHeightPart * 2)) {
                sprite.translate(-10f, -10f);
            } else {
                sprite.rotate(0.01f);
            }
        }
    }

    private void SetupImage() {
        uvs = new float[]{
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f
        };

        ByteBuffer bb = ByteBuffer.allocateDirect(uvs.length * 4);
        bb.order(ByteOrder.nativeOrder());
        uvBuffer = bb.asFloatBuffer();
        uvBuffer.put(uvs);
        uvBuffer.position(0);

        int[] texturenames = new int[1];
        GLES20.glGenTextures(1, texturenames, 0);

        int id = mContext.getResources().getIdentifier("drawable/ic_launcher", null,
                mContext.getPackageName());

        Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(), id);

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texturenames[0]);

        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,
                GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER,
                GLES20.GL_LINEAR);

        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,
                GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,
                GLES20.GL_CLAMP_TO_EDGE);

        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmp, 0);
        bmp.recycle();
    }
}
