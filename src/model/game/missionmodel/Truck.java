package model.game.missionmodel;

import controller.mission.Log;
import controller.mission.time.TimeManager;
import view.menu.exceptions.GameErrorException;
import java.util.HashMap;
import java.util.Map;

public class Truck {
    static final int COMEBACK_TIME = 10;
    static boolean isAble = true;
    static final int capacity = 20;
    static int availableCapacity;
    static HashMap<String, Integer> things = new HashMap<>();

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
        if (availableCapacity == 0) throw new GameErrorException("The truck is full!");
        if (availableCapacity < savable.volume) throw new GameErrorException("The truck is almost full");
        things.putIfAbsent(savable.name, 0);
        things.put(savable.name, things.get(savable.name) + 1);
        System.out.println(thingCount(savable.name));
        availableCapacity -= savable.volume;
        Log.logger.info(savable.name + " was loaded to the truck.");
    }

    public static void unload(Savable savable) {
        availabilityCheck();
        availableCapacity += things.get(savable.name) * savable.volume;
        things.put(savable.name, 0);
        Log.logger.info(savable.name + " unloaded from truck.");
    }

    public static void unload() {
        if (!isAble) return;
        availableCapacity = capacity;
        things.clear();
    }

    public static void go(TimeManager timeManager) {
        availabilityCheck();
        if (availableCapacity == capacity)
            throw new GameErrorException("There is nothing on the truck!");
        timeManager.putAction(timeManager.getTime() + COMEBACK_TIME, null);
        Warehouse.removeSavableList(things);
        MissionMap.removeAnimalList(things);
        isAble = false;
        Log.logger.info("Truck sent.");
    }

    public static int comeBack(){
        int returnedValue = 0;
        for (Map.Entry<String, Integer> thing: things.entrySet())
            returnedValue+= Savable.getSavable(thing.getKey()).price*thing.getValue();
        things.clear();
        availableCapacity = capacity;
        isAble = true;
        return returnedValue;
    }

    public static HashMap<String, Integer> getThings() { return things; }
    public static boolean isIsAble() { return isAble; }

    public static int thingCount(String itemName) {
        things.putIfAbsent(itemName,0);
        return things.get(itemName);
    }
}
