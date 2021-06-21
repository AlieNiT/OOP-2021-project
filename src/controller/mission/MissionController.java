package controller.mission;

import changes.Purchasable;
import controller.mission.time.TimeManager;
import model.database.FileManager;
import model.database.User;
import model.game.animals.Animal;
import model.game.animals.farmanimals.Buffalo;
import model.game.animals.farmanimals.Chicken;
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
import view.menu.color.Colors;
import view.menu.exceptions.GameErrorException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import static changes.Utils.*;
import static controller.mission.Command.*;
import static model.game.missionmodel.MissionMap.MAP_SIZE;
import static model.game.missionmodel.Savable.getColorEmoji;
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
    HashMap<String,Integer> objectives;

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
        coins = mission.getInitialCoins()+user.getRewards()[mission.getMissionNumber()-1];
        objectives = mission.getObjectives();
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
                case UPGRADE_WORKSHOP -> upgradeWorkshop(matcher.group(1));
        }
        if (!(command == TRUCK_LOAD || command == TRUCK_UNLOAD)) {
            Truck.unload();
            Log.logger.info("Truck unloaded.");
        }
        if (success()) {
            if (timeManager.getTime() < mission.getRewardTime())
                user.setReward(mission.getMissionNumber(), mission.getFinishEarlyReward());
            if (mission.getMissionNumber() == user.getCurrentMission())
                user.setCurrentMission(user.getCurrentMission() + 1);
            try {
                FileManager.writeUserData(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.logger.info(user.getUsername() + " won mission " + mission.getMissionNumber() + " in " +
                    timeManager.getTime() + " time units.");
            throw new GameErrorException("You won in " + timeManager.getTime() + " time units!");
        }
        inquiry();
    }

    private void upgradeWorkshop(String workshopName) {
        int cost = workshops.get(workshopName).upgrade(coins);
        coins -= cost;
        Log.logger.info(cost + " coins spent to upgrade " + workshopName + ".");
    }

    private void pickUpProduct(int x, int y) {
        ArrayList<Product> products = MissionMap.getProducts(x, y);
        ArrayList<Product> pickedUpProducts = new ArrayList<>();
        for (Product product : products) {
            if (Warehouse.canGet(Objects.requireNonNull(getSavable(product)))) {
                Warehouse.addSavable(Objects.requireNonNull(getSavable(product)));
                MissionMap.removeProduct(product,true);
                pickedUpProducts.add(product);
                Log.logger.info(Savable.getSavableName(product) + " moved to warehouse.");
            }
        }
    }

    private void truckUnload(String itemName) { Truck.unload(getSavable(itemName)); }

    private void truckLoad(String itemName) {
        if (itemName.equals("chicken") || itemName.equals("turkey") || itemName.equals("buffalo"))
            MissionMap.hasAnimal(itemName, Truck.thingCount(itemName) + 1);
        else Warehouse.hasSavable(getSavable(itemName), Truck.thingCount(itemName) + 1);
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
        Log.logger.info(animalName + " purchased.");
    }

    private void truckGo() { Truck.go(timeManager); }

    private void inquiry() {
        Log.logger.info("Inquiry printed.");
        int[][] map = MissionMap.getGrassMap();
        colorPrintln("▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽▽");
        String[] c = productMap();
        String[] b = animalMap();
        String[] a = showGrassMap(map);
        for (int i = 0; i < MAP_SIZE + 3; i++) System.out.println(a[i]);
        System.out.println();
        for (int i = 0; i < MAP_SIZE + 3; i++) System.out.println(b[i]);
        System.out.println();
        for (int i = 0; i < MAP_SIZE + 3; i++) System.out.println(c[i]);
        System.out.println();
        colorPrintln("coins: " + coins);
        colorPrint("water left: ");
        if (waterFilling) System.out.println("Water is being pumped.");
        else wellStatus(waterLeft);
        colorPrintln("workshops built:");
        for (Workshop ws : workshops.values())
            System.out.println(ws.getName() + "("+"level "+((ws.isUpgraded())?"2":"1")+")" + ((ws.isWorking()) ? "is working" : "is not working"));
        HashMap<String,Integer> wareHouse = Warehouse.getThings();
        for (Map.Entry<String, Integer> entry: wareHouse.entrySet())
            if (entry.getValue() > 0)
                System.out.println(entry.getKey() + ": " + entry.getValue());
        colorPrintln("time: " + timeManager.getTime());
        truckStatus();
        warehouseStatus();
        System.out.println();
        colorPrintln("△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△△");
    }

    private static String[] productMap() {
        String[] productMap = new String[MAP_SIZE + 3];
        productMap[0] = Colors.nextColor("╔══════════════╗");
        productMap[1] = "║ PRODUCT MAP: ║";
        productMap[2] = "╚══════════════╝" + colorReset();
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
            productMap[i + 3] = "";
            for (int j = 0; j < MAP_SIZE; j++) {
                productMap[i + 3]  += productBoard(i, j) + "[" + spaces2(charNumber[i][j], maxChars);
                for (Product product : MissionMap.getProducts()) {
                    if (product != null && product.getX() == i && product.getY() == j) {
                        productMap[i+3] += Savable.getColorEmoji(Savable.getSavableName(product)) + productBoard(i, j);
                    }
                }
                productMap[i +3] += "]";
            }
            productMap[i + 3] += colorReset();
        }
        return productMap;
    }

    private static String[] animalMap() {
        String[] animalMap = new String[MAP_SIZE + 3];
        animalMap[0] = Colors.nextColor("╔═════════════╗");
        animalMap[1] = "║ ANIMAL MAP: ║";
        animalMap[2] = "╚═════════════╝" + colorReset();
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
            animalMap[i + 3] = "";
            for (int j = 0; j < MAP_SIZE; j++) {
                animalMap[i + 3] += animalBoard(i, j) + "[" + spaces2(charNumber[i][j], maxChars);
                for (Animal animal : MissionMap.getAnimals()) {
                    if (animal != null && animal.getX() == i && animal.getY() == j) {
                        String temp;
                        if (animal instanceof PredatorAnimal)
                            temp = changes.PredatorAnimal.getColorEmoji(changes.PredatorAnimal.getAnimalName((PredatorAnimal) animal));
                        else temp = changes.Purchasable.getColorEmoji(changes.Purchasable.getPurchasableName(animal));
                        animalMap[i + 3] += spaces2(charNumber[i][j], maxChars) + startAnimalHealth(animal) + temp;
                    }
                }
                animalMap[i + 3] += animalBoard(i, j) + spaces2(charNumber[i][j], maxChars) + "]";
            }
            animalMap[i + 3] += colorReset();
        }
        return animalMap;
    }

    private static String[] showGrassMap(int[][] map) {
        String[] grassMap = new String[MAP_SIZE + 3];
        grassMap[0] = Colors.nextColor("╔════════════╗");
        grassMap[1] = "║ GRASS MAP: ║";
        grassMap[2] = "╚════════════╝" + colorReset();
        int mostDigits = 1;
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++)
                if (digitCount(map[i][j]) > mostDigits)
                    mostDigits = digitCount(map[i][j]);
        for (int i = 0; i < MAP_SIZE; i++) {
            grassMap[i + 3] = "";
            for (int j = 0; j < MAP_SIZE; j++) {
                grassMap[i + 3] += startGrass(0) + spaces(map[i][j], mostDigits) + startGrass(map[i][j]) +
                "[" + map[i][j] + "]";
            }
            grassMap[i + 3] += colorReset();
        }
        return grassMap;
    }

    private void truckStatus() {
        if (!Truck.isIsAble()) {
            colorPrintln("\uD83D\uDE8F Truck is gone! \uD83D\uDD59");
            return;
        }
        int truckLength = 5;
        colorPrint(" ║");
        for (Map.Entry<String, Integer> set : Truck.getThings().entrySet()) {
            for (int i = 0; i < set.getValue(); i++) System.out.print(getColorEmoji(set.getKey()));
            truckLength += 2;
        }
        System.out.println();
        reverseColor();
        colorPrint("▟╚");
        for (int i = 0; i < truckLength; i++) System.out.print("═");
        System.out.println("╝");
        System.out.print("⦿");
        for (int i = 0; i < truckLength; i++) System.out.print(" ");
        System.out.println("⦿");
    }

    private void warehouseStatus() {
        int numOfItems = 0;
        int roofBlocks = 3;
        for (Map.Entry<String, Integer> set : Warehouse.getThings().entrySet())
            numOfItems += set.getValue();
        if (numOfItems > 11) roofBlocks = 5;
        for (int i=0; i < roofBlocks; i++) {
            for (int j = roofBlocks - i; j > 1; j--) System.out.print("    ");
            colorPrint("◢");
            for (int j = 1; j <= 2 * i; j++) System.out.print("████");
            System.out.println("◣");
        }
        for (Map.Entry<String, Integer> set : Warehouse.getThings().entrySet())
            for (int i = 0; i < set.getValue(); i++) System.out.print(getColorEmoji(set.getKey()));
    }

    private void plant(int x, int y) {
        if (waterLeft > 0) {
            MissionMap.plant(x, y);
            waterLeft -= 1;
            Log.logger.info("Grass planted in (" + x + ", " + y + ").");
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
        Log.logger.info(workshopName + " built.");
    }

    private void work(String workshopName) {
        Workshop workshop = workshops.get(workshopName);
        if (workshop != null) {
            workshop.consume();
            Log.logger.info(workshopName + " started working.");
        }
    }

    private void well() {
        if (waterFilling) throw new GameErrorException("Water is being pumped.");
        if (waterLeft != 0) throw new GameErrorException("Water has not been finished yet.");
        actions.computeIfAbsent(timeManager.getTime() + 3, k -> new ArrayList<>());
        ArrayList<PredatorAnimal> tmp = actions.get(timeManager.getTime() + 3);
        tmp.add(null);
        actions.put(timeManager.getTime() + 3, tmp);
        Log.logger.info("Pumped water from well.");
        waterFilling = true;
    }

    private void turn(int n) {
        for (int i = 0; i < n; i++) {
            coins += timeManager.turn();
            MissionMap.moveAnimals();
            Log.logger.info("Time turned.");
            turn();
            if (success()) {
                Log.logger.info(user.getUsername() + " won mission " + mission.getMissionNumber() + " in " +
                        timeManager.getTime() + " time units.");
                throw new GameErrorException("You won in " + timeManager.getTime() + " time units!");
            }
        }
    }

    private void cage(int x, int y) { MissionMap.cage(x, y); }

    private void coinCheck(int coinNeeded) {
        if (coins < coinNeeded)
            throw new GameErrorException("You do not have enough coins. " + coinNeeded + " coins needed.");
        coins -= coinNeeded;
        Log.logger.info(coinNeeded + " coins spent.");
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

    public User getUser() { return user; }

    private boolean success() { return coins >= mission.getSuccessCoins() && MissionMap.success(objectives); }
}
