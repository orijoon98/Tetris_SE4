package thread;

import component.Home;

public class ScoreBoardNormalLoop implements Runnable {

    private Home home;

    public ScoreBoardNormalLoop(Home home) {
        this.home = home;
    }

    @Override
    public void run() {
        while(true) {
            home.scoreBoardGUI.scoreBoardNormalGUI.scoreBoardNormalLoop(home.homeFrame, home.scoreBoardGUI.scoreBoardFrame);
        }
    }
}