package tokyo.tommy_kw.renderingsample.geometry;

/**
 * Created by tommy on 15/10/28.
 */
public class Intersection {
    private Point point;
    private Segment segment;

    public Intersection(Point point) {
        this.point = point;
        this.segment = null;
    }

    public Intersection(Segment segment) {
        this.point = null;
        this.segment = segment;
    }

    public static Intersection intersection(Segment s1, Segment s2) {
        return Intersection.intersection(s1.p1(), s1.p2(), s2.p1(), s2.p2());
    }

    public static Point min(Point p1, Point p2) {
        if (p1.getX() == p2.getX()) {
            return p1.getY() <= p2.getY() ? p1 : p2;
        }
        return p1.getX() < p2.getX() ? p1 : p2;
    }

    public static Point max(Point p1, Point p2) {
        if (p1.getX() == p2.getX()) {
            return p1.getY() >= p2.getY() ? p1 : p2;
        }
        return p1.getX() > p2.getX() ? p1 : p2;
    }

    public static Intersection intersection(Point p1, Point p2, Point p3, Point p4) {
        Intersection result = null;
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        double x3 = p3.getX();
        double y3 = p3.getY();
        double x4 = p4.getX();
        double y4 = p4.getY();

        double denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        double uanum = (x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3);
        double ubnum = (x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3);

        if (denom == 0.0 && uanum == 0.0 && ubnum == 0.0) {
            Point xp1 = Intersection.min(p1, p2);
            Point xp2 = Intersection.max(p1, p2);
            Point xp3 = Intersection.min(p3, p4);
            Point xp4 = Intersection.max(p3, p4);
            x1 = xp1.getX();
            y1 = xp1.getY();
            x2 = xp2.getX();
            y2 = xp2.getY();
            x3 = xp3.getX();
            y3 = xp3.getY();
            x4 = xp4.getX();
            y4 = xp4.getY();

        }

        return null;
    }

