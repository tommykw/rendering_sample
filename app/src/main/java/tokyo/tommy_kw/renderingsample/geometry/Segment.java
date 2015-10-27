package tokyo.tommy_kw.renderingsample.geometry;

import android.util.Pair;

import java.util.ArrayList;

import tokyo.tommy_kw.renderingsample.util.Range;
import tokyo.tommy_kw.renderingsample.util.Utils;

/**
 * Created by tommy on 15/10/28.
 */
public class Segment {
    private Point p1;
    private Point p2;
    private Range x;
    private Range y;

    public Segment(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.x = new Range(p1.getX(), p2.getX());
        this.y = new Range(p1.getY(), p2.getY());
    }

    public static Segment newInstance(Point p1, Point p2, ) {
        return new Segment(p1, p2);
    }

    public Point p1() {
        return p1;
    }

    public Point p2() {
        return p2;
    }

    public boolean isPoint() {
        return Point.distance(p1, p2) == 0.0;
    }

    public Point middle() {
        return sample(0.5);
    }

    public Point sample(double t) {
        double xt = new Range(0.0, 1.0).trim(t);
        return new Point(x.sample(t), y.sample(t));
    }

    public ArrayList<Point> samples(int p) {
        ArrayList<Point> result = new ArrayList<Point>();
        for (double t : Range.lsamples(p)) {
            result.add(sample(t));
        }
        return result;
    }

    public ArrayList<Point> points() {
        ArrayList<Point> result = new ArrayList<Point>();
        result.add(p1);
        result.add(p2);
        return result;
    }

    public Point point(double t) {
        return sample(t);
    }

    public ArrayList<Double> coords() {
        ArrayList<Double> result = new ArrayList<Double>();
        result.add(p1.getX());
        result.add(p1.getY());
        result.add(p2.getX());
        result.add(p2.getY());
        return result;
    }

    public Vector vector() {
        return new Vector(p1, p2);
    }

    public Vector normal() {
        return vector().ortho().normalize();
    }

    public double length() {
        return vector().length();
    }

    public double length2() {
        return vector().length2();
    }

    public ArrayList<Segment> split(int time) {
        ArrayList<Segment> result = new ArrayList<Segment>();
        for (Pair<Point, Point> pair : Utils.pairs(samples(time + 1))) {
            result.add(new Segment(pair.first, pair.second));
        }
        return result;
    }

    public ArrayList<Segment> spliMaxSize(double maxSize) {
        ArrayList<Segment> result;
        if (length() <= maxSize) {
            result = new ArrayList<Segment>();
            result.add(this);
        } else {
            result = split((int)Math.ceil(length()/maxSize));
        }
        return result;
    }

    public Segment subSegment(double t1, double t2) {
        return new Segment(sample(t1), sample(t2));
    }

    public static ArrayList<Segment> sort(ArrayList<Segment> segments) {
        return null;
    }


}
