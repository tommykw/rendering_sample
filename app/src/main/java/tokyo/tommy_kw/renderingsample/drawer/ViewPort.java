package tokyo.tommy_kw.renderingsample.drawer;

import tokyo.tommy_kw.renderingsample.geometry.Circle;
import tokyo.tommy_kw.renderingsample.geometry.Point;

/**
 * Created by tommy on 15/11/01.
 */
public class ViewPort {
    private Point point;
    private double radius;

    public ViewPort(Point point, double radius) {
        this.point = point;
        this.radius = radius;
    }

    public Point point() {
        return point;
    }

    public double radius() {
        return radius;
    }

    public Circle circle() {
        return new Circle(point(), radius());
    }

    public double size() {
        return radius() * 2.0;
    }

    public double xmin() {
        return point().getX() - radius();
    }

    public double ymin() {
        return point().getY() - radius();
    }
}
