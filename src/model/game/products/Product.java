package model.game.products;

import controller.mission.time.TimeManager;
import model.game.Actioner;
import model.game.Mappable;

public abstract class Product implements Mappable, Actioner {
    final int x,y;
    public Product(TimeManager timeManager,int rotTime, int x, int y){
        this.x = x;
        this.y = y;
        timeManager.putAction(timeManager.getTime()+rotTime,this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
