package model.game.animals.predatoranimals;

import controller.mission.time.TimeManager;

import static changes.PredatorAnimal.TIGER;

public class Tiger extends PredatorAnimal{
    public Tiger(TimeManager timeManager,int x, int y) {
        super(timeManager,x, y, TIGER);
    }
}
