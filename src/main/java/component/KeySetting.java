package component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import setting.UserSetting;

public class KeySetting extends Canvas {
	
	public Frame keySettingFrame;
	private Panel keySettingPanel, buttonPanel, titlePanel;
	
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
        
        buttonPanel = new Panel();
        buttonPanel.setBounds(250, 220, 300, 250);       
        buttonPanel.setLayout(new GridLayout(6, 2));
        
        UserSetting userKey = new UserSetting();
        
        left = new Button("Left : " + ((char)KeyEvent.VK_LEFT) );
//        leftKey = new Button(" ");
        right = new Button("Right : " + ((char)userKey.getUserKey("RIGHT")));
  //      rightKey = new Button(" ");
        down = new Button("Down");
    //    downKey = new Button(" ");
        rotate = new Button("Rotate");
      //  rotateKey = new Button(" ");
        drop = new Button("Drop");
        //dropKey = new Button(" ");                
        backToSetting = new Button("Back to Setting");
        
//		selected =        ;
        
        buttonPanel.add(left);
 //       buttonPanel.add(leftKey);
        buttonPanel.add(right);
   //     buttonPanel.add(rightKey);
        buttonPanel.add(down);
     //   buttonPanel.add(downKey);
        buttonPanel.add(drop);
       // buttonPanel.add(dropKey);
        buttonPanel.add(rotate);
       // buttonPanel.add(rotateKey);        
        buttonPanel.add(backToSetting);
        
        
        keySettingFrame.add(keySettingPanel);
        
        keySettingPanel.add(buttonPanel);
        keySettingPanel.add(titlePanel);
        
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
        		settingFrame.setVisible(true);
        		keySettingFrame.setVisible(false);
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
