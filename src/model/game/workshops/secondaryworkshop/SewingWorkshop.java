package model.game.workshops.secondaryworkshop;

import controller.mission.time.Action;
import controller.mission.time.TimeManager;
import model.game.missionmodel.Savable;
import model.game.missionmodel.Warehouse;
import model.game.products.finalproducts.FinalProduct;
import model.game.products.finalproducts.Shirt;
import view.menu.exceptions.GameErrorException;

//khayati
//۲ .خياطي : پارچه هاي بافته شده در اينجا به پيراهن تبديل مي شوند.
//• هزينه ساخت: ۴۰۰ سكه
//• مدت فرايند: ۶ واحد زماني
public class SewingWorkshop extends SecondaryWorkshop {

    public SewingWorkshop(TimeManager timeManager) {
        super(6,timeManager);
    }

    @Override
    public void consume() throws Exception {
        if (isWorking)
            throw new GameErrorException("The workshop is working.");
        Warehouse.hasSavable(Savable.CLOTH,1);
        Warehouse.removeSavable(Savable.CLOTH,1);
        timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,new Action(this));
        isWorking = true;
    }

    @Override
    public FinalProduct produce() {
        isWorking = false;
        return new Shirt(timeManager);
    }
}
