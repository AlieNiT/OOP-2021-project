package model.game.missionmodel;

import changes.Purchasable;
import model.game.animals.farmanimals.Buffalo;
import model.game.animals.farmanimals.Chicken;
import model.game.animals.farmanimals.Turkey;
import model.game.products.CagedPredators.CagedBear;
import model.game.products.CagedPredators.CagedLion;
import model.game.products.CagedPredators.CagedTiger;
import model.game.products.Product;
import model.game.products.finalproducts.Bread;
import model.game.products.finalproducts.IceCream;
import model.game.products.finalproducts.Shirt;
import model.game.products.processedproducts.Cloth;
import model.game.products.processedproducts.Flour;
import model.game.products.processedproducts.PacketMilk;
import model.game.products.rawproducts.Egg;
import model.game.products.rawproducts.Feather;
import model.game.products.rawproducts.Milk;
import view.menu.exceptions.GameErrorException;

import java.util.Arrays;
import java.util.Optional;

public enum Savable {
    EGG("egg", 1, 15, Egg.class, "\u001b[38;5;15m", "\uD83E\uDD5A\033[0m"),
    MILK("milk", 1, 25, Milk.class, "\u001b[38;5;117m", "\uD83E\uDD5B\033[0m"),
    FEATHER("feather", 1, 20, Feather.class, "\u001b[38;5;204m", "\uD83E\uDDA2\033[0m"),
    CLOTH("cloth", 2, 50, Cloth.class, "\u001b[38;5;211m", "\uD83C\uDF80\033[0m"),
    FLOUR("flour", 2, 40, Flour.class, "\u001b[38;5;231m", "\uD83C\uDF5A\033[0m"),
    PACKET_MILK("pocket milk", 2, 60, PacketMilk.class, "\u001b[38;5;156m", "\uD83E\uDDC3\033[0m"),
    BREAD("bread", 4, 80, Bread.class, "\u001b[38;5;180m", "\uD83C\uDF5E\033[0m"),
    ICE_CREAM("ice cream", 4, 120, IceCream.class, "\u001b[38;5;213m", "\uD83C\uDF66\033[0m"),
    SHIRT("shirt", 4, 100, Shirt.class, "\u001b[38;5;49m", "\uD83D\uDC57\033[0m"),
    CAGED_BEAR("bear", 15, 400, CagedBear.class, "\u001b[38;5;145m", "\uD83D\uDCE6\033[0m"),
    CAGED_LION("lion", 15, 300, CagedLion.class, "\u001b[38;5;145m", "\uD83D\uDCE6\033[0m"),
    CAGED_TIGER("tiger", 15, 500, CagedTiger.class, "\u001b[38;5;145m", "\uD83D\uDCE6\033[0m"),
    CHICKEN("chicken", 2, 50, Chicken.class, "\u001b[38;5;214m", "\uD83D\uDC24\033[0m"),
    TURKEY("turkey", 4, 100, Turkey.class, "\u001b[38;5;52m", "🦃\033[0m"),
    BUFFALO("buffalo", 8, 200, Buffalo.class, "\u001b[38;5;59m", "\uD83D\uDC2E\033[0m");

    public final Class type;
    public final int volume;
    public final String name;
    public final int price;
    public final String color;
    public final String emoji;

    Savable(String name, int volume, int price, Class type, String color, String emoji) {
        this.type = type;
        this.volume = volume;
        this.name = name;
        this.price = price;
        this.color = color;
        this.emoji = emoji;
    }

    public static Savable getSavable(Product p) {
        for (Savable savable : Savable.values())
            if (savable.type == p.getClass())
                return savable;
        return null;
    }

    public static Savable getSavable(String name) {
        for (Savable savable : Savable.values())
            if (savable.name.equals(name))
                return savable;
        throw new GameErrorException("Wrong item name");
    }

    public String getName() {
        return name;
    }

    public static String getSavableName(Product product) {
        Optional<Savable> optional = Arrays.stream(Savable.values()).filter(x -> x.type == product.getClass()).findFirst();
        return optional.map(Savable::getName).orElse(null);
    }

    public static String getColorEmoji(String name) {
        for (Savable savable : Savable.values())
            if (savable.name.equals(name))
                return savable.color + savable.emoji;
        return null;
    }

}
