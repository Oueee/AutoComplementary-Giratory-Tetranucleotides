package nucleotide;

/**
 * This class represents the generic n-nucleotides.<br>
 * It is used for the Graph to contain the {@link Tetranucleotides} used in {@link Ensemble}
 * and to make them split ({@link Tetranucleotide#split()}) in smaller n-nucleotides.
 * @author Alexandre
 *
 */
public class Nnucleotide {
	protected int nbNucleotids;
	protected String nucleotids;
	
	/**
	 * Creates a n-nucleotide.
	 * @param nucleotids The nucleotides
	 */
	public Nnucleotide(String nucleotids){
		this.nucleotids = nucleotids;
		this.nbNucleotids = nucleotids.length();
	}
	
	/**
	 *  Mandatory constructor for {@link Graph} only.<br>
	 *  Never use this otherwise
	 */
	public Nnucleotide(){}
	
	//Getters...
	public int getNbNucleotids(){return this.nbNucleotids;}
	
	/**
	 * I know this one is the same as {@link #toString()}, but it leave it here
	 * just in case we want to change the String-coding into something else.
	 * @return
	 */
	public String getNucleotids(){return this.nucleotids;}
	
	public String toString(){return this.nucleotids;}
}
