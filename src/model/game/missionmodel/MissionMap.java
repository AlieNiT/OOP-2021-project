package model.game.missionmodel;

import model.game.Mapable;
import model.game.animals.Animal;
import model.game.animals.farmanimals.FarmAnimal;
import model.game.animals.guardanimals.Cat;
import model.game.animals.guardanimals.Dog;
import model.game.animals.predatoranimals.PredatorAnimal;
import model.game.products.Product;
import view.menu.exceptions.GameErrorException;
import java.util.ArrayList;
import java.util.Objects;
import static changes.Utils.digitCount;
import static changes.Utils.spaces;
import static view.menu.color.Colors.colorPrint;
import static view.menu.color.Colors.colorPrintln;

public class MissionMap {
    public static final int MAP_SIZE = 6;
    static int[][] grassMap;
    static ArrayList<Mapable>[][] map;
    static ArrayList<Product> products;
    static ArrayList<Animal> animals;

    public static void makeMap() {
        products = new ArrayList<>();
        animals = new ArrayList<>();
        grassMap = new int[MAP_SIZE][MAP_SIZE];
        map = new ArrayList[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++)
                map[i][j] = new ArrayList<>();
    }

    // Shows Grass Map
    private static void showGrassMap(int[][] map, int length) {
        colorPrintln("GRASS MAP:");
        int mostDigits = 1;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (digitCount(map[i][j]) > mostDigits)
                    mostDigits = digitCount(map[i][j]);
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                colorPrint(spaces(map[i][j], mostDigits) + "[" +map[i][j] + "]");
            }
            System.out.println();
        }
    }

    // Shows Products
    private static void showProductMap(int[][] map, int length) {
        colorPrintln("PRODUCT IN MAP:");
    }


    public static void plant(int x, int y) { grassMap[x][y] += 1; }

    public static void putProduct(Product product, int x, int y) {
        map[x][y].add(product);
        products.add(product);
    }

    public static void putAnimal(Animal animal, int x, int y) {
        map[x][y].add(animal);
        animals.add(animal);
    }

    public static void removeProduct(Product product) {
        map[product.getX()][product.getY()].removeIf(mapable -> mapable == product);
        products.remove(product);
    }

    public static ArrayList<Product> getProducts(int x, int y) {
        ArrayList<Product> list = new ArrayList<>();
        for (Mapable mapable :
                map[x][y]) {
            if (mapable instanceof Product)
                list.add((Product) mapable);
        }
        return list;
    }

    public static ArrayList<Animal> getAnimals(int x, int y) {
        ArrayList<Animal> list = new ArrayList<>();
        for (Mapable mapable :
                map[x][y]) {
            if (mapable instanceof Animal)
                list.add((Animal) mapable);
        }
        return list;
    }

    private static void computeCollisions() {
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j].stream().anyMatch(x -> x instanceof Cat))
                    for (Mapable mapable : map[i][j])
                        if (mapable instanceof Product) {
                            try {
                                Warehouse.addSavable(Objects.requireNonNull(Savable.getSavable((Product) mapable)), 1);
                            } catch (GameErrorException ignored) {
                                continue;
                            }
                            map[i][j].remove(mapable);
                        }

                while (map[i][j].stream().anyMatch(x -> x instanceof Dog) && map[i][j].stream().anyMatch(x -> x instanceof PredatorAnimal)) {
                    boolean dog = true;
                    boolean predator = true;
                    for (Mapable mapable :
                            map[i][j]) {
                        if (mapable instanceof Dog && dog) {
                            map[i][j].remove(mapable);
                            dog = false;
                        } else if (mapable instanceof PredatorAnimal && predator) {
                            map[i][j].remove(mapable);
                            predator = false;
                        }
                        if (!dog && !predator)
                            break;
                    }
                }
                if (map[i][j].stream().anyMatch(x -> x instanceof PredatorAnimal))//if the cell contains a predator animal
                    map[i][j].removeIf(x -> !(x instanceof PredatorAnimal));//remove all but predator animals
            }
    }

    public static void moveAnimals() {
        for (Animal animal : animals) {
            map[animal.getX()][animal.getY()].remove(animal);
            if (animal instanceof FarmAnimal)
                ((FarmAnimal) animal).move(MAP_SIZE, nearestGrass(animal.getX(), animal.getY()));
            else animal.move(MAP_SIZE);
            map[animal.getX()][animal.getY()].add(animal);
            if (animal instanceof FarmAnimal) {
                if (grassMap[animal.getX()][animal.getY()] > 0&&((FarmAnimal) animal).isStarving()) {
                    grassMap[animal.getX()][animal.getY()] -= 1;
                    ((FarmAnimal) animal).graze();
                }
                else if (((FarmAnimal) animal).reduceHealth()) {
                    animals.remove(animal);
                    map[animal.getX()][animal.getY()].remove(animal);
                }
            }
        }
        for (Animal animal : animals)
            if (animal instanceof PredatorAnimal)
                ((PredatorAnimal) animal).cageBreak();
        computeCollisions();
    }

    private static int[] nearestGrass(int x, int y) {
        for (int n = 0; n <= (MAP_SIZE - 1) * 2; n++) {
            for (int i = 0; i < n; i++) {
                if (i + x > 0 && n - i + y > 0 && grassMap[i + x][n - i + y] > 0)
                    return new int[]{i, n - i};

                else if (n - i + x > 0 && -i + y > 0 && grassMap[n - i + x][-i + y] > 0)
                    return new int[]{n - i, -i};

                else if (-i + x > 0 && i - n + y > 0 && grassMap[-i + x][i - n + y] > 0)
                    return new int[]{-i, i - n};

                else if (i - n + x > 0 && i + y > 0 && grassMap[i - n + x][i + y] > 0)
                    return new int[]{i - n, i};
            }
        }
        return null;
    }

    public static void cage(int x,int y) {
        for (Mapable mapable :
                map[x][y]) {
            if (mapable instanceof PredatorAnimal){
                ((PredatorAnimal) mapable).cageTry();
                return;
            }
        }
        throw new GameErrorException("No predator animals in here.");
    }
}
