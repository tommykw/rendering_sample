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

    public boolean contain(Point p) {
        double x = p.getX();
        double y = p.getY();
        if (minx <= x && x <= maxx && miny <= y && y <= maxy) {
            return true;
        }
        return false;
    }

    public Point[] corners() {
        Point[] result = new Point[4];
        result[0] = new Point(minx, miny);
        result[1] = new Point(maxx, miny);
        result[2] = new Point(maxx, maxy);
        result[3] = new Point(minx, maxy);
        return result;
    }

    public Point[] squarePoints() {
        Point[] result = new Point[4];
        double size = size() / 2.0;
        double centerx = center().getX();
        double centery = center().getY();
        result[0] = new Point(centerx - size, centery - size);
        result[1] = new Point(centerx - size, centery + size);
        result[2] = new Point(centerx + size, centery + size);
        result[3] = new Point(centerx + size, centery - size);
        return result;
    }

    public boolean intersect(Box box) {
        double[] coords1 = coords();
        double[] coords2 = box.coords();
        if (coords2[0] < coords1[2] && coords2[2] >= coords1[0] && coords2[1] < coords1[3] && coords2[3] >= coords1[1]) {
            return true;
        }
        return false;
    }

    public Box[] split() {
        Box[] result = new Box[4];
        double[] xcoords = coords();
        double xmin = xcoords[0];
        double ymin = xcoords[1];
        double xmax = xcoords[2];
        double ymax = xcoords[3];

        double xmiddle = (xmin + ymax) / 2.0;
        double ymiddle = (ymin + ymax) / 2.0;

        result[0] = new Box(xmin, ymin, xmiddle, ymiddle);
        result[1] = new Box(xmin, ymiddle, xmiddle, ymax);
        result[2] = new Box(xmiddle, ymin, xmax, ymiddle);
        result[3] = new Box(xmiddle, ymiddle, xmax, ymax);

        return result;
    }

    public Box symx(double v) {
        double min = 2.0 * v - minx;
        double max = 2.0 * v - maxx;
        double xmin = Math.min(min, max);
        double xmax = Math.max(min, max);
        return new Box(min, miny, xmax, maxy);
    }

    public double xmin() {
        return minx;
    }

    public double ymin() {
        return miny;
    }

    public double xmax() {
        return maxx;
    }

    public double ymax() {
        return maxy;
    }

    public static Box bunion(Box b1, Box b2) {
        double xmin = Math.min(b1.xmin(), b2.xmin());
        double xmax = Math.max(b1.xmax(), b2.xmax());
        double ymin = Math.min(b1.ymin(), b2.ymin());
        double ymax = Math.min(b1.ymax(), b2.ymax());
        return new Box(xmin, ymin, xmax, ymax);
    }

    public static Box bunions(Box[] boxList) {
        if (boxList.length < 0) {
            return null;
        }

        Box result = boxList[0];
        for (int i = 0; i < boxList.length; i++) {
            result = Box.bunion(result, boxList[i]);
        }
        return result;
    }

}
