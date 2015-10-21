package tokyo.tommy_kw.renderingsample.geometry;

/**
 * Created by tommy on 15/10/21.
 */
public class Polygon {
    private Point[] mPoints;
    public Polygon(Point[] ps) {
        mPoints = ps;
    }

    public Polygon(float[] coords) {
        mPoints = new Point[coords.length / 2];
        for (int i = 0; i < coords.length / 2; i++) {
            mPoints[i] = new Point(coords[2*i],coords[2*i+1]);
        }
    }

    public Point[] points() {
        return mPoints;
    }

    public float[] coords() {
        float[] result = new float[mPoints.length * 2];
        int index = 0;
        for (Point p : mPoints) {
            result[2 * index] = (float)p.getX();
            result[2 * index + 1] = (float)p.getY();
            index += 1;
        }
        return result;
    }

    public Point middle() {
        float x = 0.0f;
        float y = 0.0f;
        for (Point p : mPoints) {
            x += p.getX();
            y += p.getY();
        }
        float len = (float) mPoints.length;
        return new Point(x / len, y / len);
    }
}
