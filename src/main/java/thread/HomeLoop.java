package thread;

import component.Home;

public class HomeLoop implements Runnable {

    private Home home;

    public HomeLoop(Home home) {
        this.home = home;
    }

    @Override
    public void run() {
        while(true) {
            home.homeLoop();
        }
    }
}
