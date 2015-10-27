package tokyo.tommy_kw.renderingsample.geometry;

/**
 * Created by tommy on 15/10/20.
 */
public class Circle extends Shape {
    private Point point;
    private double radius;

    public Circle() {
        point = new Point();
        radius = 1.0;
    }

    public Circle(Point p, double r) {
        point = p;
        radius = r;
    }

    public Circle(double x, double y, double r) {
        point = new Point();
        radius = r;
    }

    public static Circle newInstace(Point p, double r) {
        return new Circle(p, r);
    }

    public Point point() {
        return point;
    }

    public double radius() {
        return radius;
    }

    public double r() {
        return radius;
    }

    public double x() {
        return point.getX();
    }

    public double y() {
        return point.getY();
    }

    public Point point(double angleratio) {
        return point.add(new Vector(Math.cos(angleratio * (2.0 * Math.PI)), Math.sin(angleratio * (2.0 * Math.PI))).scale(radius));
    }

    public Circle scale(double ratio) {
        return Circle.newInstace(point(), ratio);
    }

    @Override
    public Box box() {
        return null;
    }


}
