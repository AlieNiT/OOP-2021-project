package model.game.animals.farmanimals;

import controller.mission.time.TimeManager;
import model.game.missionmodel.MissionMap;
import model.game.products.rawproducts.Egg;

import java.util.Random;

public class Chicken extends FarmAnimal{


    public Chicken(int x, int y, TimeManager timeManager) {
        super(x, y,timeManager);
    }

    @Override
    public void produce() {
        Random random = new Random();
        int x = random.nextInt(6);
        int y = random.nextInt(6);
        MissionMap.putProduct(new Egg(timeManager,x,y),x,y);
    }
}
