package tokyo.tommy_kw.renderingsample.drawer;

import tokyo.tommy_kw.renderingsample.geometry.Polygon;

/**
 * Created by tommy on 15/11/03.
 */
public class Drawer {
    private Polygon polygon;
    private Color color;

    public Drawer(Polygon polygon, Color color) {
        this.polygon = polygon;
        this.color = color;
    }

    public Color color() {
        return color;
    }

    public Polygon polygon() {
        return polygon;
    }
}
