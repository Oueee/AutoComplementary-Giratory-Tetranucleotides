package core;

import algorithm.Algorithm_tree;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Cette classe démarre le programme et son interface graphique.
 * <br>Il n'est donc pas étonnant d'y retrouver de nombreux éléments servant uniquement à l'interface graphique.
 * <br>J'essaie néanmoins de regrouper tout l'aspect graphique vers le bas du fichier autant que possible.
 * @author Alexandre Florentin
 */
@SuppressWarnings("serial")
public class Gui extends JFrame{
    void initialize(final Algorithm_tree algo){
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            // Si on arrive pas à charger le thème, au pire c'est pas grave.
            e.printStackTrace();
        }
        this.setTitle("Algorithme avancé");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 650, 500);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        this.setContentPane(contentPane);

        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));

        final TextArea console = new TextArea();
        console.setEditable(false);
        panel_2.add(console, BorderLayout.CENTER);
        algo.console = console;

        JPanel panel_1 = new JPanel();
        panel_2.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        final JButton btnRun = new JButton("Run");

        panel_1.add(btnRun);
        btnRun.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                btnRun.setEnabled(false);

                new Thread(algo).start();
            }});

        JButton btnQuitter = new JButton("Exit");
        panel_1.add(btnQuitter);
        btnQuitter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }});

        JPanel panel_3 = new JPanel();
        panel_2.add(panel_3, BorderLayout.NORTH);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel title = new JLabel("Recherche des codes circulaires complémentaires");
        panel_3.add(title);


        this.setVisible(true);
    }
}