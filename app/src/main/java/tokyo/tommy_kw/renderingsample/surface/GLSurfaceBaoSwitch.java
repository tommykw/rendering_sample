package tokyo.tommy_kw.renderingsample.surface;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import tokyo.tommy_kw.renderingsample.bao.CirclePacking;
import tokyo.tommy_kw.renderingsample.bao.Node;
import tokyo.tommy_kw.renderingsample.bao.Pattern;
import tokyo.tommy_kw.renderingsample.bao.PatternSwitch;
import tokyo.tommy_kw.renderingsample.drawer.Color;
import tokyo.tommy_kw.renderingsample.geometry.Circle;
import tokyo.tommy_kw.renderingsample.geometry.Point;
import tokyo.tommy_kw.renderingsample.quadtree.QuadTree;

/**
 * Created by tommy on 15/11/04.
 */
public class GLSurfaceBaoSwitch extends GLPhotoSurface {
    protected QuadTree mQuadTree;
    private CirclePacking mPacking;

    public GLSurfaceBaoSwitch(Context context) {
        super(context);
        mQuadTree = new QuadTree();
    }

    @Override
    protected void onTimer(long currentTime, long elapsedTime) {
        if (mPacking != null) {
            mPacking.iter(10);
            requestRender();
        }
    }

    @Override
    protected void onTouchDown(float x, float y, long currentTime) {
        Circle newCircle = new Circle(new Point(x, y),  20.0);
        if (!mQuadTree.isColliding(newCircle)) {
            ArrayList<Color> colorPattern = new ArrayList<Color>();
            colorPattern.add(Color.white());
            colorPattern.add(Color.red());
            ArrayList<Double> radiusPattern = new ArrayList<Double>();
            radiusPattern.add(10.0);
            ArrayList<Double> sidePattern = new ArrayList<Double>();
            for (int i = 0; i < 10; i++) {
                sidePattern.add(1.0);
            }
            for (int i = 0; i < 10; i++) {
                sidePattern.add(-1.0);
            }
            PatternSwitch pattern = (PatternSwitch)PatternSwitch.newInstance(this).sidePattern(sidePattern).colorPattern(colorPattern).radiusPattern(radiusPattern);
            ArrayList<Node> nodes = Node.fromCircle(newCircle);
            mPacking = new CirclePacking(null, nodes, pattern, 1.0, mQuadTree);
            for (Node n : nodes) {
                draw(n, Color.yellow());
            }
        }
        startTimer(10);
    }

    @Override
    protected void onTouchUp(float x, float y, long currentTime) {
        stopTimer();
    }

}
