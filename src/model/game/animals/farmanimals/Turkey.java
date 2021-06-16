package model.game.animals.farmanimals;

import controller.mission.time.TimeManager;
import model.game.missionmodel.MissionMap;
import model.game.products.rawproducts.Feather;

import static changes.FarmAnimal.TURKEY;

public class Turkey extends FarmAnimal{

    public Turkey(int x, int y, TimeManager timeManager) {
        super(TURKEY.animalName,x, y,timeManager,3);
    }

    @Override
    public void produce() {
        MissionMap.putProduct(new Feather(timeManager,x,y));
        timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,this);
    }
}
