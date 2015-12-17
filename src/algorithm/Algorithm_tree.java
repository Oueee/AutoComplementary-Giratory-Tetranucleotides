package algorithm;

import core.Main;
import graph.BufferResults;
import graph.Ensemble;
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

    public void run() {
        Node root = new Node(new Tetranucleotide("AAAA"));
        Node cursor = root;

        for(Tetranucleotide key : this.dicoS12.keySet())
            root.addA(key, null);

        Graph g;
        for(Tetranucleotide key : this.dicoS114.keySet()) {
            g = Graph.createGraph(new Node(null, key));
            if(g.isCircular())
                root.addB(key, null);
        }

        Case caseRoot = new Case(root, Case.TypeCase.ABBorder);
        Case caseA = new Case(root.getASon(), Case.TypeCase.ABorder);
        Case caseB = new Case(root.getBSon(), Case.TypeCase.BBorder);

        int result[] = new int[61];
        for (int i = 0; i < 61; i++)
            result[i] = 0;

        result[1] = caseA.count();
        result[2] = caseB.count();

        Diagonal first = new Diagonal(result);
        first.add(caseRoot);

        Diagonal second = new Diagonal(result);
        second.add(caseA);
        second.add(caseB);

        Diagonal temp;
        int limit = 6;
        for (int i = 1; i < limit; i++) {
            temp = first.compute(limit);
            first = second;
            second = temp;
        }

        for (int i = 0; i <= limit; i++)
            System.out.println(i + " : " + result[i]);
    }
}