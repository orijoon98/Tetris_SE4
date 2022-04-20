package component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import setting.UserSetting;

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
        buttonPanel.setBounds(200, 220, 400, 250);       
        buttonPanel.setLayout(new GridLayout(4, 2));
        
        UserSetting userSetting = new UserSetting();

        keySetting = new Button("Key");
        scoreBoard = new Button("Reset Scoreboard");
        color = new Button("Color Blind Mode");
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
        
        
        
        if (userSetting.getColorBlindMode()) {
        	color.setLabel("Color Blind Mode : ON");
        }
        else {
        	color.setLabel("Color Blind Mode : OFF");            	
        }
        
        level.setLabel("Level : " + userSetting.getLevel());
		size.setLabel("Size : " + userSetting.getSize());
        
        
        

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeFrame.setVisible(true);
                settingFrame.setVisible(false);
                homeFrame.requestFocus();
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
        
        color.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userSetting.changeColorBlindMode();
				if (userSetting.getColorBlindMode()) {
		        	color.setLabel("Color Blind Mode : ON");
		        }
		        else {
		        	color.setLabel("Color Blind Mode : OFF");            	
		        }
				
			}
        	
        });
        
        level.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (userSetting.getLevel() == "EASY") {
					userSetting.changeLevel("NORMAL");
				}
				else if (userSetting.getLevel() == "NORMAL") {
					userSetting.changeLevel("HARD");
				}
				else if (userSetting.getLevel() == "HARD") {
					userSetting.changeLevel("EASY");
				}
				level.setLabel("Level : " + userSetting.getLevel());

				
				
			}
        	
        });
        
        size.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (userSetting.getSize() == "SMALL") {
					userSetting.changeSize("MIDDLE");
				}
				else if (userSetting.getSize() == "MIDDLE") {
					userSetting.changeSize("BIG");
				}
				else if (userSetting.getSize() == "BIG") {
					userSetting.changeSize("SMALL");
				}
				size.setLabel("Size : " + userSetting.getSize());


				
			}
        	
        });
   
    }
}
