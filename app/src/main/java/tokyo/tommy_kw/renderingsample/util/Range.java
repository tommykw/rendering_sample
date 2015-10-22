package tokyo.tommy_kw.renderingsample.util;

import java.util.ArrayList;
import java.util.List;

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

    public List<Double> samples(int i) {
        List<Double> result = new ArrayList<Double>();
        for (double a : Range.lsamples(i)) {
            result.add(sample(a));
        }
        return result;
    }

    public static List<Double> lsamples(int i) {
        List<Double> result = new ArrayList<Double>();
        if (i == 0) {
            return result;
        }

        if (i == 1) {
            result.add(0.5);
            return result;
        }

        for (int x = 0; x < i; x++) {
            result.add((double)x / (double)(i - 1));
        }
        return result;
    }

    public double rand() {
        return 0f;
    }

    public Boolean contain(double v) {
        return (v1 <= v2) ? (v1 <= v && v <= v2) : (v2 <= v && v <= v1);
    }
}
