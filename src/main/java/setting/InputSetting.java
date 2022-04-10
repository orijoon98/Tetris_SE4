package setting;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import setting.DefaultSetting;


public class InputSetting {
	
    private boolean userSetted = Boolean.FALSE;
    
    
    DefaultSetting defaultKey = new DefaultSetting();
    

    private Map<String, Integer> currentKey = new ConcurrentHashMap<>();
    
    public InputSetting()	{
    	if (userSetted == Boolean.FALSE) {
        	currentKey.put("LEFT", defaultKey.getDefaultKey("LEFT"));
        	currentKey.put("RIGHT", defaultKey.getDefaultKey("RIGHT"));
        	currentKey.put("ROTATE", defaultKey.getDefaultKey("ROTATE"));
        	currentKey.put("DROP", defaultKey.getDefaultKey("DROP"));
    	}

    }
    
 
    public void setLeft(int keyCode) {
    	if(userSetted == Boolean.FALSE) {
    		userSetted = Boolean.TRUE;
    	}
    	currentKey.replace("LEFT", keyCode);
    }
    
    public void setRight(int keyCode) {
    	if(userSetted == Boolean.FALSE) {
    		userSetted = Boolean.TRUE;
    	}
    	currentKey.replace("RIGHT", keyCode);
    }

    public void setRotate(int keyCode) {
    	if(userSetted == Boolean.FALSE) {
    		userSetted = Boolean.TRUE;
    	}
    	currentKey.replace("ROTATE", keyCode);
    }

    public void setDrop(int keyCode) {
    	if(userSetted == Boolean.FALSE) {
    		userSetted = Boolean.TRUE;
    	}
    	currentKey.replace("DROP", keyCode);
    }
    
    
    public void resetKey() {
    	currentKey.replace("LEFT", defaultKey.getDefaultKey("LEFT"));
    	currentKey.replace("RIGHT", defaultKey.getDefaultKey("RIGHT"));
    	currentKey.replace("ROTATE", defaultKey.getDefaultKey("ROTATE"));
    	currentKey.replace("DROP", defaultKey.getDefaultKey("DROP"));
    }


    
    public int leftKey()	{
    	return currentKey.get("LEFT");
    }
    
    public int rightKey()	{
    	return currentKey.get("RIGHT");
    }
    
    public int rotateKey()	{
    	return currentKey.get("ROTATE");
    }
    public int dropKey()	{
    	return currentKey.get("DROP");
    }
    
}