package model.game.workshops.primaryworkshop;

import controller.mission.time.TimeManager;
import model.game.workshops.Workshop;

public abstract class PrimaryWorkshop extends Workshop {
    public PrimaryWorkshop(String name,int production_time, TimeManager timeManager) {
        super(name, production_time,timeManager);
    }
    public abstract void produce();
}
