package graph;

import graph.Node;

import java.util.ArrayList;

/**
 * Created by rinku on 12/16/15.
 */
public class Ensemble {
    ArrayList<Node> nodes;

    public Ensemble() { nodes = new ArrayList<Node>(); }

    public void add(Node n) { nodes.add(n); }

    public ArrayList<Node> getNodes() { return nodes; }

    public int size() { return nodes.size(); }

    public boolean isEmpty() { return nodes.isEmpty(); }
}

