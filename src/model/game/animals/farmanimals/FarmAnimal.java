package model.game.animals.farmanimals;

import controller.mission.time.TimeManager;
import model.game.Actioner;
import model.game.Producer;
import model.game.animals.Animal;

public abstract class FarmAnimal extends Animal implements Actioner, Producer {
    final int PRODUCTION_TIME;
    int health;
    public FarmAnimal(String name, int x, int y,TimeManager timeManager,int productionTime) {
        super(timeManager,name,x, y, 1);
        PRODUCTION_TIME = productionTime;
        timeManager.putAction(timeManager.getTime()+PRODUCTION_TIME,this);
        health = 100;
    }

    public void move(int mapSize, int[] grassDirection){
        if (isStarving()&&grassDirection != null){
            if (grassDirection[0]!=0) x += grassDirection[0]/Math.abs(grassDirection[0]);
            else if(grassDirection[1]!=0) y += grassDirection[1]/Math.abs(grassDirection[1]);
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
        return health<=50;
    }

    public int getHealth() {
        return health;
    }
}
