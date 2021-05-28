package model.game.animals.farmanimals;

import model.game.animals.Animal;
import model.game.products.Producer;
import model.game.products.Product;

public abstract class FarmAnimal extends Animal implements Producer {
    protected static int PRICE;
    int x,y,health;

    public FarmAnimal(float x, float y) {
        super(x, y, 1);
    }

    public abstract Product produce();
}
