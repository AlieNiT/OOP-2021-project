package model.game.animals.farmanimals;

import controller.mission.time.TimeManager;
import model.Actioner;
import model.game.animals.Animal;
import model.game.products.rawproducts.RawProduct;

public abstract class FarmAnimal extends Animal implements Actioner {
    public static final int BUFFALO_PRICE = 400;
    public static final int TURKEY_PRICE = 200;
    public static final int CHICKEN_PRICE = 100;
    int x,y,health;
    TimeManager timeManager;
    public FarmAnimal(float x, float y,TimeManager timeManager) {
        super(x, y, 1);
        health = 100;
        this.timeManager = timeManager;
    }

    public abstract RawProduct produce();
}
