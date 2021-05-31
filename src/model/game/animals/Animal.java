package model.game.animals;

import java.util.Random;

public abstract class Animal {
    float x,y,v;
    public void move(int mapBoundary){
        switch (new Random().nextInt()%4){
            case 0 -> x+=v;
            case 1 -> x-=v;
            case 2 -> y+=v;
            case 3 -> y-=v;
        }
        //TODO
    }
    public Animal(float x, float y, float v) {
        this.x = x;
        this.y = y;
        this.v = v;
    }
}
