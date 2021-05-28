package model.game.animals.farmanimals;

import model.game.products.Product;
import model.game.products.rawproducts.Milk;

public class Buffalo extends FarmAnimal{


    public Buffalo(float x, float y) {
        super(x, y);
    }

    @Override
    public Product produce() {
        return new Milk();
    }
}
