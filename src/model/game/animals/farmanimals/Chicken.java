package model.game.animals.farmanimals;

import model.game.products.rawproducts.Egg;
import model.game.products.rawproducts.RawProduct;

public class Chicken extends FarmAnimal{


    public Chicken(float x, float y) {
        super(x, y);
    }

    @Override
    public RawProduct produce() { return new Egg(); }
}
