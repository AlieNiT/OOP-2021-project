package model.game.workshops.secondaryworkshop;

import controller.mission.time.TimeManager;
import model.game.products.finalproducts.FinalProduct;
import model.game.workshops.Workshop;

public abstract class SecondaryWorkshop extends Workshop {
    public SecondaryWorkshop(int production_time, TimeManager timeManager) {
        super(production_time,timeManager);
    }
    public abstract FinalProduct produce();
}
