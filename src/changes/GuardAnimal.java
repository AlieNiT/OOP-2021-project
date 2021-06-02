package changes;

public enum GuardAnimal {
    DOG("dog", 100),
    CAT("cat", 150);

    private final String animalName;
    private final int price;

    GuardAnimal(String animalName, int price) {
        this.animalName = animalName;
        this.price = price;
    }
}