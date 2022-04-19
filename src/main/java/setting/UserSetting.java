package setting;
/* 
 * [] 각 키를 바꿀 수 있는 함수
 * [] 각 키가 바뀌었을 때 setted true로 바꾸기
*/
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import setting.DefaultSetting;

public class UserSetting {
	DefaultSetting defaultKey = new DefaultSetting();

	private final Map<String, Integer> userKey = new ConcurrentHashMap<String, Integer>(); 

	
    public boolean userSettedKey = Boolean.FALSE;
//    private final Map<String, Integer> userKey = new ConcurrentHashMap<String, Integer>(); 
	
	public UserSetting() {
		UserKey();		
    }
	
	private void UserKey() {		
    	userKey.put("LEFT", defaultKey.getDefaultKey("LEFT"));
    	userKey.put("RIGHT", defaultKey.getDefaultKey("RIGHT"));
    	userKey.put("ROTATE", defaultKey.getDefaultKey("ROTATE"));
    	userKey.put("DROP", defaultKey.getDefaultKey("DROP"));
    	userKey.put("DOWN", defaultKey.getDefaultKey("DOWN"));
    	userSettedKey = Boolean.TRUE;
	}
		
	
	public int getUserKey(String KEY) {
		return userKey.get(KEY);
	}
	
	public void changeKey(String Key, int keyCode) {
		userSettedKey = Boolean.TRUE;
		userKey.replace(Key, keyCode);
	}
	
	//마무리!!!!!!!
	public void checkKey() {
		boolean exist = ( userKey.containsKey("LEFT") && userKey.containsKey("RIGHT") && userKey.containsKey("ROTATE") && userKey.containsKey("DOWN") && userKey.containsKey("DROP"));
		if (exist) {
			
		}
		
	}

	

}
