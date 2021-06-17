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
    DOG("dog",Dog.class,100, "\u001b[38;5;222m", "\uD83D\uDC15\033[0m"),
    CAT("cat",Cat.class,150, "\u001b[38;5;141m", "\uD83D\uDC31\033[0m"),
    CHICKEN("chicken", Chicken.class,100, "\u001b[38;5;214m", "\uD83D\uDC24\033[0m"),
    BUFFALO("buffalo", Buffalo.class,400, "\u001b[38;5;59m", "\uD83D\uDC2E\033[0m"),
    TURKEY("turkey", Turkey.class,200, "\u001b[38;5;137m", "🦃\033[0m"),
    MILK_PACKAGING("milk packaging workshop", MilkPackagingWorkshop.class,400, null, null),
    WEAVING("weaving workshop", WeavingWorkshop.class,250, null, null),
    WINDMILL("windmill workshop", WindmillWorkshop.class,150, null, null),
    BAKERY("bakery", Bakery.class,250, null, null),
    ICE_CREAM("ice cream workshop", IceCream.class,550, null, null),
    SEWING("sewing workshop", SewingWorkshop.class,400, null, null);
    public final String name;
    public final Class type;
    public final int price;
    public final String color;
    public final String emoji;
    Purchasable(String name, Class T, int price, String color, String emoji){
        this.name = name;
        type = T;
        this.price = price;
        this.color = color;
        this.emoji = emoji;
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
    public String getColor() { return color; }
    public String getEmoji() { return emoji; }
}
