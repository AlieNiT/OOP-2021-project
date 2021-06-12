package model.game.animals.predatoranimals;

import model.game.animals.Animal;

public abstract class PredatorAnimal extends Animal {
    int cagesLeft;
    public PredatorAnimal(float x, float y, changes.PredatorAnimal predatorAnimal) {
        super(x, y, predatorAnimal.speed);
        this.cagesLeft = predatorAnimal.cageCommands;
    }
}
