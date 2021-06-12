package model.game.products;

import controller.mission.time.Action;
import controller.mission.time.TimeManager;
import model.Actioner;
import model.game.Mapable;

public abstract class Product implements Mapable, Actioner {
    final static int ROT_TIME = 4;
    public Product(TimeManager timeManager){
        timeManager.putAction(timeManager.getTime()+ROT_TIME,new Action(this));
    }
}
