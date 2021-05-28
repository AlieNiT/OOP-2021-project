package model.game.workshops.primaryworkshop;

import model.game.products.finalproducts.FinalProduct;
import model.game.workshops.Workshop;

public abstract class PrimaryWorkshop extends Workshop {
    public abstract FinalProduct produce();
}
