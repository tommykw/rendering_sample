package tokyo.tommy_kw.renderingsample.bao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        Circle cirlce = Circle.circle2tangentout(node1, node2, newr, side);
        if (cirlce != null && !quadTree.isColliding(cirlce)) {
            Node node = new Node(this, cirlce, node1.colorIndex() + 1, index + 1);
            return node;
        }
        return null;
    }

    public static ArrayList<Node> findNode(QuadTree tree, Node lastNode, ArrayList<Node> excludedNodes, double newr) {
        Circle circle = lastNode.scale((lastNode.r() + newr * 2.1) / lastNode.r());
        ArrayList<Shape> scollidings = tree.collidings(circle);
        ArrayList<Node> ncollidings = new ArrayList<Node>();
        for (Shape s: scollidings) {
            if (s != null && s instanceof  Node) {
                Node ncoll = (Node)s;
                if (ncoll.index() >= 0) {
                    ncollidings.add(ncoll);
                }
            }
        }
        ncollidings.removeAll(excludedNodes);
        Collections.sort(ncollidings, new Comparator<Node>() {
            @Override
            public int compare(Node lhs, Node rhs) {
                return (new Integer(lhs.index())).compareTo(new Integer(rhs.index()));
            }
        });
        return ncollidings;
    }

    public void iter() {
        iter(1);
    }

    public void iter(int xiter) {
        for (int iiter = 0; iiter < xiter; xiter++) {
            if (xiter % 1000 == 0) {
            }
            ArrayList<Node> lastSeed = stack.lastSeed();
            Node lastNode = lastSeed.get(0);
            Node otherNode = lastSeed.get(1);
            double newr = pattern.next().radius();

            Node newNode;
            newNode = computeNextNode(quadTree, lastNode, otherNode, newr, stack.newIndex(), side);
            if (newNode == null) {
                otherNode = stack.next2Node(otherNode);
                newNode = computeNextNode(quadTree, lastNode, otherNode, newr, stack.newIndex(), side);

                if (newNode == null) {
                    ArrayList<Node> collidings = CirclePacking.findNode(quadTree, lastNode, stack.excludedNodes(otherNode), newr);
                    for (Node n : collidings) {
                        otherNode = n;
                        newNode = computeNextNode(quadTree, lastNode, n, newr, stack.newIndex(), side);
                        if (newNode != null) {
                            break;
                        }
                    }
                }
            }

            if (newNode == null) {
                lastNode.notFound(true);
                lastIndex = stack.rewind2FreeNode(lastIndex);
            } else {
                otherNode.retouch();
                quadTree.add(newNode);
                pattern.draw(newNode, newNode.colorIndex());
                lastIndex = stack.stack(newNode, otherNode);
            }

            if (lastIndex < 1) {
                break;
            }
        }
    }
}
