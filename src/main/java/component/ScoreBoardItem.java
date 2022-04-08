package component;

import input.ScoreBoardItemInput;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ScoreBoardItem extends Canvas {

    public Frame scoreBoardItemFrame;
    private Panel scoreBoardItemPanel, titlePanel, buttonPanel;
    public Panel tablePanel;

    private final ScoreBoardItemInput keyboard = new ScoreBoardItemInput();

    private Button back, home;
    private Button selected;

    public ScoreBoardItem(Frame homeFrame, Frame scoreBoardFrame) {
        scoreBoardItemFrame = new Frame("Seoultech SE4 Tetris");
        scoreBoardItemFrame.setSize(800, 600);
        scoreBoardItemFrame.setResizable(false);
        scoreBoardItemFrame.setLayout(null);
        scoreBoardItemFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        scoreBoardItemPanel = new Panel();
        scoreBoardItemPanel.setSize(800, 600);
        scoreBoardItemPanel.setBackground(Color.black);
        scoreBoardItemPanel.setLayout(null);
        scoreBoardItemPanel.setFont(new Font("Dialog", Font.PLAIN, 16));

        titlePanel = new Panel();
        titlePanel.setBounds(250, 100, 300, 80);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, 50));

        Label title = new Label("Item");
        title.setForeground(Color.RED);

        titlePanel.add(title);

        tablePanel = new Panel();
        tablePanel.setBounds(150, 220, 500, 210);
        tablePanel.setLayout(new GridLayout(11, 3));

        buttonPanel = new Panel();
        buttonPanel.setBounds(150, 470, 500, 50);
        buttonPanel.setLayout(new GridLayout(1, 2));

        back = new Button("Back");
        home = new Button("Home");

        selected = back;
        selected.setForeground(Color.gray);

        buttonPanel.add(back);
        buttonPanel.add(home);

        scoreBoardItemPanel.add(titlePanel);
        scoreBoardItemPanel.add(tablePanel);
        scoreBoardItemPanel.add(buttonPanel);

        scoreBoardItemFrame.add(scoreBoardItemPanel);

        scoreBoardItemFrame.setVisible(false);

        scoreBoardItemFrame.addKeyListener(keyboard);
        scoreBoardItemFrame.requestFocus();

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.removeAll();
                selected = back;
                scoreBoardFrame.setVisible(true);
                scoreBoardItemFrame.setVisible(false);
                scoreBoardFrame.requestFocus();
            }
        });

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.removeAll();
                selected = home;
                homeFrame.setVisible(true);
                scoreBoardItemFrame.setVisible(false);
                homeFrame.requestFocus();
            }
        });
    }

    public void scoreBoardItemLoop(Frame homeFrame, Frame scoreBoardFrame) {
        while (true) {
            if (keyboard.left()) {
                if (selected != back) {
                    selected.setForeground(Color.black);
                    selected = back;
                    selected.setForeground(Color.gray);
                }
            }
            if (keyboard.right()) {
                if (selected != home) {
                    selected.setForeground(Color.black);
                    selected = home;
                    selected.setForeground(Color.gray);
                }
            }
            if (keyboard.enter()) {
                if (selected == back) {
                    tablePanel.removeAll();
                    selected = back;
                    scoreBoardFrame.setVisible(true);
                    scoreBoardItemFrame.setVisible(false);
                    scoreBoardFrame.requestFocus();
                } else if (selected == home) {
                    tablePanel.removeAll();
                    selected = home;
                    homeFrame.setVisible(true);
                    scoreBoardItemFrame.setVisible(false);
                    homeFrame.requestFocus();
                } else { }
            }
            try {
                Thread.sleep(20);
            } catch (Exception e) { }
        }
    }
}
