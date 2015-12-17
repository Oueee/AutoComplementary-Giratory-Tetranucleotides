package algorithm;

import graph.Ensemble;
import graph.Graph;
import graph.Node;
import nucleotide.Tetranucleotide;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by rinku on 12/16/15.
 */
public class Case {
    public static enum TypeCase {ABBorder, ABorder, BBorder, In};

    TypeCase type;
    ArrayList<Ensemble> ensembles;


    public Case(TypeCase type) {
        this.type = type;
        this.ensembles = new ArrayList<Ensemble>();
    }

    public Case(ArrayList<Ensemble> ensembles, TypeCase type) {
        this.type = type;
        this.ensembles = ensembles;
    }

    public Case(Node n, TypeCase type) {
        this.type = type;
        Ensemble e = new Ensemble();
        e.add(n);

        this.ensembles = new ArrayList<Ensemble>();
        this.ensembles.add(e);
    }

    public Case(Ensemble e, TypeCase type) {
        this.type = type;
        this.ensembles = new ArrayList<Ensemble>();
        this.ensembles.add(e);
    }

    public Case addEnsemble(HashMap<Tetranucleotide, Set<Tetranucleotide>> dico, TypeCase t) {
        ArrayList<Ensemble> results = new ArrayList<Ensemble>();

        if(t == TypeCase.ABorder) {
            for(Ensemble e : ensembles)
                results.addAll(addA(dico, e));
        }
        else if(t == TypeCase.BBorder){
            for(Ensemble e : ensembles)
                results.addAll(addB(dico, e));
        }
        else {
            System.err.println("Cannot combinate a middle case with an ensemble");
            System.exit(1);
        }

        return new Case(results, t);
    }


    public int count() {
        int result = 0;
        for(Ensemble e : ensembles)
            result += e.size();

        return result;
    }

    public String toString() {
        String result = "";

        for(Ensemble e : ensembles) {
            result += "\n";
            for(Node n : e.getNodes())
                result += (" " + n.getTetra().toString());
        }

        return result;
    }

    private ArrayList<Ensemble> addA(HashMap<Tetranucleotide, Set<Tetranucleotide>> dico,
                                     Ensemble e) {

        ArrayList<Ensemble> result = new ArrayList<Ensemble>();
        Set<Tetranucleotide> grandChildren;

        for(Node n : e.getNodes()) {
            grandChildren = dico.get(n.getTetra());
            if(grandChildren == null)
                grandChildren = dico.keySet();
            Graph g = Graph.createGraph(n);

            for(Tetranucleotide grandChild: grandChildren) {
                g.addWithHistory(grandChild);

                if(g.isCircular())
                    n.addA(grandChild);

                g.reset();
            }

            if(!n.getASon().isEmpty())
                result.add(n.getASon());
        }

        return result;
    }

    private ArrayList<Ensemble> addB(HashMap<Tetranucleotide, Set<Tetranucleotide>> dico,
                                     Ensemble e) {

        ArrayList<Ensemble> result = new ArrayList<Ensemble>();
        Set<Tetranucleotide> grandChildren;

        for(Node n : e.getNodes()) {
            grandChildren = dico.get(n.getTetra());
            if(grandChildren == null)
                grandChildren = dico.keySet();

            Graph g = Graph.createGraph(n);

            for(Tetranucleotide grandChild: grandChildren) {
                g.addWithHistory(grandChild);

                if(g.isCircular())
                    n.addB(grandChild);

                g.reset();
            }

            if(!n.getBSon().isEmpty())
                result.add(n.getBSon());
        }

        return result;
    }


    /*
     * Make the mix with the top case and the left one and add it to the top one
     * Just keep in the tree {T,A,B} and not {T,B,A}
     * Don't mind for the next calcul because of the case abstraction ;)
     */


}
