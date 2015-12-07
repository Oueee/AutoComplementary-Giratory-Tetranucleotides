package ensemble;

import java.util.ArrayList;

import nucleotide.Tetranucleotide;
import utils.StringFacility;

/**
 * This class keeps the ensemble of {@link Tetranucleotide} we need to compute for one {@link Graph}.
 * @author Alexandre
 */


public class Ensemble{
	private ArrayList<Tetranucleotide> ensemble;
	private boolean circular = false;

	public Ensemble(){
		this.ensemble = new ArrayList<Tetranucleotide>();
	}

	public Ensemble(Tetranucleotide tetra){
		this.ensemble = new ArrayList<Tetranucleotide>();
		this.ensemble.add(tetra);
	}
	
	/**
	 * Adds a {@link Tetranucleotide} to the ensemble if it is not already in it.
	 * @param toAdd The {@link Tetranucleotide} to add to the ensemble.
	 */
	public void addTetranucleotid(Tetranucleotide toAdd){
		if(!(this.ensemble.contains(toAdd)))
			this.ensemble.add(toAdd);
		else
			//Doesn't actually need to say this, delete it if you want.
			System.err.println("Warning : the Tetranucleotid " + toAdd.toString() + " already exists.");
	}
	
	/**
	 * This serves only for the beginning's {@link Graph}s, but might be scrapped for a more generic version.<br>
	 * For now, this gives the first {@link Tetranucleotide} of the ensemble, assuming there is only this one.
	 * @return The first {@link Tetranucleotide} of the ensemble (assuming it's the only one).
	 */
	public Tetranucleotide getFirstTetranucleotid(){
		return this.getTetranucleotid(0);
	}
	public Tetranucleotide getTetranucleotid(int index){ return this.ensemble.get(index); }

	public void setCircular(boolean state){
		this.circular = state;
	}
	
	//Getters...
	public ArrayList<Tetranucleotide> getTetranucleotids(){
		return ensemble;
	}
	public int nbTetranucleotids(){
		return ensemble.size();
	}
	public boolean isCircular(){return this.circular;}

	/**
	 * So ugly aha, but just used at the beginning so it's kind of okay
	 * Check if the ensemble is auto-complementary. Use to get the ensembles
	 * at the beginning of the program.
	 * @return true if the ensemble is auto-complementary, false otherwise
	 */
	public boolean isAutocomplementary(){
		boolean result = true;

		// Check for each tetra if there is the complementary in the ensemble
		for(Tetranucleotide t : ensemble) {
			String complementary = t.getComplementary();
			boolean hasComplementary = false;
			for(Tetranucleotide toTest : ensemble) {
				if(toTest.equals(complementary)) {
					hasComplementary = true;
					break;
				}
			}

			if(!hasComplementary) {
				result = false;
				break;
			}
		}
		return result;
	}

	public String toString() {
		return "{" + StringFacility.join(ensemble.toArray(), ", ") + "}";
	}
}