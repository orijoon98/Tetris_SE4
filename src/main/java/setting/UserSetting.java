package setting;


import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import setting.DefaultSetting;
import input.GameInput;
import input.ItemInput;
import setting.ReadPreviousSetting;

public class UserSetting {
	DefaultSetting defaultKey = new DefaultSetting();

	private static Map<String, Integer> userKey = new ConcurrentHashMap<String, Integer>(); 
	private static boolean colorBlindMode;
	private static Map<String, Boolean> difficulty_level = new  ConcurrentHashMap<String, Boolean>();
	private static Map<String, Boolean> size = new  ConcurrentHashMap<String, Boolean>(); 
	
    public boolean userSettedKey;
    public boolean exist = ( userKey.containsKey("LEFT") && userKey.containsKey("RIGHT") && userKey.containsKey("ROTATE") && userKey.containsKey("DOWN") && userKey.containsKey("DROP"));
	    
	public UserSetting() {
		UserKey();
		colorBlindMode();
		difficulty_level();
		size();

    }
	
	
	
	private void UserKey() {
		if (exist) {
    	}
		else{
			userKey.put("LEFT", defaultKey.getDefaultKey("LEFT"));
			userKey.put("RIGHT", defaultKey.getDefaultKey("RIGHT"));
			userKey.put("ROTATE", defaultKey.getDefaultKey("ROTATE"));
			userKey.put("DROP", defaultKey.getDefaultKey("DROP"));
			userKey.put("DOWN", defaultKey.getDefaultKey("DOWN"));
			
			userKey.containsValue(defaultKey);
    	}
	}
			
	public int getUserKey(String KEY) {
		return userKey.get(KEY);
	}
	
	public boolean overlapCheck(KeyEvent e) {
		return userKey.containsValue(e.getKeyCode());
	}
	
	public void changeKey(String Key, int keyCode) {
		userSettedKey = Boolean.TRUE;
		userKey.replace(Key, keyCode);

		GameInput input = new GameInput();
		ItemInput itemInput = new ItemInput();
	}
	
	public void checkKey() {
		boolean exist = ( userKey.containsKey("LEFT") && userKey.containsKey("RIGHT") && userKey.containsKey("ROTATE") && userKey.containsKey("DOWN") && userKey.containsKey("DROP"));
		if (exist) {
			
		}
		
	}

	public String getStringKey(String KEY) {
    	
    	int keyCode = getUserKey(KEY);
    	
    	 if (keyCode >= KeyEvent.VK_0 && keyCode <= KeyEvent.VK_9 || keyCode >= KeyEvent.VK_A
    		        && keyCode <= KeyEvent.VK_Z) {
    		      return String.valueOf((char) keyCode);
    		    }
    		    switch (keyCode) {
    		    case KeyEvent.VK_COMMA:
    		      return "COMMA";
    		    case KeyEvent.VK_PERIOD:
    		      return "PERIOD";
    		    case KeyEvent.VK_SLASH:
    		      return "SLASH";
    		    case KeyEvent.VK_SEMICOLON:
    		      return "SEMICOLON";
    		    case KeyEvent.VK_EQUALS:
    		      return "EQUALS";
    		    case KeyEvent.VK_OPEN_BRACKET:
    		      return "OPEN_BRACKET";
    		    case KeyEvent.VK_BACK_SLASH:
    		      return "BACK_SLASH";
    		    case KeyEvent.VK_CLOSE_BRACKET:
    		      return "CLOSE_BRACKET";
    		    case KeyEvent.VK_ENTER:
    		      return "ENTER";
    		    case KeyEvent.VK_BACK_SPACE:
    		      return "BACK_SPACE";
    		    case KeyEvent.VK_TAB:
    		      return "TAB";
    		    case KeyEvent.VK_CANCEL:
    		      return "CANCEL";
    		    case KeyEvent.VK_CLEAR:
    		      return "CLEAR";
    		    case KeyEvent.VK_SHIFT:
    		      return "SHIFT";
    		    case KeyEvent.VK_CONTROL:
    		      return "CONTROL";
    		    case KeyEvent.VK_ALT:
    		      return "ALT";
    		    case KeyEvent.VK_PAUSE:
    		      return "PAUSE";
    		    case KeyEvent.VK_CAPS_LOCK:
    		      return "CAPS_LOCK";
    		    case KeyEvent.VK_ESCAPE:
    		      return "ESCAPE";
    		    case KeyEvent.VK_SPACE:
    		      return "SPACE";
    		    case KeyEvent.VK_PAGE_UP:
    		      return "PAGE_UP";
    		    case KeyEvent.VK_PAGE_DOWN:
    		      return "PAGE_DOWN";
    		    case KeyEvent.VK_END:
    		      return "END";
    		    case KeyEvent.VK_HOME:
    		      return "HOME";
    		    case KeyEvent.VK_LEFT:
    		      return "Left Arrow";
    		    case KeyEvent.VK_UP:
    		      return "Up Arrow";
    		    case KeyEvent.VK_RIGHT:
    		      return "Right Arrow";
    		    case KeyEvent.VK_DOWN:
    		      return "Down Arrow";
    		    case KeyEvent.VK_MULTIPLY:
    		      return "MULTIPLY";
    		    case KeyEvent.VK_ADD:
    		      return "ADD";
    		    case KeyEvent.VK_SEPARATOR:
    		      return "SEPARATOR";
    		    case KeyEvent.VK_SUBTRACT:
    		      return "SUBTRACT";
    		    case KeyEvent.VK_DECIMAL:
    		      return "DECIMAL";
    		    case KeyEvent.VK_DIVIDE:
    		      return "DIVIDE";
    		    case KeyEvent.VK_DELETE:
    		      return "DELETE";
    		    case KeyEvent.VK_NUM_LOCK:
    		      return "NUM_LOCK";
    		    case KeyEvent.VK_SCROLL_LOCK:
    		      return "SCROLL_LOCK";
    		    case KeyEvent.VK_F1:
    		      return "F1";
    		    case KeyEvent.VK_F2:
    		      return "F2";
    		    case KeyEvent.VK_F3:
    		      return "F3";
    		    case KeyEvent.VK_F4:
    		      return "F4";
    		    case KeyEvent.VK_F5:
    		      return "F5";
    		    case KeyEvent.VK_F6:
    		      return "F6";
    		    case KeyEvent.VK_F7:
    		      return "F7";
    		    case KeyEvent.VK_F8:
    		      return "F8";
    		    case KeyEvent.VK_F9:
    		      return "F9";
    		    case KeyEvent.VK_F10:
    		      return "F10";
    		    case KeyEvent.VK_F11:
    		      return "F11";
    		    case KeyEvent.VK_F12:
    		      return "F12";
    		    case KeyEvent.VK_F13:
    		      return "F13";
    		    case KeyEvent.VK_F14:
    		      return "F14";
    		    case KeyEvent.VK_F15:
    		      return "F15";
    		    case KeyEvent.VK_F16:
    		      return "F16";
    		    case KeyEvent.VK_F17:
    		      return "F17";
    		    case KeyEvent.VK_F18:
    		      return "F18";
    		    case KeyEvent.VK_F19:
    		      return "F19";
    		    case KeyEvent.VK_F20:
    		      return "F20";
    		    case KeyEvent.VK_F21:
    		      return "F21";
    		    case KeyEvent.VK_F22:
    		      return "F22";
    		    case KeyEvent.VK_F23:
    		      return "F23";
    		    case KeyEvent.VK_F24:
    		      return "F24";
    		    case KeyEvent.VK_PRINTSCREEN:
    		      return "PRINTSCREEN";
    		    case KeyEvent.VK_INSERT:
    		      return "INSERT";
    		    case KeyEvent.VK_HELP:
    		      return "HELP";
    		    case KeyEvent.VK_META:
    		      return "META";
    		    case KeyEvent.VK_BACK_QUOTE:
    		      return "BACK_QUOTE";
    		    case KeyEvent.VK_QUOTE:
    		      return "QUOTE";
    		    case KeyEvent.VK_KP_UP:
    		      return "KP_UP";
    		    case KeyEvent.VK_KP_DOWN:
    		      return "KP_DOWN";
    		    case KeyEvent.VK_KP_LEFT:
    		      return "KP_LEFT";
    		    case KeyEvent.VK_KP_RIGHT:
    		      return "KP_RIGHT";
    		    case KeyEvent.VK_DEAD_GRAVE:
    		      return "DEAD_GRAVE";
    		    case KeyEvent.VK_DEAD_ACUTE:
    		      return "DEAD_ACUTE";
    		    case KeyEvent.VK_DEAD_CIRCUMFLEX:
    		      return "DEAD_CIRCUMFLEX";
    		    case KeyEvent.VK_DEAD_TILDE:
    		      return "DEAD_TILDE";
    		    case KeyEvent.VK_DEAD_MACRON:
    		      return "DEAD_MACRON";
    		    case KeyEvent.VK_DEAD_BREVE:
    		      return "DEAD_BREVE";
    		    case KeyEvent.VK_DEAD_ABOVEDOT:
    		      return "DEAD_ABOVEDOT";
    		    case KeyEvent.VK_DEAD_DIAERESIS:
    		      return "DEAD_DIAERESIS";
    		    case KeyEvent.VK_DEAD_ABOVERING:
    		      return "DEAD_ABOVERING";
    		    case KeyEvent.VK_DEAD_DOUBLEACUTE:
    		      return "DEAD_DOUBLEACUTE";
    		    case KeyEvent.VK_DEAD_CARON:
    		      return "DEAD_CARON";
    		    case KeyEvent.VK_DEAD_CEDILLA:
    		      return "DEAD_CEDILLA";
    		    case KeyEvent.VK_DEAD_OGONEK:
    		      return "DEAD_OGONEK";
    		    case KeyEvent.VK_DEAD_IOTA:
    		      return "DEAD_IOTA";
    		    case KeyEvent.VK_DEAD_VOICED_SOUND:
    		      return "DEAD_VOICED_SOUND";
    		    case KeyEvent.VK_DEAD_SEMIVOICED_SOUND:
    		      return "DEAD_SEMIVOICED_SOUND";
    		    case KeyEvent.VK_AMPERSAND:
    		      return "AMPERSAND";
    		    case KeyEvent.VK_ASTERISK:
    		      return "ASTERISK";
    		    case KeyEvent.VK_QUOTEDBL:
    		      return "QUOTEDBL";
    		    case KeyEvent.VK_LESS:
    		      return "LESS";
    		    case KeyEvent.VK_GREATER:
    		      return "GREATER";
    		    case KeyEvent.VK_BRACELEFT:
    		      return "BRACELEFT";
    		    case KeyEvent.VK_BRACERIGHT:
    		      return "BRACERIGHT";
    		    case KeyEvent.VK_AT:
    		      return "AT";
    		    case KeyEvent.VK_COLON:
    		      return "COLON";
    		    case KeyEvent.VK_CIRCUMFLEX:
    		      return "CIRCUMFLEX";
    		    case KeyEvent.VK_DOLLAR:
    		      return "DOLLAR";
    		    case KeyEvent.VK_EURO_SIGN:
    		      return "EURO_SIGN";
    		    case KeyEvent.VK_EXCLAMATION_MARK:
    		      return "EXCLAMATION_MARK";
    		    case KeyEvent.VK_INVERTED_EXCLAMATION_MARK:
    		      return "INVERTED_EXCLAMATION_MARK";
    		    case KeyEvent.VK_LEFT_PARENTHESIS:
    		      return "LEFT_PARENTHESIS";
    		    case KeyEvent.VK_NUMBER_SIGN:
    		      return "NUMBER_SIGN";
    		    case KeyEvent.VK_MINUS:
    		      return "MINUS";
    		    case KeyEvent.VK_PLUS:
    		      return "PLUS";
    		    case KeyEvent.VK_RIGHT_PARENTHESIS:
    		      return "RIGHT_PARENTHESIS";
    		    case KeyEvent.VK_UNDERSCORE:
    		      return "UNDERSCORE";
    		    case KeyEvent.VK_FINAL:
    		      return "FINAL";
    		    case KeyEvent.VK_CONVERT:
    		      return "CONVERT";
    		    case KeyEvent.VK_NONCONVERT:
    		      return "NONCONVERT";
    		    case KeyEvent.VK_ACCEPT:
    		      return "ACCEPT";
    		    case KeyEvent.VK_MODECHANGE:
    		      return "MODECHANGE";
    		    case KeyEvent.VK_KANA:
    		      return "KANA";
    		    case KeyEvent.VK_KANJI:
    		      return "KANJI";
    		    case KeyEvent.VK_ALPHANUMERIC:
    		      return "ALPHANUMERIC";
    		    case KeyEvent.VK_KATAKANA:
    		      return "KATAKANA";
    		    case KeyEvent.VK_HIRAGANA:
    		      return "HIRAGANA";
    		    case KeyEvent.VK_FULL_WIDTH:
    		      return "FULL_WIDTH";
    		    case KeyEvent.VK_HALF_WIDTH:
    		      return "HALF_WIDTH";
    		    case KeyEvent.VK_ROMAN_CHARACTERS:
    		      return "ROMAN_CHARACTERS";
    		    case KeyEvent.VK_ALL_CANDIDATES:
    		      return "ALL_CANDIDATES";
    		    case KeyEvent.VK_PREVIOUS_CANDIDATE:
    		      return "PREVIOUS_CANDIDATE";
    		    case KeyEvent.VK_CODE_INPUT:
    		      return "CODE_INPUT";
    		    case KeyEvent.VK_JAPANESE_KATAKANA:
    		      return "JAPANESE_KATAKANA";
    		    case KeyEvent.VK_JAPANESE_HIRAGANA:
    		      return "JAPANESE_HIRAGANA";
    		    case KeyEvent.VK_JAPANESE_ROMAN:
    		      return "JAPANESE_ROMAN";
    		    case KeyEvent.VK_KANA_LOCK:
    		      return "KANA_LOCK";
    		    case KeyEvent.VK_INPUT_METHOD_ON_OFF:
    		      return "INPUT_METHOD_ON_OFF";

    		    case KeyEvent.VK_AGAIN:
    		      return "AGAIN";
    		    case KeyEvent.VK_UNDO:
    		      return "UNDO";
    		    case KeyEvent.VK_COPY:
    		      return "COPY";
    		    case KeyEvent.VK_PASTE:
    		      return "PASTE";
    		    case KeyEvent.VK_CUT:
    		      return "CUT";
    		    case KeyEvent.VK_FIND:
    		      return "FIND";
    		    case KeyEvent.VK_PROPS:
    		      return "PROPS";
    		    case KeyEvent.VK_STOP:
    		      return "STOP";

    		    case KeyEvent.VK_COMPOSE:
    		      return "COMPOSE";
    		    case KeyEvent.VK_ALT_GRAPH:
    		      return "ALT_GRAPH";
    		    }

    		    if (keyCode >= KeyEvent.VK_NUMPAD0 && keyCode <= KeyEvent.VK_NUMPAD9) {
    		      char c = (char) (keyCode - KeyEvent.VK_NUMPAD0 + '0');
    		      return "NUMPAD" + c;
    		    }

    		    return "unknown(0x" + Integer.toString(keyCode, 16) + ")";
    		  }

    
	
	private void colorBlindMode() {
		colorBlindMode= defaultKey.getDefaultColorBlindMode();
	}
	
	public boolean getColorBlindMode() {
		return colorBlindMode;
	}
	
	public void changeColorBlindMode() {
		if (colorBlindMode == false) {
			colorBlindMode = true;
		}
		else {
			colorBlindMode = false;
		}
	}

	

	private void difficulty_level() {
		difficulty_level.put("EASY", Boolean.TRUE);
		difficulty_level.put("NORMAL", Boolean.FALSE);
		difficulty_level.put("HARD", Boolean.FALSE);
	}
	
	public static String getDifficultyLevel() {
		if(difficulty_level.get("EASY")){
			return "EASY";
		}
		else if(difficulty_level.get("NORMAL")){
			return "NORMAL";
		}
		else if(difficulty_level.get("HARD")){
			return "HARD";
		}
		else {
			return "ERROR";
		}
	}
	
	public void changeLevel(String LEVEL) {
		difficulty_level.replace("EASY", Boolean.FALSE);
		difficulty_level.replace("NORMAL", Boolean.FALSE);
		difficulty_level.replace("HARD", Boolean.FALSE);

		difficulty_level.replace(LEVEL, Boolean.TRUE);
	}

	public static int getDifficultyIntLevel(){
		if (getDifficultyLevel() == "EASY"){
			return 3;
		}
		else if(getDifficultyLevel() == "NORMAL"){
			return 5;
		}
		else if(getDifficultyLevel() == "HARD"){
			return 7;
		} else {
			return 5;
		}
	}


	
	private void size() {
		size.put("SMALL", Boolean.TRUE);
		size.put("MIDDLE", Boolean.FALSE);
		size.put("BIG", Boolean.FALSE);
	}
	
	public String getSize() {
		if(size.get("SMALL")){
			return "SMALL";
		}
		else if(size.get("MIDDLE")){
			return "MIDDLE";
		}
		else if(size.get("BIG")){
			return "BIG";
		}
		else {
			return "ERROR";
		}
	}

	public void changeSize(String SIZE) {
		size.replace("SMALL", Boolean.FALSE);
		size.replace("MIDDLE", Boolean.FALSE);
		size.replace("BIG", Boolean.FALSE);
		
		size.replace(SIZE, Boolean.TRUE);
	}
	

}
