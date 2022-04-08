package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScoreBoardInput implements KeyListener {

    private final Map<Integer, Boolean> currentStates = new ConcurrentHashMap<Integer, Boolean>();

    public ScoreBoardInput() {
        currentStates.put(KeyEvent.VK_UP, Boolean.FALSE);
        currentStates.put(KeyEvent.VK_DOWN, Boolean.FALSE);
        currentStates.put(KeyEvent.VK_ENTER, Boolean.FALSE);
    }

    public boolean up() {
        return keyDown(KeyEvent.VK_UP);
    }

    public boolean down() {
        return keyDown(KeyEvent.VK_DOWN);
    }

    public boolean enter() {
        return keyDown(KeyEvent.VK_ENTER);
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