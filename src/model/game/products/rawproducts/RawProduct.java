package model.game.products.rawproducts;

import controller.mission.time.TimeManager;
import model.game.products.Product;

public abstract class RawProduct extends Product {
    public RawProduct(TimeManager timeManager) {
        super(timeManager);
    }
}
