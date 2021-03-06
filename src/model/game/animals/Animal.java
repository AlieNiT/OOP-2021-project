package model.game.animals;

import controller.mission.time.TimeManager;
import model.game.Mappable;

import java.util.Random;

public abstract class Animal implements Mappable {
    protected int x, y, v;
    String name;
    protected TimeManager timeManager;
    public void move(int mapSize) {
        switch (new Random().nextInt(4)) {
            case 0 -> {
                x += v;
                if (x >= mapSize) x -= 2 * (x - (mapSize - 1));
            }
            case 1 -> {
                x -= v;
                if (x < 0) x *= -1;
            }
            case 2 -> {
                y = (y + v);
                if (y >= mapSize) y -= 2 * (y - (mapSize - 1));
            }
            case 3 -> {
                y -= v;
                if (y < 0) y *= -1;
            }
        }
    }

    public Animal(TimeManager timeManager,String name,int x, int y, int v) {
        this.timeManager = timeManager;
        this.name = name;
        this.x = x;
        this.y = y;
        this.v = v;
    }
    public String getName(){return name;}
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
