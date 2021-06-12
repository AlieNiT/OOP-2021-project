package model.game.workshops.primaryworkshop;

import controller.mission.time.Action;
import controller.mission.time.TimeManager;
import model.game.products.processedproducts.Flour;
import model.game.products.processedproducts.ProcessedProduct;
import model.game.missionmodel.Savable;
import model.game.missionmodel.Warehouse;
import view.menu.exceptions.GameErrorException;

//۱ .آسياب : وظيفه آسياب توليد آرد است. كارگران آسياب با استفاده از تخم مرغ، آرد توليد مي كنند!
//• هزينه ساخت آسياب: ۱۵۰ سكه
//• مدت زمان لازم براي توليد آرد: ۴ واحد زماني
public class WindmillWorkshop extends PrimaryWorkshop {
    public WindmillWorkshop(TimeManager timeManager) {
        super(4,timeManager);
    }

    @Override
    public ProcessedProduct produce() {
        isWorking = false;
        return new Flour(timeManager);
    }

    @Override
    public void consume() throws Exception {
        if (isWorking)
            throw new GameErrorException("The workshop is working.");
        Warehouse.hasSavable(Savable.EGG,1);
        Warehouse.removeSavable(Savable.EGG,1);
        timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,new Action(this));
        isWorking = true;
    }
}
