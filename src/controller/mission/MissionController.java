package controller.mission;

import controller.mission.time.TimeManager;
import model.database.User;
import model.game.animals.predatoranimals.Bear;
import model.game.animals.predatoranimals.Lion;
import model.game.animals.predatoranimals.PredatorAnimal;
import model.game.animals.predatoranimals.Tiger;
import model.game.missionmodel.MissionMap;
import model.game.missionmodel.Truck;
import model.game.missionmodel.Warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;

import static controller.mission.Command.findCommand;
import static controller.mission.Command.getMatcher;

public class MissionController {
    User user;
    Mission mission;
    TimeManager timeManager;
    HashMap<Integer, ArrayList <PredatorAnimal>> predatorAnimals = new HashMap<>();
    int coins;

    public MissionController(User user,Mission mission) {
        this.user = user;
        this.mission = mission;
        timeManager = new TimeManager();
        Truck.makeTruck();
        Random random = new Random();
        ArrayList<PredatorAnimal> tmp;
        for (int n: mission.getBearAppearanceTimes()) {
            predatorAnimals.computeIfAbsent(n,k -> new ArrayList<>());
            tmp = predatorAnimals.get(n);
            tmp.add(new Bear(random.nextInt(MissionMap.MAP_SIZE),random.nextInt(MissionMap.MAP_SIZE)));
            predatorAnimals.put(n,tmp);
        }
        for (int n: mission.getLionAppearanceTimes()) {
            predatorAnimals.computeIfAbsent(n,k -> new ArrayList<>());
            tmp = predatorAnimals.get(n);
            tmp.add(new Lion(random.nextInt(MissionMap.MAP_SIZE),random.nextInt(MissionMap.MAP_SIZE)));
            predatorAnimals.put(n,tmp);
        }
        for (int n: mission.getTigerAppearanceTimes()) {
            predatorAnimals.computeIfAbsent(n,k -> new ArrayList<>());
            tmp = predatorAnimals.get(n);
            tmp.add(new Tiger(random.nextInt(MissionMap.MAP_SIZE),random.nextInt(MissionMap.MAP_SIZE)));
            predatorAnimals.put(n,tmp);
        }
        Warehouse.makeWarehouse();
        MissionMap.makeMap();
        coins = mission.getInitialCoins();
    }

    public void runCommand(String input){
        Command command = findCommand(input);
        Matcher matcher = getMatcher(input,command);
        switch (command) {
            case CAGE -> cage(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(2)));
            case TURN -> turn(Integer.parseInt(matcher.group(1)));
            case WELL -> well();
            case WORK -> work(matcher.group(1));
            case PLANT -> plant(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(2)));
            case INQUIRY -> inquiry();
            case TRUCK_GO -> truckGo();
            case BUY_ANIMAL -> buyAnimal(matcher.group(1));
            case TRUCK_LOAD -> truckLoad(matcher.group(1));
            case TRUCK_UNLOAD -> truckUnload(matcher.group(1));
            case PICK_UP_PRODUCT -> pickUpProduct(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(2)));
        }
    }

    private void pickUpProduct(int x, int y) {
    }

    private void truckUnload(String itemName) {
    }

    private void truckLoad(String itemName) {
    }

    private void buyAnimal(String animalName) {
    }

    private void truckGo() {
    }

    private void inquiry() {
    }

    private void plant(int x, int y) {
    }

    private void work(String workshopName) {
    }

    private void well() {
    }

    private void turn(int n) {
    }

    private void cage(int x, int y) {
    }

}
