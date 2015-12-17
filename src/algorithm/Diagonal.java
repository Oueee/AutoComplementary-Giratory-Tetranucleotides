package algorithm;

import java.util.ArrayList;

/**
 * Created by rinku on 12/16/15.
 */
public class Diagonal {
    static private int nbObject = 0;
    private int id;
    private int[] results;
    private ArrayList<Case> cases;

    public Diagonal(int[] results) {
        id = nbObject++;
        cases = new ArrayList<Case>();
        this.results = results;
    }

    public void add(Case c) { cases.add(c); }

    public Diagonal compute(int limit) {
        Diagonal result = new Diagonal(results);
        Case newCase;
        int size = cases.size();
        int idToCalculate = id + 2;

        newCase = cases.get(0).addEnsemble(Algorithm_tree.dicoS12, Case.TypeCase.ABorder);
        int number = newCase.count();

        results[idToCalculate] += number;
        result.add(newCase);
        for (int i = 0; i < size && idToCalculate + i + 1 <= limit; i++) {
            newCase = cases.get(i).addEnsemble(Algorithm_tree.dicoS114, Case.TypeCase.BBorder);
            results[idToCalculate + i + 1] += newCase.count();
            result.add(newCase);
        }


        /*r


        if(idToCalculate + size + 1 <= limit) {
            if(idToCalculate + size + 1 == 6)
                System.out.println(newCase.count());
            newCase = cases.get(size-1).addEnsemble(Algorithm_tree.dicoS114, Case.TypeCase.BBorder);
            results[idToCalculate + size + 1] += newCase.count();
            result.add(newCase);
        }*/

        return result;
    }
}
