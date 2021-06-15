package model.game.missionmodel;

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
    CAGED_BEAR("bear",15,400, CagedBear.class),
    CAGED_LION("lion",15,300, CagedLion.class),
    CAGED_TIGER("tiger",15,500, CagedTiger.class);
    final Class type;
    final int volume;
    final String name;
    final int price;
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
    public static Savable getSavable(String name){
        for (Savable savable : Savable.values())
            if (savable.name.equals(name))
                return savable;
        throw new GameErrorException("Wrong item name");
    }
}
