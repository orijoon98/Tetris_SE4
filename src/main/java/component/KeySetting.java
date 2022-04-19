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
	
	public Frame keySettingFrame;
	private Panel keySettingPanel, buttonPanel, titlePanel, discriptionPanel;
	
//	private Button left, right, down, drop, rotate, backToSetting, leftKey, rightKey, downKey, dropKey, rotateKey;
	private Button left, right, down, drop, rotate, backToSetting;

	private Button selected;

	
	public KeySetting(Frame settingFrame) {
		prepareKeySettingGUI(settingFrame);
	}
	
	private void prepareKeySettingGUI(Frame settingFrame) {
        keySettingFrame = new Frame("Seoultech SE4 Tetris");
        keySettingFrame.setSize(800, 600);
        keySettingFrame.setResizable(false);
        keySettingFrame.setLayout(null);
        keySettingFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        keySettingPanel = new Panel();
        keySettingPanel.setSize(800, 600);
        keySettingPanel.setBackground(Color.black);
        keySettingPanel.setLayout(null);
        keySettingPanel.setFont(new Font("Dialog", Font.PLAIN, 16));
       
        
        titlePanel = new Panel();
        titlePanel.setBounds(250, 100, 300, 80);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, 50));              
        
        Label title = new Label("KeySetting");
        title.setForeground(Color.BLUE); 
        
        titlePanel.add(title);
        
        discriptionPanel = new Panel();
        discriptionPanel.setBounds(250, 150, 300, 80);
        discriptionPanel.setFont(new Font("Dialog", Font.PLAIN, 30));
        Label discription = new Label("Please Click and Enter key");
        discription.setForeground(Color.WHITE); 
        
        discriptionPanel.add(discription);


        
        buttonPanel = new Panel();
        buttonPanel.setBounds(250, 220, 300, 250);       
        buttonPanel.setLayout(new GridLayout(6, 2));
        
        UserSetting userKey = new UserSetting();
        InputSetting InputSetting = new InputSetting();
        
        left = new Button("Left : " + InputSetting.getStringKey("LEFT"));
        right = new Button("Right : " + InputSetting.getStringKey("RIGHT"));
        down = new Button("Down : " + InputSetting.getStringKey("DOWN") );
        rotate = new Button("Rotate : " + InputSetting.getStringKey("ROTATE"));
        drop = new Button("Drop : "+ InputSetting.getStringKey("DROP"));
        backToSetting = new Button("Back to Setting");
        
//		selected =        ;
        
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
        
        
        left.addMouseListener(new MouseListener() {

        	// left버튼 누룰시 왼쪽 키 바꾸기 가능
        	public void mouseClicked(MouseEvent e) {
        		int a = 0;
        		System.out.println("you clicked");
        		left.setLabel("you clicked left!");
        		
        		
        			left.addKeyListener(new KeyListener() {
    					@Override
    					public void keyTyped(KeyEvent e) {
    		        		System.out.println(e.getKeyCode());
    		        		char j = e.getKeyChar();
    		        		System.out.println();
    		        		
    		        		
    						//int keyCode = e.getExtendedKeyCode();
    						//InputSetting.setLeft(keyCode);
    						left.setLabel("Left : " + j + e.getKeyCode() + userKey.getUserKey("LEFT"));    		        	
    					}
    					@Override
    					public void keyPressed(KeyEvent e) {
    					}
    					@Override
    					public void keyReleased(KeyEvent e) {    						
    					}
            			
            		});
	
        		
        		/*        		left.addKeyListener(new KeyListener() {

					@Override
					public void keyTyped(KeyEvent e) {
						int keyCode = e.getKeyCode();
						InputSetting.setLeft(keyCode);
						
//						keyListener.close();
					}

					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
        			
        		});
 */       		
//        		settingFrame.setVisible(true);
  //      		keySettingFrame.setVisible(false);
        	}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        });


		
	}
	

}
