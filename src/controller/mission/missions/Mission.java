package controller.mission.missions;

import model.database.Database;
import java.util.ArrayList;

public class Mission {
    private static int missionNumber = 0;
    private static int initialCoins = 0;
    private static int chicken = 0;
    private static int turkey = 0;
    private static int buffalo = 0;
    private static int egg = 0;
    private static int feather = 0;
    private static int milk = 0;
    private static int cloth = 0;
    private static int pocketMilk = 0;
    private static int flour = 0;
    private static int bread = 0;
    private static int iceCream = 0;
    private static int shirt = 0;
    private static ArrayList<Integer> lionAppearanceTimes = new ArrayList<>();
    private static ArrayList<Integer> bearAppearanceTimes = new ArrayList<>();
    private static ArrayList<Integer> tigerAppearanceTimes = new ArrayList<>();

    public static void set(String name, int number) {
        switch (name) {
            case "#" -> {
                missionNumber = number;
                Mission mission = new Mission();
                Database.addMission(mission);
            }
            case "initialCoins" -> initialCoins = number;
            case "chicken" -> chicken = number;
            case "turkey" -> turkey = number;
            case "buffalo" -> buffalo = number;
            case "egg" -> egg = number;
            case "feather" -> feather = number;
            case "milk" -> milk = number;
            case "cloth" -> cloth = number;
            case "pocketMilk" -> pocketMilk = number;
            case "flour" -> flour = number;
            case "bread" -> bread = number;
            case "iceCream" -> iceCream = number;
            case "shirt" -> shirt = number;
            case "lion" -> lionAppearanceTimes.add(number);
            case "bear" -> bearAppearanceTimes.add(number);
            case "tiger" -> tigerAppearanceTimes.add(number);
        }
    }

    public static Mission findMission(int missionNumber) {
        for (Mission mission : Database.getMissions()){
            if (mission.getMissionNumber() == missionNumber) {
                return mission;
            }
        }
        return null;
    }

    public static int getMissionNumber() { return missionNumber; }
}