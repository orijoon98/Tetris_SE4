package component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ScoreBoard extends Canvas {

    public Frame scoreBoardFrame;
    private Panel scoreBoardPanel, buttonPanel;

    public ScoreBoard(Frame homeFrame) {
        prepareScoreBoardGUI(homeFrame);
    }

    private void prepareScoreBoardGUI(Frame homeFrame) {
        scoreBoardFrame = new Frame("Seoultech SE4 Tetris");
        scoreBoardFrame.setSize(800, 600);
        scoreBoardFrame.setResizable(false);
        scoreBoardFrame.setLayout(new GridLayout(1, 1));
        scoreBoardFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        scoreBoardPanel = new Panel();
        scoreBoardPanel.setBackground(Color.black);
        scoreBoardPanel.setLayout(new GridLayout(1, 1));
        scoreBoardPanel.setFont(new Font("Dialog", Font.PLAIN, 16));

        buttonPanel = new Panel();

        Button home = new Button("홈으로");

        buttonPanel.add(home);

        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new GridLayout(1, 1));

        scoreBoardPanel.add(buttonPanel);

        scoreBoardFrame.add(scoreBoardPanel);

        scoreBoardFrame.setVisible(false);

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeFrame.setVisible(true);
                scoreBoardFrame.setVisible(false);
            }
        });
    }
}
