package model.game.animals.farmanimals;

import model.game.products.rawproducts.Feather;
import model.game.products.rawproducts.RawProduct;

public class Turkey extends FarmAnimal{

    public Turkey(float x, float y) {
        super(x, y);
    }

    @Override
    public RawProduct produce() {
        return new Feather();
    }
}
