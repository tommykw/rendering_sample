package tokyo.tommy_kw.renderingsample.geometry;

/**
 * Created by tommy on 15/10/20.
 */
public class Point {
    private double x;
    private double y;

    public Point() {
        x = 0.0;
        y = 0.0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Point newInstance(double x, double y) {
        return new Point(x, y);
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public Point add() {
        return null;
    }

    public static double distance(Point p1, Point p2) {
        double x = p1.getX() - p2.getX();
        double y = p1.getY() - p2.getY();
        return (x * x + y * y);
    }

    public static double distanceSqrt(Point p1, Point p2) {
        return Math.sqrt(Point.distance(p1, p2));
    }
}
