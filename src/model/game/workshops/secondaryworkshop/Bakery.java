package model.game.workshops.secondaryworkshop;

import changes.Purchasable;
import controller.mission.time.TimeManager;
import model.game.missionmodel.MissionMap;
import model.game.missionmodel.Savable;
import model.game.missionmodel.Warehouse;
import model.game.products.finalproducts.Bread;
import view.menu.exceptions.GameErrorException;

import java.util.Random;

//nanvayi
//۱ .نانوايي : ورودي نانوايي آرد است و پس از پخت و پز در خروجي به شما نان تحويل داده مي شود.
//• هزينه ساخت نانوايي: ۲۵۰ سكه
//• مدت زمان لازم براي فرآيند پخت: ۵ واحد زماني
public class Bakery extends SecondaryWorkshop {

    public Bakery(TimeManager timeManager) {
        super(Purchasable.BAKERY.getName(), 5,timeManager);
    }



    @Override
    public void consume() {
        if (isWorking)
            throw new GameErrorException("The workshop is working.");
        if (upgraded){
            try {
                Warehouse.hasSavable(Savable.FLOUR,2);
                timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,this);
                timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,this);
            } catch (GameErrorException e) {
                Warehouse.removeSavable(Savable.FLOUR);
                timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME/2,this);
            };
        }
        else {
            Warehouse.removeSavable(Savable.FLOUR);
            timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,this);
        }
        isWorking = true;
    }

    @Override
    public void produce() {
        isWorking = false;
        Random random = new Random();
        int x = random.nextInt(6);
        int y = random.nextInt(6);
        MissionMap.putProduct(new Bread(timeManager, x, y));
    }


}
