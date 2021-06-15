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

    public static String spaces(int number, int maxLength) {
        return " ".repeat(Math.max(0, maxLength - digitCount(number) + 1));
    }

}
