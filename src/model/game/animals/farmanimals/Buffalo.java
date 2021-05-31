package model.game.animals.farmanimals;

import model.game.products.rawproducts.Milk;
import model.game.products.rawproducts.RawProduct;

public class Buffalo extends FarmAnimal{


    public Buffalo(float x, float y) {
        super(x, y);
    }

    @Override
    public RawProduct produce() {
        return new Milk();
    }
}
