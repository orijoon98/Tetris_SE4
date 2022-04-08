package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScoreBoardItemInput implements KeyListener {

    private final Map<Integer, Boolean> currentStates = new ConcurrentHashMap<Integer, Boolean>();

    public ScoreBoardItemInput() {
        currentStates.put(KeyEvent.VK_LEFT, Boolean.FALSE);
        currentStates.put(KeyEvent.VK_RIGHT, Boolean.FALSE);
        currentStates.put(KeyEvent.VK_ENTER, Boolean.FALSE);
    }

    public boolean left() {
        return keyDown(KeyEvent.VK_LEFT);
    }

    public boolean right() {
        return keyDown(KeyEvent.VK_RIGHT);
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