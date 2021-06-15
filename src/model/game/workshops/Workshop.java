package model.game.workshops;

import controller.mission.time.TimeManager;
import model.game.Actioner;
import model.game.Consumer;
import model.game.Producer;

public abstract class Workshop implements Consumer,Producer, Actioner {
    final String name;
    protected final int PRODUCTION_TIME;
    protected TimeManager timeManager;
    protected boolean isWorking = false;
    protected Workshop(String name, int production_time, TimeManager timeManager) {
        this.name = name;
        this.timeManager = timeManager;
        PRODUCTION_TIME = production_time;
    }

    public String getName() {
        return name;
    }

    public boolean isWorking() {
        return isWorking;
    }
}