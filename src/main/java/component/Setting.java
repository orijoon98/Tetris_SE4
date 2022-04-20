package component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import setting.UserSetting;

public class Setting extends Canvas {
	
	static UserSetting UserSetting = new UserSetting();	
	private static int sizeInt = UserSetting.getSizeInt();


    public Frame settingFrame;
    private Panel settingPanel, buttonPanel, titlePanel;
    
    public KeySetting keySettingGUI;


    private Button home, keySetting, scoreBoard, color, difficulty_level, size, reSetting;
    private Button selected;

    private List<Button> buttonList = new ArrayList<>();
    

    public Setting(Frame homeFrame) {
        prepareSettingGUI(homeFrame);        
        keySettingGUI = new KeySetting(settingFrame);
    }

    private void prepareSettingGUI(Frame homeFrame) {
        settingFrame = new Frame("Seoultech SE4 Tetris");
        settingFrame.setSize(sizeInt * 400, sizeInt *300);
        settingFrame.setResizable(false);
        settingFrame.setLayout(null);
        settingFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        settingPanel = new Panel();
        settingPanel.setSize(sizeInt *400, sizeInt *300);
        settingPanel.setBackground(Color.black);
        settingPanel.setLayout(null);
        settingPanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt *8));

        titlePanel = new Panel();
        titlePanel.setBounds(sizeInt *125, sizeInt *50, sizeInt *150, sizeInt *40);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt *25));
        Label title = new Label("Setting");
        title.setForeground(Color.RED);
        
        titlePanel.add(title);

        buttonPanel = new Panel();
        buttonPanel.setBounds(sizeInt *100, sizeInt *110, sizeInt *200, sizeInt *125);       
        buttonPanel.setLayout(new GridLayout(4, 2));
        
        UserSetting userSetting = new UserSetting();

        keySetting = new Button("Key");
        scoreBoard = new Button("Reset Scoreboard");
        color = new Button("Color Blind Mode");
        difficulty_level = new Button("Level");
        size = new Button("Size");
        reSetting = new Button("Reset Setting");
        home = new Button("Home");

        selected = keySetting;
        
        buttonPanel.add(keySetting);
        buttonPanel.add(scoreBoard);
        buttonPanel.add(color);
        buttonPanel.add(difficulty_level);
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

        difficulty_level.setLabel("Level : " + userSetting.getDifficultyLevel());
		size.setLabel("Size : " + userSetting.getSize());
        
        
        

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeFrame.setVisible(true);
                settingFrame.setVisible(false);
                homeFrame.requestFocus();
                scoreBoard.setLabel("Reset Scoreboard");
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

        difficulty_level.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (userSetting.getDifficultyLevel() == "EASY") {
					userSetting.changeLevel("NORMAL");
				}
				else if (userSetting.getDifficultyLevel() == "NORMAL") {
					userSetting.changeLevel("HARD");
				}
				else if (userSetting.getDifficultyLevel() == "HARD") {
					userSetting.changeLevel("EASY");
				}
                difficulty_level.setLabel("Level : " + userSetting.getDifficultyLevel());

				
				
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

        scoreBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    resetScoreBoard();
                } catch (IOException exception) {}
                scoreBoard.setLabel("Reset Completed");
            }
        });
        
        reSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 디폴트값이 바뀔경우 같이 바꿔줘야한다.
                userSetting.resetSetting();
                reSetting.setLabel("Reset Completed");
                color.setLabel("Color Blind Mode : OFF");            	
                difficulty_level.setLabel("Level : EASY" );
        		size.setLabel("Size : MIDDLE");
                
            }
        });
   
    }

    private void resetScoreBoard() throws IOException {
        URL url = new URL("http://ec2-13-124-44-172.ap-northeast-2.compute.amazonaws.com:8080/api/reset");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("DELETE");
        connection.setDoOutput(true);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String returnMsg = in.readLine();
    }
}
