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

    public Vector normalize() {
        double len = length();
        if (len == 0.0) {
            return Vector.V1;
        } else {
            return scale(1.0 / len);
        }
    }

    public Vector add(Vector v) {
        return (new Vector(x + v.getX(), v.getY()));
    }

    public Vector scale(double ratio) {
        return (new Vector(x * ratio, y * ratio));
    }

    public Vector scalex(double ratio) {
        return (new Vector(x * ratio, y));
    }

    public Vector scaley(double ratio) {
        return (new Vector(x, y * ratio));
    }

    public Vector ortho() {
        return Vector.newInstance(-y, x);
    }

    public Vector rotate(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = getX();
        double y = getY();
        return Vector.newInstance(
                x * cos - y * sin,
                x * sin + y * cos);
    }

    public static double cross(Vector v1, Vector v2) {
        return (v1.getX() * v2.getY() - v1.getY() * v2.getX());
    }
}
