package model.game.workshops.secondaryworkshop;

import model.game.products.processedproducts.ProcessedProduct;
import model.game.workshops.Workshop;

public abstract class SecondaryWorkshop extends Workshop {
    public abstract ProcessedProduct produce();
}
