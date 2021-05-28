package model.game.animals.farmanimals;

import model.game.products.Product;
import model.game.products.rawproducts.Feather;

public class Turkey extends FarmAnimal{

    public Turkey(float x, float y) {
        super(x, y);
    }

    @Override
    public Product produce() {
        return new Feather();
    }
}
