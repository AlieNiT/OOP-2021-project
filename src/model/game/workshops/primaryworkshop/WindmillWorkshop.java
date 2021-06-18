package model.game.workshops.primaryworkshop;

import changes.Purchasable;
import controller.mission.time.TimeManager;
import model.game.missionmodel.MissionMap;
import model.game.missionmodel.Savable;
import model.game.missionmodel.Warehouse;
import model.game.products.processedproducts.Flour;
import view.menu.exceptions.GameErrorException;

import java.util.Random;

//۱ .آسياب : وظيفه آسياب توليد آرد است. كارگران آسياب با استفاده از تخم مرغ، آرد توليد مي كنند!
//• هزينه ساخت آسياب: ۱۵۰ سكه
//• مدت زمان لازم براي توليد آرد: ۴ واحد زماني
public class WindmillWorkshop extends PrimaryWorkshop {
    public WindmillWorkshop(TimeManager timeManager) {
        super(Purchasable.WINDMILL.getName(), 4,timeManager);
    }

    @Override
    public void produce() {
        isWorking = false;
        Random random = new Random();
        int x = random.nextInt(6);
        int y = random.nextInt(6);
        MissionMap.putProduct(new Flour(timeManager,x,y));
    }

    @Override
    public void consume() {
        if (isWorking)
            throw new GameErrorException("The workshop is working.");
        Warehouse.removeSavable(Savable.EGG);
        timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,this);
        isWorking = true;
    }

}
