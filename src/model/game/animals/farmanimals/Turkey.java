package model.game.animals.farmanimals;

import controller.mission.time.TimeManager;
import model.game.missionmodel.MissionMap;
import model.game.products.rawproducts.Feather;

import java.util.Random;

public class Turkey extends FarmAnimal{

    public Turkey(int x, int y, TimeManager timeManager) {
        super(x, y,timeManager);
    }

    @Override
    public void produce() {
        Random random = new Random();
        int x = random.nextInt()*6;
        int y = random.nextInt()*6;
        MissionMap.putProduct(new Feather(timeManager,x,y),x,y);
    }
}
