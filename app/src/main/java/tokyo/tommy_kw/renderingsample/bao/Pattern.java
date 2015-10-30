package tokyo.tommy_kw.renderingsample.bao;

import java.util.ArrayList;

import tokyo.tommy_kw.renderingsample.Color;
import tokyo.tommy_kw.renderingsample.surface.GLPhotoSurface;
import tokyo.tommy_kw.renderingsample.util.Utils;

/**
 * Created by tommy on 15/10/29.
 */
public class Pattern {
    private ArrayList<Double> radiusPattern;
    private ArrayList<Color> colorPattern;
    private int index;
    private GLPhotoSurface photoSurface;

    public Pattern(GLPhotoSurface photoSurface) {
        this.photoSurface = photoSurface;
        radiusPattern = new ArrayList<Double>();
        radiusPattern.add(1.0);
        colorPattern = new ArrayList<Color>();
        colorPattern.add(Color.black());
        index = 0;
    }

    public void draw(Node node, int index) {
        draw(node, index, color(index));
    }

    public void draw(Node node, int index, Color color) {
        photoSurface.draw(node, color);
    }

    public Pattern next() {
        index += 1;
        return this;
    }

    public Pattern radiusPattern(ArrayList<Double> radiusPattern) {
        this.radiusPattern = radiusPattern;
        return this;
    }

    public Pattern colorPattern(ArrayList<Color> colorPatter) {
        this.colorPattern = colorPatter;
        return this;
    }

    public double radius() {
        return Utils.circular(radiusPattern, index);
    }

    public Color color() {
        return color(index);
    }

    public Color color(int idx) {
        return Utils.circular(colorPattern, idx);
    }

    public static Color index2color(int period, int idx) {
        return null;
    }
}
