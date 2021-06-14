package model.game.animals.farmanimals;

import controller.mission.time.TimeManager;
import model.game.Actioner;
import model.game.Producer;
import model.game.animals.Animal;

public abstract class FarmAnimal extends Animal implements Actioner, Producer {
    public static final int BUFFALO_PRICE = 400;
    public static final int TURKEY_PRICE = 200;
    public static final int CHICKEN_PRICE = 100;
    int x,y,health;
    TimeManager timeManager;
    public FarmAnimal(int x, int y,TimeManager timeManager) {
        super(x, y, 1);
        health = 100;
        this.timeManager = timeManager;
    }

    public void move(int mapSize, int[] grassDirection){
        if (isStarving()&&grassDirection != null){
            if (grassDirection[0]!=0) x += grassDirection[0]/Math.abs(grassDirection[0]);
            else y += grassDirection[1]/Math.abs(grassDirection[1]);
        }
        else this.move(mapSize);
    }

    public abstract void produce();

    public boolean reduceHealth() {
        health-=10;
        if (health<=0)
            return true;
        return false;
    }

    public void graze() {
        health = 100;
    }
    public boolean isStarving() {
        return health<50;
    }
}
