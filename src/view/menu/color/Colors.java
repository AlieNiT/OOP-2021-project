package view.menu.color;

import java.util.Random;
import static view.menu.color.GrassShades.*;

public class Colors {
    static Rainbow[] colors = Rainbow.values();
    private static int counter = new Random().nextInt(colors.length);
    private static String nextColor(String str) {
        counter++;
        int code = colors[counter%colors.length].getCode();
        return "\u001b[38;5;" + code + "m" + str;
    }
    public static void colorPrint(String str) { System.out.print(Colors.nextColor(str)); }
    public static void colorPrintln(String str) { System.out.println(Colors.nextColor(str)); }
    public static void resetColor() { System.out.print("\033[0m"); }

    public static void startGrass(int degree) {
        int code = switch (degree) {
            case 0 -> DRY.getCode();
            case 1 -> WATER1.getCode();
            case 2 -> WATER2.getCode();
            case 3 -> WATER3.getCode();
            case 4 -> WATER4.getCode();
            default -> WATER5.getCode();
        };
        System.out.print("\u001b[48;5;" + code + "m");
    }

    public static void productBoard(int i, int j) {
        if (i%2 == j%2) System.out.print("\u001b[48;5;6m");
        else System.out.print("\u001b[48;5;4m");
    }
}
