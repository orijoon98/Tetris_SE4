package thread;

import component.Home;

public class ScoreBoardItemLoop implements Runnable {

    private Home home;

    public ScoreBoardItemLoop(Home home) {
        this.home = home;
    }

    @Override
    public void run() {
        while(true) {
            home.scoreBoardGUI.scoreBoardItemGUI.scoreBoardItemLoop(home.homeFrame, home.scoreBoardGUI.scoreBoardFrame);
        }
    }
}