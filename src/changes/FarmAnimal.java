package changes;

public enum FarmAnimal {
    CHICKEN("chicken", 100, "egg", 2),
    TURKEY("turkey", 200, "feather", 3),
    BUFFALO("buffalo", 400, "milk", 5);

    public final String animalName;
    public final int price;
    public final String product;
    public final int productionTime;

    FarmAnimal(String animalName, int price, String product, int productionTime) {
        this.animalName = animalName;
        this.price = price;
        this.product = product;
        this.productionTime = productionTime;
    }
}
