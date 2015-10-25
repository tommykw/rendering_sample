package tokyo.tommy_kw.renderingsample.quadtree;

import java.util.ArrayList;

import tokyo.tommy_kw.renderingsample.geometry.Box;
import tokyo.tommy_kw.renderingsample.geometry.Shape;

/**
 * Created by tommy on 15/10/24.
 */
public class QuadTree {
    private Box box;
    private Quad quad;
    private int push;

    public QuadTree() {
        box = new Box(-10000.0,-10000.0,10000.0,10000.0);
        quad = new Quad(box);
        push = 0;
    }

    public void push() {
        push += 1;
    }

    public void pop() {
        quad.pop(push);
        push += -1;
    }

    public void add(Shape shape) {
        quad.add(shape, push);
    }

    public void adds(ArrayList<Shape> shapes) {
        for (Shape s : shapes) {
            add(s);
        }
    }

    public ArrayList<Shape> shapes() {
        return quad.shapes();
    }

}
