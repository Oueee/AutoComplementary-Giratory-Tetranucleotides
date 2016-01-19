package algorithm;

import core.Main;
import graph.BufferResults;
import graph.Ensemble;
import graph.Graph;
import graph.Node;

import nucleotide.Tetranucleotide;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


import java.util.Date;
/**
 * Created by rinku on 12/14/15.
 */
public class Algorithm_tree {
    public static HashMap<Tetranucleotide, Set<Tetranucleotide>> dicoS12;
    public static HashMap<Tetranucleotide, Set<Tetranucleotide>> dicoS114;
    private TextArea console;

    public Algorithm_tree(HashMap dicoS12, HashMap dicoS114) {
        this.dicoS12 = dicoS12;
        this.dicoS114 = dicoS114;
    }

    private String getInterval(long d1, long d2) {
        //in milliseconds
        long diff = d2 - d1;
        long diffMilliSeconds = diff % 1000;
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;

        String result = diffHours + ":" + diffMinutes + ":" + diffSeconds + "." + diffMilliSeconds;
        if(diffSeconds <= 999)
            result += "\t";

        return result;
    }

    public void write(String message) {
        console.setText(console.getText() + message + "\n");
        System.out.println(message);
    }

    public void addLine(int id, String time, long result) {
        write(id + "\t" + time + "\t" + result);
    }

    public void run(TextArea console) {
        this.console = console;

        Node root = new Node(new Tetranucleotide("AAAA"));
        long pick;
        String time;

        int cores = Runtime.getRuntime().availableProcessors();
        //System.out.println("Votre machine a " + cores + " cpu(s).");
        //System.out.println("Un même nombre de threads vas être utilisé pour être optimal.\n");
        write("l\ttime\t\tresult");
        write("");

        // Calculate A (l = 1)
        pick = System.currentTimeMillis();
        for(Tetranucleotide key : this.dicoS12.keySet())
            root.addA(key, null);
        Case caseA = new Case(root.getASon(), Case.TypeCase.ABorder);
        time = getInterval(pick, System.currentTimeMillis());
        addLine(1, time, caseA.count());

        // Calculate B and A2 (l = 2)
        pick = System.currentTimeMillis();
        Graph g;
        for(Tetranucleotide key : this.dicoS114.keySet()) {
            g = Graph.createGraph(new Node(null, key));
            if(g.isCircular())
                root.addB(key, null);
        }

        Case caseB = new Case(root.getBSon(), Case.TypeCase.BBorder);
        Case caseA2 = caseA.addEnsemble(Algorithm_tree.dicoS12, Case.TypeCase.ABorder);

        time = getInterval(pick, System.currentTimeMillis());
        addLine(2, time, (caseA2.count() + caseB.count()));


        // Calculate l = i until i == limit
        Diagonal buffer = new Diagonal();
        Diagonal bufferTemp;
        buffer.add(caseB);
        buffer.add(caseA2);

        int limit = 60;
        for (int i = 2; i < limit; i++) {
            pick = System.currentTimeMillis();
            bufferTemp = buffer.compute();
            time = getInterval(pick, System.currentTimeMillis());

            addLine(buffer.id, time, buffer.nbCIrcular);

            buffer = bufferTemp;
        }

    }
}