package model.game.animals.farmanimals;

import controller.mission.time.TimeManager;
import model.game.products.rawproducts.Milk;
import model.game.products.rawproducts.RawProduct;

public class Buffalo extends FarmAnimal{

    public Buffalo(float x, float y, TimeManager timeManager) {
        super(x, y,timeManager);
    }

    @Override
    public RawProduct produce() {
        return new Milk(timeManager);
    }
}
