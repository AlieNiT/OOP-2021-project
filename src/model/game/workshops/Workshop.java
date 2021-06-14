package model.game.workshops;

import controller.mission.time.TimeManager;
import model.game.Actioner;
import model.game.Consumer;
import model.game.Producer;

public abstract class Workshop implements Consumer,Producer, Actioner {
    protected final int PRODUCTION_TIME;
    protected TimeManager timeManager;
    protected boolean isWorking = false;
    protected Workshop(int production_time,TimeManager timeManager) {
        this.timeManager = timeManager;
        PRODUCTION_TIME = production_time;
    }
}