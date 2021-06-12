package model.game.animals.farmanimals;

import controller.mission.time.TimeManager;
import model.game.products.rawproducts.Egg;
import model.game.products.rawproducts.RawProduct;

public class Chicken extends FarmAnimal{


    public Chicken(float x, float y, TimeManager timeManager) {
        super(x, y,timeManager);
    }

    @Override
    public RawProduct produce() { return new Egg(timeManager); }
}
