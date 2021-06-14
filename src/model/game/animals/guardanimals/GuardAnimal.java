package model.game.animals.guardanimals;

import model.game.animals.Animal;

public abstract class GuardAnimal extends Animal {
    public static final int CAT_PRICE = 400;
    public static final int DOG_PRICE = 200;
    public GuardAnimal(int x, int y) {
        super(x, y, 1);
    }
}
