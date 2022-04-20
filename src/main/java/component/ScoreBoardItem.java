package component;

import input.ScoreBoardItemInput;
import setting.UserSetting;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ScoreBoardItem extends Canvas {
	static UserSetting UserSetting = new UserSetting();	
	private static int sizeInt = UserSetting.getSizeInt();


    public Frame scoreBoardItemFrame;
    private Panel scoreBoardItemPanel, titlePanel, buttonPanel;
    public Panel tablePanel;

    private final ScoreBoardItemInput keyboard = new ScoreBoardItemInput();

    private Button exit, home;
    private Button selected;

    public ScoreBoardItem(Frame homeFrame, Frame scoreBoardFrame) {
        scoreBoardItemFrame = new Frame("Seoultech SE4 Tetris");
        scoreBoardItemFrame.setSize(sizeInt * 400, sizeInt * 300);
        scoreBoardItemFrame.setResizable(false);
        scoreBoardItemFrame.setLayout(null);
        scoreBoardItemFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        scoreBoardItemPanel = new Panel();
        scoreBoardItemPanel.setSize(sizeInt * 400, sizeInt * 300);
        scoreBoardItemPanel.setBackground(Color.black);
        scoreBoardItemPanel.setLayout(null);
        scoreBoardItemPanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));

        titlePanel = new Panel();
        titlePanel.setBounds(sizeInt * 125, sizeInt * 50, sizeInt * 150, sizeInt * 40);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 25));

        Label title = new Label("Item");
        title.setForeground(Color.RED);

        titlePanel.add(title);

        tablePanel = new Panel();
        tablePanel.setBounds(sizeInt * 75, sizeInt * 110, sizeInt * 250, sizeInt * 105);
        tablePanel.setLayout(new GridLayout(11, 3));

        buttonPanel = new Panel();
        buttonPanel.setBounds(sizeInt * 75, sizeInt * 235, sizeInt * 250, sizeInt * 25);
        buttonPanel.setLayout(new GridLayout(1, 2));

        exit = new Button("Exit");
        home = new Button("Home");

        selected = home;
        selected.setForeground(Color.gray);

        buttonPanel.add(home);
        buttonPanel.add(exit);

        scoreBoardItemPanel.add(titlePanel);
        scoreBoardItemPanel.add(tablePanel);
        scoreBoardItemPanel.add(buttonPanel);

        scoreBoardItemFrame.add(scoreBoardItemPanel);

        scoreBoardItemFrame.setVisible(false);

        scoreBoardItemFrame.addKeyListener(keyboard);
        scoreBoardItemFrame.requestFocus();

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.removeAll();
                selected.setForeground(Color.black);
                selected = home;
                selected.setForeground(Color.gray);
                homeFrame.setVisible(true);
                scoreBoardItemFrame.setVisible(false);
                homeFrame.requestFocus();
            }
        });
    }

    public void scoreBoardItemLoop(Frame homeFrame, Frame scoreBoardFrame) {
        while (true) {
            if (keyboard.right()) {
                if (selected != exit) {
                    selected.setForeground(Color.black);
                    selected = exit;
                    selected.setForeground(Color.gray);
                }
            }
            if (keyboard.left()) {
                if (selected != home) {
                    selected.setForeground(Color.black);
                    selected = home;
                    selected.setForeground(Color.gray);
                }
            }
            if (keyboard.enter()) {
                if (selected == exit) {
                    System.exit(0);
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
