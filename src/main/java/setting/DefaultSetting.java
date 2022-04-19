package setting;


import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class DefaultSetting {

	private final Map<String, Integer> defaultKey = new ConcurrentHashMap<String, Integer>(); 
	
	public DefaultSetting() {	    
		defaultKey();
	    
	}
	
	private void defaultKey() {	    
	    defaultKey.put("LEFT", KeyEvent.VK_LEFT);
	    defaultKey.put("RIGHT", KeyEvent.VK_RIGHT);
	    defaultKey.put("ROTATE", KeyEvent.VK_UP);
	    defaultKey.put("DROP", KeyEvent.VK_SPACE);
	    defaultKey.put("DOWN", KeyEvent.VK_DOWN);
	    
	}

	
	public int getDefaultKey(String KEY) {
		return defaultKey.get(KEY);
	}
	
	
	private boolean defaultColorBlindMode = Boolean.FALSE;
	
	public boolean getDefaultColorBlindMode(){
		return defaultColorBlindMode;
	}
	
	
}
