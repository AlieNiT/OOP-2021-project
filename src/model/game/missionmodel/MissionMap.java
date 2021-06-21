package model.game.missionmodel;

import changes.Purchasable;
import controller.mission.Log;
import model.game.Mappable;
import model.game.animals.Animal;
import model.game.animals.farmanimals.Buffalo;
import model.game.animals.farmanimals.Chicken;
import model.game.animals.farmanimals.FarmAnimal;
import model.game.animals.farmanimals.Turkey;
import model.game.animals.guardanimals.Cat;
import model.game.animals.guardanimals.Dog;
import model.game.animals.guardanimals.GuardAnimal;
import model.game.animals.predatoranimals.PredatorAnimal;
import model.game.products.Product;
import view.menu.exceptions.GameErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MissionMap {
    public static final int MAP_SIZE = 6;
    static int[][] grassMap;
    static ArrayList<Mappable>[][] map;
    static ArrayList<Product> products;
    static ArrayList<Animal> animals;
    static HashMap<String,Integer> totalProducts;
    public static void makeMap() {
        products = new ArrayList<>();
        animals = new ArrayList<>();
        grassMap = new int[MAP_SIZE][MAP_SIZE];
        map = new ArrayList[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++)
                map[i][j] = new ArrayList<>();
        totalProducts = new HashMap<>();
    }

    public static void plant(int x, int y) {
        grassMap[x][y] += 1;
    }

    public static void putProduct(Product product) {

        map[product.getX()][product.getY()].add(product);
        products.add(product);
    }

    public static void putAnimal(Animal animal) {
        map[animal.getX()][animal.getY()].add(animal);
        animals.add(animal);
        computeCollisions();
    }

    public static void removeProduct(Product product,boolean isSaved) {
        map[product.getX()][product.getY()].removeIf(mappable -> mappable == product);
        products.remove(product);
        if (isSaved) {
            totalProducts.putIfAbsent(Objects.requireNonNull(Savable.getSavable(product)).name, 0);
            totalProducts.put(Savable.getSavable(product).name, totalProducts.get(Savable.getSavable(product).name) + 1);
        }
    }

    public static ArrayList<Product> getProducts(int x, int y) {
        ArrayList<Product> list = new ArrayList<>();
        for (Mappable mappable :
                map[x][y]) {
            if (mappable instanceof Product)
                list.add((Product) mappable);
        }
        return list;
    }

    public static ArrayList<Animal> getAnimals(int x, int y) {
        ArrayList<Animal> list = new ArrayList<>();
        for (Mappable mappable :
                map[x][y]) {
            if (mappable instanceof Animal)
                list.add((Animal) mappable);
        }
        return list;
    }

    private static void computeCollisions() {
        ArrayList<Mappable> tmp = new ArrayList<>();
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j].stream().anyMatch(x -> x instanceof Cat)) {
                    for (Mappable mappable : map[i][j])
                        if (mappable instanceof Product)
                            try {
                                Warehouse.addSavable(Objects.requireNonNull(Savable.getSavable((Product) mappable)));
                                tmp.add(mappable);
                            } catch (GameErrorException ignored){ }

                    map[i][j].removeIf(tmp::contains);
                }
                while (map[i][j].stream().anyMatch(x -> x instanceof Dog) && map[i][j].stream().anyMatch(x -> x instanceof PredatorAnimal)) {
                    Animal dog = null;
                    Animal predator = null;
                    for (Mappable mappable : map[i][j]) {
                        if (mappable instanceof Dog && dog == null)
                            dog = (Animal) mappable;
                        else if (mappable instanceof PredatorAnimal && predator == null)
                            predator = (Animal) mappable;
                        if (dog != null && predator != null)
                            break;
                    }
                    animals.remove(dog);
                    animals.remove(predator);
                    map[i][j].remove(dog);
                    map[i][j].remove(predator);
                }
                if (map[i][j].stream().anyMatch(x -> x instanceof PredatorAnimal)) {//if the cell contains a predator animal
                    int finalI = i,finalJ = j;
                    animals.removeIf(x -> (!(x instanceof PredatorAnimal)&&x.getX()== finalI &&x.getY()== finalJ));
                    map[i][j].removeIf(x -> !(x instanceof PredatorAnimal));//remove all but predator animals
                }
                while (grassMap[i][j] > 0) {
                    Mappable hungriest = null;
                    int leastHealth = 100;
                    for (Mappable mappable : map[i][j]) {
                        if (mappable instanceof FarmAnimal&&((FarmAnimal)mappable).isStarving()&&((FarmAnimal)mappable).getHealth()<leastHealth) {
                            hungriest = mappable;
                            leastHealth = ((FarmAnimal)mappable).getHealth();
                        }
                    }
                    if (hungriest != null) {
                        grassMap[i][j] -= 1;
                        ((FarmAnimal) hungriest).graze();
                    }
                    else break;
                }
            }
    }

    public static void moveAnimals() {
        ArrayList<Animal> deadAnimals = new ArrayList();
        for (Animal animal : animals) {
            map[animal.getX()][animal.getY()].remove(animal);
            if (animal instanceof FarmAnimal)
                ((FarmAnimal) animal).move(MAP_SIZE, nearestGrass(animal.getX(), animal.getY()));
            else if (animal instanceof Cat)
                ((GuardAnimal) animal).move(MAP_SIZE, nearestProduct(animal.getX(), animal.getY()));
            else if (animal instanceof Dog)
                ((GuardAnimal) animal).move(MAP_SIZE, nearestPredator(animal.getX(), animal.getY()));
            else animal.move(MAP_SIZE);
            map[animal.getX()][animal.getY()].add(animal);
            if (animal instanceof FarmAnimal)
                if (((FarmAnimal) animal).reduceHealth()) {
                    deadAnimals.add(animal);
                    map[animal.getX()][animal.getY()].remove(animal);
                }
        }
        for (Animal deadAnimal : deadAnimals) animals.remove(deadAnimal);

        for (Animal animal : animals)
            if (animal instanceof PredatorAnimal)
                ((PredatorAnimal) animal).cageBreak();
        computeCollisions();
    }

    private static int[] nearestPredator(int x, int y) {
        if (map[x][y].stream().anyMatch(animal -> animal instanceof PredatorAnimal))
            return new int[]{0,0};
        for (int n = 0; n <= (MAP_SIZE - 1) * 2; n++)
            for (int i = 0; i < n; i++) {
                if (i + x >= 0 && n - i + y >= 0 && i + x < MAP_SIZE && n - i + y < MAP_SIZE && map[i + x][n - i + y].stream().anyMatch(animal -> animal instanceof PredatorAnimal))
                    return new int[]{i, n - i};

                else if (n - i + x >= 0 && -i + y >= 0 && n - i + x < MAP_SIZE && -i + y < MAP_SIZE && map[n - i + x][-i + y].stream().anyMatch(animal -> animal instanceof PredatorAnimal))
                    return new int[]{n - i, -i};

                else if (-i + x >= 0 && i - n + y >= 0 && -i + x < MAP_SIZE && i - n + y < MAP_SIZE && map[-i + x][i - n + y].stream().anyMatch(animal -> animal instanceof PredatorAnimal))
                    return new int[]{-i, i - n};

                else if (i - n + x >= 0 && i + y >= 0 && i - n + x < MAP_SIZE && i + y < MAP_SIZE && map[i - n + x][i + y].stream().anyMatch(animal -> animal instanceof PredatorAnimal))
                    return new int[]{i - n, i};
            }
        return null;
    }

    private static int[] nearestGrass(int x, int y) {
        if (grassMap[x][y]>0)
            return new int[]{0,0};
        for (int n = 0; n <= (MAP_SIZE - 1) * 2; n++) {
            for (int i = 0; i < n; i++) {
                if (i + x >= 0 && n - i + y >= 0 && i + x < MAP_SIZE && n - i + y < MAP_SIZE && grassMap[i + x][n - i + y] > 0)
                    return new int[]{i, n - i};

                else if (n - i + x >= 0 && -i + y >= 0 && n - i + x < MAP_SIZE && -i + y < MAP_SIZE && grassMap[n - i + x][-i + y] > 0)
                    return new int[]{n - i, -i};

                else if (-i + x >= 0 && i - n + y >= 0 && -i + x < MAP_SIZE && i - n + y < MAP_SIZE && grassMap[-i + x][i - n + y] > 0)
                    return new int[]{-i, i - n};

                else if (i - n + x >= 0 && i + y >= 0 && i - n + x < MAP_SIZE && i + y < MAP_SIZE && grassMap[i - n + x][i + y] > 0)
                    return new int[]{i - n, i};
            }
        }
        return null;
    }

    private static int[] nearestProduct(int x, int y) {
        if (map[x][y].stream().anyMatch(animal -> animal instanceof Product))
            return new int[]{0,0};
        for (int n = 0; n <= (MAP_SIZE - 1) * 2; n++) {
            for (int i = 0; i < n; i++) {
                if (i + x >= 0 && n - i + y >= 0 && i + x < MAP_SIZE && n - i + y < MAP_SIZE && map[i + x][n - i + y].stream().anyMatch(animal -> animal instanceof Product))
                    return new int[]{i, n - i};

                else if (n - i + x >= 0 && -i + y >= 0 && n - i + x < MAP_SIZE && -i + y < MAP_SIZE && map[n - i + x][-i + y].stream().anyMatch(animal -> animal instanceof Product))
                    return new int[]{n - i, -i};

                else if (-i + x >= 0 && i - n + y >= 0 && -i + x < MAP_SIZE && i - n + y < MAP_SIZE && map[-i + x][i - n + y].stream().anyMatch(animal -> animal instanceof Product))
                    return new int[]{-i, i - n};

                else if (i - n + x >= 0 && i + y >= 0 && i - n + x < MAP_SIZE && i + y < MAP_SIZE && map[i - n + x][i + y].stream().anyMatch(animal -> animal instanceof Product))
                    return new int[]{i - n, i};
            }
        }
        return null;
    }

    public static void cage(int x,int y) {
        for (Mappable mappable : map[x][y]) {
            if (mappable instanceof PredatorAnimal) {
                if(((PredatorAnimal) mappable).cageTry()) {
                    Log.logger.info(((PredatorAnimal) mappable).getName() + " caged.");
                    map[x][y].remove(mappable);
                    animals.remove(mappable);
                }
                return;
            }
        }
        throw new GameErrorException("No predator animals here.");
    }

    public static int[][] getGrassMap() { return grassMap; }

    public static ArrayList<Animal> getAnimals() {
        return animals;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static boolean success(HashMap<String, Integer> objectives) {
        for (Map.Entry<String, Integer> objective : objectives.entrySet()) {
            if (objective.getKey().equals(Purchasable.CHICKEN.name)) {
                if (animals.stream().filter(animal -> animal instanceof Chicken).count()<objective.getValue()) return false;}
            else if (objective.getKey().equals(Purchasable.TURKEY.name)) {
                if (animals.stream().filter(animal -> animal instanceof Turkey).count()<objective.getValue()) return false;}
            else if (objective.getKey().equals(Purchasable.BUFFALO.name)) {
                if (animals.stream().filter(animal -> animal instanceof Buffalo).count()<objective.getValue()) return false;}
            else if (!totalProducts.containsKey(objective.getKey())) return false;
            else if (objective.getValue()>totalProducts.get(objective.getKey())) return false;
        }
        return true;
    }

    public static void removeAnimal(String itemName, Integer count) {
        ArrayList<Animal> goneAnimals = new ArrayList<>();
        for (Animal animal : animals) {
            if (Purchasable.getPurchasableName(animal).equals(itemName)) {
                goneAnimals.add(animal);
                count-=1;
                map[animal.getX()][animal.getY()].remove(animal);
                if (count == 0) {
                    animals.removeAll(goneAnimals);
                    return;
                }
            }
        }

        throw new GameErrorException("There are not enough "+itemName+ " s in the map.");
    }

    public static void hasAnimal(String itemName,int count) {
        int n = 0;
        for (Animal animal : animals) {
            if (Purchasable.getPurchasableName(animal)!=null&&Purchasable.getPurchasableName(animal).equals(itemName))
                n++;
        }
        if (n<count) throw new GameErrorException("Not enough "+itemName+ " s in the map.");
    }

    public static void removeAnimalList(HashMap<String, Integer> things) {
        for (Map.Entry<String, Integer> entry :
                things.entrySet()) {
            if (entry.getKey().equals("chicken")||entry.getKey().equals("turkey")||entry.getKey().equals("buffalo"))
                removeAnimal(entry.getKey(),entry.getValue());
        }
    }
}
