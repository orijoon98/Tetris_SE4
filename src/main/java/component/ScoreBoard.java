package component;

import input.ScoreBoardInput;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard extends Canvas {

    public Frame scoreBoardFrame;
    private Panel scoreBoardPanel, titlePanel, buttonPanel;

    public ScoreBoardNormal scoreBoardNormalGUI;
    public ScoreBoardItem scoreBoardItemGUI;

    private final ScoreBoardInput keyboard = new ScoreBoardInput();

    private Button normal, item, home;
    private Button selected;
    private List<Button> buttonList = new ArrayList<>();

    public ScoreBoard(Frame homeFrame) {
        prepareScoreBoardGUI(homeFrame);

        scoreBoardNormalGUI = new ScoreBoardNormal(homeFrame, scoreBoardFrame);
        scoreBoardItemGUI = new ScoreBoardItem(homeFrame, scoreBoardFrame);
    }

    private void prepareScoreBoardGUI(Frame homeFrame) {
        scoreBoardFrame = new Frame("Seoultech SE4 Tetris");
        scoreBoardFrame.setSize(800, 600);
        scoreBoardFrame.setResizable(false);
        scoreBoardFrame.setLayout(null);
        scoreBoardFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        scoreBoardPanel = new Panel();
        scoreBoardPanel.setSize(800, 600);
        scoreBoardPanel.setBackground(Color.black);
        scoreBoardPanel.setLayout(null);
        scoreBoardPanel.setFont(new Font("Dialog", Font.PLAIN, 16));

        titlePanel = new Panel();
        titlePanel.setBounds(250, 100, 300, 80);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, 50));

        Label title = new Label("Scoreboard");
        title.setForeground(Color.RED);

        titlePanel.add(title);

        buttonPanel = new Panel();
        buttonPanel.setBounds(150, 220, 500, 300);
        buttonPanel.setLayout(new GridLayout(3, 1));

        normal = new Button("Normal Mode");
        item = new Button("Item Mode");
        home = new Button("Home");

        selected = normal;
        selected.setForeground(Color.gray);
        buttonList.add(normal);
        buttonList.add(item);
        buttonList.add(home);

        buttonPanel.add(normal);
        buttonPanel.add(item);
        buttonPanel.add(home);

        scoreBoardPanel.add(titlePanel);
        scoreBoardPanel.add(buttonPanel);

        scoreBoardFrame.add(scoreBoardPanel);

        scoreBoardFrame.setVisible(false);

        scoreBoardFrame.addKeyListener(keyboard);
        scoreBoardFrame.requestFocus();

        normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = normal;
                scoreBoardNormalGUI.scoreBoardNormalFrame.setVisible(true);
                scoreBoardFrame.setVisible(false);
                scoreBoardNormalGUI.scoreBoardNormalFrame.requestFocus();
            }
        });

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = item;
                scoreBoardItemGUI.scoreBoardItemFrame.setVisible(true);
                scoreBoardFrame.setVisible(false);
                scoreBoardItemGUI.scoreBoardItemFrame.requestFocus();
            }
        });

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = home;
                homeFrame.setVisible(true);
                scoreBoardFrame.setVisible(false);
                homeFrame.requestFocus();
            }
        });
    }

    public void scoreBoardLoop(Frame homeFrame) {
        while (true) {
            if (keyboard.up()) {
                int cur = buttonList.indexOf(selected);
                int next = (cur - 1 == -1 ) ? 2 : cur - 1;
                selected.setForeground(Color.black);
                selected = buttonList.get(next);
                selected.setForeground(Color.gray);
            }
            if (keyboard.down()) {
                int cur = buttonList.indexOf(selected);
                int next = (cur + 1 == 3 ) ? 0 : cur + 1;
                selected.setForeground(Color.black);
                selected = buttonList.get(next);
                selected.setForeground(Color.gray);
            }
            if (keyboard.enter()) {
                int cur = buttonList.indexOf(selected);
                switch (cur) {
                    case 0:
                        selected = normal;
                        scoreBoardNormalGUI.scoreBoardNormalFrame.setVisible(true);
                        scoreBoardFrame.setVisible(false);
                        scoreBoardNormalGUI.scoreBoardNormalFrame.requestFocus();
                        break;
                    case 1:
                        selected = item;
                        scoreBoardItemGUI.scoreBoardItemFrame.setVisible(true);
                        scoreBoardFrame.setVisible(false);
                        scoreBoardItemGUI.scoreBoardItemFrame.requestFocus();
                        break;
                    case 2:
                        selected = home;
                        homeFrame.setVisible(true);
                        scoreBoardFrame.setVisible(false);
                        homeFrame.requestFocus();
                        break;
                    default:
                        break;
                }
            }
            try {
                Thread.sleep(20);
            } catch (Exception e) { }
        }
    }
}
