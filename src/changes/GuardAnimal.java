package changes;

public enum GuardAnimal {
    DOG("dog", 100),
    CAT("cat", 150);

    public final String animalName;
    public final int price;

    GuardAnimal(String animalName, int price) {
        this.animalName = animalName;
        this.price = price;
    }
}