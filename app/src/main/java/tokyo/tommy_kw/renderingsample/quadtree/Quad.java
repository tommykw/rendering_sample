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
        box = b;
        quads = new ArrayList<Quad>();
        shapeMap = new HashMap<Integer, ArrayList<Shape>>();
        maxShapeNumber = 10;
    }

    public ArrayList<Shape> shapes() {
        ArrayList<Shape> result = new ArrayList();
        for (Quad q : quads) {
            result.addAll(q.shapes());
        }
        for (ArrayList<Shape> s : shapeMap.values()) {
            result.addAll(s);
        }
        return result;
    }

    public boolean intersect(Shape s) {
        return box.intersect(s.box());
    }

    public void add(Shape shape, int push) {
        if (!intersect(shape)) {
            box = Box.bunion(box, shape.box());
            insert(shape, push);
        } else {
            addWithoutCheck(shape, push);
        }
    }

    public void pop(int push) {
        if (shapeMap.containsKey(push)) {
            shapeMap.remove(push);
        }
        for (Quad quad : quads) {
            quad.pop(push);
        }
    }

    public void addWithoutCheck(Shape shape, int push) {
        if (quads.size() == 0) {
            insert(shape, push);
        } else {
            dispatch(shape, push);
        }
    }

    public void dispatch(Shape shape, int push) {
        for (Quad q : quads) {
            if (q.intersect(shape)) {
                q.addWithoutCheck(shape, push);
            }
        }
    }

    public int nshapes() {
        int result = 0;
        for (ArrayList<Shape> s : shapeMap.values()) {
            result += s.size();
        }
        return result;
    }

    public ArrayList<Shape> ownShapes() {
        ArrayList<Shape> result = new ArrayList<Shape>();
        for (ArrayList<Shape> shapes : shapeMap.values()) {
            result.addAll(shapes);
        }
        return result;
    }

    public void insert(Shape shape, int push) {
        if (!shapeMap.containsKey(push)) {
            shapeMap.put(push, new ArrayList<Shape>());
        }
        for (Shape s : ownShapes()) {
            if (Shape.intersect(shape, s)) {
                maxShapeNumber += 1;
            }
        }

        shapeMap.get(push).add(shape);
        if (nshapes() > maxShapeNumber) {
            split();
        }
    }

    public void split() {
        Box[] bs = box.split();
        for (Box subbox : bs) {
            quads.add(new Quad(subbox));
        }

        for (int push : shapeMap.keySet()) {
            for (Shape shape : shapeMap.get(push)) {
                dispatch(shape, push);
            }
        }

        shapeMap.clear();
    }

    public ArrayList<Shape> mayIntersect(Shape shape) {
        ArrayList<Shape> result = new ArrayList<Shape>();
        if (intersect(shape)) {
            if (quads.size() != 0) {
                for (Quad subq : quads) {
                    if (subq.intersect(shape)) {
                        ArrayList<Shape> res = subq.mayIntersect(shape);
                        for (Shape xs : res) {
                            result.add(xs);
                        }
                    }
                }
            } else {
                for (int p : shapeMap.keySet()) {
                    for (Shape s : shapeMap.get(p)) {
                        result.add(s);
                    }
                }
            }
        }
        return result;
    }

    public Box bbox() {
        return box;
    }
}
