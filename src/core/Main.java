package core;

import algorithm.Algorithm;
import algorithm.Algorithm_tree;
import algorithm.Case;
import ensemble.Ensemble;
import graph.Graph;
import graph.Node;
import nucleotide.Nnucleotide;
import nucleotide.Tetranucleotide;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

/**
 * Created by rinku on 10/20/15.
 */
public class Main {
    public static ArrayList<Ensemble> s12;
    public static ArrayList<Ensemble> s114;
    public static ArrayList<Ensemble> ensembles;

    public static HashMap<Tetranucleotide, Set<Tetranucleotide>> dicoS12;
    public static HashMap<Tetranucleotide, Set<Tetranucleotide>> dicoS114;

    /**
     * Create the ensembles used by the algorithm
     */
    public static void ensemblesInit() {
        Nnucleotide[] nucleotides = new Nnucleotide[]{new Nnucleotide("A"),
                new Nnucleotide("C"),
                new Nnucleotide("G"),
                new Nnucleotide("T")};

        // All the tetranucleotides (256)
        ensembles = new ArrayList<Ensemble>();

        //The 12 auto-complementary tetranucleotides
        s12 = new ArrayList<Ensemble>();

        //The 114 pair-complementary without circle
        s114 = new ArrayList<Ensemble>();

        //ArrayList<Tetranucleotide> tetraList = new ArrayList<Tetranucleotide>();
        for(int a=0 ; a< 4;a++)
            for(int b=0 ; b< 4;b++)
                for(int c=0 ; c< 4;c++)
                    for(int d=0 ; d< 4;d++)
                        ensembles.add(new Ensemble(
                                new Tetranucleotide(
                                        nucleotides[a].getNucleotids() +
                                                nucleotides[b].getNucleotids() +
                                                nucleotides[c].getNucleotids() +
                                                nucleotides[d].getNucleotids())));

        //Setting the starting ensembles S1 and S2
        for(Ensemble ensemble : ensembles){
            new Graph(ensemble); // to check if the ensemble is circular
            if( ensemble.isCircular() ){
                if(ensemble.isAutocomplementary())
                    s12.add(ensemble);
                else {
                    String complementary = ensemble.getFirstTetranucleotid().getComplementary();
                    boolean compIsIn = false;
                    for(Ensemble toTest : s114) {
                        if(toTest.getFirstTetranucleotid().equals(complementary)) {
                            compIsIn = true;
                            break;
                        }
                    }
                    if(!compIsIn) {
                        for(Tetranucleotide t : ensemble.getTetranucleotids())
                            t.setAutoComplementary(false);

                        s114.add(ensemble);
                    }

                }

            }
        }
    }

    public static void init(ArrayList<Ensemble> ens,
                     HashMap<Tetranucleotide, Set<Tetranucleotide>> dico) {
        int size = ens.size();
        for (int i = 0; i < size; i++) {
            Set<Tetranucleotide> dep = new HashSet<Tetranucleotide>();
            for (int j = i+1; j < size; j++)
                dep.add(ens.get(j).getFirstTetranucleotid());

            dico.put(ens.get(i).getFirstTetranucleotid(), dep);
        }
    }

    public static void nodesInit() {
        dicoS12 = new HashMap<Tetranucleotide, Set<Tetranucleotide>>();
        dicoS114 = new HashMap<Tetranucleotide, Set<Tetranucleotide>>();

        init(s12, dicoS12);
        init(s114, dicoS114);
    }

    /**
     * Very bad way to do unit testing...
     */
    public static void someChecks() {
        System.out.println("<- Must have a warning ->");
        System.out.flush();
        Ensemble test = new Ensemble(ensembles.get(10).getFirstTetranucleotid());
        test.addTetranucleotid(ensembles.get(10).getFirstTetranucleotid());

        // Check if the ensembles are well formed
        if(ensembles.size() != 256) {
            System.err.println("There isn't the good amount of tetranucleotides (" + ensembles.size() + "!=256)");
            System.exit(1);
        }

        if(s12.size() != 12) {
            System.err.println("There isn't the good amount of auto-complementary tetranucleotides (" + s12.size() + "!=12)");
            System.exit(1);
        }

        if(s114.size() != 114) {
            System.err.println("There isn't the good amount of pair-complementary tetranucleotides (" + s114.size() + "!=114)");
            System.exit(1);
        }

        Tetranucleotide ft = new Tetranucleotide("AAAA");
        Tetranucleotide st = new Tetranucleotide("AAAB");
        Tetranucleotide ft2 = new Tetranucleotide("AAAA");

        if(ft.comp(st) >= 0 || st.comp(ft) <= 0 || ft.comp(ft2) != 0) {
            System.err.println("The comparison between two tetranucleotides isn't good");
            System.exit(1);
        }

        Graph g = new Graph();
        g.addTetranucleotide(new Tetranucleotide("ATAT"));

        if(g.isCircular()) {
            System.err.println("Didn't detect a circle of one (AT<->AT)");
            System.exit(1);
        }

        g = new Graph();
        g.addTetranucleotide(new Tetranucleotide("ACGT"));
        g.addTetranucleotide(new Tetranucleotide("CGTA"));

        if(g.isCircular()) {
            System.err.println("Didn't detect a circle of two (A<->CGT)");
            System.exit(1);
        }

        Node a1 = new Node(new Tetranucleotide("ACGT"));
        Node a2 = new Node(new Tetranucleotide("ACCC"));
        Node b1 = new Node(new Tetranucleotide("CGTA"));
        Node b2 = new Node(new Tetranucleotide("GTTT"));
        graph.Ensemble a = new graph.Ensemble();
        a.add(a1);
        a.add(a2);

        graph.Ensemble b = new graph.Ensemble();
        b.add(b1);
        b.add(b2);

        Node root = new Node(new Tetranucleotide("AAAA"));
        root.addA(new Tetranucleotide("ACGT"), null);
        root.addA(new Tetranucleotide("ACCC"), null);
        root.addB(new Tetranucleotide("CGTA"), null);
        root.addB(new Tetranucleotide("GTTT"), null);

    }

    public static void main(String[] args){
        ensemblesInit();
        nodesInit();
        //someChecks();

        Algorithm_tree algo = new Algorithm_tree(dicoS12, dicoS114);
        Gui gui = new Gui();
        gui.initialize(algo);
    }
}
