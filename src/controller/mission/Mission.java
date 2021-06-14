package controller.mission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static view.menu.color.Colors.colorPrint;
import static view.menu.color.Colors.colorPrintln;

public class Mission {
    HashMap<String, Integer> objectives = new HashMap<>();
    public static int numOfMissions;
    private int initialCoins = 0;
    private final ArrayList<Integer> lionAppearanceTimes = new ArrayList<>();
    private final ArrayList<Integer> bearAppearanceTimes = new ArrayList<>();
    private final ArrayList<Integer> tigerAppearanceTimes = new ArrayList<>();
    public void set(String name, int number) {
        switch (name) {
            case "missions" -> numOfMissions = number;
            case "initialCoins" -> initialCoins = number;
            case "lion" -> lionAppearanceTimes.add(number);
            case "bear" -> bearAppearanceTimes.add(number);
            case "tiger" -> tigerAppearanceTimes.add(number);
            default -> objectives.put(name, number);
        }
    }

    public void printDetails() {
        for (Map.Entry<String,Integer> entry : objectives.entrySet()) {
            colorPrint(entry.getKey()+" : "+ entry.getValue()+" - ");
        }
        colorPrint(lionAppearanceTimes.toString());
        colorPrint(bearAppearanceTimes.toString());
        colorPrint(tigerAppearanceTimes.toString());
        colorPrintln("");
    }

    public HashMap<String, Integer> getObjectives() {
        return objectives;
    }

    public int getInitialCoins() {
        return initialCoins;
    }

    public ArrayList<Integer> getLionAppearanceTimes() {
        return lionAppearanceTimes;
    }

    public ArrayList<Integer> getBearAppearanceTimes() {
        return bearAppearanceTimes;
    }

    public ArrayList<Integer> getTigerAppearanceTimes() {
        return tigerAppearanceTimes;
    }
}