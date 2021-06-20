package model.game.missionmodel;

import view.menu.exceptions.GameErrorException;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    static final int capacity = 30;
    static int availableCapacity;
    static HashMap<String,Integer> things;

    public static void makeWarehouse(){
        availableCapacity = capacity;
        things = new HashMap<>();
    }

    public static void addSavable(Savable savable) {
        if (availableCapacity >= savable.volume) {
            things.putIfAbsent(savable.name,0);
            things.put(savable.name, things.get(savable.name) + 1);
            availableCapacity -= savable.volume;
        }
        else throw new GameErrorException("Not enough space in the warehouse.");
    }

    public static boolean canGet(Savable savable){
        return availableCapacity>=savable.volume;
    }

    public static void removeSavable(Savable savable) {
        things.putIfAbsent(savable.name,0);
        if (things.get(savable.name)<1)
            throw new GameErrorException("Not enough "+savable+" in the warehouse.");
        things.put(savable.name, things.get(savable.name) - 1);
        availableCapacity += savable.volume;
    }

    public static HashMap<String, Integer> getThings() {
        return things;
    }

    public static void hasSavable(Savable savable, int count) {
        if (things.get(savable.name)<count)
            throw new GameErrorException("not enough "+savable.name+" in the warehouse.");
    }

    public static void removeSavableList(HashMap<String, Integer> list) {

        for (Map.Entry<String, Integer> entry : list.entrySet()) {
            if (things.containsKey(entry.getKey())) {
                if (things.get(entry.getKey()) < entry.getValue())
                    throw new GameErrorException("this should not happen!!");//TODO
                things.put(entry.getKey(), entry.getValue());
            }
        }
    }
}
