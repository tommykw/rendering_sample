package tokyo.tommy_kw.renderingsample;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

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
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        renderer.onResume();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        renderer.processTouchEvent(e);
        return true;
    }
}
