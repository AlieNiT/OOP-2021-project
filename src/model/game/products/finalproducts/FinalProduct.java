package model.game.products.finalproducts;

import controller.mission.time.TimeManager;
import model.game.products.Product;

public abstract class FinalProduct extends Product {
    public FinalProduct(TimeManager timeManager) {
        super(timeManager);
    }
}
