package algorithm;

import graph.BufferResults;
import graph.Graph;
import graph.Node;

import nucleotide.Tetranucleotide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Created by rinku on 12/14/15.
 */
public class Algorithm_tree {
    public static HashMap<Tetranucleotide, Set<Tetranucleotide>> dicoS12;
    public static HashMap<Tetranucleotide, Set<Tetranucleotide>> dicoS114;

    public Algorithm_tree(HashMap dicoS12, HashMap dicoS114) {
        this.dicoS12 = dicoS12;
        this.dicoS114 = dicoS114;
    }
/*
    private Graph createGraph(Node n) {
        Graph result = new Graph();
        while(n != null) {
            result.addTetranucleotide(n.getTetra());
            n = n.getParent();
        }

        return result;
    }

    private void LeftCombination(Node root) throws Exception {
        BorderCombination(new ChildrenGetter() {
                                                public ArrayList<Node> call(Node n) {
                                                    return n.getLeftSon();
                                                }
                                            }, dicoS12, root);
    }

    private void RightCombination(Node root) throws Exception {
        BorderCombination(new ChildrenGetter() {
            public ArrayList<Node> call(Node n) {
                return n.getRightSon();
            }
        }, dicoS114, root);
    }

    private void BorderCombination(ChildrenGetter getChildren,
                                   HashMap<Tetranucleotide, Set<Tetranucleotide>> dico,
                                   Node root) throws Exception {
        Set<Tetranucleotide> grandChildren;
        ArrayList<Node> grandChildrenCircular;
        Graph g;

        for(Node children : getChildren.call(root)) {
            grandChildren = dico.get(children.getTetra());
            grandChildrenCircular = getChildren.call(children);
            g = createGraph(children);

            for(Tetranucleotide grandChild : grandChildren) {
                g.addWithHistory(grandChild);

                if(g.isCircular())
                    grandChildrenCircular.add(new Node(children, grandChild));

                g.reset();
            }
        }
    }

    private void InCombination(Node root) {
        ArrayList<Node> leftSon = root.getLeftSon();
        ArrayList<Node> rightSon = root.getRightSon();
        ArrayList<Node> lRSon;
        ArrayList<Node> rLSon;

        int i = 0, iMax = leftSon.size(),
            j = 0, jMax = rightSon.size(),
        diff;
        Graph g;

        while(i < iMax && j < jMax) {
            diff = leftSon.get(i).getTetra().comp(rightSon.get(j).getTetra());

            if(diff > 0)
                j++;
            else if(diff < 0)
                i++;
            else {
                for(Node lR : leftSon.get(i).getRightSon()) {
                    g = createGraph(lR);
                    for(Node rL : rightSon.get(j).getLeftSon()) {
                        g.addWithHistory(rL.getTetra());

                        //check

                        g.reset();
                    }
                }



                i++;
                j++;
            }
        }
        *//*int size = root;
        //Combination available
        if(leftSon.size() > 0 && rightSon.size() > 0) {
            for (int i = 0; i < ; i++) {

            }
        }*//*
    }*/

    public void run() {
        Node root = new Node(new Tetranucleotide("AAAA"));
        Node cursor = root;

        for(Tetranucleotide key : this.dicoS12.keySet())
            root.addLeft(key, null);

        Graph g;
        for(Tetranucleotide key : this.dicoS114.keySet()) {
            g = Graph.createGraph(new Node(null, key));
            if(g.isCircular())
                root.addRight(key, null);
        }


        ArrayList<Node> rootList = new ArrayList<>();
        rootList.add(root);
        BufferResults rootBuffer = new BufferResults();
        BufferResults A2Buffer;
        rootBuffer.append(rootList);

        A2Buffer = rootBuffer.borderCombination(this.dicoS12, BufferResults.BorderSide.LEFT);
        System.out.println(A2Buffer.count() + root.getRightSon().size());

        /*for (int i = 2; i < 60; i++) {

        }*/
    }
}
