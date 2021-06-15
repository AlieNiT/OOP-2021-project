package changes;

import model.game.animals.farmanimals.Buffalo;
import model.game.animals.farmanimals.Chicken;
import model.game.animals.farmanimals.Turkey;
import model.game.animals.guardanimals.Cat;
import model.game.animals.guardanimals.Dog;
import model.game.products.finalproducts.IceCream;
import model.game.workshops.primaryworkshop.MilkPackagingWorkshop;
import model.game.workshops.primaryworkshop.WeavingWorkshop;
import model.game.workshops.primaryworkshop.WindmillWorkshop;
import model.game.workshops.secondaryworkshop.Bakery;
import model.game.workshops.secondaryworkshop.SewingWorkshop;
import view.menu.exceptions.GameErrorException;

public enum Purchasable {
    DOG("dog",Dog.class,100),
    CAT("cat",Cat.class,150),
    CHICKEN("chicken", Chicken.class,100),
    BUFFALO("buffalo", Buffalo.class,400),
    TURKEY("turkey", Turkey.class,200),
    MILK_PACKAGING("milk packaging workshop", MilkPackagingWorkshop.class,400),
    WEAVING("weaving workshop", WeavingWorkshop.class,250),
    WINDMILL("windmill workshop", WindmillWorkshop.class,150),
    BAKERY("bakery", Bakery.class,250),
    ICE_CREAM("ice cream workshop", IceCream.class,550),
    SEWING("sewing workshop", SewingWorkshop.class,400);
    public final String name;
    public final Class type;
    public final int price;
    Purchasable(String name, Class T, int price){
        this.name = name;
        type = T;
        this.price = price;
    }
    public static int getCost(String name,String type){
        for (Purchasable purchasable : Purchasable.values()) {
            if (purchasable.name.equals(name))
                return purchasable.price;
        }
        throw new GameErrorException("Wrong name of "+type);
    }
    public final String getName(){
        return name;
    }
}
