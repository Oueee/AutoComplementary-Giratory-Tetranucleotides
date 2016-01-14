package algorithm;

import nucleotide.Tetranucleotide;

import javax.swing.*;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by rinku on 1/14/16.
 */
public class CaseWrapper extends SwingWorker<Void, Void> {
    HashMap<Tetranucleotide, Set<Tetranucleotide>> dico;
    Case.TypeCase t;
    Case result;
    Case c;

    public CaseWrapper(Case c, HashMap<Tetranucleotide, Set<Tetranucleotide>> dico, Case.TypeCase t) {
        this.dico = dico;
        this.t = t;
        this.c = c;
    }

    public Void doInBackground() {
        result = c.addEnsemble(dico, t);
        return null;
    }

    public Case getResult() {
        return result;
    }
}
