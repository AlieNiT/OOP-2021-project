import controller.mission.time.TimeManager;
import model.game.Mapable;
import model.game.products.Product;
import model.game.products.rawproducts.Milk;
import view.menu.color.Colors;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static changes.Utils.digitCount;
import static changes.Utils.spaces;
import static view.menu.color.Colors.colorPrint;
import static view.menu.color.Colors.colorPrintln;

public class test {
    static int MAP_SIZE = 6;
    static ArrayList<Mapable>[][] map;

    public static void main(String[] args) {
        map = new ArrayList[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++)
                map[i][j] = new ArrayList<>();

        putProduct(new Milk(new TimeManager(), 0, 0), 1, 0);

        showProductMap(map, MAP_SIZE);

//        int[][] gameMap = new int[MAP_SIZE][MAP_SIZE];
//        int counter = 0;
//
//        for (int i = 0; i < MAP_SIZE; i++) {
//            for (int j = 0; j < MAP_SIZE; j++) {
//                gameMap[i][j] = counter;
//                counter++;
//            }
//        }
//        gameMap[2][3] = 300;
//        showMap(gameMap, MAP_SIZE);
    }

    // Shows Products
    private static void showProductMap(ArrayList<Mapable>[][] map, int length) {
        colorPrintln("PRODUCTS IN MAP:");
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                colorPrint((Arrays.toString(map[i][j].toArray()) + " "));
            }
            System.out.println();
        }
    }

    private static void showMap(int[][] map, int length) {
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
                Colors.colorPrint(spaces(map[i][j], mostDigits) + "[" +map[i][j] + "]");
            }
            System.out.println();
        }
    }

    static int digitCount(int number) {
    if (number == 0)
        return 1;
        int digits = 0;
        while (number != 0) {
            number = number / 10;
            ++digits;
        }
        return digits;
    }

    static String spaces(int number, int maxLength) {
        return " ".repeat(Math.max(0, maxLength - digitCount(number) + 1));
    }

    public static void putProduct(Product product, int x, int y) { map[x][y].add(product); }

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
}

