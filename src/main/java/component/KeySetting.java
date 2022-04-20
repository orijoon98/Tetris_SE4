package component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import setting.UserSetting;
import setting.InputSetting;

public class KeySetting extends Canvas {
	static UserSetting UserSetting = new UserSetting();	
	private static int sizeInt = UserSetting.getSizeInt();

	
	public Frame keySettingFrame;
	private Panel keySettingPanel, buttonPanel, titlePanel, discriptionPanel;
	
	private Button left, right, down, drop, rotate, backToSetting;

	private Button selected;

	
	public KeySetting(Frame settingFrame) {
		prepareKeySettingGUI(settingFrame);
	}
	
	private void prepareKeySettingGUI(Frame settingFrame) {
        keySettingFrame = new Frame("Seoultech SE4 Tetris");
        keySettingFrame.setSize(sizeInt * 400, sizeInt * 300);
        keySettingFrame.setResizable(false);
        keySettingFrame.setLayout(null);
        keySettingFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        keySettingPanel = new Panel();
        keySettingPanel.setSize(sizeInt * 400, sizeInt * 300);
        keySettingPanel.setBackground(Color.black);
        keySettingPanel.setLayout(null);
        keySettingPanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));
       
        
        titlePanel = new Panel();
        titlePanel.setBounds(sizeInt * 125, sizeInt * 50, sizeInt * 150, sizeInt * 40);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 25));              
        
        Label title = new Label("KeySetting");
        title.setForeground(Color.BLUE); 
        
        titlePanel.add(title);
        
        discriptionPanel = new Panel();
        discriptionPanel.setBounds(sizeInt * 0, sizeInt * 85, sizeInt * 400, sizeInt * 40);
        discriptionPanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 10));
// !! 영어 고쳐놓기 !!
        Label discription = new Label("Please Click the botton and press the key you'd like to modify");
        discription.setForeground(Color.WHITE); 
        
        discriptionPanel.add(discription);

        buttonPanel = new Panel();
        buttonPanel.setBounds(sizeInt * 125, sizeInt * 110, sizeInt * 150, sizeInt * 125);       
        buttonPanel.setLayout(new GridLayout(6, 2));
        
        UserSetting userKey = new UserSetting();
        InputSetting InputSetting = new InputSetting();
        
        left = new Button("Left : " + InputSetting.getStringKey("LEFT"));
        right = new Button("Right : " + InputSetting.getStringKey("RIGHT"));
        down = new Button("Down : " + InputSetting.getStringKey("DOWN") );
        rotate = new Button("Rotate : " + InputSetting.getStringKey("ROTATE"));
        drop = new Button("Drop : "+ InputSetting.getStringKey("DROP"));
        backToSetting = new Button("Back to Setting");
        
        
        buttonPanel.add(left);
        buttonPanel.add(right);
        buttonPanel.add(down);
        buttonPanel.add(drop);
        buttonPanel.add(rotate);
        buttonPanel.add(backToSetting);
        
        
        keySettingFrame.add(keySettingPanel);
        
        keySettingPanel.add(buttonPanel);
        keySettingPanel.add(titlePanel);
        keySettingPanel.add(discriptionPanel);
        
        backToSetting.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		settingFrame.setVisible(true);
        		keySettingFrame.setVisible(false);
        	}
        });
        
// 누르고 키를 입력하면 바뀜
        // selected 넣어서 클릭 중복 안나게         
        clickChangeKey(left, "LEFT");
        clickChangeKey(right, "RIGHT");
        clickChangeKey(rotate, "ROTATE");
        clickChangeKey(down, "DOWN");
        clickChangeKey(drop, "DROP");
		
	}
	private void changeKey(Button button, String buttonName) {
		UserSetting userKey = new UserSetting();
		if (selected == button) {
			left.setLabel("Left : " + userKey.getStringKey("LEFT"));
		    right.setLabel("Right : " + userKey.getStringKey("RIGHT"));
		    down.setLabel("Down : " + userKey.getStringKey("DOWN") );
		    rotate.setLabel("Rotate : " + userKey.getStringKey("ROTATE"));
		    drop.setLabel("Drop : "+ userKey.getStringKey("DROP"));
			button.setLabel("Press Key for " + buttonName);			
			button.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {}
				
				@Override
				public void keyPressed(KeyEvent e) {
					if (userKey.overlapCheck(e)) {
						if(e.getKeyCode() == userKey.getUserKey(buttonName)) {
							userKey.changeKey(buttonName, e.getKeyCode());
	    					//System.out.println("heres another one?" + e.getKeyCode());
	    					button.setLabel(buttonName + " : " + userKey.getStringKey(buttonName)); 
						}
						else {
							System.out.println("OVERLAPED!");
							button.setLabel(buttonName + " : " + userKey.getStringKey(buttonName));    
						}
					}
					else {
						userKey.changeKey(buttonName, e.getKeyCode());
						button.setLabel(buttonName + " : " + userKey.getStringKey(buttonName));    
					}
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {}
	    			
	    	});

		}
		else {
			button.setLabel(buttonName + " : " + userKey.getStringKey(buttonName)); 			
		}

	}
	
	private void clickChangeKey(Button button, String buttonName) {
		button.addMouseListener(new MouseListener() {
        	public void mouseClicked(MouseEvent e) {
        		selected = button;
        		changeKey(button, buttonName);
        	}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub				
			}
        });
        
		
	}

	

}
