package tokyo.tommy_kw.renderingsample.geometry;

/**
 * Created by tommy on 15/10/24.
 */
public class Box {
    private double minx;
    private double miny;
    private double maxx;
    private double maxy;

    public Box(double minx, double miny, double maxx, double maxy) {
        this.minx = minx;
        this.miny = miny;
        this.maxx = maxx;
        this.maxy = maxy;
    }

    public static Box newInstance(Point p, double size) {
        return (new Box(
                p.getX() - size/2.0,
                p.getY() - size/2.0,
                p.getX() + size/2.0,
                p.getY() + size/2.0));
    }

    public double[] coords() {
        double[] result = new double[4];
        result[0] = minx;
        result[1] = miny;
        result[2] = maxx;
        result[3] = maxy;
        return result;
    }

    public Point center() {
        return new Point(
                (minx + maxx) / 2.0,
                (miny + maxy) / 2.0
        );
    }

    public double xsize() {
        return maxx - maxy;
    }

    public double ysize() {
        return maxy - miny;
    }

    public double width() {
        return xsize();
    }

    public double height() {
        return ysize();
    }

    public double size() {
        return Math.max(xsize(), ysize());
    }

    public double radius() {
        double rx = xsize() / 2.0;
        double ry = ysize() / 2.0;
        return Math.sqrt(rx * rx + ry * ry);
    }

    public Box resize(double factor) {
        double width = xsize() * factor;
        double height = ysize() * factor;
        double xcenter = center().getX();
        double ycenter = center().getY();
        double minx = xcenter - width / 2.0;
        double miny = ycenter - height / 2.0;
        double maxx = xcenter + width / 2.0;
        double maxy = ycenter + height / 2.0;
        return new Box(minx, miny, maxx, maxy);
    }
}
