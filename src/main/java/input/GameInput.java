package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
 
import setting.UserSetting;

public class GameInput implements KeyListener {

    private static final Map<Integer, Boolean> currentStates = new ConcurrentHashMap<Integer, Boolean>();

    UserSetting input = new UserSetting();
    
    public GameInput() {
        currentStates.put(input.getUserKey("LEFT"), Boolean.FALSE);
        currentStates.put(input.getUserKey("RIGHT"), Boolean.FALSE);
        currentStates.put(input.getUserKey("ROTATE"), Boolean.FALSE);
        currentStates.put(input.getUserKey("DROP"), Boolean.FALSE);
        currentStates.put(input.getUserKey("DOWN"), Boolean.FALSE);
        currentStates.put(KeyEvent.VK_F1, Boolean.FALSE);
        currentStates.put(KeyEvent.VK_ESCAPE, Boolean.FALSE);
        currentStates.put(KeyEvent.VK_F2, Boolean.FALSE);
        
    	System.out.println(currentStates.keySet());
    }

    public boolean left() {
        return keyDown(input.getUserKey("LEFT"));
    }

    public boolean right() {
        return keyDown(input.getUserKey("RIGHT"));
    }

    public boolean down() {
        return keyDown(input.getUserKey("DOWN"));
    }

    public boolean drop() {
        return keyDown(input.getUserKey("DROP"));
    }

    public boolean rotate() {
        return keyDown(input.getUserKey("ROTATE"));
    }

    public boolean pauseGame() {
        return keyDown(KeyEvent.VK_ESCAPE);
    }

    public boolean newGame() {
        return keyDown(KeyEvent.VK_F1);
    }

    public boolean home() {
        return keyDown(KeyEvent.VK_F2);
    }

    private boolean keyDown(int keyCode) {
        return currentStates.put(keyCode, Boolean.FALSE);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (currentStates.containsKey(keyEvent.getKeyCode())) {
            currentStates.put(keyEvent.getKeyCode(), Boolean.TRUE);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}