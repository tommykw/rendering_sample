package tokyo.tommy_kw.renderingsample;

/**
 * Created by tommy on 15/10/20.
 */
public class Color {
    private double mR;
    private double mG;
    private double mB;
    private double mA;
    private double[] mcoords;

    public Color(double r, double g, double b, double a) {
        mcoords = new double[4];
        mcoords[0] = r;
        mcoords[1] = g;
        mcoords[2] = b;
        mcoords[3] = a;
    }

    public static Color newInstance(double r, double g, double b, double a) {
        return new Color(r, g, b, a);
    }

    public double R() { return mcoords[0]; }
    public double G() { return mcoords[1]; }
    public double B() { return mcoords[2]; }
    public double A() { return mcoords[3]; }
    public double[] coords() { return mcoords; }

    public float[] GLcoords() {
        float[] result = new float[4];
        result[0] = (float)R();
        result[1] = (float)G();
        result[2] = (float)B();
        result[3] = (float)A();
        return result;
    }

    public static Color black() { return new Color(0.0, 0.0, 0.0, 1.0); }
    public static Color white() { return new Color(1.0, 1.0, 1.0, 1.0); }
    public static Color red() { return new Color(1.0, 0.0, 0.0, 1.0); }
    public static Color yellow() { return new Color(1.0, 1.0, 0.0, 1.0); }

    public static Color hue2color(double hue) {
        return Color.hsv(hue, 1.0, 1.0, 1.0);
    }

    public static Color hsv(double h, double s, double v, double a) {
        if (h > 1.0) h -= 1.0;
        if (h < 0.0) h += 1.0;
        Double[] rgb = Color.hsv2rgb(h, s, v);
        return Color.newInstance(rgb[0], rgb[1], rgb[2], a);
    }

    public static Double[] hsv2rgb(double h, double s, double v) {
        if (s == 0.0) {return new Double[]{v,v,v};}
        long i = Math.round(h * 6.0);
        double f = (h * 6.0) - i;
        double p = v * (1.0 - s);
        double q = v * (1.0 - s * f);
        double t = v * (1.0 - s * (1.0 - f));
        i = i%6;
        if (i == 0) {return new Double[]{v,t,p};}
        if (i == 1) {return new Double[]{q,v,p};}
        if (i == 2) {return new Double[]{p,v,t};}
        if (i == 3) {return new Double[]{p,q,v};}
        if (i == 4) {return new Double[]{t,p,v};}
        if (i == 5) {return new Double[]{v,p,q};}
        return null;
    }


}
