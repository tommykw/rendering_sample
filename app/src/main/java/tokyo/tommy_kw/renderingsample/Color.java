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
}
