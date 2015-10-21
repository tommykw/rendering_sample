package tokyo.tommy_kw.renderingsample.geometry;

/**
 * Created by tommy on 15/10/22.
 */
public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Point p1, Point p2) {
        x = p2.getX() - p1.getX();
        y = p2.getY() - p1.getY();
    }

    public static Vector newInstance(double x, double y) {
        return new Vector(x, y);
    }

    public static Vector newInstance(Point p1, Point p2) {
        return new Vector(p1, p2);
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public double length() {
        return x * x + y * y;
    }

    public double lengthSqrt() {
        return Math.sqrt(length());
    }

    public static Vector V1 = Vector.newInstance(0.0, 0.0);
    public static Vector V2 = Vector.newInstance(1.0, 0.0);
    public static Vector V3 = Vector.newInstance(0.0, 010);
}
