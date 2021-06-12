package model.game.animals.farmanimals;

import controller.mission.time.TimeManager;
import model.game.products.rawproducts.Feather;
import model.game.products.rawproducts.RawProduct;

public class Turkey extends FarmAnimal{

    public Turkey(float x, float y, TimeManager timeManager) {
        super(x, y,timeManager);
    }

    @Override
    public RawProduct produce() {
        return new Feather(timeManager);
    }
}
