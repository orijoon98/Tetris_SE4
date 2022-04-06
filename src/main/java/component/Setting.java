package component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Setting extends Canvas {

    public Frame settingFrame;
    private Panel settingPanel, buttonPanel;

    public Setting(Frame homeFrame) {
        prepareSettingGUI(homeFrame);
    }

    private void prepareSettingGUI(Frame homeFrame) {
        settingFrame = new Frame("Seoultech SE4 Tetris");
        settingFrame.setSize(800, 600);
        settingFrame.setResizable(false);
        settingFrame.setLayout(null);
        settingFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        settingPanel = new Panel();
        settingPanel.setSize(800, 600);
        settingPanel.setBackground(Color.black);
        settingPanel.setLayout(null);
        settingPanel.setFont(new Font("Dialog", Font.PLAIN, 16));

        buttonPanel = new Panel();

        Button home = new Button("홈으로");

        buttonPanel.add(home);
        buttonPanel.setBounds(350, 220, 100, 50);
        buttonPanel.setLayout(new GridLayout(1, 1));

        settingPanel.add(buttonPanel);

        settingFrame.add(settingPanel);

        settingFrame.setVisible(false);

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeFrame.setVisible(true);
                settingFrame.setVisible(false);
            }
        });
    }
}
