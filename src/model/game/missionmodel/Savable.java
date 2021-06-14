package model.game.missionmodel;

import model.game.animals.predatoranimals.Bear;
import model.game.animals.predatoranimals.Lion;
import model.game.animals.predatoranimals.Tiger;
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

public enum Savable {
    EGG("egg",1,15, Egg.class),
    MILK("milk",1,25, Milk.class),
    FEATHER("feather",1,20, Feather.class),
    CLOTH("cloth",2,50, Cloth.class),
    FLOUR("flour",2,40, Flour.class),
    PACKET_MILK("pocket milk",2,60, PacketMilk.class),
    BREAD("bread",4,80, Bread.class),
    ICE_CREAM("ice cream",4,120, IceCream.class),
    SHIRT("shirt",4,100, Shirt.class),
    BEAR("bear",15,400, Bear.class),
    LION("lion",15,300, Lion.class),
    TIGER("tiger",15,500, Tiger.class);
    Class type;
    int volume;
    String name;
    int price;
    Savable(String name, int volume,int price,Class type) {
        this.type = type;
        this.volume = volume;
        this.name = name;
        this.price = price;
    }
    public static Savable getSavable(Product p){
        for (Savable savable : Savable.values())
            if (savable.type == p.getClass())
                return savable;
        return null;
    }
}
