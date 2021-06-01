import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Command {
    BUY_ANIMAL("buy animal (\\w+)"),
    PICK_UP_PRODUCT("pickup (\\d+) (\\d+)"),
    WELL("well"),
    PLANT("plant (\\d+) (\\d+)"),
    WORK("work (\\w+)"),
    CAGE("cage (\\d+) (\\d+)"),
    TURN("turn (\\d+)"),
    TRUCK_LOAD("truck load (\\w+)"),
    TRUCK_UNLOAD("truck unload (\\w+)"),
    TRUCK_GO("truck go"),
    INQUIRY("inquiry");

    private final Pattern pattern;
    private final String regex;

    Command(String regex) {
        pattern = Pattern.compile(regex);
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, Command command) {
        return command.pattern.matcher(input.toLowerCase(Locale.ROOT));
    }

    public static Command findCommand(String input) {
        for (Command command : Command.values()) {
            if(Pattern.matches(command.regex, input.toLowerCase(Locale.ROOT)))
                return command;
        }
        return null;
    }
}
