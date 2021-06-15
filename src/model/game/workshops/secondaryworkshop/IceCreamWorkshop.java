package model.game.workshops.secondaryworkshop;

import changes.Purchasable;
import controller.mission.time.TimeManager;
import model.game.missionmodel.MissionMap;
import model.game.missionmodel.Savable;
import model.game.missionmodel.Warehouse;
import model.game.products.finalproducts.IceCream;
import view.menu.exceptions.GameErrorException;

import java.util.Random;

//bastaniforooshi
//۳ .بستني فروشي : در اين كارگاه از شيرپاكتي توليد شده بستني توليد مي شود.
//• هزينه ساخت: ۵۵۰ سكه
//• مدت زمان فرايند: ۷ واحد زماني
public class IceCreamWorkshop extends SecondaryWorkshop {
    public IceCreamWorkshop(TimeManager timeManager) {
        super(Purchasable.ICE_CREAM.getName(), 7,timeManager);
    }

    @Override
    public void consume() {
        if (isWorking)
            throw new GameErrorException("The workshop is working.");
        Warehouse.hasSavable(Savable.PACKET_MILK,1);
        Warehouse.removeSavable(Savable.PACKET_MILK,1);
        timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,this);
        isWorking = true;
    }

    @Override
    public void produce() {
        isWorking = false;
        Random random = new Random();
        int x = random.nextInt()*6;
        int y = random.nextInt()*6;
        MissionMap.putProduct(new IceCream(timeManager,x,y));
    }


}
