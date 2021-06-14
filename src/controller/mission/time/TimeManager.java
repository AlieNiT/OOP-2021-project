package controller.mission.time;

import model.game.Actioner;
import model.game.Producer;
import model.game.missionmodel.MissionMap;
import model.game.missionmodel.Truck;
import model.game.products.Product;

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

    public void turn(int n) {
        for (int i = 0; i < n; i++) {
            turn();
        }
    }
    public void turn() {
        time +=1;
        if (actions.get(time)!=null)
            for (Actioner actioner:actions.get(time)) {
                if (actioner instanceof Producer)
                    ((Producer) actioner).produce();
                else if (actioner instanceof Product)
                    MissionMap.removeProduct((Product)actioner);
                else Truck.comeBack();
            }
    }
    public int getTime() {
        return time;
    }
}
