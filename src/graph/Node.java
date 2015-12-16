package graph;

import nucleotide.Tetranucleotide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Callable;

public class Node {
    Tetranucleotide tetra;
    ArrayList<Node> leftSon;
    ArrayList<Node> rightSon;
    Node parent;

    public Node(Node parent, Tetranucleotide tetra) {
        this.tetra = tetra;
        leftSon = new ArrayList<>();
        rightSon = new ArrayList<>();
        this.parent = parent;
    }

    public Node(Tetranucleotide tetra) {
        this.tetra = tetra;
        leftSon = new ArrayList<>();
        rightSon = new ArrayList<>();
        this.parent = null;
    }

    public Tetranucleotide getTetra() {
        return tetra;
    }

    public Node getParent() {
        return parent;
    }

    public void addLeft(Tetranucleotide t) {
        leftSon.add(new Node(this, t));
    }

    public void addRight(Tetranucleotide t) {
        rightSon.add(new Node(this, t));
    }

    public void addLeft(Tetranucleotide t, Node n) {
        leftSon.add(new Node(n, t));
    }

    public void addRight(Tetranucleotide t, Node n) {
        rightSon.add(new Node(n, t));
    }

    public ArrayList<Node> getLeftSon() { return leftSon; }

    public ArrayList<Node> getRightSon() { return rightSon; }
}

