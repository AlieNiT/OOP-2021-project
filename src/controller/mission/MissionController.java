package controller.mission;

import controller.mission.time.TimeManager;
import model.database.User;
import model.game.missionmodel.Truck;
import model.game.missionmodel.Warehouse;

import java.util.regex.Matcher;

import static controller.mission.Command.findCommand;
import static controller.mission.Command.getMatcher;

public class MissionController {
    User user;
    Mission mission;
    TimeManager timeManager;


    public MissionController(User user,Mission mission) {
        this.user = user;
        this.mission = mission;
        timeManager = new TimeManager();
        Truck.makeTruck();
        Warehouse.makeWarehouse();
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
