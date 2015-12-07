package graph;

import java.util.ArrayList;
import ensemble.AbstractEnsemble;

/**
 * Created by rinku on 12/7/15.
 * It's an object to keep in memory the results needed to calculate other one.
 * It's just a wrapper of the ArrayList aha.
 *
 */

public class BufferResults {
    private ArrayList<AbstractEnsemble> buffer;

    public BufferResults() {
        buffer = new ArrayList<AbstractEnsemble>();
        append(new AbstractEnsemble());
    }

    public void append(AbstractEnsemble e) {
        buffer.add(e);
    }

    public AbstractEnsemble get(int index) {
        return buffer.get(index);
    }

    public void set(int index, AbstractEnsemble e) {
        buffer.set(index, e);
    }

    public int size() {
        return buffer.size();
    }
}
