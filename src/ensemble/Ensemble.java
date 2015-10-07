package ensemble;

import java.util.ArrayList;

import nucleotide.Tetranucleotide;

/**
 * This class keeps the ensemble of {@link Tetranucleotide} we need to compute for one {@link Graph}.
 * @author Alexandre
 */
public class Ensemble{
	private ArrayList<Tetranucleotide> ensemble;
	private boolean circular = false;
	private boolean autocomplementary = false;
	
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
		return this.ensemble.get(0);
	}
	
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
	public boolean isAutocomplementary(){return this.autocomplementary;}
}