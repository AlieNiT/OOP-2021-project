package model.game.workshops;

import controller.mission.time.TimeManager;
import model.game.Actioner;
import model.game.Consumer;
import model.game.Producer;
import view.menu.exceptions.GameErrorException;

public abstract class Workshop implements Consumer,Producer, Actioner {
    final String name;
    final int upgradeCost;
    protected boolean upgraded = false;
    protected final int PRODUCTION_TIME;
    protected TimeManager timeManager;
    protected boolean isWorking = false;
    protected Workshop(String name, int upgradeCost, int production_time, TimeManager timeManager) {
        this.name = name;
        this.upgradeCost = upgradeCost;
        this.timeManager = timeManager;
        PRODUCTION_TIME = production_time;
    }

    public int upgrade(int coins) {
        if (isWorking)
            throw new GameErrorException("Workshops can't be upgraded while working.");
        if (upgraded)
            throw new GameErrorException("This workshop is already upgraded.");
        if (coins<upgradeCost)
            throw new GameErrorException("You don't have enough coins.(upgrade cost: "+upgradeCost+" )");
        upgraded = true;
        return upgradeCost;
    }

    public boolean isUpgraded() { return upgraded;}
    public String getName() {
        return name;
    }

    public boolean isWorking() {
        return isWorking;
    }
}