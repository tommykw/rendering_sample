package tokyo.tommy_kw.renderingsample.geometry;

/**
 * Created by tommy on 15/10/20.
 */
public class Circle extends Shape {
    protected Point point;
    protected double radius;

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
        return Box.newInstance(point(), radius());
    }

    public static Boolean intersect(Circle c1, Circle c2) {
        double rad = (c1.radius() + c2.radius());
        Boolean result = (Point.distanceSqrt(c1.point(), c2.point()) < (rad * rad));
        return result;
    }

    public static Circle circle2tangentout(Circle c1, Circle c2, double radius, double side) {
        return Circle.circle2tangent(c1, true, c2, true, radius, side);
    }

    public static Circle circle2tangent(Circle c1, Boolean out1, Circle c2, Boolean out2, double radius, double side) {
        if (c1 == null || c2 == null) {
            return null;
        }

        double x1 = c1.x();
        double y1 = c1.y();
        double r1 = c1.r();
        double x2 = c2.x();
        double y2 = c2.y();
        double r2 = c2.r();
        Vector s3 = Vector.newInstance(c2.point(), c1.point());
        double l3 = s3.length();
        double out = 1.0;
        if (!out1 || !out2) {
            out = -1.0;
        }
        double l1 = r1 + out * radius;
        double l2 = r2 + radius;
        double denom = (2.0 * l2 * l3);
        if (denom == 0.0) {
            return null;
        }
        double cos = (l3 * l3 - l1 * l1 + l2 * l2) / denom;
        if (cos < -1.0 || cos > 1.0) {
            return null;
        }
        double angle = Math.acos(cos) * side;
        Vector v = s3.rotate(angle).normalize().scale(l2);
        Point c = c2.point().add(v);
        return Circle.newInstace(c, radius);
    }
}
