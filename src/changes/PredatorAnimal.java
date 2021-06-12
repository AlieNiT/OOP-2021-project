package changes;

public enum PredatorAnimal {
    LION("lion", 1, 3),
    BEAR("bear", 1, 4),
    TIGER("tiger", 2, 4);

    public final String animalName;
    public final int speed;
    public final int cageCommands;

    PredatorAnimal(String animalName, int speed, int cageCommands) {
        this.animalName = animalName;
        this.speed = speed;
        this.cageCommands = cageCommands;
    }
}