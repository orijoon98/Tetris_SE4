package component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Home extends Canvas {

    private Frame homeFrame;
    private Panel homePanel, titlePanel, buttonPanel;

    private Game gameGUI;
    private ScoreBoard scoreBoardGUI;
    private Setting settingGUI;

    public Home() {
        prepareHomeGUI();

        gameGUI = new Game();
        scoreBoardGUI = new ScoreBoard(homeFrame);
        settingGUI = new Setting(homeFrame);

        gameGUI.gameLoop(homeFrame);
    }

    private void prepareHomeGUI() {
        homeFrame = new Frame("Seoultech SE4 Tetris");
        homeFrame.setSize(800, 600);
        homeFrame.setResizable(false);
        homeFrame.setLayout(null);
        homeFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        homePanel = new Panel();
        homePanel.setSize(800, 600);
        homePanel.setBackground(Color.black);
        homePanel.setLayout(null);
        homePanel.setFont(new Font("Dialog", Font.PLAIN, 16));

        titlePanel = new Panel();
        titlePanel.setBounds(250, 100, 300, 80);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, 50));

        Label title = new Label("SE4 Tetris");
        title.setForeground(Color.RED);

        titlePanel.add(title);

        buttonPanel = new Panel();
        buttonPanel.setBounds(150, 220, 500, 300);
        buttonPanel.setLayout(new GridLayout(5, 1));

        Button normal = new Button("Normal Mode");
        Button item = new Button("Item Mode");
        Button scoreBoard = new Button("Scoreboard");
        Button setting = new Button("Setting");
        Button exit = new Button("Exit");

        buttonPanel.add(normal);
        buttonPanel.add(item);
        buttonPanel.add(scoreBoard);
        buttonPanel.add(setting);
        buttonPanel.add(exit);

        homePanel.add(titlePanel);
        homePanel.add(buttonPanel);

        homeFrame.add(homePanel);

        homeFrame.setVisible(true);

        normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.gameFrame.setVisible(true);
                homeFrame.setVisible(false);
            }
        });

        scoreBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scoreBoardGUI.scoreBoardFrame.setVisible(true);
                homeFrame.setVisible(false);
            }
        });

        setting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingGUI.settingFrame.setVisible(true);
                homeFrame.setVisible(false);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
