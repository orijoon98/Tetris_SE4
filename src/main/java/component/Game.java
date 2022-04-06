package component;

import component.game.GameGUI;

import javax.swing.*;

public class Game extends JFrame {

    public Game(Home home) {
    	super("SeoulTech SE4 Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel jPanel = new JPanel();

        new GameGUI().gameLoop();

        add(jPanel);
        pack();
        setResizable(false);
        setSize(1000, 800);
        setVisible(false);
    }
}