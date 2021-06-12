package model.game.workshops.secondaryworkshop;

import controller.mission.time.Action;
import controller.mission.time.TimeManager;
import model.game.missionmodel.Savable;
import model.game.missionmodel.Warehouse;
import model.game.products.finalproducts.FinalProduct;
import model.game.products.finalproducts.IceCream;
import view.menu.exceptions.GameErrorException;

//bastaniforooshi
//۳ .بستني فروشي : در اين كارگاه از شيرپاكتي توليد شده بستني توليد مي شود.
//• هزينه ساخت: ۵۵۰ سكه
//• مدت زمان فرايند: ۷ واحد زماني
public class IceCreamWorkshop extends SecondaryWorkshop {
    public IceCreamWorkshop(TimeManager timeManager) {
        super(7,timeManager);
    }

    @Override
    public void consume() throws Exception {
        if (isWorking)
            throw new GameErrorException("The workshop is working.");
        Warehouse.hasSavable(Savable.POCKET_MILK,1);
        Warehouse.removeSavable(Savable.POCKET_MILK,1);
        timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,new Action(this));
        isWorking = true;
    }

    @Override
    public FinalProduct produce() {
        isWorking = false;
        return new IceCream(timeManager);
    }
}
