package controller.mission;

import changes.Purchasable;
import controller.mission.time.TimeManager;
import model.database.User;
import model.game.animals.Animal;
import model.game.animals.farmanimals.Buffalo;
import model.game.animals.farmanimals.Chicken;
import model.game.animals.farmanimals.FarmAnimal;
import model.game.animals.farmanimals.Turkey;
import model.game.animals.guardanimals.Cat;
import model.game.animals.guardanimals.Dog;
import model.game.animals.predatoranimals.Bear;
import model.game.animals.predatoranimals.Lion;
import model.game.animals.predatoranimals.PredatorAnimal;
import model.game.animals.predatoranimals.Tiger;
import model.game.missionmodel.MissionMap;
import model.game.missionmodel.Savable;
import model.game.missionmodel.Truck;
import model.game.missionmodel.Warehouse;
import model.game.products.Product;
import model.game.workshops.Workshop;
import model.game.workshops.primaryworkshop.MilkPackagingWorkshop;
import model.game.workshops.primaryworkshop.WeavingWorkshop;
import model.game.workshops.primaryworkshop.WindmillWorkshop;
import model.game.workshops.secondaryworkshop.Bakery;
import model.game.workshops.secondaryworkshop.IceCreamWorkshop;
import model.game.workshops.secondaryworkshop.SewingWorkshop;
import view.menu.exceptions.GameErrorException;
import java.util.*;
import java.util.regex.Matcher;

import static changes.Utils.*;
import static controller.mission.Command.findCommand;
import static controller.mission.Command.getMatcher;
import static model.game.missionmodel.MissionMap.MAP_SIZE;
import static model.game.missionmodel.MissionMap.getGrassMap;
import static model.game.missionmodel.Savable.getSavable;
import static view.menu.color.Colors.*;

public class MissionController {
    User user;
    Mission mission;
    TimeManager timeManager;
    HashMap<Integer, ArrayList<PredatorAnimal>> actions = new HashMap<>();
    int coins, waterLeft = 0;
    boolean waterFilling = false;
    HashMap<String, Workshop> workshops;

    public MissionController(User user, Mission mission) {
        this.user = user;
        this.mission = mission;
        timeManager = new TimeManager();
        workshops = new HashMap<>();
        Truck.makeTruck();
        Random random = new Random();
        ArrayList<PredatorAnimal> tmp;
        for (int n : mission.getBearAppearanceTimes()) {
            actions.computeIfAbsent(n, k -> new ArrayList<>());
            tmp = actions.get(n);
            tmp.add(new Bear(timeManager,random.nextInt(MAP_SIZE), random.nextInt(MAP_SIZE)));
            actions.put(n, tmp);
        }
        for (int n : mission.getLionAppearanceTimes()) {
            actions.computeIfAbsent(n, k -> new ArrayList<>());
            tmp = actions.get(n);
            tmp.add(new Lion(timeManager,random.nextInt(MAP_SIZE), random.nextInt(MAP_SIZE)));
            actions.put(n, tmp);
        }
        for (int n : mission.getTigerAppearanceTimes()) {
            actions.computeIfAbsent(n, k -> new ArrayList<>());
            tmp = actions.get(n);
            tmp.add(new Tiger(timeManager,random.nextInt(MAP_SIZE), random.nextInt(MAP_SIZE)));
            actions.put(n, tmp);
        }
        Warehouse.makeWarehouse();
        MissionMap.makeMap();
        coins = mission.getInitialCoins();
    }

    public void runCommand(String input) {
        Command command = findCommand(input);
        Matcher matcher = getMatcher(input, command);
        if (matcher.find())
            switch (command) {
                case CAGE -> cage(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                case TURN -> turn(Integer.parseInt(matcher.group(1)));
                case WELL -> well();
                case BUILD -> build(matcher.group(1));
                case WORK -> work(matcher.group(1));
                case PLANT -> plant(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                case INQUIRY -> inquiry();
                case TRUCK_GO -> truckGo();
                case BUY_ANIMAL -> buyAnimal(matcher.group(1));
                case TRUCK_LOAD -> truckLoad(matcher.group(1));
                case TRUCK_UNLOAD -> truckUnload(matcher.group(1));
                case PICK_UP_PRODUCT -> pickUpProduct(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
            }
        inquiry();
    }

    private void pickUpProduct(int x, int y) {
        ArrayList<Product> products = MissionMap.getProducts(x, y);
        ArrayList<Product> pickedUpProducts = new ArrayList<>();
        for (Product product : products) {
            if (Warehouse.canGet(Objects.requireNonNull(getSavable(product)))) {
                Warehouse.addSavable(Objects.requireNonNull(getSavable(product)));
                MissionMap.removeProduct(product);
                pickedUpProducts.add(product);
            }
        }
    }

    private void truckUnload(String itemName) {
        Truck.unload(getSavable(itemName));
    }

    private void truckLoad(String itemName) {
        Warehouse.hasSavable(getSavable(itemName),1 );
        Warehouse.removeSavable(getSavable(itemName));
        Truck.load(getSavable(itemName));
    }

    private void buyAnimal(String animalName) {
        coinCheck(Purchasable.getCost(animalName, "animal"));
        Animal animal;
        int x = new Random().nextInt(6);
        int y = new Random().nextInt(6);
        switch (animalName) {
            case "chicken" -> animal = new Chicken(x, y, timeManager);
            case "turkey" -> animal = new Turkey(x, y, timeManager);
            case "buffalo" -> animal = new Buffalo(x, y, timeManager);
            case "cat" -> animal = new Cat(x, y);
            case "dog" -> animal = new Dog(x, y);
            default -> throw new GameErrorException("wrong name!");
        }
        MissionMap.putAnimal(animal);
    }

    private void truckGo() { Truck.go(timeManager); }

    private void inquiry() {
        int[][] map = MissionMap.getGrassMap();
        colorPrintln("▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽");
        colorPrint(""); // to change color
        showGrassMap(map);
        animalMap();
        colorPrint("");
        for (Animal animal : MissionMap.getAnimals())
            if (animal != null) System.out.println(animal.getName() + "  " + animal.getX() + " " + animal.getY() + " " +
                    ((animal instanceof FarmAnimal) ? ((FarmAnimal) animal).getHealth() + "%" : "") +
                    ((animal instanceof PredatorAnimal) ? ((PredatorAnimal) animal).getCagesLeft() : ""));
        productMap();
        colorPrint("");
        for (Product product : MissionMap.getProducts())
            colorPrintln(Objects.requireNonNull(getSavable(product)).name+ " " + product.getX() + " " + product.getY());
        colorPrintln("coins: " + coins);
        colorPrintln("water left: " + waterLeft);
        colorPrintln("workshops built:");
        for (Workshop ws : workshops.values())
            System.out.println(ws.getName() + " " + ((ws.isWorking()) ? "is working" : "is not working"));
        HashMap<String,Integer> wareHouse = Warehouse.getThings();
        for (Map.Entry<String, Integer> entry: wareHouse.entrySet())
            if (entry.getValue() > 0)
                System.out.println(entry.getKey() + ": " + entry.getValue());
        colorPrintln("time: " + timeManager.getTime());
        colorPrintln("△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△");
    }

    private static void productMap() {
        colorPrintln("product map:");
        resetColor();
        int maxChars = 0;
        int tmp = 0;
        int[][] charNumber = new int[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++) {
                for (Product product : MissionMap.getProducts())
                    if (product != null && product.getX() == i && product.getY() == j) tmp++;
                charNumber[i][j] = tmp;
                if (tmp > maxChars) maxChars = tmp;
                tmp = 0;
            }

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.print("[" + spaces2(charNumber[i][j], maxChars));
                for (Product product : MissionMap.getProducts()) {
                    if (product != null && product.getX() == i && product.getY() == j) {
                        System.out.print(Savable.getColorEmoji(Savable.getSavableName(product)));
                    }
                }
                System.out.print("]");
            }
            resetColor();
            System.out.println();
        }
    }

    private static void animalMap() {
        colorPrintln("animal map:");
        resetColor();
        int maxChars = 0;
        int tmp = 0;
        int[][] charNumber = new int[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++) {
                for (Animal animal : MissionMap.getAnimals())
                    if (animal != null && animal.getX() == i && animal.getY() == j) tmp++;
                charNumber[i][j] = tmp;
                if (tmp > maxChars) maxChars = tmp;
                tmp = 0;
            }

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                startGrass(getGrassMap()[i][j]);
                System.out.print("[" + spaces2(charNumber[i][j], maxChars));
                for (Animal animal : MissionMap.getAnimals()) {
                    if (animal != null && animal.getX() == i && animal.getY() == j) {
                        String temp;
                        if (animal instanceof PredatorAnimal)
                            temp =changes.PredatorAnimal.getColorEmoji(changes.PredatorAnimal.getAnimalName((PredatorAnimal) animal));
                        else temp = changes.Purchasable.getColorEmoji(changes.Purchasable.getPurchasableName(animal));
                        System.out.print(temp);
                        startGrass(getGrassMap()[i][j]);
                    }
                }
                System.out.print(spaces2(charNumber[i][j], maxChars) + "]");
            }
            resetColor();
            System.out.println();
        }
    }

    private static void showGrassMap(int[][] map) {
        colorPrintln("grass map:");
        resetColor();
        int mostDigits = 1;
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++)
                if (digitCount(map[i][j]) > mostDigits)
                    mostDigits = digitCount(map[i][j]);
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                startGrass(0);
                System.out.print(spaces(map[i][j], mostDigits));
                startGrass(map[i][j]);
                System.out.print("[" + map[i][j] + "]");
            }
            resetColor();
            System.out.println();
        }
    }

    private void plant(int x, int y) {
        if (waterLeft > 0) {
            MissionMap.plant(x, y);
            waterLeft -= 1;
        } else throw new GameErrorException("Water supply empty! Water must be pumped from the well first.");
    }

    private void build(String workshopName) {
        for (String name : workshops.keySet())
            if (name.equals(workshopName))
                throw new GameErrorException("This workshop has already been built.");
        coinCheck(Purchasable.getCost(workshopName, "workshop"));
        Workshop workshop;
        switch (workshopName) {
            case "milk packaging workshop" -> workshop = new MilkPackagingWorkshop(timeManager);
            case "weaving workshop" -> workshop = new WeavingWorkshop(timeManager);
            case "windmill workshop" -> workshop = new WindmillWorkshop(timeManager);
            case "bakery" -> workshop = new Bakery(timeManager);
            case "ice cream workshop" -> workshop = new IceCreamWorkshop(timeManager);
            case "sewing workshop" -> workshop = new SewingWorkshop(timeManager);
            default -> throw new GameErrorException("wrong workshop name");
        }
        workshops.put(workshopName, workshop);
    }

    private void work(String workshopName) {
        Workshop workshop = workshops.get(workshopName);
        if (workshop != null)
            workshop.consume();
    }

    private void well() {
        if (waterFilling)
            throw new GameErrorException("Water is being filled.");
        actions.computeIfAbsent(timeManager.getTime()+3,k->new ArrayList<>());
        ArrayList<PredatorAnimal> tmp = actions.get(timeManager.getTime()+3);
        tmp.add(null);
        actions.put(timeManager.getTime() + 3, tmp);
        waterFilling = true;
    }

    private void turn(int n) {
        for (int i = 0; i < n; i++) {
            coins += timeManager.turn();
            MissionMap.moveAnimals();
            turn();
        }
    }

    private void cage(int x, int y) {
        MissionMap.cage(x, y);
    }

    private void coinCheck(int coinNeeded) {
        if (coins < coinNeeded)
            throw new GameErrorException("You don't have enough coins(" + coins + ")");
        coins -= coinNeeded;
    }

    private void turn() {
        ArrayList<PredatorAnimal> action = actions.get(timeManager.getTime());
        if (action == null) return;
        for (PredatorAnimal animal : action) {
            if (animal == null) {
                waterLeft = 5;
                waterFilling = false;
            }
            else MissionMap.putAnimal(animal);
        }
    }

    public User getUser() {
        return user;
    }
    public void increaseCoin(int n){
        coins+=n;
    }
}
