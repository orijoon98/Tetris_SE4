package component;

import input.HomeInput;
import setting.UserSetting;
import thread.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Home extends Canvas {
	static UserSetting UserSetting = new UserSetting();	
	private static int sizeInt = UserSetting.getSizeInt();


    public Frame homeFrame;
    private Panel homePanel, titlePanel, buttonPanel;

    public Game gameGUI;
    public Item itemGUI;
    public NormalGameOver normalGameOverGUI;
    public ItemGameOver itemGameOverGUI;
    public ScoreBoard scoreBoardGUI;
    public Setting settingGUI;

    private final HomeInput keyboard = new HomeInput();

    private Button normal, item, scoreBoard, setting, exit;
    private Button selected;
    private List<Button> buttonList = new ArrayList<>();

    public Home() {
        prepareHomeGUI();

        gameGUI = new Game();
        itemGUI = new Item();
        normalGameOverGUI = new NormalGameOver(this);
        itemGameOverGUI = new ItemGameOver(this);
        scoreBoardGUI = new ScoreBoard(homeFrame);
        settingGUI = new Setting(homeFrame);

        Runnable homeTask = new HomeLoop(this);
        Thread homeThread = new Thread(homeTask);
        homeThread.start();

        Runnable gameTask = new GameLoop(this);
        Thread gameThread = new Thread(gameTask);
        gameThread.start();

        Runnable itemTask = new ItemLoop(this);
        Thread itemThread = new Thread(itemTask);
        itemThread.start();

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
        homeFrame.setSize(sizeInt * 400, sizeInt * 300);
        homeFrame.setResizable(false);
        homeFrame.setLayout(null);
        homeFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        homePanel = new Panel();
        homePanel.setSize(sizeInt * 400, sizeInt * 300);
        homePanel.setBackground(Color.black);
        homePanel.setLayout(null);
        homePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));

        titlePanel = new Panel();
        titlePanel.setBounds(sizeInt * 125, sizeInt * 50, sizeInt * 150, sizeInt * 40);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 25));

        Label title = new Label("SE4 Tetris");
        title.setForeground(Color.RED);

        titlePanel.add(title);

        buttonPanel = new Panel();
        buttonPanel.setBounds(sizeInt * 75, sizeInt * 110, sizeInt * 250, sizeInt * 150);
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

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected.setForeground(Color.black);
                selected = item;
                selected.setForeground(Color.gray);
                itemGUI.itemFrame.setVisible(true);
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
                        itemGUI.itemFrame.setVisible(true);
                        homeFrame.setVisible(false);
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
