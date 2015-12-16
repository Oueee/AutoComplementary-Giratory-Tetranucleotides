package graph;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import ensemble.AbstractEnsemble;
import nucleotide.Tetranucleotide;

/**
 * Created by rinku on 12/7/15.
 * It's an object to keep in memory the results needed to calculate other one.
 * It's just a wrapper of the ArrayList aha.
 *
 */

public class BufferResults {
    private ArrayList<ArrayList<Node>> buffer;

    public static enum BorderSide {LEFT, RIGHT};

    public BufferResults() {
        buffer = new ArrayList<ArrayList<Node>>();
    }

    public void append(ArrayList<Node> a) {
        buffer.add(a);
    }

    public void concat(BufferResults b2) { buffer.addAll(b2.buffer); }

    public ArrayList<Node> get(int index) {
        return buffer.get(index);
    }

    public int size() {
        return buffer.size();
    }

    private BufferResults combinationInAux(ArrayList<Node> ar) {
        BufferResults result = new BufferResults();

        for(Node n : ar) {
            for(Node fg : n.getLeftSon()) {
                Graph g = Graph.createGraph(fg);

                for(Node fd: n.getRightSon()) {
                    g.addWithHistory(fd.getTetra());

                    if(g.isCircular())
                        fg.addRight(fd.getTetra());

                    g.reset();
                }

                if(!fg.getRightSon().isEmpty())
                    result.append(fg.getRightSon());
            }
        }

        return result;
    }

    public BufferResults combinationIn() {
        BufferResults result = new BufferResults();

        for(ArrayList<Node> ar : buffer)
            result.concat(combinationInAux(ar));

        return result;
    }

    private BufferResults leftBorderCombinationAux(HashMap<Tetranucleotide, Set<Tetranucleotide>> dico,
                                        ArrayList<Node> ar) {

        BufferResults result = new BufferResults();
        Set<Tetranucleotide> grandChildren;

        for(Node n : ar) {
            for(Node fg : n.getLeftSon()) {
                grandChildren = dico.get(fg.getTetra());
                Graph g = Graph.createGraph(fg);

                for(Tetranucleotide grandChild: grandChildren) {
                    g.addWithHistory(grandChild);

                    if(g.isCircular())
                        fg.addLeft(grandChild);

                    g.reset();
                }

                if(!fg.getLeftSon().isEmpty())
                    result.append(fg.getLeftSon());
            }
        }

        return result;
    }

    private BufferResults rightBorderCombinationAux(HashMap<Tetranucleotide, Set<Tetranucleotide>> dico,
                                                    ArrayList<Node> ar) {

        BufferResults result = new BufferResults();
        Set<Tetranucleotide> grandChildren;

        for(Node n : ar) {
            for(Node fd : n.getRightSon()) {
                grandChildren = dico.get(fd.getTetra());
                Graph g = Graph.createGraph(fd);

                for(Tetranucleotide grandChild: grandChildren) {
                    g.addWithHistory(grandChild);

                    if(g.isCircular())
                        fd.addRight(grandChild);

                    g.reset();
                }

                if(!fd.getRightSon().isEmpty())
                    result.append(fd.getRightSon());
            }
        }

        return result;
    }

    public BufferResults borderCombination(HashMap<Tetranucleotide, Set<Tetranucleotide>> dico, BorderSide side) {
        BufferResults result = new BufferResults();

        if(side == BorderSide.LEFT) {
            for(ArrayList<Node> ar : buffer)
                result.concat(leftBorderCombinationAux(dico, ar));
        }
        else {
            for(ArrayList<Node> ar : buffer)
                result.concat(rightBorderCombinationAux(dico, ar));
        }


        return result;
    }

    public String toString() {
        String result = "";

        for(ArrayList<Node> ar : buffer) {
            for(Node n : ar)
                result += (" " + n.getTetra().toString());
        }

        return result;
    }

    public int count() {
        int result = 0;
        for(ArrayList<Node> ar : buffer)
            result += ar.size();

        return result;
    }
}
