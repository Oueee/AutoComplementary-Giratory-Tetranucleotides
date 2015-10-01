package algorithm;

import ensemble.Ensemble;
import graph.Graph;
import nucleotide.Tetranucleotide;

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
	public static void main(String[] args) {
		Tetranucleotide a = new Tetranucleotide("ACGT");
		
		Graph g = new Graph(new Ensemble(a));
	}
}
