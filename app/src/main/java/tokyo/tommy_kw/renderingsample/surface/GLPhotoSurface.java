package tokyo.tommy_kw.renderingsample.surface;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.view.MotionEvent;

import timber.log.Timber;
import tokyo.tommy_kw.renderingsample.Color;
import tokyo.tommy_kw.renderingsample.GlRenderer;
import tokyo.tommy_kw.renderingsample.geometry.Circle;
import tokyo.tommy_kw.renderingsample.geometry.Polygon;

/**
 * Created by tommy on 15/10/25.
 */
public class GLPhotoSurface extends GLSurfaceView {
    private final GlRenderer renderer;
    private long startTimeDown = 0;
    private long lastTimeMove = 0;
    private long startTimeTimer = 0;
    private long lastTimeTimer = 0;
    private Handler timerHandler;
    private Runnable timerRunnable;

    public GLPhotoSurface(Context context) {
        super(context);
        setEGLContextClientVersion(3);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        renderer = new GlRenderer();
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public void draw(final Circle circle, final Color color) {
        Timber.d("draw circle");
        queueEvent(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTimeDown = System.currentTimeMillis();
                lastTimeMove = startTimeDown;
                onTouchDown(e.getX(), getHeight() - e.getY(), startTimeDown);
                break;
            case MotionEvent.ACTION_MOVE:
                long amcurrentTime = System.currentTimeMillis();
                long elapsedTime = amcurrentTime - lastTimeMove;
                onTouchMove(e.getX(), getHeight() - e.getY(), amcurrentTime, elapsedTime);
                lastTimeMove = amcurrentTime;
                break;
            case MotionEvent.ACTION_UP:
                long aucurrentTime = System.currentTimeMillis();
                onTouchUp(e.getX(), getHeight() - e.getY(), aucurrentTime);
                break;
        }
        return true;
    }

    public void startTimer(final long period) {
        Timber.v("startTimer");
        startTimeTimer = System.currentTimeMillis();
        lastTimeTimer = startTimeTimer;

        timerHandler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - lastTimeTimer;
                onTimer(currentTime, elapsedTime);
                lastTimeTimer = System.currentTimeMillis();
                timerHandler.postDelayed(this, period);
            }
        };

        timerHandler.postDelayed(timerRunnable, period);
    }

    public void stopTimer() {
        Timber.v("stopTimer");
        timerHandler.removeCallbacks(timerRunnable);
    }

    protected void onTimer(long currentTime, long elapsedTime) {
    }

    protected void onTouchDown(float x, float y, long currentTime) {
    }

    protected void onTouchMove(float x, float y, long currentTime, long elapsedTime) {
    }

    protected void onTouchUp(float x, float y, long currentTime) {

    }
}
