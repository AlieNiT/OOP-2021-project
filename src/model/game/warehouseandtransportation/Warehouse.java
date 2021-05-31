package model.game.warehouseandtransportation;

import java.util.HashMap;

public class Warehouse {
    int capacity;
    int availableCapacity;
    HashMap<String,Integer> things = new HashMap<>();

    public void hasSavable(Savable savable,int count) throws Exception {
        if (things.get(savable.name)<count)
            throw new Exception("Not enough "+savable+" in the warehouse.");
    }

    public void addSavable(Savable savable,int count) throws Exception {
        if (availableCapacity >=savable.volume*count) {
            things.put(savable.name,things.get(savable.name)+count);
            availableCapacity -= savable.volume*count;
        }
        else throw new Exception("Not enough space in the warehouse.");
    }

    public void removeSavable(Savable savable, int count) {
        things.put(savable.name, things.get(savable.name) - count);
        availableCapacity += savable.volume * count;
    }
}
