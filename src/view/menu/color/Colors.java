package view.menu.color;

import model.game.animals.Animal;
import model.game.animals.farmanimals.FarmAnimal;
import model.game.animals.predatoranimals.PredatorAnimal;
import java.util.Random;
import static view.menu.color.ColorsRGB.*;
import static view.menu.color.MapColors.*;

public class Colors {
    static Rainbow[] colors = Rainbow.values();
    private static int counter = new Random().nextInt(colors.length);

    public static String nextColor(String str) {
        counter++;
        int code = colors[counter%colors.length].getCode();
        return "\u001b[38;5;" + code + "m" + str;
    }
    public static void colorPrint(String str) { System.out.print(Colors.nextColor(str)); }
    public static void colorPrintln(String str) { System.out.println(Colors.nextColor(str)); }
    public static void resetColor() { System.out.print("\033[0m"); }
    public static String colorReset() { return "\033[0m"; }

    public static String startGrass(int degree) {
        int code = switch (degree) {
            case 0 -> DRY.getCode();
            case 1 -> WATER1.getCode();
            case 2 -> WATER2.getCode();
            case 3 -> WATER3.getCode();
            case 4 -> WATER4.getCode();
            default -> WATER5.getCode();
        };
        return "\u001b[48;5;" + code + "m";
    }

    public static String startAnimalHealth(Animal animal) {
        if (animal instanceof model.game.animals.predatoranimals.PredatorAnimal) {
            return switch (((PredatorAnimal) animal).getCagesLeft()) {
                case 4 -> "\u001b[48;2;" + FOUR_CAGES_LEFT.getRGB() + "m";
                case 3 -> "\u001b[48;2;" + THREE_CAGES_LEFT.getRGB() + "m";
                case 2 -> "\u001b[48;2;" + TWO_CAGES_LEFT.getRGB() + "m";
                case 1 -> "\u001b[48;2;" + ONE_CAGE_LEFT.getRGB() + "m";
                default -> "";
            };
        }
        if (animal instanceof model.game.animals.farmanimals.FarmAnimal) {
            return switch (((FarmAnimal) animal).getHealth()) {
                case 100, 90 -> "\u001b[48;2;" + HEALTH5.getRGB() + "m";
                case 80, 70 -> "\u001b[48;2;" + HEALTH4.getRGB() + "m";
                case 60, 50 -> "\u001b[48;2;" + HEALTH3.getRGB() + "m";
                case 40, 30 -> "\u001b[48;2;" + HEALTH2.getRGB() + "m";
                case 20, 10 -> "\u001b[48;2;" + HEALTH1.getRGB() + "m";
                default -> "";
            };
        }
        return "";
    }

    public static String productBoard(int i, int j) {
        if (i%2 == j%2) return "\u001b[48;5;6m";
        else return "\u001b[48;5;4m";
    }

    public static String animalBoard(int i, int j) {
        if (i%2 == j%2) return "\u001b[48;2;255;204;233m";
        else return "\u001b[48;2;255;252;150m";
    }

    public static void wellStatus(int n) {
        resetColor();
        int WATER_CODE = 75;
        System.out.print("\u001b[38;5;" + WATER_CODE + "m");
        for (int i = 0; i < n; i++) System.out.print("▮");
        for (int i = 0; i < 5 - n; i++) System.out.print("▯");
        resetColor();
        System.out.println();
    }

    public static void reverseColor() { counter--; }
}
