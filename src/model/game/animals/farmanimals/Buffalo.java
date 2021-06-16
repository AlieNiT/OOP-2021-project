package model.game.animals.farmanimals;

import controller.mission.time.TimeManager;
import model.game.missionmodel.MissionMap;
import model.game.products.rawproducts.Milk;

import static changes.FarmAnimal.BUFFALO;

public class Buffalo extends FarmAnimal{

    public Buffalo(int x, int y, TimeManager timeManager) {
        super(BUFFALO.animalName,x, y,timeManager,5);
    }

    @Override
    public void produce() {
        MissionMap.putProduct(new Milk(timeManager,x,y));
        timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,this);
    }

}
