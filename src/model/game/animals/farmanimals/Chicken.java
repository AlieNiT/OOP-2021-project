package model.game.animals.farmanimals;

import model.game.products.Product;
import model.game.products.rawproducts.Egg;

public class Chicken extends FarmAnimal{


    public Chicken(float x, float y) {
        super(x, y);
    }

    @Override
    public Product produce() {
        return new Egg();
    }
}
