package model.game.animals.guardanimals;

import model.game.animals.Animal;

public abstract class GuardAnimal extends Animal {
    public GuardAnimal(String name,int x, int y) {
        super(null,name,x, y, 1);
    }
}
