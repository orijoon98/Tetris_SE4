package component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import component.game.*;

public class Home extends JFrame {

    public Home() {
        super("SeoulTech SE4 Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameLoop gamestart = new GameLoop(this);
        ScoreBoard scoreBoard = new ScoreBoard(this);
        Setting setting = new Setting(this);

        JPanel jPanel = new JPanel();
        JButton gameStartButton = new JButton("게임 시작");
        JButton scoreBoardButton = new JButton("스코어보드");
        JButton settingButton = new JButton("설정");
        JButton exitButton = new JButton("프로그램 종료");

        jPanel.add(gameStartButton);
        jPanel.add(scoreBoardButton);
        jPanel.add(settingButton);
        jPanel.add(exitButton);

        add(jPanel);
        setSize(1000, 800);
        setVisible(true);

        gameStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamestart.setVisible(true);
                setVisible(false);
            }
        });

        scoreBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scoreBoard.setVisible(true);
                setVisible(false);
            }
        });

        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setting.setVisible(true);
                setVisible(false);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
