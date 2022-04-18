package thread;

import component.Home;

public class ItemLoop implements Runnable {

    private Home home;

    public ItemLoop(Home home) {
        this.home = home;
    }

    @Override
    public void run() {
        while(true) {
            home.itemGUI.itemLoop(home.homeFrame, home);
        }
    }
}
