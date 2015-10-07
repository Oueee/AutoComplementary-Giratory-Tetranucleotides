package nucleotide;

import java.util.ArrayList;

/**
 * This class represents a tetranucleotide and extends Nnucleotide.
 * It is the only type of n-nucleotide included in the "S"-Ensembles and
 * it can be {@link #split()} to give a {@link Graph} every combination of {@link Nnucleotide} it needs to compute.
 * @author Alexandre
 *
 */
public class Tetranucleotide extends Nnucleotide {
	String complementary;
	
	/**
	 * Creates a Tetranucleotide as if it where a regular {@link Nnucleotide}.<br>
	 * It checks if it has the right amount of nucleotides and crashes the program if not (you might want to remove that part, though).
	 * @param tetra The letters to assign.
	 */
	public Tetranucleotide(String tetra){
		super(tetra);
		if(nbNucleotids != 4){
			System.err.println("Error : Tetranucleotid "+tetra+ " has less than 4 nucleotids.");
			System.exit(-1);
		}
		this.complementary = "";
	}
	
	/**
	 * Splits the tetranucleotide in 3 pairs of n-nucleotides.
	 * <p>For example, ACGT becomes A-CGT, AC-GT, ACG-T.
	 * @return The array containing three Nnucleotide[2] pairs.
	 */
	public ArrayList<Nnucleotide[]> split(){
		ArrayList<Nnucleotide[]> splitList = new ArrayList<>();
		for(int i = 1 ; i<4 ; i++){
			Nnucleotide[] splited = new Nnucleotide[2];
			splited[0] = new Nnucleotide(this.nucleotids.substring(0, i));
			splited[1] = new Nnucleotide(this.nucleotids.substring(i, 4));
			System.out.println(splited[0] + "-" + splited[1]);
			splitList.add(splited);
		}
		
		return splitList;
	}
	
	public String getComplementary(){
		return super.nComplementary(this.nucleotids.charAt(0)) + nComplementary(this.nucleotids.charAt(1))
			+ nComplementary(this.nucleotids.charAt(2)) + nComplementary(this.nucleotids.charAt(3));
	}
}