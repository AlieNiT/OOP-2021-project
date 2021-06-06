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
            case "#":
                missionNumber = number;
                Mission mission = new Mission();
                Database.addMission(mission);
                break;
            case "initialCoins":
                initialCoins = number;
                break;
            case "chicken":
                chicken = number;
                break;
            case "turkey":
                turkey = number;
                break;
            case "buffalo":
                buffalo = number;
                break;
            case "egg":
                egg = number;
                break;
            case "feather":
                feather = number;
                break;
            case "milk":
                milk = number;
                break;
            case "cloth":
                cloth = number;
                break;
            case "pocketMilk":
                pocketMilk = number;
                break;
            case "flour":
                flour = number;
                break;
            case "bread":
                bread = number;
                break;
            case "iceCream":
                iceCream = number;
                break;
            case "shirt":
                shirt = number;
                break;
            case "lion":
                lionAppearanceTimes.add(number);
                break;
            case "bear":
                bearAppearanceTimes.add(number);
                break;
            case "tiger":
                tigerAppearanceTimes.add(number);
                break;
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

    public static int getInitialCoins() { return initialCoins; }
    public static int getMissionNumber() { return missionNumber; }
}