package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import setting.InputSetting;

public class GameInput implements KeyListener {
	
	private InputSetting InputSetting = new InputSetting();
	private int left = InputSetting.leftKey();
    private int right = InputSetting.rightKey();
    private int rotate = InputSetting.rotateKey();
    private int drop = InputSetting.dropKey();


    private final Map<Integer, Boolean> currentStates = new ConcurrentHashMap<Integer, Boolean>();
    
    public GameInput() {
        currentStates.put(left, Boolean.FALSE);
        currentStates.put(right, Boolean.FALSE);
        currentStates.put(rotate, Boolean.FALSE);
        currentStates.put(drop, Boolean.FALSE);
        currentStates.put(KeyEvent.VK_F1, Boolean.FALSE);
        currentStates.put(KeyEvent.VK_ESCAPE, Boolean.FALSE);
        currentStates.put(KeyEvent.VK_F2, Boolean.FALSE);
    }

    public boolean left() {
        return keyDown(left);
    }

    public boolean right() {
        return keyDown(right);
    }

    public boolean drop() {
        return keyDown(drop);
    }

    public boolean rotate() {
        return keyDown(rotate);
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