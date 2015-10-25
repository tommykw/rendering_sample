package tokyo.tommy_kw.renderingsample.surface;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;

import tokyo.tommy_kw.renderingsample.GlRenderer;

/**
 * Created by tommy on 15/10/25.
 */
public class PhotoSurface extends GLSurfaceView {
    private final GlRenderer renderer;
    private long startTimeDown = 0;
    private long lastTimeMove = 0;
    private long startTimeTimer = 0;
    private long lastTimeTimer = 0;
    private Handler timerHandler;
    private Runnable timerRunnable;

    public PhotoSurface(Context context) {
        super(context);
        setEGLContextClientVersion(3);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        renderer = new GlRenderer();
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public void draw() {}
    
}
