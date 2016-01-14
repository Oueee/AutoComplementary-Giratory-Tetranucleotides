package algorithm;

import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by rinku on 12/16/15.
 */
public class Diagonal {
    static private int nbObject = 3;
    public int id;
    public long nbCIrcular;
    public ArrayList<Case> cases;

    public Diagonal() {
        id = nbObject++;
        cases = new ArrayList<Case>();
        nbCIrcular = 0;
    }

    public void add(Case c) { cases.add(c); }

    public Diagonal compute() {
        Diagonal nextBuffer = new Diagonal();
        Case newCase;
        CaseWrapper caseWrapper;
        ArrayList<CaseWrapper> casesWrappers = new ArrayList<>();

        int cores = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor es = (ThreadPoolExecutor) Executors.newFixedThreadPool(cores);

        int size = cases.size();

        if(id % 2 == 0) {
            caseWrapper = new CaseWrapper(cases.get(0), Algorithm_tree.dicoS114, Case.TypeCase.BBorder);
            casesWrappers.add(caseWrapper);
            es.execute(caseWrapper);
        }
        else
            nextBuffer.add(cases.get(0));
        
        for (int i = (id+1) % 2; i < size; i++) {
            caseWrapper = new CaseWrapper(cases.get(i), Algorithm_tree.dicoS12, Case.TypeCase.ABorder);
            casesWrappers.add(caseWrapper);
            es.execute(caseWrapper);
        }

        es.shutdown();

        try{
            while(!es.awaitTermination(100, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            System.err.println("Interruption error");
            System.exit(1);
        }

        for(CaseWrapper c : casesWrappers) {
            newCase = c.getResult();
            nbCIrcular += newCase.count();
            nextBuffer.add(newCase);
        }

        return nextBuffer;
    }
}
