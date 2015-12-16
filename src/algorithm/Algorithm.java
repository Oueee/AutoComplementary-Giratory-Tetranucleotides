package algorithm;

import ensemble.AbstractEnsemble;
import ensemble.Ensemble;
import graph.BufferResults;

import java.util.ArrayList;

/**
 * This class will contain the skeleton of the algorithm.
 * @author Alexandre
 *
 */
public class Algorithm {
    /**
     * I assume the algorithm is the starting point, but I might be wrong.<br>
     * Feel free to change this if you have a better idea, there's not much anyway.
     * @param args Thank you Java for letting us drop this argument.
     */

    public ArrayList<Ensemble> s12;
    public ArrayList<Ensemble> s114;
    private BufferResults buffer;
    private int results[];

    public Algorithm(ArrayList<Ensemble> s12, ArrayList<Ensemble> s114) {
        this.s12 = s12;
        this.s114 = s114;
        results = new int[60];

        for (int i = 0; i < 60; i++)
            results[i] = 0;

        this.buffer = new BufferResults();
    }

    /**
     * run the algorithm
     */
    public void run() {
        AbstractEnsemble A = new AbstractEnsemble();
        AbstractEnsemble B = new AbstractEnsemble();

/*        for(int i = 0 ; i < 60; i++){
            buffer.append(buffer.get(buffer.size() - 1).union(A));
            for(int j = 1; j<buffer.size()-1;j++)
                buffer.set(j,buffer.get(j).union(buffer.get(j-1)));
            buffer.set(0,buffer.get(0).union(B));

            for(int m = 0 ;m < buffer.size();m++){
                if(i+m < 60)
                    results[i+m] += buffer.get(buffer.size() -m -1).getResult();
            }



        }*/
    }

}