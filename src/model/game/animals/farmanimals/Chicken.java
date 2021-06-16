package model.game.animals.farmanimals;

import controller.mission.time.TimeManager;
import model.game.missionmodel.MissionMap;
import model.game.products.rawproducts.Egg;
import java.util.Random;
import static changes.FarmAnimal.CHICKEN;
import static view.menu.color.Colors.colorPrintln;

public class Chicken extends FarmAnimal{

    public Chicken(int x, int y, TimeManager timeManager) {
        super(CHICKEN.animalName, x, y,timeManager);
    }

    @Override
    public void produce() {
        Random random = new Random();
        int x = random.nextInt(6);
        int y = random.nextInt(6);
        MissionMap.putProduct(new Egg(timeManager, x, y));
        colorPrintln("PRODUCED EGG");
    }
}
