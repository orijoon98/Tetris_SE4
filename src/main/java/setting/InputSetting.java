package setting;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import setting.DefaultSetting;
import setting.UserSetting;

/*
 * [x] userSetting에서 가져오기
 * [] userSetting의 Key가 정상적인지 확인하기
 * [x] user의 input 받고 값 바꾸기
 */


public class InputSetting {
       
    DefaultSetting defaultKey = new DefaultSetting();
    UserSetting userKey = new UserSetting();        
 
    public void setLeft(int keyCode) {
    	userKey.changeKey("LEFT", keyCode);
    }
    
    public void setRight(int keyCode) {
    	userKey.changeKey("RIGHT", keyCode);
    }

    public void setRotate(int keyCode) {
    	userKey.changeKey("ROTATE", keyCode);
    }

    public void setDrop(int keyCode) {
    	userKey.changeKey("DROP", keyCode);
    }    
    
    public void setDown(int keyCode) {
    	userKey.changeKey("Down", keyCode);
    }    

    
    
    public void resetKey() {
    	userKey.userSettedKey = Boolean.FALSE;
    }
    
    
    
    public int leftKey()	{
    	return userKey.getUserKey("LEFT");
    }
    
    public int rightKey()	{
    	return userKey.getUserKey("RIGHT");
    }
    
    public int rotateKey()	{
    	return userKey.getUserKey("ROTATE");
    }
    public int dropKey()	{
    	return userKey.getUserKey("DROP");
    }
    public int downKey() {
    	return userKey.getUserKey("DOWN");
    }
    
}