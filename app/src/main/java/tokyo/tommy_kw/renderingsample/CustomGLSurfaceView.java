package tokyo.tommy_kw.renderingsample;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by tomita on 15/10/16.
 */
public class CustomGLSurfaceView extends GLSurfaceView {
    private final CustomRenderer renderer;

    public CustomGLSurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        renderer = new CustomRenderer(context);
        setRenderer(renderer);
        setRenderer(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
}
