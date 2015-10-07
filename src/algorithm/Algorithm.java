package algorithm;

import java.util.ArrayList;

import ensemble.Ensemble;
import graph.Graph;
import nucleotide.Nnucleotide;
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
	public static void main(String[] args){
		Nnucleotide[] nucleotides = new Nnucleotide[]{new Nnucleotide("A"),
				new Nnucleotide("C"),
				new Nnucleotide("G"),
				new Nnucleotide("T")};
		ArrayList<Ensemble> ensembles = new ArrayList<Ensemble>();
		ArrayList<Ensemble> s1 = new ArrayList<Ensemble>();
		ArrayList<Ensemble> s2 = new ArrayList<Ensemble>();
		
		ArrayList<Tetranucleotide> tetraList = new ArrayList<Tetranucleotide>();
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
			new Graph(ensemble);
			if( ! ensemble.isCircular() ){
				if(ensemble.isAutocomplementary())
					s1.add(ensemble);
				else
					s2.add(ensemble);
			}
		}
		
		
		
	}
}