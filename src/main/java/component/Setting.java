package component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


public class Setting extends Canvas {

    public Frame settingFrame;
    private Panel settingPanel, buttonPanel, titlePanel;
    
    public KeySetting keySettingGUI;


    private Button home, keySetting, scoreBoard, color, level, size, reSetting;
    private Button selected;

    private List<Button> buttonList = new ArrayList<>();
    

    public Setting(Frame homeFrame) {
        prepareSettingGUI(homeFrame);        
        keySettingGUI = new KeySetting(settingFrame);
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

        titlePanel = new Panel();
        titlePanel.setBounds(250, 100, 300, 80);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, 50));
        Label title = new Label("Setting");
        title.setForeground(Color.RED);
        
        titlePanel.add(title);

        buttonPanel = new Panel();
        buttonPanel.setBounds(250, 220, 300, 250);       
        buttonPanel.setLayout(new GridLayout(4, 2));

        keySetting = new Button("Key");
        scoreBoard = new Button("Scoreboard");
        color = new Button("Color");
        level = new Button("Level");
        size = new Button("Size");
        reSetting = new Button("ReSetting");        
        home = new Button("home");

        selected = keySetting;
        
        buttonPanel.add(keySetting);
        buttonPanel.add(scoreBoard);
        buttonPanel.add(color);
        buttonPanel.add(level);
        buttonPanel.add(size);
        buttonPanel.add(reSetting);
        buttonPanel.add(home);

        settingPanel.add(buttonPanel);
        settingFrame.add(settingPanel);
        settingPanel.add(titlePanel);

        settingFrame.setVisible(false);

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeFrame.setVisible(true);
                settingFrame.setVisible(false);
            }
        });
        keySetting.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		selected.setForeground(Color.black);
        		selected = keySetting;
        		selected.setForeground(Color.gray);
        		keySettingGUI.keySettingFrame.setVisible(true);
                settingFrame.setVisible(false);
            	
        	}
        	
        });
   
    }
}
