package model.game.workshops.secondaryworkshop;

import controller.mission.time.Action;
import controller.mission.time.TimeManager;
import model.game.missionmodel.Savable;
import model.game.missionmodel.Warehouse;
import model.game.products.finalproducts.Bread;
import model.game.products.finalproducts.FinalProduct;
import view.menu.exceptions.GameErrorException;

//nanvayi
//۱ .نانوايي : ورودي نانوايي آرد است و پس از پخت و پز در خروجي به شما نان تحويل داده مي شود.
//• هزينه ساخت نانوايي: ۲۵۰ سكه
//• مدت زمان لازم براي فرآيند پخت: ۵ واحد زماني
public class Bakery extends SecondaryWorkshop {

    public Bakery(TimeManager timeManager) {
        super(5,timeManager);
    }



    @Override
    public void consume() throws Exception {
        if (isWorking)
            throw new GameErrorException("The workshop is working.");
        Warehouse.hasSavable(Savable.FLOUR,1);
        Warehouse.removeSavable(Savable.FLOUR,1);
        timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,new Action(this));
        isWorking = true;
    }

    @Override
    public FinalProduct produce() {
        isWorking = false;
        return new Bread(timeManager);
    }
}
