package tokyo.tommy_kw.renderingsample.quadtree;

import java.util.ArrayList;
import java.util.HashMap;

import tokyo.tommy_kw.renderingsample.geometry.Box;
import tokyo.tommy_kw.renderingsample.geometry.Shape;

/**
 * Created by tommy on 15/10/24.
 */
public class Quad {
    private Box box;
    private ArrayList<Quad> quads;
    private HashMap<Integer, ArrayList<Shape>>  shapeMap;
    private int maxShapeNumber;

    public Quad(Box b) {

    }
}
