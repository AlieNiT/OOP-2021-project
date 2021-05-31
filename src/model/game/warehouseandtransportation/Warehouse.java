package model.game.warehouseandtransportation;

import java.util.HashMap;

public class Warehouse {
    int capacity;
    int availableCapacity;
    HashMap<String,Integer> things = new HashMap<>();

    public void hasSavable(String savable,int count) throws Exception {
        if (things.get(savable)<count)
            throw new Exception("Not enough "+savable+" in the warehouse.");
    }

    public void addSavable(Savable savable) throws Exception {
        if (availableCapacity >=savable.volume) {
            things.put(savable.name,things.get(savable.name)+1);
            availableCapacity -= savable.volume;
            throw new Exception("Savable is collected and added to the warehouse.");
        }
    }

    public void removeSavable(Savable savable, int count) {
        things.put(savable.name, things.get(savable.name) - count);
        availableCapacity += savable.volume * count;
    }
}
