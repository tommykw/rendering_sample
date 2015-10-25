package tokyo.tommy_kw.renderingsample.geometry;

/**
 * Created by tommy on 15/10/20.
 */
public abstract class Shape {
    public abstract Box box();
    public static boolean intersect(Shape s1, Shape s2) {
        return false;
    }
}
