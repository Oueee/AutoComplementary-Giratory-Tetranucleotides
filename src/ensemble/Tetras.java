
package ensemble;
/**
 * Created by liuxiaoyi on 15/10/15.
 */
import nucleotide.Tetranucleotide;



import nucleotide.Tetranucleotide;

import java.util.ArrayList;

public class Tetras {
    private ArrayList<String> lst, s256, s240, s228, s114, cs114, s126;

    private ArrayList<String> s16 = new ArrayList<String>() {
        {
            String[] tbS16 = { "AAAA", "ACAC", "AGAG", "ATAT", "CACA", "CCCC",
                    "CGCG", "CTCT", "GAGA", "GCGC", "GGGG", "GTGT", "TATA",
                    "TCTC", "TGTG", "TTTT" };
            for (String s : tbS16) {
                add(s);
            }
        }
    };
    private ArrayList<String> s12 = new ArrayList<String>() {
        {
            String[] tbS12 = { "AATT", "ACGT", "AGCT", "CATG", "CCGG", "CTAG",
                    "GATC", "GGCC", "GTAC", "TCGA", "TGCA", "TTAA" };
            for (String s : tbS12) {
                add(s);
            }
        }
    };

    private ArrayList<String> nucleotides = new ArrayList<String>() {
        {
            add("A");
            add("C");
            add("G");
            add("T");
        }
    };

    public Tetras() {
        lst = new ArrayList<String>();
        s256 = new ArrayList<String>();
        s240 = new ArrayList<String>();
    }

    public ArrayList<String> S256() {
        s256 = new ArrayList<String>();
        for (String s1 : nucleotides) {
            for (String s2 : nucleotides) {
                for (String s3 : nucleotides) {
                    for (String s4 : nucleotides) {
                        s256.add(s1 + s2 + s3 + s4);
                    }
                }
            }
        }
        return s256;
    }

    public ArrayList<String> S240() {
        s240 = new ArrayList<String>();
        s240 = S256();
        for (String s : s16) {
            s240.remove(s);
        }
        return s240;
    }

    public ArrayList<String> S228() {
        s228 = new ArrayList<String>();
        s228 = S240();
        for (String s : s12) {
            s228.remove(s);
        }
        return s228;
    }

    public ArrayList<String> S126() {
        s114 = new ArrayList<String>();
        cs114 = new ArrayList<String>();
        s126 = new ArrayList<String>();
        s114 = S228();
        for (int i = 0; i < s114.size(); i++) {
            String c = new Tetranucleotide(s114.get(i)).getComplementary();
            // System.out.println(i+c+"->"+s114.get(i));
            s114.remove(c);
            cs114.add(c);
        }
        // System.out.println(s114.size());
        // System.out.println(cs114.size());
        s126.addAll(s114);
        s126.addAll(s12);
        return s126;

    }

    public static void main(String[] args) {
        Tetras a = new Tetras();
        // System.out.println(a.s16);
        // System.out.println(a.S256().size());
        // System.out.println(a.S240().size());
        // System.out.println(a.S228().size());

        // System.out.println(a.S126().size());

    }

}