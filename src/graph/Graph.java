package graph;

import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.DefaultEdge;


import ensemble.Ensemble;
import nucleotide.Nnucleotide;
import nucleotide.Tetranucleotide;

/**
 * Contains and treats the graph for a given ensemble.
 * It will build every vertex and edge, then check if there is any cycle.
 * @author Alexandre
 * 
 */
public class Graph {
	/** The graph set of one ensemble, including every n-nucleotide and its edges */
	private UndirectedGraph<Nnucleotide, DefaultEdge> graph;
	
	/**
	 * Initializes the graph using the Tetranucleotides contained in <b>ensemble</b>
	 * and making every possible arrangement out of them.
	 * @param ensemble The ensemble containing every Tetranucleotide to handle in the graph.
	 */
	public Graph(Ensemble ensemble){
		this.graph = new SimpleGraph<Nnucleotide, DefaultEdge>(DefaultEdge.class);
		if(ensemble.nbTetranucleotids() == 1)
			this.buildFirst(ensemble.getFirstTetranucleotid());
		else{
			//TODO Generalize the graph build.
		}
		//checkCircular();
	}
	
	/**
	 * This will be useful when we keep the existing graphs and test if it
	 * is still circular with one more Tetranucleotide.
	 */
	public void addVertex(){
		//TODO Add a vertex and check if the graph still doesn't find a circle
	}
	
	/**
	 * Builds all 6 graphs for one tetranucleotide.
	 * <p>For example, ACGT will give A, CGT, AC, GT, ACG and T.<br>
	 * The edges would be A-CGT, AC-GT and ACG-T.
	 * </p>
	 * @param tetra The tetranucleotide that will be split in 6 n-nucleotides
	 */
	private void buildFirst(Tetranucleotide tetra){
		//ACGT => A-CGT AC-GT ACG-T
		for(Nnucleotide[] split : tetra.split()){
			graph.addVertex(split[0]);
			graph.addVertex(split[1]);
			
			graph.addEdge(split[0], split[1]);
		}
	}
	
	/**
	 * Checks if the graph contains any cycle
	 * <p>For example, A-TAT would not be circular, but AT-AT gives the same when read backwards,
	 * therefore it will return false.<br>
	 * However ACGT never reads the same when read backwards,
	 * therefore it will return true.</p>
	 * The real challenge will be to apply this to everything in the graph.
	 * @return <b>true</b> if the ensemble is circular, <b>false</b> otherwise.
	 */
	public boolean checkCircular(){
		Set<Nnucleotide> vertexSet = graph.vertexSet();
		for(Nnucleotide n : vertexSet){
			Set<DefaultEdge> cibles = graph.edgesOf(n);
			if(cibles != null){
				for(DefaultEdge cible : cibles){
					Nnucleotide a = graph.getEdgeTarget(cible);
					if(a != n){
						if(a.equals(n))
							return true;
						//System.out.println(n.toString() + " est attaché à " + a.toString());
					}
				}
			}
		}
		return false;
	}
}
