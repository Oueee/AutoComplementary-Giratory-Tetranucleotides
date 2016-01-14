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

        Case caseA = new Case(root.getASon(), Case.TypeCase.ABorder);
        Case caseB = new Case(root.getBSon(), Case.TypeCase.BBorder);
        Case caseA2 = caseA.addEnsemble(Algorithm_tree.dicoS12, Case.TypeCase.ABorder);

        System.out.println(1 + " : " + caseA.count());
        System.out.println(2 + " : " + (caseA2.count() + caseB.count()));
        
        Diagonal buffer = new Diagonal();
        buffer.add(caseB);
        buffer.add(caseA2);

        int limit = 5;
        for (int i = 2; i < limit; i++)
            buffer = buffer.compute();
    }
}