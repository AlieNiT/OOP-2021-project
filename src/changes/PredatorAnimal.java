package changes;

public enum PredatorAnimal {
    LION("lion", 1, 3, "\u001b[38;5;220m", "\uD83E\uDD81\033[0m"),
    BEAR("bear", 1, 4, "\u001b[38;5;130m", "\uD83D\uDC3B\033[0m"),
    TIGER("tiger", 2, 4, "\u001b[38;5;208m", "\uD83D\uDC2F\033[0m");

    public final String animalName;
    public final int speed;
    public final int cageCommands;
    public final String color;
    public final String emoji;

    PredatorAnimal(String animalName, int speed, int cageCommands, String color, String emoji) {
        this.animalName = animalName;
        this.speed = speed;
        this.cageCommands = cageCommands;
        this.color = color;
        this.emoji = emoji;
    }
    public static String getColorEmoji(String name){
        for (PredatorAnimal predatorAnimal : PredatorAnimal.values())
            if (predatorAnimal.animalName.equals(name))
                return predatorAnimal.color + predatorAnimal.emoji;
        return null;
    }

    public String getAnimalName() { return animalName; }
}