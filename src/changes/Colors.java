package changes;

import java.util.Random;

public class Colors {
    static Rainbow[] colors = Rainbow.values();
    private static int counter = new Random().nextInt()%colors.length;
    private static String nextColor(String str) {
        counter++;
        int code = colors[counter%colors.length].getCode();
        return "\u001b[38;5;" + code + "m" + str;
    }
    public static void colorPrint(String str) { System.out.print(Colors.nextColor(str)); }
    public static void colorPrintln(String str) { System.out.println(Colors.nextColor(str)); }
}
