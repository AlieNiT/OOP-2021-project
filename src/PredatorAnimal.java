public enum PredatorAnimal {
    LION("lion", 1, 3),
    BEAR("bear", 1, 4),
    TIGER("tiger", 2, 4);

    private final String animalName;
    private final int speed;
    private final int cageCommands;

    PredatorAnimal(String animalName, int speed, int cageCommands) {
        this.animalName = animalName;
        this.speed = speed;
        this.cageCommands = cageCommands;
    }
}