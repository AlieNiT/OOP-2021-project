package model.game.animals.predatoranimals;
import controller.mission.time.TimeManager;

import static changes.PredatorAnimal.LION;

public class Lion extends PredatorAnimal{
    public Lion(TimeManager timeManager, int x, int y) {
        super(timeManager,x, y, LION);
    }
}
