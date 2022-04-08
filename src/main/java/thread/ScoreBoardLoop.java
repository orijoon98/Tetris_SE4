package thread;

import component.Home;

public class ScoreBoardLoop implements Runnable {

    private Home home;

    public ScoreBoardLoop(Home home) {
        this.home = home;
    }

    @Override
    public void run() {
        while(true) {
            home.scoreBoardGUI.scoreBoardLoop(home.homeFrame);
        }
    }
}