package model.game.animals.predatoranimals;
import controller.mission.time.TimeManager;

import static changes.PredatorAnimal.BEAR;

public class Bear extends PredatorAnimal{
    public Bear(TimeManager timeManager, int x, int y) {
        super(timeManager,x, y, BEAR);
    }
}
