package model.game.workshops.primaryworkshop;

import changes.Purchasable;
import controller.mission.time.TimeManager;
import model.game.missionmodel.MissionMap;
import model.game.missionmodel.Savable;
import model.game.missionmodel.Warehouse;
import model.game.products.processedproducts.Cloth;
import view.menu.exceptions.GameErrorException;

import java.util.Random;

//parcheh bafi
//۲ .پارچه بافي : در اين كارگاه پرهاي گرفته شده از بوقلمون به پارچه تبديل مي شوند.
//• هزينه ساخت: ۲۵۰ سكه
//• مدت زمان لازم براي توليد پارچه: ۵ واحد زماني
public class WeavingWorkshop extends PrimaryWorkshop {
    public WeavingWorkshop(TimeManager timeManager) {
        super(Purchasable.WEAVING.getName(), 5,timeManager);
    }

    @Override
    public void produce() {
        isWorking = false;
        Random random = new Random();
        int x = random.nextInt(6);
        int y = random.nextInt(6);
        MissionMap.putProduct(new Cloth(timeManager,x,y));
    }


    @Override
    public void consume() {
        if (isWorking)
            throw new GameErrorException("The workshop is working.");
        Warehouse.removeSavable(Savable.FEATHER);
        timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,this);
        isWorking = true;
    }


}
