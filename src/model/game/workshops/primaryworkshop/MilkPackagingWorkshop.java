package model.game.workshops.primaryworkshop;

import controller.mission.time.Action;
import controller.mission.time.TimeManager;
import model.game.missionmodel.Savable;
import model.game.missionmodel.Warehouse;
import model.game.products.processedproducts.PocketMilk;
import model.game.products.processedproducts.ProcessedProduct;
import view.menu.exceptions.GameErrorException;

//basteh bandi shir
//۳ .كارگاه بسته بندي شير : ورودي اين بخش شير بدست آمده از بوفالو و خروجي آن شير پاكتي است.
//• هزينه ساخت: ۴۰۰ سكه
//• مدت زمان لازم براي توليد شير پاكتي: ۶ واحد زماني
public class MilkPackagingWorkshop extends PrimaryWorkshop {

    public MilkPackagingWorkshop(TimeManager timeManager) {
        super(6,timeManager);
    }

    @Override
    public void consume() throws Exception {
        if (isWorking)
            throw new GameErrorException("The workshop is working.");
        Warehouse.hasSavable(Savable.MILK,1);
        Warehouse.removeSavable(Savable.MILK,1);
        timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,new Action(this));
        isWorking = true;
    }

    @Override
    public ProcessedProduct produce() {
        isWorking = false;
        return new PocketMilk(this.timeManager);
    }
}
