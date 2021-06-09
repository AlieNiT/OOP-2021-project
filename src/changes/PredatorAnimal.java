package changes;

public enum PredatorAnimal {
    LION("lion", 1, 3, 300),
    BEAR("bear", 1, 4, 400),
    TIGER("tiger", 2, 4, 500);

    private final String animalName;
    private final int speed;
    private final int cageCommands;
    private final int sellingPrice;

    PredatorAnimal(String animalName, int speed, int cageCommands, int sellingPrice) {
        this.animalName = animalName;
        this.speed = speed;
        this.cageCommands = cageCommands;
        this.sellingPrice = sellingPrice;
    }
}