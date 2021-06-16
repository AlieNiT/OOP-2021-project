package model.game.animals.farmanimals;

import controller.mission.time.TimeManager;
import model.game.missionmodel.MissionMap;
import model.game.products.rawproducts.Egg;

import static changes.FarmAnimal.CHICKEN;

public class Chicken extends FarmAnimal{

    public Chicken(int x, int y, TimeManager timeManager) {
        super(CHICKEN.animalName, x, y,timeManager,2);
    }

    @Override
    public void produce() {
        MissionMap.putProduct(new Egg(timeManager, x, y));
        timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,this);
    }
}
