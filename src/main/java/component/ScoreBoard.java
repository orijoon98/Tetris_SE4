package component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreBoard extends JFrame {

    public ScoreBoard(Home home) {
        super("SeoulTech SE4 Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        JButton homeButton = new JButton("홈으로");

        jPanel.add(homeButton);

        add(jPanel);
        setSize(1000, 800);
        setVisible(false);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                home.setVisible(true);
                setVisible(false);
            }
        });
    }
}
