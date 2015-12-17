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

    public Case combinate() {
        ArrayList<Ensemble> results = new ArrayList<Ensemble>();

        for(Ensemble ens : ensembles)
            results.addAll(combinateAux(ens));

        return new Case(results, TypeCase.In);
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
            for(Node aChild : n.getASon().getNodes()) {
                grandChildren = dico.get(aChild.getTetra());
                Graph g = Graph.createGraph(aChild);

                for(Tetranucleotide grandChild: grandChildren) {
                    g.addWithHistory(grandChild);

                    if(g.isCircular())
                        aChild.addA(grandChild);

                    g.reset();
                }

                if(!aChild.getASon().isEmpty())
                    result.add(aChild.getASon());
            }
        }

        return result;
    }

    private ArrayList<Ensemble> addB(HashMap<Tetranucleotide, Set<Tetranucleotide>> dico,
                                     Ensemble e) {

        ArrayList<Ensemble> result = new ArrayList<Ensemble>();
        Set<Tetranucleotide> grandChildren;

        for(Node n : e.getNodes()) {
            for(Node bChild : n.getBSon().getNodes()) {
                grandChildren = dico.get(bChild.getTetra());
                Graph g = Graph.createGraph(bChild);

                for(Tetranucleotide grandChild: grandChildren) {
                    g.addWithHistory(grandChild);

                    if(g.isCircular())
                        bChild.addB(grandChild);

                    g.reset();
                }

                if(!bChild.getBSon().isEmpty())
                    result.add(bChild.getBSon());
            }
        }

        return result;
    }


    /*
     * Make the mix with the top case and the left one and add it to the top one
     * Just keep in the tree {T,A,B} and not {T,B,A}
     * Don't mind for the next calcul because of the case abstraction ;)
     */
    private ArrayList<Ensemble> combinateAux(Ensemble ens) {
        ArrayList<Ensemble> result = new ArrayList<Ensemble>();

        for(Node n : ens.getNodes()) {
            for(Node aChild : n.getASon().getNodes()) {
                Graph g = Graph.createGraph(aChild);

                for(Node bChild : n.getBSon().getNodes()) {
                    g.addWithHistory(bChild.getTetra());

                    if(g.isCircular()) {
                        aChild.addB(bChild.getTetra());
                        //bChild.addA(aChild.getTetra());
                    }

                    g.reset();
                }

                if(!aChild.getBSon().isEmpty())
                    result.add(aChild.getBSon());
            }
        }

        return result;
    }

}
