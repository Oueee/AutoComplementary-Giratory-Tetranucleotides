package core;

import algorithm.Algorithm;
import ensemble.Ensemble;
import graph.Graph;
import nucleotide.Nnucleotide;
import nucleotide.Tetranucleotide;

import java.util.ArrayList;

/**
 * Created by rinku on 10/20/15.
 */
public class Main {
    public static ArrayList<Ensemble> s12;
    public static ArrayList<Ensemble> s114;
    public static ArrayList<Ensemble> ensembles;

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
            if( ! ensemble.isCircular() ){
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
                    if(!compIsIn)
                        s114.add(ensemble);
                }

            }
        }
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
    }

    public static void main(String[] args){
        ensemblesInit();
        someChecks();

        Algorithm algorithmBase = new Algorithm(s12, s114);
        algorithmBase.run();
    }
}