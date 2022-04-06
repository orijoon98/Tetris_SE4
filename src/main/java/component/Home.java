package component;

import input.HomeInput;

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

    private final HomeInput keyboard = new HomeInput();

    public Home() {
        prepareHomeGUI();

        gameGUI = new Game(homeFrame);
        scoreBoardGUI = new ScoreBoard(homeFrame);
        settingGUI = new Setting(homeFrame);

        gameGUI.gameLoop();

        addKeyListener(keyboard);
    }

    private void prepareHomeGUI() {
        homeFrame = new Frame("Seoultech SE4 Tetris");
        homeFrame.setSize(800, 600);
        homeFrame.setResizable(false);
        homeFrame.setLayout(new GridLayout(1, 1));
        homeFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        homePanel = new Panel();
        homePanel.setBackground(Color.black);
        homePanel.setLayout(new GridLayout(2, 1));
        homePanel.setFont(new Font("Dialog", Font.PLAIN, 16));

        titlePanel = new Panel();
        Label title = new Label("소프트웨어공학 4팀 테트리스");
        title.setForeground(Color.white);

        titlePanel.add(title);

        buttonPanel = new Panel();

        Button normal = new Button("일반 모드");
        Button item = new Button("아이템 모드");
        Button scoreBoard = new Button("스코어보드");
        Button setting = new Button("설정");
        Button exit = new Button("프로그램 종료");

        buttonPanel.add(normal);
        buttonPanel.add(item);
        buttonPanel.add(scoreBoard);
        buttonPanel.add(setting);
        buttonPanel.add(exit);

        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new GridLayout(5, 1));

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
