package model.game.workshops.primaryworkshop;

import controller.mission.time.TimeManager;
import model.game.workshops.Workshop;

public abstract class PrimaryWorkshop extends Workshop {
    public PrimaryWorkshop(int production_time, TimeManager timeManager) {
        super(production_time,timeManager);
    }
    public abstract void produce();
}
