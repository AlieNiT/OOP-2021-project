package model.game.workshops.secondaryworkshop;

import changes.Purchasable;
import controller.mission.time.TimeManager;
import model.game.missionmodel.MissionMap;
import model.game.missionmodel.Savable;
import model.game.missionmodel.Warehouse;
import model.game.products.finalproducts.Shirt;
import view.menu.exceptions.GameErrorException;

import java.util.Random;

//khayati
//۲ .خياطي : پارچه هاي بافته شده در اينجا به پيراهن تبديل مي شوند.
//• هزينه ساخت: ۴۰۰ سكه
//• مدت فرايند: ۶ واحد زماني
public class SewingWorkshop extends SecondaryWorkshop {

    public SewingWorkshop(TimeManager timeManager) {
        super(Purchasable.SEWING.getName(), 6,timeManager);
    }

    @Override
    public void consume() {
        if (isWorking)
            throw new GameErrorException("The workshop is working.");
        Warehouse.hasSavable(Savable.CLOTH,1);
        Warehouse.removeSavable(Savable.CLOTH);
        timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,this);
        isWorking = true;
    }

    @Override
    public void produce() {
        isWorking = false;
        Random random = new Random();
        int x = random.nextInt(6);
        int y = random.nextInt(6);
        MissionMap.putProduct(new Shirt(timeManager,x,y));
    }

}
