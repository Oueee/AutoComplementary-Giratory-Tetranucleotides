package nucleotide;

import java.util.Objects;

/**
 * This class represents the generic n-nucleotides.<br>
 * It is used for the Graph to contain the {@link Tetranucleotides} used in {@link Ensemble}
 * and to make them split ({@link Tetranucleotide#split()}) in smaller n-nucleotides.
 * @author Alexandre
 *
 */
public class Nnucleotide extends Object {
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
    public boolean equals(Nnucleotide n){
        System.out.println(n.nucleotids + " " + nucleotids + " " + n.nucleotids.equals(nucleotids));
        return this.nucleotids.equals(n.nucleotids);
    }
    public boolean equals(String s){return this.nucleotids.equals(s);}
    public String toString(){return this.nucleotids;}
    public String nComplementary(char c){
        switch (c) {
            case 'A' : return "T";
            case 'C' : return "G";
            case 'G' : return "C";
            case 'T' : return "A";
        }
        return null;
    }

    public String getComplementary(){
        String result = "";

        for(int i = this.nbNucleotids-1; i >= 0; i--)
            result += nComplementary(this.nucleotids.charAt(i));

        return result;
    }
}