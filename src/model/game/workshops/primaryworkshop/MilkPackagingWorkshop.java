package model.game.workshops.primaryworkshop;

import controller.mission.time.TimeManager;
import model.game.missionmodel.MissionMap;
import model.game.missionmodel.Savable;
import model.game.missionmodel.Warehouse;
import model.game.products.processedproducts.PacketMilk;
import view.menu.exceptions.GameErrorException;

import java.util.Random;

//basteh bandi shir
//۳ .كارگاه بسته بندي شير : ورودي اين بخش شير بدست آمده از بوفالو و خروجي آن شير پاكتي است.
//• هزينه ساخت: ۴۰۰ سكه
//• مدت زمان لازم براي توليد شير پاكتي: ۶ واحد زماني
public class MilkPackagingWorkshop extends PrimaryWorkshop {

    public MilkPackagingWorkshop(TimeManager timeManager) {
        super(6,timeManager);
    }

    @Override
    public void consume(){
        if (isWorking)
            throw new GameErrorException("The workshop is working.");
        Warehouse.hasSavable(Savable.MILK,1);
        Warehouse.removeSavable(Savable.MILK,1);
        timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,this);
        isWorking = true;
    }

    @Override
    public void produce() {
        isWorking = false;
        Random random = new Random();
        int x = random.nextInt()*6;
        int y = random.nextInt()*6;
        MissionMap.putProduct(new PacketMilk(timeManager,x,y),x,y);
    }


}
