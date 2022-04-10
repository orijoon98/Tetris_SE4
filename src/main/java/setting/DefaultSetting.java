package setting;


import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class DefaultSetting {
	
	private int defaultKey(String Key) {
	    final Map<String, Integer> defaultKey = new ConcurrentHashMap<String, Integer>(); 
	    
	    defaultKey.put("LEFT", KeyEvent.VK_LEFT);
	    defaultKey.put("RIGHT", KeyEvent.VK_RIGHT);
	    defaultKey.put("ROTATE", KeyEvent.VK_UP);
	    defaultKey.put("DROP", KeyEvent.VK_SPACE);
	    
	    int keyCode;
	    
		keyCode = defaultKey.get(Key);
	    
		return keyCode;
	}
	
	public int getDefaultKey(String KEY) {
		return defaultKey(KEY);
	}
	
	private boolean defaultColor = Boolean.FALSE;
	
	
}
