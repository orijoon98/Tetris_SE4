package thread;

import component.Home;

public class GameLoop implements Runnable {

    private Home home;

    public GameLoop(Home home) {
        this.home = home;
    }

    @Override
    public void run() {
        while(true) {
            home.gameGUI.gameLoop(home.homeFrame, home);
        }
    }
}
