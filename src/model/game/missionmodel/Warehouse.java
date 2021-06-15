package model.game.missionmodel;

import view.menu.exceptions.GameErrorException;

import java.util.HashMap;

public class Warehouse {
    static final int capacity = 30;
    static int availableCapacity;
    static HashMap<String,Integer> things;

    public static void makeWarehouse(){
        availableCapacity = capacity;
        things = new HashMap<>();
    }
    public static void hasSavable(Savable savable, int count) {
        if (things.get(savable.name)<count)
            throw new GameErrorException("Not enough "+savable+" in the warehouse.");
    }

    public static void addSavable(Savable savable,int count) {
        if (availableCapacity >=savable.volume*count) {
            things.put(savable.name,things.get(savable.name)+count);
            availableCapacity -= savable.volume*count;
        }
        else throw new GameErrorException("Not enough space in the warehouse.");
    }

    public static void removeSavable(Savable savable, int count) {
        things.put(savable.name, things.get(savable.name) - count);
        availableCapacity += savable.volume * count;
    }
}