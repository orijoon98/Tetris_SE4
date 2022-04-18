package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ItemInput implements KeyListener {

    private final Map<Integer, Boolean> currentStates = new ConcurrentHashMap<Integer, Boolean>();

    public ItemInput() {
        currentStates.put(KeyEvent.VK_LEFT, Boolean.FALSE);
        currentStates.put(KeyEvent.VK_RIGHT, Boolean.FALSE);
        currentStates.put(KeyEvent.VK_DOWN, Boolean.FALSE);
        currentStates.put(KeyEvent.VK_UP, Boolean.FALSE);
        currentStates.put(KeyEvent.VK_SPACE, Boolean.FALSE);
        currentStates.put(KeyEvent.VK_F1, Boolean.FALSE);
        currentStates.put(KeyEvent.VK_ESCAPE, Boolean.FALSE);
        currentStates.put(KeyEvent.VK_F2, Boolean.FALSE);
    }

    public boolean left() {
        return keyDown(KeyEvent.VK_LEFT);
    }

    public boolean right() {
        return keyDown(KeyEvent.VK_RIGHT);
    }

    public boolean down() {
        return keyDown(KeyEvent.VK_DOWN);
    }

    public boolean drop() {
        return keyDown(KeyEvent.VK_SPACE);
    }

    public boolean rotate() {
        return keyDown(KeyEvent.VK_UP);
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