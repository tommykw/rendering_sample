package tokyo.tommy_kw.renderingsample.util;

/**
 * Created by tommy on 15/10/23.
 */
public class Range {
    private double v1;
    private double v2;

    public Range(double v1, double v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public static Range newInstance(double v1, double v2) {
        return new Range(v1, v2);
    }

    public double v1() {
        return v1;
    }

    public double v2() {
        return v2;
    }

    public double sample(double s) {
        return v1 + (v2 - v1) * s;
    }

    public double trim(double v) {
        if (v < min()) {
            return min();
        }
        if (v > max()) {
            return max();
        }
        return v;
    }

    public double min() {
        return v1 < v2 ? v1 : v2;
    }

    public double max() {
        return v1 > v2 ? v1 : v2;
    }
}
