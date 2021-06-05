package changes;
public class Colors {
    public static int counter = 0;
    public static int remainder;
    public static final int numberOfRainbowColors = 15;
    public static int code;

    public static String randomColor(String str) {
        remainder = counter%numberOfRainbowColors;
        switch (remainder) {
            case 0 -> code = Rainbow.RED1.getCode();
            case 1 -> code = Rainbow.RED2.getCode();
            case 2 -> code = Rainbow.ORANGE1.getCode();
            case 3 -> code = Rainbow.ORANGE2.getCode();
            case 4 -> code = Rainbow.YELLOW1.getCode();
            case 5 -> code = Rainbow.YELLOW2.getCode();
            case 6 -> code = Rainbow.GREEN1.getCode();
            case 7 -> code = Rainbow.GREEN2.getCode();
            case 8 -> code = Rainbow.BLUE1.getCode();
            case 9 -> code = Rainbow.BLUE2.getCode();
            case 10 -> code = Rainbow.PURPLE1.getCode();
            case 11 -> code = Rainbow.PURPLE2.getCode();
            case 12 -> code = Rainbow.PINK1.getCode();
            case 13 -> code = Rainbow.PINK2.getCode();
            case 14 -> code = Rainbow.PINK3.getCode();
        }
        String output = "\u001b[38;5;" + code + "m" + str;
        counter++;
        return output;
    }

    public static void colorPrint(String str) { System.out.print(Colors.randomColor(str)); }
    public static void colorPrintln(String str) {
        if (str == null) {
            System.out.println();
            return;
        }
        System.out.println(Colors.randomColor(str));
    }
}
