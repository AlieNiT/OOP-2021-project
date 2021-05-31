package model.game.animals.farmanimals;

import model.game.animals.Animal;
import model.game.Producer;
import model.game.products.rawproducts.RawProduct;

public abstract class FarmAnimal extends Animal implements Producer {
    public static final int BUFFALO_PRICE = 400;
    public static final int TURKEY_PRICE = 200;
    public static final int CHICKEN_PRICE = 100;
    int x,y,health;

    public FarmAnimal(float x, float y) {
        super(x, y, 1);
        health = 100;
    }
    public abstract RawProduct produce();
    private void consume(){
        return;
    }
}
