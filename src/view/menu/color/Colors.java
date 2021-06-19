package view.menu.color;

import java.util.Random;
import static view.menu.color.GrassShades.*;

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

    public static String productBoard(int i, int j) {
        if (i%2 == j%2) return "\u001b[48;5;6m";
        else return "\u001b[48;5;4m";
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
    public static void reverseColor(int times) { for (int i = 0; i < times; i++) reverseColor(); }
}
