package algorithm;

import java.util.ArrayList;

/**
 * Created by rinku on 12/16/15.
 */
public class Diagonal {
    static private int nbObject = 3;
    private int id;
    public ArrayList<Case> cases;

    public Diagonal() {
        id = nbObject++;
        cases = new ArrayList<Case>();
    }

    public void add(Case c) { cases.add(c); }

    public Diagonal compute() {
        Diagonal nextBuffer = new Diagonal();
        Case newCase;
        int size = cases.size();
        int nbCIrcular = 0;

        if(id % 2 == 0) {
            newCase = cases.get(0).addEnsemble(Algorithm_tree.dicoS114, Case.TypeCase.BBorder);
            nbCIrcular += newCase.count();
            nextBuffer.add(newCase);
        }
        else
            nextBuffer.add(cases.get(0));
        
        for (int i = (id+1) % 2; i < size; i++) {
            newCase = cases.get(i).addEnsemble(Algorithm_tree.dicoS12, Case.TypeCase.ABorder);
            nbCIrcular += newCase.count();
            nextBuffer.add(newCase);
        }

        System.out.println(id + " : " + nbCIrcular);

        return nextBuffer;
    }
}
