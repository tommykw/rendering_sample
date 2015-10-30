package tokyo.tommy_kw.renderingsample.bao;

import java.util.ArrayList;

import tokyo.tommy_kw.renderingsample.geometry.Shape;
import tokyo.tommy_kw.renderingsample.quadtree.QuadTree;

/**
 * Created by tommy on 15/10/29.
 */
public class CirclePacking {
    private Stack stack;
    private int lastIndex;
    private QuadTree quadTree;
    private Pattern patter;
    private double side;

    public CirclePacking(ArrayList<Shape> shapes,
                        ArrayList<Node> nodes,
                        Pattern pattern,
                        double side,
                        QuadTree quadTree) {

        stack = new Stack(nodes);


    }
}
