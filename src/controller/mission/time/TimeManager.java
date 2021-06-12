package controller.mission.time;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeManager {
    private int time = 0;
    HashMap<Integer, ArrayList<Action>> actions = new HashMap<>();

    public void putAction(int time, Action action) {
        actions.computeIfAbsent(time, k -> new ArrayList<>());
        ArrayList<Action> tmp = actions.get(time);
        tmp.add(action);
        actions.put(time, tmp);
    }

    public void turn(int n) {
        //TODO
    }

    public int getTime() {
        return time;
    }
}
