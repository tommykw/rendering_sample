package tokyo.tommy_kw.renderingsample.bao;

import java.util.ArrayList;

import tokyo.tommy_kw.renderingsample.geometry.Circle;
import tokyo.tommy_kw.renderingsample.geometry.Point;
import tokyo.tommy_kw.renderingsample.geometry.Segment;

/**
 * Created by tommy on 15/10/29.
 */
public class Node extends Circle {
    private CirclePacking packing;
    private Boolean retouch;
    private Boolean notFound;
    private int colorIndex;
    private int index;

    public Node(CirclePacking packing, Circle circle, int colorIdx, int index) {
        this.packing = packing;
        this.point = circle.point();
        this.radius = circle.radius();
        this.retouch = false;
        this.notFound = false;
        this.colorIndex = colorIdx;
        this.index = index;
    }

    public int colorIndex() {
        return colorIndex;
    }

    public int index() {
        return index;
    }

    public CirclePacking packing() {
        return packing;
    }

    public void packing(CirclePacking packing) {
        this.packing = packing;
    }

    public Boolean retouch() {
        return retouch;
    }

    public void retouch(Boolean retouch) {
        this.retouch = retouch;
    }

    public Boolean notFound() {
        return notFound;
    }

    public void notFound(Boolean notFound) {
        this.notFound = notFound;
    }

    public static ArrayList<Node> nodes(ArrayList<Node> nodes) {
        return Node.nodes(nodes, -1);
    }

    public static ArrayList<Node> nodes(ArrayList<Node> nodes, int startIndex) {
        int idx = startIndex;
        if (startIndex != -1) {
            idx += 1;
        }

        ArrayList<Node> result = new ArrayList<Node>();
        for (Node n : nodes) {
            int xidx = startIndex == -1 ? -1 : idx;
            result.add(new Node(null, n, idx, xidx));
            idx++;
        }

        return result;
    }

    public static ArrayList<Node> fromCircle(Circle circle) {
        return Node.fromCircle(circle, null);
    }

    public static ArrayList<Node> fromCircle(Circle circle, CirclePacking packing) {
        ArrayList<Node> result = new ArrayList<Node>();
        double x = circle.x();
        double y = circle.y();
        double r = circle.r();

        result.add(new Node(packing, new Circle(x - r / 2.0, y, r / 2.0), 0, 0));
        result.add(new Node(packing, new Circle(x + r / 2.0, y, r / 2.0), 1, 1));

        return result;
    }

    public static ArrayList<Node> fromSegement(Segment segment) {
        return fromSegement(segment, null, -1.0);
    }

    public static ArrayList<Node> fromSegment(Segment segment, CirclePacking packing) {
        return fromSegement(segment, packing, -1.0);
    }

    public static ArrayList<Node> fromSegment(Segment segment, CirclePacking packing, double rad) {
        double radius = rad == -1.0 ? segment.length() / 2.0 : rad;
        double offset = rad / segment.length();
        Point center1 = segment.sample(0.5 - offset).add(segment.normal().scale(-radius));
        Point center2 = segment.sample(0.5 + offset).add(segment.normal().scale(-radius));

        ArrayList<Node> result = new ArrayList<Node>();
        result.add(new Node(packing, new Circle(center1, radius), 0, 0));
        result.add(new Node(packing, new Circle(center2, radius), 0, 0));

        return result;
    }

    public static ArrayList<Node> nodeszero() {
        return Node.nodeszero(null);
    }

    public static ArrayList<Node> nodeszero(CirclePacking packing) {
        ArrayList<Node> result = new ArrayList<Node>();
        result.add(new Node(packing, new Circle(0.0, 0.0, 1.0), 0, 0));
        result.add(new Node(packing, new Circle(2.0, 0.0, 1.0), 0, 0));
        return result;
    }
}
