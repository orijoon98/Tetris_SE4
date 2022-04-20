package setting;


import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class DefaultSetting {

	private static Map<String, Integer> defaultKey = new ConcurrentHashMap<String, Integer>(); 
	private static Map<String, Boolean> defaultLevel = new  ConcurrentHashMap<String, Boolean>(); 
	private static Map<String, Boolean> defaultSize = new  ConcurrentHashMap<String, Boolean>(); 
	private boolean defaultColorBlindMode;

	
	public DefaultSetting() {	    
		defaultKey();
		defaultLevel();
		defaultSize();
	    defaultColorBlindMode();
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

	
	private void defaultLevel() {
		defaultLevel.put("EASY", Boolean.TRUE);
		defaultLevel.put("NORMAL", Boolean.FALSE);
		defaultLevel.put("HARD", Boolean.FALSE);
		
	}
	
	public String getDefaultLevel() {
		if(defaultLevel.get("EASY")){
			return "EASY";
		}
		else if(defaultLevel.get("NORMAL")){
			return "NORMAL";
		}
		else if(defaultLevel.get("HARD")){
			return "HARD";
		}
		else {
			return "ERROR";
		}

	}
	
	
	private void defaultSize() {
		defaultSize.put("SMALL", Boolean.TRUE);
		defaultSize.put("MIDDLE", Boolean.FALSE);
		defaultSize.put("BIG", Boolean.FALSE);
	}
	
	public String getDefaultSize() {
		if(defaultSize.get("SMALL")){
			return "SMALL";
		}
		else if(defaultSize.get("MIDDLE")){
			return "MIDDLE";
		}
		else if(defaultSize.get("BIG")){
			return "BIG";
		}
		else {
			return "ERROR";
		}
	}

	
	private void defaultColorBlindMode() {
		defaultColorBlindMode = Boolean.FALSE;
	}
	
	public boolean getDefaultColorBlindMode(){
		return defaultColorBlindMode;
	}
	
	
}
