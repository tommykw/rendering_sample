package tokyo.tommy_kw.renderingsample.bao;

import java.util.ArrayList;

import tokyo.tommy_kw.renderingsample.geometry.Circle;
import tokyo.tommy_kw.renderingsample.geometry.Shape;
import tokyo.tommy_kw.renderingsample.quadtree.QuadTree;

/**
 * Created by tommy on 15/10/29.
 */
public class CirclePacking {
    private Stack stack;
    private int lastIndex;
    private QuadTree quadTree;
    private Pattern pattern;
    private double side;

    public CirclePacking(ArrayList<Shape> shapes,
                        ArrayList<Node> nodes,
                        Pattern pattern,
                        double side,
                        QuadTree quadTree) {

        stack = new Stack(nodes);
        lastIndex = stack.lastIndex();
        quadTree = quadTree == null ? new QuadTree() : quadTree;
        this.pattern = pattern;
        this.side = side;

        if (shapes != null) {
            quadTree.adds(shapes);
        }
        for (Node n : nodes) {
            n.packing(this);
        }
    }
    public Node computeNextNode(QuadTree tree, Node node1, Node node2, double newr, int index, double side) {
        Circle cirlce;

    }



}
