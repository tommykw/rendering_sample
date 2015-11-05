package tokyo.tommy_kw.renderingsample.bao;

import java.util.ArrayList;

import tokyo.tommy_kw.renderingsample.surface.GLPhotoSurface;
import tokyo.tommy_kw.renderingsample.util.Utils;

/**
 * Created by tommy on 15/11/05.
 */
public class PatternSwitch extends Pattern {
    protected ArrayList<Double> mSidePattern;

    public PatternSwitch(GLPhotoSurface surface) {
        super(surface);
        mSidePattern = new ArrayList<Double>();
        mSidePattern.add(1.0);
    }

    public static PatternSwitch newInstance(GLPhotoSurface surface) {
        return new PatternSwitch(surface);
    }

    public double side() {
        return Utils.circular(mSidePattern, index);
    }

    public PatternSwitch sidePattern(ArrayList<Double> sidePattern) {
        mSidePattern = sidePattern;
        return this;
    }
}
