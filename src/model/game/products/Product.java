package model.game.products;

public abstract class Product {
    int volume;
    int price;

    public Product(int volume, int price) {
        this.volume = volume;
        this.price = price;
    }
}
