package model.game.workshops.primaryworkshop;

import controller.mission.time.Action;
import controller.mission.time.TimeManager;
import model.game.products.processedproducts.Cloth;
import model.game.products.processedproducts.ProcessedProduct;
import model.game.missionmodel.Savable;
import model.game.missionmodel.Warehouse;
import view.menu.exceptions.GameErrorException;

//parcheh bafi
//۲ .پارچه بافي : در اين كارگاه پرهاي گرفته شده از بوقلمون به پارچه تبديل مي شوند.
//• هزينه ساخت: ۲۵۰ سكه
//• مدت زمان لازم براي توليد پارچه: ۵ واحد زماني
public class WeavingWorkshop extends PrimaryWorkshop {
    public WeavingWorkshop(TimeManager timeManager) {
        super(5,timeManager);
    }

    @Override
    public ProcessedProduct produce() {
        isWorking = false;
        return new Cloth(timeManager);
    }


    @Override
    public void consume() throws Exception {
        if (isWorking)
            throw new GameErrorException("The workshop is working.");
        Warehouse.hasSavable(Savable.FEATHER,1);
        Warehouse.removeSavable(Savable.FEATHER,1);
        timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,new Action(this));
        isWorking = true;
    }
}
