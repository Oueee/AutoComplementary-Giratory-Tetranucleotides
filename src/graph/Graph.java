package graph;

import java.util.ArrayList;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

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
    private DirectedGraph<String, DefaultEdge> graph;
    private ArrayList<String> historyVertex;
    private ArrayList<DefaultEdge> historyEdge;

    /**
     * Initializes the graph using the Tetranucleotides contained in <b>ensemble</b>
     * and making every possible arrangement out of them.
     * @param ensemble The ensemble containing every Tetranucleotide to handle in the graph.
     */
    public Graph(Ensemble ensemble){
        this.graph = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        historyEdge = new ArrayList<>();
        historyVertex = new ArrayList<>();

        for(Tetranucleotide tetra : ensemble.getTetranucleotids())
            this.addTetranucleotide(tetra);

        ensemble.setCircular(isCircular());
    }

    public Graph(){
        this.graph = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        historyEdge = new ArrayList<>();
        historyVertex = new ArrayList<>();
    }

    /**
     * This will be useful when we keep the existing graphs and test if it
     * is still circular with one more Tetranucleotide. If we can do that... xp
     */
    public void addVertex(){

        //TODO Add a vertex and check if the graph still doesn't find a circle
    }

    public void reset() {
        for(String v : historyVertex)
            this.graph.removeVertex(v);

        for(DefaultEdge e : historyEdge)
            this.graph.removeEdge(e);

        historyEdge = new ArrayList<>();
        historyVertex = new ArrayList<>();
    }

    public void addWithHistory(Tetranucleotide tetra){
        //ACGT => A-CGT AC-GT ACG-T
        String n1;
        String n2;

        for(String[] split : tetra.split()){
            n1 = split[0];
            n2 = split[1];
            if(! graph.containsVertex(n1)) {
                historyVertex.add(n1);
                graph.addVertex(n1);
            }
            if(! graph.containsVertex(n2)) {
                historyVertex.add(n2);
                graph.addVertex(n2);
            }

            if (!graph.containsEdge(n1, n2))
                historyEdge.add(graph.addEdge(split[0], split[1]));
        }
    }

    /**
     * Builds all 6 graphs for one tetranucleotide.
     * <p>For example, ACGT will give A, CGT, AC, GT, ACG and T.<br>
     * The edges would be A-CGT, AC-GT and ACG-T.
     * </p>
     * @param tetra The tetranucleotide that will be split in 6 n-nucleotides
     */
    public void addTetranucleotide(Tetranucleotide tetra){
        //ACGT => A-CGT AC-GT ACG-T
        for(String[] split : tetra.split()){
            if(! graph.containsVertex(split[0]))
                graph.addVertex(split[0]);

            if(! graph.containsVertex(split[1]))
                graph.addVertex(split[1]);
            if (!graph.containsEdge(split[0], split[1]))
                graph.addEdge(split[0], split[1]);
        }
    }

    public void printGraph(){
        Set<String> vertexSet = graph.vertexSet();

        for(String vertex : vertexSet){
            Set<DefaultEdge> outgoing = graph.outgoingEdgesOf(vertex);
            if(outgoing.size() > 0) {
                System.out.print(vertex+"->");

                for(DefaultEdge edge : outgoing){
                    System.out.print(graph.getEdgeTarget(edge));
                    System.out.print(" ");
                }
                System.out.println("");
            }
        }
    }

    public static Graph createGraph(Node n) {
        Graph result = new Graph();
        Tetranucleotide t;
        while(n != null) {
            t = n.getTetra();
            result.addTetranucleotide(t);

            //If the tetra isn't auto complementary, we add the complementary to the graph
            if(!t.isAutoComplementary())
                result.addTetranucleotide(t.getTetraComplementary());

            n = n.getParent();
        }

        return result;
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
    public boolean isCircular(){
        CycleDetector<String, DefaultEdge> cd = new CycleDetector<>(this.graph);

        return !cd.detectCycles();
    }
}