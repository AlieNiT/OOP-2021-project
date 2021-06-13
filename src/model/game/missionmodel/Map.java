package model.game.missionmodel;

import model.game.Mapable;
import model.game.animals.Animal;
import model.game.animals.guardanimals.Cat;
import model.game.animals.guardanimals.Dog;
import model.game.animals.predatoranimals.PredatorAnimal;
import model.game.products.Product;
import view.menu.exceptions.GameErrorException;

import java.util.ArrayList;

public class Map {
    private static final int MAP_SIZE = 6;
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

    public static void plant(int x, int y) {
        grassMap[x][y] += 1;
    }

    public static void putProduct(Product product, int x, int y) {
        map[x][y].add(product);
        products.add(product);
    }

    public static void putAnimal(Animal animal, int x, int y) {
        map[x][y].add(animal);
        animals.add(animal);
    }

    public static void removeProduct(int x, int y, Product product) {
        //TODO
        map[x][y].removeIf(mapable -> mapable == product);
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
                                Warehouse.addSavable(Savable.getSavable((Product) mapable),1);
                            } catch (GameErrorException ignored) { continue; }
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
//    public static void removeRottenProducts() {
//        for (int i = 0; i < MAP_SIZE; i++) {
//            for (int j = 0; j < MAP_SIZE; j++) {
//                map[i][j].removeIf(mapable -> mapable instanceof Product && ((Product) mapable).isRotten());
//                products.removeIf(mapable -> mapable != null && mapable.isRotten());
//            }
//        }
//    }

    public static void moveAnimals() {
        for (Animal animal :
                animals) {
            map[(int) animal.getX()][(int) animal.getY()].remove(animal);
            animal.move(MAP_SIZE);
            map[(int) animal.getX()][(int) animal.getY()].add(animal);
        }
        computeCollisions();
    }
}
