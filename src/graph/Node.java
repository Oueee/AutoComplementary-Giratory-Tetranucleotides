package graph;

import nucleotide.Tetranucleotide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Callable;

public class Node {
    Tetranucleotide tetra;
    Ensemble ACombination;
    Ensemble BCombination;
    Node parent;

    public Node(Node parent, Tetranucleotide tetra) {
        this.tetra = tetra;
        ACombination = new Ensemble();
        BCombination = new Ensemble();
        this.parent = parent;
    }

    public Node(Tetranucleotide tetra) {
        this.tetra = tetra;
        ACombination = new Ensemble();
        BCombination = new Ensemble();
        this.parent = null;
    }

    public Tetranucleotide getTetra() {
        return tetra;
    }

    public Node getParent() {
        return parent;
    }

    public void addA(Tetranucleotide t) {
        ACombination.add(new Node(this, t));
    }

    public void addB(Tetranucleotide t) {
        BCombination.add(new Node(this, t));
    }

    public void addA(Tetranucleotide t, Node n) {
        ACombination.add(new Node(n, t));
    }

    public void addB(Tetranucleotide t, Node n) {
        BCombination.add(new Node(n, t));
    }

    public Ensemble getASon() { return ACombination; }

    public Ensemble getBSon() { return BCombination; }
}

