package tokyo.tommy_kw.renderingsample.bao;

import android.support.v4.util.Pair;

import java.util.ArrayList;

/**
 * Created by tommy on 15/10/29.
 */
public class Stack {
    private ArrayList<Node> nodes;
    private Node last1Node;
    private Node last2Node;
    private Node last3Node;
    private Node otherNode;
    private double prevDirection;

    public Stack(ArrayList<Node> nodes) {
        this.nodes = nodes;
        last1Node = nodes.get(nodes.size() - 1);
        last2Node = nodes.get(nodes.size() - 2);
        last3Node = null;
        otherNode = nodes.get(nodes.size() - 2);
        prevDirection = -1.0;
    }

    public void set(ArrayList<Node> nodes, int idx) {
        last1Node = prevDirection > 0.0 ? nodes.get(idx) : nodes.get(idx);
        last2Node = prevDirection > 0.0 ? nodes.get(idx - 1) : nodes.get(idx + 1);
        otherNode = last2Node;
        last3Node = null;
    }

    public int lastIndex() {
        return nodes.size() - 1;
    }

    public int stack(Node newNode, Node currentNode) {
        nodes.add(newNode);
        last3Node = last2Node;
        last2Node = last1Node;
        last1Node = newNode;
        currentNode = newNode;
        return lastIndex();
    }

    public ArrayList<Node> lastNodes() {
        ArrayList<Node> result = new ArrayList<Node>();
        result.add(last1Node);
        result.add(last2Node);
        result.add(last3Node);
        return result;
    }

    public ArrayList<Node> lastSeed() {
        ArrayList<Node> result = new ArrayList<Node>();
        result.add(last1Node);
        result.add(otherNode);
        return result;
    }

    public ArrayList<Node> excludedNodes(Node otherNode) {
        ArrayList<Node> result = new ArrayList<Node>();
        result.addAll(lastNodes());
        result.add(otherNode);
        return result;
    }

    public Pair<Node, Integer> findLastFreeNode(ArrayList<Node> nodes, int lastIndex) {
        Node freeNode = nodes.get(lastIndex);
        return null;
    }

     public int rewind2FreeNode(int lastIndex) {
         Pair<Node, Integer> idx = findLastFreeNode(nodes, lastIndex);
         int newLastIndex = idx.second;
         set(nodes, newLastIndex);
         return newLastIndex;
     }

    public ArrayList<Node> nodes() {
        return nodes;
    }

    public int newIndex() {
        return nodes.size();
    }

    public Node node(int index) {
        for (Node node : nodes) {
            if (node.index() == index) {
                return node;
            }
        }

        return null;
    }

    public Node next2Node(Node node) {
        int nodeIndex = prevDirection > 0.0 ? node.index() + 1 : node.index() - 1;
        return node(nodeIndex);
    }

    public void switchStack() {
        last1Node = last1Node;
        otherNode = last2Node;
        last2Node = null;
        last3Node = null;
        prevDirection = -prevDirection;
    }


}
