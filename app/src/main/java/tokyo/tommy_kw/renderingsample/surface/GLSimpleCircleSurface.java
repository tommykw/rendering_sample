package tokyo.tommy_kw.renderingsample.surface;

import android.content.Context;

import java.util.Random;

import tokyo.tommy_kw.renderingsample.drawer.Color;
import tokyo.tommy_kw.renderingsample.geometry.Circle;
import tokyo.tommy_kw.renderingsample.geometry.Point;
import tokyo.tommy_kw.renderingsample.util.Range;

/**
 * Created by tommy on 15/11/03.
 */
public class GLSimpleCircleSurface extends GLPhotoSurface {
    protected Random mRandom;

    public GLSimpleCircleSurface(Context context) {
        super(context);
        mRandom = new Random(0);
    }

    protected void createCircle(float x, float y) {
        Circle newCircle = new Circle(new Point(x + Range.newInstance(-10.0, 10.0).rand(mRandom), y + Range.newInstance(-10.0, 10.0).rand(mRandom)), 20.0);
        Color color = Color.rand(mRandom, 1.0);
        this.draw(newCircle, color);
        requestRender();
    }

    @Override
    public void draw(Circle circle, Color color) {
        super.draw(circle, color);
    }

    @Override
    protected void onTouchDown(float x, float y, long currentTime) {
        createCircle(x,y);
    }

    @Override
    protected void onTouchMove(float x, float y, long currentTime, long elapsedTime) {
        createCircle(x,y);
    }
}
