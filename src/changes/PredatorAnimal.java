package changes;

import model.game.animals.predatoranimals.Bear;
import model.game.animals.predatoranimals.Lion;
import model.game.animals.predatoranimals.Tiger;

import java.util.Arrays;
import java.util.Optional;

public enum PredatorAnimal {
    LION("lion", 1, 3, "\u001b[38;5;220m", "\uD83E\uDD81\033[0m", Lion.class),
    BEAR("bear", 1, 4, "\u001b[38;5;130m", "\uD83D\uDC3B\033[0m", Bear.class),
    TIGER("tiger", 2, 4, "\u001b[38;5;208m", "\uD83D\uDC2F\033[0m", Tiger.class);

    public final String animalName;
    public final int speed;
    public final int cageCommands;
    public final String color;
    public final String emoji;
    public final Class type;

    PredatorAnimal(String animalName, int speed, int cageCommands, String color, String emoji, Class T) {
        this.animalName = animalName;
        this.speed = speed;
        this.cageCommands = cageCommands;
        this.color = color;
        this.emoji = emoji;
        this.type = T;
    }
    public static String getColorEmoji(String name){
        for (PredatorAnimal predatorAnimal : PredatorAnimal.values())
            if (predatorAnimal.animalName.equals(name))
                return predatorAnimal.color + predatorAnimal.emoji;
        return null;
    }

    public String getAnimalName() { return animalName; }

    public static String getAnimalName(model.game.animals.predatoranimals.PredatorAnimal predatorAnimal) {
        Optional<PredatorAnimal> optional = Arrays.stream(PredatorAnimal.values()).filter(x -> x.type == predatorAnimal.getClass()).findFirst();
        return optional.map(animal -> animal.animalName).orElse(null);
    }

}