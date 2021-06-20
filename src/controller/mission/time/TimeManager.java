package controller.mission.time;

import model.game.Actioner;
import model.game.Producer;
import model.game.missionmodel.MissionMap;
import model.game.missionmodel.Truck;
import model.game.products.Product;
import model.game.workshops.Workshop;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeManager {
    private int time = 0;
    HashMap<Integer, ArrayList<Actioner>> actions = new HashMap<>();

    public void putAction(int time, Actioner actioner) {
        actions.computeIfAbsent(time, k -> new ArrayList<>());
        ArrayList<Actioner> tmp = actions.get(time);
        tmp.add(actioner);
        actions.put(time, tmp);
    }

    public int turn() {
        int coins = 0;
        time +=1;
        if (actions.get(time)!=null) {
            for (Actioner actioner:actions.get(time)) {
                if (actioner instanceof Producer && (actioner instanceof Workshop || MissionMap.getAnimals().contains(actioner))) {
                    ((Producer) actioner).produce();
                } else if (actioner instanceof Product)
                    MissionMap.removeProduct((Product)actioner,false);
                else if (actioner == null) coins += Truck.comeBack();
            }
        }
        return coins;
    }
    public int getTime() {
        return time;
    }
}
