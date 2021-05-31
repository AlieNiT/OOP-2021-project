package model.game.warehouseandtransportation;

import java.util.HashMap;

public class Truck {
    static boolean isAble = true;
    static int capacity;
    static int availableCapacity;
    static HashMap<String,Integer> things = new HashMap<>();

    private static void availabilityCheck() throws Exception {
        if (!isAble) throw new Exception("The truck is not available.");
    }

    public static void load(Savable savable) throws Exception {
        availabilityCheck();
        if (availableCapacity<savable.volume)
            throw new Exception("The truck is almost full");
        things.put(savable.name,things.get(savable.name)+1);
        availableCapacity -= savable.volume;
    }

    public static void unload(Savable savable) throws Exception {
        availabilityCheck();
        availableCapacity += things.get(savable.name)*savable.volume;
        things.put(savable.name,0);
    }

    public static void go() throws Exception {
        availabilityCheck();
        if (availableCapacity == capacity)
            throw new Exception("There is nothing on the truck!");

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
