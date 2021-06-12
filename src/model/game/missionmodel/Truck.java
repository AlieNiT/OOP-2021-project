package model.game.missionmodel;

import controller.mission.time.TimeManager;
import view.menu.exceptions.GameErrorException;

import java.util.HashMap;

public class Truck {
    static final int COMEBACK_TIME = 10;
    static boolean isAble = true;
    static final int capacity = 20;
    static int availableCapacity;
    static HashMap<String,Integer> things = new HashMap<>();

    public static void makeTruck(){
        things = new HashMap<>();
        availableCapacity = capacity;
        isAble = true;
    }

    private static void availabilityCheck() {
        if (!isAble) throw new GameErrorException("The truck is not available.");
    }

    public static void load(Savable savable) {
        availabilityCheck();
        if (availableCapacity<savable.volume)
            throw new GameErrorException("The truck is almost full");
        things.put(savable.name,things.get(savable.name)+1);
        availableCapacity -= savable.volume;
    }

    public static void unload(Savable savable) {
        availabilityCheck();
        availableCapacity += things.get(savable.name)*savable.volume;
        things.put(savable.name,0);
    }

    public static void go(TimeManager timeManager) {
        availabilityCheck();
        if (availableCapacity == capacity)
            throw new GameErrorException("There is nothing on the truck!");
        timeManager.putAction(timeManager.getTime()+COMEBACK_TIME,null);
        isAble = false;
    }

    public static int comeBack(){
        int returnedValue = 0;
        for (Savable savable : Savable.values())
            returnedValue += things.get(savable.name)*savable.price;
        things.clear();
        isAble = true;
        return returnedValue;
    }
}
