package model.game.animals.predatoranimals;

import model.game.animals.Animal;

public abstract class PredatorAnimal extends Animal {
    int cagesLeft;
    final int cagesNeeded;
    public PredatorAnimal(int x, int y, changes.PredatorAnimal predatorAnimal) {
        super(x, y, predatorAnimal.speed);
        cagesNeeded = predatorAnimal.cageCommands;
        this.cagesLeft = cagesNeeded;
    }
    public boolean cageTry() {
        cagesLeft -= 1;
        return cagesLeft == 0;
    }
    public void cageBreak() {
        if (cagesLeft<cagesNeeded)
            cagesLeft += 1;
    }
}
