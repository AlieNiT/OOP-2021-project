package changes;

public class Utils {

    public static int digitCount(int number) {
        if (number == 0)
            return 1;
        int digits = 0;
        while (number != 0) {
            number = number / 10;
            ++digits;
        }
        return digits;
    }

    // number is the number itself, not no. of digits
    public static String spaces(int number, int maxLength) {
        return " ".repeat(Math.max(0, maxLength - digitCount(number) + 1));
    }

    // number = number of characters
    public static String spaces2(int number, int maxLength) {
        return "  ".repeat(Math.max(0, maxLength - number));
    }

}
