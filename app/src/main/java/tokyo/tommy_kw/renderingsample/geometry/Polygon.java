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

    public int glnPoint() {
        return mPoints.length + 1;
    }

    public Point[] glPoint() {
        Point[] result = new Point[mPoints.length + 1];
        result[0] = middle();
        for (int i = 0; i < mPoints.length; i++) {
            result[i+1] = mPoints[i];
        }
        return result;
    }

    public int glOrderSize() {
        return mPoints.length * 3;
    }

    public short[] glOrder() {
        short[] result = new short[glOrderSize()];
        for (short i = 1; i < mPoints.length; i++) {
            result[i * 3] = 0;
            result[i * 3 + 1] = i;
            result[i * 3 + 2] = (short) (i + 1);
        }

        return result;
    }

}
