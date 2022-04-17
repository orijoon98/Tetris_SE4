package component;

import input.HomeInput;
import thread.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Home extends Canvas {

    public Frame homeFrame;
    private Panel homePanel, titlePanel, buttonPanel;

    public Game gameGUI;
    public NormalGameOver normalGameOverGUI;
    public ScoreBoard scoreBoardGUI;
    public Setting settingGUI;

    private final HomeInput keyboard = new HomeInput();

    private Button normal, item, scoreBoard, setting, exit;
    private Button selected;
    private List<Button> buttonList = new ArrayList<>();

    public Home() {
        prepareHomeGUI();

        gameGUI = new Game();
        normalGameOverGUI = new NormalGameOver(this);
        scoreBoardGUI = new ScoreBoard(homeFrame);
        settingGUI = new Setting(homeFrame);

        Runnable homeTask = new HomeLoop(this);
        Thread homeThread = new Thread(homeTask);
        homeThread.start();

        Runnable gameTask = new GameLoop(this);
        Thread gameThread = new Thread(gameTask);
        gameThread.start();

        Runnable scoreBoardTask = new ScoreBoardLoop(this);
        Thread scoreBoardThread = new Thread(scoreBoardTask);
        scoreBoardThread.start();

        Runnable scoreBoardNormalTask = new ScoreBoardNormalLoop(this);
        Thread scoreBoardNormalThread = new Thread(scoreBoardNormalTask);
        scoreBoardNormalThread.start();

        Runnable scoreBoardItemTask = new ScoreBoardItemLoop(this);
        Thread scoreBoardItemThread = new Thread(scoreBoardItemTask);
        scoreBoardItemThread.start();
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

        normal = new Button("Normal Mode");
        item = new Button("Item Mode");
        scoreBoard = new Button("Scoreboard");
        setting = new Button("Setting");
        exit = new Button("Exit");

        selected = normal;
        selected.setForeground(Color.gray);
        buttonList.add(normal);
        buttonList.add(item);
        buttonList.add(scoreBoard);
        buttonList.add(setting);
        buttonList.add(exit);

        buttonPanel.add(normal);
        buttonPanel.add(item);
        buttonPanel.add(scoreBoard);
        buttonPanel.add(setting);
        buttonPanel.add(exit);

        homePanel.add(titlePanel);
        homePanel.add(buttonPanel);

        homeFrame.add(homePanel);

        homeFrame.setVisible(true);

        homeFrame.addKeyListener(keyboard);
        homeFrame.requestFocus();

        normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected.setForeground(Color.black);
                selected = normal;
                selected.setForeground(Color.gray);
                gameGUI.gameFrame.setVisible(true);
                homeFrame.setVisible(false);
            }
        });

        scoreBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected.setForeground(Color.black);
                selected = scoreBoard;
                selected.setForeground(Color.gray);
                scoreBoardGUI.scoreBoardFrame.setVisible(true);
                homeFrame.setVisible(false);
                scoreBoardGUI.scoreBoardFrame.requestFocus();
            }
        });

        setting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected.setForeground(Color.black);
                selected = setting;
                selected.setForeground(Color.gray);
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

    public void homeLoop() {
        while (true) {
            if (keyboard.up()) {
                int cur = buttonList.indexOf(selected);
                int next = (cur - 1 == -1 ) ? 4 : cur - 1;
                selected.setForeground(Color.black);
                selected = buttonList.get(next);
                selected.setForeground(Color.gray);
            }
            if (keyboard.down()) {
                int cur = buttonList.indexOf(selected);
                int next = (cur + 1 == 5 ) ? 0 : cur + 1;
                selected.setForeground(Color.black);
                selected = buttonList.get(next);
                selected.setForeground(Color.gray);
            }
            if (keyboard.enter()) {
                int cur = buttonList.indexOf(selected);
                switch (cur) {
                    case 0:
                        gameGUI.gameFrame.setVisible(true);
                        homeFrame.setVisible(false);
                        break;
                    case 1:
                        break;
                    case 2:
                        scoreBoardGUI.scoreBoardFrame.setVisible(true);
                        homeFrame.setVisible(false);
                        scoreBoardGUI.scoreBoardFrame.requestFocus();
                        break;
                    case 3:
                        settingGUI.settingFrame.setVisible(true);
                        homeFrame.setVisible(false);
                        break;
                    case 4:
                        System.exit(0);
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
